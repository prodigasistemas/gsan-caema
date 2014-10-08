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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoMotivoCancelamento;
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
 * 
 * @author K�ssia Albuquerque
 * @date 08/01/2007
 * 
 * Alterado por Vinicius Medeiros
 * @date 06/06/2008
 */

public class AtualizarContratoArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarContratoArrecadadorActionForm atualizarContratoArrecadadorActionForm = (AtualizarContratoArrecadadorActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) sessao
				.getAttribute("arrecadadorContrato");

		String[] idArrecadador = new String[1];

		idArrecadador[0] = arrecadadorContrato.getArrecadador().getId()
				.toString();

		// Arrecadador
		if (atualizarContratoArrecadadorActionForm.getIdClienteCombo() != null
				&& !atualizarContratoArrecadadorActionForm.getIdClienteCombo()
						.equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			// Inclui a obejeto de cliente no filtro de arrecadador
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			// filtra arrecadador pelo cliente
			filtroArrecadador
					.adicionarParametro(new ParametroSimples(
							FiltroArrecadador.CLIENTE_ID,
							atualizarContratoArrecadadorActionForm
									.getIdClienteCombo()));
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

		arrecadadorContrato
				.setNumeroContrato(atualizarContratoArrecadadorActionForm
						.getNumeroContrato());
		// Conta Deposito Arrecadador
		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		if (atualizarContratoArrecadadorActionForm
				.getIdContaBancariaArrecadador() != null
				&& !atualizarContratoArrecadadorActionForm
						.getIdContaBancariaArrecadador().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					atualizarContratoArrecadadorActionForm
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
		if (atualizarContratoArrecadadorActionForm.getIdContaBancariaTarifa() != null
				&& !atualizarContratoArrecadadorActionForm
						.getIdContaBancariaTarifa().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					atualizarContratoArrecadadorActionForm
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
		cliente.setId(new Integer(atualizarContratoArrecadadorActionForm
				.getIdCliente()));

		// [FS0004]-Verificar se pessoa f�sica
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

		// C�digo Convenio
		arrecadadorContrato
				.setCodigoConvenio(atualizarContratoArrecadadorActionForm
						.getIdConvenio());

		// IndicadorCobrancaISS
		if (atualizarContratoArrecadadorActionForm.getIndicadorCobranca() != null) {
			arrecadadorContrato.setIndicadorCobrancaIss(new Short(
					atualizarContratoArrecadadorActionForm
							.getIndicadorCobranca()));
		} else {
			arrecadadorContrato.setIndicadorCobrancaIss(null);
		}
		
	   //Verifica se a Data Final � maior que a Inicial
       if ( atualizarContratoArrecadadorActionForm.getDtFimContrato() != null && 
		!atualizarContratoArrecadadorActionForm.getDtFimContrato().equals("")
		&& atualizarContratoArrecadadorActionForm.getDtInicioContrato() != null 
		&& !atualizarContratoArrecadadorActionForm.getDtInicioContrato().equals("")){
			this.verificarDataInicialMenorFinal(atualizarContratoArrecadadorActionForm.getDtInicioContrato(), atualizarContratoArrecadadorActionForm.getDtFimContrato(), "Data Fim","atencao.data_encerramento_anterior_data_corrente");
	   }
	
      //Verifica se a Data Encerramento � maior que a Inicial
	   if ( atualizarContratoArrecadadorActionForm.getDataContratoEncerramento() != null && 
    		  !atualizarContratoArrecadadorActionForm.getDataContratoEncerramento().equals("")){
    	  
    	  this.verificarDataInicialMenorFinal(atualizarContratoArrecadadorActionForm.getDtInicioContrato(), atualizarContratoArrecadadorActionForm.getDataContratoEncerramento(), "Data de Encerramento","atencao.data_encerramento_anterior_data_corrente");
      
       }
		
		// Intervalo de Datas do Contrato
		arrecadadorContrato.setDataContratoInicio(Util
				.converteStringParaDate(atualizarContratoArrecadadorActionForm
						.getDtInicioContrato()));
		arrecadadorContrato.setDataContratoFim(Util
				.converteStringParaDate(atualizarContratoArrecadadorActionForm
						.getDtFimContrato()));
		
		arrecadadorContrato.setDataContratoEncerramento(Util
				.converteStringParaDate(atualizarContratoArrecadadorActionForm
						.getDataContratoEncerramento()));
		
		//Seleciona Contrato Motivo Cancelamento
		FiltroContratoMotivoCancelamento filtroContratoMotivoCancelamento = new FiltroContratoMotivoCancelamento();
		filtroContratoMotivoCancelamento
				.adicionarParametro(new ParametroSimples(
						FiltroContratoMotivoCancelamento.ID,
						atualizarContratoArrecadadorActionForm.getContratoMotivoCancelamento()));
		Collection<ContratoMotivoCancelamento> colecaoContratoMotivoCancelamento = fachada.pesquisar(
				filtroContratoMotivoCancelamento, ContratoMotivoCancelamento.class.getName());
		
		if(colecaoContratoMotivoCancelamento != null){
			ContratoMotivoCancelamento contratoMotivoCancelamento = (ContratoMotivoCancelamento) Util
					.retonarObjetoDeColecao(colecaoContratoMotivoCancelamento);
			
			arrecadadorContrato.setContratoMotivoCancelamento(contratoMotivoCancelamento);
		}
		//--------------//

		String emailCliente = atualizarContratoArrecadadorActionForm
				.getDescricaoEmail();

		// email
		if (emailCliente != null && !emailCliente.trim().equals("")) {
			arrecadadorContrato.setDescricaoEmail(emailCliente);
		} else {
			arrecadadorContrato.setDescricaoEmail(null);
		}

		arrecadadorContrato.setTamanhoMaximoIdentificacaoImovel(new Integer(
				atualizarContratoArrecadadorActionForm
						.getTamanhoMaximoIdentificacaoImovel()).shortValue());
		
		
		boolean arrecadadorJaExiste = fachada.pesquisarContratoArrecadadorPorArrecadador(arrecadadorContrato.getArrecadador().getId(), arrecadadorContrato.getId());
		
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
					"pelo menos uma Tarifa de Contrato");
		}else{
			Set<Integer> idsFormasSelecionadas = mapaArrecadadorContratoTarifa.keySet();
			
			for(Integer idForma : idsFormasSelecionadas){
				ArrayList<ArrecadadorContratoTarifa> col = new ArrayList<ArrecadadorContratoTarifa>();
				
				ArrayList<ArrecadadorContratoTarifa> lista = (ArrayList<ArrecadadorContratoTarifa>) mapaArrecadadorContratoTarifa.get(idForma);	
				
				if(lista.size() > 1){
				
		    		Collections.sort(lista, new ACTarifaInicioComparator());
		    		
		    		//Adiciona na colecao a tarifa com a data de inicio vigente maior (data fim continua nula)
		    		ArrecadadorContratoTarifa arreDataFimNula = lista.get(lista.size() - 1);
		    		arreDataFimNula.setDtTarifaFim(null);
		    		col.add(arreDataFimNula);
		    		
		    		//coloca a data fim das demais tarifas da forma
		    		for(int i = lista.size() - 1; i >= 1; i--){
		    			
						//retorna a tarifa com a data de vigencia maior
		    			ArrecadadorContratoTarifa arrecadadorContratoTarifa = lista.get(i);
						
						//pega a data de vigencia inicial
						Date dataInicioAtual = arrecadadorContratoTarifa.getDtTarifaInicio();
						
						//subtrai um dia da data de viagencia inicial
						Date dtTarifaFimProx = Util.subtrairNumeroDiasDeUmaData(dataInicioAtual, 1);
						
						//pega a tarifa que est� acima da tarifa atual
						ArrecadadorContratoTarifa arrecadadorContratoTarifaAlterar = lista.get(i - 1);
						
						//seta a data fim
						arrecadadorContratoTarifaAlterar.setDtTarifaFim(dtTarifaFimProx);
						
						col.add(arrecadadorContratoTarifaAlterar);
		    		}
				}else if (lista.size() == 1){
					col.add(lista.get(0));
				}
				
				//Colecao com todos os contratos tarifa informados e com suas respectivas datas de vigencia inicial e final
				colecaoArrecadadorContratoTarifaComDataFim.addAll(col);
			}
		}	
		
		// atualiza na base de dados de Arrecadador
		fachada.atualizarContratoArrecadador(arrecadadorContrato, colecaoArrecadadorContratoTarifaComDataFim,	usuarioLogado);
		
		sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", null);

		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Arrecadador "
				+ atualizarContratoArrecadadorActionForm.getNumeroContrato()
				+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Contrato de Arrecadador",
				"exibirFiltrarContratoArrecadadorAction.do?menu=sim");

		return retorno;
	}
	
	private void verificarDataInicialMenorFinal(String data1str, String data2str, String nomeCampo, String msgErro){
		
		//Validando se os dois campos foram informados
		if(data1str != null && !data1str.equals("") &&
		   (data2str == null || data2str.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo",null,nomeCampo);
		}
		
		if(data2str != null && !data2str.equals("") &&
		   (data1str == null || data1str.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo",null,nomeCampo);
		}
		
		
		if(data1str != null && !data1str.equals("") && 
		   data2str != null && !data2str.equals("")){
			
			Date data1 = Util.converteStringParaDate(data1str);
			Date data2 = Util.converteStringParaDate(data2str);
			
			//Caso a data final seja anterior � data inicial
			if(Util.compararData(data2, data1) < 0){
				
				//exibir a mensagem "Data Final do Per�odo � anterior � Data Inicial do Per�odo"
				throw new ActionServletException(msgErro, null, nomeCampo);
			}
		}
	}
	
	class ACTarifaInicioComparator implements Comparator<ArrecadadorContratoTarifa> {
		public int compare(ArrecadadorContratoTarifa o1, ArrecadadorContratoTarifa o2) {
			return o1.getDtTarifaInicio().compareTo(o2.getDtTarifaInicio());
		}
	}

}
