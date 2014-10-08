package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1293] - Bean Responsavel por montar Relatorio Quantitativo de Contas Reimpressas
 * 
 * @author Davi Menezes
 * @date 08/03/2012
 *
 */
public class RelatorioQuantitativoContasReimpressasBean implements RelatorioBean {
	
	private String idGerencia;
	private String descricaoGerencia;
	
	private String idUnidadeNegocio;
	private String descricaoUnidade;
	
	private String idLocalidade;
	private String descricaoLocalidade;
	
	private String codigoSetor;
	private String descricaoSetor;
	
	private String idEmpresa;
	private String descricaoEmpresa;
	
	private String inscricao;
	private String matricula;
	private String nomeCliente;
	private String qtdConvencional;
	private String qtdEmCampo;
	
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}
	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
	public String getDescricaoSetor() {
		return descricaoSetor;
	}
	public void setDescricaoSetor(String descricaoSetor) {
		this.descricaoSetor = descricaoSetor;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getQtdConvencional() {
		return qtdConvencional;
	}
	public void setQtdConvencional(String qtdConvencional) {
		this.qtdConvencional = qtdConvencional;
	}
	public String getQtdEmCampo() {
		return qtdEmCampo;
	}
	public void setQtdEmCampo(String qtdEmCampo) {
		this.qtdEmCampo = qtdEmCampo;
	}
}
