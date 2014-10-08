package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
public class RelatorioRetornoOrdemServicoFiscalizacaoBean implements RelatorioBean{

	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;
	private String situacao;
	private String cliente;
	private Integer numeroOS;
	private Date dataRetorno;
	private Integer matriculaImovel;
	private String inscricaoImovel;
	private String perfilImovel;
	private String tipoDebito;
	private String situacaoDebito;
	private BigDecimal valorDebito;
	private JRBeanCollectionDataSource retornosInfomados;
	private String descricaoDebito;
	private String categoria;

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(Integer matriculaImovel) {
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
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getSituacaoDebito() {
		return situacaoDebito;
	}
	public void setSituacaoDebito(String situacaoDebito) {
		this.situacaoDebito = situacaoDebito;
	}
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	public JRBeanCollectionDataSource getRetornosInfomados() {
		return retornosInfomados;
	}
	public void setRetornosInfomados(JRBeanCollectionDataSource retornosInfomados) {
		this.retornosInfomados = retornosInfomados;
	}

	public String getDescricaoDebito() {
		return descricaoDebito;
	}
	public void setDescricaoDebito(String descricaoDebito) {
		this.descricaoDebito = descricaoDebito;
	}
	

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
