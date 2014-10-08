package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ComandoEmpresaCobrancaContaCobrancaSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private ComandoEmpresaCobrancaContaCobrancaSituacaoPK comp_id;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private CobrancaSituacao cobrancaSituacao;
	
	private Date ultimaAlteracao;

	public ComandoEmpresaCobrancaContaCobrancaSituacao() {

	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}   

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ComandoEmpresaCobrancaContaCobrancaSituacaoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaCobrancaSituacaoPK comp_id) {
		this.comp_id = comp_id;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}
	
}
