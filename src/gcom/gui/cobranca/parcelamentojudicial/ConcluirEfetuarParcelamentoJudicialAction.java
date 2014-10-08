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
		
		//Campos do formulário que serão utilizados para efetuar o parcelamento
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
		
		
		
		
		//VALIDAÇÕES
		//================================================================================================================================
		//[FE0001] Verificar informações 1ª Aba
		Collection<RegistroImovelHelper> listaRegistroImovelHelper = form.getListaRegistroImovelHelper();
		String idRegistroPrincipal = form.getIdRegistroPrincipal();
		String periodoInicial = form.getAmReferenciaInicial();
		String periodoFinal = form.getAmReferenciaFinal();
		boolean debitosImoveisInformados = form.verificarDebitosImoveisInformados();
		this.getFachada().validarEfetuarParcelamentoJudicialImovel(listaRegistroImovelHelper,idRegistroPrincipal,periodoInicial,periodoFinal,debitosImoveisInformados);
		
		//[FE0002] Verificar informações 2ª Aba.
		String[] idsContasSelecionadas = form.getIdsContasSelecionadas();
		this.getFachada().validarEfetuarParcelamentoJudicialDebitos(idsContasSelecionadas,form.getRegistroImovelPrincipal());
		
		//[FE0003] Verificar informações 3ª Aba.
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
		
		//[FE0004] Verificar informações 4ª Aba.
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
		
		
		//1. O sistema deverá obter a Coleção de Quantidades por Categoria, 
		//   passando como parâmetro o Id do Imóvel Principal selecionado na 1ª aba
		//   [UC0108] Obter Quantidade de Economias por Categoria
		Collection<Categoria> colecaoCategoriasImovel = this.getFachada().obterQuantidadeEconomiasCategoria(imovel);
		
		//2. O sistema deverá incluir o parcelamento judicial 
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
		
		//3. Caso o Indicador Valor das Custas informado na 4ª aba possua valor igual a "Não",
		//   o sistema deverá incluir as guias de pagamento de custas
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
			    	"Guia referente as custas do parcelamento judicial de n° "+numeroProcessoJudicial,
			    	usuario.getId(), 
			    	colecaoCategoriasImovel
			    	);
		}
		
		//4. Caso o Indicador Valor dos Honorários informado na 4ª aba possua valor igual a "Não", 
		//   o sistema deverá incluir as guias de pagamento de honorários
		//	[SB0033] Incluir Guia de Pagamento de Honorários
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
			    	"Guia referente aos honorarios do parcelamento judicial de n° "+numeroProcessoJudicial,
			    	usuario.getId(), 
			    	colecaoCategoriasImovel
			    	);
		}
		
		
		//5. Caso o Indicador Entrada Parcelamento informado na 4ª aba possua valor igual a "Sim", 
		//   o sistema deverá incluir a guia de pagamento de entrada
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
		
		
		//5. O sistema deverá incluir as parcelas do parcelamento judicial
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
		
		//6. O sistema deverá atualizar os imóveis do parcelamento
		//	 [SB0035] Atualizar Imóveis do Parcelamento
		this.getFachada().atualizarImoveisParcelamento(
				listaRegistroImovelHelper,
				idParcelamentoInserido,
				idRegistroPrincipal						
		);
			
		
		
		//7. O sistema deverá atualizar as contas parceladas 
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
