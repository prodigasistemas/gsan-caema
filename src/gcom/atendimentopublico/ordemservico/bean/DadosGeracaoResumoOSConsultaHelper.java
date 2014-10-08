package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC 1558] - Filtrar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 19/09/2013
 *
 */
public class DadosGeracaoResumoOSConsultaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer anoMesReferencia;
	
	private Integer eloPolo;
	
	private Integer idLocalidade;
	
	private Integer codigoSetorComercial;
	
	private Collection<Integer> colecaoServicoTipo;
	
	private Collection<Integer> colecaoGerenciaRegional;
	
	private Collection<Integer> colecaoUnidadeNegocio;
	
	private Collection<Integer> colecaoImovelPerfil;
	
	private Collection<Integer> colecaoLigacaoAguaSituacao;
	
	private Collection<Integer> colecaoLigacaoEsgotoSituacao;
	
	private Collection<Integer> colecaoCategorias;
	
	private Collection<Integer> colecaoEsferaPoder;
	
	private Collection<Integer> colecaoEmpresa;

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Integer eloPolo) {
		this.eloPolo = eloPolo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Collection<Integer> getColecaoServicoTipo() {
		return colecaoServicoTipo;
	}

	public void setColecaoServicoTipo(Collection<Integer> colecaoServicoTipo) {
		this.colecaoServicoTipo = colecaoServicoTipo;
	}

	public Collection<Integer> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(
			Collection<Integer> colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	public Collection<Integer> getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	public void setColecaoUnidadeNegocio(Collection<Integer> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	public Collection<Integer> getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	public void setColecaoImovelPerfil(Collection<Integer> colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<Integer> getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(
			Collection<Integer> colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

	public Collection<Integer> getColecaoLigacaoEsgotoSituacao() {
		return colecaoLigacaoEsgotoSituacao;
	}

	public void setColecaoLigacaoEsgotoSituacao(
			Collection<Integer> colecaoLigacaoEsgotoSituacao) {
		this.colecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao;
	}

	public Collection<Integer> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Integer> colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Collection<Integer> getColecaoEmpresa() {
		return colecaoEmpresa;
	}

	public void setColecaoEmpresa(Collection<Integer> colecaoEmpresa) {
		this.colecaoEmpresa = colecaoEmpresa;
	}
	
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}

}
