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
package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00725] Gerar Relat�rio de Im�veis por Situa��o da Liga��o de �gua
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarCertidaoNegativaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirCertidaoNegativa");
		
		// Form
		GerarCertidaoNegativaActionForm form = (GerarCertidaoNegativaActionForm) actionForm;
		
		//Sessao
		HttpSession sessao = httpServletRequest.getSession();
		
		String acessoLoja = httpServletRequest.getParameter("acessoLoja");
		
		Fachada fachada = Fachada.getInstancia();
		
		if(httpServletRequest.getParameter("acessoLoja") != null && httpServletRequest.getParameter("acessoLoja").equals("1")){
			if(sessao.getAttribute("matricula") != null && !sessao.getAttribute("matricula").equals("")){
				Integer matricula = (Integer) sessao.getAttribute("matricula");
				form.setIdImovel(String.valueOf(matricula));
			
				String ip = httpServletRequest.getRemoteAddr(); 
				fachada.verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CERTIDAO_NEGATIVA_DEBITOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
			}
		}
		
		// Imovel que foi informado
		Imovel imo = null;
		
		if (form.getIdImovel() != null && 
			!form.getIdImovel().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			imo = new Imovel();
			imo.setId( Integer.valueOf( form.getIdImovel() ) );
			
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imo.getId()));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo.esferaPoder");
			
			Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			
			if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
				
				if (clienteImovel.getCliente().getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaImovel().equals(ConstantesSistema.NAO)) {
					if(acessoLoja != null && acessoLoja.equals("1")){
						httpServletRequest.setAttribute("esferaPoderResponsavel", true);
					}else{
						throw new ActionServletException("atencao.esfera_poder_responsavel_nao_permite_geracao_certidao_negativa");
					}
				}
			}
			
		}		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Usuario usuarioLogado = this.getUsuarioLogado( httpServletRequest );
		
		TarefaRelatorio relatorio = 
			new RelatorioCertidaoNegativa( usuarioLogado );		
		
		relatorio.addParametro("imovel", imo);
		relatorio.addParametro("usuarioLogado", usuarioLogado);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}	

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
}