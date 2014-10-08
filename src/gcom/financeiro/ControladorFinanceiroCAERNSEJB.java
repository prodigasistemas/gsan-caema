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
package gcom.financeiro;

import gcom.financeiro.bean.GerarIntegracaoContabilidadeHelper;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.ejb.SessionBean;

/**
 * Controlador Financeiro CAERN
 *
 * @author Raphael Rossiter
 * @date 26/06/2006
 */
public class ControladorFinanceiroCAERNSEJB extends ControladorFinanceiro implements SessionBean{

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA CAERN
	//==============================================================================================================
	
	
	/**
	 * este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
		
		/*
		 * Pesquisa os dados para gerar a integra��o para a contabilidade.
		 * 
		 * 0 - n�mero do cart�o
		 * 1 - c�digo tipo
		 * 2 - n�mero folha
		 * 3 - indicador linha
		 * 4 - prefixo cont�bil
		 * 5 - n�mero conta
		 * 6 - n�mero d�gito
		 * 7 - n�mero terceiros
		 * 8 - c�digo refer�ncia
		 * 9 - valor lan�amento
		 * 10 - indicador d�bito cr�dito
		 * 11 - n�mero cart�o 2
		 * 12 - n�mero vers�o
		 * 13 - id da localidade
		 * 14 - c�digo centro custo
		 * 
		 */
		Collection<Object[]> colecaoDadosGerarIntegracao = null;

		colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
		
		/** defini��o das vari�veis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		String dataFormatada = data.replace("/","");
		
		/*
		 * Caso a cole��o dos dados n�o esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			/** defini��o das vari�veis */
			
			Short numeroCartao = null;
			String creditoDebito = "";
			BigDecimal valorLancamento = null;
			
