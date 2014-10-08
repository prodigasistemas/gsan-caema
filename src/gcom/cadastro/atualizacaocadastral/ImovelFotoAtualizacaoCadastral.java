package gcom.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ImovelFotoAtualizacaoCadastral extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;
	private FotoSituacaoOrdemServico fotoSituacao;
	private byte[] fotoImovel;
	private Date ultimaAlteracao;
	
	public ImovelFotoAtualizacaoCadastral(){
		
	}
	
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

	public FotoSituacaoOrdemServico getFotoSituacao() {
		return fotoSituacao;
	}

	public void setFotoSituacao(FotoSituacaoOrdemServico fotoSituacao) {
		this.fotoSituacao = fotoSituacao;
	}
	
	public byte[] getFotoImovel() {
		return fotoImovel;
	}

	public void setFotoImovel(byte[] fotoImovel) {
		this.fotoImovel = fotoImovel;
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