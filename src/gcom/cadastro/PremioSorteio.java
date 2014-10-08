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
package gcom.cadastro;
import java.io.Serializable;import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PremioSorteio implements Serializable {		private static final long serialVersionUID = 1L;
    /** identifier field */    private Integer id;
    /** persistent field */    private String descricao;
    /** persistent field */    private Integer quantidadeInicial;
    /** persistent field */    private Integer quantidadeFinal;
    /** persistent field */    private Integer numeroOrdemSorteio;
    /** persistent field */    private Date ultimaAlteracao;
    
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
    
	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}	
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

	public Date getUltimaAlteracao() {		return ultimaAlteracao;	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {		this.ultimaAlteracao = ultimaAlteracao;	}
		public Sorteios getSorteios() {
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

	public String toString() {        return new ToStringBuilder(this)            .append("id", getId())            .toString();    }
	
}