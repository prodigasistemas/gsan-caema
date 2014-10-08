package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ImovelOcorrenciaAtualizacaoCadastral extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;
	private CadastroOcorrencia cadastroOcorrencia;
	private Date ultimaAlteracao;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void setImovelAtualizacaoCadastral(
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
	}

	public CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

}