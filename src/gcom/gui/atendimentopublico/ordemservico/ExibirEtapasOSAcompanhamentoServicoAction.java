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

import gcom.atendimentopublico.ordemservico.EtapaOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroEtapaOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.ExibirEtapasOSAcompanhamentoServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] Acompanhar Arquivos de Roteiro
 * [RM6655]
 * @author Fernanda Almeida
 *
 * @date 11/05/2012
 */
public class ExibirEtapasOSAcompanhamentoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirEtapasOSAcompanhamentoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		String limparSessao = httpServletRequest.getParameter("limparSessao");

		String idOs = httpServletRequest.getParameter("chave");
		
		FiltroOrdemServico filtroOs = new FiltroOrdemServico();
        filtroOs.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, idOs));
        Collection<?> colOrdemServico = fachada.pesquisar(filtroOs,OrdemServico.class.getName());
        OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colOrdemServico);
                
        Collection<Object[]> colecaoDadosOS = fachada.pesquisarOrdemServicoEtapaRa(ordemServico.getId(), 
                ordemServico.getRegistroAtendimento().getId(),(short)ConstantesSistema.SIM);
        
        Collection<ExibirEtapasOSAcompanhamentoServicoHelper> colecaoHelper = new ArrayList();
        
        if(colecaoDadosOS != null && !colecaoDadosOS.isEmpty()){
		
			for(Object[] dadosOS:colecaoDadosOS ){
				ExibirEtapasOSAcompanhamentoServicoHelper helper = new ExibirEtapasOSAcompanhamentoServicoHelper();
				String nomeEquipe = fachada.obterEquipeOsProgramacaoAcompServico(Integer.valueOf(""+dadosOS[0]));
				
				FiltroOrdemServicoSituacao filtroOsSituacao = new FiltroOrdemServicoSituacao();
				filtroOsSituacao.adicionarParametro(new ParametroSimples(FiltroOrdemServicoSituacao.ID,""+dadosOS[1]));
				Collection<?> colOsSituacao = fachada.pesquisar(filtroOsSituacao,OrdemServicoSituacao.class.getName());
				OrdemServicoSituacao ordemServicoSituacao = (OrdemServicoSituacao) Util.retonarObjetoDeColecao(colOsSituacao);
				

				
				helper.setNmEquipe(nomeEquipe);
				helper.setDsSituacao(ordemServicoSituacao.getDescricaoSituacao());
				helper.setNmEtapa(""+dadosOS[2]);
				
				colecaoHelper.add(helper);
				
				
			}
        }

		sessao.setAttribute("colecaoEtapasOSAcompanhamentoServico", colecaoHelper);
		
		return retorno;
	}
	
	private void atualizarInformaSituacaoOrdemServico(HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = (Date)sessao.getAttribute("dataRoteiroInformarSituacao");
		String idOs = (String) sessao.getAttribute("chaveOsInformarSituacao");
		String chaveArquivo = (String) sessao.getAttribute("chaveArquivoInformarSituacao");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(Util.converterStringParaInteger(idOs));
		
		Integer motivoNaoEncerramentoOs = null;
		
		fachada.atualizarOrdemProgramacaoAcompServicoInformarSituacao(Util.converterStringParaInteger(chaveArquivo),dataRoteiro,
				ordemServico.getId(), ordemServico.getSituacao(), motivoNaoEncerramentoOs);
		
		boolean naoInformaIndicadorAtivo = false;
		
		fachada.atualizarOrdemServicoProgramacaoSituacaoOs(ordemServico.getId(),
				dataRoteiro,ordemServico.getSituacao(),motivoNaoEncerramentoOs, naoInformaIndicadorAtivo);
		
		sessao.removeAttribute("dataRoteiroInformarSituacao");
		sessao.removeAttribute("chaveOsInformarSituacao");
		sessao.removeAttribute("chaveArquivoInformarSituacao");
	}
}