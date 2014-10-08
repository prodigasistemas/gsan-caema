package gcom.autoatendimento;

import java.io.Serializable;

/**
 * 
 * @author Arthur
 *
 */
public class DebitoCreditoPagamentoAVistaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String referencia;
	
	private String vencimento;
	
	private String parcelas;
	
	private String valor;
	
	public DebitoCreditoPagamentoAVistaHelper() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
