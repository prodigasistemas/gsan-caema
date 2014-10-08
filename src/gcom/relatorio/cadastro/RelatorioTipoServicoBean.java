package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioTipoServicoBean implements RelatorioBean {
	
	private String meioSolicitacao;
	private Integer quantidade;
	private String nome;
	private String login;
	private String lotacao;
	private String localidade;
	private String gerencia;
	
	
	
	public RelatorioTipoServicoBean(String meioSolicitacao, Integer quantidade) {
		super();
		this.meioSolicitacao = meioSolicitacao;
		this.quantidade = quantidade;
		
	}
	
	public RelatorioTipoServicoBean(String meioSolicitacao, Integer quantidade, String nome, String login,String lotacao) {
		super();
		this.meioSolicitacao = meioSolicitacao;
		this.quantidade = quantidade;
		this.nome = nome;
		this.login = login;
		this.lotacao = lotacao;

	}
	
	
	public RelatorioTipoServicoBean(Integer quantidade, String localidade, String gerencia) {
		super();
		this.localidade = localidade;
		this.quantidade = quantidade;
		this.gerencia = gerencia;
	}
	
	
	public RelatorioTipoServicoBean() {
		super();
	}
	
	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}
	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	
	
	
	
	
}
