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
package gcom.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorContratoTarifa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

    /** nullable persistent field */
    private BigDecimal valorTarifa;

    /** nullable persistent field */
    private Short numeroDiaFloat;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma;

    /** nullable persistent field */
    private gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato;
    
    /** nullable persistent field */
    private BigDecimal valorTarifaPercentual;
    
    private Date dtTarifaInicio;
    
    private Date dtTarifaFim;

    /** full constructor */
    public ArrecadadorContratoTarifa(BigDecimal valorTarifa, Short numeroDiaFloat, Date ultimaAlteracao, gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma, gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato) {
        this.valorTarifa = valorTarifa;
        this.numeroDiaFloat = numeroDiaFloat;
        this.ultimaAlteracao = ultimaAlteracao;
        this.arrecadacaoForma = arrecadacaoForma;
        this.arrecadadorContrato = arrecadadorContrato;
    }

    /** default constructor */
    public ArrecadadorContratoTarifa() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public BigDecimal getValorTarifa() {
        return this.valorTarifa;
    }

    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Short getNumeroDiaFloat() {
        return this.numeroDiaFloat;
    }

    public void setNumeroDiaFloat(Short numeroDiaFloat) {
        this.numeroDiaFloat = numeroDiaFloat;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.arrecadacao.ArrecadacaoForma getArrecadacaoForma() {
        return this.arrecadacaoForma;
    }

    public void setArrecadacaoForma(gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma) {
        this.arrecadacaoForma = arrecadacaoForma;
    }

    public gcom.arrecadacao.ArrecadadorContrato getArrecadadorContrato() {
        return this.arrecadadorContrato;
    }

    public void setArrecadadorContrato(gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato) {
        this.arrecadadorContrato = arrecadadorContrato;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrecadadorContratoTarifa other = (ArrecadadorContratoTarifa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BigDecimal getValorTarifaPercentual() {
		return valorTarifaPercentual;
	}

	public void setValorTarifaPercentual(BigDecimal valorTarifaPercentual) {
		this.valorTarifaPercentual = valorTarifaPercentual;
	}

	public Date getDtTarifaInicio() {
		return dtTarifaInicio;
	}

	public void setDtTarifaInicio(Date dtTarifaInicio) {
		this.dtTarifaInicio = dtTarifaInicio;
	}

	public Date getDtTarifaFim() {
		return dtTarifaFim;
	}

	public void setDtTarifaFim(Date dtTarifaFim) {
		this.dtTarifaFim = dtTarifaFim;
	}
	
	
}
