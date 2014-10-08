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

package gcom.gui.cobranca.parcelamentojudicial;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.ContaParcelamentoJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 20/03/2013
 */

public class ExibirEfetuarParcelamentoJudicialDebitosAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		if(form.getDocumentoAcordoJudicial() != null && !form.getDocumentoAcordoJudicial().getFileName().equals("")){
			form.setDocumentoAcordoJudicialCopia(form.getDocumentoAcordoJudicial());
		}
		else if(form.getDocumentoAcordoJudicialCopia() != null){
			form.setDocumentoAcordoJudicial(form.getDocumentoAcordoJudicialCopia());
		}
		
		
		//VALIDAÇÃO DA 1ª ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 1ª aba para validar
		Collection<RegistroImovelHelper> listaImoveis = form.getListaRegistroImovelHelper();
		String idRegistroPrincipal = form.getIdRegistroPrincipal();
		String periodoInicial = form.getAmReferenciaInicial();
		String periodoFinal = form.getAmReferenciaFinal();		
		boolean debitosImoveisInformados = form.verificarDebitosImoveisInformados();
		
		//Validando a 1º aba
		this.getFachada().validarEfetuarParcelamentoJudicialImovel(listaImoveis,idRegistroPrincipal,periodoInicial,periodoFinal,debitosImoveisInformados);
		
		//[SB0039] Verificar Campos Alterados 1ª Aba
		form.verificarCamposAlteradosPrimeiraAba();
		form.setListaRegistroImovelHelperAnterior(new ArrayList<RegistroImovelHelper>(form.getListaRegistroImovelHelper()));
		
		//Selecionando o registro principal
		for(Iterator<RegistroImovelHelper> it = listaImoveis.iterator();it.hasNext();){
			RegistroImovelHelper helper = it.next();
			if(helper.getIdColecao().equals(idRegistroPrincipal)){
				form.setRegistroImovelPrincipal(helper);
				break;
			}
		}
		//-----------------------------------------------------------------------------------------------------------------
		
		
		//Campos da 2ª aba
		RegistroImovelHelper registroImovelPrincipal = form.getRegistroImovelPrincipal();
		
		//1.3.2. Imóvel Principal
		String matriculaImovelPrincipal = registroImovelPrincipal.getIdImovel().toString();
		form.setMatriculaImovelPrincipal(matriculaImovelPrincipal);
		
		//1.3.3. Situação de Ligação de Água
		//       [IT0007] Pesquisar Situação de Ligação de Água
		LigacaoAguaSituacao ligacaoAgua = this.getFachada().pesquisarLigacaoAguaSituacao(registroImovelPrincipal.getIdImovel());
		form.setDescSituacaoLigacaoAgua(ligacaoAgua.getDescricao());
		
		//1.3.4. Situação de Ligação de Esgoto
		LigacaoEsgotoSituacao ligacaoEsgoto = this.getFachada().pesquisarLigacaoEsgotoSituacao(registroImovelPrincipal.getIdImovel());
		form.setDescSituacaoLigacaoEsgoto(ligacaoEsgoto.getDescricao());
		
		//1.3.5. Quadro com o Endereço do Imóvel
		//       Incluir [UC0085]
		String endereco = this.getFachada().pesquisarEndereco(registroImovelPrincipal.getIdImovel());
		form.setEnderecoImovel(endereco);
		
		if(form.getListaContaParcelamentoJudicialHelper() == null){
			
			//1.3.6. Quadro com a Lista dos Débitos
			//       Para cada item obtido nas listas de contas retornadas pelo [UC0067]
			//       a partir dos subfluxos [SB0003] e [SB0008], o sistema deverá pesquisar 
			//       os dados da conta 
			//       [IT0009 - Obter Dados da Conta]
			Collection<ContaParcelamentoJudicialHelper> colecaoContaParcelamentoJudicialHelper = 
					new ArrayList<ContaParcelamentoJudicialHelper>();
			
			//Obtendo os valores das contas
			
			boolean preencheu = false;
			
			//Verificar se os campos de ano/Mês foram preenchidos pelo usuário
			if(Util.verificarNaoVazio(periodoInicial) && Util.verificarNaoVazio(periodoFinal))
				preencheu = true;
			
			
			for(Iterator<RegistroImovelHelper> it = form.getListaRegistroImovelHelper().iterator();it.hasNext();){
				RegistroImovelHelper registroHelper = it.next();
				Integer idImovel = registroHelper.getIdImovel();
				for(Iterator<ContaValoresHelper> it2 = registroHelper.getColecaoContaValoresHelper().iterator();it2.hasNext();){
					ContaValoresHelper contaHelper = it2.next();
					
					ContaParcelamentoJudicialHelper contaParcJudHelper = new ContaParcelamentoJudicialHelper();
					
					//Id da conta
					contaParcJudHelper.setIdConta(contaHelper.getConta().getId());
					
					//1.3.6.3.1.2. matrícula do imóvel
					contaParcJudHelper.setIdImovel(idImovel);
					
					//1.3.6.3.1.3. Mês/ano
					contaParcJudHelper.setAmConta(contaHelper.getConta().getReferencia());
					
					//1.3.6.3.1.4. Vencimento
					contaParcJudHelper.setDataVencimentoConta(contaHelper.getConta().getDataVencimentoConta());
					
					//4. Valor da Conta
					//--------------------------------------------------------------------------------------
						//Valor água
						contaParcJudHelper.setValorAgua(contaHelper.getConta().getValorAgua());
						
						//Valor esgoto
						contaParcJudHelper.setValorEsgoto(contaHelper.getConta().getValorEsgoto());
						
						//Valor débitos
						contaParcJudHelper.setValorDebitos(contaHelper.getConta().getDebitos());
						
						//Valor créditos
						contaParcJudHelper.setValorCreditos(contaHelper.getConta().getValorCreditos());
						
						//Valor impostos
						contaParcJudHelper.setValorImpostos(contaHelper.getConta().getValorImposto());
					//--------------------------------------------------------------------------------------
					
					//5. Acréscimos Por Impontualidade
					//--------------------------------------------------------------------------------------
						//Valor da multa
						contaParcJudHelper.setValorMulta(contaHelper.getValorMulta());
						
						//Valor dos juros de mora
						contaParcJudHelper.setValorJurosMora(contaHelper.getValorJurosMora());
						
						//valor de atualização monetária
						contaParcJudHelper.setValorAtualizacaoMonetaria(contaHelper.getValorAtualizacaoMonetaria());
					//--------------------------------------------------------------------------------------
						
					//6. Situação da Conta	
					DebitoCreditoSituacao cdSit = this.getFachada().pesquisarDebitoCreditoSituacaoConta(contaHelper.getConta().getId());
					contaParcJudHelper.setSituacaoConta(cdSit.getDescricaoDebitoCreditoSituacao());
					
					//Caso tenha preenchido o ano/Mês inicial e final
					if(preencheu){
						
						//Se a conta estiver no período passado como parâmetro, adicionar para aparecer pro usuário
						if(contaParcJudHelper.getAmConta().compareTo(Util.formatarMesAnoComBarraParaAnoMes(periodoInicial)) >= 0 &&
								contaParcJudHelper.getAmConta().compareTo(Util.formatarMesAnoComBarraParaAnoMes(periodoFinal)) <= 0)
						colecaoContaParcelamentoJudicialHelper.add(contaParcJudHelper);
					}
					else{
						colecaoContaParcelamentoJudicialHelper.add(contaParcJudHelper);
					}
				}
				
			}
			
			////1.3.6.3. o sistema deverá ordenar pelas colunas "Imóvel" e "Mês/Ano"
			List<ContaParcelamentoJudicialHelper> listaAOrdenar = 
					new ArrayList<ContaParcelamentoJudicialHelper>(colecaoContaParcelamentoJudicialHelper);
			Collections.sort(listaAOrdenar, new ContaParcelamentoJudicialHelper());
			form.setListaContaParcelamentoJudicialHelper(colecaoContaParcelamentoJudicialHelper);
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialDebitosAction");
	}
	
}
