package gcom.integracao;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jonathan Marcos
 * @date 17/10/2013
 * [UC1567] Consultar Débitos Imóvel Via Webservice
 * [Observacao] 
 * 		1 - Classe Basica
 */
public class CarroPipaRetornoTipo implements Serializable {
	
	//Tipos de idRetorno XML
	public final static Integer ID_TIPO_REQUISICAO_INVALIDA = 1;
	public final static Integer ID_IMOVEL_SEM_DEBITO = 2;
	public final static Integer ID_IMOVEL_COM_DEBITO = 3;
	public final static Integer ID_IMOVEL_INVALIDO = 4;
	public final static Integer ID_SEQUENCIAL_INVALIDO = 5;
	public final static Integer ID_PLACA_CAMINHAO_INVALIDA = 6;
	public final static Integer ID_DATA_HORA_ABASTECIMENTO_INVALIDA = 7;
	public final static Integer ID_VOLUME_ABASTECIMENTO_INVALIDO = 8;
	public final static Integer ID_INDICADOR_COBRANCA_INVALIDO = 9;
	public final static Integer ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO = 10;
	public final static Integer ID_CADASTRO_EFETUADO_SUCESSO = 11;
	
	//Tipos de descricaoRetorno XML
	public final static String DESCRICAO_TIPO_REQUISICAO_INVALIDA = "TIPO DE REQUISICAO INVALIDA";
	public final static String DESCRICAO_IMOVEL_SEM_DEBITO = "IMOVEL SEM DEBITO";
	public final static String DESCRICAO_IMOVEL_COM_DEBITO = "IMOVEL COM DEBITO";
	public final static String DESCRICAO_IMOVEL_INVALIDO = "IMOVEL INVALIDO";
	public final static String DESCRICAO_SEQUENCIAL_INVALIDO = "SEQUENCIAL INVALIDO";
	public final static String DESCRICAO_PLACA_CAMINHAO_INVALIDA = "PLACA DO CAMINHAO INVALIDA";
	public final static String DESCRICAO_DATA_HORA_ABASTECIMENTO_INVALIDA = "DATA E HORA DO ABASTECIMENTO INVALIDA";
	public final static String DESCRICAO_VOLUME_ABASTECIMENTO_INVALIDO = "VOLUME ABASTECIMENTO INVALIDO"; 
	public final static String DESCRICAO_INDICADOR_COBRANCA_INVALIDO = "INDICADOR DE COBRANCA INVALIDO";
	public final static String DESCRICAO_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO = "INDICADOR ABASTECIMENTO AVULSO INVALIDO";
	public final static String DESCRICAO_CADASTRADO_SUCESSO = "CADASTRAMENTO EFETUADO COM SUCESSO";
	
	//Tipos de protocolo : o null no caso em que nao se disponibiliza o numero do protocolo
	public final static String NULL = "";
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricaoRetorno;
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}
	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
