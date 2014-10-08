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
package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1121] Gerar Relat�rio de Im�veis com Altera��o de Inscri��o Via Batch
 * @author Vivianne Sousa
 * @date 24/07/2013
 */
public class RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean implements RelatorioBean {
	
	
	private JRBeanCollectionDataSource arrayJRSetor;
	private ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean;
	
	private JRBeanCollectionDataSource arrayJRUsuario;
	private ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean;
	
	private JRBeanCollectionDataSource arrayJRLigAgua;
	private ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean;
	
	private String codLocalidade;
	private String descLocalidade;
	private Integer qtdeImoveisLocalidade;

	public RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean(
			    String codLocalidade, 
			    String descLocalidade,
			    Collection colecaoSetor,
			    Collection colecaoUsuario,
			    Collection colecaoLigAgua,
			    Integer qtdeImoveisLocalidade) {
	    	
		    this.codLocalidade = codLocalidade;
		    this.descLocalidade = descLocalidade;
		    this.qtdeImoveisLocalidade = qtdeImoveisLocalidade;
		    
			this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean = new ArrayList();
			this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean.addAll(colecaoSetor);
			this.arrayJRSetor = new JRBeanCollectionDataSource(
					this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean);
		  
			this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean = new ArrayList();
			if ( colecaoUsuario != null ) {
				this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean.addAll(colecaoUsuario);
				this.arrayJRUsuario = new JRBeanCollectionDataSource(
						this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean);
			}else{
				this.arrayJRUsuario = null;
			}

			this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean = new ArrayList();
			this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean.addAll(colecaoLigAgua);
			this.arrayJRLigAgua = new JRBeanCollectionDataSource(
					this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean);

    }

	public JRBeanCollectionDataSource getArrayJRSetor() {
		return arrayJRSetor;
	}

	public void setArrayJRSetor(JRBeanCollectionDataSource arrayJRSetor) {
		this.arrayJRSetor = arrayJRSetor;
	}

	public ArrayList getArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean() {
		return arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean;
	}

	public void setArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean(
			ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean) {
		this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean = arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchSetorBean;
	}

	public JRBeanCollectionDataSource getArrayJRUsuario() {
		return arrayJRUsuario;
	}

	public void setArrayJRUsuario(JRBeanCollectionDataSource arrayJRUsuario) {
		this.arrayJRUsuario = arrayJRUsuario;
	}

	public ArrayList getArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean() {
		return arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean;
	}

	public void setArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean(
			ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean) {
		this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean = arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchUsuarioBean;
	}

	public JRBeanCollectionDataSource getArrayJRLigAgua() {
		return arrayJRLigAgua;
	}

	public void setArrayJRLigAgua(JRBeanCollectionDataSource arrayJRLigAgua) {
		this.arrayJRLigAgua = arrayJRLigAgua;
	}

	public ArrayList getArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean() {
		return arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean;
	}

	public void setArrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean(
			ArrayList arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean) {
		this.arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean = arrayRelatorioResumoImoveisAlteracaoInscricaoViaBatchLigAguaBean;
	}

	public String getCodLocalidade() {
		return codLocalidade;
	}

	public void setCodLocalidade(String codLocalidade) {
		this.codLocalidade = codLocalidade;
	}

	public String getDescLocalidade() {
		return descLocalidade;
	}

	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}

	public Integer getQtdeImoveisLocalidade() {
		return qtdeImoveisLocalidade;
	}

	public void setQtdeImoveisLocalidade(Integer qtdeImoveisLocalidade) {
		this.qtdeImoveisLocalidade = qtdeImoveisLocalidade;
	}
	
	}



