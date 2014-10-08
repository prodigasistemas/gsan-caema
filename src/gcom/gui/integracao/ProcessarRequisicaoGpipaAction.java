package gcom.gui.integracao;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.integracao.CarroPipaRetornoOcorrencia;
import gcom.integracao.CarroPipaRetornoTipo;
import gcom.util.Util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jonathan Marcos
 * @date 17/10/2013
 * [UC1567] Consultar Débitos Imóvel Via Webservice 
 * [UC1568] Receber Informações Abastecimentos Carros Pipa Via Webservice  
 * [Observacao] 
 * 		1 - Webservice
 */
public class ProcessarRequisicaoGpipaAction extends GcomAction {

	private static final String GPIPA = "Gpipa";
	
	//Tipos de Requisição
	private static final short CONSULTAR_DEBITO_IMOVEL = 1;
	private static final short RECEBER_DADOS_ABASTECIMENTO = 2;
	
	//Nomes dos parametros Passadas via URL
	public static final String TIPO_REQUISICAO = "tipoRequisicao";
	public static final String SEQUENCIAL = "sequencial";
	public static final String DESCRICAO_PLACA = "placaCaminhao";
	public static final String ID_IMOVEL = "idImovel";
	public static final String VOLUME_ABASTECIMENTO = "volumeAbastecimento";
	public static final String INDICADOR_COBRANCA = "indicadorCobranca";
	public static final String INDICADOR_ABASTECIMENTO_AVULSO = "indicadorAbastecimentoAvulso";
	public static final String DATA_HORA_ABASTECIMENTO = "dataHoraAbastecimento";
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
				HttpServletRequest request, HttpServletResponse response){
		
		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;
		try {
			out = response.getOutputStream();
			in = request.getInputStream();
			din = new DataInputStream(in);
			if(request.getParameter(TIPO_REQUISICAO)!=null){
				if(!request.getParameter(TIPO_REQUISICAO).toString().trim().equals("")
						&& this.validarStringComoInteger(request.getParameter(TIPO_REQUISICAO).toString())){
					if(request.getParameter(TIPO_REQUISICAO).toString().trim().equals(String.valueOf(CONSULTAR_DEBITO_IMOVEL))
							|| request.getParameter(TIPO_REQUISICAO).toString().trim()
								.equals(String.valueOf(RECEBER_DADOS_ABASTECIMENTO))){
							
							Short tipoRequisicao = Short.valueOf(request.getParameter(TIPO_REQUISICAO).toString());
							if(tipoRequisicao==CONSULTAR_DEBITO_IMOVEL){
								this.pesquisarDebitos(din, response, out, request);
							}else if(tipoRequisicao==RECEBER_DADOS_ABASTECIMENTO){
								this.inserirCarroPipaAbastecimento(din, response, out, request);
							}
						}else{
							
							CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
							CarroPipaRetornoTipo carroPipaRetornoTipo = new CarroPipaRetornoTipo();
							
							carroPipaRetornoOcorrencia.setCodigoRequisicao(Short.valueOf(request.getParameter(TIPO_REQUISICAO)));
							
							carroPipaRetornoTipo.setId(Integer.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA));
							carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
							carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(null);
							carroPipaRetornoOcorrencia.setImovel(null);
							carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
							carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
							
							Fachada.getInstancia().inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
							
							this.xmlRetorno(out,String.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA), 
								CarroPipaRetornoTipo.DESCRICAO_TIPO_REQUISICAO_INVALIDA,CarroPipaRetornoTipo.NULL);
						}
				}else{
					
					CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
					CarroPipaRetornoTipo carroPipaRetornoTipo = new CarroPipaRetornoTipo();
					
					carroPipaRetornoOcorrencia.setCodigoRequisicao(null);
					
					carroPipaRetornoTipo.setId(Integer.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA));
					carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
					carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(null);
					carroPipaRetornoOcorrencia.setImovel(null);
					carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
					carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
					
					Fachada.getInstancia().inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
					
					this.xmlRetorno(out,String.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA), 
						CarroPipaRetornoTipo.DESCRICAO_TIPO_REQUISICAO_INVALIDA,CarroPipaRetornoTipo.NULL);
				}
			}else{
				
				CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
				CarroPipaRetornoTipo carroPipaRetornoTipo = new CarroPipaRetornoTipo();
				
				carroPipaRetornoOcorrencia.setCodigoRequisicao(null);
				
				carroPipaRetornoTipo.setId(Integer.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA));
				carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
				carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(null);
				carroPipaRetornoOcorrencia.setImovel(null);
				carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
				carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
				
				Fachada.getInstancia().inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
				
				this.xmlRetorno(out,String.valueOf(CarroPipaRetornoTipo.ID_TIPO_REQUISICAO_INVALIDA),
					CarroPipaRetornoTipo.DESCRICAO_TIPO_REQUISICAO_INVALIDA,CarroPipaRetornoTipo.NULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	/*
	 * Autor : Jonathan Marcos
	 * Data : 14/10/2013
	 * [Observacao] pesquisa o(s) debito(s) do imovel
	 */
	public void pesquisarDebitos(DataInputStream din, HttpServletResponse response,
			OutputStream out, HttpServletRequest request){
		String protocolo = "";
		String idRetorno = "";
		String descricaoRetorno = "";
		try {
		
			Integer retornoValidacao = this.validarPesquisarDebitosImovel(din, response, out, request);
			
				CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
				CarroPipaRetornoTipo carroPipaRetornoTipo = new CarroPipaRetornoTipo();
				Imovel imovel = new Imovel();
				
				carroPipaRetornoOcorrencia.setCodigoRequisicao(Short.valueOf(request.
					getParameter(TIPO_REQUISICAO).toString()));
				
				carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
				
				if(retornoValidacao==CarroPipaRetornoTipo.ID_IMOVEL_SEM_DEBITO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_IMOVEL_SEM_DEBITO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_IMOVEL_SEM_DEBITO;
					imovel.setId(Integer.valueOf(request.getParameter(ID_IMOVEL).toString()));
				}else if(retornoValidacao==CarroPipaRetornoTipo.ID_IMOVEL_COM_DEBITO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_IMOVEL_COM_DEBITO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_IMOVEL_COM_DEBITO;
					imovel.setId(Integer.valueOf(request.getParameter(ID_IMOVEL).toString()));
				}else if(retornoValidacao==CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_IMOVEL_INVALIDO;
					imovel.setId(null);
				}
				
				carroPipaRetornoOcorrencia.setImovel(imovel);
		
				carroPipaRetornoTipo.setId(Integer.valueOf(idRetorno));
				carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
				
				carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
				
				protocolo = Fachada.getInstancia().
						inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia,true);
				
				if(retornoValidacao!=CarroPipaRetornoTipo.ID_IMOVEL_SEM_DEBITO){
					protocolo = CarroPipaRetornoTipo.NULL;
				}
				
				this.xmlRetorno(out, idRetorno, descricaoRetorno, protocolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/*
	 * Autor: Jonathan Marcos
	 * Data: 10/10/2013
	 * [Observacao] 
	 * 	1 - Retorna true se o imovel possui debito(s)
	 *  2 - Retorna false se o imovel nao possui debito(s)
	 */
	public boolean obterDebitos(Integer idImovel){
	
		Integer indicadorDebitoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(2);
		Integer indicadorCredito = new Integer(2);
		Integer indicadorNotas = new Integer(2);
		Integer indicadorGuias = new Integer(2);
		Integer indicadorAtualizar = new Integer(1);
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial
				.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal
				.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF = new Date();

		try {
			dataVencimentoDebitoI = formatoData.parse("01/01/0001");
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = 
				fachada.obterDebitoImovelOuCliente(
					indicadorDebitoImovel.intValue(),
					idImovel.toString(), null, null, anoMesInicial,
					anoMesFinal, dataVencimentoDebitoI,
					dataVencimentoDebitoF, indicadorPagamento.intValue(),
					indicadorConta.intValue(), indicadorDebito.intValue(),
					indicadorCredito.intValue(), indicadorNotas.intValue(),
					indicadorGuias.intValue(), indicadorAtualizar
							.intValue(), null);
		
		if (colecaoDebitoImovel == null 
				|| ((colecaoDebitoImovel.getColecaoContasValores() == null
						|| colecaoDebitoImovel.getColecaoContasValores().isEmpty())
					&& (colecaoDebitoImovel.getColecaoContasValoresImovel() == null
							|| colecaoDebitoImovel.getColecaoContasValoresImovel().isEmpty())
					)
			){
			return false;
		}
		return true;
	}
	
	/*
	 * Autor: Jonathan Marcos
	 * Data: 10/10/2013
	 * [Observacao]
	 *  1 - Esse método inserir o objeto carroPipaAbastecimento na Base
	 */
	public void inserirCarroPipaAbastecimento(DataInputStream din, HttpServletResponse response,
			OutputStream out, HttpServletRequest request){
		
		String descricaoPlaca = null;
		Integer idImovel = null;
		Integer sequencial = null;
		Integer volumeAbastecimento = null;
		Short indicadorCobranca = null;
		Short indicadorAbastecimentoAvulso = null;
		Date dataAbastecimento = null;
		
		String idRetorno = "";
		String descricaoRetorno = "";
		
		Integer idCarroPipaAbastecimento = null;
		
		CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = null;
		CarroPipaRetornoTipo carroPipaRetornoTipo = null;
		Imovel imovel = null;
		
		try {
		
			CarroPipaAbastecimentoHelper carroPipaAbastecimentoHelper = Fachada.getInstancia().
					montarHelperCarroPipaAbastecimento(request, null);
			
			if(carroPipaAbastecimentoHelper.getRetornoErro()!=null){
				if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_SEQUENCIAL_INVALIDO;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_IMOVEL_INVALIDO;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_PLACA_CAMINHAO_INVALIDA){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_PLACA_CAMINHAO_INVALIDA);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_PLACA_CAMINHAO_INVALIDA;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_DATA_HORA_ABASTECIMENTO_INVALIDA){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_DATA_HORA_ABASTECIMENTO_INVALIDA);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_DATA_HORA_ABASTECIMENTO_INVALIDA;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_VOLUME_ABASTECIMENTO_INVALIDO){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_VOLUME_ABASTECIMENTO_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_VOLUME_ABASTECIMENTO_INVALIDO;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==Integer.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_COBRANCA_INVALIDO)){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_COBRANCA_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_INDICADOR_COBRANCA_INVALIDO;
				}else if(carroPipaAbastecimentoHelper.getRetornoErro()==Integer.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO)){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO;
				}
				
				carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
				carroPipaRetornoTipo = new CarroPipaRetornoTipo();
				
				carroPipaRetornoOcorrencia.setCodigoRequisicao(Short.valueOf(request.getParameter(TIPO_REQUISICAO)));
				
				carroPipaRetornoTipo.setId(Integer.valueOf(idRetorno));
				carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
				carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(null);
				
				if(!(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO)){
					imovel = new Imovel();
					imovel.setId(Integer.valueOf(request.getParameter(ID_IMOVEL)));
				}
				
				carroPipaRetornoOcorrencia.setImovel(imovel);
				carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
				carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
				
				Fachada.getInstancia().inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
				
				this.xmlRetorno(out, idRetorno, descricaoRetorno, CarroPipaRetornoTipo.NULL);
				
			}else{
			
				sequencial = Integer.valueOf(request.getParameter(SEQUENCIAL));
				descricaoPlaca = request.getParameter(DESCRICAO_PLACA).toString();
				idImovel = Integer.valueOf(request.getParameter(ID_IMOVEL).toString());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				dataAbastecimento = simpleDateFormat.parse(request.getParameter(DATA_HORA_ABASTECIMENTO).toString());
				volumeAbastecimento = Integer.parseInt(request.getParameter(VOLUME_ABASTECIMENTO).toString());
				indicadorCobranca = Short.valueOf(request.getParameter(INDICADOR_COBRANCA).toString());
				indicadorAbastecimentoAvulso = Short.valueOf(request.getParameter(INDICADOR_ABASTECIMENTO_AVULSO).toString());
				
				
				idCarroPipaAbastecimento = Fachada.getInstancia().
						inserirCarroPipaAbastecimento(sequencial,descricaoPlaca.toUpperCase(), idImovel, dataAbastecimento, 
					volumeAbastecimento, indicadorCobranca, indicadorAbastecimentoAvulso);
				
				if(idCarroPipaAbastecimento!=null){
					idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_CADASTRO_EFETUADO_SUCESSO);
					descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_CADASTRADO_SUCESSO;
				}
				
				carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
				carroPipaRetornoTipo = new CarroPipaRetornoTipo();
				
				carroPipaRetornoOcorrencia.setCodigoRequisicao(Short.valueOf(request.getParameter(TIPO_REQUISICAO)));
				
				carroPipaRetornoTipo.setId(Integer.valueOf(idRetorno));
				carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
				carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(null);
				
				imovel = new Imovel();
				imovel.setId(Integer.valueOf(request.getParameter(ID_IMOVEL)));
				carroPipaRetornoOcorrencia.setImovel(imovel);
				
				carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
				carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
				
				Fachada.getInstancia().inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
				
				this.xmlRetorno(out, idRetorno, descricaoRetorno, CarroPipaRetornoTipo.NULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Autor: Jonathan Marcos
	 * Data: 10/10/2013
	 * [Observacao]
	 *  1 - Monta o xml de retorno com um unico parametro
	 *  	1.1 - idRetorno : id da tag xml
	 *  	1.2 - descricaoRetorno : descricao da tag xml
	 *  	1.3 - protocolo : protocolo da tag xml
	 */
	public void xmlRetorno(OutputStream out,String idRetorno,
			String descricaoRetorno,String protocolo) 
					throws IOException{
		JAXBContext context;
		RequisicaoGpipaRetorno requisicaoGpipaRetorno;
		try {
			context = JAXBContext.newInstance(RequisicaoGpipaRetorno.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			
			requisicaoGpipaRetorno = new RequisicaoGpipaRetorno();
			
			requisicaoGpipaRetorno.setCodigoRetorno(idRetorno);
			requisicaoGpipaRetorno.setDescricaoRetorno(descricaoRetorno);
			requisicaoGpipaRetorno.setProtocolo(protocolo);
			
			
			JAXBElement<RequisicaoGpipaRetorno> jaxbElement = new JAXBElement<RequisicaoGpipaRetorno>(new QName(GPIPA), RequisicaoGpipaRetorno.class, requisicaoGpipaRetorno);
			marshaller.marshal(jaxbElement, out);
			out.flush();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [Observacoes]
	 * 		1 - Parametros
	 * 			1.1 - DataInputStream din
	 * 			1.2 - HttpServletResponse response
	 * 			1.3 - OutputStream out
	 * 			1.4 - HttpServletRequest request
	 * 		2 - return : Integer idValidacao
	 */
	public Integer validarPesquisarDebitosImovel(DataInputStream din, HttpServletResponse response,
			OutputStream out, HttpServletRequest request){
		
		Integer retorno = null;
		
		if(request.getParameter(ID_IMOVEL)!=null){
			if(!request.getParameter(ID_IMOVEL).toString().trim().equals("")
					&& this.validarStringComoInteger(request.getParameter(ID_IMOVEL).toString())
					&& Fachada.getInstancia().verificarExistenciaMatriculaImovel(Integer.valueOf(request.
						getParameter(ID_IMOVEL).toString()))){
			if(!this.obterDebitos(Integer.valueOf(
				request.getParameter(ID_IMOVEL).toString()))){
				retorno = CarroPipaRetornoTipo.ID_IMOVEL_SEM_DEBITO;
				return retorno;
			}else{
				retorno = CarroPipaRetornoTipo.ID_IMOVEL_COM_DEBITO;
				return retorno;
			}
		}else{
			retorno = CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO;
		    return retorno;
		}
	 }else{
		 retorno = CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO;
		 return retorno;
	 }
	}
	
	/*
	 * Autor : Jonathan Marcos
	 * Data : 14/10/2013
	 * [Observacao] valida  a conversao de uma String para Integer
	 */
	public boolean validarStringComoInteger(String numero){
		boolean retorno = true;
		try {
			Integer numeroRetorno = new Integer(numero);
			if(numeroRetorno==0){
				retorno = false;
			}
		} catch (NumberFormatException e) {
			retorno = false;
		}
		return retorno;
	}
	
	/*
	 * Autor : Jonathan Marcos
	 * Data : 14/10/2013
	 * [Observacao] valida a data de abastecimento 
	 */
	public boolean validarDataHoraAbastecimentoInvalida(String dataHoraAbastecimento){
		
		boolean retorno = false;
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date data = simpleDateFormat.parse(dataHoraAbastecimento);
			
			if(retorno==false){
				if (dataHoraAbastecimento != null && !dataHoraAbastecimento.equals("") && dataHoraAbastecimento.length() >= 10){
					
					int anoInt = Integer.parseInt(dataHoraAbastecimento.substring(6, 10));
					int mesInt = Integer.parseInt(dataHoraAbastecimento.substring(3, 5));
					int diaInt = Integer.parseInt(dataHoraAbastecimento.substring(0, 2));
					
					if (mesInt > 12 || mesInt==0) {
						retorno = true;
					}
					if (diaInt > 31 || mesInt==0) {
						retorno = true;
					}
					if (anoInt == 0) {
						retorno = true;
					}
					
					int ultimoDiaMes = Integer.valueOf(Util.obterUltimoDiaMes(mesInt, anoInt));
					
					if (diaInt > ultimoDiaMes){
						retorno = true;
					}
					
					if (!retorno){
						
						int horaInt = 0;
						int minutoInt = 0;
						int segundoInt = 0;

						if (dataHoraAbastecimento.length() > 10 && dataHoraAbastecimento.length() == 19){
							
							horaInt = Integer.parseInt(dataHoraAbastecimento.substring(11, 13));
							minutoInt = Integer.parseInt(dataHoraAbastecimento.substring(14, 16));
							segundoInt = Integer.parseInt(dataHoraAbastecimento.substring(17, 19));
							
							if (horaInt > 23) {
								retorno = true;
							}
							if (minutoInt > 59) {
								retorno = true;
							}
							if (segundoInt > 59) {
								retorno = true;
							}
						}
						else{
							retorno = true;
						}
					}
				}
				else{
					retorno = true;
				}
			}
		} catch (Exception e) {
			retorno = true;
		}
		return retorno;
	}
}
