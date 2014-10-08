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
package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoPermissao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoPermissaoPK;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
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
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 11/11/2010
 */

public class InserirSolicitacaoAcessoAction extends GcomAction {

	private Integer idSolicitacaoAnterior = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Seta Objeto Solicitacao Acesso
		SolicitacaoAcesso solicitacaoAcesso = setSolicitacaoAcesso(form, sessao, usuario);
		
		if(idSolicitacaoAnterior==null){ //Inserir solicitacao
			Integer idSolicitacaoAcesso = (Integer) Fachada.getInstancia().inserir(solicitacaoAcesso);			
			solicitacaoAcesso.setId(idSolicitacaoAcesso);
		} else { //Atualiza solicitacao
			solicitacaoAcesso.setId(idSolicitacaoAnterior);
			Fachada.getInstancia().atualizar(solicitacaoAcesso);
		}
				
		/* Recupera as permissões especiais marcadas */
    	String[] permissoesEspeciaisMarcadas = null;
    	permissoesEspeciaisMarcadas = (String[]) form.getPermissoesEspeciais();
        Collection<PermissaoEspecial> colecaoPermissaoEspecialSelecionada  = new ArrayList<PermissaoEspecial>();
    	SolicitacaoAcessoPermissao aux = null;
    	if (permissoesEspeciaisMarcadas!=null){
	    	for (String s : permissoesEspeciaisMarcadas){
	    		aux = new SolicitacaoAcessoPermissao();
	    		aux.setComp_id(new SolicitacaoAcessoPermissaoPK(solicitacaoAcesso.getId(), Integer.parseInt(s)));
	    		aux.setUltimaAlteracao(new Date());
	    		Fachada.getInstancia().inserir(aux);
                PermissaoEspecial permissaoEspecial = new PermissaoEspecial();
                permissaoEspecial.setId(Integer.parseInt(s));
                colecaoPermissaoEspecialSelecionada.add(permissaoEspecial);
	    	}
    	}    
    	
    	 //RM8046 - alterado por Ana Maria - 22/10/2012 
    	 /* Seleciona as permissões especais já incluidas para o usuário*/           
        Collection<PermissaoEspecial> colecaoPermissaoEspecialBase = this.getFachada().pesquisarPermissaoEspecialUsuario(solicitacaoAcesso.getLogin());
              
        if(!Util.isVazioOrNulo(colecaoPermissaoEspecialBase) && !Util.isVazioOrNulo(colecaoPermissaoEspecialSelecionada)){
            Iterator permissaoEspecialBaseIterator = colecaoPermissaoEspecialBase.iterator();
            while (permissaoEspecialBaseIterator.hasNext()) {
                PermissaoEspecial permissaoEspecial = (PermissaoEspecial) permissaoEspecialBaseIterator.next();
                if (colecaoPermissaoEspecialSelecionada.contains(permissaoEspecial)){
                    colecaoPermissaoEspecialSelecionada.remove(permissaoEspecial);
                }
            }   
        }
    	
    	SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
    	SolicitacaoAcessoGrupoPK solicitacaoAcessoGrupoPK = null;
    	Grupo grupo = null;
    	
    	//Grupo
    	if (form.getGrupo()!=null && !form.getGrupo().equals("")){	 
    		// remover todos os grupos associado a Solicitação de Acesso.
    		this.getFachada().removerGrupoDeSolicitacaoAcesso(solicitacaoAcesso.getId());
    		
    		grupo = new Grupo();
	    	grupo.setId(Integer.parseInt(form.getGrupo()));
	    	solicitacaoAcessoGrupoPK = new SolicitacaoAcessoGrupoPK(solicitacaoAcesso.getId(), grupo.getId());		
			solicitacaoAcessoGrupo = new SolicitacaoAcessoGrupo(solicitacaoAcessoGrupoPK, new Date());
			Fachada.getInstancia().inserir(solicitacaoAcessoGrupo);
			
			//SB0002-Emite Email Usuários do Grupo Especial Segurança
			Fachada.getInstancia().enviarEmailGrupoEspecialSeguranca(solicitacaoAcesso, EnvioEmail.INSERIR_SOLICITACAO_ACESSO, grupo.getId());			
    	}
    	
		//Niveis
    	if (form.getNivel()!=null && !form.getNivel().equals("")){
    		Fachada.getInstancia().inserirNivel(Integer.parseInt(form.getNivel()), solicitacaoAcesso);
    	}    	
		
		// Enviar Email
		EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(
				EnvioEmail.INSERIR_SOLICITACAO_ACESSO);

