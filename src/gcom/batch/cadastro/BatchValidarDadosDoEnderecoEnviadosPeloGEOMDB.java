package gcom.batch.cadastro;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * [UC1560] - Validar Dados dos Endereços Enviados pelo GEO
 * 
 * @author Anderson Cabral
 * @date 19/09/2013
 */
public class BatchValidarDadosDoEnderecoEnviadosPeloGEOMDB implements MessageDrivenBean, MessageListener{

	private static final long serialVersionUID = 1L;
	

	public BatchValidarDadosDoEnderecoEnviadosPeloGEOMDB() {
		super();
	}

	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try{
				this.getControladorCadastro().validarDadosDoEnderecoEnviadosPeloGEO((Integer) ((Object []) objectMessage.getObject())[0]);
			}catch (ControladorException e){
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			} catch (JMSException e) {
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retorna o valor de ControladorCadastro
	 * 
	 * @return ControladorCadastro
	 */
	private ControladorCadastroLocal getControladorCadastro(){
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;
		
		ServiceLocator locator = null;
		try{
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
			
			local = localHome.create();
			
			return local;
		}catch (CreateException ce) {
			throw new SistemaException(ce);
		}catch (ServiceLocatorException e){
			throw new SistemaException(e);
		}
	}
	
    public void ejbCreate() {}
	public void ejbRemove() throws EJBException {}
	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {}

}
