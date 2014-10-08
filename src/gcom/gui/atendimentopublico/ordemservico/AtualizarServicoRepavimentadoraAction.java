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
 * [UC 1474] - Manter Serviço da Repavimentadora 
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
		
		//[FS0004]Verificar Sucesso da Atualização
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Serviço da Repavimentadora "+ atualizarServicoRepavimentadoraActionForm.getDescricao() +
				" atualizado com sucesso.", "Realizar outra Manutenção do Serviço da Repavimentadora",
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