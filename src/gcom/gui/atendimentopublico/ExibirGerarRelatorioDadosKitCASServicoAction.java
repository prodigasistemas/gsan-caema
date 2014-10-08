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
package gcom.gui.atendimentopublico;
import gcom.cadastro.geografico.FiltroMunicipio;import gcom.cadastro.geografico.Municipio;import gcom.cadastro.localidade.Localidade;import gcom.cadastro.sistemaparametro.SistemaParametro;import gcom.fachada.Fachada;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioDadosKitCASServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioKitCAS");				HttpSession sessao = httpServletRequest.getSession(false);
		GerarRelatorioDadosKitCASServicoActionForm form = 			(GerarRelatorioDadosKitCASServicoActionForm) actionForm;				Fachada fachada = Fachada.getInstancia();				//Coleção da Gerência Regional		Collection colecaoGerenciaRegional = fachada.obterColecaoGerenciaRegional();		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);				//Coleção Unidade Negócio		Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocioGerencial();		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);								        				//Busca da Localidade através do Enter  		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");  		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){  			Integer idLocalidade = new Integer(form.getIdLocalidade());  			  			Localidade localidade = fachada.obterColecaoLocalidade(idLocalidade, null,null);  			  			//[FS0002 - Verificar existência da localidade]			if(localidade != null){				sessao.removeAttribute("localidadeException");  				form.setDescLocalidade(localidade.getDescricao());  				return retorno;			}			else{  				form.setDescLocalidade("LOCALIDADE INEXISTENTE");  				form.setIdLocalidade("");  				sessao.setAttribute("localidadeException","ok");			}  		}  		  		  		//Busca do município através do Enter  		String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");  		if(pesquisarMunicipio != null && !"".equals(pesquisarMunicipio)){  			Integer idMunicipio = new Integer(form.getIdMunicipio());  			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();  			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));  			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));  			  			Collection colecao = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());  		  			  			//Municipio municipio = fachada.pesquisarObjetoMunicipioRelatorio(idMunicipio);  			  			//[FS0003 - Verificar existência da Município]			if(colecao != null && colecao.size() > 0){				sessao.removeAttribute("municipioException");				Municipio municipio = (Municipio)Util.retonarObjetoDeColecao(colecao);  				form.setDescMunicipio(municipio.getNome());  				return retorno;			}			else{				form.setDescMunicipio("MUNICIPIO INEXISTENTE");  				form.setIdMunicipio("");  				sessao.setAttribute("municipioException","ok");			}  		}
		return retorno;	}
}
