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
package gcom.gui.cobranca;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 * [UC0217] Inserir Limita��o Geogr�fica RD
 * @author Nathalia Santos 
 * @since 18/05/2012
 */
public class AdicionarLimitacaoGeograficaRDPopupActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String numero;
	
	private String dataVigenciaInicio;
	
	private String dataVigenciaFim;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private Integer[] idLocalidade;

	private Integer[] codigoSetorComercial;
	
    private Integer[] numeroQuadra;
    
    private String dataLimiteVencimentoContaParcelar;
    
    private String dataLimiteVencimentoContaVista;
    
    
    public void limparTodosCamposForm() {
    	this.dataVigenciaInicio = null;
		this.dataVigenciaFim = null;
		this.idGerenciaRegional = null;	
		this.idUnidadeNegocio = null;
		this.codigoSetorComercial = null;
		this.idLocalidade = null;
		this.numeroQuadra = null;
		this.dataLimiteVencimentoContaParcelar = null;
		this.dataLimiteVencimentoContaVista = null;
		
	}
    
    public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataVigenciaInicio() {
		return dataVigenciaInicio;
	}

	public void setDataVigenciaInicio(String dataVigenciaInicio) {
		this.dataVigenciaInicio = dataVigenciaInicio;
	}

	public String getDataVigenciaFim() {
		return dataVigenciaFim;
	}

	public void setDataVigenciaFim(String dataVigenciaFim) {
		this.dataVigenciaFim = dataVigenciaFim;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public Integer[] getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer[] idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public Integer[] getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer[] codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer[] getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer[] numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getDataLimiteVencimentoContaParcelar() {
		return dataLimiteVencimentoContaParcelar;
	}

	public void setDataLimiteVencimentoContaParcelar(
			String dataLimiteVencimentoContaParcelar) {
		this.dataLimiteVencimentoContaParcelar = dataLimiteVencimentoContaParcelar;
	}

	public String getDataLimiteVencimentoContaVista() {
		return dataLimiteVencimentoContaVista;
	}

	public void setDataLimiteVencimentoContaVista(
			String dataLimiteVencimentoContaVista) {
		this.dataLimiteVencimentoContaVista = dataLimiteVencimentoContaVista;
	}
	
	

}
