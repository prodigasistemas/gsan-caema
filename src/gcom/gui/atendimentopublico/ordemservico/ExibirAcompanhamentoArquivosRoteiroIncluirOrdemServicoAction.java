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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
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
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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

public class ExibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction extends GcomAction {
	
	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirIncluirOrdemServicoAcompanhamentoArqRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<?> colecaoAcompanhamentoArquivosRoteiro = 
				(Collection<?>) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		

		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String chave = httpServletRequest.getParameter("chave");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;

		
		if (chave != null){
			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chave));
			
			
			Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
					filtroArquivoTextoAcompanhamentoServico,
				    ArquivoTextoAcompanhamentoServico.class.getName());
			
			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
				    .iterator().next();
			
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.incluir_situacao_finalizado");
			}
			
		}
		
		int valor = 0;
		Integer idUnidadeLotacao = null;
				
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
				!httpServletRequest.getParameter("tipoConsulta").equals("") &&
				!httpServletRequest.getParameter("tipoConsulta").equals("ordemServico")) {
			
			FiltroEquipe filtro = new FiltroEquipe();
			filtro.adicionarParametro(new ParametroSimples( FiltroEquipe.ID,new Integer(arquivoTextoAcompanhamentoServico.getEquipe().getId())));
			Collection<?> colecaoEquipeOs = 	Fachada.getInstancia().pesquisar(filtro, Equipe.class.getName());	
			Equipe equipeOs = 
					(Equipe) Util.retonarObjetoDeColecao(colecaoEquipeOs);
			
			idUnidadeLotacao = equipeOs.getUnidadeOrganizacional().getId();	
			acompanharActionForm.setUnidadeLotacao(idUnidadeLotacao.toString());
			
			valor = this.retornaUltimoSequencial(arquivoTextoAcompanhamentoServico.getEquipe().getId().toString(),colecaoAcompanhamentoArquivosRoteiro)+1;
			
			acompanharActionForm.setSequencialProgramacao(""+valor);
			
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {
				acompanharActionForm.setNumeroRA(id);
				acompanharActionForm.setDescricaoRA(descricao);
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("ordemServico")) {
								
				acompanharActionForm.setIdOrdemServico(id);
				acompanharActionForm.setDescricaoOrdemServico(descricao);
				
				// [FS0008] - Verificar possibilidade da inclus�o da ordem de servi�o
				this.pesquisarOrdemServico(acompanharActionForm,idUnidadeLotacao, usuario);
				
			}

				
		} else {
			// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			if(objetoConsulta == null){

				objetoConsulta = (String) httpServletRequest.getAttribute("objetoConsulta");
				
				if(objetoConsulta != null){
					acompanharActionForm.setIdOrdemServico((String)httpServletRequest.getAttribute("idOrdemServico"));
					
					valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
					
					OrdemServico os = 
							Fachada.getInstancia().recuperaOSPorId(Util.converterStringParaInteger(acompanharActionForm.getIdOrdemServico().trim()));
					
					if(os!= null && os.getRegistroAtendimento() != null){
						acompanharActionForm.setNumeroRA(os.getRegistroAtendimento().getId().toString());
					}
					
					acompanharActionForm.setSequencialProgramacao(""+valor);
				}
			}

			Integer numeroRA = (Integer) httpServletRequest.getAttribute("numeroRa");
			String osDescricao = (String) httpServletRequest.getAttribute("descOrdemServico");
			
			//[UC0443] - Pesquisar Registro Atendimento
			if ((objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) || (numeroRA != null && !numeroRA.equals(""))) {
				
				if (numeroRA != null && !numeroRA.equals("")){
					acompanharActionForm.setNumeroRA(Integer.toString(numeroRA));
				}
				
				if (osDescricao != null && !osDescricao.equals("")){
					acompanharActionForm.setDescricaoOrdemServico(osDescricao);
				}
				
				this.pesquisarRegistroAtendimento(acompanharActionForm);				
			}
			
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
					objetoConsulta.trim().equals("2")) {
				

				this.pesquisarOrdemServico(acompanharActionForm,new Integer(acompanharActionForm.getUnidadeLotacao()), usuario);
				
			}else if(chave != null){
		
				FiltroEquipe filtroEquipe =  new FiltroEquipe();
				filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, arquivoTextoAcompanhamentoServico.getEquipe().getId()));		
				
				Collection<?> colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
				Equipe equipe = (Equipe) colecaoEquipe.iterator().next();
				
				//[UC0471]- Obter Dados da Equipe
				ObterDadosEquipe obterDadosEquipe = fachada.obterDadosEquipe(equipe.getId());
				
				acompanharActionForm.setIdEquipe(""+obterDadosEquipe.getEquipe().getId());
				acompanharActionForm.setNomeEquipe(obterDadosEquipe.getEquipe().getNome());
				acompanharActionForm.setPlacaVeiculo(obterDadosEquipe.getEquipe().getPlacaVeiculo());
				
				acompanharActionForm.setCargaTrabalhoDia(""+ (obterDadosEquipe.getEquipe().getCargaTrabalho()));
				acompanharActionForm.setIdUnidade(""+obterDadosEquipe.getEquipe().getUnidadeOrganizacional().getId());
				acompanharActionForm.setUnidadeLotacao(""+obterDadosEquipe.getEquipe().getUnidadeOrganizacional().getId());
				acompanharActionForm.setDescricaoUnidade(obterDadosEquipe.getEquipe().getUnidadeOrganizacional().getDescricao());
				acompanharActionForm.setIdTipoPerfilServico(""+obterDadosEquipe.getEquipe().getServicoPerfilTipo().getId());
				acompanharActionForm.setDescricaoTipoPerfilServico(obterDadosEquipe.getEquipe().getServicoPerfilTipo().getDescricao());
				acompanharActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
				acompanharActionForm.setIdOrdemServico(null);
				acompanharActionForm.setIdAcompanhamentoArquivosRoteiro(chave);
				
				valor = this.retornaUltimoSequencial(Integer.toString(obterDadosEquipe.getEquipe().getId()),colecaoAcompanhamentoArquivosRoteiro)+1;
				
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
	
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			fachada.obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		acompanharActionForm.setHabilitarGerar("ok");
		if (obter.getRegistroAtendimento()  != null) {
			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			if (registroAtendimento.getCodigoSituacao() == 2){
				acompanharActionForm.setHabilitarGerar("");

				throw new ActionServletException("atencao.situacao.ra.pendente");				
			}else{

				acompanharActionForm.setHabilitarGerar("ok");
				acompanharActionForm.setNumeroRA(registroAtendimento.getId().toString());
				acompanharActionForm.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			}
		} else {
			acompanharActionForm.setDescricaoRA("Registro Atendimento inexistente");
			acompanharActionForm.setNumeroRA("");
		}
	}
	
	private void pesquisarOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Integer idUnidadeLotacao, Usuario usuario) {
		
		OrdemServico os = 
			fachada.recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));

		if (os != null) {

			// [FS0015] - Verificar registro de atendimento e ordem de servi�o
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
			acompanharActionForm.setHabilitarGerar("ok");
			
			Date dataRoteiro = null;
			
			if (acompanharActionForm.getDataProgramacao() != null && !acompanharActionForm.getDataProgramacao().equals("")){
				
				dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
			}
					
			// [FS0008] - Verificar possibilidade da inclus�o da ordem de servi�o
			if(idUnidadeLotacao != null){
				fachada.validarInclusaoOsNaProgramacao(os,idUnidadeLotacao, dataRoteiro, usuario);
			}
			
			//[FS0009]
			if(os.getRegistroAtendimento() != null){
				acompanharActionForm.setNumeroRA(os.getRegistroAtendimento().getId().toString());
			}
			
			acompanharActionForm.setIdOrdemServico(os.getId().toString());
			acompanharActionForm.setDescricaoOrdemServico(os.getServicoTipo().getDescricao());

		} else {
			acompanharActionForm.setDescricaoOrdemServico("Ordem de Servi�o inexistente");
			acompanharActionForm.setIdOrdemServico("");
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm){
		
		// Registro Atendimento
		if(acompanharActionForm.getNumeroRA() != null && 
			!acompanharActionForm.getNumeroRA().equals("") && 
			acompanharActionForm.getDescricaoRA() != null && 
			!acompanharActionForm.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}

		// Ordem de Servi�o
		if(acompanharActionForm.getIdOrdemServico() != null && 
			!acompanharActionForm.getIdOrdemServico().equals("") && 
			acompanharActionForm.getDescricaoOrdemServico() != null && 
			!acompanharActionForm.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("idOsEncontrada","true");
		}
	}
	
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