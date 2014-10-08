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

package gcom.gui.cobranca.parcelamentojudicial;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.parcelamento.ParcelamentoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.ContaParcelamentoJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.ParcelaJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * [SB0030] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 02/04/2013
 */
public class ConcluirEfetuarParcelamentoJudicialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
	
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm) actionForm;
		ActionForward retorno = mapping.findForward("telaSucesso");
		
		Usuario usuario = (Usuario)this.getSessao(request).getAttribute("usuarioLogado");
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		//Campos do formul�rio que ser�o utilizados para efetuar o parcelamento
		String clienteResponsavel = form.getIdClienteResponsavel();
		
		String clienteUsuario = form.getIdClienteUsuario();
		Integer amRefFaturamento = sistemaParametro.getAnoMesFaturamento();
		String numeroProcessoJudicial = form.getNumeroProcessoJudicial();
		String observacao = form.getObservacao();	
		String indicadorValorCustas = form.getIndicadorValorCustas();
		String indicadorValorHonorarios = form.getIndicadorValorHonorarios();
		String indicadorInformarValorParcela = form.getIndicadorInformarValorParcela();
		String indicadorParcelamentoComJuros = form.getIndicadorParcelamentoComJuros();
		String indicadorPerdeDesconto = form.getIndicadorPerdeDesconto();
		String indicadorEntradaParcelamento = form.getIndicadorEntradaParcelamento();
		String numeroParcelas = form.getQtdParcelas();
		String taxaJuros = form.getPercentualJuros();	
		String percentualDescontos = form.getPercentualDesconto();
		BigDecimal valorConta = form.somarContas();
		BigDecimal valorAcrescimoImp = form.somarAcrescimos();	
		String valorParcelado = form.getValorParcelado();
		String dataVencimentoPrimeiraParcela = form.getDataVencimentoPrimeiraParcela();
		String qtdParcelas = form.getQtdParcelas();	
		String dataVencimentoEntrada = form.getDataVencimentoEntrada();
		String valorEntrada = form.getValorEntrada();
		String diaVencimentoParcelas = form.getDiaVencimentoParcelas();
		
		Collection<ParcelaJudicialHelper> listaParcelaJudicial = form.getListaParcelaJudicial();	
		Collection<ContaParcelamentoJudicialHelper> contasSelecionadas = form.selecionarContas();
		
		
		
		
		//VALIDA��ES
		//================================================================================================================================
		//[FE0001] Verificar informa��es 1� Aba
		Collection<RegistroImovelHelper> listaRegistroImovelHelper = form.getListaRegistroImovelHelper();
		String idRegistroPrincipal = form.getIdRegistroPrincipal();
		String periodoInicial = form.getAmReferenciaInicial();
		String periodoFinal = form.getAmReferenciaFinal();
		boolean debitosImoveisInformados = form.verificarDebitosImoveisInformados();
		this.getFachada().validarEfetuarParcelamentoJudicialImovel(listaRegistroImovelHelper,idRegistroPrincipal,periodoInicial,periodoFinal,debitosImoveisInformados);
		
		//[FE0002] Verificar informa��es 2� Aba.
		String[] idsContasSelecionadas = form.getIdsContasSelecionadas();
		this.getFachada().validarEfetuarParcelamentoJudicialDebitos(idsContasSelecionadas,form.getRegistroImovelPrincipal());
		
		//[FE0003] Verificar informa��es 3� Aba.
		String valorDebito = form.getValorDebito();
		String valorAcordo = form.getValorAcordo();
		String valorCustas = form.getValorCustas();
		String percentualCustas = form.getPercentualCustas();
		String valorHonorarios = form.getValorHonorarios();
		String percentualHonorarios = form.getPercentualHonorarios();
		String advogado = form.getAdvogadoResponsavel();
		String numeroOAB = form.getNumeroOAB();
		FormFile arquivo = form.getDocumentoAcordoJudicial();
		byte[] conteudoArquivo = null;
		String nomeArquivo = null;
		
		
		//Separando os elementos do arquivo
		try {
			if(arquivo != null){
				conteudoArquivo = arquivo.getFileData();
				nomeArquivo = arquivo.getFileName();
			}
		} catch (FileNotFoundException e) {
			throw new ActionServletException("atencao.elemento_invalido",null,"Documento do Acordo Judicial");
		} catch (IOException e) {
			throw new ActionServletException("atencao.elemento_invalido",null,"Documento do Acordo Judicial");
		}
		this.getFachada().validarEfetuarParcelamentoJudicialNegociacao(valorDebito,
																	   valorAcordo,
																	   percentualDescontos,
																	   valorCustas,
																	   percentualCustas,
																	   valorHonorarios,
																	   percentualHonorarios,
																	   numeroProcessoJudicial,
																	   clienteResponsavel,
																	   advogado,
																	   numeroOAB,
																	   conteudoArquivo,
																	   nomeArquivo);
		
		//[FE0004] Verificar informa��es 4� Aba.
		this.getFachada().validarEfetuarParcelamentoJudicialConclusao(
				indicadorPerdeDesconto, indicadorValorCustas,
				indicadorValorHonorarios,
				indicadorParcelamentoComJuros,
				indicadorInformarValorParcela,
				indicadorEntradaParcelamento,
				dataVencimentoEntrada,
				valorEntrada,
				dataVencimentoPrimeiraParcela,
				diaVencimentoParcelas,
				qtdParcelas,
				taxaJuros,
				listaParcelaJudicial);
		//================================================================================================================================
		
		Integer idImovelPrincipal = form.getRegistroImovelPrincipal().getIdImovel();
		Integer idLocalidadeImovel = form.getRegistroImovelPrincipal().getLocalidadeImovel().getId();
		Imovel imovel = new Imovel();
		imovel.setId(idImovelPrincipal);
		
		
		//1. O sistema dever� obter a Cole��o de Quantidades por Categoria, 
		//   passando como par�metro o Id do Im�vel Principal selecionado na 1� aba
		//   [UC0108] Obter Quantidade de Economias por Categoria
		Collection<Categoria> colecaoCategoriasImovel = this.getFachada().obterQuantidadeEconomiasCategoria(imovel);
		
		//2. O sistema dever� incluir o parcelamento judicial 
		//   [SB0031] Incluir Parcelamento Judicial
		Integer idParcelamentoInserido = this.getFachada().incluirParcelamentoJudicial(
																		Util.converterStringParaInteger(clienteResponsavel),
																		advogado,
																		Util.converterStringParaInteger(clienteUsuario),
																		amRefFaturamento,
																		numeroProcessoJudicial,
																		numeroOAB,
																		observacao,
																		conteudoArquivo,
																		Util.converterStringParaShort(indicadorValorCustas),
																		Util.converterStringParaShort(indicadorValorHonorarios),
																		Util.converterStringParaShort(indicadorInformarValorParcela),
																		Util.converterStringParaShort(indicadorParcelamentoComJuros),
																		Util.converterStringParaShort(indicadorPerdeDesconto),
																		Util.converterStringParaShort(indicadorEntradaParcelamento),
																		Util.converterStringParaInteger(diaVencimentoParcelas),
																		Util.converterStringParaInteger(numeroParcelas),
																		Util.formatarMoedaRealparaBigDecimalComErro(taxaJuros),
																		Util.formatarMoedaRealparaBigDecimalComErro(percentualCustas),
																		Util.formatarMoedaRealparaBigDecimalComErro(percentualHonorarios),
																		Util.formatarMoedaRealparaBigDecimalComErro(percentualDescontos),
																		valorConta,
																		valorAcrescimoImp,
																		Util.formatarMoedaRealparaBigDecimalComErro(valorAcordo),
																		Util.formatarMoedaRealparaBigDecimalComErro(valorCustas),
																		Util.formatarMoedaRealparaBigDecimalComErro(valorHonorarios),
																		Util.formatarMoedaRealparaBigDecimalComErro(valorParcelado),
																		Util.formatarMoedaRealparaBigDecimalComErro(valorEntrada),
																		usuario
																	);
		
		
		DebitoTipo debitoTipoCustas = this.obterDebitoTipo(DebitoTipo.CUSTAS_PARC_JUDICIAL);
		DebitoTipo debitoTipoHonorarios = this.obterDebitoTipo(DebitoTipo.HONORARIOS_PARC_JUDICIAL);
		
		//3. Caso o Indicador Valor das Custas informado na 4� aba possua valor igual a "N�o",
		//   o sistema dever� incluir as guias de pagamento de custas
		if(indicadorValorCustas.equals(ConstantesSistema.NAO.toString())){		
			this.getFachada().incluirGuiaPagamentoParcelamentoJudicial(
					idParcelamentoInserido,
					idLocalidadeImovel, 
					idImovelPrincipal, 
					Util.converterStringParaInteger(clienteResponsavel),
					amRefFaturamento,
					Util.converteStringParaDate(dataVencimentoPrimeiraParcela),
					Util.formatarMoedaRealparaBigDecimalComErro(valorCustas), 
					debitoTipoCustas.getId(), //,
					debitoTipoCustas.getLancamentoItemContabil().getId(), 
			    	"Guia referente as custas do parcelamento judicial de n� "+numeroProcessoJudicial,
			    	usuario.getId(), 
			    	colecaoCategoriasImovel
			    	);
		}
		
		//4. Caso o Indicador Valor dos Honor�rios informado na 4� aba possua valor igual a "N�o", 
		//   o sistema dever� incluir as guias de pagamento de honor�rios
		//	[SB0033] Incluir Guia de Pagamento de Honor�rios
		if(indicadorValorHonorarios.equals(ConstantesSistema.NAO.toString())){
			this.getFachada().incluirGuiaPagamentoParcelamentoJudicial(
					idParcelamentoInserido,
					idLocalidadeImovel, 
					idImovelPrincipal, 
					Util.converterStringParaInteger(clienteResponsavel),
					amRefFaturamento,
					Util.converteStringParaDate(dataVencimentoPrimeiraParcela),
					Util.formatarMoedaRealparaBigDecimalComErro(valorHonorarios),
					debitoTipoHonorarios.getId(), 
					debitoTipoHonorarios.getLancamentoItemContabil().getId(),
			    	"Guia referente aos honorarios do parcelamento judicial de n� "+numeroProcessoJudicial,
			    	usuario.getId(), 
			    	colecaoCategoriasImovel
			    	);
		}
		
		
		//5. Caso o Indicador Entrada Parcelamento informado na 4� aba possua valor igual a "Sim", 
		//   o sistema dever� incluir a guia de pagamento de entrada
		//   [SB0038] Incluir Guia de Pagamento de Entrada
		if(indicadorEntradaParcelamento.equals(ConstantesSistema.SIM.toString())){
			this.getFachada().incluirGuiaPagamentoEntrada(
					listaParcelaJudicial,
					Util.converterStringParaInteger(qtdParcelas),
					idParcelamentoInserido,
					colecaoCategoriasImovel,
					idLocalidadeImovel,
					idImovelPrincipal,
					Util.converterStringParaInteger(clienteResponsavel),
					usuario.getId(),
					Util.converterStringParaShort(indicadorParcelamentoComJuros),
					Util.converterStringParaShort(indicadorValorCustas),
					Util.converterStringParaShort(indicadorValorHonorarios),			
					amRefFaturamento,
					Util.formatarMoedaRealparaBigDecimalComErro(percentualDescontos),
					numeroProcessoJudicial,
					Util.converteStringParaDate(dataVencimentoEntrada),
					Util.formatarMoedaRealparaBigDecimalComErro(valorEntrada),
					Util.formatarMoedaRealparaBigDecimalComErro(valorAcordo),
					Util.formatarMoedaRealparaBigDecimalComErro(valorDebito)
			);
		}
		
		
		//5. O sistema dever� incluir as parcelas do parcelamento judicial
		//	 [SB0034] Incluir Parcelas
		this.getFachada().incluirParcelasParcelamentoJudicial(
				listaParcelaJudicial,
				Util.converterStringParaInteger(qtdParcelas),
				idParcelamentoInserido,
				colecaoCategoriasImovel,
				idLocalidadeImovel,
				idImovelPrincipal,
				Util.converterStringParaInteger(clienteResponsavel),
				usuario.getId(),
				Util.converterStringParaShort(indicadorParcelamentoComJuros),
				Util.converterStringParaShort(indicadorValorCustas),
				Util.converterStringParaShort(indicadorValorHonorarios),			
				amRefFaturamento,
				Util.formatarMoedaRealparaBigDecimalComErro(percentualDescontos),
				numeroProcessoJudicial
		);
		
		//6. O sistema dever� atualizar os im�veis do parcelamento
		//	 [SB0035] Atualizar Im�veis do Parcelamento
		this.getFachada().atualizarImoveisParcelamento(
				listaRegistroImovelHelper,
				idParcelamentoInserido,
				idRegistroPrincipal						
		);
			
		
		
		//7. O sistema dever� atualizar as contas parceladas 
		//   [SB0036] Atualizar Contas Parceladas
		this.getFachada().atualizarContasParceladas(
				contasSelecionadas,
				idParcelamentoInserido,
				amRefFaturamento,
				valorConta,
				valorAcrescimoImp
				);
		
		
		montarPaginaSucesso(request, 
				"Parcelamento Judicial " + numeroProcessoJudicial + " efetuado com sucesso.", 
				"Efetuar outro Parcelamento Judicial",
                "exibirEfetuarParcelamentoJudicialAction.do?menu=sim",
                "manterParcelamentoJudicialConsultarParcelamentoJudicialAction.do?id="+idParcelamentoInserido.intValue(),
				"Consultar Dados do Parcelamento Judicial");
		
		return retorno;
	}
	
	
	
	private DebitoTipo obterDebitoTipo(Integer id){
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
		FiltroDebitoTipo.ID, id));
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection debitoTipoEncontrado = this.getFachada().pesquisar(
		filtroDebitoTipo, DebitoTipo.class.getName());
		if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {
			return (DebitoTipo) debitoTipoEncontrado.iterator().next();	
		}
		else
			return null;
	}
}
