package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class UsuarioNaturezaAcompEquipe implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private EquipeNatureza equipeNatureza;
	
	private Usuario usuario;
	
	private Date ultimaAlteracao;

	public UsuarioNaturezaAcompEquipe(){
		
	}
	
	public UsuarioNaturezaAcompEquipe(EquipeNatureza equipeNatureza, Usuario usuario, Date ultimaAlteracao){
		super();
		this.equipeNatureza = equipeNatureza;
		this.usuario = usuario;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipeNatureza getEquipeNatureza() {
		return equipeNatureza;
	}

	public void setEquipeNatureza(EquipeNatureza equipeNatureza) {
		this.equipeNatureza = equipeNatureza;
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
