package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1380] Cadastrar Imóveis para Sorteio
 * 
 * @author Mariana Victor
 * @date 19/10/2012
 */
public class CadastrarImovelSorteioActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String nome;
	private String email;
	private String rg;
	private String dataNascimento;
	private String indicadorCpfCnpj;
	private String cpfCliente;
	private String cnpjCliente;
	private String indicadorTipoRelacao;
	private String telefoneFixo;
	private String telefoneCelular;
	private String totalInscritos;
	private String idMunicipio;
	private String idBairro;
	private String logradouro;
	private String numeroEndereco;
	private String complemento;
	private String cep;
	private String indicadorAceitaRegulamento;
	private String inscricaoImovel;
	private String numeroSorteio;
	private String informacaoImovelInexistente;
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIndicadorCpfCnpj() {
		return indicadorCpfCnpj;
	}

	public void setIndicadorCpfCnpj(String indicadorCpfCnpj) {
		this.indicadorCpfCnpj = indicadorCpfCnpj;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getIndicadorTipoRelacao() {
		return indicadorTipoRelacao;
	}

	public void setIndicadorTipoRelacao(String indicadorTipoRelacao) {
		this.indicadorTipoRelacao = indicadorTipoRelacao;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTotalInscritos() {
		return totalInscritos;
	}

	public void setTotalInscritos(String totalInscritos) {
		this.totalInscritos = totalInscritos;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getIndicadorAceitaRegulamento() {
		return indicadorAceitaRegulamento;
	}

	public void setIndicadorAceitaRegulamento(String indicadorAceitaRegulamento) {
		this.indicadorAceitaRegulamento = indicadorAceitaRegulamento;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(String numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}

	public String getInformacaoImovelInexistente() {
		return informacaoImovelInexistente;
	}

	public void setInformacaoImovelInexistente(String informacaoImovelInexistente) {
		this.informacaoImovelInexistente = informacaoImovelInexistente;
	}

}