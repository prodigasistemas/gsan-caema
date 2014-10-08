package gcom.cobranca;

import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.interceptor.ObjetoGcom;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ComandoEmpresaCobrancaContaAcaoCobrancaPK extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer comandoEmpresaCobrancaContaId;
	
	private Integer acaoCobrancaId;
	


	public ComandoEmpresaCobrancaContaAcaoCobrancaPK() {
		super();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "acaoCobrancaId";
		return retorno;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getAcaoCobrancaId() {
		return acaoCobrancaId;
	}

	public void setAcaoCobrancaId(Integer acaoCobrancaId) {
		this.acaoCobrancaId = acaoCobrancaId;
	}

	
	
}
