package gcom.cadastro;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1386] - Processar Arquivo de Resetorização dos Imóveis
 * 
 * @author Davi Menezes
 * @date 30/10/2012
 *
 */
public class ImovelInscricaoResetorizada extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer codigoSetorComercial;
	
	private Integer numeroQuadra;
	
	private Integer numeroLote;
	
	private Integer numeroSubLote;
	
	private Short indicadorAtualizacao;
	
	private Date ultimaAlteracao;
	
	private Imovel imovel;
	
	private Localidade localidade;
	
	private OcorrenciaResetorizacao ocorrenciaResetorizacao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(Integer numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Short getIndicadorAtualizacao() {
		return indicadorAtualizacao;
	}

	public void setIndicadorAtualizacao(Short indicadorAtualizacao) {
		this.indicadorAtualizacao = indicadorAtualizacao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public OcorrenciaResetorizacao getOcorrenciaResetorizacao() {
		return ocorrenciaResetorizacao;
	}

	public void setOcorrenciaResetorizacao(OcorrenciaResetorizacao ocorrenciaResetorizacao) {
		this.ocorrenciaResetorizacao = ocorrenciaResetorizacao;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroImovelInscricaoResetorizada filtroImovelInscricaoResetorizada = new FiltroImovelInscricaoResetorizada();
		filtroImovelInscricaoResetorizada.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID, this.getId()));
		filtroImovelInscricaoResetorizada.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.IMOVEL);
		filtroImovelInscricaoResetorizada.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.LOCALIDADE);
		filtroImovelInscricaoResetorizada.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO);
		
		return filtroImovelInscricaoResetorizada;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}
