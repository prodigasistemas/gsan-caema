package gcom.cadastro;

import gcom.cadastro.endereco.Logradouro;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1361] - Efetuar Digitação Dados para Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 24/07/2012
 *
 */
public class CapaLoteLogradouroAtualizacaoCadastral implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date ultimaAlteracao;
	
	private Logradouro logradouro;
	
	private CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public CapaLoteAtualizacaoCadastral getCapaLoteAtualizacaoCadastral() {
		return capaLoteAtualizacaoCadastral;
	}

	public void setCapaLoteAtualizacaoCadastral(CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral) {
		this.capaLoteAtualizacaoCadastral = capaLoteAtualizacaoCadastral;
	}
	
}
