package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ConcederCreditoConjuntoContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1385] - Conceder Crédito Conjunto de Contas 
 *
 * @author Davi Menezes
 * @date 19/10/2012
 */
public class ConcederCreditoConjuntoContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		//Seta o retorno
        ActionForward retorno = actionMapping.findForward("concederCreditoConjuntoConta");
        
        //Form
        ConcederCreditoConjuntoContaActionForm form = (ConcederCreditoConjuntoContaActionForm) actionForm;
        
        //Fachada
        Fachada fachada = this.getFachada();
        
        //Recuperar a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = 
    		this.getFachada().pesquisarParametrosDoSistema();
        
        //Recuperar o usuário logado na sessão
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Validar Registro Atendimento
        this.validarRegistroAtendimento(form, fachada);
        
        /*
         * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O CONCEDER CREDITO CONJUNTO DE CONTAS TIVER SIDO
         * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
         */
        Collection colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");
        
        Collection colecaoContas = (Collection) sessao.getAttribute("colecaoContas");
        
        String valorCreditoInformado = form.getValorCredito();
        
        String registroAtendimento = form.getNumeroRA();
        
        //RECEBENDO OS DADOS PARA RETIFICAÇÃO DA(S) CONTA(S)
        
        //DATA DE VENCIMENTO
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataVencimentoConta;
        
        try{
        	dataVencimentoConta = formatoData.parse(form.getVencimentoConta());
        	
        	// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 19/08/2010 
			// Analista :  Aryed Lins.
        	// [FS0007] - Validar data de vencimento.		
			// -------------------------------------------------------------------------------------------
    				
			// Caso data corrente seja posterior a data corrente mais a quantidade de dias parametro.
			Date dataCorrente = new Date();	
			
			Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
			
			Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
			
			// E o usuário não tenha permissão especial.	
			boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = 
				this.getFachada().verificarPermissaoEspecial(
					PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
					usuarioLogado);
			
			// 1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
			if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && 
				temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
				
				throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
			}
			
			Calendar data1985 = new GregorianCalendar(1985, 1, 1);
			if(Util.compararData(dataVencimentoConta, data1985.getTime()) == -1){
				throw new ActionServletException("atencao.data_vencimento_menor_1985");
			}
				
		} catch (ParseException ex) {
        	dataVencimentoConta = null;
        }
        
        //CONTA_MOTIVO_REVISAO
        ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
        contaMotivoRetificacao.setId(new Integer(form.getMotivoRetificacaoID()));
        
        ConcederCreditoConjuntoContaHelper helper = null;
        
        Collection<ConcederCreditoConjuntoContaHelper> colecaoHelper = 
        		new ArrayList<ConcederCreditoConjuntoContaHelper>();
        
        CreditoTipo creditoTipo = null;
        LancamentoItemContabil lancamentoItemContabil = null;
        
        FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
        filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID, CreditoTipo.DESCONTOS_CONCEDIDOS));
        filtroCreditoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoTipo.LANCAMENTO_ITEM_CONTABIL);
        
        Collection<?> colCreditoTipo = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
        if(!Util.isVazioOrNulo(colCreditoTipo)){
        	creditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colCreditoTipo);
        	lancamentoItemContabil = creditoTipo.getLancamentoItemContabil();
        }
        
        AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;
        
        FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
        filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
        		FiltroAtendimentoMotivoEncerramento.ID, AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO));
        
        Collection<?> colAtendimentoMotivoEncerramento = fachada.pesquisar(
        		filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
        if(!Util.isVazioOrNulo(colAtendimentoMotivoEncerramento)){
        	atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento)
        			Util.retonarObjetoDeColecao(colAtendimentoMotivoEncerramento);
        }
        
        /*
         * FORMATAR DADOS PARA RETIFICAÇÃO DA CONTA
         */
        if(!Util.isVazioOrNulo(colecaoContas)){
        	Conta conta = null;
        	Iterator<?> it = colecaoContas.iterator();
        	while(it.hasNext()){
        		conta = (Conta) it.next();
        		helper = new ConcederCreditoConjuntoContaHelper();
        		
        		helper.setConta(conta);
        		helper.setIdConta(conta.getId());
        		helper.setAnoMesReferencia(conta.getReferencia());
        		helper.setContaMotivoRetificacao(contaMotivoRetificacao);
        		
        		if(dataVencimentoConta != null){
        			helper.setDataVencimento(dataVencimentoConta);
        		}else{
        			helper.setDataVencimento(conta.getDataVencimentoConta());
        		}
        		
        		helper.setIdRegistroAtendimento(Integer.parseInt(registroAtendimento));
        		helper.setConsumoTarifa(conta.getConsumoTarifa());
        		helper.setLigacaoAguaSituacao(conta.getLigacaoAguaSituacao());
        		helper.setConsumoAgua(conta.getConsumoAgua());
        		helper.setLeituraAnteriorAgua(conta.getNumeroLeituraAnterior());
        		helper.setLeituraAtualAgua(conta.getNumeroLeituraAtual());
        		helper.setLigacaoEsgotoSituacao(conta.getLigacaoEsgotoSituacao());
        		helper.setConsumoEsgoto(conta.getConsumoEsgoto());
        		helper.setPercentualEsgoto(conta.getPercentualEsgoto());
        		helper.setVolumePoco(conta.getNumeroVolumePoco());
        		helper.setLeituraAnteriorPoco(conta.getNumeroLeituraAnteriorPoco());
        		helper.setLeituraAtualPoco(conta.getNumeroLeituraAtualPoco());
        		helper.setUsuarioLogado(usuarioLogado);
        		helper.setCreditoTipo(creditoTipo);
        		helper.setLancamentoItemContabil(lancamentoItemContabil);
        		helper.setCreditoOrigem(new CreditoOrigem(CreditoOrigem.DESCONTOS_CONDICIONAIS));
        		helper.setValorCredito(Util.formatarMoedaRealparaBigDecimal(form.getValorCredito()));
        		helper.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
        		
        		colecaoHelper.add(helper);
        	}
        	
        	//adicionado por Davi Menezes - 04/08/2009
        	Map parametros = new HashMap();
        	parametros.put("colecaoHelper",colecaoHelper);
        	
        	Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.CONCEDER_CREDITO_CONJUNTO_CONTAS,
             		this.getUsuarioLogado(httpServletRequest));
            
        	sessao.removeAttribute("anoMes");
            sessao.removeAttribute("anoMesFim");
            sessao.removeAttribute("dataVencimentoContaInicial");
            sessao.removeAttribute("dataVencimentoContaFinal");
            sessao.removeAttribute("indicadorContaPaga");
            sessao.removeAttribute("colecaoContas");
            sessao.removeAttribute("colecaoContaMotivoRetificacao");
            
            //Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPageConcederCreditoConjuntoConta", "OK");
        	
        }else{
        	throw new ActionServletException("atencao.nao.conceder.credito");
        }
		
        return retorno;
	}
	
	/**
	  * Pesquisar Registro de Atendimento pelo Número da RA
	  * 
	  * @author Davi Menezes
	  * @date 19/10/2012
	  */
	private void validarRegistroAtendimento(ConcederCreditoConjuntoContaActionForm form, Fachada fachada){
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, form.getNumeroRA()));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, ConstantesSistema.PENDENTE));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO, "110"));
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
		 
		Collection<?> colRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		if(Util.isVazioOrNulo(colRegistroAtendimento)){
			throw new ActionServletException("atencao.registro_atendimento.inexistente");
		}
	}
	
