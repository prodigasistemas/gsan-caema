package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class UsuarioEmpresaAcompEquipe implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Empresa empresa;
	
	private Usuario usuario;
	
	private Date ultimaAlteracao;

	public UsuarioEmpresaAcompEquipe(){
		
	}
	
	public UsuarioEmpresaAcompEquipe(Empresa empresa, Usuario usuario, Date ultimaAlteracao){
		super();
		this.empresa = empresa;
		this.usuario = usuario;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
