package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioConsultarOSBean implements RelatorioBean, Comparable<RelatorioConsultarOSBean> {	

	private String idGerenciaRegional;
	private String descricaoGerenciaRegional;
	private String idUnidadeNegocio;
	private String descricaoUnidadeNegocio;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String matriculaImovel;
	private String numeroOS;
	private String idTipoServico;
	private String descricaoTipoServico;
	private String dataGeracaoOS;
	private String dataEncerramentoOS;
	private String motivoEncerramentoOS;
	private String idSituacaoOS;
	private String descricaoSituacaoOS;
	private String descricaoEstado;
	
	private Integer indicadorOSGerada;
	private Integer indicadorOSExecutada;
	private Integer indicadorOSCancelada;
	private BigDecimal enviadosCorte;
	private BigDecimal enviadosSupressao;
	private BigDecimal pagosCorte;
	private BigDecimal pagosSupressao;
	
	private JRBeanCollectionDataSource arrayJRTotalUnidadeNegocio;
	private JRBeanCollectionDataSource arrayJRTotalGerenciaRegional;
	private JRBeanCollectionDataSource arrayJRTotalEstado;
	

	public JRBeanCollectionDataSource getArrayJRTotalUnidadeNegocio() {
		return arrayJRTotalUnidadeNegocio;
	}
	
	public void setArrayJRTotalUnidadeNegocio(List colecao) {
		this.arrayJRTotalUnidadeNegocio = new JRBeanCollectionDataSource(colecao);
	}
	
	public JRBeanCollectionDataSource getArrayJRTotalGerenciaRegional() {
		return arrayJRTotalGerenciaRegional;
	}
	
	public void setArrayJRTotalGerenciaRegional(List colecao) {
		this.arrayJRTotalGerenciaRegional = new JRBeanCollectionDataSource(colecao);
	}
	
	public JRBeanCollectionDataSource getArrayJRTotalEstado() {
		return arrayJRTotalEstado;
	}
	
	public void setArrayJRTotalEstado(List colecao) {
		this.arrayJRTotalEstado = new JRBeanCollectionDataSource(colecao);
	}
	
	
	public RelatorioConsultarOSBean(){
		this.indicadorOSGerada = new Integer(0);
		this.indicadorOSExecutada = new Integer(0);
		this.indicadorOSCancelada = new Integer(0);
	}



	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}



	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}



	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}



	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}



	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}



	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}



	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}



	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
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



	public String getMatriculaImovel() {
		return matriculaImovel;
	}



	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}



	public String getNumeroOS() {
		return numeroOS;
	}



	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}



	public String getIdTipoServico() {
		return idTipoServico;
	}



	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}



	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}



	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}



	public String getDataGeracaoOS() {
		return dataGeracaoOS;
	}



	public void setDataGeracaoOS(String dataGeracaoOS) {
		this.dataGeracaoOS = dataGeracaoOS;
	}



	public String getDataEncerramentoOS() {
		return dataEncerramentoOS;
	}



	public void setDataEncerramentoOS(String dataEncerramentoOS) {
		this.dataEncerramentoOS = dataEncerramentoOS;
	}



	public String getMotivoEncerramentoOS() {
		return motivoEncerramentoOS;
	}



	public void setMotivoEncerramentoOS(String motivoEncerramentoOS) {
		this.motivoEncerramentoOS = motivoEncerramentoOS;
	}



	public String getIdSituacaoOS() {
		return idSituacaoOS;
	}



	public void setIdSituacaoOS(String idSituacaoOS) {
		this.idSituacaoOS = idSituacaoOS;
	}



	public String getDescricaoSituacaoOS() {
		return descricaoSituacaoOS;
	}



	public void setDescricaoSituacaoOS(String descricaoSituacaoOS) {
		this.descricaoSituacaoOS = descricaoSituacaoOS;
	}



	public Integer getIndicadorOSGerada() {
		return indicadorOSGerada;
	}



	public void setIndicadorOSGerada(Integer indicadorOSGerada) {
		this.indicadorOSGerada = indicadorOSGerada;
	}



	public Integer getIndicadorOSExecutada() {
		return indicadorOSExecutada;
	}



	public void setIndicadorOSExecutada(Integer indicadorOSExecutada) {
		this.indicadorOSExecutada = indicadorOSExecutada;
	}



	public Integer getIndicadorOSCancelada() {
		return indicadorOSCancelada;
	}



	public void setIndicadorOSCancelada(Integer indicadorOSCancelada) {
		this.indicadorOSCancelada = indicadorOSCancelada;
	}

	public String getDescricaoEstado() {
		return descricaoEstado;
	}

	public void setDescricaoEstado(String descricaoEstado) {
		this.descricaoEstado = descricaoEstado;
	}

	public BigDecimal getEnviadosCorte() {
		return enviadosCorte;
	}

	public void setEnviadosCorte(BigDecimal enviadosCorte) {
		this.enviadosCorte = enviadosCorte;
	}

	public BigDecimal getEnviadosSupressao() {
		return enviadosSupressao;
	}

	public void setEnviadosSupressao(BigDecimal enviadosSupressao) {
		this.enviadosSupressao = enviadosSupressao;
	}

	public BigDecimal getPagosCorte() {
		return pagosCorte;
	}

	public void setPagosCorte(BigDecimal pagosCorte) {
		this.pagosCorte = pagosCorte;
	}

	public BigDecimal getPagosSupressao() {
		return pagosSupressao;
	}

	public void setPagosSupressao(BigDecimal pagosSupressao) {
		this.pagosSupressao = pagosSupressao;
	}

	public int compareTo(RelatorioConsultarOSBean o) {		       
		return getDescricaoSituacaoOS().compareTo(o.getDescricaoSituacaoOS());
	}

}