//	private BigDecimal obterValorCredito(Conta conta, String valorCreditoInformado){
//		BigDecimal retorno = new BigDecimal("0.00");
//
//		// Valor de Água
//		if (conta.getValorAgua() != null) {
//			retorno = retorno.add(conta.getValorAgua());
//		}
//		// Valor de Esgoto
//		if (conta.getValorEsgoto() != null) {
//			retorno = retorno.add(conta.getValorEsgoto());
//		}
//		// Valor de Débitos
//		if (conta.getDebitos() != null) {
//			retorno = retorno.add(conta.getDebitos());
//		}
//		// Valor de Créditos
//		if (conta.getValorCreditos() != null) {
//			retorno = retorno.subtract(conta.getValorCreditos());
//		}
//
//		// Valor do Imposto
//		//if (conta.getValorImposto() != null) {
//			//retorno = retorno.subtract(conta.getValorImposto());
//		//}
//
//		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
//		
//		BigDecimal valorCredito = Util.formatarMoedaRealparaBigDecimal(valorCreditoInformado);
//		if(valorCredito.compareTo(new BigDecimal("0")) == 0){
//			return retorno;
//		}else{
//			if(valorCredito.compareTo(retorno) == 1){
//				return retorno;
//			}else{
//				return valorCredito;
//			}
//		}
//	}
	
}
