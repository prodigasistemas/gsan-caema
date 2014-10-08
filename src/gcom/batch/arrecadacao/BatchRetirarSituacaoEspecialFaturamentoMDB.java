package gcom.batch.arrecadacao;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
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
 * Batch que ir� Retirar Situa��o Especial
 * De faturamento de suspens�o do faturamento por inadimplencia 
 * @author Carlos Chaves
 * @date 21/11/2012
 */
public class BatchRetirarSituacaoEspecialFaturamentoMDB implements MessageDrivenBean,MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchRetirarSituacaoEspecialFaturamentoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
	}

	public void ejbRemove() throws EJBException {

	}

	/**
	 * Default create method
	 *
	 * @throws CreateException
	 */
	public void ejbCreate() {
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				this.getControladorArrecadacao()
				.retirarSituacaoEspecialFaturamento(
                        (Integer) ((Object[]) objectMessage.getObject())[0]);

			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Retorna o valor de controladorLocalidade
	 *
	 * @return O valor de controladorLocalidade
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}