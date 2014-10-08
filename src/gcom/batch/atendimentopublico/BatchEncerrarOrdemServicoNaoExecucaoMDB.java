package gcom.batch.atendimentopublico;

import java.util.Collection;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

/**
 * [UC1539] Encerrar Ordem de Serviço Por Não Execução
 * 
 * @author Hugo Azevedo	
 * @date 21/08/2013
 * 
 */
public class BatchEncerrarOrdemServicoNaoExecucaoMDB implements MessageDrivenBean,
		MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatchEncerrarOrdemServicoNaoExecucaoMDB() {
		super();
	}

	public void ejbRemove() throws EJBException {

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				
					//Encerrar Ordem de Servico Por Não Execução
					this.getControladorAtendimentoPublico()
							.encerrarOrdemServicoNaoExecucao(
									(Integer) ((Object[]) objectMessage.getObject())[0],  //Id Funcionalidade
									(Collection<Integer>) ((Object[]) objectMessage.getObject())[1],  //Colecao Comando
									(Integer) ((Object[]) objectMessage.getObject())[2],  //Id Faturamento Grupo
									(Usuario) ((Object[]) objectMessage.getObject())[3]   //Usuário Logado
									
					);	

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
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}

	/**
	 * Retorna o valor de controladorAtendimentoPublico
	 * 
	 * @return O valor de controladorAtendimentoPublico
	 */
	protected ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {

		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
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

}
