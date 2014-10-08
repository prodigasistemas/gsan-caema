/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.operacional;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSubsistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirSetorAbastecimentoAction extends GcomAction {


	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirSetorAbastecimentoActionForm form = (InserirSetorAbastecimentoActionForm)actionForm;
		Fachada fachada = this.getFachada();
		
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String sistemaAbastecimentoPrincipal = form.getSistemaAgua1();
		String sistemaAbastecimentoSecundario = form.getSistemaAgua2();
		
		String subsistemaPrincipal = form.getSubsistemaPrincipal();
		String[] subsistemaSecundarios = form.getSubsistemasSecundarios();
		
		//Validar campos obrigatórios
		if(!Util.verificarNaoVazio(descricao)){
			throw new ActionServletException(
            		"atencao.required",null,"Descrição");
		}
		
		if(!Util.verificarIdNaoVazio(subsistemaPrincipal)){
			throw new ActionServletException(
            		"atencao.required",null,"Subsistema principal");
		}
		
		//Verificar se o subsistema principal foi incluido também como secundário
		if(subsistemaSecundarios != null && subsistemaSecundarios.length > 0){
			for(String id : subsistemaSecundarios){
				if(id.equals(subsistemaPrincipal)){
					
					FiltroSubsistemaAbastecimento filtro = new FiltroSubsistemaAbastecimento();
					filtro.adicionarParametro(new ParametroSimples("id",id ));
					SubsistemaAbastecimento subsistema = 
							(SubsistemaAbastecimento)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, SubsistemaAbastecimento.class.getName()));
					
					throw new ActionServletException(
		            		"atencao.subsistema_abastecimento.sistema_ja_informado",null,subsistema.getDescricao().toUpperCase());
				}
			}
		}
		
		SetorAbastecimento setorAbastecimento = new SetorAbastecimento();
		setorAbastecimento.setDescricao(descricao.toUpperCase());
		setorAbastecimento.setDescricaoAbreviada(descricaoAbreviada.toUpperCase());
		
		SistemaAbastecimento sistemaAbsPrincipal = new SistemaAbastecimento();
		sistemaAbsPrincipal.setId(Integer.parseInt(sistemaAbastecimentoPrincipal));
		
		SistemaAbastecimento sistemaAbsSecundario = null;
		if(Util.verificarIdNaoVazio(sistemaAbastecimentoSecundario)){
			sistemaAbsSecundario = new SistemaAbastecimento();
			sistemaAbsSecundario.setId(Integer.parseInt(sistemaAbastecimentoSecundario));
		}
		
		setorAbastecimento.setSistemaAbastecimento(sistemaAbsPrincipal);
		setorAbastecimento.setSistemaAbastecimentoSecundario(sistemaAbsSecundario);
		setorAbastecimento.setIndicadorUso(ConstantesSistema.SIM);
		setorAbastecimento.setUltimaAlteracao(new Date());
		
		//Gravando no banco
		Integer idSetorAbastecimento = (Integer)fachada.inserir(setorAbastecimento);
		
		//Gravando subsistema principal
		SetorSubsistemaAbastecimento setorSub = new SetorSubsistemaAbastecimento();
		setorSub.setIndicadorPrincipal(ConstantesSistema.SIM);
		setorSub.setUltimaAlteracao(new Date());
		
		
		SetorAbastecimento setor = new SetorAbastecimento();
		setor.setId(idSetorAbastecimento);
		
		SubsistemaAbastecimento subsistema = new SubsistemaAbastecimento();
		subsistema.setId(Integer.parseInt(subsistemaPrincipal));
		
		setorSub.setSetorAbastecimento(setor);
		setorSub.setSubsistemaAbastecimento(subsistema);
		
		fachada.inserir(setorSub);
		
		//Gravando subsistemas secundários
		if(subsistemaSecundarios != null && subsistemaSecundarios.length > 0){
			for(String id : subsistemaSecundarios){
				if(id != null && !id.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
					
					setorSub = new SetorSubsistemaAbastecimento();
					setorSub.setIndicadorPrincipal(ConstantesSistema.NAO);
					setorSub.setUltimaAlteracao(new Date());
					
					subsistema = new SubsistemaAbastecimento();
					subsistema.setId(Integer.parseInt(id));
					
					setorSub.setSetorAbastecimento(setor);
					setorSub.setSubsistemaAbastecimento(subsistema);
					
					fachada.inserir(setorSub);
				}
			}
		}

		montarPaginaSucesso(httpServletRequest,
				"Setor de abastecimento " + setorAbastecimento.getDescricao() + " inserido com sucesso.",
				"Inserir outro setor de abastecimento",
				"exibirInserirSetorAbastecimentoAction.do?menu=sim",
				"exibirAtualizarSetorAbastecimentoAction.do?idSetorAbastecimento="+idSetorAbastecimento,
				"Atualizar setor de abastecimento inserido");

		return retorno;
		
		}

	}		
