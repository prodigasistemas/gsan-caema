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
package gcom.gui.portal;

import gcom.relatorio.RelatorioBean;

/**

 * @author Arthur Carvalho
 * @date 13/02/2012
 */
public class RelatorioSegundaviaAdesaoTacitaBean implements RelatorioBean {
	
	private String coluna1;
	
	private String coluna2;
	
	private String coluna1pagina3;
	
	private String coluna2pagina3;
	
	private String coluna1pagina2;
	
	private String coluna2pagina2;
	
	public RelatorioSegundaviaAdesaoTacitaBean(String coluna1, String coluna2, String coluna1pagina2, String coluna2pagina2, String coluna1pagina3, String coluna2pagina3) {
    	this.coluna1 = coluna1;
    	this.coluna2 = coluna2;
		this.coluna1pagina2 = coluna1pagina2;
		this.coluna2pagina2 = coluna2pagina2;
		this.coluna1pagina3 = coluna1pagina3;
		this.coluna2pagina3 = coluna2pagina3;
}

	public RelatorioSegundaviaAdesaoTacitaBean(String coluna1, String coluna2) {
    	this.coluna1 = coluna1;
    	this.coluna2 = coluna2;
}

	public String getColuna1() {
		return coluna1;
	}


	public void setColuna1(String coluna1) {
		this.coluna1 = coluna1;
	}


	public String getColuna2() {
		return coluna2;
	}


	public void setColuna2(String coluna2) {
		this.coluna2 = coluna2;
	}


	public String getColuna1pagina3() {
		return coluna1pagina3;
	}


	public void setColuna1pagina3(String coluna1pagina3) {
		this.coluna1pagina3 = coluna1pagina3;
	}


	public String getColuna2pagina3() {
		return coluna2pagina3;
	}


	public void setColuna2pagina3(String coluna2pagina3) {
		this.coluna2pagina3 = coluna2pagina3;
	}


	public String getColuna1pagina2() {
		return coluna1pagina2;
	}


	public void setColuna1pagina2(String coluna1pagina2) {
		this.coluna1pagina2 = coluna1pagina2;
	}


	public String getColuna2pagina2() {
		return coluna2pagina2;
	}


	public void setColuna2pagina2(String coluna2pagina2) {
		this.coluna2pagina2 = coluna2pagina2;
	}
	
	

	
}
