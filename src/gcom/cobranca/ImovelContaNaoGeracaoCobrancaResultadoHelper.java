package gcom.cobranca;


import java.math.BigDecimal;


public class ImovelContaNaoGeracaoCobrancaResultadoHelper implements Comparable<ImovelContaNaoGeracaoCobrancaResultadoHelper>{
	
	private String descricaoMotivo;	
	private Integer idImovel;		
	private String mesAnoConta;
	private String valorConta;
	private String hint;
	
	
	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}

	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}

	
	
	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getValorConta() {
		return valorConta;
	}

	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}		

	public int compareTo(ImovelContaNaoGeracaoCobrancaResultadoHelper o) {
		Integer im1 = getIdImovel();
		Integer im2 = o.getIdImovel();		
		return im1.compareTo(im2);
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	

}
