package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

public class RelatorioGerarOrdemServicoConexaoEsgotoBean implements RelatorioBean{

	//Atributo(s)
	private String matricula;
	private String endereco;
	private String numeroDiasFactivelFaturavelSistemaParametro;
	
	//Controlador(es)
	public RelatorioGerarOrdemServicoConexaoEsgotoBean(RelatorioGerarOrdemServicoConexaoEsgotoHelper helper){
		this.matricula = helper.getMatricula();
		this.endereco = helper.getEndereco();
		this.numeroDiasFactivelFaturavelSistemaParametro = helper.getNumeroDiasFactivelFaturavelSistemaParametro();
	}

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
