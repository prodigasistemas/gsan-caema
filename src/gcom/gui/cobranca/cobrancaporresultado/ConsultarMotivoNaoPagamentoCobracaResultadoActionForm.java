
 
package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cobranca.ComandoEmpresaCobrancaConta;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Diego Maciel
 * @date 06/07/2006
 */
public class ConsultarMotivoNaoPagamentoCobracaResultadoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;

	private String matriculaImovel;

	private String inscricaoImovel;

	private String clienteUsuario;

	private String cpfCnpjCliente;

	private String situacaoLigacaoAgua;

	private String situacaoLigacaoEsgoto;

    private String alteracaoValor;
    
    private String periodoReferenciaArrecardacao;
    
    private String referenciaInicial;
	
	private String referenciaFinal;
	
	private String codigoImovel ;
    
    private String[] idsComandos;
    
    private Collection<ComandoEmpresaCobrancaConta> comandos;

	
	public String getPeriodoReferenciaArrecardacao() {
		return periodoReferenciaArrecardacao;
	}

	public void setPeriodoReferenciaArrecardacao(
			String periodoReferenciaArrecardacao) {
		this.periodoReferenciaArrecardacao = periodoReferenciaArrecardacao;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}
	
   

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @param clienteUsuario
	 *            O clienteUsuario a ser setado.
	 */
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	/**
	 * @return Retorna o campo cpfCnpjCliente.
	 */
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	/**
	 * @param cpfCnpjCliente
	 *            O cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel
	 *            O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel
	 *            O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua
	 *            O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto
	 *            O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public Collection<ComandoEmpresaCobrancaConta> getComandos() {
		return comandos;
	}

	public void setComandos(Collection<ComandoEmpresaCobrancaConta> comandos) {
		this.comandos = comandos;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String[] getIdsComandos() {
		return idsComandos;
	}

	public void setIdsComandos(String[] idsComandos) {
		this.idsComandos = idsComandos;
	}

	
	
	
}
