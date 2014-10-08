package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DocumentoImpedidoSorteio implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String cpf;

    /** persistent field */
    private String nomeImpedido;

    /** persistent field */
    private Date ultimaAlteracao;

	public DocumentoImpedidoSorteio() {
		super();
	}

	public DocumentoImpedidoSorteio(
			Integer id, String cpf, String nomeImpedido, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nomeImpedido = nomeImpedido;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeImpedido() {
		return nomeImpedido;
	}

	public void setNomeImpedido(String nomeImpedido) {
		this.nomeImpedido = nomeImpedido;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
