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
package gcom.mobile;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ExecucaoOSCorte extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private ExecucaoOSPK comp_id;

    /** nullable persistent field */
    private Integer leituraCorte;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private MotivoCorte motivoCorte;

    /** persistent field */
    private CorteTipo corteTipo;
    
    private Integer numeroSelo;
    
    /** full constructor */
    public ExecucaoOSCorte(ExecucaoOSPK comp_id, Integer leituraCorte, Date ultimaAlteracao, MotivoCorte motivoCorte, CorteTipo corteTipo, Integer numeroSelo) {
        this.comp_id = comp_id;
    	this.leituraCorte = leituraCorte;
        this.ultimaAlteracao = ultimaAlteracao;
        this.motivoCorte = motivoCorte;
        this.corteTipo = corteTipo;
        this.numeroSelo = numeroSelo;
    }
    
    public ExecucaoOSCorte( Integer idArquivoTexto, String linha ){

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
        
        MotivoCorte mc = new MotivoCorte();
        mc.setId( new Integer( colunas[2] ) );
        this.setMotivoCorte(mc);
        
        CorteTipo ct = new CorteTipo();
        ct.setId( new Integer( colunas[3] ) );
        this.setCorteTipo(ct);
        
        if ( colunas[4] != null && !colunas[4].equals("") ){
        	this.setLeituraCorte( new Integer( colunas[4] ) );	
        }
        
        if ( colunas[5] != null && !colunas[5].equals("")) {                
        	this.setNumeroSelo( new Integer( colunas[5] ) );
        }
        
        this.setUltimaAlteracao( new Date() );
    }

    /** default constructor */
    public ExecucaoOSCorte() {
    }
    
    public ExecucaoOSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ExecucaoOSPK comp_id) {
		this.comp_id = comp_id;
	}


	public Integer getLeituraCorte() {
		return leituraCorte;
	}

	public void setLeituraCorte(Integer leituraCorte) {
		this.leituraCorte = leituraCorte;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public MotivoCorte getMotivoCorte() {
		return motivoCorte;
	}

	public void setMotivoCorte(MotivoCorte motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	public CorteTipo getCorteTipo() {
		return corteTipo;
	}

	public void setCorteTipo(CorteTipo corteTipo) {
		this.corteTipo = corteTipo;
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

	public Integer getNumeroSelo() {
		return numeroSelo;
	}

	public void setNumeroSelo(Integer numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

}
