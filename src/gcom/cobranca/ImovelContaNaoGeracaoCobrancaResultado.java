package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.util.Date;

public class ImovelContaNaoGeracaoCobrancaResultado implements Serializable, Comparable<ImovelContaNaoGeracaoCobrancaResultado>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private MotivoNaoGeracaoCobrancaResultado motivoNaoGeracaoCobrancaResultado;
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	private Imovel imovel;
	private Conta conta;
	private Date dataNaoGeracao;
	private Date ultimaAlteracao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MotivoNaoGeracaoCobrancaResultado getMotivoNaoGeracaoCobrancaResultado() {
		return motivoNaoGeracaoCobrancaResultado;
	}
	public void setMotivoNaoGeracaoCobrancaResultado(MotivoNaoGeracaoCobrancaResultado motivoNaoGeracaoCobrancaResultado) {
		this.motivoNaoGeracaoCobrancaResultado = motivoNaoGeracaoCobrancaResultado;
	}
	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}
	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Conta getConta(){
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataNaoGeracao() {
		return dataNaoGeracao;
	}
	public void setDataNaoGeracao(Date dataNaoGeracao) {
		this.dataNaoGeracao = dataNaoGeracao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public int compareTo(ImovelContaNaoGeracaoCobrancaResultado o) {				
		Integer im1 = imovel.getId();
		Integer im2 = o.getImovel().getId();		
		return im1.compareTo(im2);
	}
	
}
