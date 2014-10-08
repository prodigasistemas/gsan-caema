package gcom.cadastro.imovel.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContasCobrancaHelper implements Comparable<ContasCobrancaHelper>{

	private String mesAno;
	private String valorConta;
	private String situacao;
	private String dataPagamento;
	private String documentoTipo;
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getValorConta() {
		return valorConta;
	}
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDocumentoTipo() {
		return documentoTipo;
	}
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	public int compareTo(ContasCobrancaHelper o) {
		Integer retorno = null;
		try {
		 SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		 Date date1 = format.parse(mesAno);
		 Date date2 = format.parse(o.mesAno);
	     
	     retorno = date1.compareTo(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	
}
