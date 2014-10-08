package gcom.gui.cobranca.parcelamentojudicial.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.bean.ContaValoresHelper;

import java.util.ArrayList;
import java.util.Collection;

public class RegistroImovelHelper {

	private Imovel imovel;
	private Cliente cliente;
	private Collection<ContaValoresHelper> colecaoContaValoresHelper;
	
	public RegistroImovelHelper(){
		colecaoContaValoresHelper = new ArrayList<ContaValoresHelper>();
	}
	
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Imovel getImovel(){
		return this.imovel;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Localidade getLocalidadeImovel(){
		if(this.imovel != null){
			return this.imovel.getLocalidade();
		}
		else
			return null;
	}
	
	public void setColecaoContaValoresHelper(Collection<ContaValoresHelper> colecaoHelper){
		this.colecaoContaValoresHelper = colecaoHelper;
	}
	
	public Collection<ContaValoresHelper> getColecaoContaValoresHelper(){
		return this.colecaoContaValoresHelper;
	}
	
	public String getMatriculaImovelFormatada(){
		String id = null;
		if(this.imovel != null){
			id = this.imovel.getId().toString();
			id = id.substring(0,id.length() - 1) + "." + id.substring(id.length() - 1,id.length());
		}
		return id;
	}
	
	public String getNomeClienteUsuario(){
		if(this.cliente != null)
			return this.cliente.getNome();
		else
			return null;
	}
	
	public String getIdColecao(){
		if(this.imovel != null && this.cliente != null)
			return this.imovel.getId().toString() + this.cliente.getId().toString();
		else
			return "-1";
	}
	
	public Integer getIdImovel(){
		if(this.imovel != null){
			return this.imovel.getId();
		}
		else
			return -1;
	}
	
	public void addHelper(ContaValoresHelper helper){
		if(this.colecaoContaValoresHelper != null){
			this.colecaoContaValoresHelper.add(helper);
		}
	}
	
	
	public boolean equals(Object o){
		if(o instanceof RegistroImovelHelper){
			RegistroImovelHelper helper = (RegistroImovelHelper)o;
			return helper.getIdColecao().equals(this.getIdColecao());
		}
		else{
			return false;
		}
	}

}
