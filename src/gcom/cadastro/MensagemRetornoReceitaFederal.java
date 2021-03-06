package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class MensagemRetornoReceitaFederal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static String CONSULTA_REALIZADA_COM_SUCESSO = "0100";
	public static String PARAMETRO_INVALIDO = "0221";
	public static String INFORMACAO_NAO_ENCONTRADA = "0222";
	public static String FALHA_NA_CONSULTA = "0199";
	public static String SEM_CONEXAO = "Sem conex�o";
	
	public static Integer ID_CONSULTA_REALIZADA_COM_SUCESSO = 1;
	public static Integer ID_PARAMETRO_INVALIDO = 2;
	public static Integer ID_INFORMACAO_NAO_ENCONTRADA = 3;
	public static Integer ID_FALHA_NA_CONSULTA = 4;
	
	private Integer id;
	
	private Integer codigoMensagemRetorno;
	
	private String descricaoMensagemRetorno;
	
	private Date dataUltimaAlteracao;
	
	public MensagemRetornoReceitaFederal(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoMensagemRetorno() {
		return codigoMensagemRetorno;
	}

	public void setCodigoMensagemRetorno(Integer codigoMensagemRetorno) {
		this.codigoMensagemRetorno = codigoMensagemRetorno;
	}

	public String getDescricaoMensagemRetorno() {
		return descricaoMensagemRetorno;
	}

	public void setDescricaoMensagemRetorno(String descricaoMensagemRetorno) {
		this.descricaoMensagemRetorno = descricaoMensagemRetorno;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	
}
