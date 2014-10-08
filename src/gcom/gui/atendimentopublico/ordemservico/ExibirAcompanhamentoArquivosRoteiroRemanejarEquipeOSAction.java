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
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.PermissaoEspecial;
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

public class ExibirAcompanhamentoArquivosRoteiroRemanejarEquipeOSAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirRemanejarEquipeOSAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
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
			
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.remanejar.situacao_finalizado");
			}
		}

        String idsAsSelecteds = httpServletRequest.getParameter("chaves");

        String[] idsAs = idsAsSelecteds.split(",");
		
		ArrayList<Integer> oSsFormatas = new ArrayList<Integer>();
		
		Collection<String> oSsEncerradas = new ArrayList<String>();
		StringBuilder ids = new StringBuilder();

		String descricaoOs = null;
		
		Collection<OrdemServico> collectionOs = new ArrayList<OrdemServico>();
		
		for(int i = 0; i < idsAs.length; i++){
			
			// Pega apenas o id da OS, pois o id do form vem com o id do arquivo
			String[] separaOs = idsAs[i].split("___");
			if(separaOs != null){
				oSsFormatas.add(new Integer(separaOs[0])); 
			}
			
			OrdemServico ordemServico = fachada.recuperaOSPorId(oSsFormatas.get(i));
			collectionOs.add(ordemServico);
			if(i == 0){
				descricaoOs = ordemServico.getServicoTipo().getDescricao();
			}
			
			//[FS0007 - Verificar sele��o de ordem de servi�o encerrada]
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
			throw new ActionServletException("atencao.situacao.nao_pendente_os_remanejar",null,ids.toString());			
		}
		
		
		for(OrdemServico ordemServico : collectionOs ){				
			OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico = 
				fachada.pesquisarOSProgramacaoAcompServicoPorIdArqTxt(ordemServico.getId(),arquivoTextoAcompanhamentoServico.getId());
			
			if(!fachada.verificarSituacaoPendenteDevolvidaOsAcompServico(osProgramacaoAcompanhamentoServico)){
				throw new ActionServletException("atencao.situacao.diferente_pendente",  ordemServico.getId()+"","remanejar a");
				
			}else
				
			if(osProgramacaoAcompanhamentoServico == null){
				throw new ActionServletException("atencao.varias_equipes");				
			}
		}
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());		
		
		int count = 0;
		Equipe equipeAtual = null;
		
		if(collectionOs != null && !collectionOs.isEmpty()){
			Iterator<OrdemServico> it = collectionOs.iterator();
			
			while(it.hasNext()){
				
				OrdemServico os = it.next();
				
				Collection<Equipe> colecao = null;
				// S� precisa verifica na primeira OS(s�o todas a mesma equipe)
				if(count == 0){
					colecao = 
						fachada.recuperaEquipeDaOSProgramacaoPorDataRoteiro(os.getId(),dataRoteiro);
				
					equipeAtual = (Equipe) Util.retonarObjetoDeColecao(colecao);
				}
				
				//[FS0006 - Verificar ordem de servi�o programada para mais de uma equipe]
				if(colecao != null && colecao.size() > 1){
					throw new ActionServletException("atencao.ordem_servico_programada_varias_equipes",null, os.getId()+"");		
				}
				
				count++;
			}
		}
		
		if(equipeAtual != null){
			Collection<Equipe> colecaoEquipe = this.pesquisarEquipes(idUnidadeLotacao,dataRoteiro, equipeAtual.getId(),httpServletRequest);	
			
			if(Util.isVazioOrNulo(colecaoEquipe)){
				throw new ActionServletException("atencao.equipes_nao_remanejadas");
			}
			
		
			acompanharActionForm.setEquipes(colecaoEquipe);		
			acompanharActionForm.setIdEquipeAtual(equipeAtual.getId()+"");
			// Retira os colchetes do arraylist
			String idsFormatados = oSsFormatas.toString().substring(1, oSsFormatas.toString().length() -1);
			
			if(oSsFormatas.size() > 1){
				acompanharActionForm.setDescricaoOrdemServico("V�rios");
			}else if(descricaoOs != null ){
				acompanharActionForm.setIdOrdemServico(idsFormatados);
				acompanharActionForm.setDescricaoOrdemServico(descricaoOs);
			}
			acompanharActionForm.setIdsOs(idsFormatados);
			acompanharActionForm.setNomeEquipeAtual(equipeAtual.getNome());
			acompanharActionForm.setIdAcompanhamentoArquivosRoteiro(chaveArquivo);
			acompanharActionForm.setUnidadeLotacao(idUnidadeLotacao+"");
			
		}else{

			throw new ActionServletException("atencao.equipe_inexistente_programacao");
		}
		return retorno;
	}
	
	private Collection<Equipe> pesquisarEquipes(Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe,HttpServletRequest httpServletRequest){

		Collection<Equipe> retorno = new ArrayList<Equipe>();
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Se n�o possui permissao para tramitar RA para qualquer unidade
		if ( !Fachada.getInstancia().verificarPermissaoEspecial(PermissaoEspecial.TRAMITAR_RA_PARA_QUALQUER_UNIDADE, usuarioLogado) ){
			
			retorno = Fachada.getInstancia().pesquisarEquipesOSNaoEnviadasProgramadas(idUnidadeLotacao, dataRoteiro, idEquipe);
			
		}else{
			retorno = Fachada.getInstancia().pesquisarEquipesOSNaoEnviadasProgramadas(0, dataRoteiro, idEquipe);
		}
		
		return retorno;

	}
}