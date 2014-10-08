package gcom.cobranca;

import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

public class CobrancaAcaoAtividadeComandoSetorComercial implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	private SetorComercial setorComercial;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
