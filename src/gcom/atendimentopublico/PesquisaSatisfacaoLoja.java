package gcom.atendimentopublico;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.util.Date;

public class PesquisaSatisfacaoLoja implements Serializable {

	private static final long serialVersionUID = 3219688326536485532L;
	
	private Integer id;
	
	private String nomeCliente;
	
	private short codigoDDD;
	
	private String telefone;
	
	private String email;
	
	private Date dataAtendimento;
	
	private Date ultimaAlteracao;
	
	private short avaliacaoAtendente;
	
	private short agilidadeAtendimento;
	
	private short tempoEspera;
	
	private short confortoLimpeza;
	
	private short localizacao;
	
	private short seguranca;
	
	private short estacionamento;
	
	private String comentarios;
	
	private Imovel imovel;
	
	private UnidadeOrganizacional unidadeOrganizacional;
	
	public PesquisaSatisfacaoLoja(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public short getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(short codigoDDD) {
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

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getAvaliacaoAtendente() {
		return avaliacaoAtendente;
	}

	public void setAvaliacaoAtendente(short avaliacaoAtendente) {
		this.avaliacaoAtendente = avaliacaoAtendente;
	}

	public short getAgilidadeAtendimento() {
		return agilidadeAtendimento;
	}

	public void setAgilidadeAtendimento(short agilidadeAtendimento) {
		this.agilidadeAtendimento = agilidadeAtendimento;
	}

	public short getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(short tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public short getConfortoLimpeza() {
		return confortoLimpeza;
	}

	public void setConfortoLimpeza(short confortoLimpeza) {
		this.confortoLimpeza = confortoLimpeza;
	}

	public short getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(short localizacao) {
		this.localizacao = localizacao;
	}

	public short getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(short seguranca) {
		this.seguranca = seguranca;
	}

	public short getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(short estacionamento) {
		this.estacionamento = estacionamento;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

}
