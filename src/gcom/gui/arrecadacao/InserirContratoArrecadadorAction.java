/**
 * 
 */
/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 19/03/2007
 */
public class InserirContratoArrecadadorAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo Contrato de Arrecadador
	 * 
	 * [UC0509] InserirContratoArrecadador
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 19/03/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirContratoArrecadadorActionForm inserirContratoArrecadadorActionForm = (InserirContratoArrecadadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirInserirContratoArrecadadorAction.do");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		ArrecadadorContrato arrecadadorContrato = new ArrecadadorContrato();

		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		// Arrecadador
		if (inserirContratoArrecadadorActionForm.getIdClienteCombo() != null
				&& !inserirContratoArrecadadorActionForm.getIdClienteCombo()
						.equals("")) {
			// Inclui a obejeto de cliente no filtro de arrecadador
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			// filtra arrecadador pelo cliente
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CLIENTE_ID,
					inserirContratoArrecadadorActionForm.getIdClienteCombo()));
			// Preenche colecao de arrecadador baseado no cliente escolhido
			Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {
				Iterator iteratorColecaoArrecadador = colecaoArrecadador
						.iterator();
				while (iteratorColecaoArrecadador.hasNext()) {
					Arrecadador arrecadador = (Arrecadador) iteratorColecaoArrecadador
							.next();
					arrecadadorContrato.setArrecadador(arrecadador);
				}
			} else {
				arrecadadorContrato.setArrecadador(null);
			}
		}

		// [FS0007]-Verificar existência do contrato de arrecadador
		String numeroContrato = inserirContratoArrecadadorActionForm
				.getNumeroContrato();

		// Numero de Contrato
		arrecadadorContrato
				.setNumeroContrato(inserirContratoArrecadadorActionForm
						.getNumeroContrato());

		// Conta Deposito Arrecadador
		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		if (inserirContratoArrecadadorActionForm
				.getIdContaBancariaArrecadador() != null
				&& !inserirContratoArrecadadorActionForm
						.getIdContaBancariaArrecadador().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					inserirContratoArrecadadorActionForm
							.getIdContaBancariaArrecadador()));
			Collection<ContaBancaria> colecaoContaBancariaArrecadador = fachada
					.pesquisar(filtroContaBancaria, ContaBancaria.class
							.getName());
			if (colecaoContaBancariaArrecadador != null
					&& !colecaoContaBancariaArrecadador.isEmpty()) {
				Iterator iteratorColecaoContaBancariaArrecadador = colecaoContaBancariaArrecadador
						.iterator();
				while (iteratorColecaoContaBancariaArrecadador.hasNext()) {
					ContaBancaria contaBancariaArrecadador = (ContaBancaria) iteratorColecaoContaBancariaArrecadador
							.next();
					arrecadadorContrato
							.setContaBancariaDepositoArrecadacao(contaBancariaArrecadador);
				}
			} else {
				arrecadadorContrato.setContaBancariaDepositoArrecadacao(null);
			}
		}

		// Conta Deposito Tarifa
		filtroContaBancaria = new FiltroContaBancaria();
		if (inserirContratoArrecadadorActionForm.getIdContaBancariaTarifa() != null
				&& !inserirContratoArrecadadorActionForm
						.getIdContaBancariaTarifa().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					inserirContratoArrecadadorActionForm
							.getIdContaBancariaTarifa()));
			Collection<ContaBancaria> colecaoContaBancariaTarifa = fachada
					.pesquisar(filtroContaBancaria, ContaBancaria.class
							.getName());
			if (colecaoContaBancariaTarifa != null
					&& !colecaoContaBancariaTarifa.isEmpty()) {

				Iterator iteratorColecaoContaBancariaTarifa = colecaoContaBancariaTarifa
						.iterator();
				while (iteratorColecaoContaBancariaTarifa.hasNext()) {
					ContaBancaria contaBancariaTarifa = (ContaBancaria) iteratorColecaoContaBancariaTarifa
							.next();
					arrecadadorContrato
							.setContaBancariaDepositoTarifa(contaBancariaTarifa);
				}
			} else {
				arrecadadorContrato.setContaBancariaDepositoTarifa(null);
			}
		}
		// Cliente
		Cliente cliente = new Cliente();
		cliente.setId(new Integer(inserirContratoArrecadadorActionForm
				.getIdCliente()));

		// [FS0004]-Verificar se pessoa física
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				cliente.getId()));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		Collection colecaoCliente = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());
		Cliente clientePesq = (Cliente) Util
				.retonarObjetoDeColecao(colecaoCliente);

		if (clientePesq.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
				&& clientePesq.getClienteTipo()
						.getIndicadorPessoaFisicaJuridica().equals(
								new Short("2"))) {
			throw new ActionServletException(
					"atencao.cliente_arrecadador_pessoa_fisica");
		}

		arrecadadorContrato.setCliente(cliente);

		// Código Convenio
		arrecadadorContrato
				.setCodigoConvenio(inserirContratoArrecadadorActionForm
						.getIdConvenio());

		// IndicadorCobrancaISS
		if (inserirContratoArrecadadorActionForm.getIndicadorCobranca() != null) {
			arrecadadorContrato
					.setIndicadorCobrancaIss(new Short(
							inserirContratoArrecadadorActionForm
									.getIndicadorCobranca()));
		} else {
			arrecadadorContrato.setIndicadorCobrancaIss(null);
		}
		// Intervalo de Datas do Contrato
		arrecadadorContrato.setDataContratoInicio(Util
				.converteStringParaDate(inserirContratoArrecadadorActionForm
						.getDtInicioContrato()));
		arrecadadorContrato.setDataContratoFim(Util
				.converteStringParaDate(inserirContratoArrecadadorActionForm
						.getDtFimContrato()));

		arrecadadorContrato.setTamanhoMaximoIdentificacaoImovel(new Integer(
				inserirContratoArrecadadorActionForm
						.getTamanhoMaximoIdentificacaoImovel()).shortValue());

		String emailCliente = inserirContratoArrecadadorActionForm
				.getEmailCliente();

		if (emailCliente != null && !emailCliente.trim().equals("")) {
			arrecadadorContrato.setDescricaoEmail(emailCliente);
		} else {
			arrecadadorContrato.setDescricaoEmail(null);
		}
		
		boolean arrecadadorJaExiste = fachada.pesquisarContratoArrecadadorPorArrecadador(arrecadadorContrato.getArrecadador().getId(), null);
		
		if(arrecadadorJaExiste){
			throw new ActionServletException("atencao.arrecadador_ja_existe");
		}
		
		ArrayList<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifaComDataFim = new ArrayList<ArrecadadorContratoTarifa>();
		ArrayList<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa = (ArrayList<ArrecadadorContratoTarifa>) sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados");		
		
		//Verificar se tem alguma tarifa com data menor que a data de inicio do contrato ou maior que a data fim
		if(arrecadadorContrato.getDataContratoInicio() != null || arrecadadorContrato.getDataContratoFim() != null){
			for(ArrecadadorContratoTarifa arrecadadorContratoTarifa : colecaoArrecadadorContratoTarifa){
				
				if(arrecadadorContrato.getDataContratoInicio() != null){
					if(arrecadadorContratoTarifa.getDtTarifaInicio().compareTo(arrecadadorContrato.getDataContratoInicio()) == -1){
						throw new ActionServletException("atencao.data_tarifa_fora_do_intervalo");
					}
				}
				
				if(arrecadadorContrato.getDataContratoFim() != null){
					if(arrecadadorContratoTarifa.getDtTarifaInicio().compareTo(arrecadadorContrato.getDataContratoFim()) == 1){
						throw new ActionServletException("atencao.data_tarifa_fora_do_intervalo");
					}
				}
				
			}
		}
		
		
		//Mapa com as colecoes de ArrecadadorContratoTarifa por forma
		HashMap<Integer, ArrayList<ArrecadadorContratoTarifa>> mapaArrecadadorContratoTarifa = (HashMap<Integer, ArrayList<ArrecadadorContratoTarifa>>) sessao.getAttribute("mapaArrecadadorContratoTarifaSelecionados");

		if (colecaoArrecadadorContratoTarifa == null || colecaoArrecadadorContratoTarifa.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					"Arrecadador(es) Contrato(s) Tarifa(s)");
		}else{
			
			Set<Integer> idsFormasSelecionadas = mapaArrecadadorContratoTarifa.keySet();
			
			for(Integer idForma : idsFormasSelecionadas){
				ArrayList<ArrecadadorContratoTarifa> col = new ArrayList<ArrecadadorContratoTarifa>();
				
				ArrayList<ArrecadadorContratoTarifa> lista = (ArrayList<ArrecadadorContratoTarifa>) mapaArrecadadorContratoTarifa.get(idForma);	
				
				if(lista.size() > 1){
				
		    		Collections.sort(lista, new ACTarifaInicioComparator());
		    		
		    		//Adiciona na colecao a tarifa com a data de inicio vigente maior (data fim continua nula)
		    		col.add(lista.get(lista.size() - 1));
		    		
		    		//coloca a data fim das demais tarifas da forma
		    		for(int i = lista.size() - 1; i >= 1; i--){
		    			
						//retorna a tarifa com a data de vigencia maior
		    			ArrecadadorContratoTarifa arrecadadorContratoTarifa = lista.get(i);
						
						//pega a data de vigencia inicial
						Date dataInicioAtual = arrecadadorContratoTarifa.getDtTarifaInicio();
						
						//subtrai um dia da data de viagencia inicial
						Date dtTarifaFimProx = Util.subtrairNumeroDiasDeUmaData(dataInicioAtual, 1);
						
						//pega a tarifa que está acima da tarifa atual
						ArrecadadorContratoTarifa arrecadadorContratoTarifaAlterar = lista.get(i - 1);
						
						//seta a data fim
						arrecadadorContratoTarifaAlterar.setDtTarifaFim(dtTarifaFimProx);
						
						col.add(arrecadadorContratoTarifaAlterar);
		    		}
				}else{
					col.add(lista.get(0));
				}
				
				//Colecao com todos os contratos tarifa informados e com suas respectivas datas de vigencia inicial e final
				colecaoArrecadadorContratoTarifaComDataFim.addAll(col);
			}
		}
		
		Integer idContratoArrecadador = fachada.inserirContratoArrecadador(arrecadadorContrato, colecaoArrecadadorContratoTarifaComDataFim,  usuario);

		String idRegistroAtualizacao = idContratoArrecadador.toString();
		sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Arrecadador "
				+ numeroContrato + " inserido com sucesso.",
				"Inserir outro Contrato de Arrecadador",
				"exibirInserirContratoArrecadadorAction.do?menu=sim",
				"exibirAtualizarContratoArrecadadorAction.do?idRegistroInseridoAtualizar="
						+ idContratoArrecadador,
				"Atualizar Contrato de Arrecadador Inserido");

		sessao.removeAttribute("InserirContratoArrecadadorActionForm");

		return retorno;
	}
	
	class ACTarifaInicioComparator implements Comparator<ArrecadadorContratoTarifa> {
		public int compare(ArrecadadorContratoTarifa o1, ArrecadadorContratoTarifa o2) {
			return o1.getDtTarifaInicio().compareTo(o2.getDtTarifaInicio());
		}
	}

}