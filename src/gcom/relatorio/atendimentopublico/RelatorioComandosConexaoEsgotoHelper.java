package gcom.relatorio.atendimentopublico;

import java.io.Serializable;
import java.util.Date;

public class RelatorioComandosConexaoEsgotoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idMunicipio;

	private String descricaoMunicipio;

	private Integer idLocalidade;

	private String descricaoLocalidade;

	private Integer idSetorComercial;

	private String descricaoSetorComercial;

	private Integer numeroOS;

	private Integer matricula;

	private Date dataEncerramento;

	private String situacaoEncerramento;

	private String situacaoLigacaoEsgoto;

	private Integer quantidadeOS;

	private Integer idMotivoEncerramento;

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getSituacaoEncerramento() {
		return situacaoEncerramento;
	}

	public void setSituacaoEncerramento(String situacaoEncerramento) {
		this.situacaoEncerramento = situacaoEncerramento;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public Integer getQuantidadeOS() {
		return quantidadeOS;
	}

	public void setQuantidadeOS(Integer quantidadeOS) {
		this.quantidadeOS = quantidadeOS;
	}

	public Integer getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(Integer idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

}
