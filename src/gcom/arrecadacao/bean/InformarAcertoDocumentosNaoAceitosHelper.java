package gcom.arrecadacao.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class InformarAcertoDocumentosNaoAceitosHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private Collection<ContaValoresHelper> colecaoContaValores;
	
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores;
	
	private Collection<DebitoACobrarValoresHelper> colecaoDebitoACobrar;
	
	private Collection<CobrancaDocumentoHelper> colecaoCobrancaDocumentoValores;
	
	private BigDecimal valorTotalDebitos;
	
	private BigDecimal valorPagamento;
	
	private Integer idPagamento;
	
	private Imovel imovel;
	
	private Usuario usuario;
	
	public Collection<ContaValoresHelper> getColecaoContaValores() {
		return colecaoContaValores;
	}

	public void setColecaoContaValores(
			Collection<ContaValoresHelper> colecaoContaValores) {
		this.colecaoContaValores = colecaoContaValores;
	}

	public Collection<DebitoACobrarValoresHelper> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrarValoresHelper> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiaPagamentoValores() {
		return colecaoGuiaPagamentoValores;
	}

	public void setColecaoGuiaPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores) {
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
	}
	
	public Collection<CobrancaDocumentoHelper> getColecaoCobrancaDocumentoValores() {
		return colecaoCobrancaDocumentoValores;
	}

	public void setColecaoCobrancaDocumentoValores(
			Collection<CobrancaDocumentoHelper> colecaoCobrancaDocumentoValores) {
		this.colecaoCobrancaDocumentoValores = colecaoCobrancaDocumentoValores;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public BigDecimal getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	public void setValorTotalDebitos(BigDecimal valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
