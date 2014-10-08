package gcom.atendimentopublico;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Date;
import java.io.Serializable;

public class UnidadeRepavimentadoraMunicipio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descricaoAgrupamento;

	private Short indicadorUso;

	private UnidadeOrganizacional unidadeRepavimentadora;

	private Municipio municipio;

	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoAgrupamento() {
		return descricaoAgrupamento;
	}

	public void setDescricaoAgrupamento(String descricaoAgrupamento) {
		this.descricaoAgrupamento = descricaoAgrupamento;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
