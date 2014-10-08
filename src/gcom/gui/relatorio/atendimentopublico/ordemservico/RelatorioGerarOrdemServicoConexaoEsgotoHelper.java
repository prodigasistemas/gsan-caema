package gcom.gui.relatorio.atendimentopublico.ordemservico;

import java.io.Serializable;

public class RelatorioGerarOrdemServicoConexaoEsgotoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Atributo(s)
	private String matricula;
	private String endereco;
	private String numeroDiasFactivelFaturavelSistemaParametro;
		
	//Construtor(es)
	public RelatorioGerarOrdemServicoConexaoEsgotoHelper(){}

	//Métodos get(s) e set(s)
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
	public String getNumeroDiasFactivelFaturavelSistemaParametro() {
		return numeroDiasFactivelFaturavelSistemaParametro;
	}
	public void setNumeroDiasFactivelFaturavelSistemaParametro(String numeroDiasFactivelFaturavelSistemaParametro) {
		this.numeroDiasFactivelFaturavelSistemaParametro = numeroDiasFactivelFaturavelSistemaParametro;
	}
}
