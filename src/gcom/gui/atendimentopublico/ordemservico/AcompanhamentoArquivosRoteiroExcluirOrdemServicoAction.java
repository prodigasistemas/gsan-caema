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

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * [UC1199] – Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
 * 
 * @author Sávio Luiz
 *
 * @date 27/04/2012
 */
public class AcompanhamentoArquivosRoteiroExcluirOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("acompanhamentoArqRoterio");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
		acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String[] idsAs = acompanharActionForm.getIdOsProgramacaoAcompanhamentoServico();
		
		ArrayList<Integer> oSsFormatas = new ArrayList<Integer>();
		
		Collection<String> oSsEncerradas = new ArrayList<String>();
		StringBuilder ids = new StringBuilder();
		
		Collection<OrdemServico> collectionOs = new ArrayList<OrdemServico>();
		
		for(int i = 0; i < idsAs.length; i++){
			
			// Pega apenas o id da OS, pois o id do form vem com o id do arquivo
			String[] separaOs = idsAs[i].split("___");
			if(separaOs != null){
				oSsFormatas.add(new Integer(separaOs[0])); 
			}
			
			OrdemServico ordemServico = fachada.recuperaOSPorId(oSsFormatas.get(i));
			collectionOs.add(ordemServico);
			
			//[FS0007 - Verificar seleção de ordem de serviço encerrada]
			if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
				oSsEncerradas.add(ordemServico.getId().toString());	
				if( i == idsAs.length-1){
					ids.append(ordemServico.getId().toString());
				}else{
					ids.append(ordemServico.getId().toString()+",");
				}
			}
		}
		
		if(oSsEncerradas.size() != 0){
			throw new ActionServletException("atencao.situacao.nao_pendente_os",null,ids.toString());			
		}
		
		
		String chaveArquivo = httpServletRequest.getParameter("chaveArquivo");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;
		
		if (chaveArquivo != null){
			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chaveArquivo));
			
			
			Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
					filtroArquivoTextoAcompanhamentoServico,
				    ArquivoTextoAcompanhamentoServico.class.getName());
			
			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
				    .iterator().next();
			
			for(OrdemServico ordemServico : collectionOs ){
				OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico = 
						fachada.pesquisarOSProgramacaoAcompServicoPorIdArqTxt( ordemServico.getId(),arquivoTextoAcompanhamentoServico.getId() );
				
				if(!fachada.verificarSituacaoPendenteDevolvidaOsAcompServico(osProgramacaoAcompanhamentoServico)){
					throw new ActionServletException("atencao.situacao.diferente_pendente", ordemServico.getId()+"",  "excluir a");
				}else if(osProgramacaoAcompanhamentoServico == null){
					throw new ActionServletException("atencao.varias_equipes");				
				}
			}
			
		}
		
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		acompanharActionForm.setDataRoteiro(Util.formatarData(dataRoteiro));
		acompanharActionForm.setUnidadeLotacao(""+idUnidadeLotacao);
				
			
		for(OrdemServico os:collectionOs ){
			Collection<Equipe> colecao = fachada.recuperaEquipeDaOSProgramacaoPorDataRoteiro( os.getId(), dataRoteiro );
	
			// [FS0006 - Verificar ordem de serviço programada para mais de uma
			// equipe]
			if (colecao != null && colecao.size() > 1) {
				throw new ActionServletException("atencao.ordem_servico_programada_varias_equipes",null, os.getId()+"");
			}
			

			Equipe equipeAtual = (Equipe) Util.retonarObjetoDeColecao(colecao);

			this.removerOrdemServico(acompanharActionForm,usuario,equipeAtual,os,sessao);
		
		}
		
		return retorno;
	}
	
	private void removerOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Usuario usuario, Equipe equipeAtual, OrdemServico ordemServico, HttpSession sessao){

		Fachada fachada = Fachada.getInstancia();
		
		// Recuperar Os Programacao para controle de transação
		OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompanhamentoServico = null;
		
		Collection<AcompanhamentoArquivosRoteiroHelper> colecaoAcompanhamentoArquivosRoteiro = 
				(Collection<AcompanhamentoArquivosRoteiroHelper>) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
		
		for (Iterator iterator = colecaoAcompanhamentoArquivosRoteiro.iterator(); iterator.hasNext();) {
			AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = (AcompanhamentoArquivosRoteiroHelper) iterator.next();
			
			for (Iterator iOsProgramacao = acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper().iterator(); iOsProgramacao.hasNext();) {
				OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServicoHelper) iOsProgramacao.next();
				
				if (osProgramacaoAcompServico.getIdOrdemServico().equals(ordemServico.getId().toString())){
					
					osProgramacaoAcompanhamentoServico = osProgramacaoAcompServico;
					break;
				}
			}
			
			if (osProgramacaoAcompanhamentoServico != null){
				break;
			}
		}
		/////////// 
			
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Integer idEquipeAtual = equipeAtual.getId();
		Integer idOrdemServico = ordemServico.getId();

		fachada.alocaEquipeParaOs(idOrdemServico,dataRoteiro,idEquipeAtual, true, Util.converterStringParaInteger(acompanharActionForm.getIdAcompanhamentoArquivosRoteiro()), osProgramacaoAcompanhamentoServico );
		
		
		
	}
}