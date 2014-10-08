package gcom.cadastro;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1361] - Efetuar Digitação Dados para Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 24/07/2012
 *
 */
public class CapaLoteAtualizacaoCadastral implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Short indicadorFinalizado;
	
	private Integer quantidadeDocumentosDigitados;
	
	private Integer quantidadeLimiteDocumentos;
	
	private Date ultimaAlteracao;
	
	private Localidade localidade;
	
	private SetorComercial setorComercial;
	
	private Quadra quadra;
	
	private Bairro bairro;
	
	private ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorFinalizado() {
		return indicadorFinalizado;
	}

	public void setIndicadorFinalizado(Short indicadorFinalizado) {
		this.indicadorFinalizado = indicadorFinalizado;
	}

	public Integer getQuantidadeDocumentosDigitados() {
		return quantidadeDocumentosDigitados;
	}

	public void setQuantidadeDocumentosDigitados(Integer quantidadeDocumentosDigitados) {
		this.quantidadeDocumentosDigitados = quantidadeDocumentosDigitados;
	}

	public Integer getQuantidadeLimiteDocumentos() {
		return quantidadeLimiteDocumentos;
	}

	public void setQuantidadeLimiteDocumentos(Integer quantidadeLimiteDocumentos) {
		this.quantidadeLimiteDocumentos = quantidadeLimiteDocumentos;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public ParametroTabelaAtualizacaoCadastro getParametroTabelaAtualizacaoCadastro() {
		return parametroTabelaAtualizacaoCadastro;
	}

	public void setParametroTabelaAtualizacaoCadastro(ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro) {
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}
	
}
