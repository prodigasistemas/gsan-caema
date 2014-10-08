package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Davi Menezes
 * @date 27/01/2014
 */
public class CobrancaAcaoAtividadeCronogramaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
	
	private Date dataRealizacao;

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
}
