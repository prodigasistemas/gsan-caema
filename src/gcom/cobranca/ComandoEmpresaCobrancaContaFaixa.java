package gcom.cobranca;

import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ComandoEmpresaCobrancaContaFaixa extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private ComandoEmpresaCobrancaContaFaixaPK comp_id;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private EmpresaCobrancaFaixa empresaCobrancaFaixa;
	
	private BigDecimal percentualInformadoFaixa;
	
	private Date ultimaAlteracao;


	public ComandoEmpresaCobrancaContaFaixa() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroComandoEmpresaCobrancaContaFaixa filtroComandoEmpresaCobrancaContaFaixa = new FiltroComandoEmpresaCobrancaContaFaixa();
		
		filtroComandoEmpresaCobrancaContaFaixa.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroComandoEmpresaCobrancaContaFaixa.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta");
		filtroComandoEmpresaCobrancaContaFaixa.adicionarCaminhoParaCarregamentoEntidade("empresaCobrancaFaixa");

		
		filtroComandoEmpresaCobrancaContaFaixa.adicionarParametro(
						new ParametroSimples(FiltroComandoEmpresaCobrancaContaFaixa.COMANDO_EMPRESA_COBRANCA_CONTA_ID, comandoEmpresaCobrancaConta.getId()));
		
		filtroComandoEmpresaCobrancaContaFaixa.adicionarParametro(
				new ParametroSimples(FiltroComandoEmpresaCobrancaContaFaixa.EMPR_COB_FAIXA_ID, empresaCobrancaFaixa.getId()));
		
		return filtroComandoEmpresaCobrancaContaFaixa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getPercentualInformadoFaixa() {
		return percentualInformadoFaixa;
	}

	public void setPercentualInformadoFaixa(BigDecimal percentualInformadoFaixa) {
		this.percentualInformadoFaixa = percentualInformadoFaixa;
	}

	public ComandoEmpresaCobrancaContaFaixaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaFaixaPK comp_id) {
		this.comp_id = comp_id;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public EmpresaCobrancaFaixa getEmpresaCobrancaFaixa() {
		return empresaCobrancaFaixa;
	}

	public void setEmpresaCobrancaFaixa(EmpresaCobrancaFaixa empresaCobrancaFaixa) {
		this.empresaCobrancaFaixa = empresaCobrancaFaixa;
	}

	
	
}
