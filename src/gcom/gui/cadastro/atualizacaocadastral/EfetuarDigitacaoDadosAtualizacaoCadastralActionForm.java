package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC9999] Efetuar Digitacao de Dados para Atualização Cadastral
 * 
 * @since 13/07/2012
 * @author Rafael Pinto
 *
 */
public class EfetuarDigitacaoDadosAtualizacaoCadastralActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String idMunicipio;
	private String nomeMunicipio;
	private String bairro;
	private String logradouroSelecao;
	private String nomeLogradouroSelecao;
	private String quantidadeDocumentos;
	
	private String[] logradouroSelecionado;

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouroSelecao() {
		return logradouroSelecao;
	}

	public void setLogradouroSelecao(String logradouroSelecao) {
		this.logradouroSelecao = logradouroSelecao;
	}

	public String[] getLogradouroSelecionado() {
		return logradouroSelecionado;
	}

	public void setLogradouroSelecionado(String[] logradouroSelecionado) {
		this.logradouroSelecionado = logradouroSelecionado;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public String getNomeLogradouroSelecao() {
		return nomeLogradouroSelecao;
	}

	public void setNomeLogradouroSelecao(String nomeLogradouroSelecao) {
		this.nomeLogradouroSelecao = nomeLogradouroSelecao;
	}

	public void reset(){
		this.idLocalidade = null;
		this.codigoSetorComercial = null;
		this.numeroQuadra = null;
		this.idMunicipio = null;
		this.nomeMunicipio = null;
		this.bairro = null;
		this.logradouroSelecao = null;
		this.nomeLogradouroSelecao = null;
		this.quantidadeDocumentos = null;
		this.logradouroSelecionado = null;
	}

}