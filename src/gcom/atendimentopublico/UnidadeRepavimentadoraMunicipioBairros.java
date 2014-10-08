package gcom.atendimentopublico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

public class UnidadeRepavimentadoraMunicipioBairros extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UnidadeRepavimentadoraMunicipioBairrosPK comp_id;

	private Date ultimaAlteracao;

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeRepavimentadoraMunicipioBairrosPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(UnidadeRepavimentadoraMunicipioBairrosPK comp_id) {
		this.comp_id = comp_id;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroUnidadeRepavimentadoraMunicipioBairros filtro = new FiltroUnidadeRepavimentadoraMunicipioBairros();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipioBairros.ID_UNIDADE_REPAVIMENTADORA,
				this.getComp_id().getUnidadeRepavimentadoraMunicipioId().getId()));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	

}
