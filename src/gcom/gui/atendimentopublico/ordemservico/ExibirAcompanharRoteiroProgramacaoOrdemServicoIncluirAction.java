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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("incluirOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;

		FiltroEquipe filtro = new FiltroEquipe();
		filtro.adicionarParametro(new ParametroSimples( FiltroEquipe.ID,Integer.parseInt(acompanharActionForm.getIdEquipe())));
		Collection<?> colecaoEquipe = 	Fachada.getInstancia().pesquisar(filtro, Equipe.class.getName());	
		Equipe equipeOs = 
				(Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);
		
		Integer idUnidadeLotacao = equipeOs.getUnidadeOrganizacional().getId();
		acompanharActionForm.setUnidadeLotacao(idUnidadeLotacao.toString());
				
//		HashMap mapOsProgramacaoHelper = 
//			(HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		Collection<?> colecaoAcompanhamentoArquivosRoteiro = 
				(Collection<?>) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
				!httpServletRequest.getParameter("tipoConsulta").equals("")) {
					
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {

				acompanharActionForm.setNumeroRA(id);
				acompanharActionForm.setDescricaoRA(descricao);
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("ordemServico")) {

				acompanharActionForm.setIdOrdemServico(id);
				acompanharActionForm.setDescricaoOrdemServico(descricao);
				
				
				// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
				this.pesquisarOrdemServico(acompanharActionForm,idUnidadeLotacao, usuario);
				
			}

//			String chave = acompanharActionForm.getNomeEquipe();
//			Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
			int valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
			
			acompanharActionForm.setSequencialProgramacao(""+valor);
			
				
		} else {

			// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			if(objetoConsulta == null){

				objetoConsulta = (String) httpServletRequest.getAttribute("objetoConsulta");
				
				if(objetoConsulta != null){
					acompanharActionForm.setIdOrdemServico((String)httpServletRequest.getAttribute("idOrdemServico"));
					
					OrdemServico os = 
							Fachada.getInstancia().recuperaOSPorId(Util.converterStringParaInteger(acompanharActionForm.getIdOrdemServico().trim()));
					
					if(os!= null && os.getRegistroAtendimento() != null){
						acompanharActionForm.setNumeroRA(os.getRegistroAtendimento().getId().toString());
					}
					
//					String chave = acompanharActionForm.getNomeEquipe();
//					Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
					int valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
					
					acompanharActionForm.setSequencialProgramacao(""+valor);
				}
			}
			
			String chave = httpServletRequest.getParameter("chave");
			
			//[UC0443] - Pesquisar Registro Atendimento
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {

				// Faz a consulta de Registro Atendimento
				this.pesquisarRegistroAtendimento(acompanharActionForm);

			//[UC0450] - Pesquisar Ordem de Servico
			}else if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
					objetoConsulta.trim().equals("2")) {
				
								
				this.pesquisarOrdemServico(acompanharActionForm,idUnidadeLotacao, usuario);
				
			}else if(chave != null){
				
				HashMap mapEquipe = 
					(HashMap) sessao.getAttribute("mapEquipe");
				
				Equipe equipe = (Equipe) mapEquipe.get(chave);
				
				ObterDadosEquipe obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(equipe.getId());
				
				acompanharActionForm.setIdEquipe(""+equipe.getId());
				acompanharActionForm.setNomeEquipe(equipe.getNome());
				acompanharActionForm.setPlacaVeiculo(equipe.getPlacaVeiculo());
				
				//Para exibir a carga horária em horas e não em minutos - Raphael Rossiter em 13/02/2007
				acompanharActionForm.setCargaTrabalhoDia(""+ (equipe.getCargaTrabalho() / 60));
				
				acompanharActionForm.setIdUnidade(""+equipe.getUnidadeOrganizacional().getId());
				acompanharActionForm.setDescricaoUnidade(equipe.getUnidadeOrganizacional().getDescricao());
				acompanharActionForm.setIdTipoPerfilServico(""+equipe.getServicoPerfilTipo().getId());
				acompanharActionForm.setDescricaoTipoPerfilServico(equipe.getServicoPerfilTipo().getDescricao());
				acompanharActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
				acompanharActionForm.setIdOrdemServico(null);
				
//				Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
				int valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
				
				acompanharActionForm.setSequencialProgramacao(""+valor);
				acompanharActionForm.setSequencialProgramacaoPrevisto(""+valor);
				
			}
		}
		if(httpServletRequest.getParameter("limpar") != null && 
				!httpServletRequest.getParameter("limpar").equals("")) {
			
			acompanharActionForm.setIdOrdemServico("");
			acompanharActionForm.setDescricaoOrdemServico("");
			acompanharActionForm.setNumeroRA("");
			acompanharActionForm.setHabilitarGerar("");
			acompanharActionForm.setDescricaoRA("");
			acompanharActionForm.setSequencialProgramacaoPrevisto("");			
		}
		this.setaRequest(httpServletRequest,acompanharActionForm);
		
		return retorno;
	}
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		acompanharActionForm.setHabilitarGerar("ok");
		if (obter.getRegistroAtendimento()  != null) {

			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			if (registroAtendimento.getCodigoSituacao() == 2){

				acompanharActionForm.setHabilitarGerar("");
				throw new ActionServletException("atencao.situacao.ra.pendente");				
			}else{
			
				acompanharActionForm.setNumeroRA(registroAtendimento.getId().toString());
				acompanharActionForm.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			
			}
		} else {

			acompanharActionForm.setHabilitarGerar("");
			acompanharActionForm.setDescricaoRA("Registro Atendimento inexistente");
			acompanharActionForm.setNumeroRA("");

		}
	}
	
	/**
	 * Pesquisa Ordem de Serviço 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Integer idUnidadeLotacao, Usuario usuario) {
		
		OrdemServico os = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));


		if (os != null) {

			// [FS0015] - Verificar registro de atendimento e ordem de serviço
			String numeroRa = acompanharActionForm.getNumeroRA();
			if(numeroRa != null && !numeroRa.equals("")){
				
				if(os.getRegistroAtendimento() != null && (os.getRegistroAtendimento().getId().intValue() != new Integer(numeroRa).intValue())){
					String[] parametros = new String[2];
					parametros[0] = os.getId().toString();
					parametros[1] = numeroRa;

					acompanharActionForm.setHabilitarGerar("");
					throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
				}
			}else{
				numeroRa = "inexistente";
			}
			
			if(os.getRegistroAtendimento() == null){
				String[] parametros = new String[2];
				parametros[0] = os.getId().toString();
				parametros[1] = numeroRa;
				acompanharActionForm.setHabilitarGerar("");
				throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
			}
			
			Date dataRoteiro = null;
			
			if (acompanharActionForm.getDataProgramacao() != null && !acompanharActionForm.getDataProgramacao().equals("")){
				
				dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
			}
			// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
			if(idUnidadeLotacao != null){
				Fachada.getInstancia().validarInclusaoOsNaProgramacao(os,idUnidadeLotacao,dataRoteiro, usuario);
			}
				
			acompanharActionForm.setIdOrdemServico(os.getId().toString());
			acompanharActionForm.setDescricaoOrdemServico(os.getServicoTipo().getDescricao());
			
			//[FS0009]
			if(os.getRegistroAtendimento() != null){
				acompanharActionForm.setNumeroRA(os.getRegistroAtendimento().getId().toString());
			}
			

			acompanharActionForm.setHabilitarGerar("ok");
		} else {

			acompanharActionForm.setHabilitarGerar("");
			acompanharActionForm.setDescricaoOrdemServico("Ordem de Serviço inexistente");
			acompanharActionForm.setIdOrdemServico("");

		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm){
		
		// Registro Atendimento
		if(acompanharActionForm.getNumeroRA() != null && 
			!acompanharActionForm.getNumeroRA().equals("") && 
			acompanharActionForm.getDescricaoRA() != null && 
			!acompanharActionForm.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}

		// Ordem de Serviço
		if(acompanharActionForm.getIdOrdemServico() != null && 
			!acompanharActionForm.getIdOrdemServico().equals("") && 
			acompanharActionForm.getDescricaoOrdemServico() != null && 
			!acompanharActionForm.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("idOsEncontrada","true");
		}
		
	}
	
	/**
	 * Retorna o ultimo sequencial das os´s programadas
	 */
	private int retornaUltimoSequencial(String idEquipe, Collection<?> colecaoAcompanhamentoArquivosRoteiro){
		short valorSequencial = 0;
		if (colecaoAcompanhamentoArquivosRoteiro != null && !colecaoAcompanhamentoArquivosRoteiro.isEmpty()){	
			Iterator<?> itDadosAcompanhamentoArquivosRoteiroHelper = colecaoAcompanhamentoArquivosRoteiro.iterator();
			while (itDadosAcompanhamentoArquivosRoteiroHelper.hasNext()){
				AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = (AcompanhamentoArquivosRoteiroHelper) itDadosAcompanhamentoArquivosRoteiroHelper.next();
				if (acompanhamentoArquivosRoteiroHelper.getIdEquipe().equals(idEquipe)){
					Collection<?> osProgramacaoAcompServicoHelper = acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper();
					
					Iterator<?> iter = osProgramacaoAcompServicoHelper.iterator(); 
							
					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServicoHelper helper = (OSProgramacaoAcompanhamentoServicoHelper) iter.next();
						
						if(valorSequencial < Short.parseShort(helper.getNnSequencialProgramacao())){
							valorSequencial = Short.parseShort(helper.getNnSequencialProgramacao());
						}
					}
				}
			}
		}
		return valorSequencial;
	}
	
}