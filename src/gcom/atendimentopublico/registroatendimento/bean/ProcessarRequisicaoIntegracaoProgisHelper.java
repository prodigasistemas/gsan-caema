package gcom.atendimentopublico.registroatendimento.bean;

import java.math.BigDecimal;

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




/**
 * Esta classe tem por finalidade facilitar a forma como ser� retornado o resultado do 
 * [UC1400] Integra��o Progis Com Gsan Via Celular 
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