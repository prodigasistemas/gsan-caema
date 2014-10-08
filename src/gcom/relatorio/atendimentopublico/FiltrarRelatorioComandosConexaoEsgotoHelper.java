package gcom.relatorio.atendimentopublico;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
 * 
 * @author Mariana Victor
 * @date 21/08/2013
 * */
public class FiltrarRelatorioComandosConexaoEsgotoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoComando;
	
	private Short indicadorExecucao;
	
	private String descricaoExecucao;
	
	private Integer idImovel;
	
	private Integer idMunicipio;
	
	private String descricaoMunicipio;
	
	private Integer idLogradouro;
	
	private String descricaoLogradouro;
	
	private Integer idLocalidadeInicial;
	
	private Integer idSetorComercialInicial;
	
	private Integer codigoSetorComercialInicial;
	
	private Integer idQuadraInicial;
	
	private Integer quadraInicial;
	
	private Integer idRotaInicial;
	
	private Integer codigoRotaInicial;
	
	private Integer sequencialRotaInicial;
	
	private Integer idLocalidadeFinal;
	
	private Integer idSetorComercialFinal;
	
	private Integer codigoSetorComercialFinal;
	
	private Integer idQuadraFinal;
	
	private Integer quadraFinal;
	
	private Integer idRotaFinal;
	
	private Integer codigoRotaFinal;
	
	private Integer sequencialRotaFinal;
	
	private Integer idFaturamentoGrupo;
	
	private String descricaoFaturamentoGrupo;
	
	private Integer idSituacaoOrdemServico;
	
	private String descricaoSituacaoOS;
	
	private Date dataGeracaoInicial;
	
	private Date dataGeracaoFinal;

	public String getDescricaoComando() {
		return descricaoComando;
	}

	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	public Short getIndicadorExecucao() {
		return indicadorExecucao;
	}

	public void setIndicadorExecucao(Short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}

	public void setIdSetorComercialInicial(Integer idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	public Integer getIdQuadraInicial() {
		return idQuadraInicial;
	}

	public void setIdQuadraInicial(Integer idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}

	public Integer getIdRotaInicial() {
		return idRotaInicial;
	}

	public void setIdRotaInicial(Integer idRotaInicial) {
		this.idRotaInicial = idRotaInicial;
	}

	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	public void setIdSetorComercialFinal(Integer idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	public Integer getIdQuadraFinal() {
		return idQuadraFinal;
	}

	public void setIdQuadraFinal(Integer idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}

	public Integer getIdRotaFinal() {
		return idRotaFinal;
	}

	public void setIdRotaFinal(Integer idRotaFinal) {
		this.idRotaFinal = idRotaFinal;
	}

	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdSituacaoOrdemServico() {
		return idSituacaoOrdemServico;
	}

	public void setIdSituacaoOrdemServico(Integer idSituacaoOrdemServico) {
		this.idSituacaoOrdemServico = idSituacaoOrdemServico;
	}

	public Date getDataGeracaoInicial() {
		return dataGeracaoInicial;
	}

	public void setDataGeracaoInicial(Date dataGeracaoInicial) {
		this.dataGeracaoInicial = dataGeracaoInicial;
	}

	public Date getDataGeracaoFinal() {
		return dataGeracaoFinal;
	}

	public void setDataGeracaoFinal(Date dataGeracaoFinal) {
		this.dataGeracaoFinal = dataGeracaoFinal;
	}

	public String getDescricaoExecucao() {
		return descricaoExecucao;
	}

	public void setDescricaoExecucao(String descricaoExecucao) {
		this.descricaoExecucao = descricaoExecucao;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getDescricaoFaturamentoGrupo() {
		return descricaoFaturamentoGrupo;
	}

	public void setDescricaoFaturamentoGrupo(String descricaoFaturamentoGrupo) {
		this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
	}

	public String getDescricaoSituacaoOS() {
		return descricaoSituacaoOS;
	}

	public void setDescricaoSituacaoOS(String descricaoSituacaoOS) {
		this.descricaoSituacaoOS = descricaoSituacaoOS;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Integer getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Integer quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Integer getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(Integer codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Integer quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Integer getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(Integer codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

}
