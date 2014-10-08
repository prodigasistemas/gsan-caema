package gcom.autoatendimento;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1550] Consultar Vencimento Alternativo para Webservice
 * 
 * @author Anderson Cabral
 * @since 03/09/2013
 * 
 * **/
public class VecimentoAlternativoHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> diasPossiveis;
	private String vencimentoAtual;
	private String permiteAlteracao;
	
	public ArrayList<Integer> getDiasPossiveis() {
		return diasPossiveis;
	}
	public void setDiasPossiveis(ArrayList<Integer> diasPossiveis) {
		this.diasPossiveis = diasPossiveis;
	}
	public String getVencimentoAtual() {
		return vencimentoAtual;
	}
	public void setVencimentoAtual(String vencimentoAtual) {
		this.vencimentoAtual = vencimentoAtual;
	}
	public String getPermiteAlteracao() {
		return permiteAlteracao;
	}
	public void setPermiteAlteracao(String permiteAlteracao) {
		this.permiteAlteracao = permiteAlteracao;
	}
}
