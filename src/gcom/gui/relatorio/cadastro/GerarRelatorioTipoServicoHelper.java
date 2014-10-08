package gcom.gui.relatorio.cadastro;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC1333] Gerar Relat�rio por tipo de servico
 * 
 * @autor Carlos Chaves
 * @date 04/05/2012
 */
public class GerarRelatorioTipoServicoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tipoRelatorio;
	private String periodoInicial;
	private String periodoFinal;
	private String[] colecaoMeio;
	private Collection<MeioSolicitacao> colecaoMeioSolicitacao;
	private String idServico;
	
	// Caso tipo Usuario
	private String idUnidadeSuperior;
	private Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional;
	private Collection<Usuario> colecaoUsuario;
	
	// Caso tipo Localidade
	private String idGerenciaRegional;
	private String idGerenciaRegionalPorLocalidade;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String opcaoTotalizacao;
	
	
	// Construtor para o tipo Usu�rio
	public GerarRelatorioTipoServicoHelper(String tipoRelatorio, 
			String periodoInicial, String periodoFinal, 
			String[] colecaoMeio, Collection<MeioSolicitacao> colecaoMeioSolicitacao,
			String idUnidadeSuperior, 
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional, 
			Collection<Usuario> colecaoUsuario, String idServico) {
		super();
		this.tipoRelatorio = tipoRelatorio;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.colecaoMeio = colecaoMeio;
		this.colecaoMeioSolicitacao = colecaoMeioSolicitacao;
		this.idUnidadeSuperior = idUnidadeSuperior;
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
		this.colecaoUsuario = colecaoUsuario;
		this.idServico = idServico;
	}
	
	// Construtor para o tipo Localidade
	public GerarRelatorioTipoServicoHelper(String tipoRelatorio, String periodoInicial,
			String periodoFinal, String[] colecaoMeio, Collection<MeioSolicitacao> colecaoMeioSolicitacao,
			String idGerenciaRegional, String idGerenciaRegionalPorLocalidade,
			String idUnidadeNegocio, String idLocalidade, String opcaoTotalizacao, String idServico) {
		super();
		this.tipoRelatorio = tipoRelatorio;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.colecaoMeio = colecaoMeio;
		this.colecaoMeioSolicitacao = colecaoMeioSolicitacao;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idGerenciaRegionalPorLocalidade = idGerenciaRegionalPorLocalidade;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.idServico = idServico;
	}

	// Construtor para o tipo Meio
	public GerarRelatorioTipoServicoHelper(String tipoRelatorio,
			String periodoInicial, String periodoFinal,
			String[] colecaoMeio, Collection<MeioSolicitacao> colecaoMeioSolicitacao, String idServico) {
		super();
		this.tipoRelatorio = tipoRelatorio;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.colecaoMeio = colecaoMeio;
		this.colecaoMeioSolicitacao = colecaoMeioSolicitacao;
		this.idServico = idServico;
	}

	public String[] getColecaoMeio() {
		return colecaoMeio;
	}

	public void setColecaoMeio(String[] colecaoMeio) {
		this.colecaoMeio = colecaoMeio;
	}

	public Collection<MeioSolicitacao> getColecaoMeioSolicitacao() {
		return colecaoMeioSolicitacao;
	}

	public void setColecaoMeioSolicitacao(
			Collection<MeioSolicitacao> colecaoMeioSolicitacao) {
		this.colecaoMeioSolicitacao = colecaoMeioSolicitacao;
	}

	public Collection<UnidadeOrganizacional> getColecaoUnidadeOrganizacional() {
		return colecaoUnidadeOrganizacional;
	}

	public void setColecaoUnidadeOrganizacional(
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional) {
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
	}

	public Collection<Usuario> getColecaoUsuario() {
		return colecaoUsuario;
	}

	public void setColecaoUsuario(Collection<Usuario> colecaoUsuario) {
		this.colecaoUsuario = colecaoUsuario;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdGerenciaRegionalPorLocalidade() {
		return idGerenciaRegionalPorLocalidade;
	}

	public void setIdGerenciaRegionalPorLocalidade(
			String idGerenciaRegionalPorLocalidade) {
		this.idGerenciaRegionalPorLocalidade = idGerenciaRegionalPorLocalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	
	
	

}
