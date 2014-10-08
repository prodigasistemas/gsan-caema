package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Date;

public class ServicoRepavimentadora extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private Short indicadorUso;
	
	private BigDecimal valorServico;
	
	private Date ultimaAlteracao;
	
	private UnidadeOrganizacional unidadeRepavimentadora;
	
	private MaterialUnidade materialUnidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public MaterialUnidade getMaterialUnidade() {
		return materialUnidade;
	}

	public void setMaterialUnidade(MaterialUnidade materialUnidade) {
		this.materialUnidade = materialUnidade;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}
