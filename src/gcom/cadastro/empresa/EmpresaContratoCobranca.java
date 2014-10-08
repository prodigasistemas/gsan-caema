package gcom.cadastro.empresa;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class EmpresaContratoCobranca extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_INSERIR_EMPRESA = 1304;
	public static final int ATRIBUTOS_ATUALIZAR_EMPRESA = 1311;
	public static final int ATRIBUTOS_REMOVER_EMPRESA = 1417;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Integer id;

	@ControleAlteracao(value=FiltroEmpresaContratoCobranca.EMPRESA, 
			funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Empresa empresa;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private BigDecimal percentualContratoCobranca;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Date dataInicioContrato;

	/** nullable persistent field */
	@SuppressWarnings("unused")
	private Date ultimaAlteracao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Date dataFinalContrato;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_EMPRESA, ATRIBUTOS_ATUALIZAR_EMPRESA, ATRIBUTOS_REMOVER_EMPRESA})
	private Short codigoLayoutTxt;
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataFinalContrato() {
		return dataFinalContrato;
	}

	public void setDataFinalContrato(Date dataFinalContrato) {
		this.dataFinalContrato = dataFinalContrato;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualContratoCobranca() {
		return percentualContratoCobranca;
	}

	public void setPercentualContratoCobranca(
			BigDecimal percentualContratoCobranca) {
		this.percentualContratoCobranca = percentualContratoCobranca;
	}

	public EmpresaContratoCobranca(Integer id, Empresa empresa,
			BigDecimal percentualContratoCobranca, Date dataInicioContrato,
			Date ultimaAlteracao, Date dataFinalContrato) {
		super();

		this.id = id;
		this.empresa = empresa;
		this.percentualContratoCobranca = percentualContratoCobranca;
		this.dataInicioContrato = dataInicioContrato;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataFinalContrato = dataFinalContrato;
	}

	public EmpresaContratoCobranca() {

	}

	public Short getCodigoLayoutTxt() {
		return codigoLayoutTxt;
	}

	public void setCodigoLayoutTxt(Short codigoLayoutTxt) {
		this.codigoLayoutTxt = codigoLayoutTxt;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

		filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
				FiltroEmpresaContratoCobranca.ID, this.getId()));
		return filtroEmpresaCobranca;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"dataInicioContrato",
				"dataFinalContrato",
				"percentualContratoCobranca",
				"codigoLayoutTxt"};
		return labels;
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Dt Inicio Contrato",
				"Dt Final Contrato",
				"Perc Cobranca",
				"Cod Layout"};
		return labels;		
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtro;
	}
	
}
