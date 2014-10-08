package gcom.cobranca;

import gcom.cadastro.MotivoRetiradaCobranca;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaContaProporcionalidade extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Empresa empresa;
	
	/** nullable persistent field */
	private ContaGeral contaGeral;
	
	/** nullable persistent field */
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

	/** nullable persistent field */
	private BigDecimal valorOriginalConta;
	
	/** nullable persistent field */
	private BigDecimal percentualEmpresaConta;
	
	/** nullable persistent field */
	private Short indicadorPagamentoValido;
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	
	private Imovel imovel;
	
	private int anoMesReferenciaConta;
	
	private Date dataEnvioConta;
	
	private Date dataRetiradaConta;	
	
	private MotivoRetiradaCobranca motivoRetirada;
	
	private SetorComercial setorComercial;
	
	private Integer quantidadeVezesAssociadoComandos;

	public Integer getQuantidadeVezesAssociadoComandos() {
		return quantidadeVezesAssociadoComandos;
	}

	public void setQuantidadeVezesAssociadoComandos(Integer quantidadeVezesAssociadoComandos) {
		this.quantidadeVezesAssociadoComandos = quantidadeVezesAssociadoComandos;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public BigDecimal getValorOriginalConta() {
		return valorOriginalConta;
	}

	public void setValorOriginalConta(BigDecimal valorOriginalConta) {
		this.valorOriginalConta = valorOriginalConta;
	}

	public BigDecimal getPercentualEmpresaConta() {
		return percentualEmpresaConta;
	}

	public void setPercentualEmpresaConta(BigDecimal percentualEmpresaConta) {
		this.percentualEmpresaConta = percentualEmpresaConta;
	}

	public Short getIndicadorPagamentoValido() {
		return indicadorPagamentoValido;
	}

	public void setIndicadorPagamentoValido(Short indicadorPagamentoValido) {
		this.indicadorPagamentoValido = indicadorPagamentoValido;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public int getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	public void setAnoMesReferenciaConta(int anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public Date getDataEnvioConta() {
		return dataEnvioConta;
	}

	public void setDataEnvioConta(Date dataEnvioConta) {
		this.dataEnvioConta = dataEnvioConta;
	}

	public Date getDataRetiradaConta() {
		return dataRetiradaConta;
	}

	public void setDataRetiradaConta(Date dataRetiradaConta) {
		this.dataRetiradaConta = dataRetiradaConta;
	}

	public MotivoRetiradaCobranca getMotivoRetirada() {
		return motivoRetirada;
	}

	public void setMotivoRetirada(MotivoRetiradaCobranca motivoRetirada) {
		this.motivoRetirada = motivoRetirada;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

}
