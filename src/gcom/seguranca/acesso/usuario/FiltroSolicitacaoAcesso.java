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
 * Filtro Solicitacao Acesso
 *
 * @author Hugo Leonardo
 * @date 12/11/2010
 */
public class FiltroSolicitacaoAcesso extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
	public final static String ID = "id";
    
    public final static String LOGIN = "login";
    
    public final static String CPF = "cpf";
    
    public final static String FUNCIONARIO_SOLICITANTE = "funcionarioSolicitante";
    
    public final static String USUARIO_SOLICITANTE = "usuarioSolicitante";
    
    public final static String USUARIO_SOLICITANTE_ID = "usuarioSolicitante.id";
    
    public final static String FUNCIONARIO_SOLICITANTE_ID = "funcionarioSolicitante.id";
    
    public final static String FUNCIONARIO_RESPONSAVEL = "funcionarioResponsavel";
    
    public final static String FUNCIONARIO_RESPONSAVEL_ID = "funcionarioResponsavel.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String USUARIO_SOLICITACAO = "usuario";
    
    
    public final static String LOCALIDADE_ELO = "localidadeElo";
    
    public final static String ABRANGENCIA_ACESSO = "usuarioAbrangencia";
    
    public final static String GERENCIA_REGIONAL = "gerenciaRegional";
    
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio";
    
    public final static String EMAIL = "email";
    
    public final static String EMPRESA = "empresa";
    
    public final static String EMPRESA_ID = "empresa.id";
    
    public final static String FUNCIONARIO = "funcionario";
    
    public final static String FUNCIONARIO_ID = "funcionario.id";
    
    public final static String FUNCIONARIO_UNIDADE_ORGANIZACIONAL = "funcionario.unidadeOrganizacional";
    
    public final static String USUARIO_NOME = "nomeUsuario";
    
    public final static String USUARIO_RESPONSAVEL = "usuarioResponsavel";
    
    public final static String USUARIO_RESPONSAVEL_ID = "usuarioResponsavel.id";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO = "solicitacaoAcessoSituacao";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO_ID = "solicitacaoAcessoSituacao.id";
    
    public final static String PERIODO_INICIO = "periodoInicial";
    
    public final static String PERIODO_FINAL = "periodoFinal";
    
    public final static String DATA_SOLICITACAO = "dataSolicitacao";
    
    public final static String DATA_AUTORIZACAO = "dataAutorizacao";
    
    public final static String DATA_CADASTRAMENTO = "dataCadastramento";
    
    public final static String USUARIO_TIPO = "usuarioTipo";
    
    public final static String USUARIO_ABRANGENCIA = "usuarioAbrangencia";
    
    public final static String USUARIO_FUNCIONARIO = "usuario.funcionario";
    
    public final static String USUARIO_FUNCIONARIO_UNIDADE_ORGANIZACIONAL = "usuario.funcionario.unidadeOrganizacional";
    
    public final static String DATA_NASCIMENTO = "dataNascimento";
    
    public final static String USUARIO_TIPO_ID = "usuarioTipo.id";
    public final static String ABRANGENCIA_ACESSO_ID = "usuarioAbrangencia.id";
    public final static String GERENCIA_REGIONAL_ID = "gerenciaRegional.id";
    public final static String UNIDADE_NEGOCIO_ID = "unidadeNegocio.id";
    public final static String LOCALIDADE_ID = "localidade.id";
    public final static String USUARIO_SITUACAO_ID = "usuario.usuarioSituacao.id";
    
    
	/**
     * Constructor for the FiltroSolicitacaoAcesso object
     */
    public FiltroSolicitacaoAcesso() {
    }

    /**
     * Constructor for the FiltroSolicitacaoAcesso object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoAcesso(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}