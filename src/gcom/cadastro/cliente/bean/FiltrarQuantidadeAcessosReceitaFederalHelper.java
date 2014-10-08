package gcom.cadastro.cliente.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
 *
 * @author Ricardo Germinio
 * @date 27/11/2012
 *
 */

public class FiltrarQuantidadeAcessosReceitaFederalHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date dataInicial;
	private Date dataFinal;
	private String mensagemRetorno;
	
	public Date getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}

}
