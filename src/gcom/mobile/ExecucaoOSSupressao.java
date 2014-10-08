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
package gcom.mobile;

import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ExecucaoOSSupressao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private ExecucaoOSPK comp_id;

    /** nullable persistent field */
    private Integer leituraSupressao;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private SupressaoMotivo supressaoMotivo;

    /** persistent field */
    private SupressaoTipo supressaoTipo;
    
    private Integer indicadorPermanenciaHidrometro;    
    private Integer numeroSelo;
    
    /** full constructor */
    public ExecucaoOSSupressao(ExecucaoOSPK comp_id, Integer leituraSupressao, Date ultimaAlteracao, SupressaoMotivo supressaoMotivo, SupressaoTipo supressaoTipo, Integer indicadorPermanenciaHidrometro, Integer numeroSelo) {
        this.comp_id = comp_id;
    	this.leituraSupressao = leituraSupressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.supressaoMotivo = supressaoMotivo;
        this.supressaoTipo = supressaoTipo;
        this.indicadorPermanenciaHidrometro = indicadorPermanenciaHidrometro;
        this.numeroSelo = numeroSelo;
    }
    
    public ExecucaoOSSupressao( Integer idArquivoTexto, String linha ) {
    	String[] colunas = linha.split( "\\|" );
        
        Integer idOs = Integer.parseInt( colunas[1] );        
        
        ExecucaoOSPK pk = new ExecucaoOSPK();
        
        ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
        atoc.setId( idArquivoTexto );
        pk.setArquivoTextoOSCobranca( atoc );
        
        OrdemServico os = new OrdemServico();
        os.setId( idOs );
        pk.setOrdemServico( os );
        
        this.setComp_id( pk );
        
        SupressaoMotivo sm = new SupressaoMotivo();
        sm.setId( new Integer( colunas[2] ) );
        this.setSupressaoMotivo( sm );
        
        SupressaoTipo st = new SupressaoTipo();
        st.setId( new Integer( colunas[3] ) );
        this.setSupressaoTipo( st );
        
        if ( colunas[4] != null && !colunas[4].equals("") ){
        	this.setLeituraSupressao( new Integer( colunas[4] ) );	
        }
        
        if ( colunas[5] != null && !colunas[5].equals("") ){               
        	this.setNumeroSelo( new Integer( colunas[5] ) );
        }
        
        if ( colunas[6] != null && !colunas[6].equals("") ){               
        	this.setIndicadorPermanenciaHidrometro( new Integer( colunas[6] ) );
        }
 
        this.setUltimaAlteracao( new Date() );
    }    

    /** default constructor */
    public ExecucaoOSSupressao() {
    }
    
    public ExecucaoOSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ExecucaoOSPK comp_id) {
		this.comp_id = comp_id;
	}


	public Integer getLeituraSupressao() {
		return leituraSupressao;
	}

	public void setLeituraSupressao(Integer leituraSupressao) {
		this.leituraSupressao = leituraSupressao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SupressaoMotivo getSupressaoMotivo() {
		return supressaoMotivo;
	}

	public void setSupressaoMotivo(SupressaoMotivo supressaoMotivo) {
		this.supressaoMotivo = supressaoMotivo;
	}

	public SupressaoTipo getSupressaoTipo() {
		return supressaoTipo;
	}

	public void setSupressaoTipo(SupressaoTipo supressaoTipo) {
		this.supressaoTipo = supressaoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		
		return null;
	}

	public Integer getIndicadorPermanenciaHidrometro() {
		return indicadorPermanenciaHidrometro;
	}

	public void setIndicadorPermanenciaHidrometro(
			Integer indicadorPermanenciaHidrometro) {
		this.indicadorPermanenciaHidrometro = indicadorPermanenciaHidrometro;
	}

	public Integer getNumeroSelo() {
		return numeroSelo;
	}

	public void setNumeroSelo(Integer numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

}
