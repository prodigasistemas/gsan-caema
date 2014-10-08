package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
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

/**[UC1534] Gerar Ordem de Servico Conexao Esgoto
 * @author: Jonathan Marcos
 * @date:12/08/2013
 */
public class BatchGerarOrdemServicoConexaoEsgotoMDB implements
MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatchGerarOrdemServicoConexaoEsgotoMDB() {
		super();
	}
	public void ejbRemove() throws EJBException {}
	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {}
	public void onMessage(Message message) {
		 if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
	            try {
	            	//Chama o metodo gerarOrdemServicoConexaoEsgoto do controladorOrdemServicoSEJB
	            	this.getControladorOrdemServico().gerarOrdemServicoConexaoEsgoto(
	            		(Integer) ((Object[]) objectMessage.getObject())[0],
	            		(Usuario) ((Object[]) objectMessage.getObject())[1],
	            		(Integer) ((Object[]) objectMessage.getObject())[2]);
	            } catch (JMSException e) {
	                System.out.println("Erro no MDB");
	                e.printStackTrace();
	            } catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
	 /**
     * Retorna o valor do Controlador de Atendimento ao Publico
	 * 
	 * @return O valor de controladorAtendimentoPublicoLocal
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {

		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
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
	
	 /**
     * Default create method
     * 
     * @throws CreateException
     */
	public void ejbCreate() {}
}
