package gcom.batch.financeiro;

import java.util.Map;

import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
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
 * [UC1272] Gerar Arquivos EFD-PIS/Confins
 * 
 * @author Erivan Sousa
 * @date 02/07/2012
 */
public class BatchGerarArquivoEfdPisCofinsMDB implements MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoEfdPisCofinsMDB() {
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
				Integer idFuncionalidadeIniciada = (Integer) ((Object[]) objectMessage.getObject())[0];
				
    			Map parametros = (Map) ((Object[]) objectMessage.getObject())[1];
				
				int[] tipoArquivo =  (int[]) parametros.get("tipoRegistro");
				
				Integer anoMesReferencia =  (Integer) parametros.get("anoMesReferencia");
				
				boolean quebraPorMunicipio =  (Boolean) parametros.get("quebraPorMunicipio");
				
				Short indicadorTipoGeracao = (Short) parametros.get("indicadorTipoGeracao");
				
				this.getControladorFinanceiro()
						.gerarArquivoEFDPisConfins(idFuncionalidadeIniciada, tipoArquivo, 
							anoMesReferencia, quebraPorMunicipio, indicadorTipoGeracao);
						
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
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFinanceiroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
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
	public void ejbCreate() {

	}
}
