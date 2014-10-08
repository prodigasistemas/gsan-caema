package gcom.cadastro.imovel;

import java.io.Serializable;

public class ImovelFotoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte [] foto;
	
	private String descricao;
	
	private String idTipoFoto;
	
	private String observacao;
	
	private String extensaoArquivo;
	
	private String nomeArquivo;
	
	private String hint;
	
	public String getIdTipoFoto() {
		return idTipoFoto;
	}

	public void setIdTipoFoto(String idTipoFoto) {
		this.idTipoFoto = idTipoFoto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getExtensaoArquivo() {
		return extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	@Override
	public boolean equals(Object other) {
		if((this == other)){
			return true;
		}
		if(!(other instanceof ImovelFotoHelper)){
			return false;
		}
		ImovelFotoHelper castOther = (ImovelFotoHelper) other;
		
		boolean ehIgual =
				
				((this.getDescricao() == null && castOther.getDescricao() == null) ||
				(this.getDescricao() != null && this.getDescricao().equals(castOther.getDescricao()))) &&
				
				((this.getIdTipoFoto() == null && castOther.getIdTipoFoto() == null) ||
				(this.getIdTipoFoto() != null && this.getIdTipoFoto().equals(castOther.getIdTipoFoto()))) &&
				
				((this.getObservacao() == null && castOther.getObservacao() == null) || 
				(this.getObservacao() != null && this.getObservacao().equals(castOther.getObservacao()))) && 
				
				((this.getExtensaoArquivo() == null && castOther.getExtensaoArquivo() == null) || 
				(this.getExtensaoArquivo() != null && this.getExtensaoArquivo().equals(castOther.getExtensaoArquivo()))) &&
				
				((this.getNomeArquivo() == null && castOther.getNomeArquivo() == null) || 
				(this.getNomeArquivo() != null && this.getNomeArquivo().equals(castOther.getNomeArquivo())));
				
		return ehIgual;
	}
}
