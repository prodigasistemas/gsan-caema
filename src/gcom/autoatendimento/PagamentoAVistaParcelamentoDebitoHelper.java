package gcom.autoatendimento;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1555] - Consultar Pagamento a Vista Parcelamento de Débitos Webservice
 * 
 * @author Arthur Carvalho
 * @date 05/09/2013
 * @param matricula
 */
public class PagamentoAVistaParcelamentoDebitoHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String cnpj;
	
	private String escritorio;
	
	private String rota;
	
	private String inscricao;
	
	private String nomeUsuario;
	
	private String cpfcnpjUsuario;
	
	private String matricula;
	
	private String endereco;
	
	private String sequencial;
	
	private ArrayList<EconomiaHelper> economias;
	
	private String tipoConsumo;
	
	private String dataEmissao;
	
	private String naoReceberApos;
	
	private ArrayList<ContaPagamentoAVistaHelper> contas;
	
	private ArrayList<DebitoCreditoPagamentoAVistaHelper> debitosCreditos;
	
	private String debitoOriginal;
	
	private String servicosAtualizacao;
	
	private String descontosCreditos;
	
	private String valorAPagar;
	
	private String emitidoPor;
	
	private String codigoBarras;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(String escritorio) {
		this.escritorio = escritorio;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfcnpjUsuario() {
		return cpfcnpjUsuario;
	}

	public void setCpfcnpjUsuario(String cpfcnpjUsuario) {
		this.cpfcnpjUsuario = cpfcnpjUsuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getNaoReceberApos() {
		return naoReceberApos;
	}

	public void setNaoReceberApos(String naoReceberApos) {
		this.naoReceberApos = naoReceberApos;
	}


	public ArrayList<ContaPagamentoAVistaHelper> getContas() {
		return contas;
	}

	public void setContas(ArrayList<ContaPagamentoAVistaHelper> contas) {
		this.contas = contas;
	}

	public ArrayList<DebitoCreditoPagamentoAVistaHelper> getDebitosCreditos() {
		return debitosCreditos;
	}

	public void setDebitosCreditos(
			ArrayList<DebitoCreditoPagamentoAVistaHelper> debitosCreditos) {
		this.debitosCreditos = debitosCreditos;
	}

	public String getDebitoOriginal() {
		return debitoOriginal;
	}

	public void setDebitoOriginal(String debitoOriginal) {
		this.debitoOriginal = debitoOriginal;
	}

	public String getServicosAtualizacao() {
		return servicosAtualizacao;
	}

	public void setServicosAtualizacao(String servicosAtualizacao) {
		this.servicosAtualizacao = servicosAtualizacao;
	}

	public String getDescontosCreditos() {
		return descontosCreditos;
	}

	public void setDescontosCreditos(String descontosCreditos) {
		this.descontosCreditos = descontosCreditos;
	}

	public String getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(String valorAPagar) {
		this.valorAPagar = valorAPagar;
	}

	public String getEmitidoPor() {
		return emitidoPor;
	}

	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public ArrayList<EconomiaHelper> getEconomias() {
		return economias;
	}

	public void setEconomias(ArrayList<EconomiaHelper> economias) {
		this.economias = economias;
	}
	
}
