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
package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1395] Pesquisar Documentos Pagos
 * 
 * @author Hugo Azevedo
 * @date 20/11/2012
 * 
 */
public class PesquisarDocumentosPagosActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String matriculaImovel;
	private String idRA;
	private String inscricaoImovel;
	private String tipoDocumento;
	private String referenciaInicial;
	private String referenciaFinal;
	private String dataEmissaoInicial;
	private String dataEmissaoFinal;
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	private String dataPagamentoInicial;
	private String dataPagamentoFinal;
	private String[] idsSelecionados;
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public String getReferenciaInicial() {
		return referenciaInicial;
	}
	public String getReferenciaFinal() {
		return referenciaFinal;
	}
	public String getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}
	public String getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	public String getDataPagamentoInicial() {
		return dataPagamentoInicial;
	}
	public String getDataPagamentoFinal() {
		return dataPagamentoFinal;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}
	public void setDataEmissaoInicial(String dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}
	public void setDataEmissaoFinal(String dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	public void setDataPagamentoInicial(String dataPagamentoInicial) {
		this.dataPagamentoInicial = dataPagamentoInicial;
	}
	public void setDataPagamentoFinal(String dataPagamentoFinal) {
		this.dataPagamentoFinal = dataPagamentoFinal;
	}
	public String[] getIdsSelecionados() {
		return idsSelecionados;
	}
	public void setIdsSelecionados(String[] idsSelecionados) {
		this.idsSelecionados = idsSelecionados;
	}
	
	public void reset(){
		this.matriculaImovel = null;
		this.inscricaoImovel = null;
		this.tipoDocumento = null;
		this.referenciaInicial = null;
		this.referenciaFinal = null;
		this.dataEmissaoInicial = null;
		this.dataEmissaoFinal = null;
		this.dataVencimentoInicial = null;
		this.dataVencimentoFinal = null;
		this.dataPagamentoInicial = null;
		this.dataPagamentoFinal = null;
		this.idsSelecionados = null;
	}
	public String getIdRA() {
		return idRA;
	}
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}
	
	
	

}
