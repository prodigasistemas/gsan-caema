package gcom.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class ResumoOrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** identifier field */
    private Integer id;
    
    /** persistent field */
    private int anoMesReferencia;
    
    /** persistent field */
    private int codigoSetorComercial;
    
    /** persistent field */
    private int numeroQuadra;
    
    /** persistent field */
    private int quantidadeContas;
    
    /** persistent field */
    private int quantidadeOrdemServico;
    
    /** persistent field */
    private BigDecimal valorDocumentos;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Rota rota;
    
    /** persistent field */
    private CobrancaDebitoSituacao cobrancaDebitoSituacao;
    
    /** persistent field */
    private SetorComercial setorComercial;
    
    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
    
    /** persistent field */
    private EsferaPoder esferaPoder;
    
    /** persistent field */
    private ImovelPerfil imovelPerfil;
    
    /** persistent field */
    private GerenciaRegional gerenciaRegional;
    
    /** persistent field */
    private Localidade localidade;
    
    /** persistent field */
    private Quadra quadra;
       
    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;
    
    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private AtendimentoMotivoEncerramento motivoEncerramento;
    
    /** persistent field */
    private UnidadeNegocio unidadeNegocio;
    
    /** persistent field */
    private ServicoTipo servicoTipo;
    
    /** persistent field */
    private OrdemServicoSituacao ordemServicoSituacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(int quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}

	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(
			CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public OrdemServicoSituacao getOrdemServicoSituacao() {
		return ordemServicoSituacao;
	}

	public void setOrdemServicoSituacao(OrdemServicoSituacao ordemServicoSituacao) {
		this.ordemServicoSituacao = ordemServicoSituacao;
	}

	public int getQuantidadeOrdemServico() {
		return quantidadeOrdemServico;
	}

	public void setQuantidadeOrdemServico(int quantidadeOrdemServico) {
		this.quantidadeOrdemServico = quantidadeOrdemServico;
	}

}
