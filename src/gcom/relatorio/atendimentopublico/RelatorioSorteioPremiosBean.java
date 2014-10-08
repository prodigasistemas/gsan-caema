package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

public class RelatorioSorteioPremiosBean implements RelatorioBean {

	private String premio;
	
	private String ordemPremio;
	
	private String numeroSorteio;
	
	private String matricula;
	
	private String nomeCliente;
	
	private String cpfCliente;
	
	private String endereco;
	
	private String gerenciaRegional;
	
	private String localidade;

	public RelatorioSorteioPremiosBean() {
		super();
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getOrdemPremio() {
		return ordemPremio;
	}

	public void setOrdemPremio(String ordemPremio) {
		this.ordemPremio = ordemPremio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(String numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

}
