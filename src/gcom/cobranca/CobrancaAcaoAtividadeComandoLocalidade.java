package gcom.cobranca;

import gcom.cadastro.localidade.Localidade;

import java.io.Serializable;
import java.util.Date;

public class CobrancaAcaoAtividadeComandoLocalidade implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	private Localidade localidade;
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
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
