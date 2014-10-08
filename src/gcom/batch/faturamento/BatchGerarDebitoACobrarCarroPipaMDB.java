package gcom.batch.faturamento;

import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.micromedicao.Rota;
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
 * [UC1564] Gerar Débito A Cobrar Carro-Pipa
 *  
 * @author Mariana Victor
 * @created 14/10/2013
 */
public class BatchGerarDebitoACobrarCarroPipaMDB implements MessageDrivenBean,
MessageListener{

	private static final long serialVersionUID = 1L;

	public BatchGerarDebitoACobrarCarroPipaMDB(){
		super();
	}

	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){

			ObjectMessage objectMessage = (ObjectMessage) message;
			try{
				this.getControladorFaturamento()
					.gerarDebitoACobrarCarroPipa(
						(Integer) ((Object []) objectMessage.getObject())[0],
						(Rota)    ((Object []) objectMessage.getObject())[1],
						(Integer)    ((Object []) objectMessage.getObject())[2]);

			}catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorna o valor de controladorFaturamento
	 * 
	 * @return O valor de controladorFaturamento
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void ejbCreate() {

	}

}
