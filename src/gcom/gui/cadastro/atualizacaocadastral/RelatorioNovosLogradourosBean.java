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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1443] Gerar Relatorio de Novos Logradouros 
 * @author Anderson Cabral
 * @date 15/03/2013
 */
public class RelatorioNovosLogradourosBean implements RelatorioBean{
	private String idLogradouro;
	private String idTipo;
	private String nomeTipo;
	private String idTitulo;
	private String nomeTitulo;
	private String nomeLogradouro;
	private String nomePopular;
	private String idMunicipio;
	private String nomeMunicipio;
	private String idBairro;
	private String nomeBairro;
	private String idCep;
	private String codigoCep;

	public RelatorioNovosLogradourosBean() {
		super();
	}

	public RelatorioNovosLogradourosBean(String idLogradouro, String idTipo,
			String nomeTipo, String idTitulo, String nomeTitulo,
			String nomeLogradouro, String nomePopular, String idMunicipio,
			String nomeMunicipio, String idBairro, String nomeBairro,
			String idCep, String codigoCep) {
		super();
		this.idLogradouro = idLogradouro;
		this.idTipo = idTipo;
		this.nomeTipo = nomeTipo;
		this.idTitulo = idTitulo;
		this.nomeTitulo = nomeTitulo;
		this.nomeLogradouro = nomeLogradouro;
		this.nomePopular = nomePopular;
		this.idMunicipio = idMunicipio;
		this.nomeMunicipio = nomeMunicipio;
		this.idBairro = idBairro;
		this.nomeBairro = nomeBairro;
		this.idCep = idCep;
		this.codigoCep = codigoCep;
	}
	
	public String getIdLogradouro() {
		return idLogradouro;
	}
	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	public String getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}
	public String getNomeTipo() {
		return nomeTipo;
	}
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	public String getIdTitulo() {
		return idTitulo;
	}
	public void setIdTitulo(String idTitulo) {
		this.idTitulo = idTitulo;
	}
	public String getNomeTitulo() {
		return nomeTitulo;
	}
	public void setNomeTitulo(String nomeTitulo) {
		this.nomeTitulo = nomeTitulo;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public String getNomePopular() {
		return nomePopular;
	}
	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getIdBairro() {
		return idBairro;
	}
	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getIdCep() {
		return idCep;
	}
	public void setIdCep(String idCep) {
		this.idCep = idCep;
	}
	public String getCodigoCep() {
		return codigoCep;
	}
	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}
}
