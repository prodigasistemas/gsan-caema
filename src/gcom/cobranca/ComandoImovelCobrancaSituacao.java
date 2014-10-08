package gcom.cobranca;

import gcom.cadastro.imovel.ImovelCobrancaSituacao;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de entidade da tabela COBRANCA.CMD_IMOVEL_COBR_SITUACAO
 * 
 * @author Raimundo Martins
 * */
public class ComandoImovelCobrancaSituacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ComandoEmpresaCobrancaConta comando;
	private ImovelCobrancaSituacao imovelCobranca;
	private Date ultimaAlteracao;
	private ComandoImovelCobrancaSituacaoPK pk;
	
	public ComandoEmpresaCobrancaConta getComando() {
		return comando;
	}
	public void setComando(ComandoEmpresaCobrancaConta comando) {
		this.comando = comando;
	}
	public ImovelCobrancaSituacao getImovelCobranca() {
		return imovelCobranca;
	}
	public void setImovelCobranca(ImovelCobrancaSituacao imovelCobranca) {
		this.imovelCobranca = imovelCobranca;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public ComandoImovelCobrancaSituacaoPK getPk() {
		return pk;
	}
	public void setPk(ComandoImovelCobrancaSituacaoPK pk) {
		this.pk = pk;
	}	
}
