/**
 * 
 */
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1395] Pesquisar Documentos Pagos
 * 
 * @author Hugo Azevedo
 * @date 20/11/2012
 * 
 */
public class InformarDadosDevValorFatPagoIndevActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	

	private String matriculaImovel;
	private String tipoDocumento;
	private String anoMesDocumento;
	
	//Guia de Pagamento
	//Débito a Cobrar
	private String valorFaturado;
	private String valorCorrigido;
	
	//Conta
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String idMotivoCobrancaIndevida;
	private String idConsumoTarifa;
	private String idSituacaoAguaConta;
	private String consumoAgua;
	private String idSituacaoEsgotoConta;
	private String consumoEsgoto;
	private String percentualEsgoto;
	private String consumoFaturadoPoco;
	private String percentualColeta;
	private String valorAgua;
	private String valorEsgoto;
	private String valorDebitos;
	private String valorCreditos;
	private String valorTotalConta;
	
	
	public String getValorAgua() {
		return valorAgua;
	}


	public String getValorEsgoto() {
		return valorEsgoto;
	}


	public String getValorDebitos() {
		return valorDebitos;
	}


	public String getValorCreditos() {
		return valorCreditos;
	}


	public String getValorTotalConta() {
		return valorTotalConta;
	}


	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}


	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}


	public void setValorDebitos(String valorDebitos) {
		this.valorDebitos = valorDebitos;
	}


	public void setValorCreditos(String valorCreditos) {
		this.valorCreditos = valorCreditos;
	}


	public void setValorTotalConta(String valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}


	public String getSituacaoAgua() {
		return situacaoAgua;
	}


	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}


	public String getIdMotivoCobrancaIndevida() {
		return idMotivoCobrancaIndevida;
	}


	public String getIdConsumoTarifa() {
		return idConsumoTarifa;
	}


	public String getIdSituacaoAguaConta() {
		return idSituacaoAguaConta;
	}


	public String getConsumoAgua() {
		return consumoAgua;
	}


	public String getIdSituacaoEsgotoConta() {
		return idSituacaoEsgotoConta;
	}


	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}


	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}


	public String getConsumoFaturadoPoco() {
		return consumoFaturadoPoco;
	}


	public String getPercentualColeta() {
		return percentualColeta;
	}


	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}


	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}


	public void setIdMotivoCobrancaIndevida(String idMotivoCobrancaIndevida) {
		this.idMotivoCobrancaIndevida = idMotivoCobrancaIndevida;
	}


	public void setIdConsumoTarifa(String idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}


	public void setIdSituacaoAguaConta(String idSituacaoAguaConta) {
		this.idSituacaoAguaConta = idSituacaoAguaConta;
	}


	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}


	public void setIdSituacaoEsgotoConta(String idSituacaoEsgotoConta) {
		this.idSituacaoEsgotoConta = idSituacaoEsgotoConta;
	}


	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}


	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}


	public void setConsumoFaturadoPoco(String consumoFaturadoPoco) {
		this.consumoFaturadoPoco = consumoFaturadoPoco;
	}


	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}


	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public String getAnoMesDocumento() {
		return anoMesDocumento;
	}


	public String getValorFaturado() {
		return valorFaturado;
	}


	public String getValorCorrigido() {
		return valorCorrigido;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public void setAnoMesDocumento(String anoMesDocumento) {
		this.anoMesDocumento = anoMesDocumento;
	}


	public void setValorFaturado(String valorFaturado) {
		this.valorFaturado = valorFaturado;
	}


	public void setValorCorrigido(String valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	//Funções auxiliares
	//====================================================
	public String getSituacaoAguaCortado(){
		return LigacaoAguaSituacao.CORTADO.toString();
	}
	
	public String getSituacaoAguaLigado(){
		return LigacaoAguaSituacao.LIGADO.toString();
	}
		
	public String getSim(){
		return ConstantesSistema.SIM.toString();
	}
	
	public String getNao(){
		return ConstantesSistema.NAO.toString();
	}
	
	//====================================================
	
	
	public void reset(){
		matriculaImovel = null;
		tipoDocumento = null;
		anoMesDocumento = null;
		
		//Guia de Pagamento
		//Débito a Cobrar
		valorFaturado = null;
		valorCorrigido = null;
		
		//Conta
		situacaoAgua = null;
		situacaoEsgoto = null;
		idMotivoCobrancaIndevida = null;
		idConsumoTarifa = null;
		idSituacaoAguaConta = null;
		consumoAgua = null;
		idSituacaoEsgotoConta = null;
		consumoEsgoto = null;
		percentualEsgoto = null;
		consumoFaturadoPoco = null;
		percentualColeta = null;
		valorAgua = null;
		valorEsgoto = null;
		valorDebitos = null;
		valorCreditos = null;
		valorTotalConta = null;
	}
	
	
	

}
