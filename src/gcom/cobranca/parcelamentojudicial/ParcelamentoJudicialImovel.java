package gcom.cobranca.parcelamentojudicial;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;

import java.io.Serializable;
import java.util.Date;

@ControleAlteracao()
public class ParcelamentoJudicialImovel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	private ParcelamentoJudicial parcelamentoJudicial;
	
	private Imovel imovel;
	
	private Short indicadorImovelPrincipal;
	
	private Date ultimaAlteracao;
	
	/** default constructor */
	public ParcelamentoJudicialImovel() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorImovelPrincipal() {
		return indicadorImovelPrincipal;
	}

	public void setIndicadorImovelPrincipal(Short indicadorImovelPrincipal) {
		this.indicadorImovelPrincipal = indicadorImovelPrincipal;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ParcelamentoJudicial getParcelamentoJudicial() {
		return parcelamentoJudicial;
	}

	public void setParcelamentoJudicial(ParcelamentoJudicial parcelamentoJudicial) {
		this.parcelamentoJudicial = parcelamentoJudicial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
		
}
