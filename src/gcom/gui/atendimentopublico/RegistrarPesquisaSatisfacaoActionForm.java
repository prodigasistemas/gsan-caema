package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem como objetivo receber os parâmetros da Pesquisa de Satisfação da Loja 
 * 
 * @author Davi Menezes
 * @date 27/02/2012
 *
 */
public class RegistrarPesquisaSatisfacaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String codigoDDD;
	
	private String telefone;
	
	private String email;
	
	private String idImovel;
	
	private String descricaoImovel;
	
	private String idUnidade;
	
	private String descricaoUnidade;
	
	private String dataAtendimento;
	
	private String horaAtendimento;
	
	private String comentarios;
	
	private String avaliacaoAtendente;
	
	private String agilidadeAtendimento;
	
	private String tempoEspera;
	
	private String confortoAmbiente;
	
	private String localizacaoAmbiente;
	
	private String seguranca;
	
	private String estacionamento;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(String codigoDDD) {
		this.codigoDDD = codigoDDD;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getAvaliacaoAtendente() {
		return avaliacaoAtendente;
	}

	public void setAvaliacaoAtendente(String avaliacaoAtendente) {
		this.avaliacaoAtendente = avaliacaoAtendente;
	}

	public String getAgilidadeAtendimento() {
		return agilidadeAtendimento;
	}

	public void setAgilidadeAtendimento(String agilidadeAtendimento) {
		this.agilidadeAtendimento = agilidadeAtendimento;
	}

	public String getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(String tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public String getConfortoAmbiente() {
		return confortoAmbiente;
	}

	public void setConfortoAmbiente(String confortoAmbiente) {
		this.confortoAmbiente = confortoAmbiente;
	}

	public String getLocalizacaoAmbiente() {
		return localizacaoAmbiente;
	}

	public void setLocalizacaoAmbiente(String localizacaoAmbiente) {
		this.localizacaoAmbiente = localizacaoAmbiente;
	}

	public String getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(String seguranca) {
		this.seguranca = seguranca;
	}

	public String getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(String estacionamento) {
		this.estacionamento = estacionamento;
	}

}
