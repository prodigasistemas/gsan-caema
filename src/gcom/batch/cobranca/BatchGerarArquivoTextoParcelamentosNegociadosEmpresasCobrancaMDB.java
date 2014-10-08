package gcom.batch.cobranca;

import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
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

public class BatchGerarArquivoTextoParcelamentosNegociadosEmpresasCobrancaMDB implements MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BatchGerarArquivoTextoParcelamentosNegociadosEmpresasCobrancaMDB(){
		super();
	}
	
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				this.getControladorCobranca()
				.gerarArquivoTxtParcelamentosNegociadosEmpresaCobranca(
						(Integer) ((Object[]) objectMessage.getObject())[0]);

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	public void ejbCreate() {}
	
	public void ejbRemove() throws EJBException {}

	public void setMessageDrivenContext(MessageDrivenContext arg0)throws EJBException {}

	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
}
