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
package gcom.cadastro;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PremioSorteio implements Serializable {
    /** identifier field */
    /** persistent field */
    /** persistent field */
    /** persistent field */
    /** persistent field */
    /** persistent field */
    
    /** persistent field */
    private Sorteios sorteios;
    
    /** persistent field */
    private Short indicadorInformarPremioSecundario;
    
    /** persistent field */
    private Short indicadorInformarPremioPrincipal;
    
    /** persistent field */
    private Integer numeroSorteio;
    
    /** persistent field */
    private Date dataCadastroInicial;
    
    /** persistent field */
    private Date dataCadastroFinal;
    
	public Integer getId() {
	public void setId(Integer id) {
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeInicial() {
		return quantidadeInicial;
	}

	public void setQuantidadeInicial(Integer quantidadeInicial) {
		this.quantidadeInicial = quantidadeInicial;
	}

	public Integer getQuantidadeFinal() {
		return quantidadeFinal;
	}

	public void setQuantidadeFinal(Integer quantidadeFinal) {
		this.quantidadeFinal = quantidadeFinal;
	}

	public Integer getNumeroOrdemSorteio() {
		return numeroOrdemSorteio;
	}

	public void setNumeroOrdemSorteio(Integer numeroOrdemSorteio) {
		this.numeroOrdemSorteio = numeroOrdemSorteio;
	}

	public Date getUltimaAlteracao() {
	public void setUltimaAlteracao(Date ultimaAlteracao) {
	
		return sorteios;
	}

	public void setSorteios(Sorteios sorteios) {
		this.sorteios = sorteios;
	}
	
	public Short getIndicadorInformarPremioSecundario() {
		return indicadorInformarPremioSecundario;
	}

	public void setIndicadorInformarPremioSecundario(Short indicadorInformarPremioSecundario) {
		this.indicadorInformarPremioSecundario = indicadorInformarPremioSecundario;
	}

	public Short getIndicadorInformarPremioPrincipal() {
		return indicadorInformarPremioPrincipal;
	}

	public void setIndicadorInformarPremioPrincipal(Short indicadorInformarPremioPrincipal) {
		this.indicadorInformarPremioPrincipal = indicadorInformarPremioPrincipal;
	}

	public Integer getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(Integer numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}
	
	public Date getDataCadastroInicial() {
		return dataCadastroInicial;
	}

	public void setDataCadastroInicial(Date dataCadastroInicial) {
		this.dataCadastroInicial = dataCadastroInicial;
	}

	public Date getDataCadastroFinal() {
		return dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public String toString() {
	
}