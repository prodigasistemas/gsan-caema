package gcom.relatorio.cadastro.cliente;

import java.io.Serializable;

/**
 * 
 * [UC1429] Filtrar Cliente para Validar na Base da Receita Federal.
 * 
 * @author Maxwell Moreira
 *
 * @date 25/01/2011
 * 
 */
public class RelatorioClienteReceitaFederalHelper implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String documento;
	private String mensagem;
	private Boolean erroValidacao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Boolean getErroValidacao() {
		return erroValidacao;
	}
	public void setErroValidacao(Boolean erroValidacao) {
		this.erroValidacao = erroValidacao;
	}
}