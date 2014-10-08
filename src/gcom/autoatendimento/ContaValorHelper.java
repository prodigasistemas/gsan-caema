package gcom.autoatendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * [UC1546] - Consultar 2 Via de Conta
 * Classe que representa as contas que seram listadas no totem
 * 
 * @author Anderson Cabral
 * @date 27/08/2013
 */
public class ContaValorHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idConta;
	private String periodo;
	private BigDecimal valor;
	
	private ArrayList<ContaValorHelper> conta;
	
	public ContaValorHelper() {
		super();
	}
	
	public ContaValorHelper(Integer idConta, String periodo, BigDecimal valor) {
		super();
		this.idConta = idConta;
		this.periodo = periodo;
		this.valor = valor;
	}
	
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/***CNTA_VLAGUA+CNTA_VLESGOTO+CNTA_VLDEBITOS+ CNTA_VLIMPOSTOS-CNTA_VLCREDITOS**/
	public BigDecimal getValor() {
		return valor;
	}
	/***CNTA_VLAGUA+CNTA_VLESGOTO+CNTA_VLDEBITOS+ CNTA_VLIMPOSTOS-CNTA_VLCREDITOS**/
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ArrayList<ContaValorHelper> getConta() {
		return conta;
	}

	public void setConta(ArrayList<ContaValorHelper> conta) {
		this.conta = conta;
	}
	
	
	
}
