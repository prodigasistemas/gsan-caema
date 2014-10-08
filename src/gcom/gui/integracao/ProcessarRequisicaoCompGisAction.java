package gcom.gui.integracao;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.CoordenadasCompGis;
import gcom.atendimentopublico.registroatendimento.FiltroCoordenadasCompGis;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ProcessarRequisicaoCompGisAction  extends GcomAction {
	
	private static final String INSERIR_COORDENADAS = "inserirCoordenadas";
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("ERROR:REQUISICAO COMPGIS: " + e.getMessage());
		}
		
		try {
			
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);			
			
			String requisicaoTipo = null;
			if ( request.getParameter("requisicaoTipo") != null && !request.getParameter("requisicaoTipo").equals("") ) {
				requisicaoTipo = (String) request.getParameter("requisicaoTipo");
			} else {
				requisicaoTipo = din.readUTF();	
			}		
				
			System.out.println("INICIO:SOLICITACAO REQUISICAO COMPGIS DO TIPO: " + requisicaoTipo);
			
			if (requisicaoTipo != null && !requisicaoTipo.equals("") ) {
				
				if ( requisicaoTipo.equals(INSERIR_COORDENADAS) ) {
				
					processarRequisicaoInserirCoordenadas(din, response, out, request);
				
				}else {
					throw new Exception("atencao.parametros_autoatendimento_invalidos");
				}
				
				
			} else {
				System.out.println("FIM: SOLICITACAO REQUISICAO AUTOATENDIMENTO - REQUISICAO TIPO EM BRANCO OU NULO ");
			}
			System.out.println("FIM:SOLICITACAO REQUISICAO AUTOATENDIMENTO DO TIPO: " + requisicaoTipo);
		} catch (Exception e) {
			
			try {
				
				String mensagem = null;
				String[] parametrosArray = null;
				
				if(e instanceof FachadaException){
					List<String> parametros = ((FachadaException) e).getParametroMensagem();
					if(parametros != null){
						parametrosArray = parametros.toArray(new String[((FachadaException) e).getParametroMensagem().size()]);
					}
				}else if (e instanceof EOFException){
					mensagem  = ConstantesAplicacao.get("atencao.parametros_autoatendimento_invalidos");
				}
				
				if(mensagem == null && parametrosArray !=null && parametrosArray.length > 0){
					mensagem = ConstantesAplicacao.get(e.getMessage(), parametrosArray);
				}else if(mensagem == null){
					mensagem = ConstantesAplicacao.get(e.getMessage());
				}
				
				
				JAXBContext jc = JAXBContext.newInstance(String.class);
				
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName("mensagemErro"), String.class, mensagem);
				marshaller.marshal(jaxbElement, out);
				
				System.out.println("ERROR:SOLICITACAO REQUISICAO AUTOATENDIMENTO - MessagemErro = " + mensagem);
				out.flush();
			} catch (Exception e1) {
				System.out.println("ERROR:SOLICITACAO REQUISICAO AUTOATENDIMENTO: " + e.getMessage());
			}			
		}finally{
			if (in != null) {
				try {
					in.close();					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null){
				try {					
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
		
	}
	
	/**
	 * Armazenar coordenadas do usuario CompGis.
	 * 
	 * @author Anderson Cabral
	 * @date 10/01/2014
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoInserirCoordenadas(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO INSERIR/ATUALIZAR COORDENADAS");
		
		String logradouroBairroId = null;
		if ( request.getParameter("logradouroBairroId") != null && !request.getParameter("logradouroBairroId").equals("") ) {
			logradouroBairroId = request.getParameter("logradouroBairroId");
		}
		
		String loginUsuario = null;
		if ( request.getParameter("loginUsuario") != null && !request.getParameter("loginUsuario").equals("") ) {
			loginUsuario = (String) request.getParameter("loginUsuario");
		} else {
			loginUsuario = din.readUTF();	
		}
		
		String coordenadaX = null;
		if ( request.getParameter("coordenadaX") != null && !request.getParameter("coordenadaX").equals("") ) {
			coordenadaX = (String) request.getParameter("coordenadaX");
		} else {
			coordenadaX = din.readUTF();	
		}
		
		String coordenadaY = null;
		if ( request.getParameter("coordenadaY") != null && !request.getParameter("coordenadaY").equals("") ) {
			coordenadaY = (String) request.getParameter("coordenadaY");
		} else {
			coordenadaY = din.readUTF();	
		}
		
		if(logradouroBairroId != null && !Util.verificaSeNumeroNatural(logradouroBairroId)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		Usuario usuario = pesquisarUsuario(loginUsuario);
		
		if(validarParametros(logradouroBairroId, loginUsuario, coordenadaX, coordenadaY, usuario)){
			String msg = "Coordenadas Atualizadas com sucesso.";
			
			usuario = pesquisarUsuario(loginUsuario);
			
			CoordenadasCompGis coordenadasCompGis = pesquisarCoordenadasCompGis(usuario.getId());
			if(coordenadasCompGis == null || coordenadasCompGis.getId() == null){
				coordenadasCompGis = new CoordenadasCompGis();
				msg = "Coordenadas Inseridas com sucesso.";
			}
			coordenadasCompGis.setCoordenadaX(Util.formatarStringParaBigDecimal(coordenadaX));
			coordenadasCompGis.setCoordenadaY(Util.formatarStringParaBigDecimal(coordenadaY));
			if(logradouroBairroId != null){
				LogradouroBairro logradouroBairro = pesquisarLogradouroBairro(logradouroBairroId);
				coordenadasCompGis.setLogradouroBairro(logradouroBairro);
			}else{
				coordenadasCompGis.setLogradouroBairro(null);
			}
			coordenadasCompGis.setUsuario(usuario);
			coordenadasCompGis.setUltimaAlteracao(new Date());
			
			getFachada().inserirOuAtualizar(coordenadasCompGis);
			
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(String.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName("Sucesso"), String.class, msg);
			marshaller.marshal(jaxbElement, out);
			
			out.flush();
			
			System.out.println("FIM: INSERIR/ATUALIZAR COORDENADAS");
			
		}
	}
	
	private boolean validarParametros(String logradouroBairroId,  String loginUsuario, String coordenadaX, String coordenadaY, Usuario usuario){
			boolean retorno = false;
			//Verifica se os parametros foram informados
			if(loginUsuario == null || loginUsuario.equals("")){
				throw new ActionServletException("atencao.campo.nao.informado", null, "Login do Usuário");
			}else if(coordenadaX == null || coordenadaX.equals("")){
				throw new ActionServletException("atencao.campo.nao.informado", null, "Coordenada X");
			}else if(coordenadaY == null || coordenadaY.equals("")){
				throw new ActionServletException("atencao.campo.nao.informado", null, "Coordenada Y");
			}else{
				
				if ( logradouroBairroId != null && !logradouroBairroId.equals("")) {

					LogradouroBairro logradouroBairro = pesquisarLogradouroBairro(logradouroBairroId);
					
					//verifica se o id do logradouro bairro e valido 
					if (logradouroBairro != null && logradouroBairro.getId() != null){
						if(logradouroBairro.getBairro().getIndicadorUso().equals(ConstantesSistema.NAO)){
							throw new ActionServletException("atencao.bairro.inativo");
						}
						else if(logradouroBairro.getLogradouro().getIndicadorUso().equals(ConstantesSistema.NAO)){
							throw new ActionServletException("atencao.logradouro.inativo");
						}
					}else{
						throw new ActionServletException("atencao.logradouro.bairro.nao.existe");
					}
				}
				
				//verifica se o id do usuario e valido
				if(usuario == null || usuario.getId() == null){
					throw new ActionServletException("atencao.usuario.nao.existe");
				}
				//verifica se foi digitado caracteres que nao sejam numeros
				else if(!Util.isBigDecimal(coordenadaX) || !Util.isBigDecimal(coordenadaY)){
					throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
				}
				//Valida o tamanho das coordenadas
				else if((!coordenadaX.contains(".") && coordenadaX.length() > 12) || 
						(coordenadaX.contains(".") && (coordenadaX.split("\\.")[0].length() > 12 || coordenadaX.split("\\.")[1].length() > 16)) ||
						(!coordenadaY.contains(".") && coordenadaY.length() > 12) || 
						(coordenadaY.contains(".") && (coordenadaY.split("\\.")[0].length() > 12 || coordenadaY.split("\\.")[1].length() > 16))){
					throw new ActionServletException("atencao.coordenadas_tamanho_excedido");
				}else{
					retorno = true;
				}
			}
			
			return retorno;
	}
	
	private LogradouroBairro pesquisarLogradouroBairro(String idLogradouroBairro){
		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID, Integer.parseInt(idLogradouroBairro)));
		Collection<LogradouroBairro> colecaoLogradouroBairro = getFachada().pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
		return (LogradouroBairro) Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
	}
	
	private Usuario pesquisarUsuario(String loginUsuario){
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
		Collection<Usuario> colecaoUsuario = getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
		return (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
	}
	
	private CoordenadasCompGis pesquisarCoordenadasCompGis(Integer idUsuario){
		FiltroCoordenadasCompGis filtroCoordenadasCompGis = new FiltroCoordenadasCompGis();
		filtroCoordenadasCompGis.adicionarParametro(new ParametroSimples(FiltroCoordenadasCompGis.USUARIO_ID, idUsuario));
		Collection<CoordenadasCompGis> colecaoCoordenadasCompGis = getFachada().pesquisar(filtroCoordenadasCompGis, CoordenadasCompGis.class.getName());
		return (CoordenadasCompGis) Util.retonarObjetoDeColecao(colecaoCoordenadasCompGis);
	}

}