		String emailRemetente = envioEmail.getEmailRemetente();

		String tituloMensagem = "Solicitação de Acesso.";
		
		String emailReceptor = form.getEmail();
		if(form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, form.getIdFuncionarioSuperior()));

			Collection<Usuario> usuarioPesquisado = Fachada.getInstancia().pesquisar(
					filtroUsuario, Usuario.class.getName());
			if(usuarioPesquisado != null && !usuarioPesquisado.isEmpty()){
				emailReceptor = ((Usuario)Util.retonarObjetoDeColecao(usuarioPesquisado)).getDescricaoEmail();
			}	
			
		}

		String descPermissoesEspeciais =  "";
        if (colecaoPermissaoEspecialSelecionada != null && !colecaoPermissaoEspecialSelecionada.isEmpty()) {
            Iterator permissaoEspecialSelecionadaIterator = colecaoPermissaoEspecialSelecionada.iterator();

            descPermissoesEspeciais = descPermissoesEspeciais + " e para a(s) permissão(ões) especial(is) ";
           
            while (permissaoEspecialSelecionadaIterator.hasNext()) {
                PermissaoEspecial prmissaoEspecial = (PermissaoEspecial) permissaoEspecialSelecionadaIterator.next();
                FiltroPermissaoEspecial filtro = new FiltroPermissaoEspecial();
                filtro.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.ID, prmissaoEspecial.getId()));
                Collection<PermissaoEspecial> colecaoPermissaoEspecial = Fachada.getInstancia().pesquisar(filtro, PermissaoEspecial.class.getName());
                PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPermissaoEspecial);
                if (permissaoEspecial!=null){
                    descPermissoesEspeciais = descPermissoesEspeciais + permissaoEspecial.getDescricao()+", ";
                }   
            }
            //Retira a ultima virgula
            descPermissoesEspeciais = descPermissoesEspeciais.substring(0, (descPermissoesEspeciais.length() - 2));
        }
		
		String mensagem = "";
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			mensagem += " O Funcionário, Matrícula: "+ solicitacaoAcesso.getFuncionario().getId()
					+ " e de nome "+ solicitacaoAcesso.getNomeUsuario()+" necessita de sua liberação de acesso para o(s) grupo(s) "
					+ Fachada.getInstancia().obterNomesGrupos(form.getGrupo(), form.getNivel())+ descPermissoesEspeciais +".";
		}else{
		
			mensagem += " O Prestador de serviços, CPF: "+ solicitacaoAcesso.getCpf()
					+ " e de nome "+ solicitacaoAcesso.getNomeUsuario()+" necessita de sua liberação de acesso para o(s) grupo(s) "
					+ Fachada.getInstancia().obterNomesGrupos(form.getGrupo(), form.getNivel())+ descPermissoesEspeciais +".";
		}
		
		if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
			
			try {
				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
						tituloMensagem, mensagem);
				
			} catch (ErroEmailException erroEnviarEmail) {
				erroEnviarEmail.printStackTrace();
			}
		}

		   	
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Solicitação de Acesso para usuário: " 
				+  solicitacaoAcesso.getNomeUsuario()
				+ " inserida com sucesso. ",
				"Inserir outra Solicitação de Acesso",
				"exibirInserirSolicitacaoAcessoAction.do?menu=sim");

		return retorno;
	
	}
	
	/**
	 * Preenche objeto com informações vindas da tela
	 * 
	 * @author Hugo Leonardo
	 * @date 11/11/2010
	 * 
	 * @param form
	 * @return SolicitacaoAcesso
	 */
	private SolicitacaoAcesso setSolicitacaoAcesso(ExibirInserirSolicitacaoAcessoActionForm form, 
			HttpSession sessao, Usuario usuario) {		
		
		SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
		
		Fachada fachada = Fachada.getInstancia();
		
		// Usuario solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdFuncionarioSolicitante().toLowerCase()));
			Collection<Usuario> CollUsuarioResponsavel = fachada.pesquisar(
				filtroUsuario, Usuario.class.getName());
			Usuario usuarioSolicitante = (Usuario) Util.retonarObjetoDeColecao(CollUsuarioResponsavel);
			solicitacaoAcesso.setUsuarioSolicitante(usuarioSolicitante);
			
			solicitacaoAcesso.setFuncionarioSolicitante(null);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Funcionário Solicitante");
		}
		
		// funcionário responsável
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, form.getIdFuncionarioSuperior()));
			Collection<Usuario> CollUsuarioResponsavel = fachada.pesquisar(
				filtroUsuario, Usuario.class.getName());
			Usuario usuarioResponsavel = (Usuario) Util.retonarObjetoDeColecao(CollUsuarioResponsavel);
			
			/**
			 * RM9331 - Ajustes nas funcionalidades de Segurança de Acesso
			 * Verifica se o usuário tem acesso a permissão especial AUTORIZAR SOLICITACAO ACESSO
			 */
			boolean possuiPermissao = 
	    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
	    					PermissaoEspecial.AUTORIZAR_SOLICITACAO_ACESSO,usuarioResponsavel);
			if (!possuiPermissao){
				throw new ActionServletException("atencao.usuario_sem_permissao_autorizar");	
			}
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSuperior()));
			solicitacaoAcesso.setFuncionarioResponsavel(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Responsável pela Autorização");
		}
		
		// Indicador Notificar por E-mail
		if(Util.verificarNaoVazio(form.getIcNotificar())){
			
			if(form.getIcNotificar().equals("0")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("1"));
			}else if(form.getIcNotificar().equals("1")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("2"));
			}
		}
		
		// tipo Usuário
		if(Util.verificarNaoVazio(form.getIdTipoUsuario())){
			UsuarioTipo usuarioTipo = new UsuarioTipo();
			usuarioTipo.setId(new Integer(form.getIdTipoUsuario()));
			solicitacaoAcesso.setUsuarioTipo(usuarioTipo);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Tipo de Usuário");
		}
		
		// funcionário usuário
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionario()));
			solicitacaoAcesso.setFuncionario(funcionario);
		}

		// Empresa, Nome Usuário
        if(Util.verificarNaoVazio(form.getIdFuncionario())){
        
            FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
            filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(
                    FiltroFuncionario.UNIDADE_EMPRESA);
            filtroFuncionario.adicionarParametro(new ParametroSimples(
                    FiltroFuncionario.ID, form.getIdFuncionario()));

            Collection<Funcionario> funcionarioPesquisado = Fachada.getInstancia().pesquisar(
                    filtroFuncionario, Funcionario.class.getName());
            
            if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
                Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
                
                solicitacaoAcesso.setEmpresa(funcionario.getEmpresa());
                
                // CPF
                if(funcionario.getNumeroCpf() != null){
                    
                    if(Util.validacaoCPF(funcionario.getNumeroCpf())){
                        solicitacaoAcesso.setCpf(funcionario.getNumeroCpf());
                    }else{
                        throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
                    }
                }
                
                // Data Nascimento
                if(funcionario.getDataNascimento() != null){
                    solicitacaoAcesso.setDataNascimento(funcionario.getDataNascimento());
                }

                // Nome Usuário
                int tamanhoMaximoCampo = 50;
                if (tamanhoMaximoCampo < funcionario.getNome().length()) {
                    // trunca a String
                    String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
                    solicitacaoAcesso.setNomeUsuario(strTruncado);
                }else{
                    solicitacaoAcesso.setNomeUsuario(funcionario.getNome());
                }
            } else { //Funcionario não encontrado
                if (Integer.parseInt(form.getIdTipoUsuario())!=8){ //Funcionário é obrigatório
                    throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Matrícula do Funcionário");
                }
            }
            
        }else if(Util.verificarNaoVazio(form.getIdEmpresa())){
            Empresa empresa = new Empresa();
            empresa.setId(new Integer(form.getIdEmpresa()));
            solicitacaoAcesso.setEmpresa(empresa);
        }
        
		
		
		// nome Usuário
		if(!Util.verificarNaoVazio(form.getIdFuncionario()) 
				&& Util.verificarNaoVazio(form.getNomeUsuario())){
			
			solicitacaoAcesso.setNomeUsuario(form.getNomeUsuario());
		}
		
		// CPF
		if(form.getCpf() != null 
				&& Util.verificarNaoVazio(form.getCpf())){
			
			if(Util.validacaoCPF(form.getCpf())){
				solicitacaoAcesso.setCpf(form.getCpf());
				
			}else{
				throw new ActionServletException("atencao.cpf_invalido");
			}
			
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())){
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Número do CPF");
		}
		
		// data Nascimento
		if(solicitacaoAcesso.getDataNascimento() == null 
				&& Util.verificarNaoVazio(form.getDataNascimento())
				&& !Util.validarDiaMesAno(form.getDataNascimento())){
			
			solicitacaoAcesso.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())) {
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data de Nascimento");
		}
		
		// Unidade de Lotação
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, new Integer(form.getIdLotacao())));
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				solicitacaoAcesso.setUnidadeOrganizacional((UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional));	                
			} else {
				throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Unidade de Lotação");
			}	        
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Unidade de Lotação");
		}
				
		// Login
		if(Util.verificarNaoVazio(form.getLogin())){
			
			solicitacaoAcesso.setLogin(form.getLogin());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Login");
		}
		
		// Email
		if(Util.verificarNaoVazio(form.getEmail())){
			
			solicitacaoAcesso.setEmail(form.getEmail());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "E-Mail");
		}
		
		//Período Inicial e Período Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				
				solicitacaoAcesso.setPeriodoInicial(Util.converteStringParaDate(form.getDataInicial()));
				
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					solicitacaoAcesso.setPeriodoFinal(Util.converteStringParaDate(form.getDataFinal()));
					if(Util.compararData(solicitacaoAcesso.getPeriodoInicial(),solicitacaoAcesso.getPeriodoFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}	
				
				//[FS0008]Validar Período (Período de cadastramento não pode ser superior a 12 meses)
//				if (Util.retornaQuantidadeMeses(Util.getAnoMesComoInteger(solicitacaoAcesso.getPeriodoFinal()),Util.getAnoMesComoInteger(solicitacaoAcesso.getPeriodoInicial()))>12){
//					throw new ActionServletException("atencao.periodo_superior_12_meses");					
//				}
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
		}else{
			solicitacaoAcesso.setPeriodoInicial(null);
			solicitacaoAcesso.setPeriodoFinal(null);
			
		}
		
		//competencia para retificação
        if(form.getCompetenciaRetificacao() !=null
        		&& !form.getCompetenciaRetificacao().equals("")
        		&& !form.getCompetenciaRetificacao().equals("0")){
        	solicitacaoAcesso.setCompetenciaRetificacao(Util.formatarMoedaRealparaBigDecimal(form.getCompetenciaRetificacao()));
        }
		
		// Solicitação Acesso Situação
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		filtroSolicitacaoAcessoSituacao.adicionarParametro(
				new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
						SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
		filtroSolicitacaoAcessoSituacao.adicionarParametro(
				new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<SolicitacaoAcessoSituacao> colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
				SolicitacaoAcessoSituacao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
			
			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
				(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
			
			solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
			solicitacaoAcesso.setDataAutorizacao(new Date());
		}			
		
		// Data Solicitação
		solicitacaoAcesso.setDataSolicitacao(new Date());
		
		// Ultima Alteração
		solicitacaoAcesso.setUltimaAlteracao(new Date());
		
		//Verifica CPF já cadastrado
		if(solicitacaoAcesso.getCpf() != null && !solicitacaoAcesso.getCpf().equals("")){
			
			FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.CPF, solicitacaoAcesso.getCpf()));
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
			Collection<SolicitacaoAcesso> colecaoSolicitacaoAcessoComCpf = Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso,
					SolicitacaoAcesso.class.getName());
			SolicitacaoAcesso solicitacaoAcessoComCpf = null;
			if (!colecaoSolicitacaoAcessoComCpf.isEmpty()) {
				solicitacaoAcessoComCpf = (SolicitacaoAcesso) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoComCpf);
			}
			if (solicitacaoAcessoComCpf!=null) {
				if (solicitacaoAcessoComCpf.getSolicitacaoAcessoSituacao().getId().intValue()==SolicitacaoAcessoSituacao.CAD_POR_AUTORIZACAO.intValue()){
					//Se existe SOLICITACAO_ACESSO AUTORIZADA para o mesmo CPF verifica se o usuario esta INATIVO
					FiltroUsuario filtro = new FiltroUsuario();
					filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.CPF, solicitacaoAcesso.getCpf()));
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_TIPO);
					Collection<Usuario> colecao = Fachada.getInstancia().pesquisar(filtro,Usuario.class.getName());
					if (colecao!=null){
						Usuario usuarioAnterior = (Usuario) Util.retonarObjetoDeColecao(colecao);
						if(usuarioAnterior!=null && usuarioAnterior.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO)){
							//Guarda id da solicitacao anterior que a solicitacao seja atualizada
							idSolicitacaoAnterior = solicitacaoAcessoComCpf.getId();
						} else {
							throw new ActionServletException(
									"atencao.cpf.usuario.ja_cadastrado", null, ""
											+ solicitacaoAcessoComCpf.getCpf());
						}
					}								
				} else {
					throw new ActionServletException(
							"atencao.solicitacao_acesso_existente_cpf", null, ""
									+ solicitacaoAcessoComCpf.getCpf());
				}
			}
		}
		
		if(idSolicitacaoAnterior==null && solicitacaoAcesso.getLogin() != null){
			
			FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();

			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.LOGIN, solicitacaoAcesso.getLogin()));

			Collection colecaoSolicitacaoAcessoComLogin = Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso,
					SolicitacaoAcesso.class.getName());

			if (!colecaoSolicitacaoAcessoComLogin.isEmpty()) {
				SolicitacaoAcesso solicitacaoAcessoComLogin = (SolicitacaoAcesso) colecaoSolicitacaoAcessoComLogin
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.login.ja.existe", null, ""
								+ solicitacaoAcessoComLogin.getLogin());
			}
		}
		
		if(idSolicitacaoAnterior==null && solicitacaoAcesso.getEmail() != null){
			
			FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();

			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.EMAIL, solicitacaoAcesso.getEmail()));

			Collection colecaoSolicitacaoAcessoComEmail = Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso,
					SolicitacaoAcesso.class.getName());

			if (!colecaoSolicitacaoAcessoComEmail.isEmpty()) {
				SolicitacaoAcesso solicitacaoAcessoComEmail = (SolicitacaoAcesso) colecaoSolicitacaoAcessoComEmail
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.email.ja.existe", null, ""
								+ solicitacaoAcessoComEmail.getEmail());
			}
		}
		
		if(idSolicitacaoAnterior==null && solicitacaoAcesso.getFuncionario() != null){
			
			FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();

			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.FUNCIONARIO_ID, solicitacaoAcesso.getFuncionario().getId()));
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
					FiltroSolicitacaoAcesso.FUNCIONARIO);

			Collection colecaoSolicitacaoAcessoComFuncionario = Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso,
					FiltroSolicitacaoAcesso.class.getName());

			if (!colecaoSolicitacaoAcessoComFuncionario.isEmpty()) {
				SolicitacaoAcesso solicitacaoAcessoComFuncionario = (SolicitacaoAcesso) colecaoSolicitacaoAcessoComFuncionario
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.funcionario.ja.existe", null, ""
								+ solicitacaoAcessoComFuncionario.getFuncionario().getNome());
			}
		}
		
		/* RM 3892 Implantar norma de senhas no GSAN */
		//Usuario Abrangencia
		if (form.getAbrangencia() != null && !form.getAbrangencia().equals("")){
			FiltroUsuarioAbrangencia filtroAbrangencia = new FiltroUsuarioAbrangencia(); 
			filtroAbrangencia.adicionarParametro(new ParametroSimples(FiltroUsuarioAbrangencia.ID, form.getAbrangencia()));
			Collection<UsuarioAbrangencia> colecaoUsuarioAbrangencia = fachada.pesquisar(
					filtroAbrangencia, UsuarioAbrangencia.class.getName());

			if (colecaoUsuarioAbrangencia != null
					&& !colecaoUsuarioAbrangencia.isEmpty()) {
				solicitacaoAcesso.setUsuarioAbrangencia((UsuarioAbrangencia) Util
						.retonarObjetoDeColecao(colecaoUsuarioAbrangencia));
			}
		}
		//Gerencia Regional
		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("")){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, form.getGerenciaRegional()));

			Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {
				solicitacaoAcesso.setGerenciaRegional((GerenciaRegional) Util
						.retonarObjetoDeColecao(colecaoGerenciaRegional));
			}
		}
		//Unidade de Negócio
		if (form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("")){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				solicitacaoAcesso.setUnidadeNegocio((UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio));
			}
		}
		//Localidade
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				solicitacaoAcesso.setLocalidade((Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade));
			}
		}
		/* FIM RM 3892 */
		
		
		/* RM 3892.2 Implantar norma de senhas no GSAN */
		if(form.getIdUsuarioResponsavel()!= null && !form.getIdUsuarioResponsavel().equals("")){
			//Usuario Responsavel
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuarioResponsavel()));
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				// verifica se o usuário responsável por revalidar tem permissão especial para ser autorizador
				/**
				 * RM9331 - Ajustes nas funcionalidades de Segurança de Acesso
				 * Verifica se o usuário tem acesso a permissão especial REVALIDAR USUARIO
				 */
				Usuario usuarioResponsavel = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				boolean possuiPermissao = 
		    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.REVALIDAR_USUARIO,usuarioResponsavel);
				if (!possuiPermissao){
					throw new ActionServletException("atencao.usuario_sem_permissao_revalidar_soli_acesso");	
				}
				solicitacaoAcesso.setUsuarioResponsavel(usuarioResponsavel);	                
			} else {
				throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Usuário responsável");
			}
			/* FIM RM 3892.2 */
		} else {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Usuário responsável");
		}
		
		return solicitacaoAcesso;

	}

}
