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
* Anderson Italo Felinto de Lima
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.atualizacaocadastral.FiltroLogradouroAtlzCad;
import gcom.cadastro.atualizacaocadastral.FiltroLogradouroBairroAtlzCad;
import gcom.cadastro.atualizacaocadastral.FiltroLogradouroCepAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroBairroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroCepAtlzCad;
import gcom.cadastro.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Pre- processamento para inserir os logradouros adicionados pelo tablet
 * 
 * @author Anderson Cabral
 * @date 06/03/2012
 */
public class ExibirInserirNovosLogradourosAtualizacaoCadastralAction extends GcomAction{
	

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);		
		ActionForward retorno = actionMapping.findForward("exibirInserirNovosLogradourosAtualizacaoCadastralAction");
		
		InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm = (InserirNovosLogradourosAtualizacaoCadastralActionForm) actionForm;
		
		//Caso tenha sido a primeira vez que carrega a tela
		if (httpServletRequest.getParameter("pesquisarLogradouros") == null && 
				httpServletRequest.getParameter("substituirLogradouro") == null){
			
			//Carregar lista de Empresas
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();		
			filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			
			//Pesquisar Localidade
			if (inserirNovosLogradourosActionForm.getIdLocalidade() != null && !inserirNovosLogradourosActionForm.getIdLocalidade().trim().equals("")) {			
				pesquisarLocalidade( sessao, inserirNovosLogradourosActionForm);
			}
			
		}
		
		//Pesquisa os logradouros 
		if (httpServletRequest.getParameter("pesquisarLogradouros") != null){			
			montarColecaoLogradouros(inserirNovosLogradourosActionForm, fachada, sessao);
		}
		
		//Substituir Logradouro
		if(httpServletRequest.getParameter("substituirLogradouro") != null){
			String[] idLogradourosSelecionados = inserirNovosLogradourosActionForm.getIdsRegistros();
			String idLogradouroSubstituto  	   = inserirNovosLogradourosActionForm.getIdLogradouroSubstituto();
			
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");
			
			//pesquisa o logradouro a ser substituido
			for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
				for(String idLogradouroSelecionado : idLogradourosSelecionados){
					if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equalsIgnoreCase(idLogradouroSelecionado)){
						logradouroHelper.setIdSubstituirLogra(idLogradouroSubstituto);
						break;
					}
				}
			}
			
			sessao.setAttribute("colecaoLogradouroHelper", colecaoLogradouroHelper);
		
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa a localidade pelo id
	 * 
	 * @author Anderson Cabral
	 * @date 06/03/2012
	 */
	private void pesquisarLocalidade(HttpSession session, 
			InserirNovosLogradourosAtualizacaoCadastralActionForm form) {

		Fachada fachada = Fachada.getInstancia();
		
		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));	
		
		Collection<Localidade> localidadePesquisada = 
				fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		// Se nenhuma localidade for encontrada a mensagem e enviada para a pagina
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade(""+localidade.getId());
			form.setNomeLocalidade(localidade.getDescricao());
			session.setAttribute("localidadeEncontrada",true);
		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			session.removeAttribute("localidadeEncontrada");
		}
	}
	
	private void montarColecaoLogradouros(InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm, Fachada fachada, HttpSession sessao){
		if(Util.isInteger(inserirNovosLogradourosActionForm.getIdLocalidade())){
			
			FiltroLogradouroAtlzCad filtroLogradouroAtlzCad = new FiltroLogradouroAtlzCad("logradouroTipo.descricao, objeto.nome ");			
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCad.ID_EMPRESA,
					inserirNovosLogradourosActionForm.getIdEmpresa()));
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCad.ID_LOCALIDADE,
					inserirNovosLogradourosActionForm.getIdLocalidade()));
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCad.INDICADOR_ATULIZADO, 2));
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.LOGRADOUROTIPO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.LOGRADOUROTITULO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.MUNICIPIO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.UF);
			
			Collection<LogradouroAtlzCad> colecaoLogradouroAtlzCad = fachada.pesquisar(filtroLogradouroAtlzCad, LogradouroAtlzCad.class.getName());
			
			//caso exista LogradouroAtlzCad para as informacoes passada, monta o helper
			if(colecaoLogradouroAtlzCad != null && colecaoLogradouroAtlzCad.size() != 0){
				AtualizacaoCadastralLogradouroHelper logradouroHelper;
				ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = new ArrayList<AtualizacaoCadastralLogradouroHelper>();				
				
				for(LogradouroAtlzCad logradouroAtlzCad : colecaoLogradouroAtlzCad){
					logradouroHelper = new AtualizacaoCadastralLogradouroHelper();
					logradouroHelper.setLogradouroAtlzCad(logradouroAtlzCad);
					
					//Pesquisa os LogradouroBairroAtlzCad do LogradouroAtlzCad
					ArrayList<LogradouroBairroAtlzCad> colecaoLogradouroBairroAtlzCad = pesquisarLogradouroBairroAtlzCad(logradouroAtlzCad.getId().toString(), fachada);
					logradouroHelper.setColecaoLogardouroBairroAtlzCad(colecaoLogradouroBairroAtlzCad);
					
					//caso exista LogradouroBairroAtlzCad para o LogradouroAtlzCad, monta a String com os nomes dos bairros para exibir no grid
					if(colecaoLogradouroBairroAtlzCad !=  null){
						String bairros = "";
						for(LogradouroBairroAtlzCad logradouroBairroAtlzCad : colecaoLogradouroBairroAtlzCad){							
							bairros = bairros + logradouroBairroAtlzCad.getBairro().getNome() + "<br>";
						}
						logradouroHelper.setBairros(bairros);
					}
					
					//Pesquisa os colecaoLogradouroCepAtlzCad do LogradouroAtlzCad
					ArrayList<LogradouroCepAtlzCad> colecaoLogradouroCepAtlzCad = pesquisarLogradouroCepAtlzCad(logradouroAtlzCad.getId().toString(), fachada);
					logradouroHelper.setColecaoLogradouroCepAtlzCad(colecaoLogradouroCepAtlzCad);
					
					colecaoLogradouroHelper.add(logradouroHelper);
				}
				
				sessao.setAttribute("colecaoLogradouroHelper", colecaoLogradouroHelper);
			}else{
				throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Logradouros");
			}
		}else{
			throw new ActionServletException("atencao.inteiroZeroPositivo", null, "Localidade");
		}
	}
	
	private ArrayList<LogradouroBairroAtlzCad> pesquisarLogradouroBairroAtlzCad(String idLogradouro, Fachada fachada){
		FiltroLogradouroBairroAtlzCad filtroLogradouroBairroAtlzCad = new FiltroLogradouroBairroAtlzCad();
		filtroLogradouroBairroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroBairroAtlzCad.ID_LOGRADOURO_ATLZ_CAD,
				idLogradouro));
		filtroLogradouroBairroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairroAtlzCad.BAIRRO);
		
		ArrayList<LogradouroBairroAtlzCad> colecaoLogradouroCepAtlzCad = (ArrayList<LogradouroBairroAtlzCad>) fachada.pesquisar(filtroLogradouroBairroAtlzCad, LogradouroBairroAtlzCad.class.getName());
		
		return colecaoLogradouroCepAtlzCad;
	}
	
	private ArrayList<LogradouroCepAtlzCad> pesquisarLogradouroCepAtlzCad(String idLogradouro, Fachada fachada){
		FiltroLogradouroCepAtlzCad filtroLogradouroCepAtlzCad = new FiltroLogradouroCepAtlzCad();
		filtroLogradouroCepAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroCepAtlzCad.ID_LOGRADOURO_ATLZ_CAD, idLogradouro));
		filtroLogradouroCepAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCepAtlzCad.CEP_ATLZ_CAD);
		
		ArrayList<LogradouroCepAtlzCad> colecaoLogradouroCepAtlzCad = (ArrayList<LogradouroCepAtlzCad>) fachada.pesquisar(filtroLogradouroCepAtlzCad, LogradouroCepAtlzCad.class.getName());
		
		return colecaoLogradouroCepAtlzCad;
	}
}
