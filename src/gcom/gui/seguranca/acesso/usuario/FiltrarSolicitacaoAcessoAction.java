/**
 * 
 */
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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Hugo Leonardo	
 * @date 16/11/2010
 */
public class FiltrarSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC1096] Filtrar Solicita��o de Acesso.
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de Solicitacao de Acesso.
	 * 
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSolicitacaoAcessoAction");

		FiltrarSolicitacaoAcessoActionForm form = (FiltrarSolicitacaoAcessoActionForm) actionForm;

		FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo = new FiltroSolicitacaoAcessoGrupo();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String objeto = (String) sessao.getAttribute("objeto");

		boolean peloMenosUmParametroInformado = false;
		
		String idFuncionarioSolicitante = form.getIdFuncionarioSolicitante(); 
		String idFuncionarioResponsavel = form.getIdFuncionarioSuperior(); 
		String idFuncionario = form.getIdFuncionario(); 
		String idEmpresa = form.getIdEmpresa(); 
		String idLotacao = form.getIdLotacao();  
		String nomeUsuario = form.getNomeUsuario(); 
		String[] situacoes = form.getIdsSituacao();
		String abrangencia = form.getAbrangencia();
		String gerenciaRegional = form.getGerenciaRegional();
		String unidadeNegocio = form.getUnidadeNegocio();
		String idLocalidade = form.getIdLocalidade();
		String usuarioTipo = form.getUsuarioTipo();
		String usuarioSituacao = form.getUsuarioSituacao();
		String cpf = form.getCpf();
		String dataNascimento = form.getDataNascimento();
		String login = form.getLogin();
		String grupoNivel = form.getNivel();
		String grupoEspecial = form.getGrupo();
	
		// verificar Situa��o
		if (situacoes != null && situacoes.length >= 0 
				&& !situacoes[0].equals("-1")){ 
			 
			List lista = Arrays.asList(form.getIdsSituacao());  
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimplesIn(
					FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_SITUACAO_ID, lista));
			
			peloMenosUmParametroInformado = true;
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Situa��o");
		}
		
		// Verifica se o campo Usuario Solicitante foi informado
		if (Util.verificarNaoVazio(idFuncionarioSolicitante)) {
			//Obtem o objeto usuario pelo LOGIN
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idFuncionarioSolicitante));
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtro, Usuario.class.getName());
			Usuario usuarioSolicitante = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_USUARIO_SOLICITANTE_ID, usuarioSolicitante.getId()));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Funcionario Respons�vel foi informado
		if (Util.verificarNaoVazio(idFuncionarioResponsavel)) {
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.FUNCIONARIO_RESPONSAVEL_ID, idFuncionarioResponsavel));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Funcionario foi informado
		if (Util.verificarNaoVazio(idFuncionario)) {
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcessoGrupo.FUNCIONARIO_ID, idFuncionario));
			
			peloMenosUmParametroInformado = true;
		}

		// Verifica se o campo Empresa foi informado
		if (idEmpresa != null && !idEmpresa.equals("-1")) {
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcessoGrupo.EMPRESA_ID, idEmpresa));
			
			peloMenosUmParametroInformado = true;
		} 
		
		// Verifica se o campo Lota��o foi informado
		if (Util.verificarNaoVazio(idLotacao)) {
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcessoGrupo.UNIDADE_ORGANIZACIONAL_ID, idLotacao));
			
			peloMenosUmParametroInformado = true;
		}

		// Verifica se o campo Nome Usu�rio foi informado
		if (Util.verificarNaoVazio(nomeUsuario)) {
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ComparacaoTexto(
					FiltroSolicitacaoAcessoGrupo.USUARIO_NOME, nomeUsuario.toUpperCase()));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Per�odo de Solicita��o foi informado
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					if(Util.compararData(Util.converteStringParaDate(form.getDataInicial()), Util.converteStringParaDate(form.getDataFinal())) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}else{
						
						filtroSolicitacaoAcessoGrupo.adicionarParametro(new Intervalo(
							FiltroSolicitacaoAcessoGrupo.DATA_SOLICITACAO, 
								Util.converteStringParaDate(form.getDataInicial()), 
								Util.converteStringParaDate(form.getDataFinal())));
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
			peloMenosUmParametroInformado = true;
		}
		
		if (abrangencia != null && !abrangencia.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.ABRANGENCIA_ACESSO_ID, abrangencia));
		}
		if (gerenciaRegional != null
				&& !gerenciaRegional.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.GERENCIA_REGIONAL_ID,
					gerenciaRegional));
		}
		if (unidadeNegocio != null
				&& !unidadeNegocio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.UNIDADE_NEGOCIO_ID,
					unidadeNegocio));
		}
		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.LOCALIDADE_ID, idLocalidade));
		}
		
		if(login != null && !login.trim().equalsIgnoreCase("")){
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.LOGIN,login));
		}
		
		if (dataNascimento != null
				&& !dataNascimento.trim().equalsIgnoreCase("")) {
			
			Date dataNascimentoConvertida = Util.converteStringParaDate(dataNascimento);
			peloMenosUmParametroInformado = true;
			

			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.DATA_NASCIMENTO,dataNascimentoConvertida));
		}
		
		// Insere os par�metros informados no filtro
		if (usuarioTipo != null && !usuarioTipo.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.USUARIO_TIPO_ID, usuarioTipo));
		}
		
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ComparacaoTextoCompleto(
				FiltroSolicitacaoAcessoGrupo.CPF, cpf.toUpperCase()));
			
		}
		
		if (usuarioSituacao != null
				&& !usuarioSituacao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoGrupo.USUARIO_SITUACAO_ID, usuarioSituacao));
		}
		
		if(grupoEspecial != null && !grupoEspecial.trim().equalsIgnoreCase("") || 
				grupoNivel != null && !grupoNivel.trim().equalsIgnoreCase("")){
				 
				List lista = new ArrayList();
				if(grupoEspecial != null && !grupoEspecial.trim().equalsIgnoreCase("")){
					lista.add(Util.converterStringParaInteger(grupoEspecial));
				}
				if(grupoNivel != null && !grupoNivel.trim().equalsIgnoreCase("")){
					lista.add(Util.converterStringParaInteger(grupoNivel));
				}
				filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimplesIn(
						FiltroSolicitacaoAcessoGrupo.GRUPO_ID, lista));
				peloMenosUmParametroInformado = true;
		}
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
			FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_SITUACAO);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.FUNCIONARIO_RESPONSAVEL);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.FUNCIONARIO_SOLICITANTE);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.UNIDADE_ORGANIZACIONAL);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.EMPRESA);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.ABRANGENCIA_ACESSO);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.GERENCIA_REGIONAL);	
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.UNIDADE_NEGOCIO);	
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.LOCALIDADE);
		
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
			FiltroSolicitacaoAcessoGrupo.USUARIO_SOLICITACAO);
		
		/*
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.LOCALIDADE_ELO);
		*/
		//RM3892.2 - Implementar Normas de Senhas no GSAN
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.USUARIO_RESPONSAVEL);

		filtroSolicitacaoAcessoGrupo.setCampoOrderBy(FiltroSolicitacaoAcessoGrupo.DATA_SOLICITACAO);
		filtroSolicitacaoAcessoGrupo.setCampoOrderBy(FiltroSolicitacaoAcessoGrupo.USUARIO_NOME);

