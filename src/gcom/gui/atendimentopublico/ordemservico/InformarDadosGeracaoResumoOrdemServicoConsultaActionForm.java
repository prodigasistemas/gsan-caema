package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1558] - Filtrar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 19/09/2013
 *
 */
public class InformarDadosGeracaoResumoOrdemServicoConsultaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	
	private String[] servicoTipo;
	
	private String[] gerencialRegional;
	
	private String[] unidadeNegocio;
	
	private String eloPolo;
	
	private String descricaoEloPolo;
	
	private String localidade;
	
	private String descricaoLocalidade;
	
	private String idSetorComercial;
	
	private String setorComercial;
	
	private String descricaoSetorComercial;
	
	private String[] perfilImovel;
	
	private String[] situacaoLigacaoAgua;
	
	private String[] situacaoLigacaoEsgoto;
	
	private String[] categoria;
	
	private String[] esferaPoder;
	
	private String[] empresa;

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String[] getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(String[] servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public String[] getGerencialRegional() {
		return gerencialRegional;
	}

	public void setGerencialRegional(String[] gerencialRegional) {
		this.gerencialRegional = gerencialRegional;
	}

	public String[] getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String[] unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String[] getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String[] getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String[] situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String[] getCategoria() {
		return categoria;
	}

	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}

	public String[] getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String[] getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String[] empresa) {
		this.empresa = empresa;
	}
	
	public void reset(){
		this.mesAnoReferencia = "";
		this.servicoTipo = null;
		this.gerencialRegional = null;
		this.unidadeNegocio = null;
		this.eloPolo = "";
		this.descricaoEloPolo = "";
		this.localidade = "";
		this.descricaoLocalidade = "";
		this.idSetorComercial = "";
		this.setorComercial = "";
		this.descricaoSetorComercial = "";
		this.perfilImovel = null;
		this.situacaoLigacaoAgua = null;
		this.situacaoLigacaoEsgoto = null;
		this.categoria = null;
		this.esferaPoder = null;
		this.empresa = null;
	}
	
}
