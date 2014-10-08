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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Internacionalizador;
import gcom.util.IoUtil;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.zip.ZipOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRTemplatePrintRectangle;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * 
 * @author Vivianne Sousa
 * @date 15/09/2006
 */

public class Relatorio2ViaConta extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;

	/**
	 * Quantidade máxima de linhas do detail da primeira página da conta
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 15;
	
	/**
	 * Quantidade máxima de linhas do detail a partir da segunda página do relátorio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 43;
	
	/**
	 * Quantidade máxima de linhas do detail da primeira página do boleto bancario
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 9;
		
	public Relatorio2ViaConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA);
	}
	
	public Relatorio2ViaConta(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public Relatorio2ViaConta() {
		super(null, "");
	}
	
	
	protected Collection<Relatorio2ViaContaBean> inicializarBeanRelatorio(
			Collection colecaoEmitirContaHelper, SistemaParametro sistemaParametro, String empresa, Fachada fachada,
			String tipoConta, Integer idCliente) {
		
		String enderecoEmpresa = null;
		
		Collection<Relatorio2ViaContaBean> retorno = new ArrayList<Relatorio2ViaContaBean>();
		String cedente = sistemaParametro.getNomeAbreviadoEmpresa() + "-" + sistemaParametro.getNomeEmpresa();
		
		String nomeCliente = null;
		String documentoCliente = null;
		if(tipoConta != null && tipoConta.equals("2") && idCliente != null){
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
			
			Collection<?> colClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			if(!Util.isVazioOrNulo(colClienteImovel)){
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colClienteImovel);
				if(clienteImovel != null && clienteImovel.getCliente() != null){
					nomeCliente = clienteImovel.getCliente().getNome();
					
					if(clienteImovel.getCliente().getCpf() != null){
						documentoCliente = clienteImovel.getCliente().getCpfFormatado();
					}else if(clienteImovel.getCliente().getCnpj() != null){
						documentoCliente = clienteImovel.getCliente().getCnpjFormatado();
					}
				}
			}
		}
		
		Iterator iter = colecaoEmitirContaHelper.iterator();
		while (iter.hasNext()) {
			
			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) iter.next();
			
			enderecoEmpresa = null;
			
			if(sistemaParametro.getNomeAbreviadoEmpresa().toString().equals("CAEMA")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID,
						emitirContaHelper.getIdLocalidade()));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				Collection cLocalidade = 
					(Collection) fachada.pesquisar(
						filtroLocalidade,Localidade.class.getName());
				
				Localidade localidade = (Localidade) cLocalidade.iterator().next();
				
				String numeroFatura = emitirContaHelper.getIdConta()+ "/" + 
					Util.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper.getAmReferencia());
				String endereco = localidade.getEnderecoFormatadoTituloAbreviado();
				String telefone = Util.completaString(localidade.getFone(), 9);
				String cnpj = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
				String inscricaoEstadual = Util.formatarInscricaoEstadualCaema(sistemaParametro.getInscricaoEstadual());
				String dataEmissao = Util.formatarData(new Date());
				/*
				empresa = "Nº Fatura:"+numeroFatura+"    "+endereco+"\n"+
					"Emitida em:"+dataEmissao+"   Cnpj:"+cnpj+"    Fone:"+telefone+"\n" +
					" Insc. Estadual:"+inscricaoEstadual;
				*/
				enderecoEmpresa = "NºFatura:"+numeroFatura+"\n"+ 
				"Emitida em:"+dataEmissao+"\n";
				
				empresa = endereco+"\n"+
					"Cnpj:"+cnpj+"  Fone:"+telefone+"\n" +
					"Insc. Estadual:"+inscricaoEstadual;
				
				
			}
			
			String codigoRota = null;
			
			if(emitirContaHelper.getRotaEntrega()!= null &&	!emitirContaHelper.getRotaEntrega().equals("")){
				codigoRota = emitirContaHelper.getRotaEntrega();
			}
			
			String debitoCreditoSituacaoAtualConta = "";
			if (emitirContaHelper.getDebitoCreditoSituacaoAtualConta()!= null){
				debitoCreditoSituacaoAtualConta = emitirContaHelper.getDebitoCreditoSituacaoAtualConta().toString();
			}
			
			String contaPaga = null;
			if(emitirContaHelper.getContaPaga() != null && !emitirContaHelper.getContaPaga().equals("")){
				contaPaga = emitirContaHelper.getContaPaga();
			}
			
			String enderecoClienteResponsavel = "";
			String enderecoClienteResponsavelLinha2 = "";
			if(emitirContaHelper.getEnderecoClienteResponsavel() != null && !emitirContaHelper.getEnderecoClienteResponsavel().equals("")){
				
				if(emitirContaHelper.getEnderecoClienteResponsavel().length() >= 64){
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel().substring(0,64);
					enderecoClienteResponsavelLinha2 = emitirContaHelper.getEnderecoClienteResponsavel().substring(64);
				}else{
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel();
				}
			}
			
