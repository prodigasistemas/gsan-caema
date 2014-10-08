package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ComandoEmpresaCobrancaContaAcaoCobranca extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private ComandoEmpresaCobrancaContaAcaoCobrancaPK comp_id;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private CobrancaAcao acaoCobranca;
	
	private Date ultimaAlteracao;


	public ComandoEmpresaCobrancaContaAcaoCobranca() {

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

	public ComandoEmpresaCobrancaContaAcaoCobrancaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaAcaoCobrancaPK comp_id) {
		this.comp_id = comp_id;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public CobrancaAcao getAcaoCobranca() {
		return acaoCobranca;
	}

	public void setAcaoCobranca(CobrancaAcao acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	
	
}
