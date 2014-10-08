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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class InformarDadosConsultaNegativacaoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
  	
    	//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = new DadosConsultaNegativacaoHelper();
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//if (form.getIdNegativador() != null && !form.getIdNegativador().equals("") && new Integer(form.getIdNegativador()) > 0){
		//	dadosConsultaNegativacaoHelper.setIdNegativador(new Integer(form.getIdNegativador()));
		//	dadosConsultaNegativacaoHelper.setNomeNegativador(form.getNomeNegativador());
		//}
		//--------------------------------------------------------------------------------------------------------
		// Negativador
		//--------------------------------------------------------------------------------------------------------
		String[] arrayNegativador = form.getArrayNegativador();		
		Negativador negativadorColecao = new Negativador();
		negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		int i = 0;		

		Collection colecaoIdNegativador = new ArrayList();
		if (arrayNegativador != null) {
			//negativadorColecao.setCliente("OP��ES SELECIONADAS");
			colecaoNegativador.add(negativadorColecao);
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			for (i = 0; i < arrayNegativador.length; i++) {
				if (!arrayNegativador[i].equals("")
						&& !arrayNegativador[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdNegativador.add(arrayNegativador[i]);
				}
			}

			filtroNegativador.adicionarParametro(new ParametroSimplesIn(
					FiltroNegativador.ID, colecaoIdNegativador));
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);

			Collection colecaoNegativadorPesquisa = fachada.pesquisar(
					filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null
					&& !colecaoNegativadorPesquisa.isEmpty()) {
				colecaoNegativador.addAll(colecaoNegativadorPesquisa);
			}
		} else {
			//negativadorColecao.setDescricao("TODOS");
			colecaoNegativador.add(negativadorColecao);
		}
			
		dadosConsultaNegativacaoHelper.setColecaoNegativador(colecaoNegativador);
		//********************************************************
		
		if (form.getPeriodoEnvioNegativacaoInicio() != null && !form.getPeriodoEnvioNegativacaoInicio().equals("")){
			String periodoEnvioNegativacaoInicio = form.getPeriodoEnvioNegativacaoInicio();
			sessao.setAttribute("periodoEnvioNegativacaoInicio",periodoEnvioNegativacaoInicio);
			dadosConsultaNegativacaoHelper.setPeriodoEnvioNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoInicio()));  
		}
		
		if (form.getPeriodoEnvioNegativacaoFim() != null && !form.getPeriodoEnvioNegativacaoFim().equals("")){
			String periodoEnvioNegativacaoFim = form.getPeriodoEnvioNegativacaoFim();
			sessao.setAttribute("periodoEnvioNegativacaoFim",periodoEnvioNegativacaoFim);
			dadosConsultaNegativacaoHelper.setPeriodoEnvioNegativacaoFim(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoFim()));  
		}
		
		if (form.getPeriodoExclusaoNegativacaoInicio() != null && !form.getPeriodoExclusaoNegativacaoInicio().equals("")){
			String periodoExclusaoNegativacaoInicio = form.getPeriodoExclusaoNegativacaoInicio();
			sessao.setAttribute("periodoExclusaoNegativacaoInicio",periodoExclusaoNegativacaoInicio);
			dadosConsultaNegativacaoHelper.setPeriodoExclusaoNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoInicio()));  
		}
		
		if (form.getPeriodoExclusaoNegativacaoFim() != null && !form.getPeriodoExclusaoNegativacaoFim().equals("")){
			String periodoExclusaoNegativacaoFim = form.getPeriodoExclusaoNegativacaoFim();
			sessao.setAttribute("periodoExclusaoNegativacaoFim",periodoExclusaoNegativacaoFim);
			dadosConsultaNegativacaoHelper.setPeriodoExclusaoNegativacaoFim(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoFim()));  
		}		
		
		if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio() != null && dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim() != null){
			if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim().before(dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}
		

		if (dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoInicio() != null && dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoFim() != null){
			if (dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoFim().before(dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}
		
		
		
		if (form.getIdNegativadorExclusaoMotivo() != null && !form.getIdNegativadorExclusaoMotivo().equals("") && new Integer(form.getIdNegativadorExclusaoMotivo()) > 0){
			dadosConsultaNegativacaoHelper.setIdNegativadorExclusaoMotivo(new Integer(form.getIdNegativadorExclusaoMotivo()));			
		}
		
		
		if (form.getIdNegativacaoComando() != null && !form.getIdNegativacaoComando().equals("")){
			dadosConsultaNegativacaoHelper.setIdNegativacaoComando(new Integer(form.getIdNegativacaoComando()));
		}

		//--------------------------------------------------------------------------------------------------------
		//Grupo Cobran�a
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCobrancaGrupo = form.getArrayCobrancaGrupo();		
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();		
		Collection colecaoIdCobrancaGrupo = new ArrayList();
		
		if (arrayCobrancaGrupo != null) {
//			cobrancaGrupoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (i = 0; i < arrayCobrancaGrupo.length; i++) {
								
				if (!arrayCobrancaGrupo[i].equals("")
						&& !arrayCobrancaGrupo[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdCobrancaGrupo.add(arrayCobrancaGrupo[i]);
				}
			}
			
			Collection colecaoCobrancaGrupoPesquisa = null;
			
			if(colecaoIdCobrancaGrupo.size() > 0){
			
				filtroCobrancaGrupo.adicionarParametro(new ParametroSimplesIn(
						FiltroCobrancaGrupo.ID, colecaoIdCobrancaGrupo));
	
				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
	
				colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
						filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			}

			if (colecaoCobrancaGrupoPesquisa != null
					&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
//			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}
			
		dadosConsultaNegativacaoHelper.setColecaoCobrancaGrupo(colecaoCobrancaGrupo);
		
		//--------------------------------------------------------------------------------------------------------

		if (form.getIdEloPolo() != null && !form.getIdEloPolo().equals("") && new Integer(form.getIdEloPolo()) > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdEloPolo()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.elo.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdEloPolo(new Integer(form.getIdEloPolo()));
			}
		}
		
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && new Integer(form.getIdLocalidade()) > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.localidade.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdLocalidade(new Integer(form.getIdLocalidade()));
			}
		}
		
		if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("") && new Integer(form.getIdSetorComercial()) > 0){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("atencao.setor_comercial.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdSetorComercial(new Integer(form.getIdSetorComercial()));
			}
		}
		
		if (form.getIdQuadra() != null && !form.getIdQuadra().equals("") && new Integer(form.getIdQuadra()) > 0){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, form.getIdQuadra()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("atencao.quadra.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdQuadra(new Integer(form.getIdQuadra()));
			}
		}

		
		//************************************************************************************************
//		--------------------------------------------------------------------------------------------------------
		//Ger�ncia Regional
		//--------------------------------------------------------------------------------------------------------
		String[] arrayGerenciaRegional = form.getArrayGerenciaRegional();		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();
		Collection colecaoIdGerenciaRegional = new ArrayList();
		int j = 0;		
		
		if (arrayGerenciaRegional != null) {
//			gerenciaRegionalColecao.setNome("OP��ES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (j = 0; j < arrayGerenciaRegional.length; j++) {
				if (!arrayGerenciaRegional[j].equals("")
						&& !arrayGerenciaRegional[j].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdGerenciaRegional.add(arrayGerenciaRegional[j]);
				}
			}
			
			Collection colecaoGerenciaRegionalPesquisa = null;
			
			if(colecaoIdGerenciaRegional.size() > 0){
				filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(
						FiltroGerenciaRegional.ID, colecaoIdGerenciaRegional));
	
				filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
	
				colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
						filtroGerenciaRegional, GerenciaRegional.class.getName());
			}

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}		
		
		dadosConsultaNegativacaoHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
		//--------------------------------------------------------------------------------------------------------
		//Unidade de Neg�cio
		//--------------------------------------------------------------------------------------------------------
		String[] arrayUnidadeNegocio = form.getArrayUnidadeNegocio();		
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();
		Collection colecaoIdUnidadeNegocio = new ArrayList();
		int l = 0;		
		
		if (arrayUnidadeNegocio != null) {
//			unidadeNegocioColecao.setNome("OP��ES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (l = 0; l < arrayUnidadeNegocio.length; l++) {
				if (!arrayUnidadeNegocio[l].equals("")
						&& !arrayUnidadeNegocio[l].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					colecaoIdUnidadeNegocio.add(arrayUnidadeNegocio[l]);
				}
			}
			
			Collection colecaoUnidadeNegocioPesquisa = null;
			
			if(colecaoIdUnidadeNegocio.size() > 0){
			
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(
						FiltroUnidadeNegocio.ID, colecaoIdUnidadeNegocio));
	
				filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
	
				colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
						filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			}

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
//			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
		//--------------------------------------------------------------------------------------------------------
		//Perfil Im�vel
		//--------------------------------------------------------------------------------------------------------
		String[] arrayImovelPerfil = form.getArrayImovelPerfil();		
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();
		Collection colecaoIdImovelPerfil = new ArrayList();
		int m = 0;		
		
		if (arrayImovelPerfil != null) {
//			imovelPerfilColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (m = 0; m < arrayImovelPerfil.length; m++) {
				if (!arrayImovelPerfil[m].equals("")
						&& !arrayImovelPerfil[m].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdImovelPerfil.add(arrayImovelPerfil[m]);

				}
			}
			
			Collection colecaoImovelPerfilPesquisa = null;
			
			if(colecaoIdImovelPerfil.size() > 0){
			
				filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(
						FiltroImovelPerfil.ID, colecaoIdImovelPerfil));
	
				filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
	
				colecaoImovelPerfilPesquisa = fachada.pesquisar(
						filtroImovelPerfil, ImovelPerfil.class.getName());
			
			}

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
//			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoImovelPerfil(colecaoImovelPerfil);
		//--------------------------------------------------------------------------------------------------------
		//Categoria
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCategoria = form.getArrayCategoria();		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();
		Collection colecaoIdCategoria = new ArrayList();
		int n = 0;		
		
		if (arrayCategoria != null) {
//			categoriaColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (n = 0; n < arrayCategoria.length; n++) {
				if (!arrayCategoria[n].equals("")
						&& !arrayCategoria[n].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdCategoria.add(arrayCategoria[n]);

				}
			}
			
			Collection colecaoCategoriaPesquisa = null;
			
			if(colecaoIdCategoria.size() > 0){
			
				filtroCategoria.adicionarParametro(new ParametroSimplesIn(
						FiltroCategoria.CODIGO, colecaoIdCategoria));
	
				filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
	
				colecaoCategoriaPesquisa = fachada.pesquisar(
						filtroCategoria, Categoria.class.getName());
			
			}

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
//			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoCategoria(colecaoCategoria);
		//--------------------------------------------------------------------------------------------------------
		//TipoCliente
		//--------------------------------------------------------------------------------------------------------
		String[] arrayTipoCliente = form.getArrayTipoCliente();		
		ClienteTipo tipoClienteColecao = new ClienteTipo();
		tipoClienteColecao.setId(-1);

		Collection colecaoTipoCliente = new ArrayList();
		Collection colecaoIdTipoCliente = new ArrayList();
		int o = 0;		
		
		if (arrayTipoCliente != null) {
//			tipoClienteColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoTipoCliente.add(tipoClienteColecao);
			FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

			for (o = 0; o < arrayTipoCliente.length; o++) {
				if (!arrayTipoCliente[o].equals("")
						&& !arrayTipoCliente[o].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdTipoCliente.add(arrayTipoCliente[o]);

				}
			}
			
			Collection colecaoTipoClientePesquisa = null;
			
			if(colecaoIdTipoCliente.size() > 0){
			
				filtroClienteTipo.adicionarParametro(new ParametroSimplesIn(
						FiltroClienteTipo.ID, colecaoIdTipoCliente));
	
				filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);
	
				colecaoTipoClientePesquisa = fachada.pesquisar(
						filtroClienteTipo, ClienteTipo.class.getName());			
			}

			if (colecaoTipoClientePesquisa != null
					&& !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
//			tipoClienteColecao.setDescricao("TODOS");
			colecaoTipoCliente.add(tipoClienteColecao);
		}
	
		dadosConsultaNegativacaoHelper.setColecaoClienteTipo(colecaoTipoCliente);
		//--------------------------------------------------------------------------------------------------------
		//Esfera Poder
		//--------------------------------------------------------------------------------------------------------
		String[] arrayEsferaPoder = form.getArrayEsferaPoder();		
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();
		Collection colecaoIdEsferaPoder = new ArrayList();
		int p = 0;		
		
		if (arrayEsferaPoder != null) {
//			esferaPoderColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (p = 0; p < arrayEsferaPoder.length; p++) {
				if (!arrayEsferaPoder[p].equals("")
						&& !arrayEsferaPoder[p].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdEsferaPoder.add(arrayEsferaPoder[p]);

				}
			}
			
			Collection colecaoEsferaPoderPesquisa = null;
			
			if(colecaoIdEsferaPoder.size() > 0){
			
				filtroEsferaPoder.adicionarParametro(new ParametroSimplesIn(
						FiltroEsferaPoder.ID, colecaoIdEsferaPoder));
	
				filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
	
				colecaoEsferaPoderPesquisa = fachada.pesquisar(
						filtroEsferaPoder, EsferaPoder.class.getName());
			
			}

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
//			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		dadosConsultaNegativacaoHelper.setColecaoEsferaPoder(colecaoEsferaPoder);
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Agua Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoAguaSituacao = form.getArrayLigacaoAguaSituacao();		
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		Collection colecaoIdLigacaoAguaSituacao = new ArrayList();
		int x = 0;		
		
		if (arrayLigacaoAguaSituacao != null) {
//			ligacaoAguaSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			
			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("")
						&& !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					colecaoIdLigacaoAguaSituacao.add(arrayLigacaoAguaSituacao[x]);

				}
			}
			
			Collection colecaoLigacaoAguaSituacaoPesquisa = null;
			
			if(colecaoIdLigacaoAguaSituacao.size() > 0){
			
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimplesIn(
						FiltroLigacaoAguaSituacao.ID, colecaoIdLigacaoAguaSituacao));
				
				filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
				
				colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			
			}

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
//			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
		
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Esgoto Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoEsgotoSituacao = form.getArrayLigacaoEsgotoSituacao();		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
		Collection colecaoIdLigacaoEsgotoSituacao = new ArrayList();
		
		if (arrayLigacaoEsgotoSituacao != null) {
//			ligacaoEsgotoSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("")
						&& !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					colecaoIdLigacaoEsgotoSituacao.add(arrayLigacaoEsgotoSituacao[x]);

				}
			}
			
			Collection colecaoLigacaoEsgotoSituacaoPesquisa = null;
			
			if(colecaoIdLigacaoEsgotoSituacao.size()  > 0){
			
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimplesIn(
						FiltroLigacaoEsgotoSituacao.ID, colecaoIdLigacaoEsgotoSituacao));
				
				filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
				
				colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			
			}

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
//			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);
		
		//************************************************************************************************
		if(form.getIndicadorRelAcompanhamentoClientesNegativados()!= null && 
				form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")){
			//--------------------------------------------------------------------------------------------------------
			//Motivo de Rejei��o
			//--------------------------------------------------------------------------------------------------------
			String[] arrayMotivoRejeicao = form.getArrayMotivoRejeicao();		
			NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
			negativadorRetornoMotivoColecao.setId(-1);
	
			Collection colecaoMotivoRejeicao = new ArrayList();
			Collection colecaoIdMotivoRejeicao = new ArrayList();
			int t = 0;		
			
			if (arrayMotivoRejeicao != null) {
//				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("OP��ES SELECIONADAS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
				FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoIdNegativador));
				
				for (t = 0; t < arrayMotivoRejeicao.length; t++) {
					if (!arrayMotivoRejeicao[t].equals("")
						&& !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
						
						colecaoIdMotivoRejeicao.add(arrayMotivoRejeicao[t]);
	
					}
				}
				
				Collection colecaoNegativadorRetornoMotivoPesquisa = null;
				
				if(colecaoIdMotivoRejeicao.size() > 0){
				
					filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(
							FiltroEsferaPoder.ID, colecaoIdMotivoRejeicao));
		
					filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);
		
					colecaoNegativadorRetornoMotivoPesquisa = fachada.pesquisar(
							filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
				
				}
	
				if (colecaoNegativadorRetornoMotivoPesquisa != null
						&& !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
					colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
				}
			} else {
//				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("TODOS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			}
	
			dadosConsultaNegativacaoHelper.setColecaoMotivoRejeicao(colecaoMotivoRejeicao);
			if(form.getIndicadorApenasNegativacoesRejeitadas() != null &&
					!form.getIndicadorApenasNegativacoesRejeitadas().equals("")){
				dadosConsultaNegativacaoHelper.setIndicadorApenasNegativacoesRejeitadas(new Short(form.getIndicadorApenasNegativacoesRejeitadas()));
			}
		}
		//************************************************************************************************
		
		sessao.setAttribute("dadosConsultaNegativacaoHelper",dadosConsultaNegativacaoHelper);
		
		if(sessao.getAttribute("gerarRelatorio").equals("nao")){			
			retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
				
		}else if(sessao.getAttribute("gerarRelatorio").equals("relatorioAcompanhamentoClientesNegativados")){			
			retorno = actionMapping.findForward("gerarRelatorioAcompanhamentoClientesNegativados");
				
		}else if(sessao.getAttribute("gerarRelatorio").equals("relatorioNegativacoesExcluidas")){			
			retorno = actionMapping.findForward("gerarRelatorioNegativacoesExcluidas");
				
		}
		
		
		
		return retorno;
        
    }
}