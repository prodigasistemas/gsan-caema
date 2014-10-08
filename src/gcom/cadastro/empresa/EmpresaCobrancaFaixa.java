package gcom.cadastro.empresa;

import java.math.BigDecimal;
import java.util.Date;

import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoItem;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

@ControleAlteracao()
public class EmpresaCobrancaFaixa extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_INSERIR_EMPRESA = 1304;
	public static final int ATRIBUTOS_ATUALIZAR_EMPRESA = 1311;
	public static final int ATRIBUTOS_REMOVER_EMPRESA = 1417;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Integer id;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private BigDecimal percentualFaixa;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private BigDecimal percentualImovelFaixa;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Integer numeroMinimoContasFaixa;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Date ultimaAlteracao;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private String descricao;

	@ControleAlteracao(value=FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA, 
			funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private EmpresaContratoCobranca empresaContratoCobranca;
	
	// utilizado no processo 152 Gerar Movimento de contas em cobranca por empresa
	private Integer numeroMaximoImoveisFaixa;

	
	public Integer getNumeroMaximoImoveisFaixa() {
		return numeroMaximoImoveisFaixa;
	}

	public void setNumeroMaximoImoveisFaixa(Integer numeroMaximoImoveisFaixa) {
		this.numeroMaximoImoveisFaixa = numeroMaximoImoveisFaixa;
	}

	public EmpresaCobrancaFaixa() {
		super();
	}

	public EmpresaCobrancaFaixa(Integer id, BigDecimal percentualFaixa, BigDecimal percentualImovelFaixa, Integer numeroMinimoContasFaixa, Date ultimaAlteracao, EmpresaContratoCobranca empresaContratoCobranca) {
		super();
		this.id = id;
		this.percentualFaixa = percentualFaixa;
		this.percentualImovelFaixa = percentualImovelFaixa;
		this.numeroMinimoContasFaixa = numeroMinimoContasFaixa;
		this.ultimaAlteracao = ultimaAlteracao;
		this.empresaContratoCobranca = empresaContratoCobranca;
	}

	public EmpresaContratoCobranca getEmpresaContratoCobranca() {
		return empresaContratoCobranca;
	}

	public void setEmpresaContratoCobranca(
			EmpresaContratoCobranca empresaContratoCobranca) {
		this.empresaContratoCobranca = empresaContratoCobranca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroMinimoContasFaixa() {
		return numeroMinimoContasFaixa;
	}

	public void setNumeroMinimoContasFaixa(Integer numeroMinimoContasFaixa) {
		this.numeroMinimoContasFaixa = numeroMinimoContasFaixa;
	}

	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}

	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}
	
	public BigDecimal getPercentualImovelFaixa() {
		return percentualImovelFaixa;
	}

	public void setPercentualImovelFaixa(BigDecimal percentualImovelFaixa) {
		this.percentualImovelFaixa = percentualImovelFaixa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();

		filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobrancaFaixa.ID, this.getId()));
		return filtroEmpresaCobrancaFaixa;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {"descricao",
				"percentualFaixa",
				"numeroMinimoContasFaixa",
				"percentualImovelFaixa"};
		return atributos;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []atributos = {"Descricao",
				"Perc Faixa",
				"Num Minimo Contas Faixa",
				"Perc Imovel Faixa"};
		return atributos;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtro;
	}
}
