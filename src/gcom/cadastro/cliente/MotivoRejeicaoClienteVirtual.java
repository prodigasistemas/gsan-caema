package gcom.cadastro.cliente;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class MotivoRejeicaoClienteVirtual extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Short indicadorUso;
	private String descricaoMotivo;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}

	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroMotivoRejeicaoClienteVirtual filtro = new FiltroMotivoRejeicaoClienteVirtual();
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoRejeicaoClienteVirtual.ID, this.getId()));
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoMotivo();
	}

}
