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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.cadastro.AreaAtualizacaoCadastral;
import gcom.cadastro.FiltroAreaAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarGerarTabelasTemporariasPorLocalidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarGerarTabelasTemporariasPorLocalidade");

		GerarTabelasTemporariasPorLocalidadeActionForm form = 
			(GerarTabelasTemporariasPorLocalidadeActionForm) actionForm;
		
		if ((httpServletRequest.getParameter("limparForm") != null && 
			httpServletRequest.getParameter("limparForm").equalsIgnoreCase("S"))){

			// Limpando o formulario
			form.setEmpresa("-1");
			form.setNomeImovel("");
			form.setMatriculaImovel("");
			form.setRota(null);
			form.setRotaSelecionados(null);
			form.setQuadra(null);
			form.setQuadraSelecionados(null);
			form.setSetorComercial(null);
			form.setSetorComercialSelecionados(null);
			form.setIndicadorGeracaoSetor(null);
			form.setIndicadorSelecaoQuadraRota(null);
			form.setLocalidade("-1");
			
		}
		
		if ((httpServletRequest.getParameter("objetoConsulta") != null && 
				httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("limparInscricao"))){

				// Limpando o formulario
				form.setNomeImovel("");
				form.setMatriculaImovel("");
				form.setRota(null);
				form.setRotaSelecionados(null);
				form.setQuadra(null);
				form.setQuadraSelecionados(null);
				form.setSetorComercial(null);
				form.setSetorComercialSelecionados(null);
				form.setIndicadorGeracaoSetor(null);
				form.setIndicadorSelecaoQuadraRota(null);
				
				this.pesquisarLocalidade(form,httpServletRequest);
			}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		this.pesquisarEmpresa(httpServletRequest);

		// Faz a consulta de Localidade
		if ( objetoConsulta != null && objetoConsulta.equals("localidade") ) {
			if ( form.getEmpresa() != null && !form.getEmpresa().equals("-1") ) {
				this.pesquisarLocalidade(form,httpServletRequest);
			} else {
				form.setLocalidade("-1");
			}
		} else if ( form.getLocalidade() != null && !form.getLocalidade().equals("-1") ) {
			
			this.pesquisarLocalidade(form,httpServletRequest);
			
			if ( form.getIndicadorGeracaoSetor() != null && form.getIndicadorGeracaoSetor().equals("2") ) {
				this.pesquisarSetorComercial(form,httpServletRequest);
			}
		}
		
		if ( objetoConsulta != null && objetoConsulta.equals("total") ) {
			form.setIndicadorSelecaoQuadraRota(null);
			form.setSetorComercial(null);
			form.setSetorComercialSelecionados(null);
			form.setQuadra(null);
			form.setQuadraSelecionados(null);
			form.setRota(null);
			form.setRotaSelecionados(null);
		}
		
		if ( objetoConsulta != null && objetoConsulta.equals("setorComercial") ) {
			this.pesquisarSetorComercial(form,httpServletRequest);
			this.pesquisarSetorComercialEnviados(form, httpServletRequest);
			this.pesquisarSetorComercialEnviadosDinamico(form, httpServletRequest);
		} else if ( (form.getSetorComercial() != null && !form.getSetorComercial().equals("-1")) ) {
			this.pesquisarSetorComercial(form,httpServletRequest);
			this.pesquisarSetorComercialEnviados(form, httpServletRequest);
			this.pesquisarSetorComercialEnviadosDinamico(form, httpServletRequest);
		}
		
		if ( form.getSetorComercialSelecionados() != null ) {
			this.pesquisarSetorComercialSelecionados(form,httpServletRequest);
			this.pesquisarSetorComercialEnviados(form, httpServletRequest);
			this.pesquisarSetorComercialEnviadosDinamico(form, httpServletRequest);
		}

		if ( objetoConsulta != null && objetoConsulta.equals("quadra") ) {
			form.setRota(null);
			form.setRotaSelecionados(null);
			this.pesquisarQuadra(form,httpServletRequest);
		} else if ( form.getQuadra() != null && !form.getQuadra().equals("-1") ) {
			this.pesquisarQuadra(form,httpServletRequest);
		}
		
		if ( form.getQuadraSelecionados() != null ) {
			this.pesquisarQuadraSelecioadas(form, httpServletRequest);
		}
		
		if ( objetoConsulta != null && objetoConsulta.equals("rota") ) {
			form.setQuadra(null);
			form.setQuadraSelecionados(null);
			this.pesquisarRota(form,httpServletRequest);
		} else if ( form.getRota() != null && !form.getRota().equals("-1") ) {
			this.pesquisarRota(form,httpServletRequest);
		}
		
		if ( form.getRotaSelecionados() != null ) {
			this.pesquisarRotaSelecionadas(form, httpServletRequest);
		}
		
		// Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("5")   ) {

			this.pesquisarImovel(form, httpServletRequest);
		}
		
		//Pesquisar Quadra 
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("quadra")   ) {
				
			Integer[] idsSetorComercialSelecionado = (Integer[]) form.getSetorComercialSelecionados();
			boolean setorComercialInformado = false;
			if(idsSetorComercialSelecionado.length > 0){
				if(idsSetorComercialSelecionado[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
					
					setorComercialInformado = false;
				}else{
					setorComercialInformado = true;
				}
			}
	
			if ( setorComercialInformado ) {
				// Faz a consulta do Imovel
				this.pesquisarQuadra(form,httpServletRequest);
			} 
		}
		

		return retorno;
	}
	
	/**
	 * Pesquisa Imovel
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarImovel(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest request) {

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatriculaImovel()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

		// Recupera Localidade
		Collection<Imovel> colecaoImovel = this.getFachada().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

			form.setMatriculaImovel(imovel.getId().toString());
			form.setNomeImovel(imovel.getInscricaoFormatada());

		} else {
			form.setMatriculaImovel("");
			request.setAttribute("imovelEncontrado", true);
			form.setNomeImovel("Imóvel inexistente");
		}
	}
	
	
	/**
	 * Pesquisa Empresa 
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Empresa> colecaoEmpresa = 
			this.getFachada().pesquisar(filtroEmpresa,Empresa.class.getName());


		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa",colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisa Localidade
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void pesquisarLocalidade(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest httpServletRequest) {

		
//		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = new FiltroAreaAtualizacaoCadastral();
//		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, Integer.valueOf(form.getEmpresa())));
//		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
//		filtroAreaAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.LOCALIDADE);
		
		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisarLocalidadeAreaAtualizacaoCadastral(Integer.valueOf(form.getEmpresa()));
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);
		} 
	}
	
	private void pesquisarSetorComercial(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest httpServletRequest) {

		//2.6.1.1
		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = new FiltroAreaAtualizacaoCadastral();
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, Integer.valueOf(form.getEmpresa())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, Integer.valueOf(form.getLocalidade())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL));

		Collection<AreaAtualizacaoCadastral> colecaoAreaAtualizacaoCadastral = this.getFachada().pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
	
		if (colecaoAreaAtualizacaoCadastral != null && !colecaoAreaAtualizacaoCadastral.isEmpty()) {
			
			Collection<Integer> colecaoSetorComercialSelecionados = null;			
			if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
				colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				
				for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
					colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
				}
			}
			Collection<SetorComercial> colecao = pesquisarSetorComercialEnviados(form, httpServletRequest);
			if ( colecao != null && !colecao.isEmpty() ) {
				if ( colecaoSetorComercialSelecionados == null ) {
					colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				}
				Iterator<SetorComercial> iterator = colecao.iterator();
				while ( iterator.hasNext() ) {
					SetorComercial comercial = (SetorComercial) iterator.next();
					 
					colecaoSetorComercialSelecionados.add(comercial.getCodigo());
				}
			}

			Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisarColecaoSetorComercialTabelasTemporarias(form.getLocalidade(),
					form.getEmpresa(), colecaoSetorComercialSelecionados, " not in ");
				
			
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
			}
			
		}  else {
			
			Collection<Integer> colecaoSetorComercialSelecionados = null;			
			if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
				colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				
				for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
					colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
				}
			}
			
			Collection<SetorComercial> colecao = pesquisarSetorComercialEnviados(form, httpServletRequest);
			if ( colecao != null && !colecao.isEmpty() ) {
				if ( colecaoSetorComercialSelecionados == null ) {
					colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				}
				Iterator<SetorComercial> iterator = colecao.iterator();
				while ( iterator.hasNext() ) {
					SetorComercial comercial = (SetorComercial) iterator.next();
					 
					colecaoSetorComercialSelecionados.add(comercial.getCodigo());
				}
			}
		
			Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisarColecaoSetorComercialTabelasTemporariasParcial(form.getLocalidade(), 
					form.getEmpresa(), colecaoSetorComercialSelecionados, " not in ");
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
			}
		}
	}
	
	/**
	 * Pesquisa Setor comercial
	 * @author Arthur Carvalho
	 * @date 26/04/2012
	 */
	private void pesquisarSetorComercialSelecionados(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest httpServletRequest) {

		//2.6.1.1
				FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = new FiltroAreaAtualizacaoCadastral();
				filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, Integer.valueOf(form.getEmpresa())));
				filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
				filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, Integer.valueOf(form.getLocalidade())));
				filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL));

				Collection<AreaAtualizacaoCadastral> colecaoAreaAtualizacaoCadastral = this.getFachada().pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
			
				if (colecaoAreaAtualizacaoCadastral != null && !colecaoAreaAtualizacaoCadastral.isEmpty()) {
					
					Collection<Integer> colecaoSetorComercialSelecionados = null;			
					if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
						colecaoSetorComercialSelecionados = new ArrayList<Integer>();
						
						for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
							colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
						}
					}

					Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisarColecaoSetorComercialTabelasTemporarias(form.getLocalidade(),
							form.getEmpresa(), colecaoSetorComercialSelecionados, "in");
						
					
					
					if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
						httpServletRequest.setAttribute("colecaoSetorComercialSelecionados", colecaoSetorComercial);
					}
					
				}  else {
					
					Collection<Integer> colecaoSetorComercialSelecionados = null;			
					if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
						colecaoSetorComercialSelecionados = new ArrayList<Integer>();
						
						for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
							colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
						}
					}
				
					Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisarColecaoSetorComercialTabelasTemporariasParcial(form.getLocalidade(), 
							form.getEmpresa(), colecaoSetorComercialSelecionados, "in");
					
					if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
						httpServletRequest.setAttribute("colecaoSetorComercialSelecionados", colecaoSetorComercial);
					}
				}
	}
	
	/**
	 * Pesquisa Setor comercial
	 * @author Arthur Carvalho
	 * @date 26/04/2012
	 */
	private Collection<SetorComercial> pesquisarSetorComercialEnviados(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest httpServletRequest) {

		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = new FiltroAreaAtualizacaoCadastral();
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, Integer.valueOf(form.getEmpresa())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, Integer.valueOf(form.getLocalidade())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL));

		Collection<AreaAtualizacaoCadastral> colecaoAreaAtualizacaoCadastral = this.getFachada().pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
		Collection<SetorComercial> colecaoSetorComercialEnviados = null;
		if (colecaoAreaAtualizacaoCadastral != null && !colecaoAreaAtualizacaoCadastral.isEmpty()) {
			
			colecaoSetorComercialEnviados = Fachada.getInstancia().
					pesquisarColecaoSetorComercialEnviadosTabelasTemporarias(form.getLocalidade(), form.getEmpresa());
			
			if (colecaoSetorComercialEnviados != null && !colecaoSetorComercialEnviados.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercialEnviados", colecaoSetorComercialEnviados);
			}
		} else {
			colecaoSetorComercialEnviados = Fachada.getInstancia().
					pesquisarColecaoSetorComercialEnviadosTabelasTemporariasParcial(form.getLocalidade(), form.getEmpresa());
			
			if (colecaoSetorComercialEnviados != null && !colecaoSetorComercialEnviados.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercialEnviados", colecaoSetorComercialEnviados);
			}
		}
		return colecaoSetorComercialEnviados;
	}
	
	/**
	 * Pesquisa Setor comercial
	 * @author Arthur Carvalho
	 * @date 26/04/2012
	 */
	private Collection<SetorComercial> pesquisarSetorComercialEnviadosDinamico(GerarTabelasTemporariasPorLocalidadeActionForm form, HttpServletRequest httpServletRequest) {
		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = new FiltroAreaAtualizacaoCadastral();
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, Integer.valueOf(form.getEmpresa())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, Integer.valueOf(form.getLocalidade())));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL));

		Collection<AreaAtualizacaoCadastral> colecaoAreaAtualizacaoCadastral = this.getFachada().pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
		Collection<SetorComercial> colecaoSetorComercialEnviados = null;
		if (colecaoAreaAtualizacaoCadastral != null && !colecaoAreaAtualizacaoCadastral.isEmpty()) {
			
			Collection<Integer> colecaoSetorComercialSelecionados = null;			
			if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
				colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				
				for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
					colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
				}
			}
			
			colecaoSetorComercialEnviados = Fachada.getInstancia().
					pesquisarColecaoSetorComercialEnviadosTabelasTemporarias(form.getLocalidade(), form.getEmpresa(), colecaoSetorComercialSelecionados);
			
			if (colecaoSetorComercialEnviados != null && !colecaoSetorComercialEnviados.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercialEnviadosDinamico", colecaoSetorComercialEnviados);
			}
			
		} else {
			Collection<Integer> colecaoSetorComercialSelecionados = null;			
			if ( form.getSetorComercialSelecionados() != null && !form.getSetorComercialSelecionados().equals("-1") ) {
				colecaoSetorComercialSelecionados = new ArrayList<Integer>();
				
				for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
					colecaoSetorComercialSelecionados.add(form.getSetorComercialSelecionados()[i]);
				}
			}
			
			colecaoSetorComercialEnviados = Fachada.getInstancia().
					pesquisarColecaoSetorComercialEnviadosTabelasTemporariasParcial(form.getLocalidade(), form.getEmpresa(), colecaoSetorComercialSelecionados);
			
			if (colecaoSetorComercialEnviados != null && !colecaoSetorComercialEnviados.isEmpty()) {
				httpServletRequest.setAttribute("colecaoSetorComercialEnviadosDinamico", colecaoSetorComercialEnviados);
			}
		}
		return colecaoSetorComercialEnviados;
	}

	/**
	 * @date 28/12/2011
	 * @author Arthur Carvalho
	 */
	private void pesquisarQuadra(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		String local = form.getLocalidade();
		
		Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
		
		for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
			colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
		}
		
        
		Collection<Quadra> quadras = this.getFachada().pesquisarColecaoQuadraTabelasTemporarias(local,colecaoSetorComercial);
		
		if (quadras != null && !quadras.isEmpty()) {

			httpServletRequest.setAttribute( "colecaoQuadra", quadras );
		} 
	}
	
	/**
	 * @date 28/12/2011
	 * @author Arthur Carvalho
	 */
	private void pesquisarQuadraSelecioadas(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		String local = form.getLocalidade();
		
		Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
		
		for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
			colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
		}
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(local)));
		filtroQuadra.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.CODIGO_SETORCOMERCIAL, colecaoSetorComercial));
        filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
		
        if ( form.getQuadraSelecionados() != null ) {
			Collection<Integer> colecaoQuadra = new ArrayList<Integer>();
			
			for (int i = 0; i < form.getQuadraSelecionados().length; i++) {
				colecaoQuadra.add(form.getQuadraSelecionados()[i]);
			}
			filtroQuadra.adicionarParametro( new ParametroSimplesIn(FiltroQuadra.ID, colecaoQuadra));
		}
        
		Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (quadras != null && !quadras.isEmpty()) {

			httpServletRequest.setAttribute( "colecaoQuadraSelecionadas", quadras );
		} else {
			form.setQuadraSelecionados(null);
		}
	}

	private void pesquisarRota(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
		for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
			colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
		}
		
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, new Integer(form.getLocalidade())));
		filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.SETOR_COMERCIAL_CODIGO, colecaoSetorComercial));
        filtroRota.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");	
		filtroRota.setCampoOrderBy(FiltroRota.SETOR_COMERCIAL_CODIGO, FiltroRota.CODIGO_ROTA);
        
        if ( form.getRotaSelecionados() != null ) {
			Collection<Integer> colecaoRota = new ArrayList<Integer>();
			
			for (int i = 0; i < form.getRotaSelecionados().length; i++) {
				colecaoRota.add(form.getRotaSelecionados()[i]);
			}
			filtroRota.adicionarParametro( new ParametroSimplesNotIn(FiltroRota.CODIGO_ROTA, colecaoRota));
		}
        
		Collection<Rota> rotas = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
		
		if (rotas != null && !rotas.isEmpty()) {

			httpServletRequest.setAttribute( "colecaoRota", rotas );
		} 
	}
	
	private void pesquisarRotaSelecionadas(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
		for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
			colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
		}
		
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, new Integer(form.getLocalidade())));
		filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.SETOR_COMERCIAL_CODIGO, colecaoSetorComercial));
        filtroRota.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroRota.setCampoOrderBy(FiltroRota.SETOR_COMERCIAL_CODIGO, FiltroRota.CODIGO_ROTA);
        
        if ( form.getRotaSelecionados() != null ) {
			Collection<Integer> colecaoRota = new ArrayList<Integer>();
			
			for (int i = 0; i < form.getRotaSelecionados().length; i++) {
				colecaoRota.add(form.getRotaSelecionados()[i]);
			}
			filtroRota.adicionarParametro( new ParametroSimplesIn(FiltroRota.CODIGO_ROTA, colecaoRota));
		}
        
		Collection<Rota> rotas = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
		
		if (rotas != null && !rotas.isEmpty()) {

			httpServletRequest.setAttribute( "colecaoRotaSelecionadas", rotas );
		} else {
			form.setRotaSelecionados(null);
		}
	}

}
