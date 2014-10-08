package gcom.gui.atendimentopublico.registroatendimento;

import java.io.Serializable;

public class ConsultarNormasProcedimentosHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descricaoDocumentoTipo;
	private String descricaoTitulo;
	private Integer idArquivo;
	private String descricaoArquivo;
	private String descricaoAssunto;
	
	public ConsultarNormasProcedimentosHelper(){}

	public String getDescricaoDocumentoTipo() {
		return descricaoDocumentoTipo;
	}
	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}

	public String getDescricaoTitulo() {
		return descricaoTitulo;
	}
	public void setDescricaoTitulo(String descricaoTitulo) {
		this.descricaoTitulo = descricaoTitulo;
	}

	public Integer getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}
	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}
}