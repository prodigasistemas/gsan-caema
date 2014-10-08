package gcom.gui.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.BoletimMedicaoJustificativaPenalidade;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.cobranca.AcoesPenalidadeGrupoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1153] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Cobran�a
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class ExibirSolicitarGeracaoBoletimMedicaoCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirSolicitarGeracaoBoletimMedicaoCobranca");
		
		GerarRelatorioBoletimMedicaoCobrancaForm form = 
			(GerarRelatorioBoletimMedicaoCobrancaForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
	
		Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        Usuario u = (Usuario) sessao.getAttribute("usuarioLogado");
        if(fachada.verificarPermissaoEspecial(113,u)){        	
        	httpServletRequest.setAttribute("temPermissao", u);
        }
		if (form.getMesAnoReferencia() != null
				&& !form.getMesAnoReferencia().equals("")) {
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			
			if (!Util.validarMesAno(form.getMesAnoReferencia())) {
				throw new ActionServletException("atencao.mes.ano.informado.invalido");
			}
			
			if (Util.compararAnoMesReferencia(anoMes, sistemaParametro.getAnoMesFaturamento(), ">")){
				throw new ActionServletException("atencao.mes.ano.informado.maior.faturamento.atual", null, sistemaParametro.getAnoMesFaturamento().toString());
			}
		}
		
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("1")) {
			
			
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			Integer idGrupoCobranca = new Integer(form.getGrupoCobranca());
			
			// [FS0002] � A��es n�o encerradas no cronograma.
			Integer contador = fachada.pesquisarAcoesEncerradasCronograma(
					anoMes, idGrupoCobranca);

			if (contador != null && contador > 0){
				throw new ActionServletException("atencao.faltam.acoes.encerradas.cronograma.grupo");
			}
			
			//[FS0003] � Boletim j� existe.
			FiltroCobrancaBoletimMedicao filtroCobrancaBoletimMedicao = new FiltroCobrancaBoletimMedicao();
			filtroCobrancaBoletimMedicao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA, anoMes));
			filtroCobrancaBoletimMedicao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID, idGrupoCobranca));
			Collection colecaoCobrancaBoletimMedicao = fachada.pesquisar(
					filtroCobrancaBoletimMedicao, CobrancaBoletimMedicao.class.getName());
			
			if (colecaoCobrancaBoletimMedicao != null
					&& !colecaoCobrancaBoletimMedicao.isEmpty()) {
				throw new ActionServletException("atencao.boletim.ja.existe");
			}
			
			String pesquisaPenalidade = httpServletRequest.getParameter("pesquisarPenalidades");
			
			if(pesquisaPenalidade !=null && pesquisaPenalidade.equals("true")){				
				
				Collection<AcoesPenalidadeGrupoHelper> acoes = fachada.pesquisarAcoesPenalidadesPorGrupoMes(idGrupoCobranca, anoMes);
				if(acoes !=null && !acoes.isEmpty()){
					httpServletRequest.setAttribute("exibeTabelaPenalidade",true);
					httpServletRequest.setAttribute("acoesPenalidades",	acoes);
				}
				else{
					throw new ActionServletException("atencao.penalidades_inexistentes");
					//httpServletRequest.setAttribute("naoExistePenalidade",true);
				}
			}
			else if(pesquisaPenalidade !=null && pesquisaPenalidade.equals("false")){
				httpServletRequest.removeAttribute("exibeTabelaPenalidade");
			}
			else{
				// Usuario logado no sistema
				Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
				
				String[] idAcoes = httpServletRequest.getParameterValues("idAcao");
				String[] justificativas = httpServletRequest.getParameterValues("justificativa");				
				ArrayList<String> justi = new ArrayList<String>();
				if(idAcoes != null && idAcoes.length > 0){
					if(justificativas !=null && justificativas.length >= 1){
						if(idAcoes.length == justificativas.length){
							for(int i = 0; i < idAcoes.length; i++){
								if(justificativas[i] != null){ 
									if(justificativas[i].trim().equals("")){
										throw new ActionServletException("atencao.campo_selecionado.obrigatorio","Justificativa(as) com caracteres v�lidos");
									}
									if(justificativas[i].length() >150){
										throw new ActionServletException("atencao.quantidade_caracter_justificativa");
									}
									else{
										justi.add(justificativas[i]);
									}
								}
							}
						}
						else{
							for(int i =0; i < justificativas.length; i++){
								if(justificativas[i] !=null && !justificativas[i].trim().equals("")){
									if(justificativas[i].length() >150){
										throw new ActionServletException("atencao.quantidade_caracter_justificativa");
									}
									else{
										justi.add(justificativas[i]);
									}
								}
							}
						}
					}				
				
					if(!justi.isEmpty()){
						for(int i = 0; i < idAcoes.length; i++){
							
							BoletimMedicaoJustificativaPenalidade bmpj = new BoletimMedicaoJustificativaPenalidade();
							bmpj.setJustificativa(justi.get(i));
							bmpj.setCobrancaAcaoCronograma(fachada.pesquisarCobrancaAcaoCronograma(idGrupoCobranca, anoMes, Integer.parseInt(idAcoes[i])));
							bmpj.setUltimaAlteracao(new Date());
							
							fachada.inserir(bmpj);
						}
					}else{
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio","a(s) Justificativa(s) da(s) A��o(�es)");
					}
				}
				
				//[UC1151] Gerar Boletim de Medi��o
				fachada.gerarBoletimMedicao(idGrupoCobranca,anoMes,usuarioLogado);
				
				retorno = actionMapping
				.findForward("telaSucesso");
				
				montarPaginaSucesso(httpServletRequest, " Gerar Boletim de Medi��o encaminhado para processamento. ","Voltar","/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?menu=sim");
				
				return retorno;
			}
			
		}

		// Tipo da Opera��o igual a Emitir Boletim
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("2")) {

			// Pesquisa Localidade Inicial
			if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")
					&& (form.getDescricaoLocalidadeInicial() == null || form.getDescricaoLocalidadeInicial().equals(""))){
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(colecaoLocalidade.isEmpty()){
					form.setIdLocalidadeInicial("");
					form.setDescricaoLocalidadeInicial("Localidade Inexistente");
					httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrado","exception");
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
				}else{
					Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
					form.setIdLocalidadeInicial(localidade.getId().toString());
					form.setDescricaoLocalidadeInicial(localidade.getDescricao());
					if(form.getIdLocalidadeFinal() == null || form.getIdLocalidadeFinal().equals("")){
						form.setIdLocalidadeFinal(localidade.getId().toString());
						form.setDescricaoLocalidadeFinal(localidade.getDescricao());
					}
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
					
				}
			}

			// Pesquisa Localidade Final
			if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")
					&&(form.getDescricaoLocalidadeFinal() == null || form.getDescricaoLocalidadeFinal().equals(""))){
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(colecaoLocalidade.isEmpty()){
					form.setIdLocalidadeFinal("");
					form.setDescricaoLocalidadeFinal("Localidade Inexistente");
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
					httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrado","exception");
				}else{
					Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
					form.setIdLocalidadeFinal(localidade.getId().toString());
					form.setDescricaoLocalidadeFinal(localidade.getDescricao());
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				}
			}
		}

		if (httpServletRequest.getAttribute("colecaoCobrancaGrupo") == null
				|| httpServletRequest.getAttribute("colecaoCobrancaGrupo").equals("")){
			// Popular o combo-box de Grupo de Cobran�a 
			this.pesquisarCobrancaGrupo(httpServletRequest);
		}

		if (httpServletRequest.getAttribute("colecaoGerenciaRegional") == null
				|| httpServletRequest.getAttribute("colecaoGerenciaRegional").equals("")){
			// Popular o combo-box de Ger�ncia Regional
			this.pesquisarGerenciaRegional(httpServletRequest);
		}
		

		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		
		return retorno;
	}
	
	private void pesquisarCobrancaGrupo(HttpServletRequest httpServletRequest){
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		
		filtroCobrancaGrupo.setConsultaSemLimites(true);
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		filtroCobrancaGrupo.adicionarParametro(
				new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCobrancaGrupo = 
			this.getFachada().pesquisar(filtroCobrancaGrupo,CobrancaGrupo.class.getName());

		if (colecaoCobrancaGrupo == null || colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Grupo de cobran�a");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaGrupo",colecaoCobrancaGrupo);
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Ger�ncia Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}

	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioBoletimMedicaoCobrancaForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Neg�cio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
}
