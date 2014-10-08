package gcom.relatorio.cobranca;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import gcom.relatorio.RelatorioBean;
public class RelatorioRetornoOSFiscalizacaoBean implements RelatorioBean{

	private String situacaoEncontradaDesc;
	private String nomeCliente;
	private String idOs;
	private String dataRetorno;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String perfilImovel;
	private String locaId;
	private String locaDesc;
	private JRBeanCollectionDataSource retornosInfomados;
	private JRBeanCollectionDataSource debitosGerados;
	private JRBeanCollectionDataSource totalLocas;
	
	public String getSituacaoEncontradaDesc() {
		return situacaoEncontradaDesc;
	}
	public void setSituacaoEncontradaDesc(String situacaoEncontradaDesc) {
		this.situacaoEncontradaDesc = situacaoEncontradaDesc;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getIdOs() {
		return idOs;
	}
	public void setIdOs(String idOs) {
		this.idOs = idOs;
	}
	public String getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public JRBeanCollectionDataSource getRetornosInfomados() {
		return retornosInfomados;
	}
	public void setRetornosInfomados(JRBeanCollectionDataSource retornosInfomados) {
		this.retornosInfomados = retornosInfomados;
	}
	public JRBeanCollectionDataSource getDebitosGerados() {
		return debitosGerados;
	}
	public void setDebitosGerados(JRBeanCollectionDataSource debitosGerados) {
		this.debitosGerados = debitosGerados;
	}
	public String getLocaId() {
		return locaId;
	}
	public void setLocaId(String locaId) {
		this.locaId = locaId;
	}
	public String getLocaDesc() {
		return locaDesc;
	}
	public void setLocaDesc(String locaDesc) {
		this.locaDesc = locaDesc;
	}
	public JRBeanCollectionDataSource getTotalLocas() {
		return totalLocas;
	}
	public void setTotalLocas(JRBeanCollectionDataSource totalLocas) {
		this.totalLocas = totalLocas;
	}
}
