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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Gerar Relatorio CPF/CNPJ Inconsistentes para Imoveis novos
 * 
 * @author Anderson Cabral
 * @date 22/03/2013
 */
public class GerarRelatorioCpfCnpjInconsistentesImoveisNovosAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
//		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;
		
		//recebe a colecao de imoveis com os clientes a serem validados na receita
		Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = new ArrayList<DadosImovelPreGsanHelper>();
		if ( sessao.getAttribute("colecaoImovelPreGsan")!= null && !sessao.getAttribute("colecaoImovelPreGsan").equals("") ) {
			colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");	
		} else {
			colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelCadastradoPreGsan");
		}

		//Colecao de cpf/cnpj inconsistentes
		ArrayList<DadosImovelPreGsanHelper> colecaoCpfCnpjInconsistentes = new ArrayList<DadosImovelPreGsanHelper>();
		
		//Seleciona os cliente com cpfCnpj inconsistentes
		for(DadosImovelPreGsanHelper dadosImovelPreGsanHelper : colecaoDadosImovelPreGsanHelper){
			//caso o cpf tenha sido rejeitado, insere na colecao
			if(dadosImovelPreGsanHelper.getIndicadorCpfCnpjConfirmado() != null && dadosImovelPreGsanHelper.getIndicadorCpfCnpjConfirmado().equalsIgnoreCase("2")){
				colecaoCpfCnpjInconsistentes.add(dadosImovelPreGsanHelper);
			}
		}

		if(!colecaoCpfCnpjInconsistentes.isEmpty()){
			RelatorioCpfCnpjInconsistentesImoveisNovos relatorioCpfCnpjInconsistentesImoveisNovos = new RelatorioCpfCnpjInconsistentesImoveisNovos(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
			
			Localidade localidade = new Localidade();
			localidade.setDescricao(form.getDescricaoLocalidade());
			
			if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") 
					&& (form.getDescricaoLocalidade() == null || form.getDescricaoLocalidade().equals(""))){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
			}
			
			relatorioCpfCnpjInconsistentesImoveisNovos.addParametro("empresa", empresa.getDescricao());
			relatorioCpfCnpjInconsistentesImoveisNovos.addParametro("idLocalidade", form.getIdLocalidade());
			relatorioCpfCnpjInconsistentesImoveisNovos.addParametro("nomeLocalidade", localidade.getDescricao());
			relatorioCpfCnpjInconsistentesImoveisNovos.addParametro("colecaoCpfCnpjInconsistentes", colecaoCpfCnpjInconsistentes);
			relatorioCpfCnpjInconsistentesImoveisNovos.addParametro("tipoFormatoRelatorio",	Integer.parseInt(tipoRelatorio));
	
			retorno = processarExibicaoRelatorio(relatorioCpfCnpjInconsistentesImoveisNovos, tipoRelatorio, 
					httpServletRequest, httpServletResponse, actionMapping);
		}else{
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencao");
		}
		
		return retorno;	
	}
}
