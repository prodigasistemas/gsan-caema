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

* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.ImovelEnderecoArquivoTextoHelper;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class ExibirLiberarLogradourosParaAtualizacaoAction extends GcomAction {
	
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirLiberarLogradourosParaAtualizacaoAction");

		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		LiberarLogradourosParaAtualizacaoActionForm form = (LiberarLogradourosParaAtualizacaoActionForm) actionForm;

		if (httpServletRequest.getParameter("pesquisarMunicipio") != null) {
			this.pesquisarMunicipio(form, sessao);
			httpServletRequest.setAttribute("nomeCampo", "municipio");
		}
		else if (httpServletRequest.getParameter("pesquisarLogradouros") != null) {
			this.pesquisarLogradouros(form, sessao, fachada);
		}
		else if(httpServletRequest.getParameter("limpar") != null){
			form.setIdMunicipio("");
			form.setDescricaoMunicipio("");
			sessao.removeAttribute("colecaoImovelEnderecoArquivoTextoHelper");
		}	
		
       return retorno;

	}
	
	/**
	 * Pesquisa Municipio 
	 *
	 * @author Anderson Cabral
	 * @date 25/09/2013
	 */
	private void pesquisarMunicipio(LiberarLogradourosParaAtualizacaoActionForm form, HttpSession sessao) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, new Integer(form.getIdMunicipio())));
		Collection<Municipio> colecaoMunicipio = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setIdMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			sessao.setAttribute("municipioEncontrado", "sim");
		} else {
			form.setIdMunicipio("");
			form.setDescricaoMunicipio("Município inexistente");
			sessao.removeAttribute("municipioEncontrado");
		}
	}
	
	/**
	 * Pesquisa Logradouros 
	 *
	 * @author Anderson Cabral
	 * @date 25/09/2013
	 */
	private void pesquisarLogradouros(LiberarLogradourosParaAtualizacaoActionForm form, HttpSession sessao, Fachada fachada) {
		
		ArrayList<ImovelEnderecoArquivoTextoHelper> colecaoImovelEnderecoArquivoTextoHelper = fachada.pesquisarLogradourosParaAtualizar(Integer.parseInt(form.getIdMunicipio()));
		
		if(!Util.isVazioOrNulo(colecaoImovelEnderecoArquivoTextoHelper)){			
			sessao.setAttribute("colecaoImovelEnderecoArquivoTextoHelper", colecaoImovelEnderecoArquivoTextoHelper);
		}else{
			sessao.removeAttribute("colecaoImovelEnderecoArquivoTextoHelper");
			throw new ActionServletException("atencao.nenhum_logradouro_encontrado");
		}
	}
}
