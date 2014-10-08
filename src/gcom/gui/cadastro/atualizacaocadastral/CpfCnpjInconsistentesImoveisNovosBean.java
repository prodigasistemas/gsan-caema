package gcom.gui.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;


public class CpfCnpjInconsistentesImoveisNovosBean implements RelatorioBean {
	
	private String setor;
	private String quadra;
	private String Lote;
	private String endereco;
	private String cpfCnpj;
	
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getLote() {
		return Lote;
	}
	public void setLote(String lote) {
		Lote = lote;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

}