//			String cpfCnpj = "" ;
//			
//			if ( emitirContaHelper.getCpf() != null 
//					&& !emitirContaHelper.getCpf().equals("") ) {
//				cpfCnpj = emitirContaHelper.getCpf();
//			} else if ( emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("") ) {
//				cpfCnpj = emitirContaHelper.getCnpj();
//			}
//			
			
			BigDecimal somaValorAguaEsgoto = emitirContaHelper.getValorAgua().add(emitirContaHelper.getValorEsgoto());
			BigDecimal somaValorAguaEsgotoAuxiliar = somaValorAguaEsgoto;
			
			emitirContaHelper.setSomaValorAguaEsgoto(Util.formatarMoedaReal(somaValorAguaEsgoto));
			emitirContaHelper.setSomaValorAguaEsgotoBoleto(Util.formatarMoedaReal(somaValorAguaEsgoto));
			emitirContaHelper.setValorImpostoPisBoleto(Util.formatarMoedaReal((somaValorAguaEsgoto.multiply(new BigDecimal("1.65")).divide(new BigDecimal("100")))));
			emitirContaHelper.setValorImpostoConfinsBoleto(Util.formatarMoedaReal((somaValorAguaEsgotoAuxiliar.multiply(new BigDecimal("7.6")).divide(new BigDecimal("100")))));
			
			Collection colecaolinhasDescricaoServicosTarifasTotal = emitirContaHelper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();

			Collection<Relatorio2ViaContaDetailBean> colecaoDetail = new ArrayList<Relatorio2ViaContaDetailBean>();
			Collection<Relatorio2ViaContaBoletoBancarioBean> colecaoBoleto = new ArrayList<Relatorio2ViaContaBoletoBancarioBean>();

			int totalLinhasRelatorio = 0;
			int totalPaginasRelatorio = 1;
			int indicadorPrimeiraPagina = 1;

			String dataVencimentoFormatada = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
			
			Boolean ehBoleto = false;
			int boleto = 2;
			int numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA;
			
			if((sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)
				&& emitirContaHelper.getValorConta()!= null && emitirContaHelper.getValorConta().
				compareTo(EmitirContaHelper.VALOR_LIMITE_FICHA_COMPENSACAO) == 1) 
				|| (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)
					&& sistemaParametro.getValorContaFichaComp() != null 
					&& !sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))
					&& emitirContaHelper.getValorConta()!= null 
					&& emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) == 1) ){
				
				ehBoleto = true;
				numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
				
			}
			
			Iterator iterator = colecaolinhasDescricaoServicosTarifasTotal.iterator();		
			while (iterator.hasNext()) {
			
				ContaLinhasDescricaoServicosTarifasTotalHelper linhasDescricaoServicosTarifasTotalHelper 
					= (ContaLinhasDescricaoServicosTarifasTotalHelper) iterator.next();
				
				Relatorio2ViaContaDetailBean relatorio2ViaContaDetailBean = 
					new Relatorio2ViaContaDetailBean(linhasDescricaoServicosTarifasTotalHelper);
				
				colecaoDetail.add(relatorio2ViaContaDetailBean);
				totalLinhasRelatorio = totalLinhasRelatorio + 1;
				
				if ((totalLinhasRelatorio== numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					
						if (ehBoleto){
							
							if (indicadorPrimeiraPagina == 1){
								
								boleto = 1;
								
								Relatorio2ViaContaBoletoBancarioBean boletoBean = 
									new Relatorio2ViaContaBoletoBancarioBean(
										emitirContaHelper, indicadorPrimeiraPagina ,
										totalPaginasRelatorio, contaPaga, 
										colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);
								
								colecaoBoleto.add(boletoBean);
							}
							
						}
						
						Relatorio2ViaContaBean relatorio2ViaContaBean = 
									new Relatorio2ViaContaBean(
										emitirContaHelper, indicadorPrimeiraPagina,
										colecaoDetail, dataVencimentoFormatada,
										enderecoClienteResponsavel,	totalPaginasRelatorio,
										codigoRota,	debitoCreditoSituacaoAtualConta,
										contaPaga, enderecoClienteResponsavelLinha2,
										colecaoBoleto,boleto);
						relatorio2ViaContaBean.setEmpresa(empresa);
						relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
						
						if(nomeCliente != null && documentoCliente != null){
							relatorio2ViaContaBean.setNomeCliente(nomeCliente);
							relatorio2ViaContaBean.setNumeroCpfCnpj(documentoCliente);
						}
						
						
						// -------------------------------------- 2 VIA CONTA INICIO PARTE ESTATICA -------------------------------
						
						if(emitirContaHelper.getCpf()!=null){
							relatorio2ViaContaBean.setNumeroCpfCnpj(Util.formatarCpf(emitirContaHelper.getCpf()));
						}else if(emitirContaHelper.getCnpj()!=null){
							relatorio2ViaContaBean.setNumeroCpfCnpj(Util.formatarCnpj(emitirContaHelper.getCnpj()));
						}
						
						relatorio2ViaContaBean.setTurbidezExigida(emitirContaHelper.getTurbidezExigida());
						relatorio2ViaContaBean.setTurbidezRealizada(emitirContaHelper.getTurbidezRealizada());
						relatorio2ViaContaBean.setTurbidezLegislacao(emitirContaHelper.getTurbidezLegislacao());
						
						relatorio2ViaContaBean.setCorAparenteExigida(emitirContaHelper.getCorAparenteExigida());
						relatorio2ViaContaBean.setCorAparenteRealizada(emitirContaHelper.getCorAparenteRealizada());
						relatorio2ViaContaBean.setCorAparenteLegislacao(emitirContaHelper.getCorAparenteLegislacao());
						
						relatorio2ViaContaBean.setCloroResidualLivreExigido(emitirContaHelper.getCloroResidualLivreExigido());
						relatorio2ViaContaBean.setCloroResidualLivreRealizado(emitirContaHelper.getCloroResidualLivreRealizado());
						relatorio2ViaContaBean.setCloroResidualLivreLegislacao(emitirContaHelper.getCloroResidualLivreLegislacao());
						
						relatorio2ViaContaBean.setColiformesTotaisExigidos(emitirContaHelper.getColiformesTotaisExigidos());
						relatorio2ViaContaBean.setColiformesTotaisRealizado(emitirContaHelper.getColiformesTotaisRealizado());
						relatorio2ViaContaBean.setColiformesTotaisLegislacao(emitirContaHelper.getColiformesTotaisLegislacao());
						
						relatorio2ViaContaBean.setEscherichiaColiExigida(emitirContaHelper.getEscherichiaColiExigida());
						relatorio2ViaContaBean.setEscherichiaColiRealizada(emitirContaHelper.getEscherichiaColiRealizada());
						relatorio2ViaContaBean.setEscherichiaColiLegislacao(emitirContaHelper.getEscherichiaColiLegislacao());
						
						relatorio2ViaContaBean.setNumHidrometro(emitirContaHelper.getNumHidrometro());
						
						relatorio2ViaContaBean.setConsumoTipoDescricaoAbreviadaAgua(emitirContaHelper.getConsumoTipoDescricaoAbreviadaAgua());
						relatorio2ViaContaBean.setDescricaoAnormalidadeConsumoAgua(emitirContaHelper.getDescricaoAnormalidadeConsumoAgua());
						relatorio2ViaContaBean.setConsumoTipoDescricaoAbreviadaEsgoto(emitirContaHelper.getConsumoTipoDescricaoAbreviadaEsgoto());
						relatorio2ViaContaBean.setDescricaoAnormalidadeConsumoEsgoto(emitirContaHelper.getDescricaoAnormalidadeConsumoEsgoto());
						
						relatorio2ViaContaBean.setLeituraAnteriorAgua(emitirContaHelper.getLeituraAnteriorAgua());
						relatorio2ViaContaBean.setLeituraAtualAgua(emitirContaHelper.getLeituraAtualAgua());
						relatorio2ViaContaBean.setLeituraFaturamentoAgua(emitirContaHelper.getLeituraFaturamentoAgua());
						relatorio2ViaContaBean.setDescricaoAnormalidadeLeituraAgua(emitirContaHelper.getDescricaoAnormalidadeLeituraAgua());
						relatorio2ViaContaBean.setConsumoAguaMedioDiario(emitirContaHelper.getConsumoAgua().toString());
						
						relatorio2ViaContaBean.setLeituraAnteriorEsgoto(emitirContaHelper.getLeituraAnteriorEsgoto());
						relatorio2ViaContaBean.setLeituraAtualEsgoto(emitirContaHelper.getLeituraAtualEsgoto());
						relatorio2ViaContaBean.setLeituraFaturamentoEsgoto(emitirContaHelper.getLeituraFaturamentoEsgoto());
						relatorio2ViaContaBean.setDescricaoAnormalidadeLeituraEsgoto(emitirContaHelper.getDescricaoAnormalidadeLeituraEsgoto());
						relatorio2ViaContaBean.setVolumeEsgotoMedioDiario(emitirContaHelper.getConsumoEsgoto().toString());
						
						relatorio2ViaContaBean.setDtLeituraAnterior(emitirContaHelper.getDtLeituraAnterior());
						relatorio2ViaContaBean.setDtLeituraAtual(emitirContaHelper.getDtLeituraAtual());
						
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes1(emitirContaHelper.getConsumoHistoricoAguaMes1());
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes2(emitirContaHelper.getConsumoHistoricoAguaMes2());
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes3(emitirContaHelper.getConsumoHistoricoAguaMes3());
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes4(emitirContaHelper.getConsumoHistoricoAguaMes4());
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes5(emitirContaHelper.getConsumoHistoricoAguaMes5());
						relatorio2ViaContaBean.setConsumoHistoricoAguaMes6(emitirContaHelper.getConsumoHistoricoAguaMes6());
						
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes1(emitirContaHelper.getConsumoHistoricoEsgotoMes1());
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes2(emitirContaHelper.getConsumoHistoricoEsgotoMes2());
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes3(emitirContaHelper.getConsumoHistoricoEsgotoMes3());
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes4(emitirContaHelper.getConsumoHistoricoEsgotoMes4());
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes5(emitirContaHelper.getConsumoHistoricoEsgotoMes5());
						relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes6(emitirContaHelper.getConsumoHistoricoEsgotoMes6());
						
						relatorio2ViaContaBean.setMediaConsumoAgua(emitirContaHelper.getMediaConsumoAgua());
						relatorio2ViaContaBean.setMediaConsumoEsgoto(emitirContaHelper.getMediaConsumoEsgoto());
						
						relatorio2ViaContaBean.setResidencial(emitirContaHelper.getResidencial());
						relatorio2ViaContaBean.setComercial(emitirContaHelper.getComercial());
						relatorio2ViaContaBean.setIndustrial(emitirContaHelper.getIndustrial());
						relatorio2ViaContaBean.setPublico(emitirContaHelper.getPublico());
						
						if(emitirContaHelper.getCodigoDebitoAutomatico()!=null &&
								!emitirContaHelper.getCodigoDebitoAutomatico().equals("")){
							
							relatorio2ViaContaBean.setCodigoDebitoAutomatico(emitirContaHelper.getCodigoDebitoAutomaticoFormatado());
								
						}
						
						relatorio2ViaContaBean.setSomaValorAguaEsgoto(emitirContaHelper.getSomaValorAguaEsgoto());
						relatorio2ViaContaBean.setValorImpostoPis(emitirContaHelper.getValorImpostoPis());
						relatorio2ViaContaBean.setValorImpostoCofins(emitirContaHelper.getValorImpostoCofins());
						
						relatorio2ViaContaBean.setTamanhoGraficoConsumoAgua(emitirContaHelper.getTamanhoGraficoConsumoAgua());
						relatorio2ViaContaBean.setTamanhoGraficoVolumeEsgoto(emitirContaHelper.getTamanhoGraficoVolumeEsgoto());
						relatorio2ViaContaBean.setTamanhoGraficoMediaConsumoAguaVolumeEsgoto(emitirContaHelper.getTamanhoGraficoMediaConsumoAguaVolumeEsgoto());
						
						// -------------------------------------- 2 VIA CONTA INICIO PARTE ESTATICA -------------------------------

						
						retorno.add(relatorio2ViaContaBean);
						colecaoDetail.clear();
						colecaoBoleto.clear();
				}
			
				
				
				
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
				
			}	
			
			if  (totalLinhasRelatorio!= numeroMaxLinhasDetailPrimeiraPagina && 
					((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) %
							NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0)) {
	
				if (ehBoleto){
					
					if (indicadorPrimeiraPagina == 1){
						
						boleto = 1;
						
						Relatorio2ViaContaBoletoBancarioBean boletoBean = 
							new Relatorio2ViaContaBoletoBancarioBean(
								emitirContaHelper, indicadorPrimeiraPagina ,
								totalPaginasRelatorio, contaPaga, 
								colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);
						
						colecaoBoleto.add(boletoBean);
						
					}
					
				}
				
				Relatorio2ViaContaBean relatorio2ViaContaBean = 
					new Relatorio2ViaContaBean(
						emitirContaHelper, indicadorPrimeiraPagina,
						colecaoDetail, dataVencimentoFormatada,
						enderecoClienteResponsavel,	totalPaginasRelatorio,
						codigoRota,	debitoCreditoSituacaoAtualConta,
						contaPaga, enderecoClienteResponsavelLinha2,
						colecaoBoleto,boleto);
				
				relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
				relatorio2ViaContaBean.setEmpresa(empresa);
				
				if(nomeCliente != null && documentoCliente != null){
					relatorio2ViaContaBean.setNomeCliente(nomeCliente);
					relatorio2ViaContaBean.setNumeroCpfCnpj(documentoCliente);
				}
				
				
				// -------------------------------------- 2 VIA CONTA INICIO PARTE ESTATICA -------------------------------
				
				if(emitirContaHelper.getCpf()!=null){
					relatorio2ViaContaBean.setNumeroCpfCnpj(Util.formatarCpf(emitirContaHelper.getCpf()));
				}else if(emitirContaHelper.getCnpj()!=null){
					relatorio2ViaContaBean.setNumeroCpfCnpj(Util.formatarCnpj(emitirContaHelper.getCnpj()));
				}
				
				relatorio2ViaContaBean.setTurbidezExigida(emitirContaHelper.getTurbidezExigida());
				relatorio2ViaContaBean.setTurbidezRealizada(emitirContaHelper.getTurbidezRealizada());
				relatorio2ViaContaBean.setTurbidezLegislacao(emitirContaHelper.getTurbidezLegislacao());
				
				relatorio2ViaContaBean.setCorAparenteExigida(emitirContaHelper.getCorAparenteExigida());
				relatorio2ViaContaBean.setCorAparenteRealizada(emitirContaHelper.getCorAparenteRealizada());
				relatorio2ViaContaBean.setCorAparenteLegislacao(emitirContaHelper.getCorAparenteLegislacao());
				
				relatorio2ViaContaBean.setCloroResidualLivreExigido(emitirContaHelper.getCloroResidualLivreExigido());
				relatorio2ViaContaBean.setCloroResidualLivreRealizado(emitirContaHelper.getCloroResidualLivreRealizado());
				relatorio2ViaContaBean.setCloroResidualLivreLegislacao(emitirContaHelper.getCloroResidualLivreLegislacao());
				
				relatorio2ViaContaBean.setColiformesTotaisExigidos(emitirContaHelper.getColiformesTotaisExigidos());
				relatorio2ViaContaBean.setColiformesTotaisRealizado(emitirContaHelper.getColiformesTotaisRealizado());
				relatorio2ViaContaBean.setColiformesTotaisLegislacao(emitirContaHelper.getColiformesTotaisLegislacao());
				
				relatorio2ViaContaBean.setEscherichiaColiExigida(emitirContaHelper.getEscherichiaColiExigida());
				relatorio2ViaContaBean.setEscherichiaColiRealizada(emitirContaHelper.getEscherichiaColiRealizada());
				relatorio2ViaContaBean.setEscherichiaColiLegislacao(emitirContaHelper.getEscherichiaColiLegislacao());
				
				relatorio2ViaContaBean.setNumHidrometro(emitirContaHelper.getNumHidrometro());
				
				relatorio2ViaContaBean.setConsumoTipoDescricaoAbreviadaAgua(emitirContaHelper.getConsumoTipoDescricaoAbreviadaAgua());
				relatorio2ViaContaBean.setDescricaoAnormalidadeConsumoAgua(emitirContaHelper.getDescricaoAnormalidadeConsumoAgua());
				relatorio2ViaContaBean.setConsumoTipoDescricaoAbreviadaEsgoto(emitirContaHelper.getConsumoTipoDescricaoAbreviadaEsgoto());
				relatorio2ViaContaBean.setDescricaoAnormalidadeConsumoEsgoto(emitirContaHelper.getDescricaoAnormalidadeConsumoEsgoto());
				
				relatorio2ViaContaBean.setLeituraAnteriorAgua(emitirContaHelper.getLeituraAnteriorAgua());
				relatorio2ViaContaBean.setLeituraAtualAgua(emitirContaHelper.getLeituraAtualAgua());
				relatorio2ViaContaBean.setLeituraFaturamentoAgua(emitirContaHelper.getLeituraFaturamentoAgua());
				relatorio2ViaContaBean.setDescricaoAnormalidadeLeituraAgua(emitirContaHelper.getDescricaoAnormalidadeLeituraAgua());
				relatorio2ViaContaBean.setConsumoAguaMedioDiario(emitirContaHelper.getConsumoAgua().toString());
				
				relatorio2ViaContaBean.setLeituraAnteriorEsgoto(emitirContaHelper.getLeituraAnteriorEsgoto());
				relatorio2ViaContaBean.setLeituraAtualEsgoto(emitirContaHelper.getLeituraAtualEsgoto());
				relatorio2ViaContaBean.setLeituraFaturamentoEsgoto(emitirContaHelper.getLeituraFaturamentoEsgoto());
				relatorio2ViaContaBean.setDescricaoAnormalidadeLeituraEsgoto(emitirContaHelper.getDescricaoAnormalidadeLeituraEsgoto());
				relatorio2ViaContaBean.setVolumeEsgotoMedioDiario(emitirContaHelper.getConsumoEsgoto().toString());
				
				relatorio2ViaContaBean.setDtLeituraAnterior(emitirContaHelper.getDtLeituraAnterior());
				relatorio2ViaContaBean.setDtLeituraAtual(emitirContaHelper.getDtLeituraAtual());
				
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes1(emitirContaHelper.getConsumoHistoricoAguaMes1());
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes2(emitirContaHelper.getConsumoHistoricoAguaMes2());
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes3(emitirContaHelper.getConsumoHistoricoAguaMes3());
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes4(emitirContaHelper.getConsumoHistoricoAguaMes4());
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes5(emitirContaHelper.getConsumoHistoricoAguaMes5());
				relatorio2ViaContaBean.setConsumoHistoricoAguaMes6(emitirContaHelper.getConsumoHistoricoAguaMes6());
				
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes1(emitirContaHelper.getConsumoHistoricoEsgotoMes1());
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes2(emitirContaHelper.getConsumoHistoricoEsgotoMes2());
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes3(emitirContaHelper.getConsumoHistoricoEsgotoMes3());
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes4(emitirContaHelper.getConsumoHistoricoEsgotoMes4());
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes5(emitirContaHelper.getConsumoHistoricoEsgotoMes5());
				relatorio2ViaContaBean.setConsumoHistoricoEsgotoMes6(emitirContaHelper.getConsumoHistoricoEsgotoMes6());
				
				relatorio2ViaContaBean.setMediaConsumoAgua(emitirContaHelper.getMediaConsumoAgua());
				relatorio2ViaContaBean.setMediaConsumoEsgoto(emitirContaHelper.getMediaConsumoEsgoto());
				
				relatorio2ViaContaBean.setResidencial(emitirContaHelper.getResidencial());
				relatorio2ViaContaBean.setComercial(emitirContaHelper.getComercial());
				relatorio2ViaContaBean.setIndustrial(emitirContaHelper.getIndustrial());
				relatorio2ViaContaBean.setPublico(emitirContaHelper.getPublico());
				
				emitirContaHelper.setSomaValorAguaEsgoto(Util.formatarMoedaReal(somaValorAguaEsgoto));
				emitirContaHelper.setValorImpostoPisBoleto(Util.formatarMoedaReal((somaValorAguaEsgoto.multiply(new BigDecimal("1.65")).divide(new BigDecimal("100")))));
				emitirContaHelper.setValorImpostoConfinsBoleto(Util.formatarMoedaReal((somaValorAguaEsgotoAuxiliar.multiply(new BigDecimal("7.6")).divide(new BigDecimal("100")))));
				
				relatorio2ViaContaBean.setSomaValorAguaEsgoto(emitirContaHelper.getSomaValorAguaEsgoto());
				relatorio2ViaContaBean.setValorImpostoPis(emitirContaHelper.getValorImpostoPis());
				relatorio2ViaContaBean.setValorImpostoCofins(emitirContaHelper.getValorImpostoCofins());
				
				if(emitirContaHelper.getCodigoDebitoAutomatico()!=null &&
						!emitirContaHelper.getCodigoDebitoAutomatico().equals("")){
					
					relatorio2ViaContaBean.setCodigoDebitoAutomatico(emitirContaHelper.getCodigoDebitoAutomaticoFormatado());
						
				}
				
				relatorio2ViaContaBean.setTamanhoGraficoConsumoAgua(emitirContaHelper.getTamanhoGraficoConsumoAgua());
				relatorio2ViaContaBean.setTamanhoGraficoVolumeEsgoto(emitirContaHelper.getTamanhoGraficoVolumeEsgoto());
				relatorio2ViaContaBean.setTamanhoGraficoMediaConsumoAguaVolumeEsgoto(emitirContaHelper.getTamanhoGraficoMediaConsumoAguaVolumeEsgoto());
				
				// -------------------------------------- 2 VIA CONTA FIM PARTE ESTATICA -------------------------------
				
				retorno.add(relatorio2ViaContaBean);

			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		Collection idsConta = (Collection) getParametro("idsConta");
		boolean cobrarTaxaEmissaoConta = (Boolean) getParametro("cobrarTaxaEmissaoConta");
		Short contaSemCodigoBarras = (Short) getParametro("contaSemCodigoBarras");
		
		Integer idCliente = (Integer) getParametro("idCliente");
		String tipoConta = (String) getParametro("tipoConta");
		
		Integer idContaHistorico = (Integer)getParametro("idContaHistorico");
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clientePresidenteCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalIdPresidencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorComercialCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteFicticioParaAssociarOsPagamentosNaoIdentificados");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteUsuarioDesconhecido");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteResponsavelProgramaEspecial");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");
		
		Collection colecaoSistemaParametro = 
				fachada.pesquisar(filtroSistemaParametro, 
					SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		Collection colecaoEmitirContaHelper = new ArrayList();
		if (idContaHistorico == null) {
			colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			colecaoEmitirContaHelper = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		String nomeUsuario = "";
		
		//**********************************************************************
		// Alterado por: Ivan Sergio
		// Data: 30/04/2009
		// CRC1656
		//**********************************************************************
		if (usuario != null) {
			if (sistemaParametro.getIndicadorImprimeUsuarioSegundaVia().equals(ConstantesSistema.SIM)) {
				idUsuario = usuario.getId().toString();
				nomeUsuario = usuario.getNomeUsuario();
			}
		} else {
			idUsuario = "INTERNET";
			nomeUsuario = "INTERNET";
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
	 
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",nomeEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("numero0800compesa", sistemaParametro.getNumero0800Empresa());
		
		String empresa = "\n	  	"+nomeEmpresa +" - "+cnpjEmpresa;
		
		Collection dadosRelatorio = colecaoEmitirContaHelper;

		Collection<Relatorio2ViaContaBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio,sistemaParametro,empresa,fachada, tipoConta, idCliente);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		// DADOS DA EMPRESA
		String dadosEmpresa = "";
		// PESQUISA E RETORNA O ENDERECO FORMATADO REFERENTE AO SISTEMA PARAMETRO
		String enderecoFormatado = sistemaParametro.getEnderecoFormatado2ViaContaFichaCompensacao();
		
		/*
		 * VERIFICA SE EH DIFERENTE DE NULL
		 * ENDERECO FORMATADO
		 */
		if(enderecoFormatado!=null){
			dadosEmpresa += enderecoFormatado+". ";
		}
		/*
		 * VERIFICA SE EH DIFERENTE DE NULL
		 * TELEFONE
		 */
		if(sistemaParametro.getNumeroTelefone()!=null){
			dadosEmpresa += "Fone: (081) "+sistemaParametro.getNumeroTelefone()+"\n";
		}
		/*
		 * VERIFICA SE EH DIFERENTE DE NULL
		 * INSCRICAO ESTADUAL
		 */
		if(sistemaParametro.getInscricaoEstadual()!=null){
			dadosEmpresa += "Inscrição Estadual: "+Util.formatarInscricaoEstadualPE(sistemaParametro.getInscricaoEstadual())+"\n";
		}
		/*
		 * VERIFICA SE EH DIFERENTE DE NULL
		 * CNPJ
		 */
		if(sistemaParametro.getCnpjEmpresa()!=null){
			dadosEmpresa += "CNPJ: "+Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		// ATRIBUI OS DADOS DA EMPRESA AO PARAMETRO DO RELATORIO
		parametros.put("dadosEmpresa",dadosEmpresa);
		parametros.put("imagemLogoArpe", sistemaParametro.getImagemArpe());
		parametros.put("imagemLogoCompesaConta0800", sistemaParametro.getImagemRodapeConta());
		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA, parametros, ds,
				tipoFormatoRelatorio,colecaoBean);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.SEGUNDA_VIA_CONTA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Integer qtdeContas = (Integer) getParametro("qtdeContas");
		
		return qtdeContas;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("Relatorio2ViaConta", this);
	}
	
	/**
	 * @autor Jonathan
	 * @date 10/01/2013
	 * [Observacao] Esse metodo foi  Sobrescrito
	 */
	public byte[] gerarRelatorio(String nomeRelatorio, Map parametrosRelatorio,
			RelatorioDataSource relatorioDataSource, int tipoSaidaRelatorio,
			Collection<Relatorio2ViaContaBean> colecaoBean)
					throws RelatorioVazioException {

		// valor de retorno
		ByteArrayOutputStream retorno = new ByteArrayOutputStream();
		byte[] retornoArray = null;
		// cria uma instância da classe JasperReport que vai conter o relatório
		// criado
		JasperReport jasperReport = null;
		InputStream stream = null;

		try {
			// carrega o arquivo do relatório (jasper) já compilado
			stream = (ConstantesRelatorios.getURLRelatorio(nomeRelatorio)).openStream();
			// carrega o relatório compilado
			jasperReport = (JasperReport) JRLoader.loadObject(stream);
		
			stream.close();

			if(Internacionalizador.getLocale() != null){
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, Internacionalizador.getLocale())) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, Internacionalizador.getLocale());				
			}else{
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, new Locale("pt","BR"))) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, new Locale("pt","BR"));								
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					parametrosRelatorio, relatorioDataSource);
			
			
			// GERAR GRAFICO RELATORIO
			jasperPrint = this.gerarGraficoConsumoAguaVolumeEsgoto(jasperPrint, colecaoBean);
			
			// Verifica qual o tipo de relatório para definir a geração
			switch (tipoSaidaRelatorio) {
			case TIPO_PDF:

				JRPdfExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterPDF.exportReport();

				break;
			case TIPO_RTF:

				JRRtfExporter exporterRTF = new JRRtfExporter();
				exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterRTF.exportReport();

				break;
			case TIPO_XLS:

				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterXLS.setParameter(
						JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						Boolean.TRUE);
				
				//exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, false);
				//exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
				exporterXLS.exportReport();

				break;
			case TIPO_HTML:

				// Para evitar problemas de concorrência, o nome do arquivo html
				// gerado possui um número aleatório no nome
				int numeroNomeRelatorio = new Double((Math.random() * 10000))
						.intValue();
				JasperExportManager.exportReportToHtmlFile(jasperPrint,
						"relatorio" + numeroNomeRelatorio + ".html");

				// pegar o arquivo, zipar pasta e arquivo e escrever no stream
				// criar o arquivo zip
				File arquivoZip = File.createTempFile("zipHtml"
						+ numeroNomeRelatorio, ".zip");

				ZipOutputStream zos = new ZipOutputStream(
						new FileOutputStream(arquivoZip));

				// Pega o arquivo gerado do relatório
				File pagina = new File("relatorio" + numeroNomeRelatorio
						+ ".html");
				// Pega a pasta que acompanha o arquivo do relatório gerado
				File pastaDeImagens = new File("relatorio"
						+ numeroNomeRelatorio + ".html_files");
				ZipUtil.adicionarArquivo(zos, pagina);
				ZipUtil.adicionarPasta(pastaDeImagens, zos);
				// close the stream
				zos.close();

				FileInputStream inputStream = new FileInputStream(
						arquivoZip);

				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0,
						INPUT_BUFFER_SIZE)) != -1) {
					retorno.write(temp, 0, numBytesRead);
				}

				inputStream.close();
				inputStream = null;

				// Apaga todo o conteúdo gerado
				pagina.delete();
				IoUtil.deleteDir(pastaDeImagens);
				arquivoZip.delete();

				break;
			default:
				throw new RelatorioVazioException(
						"Escolha um tipo de relatório");
			}
			retornoArray = retorno.toByteArray();

		} catch (JRException ex) {
			// erro ao cria o relatório
			ex.printStackTrace();
			throw new SistemaException(ex, "Erro ao criar relatório");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SistemaException(e, "Erro ao criar relatório");
		}finally{
			if (stream != null) {
				try {
					stream.close();					
				} catch (IOException e) {
					e.printStackTrace();
					throw new SistemaException(e, "Erro ao fechar relatório");
				}
			}
		}
		// RETORNO
		return retornoArray;
	}
	
	/**
	 * @autor Jonathan
	 * @date 06/01/2014
	 * @param jasperReport
	 * @param parametrosRelatorio
	 */
	public JasperPrint gerarGraficoConsumoAguaVolumeEsgoto(
			JasperPrint jasperPrint,Collection<Relatorio2ViaContaBean>
			colecaoBean){
		
		// ARRAY COM TODAS AS PAGINAS DO RELATORIO
		ArrayList<JRPrintPage> listaDePaginasRelatorioTemporaria = (ArrayList<JRPrintPage>) jasperPrint.getPages();
		ArrayList<JRPrintPage> listaDePaginasRelatorio = null;
		
		int quantidadePaginas = listaDePaginasRelatorioTemporaria.size();
		
		// QUANTIDADE DE PAGINAS CONTIDA NO RELATORIO
		int paginaAtualRelatorio = 0;
		
		// VETOR COM AS POSICOES DOS ELEMENTOS CONTENDO OS RELANGULOS EM CADA PAGINA
		int[] vetorRetangulosPagina = {140,141,142,143,144,145,146,147,148,149,150,151};
		int[] vetorRetangulosPaginaMedia = {169,168};
		int posicaoVetorRetangulos = 0;
		int posicaoVetorRetangulosMedia = 0;
		
		// JRTemplatePrintRectangle RETORNA RETANGULO DA PAGINA
		JRTemplatePrintRectangle jrTemplatePrintRectangle = null;
	
		// OBJETO QUE ARMAZENA OS BEANS
		Relatorio2ViaContaBean relatorio2ViaContaBean = null;
		
		Iterator iteratorColecaoBean = colecaoBean.iterator();
		
		int posicaoArrayTamanhoGraficoConsumoAgua = 0;
		int posicaoArrayTamanhoGraficoVolumeEsgoto = 0;
		
		while(paginaAtualRelatorio<quantidadePaginas){
			
			posicaoVetorRetangulos = 0;
			posicaoVetorRetangulosMedia = 0;
			posicaoArrayTamanhoGraficoConsumoAgua = 0;
			posicaoArrayTamanhoGraficoVolumeEsgoto = 0;
			
			// BEAN ATUAL DE IMPRESSAO
			relatorio2ViaContaBean = (Relatorio2ViaContaBean) iteratorColecaoBean.next();
			
			// MONTA GRAFICO CONSUMO AGUA E VOLUME ESGOTO 6 ULTIMOS MESES 
			while(posicaoVetorRetangulos<12){
				// RETORNA RETANGULA
				jrTemplatePrintRectangle =(JRTemplatePrintRectangle) listaDePaginasRelatorioTemporaria.get(paginaAtualRelatorio)
						.getElements().get(vetorRetangulosPagina[posicaoVetorRetangulos]);
			
				if(vetorRetangulosPagina[posicaoVetorRetangulos]%2==0){
					// ATRIBUIR VALOR GRAFICO AO RETANGULO CONSUMO AGUA
					jrTemplatePrintRectangle.setWidth(relatorio2ViaContaBean.getTamanhoGraficoConsumoAgua()[posicaoArrayTamanhoGraficoConsumoAgua]);
					listaDePaginasRelatorioTemporaria.get(paginaAtualRelatorio).getElements().set(vetorRetangulosPagina[posicaoVetorRetangulos], jrTemplatePrintRectangle);
					posicaoArrayTamanhoGraficoConsumoAgua++;
				}else{
					// ATRIBUIR VALOR GRAFICO AO RETANGULO VOLUME ESGOTO
					jrTemplatePrintRectangle.setWidth(relatorio2ViaContaBean.getTamanhoGraficoVolumeEsgoto()[posicaoArrayTamanhoGraficoVolumeEsgoto]);
					listaDePaginasRelatorioTemporaria.get(paginaAtualRelatorio).getElements().set(vetorRetangulosPagina[posicaoVetorRetangulos], jrTemplatePrintRectangle);
					posicaoArrayTamanhoGraficoVolumeEsgoto++;
				}
				posicaoVetorRetangulos++;
			}
			
			// MONTA GRAFICO MEDIA DOS 6 ULTIMOS MESES CONSUMO AGUA E VOLUME ESGOTO
			while(posicaoVetorRetangulosMedia<2){
				// RETORNA RETANGULO
				jrTemplatePrintRectangle =(JRTemplatePrintRectangle) listaDePaginasRelatorioTemporaria.get(paginaAtualRelatorio)
						.getElements().get(vetorRetangulosPaginaMedia[posicaoVetorRetangulosMedia]);
				
				// ATRIBUI VALOR AO GRAFICO AO RETANGULO VOLUME ESGOTO
				jrTemplatePrintRectangle.setWidth(relatorio2ViaContaBean.getTamanhoGraficoMediaConsumoAguaVolumeEsgoto()[posicaoVetorRetangulosMedia]);
				listaDePaginasRelatorioTemporaria.get(paginaAtualRelatorio).getElements().set(vetorRetangulosPaginaMedia[posicaoVetorRetangulosMedia], jrTemplatePrintRectangle);
				
				posicaoVetorRetangulosMedia++;
			}
			
			paginaAtualRelatorio++;
		}
		
		listaDePaginasRelatorio = new ArrayList<JRPrintPage>(listaDePaginasRelatorioTemporaria);
		jasperPrint.getPages().clear();
		jasperPrint.getPages().addAll(listaDePaginasRelatorio);
		
		return jasperPrint;
	}
}
