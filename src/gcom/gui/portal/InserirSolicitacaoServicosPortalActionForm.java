package gcom.gui.portal;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * <p>
 * Classe Responsável pelo formulário de cadastro para solicitação de serviços
 * na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 28/06/2011
 */
public class InserirSolicitacaoServicosPortalActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 6585396434051685339L;

	private String solicitacaoTipo;

	private String especificacao;

	private String nomeSolicitante;

	private String telefoneContato;

	private String email;

	private String pontoReferencia;

	private String observacoes;

	private String matricula;
	
	private String anexarArquivo;
	
	private String indicadorAnexoObrigatorio;
	
	private String controle;
	
	private String localOcorrencia;
	
	private String nomeUsuario;
	
	private String municipio;
	
	private String bairro;
	
	private String pavimentoRua;
	
	private String pavimentoCalcada;
	
	private String cpfCnpj;	
	
	private FormFile arquivo1;
	private FormFile arquivo2;
	private FormFile arquivo3;
	private FormFile arquivo4;
	
	public void reset() {
		this.email = null;
		this.telefoneContato = null;
		this.especificacao = null;
		this.solicitacaoTipo = null;
		this.nomeSolicitante = null;
		this.observacoes = null;
		this.pontoReferencia = null;
		this.anexarArquivo = null;
		this.indicadorAnexoObrigatorio = null;
		this.localOcorrencia = null;
		this.nomeUsuario = null;
		this.municipio = null;
		this.bairro = null;
		this.pavimentoRua = null;
		this.pavimentoCalcada = null;
		this.cpfCnpj = null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	public void setSolicitacaoTipo(String solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAnexarArquivo() {
		return anexarArquivo;
	}

	public void setAnexarArquivo(String anexarArquivo) {
		this.anexarArquivo = anexarArquivo;
	}

	public String getIndicadorAnexoObrigatorio() {
		return indicadorAnexoObrigatorio;
	}

	public void setIndicadorAnexoObrigatorio(String indicadorAnexoObrigatorio) {
		this.indicadorAnexoObrigatorio = indicadorAnexoObrigatorio;
	}

	public FormFile getArquivo1() {
		return arquivo1;
	}

	public void setArquivo1(FormFile arquivo1) {
		this.arquivo1 = arquivo1;
	}

	public FormFile getArquivo2() {
		return arquivo2;
	}

	public void setArquivo2(FormFile arquivo2) {
		this.arquivo2 = arquivo2;
	}

	public FormFile getArquivo3() {
		return arquivo3;
	}

	public void setArquivo3(FormFile arquivo3) {
		this.arquivo3 = arquivo3;
	}

	public FormFile getArquivo4() {
		return arquivo4;
	}

	public void setArquivo4(FormFile arquivo4) {
		this.arquivo4 = arquivo4;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public String getLocalOcorrencia() {
		return localOcorrencia;
	}

	public void setLocalOcorrencia(String localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
}