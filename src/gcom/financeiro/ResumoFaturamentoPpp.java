package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.operacional.Bacia;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResumoFaturamentoPpp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	/** nullable persistent field */
	private Integer anoMesReferencia;
	
	/** nullable persistent field */
	private BigDecimal valorItemFaturamento;

	/** nullable persistent field */
	private Short sequenciaTipoLancamento;

	/** nullable persistent field */
	private Short sequenciaItemTipoLancamento;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private LancamentoTipo lancamentoTipo;
	
	private LancamentoItem lancamentoItem;
	
	private LancamentoItemContabil lancamentoItemContabil;
	
	private Localidade localidade;
	
	private Categoria categoria;
	
	private GerenciaRegional gerenciaRegional;
	
	private UnidadeNegocio unidadeNegocio;
	
	private SistemaEsgoto sistemaEsgoto;
	
	private Bacia bacia;
	
	private SubSistemaEsgoto subsistemaEsgoto;
	
	public ResumoFaturamentoPpp(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public BigDecimal getValorItemFaturamento() {
		return valorItemFaturamento;
	}

	public void setValorItemFaturamento(BigDecimal valorItemFaturamento) {
		this.valorItemFaturamento = valorItemFaturamento;
	}

	public Short getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}

	public Short getSequenciaItemTipoLancamento() {
		return sequenciaItemTipoLancamento;
	}

	public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public SistemaEsgoto getSistemaEsgoto() {
		return sistemaEsgoto;
	}

	public void setSistemaEsgoto(SistemaEsgoto sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}

	public Bacia getBacia() {
		return bacia;
	}

	public void setBacia(Bacia bacia) {
		this.bacia = bacia;
	}

	public SubSistemaEsgoto getSubsistemaEsgoto() {
		return subsistemaEsgoto;
	}

	public void setSubsistemaEsgoto(SubSistemaEsgoto subsistemaEsgoto) {
		this.subsistemaEsgoto = subsistemaEsgoto;
	}
	
}
