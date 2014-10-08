package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

import gcom.cadastro.localidade.SetorComercial;

/**
 * Classe de entidade da tabela
 * atendimentopublico.comando_os_setor
 * 
 * @author Raimundo Martins
 * @date 18/01/2012
 * */
public class ComandoOsSetor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ComandoOsSetorPK pk;
	private ComandoOrdemSeletiva comando;
	private SetorComercial setor;
	private Integer numeroQuadraIni;
	private Integer numeroQuadraFin;
	private Integer numeroRotaIni;
	private Integer numeroRotaFin;
	private Integer numeroSequencialIni;
	private Integer numeroSequencialFin;
	private Date ultimaAlteracao;
	
	
	public ComandoOsSetorPK getPk() {
		return pk;
	}
	public void setPk(ComandoOsSetorPK pk) {
		this.pk = pk;
	}
	public ComandoOrdemSeletiva getComando() {
		return comando;
	}
	public void setComando(ComandoOrdemSeletiva comando) {
		this.comando = comando;
	}
	public SetorComercial getSetor() {
		return setor;
	}
	public void setSetor(SetorComercial setor) {
		this.setor = setor;
	}
	public Integer getNumeroQuadraIni() {
		return numeroQuadraIni;
	}
	public void setNumeroQuadraIni(Integer numeroQuadraIni) {
		this.numeroQuadraIni = numeroQuadraIni;
	}
	public Integer getNumeroQuadraFin() {
		return numeroQuadraFin;
	}
	public void setNumeroQuadraFin(Integer numeroQuadraFin) {
		this.numeroQuadraFin = numeroQuadraFin;
	}
	public Integer getNumeroRotaIni() {
		return numeroRotaIni;
	}
	public void setNumeroRotaIni(Integer numeroRotaIni) {
		this.numeroRotaIni = numeroRotaIni;
	}
	public Integer getNumeroRotaFin() {
		return numeroRotaFin;
	}
	public void setNumeroRotaFin(Integer numeroRotaFin) {
		this.numeroRotaFin = numeroRotaFin;
	}
	public Integer getNumeroSequencialIni() {
		return numeroSequencialIni;
	}
	public void setNumeroSequencialIni(Integer numeroSequencialIni) {
		this.numeroSequencialIni = numeroSequencialIni;
	}
	public Integer getNumeroSequencialFin() {
		return numeroSequencialFin;
	}
	public void setNumeroSequencialFin(Integer numeroSequencialFin) {
		this.numeroSequencialFin = numeroSequencialFin;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
