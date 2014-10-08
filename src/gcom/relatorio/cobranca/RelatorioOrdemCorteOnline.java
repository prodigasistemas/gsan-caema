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
package gcom.relatorio.cobranca;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1054] Gerar Ordem de Corte
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Corte
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class RelatorioOrdemCorteOnline extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	ControladorFaturamento controladorFaturamento = null;
	
	public RelatorioOrdemCorteOnline(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORDEM_CORTE_ONLINE);
	}	

	@Deprecated
	public RelatorioOrdemCorteOnline() {
		super(null, "");
	}

	@SuppressWarnings("null")
	public Object executar() throws TarefaException {
		
		System.out.println("********************************************");
		System.out.println("ENTROU NO EXECUTAR RELATORIO ORDEM DE CORTE ");
		System.out.println("********************************************");
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String descricaoEmpresa = 
				sistemaParametro.getNomeAbreviadoEmpresa() +" - "
				+ sistemaParametro.getNomeEmpresa();				
		
		String inscricaoEmpresa = 
			"CNPJ:"+Util.formatarCnpj(sistemaParametro.getCnpjEmpresa())+
			" INSC. EST. NR. " + sistemaParametro.getInscricaoEstadual();
		
		// valor de retorno
		byte[] retorno = null;

		Integer matriculaImovel = (Integer) getParametro("matricula");		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) getParametro("cobrancaDocumento");
		
		Short indicador = ConstantesSistema.TODOS;
		
		Collection<RelatorioOrdemCorteContasDetailBean> 
			colecaoRelatorioOrdemCorteContasDetailBean = 
						this.pesquisarCobrancaDocumentoItem(cobrancaDocumento.getId(), fachada, indicador);	
		
		indicador = ConstantesSistema.SIM;
		
		Collection<RelatorioOrdemCorteContasDetailBean> 
		colecaoRelatorioOrdemCorteContasDetailBeanPadrao = 
				this.pesquisarCobrancaDocumentoItem(cobrancaDocumento.getId(), fachada, indicador);
		
		indicador = ConstantesSistema.NAO;
		
		Collection<RelatorioOrdemCorteContasDetailBean> 
		colecaoRelatorioSegundaColuna = this.pesquisarCobrancaDocumentoItem(cobrancaDocumento.getId(), fachada, indicador);				
		
		BigDecimal valorTotalDebitos = BigDecimal.ZERO;
		
		for (RelatorioOrdemCorteContasDetailBean bean : colecaoRelatorioOrdemCorteContasDetailBean) {
			
			BigDecimal valor = Util.formatarMoedaRealparaBigDecimal(bean.getValorFatura());
			
			valorTotalDebitos = Util.somaBigDecimal(valorTotalDebitos, valor);		
		}			
		
		Conta conta = new Conta();
		FiltroConta filtroConta = new FiltroConta();
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, matriculaImovel));
		Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoConta)){
			conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);			
		}
		
		String representacaoNumericaCodBarraFormatadaBoleto;	
		
		Localidade localidade = (Localidade) getParametro("localidade");
		
		Integer numeroSequencialDocumento = cobrancaDocumento.getId();
		
		//Formata a representação númerica do código de barras | [UC1054] - Gerar Ordem de Corte
		//[UC0229] - Obter Representação Númerica do Código de Barras
		//[SB0002] - Formato Convencional
		representacaoNumericaCodBarraFormatadaBoleto = fachada.obterRepresentacaoNumericaCodigoBarra(
			5, 
			valorTotalDebitos, 
			localidade.getId(), 
			matriculaImovel, 
			null, 
			null, 
			null, 
			null, 
			numeroSequencialDocumento.toString(),
			cobrancaDocumento.getDocumentoTipo().getId(),
			null, 			
			null, 
			null);		
		
		String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatadaBoleto
				.substring(0, 11)
				+ "-"
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(11, 12)
				+ " "
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(12, 23)
				+ "-"
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(23, 24)
				+ " "
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(24, 35)
				+ "-"
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(35, 36)
				+ " "
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(36, 47)
				+ "-"
				+ representacaoNumericaCodBarraFormatadaBoleto.substring(47, 48);			
		
		String representacaoNumericaCodBarraSemDigito = 
				representacaoNumericaCodBarraFormatadaBoleto.substring(0, 11)
			+ representacaoNumericaCodBarraFormatadaBoleto.substring(12, 23)
			+ representacaoNumericaCodBarraFormatadaBoleto.substring(24, 35)
			+ representacaoNumericaCodBarraFormatadaBoleto.substring(36, 47);	
		
		//[SB0003] - Ficha Compensação | [UC1054] - Gerar Ordem de Corte
		//Nosso Número
		String nossoNumeroSemDv = "1474823" + Util.adicionarZerosEsquedaNumero(2, 
			cobrancaDocumento.getDocumentoTipo().getId().toString()) 
			+ Util.adicionarZerosEsquedaNumero(8, cobrancaDocumento.getId().toString());
		
		//[UC0261] - Obter Dígito Verificador Módulo | [UC1054] - Gerar Ordem de Corte
		String digitoVerificador = Util.obterDigitoVerificadorModulo11(nossoNumeroSemDv).toString();
		
		String nossoNumero = nossoNumeroSemDv + "-" + digitoVerificador;
		
		List relatorioBeans = new ArrayList();
				
		RelatorioOrdemCorteOnlineBean relatorioOrdemCorteOnlineBean = 
			new RelatorioOrdemCorteOnlineBean(
					(String)getParametro("qtdeEconResidencial"),
					(String)getParametro("qtdeEconComercial"),
					(String)getParametro("qtdeEconIndustrial"),
					(String)getParametro("qtdeEconPublica"),
					(String)getParametro("qtdeEconTotal"),
					new JRBeanCollectionDataSource(
						colecaoRelatorioOrdemCorteContasDetailBean),
					new JRBeanCollectionDataSource(
						colecaoRelatorioOrdemCorteContasDetailBeanPadrao),
					new JRBeanCollectionDataSource(
						colecaoRelatorioSegundaColuna));		

		relatorioBeans.add(relatorioOrdemCorteOnlineBean);
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("descricaoEmpresa", descricaoEmpresa);
		parametros.put("inscricaoEmpresa", inscricaoEmpresa);
		parametros.put("idsOrdemServico", getParametro("idsOrdemServico"));
		parametros.put("endereco", getParametro("endereco"));
		parametros.put("matricula",Util.retornaMatriculaImovelFormatada(matriculaImovel));
		parametros.put("situacaoAgua", getParametro("situacaoAgua"));
		parametros.put("sistuacaoEsgoto", getParametro("sistuacaoEsgoto"));
		parametros.put("inscricao", getParametro("inscricao"));
		parametros.put("grupo", getParametro("grupo"));
		parametros.put("dataGeracaoOs", getParametro("dataGeracaoOs"));
		parametros.put("nomeCliente", getParametro("nomeCliente"));
		parametros.put("numeroHidrometro", getParametro("numeroHidrometro"));
		parametros.put("valorTotalDebitos", Util.formatarMoedaReal(valorTotalDebitos));
		parametros.put("anoMesfaturamentoGrupo", getParametro("anoMesfaturamentoGrupo"));
		parametros.put("perfilImovel", getParametro("perfilImovel"));
		parametros.put("unidadeNegocio", getParametro("unidadeNegocio"));
		parametros.put("dataAtual", Util.formatarData(new Date()));
		parametros.put("matriculaImovel", matriculaImovel.toString());
		parametros.put("nossoNumero", nossoNumero);		
		parametros.put("clienteNome", getParametro("clienteNome").toString());
		parametros.put("numeroSequencialDocumento", numeroSequencialDocumento.toString());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);				
		 
		//1.3 Dados do Documento de Cobrança | [UC1054] - Gerar Ordem de Corte
		//Caso o valor total dos Débitos seja menor que o valor para emissão de ficha Compensação
		//[SB0002] - Formato Convencional | [UC1054] - Gerar Ordem de Corte
		if(valorTotalDebitos.intValue() < sistemaParametro.getValorExtratoFichaComp().intValue()){	
			
			parametros.put("representacaoNumericaCodBarraFormatadaBoleto", 
				representacaoNumericaCodBarraFormatada);
			parametros.put("representacaoNumericaCodBarraSemDigito", 
				representacaoNumericaCodBarraSemDigito);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_CORTE_ONLINE,
				parametros, ds, tipoFormatoRelatorio);
		}
		//caso Contrário [SB0003] - Ficha de Compensação | [UC1054] - Gerar Ordem de Corte
		else{								
			
			Date dataVencimentoMais75 = Util.adicionarNumeroDiasDeUmaData(new Date(),75);
	        String fatorVencimento = null;
						
			fatorVencimento = fachada.obterFatorVencimento(dataVencimentoMais75);			
			
			//[UC0716] - Obter Representação Númerica do Código de Barras da Ficha de Compensação
			//[UC0340] - Gerar Código de Barras
			//[SB0003] - Formato Ficha de Compensação  | [UC1054] - Gerar Ordem de Corte
			String representacaoNumericaCodBarraFormatadaBoletoCompensacao = fachada.
					obterEspecificacaoCodigoBarraFichaCompensacao("001", "9", valorTotalDebitos, 
						nossoNumeroSemDv, "18", fatorVencimento);	
			
			representacaoNumericaCodBarraFormatada = 
					fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(
						representacaoNumericaCodBarraFormatadaBoletoCompensacao);					
			
			parametros.put("representacaoNumericaCodBarraFormatadaBoletoCompensacao", 
				representacaoNumericaCodBarraFormatada);			
			
			parametros.put("representacaoNumericaCodBarraSemDigito", 
				representacaoNumericaCodBarraFormatadaBoletoCompensacao);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_CORTE_ONLINE_COMPENSACAO,
				parametros, ds, tipoFormatoRelatorio);			
		}			
		// retorna o relatório gerado
		return retorno;
	}

	
	private Collection<RelatorioOrdemCorteContasDetailBean> pesquisarDebitosImovel(
			Integer idImovelDebitos,Fachada fachada, Short indicador) {		
		
		Collection<RelatorioOrdemCorteContasDetailBean> colecaoRelatorioOrdemCorteContasDetailBean = 
			new ArrayList<RelatorioOrdemCorteContasDetailBean>();
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(1);
		Integer indicadorCredito = new Integer(1);
		Integer indicadorNotas = new Integer(1);
		Integer indicadorGuias = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						idImovelDebitos.toString().trim(), null, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null);
		
		Collection<ContaValoresHelper> colecaoContas = null;
		
		if(colecaoDebitoImovel.getColecaoContasValores()!=null 
				&& !colecaoDebitoImovel.getColecaoContasValores().isEmpty()){
			
			colecaoContas = colecaoDebitoImovel.getColecaoContasValores();		
					
			if(colecaoContas.size() > 11 && indicador.equals(ConstantesSistema.TODOS)){
				int qtdContasParaSomar = colecaoContas.size() - 9;
				int controle = 1;
				
				String referencia = "SD. ANT.";
				String vencimento = "";
				BigDecimal valor = BigDecimal.ZERO;
				boolean jaSomou = false;
				
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					if(controle<qtdContasParaSomar){
						valor = Util.somaBigDecimal(valor, contaValoresHelper.getConta().getValorTotal());
						vencimento = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
						++controle;
						
					}else if(!jaSomou){			
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									referencia,
									vencimento,
									Util.formatarMoedaReal(valor));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						
						jaSomou = true;
						
						RelatorioOrdemCorteContasDetailBean beanFluxo = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(beanFluxo);
						
					}else{
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
				/*
				qtdContasParaSomar = colecaoContas.size() - 9;
				controle = 0;
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					if(colecaoRelatorioOrdemCorteContasDetailBean.size()<11){
						
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
				*/
			}else if(colecaoContas.size() > 14 && indicador.equals(ConstantesSistema.SIM)){
				
				int qtdContasParaSomar = colecaoContas.size() - 12;
				int controle = 1;
				int controleFinal = 1;
				
				String referencia = "SD. ANT.";
				String vencimento = "";
				BigDecimal valor = BigDecimal.ZERO;
				boolean jaSomou = false;
				
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					if(controle<qtdContasParaSomar){
						valor = Util.somaBigDecimal(valor, contaValoresHelper.getConta().getValorTotal());
						vencimento = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
						++controle;
						
					}else if(!jaSomou){			
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									referencia,
									vencimento,
									Util.formatarMoedaReal(valor));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						
						jaSomou = true;
						
						RelatorioOrdemCorteContasDetailBean beanFluxo = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(beanFluxo);
						
					}else{
						controleFinal++;
						if(controleFinal < 7){
							RelatorioOrdemCorteContasDetailBean bean = 
								new RelatorioOrdemCorteContasDetailBean(
										contaValoresHelper.getConta().getReferenciaFormatada(),
										Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
										Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
							
							colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						}else{
							break;
						}
					}
				}	
				
				
			}else if(colecaoContas.size() > 14 && indicador.equals(ConstantesSistema.NAO)){
				
				int quantSegundaColuna = colecaoContas.size() - 7;
				
				Integer contador = 0;
				
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					
					contador++;
					if(contador.intValue() > quantSegundaColuna){
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}			
			}else{
				
				if(!indicador.equals(ConstantesSistema.NAO)){
					for (ContaValoresHelper contaValoresHelper : colecaoContas) {
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
			}
			
		}		

		return colecaoRelatorioOrdemCorteContasDetailBean;
	}	
	
	private Collection<RelatorioOrdemCorteContasDetailBean> pesquisarCobrancaDocumentoItem(
		Integer idCobrancaDocumento,Fachada fachada, Short indicador) {		
	
		Collection<RelatorioOrdemCorteContasDetailBean> colecaoRelatorioOrdemCorteContasDetailBean = 
			new ArrayList<RelatorioOrdemCorteContasDetailBean>();
		
		FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
		
		filtroCobrancaDocumentoItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
		filtroCobrancaDocumentoItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.contaHistorico");
		filtroCobrancaDocumentoItem.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumentoItem.COBRANCA_DOCUMENTO_ID, idCobrancaDocumento));
		
		Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem = fachada.pesquisar(filtroCobrancaDocumentoItem, CobrancaDocumentoItem.class.getName());
		
		if(colecaoCobrancaDocumentoItem !=null && !colecaoCobrancaDocumentoItem.isEmpty()){
			
			if(colecaoCobrancaDocumentoItem.size() > 11 && indicador.equals(ConstantesSistema.TODOS)){
				int qtdContasParaSomar = colecaoCobrancaDocumentoItem.size() - 9;
				int controle = 1;
				
				String referencia = "SD. ANT.";
				String vencimento = "";
				BigDecimal valor = BigDecimal.ZERO;
				boolean jaSomou = false;
				
				for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoCobrancaDocumentoItem) {
					if(controle<qtdContasParaSomar){
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							valor = Util.somaBigDecimal(valor, cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal());
							vencimento = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta());
						}
						else{
							
							valor = Util.somaBigDecimal(valor, cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal());
							vencimento = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta());
						}
						
						++controle;
						
					}else if(!jaSomou){			
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									referencia,
									vencimento,
									Util.formatarMoedaReal(valor));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						
						jaSomou = true;
						
						RelatorioOrdemCorteContasDetailBean beanFluxo = null;
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							beanFluxo = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
						}
						else{
							
							beanFluxo = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
						}
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(beanFluxo);
						
					}else{
						
						RelatorioOrdemCorteContasDetailBean bean = null;
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
						}
						else{
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
						}
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
				
			}else if(colecaoCobrancaDocumentoItem.size() > 14 && indicador.equals(ConstantesSistema.SIM)){
				
				int qtdContasParaSomar = colecaoCobrancaDocumentoItem.size() - 12;
				int controle = 1;
				int controleFinal = 1;
				
				String referencia = "SD. ANT.";
				String vencimento = "";
				BigDecimal valor = BigDecimal.ZERO;
				boolean jaSomou = false;
				
				for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoCobrancaDocumentoItem) {
					if(controle<qtdContasParaSomar){
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							valor = Util.somaBigDecimal(valor, cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal());
							vencimento = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta());
						}
						else{
							
							valor = Util.somaBigDecimal(valor, cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal());
							vencimento = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta());
						}
						
						++controle;
						
					}else if(!jaSomou){			
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									referencia,
									vencimento,
									Util.formatarMoedaReal(valor));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						
						jaSomou = true;
						
						RelatorioOrdemCorteContasDetailBean beanFluxo = null;
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							beanFluxo = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
						}
						else{
							
							beanFluxo = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
						}
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(beanFluxo);
						
					}else{
						controleFinal++;
						if(controleFinal < 7){
							
							RelatorioOrdemCorteContasDetailBean bean = null;
							
							if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
								
								bean = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
							}
							else{
								
								bean = new RelatorioOrdemCorteContasDetailBean(
											cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
											Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
											Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
							}
							
							colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						}else{
							break;
						}
					}
				}	
				
				
			}else if(colecaoCobrancaDocumentoItem.size() > 14 && indicador.equals(ConstantesSistema.NAO)){
				
				int quantSegundaColuna = colecaoCobrancaDocumentoItem.size() - 7;
				
				Integer contador = 0;
				
				for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoCobrancaDocumentoItem) {
					
					contador++;
					if(contador.intValue() > quantSegundaColuna){
						
						RelatorioOrdemCorteContasDetailBean bean = null;
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
						}
						else{
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
						}
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}			
			}else{
				
				if(!indicador.equals(ConstantesSistema.NAO)){
					for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoCobrancaDocumentoItem) {
						
						RelatorioOrdemCorteContasDetailBean bean = null;
						
						if (cobrancaDocumentoItem.getContaGeral().getConta() != null){
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getConta().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getConta().getValorTotal()));
						}
						else{
							
							bean = new RelatorioOrdemCorteContasDetailBean(
										cobrancaDocumentoItem.getContaGeral().getContaHistorico().getReferenciaFormatada(),
										Util.formatarData(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getDataVencimentoConta()),
										Util.formatarMoedaReal(cobrancaDocumentoItem.getContaGeral().getContaHistorico().getValorTotal()));
						}
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
			}
			
		}		
	
		return colecaoRelatorioOrdemCorteContasDetailBean;
	}
	

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOrdemCorteOnline", this);

	}
}