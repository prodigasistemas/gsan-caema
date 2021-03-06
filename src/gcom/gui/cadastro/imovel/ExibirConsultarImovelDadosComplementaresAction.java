/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.bean.ContratoDemandaHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroImovelEloAnormalidade;
import gcom.cadastro.imovel.FiltroImovelFoto;
import gcom.cadastro.imovel.FiltroImovelRamoAtividade;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelFoto;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.faturamento.FiltroContratoDemandaImovel;
import gcom.faturamento.contratodemanda.ContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.ContratoDemandaImovel;
import gcom.faturamento.contratodemanda.ContratoDemandaImovelComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaImovelComercialIndustrial;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 * 
 */
public class ExibirConsultarImovelDadosComplementaresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		if (isLimparDadosTela(httpServletRequest)) {

			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			limparFormSessao(consultarImovelActionForm, sessao);
						
        }else if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
        			|| isImovelInformadoOutraTela(sessao) ){

        	consultarImovelActionForm.setIdImovelDadosComplementares(
        			definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));
        	
        	Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);
        	
			boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				
				
				sessao.setAttribute("imovelDadosComplementares", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				
				consultarImovelActionForm.setIdImovelDadosComplementares(imovel.getId().toString());
				
                if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
                
				if (isNovoImovelPesquisado) {

					httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");

					setarDadosNoFormESessao(consultarImovelActionForm, imovel,sessao);
				}
			} else {
				limparFormSessao(consultarImovelActionForm, sessao);

				httpServletRequest.setAttribute("idImovelDadosComplementaresNaoEncontrado", "true");				
				consultarImovelActionForm.setMatriculaImovelDadosComplementares("IM�VEL INEXISTENTE");				
			}

		} else {
			
			String idAux = consultarImovelActionForm.getIdImovelDadosComplementares();
			
			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			
			limparFormSessao(consultarImovelActionForm, sessao);
			
			consultarImovelActionForm.setIdImovelDadosComplementares(idAux);			
			
		}
	
		if(httpServletRequest.getParameter("pesquisarHistorico") != null && httpServletRequest.getParameter("pesquisarHistorico").equals("OK")){
			pesquisarHistoricoImovel(httpServletRequest, consultarImovelActionForm);
		}

		return actionMapping.findForward("consultarImovelDadosComplementares");
	}

	/**
	 * Esse m�todo seta os dados necess�rios do Imovel
	 * no form e alguns na sess�o (cole��es).
	 * 
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao) {

		// seta na tela a inscri��o do imovel
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(
			this.getFachada().pesquisarInscricaoImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		// seta a situa��o de agua
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(
			imovel.getLigacaoAguaSituacao().getDescricao());
		
		// seta a situa��o de esgoto
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(
			imovel.getLigacaoEsgotoSituacao().getDescricao());

		// consumo tarifa
		if (imovel.getConsumoTarifa() != null) {
			consultarImovelActionForm.setTarifaConsumoDadosComplementares(
				imovel.getConsumoTarifa().getDescricao());
		}
		
		// numero retificacao
		if (imovel.getNumeroRetificacao() != null) {
			consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(
				imovel.getNumeroRetificacao().toString());
		}
		
		// numero parcelamento
		if (imovel.getNumeroParcelamento() != null) {
			consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(
				imovel.getNumeroParcelamento().toString());
		}
		
		// numero reparcelamento
		if (imovel.getNumeroReparcelamento() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(
				imovel.getNumeroReparcelamento().toString());
		}
		
		// numero reparcelamento consecutivos
		if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(
				imovel.getNumeroReparcelamentoConsecutivos().toString());
		}
		
		
		// funcionario
		if (imovel.getFuncionario() != null) {
			consultarImovelActionForm.setIdFuncionario(""+imovel.getFuncionario().getId());
			consultarImovelActionForm.setNomeFuncionario(imovel.getFuncionario().getNome());
		} else {
			consultarImovelActionForm.setIdFuncionario("");
			consultarImovelActionForm.setNomeFuncionario("");
		}
		
		// cadastro ocorrencia
		if (imovel.getCadastroOcorrencia() != null) {
			consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(
				imovel.getCadastroOcorrencia().getDescricao());
		}
		
		// elo anormalidade
		if (imovel.getEloAnormalidade() != null) {
			consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(
				imovel.getEloAnormalidade().getDescricao());
		}
		
		//descricao das informacoes complementares
		if(imovel.getInformacoesComplementares() != null){
			consultarImovelActionForm.setInformacoesComplementares(imovel.getInformacoesComplementares());
		} else {
			consultarImovelActionForm.setInformacoesComplementares("");
		}
		
		sessao.setAttribute("colecaoVencimentosAlternativos",
				this.getFachada().pesquisarVencimentoAlternativoImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		sessao.setAttribute("colecaoDebitosAutomaticos",
				this.getFachada().pesquisarDebitosAutomaticosImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoFaturamentosSituacaoHistorico",
				this.getFachada().pesquisarFaturamentosSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoCobrancasSituacaoHistorico",
				this.getFachada().pesquisarCobrancasSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		/**
		 * Pesquisa os clientes e seus telefones.
		 * @author Anderson Cabral
		 * @date 11/10/2012
		 * **/
		Collection<ClienteImovel> colecaoHistoricoClientesImovel = this.getFachada().pesquisarClientesImovelDadosComplementares(
				new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		if(colecaoHistoricoClientesImovel != null && !colecaoHistoricoClientesImovel.isEmpty()){
			for(ClienteImovel clienteImovel : colecaoHistoricoClientesImovel){
				Collection clientesFone = this.getFachada().pesquisarClienteFone(clienteImovel.getCliente().getId());
				if(clientesFone != null){
					clienteImovel.getCliente().setClienteFones(new HashSet(clientesFone));
				}
			}
		}
		
		sessao.setAttribute("colecaoHistoricoClientesImovel",colecaoHistoricoClientesImovel);
		
		/*******************/
		
	
		setarColecaoCadastroOcorrenciaSessao(consultarImovelActionForm, sessao);
		
		setarColecaoEloAnormalidadeSessao(consultarImovelActionForm, sessao);
		
    	setarColecaoImovelRamoAtividadeSessao(imovel, sessao);	
    	
    	setarColecaoDadosImovelCobrancaSituacaoSessao(consultarImovelActionForm,sessao);

    	setarColecaoDadosNegativadorMovimentoRegSessao(consultarImovelActionForm,sessao);

    	setarColecaoContratoDemandaHelper(imovel,sessao);
    	
    	setarColecaoMatriculasAssociadas(imovel,sessao);
    	
    	setarColecaoImovelFotoSessao(consultarImovelActionForm, sessao);
    	

		//RM8015 - alterado por Ana Maria - 25/10/2012 
		//[Sb0003] - Exibir Informa��o Im�vel Apto para Sorteio
    	Fachada fachada = Fachada.getInstancia();
		Date periodoVencimentoDebitoInicial = Util.converteStringParaDate("01/01/0001");
		Date periodoVencimentoDebitoFinal = new Date();
	
		// Obtendo os d�bitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada.obterDebitoImovelOuCliente(
						1,
						imovel.getId().toString(), null, null, "000101",
						"999912", periodoVencimentoDebitoInicial, periodoVencimentoDebitoFinal, 1,
						1, 2, 2, 2, 2, 1, null);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Date dataSorteio = sistemaParametro.getDataSorteio();
		Date dataLimiteSorteio = sistemaParametro.getDataLimiteCadastroSorteio();
		
		Date dataAtual = new Date();
		
		if(dataSorteio != null && Util.compararData(dataAtual, dataSorteio) !=  1 && fachada.pesquisarImovelCadastradoSorteio(imovel.getId())) {
			consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("IM�VEL INSCRITO NO SORTEIO");
		}else if(dataLimiteSorteio != null && Util.compararData(dataAtual, dataLimiteSorteio) !=  1){
			if ((colecaoDebitoImovel.getColecaoContasValores()== null 
					|| colecaoDebitoImovel.getColecaoContasValores().size() == 0 )&&
		  	    (colecaoDebitoImovel.getColecaoContasValoresImovel()== null 
		  	    	|| colecaoDebitoImovel.getColecaoContasValoresImovel().size() == 0 )) {
					if(imovel.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO) || 
							imovel.getLigacaoEsgotoSituacao().getId().equals(LigacaoEsgotoSituacao.LIGADO)){
						if(fachada.verificarImovelAptoSorteio(imovel.getId())){
							consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("IM�VEL APTO N�O INSCRITO NO SORTEIO");
						}else{
							consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("");
						}
					}else{
						consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("");
					}
			}else{
				consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("");
			}
		}else{
			consultarImovelActionForm.setMsgImovelAptoSorteioComplementares("");
		}
    	
	}
	
	
	/**
	 * Pesquisar Historico de alteracoes no Imovel.
	 * @author Flavio Ferreira
	 * @date 03/12/2013
	 * **/
	
	private void pesquisarHistoricoImovel(HttpServletRequest httpServletRequest,ConsultarImovelActionForm consultarImovelActionForm){
		
		
		Integer idUsuarioAcao       = null;
		String[] idOperacoes        = null;
		String idUsuario            = null;
		Date dataInicial            = null;
		Date dataFinal              = null;
		Date horaInicial            = null;
		Date horaFinal              = null;
		Integer idImovel            = null;
		String unidadeNegocio       = null;
		
		
		
		Hashtable<String,String> argumentos = new Hashtable<String,String>();
		argumentos.put("", consultarImovelActionForm.getIdImovelDadosComplementares());
		
		Collection colecaoAlteracaoImovel = Fachada.getInstancia().pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao, idOperacoes,
			idUsuario, dataInicial, dataFinal, horaInicial, horaFinal, argumentos, idImovel, null , unidadeNegocio, true);
		
			httpServletRequest.setAttribute("colecaoAlteracaoImovel", colecaoAlteracaoImovel);
		}
	
	
	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de elo anormalidade do imovel.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoImovelRamoAtividadeSessao(Imovel imovel,
			HttpSession sessao) {
		
		FiltroImovelRamoAtividade filtroImovelRamoAtividade = new FiltroImovelRamoAtividade();
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovel");
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.ramo_atividade");
        
        filtroImovelRamoAtividade.adicionarParametro(new ParametroSimples(
                FiltroImovelRamoAtividade.IMOVEL_ID, new Integer(imovel.getId())));
        
        sessao.setAttribute("colecaoImovelRamosAtividade", 
        		this.getFachada().pesquisar(filtroImovelRamoAtividade,
        				ImovelRamoAtividade.class.getName()));
	}

	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de elo anormalidade do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoEloAnormalidadeSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
		filtroImovelEloAnormalidade.adicionarParametro(
			new ParametroSimples(FiltroImovelEloAnormalidade.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelEloAnormalidade.setCampoOrderBy(FiltroImovelEloAnormalidade.DATA_ANORMALIDADE);
		filtroImovelEloAnormalidade.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
		
		sessao.setAttribute("colecaoImovelEloAnormalidade",
				this.getFachada().pesquisar(filtroImovelEloAnormalidade, 
						ImovelEloAnormalidade.class.getName()));
	}

	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de cadastro ocorrencia do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoCadastroOcorrenciaSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = 
			new FiltroImovelCadastroOcorrencia();
		
		filtroImovelCadastroOcorrencia.adicionarParametro(
			new ParametroSimples(FiltroImovelCadastroOcorrencia.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelCadastroOcorrencia.setCampoOrderBy(FiltroImovelCadastroOcorrencia.DATA_OCORRENCIA);
		filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		
		sessao.setAttribute("colecaoImovelCadastroOcorrencia",
				this.getFachada().pesquisar(filtroImovelCadastroOcorrencia, 
						ImovelCadastroOcorrencia.class.getName()));
	}

	/**
	 *Esse m�todo limpa todos os atributos do form
	 *e os atributos na sesss�o 
	 *que s�o usados pelo action e/ou jsp.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		sessao.removeAttribute("imovelDadosComplementares");
		sessao.removeAttribute("colecaoVencimentosAlternativos");
		sessao.removeAttribute("colecaoDebitosAutomaticos");
		sessao.removeAttribute("colecaoFaturamentosSituacaoHistorico");
		sessao.removeAttribute("colecaoCobrancasSituacaoHistorico");
		sessao.removeAttribute("idImovelPrincipalAba");			
		sessao.removeAttribute("colecaoImovelCadastroOcorrencia");
		sessao.removeAttribute("colecaoImovelEloAnormalidade");
		sessao.removeAttribute("colecaoImovelRamosAtividade");
		sessao.removeAttribute("contratoDemandaHelper");
		sessao.removeAttribute("colecaoMatriculasAssociadas");
		sessao.removeAttribute("colecaoDadosImovelCobrancaSituacao");
		sessao.removeAttribute("colecaoFotosImovel");
		sessao.removeAttribute("colecaoDadosNegativadorMovimentoReg");
		sessao.removeAttribute("colecaoContratoDemanda");
		sessao.removeAttribute("colecaoHistoricoClientesImovel");
		
		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
		
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(null);
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(null);
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(null);
		consultarImovelActionForm.setTarifaConsumoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(null);
		consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(null);
		consultarImovelActionForm.setIdFuncionario(null);
		consultarImovelActionForm.setNomeFuncionario(null);
		consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(null);
		consultarImovelActionForm.setInformacoesComplementares(null);
		consultarImovelActionForm.setMsgImovelAptoSorteioComplementares(null);
	}

	/**
	 * Caso o usu�rio tenha clicado no bot�o de limpar
	 * esse m�todo retornar� true.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse m�todo verifica se j� foi informado um im�vel em outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse m�todo verifica se o im�vel foi informado na tela
	 * de Dados Adicionais
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaDadosAdicionais(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelDadosComplementares());
	}
	
	/**
	 * Esse m�todo retorna o id do im�vel a ser pesquisado,
	 * verificando se � o id possivelmente informado pelo usu�rio na tela 
	 * de dados adicionais ou se o id j� informado em uma outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelDadosComplementares();
	}

	/**
	 * Consulta o Imovel com todas as informa��es necess�rias,
	 * ou simplesmetne pega o Imovel da sess�o caso o mesmo j�
	 * tenha sido pesquisado.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosComplementares") == null){
			imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosComplementares");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse m�todo retorna true se foi necess�rio consultar um novo imovel.
	 * Caso o im�vel seja o mesmo j� consultado anteriormente ele retorna false.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosComplementares") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosComplementares");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
			return true;
		}

		return false;
	}
	
	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosImovelCobrancaSituacaoSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {		
		sessao.setAttribute("colecaoDadosImovelCobrancaSituacao",this.getFachada().pesquisarDadosImovelCobrancaSituacaoPorImovel(
			new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}

	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosNegativadorMovimentoRegSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {
		
		sessao.setAttribute("colecaoDadosNegativadorMovimentoReg",
				this.getFachada().consultarDadosNegativadorMovimentoReg(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoContratoDemandaHelper(Imovel imovel,HttpSession sessao) {
		Collection<ContratoDemandaHelper> colecaoContrato = new ArrayList<ContratoDemandaHelper>();
		
		ContratoDemandaHelper contratoDemandaHelper = null;
		
//		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
//		filtroContratoDemanda.adicionarParametro(
//				new ParametroSimples(FiltroContratoDemanda.IMOVEL, imovel.getId()));
//		filtroContratoDemanda.adicionarParametro(
//				new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
//		Collection colecaoContratoDemanda = this.getFachada().pesquisar(
//				filtroContratoDemanda, ContratoDemanda.class.getName());
//		
//		if (colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()) {
//			Object[] consumoContratado = this.getFachada().consultarConsumoCadastrado(imovel.getId());
//			if (consumoContratado != null) {
//				contratoDemandaHelper = new ContratoDemandaHelper();
//				contratoDemandaHelper.setContratoDemanda((ContratoDemanda) Util.retonarObjetoDeColecao(colecaoContratoDemanda));
//				contratoDemandaHelper.setContratoDemandaImovel(null);
//				contratoDemandaHelper.setConsumoContratado(consumoContratado[0].toString());
//				contratoDemandaHelper.setValorTarifa(Util.formatarMoedaReal(
//						(BigDecimal) consumoContratado[1]));
//				
//				colecaoContrato.add(contratoDemandaHelper);
//			}
//		}
		
		FiltroContratoDemandaImovelComercialIndustrial filtroContratoDemandaImovelComInd = new FiltroContratoDemandaImovelComercialIndustrial();
		filtroContratoDemandaImovelComInd.adicionarParametro(new ParametroSimples(
				FiltroContratoDemandaImovelComercialIndustrial.ID_IMOVEL, imovel.getId()));
		filtroContratoDemandaImovelComInd.adicionarParametro(new ParametroNulo(
				FiltroContratoDemandaImovelComercialIndustrial.DATA_CONTRATO_ENCERRAMENTO));
		filtroContratoDemandaImovelComInd.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoDemandaImovelComercialIndustrial.CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL);
		
		Collection<?> colecaoContratoDemanda = this.getFachada().pesquisar(
				filtroContratoDemandaImovelComInd, ContratoDemandaImovelComercialIndustrial.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoContratoDemanda)){
			ContratoDemandaComercialIndustrial contratoDemanda = null;
			ContratoDemandaImovelComercialIndustrial contratoDemandaImovelComInd = null;
			Object[] consumoContratado = this.getFachada().consultarConsumoCadastrado(imovel.getId());
			if(consumoContratado != null){
				contratoDemandaHelper = new ContratoDemandaHelper();
				contratoDemandaImovelComInd = (ContratoDemandaImovelComercialIndustrial) Util.retonarObjetoDeColecao(colecaoContratoDemanda);
				contratoDemanda = contratoDemandaImovelComInd.getComp_id().getContratoDemandaComercialIndustrial();
				
				contratoDemandaHelper.setContratoDemandaComercialIndustrial(contratoDemanda);
				contratoDemandaHelper.setContratoDemanda(null);
				contratoDemandaHelper.setContratoDemandaImovel(null);
				contratoDemandaHelper.setConsumoContratado(consumoContratado[0].toString());
				contratoDemandaHelper.setValorTarifa(Util.formatarMoedaReal((BigDecimal) consumoContratado[1]));
				
				colecaoContrato.add(contratoDemandaHelper);
			}
		}
		
		FiltroContratoDemandaImovel filtroContratoDemandaImovel = new FiltroContratoDemandaImovel();
		filtroContratoDemandaImovel.adicionarParametro(
			new ParametroSimples(FiltroContratoDemandaImovel.IMOVEL, imovel.getId()));
		filtroContratoDemandaImovel.adicionarParametro(
			new ParametroNulo(FiltroContratoDemandaImovel.DATA_ENCERRAMENTO_CONTRATO));
		
		filtroContratoDemandaImovel.adicionarCaminhoParaCarregamentoEntidade("faixaConsumo");
		
		Collection colecaoContratoDemandaImovel = this.getFachada().pesquisar(
			filtroContratoDemandaImovel, ContratoDemandaImovel.class.getName());
		
		if (colecaoContratoDemandaImovel != null && !colecaoContratoDemandaImovel.isEmpty()) {
			ContratoDemandaImovel contratoDemandaImovel = 
				(ContratoDemandaImovel) Util.retonarObjetoDeColecao(colecaoContratoDemandaImovel);
			
			contratoDemandaHelper = new ContratoDemandaHelper();
			contratoDemandaHelper.setContratoDemandaImovel(contratoDemandaImovel);
			contratoDemandaHelper.setConsumoContratado(""+contratoDemandaImovel.getDemandaMinimaContratada());
			if(contratoDemandaImovel.getFaixaConsumo() != null){
//				contratoDemandaHelper.setConsumoContratado(
//					contratoDemandaImovel.getFaixaConsumo().getFaixaInicial() +" � "+
//					contratoDemandaImovel.getFaixaConsumo().getFaixaFinal());
				
				contratoDemandaHelper.setValorTarifa(contratoDemandaImovel.getFaixaConsumo().getPercentualDesconto()+"%");
				
			}

			colecaoContrato.add(contratoDemandaHelper);
		}
		
		if (contratoDemandaHelper != null) {
			sessao.setAttribute("colecaoContratoDemanda", colecaoContrato);
		} else {
			sessao.removeAttribute("colecaoContratoDemanda");
		}
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoMatriculasAssociadas(Imovel imovel,HttpSession sessao) {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.CONSUMO_TARIFA);
		Collection<Imovel> colecaoImovel = this.getFachada().pesquisar(
				filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovelRetornado = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			
			//Verifica se o im�vel ja encerrou o contrato de demanda
			FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(FiltroContratoDemanda.IMOVEL, imovelRetornado.getId()));
			filtroContratoDemanda.adicionarParametro(new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
			Collection<ContratoDemanda> colecaoContratoDemanda = this.getFachada().pesquisar(filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if(colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()){
				Collection<Integer> colecaoMatriculasAssociadas = this.getFachada().consultarMatriculasAssociadas(
					imovelRetornado.getConsumoTarifa().getId(), imovelRetornado.getId());			
				if (colecaoMatriculasAssociadas != null && !colecaoMatriculasAssociadas.isEmpty()) {
					sessao.setAttribute("colecaoMatriculasAssociadas", colecaoMatriculasAssociadas);
				} else {
					sessao.removeAttribute("colecaoMatriculasAssociadas");
				}
			}else {
				sessao.removeAttribute("colecaoMatriculasAssociadas");
			}
			
		}
		
	}
	
	/**
	 *@since 18/06/2012
	 *@author Davi Menezes
	 */
	private void setarColecaoImovelFotoSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelFoto filtroImovelFoto = new FiltroImovelFoto();
		filtroImovelFoto.adicionarParametro(new ParametroSimples(FiltroImovelFoto.IMOVEL_ID, 
				consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		filtroImovelFoto.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelFoto.FOTO_SITUACAO_OS);
		
		Collection colecaoFotosImovel = this.getFachada().pesquisar(filtroImovelFoto, ImovelFoto.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoFotosImovel)){
			sessao.setAttribute("colecaoFotosImovel", colecaoFotosImovel);
		}else{
			sessao.removeAttribute("colecaoFotosImovel");
		}
		
	}
	
}