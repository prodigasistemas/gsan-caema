package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

public class DadosContratoPPPHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private String indicadorTipoOperacao;
	
	private String idMunicipio;
	
	private String idLogradouro;
	
	private String idLocalidadeInicial;
	
	private String descricaoLocalidadeInicial;
	
	private String codigoSetorComercialInicial;
	
	private String descricaoSetorComercialInicial;
	
	private String numeroQuadraInicial;
	
	private String numeroRotaInicial;
	
	private String numeroSequencialRotaInicial;
	
	private String idLocalidadeFinal;
	
	private String descricaoLocalidadeFinal;
	
	private String codigoSetorComercialFinal;
	
	private String descricaoSetorComercialFinal;
	
	private String numeroQuadraFinal;
	
	private String numeroRotaFinal;
	
	private String numeroSequencialRotaFinal;
	
	private String idTipoSolicitacaoRA;
	
	private String idEspecificacao;
	
	private String dataLigacao;
	
	private Collection<Integer> colecaoIdImovel;
	
	private Usuario usuario;
	
	private LigacaoEsgoto ligacaoEsgoto;

	public String getIndicadorTipoOperacao() {
		return indicadorTipoOperacao;
	}

	public void setIndicadorTipoOperacao(String indicadorTipoOperacao) {
		this.indicadorTipoOperacao = indicadorTipoOperacao;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public String getNumeroRotaInicial() {
		return numeroRotaInicial;
	}

	public void setNumeroRotaInicial(String numeroRotaInicial) {
		this.numeroRotaInicial = numeroRotaInicial;
	}

	public String getNumeroSequencialRotaInicial() {
		return numeroSequencialRotaInicial;
	}

	public void setNumeroSequencialRotaInicial(String numeroSequencialRotaInicial) {
		this.numeroSequencialRotaInicial = numeroSequencialRotaInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public String getNumeroRotaFinal() {
		return numeroRotaFinal;
	}

	public void setNumeroRotaFinal(String numeroRotaFinal) {
		this.numeroRotaFinal = numeroRotaFinal;
	}

	public String getNumeroSequencialRotaFinal() {
		return numeroSequencialRotaFinal;
	}

	public void setNumeroSequencialRotaFinal(String numeroSequencialRotaFinal) {
		this.numeroSequencialRotaFinal = numeroSequencialRotaFinal;
	}

	public String getIdTipoSolicitacaoRA() {
		return idTipoSolicitacaoRA;
	}

	public void setIdTipoSolicitacaoRA(String idTipoSolicitacaoRA) {
		this.idTipoSolicitacaoRA = idTipoSolicitacaoRA;
	}

	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Collection<Integer> getColecaoIdImovel() {
		return colecaoIdImovel;
	}

	public void setColecaoIdImovel(Collection<Integer> colecaoIdImovel) {
		this.colecaoIdImovel = colecaoIdImovel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}
	
	
	
}
