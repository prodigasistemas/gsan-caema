package gcom.atendimentopublico.registroatendimento.bean;

import java.math.BigDecimal;

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




/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC1400] Integração Progis Com Gsan Via Celular 
 * 
 * @author Carlos Chaves
 * @date 06/11/2006
 */
public class ProcessarRequisicaoIntegracaoProgisHelper {

	private String tipoRequisicao;
	private Long celularSolicitante;
	private Integer idRegistroAtendimento;
	private Integer idTipoSolicitacao;
	private Integer idTipoEspecificacao;
	private Integer idEndereco;
	private String nomeSolicitante;
	private String pontoReferencia;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	private String email;
	private byte[] foto;
	private long fileSizeFoto;
	private String extensao;
	private String loginAtendenteAbertura;
	private Integer idImovel;
	private String numeroProtocolo;
	private String numeroCPF;
	private String observacao;
	
	public ProcessarRequisicaoIntegracaoProgisHelper() {
		super();
	}
	
	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public ProcessarRequisicaoIntegracaoProgisHelper(Long celularSolicitante,
			Integer idRegistroAtendimento, Integer idTipoSolicitacao,
			Integer idTipoEspecificacao, Integer idEndereco,
			String nomeSolicitante, String pontoReferencia,
			BigDecimal coordenadaX, BigDecimal coordenadaY, String email,
			byte[] foto, long fileSizeFoto, String extensao,
			String loginAtendenteAbertura) {
		super();
		this.celularSolicitante = celularSolicitante;
		this.idRegistroAtendimento = idRegistroAtendimento;
		this.idTipoSolicitacao = idTipoSolicitacao;
		this.idTipoEspecificacao = idTipoEspecificacao;
		this.idEndereco = idEndereco;
		this.nomeSolicitante = nomeSolicitante;
		this.pontoReferencia = pontoReferencia;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.email = email;
		this.foto = foto;
		this.fileSizeFoto = fileSizeFoto;
		this.extensao = extensao;
		this.setLoginAtendenteAbertura(loginAtendenteAbertura);
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}
	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public Integer getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}
	public void setIdTipoSolicitacao(Integer idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}
	public Integer getIdTipoEspecificacao() {
		return idTipoEspecificacao;
	}
	public void setIdTipoEspecificacao(Integer idTipoEspecificacao) {
		this.idTipoEspecificacao = idTipoEspecificacao;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public Integer getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	public Long getCelularSolicitante() {
		return celularSolicitante;
	}
	public void setCelularSolicitante(Long celularSolicitante) {
		this.celularSolicitante = celularSolicitante;
	}
	public long getFileSizeFoto() {
		return fileSizeFoto;
	}
	public void setFileSizeFoto(long fileSizeFoto) {
		this.fileSizeFoto = fileSizeFoto;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	public String getLoginAtendenteAbertura() {
		return loginAtendenteAbertura;
	}
	public void setLoginAtendenteAbertura(String loginAtendenteAbertura) {
		this.loginAtendenteAbertura = loginAtendenteAbertura;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}
	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}
	public String getNumeroCPF() {
		return numeroCPF;
	}
	public void setNumeroCPF(String numeroCPF) {
		this.numeroCPF = numeroCPF;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}