package gcom.atendimentopublico;


import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ParametrosSms extends ObjetoTransacao  {

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String ipProxy;
	private String portaProxy;
	private String urlWebService;
	private String protocolo;
	private String usuarioWebService;
	private String senhaWebService;
	private Date ultimaAlteracao;
	
	public ParametrosSms(Integer id, String ipProxy, String portaProxy,
			String urlWebService, String protocolo, String usuarioWebService,
			String senhaWebService, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.ipProxy = ipProxy;
		this.portaProxy = portaProxy;
		this.urlWebService = urlWebService;
		this.protocolo = protocolo;
		this.usuarioWebService = usuarioWebService;
		this.senhaWebService = senhaWebService;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ParametrosSms() {
		super();
	}

	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getIpProxy() {
		return ipProxy;
	}



	public void setIpProxy(String ipProxy) {
		this.ipProxy = ipProxy;
	}



	public String getPortaProxy() {
		return portaProxy;
	}



	public void setPortaProxy(String portaProxy) {
		this.portaProxy = portaProxy;
	}



	public String getUrlWebService() {
		return urlWebService;
	}



	public void setUrlWebService(String urlWebService) {
		this.urlWebService = urlWebService;
	}



	public String getUsuarioWebService() {
		return usuarioWebService;
	}



	public void setUsuarioWebService(String usuarioWebService) {
		this.usuarioWebService = usuarioWebService;
	}



	public String getSenhaWebService() {
		return senhaWebService;
	}



	public void setSenhaWebService(String senhaWebService) {
		this.senhaWebService = senhaWebService;
	}



	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}



	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}



	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	
	
}