//		Collection <SolicitacaoAcesso> colecaoSolicitacaoAcesso = 
//			fachada.pesquisar(filtroSolicitacaoAcessoGrupo, SolicitacaoAcessoGrupo.class.getName());		
//		
//		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
//		if (!peloMenosUmParametroInformado) {
//			throw new ActionServletException(
//					"atencao.filtro.nenhum_parametro_informado");
//		}
//
		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterSolicitacaoAcessoAction para nele verificar se ir�
		// para o atualizar ou para o manter
		if (form.getAtualizar() != null && form.getAtualizar().equalsIgnoreCase("1")) {
			
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());
		}
//
//		// Pesquisa sem registros
//		if (colecaoSolicitacaoAcesso == null || colecaoSolicitacaoAcesso.isEmpty()) {
//			throw new ActionServletException(
//					"atencao.pesquisa.nenhumresultado", null, "Solicita��o de Acesso");
//		} else {
//			SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
//			solicitacaoAcesso = (SolicitacaoAcesso) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcesso);
//			String idRegistroAtualizar = solicitacaoAcesso.getId().toString();
//
//			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
//			httpServletRequest.setAttribute("colecaoSolicitacaoAcesso", colecaoSolicitacaoAcesso);
//			sessao.removeAttribute("solicitacaoAcesso");
//		}
		
		sessao.setAttribute("filtroSolicitacaoAcessoGrupo", filtroSolicitacaoAcessoGrupo);
		httpServletRequest.setAttribute("filtroSolicitacaoAcessoGrupo", filtroSolicitacaoAcessoGrupo);
		
		//if (objeto != null && objeto.equals("relatorio")){
			sessao.setAttribute("filtroForm", form);
		//}
		
		httpServletRequest.setAttribute("situacoes", situacoes[0]);
		
		return retorno;
		
	}
	
}
