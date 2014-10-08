/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest?o de Servi?os de Saneamento
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
* GSAN - Sistema Integrado de Gest?o de Servi?os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara?jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl?udio de Andrade Lira
* Denys Guimar?es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab?ola Gomes de Ara?jo
* Fl?vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J?nior
* Homero Sampaio Cavalcanti
* Ivan S?rgio da Silva J?nior
* Jos? Edmar de Siqueira
* Jos? Thiago Ten?rio Lopes
* K?ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M?rcio Roberto Batista da Silva
* Maria de F?tima Sampaio Leite
* Micaela Maria Coelho de Ara?jo
* Nelson Mendon?a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr?a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara?jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S?vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa ? software livre; voc? pode redistribu?-lo e/ou
* modific?-lo sob os termos de Licen?a P?blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers?o 2 da
* Licen?a.
* Este programa ? distribu?do na expectativa de ser ?til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl?cita de
* COMERCIALIZA??O ou de ADEQUA??O A QUALQUER PROP?SITO EM
* PARTICULAR. Consulte a Licen?a P?blica Geral GNU para obter mais
* detalhes.
* Voc? deve ter recebido uma c?pia da Licen?a P?blica Geral GNU
* junto com este programa; se n?o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] ? Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
 * 
 * @author Th?lio Ara?jo
 *
 * @date 22/08/2011
 */
public class AcompanhamentoArquivosRoteiroRemanejarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remanejarEquipeOSAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
		acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		AcompanharRoteiroProgramacaoOrdemServicoHelper helper = this.CarregarAcompanharRoteiroProgramacaoHelper(acompanharActionForm);
		
		if(confirmado == null || confirmado.equals("")){
			sessao.setAttribute("idEquipeAtual", acompanharActionForm.getIdEquipeAtual());
			sessao.setAttribute("idEquipePrincipal", acompanharActionForm.getIdEquipePrincipal());
			sessao.setAttribute("idOS", acompanharActionForm.getIdsOs());
			sessao.setAttribute("idUnidade", acompanharActionForm.getIdUnidade());
		}
		else{
			helper.setIdEquipeAtual((String)sessao.getAttribute("idEquipeAtual"));
			helper.setIdEquipePrincipal((String)sessao.getAttribute("idEquipePrincipal"));
			helper.setIdsOs((String)sessao.getAttribute("idOS"));
			helper.setIdUnidade((String)sessao.getAttribute("idUnidade"));
						
		}
		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		helper.setDataRoteiro(Util.formatarData(dataRoteiro));
		helper.setUnidadeLotacao(""+idUnidadeLotacao);

		/*OrdemServico os = new OrdemServico();
		os.setId(Integer.parseInt(helper.getIdsOs()));*/
		
		FiltroEquipe filtro = new FiltroEquipe();
		filtro.adicionarParametro(new ParametroSimples( FiltroEquipe.ID,Integer.parseInt(helper.getIdEquipePrincipal())));
		Collection<?> colecaoEquipe = 	Fachada.getInstancia().pesquisar(filtro, Equipe.class.getName());	
		Equipe equipe = 
				(Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);
		
		helper.setIdUnidade(equipe.getUnidadeOrganizacional().getId().toString());
		
		
		//Quebrar a OS para análise individual
		String[] osQuebradas = helper.getIdsOs().split(",");
		
		
		//[FS0011] - Validar Bairro da Ordem de Serviço
		
		boolean validarBairro = true;
		
		for(String idOS : osQuebradas){
			
			OrdemServico os = new OrdemServico();
			os.setId(Integer.parseInt(idOS));
			validarBairro =  this.getFachada().validarBairroOrdemServico(dataRoteiro,equipe,os);
			if(!validarBairro)
				break;
		}
		
		// Recuperar Os Programacao para controle de transação
				OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompanhamentoServico = null;
				
				Collection<AcompanhamentoArquivosRoteiroHelper> colecaoAcompanhamentoArquivosRoteiro = 
						(Collection<AcompanhamentoArquivosRoteiroHelper>) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
				
				for (Iterator iterator = colecaoAcompanhamentoArquivosRoteiro.iterator(); iterator.hasNext();) {
					AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = (AcompanhamentoArquivosRoteiroHelper) iterator.next();
					
					for (Iterator iOsProgramacao = acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper().iterator(); iOsProgramacao.hasNext();) {
						OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServicoHelper) iOsProgramacao.next();
						
						if (osProgramacaoAcompServico.getIdOrdemServico().equals(acompanharActionForm.getIdsOs())){
							
							osProgramacaoAcompanhamentoServico = osProgramacaoAcompServico;
							break;
						}
					}
					
					if (osProgramacaoAcompanhamentoServico != null){
						break;
					}
				}
				/////////// 
		
		if ((confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) && !validarBairro) {

			httpServletRequest.setAttribute(
					"caminhoActionConclusao",
					"/gsan/acompanhamentoArquivosRoteiroRemanejarOrdemServicoAction.do");

				return montarPaginaConfirmacao(
					"atencao.validar_bairro_os_rem",
					httpServletRequest, 
					actionMapping
				);

		}
		else{
			Fachada.getInstancia().remanejaOrdemServico(helper,usuario, osProgramacaoAcompanhamentoServico);
		}
		
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	public AcompanharRoteiroProgramacaoOrdemServicoHelper CarregarAcompanharRoteiroProgramacaoHelper(
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm){
		
		AcompanharRoteiroProgramacaoOrdemServicoHelper helper = new AcompanharRoteiroProgramacaoOrdemServicoHelper();
		
		helper.setChaveArquivo(acompanharActionForm.getChaveArquivo());
		helper.setIdAcompanhamentoArquivosRoteiro(acompanharActionForm.getIdAcompanhamentoArquivosRoteiro());
		helper.setIdEquipeAtual(acompanharActionForm.getIdEquipeAtual());
		helper.setIdEquipePrincipal(acompanharActionForm.getIdEquipePrincipal());
		
		helper.setIdsOs(acompanharActionForm.getIdsOs());
		
		
		return helper;
	}
	
	
}