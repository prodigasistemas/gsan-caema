package gcom.autoatendimento;

import gcom.relatorio.faturamento.RelatorioDeclaracaoAnualQuitacaoDebitosBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1549] - ConsultarImpressãoDeclaraçãoQuitaçãoWebservice
 * 
 * @author Anderson Cabral
 * @since 03/09/2013
 * 
 * **/
public class DeclaracaoAnualQuitacaoDebitoHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String endereco;
	private String nome;
	private String ano;
	private String sequencial;
	private ArrayList<RelatorioDeclaracaoAnualQuitacaoDebitosBean> faturas;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public ArrayList<RelatorioDeclaracaoAnualQuitacaoDebitosBean> getFaturas() {
		return faturas;
	}
	public void setFaturas(
			ArrayList<RelatorioDeclaracaoAnualQuitacaoDebitosBean> faturas) {
		this.faturas = faturas;
	}

}
