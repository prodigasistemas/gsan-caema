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
