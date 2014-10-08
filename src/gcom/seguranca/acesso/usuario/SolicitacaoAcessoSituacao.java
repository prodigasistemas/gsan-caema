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
 * R�mulo Aur�lio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 * Anderson Italo felinto de Lima
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

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
@ControleAlteracao()
public class SolicitacaoAcessoSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR = 1707;
	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR = 1710;
	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER = 1709;
	
	public final static Short AG_AUTORIZACAO_SUP = new Short("1");
	public final static Short CAD_POR_AUTORIZACAO = new Short("4");
	public final static Short AG_CADASTRAMENTO = new Short("2");
	public final static Short ATIVO = new Short("3");
	public final static Short NAO_AUTORIZADO = new Short("5");

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private String descricao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Date ultimaAlteracao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Short codigoSituacao;

	/** default constructor */
	public SolicitacaoAcessoSituacao() {

	}

	/** Constructor */
	public SolicitacaoAcessoSituacao(String descricao, Short indicadorUso,
			Date ultimaAlteracao) {

		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		filtroSolicitacaoAcessoSituacao
				.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoAcessoSituacao.ID, this.getId()));
		return filtroSolicitacaoAcessoSituacao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoSituacao. ID, this.getId()));

		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = { "descricao" };
		return labels;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = { "Descricao" };
		return labels;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

}