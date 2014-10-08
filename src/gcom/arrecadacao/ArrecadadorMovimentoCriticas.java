package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Raimundo Martins
 * @date 03/05/2012
 * */
public class ArrecadadorMovimentoCriticas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Integer TIPO_INVALIDO = new Integer(1);
	
	public static final Integer ARQUIVO_MOVIMENTO_SEM_HEADER = new Integer(2);
	
	public static final Integer CODIGO_REMESSA_NAO_CORRESPONDE_2_RETORNO = new Integer(3);
	
	public static final Integer VERSAO_LAYOUT_ARQUIVO_INVALIDA = new Integer(4);
	
	public static final Integer ARQUIVO_MOVIMENTO_NAO_POSSUI_Z = new Integer(5);
	
	public static final Integer ARQUIVO_MOVIMENTO_REGISTROS_CODIGOS_INVALIDOS = new Integer(6);
	
	public static final Integer TOTAL_REGISTROS_ARQUIVO_INVALIDO = new Integer(7);
	
	public static final Integer VALOR_REGISTROS_INVALIDO = new Integer(8);
	
	public static final Integer LOTE_SERVICO_DIFERENTE_HEADER = new Integer(9);
	
	public static final Integer ARQUIVO_MOVIMENTO_NAO_POSSUI_9 = new Integer(10);
	
	public static final Integer ARQUIVO_MOVIMENTO_FORA_SEQUENCIA = new Integer(11);
	
	
	private Integer id;
	private Integer codigoBanco;
	private String identificacaoServico;
	private Integer nsa;
	private Date dataGeracao;
	private Date dataProcessamento;
	private Date ultimaAlteracao;
	private ArrecadadorCritica arrecadadorCritica;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public Integer getNsa() {
		return nsa;
	}
	public void setNsa(Integer nsa) {
		this.nsa = nsa;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public ArrecadadorCritica getArrecadadorCritica() {
		return arrecadadorCritica;
	}
	public void setArrecadadorCritica(ArrecadadorCritica arrecadadorCritica) {
		this.arrecadadorCritica = arrecadadorCritica;
	}
	
}
