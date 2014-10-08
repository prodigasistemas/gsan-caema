package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.util.Date;
public class RelatorioComprovanteCadastramentoSorteioBean implements RelatorioBean{

	private String nomeCliente;
	private String logradouro;
	private String numeroEndereco;
	private String complemento;
	private String bairro;
	private String municipio;
	private String estado;
	private String email;
	private String telFixo;
	private String telCelular;
	private String unidadeOrganizacional;
	private Date dataInscricao;
	private String numeroSorteio;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelFixo() {
		return telFixo;
	}
	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}
	public String getTelCelular() {
		return telCelular;
	}
	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public String getNumeroSorteio() {
		return numeroSorteio;
	}
	public void setNumeroSorteio(String numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}
	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	

}
