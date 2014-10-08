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
package gcom.faturamento;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ComandoOsAnormalidade implements Serializable {

	public static final short INDICADOR_COMANDO_EXECUTADO = 1;
	public static final short INDICADOR_COMANDO_NAO_EXECUTADO = 2;

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private Localidade localidadeInicial;    

    /** persistent field */
    private Localidade localidadeFinal;

    /** persistent field */
    private SetorComercial setorComercialInicial;
    
    /** persistent field */
    private SetorComercial setorComercialFinal;

    /** persistent field */
    private Quadra quadraInicial;

    /** persistent field */
    private Quadra quadraFinal;

    /** persistent field */
    private Rota rotaInicial;

    /** persistent field */
    private Rota rotaFinal;

    /** persistent field */
    private Short indicadorComandoExecutado;    

    /** persistent field */
    private Short indicadorBaixoConsumo;

    /** persistent field */
    private Short indicadorEstouroConsumo;

    /** persistent field */
    private Date ultimaAlteracao;

    /** default constructor */
    public ComandoOsAnormalidade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public SetorComercial getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public SetorComercial getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Quadra getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Quadra quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Quadra getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Quadra quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Rota getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Rota rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Rota getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Rota rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Short getIndicadorComandoExecutado() {
		return indicadorComandoExecutado;
	}

	public void setIndicadorComandoExecutado(Short indicadorComandoExecutado) {
		this.indicadorComandoExecutado = indicadorComandoExecutado;
	}

	
	public Short getIndicadorBaixoConsumo() {
		return indicadorBaixoConsumo;
	}

	public void setIndicadorBaixoConsumo(Short indicadorBaixoConsumo) {
		this.indicadorBaixoConsumo = indicadorBaixoConsumo;
	}

	public Short getIndicadorEstouroConsumo() {
		return indicadorEstouroConsumo;
	}

	public void setIndicadorEstouroConsumo(Short indicadorEstouroConsumo) {
		this.indicadorEstouroConsumo = indicadorEstouroConsumo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

   
}
