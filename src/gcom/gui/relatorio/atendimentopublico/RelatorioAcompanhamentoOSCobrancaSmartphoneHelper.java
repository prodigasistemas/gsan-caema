package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
 * 
 * @author Anderson Cabral
 * @since 19/08/2013
 */
public class RelatorioAcompanhamentoOSCobrancaSmartphoneHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipoRelatorio;
	private Empresa empresa;
	private String anoMesReferencia;
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	private ArrayList<String> idsTipoServico;
	private Date periodoGeracaoInicial;
	private Date periodoGeracaoFinal;
	
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public ArrayList<String> getIdsTipoServico() {
		return idsTipoServico;
	}
	public void setIdsTipoServico(ArrayList<String> idsTipoServico) {
		this.idsTipoServico = idsTipoServico;
	}
	public Date getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}
	public void setPeriodoGeracaoInicial(Date periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}
	public Date getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}
	public void setPeriodoGeracaoFinal(Date periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}
	
}
