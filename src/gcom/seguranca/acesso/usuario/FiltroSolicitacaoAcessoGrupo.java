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
package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Solicitacao Acesso Grupo 
 *
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class FiltroSolicitacaoAcessoGrupo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
    public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_ACESSO = "solicitacaoAcesso";
    
    public final static String GRUPO = "grupo";
    
    public final static String SOLICITACAO_ACESSO_ID = "solicitacaoAcesso.id";
    
    public final static String GRUPO_ID = "grupo.id";
    
    // dados do filtrar solicitação de acesso
    public final static String SOLICITACAO_ACESSO_SITUACAO_ID = "solicitacaoAcesso.solicitacaoAcessoSituacao.id";
    public final static String FUNCIONARIO_SOLICITANTE_ID = "solicitacaoAcesso.funcionarioSolicitante.id";
    public final static String USUARIO_SOLICITANTE_ID = "solicitacaoAcesso.usuarioSolicitante.id";
    public final static String FUNCIONARIO_RESPONSAVEL_ID = "solicitacaoAcesso.funcionarioResponsavel.id";
    
    public final static String LOGIN = "solicitacaoAcesso.login";
    
    public final static String SOLICITACAO_ACESSO_USUARIO_SOLICITANTE_ID = "solicitacaoAcesso.usuarioSolicitante.id";
    
    public final static String CPF = "solicitacaoAcesso.cpf";
    
    public final static String FUNCIONARIO_SOLICITANTE = "solicitacaoAcesso.funcionarioSolicitante";
    
    public final static String FUNCIONARIO_RESPONSAVEL = "solicitacaoAcesso.funcionarioResponsavel";
    
    public final static String UNIDADE_ORGANIZACIONAL = "solicitacaoAcesso.unidadeOrganizacional";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "solicitacaoAcesso.unidadeOrganizacional.id";
    
    public final static String LOCALIDADE = "solicitacaoAcesso.localidade";
    
    public final static String USUARIO_SOLICITACAO = "solicitacaoAcesso.usuario";
    
    public final static String LOCALIDADE_ELO = "solicitacaoAcesso.localidadeElo";
    
    public final static String ABRANGENCIA_ACESSO = "solicitacaoAcesso.usuarioAbrangencia";
    
    public final static String GERENCIA_REGIONAL = "solicitacaoAcesso.gerenciaRegional";
    
    public final static String UNIDADE_NEGOCIO = "solicitacaoAcesso.unidadeNegocio";
    
    public final static String EMAIL = "solicitacaoAcesso.email";
    
    public final static String EMPRESA = "solicitacaoAcesso.empresa";
    
    public final static String EMPRESA_ID = "solicitacaoAcesso.empresa.id";
    
    public final static String FUNCIONARIO = "solicitacaoAcesso.funcionario";
    
    public final static String FUNCIONARIO_ID = "solicitacaoAcesso.funcionario.id";
    
    public final static String FUNCIONARIO_UNIDADE_ORGANIZACIONAL = "solicitacaoAcesso.funcionario.unidadeOrganizacional";
    
    public final static String USUARIO_NOME = "solicitacaoAcesso.nomeUsuario";
    
    public final static String USUARIO_RESPONSAVEL = "solicitacaoAcesso.usuarioResponsavel";
    
    public final static String USUARIO_RESPONSAVEL_ID = "solicitacaoAcesso.usuarioResponsavel.id";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO = "solicitacaoAcesso.solicitacaoAcessoSituacao";
    
    public final static String PERIODO_INICIO = "solicitacaoAcesso.periodoInicial";
    
    public final static String PERIODO_FINAL = "solicitacaoAcesso.periodoFinal";
    
    public final static String DATA_SOLICITACAO = "solicitacaoAcesso.dataSolicitacao";
    
    public final static String DATA_AUTORIZACAO = "solicitacaoAcesso.dataAutorizacao";
    
    public final static String DATA_CADASTRAMENTO = "solicitacaoAcesso.dataCadastramento";
    
    public final static String USUARIO_TIPO = "solicitacaoAcesso.usuarioTipo";
    
    public final static String USUARIO_ABRANGENCIA = "solicitacaoAcesso.usuarioAbrangencia";
    
    public final static String USUARIO_FUNCIONARIO = "solicitacaoAcesso.usuario.funcionario";
    
    public final static String USUARIO_FUNCIONARIO_UNIDADE_ORGANIZACIONAL = "solicitacaoAcesso.usuario.funcionario.unidadeOrganizacional";
    
    public final static String DATA_NASCIMENTO = "solicitacaoAcesso.dataNascimento";
    
    public final static String USUARIO_TIPO_ID = "solicitacaoAcesso.usuarioTipo.id";
    public final static String ABRANGENCIA_ACESSO_ID = "solicitacaoAcesso.usuarioAbrangencia.id";
    public final static String GERENCIA_REGIONAL_ID = "solicitacaoAcesso.gerenciaRegional.id";
    public final static String UNIDADE_NEGOCIO_ID = "solicitacaoAcesso.unidadeNegocio.id";
    public final static String LOCALIDADE_ID = "solicitacaoAcesso.localidade.id";
    public final static String USUARIO_SITUACAO_ID = "solicitacaoAcesso.usuario.usuarioSituacao.id";
    
	/**
     * Constructor for the FiltroSolicitacaoAcessoGrupo object
     */
    public FiltroSolicitacaoAcessoGrupo() {
    }

    /**
     * Constructor for the FiltroSolicitacaoAcessoGrupo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoAcessoGrupo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}
