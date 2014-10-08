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
package gcom.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.SessionBean;

/**
 * Controlador Arrecadacao Juazeiro
 *
 * @author Rafael Corr�a
 * @date 30/06/2009
 */
public class ControladorArrecadacaoCOSAMASEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COSAMA
	//==============================================================================================================
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2010
	 *
	 * @param idPagamento
	 * @param tipoPagamento
	 * @param idEmpresa
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();
		
		//===============================================================================================================================
		//LEGADO - COSAMA
		//===============================================================================================================================
		String filler = "";
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString())){
			filler = idPagamento.substring(15, 24).trim();
		}
		else{
			filler = idPagamento.substring(13, 24).trim();
		}
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			filler.equals("000000000")){
			
			//Im�vel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
				
			//Ano e M�s
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(8, 14).trim());
			
			//D�gito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(14, 15).trim());
				
			//Tipo de Documento (MOVER PARA 0 - ZERO)
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString());

		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString())){
			
			//Im�vel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
				
			//Ano e M�s 1
			String meAno1 = idPagamento.substring(8, 10).trim() + "20" + idPagamento.substring(10, 12).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(meAno1);
			
			//Ano e M�s 2
			String meAno2 = idPagamento.substring(12, 14).trim() + "20" + idPagamento.substring(14, 16).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(meAno2);
			
			//Ano e M�s 3
			String meAno3 = idPagamento.substring(16, 18).trim() + "20" + idPagamento.substring(18, 20).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(meAno3);
			
			//Ano e M�s 4
			String meAno4 = idPagamento.substring(20, 22).trim() + "20" + idPagamento.substring(22, 24).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(meAno4);
				
			//Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString());
			
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				filler.equals("00000000000")){
				
				//Im�vel
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
					
				//Localidade
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(8, 11).trim());
				
				//tipo do d�bito
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(11, 13).trim());
					
				//Tipo de Documento (MOVER PARA 30 - trinta)
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString());
				
		}
		else{
			
			registroHelperCodigoBarrasTipoPagamento = super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		}
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2010
	 *
	 * @param registroHelperCodigoBarras
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {
		
		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = null;
		
		String tipoPagamento = registroHelperCodigoBarras.getTipoPagamento();

		
		//LEGADO - COSANPA
		//===============================================================================================================================
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
			
			System.out.println("CONTA LEGADO");
			
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasExtrato_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
					idFormaArrecadacao);
			
			System.out.println("EXTRATO LEGADO");
						
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasGuiaPagamento_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
					idFormaArrecadacao);
			
			System.out.println("GUIA DE PAGAMENTO LEGADO");
		}
		else{
		
		pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
				registroHelperCodigoBarras, dataPagamento,
				anoMesPagamento, valorPagamento,
				idFormaArrecadacao, sistemaParametro, usuarioLogado);
		}

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * LEGADO CONTA
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 20/05/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		int anoMes = 0;
		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		boolean anoMesReferencia = false;

		String idImovel = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1();
		
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(idImovel);

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} else {

			/*
			 * Verifica se existe a matricula do im�vel na base
			 */
			matriculaImovel = new Integer(idImovel);

			idImovelNaBase = null;

			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovel);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			/*
			 * Se o id do imovel pesquisado na base for diferente de nulo
			 */
			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}

		anoMesReferencia = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (!anoMesReferencia) {

			// valida o ano mes de referencia da conta
			anoMes = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			anoMesReferencia = Util.validarAnoMesSemBarra("" + anoMes);

			if (anoMesReferencia) {
				descricaoOcorrencia = "ANO/M�S DE REFER�NCIA DA CONTA INV�LIDA";
			}

		} else {
			descricaoOcorrencia = "ANO/M�S DE REFER�NCIA DA CONTA INV�LIDA";
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idConta = null;

			// Valida o amo mes de referencia da conta
			int anoMesReferenciaInt = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				idConta = repositorioFaturamento
						.pesquisarExistenciaContaComSituacaoAtual(imovel,
								anoMesReferenciaInt);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e N�O com
             * a localidade do im�vel.
             */
			if (idConta == null || idConta.equals("")) {
				descricaoOcorrencia = "CONTA INEXISTENTE";
			
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}
			else {
				
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(anoMes);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(null);

			// Verifica se o id da conta � diferente de nulo
			if (idConta != null) {

				ContaGeral conta = new ContaGeral();
				conta.setId(idConta);
				pagamento.setContaGeral(conta);

			} else {

				pagamento.setContaGeral(null);
			}
			pagamento.setGuiaPagamento(null);

			// verifica se o id da conta � diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);
			} else {

				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			pagamento.setDocumentoTipo(documentoTipo);
			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			/*
			 * Alteracao referente ao relatorio de Float - Francisco : 14/07/08
			 */
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());

			colecaoPagamentos.add(pagamento);

		} else {
			// atribui o valor 2(N�O) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2011
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		//LEGADO - COSAMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA
			String identificacao = registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1();
				
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO
			String identificacao = registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1();
				
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
						
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO
			String identificacao = registroHelperCodigoG
			.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento1();
			
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
		}
		else{
			
			super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
					arrecadadorMovimentoItemHelper);
		}
	}
	
	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conte�do do do c�digo de barras
	 * 
	 * [SF0003] Apresentar Dados do Conte�do do C�digo de Barras
	 * 
	 * @author Raphael Rossiter
	 * @data 20/05/2011
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();
		
		
		//LEGADO - COSAMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
			
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
						
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
			
		}
		else{
			
			//GERADO PELO GSAN
			retorno = super.apresentarDadosConteudoCodigoBarras(registroHelperCodigoG);
		}
		

		return retorno;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 03/06/2011
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaArrecadacao
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		boolean idLocalidadeInvalida = false;
		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;
		
		Integer idLocalidade = null;
		Integer idDebitoTipoNaBase = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "C�DIGO DA LOCALIDADE N�O NUM�RICA";
		}
		
		idLocalidade = new Integer(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento2());

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} else {

			// Verifica se existe a matricula do im�vel na base
			matriculaImovel = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1());

			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(new Integer(matriculaImovel));
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}

		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		
		
		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO D�BITO N�O NUM�RICO";
		} else {

			idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento3()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO D�BITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			// GERA��O DO PAGAMENTO
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipoNaBase);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);
			
			pagamento.setGuiaPagamento(null);
			
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			pagamento.setLocalidade(localidade);
			
			DocumentoTipo documentoTipo = new DocumentoTipo();
			if (debitoTipo.getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
				documentoTipo.setId(DocumentoTipo.ENTRADA_DE_PARCELAMENTO);	
			} else {
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			}
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);
			
			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			pagamento.setImovel(imovel);			

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			/*
			 * Alteracao referente ao Relatorio do Float - Francisco: 14/07/08
			 */
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());
			
			colecaoPagamentos.add(pagamento);

		} 
		else {
			
			// Atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * EXTRATO LEGADO
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 03/06/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasExtrato_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		
		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentosRetorno = new ArrayList();
		
		RegistroHelperCodigoBarras codigoBarras = new RegistroHelperCodigoBarras();
		codigoBarras.setIdProduto(registroHelperCodigoBarras.getIdProduto());
		codigoBarras.setIdSegmento(registroHelperCodigoBarras.getIdSegmento());
		codigoBarras.setIdValorReal(registroHelperCodigoBarras.getIdValorReal());
		codigoBarras.setDigitoVerificadorGeral(registroHelperCodigoBarras.getDigitoVerificadorGeral());
		codigoBarras.setIdEmpresa(registroHelperCodigoBarras.getIdEmpresa());
		
		
		for (int i = 1; i <= 4; i++) {
			
			switch (i) {
			case 1:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2().equals("002000")){
					
					//CONTA 01
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;

			case 2:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3().equals("002000")){
					
					//CONTA 02
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento3(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
				
			case 3:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals("002000")){
					
					//CONTA 03
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
				
			case 4:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5().equals("002000")){
					
					//CONTA 04
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
			default:
				break;
			}
		}

		if (colecaoPagamentosRetorno == null || colecaoPagamentosRetorno.isEmpty()){
			
			descricaoOcorrencia = "EXTRATO INEXISTENTE";
			indicadorAceitacaoRegistro = "2";
		}
		
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentosRetorno);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * EXTRATO LEGADO
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 03/06/2010
	 */
	protected Pagamento processarPagamentosCodigoBarrasExtratoPorConta(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento, String idImovel, String anoMesReferenciaConta,
			Integer anoMesPagamento, Integer idFormaArrecadacao) throws ControladorException {
		
		Pagamento pagamentoGerado = null;
		
		/*
		 * FORMATANDO A IDENTIFICA��O DO PAGAMENTO PARA PROCESSAR CONTA POR CONTA
		 * (imovel + referencia + digito verificador + filler
		 */ 
		String idPagamento = idImovel + anoMesReferenciaConta + "1000000000";
		
		//DISTRIBUINDO O C�DIGO DE BARRAS NO FORMATO DE CONTA LEGADO
		RegistroHelperCodigoBarrasTipoPagamento codigoBarrasTipoPagamento = 
		this.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, "1", null);
		
		//ATRIBUINDO O NOVO FORMATO
		registroHelperCodigoBarras.setRegistroHelperCodigoBarrasTipoPagamento(codigoBarrasTipoPagamento);
		
		//PESQUISANDO O VALOR DA CONTA QUE SER� ATRIBUIDO AO VALOR DO PAGAMENTO
		BigDecimal valorPagamentoConta = null;
		
		try {
			
			valorPagamentoConta = repositorioFaturamento.pesquisarValorContaComSituacaoAtual(Integer.parseInt(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1()), Util.formatarMesAnoParaAnoMes(Integer.parseInt(anoMesReferenciaConta)));

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
		//CASO O VALOR DA CONTA N�O SEJA IDENTIFICADO O PAGAMENTO N�O SER� GERADO
		if (valorPagamentoConta != null){
			
			//PROCESSANDO O PAGAMENTO POR CONTA
			PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro, dataPagamento,
					anoMesPagamento, valorPagamentoConta, idFormaArrecadacao);
			
			if (pagamentoHelperCodigoBarras.getColecaoPagamentos() != null &&
				!pagamentoHelperCodigoBarras.getColecaoPagamentos().isEmpty()){
				
				pagamentoGerado = (Pagamento) Util.retonarObjetoDeColecao(pagamentoHelperCodigoBarras.getColecaoPagamentos());
			}
		}
		
		
		return pagamentoGerado;
	}

}
