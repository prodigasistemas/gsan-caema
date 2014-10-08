package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
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

public class BatchAtualizarSituacaoLigacaoAguaImovelFiscalizadoMDB implements
		MessageDrivenBean, MessageListener {
	
	private static final long serialVersionUID = 1L;
	
	public BatchAtualizarSituacaoLigacaoAguaImovelFiscalizadoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {

				this
						.getControladorOrdemServicoLocalHome()
						.atualizarSituacaoLigacaoAguaImovelFiscalizado(
								(Integer) ((Object[]) objectMessage.getObject())[0],
								(Integer) ((Object[]) objectMessage.getObject())[1],
								(Integer) ((Object[]) objectMessage.getObject())[2]);

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
	 * Retorna o valor de controladorOrdemServicoLocal
	 * 
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServicoLocalHome() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

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
