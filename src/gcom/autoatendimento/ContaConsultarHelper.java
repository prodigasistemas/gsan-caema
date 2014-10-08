package gcom.autoatendimento;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1546] - Consultar 2 Via de Conta
 * Classe que representa as contas que seram listadas no totem
 * 
 * @author Anderson Cabral
 * @date 27/08/2013
 */
public class ContaConsultarHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<ContaValorHelper> conta;
	
	public ContaConsultarHelper() {
		super();
	}
	


	public ArrayList<ContaValorHelper> getConta() {
		return conta;
	}

	public void setConta(ArrayList<ContaValorHelper> conta) {
		this.conta = conta;
	}
	
	
	
}
