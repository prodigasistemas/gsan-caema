package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class LogradouroAdmin extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nomelogradouro;
	
	private Short indicadorAtualizado;

	private Date dataAtualizacaoGsan;

	private Date ultimaAlteracao;
	
	private LogradouroTitulo logradouroTitulo;
	
	private LogradouroTipo logradouroTipo;

    public LogradouroAdmin(){
    	
    }
    
	public LogradouroAdmin(Integer id, String nomelogradouro,
			Short indicadorAtualizado, Date dataAtualizacaoGsan,
			Date ultimaAlteracao, LogradouroTitulo logradouroTitulo, 
			LogradouroTipo logradouroTipo) {
		super();
		this.id = id;
		this.nomelogradouro = nomelogradouro;
		this.indicadorAtualizado = indicadorAtualizado;
		this.dataAtualizacaoGsan = dataAtualizacaoGsan;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroTitulo = logradouroTitulo;
		this.logradouroTipo = logradouroTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomelogradouro() {
		return nomelogradouro;
	}

	public void setNomelogradouro(String nomelogradouro) {
		this.nomelogradouro = nomelogradouro;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public Date getDataAtualizacaoGsan() {
		return dataAtualizacaoGsan;
	}

	public void setDataAtualizacaoGsan(Date dataAtualizacaoGsan) {
		this.dataAtualizacaoGsan = dataAtualizacaoGsan;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LogradouroTitulo getLogradouroTitulo() {
		return logradouroTitulo;
	}

	public void setLogradouroTitulo(LogradouroTitulo logradouroTitulo) {
		this.logradouroTitulo = logradouroTitulo;
	}

	public LogradouroTipo getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(LogradouroTipo logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

}
