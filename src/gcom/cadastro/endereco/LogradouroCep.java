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
package gcom.cadastro.endereco;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class LogradouroCep extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro() {
		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, cep.getCepId()));
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.LOGRADOURO);
		return filtroLogradouroCep;
	}

	/** nullable persistent field */
    private Integer id;
    
    /** nullable persistent field */
    private Logradouro logradouro;

    /** nullable persistent field */
    private Cep cep;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;



    /** default constructor */
    public LogradouroCep() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LogradouroCep) ) return false;
        LogradouroCep castOther = (LogradouroCep) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

	public LogradouroCep(Integer id, Logradouro logradouro, Cep cep, Short indicadorUso, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo cep.
	 */
	public Cep getCep() {
		return cep;
	}

	/**
	 * @param cep O cep a ser setado.
	 */
	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo logradouro.
	 */
	public Logradouro getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro O logradouro a ser setado.
	 */
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
		if (getLogradouro() != null){
			getLogradouro().initializeLazy();
		}
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.getLogradouro().getDescricaoParaRegistroTransacao()
		 + " - " + this.cep.getCepFormatado();
	}
}