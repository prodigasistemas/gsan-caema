package gcom.cadastro;

import java.io.Serializable;

/**
 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class ImovelEnderecoArquivoTextoHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idLogradouro;
	private String nomeLogradouro;
	private Integer idBairro;
	private String nomeBairro;
	private Integer qtdeImoveisTransferidos;
	private Integer qtdeImoveisTotal;
	
	public Integer getIdLogradouro() {
		return idLogradouro;
	}
	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public Integer getIdBairro() {
		return idBairro;
	}
	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public Integer getQtdeImoveisTransferidos() {
		return qtdeImoveisTransferidos;
	}
	public void setQtdeImoveisTransferidos(Integer qtdeImoveisTransferidos) {
		this.qtdeImoveisTransferidos = qtdeImoveisTransferidos;
	}
	public Integer getQtdeImoveisTotal() {
		return qtdeImoveisTotal;
	}
	public void setQtdeImoveisTotal(Integer qtdeImoveisTotal) {
		this.qtdeImoveisTotal = qtdeImoveisTotal;
	}
	
}
