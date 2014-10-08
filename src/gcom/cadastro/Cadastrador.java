package gcom.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class Cadastrador extends ObjetoTransacao {

	
	public final static Integer MATRICULA_CADASTRADOR_AUTOMATICO = new Integer(1);
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer matricula;

    /** persistent field */
    private String nome;

    /** nullable persistent field */
    private String cpf;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Empresa empresa;

    /** default constructor */
    public Cadastrador() {
    }

    
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
