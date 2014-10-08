package gcom.cadastro;

import java.io.Serializable;

/**
 * [UC0000] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 06/03/2012
 */
public class ImovelSorteadoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String numeroSorteado;
	
	private String matricula;
	
	private String gerenciaRegional;
	
	private String localidade;
	
	private String nomeClienteUsuario;
	
	private String cpfClienteUsuario;
	
	private String endereco;
	
	private String cpfCnpjCliente;

	public String getNumeroSorteado() {
		return numeroSorteado;
	}

	public void setNumeroSorteado(String numeroSorteado) {
		this.numeroSorteado = numeroSorteado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getCpfClienteUsuario() {
		return cpfClienteUsuario;
	}

	public void setCpfClienteUsuario(String cpfClienteUsuario) {
		this.cpfClienteUsuario = cpfClienteUsuario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	
}
