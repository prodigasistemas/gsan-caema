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
package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarSubsistemaAbastecimentoAction extends GcomAction  {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirManterSubsistemaAbastecimento");

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSubsistemaAbastecimentoActionForm filtrarSubsistemaAbastecimentoActionForm = (FiltrarSubsistemaAbastecimentoActionForm) actionForm;

		FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = null;
		
		
		String codigo = filtrarSubsistemaAbastecimentoActionForm.getCodigo();
		String descricao = filtrarSubsistemaAbastecimentoActionForm.getDescricao();
		String tipoPesquisa = filtrarSubsistemaAbastecimentoActionForm.getTipoPesquisa();
		String descricaoAbreviada = filtrarSubsistemaAbastecimentoActionForm.getDescricaoAbreviada();
		String idSistemaAbastecimento = filtrarSubsistemaAbastecimentoActionForm.getIdSistemaAbastecimento();
		String indicadorUso = filtrarSubsistemaAbastecimentoActionForm.getIndicadorUso();

		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		boolean peloMenosUmParametroInformado = false;
		
		if (codigo != null && !codigo.equals("")){
			
			peloMenosUmParametroInformado = true;
			
			if (filtroSubsistemaSistemaAbastecimento == null){
				filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			}
			
			filtroSubsistemaSistemaAbastecimento.adicionarParametro(
				new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, Integer.valueOf(codigo)));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (filtroSubsistemaSistemaAbastecimento == null){
				filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			}
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroSubsistemaSistemaAbastecimento
					.adicionarParametro(new ComparacaoTextoCompleto(FiltroSubsistemaSistemaAbastecimento.DESCRICAO_SUBSISTEMA_ABASTECIMENTO, descricao));
			} 
			else {
				filtroSubsistemaSistemaAbastecimento
					.adicionarParametro(new ComparacaoTexto(FiltroSubsistemaSistemaAbastecimento.DESCRICAO_SUBSISTEMA_ABASTECIMENTO, descricao));
			}
		}
		
		if (descricaoAbreviada != null && !descricaoAbreviada.equals("")){
			
			peloMenosUmParametroInformado = true;
			
			if (filtroSubsistemaSistemaAbastecimento == null){
				filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			}
			
			filtroSubsistemaSistemaAbastecimento.adicionarParametro(
				new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.DESCRICAO_ABREVIADA_SUBSISTEMA_ABASTECIMENTO, descricaoAbreviada));
		}
		
		if (idSistemaAbastecimento != null && 
			!idSistemaAbastecimento.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			if (filtroSubsistemaSistemaAbastecimento == null){
				filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			}
			
			filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(
				FiltroSubsistemaSistemaAbastecimento.ID_SISTEMA_ABASTECIMENTO, Integer.valueOf(idSistemaAbastecimento)));
		}
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("") && !indicadorUso.equalsIgnoreCase("3")) {
			
			peloMenosUmParametroInformado = true;
			
			if (filtroSubsistemaSistemaAbastecimento == null){
				filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			}
			
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				
				filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSubsistemaSistemaAbastecimento.INDICADOR_USO_SUBSISTEMA_ABASTECIMENTO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} 
			else {
				
				filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSubsistemaSistemaAbastecimento.INDICADOR_USO_SUBSISTEMA_ABASTECIMENTO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		if (filtroSubsistemaSistemaAbastecimento != null){
			
			filtroSubsistemaSistemaAbastecimento.setCampoOrderBy(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO);
			
			// Objetos que ser�o retornados pelo hibernate
			filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
			filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
			
			if (idSistemaAbastecimento == null ||
				idSistemaAbastecimento.trim().equals("") ||
				idSistemaAbastecimento.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSubsistemaSistemaAbastecimento.IC_PRINCIPAL, ConstantesSistema.SIM));
			}
		}
		

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroSubsistemaSistemaAbastecimento", filtroSubsistemaSistemaAbastecimento);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		return retorno;
	}
}
