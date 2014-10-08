package gcom.cadastro.imovel;

import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ImovelFoto extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	private FotoSituacaoOrdemServico fotoSituacao;
	private byte[] fotoImovel;
	private Date ultimaAlteracao;
	private String observacaoFoto;
	
	public ImovelFoto(){

	}
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * @param id
	 * @param os
	 * @param descricaoFoto
	 * @param foto
	 */
	public ImovelFoto(Integer id, Imovel im, byte[] foto){
		this.id = id;
		this.imovel = im;
		this.fotoImovel = foto;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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

	public String getObservacaoFoto() {
		return observacaoFoto;
	}

	public void setObservacaoFoto(String observacaoFoto) {
		this.observacaoFoto = observacaoFoto;
	}
	
	
}