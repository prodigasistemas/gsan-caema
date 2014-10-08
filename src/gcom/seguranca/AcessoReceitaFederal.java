package gcom.seguranca;

import gcom.cadastro.MensagemRetornoReceitaFederal;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Arthur
 *
 */
public class AcessoReceitaFederal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataConsulta;
	
	private Date ultimaAlteracao;
	
	private MensagemRetornoReceitaFederal mensagemRetornoReceitaFederal;

	public AcessoReceitaFederal(){
	}
	
	public AcessoReceitaFederal(Date dataConsulta , Date ultimaAlteracao, MensagemRetornoReceitaFederal mensagemRetornoReceitaFederal){
		this.dataConsulta = dataConsulta;
		this.ultimaAlteracao = ultimaAlteracao;
		this.mensagemRetornoReceitaFederal = mensagemRetornoReceitaFederal;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public MensagemRetornoReceitaFederal getMensagemRetornoReceitaFederal() {
		return mensagemRetornoReceitaFederal;
	}

	public void setMensagemRetornoReceitaFederal(
			MensagemRetornoReceitaFederal mensagemRetornoReceitaFederal) {
		this.mensagemRetornoReceitaFederal = mensagemRetornoReceitaFederal;
	}
	
}