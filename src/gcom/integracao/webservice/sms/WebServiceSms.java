package gcom.integracao.webservice.sms;

import gcom.atendimentopublico.ParametrosSms;
import gcom.fachada.Fachada;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WebServiceSms {

	/*
	 * Classe que conssume o webservice playSms
	 * 
	 * https://github.com/antonraharja/playSMS/wiki/Webservices
	 * http://playsms.org/
	 */
	
	ParametrosSms parametrosSms = Fachada.getInstancia().pesquisarParametrosSms();
   
	private final String ENCODER = "UTF-8";
	public final static int LIMITE_CARACTER_SMS = 160;
	public final static int QUANTIDADE_DIGITOS_TELEFONE = 10;
	
	
	
    /**
	 * [UC1402] - 
	 * Metodo que envia mensagem sms para um celular por meio de um web service. 
	 * @author Carlos Chaves
	 * @since 04/12/2012
	 * @param String Mensagem
	 * @param String nroDestino
	 * @return String
	 */
    public String enviarSmsWebService(String mensagem,Long nroDestino){
        
    	System.out.println("MSG NÃO CODIFICADA: " +mensagem);
    	
    	mensagem = Util.retirarAcentuacao(mensagem);
    	
    	//Codifica a mensagem
    	try {
    	    mensagem = URLEncoder.encode(mensagem, ENCODER);
  		} catch (UnsupportedEncodingException e) {
  			e.printStackTrace();
  		}
    	 
    	if(parametrosSms!=null && parametrosSms.getUrlWebService()!=null ){
    		//Url de envio
        	String url = parametrosSms.getUrlWebService() +
    			    	"&to=55" +nroDestino.toString() +
    			    	"&ta=pv" +
    			        "&msg=" + mensagem;

        	System.out.println("MSG CODIFICADA: " +mensagem);
        	System.out.println("TELEFONE DESTINO: " +nroDestino.toString());
        	
        	return consumirWebService(url);
    	}else{
    		return "ERRO PARAMETROS SMS NÃO CONFIGURADO";
    	}
		
    }
        
    private String consumirWebService(String url){

    	String retorno = "";
    	
    	try {
    		
    		URL u ;
    		
    		if(parametrosSms == null 
    				|| parametrosSms.getProtocolo() == null || parametrosSms.getIpProxy() == null 
    				|| parametrosSms.getPortaProxy() == null ){

    			u = new URL(url);	
    		}else{
    			u = new URL(parametrosSms.getProtocolo(), parametrosSms.getIpProxy(), new Integer(parametrosSms.getPortaProxy()), url);
    		}
    		
    	
            URLConnection conn = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String linha;
            StringBuilder retornoSite = new StringBuilder();
            
            //Ler o retorno
            while ((linha = in.readLine()) != null) {
            	retornoSite.append(linha);
            	retornoSite.append("\n");
            }
            in.close();
            
            //Codificando para UTF8
            byte ptext[] = retornoSite.toString().getBytes();
            retorno = new String(ptext,ENCODER);
            
        } catch (Exception ex) {
    	    ex.getMessage();
    	    return retorno;
        }
        return retorno;
    }
    
}