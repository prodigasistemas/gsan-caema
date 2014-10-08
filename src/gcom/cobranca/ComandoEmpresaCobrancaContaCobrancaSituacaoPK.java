package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class ComandoEmpresaCobrancaContaCobrancaSituacaoPK extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	private Integer comandoEmpresaCobrancaContaId;
	
	private Integer cobrancaSituacaoId;

	public ComandoEmpresaCobrancaContaCobrancaSituacaoPK() {
		super();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "cobrancaSituacaoId";
		return retorno;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getCobrancaSituacaoId() {
		return cobrancaSituacaoId;
	}

	public void setCobrancaSituacaoId(Integer cobrancaSituacaoId) {
		this.cobrancaSituacaoId = cobrancaSituacaoId;
	}

}
