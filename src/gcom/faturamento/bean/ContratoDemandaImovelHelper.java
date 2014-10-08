package gcom.faturamento.bean;

/**
 * Helper utilizado pelo adicionar/manter de contrato demanda
 * 
 * @author Davi Menezes
 * @since 19/04/2013
 */
public class ContratoDemandaImovelHelper {

	private String idImovel;
	
	private String matricula;
	
	private String inscricaoImovel;
	
	private String enderecoImovel;
	
	private String nomeClienteUsuario;
	
	private String descricaoCategoria;
	
	private String quantidadeEconomias;
	
	private String volumeEsgoto;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(String volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoDemandaImovelHelper other = (ContratoDemandaImovelHelper) obj;
		if (idImovel == null) {
			if (other.idImovel != null)
				return false;
		} else if (!idImovel.equals(other.idImovel))
			return false;
		return true;
	}
	
}
