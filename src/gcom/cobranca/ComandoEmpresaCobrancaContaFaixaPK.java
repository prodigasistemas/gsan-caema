package gcom.cobranca;

import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.interceptor.ObjetoGcom;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ComandoEmpresaCobrancaContaFaixaPK extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer comandoEmpresaCobrancaContaId;
	
	private Integer empresaCobrancaFaixaId;
	


	public ComandoEmpresaCobrancaContaFaixaPK() {
		super();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "empresaCobrancaFaixaId";
		return retorno;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getEmpresaCobrancaFaixaId() {
		return empresaCobrancaFaixaId;
	}

	public void setEmpresaCobrancaFaixaId(Integer empresaCobrancaFaixaId) {
		this.empresaCobrancaFaixaId = empresaCobrancaFaixaId;
	}

	
	
}
