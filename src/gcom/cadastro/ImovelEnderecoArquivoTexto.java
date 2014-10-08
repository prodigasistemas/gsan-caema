package gcom.cadastro;

import java.util.Date;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

/**
 * [UC1560] - Validar Dados dos Endereços Enviados pelo GEO
 * 
 * @author Anderson Cabral
 * @date 19/09/2013
 */
public class ImovelEnderecoArquivoTexto extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	private Logradouro logradouro;
	private String numeroImovel;
	private String complementoEndereco;
	private Cep cep;
	private Bairro bairro;
	private Short indicadorAtualizacao;
	private Date ultimaAlteracao;
	

	public ImovelEnderecoArquivoTexto() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Short getIndicadorAtualizacao() {
		return indicadorAtualizacao;
	}

	public void setIndicadorAtualizacao(Short indicadorAtualizacao) {
		this.indicadorAtualizacao = indicadorAtualizacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