			/*
			 * La�o para gerar o txt 
			 */
			Iterator iterator = colecaoDadosGerarIntegracao.iterator();
			while (iterator.hasNext()){
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = (GerarIntegracaoContabilidadeHelper)iterator.next();
				//n�mero do cart�o 
				numeroCartao = gerarIntegracaoContabilidadeHelper.getNumeroCartao();
					
				//CreditoDebito
				creditoDebito = gerarIntegracaoContabilidadeHelper.getCreditoDebito();
		
				//valor do lan�amento
				valorLancamento = (BigDecimal) gerarIntegracaoContabilidadeHelper.getValorLancamento();
				
				
				/*
				 * Inicio da gera��o do txt
				 */
				//Cartao
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(3,numeroCartao + ""));
				//Sequencial
				gerarIntegracaoTxt.append("01");
				//Lote
				gerarIntegracaoTxt.append("8888");
				//Documento
				gerarIntegracaoTxt.append("200001");
				//Linha
				gerarIntegracaoTxt.append("01");
				//data completa
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
				//CreditoDebito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(creditoDebito.trim(), 1));
				//COnta Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaDebito()+"", 20));
				//COnta Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaCredito()+"", 20));
				//Moeda
				gerarIntegracaoTxt.append("SSSSS");
				//Valor Lancamento
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda((valorLancamento + "").replace(".",""), 17));
				//LCO_HISTORICO
				if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL FATURAMENTO", 15));
				}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL ARRECADACAO", 15));
				}
				//MesAno
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarAnoMesParaMesAnoSemBarra(new Integer(anoMes))+"",6));
				//COdigo Custo Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoDebito()+"", 9));
				//COdigo Custo Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoCredito()+"", 9));
				//dia mes ano fechamento
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("DDDD", 4));
				//ANOMES
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6,anoMes));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("LANCAMENTO GCOM", 15));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 318));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 33));
				//Quebra de Linha
				gerarIntegracaoTxt.append(System.getProperty("line.separator"));

			}
			/*
			 * Determina se o arquivo � de faturamento ou arrecada��o 
			 * para concatenar no nome do arquivo .zip
			 */
			String descricaoLancamento = "";
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				descricaoLancamento = "FATURAMENTO";
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				descricaoLancamento = "ARRECADACAO";
			}
			
			/*
			 * Gerando o arquivo zip
			 */
			String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			/*
			 * Caso oarquivo txt n�o esteja vazio 
			 * adiciona o txt ao arquivo zip.
			 */
			if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
				try {
					System.out.println("CRIANDO ZIP");
					zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
					out.write(gerarIntegracaoTxt.toString());
					out.flush();
					ZipUtil.adicionarArquivo(zos, leituraTipo);
					zos.close();
					leituraTipo.delete();
					out.close();
				} catch (IOException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				} catch (Exception e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);

				}
				
			}
			//caso n�o exista informa��o para os dados informados
		}else{
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}
		}
	}
	
	
	
	/**
	 * Este metodo � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Fl�vio Leonardo
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) throws ControladorException{
		
		Collection colecaoObjetoGerar = null;
		Collection colecaoGerarIntegracaoContabilidade = null;
		
		try {

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCaern(idLancamentoOrigem, anoMes);
			
			if(!colecaoObjetoGerar.isEmpty()){
				Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
				
				colecaoGerarIntegracaoContabilidade = new ArrayList();
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
				Object[] objetoGerar = null;
				
				while(iteratorPesquisa.hasNext()){
					gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
					
					objetoGerar = (Object[]) iteratorPesquisa.next();
					
					//indicador debito credito
					if(objetoGerar[10] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta(new Integer((Short) objetoGerar[10]));
					}
					
					//LCO_DEB_CRE
					if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setCreditoDebito("C");
					}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setCreditoDebito("D");
					}
					
					//numero cartao
					if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("402"));
					}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("401"));
					}

					//lancamento tipo
					if(objetoGerar[1] != null){
						gerarIntegracaoContabilidadeHelper.setIdTipoLancamento(new Integer((Short) objetoGerar[1]));
					}
					
					//folha
					if(objetoGerar[2] != null){
						gerarIntegracaoContabilidadeHelper.setFolha(new Integer((Short) objetoGerar[2]));
					}
					
					//linha
					if(objetoGerar[3] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorLinha(new Integer((Short)objetoGerar[3]));
					}
					
					//prefixo contabil
					if(objetoGerar[4] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
					}
					
					//conta
					if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito("");
					}else if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito("");
					}
					
					//digito
					if(objetoGerar[6] != null){
						gerarIntegracaoContabilidadeHelper.setDigito(new Integer(((String) objetoGerar[6]).trim()));
					}
					
					//terceiros
					if(objetoGerar[7] != null){
						gerarIntegracaoContabilidadeHelper.setTerceiros(new Integer(((String) objetoGerar[7]).trim()));
					}
					
					//referencia
					if(objetoGerar[8] != null){
						gerarIntegracaoContabilidadeHelper.setReferencial(new Integer(((String) objetoGerar[8]).trim()));
					}
					
					//valor lancamento
					if(objetoGerar[9] != null){
						gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[9]);
					}
					
					//Cartao2
					if(objetoGerar[11] != null){
						gerarIntegracaoContabilidadeHelper.setCartao2(new Integer((Short) objetoGerar[11]));
					}
					
//					Versao
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setVersao(new Integer((Short) objetoGerar[12]));
					}
					
					/*//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer)objetoGerar[13]);
					}*/
					
					// Indicador Centro Custo
					if ( objetoGerar[14] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorCentroCusto( "" + objetoGerar[14] );
					}
					
					//codigo centro custo
					if(objetoGerar[13] != null 
						&& gerarIntegracaoContabilidadeHelper.getCreditoDebito().equalsIgnoreCase("C")
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto()!=null
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto().equals(ConstantesSistema.SIM+"")){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(((String) objetoGerar[13]).trim()));
					}else{
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(0));
					}
					
					if(objetoGerar[13] != null 
						&& gerarIntegracaoContabilidadeHelper.getCreditoDebito().equalsIgnoreCase("D")
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto()!=null
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto().equals(ConstantesSistema.SIM+"")){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(((String) objetoGerar[13]).trim()));
					}else{
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(0));
					}
					
					colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
				}
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoGerarIntegracaoContabilidade;
	}
	

	
	
}
