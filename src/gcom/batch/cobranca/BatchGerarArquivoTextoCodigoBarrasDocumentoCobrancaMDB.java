package gcom.batch.cobranca;

import java.util.Collection;

import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarArquivoTextoCodigoBarrasDocumentoCobrancaMDB implements MessageDrivenBean,
		MessageListener {

	private static final long serialVersionUID = 1L;
	
	public BatchGerarArquivoTextoCodigoBarrasDocumentoCobrancaMDB(){
		super();
	}
	
	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
	
	}
	
	public void ejbRemove() throws EJBException {
		
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				
				int idFuncionalidadeIniciada = (Integer)((Object[]) objectMessage.getObject()) [0];
				
				String emailEmpresa = (String) ((Object []) objectMessage.getObject()) [1];
				
				Collection<?> idsImoveis = (Collection<?>) ((Object[]) objectMessage.getObject()) [2];
				
				if(!Util.isVazioOrNulo(idsImoveis)){
					this.getControladorCobranca()
						.gerarArquivoTextoCodigoBarrasDocumentoCobranca(
								emailEmpresa,
								idsImoveis, 
								idFuncionalidadeIniciada);
					
				}
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorna o valor de ControladorCobrancaLocal
	 * 
	 * @return O valor de ControladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
	
}
