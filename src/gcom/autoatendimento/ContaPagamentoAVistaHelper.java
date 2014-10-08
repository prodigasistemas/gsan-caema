package gcom.autoatendimento;

import java.io.Serializable;

/**
 * 
 * @author Arthur
 *
 */
public class ContaPagamentoAVistaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	
	private String vencimento;
	
	private String valor;
	
	public ContaPagamentoAVistaHelper() {
		super();
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
