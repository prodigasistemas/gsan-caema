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

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.ordemservico.UsuarioEmpresaAcompEquipe;
import gcom.atendimentopublico.ordemservico.UsuarioNaturezaAcompEquipe;
import gcom.atendimentopublico.ordemservico.UsuarioUnidadeAcompEquipe;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1398] - Definir Equipes a Serem Acompanhadas
 * 
 * @author Amelia Pessoa
 *
 * @date 23/11/2012
 */
public class DefinirUsuarioAcompanhamentoEquipesAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form = 
			(ExibirDefinirUsuarioAcompanhamentoEquipesActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroUsuario filtro = new FiltroUsuario();
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().toLowerCase(), ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().toUpperCase()));
		
		Collection<Usuario> colecao = Fachada.getInstancia().pesquisar(filtro, Usuario.class.getName());
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecao);
		
		if(usuario == null){
			throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
		}
		
		//Exclui todas as unidades do usuario
		fachada.excluirUnidadesOrganizacionaisAssociadasUsuario(usuario.getId());
		
		//Exclui todas as empresas do usuario
		fachada.excluirEmpresasAssociadasUsuario(usuario.getId());
		
		//Exclui todas as naturezas de equipe do usuario
		fachada.excluirNaturezaEquipeAssociadasUsuario(usuario.getId());
				
		//Inseri as unidades marcadas para o usuario
		if ((form.getExcluirUnidade()==null || !form.getExcluirUnidade().equals("on")) && form.getUnidadesMarcadas()!=null){
	    	for (String unidade : form.getUnidadesMarcadas()){
	    		UsuarioUnidadeAcompEquipe usuarioUnidadeAcompEquipe = new UsuarioUnidadeAcompEquipe();
	    		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
	    		unidadeOrganizacional.setId(Integer.parseInt(unidade));
	    		usuarioUnidadeAcompEquipe.setUnidadeOrganizacional(unidadeOrganizacional);
	    		usuarioUnidadeAcompEquipe.setUsuario(usuario);
	    		usuarioUnidadeAcompEquipe.setUltimaAlteracao(new Date());
	    		
	    		fachada.inserir(usuarioUnidadeAcompEquipe);
	    	}
    	}
		
		//Inseri as empresas marcadas para o usuario
		if((form.getExcluirEmpresa() == null || !form.getExcluirEmpresa().equals("on")) && form.getEmpresasMarcadas() != null){
			for(String emp : form.getEmpresasMarcadas()){
				Empresa empresa = new Empresa();
				empresa.setId(Integer.parseInt(emp));
				UsuarioEmpresaAcompEquipe usuarioEmpresaAcompEquipe = new UsuarioEmpresaAcompEquipe();
				usuarioEmpresaAcompEquipe.setEmpresa(empresa);
				usuarioEmpresaAcompEquipe.setUsuario(usuario);
				usuarioEmpresaAcompEquipe.setUltimaAlteracao(new Date());
				
				fachada.inserir(usuarioEmpresaAcompEquipe);
			}
		}
		
		//Inseri as naturezas de equipe marcadas para o usuario
		if((form.getExcluirNaturezaEquipe() == null || !form.getExcluirNaturezaEquipe().equals("on")) && form.getNaturezaEquipeMarcadas() != null){
			for(String natureza : form.getNaturezaEquipeMarcadas()){
				EquipeNatureza equipeNatureza = new EquipeNatureza();
				equipeNatureza.setId(Integer.parseInt(natureza));
				UsuarioNaturezaAcompEquipe usuarioNaturezaAcompEquipe = new UsuarioNaturezaAcompEquipe();
				usuarioNaturezaAcompEquipe.setEquipeNatureza(equipeNatureza);
				usuarioNaturezaAcompEquipe.setUsuario(usuario);
				usuarioNaturezaAcompEquipe.setUltimaAlteracao(new Date());
				
				fachada.inserir(usuarioNaturezaAcompEquipe);
			}
		}
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Atualização das Unidades/Empresas/Naturezas realizada com sucesso.",
				"Definir Usuário para Acompanhamento das Equipes",
				"exibirDefinirUsuarioAcompanhamentoEquipesAction.do?menu=sim");

		return retorno;	
	}

}