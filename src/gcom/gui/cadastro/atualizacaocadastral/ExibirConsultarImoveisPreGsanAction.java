/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.integracao.webservice.neurotech.fachada.ConsultaWebServiceGATEWAY;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;
import gcom.util.filtro.ParametroSimplesIn;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1447] - Consultar Imóveis no Ambiente Pré-GSAN
 * 
 * @author Arthur Carvalho
 * @created 14/02/2012
 */
public class ExibirConsultarImoveisPreGsanAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarImoveisPreGsanAction");

		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;

		HttpSession sessao = request.getSession(false);
		
		//Atribui os valores iniciais no formulario
		if ( request.getParameter("menu") != null && request.getParameter("menu").equals("sim") ) {
			form.setIndicadorTipoSelecao("2");
		}
		
		//[IT0001] - Exibir relação de empresas
		this.pesquisarEmpresa(request);
		
		//[IT0002] - Exibir relação de ocorrência de cadastro
		this.pesquisarOcorrenciaCadastro(request);
		
		
		if ( request.getParameter("limparQuadras") != null){
			form.setIdQuadraSelecionados(null);
		}
		
		if( request.getParameter("pesquisarCadastradores") != null ){
			if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
				pesquisarCadastradores(form, request);
			}else{
				sessao.removeAttribute("colecaoLeiturista");
			}
		}		
		
		if ( request.getParameter("objetoConsulta") != null && !request.getParameter("objetoConsulta").equals("") ) {
			
			String objetoConsulta = request.getParameter("objetoConsulta");
			
			if ( objetoConsulta.equals("2") ) {
				
				this.pesquisarLocalidade( form, request);
			
			} else if ( objetoConsulta.equals("3") ) {
				
				this.pesquisarSetorComercial(form, request);
				
			} else if ( objetoConsulta.equals("4") ) {
				
				this.pesquisarQuadra(form, request);
				
			} else if ( objetoConsulta.equals("pesquisar") ) {
				
				form.setIndicadorValidarCpfCnpjRF(null);
				form.setIndicadorSituacaoTodosHabilitado("1");
				
				this.pesquisarImoveis(form, sessao);
				
			} else if ( objetoConsulta.equals("limpar") ) {
				
				form.limpar();
				sessao.removeAttribute("colecaoImovelPreGsan");
				sessao.removeAttribute("colecaoImovelCadastradoPreGsan");
				form.setIndicadorTipoSelecao("2");
				form.setIdCadastroOcorrencia("-1");				
			}
		} else if ( request.getParameter("limpar") != null ) {
			
			if ( request.getParameter("limpar").equals("localidade") ) {
				form.setIdLocalidade(null);
				form.setDescricaoLocalidade(null);
				form.setIdSetorComercial(null);
				form.setDescricaoSetorComercial(null);
				form.setIdQuadra(null);
				form.setIdQuadraSelecionados(null);
				
			} else if ( request.getParameter("limpar").equals("setor") ) {
				form.setIdSetorComercial(null);
				form.setDescricaoSetorComercial(null);
				form.setIdQuadra(null);
				form.setIdQuadraSelecionados(null);
			}
			
		} else if ( request.getParameter("idImovelAtlzCadastral") != null && request.getParameter("idImovel") != null &&
				!request.getParameter("idImovel").equals("") ) {
			
			String idImovel = request.getParameter("idImovel");
			
			Imovel imovel = Fachada.getInstancia().pesquisarImovel(Integer.valueOf(idImovel));
			
			if ( imovel != null && imovel.getId() != null ) {
				String idImovelAtlzCad = (String) request.getParameter("idImovelAtlzCadastral");
				
				//recebe a colecao de imóveis com os clientes a serem validados na receita
				Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");
				
				Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoDadosImovelPreGsanHelper.iterator();
				while( iteratorHelper.hasNext() ) {
					DadosImovelPreGsanHelper helper = (DadosImovelPreGsanHelper) iteratorHelper.next();
					
					if ( helper.getIdImovelAtualizacaoCadastral().equals(idImovelAtlzCad) ) {
						boolean validarInscricao = true;
						if ( helper.getMatriculaGsan() != null ) {
							Collection colecao = helper.getColecaoMatriculaGsan();
							colecao.add(imovel.getId());
							helper.setColecaoMatriculaGsan(colecao);
							validarInscricao = false;
						} else {
							helper.setMatriculaGsan(""+imovel.getId());
							Collection colecao = new ArrayList();
							colecao.add(imovel.getId());
							helper.setColecaoMatriculaGsan(colecao);
						}
						

						//caso seja diferente de logradouro inexistente
						if ( !helper.getIndicadorHabilitaTipoSituacao().equals("7") 
//								&& !helper.getIndicadorHabilitaTipoSituacao().equals("8") 
//								&& !helper.getIndicadorHabilitaTipoSituacao().equals("9") 
								) {

							
							String inscricaoImovelAssociado = Fachada.getInstancia().pesquisarInscricaoImovel(Integer.valueOf(idImovel));
							String inscricao = montarInscricaoFormatada(helper);

							//verifica se a inscricao do imovel é igual a inscricao do imovel informado para atualizacao.
							//se for retira a mensagem de inscricao em duplicidade.
							if ( inscricao.equals(inscricaoImovelAssociado) && helper.getMatriculaGsan().equals(idImovel))  {
								helper.setIndicadorHabilitaTipoSituacao("6");
							} else {
								//so valida a inscricao se o imovel adicionado for o primeiro.
								//caso seja o segundo em diante, nao vai esta associado ao imovel novo
							
								FiltroImovel filtroImovel = new FiltroImovel();
								filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID, helper.getIdLocalidade()));
								filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO, helper.getCodigoSetorComercial()));
								filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO, helper.getNumeroQuadra()));
								filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOTE, helper.getNumeroLote()));
								filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SUBLOTE, helper.getNumeroSubLote()));
								if ( helper.getMatriculaGsan() != null && !helper.getMatriculaGsan().equals("") ) {
									filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.ID, Integer.valueOf(helper.getMatriculaGsan())));
								}
								Collection<?> colImovel = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
								
								if ( colImovel != null && !colImovel.isEmpty() ) {
									//imovel com inscricao duplicada.
									helper.setIndicadorHabilitaTipoSituacao("10");
								} else {
									if ( validarInscricao ) {
										helper.setIndicadorHabilitaTipoSituacao("6");
									}
								}
							}
						}
					}
				}
			}
		} else if ( request.getParameter("idImovelSelecionado") != null && !request.getParameter("idImovelSelecionado").equals("")&&
				request.getParameter("idAtualizacaoCadastralPopup") != null && !request.getParameter("idAtualizacaoCadastralPopup").equals("")) {
			
			String idImovel = request.getParameter("idImovelSelecionado");
			String idAtualizacaoCadastralPopup = request.getParameter("idAtualizacaoCadastralPopup");

			//recebe a colecao de imóveis com os clientes a serem validados na receita
			Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");
			
			Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoDadosImovelPreGsanHelper.iterator();
			while( iteratorHelper.hasNext() ) {
				DadosImovelPreGsanHelper helper = (DadosImovelPreGsanHelper) iteratorHelper.next();
				
				if (helper.getIdImovelAtualizacaoCadastral().equals(idAtualizacaoCadastralPopup) ) {
					helper.setMatriculaGsan(idImovel);
					
					String inscricaoImovelAssociado = Fachada.getInstancia().pesquisarInscricaoImovel(Integer.valueOf(idImovel));
					String inscricao = montarInscricaoFormatada(helper);

					if ( !helper.getIndicadorHabilitaTipoSituacao().equals("7") 
//							&&  !helper.getIndicadorHabilitaTipoSituacao().equals("8") 
//							&& !helper.getIndicadorHabilitaTipoSituacao().equals("9") 
							) {
						//verifica se a inscricao do imovel é igual a inscricao do imovel informado para atualizacao.
						//se for retira a mensagem de inscricao em duplicidade.
						if ( inscricao.equals(inscricaoImovelAssociado) ) {
							helper.setIndicadorHabilitaTipoSituacao("6");
						} else {
	
							FiltroImovel filtroImovel = new FiltroImovel();
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID, helper.getIdLocalidade()));
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO, helper.getCodigoSetorComercial()));
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO, helper.getNumeroQuadra()));
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOTE, helper.getNumeroLote()));
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SUBLOTE, helper.getNumeroSubLote()));
							
							Collection<?> colImovel = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
							
							if ( colImovel != null && !colImovel.isEmpty() ) {
								//imovel com inscricao duplicada.
								helper.setIndicadorHabilitaTipoSituacao("10");
							} else {
									helper.setIndicadorHabilitaTipoSituacao("6");	
							}
						}
						helper.setIndicadorCpfCnpjValidadoNaReceita("2");
					}
				}
			}
			
		} else if ( form.getIndicadorValidarCpfCnpjRF() != null && form.getIndicadorValidarCpfCnpjRF().equals("1") ) {
			Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = new ArrayList<DadosImovelPreGsanHelper>();
			boolean atualizado= false;
			if ( sessao.getAttribute("colecaoImovelPreGsan")!= null && !sessao.getAttribute("colecaoImovelPreGsan").equals("") ) {
				colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");
				atualizado = false;				
			} else {
				colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelCadastradoPreGsan");
				atualizado = true;
			}
			
			//verificar cpf/cnpj dos novos clientes na receita federal
			retorno = this.validaCPFouCNPJnaReceitaFederal(request, sessao, actionMapping, retorno, form, colecaoDadosImovelPreGsanHelper, atualizado);
		} else if ( request.getParameter("carregar") != null && request.getParameter("carregar").equals("situacao") ) {
			
			//recebe a colecao de imóveis com os clientes a serem validados na receita
			
			
			Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = new ArrayList<DadosImovelPreGsanHelper>();
			if ( sessao.getAttribute("colecaoImovelPreGsan")!= null && !sessao.getAttribute("colecaoImovelPreGsan").equals("") ) {
				colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");	
			} else {
				colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelCadastradoPreGsan");
			}
			
			
			// Organizar a coleção de Conta
	        if (colecaoDadosImovelPreGsanHelper != null && !colecaoDadosImovelPreGsanHelper.isEmpty()) {
	            Collections.sort((List) colecaoDadosImovelPreGsanHelper, new Comparator() {
	                public int compare(Object a, Object b) {
	                    String imac1 = ((DadosImovelPreGsanHelper) a).getIdImovelAtualizacaoCadastral();
	                    String imac2 = ((DadosImovelPreGsanHelper) b).getIdImovelAtualizacaoCadastral();

	                    return imac1.compareTo(imac2);
	                }
	            });
	        }
			
	        Map<String, String[]> requestMap = request.getParameterMap();
			
	        if ( requestMap.get("arquivo1") != null ) {
				
	        	String situacaoTodos = (requestMap.get("arquivo1"))[0];
		        
				Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoDadosImovelPreGsanHelper.iterator();
				while( iteratorHelper.hasNext() ) {
					
					//percorre a colecao de imóveis e verifica qual ainda nao teve o cpf/cnpj validado
					DadosImovelPreGsanHelper helper = (DadosImovelPreGsanHelper) iteratorHelper.next();
					//se o imóvel ainda nao foi validado e tem a situacao diferente de logradouro novo inexistente
					if ( helper.getIndicadorCpfCnpjValidadoNaReceita().equals("2") && !helper.getIndicadorHabilitaTipoSituacao().equals("7") && 
							!helper.getIndicadorHabilitaTipoSituacao().equals("6") && !helper.getIndicadorHabilitaTipoSituacao().equals("8") &&
							!helper.getIndicadorHabilitaTipoSituacao().equals("9")) {
						helper.setIndicadorValueSelect(situacaoTodos);
					}
				}
				form.setIndicadorSituacaoTodosHabilitado("1");
	        }
	    	
		}
		
		this.pesquisarQuadra(form, request);
		//fim verificar cpf/cnpj dos novos clientes na receita federal
		
				
		return retorno;
	}
	
	
	private void pesquisarEmpresa(HttpServletRequest request) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
		
		Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
	
		if ( colecaoEmpresa != null && !colecaoEmpresa.isEmpty() ) {
			request.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,"Empresa");
		}
	}
	
	private void pesquisarOcorrenciaCadastro(HttpServletRequest request) {
		
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		filtroCadastroOcorrencia.adicionarParametro( new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<CadastroOcorrencia> colecaoCadastroOcorrencia = Fachada.getInstancia().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
	
		if ( colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty() ) {
			request.setAttribute("colecaoCadastroOcorrencia", colecaoCadastroOcorrencia);
		}
	}
	
	private void pesquisarLocalidade(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, Integer.valueOf(form.getIdLocalidade())));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());

		} else {
			form.setIdLocalidade(null);
			form.setDescricaoLocalidade("Localidade Inexistente");
			request.setAttribute("localidadeEncontrada",true);
		}
	}
	
	private void pesquisarSetorComercial(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {

		if ( form.getIdLocalidade()  != null ) {
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
			// Recupera Localidade
			Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
	
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidade(localidade.getId().toString());
				form.setDescricaoLocalidade(localidade.getDescricao());
	
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Localidade");
			}
		}
		
		
		
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, Integer.valueOf(form.getIdLocalidade())));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

			form.setIdSetorComercial("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
			this.pesquisarQuadra(form, request);
		} else {

			form.setIdSetorComercial(null);
			form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			request.setAttribute("setorEncontrado", true);

		}
	}
	
	private void pesquisarQuadra(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {

		if ( form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("") ) {
			//PESQUISANDO QUADRA...
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			FiltroQuadra filtroQuadraSelecionadas = new FiltroQuadra();
			if ( form.getIdQuadraSelecionados() != null && form.getIdQuadraSelecionados().length > 0 ) {
	
				Collection<Integer> colecaoQuadrasSel = new ArrayList<Integer>();
				for (int i = 0; i < form.getIdQuadraSelecionados().length; i++) {
					colecaoQuadrasSel.add(form.getIdQuadraSelecionados()[i]);
				}
				filtroQuadra.adicionarParametro(new ParametroSimplesNotIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasSel));
				
				//pesquisa as quadras selecionadas
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasSel));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
				
				
				Collection<Quadra> colecaoQuadrasSelecionadas = Fachada.getInstancia().pesquisar(filtroQuadraSelecionadas, Quadra.class.getName());
				
				if (colecaoQuadrasSelecionadas != null && !colecaoQuadrasSelecionadas.isEmpty()) {
					request.setAttribute("colecaoQuadrasSelecionadas", colecaoQuadrasSelecionadas);
				} 
			}
			
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
			filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
			
			Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
				request.setAttribute("colecaoQuadras", colecaoQuadras);
			} 
		
		}
		
	}
	
	private void pesquisarImoveis(ConsultarImoveisPreGsanActionForm form , HttpSession sessao) {

		DadosImovelPreGsanHelper parametros = new DadosImovelPreGsanHelper();
		//monta os parametros informados na tela.
		parametros.setParametroEmpresa(form.getIdEmpresa());
		parametros.setParametroLocalidade(form.getIdLocalidade());
		parametros.setParametroSetorComercial(form.getIdSetorComercial());
		parametros.setParametroQuadra(form.getIdQuadraSelecionados());
		parametros.setParametroCadastroOcorrencia(form.getIdCadastroOcorrencia());
		parametros.setParametroTipoSelecao(form.getIndicadorTipoSelecao());
		parametros.setParametroCadastrador(form.getCadastrador());
		
		Collection<DadosImovelPreGsanHelper> colecaoImovelPreGsan = Fachada.getInstancia().pesquisarImovelPreGsan(parametros, false);
		
		if ( colecaoImovelPreGsan != null ) {
			
			// Ordena a coleção de Imóveis
	        if (colecaoImovelPreGsan != null && !colecaoImovelPreGsan.isEmpty()) {
	            Collections.sort((List) colecaoImovelPreGsan, new Comparator() {
	                public int compare(Object a, Object b) {
	                    String imac1 = ((DadosImovelPreGsanHelper) a).getIdImovelAtualizacaoCadastral();
	                    String imac2 = ((DadosImovelPreGsanHelper) b).getIdImovelAtualizacaoCadastral();

	                    return imac1.compareTo(imac2);
	                }
	            });
	        }
			//verifica se são imóveis novos 
			if ( form.getIndicadorTipoSelecao().equals("1") ) {
				sessao.setAttribute("colecaoImovelPreGsan", colecaoImovelPreGsan);
				sessao.removeAttribute("colecaoImovelCadastradoPreGsan");
			} else {
				sessao.setAttribute("colecaoImovelCadastradoPreGsan", colecaoImovelPreGsan);
				sessao.removeAttribute("colecaoImovelPreGsan");
			}
		} else {
			throw new ActionServletException( "atencao.pesquisa.nenhumresultado", "exibirConsultarImoveisPreGsanAction.do", null, new String[] {} );
		}
		 
	}
	
	
	public ActionForward validaCPFouCNPJnaReceitaFederal(HttpServletRequest request, HttpSession sessao, ActionMapping actionMapping, ActionForward retorno,
			ConsultarImoveisPreGsanActionForm form, Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper, boolean atualizados){
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//recebe a colecao de imóveis com os clientes a serem validados na receita
//		Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");
		
		
		// Organizar a coleção de Conta
        if (colecaoDadosImovelPreGsanHelper != null && !colecaoDadosImovelPreGsanHelper.isEmpty()) {
            Collections.sort((List) colecaoDadosImovelPreGsanHelper, new Comparator() {
                public int compare(Object a, Object b) {
                    String imac1 = ((DadosImovelPreGsanHelper) a).getIdImovelAtualizacaoCadastral();
                    String imac2 = ((DadosImovelPreGsanHelper) b).getIdImovelAtualizacaoCadastral();

                    return imac1.compareTo(imac2);
                }
            });
        }
        
        
        /**
         * @author Jonathan Marcos
         * @date 17/04/2014
         * [OBSERVACOES]
         * 		- VERIFICA SE O RADIO BUTTON SELECIONADO
         * 		- E O DE Imoveis com Ocorrencia Cadastro 
         */
        if(atualizados){
        	/* MONTA A STRING DE MAPEAMENTO(IMAC;TRUE OU FALSE TIPO LIBERADO ATUALIZACAO GSAN)
        	 * DA colecaoDadosImovelPreGsanHelper
        	 */
        	if ( request.getParameter("tipoRelatorio") == null  ) {
        		request.getSession(false).setAttribute("stringImovelPreGsanHelper", 
        			this.verificaAlteracaoTipoLiberadoAtualizacaoDadosImovelPreGsan(request, colecaoDadosImovelPreGsanHelper));
			}
	
        	/* VERIFICA TIPO LIBERADO ATUALIZACAO GSAN A colecaoDadosImovelPreGsanHelper 
        	 * CASO NAO TENHA NENHUM SELECIONADO SOBE EXCEPTION
        	 */
        	if(atualizados && !verificarSelecaoLiberadoAtualizacaoGsanView(request)){
    			throw new ActionServletException("atencao.nao.existe.imovel.liberado.gsan");
    		}
        }
    
        Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoDadosImovelPreGsanHelper.iterator();
		while( iteratorHelper.hasNext() ) {
			//percorre a colecao de imóveis e verifica qual ainda nao teve o cpf/cnpj validado
			DadosImovelPreGsanHelper helper = (DadosImovelPreGsanHelper) iteratorHelper.next();
			
			System.out.println("IMAC = " +helper.getIdImovelAtualizacaoCadastral());
				//se o imóvel ainda nao foi validado e tem a situacao diferente: 
				if ( helper.getIndicadorCpfCnpjValidadoNaReceita().equals("2") 
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("7")  //de logradouro novo inexistente
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("8")  //setor inexistente  
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("9")  //quadra inexistente
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("10") //imovel com inscricao em duplicidade
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("11") //remover imovel
						&& !helper.getIndicadorHabilitaTipoSituacao().equals("12") //imovel com inscricao em duplicidade no ambiente pre gsan.
						) {
					
					String cpf = null;
					String cnpj = null;
	
					if ( helper.getCpfCnpj() != null && helper.getCpfCnpj().length() > 11 ) {
						cnpj = helper.getCpfCnpj();
					} else if ( helper.getCpfCnpj() != null && !helper.getCpfCnpj().equals("") ) {
						cpf = helper.getCpfCnpj();
					}
					
					Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
					if(cpf == null && cnpj == null){
						indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
					}
					
					ConsultarReceitaFederal consultaRF = null;
					
					String confirmadoCpfCnpj = null;
					if ( request.getParameter("tipoRelatorio") != null  ) {
						confirmadoCpfCnpj = request.getParameter("tipoRelatorio");
					}
					
					String confirmado = null;
					if ( request.getParameter("confirmado") != null  ) {
						confirmado = request.getParameter("confirmado");
					}
					
					if ( helper.getNomeClienteReceitaFederal() == null || helper.getNomeClienteReceitaFederal().equals("") ) {
						confirmado = null;
						confirmadoCpfCnpj = null;
					}
					
					
					//Verifica se o indicador de consulta na receita esta ativo
					//verifica se a tela de confirmacao ja foi chamada.
					
					boolean verificaIndicadorLiberadoAtualizacao = false;
					
					// VERIFICA SE O RADIO BUTTON SELECIONADO E O DE Imoveis com Ocorrencia Cadastro  
					if(atualizados){
						verificaIndicadorLiberadoAtualizacao = this.retornaIndicadorLiberadoAtualizacaoGsan(request, helper);
					}
					
					if(!atualizados || (atualizados && verificaIndicadorLiberadoAtualizacao)){
					
						if (indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) && 
								((confirmadoCpfCnpj != null && !confirmadoCpfCnpj.equals("3") ) || confirmadoCpfCnpj == null )){
							
							ConsultaWebServiceGATEWAY consultaWebService = new ConsultaWebServiceGATEWAY();
							
							try {
								
								if(!atualizados || (atualizados && verificaIndicadorLiberadoAtualizacao)){
									//CPF
									if (cpf != null){
										
										//Verifica se os dados da receita ja estao cadastrado no GSAN para esse cpf informado
										ConsultarReceitaFederal consultaGSAN = Fachada.getInstancia().pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(cpf);
										if(consultaGSAN == null){
											//Caso nao exista os dados cadastrados no GSAN, faz a consulta na receita
											consultaRF = consultaWebService.consultarPessoaFisica(cpf, usuario, null, null, null);
											System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CPF: "+helper.getCpfCnpj());
											sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
											sessao.setAttribute("consultaGSAN", false);
											helper.setNomeClienteReceitaFederal(consultaRF.getNomePessoaFisica());
										}else{
											System.out.println("CONSULTA GSAN INSERIR CLIENTE CPF: "+helper.getCpfCnpj());
											sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
											sessao.setAttribute("consultaGSAN", true);
											helper.setNomeClienteReceitaFederal(consultaGSAN.getNomePessoaFisica());
										}
										
									}else if ( cnpj != null){
										//CNPJ
										//Verifica se os dados da receita ja estao cadastrado no GSAN para esse cnpj informado
										ConsultarReceitaFederal consultaGSAN = Fachada.getInstancia().pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(cnpj);
										if(consultaGSAN == null){
											//Caso nao exista os dados cadastrados no GSAN, faz a consulta na receita
											consultaRF = consultaWebService.consultaPessoaJuridica(cnpj, usuario, null, null, null);
											System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CNPJ: "+helper.getCpfCnpj());
											sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
											sessao.setAttribute("consultaGSAN", false);
											helper.setNomeClienteReceitaFederal(consultaRF.getRazaoSocial());
										}else{
											System.out.println("CONSULTA GSAN INSERIR CLIENTE CNPJ: "+helper.getCpfCnpj());
											sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
											sessao.setAttribute("consultaGSAN", true);
											helper.setNomeClienteReceitaFederal(consultaGSAN.getRazaoSocial());
										}
									}
								}
								
							} catch (Exception e) {
								//Erro na consulta da receita federal.
								if(consultaRF != null && consultaRF.getMensagemRetornoReceitaFederal() != null){
									throw new ActionServletException("atencao.falha_webservice_gateway", consultaRF.getMensagemRetornoReceitaFederal().getCodigoMensagemRetorno() + " - " 
											+ consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno() + ". Os dados do cliente não podem ser atualizados");
								}else{
									throw new ActionServletException("atencao.falha_webservice_gateway", "0199" + " - " + "Falha na consulta"+ ". Os dados do cliente não podem ser atualizados");
								}
							}
						}
						
						if(indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
							consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
							if ( consultaRF != null ) {
								if(consultaRF.getCpfCliente() != null && !consultaRF.getCpfCliente().equals("")){
									helper.setNomeClienteReceitaFederal(consultaRF.getNomePessoaFisica());
									if(consultaRF.getNomePessoaFisica() == null){
										helper.setNomeClienteReceitaFederal( consultaRF.getNomeCliente());
									}
								}else if(consultaRF.getCnpjCliente() != null && !consultaRF.getCnpjCliente().equals("")){
									helper.setNomeClienteReceitaFederal(consultaRF.getRazaoSocial());
									if(consultaRF.getRazaoSocial() == null){
										helper.setNomeClienteReceitaFederal(consultaRF.getNomeCliente());
									}
								}
							}
						} else {
							helper.setNomeClienteReceitaFederal(helper.getNomeCliente());
						}
					
						//verifica que nao vai entrar novamente no reload.
						if (  (confirmadoCpfCnpj != null && !confirmadoCpfCnpj.equals("3") ) || confirmadoCpfCnpj == null ){
							
							//Verifica se o nome na receita é igual ao nome informado, caso seja diferente monta uma tela de confirmação
							if ( helper.getNomeClienteReceitaFederal() != null && !helper.getNomeClienteReceitaFederal().equals("") && 
									!helper.getNomeClienteReceitaFederal().equals(helper.getNomeCliente()) && indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) ) {
								
								request.setAttribute("nomeBotao1", "Aceitar");
								request.setAttribute("nomeBotao3", "Rejeitar");
								request.setAttribute("tipoRelatorio", "3");
					
								return montarPaginaConfirmacao("atencao.confirmacao_nome_receita_federal",
										request, actionMapping, helper.getNomeCliente(), helper.getNomeClienteReceitaFederal());
							} else {
								if(atualizados){
									helper.setIndicadorHabilitaTipoSituacao("4");
								}
								helper.setIndicadorCpfCnpjValidadoNaReceita("1");
							}
						} else if ( confirmadoCpfCnpj != null && confirmadoCpfCnpj.equals("3") && confirmado != null && confirmado.equals("ok") ) {
							
							helper.setNomeCliente(helper.getNomeClienteReceitaFederal());
							
							Collection<ClienteAtualizacaoCadastral> colecaoClienteAtualizacaoCadastral = Fachada.getInstancia().pesquisarClienteAtualizacaoCadastral(Integer.valueOf(helper.getIdImovelAtualizacaoCadastral()));
							
							if ( colecaoClienteAtualizacaoCadastral != null && !colecaoClienteAtualizacaoCadastral.isEmpty() ) {
								
								ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = (ClienteAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoClienteAtualizacaoCadastral);
								
								clienteAtualizacaoCadastral.setNomeCliente(helper.getNomeClienteReceitaFederal());
								Fachada.getInstancia().atualizar(clienteAtualizacaoCadastral);
								System.out.println("ATUALIZOU CLIENTE: OK");
							}
							
							consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
							Date dataAtual = new Date();
							consultaRF.setDataAcesso(dataAtual);
							consultaRF.setAcaoUsuario(Short.parseShort("1"));
							
							this.getFachada().inserirOuAtualizar(consultaRF);
							System.out.println("ATUALIZOU CONSULTA RF: OK");
							helper.setIndicadorCpfCnpjValidadoNaReceita("1");
							helper.setIndicadorCpfCnpjConfirmado("1");
							
							//indicador responsavel por carregar as opções de situações permitidas.
							helper.setIndicadorHabilitaTipoSituacao("4");
							
						} else if ( confirmadoCpfCnpj != null && confirmadoCpfCnpj.equals("3") && confirmado != null && confirmado.equals("nao") ) {
							
							consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
							consultaRF.setAcaoUsuario(Short.parseShort("2"));
							Date dataAtual = new Date();
							consultaRF.setDataAcesso(dataAtual);
							
							this.getFachada().inserirOuAtualizar(consultaRF);
							System.out.println("ATUALIZOU CONSULTA RF: NAO");
							helper.setIndicadorCpfCnpjValidadoNaReceita("1");
							helper.setIndicadorCpfCnpjConfirmado("2");
							
							//indicador responsavel por carregar as opções de situações permitidas.
							helper.setIndicadorHabilitaTipoSituacao("5");
						}
						//fim 
					}
				}
		}
		form.setIndicadorValidarCpfCnpjRF(null);
		return retorno;	
	}
	
	private String montarInscricaoFormatada( DadosImovelPreGsanHelper helper) {
		Imovel imovel = new Imovel();
		Localidade localidade = new Localidade();
		SetorComercial setorComercial = new SetorComercial();
		Quadra quadra = new Quadra();
		// parte da montagem da inscrição
		// primeiro o id da localidade
		localidade.setId(Integer.valueOf(helper.getIdLocalidade()));
		imovel.setLocalidade(localidade);
		
		// codigo do setor comercial
		setorComercial.setCodigo(Integer.valueOf(helper.getCodigoSetorComercial()));
		imovel.setSetorComercial(setorComercial);
		// número da quadra
		quadra.setNumeroQuadra(Integer.valueOf(helper.getNumeroQuadra()));
		imovel.setQuadra(quadra);
		// lote
		imovel.setLote(Short.valueOf(helper.getNumeroLote()));
		// sublote
		imovel.setSubLote(Short.valueOf(helper.getNumeroSubLote()));
		
		return imovel.getInscricaoFormatada();
	}
	
	/**
	 * Pesquisar Cadastradores de Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarCadastradores(ConsultarImoveisPreGsanActionForm form, HttpServletRequest request){
		Collection<DadosLeiturista> colecaoLeiturista = new ArrayList<DadosLeiturista>();
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.USUARIO_NOME);
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
		
		Collection<?> colecao = Fachada.getInstancia().pesquisar(filtroLeiturista,	Leiturista.class.getName());

		if (!Util.isVazioOrNulo(colecao)) {
			Iterator<?> it = colecao.iterator();
			while (it.hasNext()) {
				Leiturista leitu = (Leiturista) it.next();
				DadosLeiturista dadosLeiu = null;
				
				if(leitu.getUsuario() != null){
					dadosLeiu = new DadosLeiturista(leitu.getId(), leitu.getUsuario().getNomeUsuario());
				}
				
				if(!colecaoLeiturista.contains(dadosLeiu)){
					colecaoLeiturista.add(dadosLeiu);
				}
			}
		}
		
		request.getSession().setAttribute("colecaoLeiturista", colecaoLeiturista);
	}
	
	/**
	 * @author Jonathan Marcos
	 * @date 17/04/2014
	 * [OBSERVACOES]
	 * 		- MONTA A stringImovelPreGsanHelper SETADA NO REQUEST 
	 */
	public String[] verificaAlteracaoTipoLiberadoAtualizacaoDadosImovelPreGsan(HttpServletRequest httpServletRequest,Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper){
			HashMap<String, String[]> hashMapRequest = (HashMap<String, String[]>) httpServletRequest.getParameterMap();
			String[] retornoHashMapRequest = new String[1];
			String[] hashMapRetorno = new String[colecaoDadosImovelPreGsanHelper.size()];
			
			Iterator iterator = colecaoDadosImovelPreGsanHelper.iterator();
			DadosImovelPreGsanHelper dadosImovelPreGsanHelper = null;
			int count = 0;
			while(iterator.hasNext()){
				dadosImovelPreGsanHelper = new DadosImovelPreGsanHelper();
				dadosImovelPreGsanHelper = (DadosImovelPreGsanHelper) iterator.next();
				retornoHashMapRequest = hashMapRequest.get("alteracao"+dadosImovelPreGsanHelper.getIdImovelAtualizacaoCadastral());
				hashMapRetorno[count] = dadosImovelPreGsanHelper.getIdImovelAtualizacaoCadastral()+";"+this.indicadorTipoLiberadoAlteracaoGsan(retornoHashMapRequest);
				count++;
			}
		return hashMapRetorno;
	}
	
	/**
	 * @author Jonathan Marcos
	 * @date 17/04/2014
	 * [OBSERVACOES]
	 * 		- VERIFICA INDICADOR TIPO LIBERADO ALTERACAO GSAN 
	 */
	public boolean indicadorTipoLiberadoAlteracaoGsan(String[] retornoHashMap){
		boolean retornoIndicador = false;
		if(retornoHashMap[0].toString().compareTo(ConstantesSistema.SIM.toString())==0){
			retornoIndicador = true;
		}
		return retornoIndicador;
	}

	/**
	 * @author Jonathan Marcos
	 * @date 17/04/2014
	 * [OBSERVACOES]
	 * 		- RETORNA O INDICADOR DA LIBERADO ALTERACAO GSAN 
	 */
	public boolean retornaIndicadorLiberadoAtualizacaoGsan(HttpServletRequest httpServletRequest,DadosImovelPreGsanHelper helper){
		String[] listaDados = (String[]) httpServletRequest.getSession(false).getAttribute("stringImovelPreGsanHelper");
		String[] temporario = new String[1]; 
		
		boolean verificaIndicadorLiberadoAtualizacao = false;
		for(int posicao = 0;posicao<listaDados.length;posicao++){
			temporario = listaDados[posicao].split(";");
			if(temporario[0].toString().compareTo(helper.getIdImovelAtualizacaoCadastral())==0){
				verificaIndicadorLiberadoAtualizacao = Boolean.valueOf(temporario[1]);
				break;
			}
		}
		return verificaIndicadorLiberadoAtualizacao;
	}
	
	/**
	 * @author Jonathan Marcos
	 * @date 17/04/2014
	 * [OBSERVACOES]
	 * 		- VERIFICA TIPO LIBERADO ATUALIZACAO GSAN NO REQUEST
	 *        stringImovelPreGsanHelper 
     */
	public boolean verificarSelecaoLiberadoAtualizacaoGsanView(HttpServletRequest httpServletRequest){
		String[] listaDados = (String[]) httpServletRequest.getSession(false).getAttribute("stringImovelPreGsanHelper");
		String[] temporario = new String[1];
		boolean verificaSelecaoLiberado = false;
		for(int posicao = 0;posicao<listaDados.length;posicao++){
			temporario = listaDados[posicao].split(";");
			if(Boolean.valueOf(temporario[1])==true){
				verificaSelecaoLiberado = true;
				break;
			}
		}
		return verificaSelecaoLiberado;
	}
}