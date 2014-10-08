/**
	 * Este caso de uso permite a inclusão de um motivo de corte
	 * 
	 * [UC0754] Inserir Motivo Nao Geracao Cobranca Resultado
	 * 
	 * 
	 * @author Diego Maciel
	 * @date 27/04/2012
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class InserirMotivoNaoGeracaoCobrancaResultadoActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String codigo;
	private String descricao;
	private String descricaoAbreviada;
	private String tipoMotivo;
	private String indicadorUso;
	private Date dataHoraOperacao;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(String tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDataHoraOperacao() {
		return dataHoraOperacao;
	}
	public void setDataHoraOperacao(Date dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}
	
	
}
