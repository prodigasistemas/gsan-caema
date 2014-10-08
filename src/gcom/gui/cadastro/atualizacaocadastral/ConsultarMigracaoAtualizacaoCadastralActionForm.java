package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1326] Consultar Migração dos Setores/Quadra Para Atualizacao Cadastral
 * 
 * @author Davi Menezes
 * @date 25/04/2012
 *
 */
public class ConsultarMigracaoAtualizacaoCadastralActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String localidade;
	private String nomeLocalidade;
	
	private String setorNaoMigrado;
	private String quadraNaoMigrada;
	private String imovelNaoMigrado;
	
	private String setorMigrado;
	private String quadraMigrada;
	private String imovelMigrado;
	
	private String setorRetornado;
	private String quadraRetornada;
	private String imovelRetornado;
	
	private String totalImoveisNaoMigrados;
	private String totalImoveisMigrados;
	private String totalImoveisRetornados;
	
	private String [] idsQuadrasNaoMigradas;
	private String [] idsQuadrasMigradas;
	private String [] idsQuadrasRetornadas;
	
	public void reset(){
		this.idEmpresa = "-1";
		this.localidade = "";
		this.nomeLocalidade = "";
		this.setorNaoMigrado = "-1";
		this.quadraNaoMigrada = "-1";
		this.imovelNaoMigrado = "-1";
		this.setorMigrado = "-1";
		this.quadraMigrada = "-1";
		this.imovelMigrado = "-1";
		this.setorRetornado = "-1";
		this.quadraRetornada = "-1";
		this.imovelRetornado = "-1";
		this.totalImoveisNaoMigrados = "";
		this.totalImoveisMigrados = "";
		this.totalImoveisRetornados = "";
		this.idsQuadrasNaoMigradas = null;
		this.idsQuadrasMigradas = null;
		this.idsQuadrasRetornadas = null;
	}
	
	public void limpar(){
		this.localidade = "";
		this.nomeLocalidade = "";
		this.setorNaoMigrado = "-1";
		this.quadraNaoMigrada = "-1";
		this.imovelNaoMigrado = "-1";
		this.setorMigrado = "-1";
		this.quadraMigrada = "-1";
		this.imovelMigrado = "-1";
		this.setorRetornado = "-1";
		this.quadraRetornada = "-1";
		this.imovelRetornado = "-1";
		this.totalImoveisNaoMigrados = "";
		this.totalImoveisMigrados = "";
		this.totalImoveisRetornados = "";
		this.idsQuadrasNaoMigradas = null;
		this.idsQuadrasMigradas = null;
		this.idsQuadrasRetornadas = null;
	}
	
	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getTotalImoveisNaoMigrados() {
		return totalImoveisNaoMigrados;
	}

	public void setTotalImoveisNaoMigrados(String totalImoveisNaoMigrados) {
		this.totalImoveisNaoMigrados = totalImoveisNaoMigrados;
	}

	public String getSetorNaoMigrado() {
		return setorNaoMigrado;
	}

	public void setSetorNaoMigrado(String setorNaoMigrado) {
		this.setorNaoMigrado = setorNaoMigrado;
	}

	public String getQuadraNaoMigrada() {
		return quadraNaoMigrada;
	}

	public void setQuadraNaoMigrada(String quadraNaoMigrada) {
		this.quadraNaoMigrada = quadraNaoMigrada;
	}

	public String getImovelNaoMigrado() {
		return imovelNaoMigrado;
	}

	public void setImovelNaoMigrado(String imovelNaoMigrado) {
		this.imovelNaoMigrado = imovelNaoMigrado;
	}

	public String getSetorMigrado() {
		return setorMigrado;
	}

	public void setSetorMigrado(String setorMigrado) {
		this.setorMigrado = setorMigrado;
	}

	public String getQuadraMigrada() {
		return quadraMigrada;
	}

	public void setQuadraMigrada(String quadraMigrada) {
		this.quadraMigrada = quadraMigrada;
	}

	public String getImovelMigrado() {
		return imovelMigrado;
	}

	public void setImovelMigrado(String imovelMigrado) {
		this.imovelMigrado = imovelMigrado;
	}

	public String getTotalImoveisMigrados() {
		return totalImoveisMigrados;
	}

	public void setTotalImoveisMigrados(String totalImoveisMigrados) {
		this.totalImoveisMigrados = totalImoveisMigrados;
	}

	public String getSetorRetornado() {
		return setorRetornado;
	}

	public void setSetorRetornado(String setorRetornado) {
		this.setorRetornado = setorRetornado;
	}

	public String getQuadraRetornada() {
		return quadraRetornada;
	}

	public void setQuadraRetornada(String quadraRetornada) {
		this.quadraRetornada = quadraRetornada;
	}

	public String getImovelRetornado() {
		return imovelRetornado;
	}

	public void setImovelRetornado(String imovelRetornado) {
		this.imovelRetornado = imovelRetornado;
	}

	public String getTotalImoveisRetornados() {
		return totalImoveisRetornados;
	}

	public void setTotalImoveisRetornados(String totalImoveisRetornados) {
		this.totalImoveisRetornados = totalImoveisRetornados;
	}

	public String[] getIdsQuadrasNaoMigradas() {
		return idsQuadrasNaoMigradas;
	}

	public void setIdsQuadrasNaoMigradas(String[] idsQuadrasNaoMigradas) {
		this.idsQuadrasNaoMigradas = idsQuadrasNaoMigradas;
	}

	public String[] getIdsQuadrasMigradas() {
		return idsQuadrasMigradas;
	}

	public void setIdsQuadrasMigradas(String[] idsQuadrasMigradas) {
		this.idsQuadrasMigradas = idsQuadrasMigradas;
	}

	public String[] getIdsQuadrasRetornadas() {
		return idsQuadrasRetornadas;
	}

	public void setIdsQuadrasRetornadas(String[] idsQuadrasRetornadas) {
		this.idsQuadrasRetornadas = idsQuadrasRetornadas;
	}
	
}