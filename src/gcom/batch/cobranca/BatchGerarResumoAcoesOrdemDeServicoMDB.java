package gcom.batch.cobranca;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.ControladorAtendimentoPublicoSEJB;
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

/**
 * Descrição da classe
 * 
 * @author Diogo Luiz
 * @date 17/09/2013
 */

public class BatchGerarResumoAcoesOrdemDeServicoMDB implements
		MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchGerarResumoAcoesOrdemDeServicoMDB() {
		super();
	}
	
	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {								
				this.getControladorAtendimentoPublico().gerarResumoAcoesOrdemDeServico(
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

	private ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		// pega a instÃ¢ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	public void ejbRemove() throws EJBException {
		
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {
		
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}

}
