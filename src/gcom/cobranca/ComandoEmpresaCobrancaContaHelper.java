package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.util.Collection;

public class ComandoEmpresaCobrancaContaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private Collection<UnidadeNegocio> colecaoUnidadeNegocio;
	
	private Collection<GerenciaRegional> colecaoGerenciaRegional;
	
	private Collection<ImovelPerfil> colecaoImovelPerfil;

	private Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao;
	
	private Collection<SetorComercial> colecaoSetoresComponent;
	
	private Collection<CobrancaAcao> colecaoCobrancaAcao;
	
	private Collection<CobrancaAcao> colecaoAcoesCobrancaNaoGeracao;
	
	private Collection<CobrancaSituacao> colecaoCobrancaSituacao;
	
	private Boolean indicadorTotalDebito;

	public ComandoEmpresaCobrancaContaHelper() {
		super();
	}

	public ComandoEmpresaCobrancaContaHelper(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Collection<UnidadeNegocio> colecaoUnidadeNegocio, Collection<GerenciaRegional> colecaoGerenciaRegional, Collection<ImovelPerfil> colecaoImovelPerfil) {
		super();
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<GerenciaRegional> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(
			Collection<GerenciaRegional> colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	public Collection<ImovelPerfil> getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	public void setColecaoImovelPerfil(Collection<ImovelPerfil> colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	public void setColecaoUnidadeNegocio(
			Collection<UnidadeNegocio> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public Collection<LigacaoAguaSituacao> getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(
			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

	public Boolean getIndicadorTotalDebito() {
		return indicadorTotalDebito;
	}

	public void setIndicadorTotalDebito(Boolean indicadorTotalDebito) {
		this.indicadorTotalDebito = indicadorTotalDebito;
	}

	public Collection<SetorComercial> getColecaoSetoresComponent() {
		return colecaoSetoresComponent;
	}

	public void setColecaoSetoresComponent(Collection<SetorComercial> colecaoSetoresComponent) {
		this.colecaoSetoresComponent = colecaoSetoresComponent;
	}

	public Collection<CobrancaAcao> getColecaoAcoesCobrancaNaoGeracao() {
		return colecaoAcoesCobrancaNaoGeracao;
	}

	public void setColecaoAcoesCobrancaNaoGeracao(
			Collection<CobrancaAcao> colecaoAcoesCobrancaNaoGeracao) {
		this.colecaoAcoesCobrancaNaoGeracao = colecaoAcoesCobrancaNaoGeracao;
	}
	
	
	public Collection<CobrancaAcao> getColecaoCobrancaAcao() {
		return colecaoCobrancaAcao;
	}

	public void setColecaoCobrancaAcao(Collection<CobrancaAcao> colecaoCobrancaAcao) {
		this.colecaoCobrancaAcao = colecaoCobrancaAcao;
	}

	public Collection<CobrancaSituacao> getColecaoCobrancaSituacao() {
		return colecaoCobrancaSituacao;
	}

	public void setColecaoCobrancaSituacao(Collection<CobrancaSituacao> colecaoCobrancaSituacao) {
		this.colecaoCobrancaSituacao = colecaoCobrancaSituacao;
	}	
	
}
