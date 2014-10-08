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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Mariana Victor
 * @since 12/11/2013
 */
public class ExecucaoOSSubstituicao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private ExecucaoOSPK comp_id;

    private Integer numeroLeitura;

    private Short indicadorTipoHidrometro;

    private Short indicadorCavalete;

    private Date ultimaAlteracao;
    
    private HidrometroSituacao hidrometroSituacao;

    private HidrometroLocalArmazenagem hidrometroLocalArmazenagem;
    
    private Hidrometro hidrometro;
    
    private Integer numeroLeituraInstalacao;
    
    private String numeroSelo;
    
    public ExecucaoOSSubstituicao(ExecucaoOSPK comp_id, Integer numeroLeitura, Short indicadorTipoHidrometro, 
    		Short indicadorCavalete, Date ultimaAlteracao, HidrometroSituacao hidrometroSituacao, 
    		HidrometroLocalArmazenagem hidrometroLocalArmazenagem, Hidrometro hidrometro) {
        this.comp_id = comp_id;
    	this.numeroLeitura = numeroLeitura;
    	this.indicadorTipoHidrometro = indicadorTipoHidrometro;
    	this.indicadorCavalete = indicadorCavalete;
        this.ultimaAlteracao = ultimaAlteracao;
        this.hidrometroSituacao = hidrometroSituacao;
        this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
        this.hidrometro = hidrometro;
    }
    
    public ExecucaoOSSubstituicao(Integer idArquivoTexto, String linha){

    	String[] colunas = linha.split("\\|");
    	
    	Integer idOs = Integer.parseInt(colunas[1]);
    	
        ExecucaoOSPK pk = new ExecucaoOSPK();
        
        ArquivoTextoOSCobranca arquivoTextoOSCobranca = new ArquivoTextoOSCobranca();
        arquivoTextoOSCobranca.setId(idArquivoTexto);
        pk.setArquivoTextoOSCobranca(arquivoTextoOSCobranca);
        
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setId(idOs);
        pk.setOrdemServico(ordemServico);
        
        this.setComp_id(pk);

    	this.setNumeroLeitura(new Integer(colunas[2]));	
        
        HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();
        hidrometroSituacao.setId(new Integer(colunas[3]));
        this.setHidrometroSituacao(hidrometroSituacao);
        
        HidrometroLocalArmazenagem hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();
        hidrometroLocalArmazenagem.setId(new Integer(colunas[4]));
        this.setHidrometroLocalArmazenagem(hidrometroLocalArmazenagem);
        
    	this.setIndicadorTipoHidrometro(new Short(colunas[5]));
        
        Hidrometro hidrometro = new Hidrometro();
        hidrometro.setId(new Integer(colunas[6]));
        this.setHidrometro(hidrometro);

    	this.setIndicadorCavalete(new Short(colunas[7]));

    	if(colunas.length>8){
    		
    		if(colunas.length==9){
    			
    			if(colunas[8] != null 
        				&& !colunas[8].toString().equals(""))
        			this.setNumeroLeituraInstalacao(new Integer(colunas[8]));
    			
    			this.setNumeroSelo(null);
    			
    		}else{
    			
    			if(colunas[8] != null 
        				&& !colunas[8].toString().equals(""))
        			this.setNumeroLeituraInstalacao(new Integer(colunas[8]));
        		
        		this.setNumeroSelo(colunas[9]);
    			
    		}
    		
    	}else{
    		this.setNumeroLeituraInstalacao(null);
    		this.setNumeroSelo(null);
    	}
        
        this.setUltimaAlteracao(new Date());
    }

    public ExecucaoOSSubstituicao() {
    	
    }
    
    public ExecucaoOSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ExecucaoOSPK comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getNumeroLeitura() {
		return numeroLeitura;
	}

	public void setNumeroLeitura(Integer numeroLeitura) {
		this.numeroLeitura = numeroLeitura;
	}

	public Short getIndicadorTipoHidrometro() {
		return indicadorTipoHidrometro;
	}

	public void setIndicadorTipoHidrometro(Short indicadorTipoHidrometro) {
		this.indicadorTipoHidrometro = indicadorTipoHidrometro;
	}

	public Short getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(Short indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public HidrometroSituacao getHidrometroSituacao() {
		return hidrometroSituacao;
	}

	public void setHidrometroSituacao(HidrometroSituacao hidrometroSituacao) {
		this.hidrometroSituacao = hidrometroSituacao;
	}

	public HidrometroLocalArmazenagem getHidrometroLocalArmazenagem() {
		return hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(HidrometroLocalArmazenagem hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}

	public Hidrometro getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(Hidrometro hidrometro) {
		this.hidrometro = hidrometro;
	}

	public Integer getNumeroLeituraInstalacao() {
		return numeroLeituraInstalacao;
	}

	public void setNumeroLeituraInstalacao(Integer numeroLeituraInstalacao) {
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
	}

	public String getNumeroSelo() {
		return numeroSelo;
	}

	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
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

}
