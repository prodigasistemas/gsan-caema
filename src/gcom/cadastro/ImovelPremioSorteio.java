package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class ImovelPremioSorteio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private PremioSorteio premioSorteio;
	
	private ImovelCadastroSorteio imovelCadastroSorteio;
	
	private Date dataSorteio;
	
	private Integer numeroOrdemSorteio;
	
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PremioSorteio getPremioSorteio() {
		return premioSorteio;
	}

	public void setPremioSorteio(PremioSorteio premioSorteio) {
		this.premioSorteio = premioSorteio;
	}

	public ImovelCadastroSorteio getImovelCadastroSorteio() {
		return imovelCadastroSorteio;
	}

	public void setImovelCadastroSorteio(ImovelCadastroSorteio imovelCadastroSorteio) {
		this.imovelCadastroSorteio = imovelCadastroSorteio;
	}

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public Integer getNumeroOrdemSorteio() {
		return numeroOrdemSorteio;
	}

	public void setNumeroOrdemSorteio(Integer numeroOrdemSorteio) {
		this.numeroOrdemSorteio = numeroOrdemSorteio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
