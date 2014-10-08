package gcom.cobranca;

import java.io.Serializable;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * [SB0001] Adicionar Limitação Geográfica da RD
 * POPUP
 * 
 * @author Nathalia Santos 
 * @since 21/05/2012
 */
public class LimitacaoGeograficaRDHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numero;
	
	private String dataVigenciaInicio;
	
	private String dataVigenciaFim;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private Integer idLocalidade;
	
	private Integer codigoSetorComercial;
	
    private Integer numeroQuadra;
    
    private String dataLimiteVencimentoContaParcelar;
    
    private String dataLimiteVencimentoContaVista;
    
    
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataVigenciaInicio() {
		return dataVigenciaInicio;
	}

	public void setDataVigenciaInicio(String dataVigenciaInicio) {
		this.dataVigenciaInicio = dataVigenciaInicio;
	}

	public String getDataVigenciaFim() {
		return dataVigenciaFim;
	}

	public void setDataVigenciaFim(String dataVigenciaFim) {
		this.dataVigenciaFim = dataVigenciaFim;
	}

	

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getDataLimiteVencimentoContaParcelar() {
		return dataLimiteVencimentoContaParcelar;
	}

	public void setDataLimiteVencimentoContaParcelar(
			String dataLimiteVencimentoContaParcelar) {
		this.dataLimiteVencimentoContaParcelar = dataLimiteVencimentoContaParcelar;
	}

	public String getDataLimiteVencimentoContaVista() {
		return dataLimiteVencimentoContaVista;
	}

	public void setDataLimiteVencimentoContaVista(
			String dataLimiteVencimentoContaVista) {
		this.dataLimiteVencimentoContaVista = dataLimiteVencimentoContaVista;
	}

	@Override
	public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof LimitacaoGeograficaRDHelper)) {
            return false;
        }
        LimitacaoGeograficaRDHelper castOther = (LimitacaoGeograficaRDHelper) other;
        
        boolean ehIgual = 
        	
        	((this.getNumero() == null && castOther.getNumero() ==null) ||
        	(this.getNumero() != null &&  this.getNumero().equals(castOther.getNumero()))) &&
	        
        	((this.getDataVigenciaInicio() == null && castOther.getDataVigenciaInicio() ==null) ||
        	(this.getDataVigenciaInicio() != null && this.getDataVigenciaInicio().equals(castOther.getDataVigenciaInicio()))) &&
	        
        	((this.getDataVigenciaFim() == null && castOther.getDataVigenciaFim() ==null) ||
        	(this.getDataVigenciaFim() != null && this.getDataVigenciaFim().equals(castOther.getDataVigenciaFim()))) &&
	        
        	((this.getIdGerenciaRegional() == null && castOther.getIdGerenciaRegional() ==null) ||
        	(this.getIdGerenciaRegional() != null && this.getIdGerenciaRegional().equals(castOther.getIdGerenciaRegional())))&&
	   		
        	((this.getIdUnidadeNegocio() == null && castOther.getIdUnidadeNegocio() ==null) ||
        	(this.getIdUnidadeNegocio() != null && this.getIdUnidadeNegocio().equals(castOther.getIdUnidadeNegocio())))&&
			
        	((this.getIdLocalidade() == null && castOther.getIdLocalidade() ==null) ||
        	(this.getIdLocalidade() != null && this.getIdLocalidade().equals(castOther.getIdLocalidade())))&&
			
        	((this.getCodigoSetorComercial() == null && castOther.getCodigoSetorComercial() ==null) ||
        	(this.getCodigoSetorComercial() != null && this.getCodigoSetorComercial().equals(castOther.getCodigoSetorComercial())))&&
			
        	((this.getNumeroQuadra() == null && castOther.getNumeroQuadra() ==null) ||
        	(this.getNumeroQuadra() != null && this.getNumeroQuadra().equals(castOther.getNumeroQuadra())))&&
			
        	((this.getDataLimiteVencimentoContaParcelar() == null && castOther.getDataLimiteVencimentoContaParcelar() ==null) ||
        	(this.getDataLimiteVencimentoContaParcelar() != null && this.getDataLimiteVencimentoContaParcelar().equals(castOther.getDataLimiteVencimentoContaParcelar())))&&
			
        	((this.getDataLimiteVencimentoContaVista() == null && castOther.getDataLimiteVencimentoContaVista() ==null) ||
        	(this.getDataLimiteVencimentoContaVista() != null && this.getDataLimiteVencimentoContaVista().equals(castOther.getDataLimiteVencimentoContaVista())));
        
        return ehIgual;
    }
	
	
	
}
