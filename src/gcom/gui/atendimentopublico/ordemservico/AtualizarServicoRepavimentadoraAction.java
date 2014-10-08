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

import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1474] - Manter Servi�o da Repavimentadora 
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class AtualizarServicoRepavimentadoraAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarServicoRepavimentadoraActionForm atualizarServicoRepavimentadoraActionForm = (AtualizarServicoRepavimentadoraActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
				
		if(validarCampos(atualizarServicoRepavimentadoraActionForm)){
			ServicoRepavimentadora servicoRepavimentadora = new ServicoRepavimentadora();
			servicoRepavimentadora.setId(Integer.valueOf(atualizarServicoRepavimentadoraActionForm.getId()));
			
			servicoRepavimentadora.setId(new Integer(atualizarServicoRepavimentadoraActionForm.getId()));
			servicoRepavimentadora.setDescricao(atualizarServicoRepavimentadoraActionForm.getDescricao());
			servicoRepavimentadora.setDescricaoAbreviada(atualizarServicoRepavimentadoraActionForm.getDescricaoAbreviada());
			
			MaterialUnidade materialUnidade = new MaterialUnidade();
			materialUnidade.setId(Integer.valueOf(atualizarServicoRepavimentadoraActionForm.getUnidadeMaterial()));
			servicoRepavimentadora.setMaterialUnidade(materialUnidade);
			
			UnidadeOrganizacional unidadeRepavimentadora = new UnidadeOrganizacional();
			unidadeRepavimentadora.setId(Integer.valueOf(atualizarServicoRepavimentadoraActionForm.getUnidadeRepavimentadora()));
			servicoRepavimentadora.setUnidadeRepavimentadora(unidadeRepavimentadora);
			
			servicoRepavimentadora.setIndicadorUso(new Short(atualizarServicoRepavimentadoraActionForm.getIndicadorUso()));
			servicoRepavimentadora.setUltimaAlteracao(new Date());
			
			String valorServico = atualizarServicoRepavimentadoraActionForm.getValorServico(); 
			valorServico = valorServico.replaceAll(",", ".");
			servicoRepavimentadora.setValorServico(Util.formatarMoedaRealparaBigDecimal(valorServico));
			
			//atualiza na base de dados Material
			fachada.atualizar(servicoRepavimentadora);
		}else{
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}
		
		//[FS0004]Verificar Sucesso da Atualiza��o
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Servi�o da Repavimentadora "+ atualizarServicoRepavimentadoraActionForm.getDescricao() +
				" atualizado com sucesso.", "Realizar outra Manuten��o do Servi�o da Repavimentadora",
				"exibirFiltrarServicoRepavimentadoraAction.do?menu=sim");
		
		return retorno;
	}
	
	//[FS0003] Verificar Preenchimento dos campos
	private boolean validarCampos(AtualizarServicoRepavimentadoraActionForm form){
		boolean valido = false;
		
		if(Util.verificarIdNaoVazio(form.getId()) && Util.verificarNaoVazio(form.getDescricao()) && 
				Util.verificarNaoVazio(form.getIndicadorUso()) && Util.verificarIdNaoVazio(form.getUnidadeMaterial()) && 
				Util.verificarIdNaoVazio(form.getUnidadeRepavimentadora()) && Util.verificarNaoVazio(form.getValorServico())){
			valido = true;
		}
		return valido;
	}	
}