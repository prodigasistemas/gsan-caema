package gcom.cadastro;

import java.io.Serializable;

/**
 * [UC 1386] - Processar Arquivo de Resetorização de Imóveis 
 * 
 * @author Davi Menezes
 * @date 01/11/2012
 *
 */
public class ImovelInscricaoResetorizadaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	private String numeroQuadra;
	
	private String numeroLote;
	
	private String numeroSubLote;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}
	
}
