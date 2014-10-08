package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
 * 
 * @author Mariana Victor
 * @date 20/08/2013
 * */
public class GerarRelatorioComandosConexaoEsgotoActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	public static final int COMPESA = 1;
	public static final int PPP = 2;

	//Atributo(s)
	private String descricaoComando;
	private String indicadorExecucao;
	private String idImovel;
	private String descricaoImovel;
	private String idMunicipio;
	private String descricaoMunicipio;
	private String idLogradouro;
	private String descricaoLogradouro;
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String codigoSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String quadraInicial;
	private String codigoRotaInicial;
	private String sequencialRotaInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String codigoSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String quadraFinal;
	private String codigoRotaFinal;
	private String sequencialRotaFinal;
	private String idFaturamentoGrupo;
	private String idSituacaoOrdemServico;
	private String dataGeracaoInicial;
	private String dataGeracaoFinal;
	private String tipoRelatorioSinteticoAnalitico;
	
	//Contrutor(es)
	public GerarRelatorioComandosConexaoEsgotoActionForm(){}

	//Métodos get(s) e set(s)
	public int getCompesa(){
		return COMPESA;
	}
	public int getPpp(){
		return PPP;
	}
	public String getDescricaoComando() {
		return descricaoComando;
	}
	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}
	public String getIndicadorExecucao() {
		return indicadorExecucao;
	}
	public void setIndicadorExecucao(String indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
	public String getIdLogradouro() {
		return idLogradouro;
	}
	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}
	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}
	public void setDescricaoSetorComercialInicial(String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}
	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}
	public String getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public String getQuadraFinal() {
		return quadraFinal;
	}
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}
	public String getIdSituacaoOrdemServico() {
		return idSituacaoOrdemServico;
	}
	public void setIdSituacaoOrdemServico(String idSituacaoOrdemServico) {
		this.idSituacaoOrdemServico = idSituacaoOrdemServico;
	}
	public String getDataGeracaoInicial() {
		return dataGeracaoInicial;
	}
	public void setDataGeracaoInicial(String dataGeracaoInicial) {
		this.dataGeracaoInicial = dataGeracaoInicial;
	}
	public String getDataGeracaoFinal() {
		return dataGeracaoFinal;
	}
	public void setDataGeracaoFinal(String dataGeracaoFinal) {
		this.dataGeracaoFinal = dataGeracaoFinal;
	}
	public String getTipoRelatorioSinteticoAnalitico() {
		return tipoRelatorioSinteticoAnalitico;
	}
	public void setTipoRelatorioSinteticoAnalitico(String tipoRelatorioSinteticoAnalitico) {
		this.tipoRelatorioSinteticoAnalitico = tipoRelatorioSinteticoAnalitico;
	}
	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}
	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}
	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}
	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}
	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}
	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}
	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}
	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
}
