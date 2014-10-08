package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * @author Raimundo Martins
 * @date 10/07/2012
 * */
public class DebitosGeradosRelatorioRetornoOSFiscalizacaoHelper implements RelatorioBean {

	private String debitoTipo;
	private String valor;
	private String situacao;
	
	public String getDebitoTipo() {
		return debitoTipo;
	}
	public void setDebitoTipo(String debitoTipo) {
		this.debitoTipo = debitoTipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}	
}
