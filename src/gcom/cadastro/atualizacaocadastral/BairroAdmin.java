package gcom.cadastro.atualizacaocadastral;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class BairroAdmin extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nomeBairro;
	
	private Short indicadorAtualizado;
	
	private Date dataAtualizacaoGsan;

	private Date ultimaAlteracao;


    public BairroAdmin(Integer id, String nomeBairro,
			Short indicadorAtualizado, Date dataAtualizacaoGsan,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.nomeBairro = nomeBairro;
		this.indicadorAtualizado = indicadorAtualizado;
		this.dataAtualizacaoGsan = dataAtualizacaoGsan;
		this.ultimaAlteracao = ultimaAlteracao;
	}
    
    public BairroAdmin(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
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
