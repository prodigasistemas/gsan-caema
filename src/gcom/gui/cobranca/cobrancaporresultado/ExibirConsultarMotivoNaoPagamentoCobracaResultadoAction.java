package gcom.gui.cobranca.cobrancaporresultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Diego Maciel
 * @date 21/05/2012
 */
public class ExibirConsultarMotivoNaoPagamentoCobracaResultadoAction extends
		GcomAction {
	/**
	 * [UC1342] Consultar Motivo de Não Pagamento na Cobrança por Resultado
	 * 
	 * Este caso de uso permite consultar os motivos de não geração dos
	 * pagamentos para a empresa de cobrança.
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirMotivoNaoPagamentoCobrancaResultado");

		ConsultarMotivoNaoPagamentoCobracaResultadoActionForm form = (ConsultarMotivoNaoPagamentoCobracaResultadoActionForm) actionForm;

		SistemaParametro sistemaParametro = this.getFachada()
				.pesquisarParametrosDoSistema();

		Integer anoMesInicial = null;
		Integer anoMesFinal = null;

		if (form.getReferenciaInicial() != null
				&& !form.getReferenciaInicial().equals("")
				&& form.getReferenciaFinal() != null
				&& !form.getReferenciaFinal().equals("")) {

			String anoMesReferenciaInicial = form.getReferenciaInicial();
			String anoMesReferenciaFinal = form.getReferenciaFinal();

			anoMesInicial = Util
					.formatarMesAnoComBarraParaAnoMes(anoMesReferenciaInicial);

			anoMesFinal = Util
					.formatarMesAnoComBarraParaAnoMes(anoMesReferenciaFinal);
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		String idImovel = httpServletRequest.getParameter("idImovel");
		String codigoImovel = form.getCodigoImovel();

		List<Integer> idsComandos = new ArrayList<Integer>();
		Integer referenciaInicial = null;
		Integer referenciaFinal = null;

		// cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		if (form.getIdImovel() != null) {
			idImovel = form.getIdImovel();
		}

		if (form.getIdsComandos() != null && form.getIdsComandos().length > 0) {
			String[] comandos = form.getIdsComandos();
			for (int i = 0; i < comandos.length; i++) {
				if (!comandos[i].equals("-1")) {
					idsComandos.add(new Integer(comandos[i]));
				}
			}
		}
		// Referência Inicial

		if (form.getReferenciaInicial() != null
				&& !form.getReferenciaInicial().equals("")) {
			peloMenosUmParametroInformado = true;

			referenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(form
					.getReferenciaInicial());
		}

		// Referência Final

		if (form.getReferenciaFinal() != null
				&& !form.getReferenciaFinal().equals("")) {
			peloMenosUmParametroInformado = true;

			referenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(form
					.getReferenciaFinal());
		}

		// Passa o paramentro limpar
		if (httpServletRequest.getParameter("limpar") != null
				&& httpServletRequest.getParameter("limpar").equalsIgnoreCase(
						"sim")) {

			sessao.removeAttribute("colecaoMotivosNaoPagamentoCobrancaoResultadoHelper");

		}
	
		
		if (anoMesInicial != null && !anoMesInicial.equals("")) {

			// compara data com sistema parametro
			if (sistemaParametro.getAnoMesArrecadacao().intValue() < anoMesInicial
					.intValue()) {
				throw new ActionServletException(
						"atencao.mes_ano.motivo.nao.pagamento.arrecadacao.inferior", null, ""
								+ Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao()));
			}
		}
		if (anoMesFinal != null && !anoMesFinal.equals("")) {

			// compara data com sistema parametro
			if (sistemaParametro.getAnoMesArrecadacao().intValue() < anoMesFinal
					.intValue()) {
				throw new ActionServletException(
						"atencao.mes_ano.motivo.nao.pagamento.arrecadacao.inferior", null, ""
								+ Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao()));
			}
		}

		Imovel imovel = null;

		if (idImovel != null && !idImovel.trim().equals("")) {
			imovel = this.getFachada().pesquisarImovel(new Integer(idImovel));
			if (imovel != null) {

				form.setMatriculaImovel(imovel.getId().toString());
				form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao()
						.getDescricao());
				form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao()
						.getDescricao());
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				form.setClienteUsuario("");
				form.setCpfCnpjCliente("");

				Cliente c = Fachada.getInstancia()
						.pesquisarClienteUsuarioImovelExcluidoOuNao(
								imovel.getId());
				if (c != null)
					form.setClienteUsuario(c.getNome());
				if (c.getCpfFormatado() != null)
					form.setCpfCnpjCliente(c.getCpfFormatado());
				if (c.getCnpjFormatado() != null)
					form.setCpfCnpjCliente(c.getCnpjFormatado());

				Collection<Integer> comandos = Fachada.getInstancia()
						.pesquisarComandosPorImovel(imovel.getId());
				if (comandos != null && !comandos.isEmpty()) {
					Collection<MotivoNaoPagamentoCobrancaResultadoHelper> helpers = new ArrayList<MotivoNaoPagamentoCobrancaResultadoHelper>();

					for (Integer comando : comandos) {
						MotivoNaoPagamentoCobrancaResultadoHelper helper = new MotivoNaoPagamentoCobrancaResultadoHelper();
						helper.setComando(comando.toString());

						helpers.add(helper);
					}

					sessao.setAttribute("colecaoHelper", helpers);
				}

				httpServletRequest.setAttribute("exibeDados", true);

				sessao.setAttribute("imovel", imovel);
			} else {
				throw new ActionServletException("atencao.imovel.inexistente");

			}

			// usuário efetua a filtragem
			if (httpServletRequest.getParameter("pesquisarDadosImovel") != null
					&& httpServletRequest.getParameter("pesquisarDadosImovel")
							.toString().trim().equalsIgnoreCase("sim")) {

				Collection<MotivosNaoPagamentoCobrancaoResultadoHelper> colecaoMotivosNaoPagamentoCobrancaoResultadoHelper = fachada
						.pesquisarMotivoNaoPagamentoCobrancaResultado(
								new Integer(idImovel), idsComandos,
								referenciaInicial, referenciaFinal);

				if (colecaoMotivosNaoPagamentoCobrancaoResultadoHelper != null
						&& !colecaoMotivosNaoPagamentoCobrancaoResultadoHelper
								.isEmpty()) {

					sessao.setAttribute(
							"colecaoMotivosNaoPagamentoCobrancaoResultadoHelper",
							colecaoMotivosNaoPagamentoCobrancaoResultadoHelper);

				} else {
					sessao.removeAttribute("colecaoMotivosNaoPagamentoCobrancaoResultadoHelper");

					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado");
				}

			}

		}
		else{
			form.setMatriculaImovel("");
			form.setSituacaoLigacaoAgua("");
			form.setSituacaoLigacaoEsgoto("");
			form.setInscricaoImovel("");
			form.setClienteUsuario("");
			form.setCpfCnpjCliente("");
			form.setClienteUsuario("");
			form.setCpfCnpjCliente("");			
			form.setCpfCnpjCliente("");
			form.setReferenciaFinal("");
			form.setReferenciaInicial("");
			sessao.removeAttribute("colecaoHelper");
			
		}

		return retorno;
	}

}
