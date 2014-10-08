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
package gcom.gui.relatorio.cadastro.imovel;


import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Este caso de uso gera relat�rio de an�lise do im�vel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */


public class ExibirGerarRelatorioAnaliseImovelCorporativoGrandeAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("gerarRelatorioAnaliseImovelCorporativoGrande");
	
			Fachada fachada = Fachada.getInstancia();
			
			GerarRelatorioAnaliseImovelCorporativoGrandeActionForm form = (GerarRelatorioAnaliseImovelCorporativoGrandeActionForm)actionForm;
	
			HttpSession sessao = httpServletRequest.getSession(false);

			//--------- SETANDO FOCO INICIAL E CARREGANDO AS COLE��ES QUE SER�O MOSTRADAS NA P�GINA INICIAL
			
			if (httpServletRequest.getParameter("menu")!= null && !httpServletRequest.getParameter("menu").equals("")){
				
				httpServletRequest.setAttribute("nomeCampo", "referencia");
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- UNIDADE DE NEGOCIO 
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
				if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Neg�cio");
				}
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  GERENCIA REGIONAL
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
				if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Ger�ncia Regional");
				}
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- PERFIL DO IMOVEL 
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
				if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Im�vel Perfil");
				}
				sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
				//Categoria
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
				if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Categoria");
				}
				
				sessao.setAttribute("colecaoCategoria", colecaoCategoria);
				
				//Ligacao Agua Situacao
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				if (colecaoLigacaoAguaSituacao == null || colecaoLigacaoAguaSituacao.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Liga��o �gua Situa��o");
				}
				
				sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
				
				//Ligacao Esgoto Situacao
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
				if (colecaoLigacaoEsgotoSituacao == null || colecaoLigacaoEsgotoSituacao.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Liga��o Esgoto Situa��o");
				}
				
				sessao.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);
				
			}
			
			//---------- [FS0003 - VERIFICAR EXISTENCIA DA LOCALIDADE]
			
			String idLocalidadeInicialForm = form.getIdLocalidadeInicial();

			if (idLocalidadeInicialForm != null && !idLocalidadeInicialForm.equals("")) {
				
				FiltroLocalidade filtroLocalidadeOrigem = new FiltroLocalidade();
				filtroLocalidadeOrigem.adicionarParametro(new ParametroSimples( FiltroLocalidade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidadeOrigem.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, new Integer(idLocalidadeInicialForm)));
				Collection colecaoLocalidadeOrigem = fachada.pesquisar(filtroLocalidadeOrigem, Localidade.class.getName());
				

				if (colecaoLocalidadeOrigem != null && !colecaoLocalidadeOrigem.isEmpty()) {
					
					Localidade localidadeOrigem = (Localidade)colecaoLocalidadeOrigem.iterator().next();
					form.setIdLocalidadeInicial(""+localidadeOrigem.getId());
					form.setNomeLocalidadeInicial(localidadeOrigem.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
					
				} else {
					form.setIdLocalidadeInicial("");
					form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInicialInexistente",true);
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
				}

			} else {
				form.setNomeLocalidadeInicial("");
			}
			
			
			String idLocalidadeFinalForm = form.getIdLocalidadeFinal();

			if (idLocalidadeFinalForm != null && !idLocalidadeFinalForm.equals("")) {

				FiltroLocalidade filtroLocalidadeDestino = new FiltroLocalidade();
				filtroLocalidadeDestino.adicionarParametro(new ParametroSimples( FiltroLocalidade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidadeDestino.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, new Integer(idLocalidadeFinalForm)));
				Collection colecaoLocalidadeDestino = fachada.pesquisar(filtroLocalidadeDestino, Localidade.class.getName());
				
				if (colecaoLocalidadeDestino != null && !colecaoLocalidadeDestino.isEmpty()) {
					
					Localidade localidadeDestino = (Localidade)colecaoLocalidadeDestino.iterator().next();
					form.setIdLocalidadeFinal(""+localidadeDestino.getId());
					form.setNomeLocalidadeFinal(localidadeDestino.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "referencia");
					
				} else {
					
					form.setIdLocalidadeFinal("");
					form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeFinalInexistente",true);
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
				}

			} else {
				
				form.setNomeLocalidadeFinal("");
			}
			
			String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("") &&
				idLocalidadeInicialForm != null && !idLocalidadeInicialForm.equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicialForm));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial inicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					form.setCodigoSetorComercialInicial(inicial.getCodigo() + "");
					form.setNomeSetorComercialInicial(inicial.getDescricao());
				} else {
					form.setCodigoSetorComercialInicial("");
					form.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.setAttribute("setorComercialInicialInexistente",true);
				}
			} else {
				form.setCodigoSetorComercialInicial("");
				form.setNomeSetorComercialInicial("");
			}
			
			String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("") &&
				idLocalidadeFinalForm != null && !idLocalidadeFinalForm.equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeFinalForm));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial Final = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					form.setCodigoSetorComercialFinal(Final.getCodigo() + "");
					form.setNomeSetorComercialFinal(Final.getDescricao());
				} else {
					form.setCodigoSetorComercialFinal("");
					form.setNomeSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.setAttribute("setorComercialFinalInexistente",true);
				}
			} else {
				form.setCodigoSetorComercialFinal("");
				form.setNomeSetorComercialFinal("");
			}
			
			// --------------- [FS0004 - VALIDAR M�S/ANO REFER�NCIA]
			if (form.getReferencia()!= null && !form.getReferencia().equals("")){
				
				FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
				Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
				
				if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
					
					SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
					String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());
					String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
					
					Integer resultado = anoMesReferencia.compareTo(anoMesFaturamentoCorrente);
					
					if (resultado >= 0){
						
						httpServletRequest.setAttribute("nomeCampo", "referencia");	
						throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
								null, Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
					}
				
				}
			}			
	
			return retorno;
	}

}
