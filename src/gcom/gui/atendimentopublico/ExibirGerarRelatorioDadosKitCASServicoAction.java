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
package gcom.gui.atendimentopublico;
import gcom.cadastro.geografico.FiltroMunicipio;import gcom.cadastro.geografico.Municipio;import gcom.cadastro.localidade.Localidade;import gcom.cadastro.sistemaparametro.SistemaParametro;import gcom.fachada.Fachada;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioDadosKitCASServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioKitCAS");				HttpSession sessao = httpServletRequest.getSession(false);
		GerarRelatorioDadosKitCASServicoActionForm form = 			(GerarRelatorioDadosKitCASServicoActionForm) actionForm;				Fachada fachada = Fachada.getInstancia();				//Cole��o da Ger�ncia Regional		Collection colecaoGerenciaRegional = fachada.obterColecaoGerenciaRegional();		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);				//Cole��o Unidade Neg�cio		Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocioGerencial();		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);								        				//Busca da Localidade atrav�s do Enter  		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");  		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){  			Integer idLocalidade = new Integer(form.getIdLocalidade());  			  			Localidade localidade = fachada.obterColecaoLocalidade(idLocalidade, null,null);  			  			//[FS0002 - Verificar exist�ncia da localidade]			if(localidade != null){				sessao.removeAttribute("localidadeException");  				form.setDescLocalidade(localidade.getDescricao());  				return retorno;			}			else{  				form.setDescLocalidade("LOCALIDADE INEXISTENTE");  				form.setIdLocalidade("");  				sessao.setAttribute("localidadeException","ok");			}  		}  		  		  		//Busca do munic�pio atrav�s do Enter  		String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");  		if(pesquisarMunicipio != null && !"".equals(pesquisarMunicipio)){  			Integer idMunicipio = new Integer(form.getIdMunicipio());  			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();  			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));  			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));  			  			Collection colecao = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());  		  			  			//Municipio municipio = fachada.pesquisarObjetoMunicipioRelatorio(idMunicipio);  			  			//[FS0003 - Verificar exist�ncia da Munic�pio]			if(colecao != null && colecao.size() > 0){				sessao.removeAttribute("municipioException");				Municipio municipio = (Municipio)Util.retonarObjetoDeColecao(colecao);  				form.setDescMunicipio(municipio.getNome());  				return retorno;			}			else{				form.setDescMunicipio("MUNICIPIO INEXISTENTE");  				form.setIdMunicipio("");  				sessao.setAttribute("municipioException","ok");			}  		}
		return retorno;	}
}
