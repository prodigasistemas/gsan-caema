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
    
    // dados do filtrar solicita��o de acesso
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
