/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContratoTarifa;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0507] ATUALIZAR CONTRATO DE ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 11/04/2007
 * 
 * Modificado por:
 * @author Filipe Selva 
 * @date 13/06/2013
 * 
 */

public class ExibirAtualizarContratoArrecadadorAction extends GcomAction {

	private Collection colecaoPesquisa;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarContratoArrecadador");

		AtualizarContratoArrecadadorActionForm atualizarContratoArrecadadorActionForm = (AtualizarContratoArrecadadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//[SB0003] - Bloquear tamanho m�ximo para identifica��o do im�vel.
		boolean permissaoAlterarTamanhoMaximo = fachada.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_TAM_MAXIMO_PARA_IDENT_DO_IMOVEL_NO_CONTRATO_DE_ARRECADADOR, usuarioLogado);		
		sessao.setAttribute("permissaoAlterarTamanhoMaximo", permissaoAlterarTamanhoMaximo);		
				
		String idContratoArrecadador = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		if (idContratoArrecadador == null) {

			if (sessao.getAttribute("idRegistroAtualizacao") != null) {
				idContratoArrecadador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if (idContratoArrecadador == null) {
				idContratoArrecadador = (String) httpServletRequest
						.getAttribute("idRegistroAtualizacao");
			}

		}else {
			sessao.setAttribute("idRegistroAtualizacao", idContratoArrecadador);
			sessao.setAttribute("i", true);
		}

		// Arrecadador
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		// Ordena filtro de arrecadador por id do cliente
		filtroArrecadador.setCampoOrderBy(FiltroArrecadador.CLIENTE_ID);
		// Inclui a objeto de cliente no filtro de arrecadador
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		// Preenche colecao de arrecadador
		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());
		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Arrecadador");
		} else {
			FiltroCliente filtroCliente = new FiltroCliente();
			Iterator iteratorColecaoArrecadador = colecaoArrecadador.iterator();
			Cliente cliente = new Cliente();
			while (iteratorColecaoArrecadador.hasNext()) {
				Arrecadador arrecadador = (Arrecadador) iteratorColecaoArrecadador
						.next();
				cliente = arrecadador.getCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, cliente.getId(),
						ParametroSimples.CONECTOR_OR));
			}
			Collection colecaoClienteArrecadador = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());
			sessao.setAttribute("colecaoClienteArrecadador",
					colecaoClienteArrecadador);
		}

		Collection collectionArrecadadorContrato = (Collection) httpServletRequest
				.getAttribute("colecaoArrecadadorContrato");
		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) Util
				.retonarObjetoDeColecao(collectionArrecadadorContrato);

		String idCliente = null;

		// ///////////////////// VALIDACAO DE CLIENTE ///////////////////
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// Cliente
			case 1:
				// Recebe o valor do campo bancoID do formul�rio.
				idCliente = atualizarContratoArrecadadorActionForm
						.getIdCliente();

				FiltroCliente filtroCliente1 = new FiltroCliente();

				filtroCliente1.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));

				filtroCliente1.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Cliente
				colecaoPesquisa = fachada.pesquisar(filtroCliente1,
						Cliente.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Limpa o campo clienteID do formul�rio
					atualizarContratoArrecadadorActionForm.setIdCliente("");
					atualizarContratoArrecadadorActionForm
							.setNomeCliente("Cliente inexistente.");
					httpServletRequest.setAttribute("existeCliente",
							"exception");

					httpServletRequest.setAttribute("nomeCampo", "clienteID");

				} else {

					Cliente objetoCliente = (Cliente) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					atualizarContratoArrecadadorActionForm.setIdCliente(String
							.valueOf(objetoCliente.getId()));
					atualizarContratoArrecadadorActionForm
							.setNomeCliente(objetoCliente.getDescricao());

					httpServletRequest.setAttribute("existeCliente", "valor");
					httpServletRequest.setAttribute("nomeCampo", "clienteID");
				}
				break;
			default:

				break;
			}
		}

		// Verificar se o n�mero do cliente n�o est� cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente == null || colecaoCliente.isEmpty()) {

				atualizarContratoArrecadadorActionForm.setIdCliente("");
				httpServletRequest.setAttribute("existeCliente", "exception");

				//throw new ActionServletException("atencao.cliente.inexistente");

			} else {

				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);

				// [FS0004]-Verificar se pessoa f�sica
				if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
						&& cliente.getClienteTipo()
								.getIndicadorPessoaFisicaJuridica().equals(
										new Short("2"))) {

					throw new ActionServletException(
							"atencao.cliente_arrecadador_pessoa_fisica");
				}

				atualizarContratoArrecadadorActionForm.setIdCliente(cliente
						.getId().toString());
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		// ///////////////////// FIM VALIDACAO DE CLIENTE ///////////////////

		if (idContratoArrecadador != null
				&& !idContratoArrecadador.trim().equals("") &&

				Integer.parseInt(idContratoArrecadador) > 0) {

			FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();

			// Adiciona entidade estrangeira para carregamento do objeto
			// "CLIENTE"
			// (ou seja, em ARRECADADOR existe um atributo do tipo Cliente,
			// ent�o � preciso carregar o cliente)
			// o mesmo para Imovel.

			filtroArrecadadorContrato
					.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
			filtroArrecadadorContrato
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.ID, idContratoArrecadador));
			Collection<ArrecadadorContrato> colecaoArrecadadorContrato = fachada
					.pesquisar(filtroArrecadadorContrato,
							ArrecadadorContrato.class.getName());

			if (colecaoArrecadadorContrato != null
					&& !colecaoArrecadadorContrato.isEmpty()) {
				arrecadadorContrato = (ArrecadadorContrato) Util
						.retonarObjetoDeColecao(colecaoArrecadadorContrato);
				atualizarContratoArrecadadorActionForm
						.setIdClienteCombo(arrecadadorContrato.getArrecadador()
								.getCliente().getId().toString());
				atualizarContratoArrecadadorActionForm
						.setIdArrecadador(arrecadadorContrato.getArrecadador()
								.getId().toString());
				// atualizarContratoArrecadadorActionForm.setNomeCliente(arrecadadorContrato.getArrecadador().getCliente().getNome());
			}
		}

		atualizarContratoArrecadadorActionForm
				.setNumeroContrato(arrecadadorContrato.getNumeroContrato());

		ContaBancaria contaBancariaArrecadacao = arrecadadorContrato
				.getContaBancariaDepositoArrecadacao();
		if (contaBancariaArrecadacao != null) {
			String idContaBancaria = contaBancariaArrecadacao.getId()
					.toString();
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID, idContaBancaria));
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroContaBancaria, ContaBancaria.class.getSimpleName());

			contaBancariaArrecadacao = (ContaBancaria) coll.iterator().next();
			atualizarContratoArrecadadorActionForm
					.setBcoArrecadadorConta(contaBancariaArrecadacao
							.getAgencia().getBanco().getId().toString());
			atualizarContratoArrecadadorActionForm
					.setAgArrecadadorConta(contaBancariaArrecadacao
							.getAgencia().getCodigoAgencia());
			atualizarContratoArrecadadorActionForm
					.setNumeroArrecadadorConta(contaBancariaArrecadacao
							.getNumeroConta());
		}

		ContaBancaria contaBancariaTarifa = arrecadadorContrato
				.getContaBancariaDepositoTarifa();
		if (contaBancariaTarifa != null) {
			String idContaBancaria = contaBancariaTarifa.getId().toString();
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID, idContaBancaria));
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroContaBancaria, ContaBancaria.class.getSimpleName());

			contaBancariaTarifa = (ContaBancaria) coll.iterator().next();
			atualizarContratoArrecadadorActionForm
					.setBcoTarifaConta(contaBancariaTarifa.getAgencia()
							.getBanco().getId().toString());
			atualizarContratoArrecadadorActionForm
					.setAgTarifaConta(contaBancariaTarifa.getAgencia()
							.getCodigoAgencia());
			atualizarContratoArrecadadorActionForm
					.setNumeroTarifaConta(contaBancariaTarifa.getNumeroConta());
		}

		if (idCliente == null || idCliente.trim().equals("")) {
			atualizarContratoArrecadadorActionForm
					.setIdCliente(arrecadadorContrato.getCliente().getId()
							.toString());
			atualizarContratoArrecadadorActionForm
					.setNomeCliente(arrecadadorContrato.getCliente().getNome());
		}

		atualizarContratoArrecadadorActionForm
				.setIdConvenio(arrecadadorContrato.getCodigoConvenio());

		if (arrecadadorContrato.getIndicadorCobrancaIss() != null
				&& !arrecadadorContrato.getIndicadorCobrancaIss().toString()
						.trim().equals("")) {
			atualizarContratoArrecadadorActionForm
					.setIndicadorCobranca(arrecadadorContrato
							.getIndicadorCobrancaIss().toString());
		}
		if (arrecadadorContrato.getDataContratoInicio() != null) {
			atualizarContratoArrecadadorActionForm.setDtInicioContrato(Util
					.formatarData(arrecadadorContrato.getDataContratoInicio()));
		}
		if (arrecadadorContrato.getDataContratoFim() != null) {
			atualizarContratoArrecadadorActionForm.setDtFimContrato(Util
					.formatarData(arrecadadorContrato.getDataContratoFim()));
		}
		if (arrecadadorContrato.getDataContratoEncerramento() != null) {
			atualizarContratoArrecadadorActionForm
					.setDataContratoEncerramento(Util
							.formatarData(arrecadadorContrato
									.getDataContratoEncerramento()));
		}
		
		//Inicio altera��o - Exibe e-mail na tela de atualizar contrato arrecadador
		if (arrecadadorContrato.getDescricaoEmail() != null) {
			atualizarContratoArrecadadorActionForm
				.setDescricaoEmail(arrecadadorContrato.getDescricaoEmail());
		}
		//Fim altera��o
		
		// Motivo Cancelamento
		FiltroMotivoCancelamento filtroMotivoCancelamento = new FiltroMotivoCancelamento();

		// Ordena filtro de motivo cancelamento
		filtroMotivoCancelamento
				.setCampoOrderBy(FiltroMotivoCancelamento.INDICADOR_USO);

		// Preenche colecao de motivos
		Collection<FiltroMotivoCancelamento> colecaoFiltroMotivoCancelamento = fachada
				.pesquisar(filtroMotivoCancelamento,
						ContratoMotivoCancelamento.class.getName());

		if (colecaoFiltroMotivoCancelamento == null
				|| colecaoFiltroMotivoCancelamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Motivo Cancelamento");
		} else {
//			ContratoMotivoCancelamento contratoMotivoCancelamento = (ContratoMotivoCancelamento) Util
//					.retonarObjetoDeColecao(colecaoFiltroMotivoCancelamento);
			sessao.setAttribute("colecaoFiltroMotivoCancelamento",
					colecaoFiltroMotivoCancelamento);

			if(arrecadadorContrato.getContratoMotivoCancelamento() != null && arrecadadorContrato.getContratoMotivoCancelamento().getId() != null){
				atualizarContratoArrecadadorActionForm
						.setContratoMotivoCancelamento(arrecadadorContrato.getContratoMotivoCancelamento().getId().toString());
			}
		}
		atualizarContratoArrecadadorActionForm
				.setTamanhoMaximoIdentificacaoImovel(arrecadadorContrato
						.getTamanhoMaximoIdentificacaoImovel().toString());
		sessao.setAttribute("arrecadadorContrato", arrecadadorContrato);

		/**
		 * Atualizar Arrecadador Contrato Tarifa
		 * @date 12/06/09
		 * @author Arthur Carvalho
		 */
		if (atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() == null
				|| atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("")) {

			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.setCampoOrderBy(FiltroArrecadacaoForma.CODIGO);
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );

			if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
				throw new ActionServletException(

				"atencao.pesquisa.nenhum_registro_tabela", null, "Forma de Arrecada��o");

			} else {

				sessao.setAttribute("colecaoFormaArrecadacao", colecaoArrecadacaoForma);
			}
		}
		
		//Caso volte a funcionalidade e seja feito uma nova pesquisa, limpar as tarifas que ficam na sess�o
		//e n�o entrar novamente nesse metodo, a nao ser que seja a primeira vez que carregue a pagina.
		if ( sessao.getAttribute("menu") != null &&sessao.getAttribute("menu").equals("sim") ) {
			sessao.setAttribute("menu", "nao");
			sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", null);
			sessao.setAttribute("mapaArrecadadorContratoTarifaSelecionados", null);
		}
		
		ArrecadadorContratoTarifa arrecadadorContratoTarifa = new ArrecadadorContratoTarifa();	
		ArrayList<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifaSelecionados = new ArrayList<ArrecadadorContratoTarifa>();
		//Mapa com as colecoes de ArrecadadorContratoTarifa por forma
		HashMap<Integer, ArrayList<ArrecadadorContratoTarifa>> mapaArrecadadorContratoTarifa = new HashMap<Integer, ArrayList<ArrecadadorContratoTarifa>>();
		
		//*****************************************//
		if(  sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados") == null &&	httpServletRequest.getParameter("acao") == null ) {

			FiltroArrecadadorContratoTarifa filtroArrecadadorContratoTarifa = new FiltroArrecadadorContratoTarifa();
			filtroArrecadadorContratoTarifa.adicionarParametro(new ParametroSimples(FiltroArrecadadorContratoTarifa.ARRECADADOR_CONTRATO_ID, arrecadadorContrato.getId()));		
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadadorContrato");

			colecaoArrecadadorContratoTarifaSelecionados = (ArrayList<ArrecadadorContratoTarifa>) fachada.pesquisar(filtroArrecadadorContratoTarifa, ArrecadadorContratoTarifa.class.getName());
			
			//popula o mapa
			if(colecaoArrecadadorContratoTarifaSelecionados != null && !colecaoArrecadadorContratoTarifaSelecionados.isEmpty()){
				
				for(ArrecadadorContratoTarifa tarifa : colecaoArrecadadorContratoTarifaSelecionados){
					ArrayList<ArrecadadorContratoTarifa> colArrecadadorContratoTarifa = (ArrayList<ArrecadadorContratoTarifa>) mapaArrecadadorContratoTarifa.get(tarifa.getArrecadacaoForma().getId());
					
		    		if(colArrecadadorContratoTarifa != null){
		    			colArrecadadorContratoTarifa.add(tarifa);
		    		}else{
		    			colArrecadadorContratoTarifa = new ArrayList<ArrecadadorContratoTarifa>();
		    			colArrecadadorContratoTarifa.add(tarifa);
		    		}
		    		
		    		mapaArrecadadorContratoTarifa.put(tarifa.getArrecadacaoForma().getId(), colArrecadadorContratoTarifa);
				}
			}
			
		} else {
			colecaoArrecadadorContratoTarifaSelecionados = (ArrayList) sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados");
			mapaArrecadadorContratoTarifa = (HashMap) sessao.getAttribute("mapaArrecadadorContratoTarifaSelecionados");
			
		}
		//****************************************//
		
		//Forma de Arrecadacao
		if (atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() != null
				&& !"-1".equals( atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() ) ) {
			
			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao()));
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );
			
			if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()) {
				ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
				arrecadadorContratoTarifa.setArrecadacaoForma(arrecadacaoForma);
			}
			 
			
		}
		
		//Valor Tarifa
		BigDecimal valorTarifa = null;
		if (atualizarContratoArrecadadorActionForm.getValorTarifa() != null
				&& !"".equals( atualizarContratoArrecadadorActionForm.getValorTarifa() ) ) {
			valorTarifa =  Util.formatarMoedaRealparaBigDecimal( atualizarContratoArrecadadorActionForm.getValorTarifa() ) ;
			
			arrecadadorContratoTarifa.setValorTarifa( valorTarifa );
		}
		
		//Valor Tarifa Percentual
		BigDecimal valorTarifaPercentual = null;
		if ( atualizarContratoArrecadadorActionForm.getValorTarifaPercentual() != null &&
				!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
			
			valorTarifaPercentual = Util.formatarMoedaRealparaBigDecimal(atualizarContratoArrecadadorActionForm.getValorTarifaPercentual());
			arrecadadorContratoTarifa.setValorTarifaPercentual(valorTarifaPercentual);
			
		}
		
		//Numero de dias Float
		Short nmDiasFloat = null;
		if (atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() != null
				&& !"".equals( atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() ) ) {
			nmDiasFloat =  new Short( atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() ) ;
			arrecadadorContratoTarifa.setNumeroDiaFloat(nmDiasFloat);
		}
		
		//Data inicio Vigencia
		Date dtInicioVigencia = null;
		if (atualizarContratoArrecadadorActionForm.getDtInicioVigencia() != null
				&& !atualizarContratoArrecadadorActionForm.getDtInicioVigencia().equals("") ) {
			dtInicioVigencia = Util.converteStringParaDate(atualizarContratoArrecadadorActionForm.getDtInicioVigencia());
			arrecadadorContratoTarifa.setDtTarifaInicio(dtInicioVigencia);
		}
		
        //Verifica se o usuario clicou no botao adicionar
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") &&
        	!atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("-1") &&
        	( !atualizarContratoArrecadadorActionForm.getValorTarifa().equals("") || 
                	!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("") )
            && !atualizarContratoArrecadadorActionForm.getNumeroDiaFloat().equals("")
            && !atualizarContratoArrecadadorActionForm.getDtInicioVigencia().equals("")) {
        	
	        	arrecadadorContratoTarifa.setUltimaAlteracao(new Date());
	 
	        	if ( colecaoArrecadadorContratoTarifaSelecionados != null ) {
//		        	Iterator iteratorColecaoArrecadadorContratoTarifa = colecaoArrecadadorContratoTarifaSelecionados.iterator();
//		    		ArrecadadorContratoTarifa contratoTarifa = null;
		    		
		    		if (atualizarContratoArrecadadorActionForm.getValorTarifaPercentual() != null && 
		    				!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
		    			
		    			//Valida��o do valor da tarifa percentual
			    		BigDecimal valorTarifaPerc = Util.formatarMoedaRealparaBigDecimal
			    			(atualizarContratoArrecadadorActionForm.getValorTarifaPercentual());
			    		//Variaveis para comparar valorTarifaPercentual
			    		BigDecimal igualZero = new BigDecimal(0);
			    		BigDecimal maiorQue100 = new BigDecimal(100);
			    		if (valorTarifaPerc.compareTo(igualZero) == 0){
			    			
			    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
			    			
			    		}else if ( valorTarifaPerc.compareTo(maiorQue100) == 1){
			    			
			    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
			    			
			    		}
		    			
		    		}
		    		
		    		//Valida se ja existe forma de arrecadacao
//		    		while (iteratorColecaoArrecadadorContratoTarifa.hasNext()) {
//		    			
//		    			contratoTarifa = (ArrecadadorContratoTarifa) iteratorColecaoArrecadadorContratoTarifa.next();
//		    			
//		    			if ( arrecadadorContratoTarifa.getArrecadacaoForma().getId().intValue() == contratoTarifa.getArrecadacaoForma().getId().intValue() ) {
//		    				throw new ActionServletException("atencao.forma_ja_cadastrada", null ,"Tarifa de Contrato" );
//		    			} 
//		        	
//		    		}
	        	} else {
	        		colecaoArrecadadorContratoTarifaSelecionados = new ArrayList();
	        	}
	        	
				//Validar se ja existe alguma tarifa com a mesma data de vigencia da 
				for(ArrecadadorContratoTarifa arrecadador : colecaoArrecadadorContratoTarifaSelecionados){
					if(arrecadador.getDtTarifaInicio() != null && arrecadadorContratoTarifa.getArrecadacaoForma().getId().equals(arrecadador.getArrecadacaoForma().getId()) 
						&& arrecadadorContratoTarifa.getDtTarifaInicio().equals(arrecadador.getDtTarifaInicio())){
						throw new ActionServletException("atencao.data_vigencia_ja_existe");
					}
				}
	        	
				colecaoArrecadadorContratoTarifaSelecionados.add(arrecadadorContratoTarifa);
				
				//Ordena pela forma e pela data de inicio de vigencia da tarifa 
				Collections.sort(colecaoArrecadadorContratoTarifaSelecionados, new ACComparator());				
	    		
				/***Mapa com as colecoes de ArrecadadorContratoTarifa por forma***/
				ArrayList<ArrecadadorContratoTarifa> colArrecadadorContratoTarifa = (ArrayList<ArrecadadorContratoTarifa>) mapaArrecadadorContratoTarifa.get(arrecadadorContratoTarifa.getArrecadacaoForma().getId());
				
	    		if(colArrecadadorContratoTarifa != null){
	    			colArrecadadorContratoTarifa.add(arrecadadorContratoTarifa);
	    		}else{
	    			colArrecadadorContratoTarifa = new ArrayList<ArrecadadorContratoTarifa>();
	    			colArrecadadorContratoTarifa.add(arrecadadorContratoTarifa);
	    		}
	    		
	    		mapaArrecadadorContratoTarifa.put(arrecadadorContratoTarifa.getArrecadacaoForma().getId(), colArrecadadorContratoTarifa);
	    		/********************************************************************/
				
				atualizarContratoArrecadadorActionForm.setTamanhoColecao("" + colecaoArrecadadorContratoTarifaSelecionados.size());
				atualizarContratoArrecadadorActionForm.setNumeroDiaFloat("");
				atualizarContratoArrecadadorActionForm.setValorTarifa("");
				atualizarContratoArrecadadorActionForm.setValorTarifaPercentual("");
	    		atualizarContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
	    		atualizarContratoArrecadadorActionForm.setDtInicioVigencia("");
        }
        
        //Remover o Contrato Tarifa da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	if (colecaoArrecadadorContratoTarifaSelecionados.size() >= obj) {      		
        		ArrecadadorContratoTarifa arrecadadorContratoTarifaRemovido = colecaoArrecadadorContratoTarifaSelecionados.remove(obj-1);
        		
        		/**Retira do mapa**/
        		ArrayList<ArrecadadorContratoTarifa> colArrecadadorContratoTarifa = mapaArrecadadorContratoTarifa.get(arrecadadorContratoTarifaRemovido.getArrecadacaoForma().getId());	    		
	    		
        		for(int i = 0; i < colArrecadadorContratoTarifa.size(); i++){       			
        			if(colArrecadadorContratoTarifa.get(i).getDtTarifaInicio().equals(arrecadadorContratoTarifaRemovido.getDtTarifaInicio())
        					&& colArrecadadorContratoTarifa.get(i).getArrecadacaoForma().getId().equals(arrecadadorContratoTarifaRemovido.getArrecadacaoForma().getId())){
        				
        				colArrecadadorContratoTarifa.remove(i);
        				break;
        			}
        		}
        	}        	
        }
        
        if ( httpServletRequest.getParameter("desfazer") != null &&
        		httpServletRequest.getParameter("desfazer").equals("S") ) {
        
        	FiltroArrecadadorContratoTarifa filtroArrecadadorContratoTarifa = new FiltroArrecadadorContratoTarifa();
			filtroArrecadadorContratoTarifa.adicionarParametro(new ParametroSimples(FiltroArrecadadorContratoTarifa.ARRECADADOR_CONTRATO_ID, arrecadadorContrato.getId()));		
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadadorContrato");

			colecaoArrecadadorContratoTarifaSelecionados = (ArrayList) fachada.pesquisar(filtroArrecadadorContratoTarifa, ArrecadadorContratoTarifa.class.getName());
			
			//popula o mapa
			mapaArrecadadorContratoTarifa = new HashMap<Integer, ArrayList<ArrecadadorContratoTarifa>>();
			if(colecaoArrecadadorContratoTarifaSelecionados != null && !colecaoArrecadadorContratoTarifaSelecionados.isEmpty()){
				
				for(ArrecadadorContratoTarifa tarifa : colecaoArrecadadorContratoTarifaSelecionados){
					ArrayList<ArrecadadorContratoTarifa> colArrecadadorContratoTarifa = (ArrayList<ArrecadadorContratoTarifa>) mapaArrecadadorContratoTarifa.get(tarifa.getArrecadacaoForma().getId());
					
		    		if(colArrecadadorContratoTarifa != null){
		    			colArrecadadorContratoTarifa.add(tarifa);
		    		}else{
		    			colArrecadadorContratoTarifa = new ArrayList<ArrecadadorContratoTarifa>();
		    			colArrecadadorContratoTarifa.add(tarifa);
		    		}
		    		
		    		mapaArrecadadorContratoTarifa.put(tarifa.getArrecadacaoForma().getId(), colArrecadadorContratoTarifa);
				}
			}
        }
        
        sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", colecaoArrecadadorContratoTarifaSelecionados );
        sessao.setAttribute("mapaArrecadadorContratoTarifaSelecionados", mapaArrecadadorContratoTarifa );
        
        atualizarContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
		
		return retorno;
	}
	
	class ACComparator implements Comparator<ArrecadadorContratoTarifa> {
		public int compare(ArrecadadorContratoTarifa o1, ArrecadadorContratoTarifa o2) {
		       int forma = o1.getArrecadacaoForma().getDescricao().compareTo(o2.getArrecadacaoForma().getDescricao());  
		       if (forma != 0) return forma; //Caso a descricao da forma sejam diferentes, ordena pela descricao da forma.  
		       
		       //Se forem iguais, ordena pela data de tarifa inicio.  
		       int data = o1.getDtTarifaInicio().compareTo(o2.getDtTarifaInicio());  
		       if (data != 0) return data;
		       
		       return 0;
		}
	}

}
