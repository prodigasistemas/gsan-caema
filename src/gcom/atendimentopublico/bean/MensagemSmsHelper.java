package gcom.atendimentopublico.bean;

import java.io.Serializable;
import java.util.Date;

public class MensagemSmsHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idMensagemSms;
	
	private Date dataInicial;	
	
	private Date dataFinal;
	
	private Short situacaoMensagem;

	private String tipoSms;
	
	private Date ultimaAlteracao;

	public String getIdMensagemSms() {
		return idMensagemSms;
	}

	public void setIdMensagemSms(String idMensagemSms) {
		this.idMensagemSms = idMensagemSms;
	}

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

	public Short getSituacaoMensagem() {
		return situacaoMensagem;
	}

	public void setSituacaoMensagem(Short situacaoMensagem) {
		this.situacaoMensagem = situacaoMensagem;
	}

	public String getTipoSms() {
		return tipoSms;
	}

	public void setTipoSms(String tipoSms) {
		this.tipoSms = tipoSms;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
		
	}
}
