package gcom.relatorio.atendimentopublico;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "rawtypes", "unused" })
public class RelatorioDadosKitCASServico extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	
	public RelatorioDadosKitCASServico(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_DADOS_KIT_CAS_SERVICOS);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}
	
	//Constantes
	private static final Integer TOTALIZACAO_ESTADO = 1;
    private static final Integer TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL = 2;
    private static final Integer TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO = 3;
    private static final Integer TOTALIZACAO_ESTADO_POR_MUNICIPIO = 4;
    
	private static final Integer TOTALIZACAO_GERENCIA_REGIONAL = 6;
    private static final Integer TOTALIZACAO_UNIDADE_NEGOCIO = 10;
    private static final Integer TOTALIZACAO_LOCALIDADE = 17;
	private static final Integer TOTALIZACAO_MUNICIPIO = 20;
	
	private static final String GRUPO_CATEGORIA = "1";
	private static final String GRUPO_PERFIL = "2";


	@Override
	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarResumoDadosCasHelper helper = (FiltrarResumoDadosCasHelper) getParametro("helper");
		Integer tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		byte[] retorno = null;

		List<RelatorioDadosKitCASServicoBean> beans = new ArrayList<RelatorioDadosKitCASServicoBean>();
		
		ArrayList<Integer> categorias = new ArrayList<Integer>();
		categorias.add(Categoria.RESIDENCIAL);
		categorias.add(Categoria.COMERCIAL);
		categorias.add(Categoria.INDUSTRIAL);
		categorias.add(Categoria.PUBLICO);
		
		ArrayList<Integer> tipos = new ArrayList<Integer>();
		tipos.add(ImovelPerfil.NORMAL);
		tipos.add(ImovelPerfil.TARIFA_SOCIAL);
		tipos.add(ImovelPerfil.GRANDE);
		tipos.add(ImovelPerfil.CORPORATIVO);
		tipos.add(ImovelPerfil.GRANDE_TELEMEDIDO);
		tipos.add(ImovelPerfil.CORPORATIVO_TELEMED);
		tipos.add(ImovelPerfil.CHAFARIZ);
		tipos.add(ImovelPerfil.MICRO_TELEMEDIDO);
		tipos.add(ImovelPerfil.CADASTRO_PROVISORIO);
		
		
		Iterator itCategoria = categorias.iterator();
		Iterator itTipo = tipos.iterator();
		RelatorioDadosKitCASServicoBean bean = null;
		RelatorioDadosKitCASServicoBean beanTotal = new RelatorioDadosKitCASServicoBean();
		RelatorioDadosKitCASServicoBean beanTotal2 = new RelatorioDadosKitCASServicoBean();
		boolean mudou = false;
		
		while(itCategoria.hasNext()){
			
			Iterator iteratorBeans = beans.iterator();
			
			
			Integer categoria = (Integer)itCategoria.next();
			//=================================================================================================================
			//CATEGORIA
			//=================================================================================================================
			
			//Instalação Hidrômetro
			Collection colecaoDadosInstalacaoHidrometro = 
					fachada.obterDadosKitCASInstalacaoHidrometro(helper,categoria,null);
			
			//Ligacao Economia
			Collection colecaoDadosLigacaoEconomia = 
					fachada.obterDadosKitCASLigacaoEconomia(helper,categoria,null);
			
			//Servicos
			Collection colecaoDadosServicos = fachada.obterDadosKitCASServicos(helper,categoria,null);
			
			//Resumo Faturamento
			Collection colecaoDadosResumoFaturamento = fachada.obterDadosKitCASResumoFaturamento(helper,categoria,null);
			
			//Desempenho micromedicao
			Collection colecaoDadosDesempenhoMicromedicao = fachada.obterDadosKitCASDesempenhoMicromedicao(helper,categoria,null);
			
			
			//Leitura Anormalidade
			Collection colecaoDadosLeituraAnormalidade = fachada.obterDadosKitCASLeituraAnormalidade(helper,categoria,null);
			
			//Consumo água
			Collection colecaoDadosConsumoAgua = fachada.obterDadosKitCASConsumoAgua(helper,categoria,null);
			
			//Consumo água 2
			Collection colecaoDadosConsumoAgua2 = fachada.obterDadosKitCASConsumoAgua2(helper,categoria,null);
			
			//Ligacao Inativa
			Collection colecaoDadosLigacaoInativa = fachada.obterDadosKitCASLigacaoInativaEconomia(helper,categoria,null);
			
			//Cartas de Negativação
			Collection colecaoDadosCartasNegativacao = fachada.obterDadosKitCASServicosCartasNegativacao(helper,categoria,null);
			
			//Faturas em Revisão
			Collection colecaoFaturasRevisao1 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,categoria,null,ContaMotivoRevisao.REVISAO_RECLAMACAO_CONSUMO);
			Collection colecaoFaturasRevisao2 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,categoria,null,ContaMotivoRevisao.REVISAO_RECORRENCIA);
			Collection colecaoFaturasRevisao3 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,categoria,null,ContaMotivoRevisao.REVISAO_FATURAMENTO_INDEVIDO);
			
	
			//Iterators
			//==================================================================================
			//
			Iterator itInstalacaoHidrometro = colecaoDadosInstalacaoHidrometro.iterator();
			Iterator itLigacaoEconomia = colecaoDadosLigacaoEconomia.iterator();
			Iterator itDadosServicos = colecaoDadosServicos.iterator();
			Iterator itResumoFaturamento = colecaoDadosResumoFaturamento.iterator();
			Iterator itDesempenhoMicromedicao = colecaoDadosDesempenhoMicromedicao.iterator();
			Iterator itLeituraAnormalidade = colecaoDadosLeituraAnormalidade.iterator();
			Iterator itConsumoAgua = colecaoDadosConsumoAgua.iterator();
			Iterator itConsumoAgua2 = colecaoDadosConsumoAgua2.iterator();
			Iterator itLigacaoInativa = colecaoDadosLigacaoInativa.iterator();
			Iterator itCartasNeg = colecaoDadosCartasNegativacao.iterator();
			Iterator itFaturasRevisao1 = colecaoFaturasRevisao1.iterator();
			Iterator itFaturasRevisao2 = colecaoFaturasRevisao2.iterator();
			Iterator itFaturasRevisao3 = colecaoFaturasRevisao3.iterator();
			
			while(itInstalacaoHidrometro.hasNext() ||
					itLigacaoEconomia.hasNext() ||
					itDadosServicos.hasNext() ||
					itResumoFaturamento.hasNext() ||
					itDesempenhoMicromedicao.hasNext() ||
					itLeituraAnormalidade.hasNext() ||
					itConsumoAgua.hasNext() ||
					itConsumoAgua2.hasNext() ||
					itLigacaoInativa.hasNext() ||
					itCartasNeg.hasNext() ||
					(itFaturasRevisao1.hasNext() &&
					 itFaturasRevisao2.hasNext() &&
					 itFaturasRevisao3.hasNext()
					)){
					
					
						//Ligacao Economia
						Object[] objLE = null; 		
						if(colecaoDadosLigacaoEconomia != null && colecaoDadosLigacaoEconomia.size() > 0 && itLigacaoEconomia.hasNext()){
							objLE = 	(Object[]) itLigacaoEconomia.next();
							bean = new RelatorioDadosKitCASServicoBean();
							bean.setGrupoDados(GRUPO_CATEGORIA);
						
							switch(categoria){
								case Categoria.RESIDENCIAL_INT:
									bean.setCortesExecutadosResidencial((Integer)objLE[0]);
									bean.setReligacoesResidencial((Integer)objLE[1]);
									bean.setLigImplantadasAguaResidencial((Integer)objLE[2]);
									bean.setLigImplantadasEsgotoResidencial((Integer)objLE[3]);	
								break;
								
								case Categoria.COMERCIAL_INT:
									bean.setCortesExecutadosComercial((Integer)objLE[0]);
									bean.setReligacoesComercial((Integer)objLE[1]);
									bean.setLigImplantadasAguaComercial((Integer)objLE[2]);
									bean.setLigImplantadasEsgotoComercial((Integer)objLE[3]);
								break;
								case Categoria.INDUSTRIAL_INT:
									bean.setCortesExecutadosIndustrial((Integer)objLE[0]);
									bean.setReligacoesIndustrial((Integer)objLE[1]);
									bean.setLigImplantadasAguaIndustrial((Integer)objLE[2]);
									bean.setLigImplantadasEsgotoIndustrial((Integer)objLE[3]);
								break;
								case Categoria.PUBLICO_INT:
									bean.setCortesExecutadosPublico((Integer)objLE[0]);
									bean.setReligacoesPublico((Integer)objLE[1]);
									bean.setLigImplantadasAguaPublico((Integer)objLE[2]);
									bean.setLigImplantadasEsgotoPublico((Integer)objLE[3]);
								break;
							}
							
							
							
								bean.setGerenciaRegionalID((String)objLE[4]);
								bean.setGerenciaRegional((String)objLE[5]);
								bean.setUnidadeNegocioID((String)objLE[6]);
								bean.setUnidadeNegocio((String)objLE[7]);
								bean.setLocalidadeID((String)objLE[8]);
								bean.setLocalidade((String)objLE[9]);				
								bean.setMunicipioID((String)objLE[10]);
								bean.setMunicipio((String)objLE[11]);
								
								beans.add(bean);
					}
							
						
							
							
							
				
					//=============================================================================================
					
					//Instalação Hidrômetro
					Object[] objIH = null;	
					if(colecaoDadosInstalacaoHidrometro != null && colecaoDadosInstalacaoHidrometro.size() > 0 && itInstalacaoHidrometro.hasNext()){
						objIH = (Object[]) itInstalacaoHidrometro.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setHidSubstituidosRamalResidencial((Integer)objIH[0]);
								bean.setHidSubstituidosPocoResidencial((Integer)objIH[1]);
								bean.setHidInstaladosRamalResidencial((Integer)objIH[2]);
								bean.setHidInstaladosPocoResidencial((Integer)objIH[3]);
							break;
							
							case Categoria.COMERCIAL_INT:
								bean.setHidSubstituidosRamalComercial((Integer)objIH[0]);
								bean.setHidSubstituidosPocoComercial((Integer)objIH[1]);
								bean.setHidInstaladosRamalComercial((Integer)objIH[2]);
								bean.setHidInstaladosPocoComercial((Integer)objIH[3]);
								
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setHidSubstituidosRamalIndustrial((Integer)objIH[0]);
								bean.setHidSubstituidosPocoIndustrial((Integer)objIH[1]);
								bean.setHidInstaladosRamalIndustrial((Integer)objIH[2]);
								bean.setHidInstaladosPocoIndustrial((Integer)objIH[3]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setHidSubstituidosRamalPublico((Integer)objIH[0]);
								bean.setHidSubstituidosPocoPublico((Integer)objIH[1]);
								bean.setHidInstaladosRamalPublico((Integer)objIH[2]);
								bean.setHidInstaladosPocoPublico((Integer)objIH[3]);
							break;
							
						}
						
						
						bean.setGerenciaRegionalID((String)objIH[4]);
						bean.setGerenciaRegional((String)objIH[5]);
						bean.setUnidadeNegocioID((String)objIH[6]);
						bean.setUnidadeNegocio((String)objIH[7]);
						bean.setLocalidadeID((String)objIH[8]);
						bean.setLocalidade((String)objIH[9]);				
						bean.setMunicipioID((String)objIH[10]);
						bean.setMunicipio((String)objIH[11]);
						
						beans.add(bean);
						
						
					}
					
					//Cartas Negativação
					Object[] objCN = null;
					if(colecaoDadosCartasNegativacao != null && colecaoDadosCartasNegativacao.size() > 0 && itCartasNeg.hasNext()){
						objCN = (Object[]) itCartasNeg.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setCartasNegativacaoResidencial((Integer)objCN[0]);	
							break;
							case Categoria.COMERCIAL_INT:
								bean.setCartasNegativacaoComercial((Integer)objCN[0]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setCartasNegativacaoIndustrial((Integer)objCN[0]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setCartasNegativacaoPublico((Integer)objCN[0]);
							break;
						}
					
						bean.setGerenciaRegionalID((String)objCN[1]);
						bean.setGerenciaRegional((String)objCN[2]);
						bean.setUnidadeNegocioID((String)objCN[3]);
						bean.setUnidadeNegocio((String)objCN[4]);
						bean.setLocalidadeID((String)objCN[5]);
						bean.setLocalidade((String)objCN[6]);				
						bean.setMunicipioID((String)objCN[7]);
						bean.setMunicipio((String)objCN[8]);
						
						beans.add(bean);
						
					}
					
					//Servicos
					Object[] objSE = null;
					if(colecaoDadosServicos != null && colecaoDadosServicos.size() > 0 && itDadosServicos.hasNext()){
						objSE = (Object[]) itDadosServicos.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setSuprExecutadasResidencial((Integer)objSE[0]);
								bean.setReestabResidencial((Integer)objSE[1]);
								bean.setClandCortadosResidencial((Integer)objSE[2]);
								bean.setClandSuprimidosResidencial((Integer)objSE[3]);
								bean.setAvisoCorteResidencial((Integer)objSE[4]);
								bean.setRaPendentesComPrazoResidencial((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoResidencial((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoResidencial((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoResidencial((Integer)objSE[8]);
								bean.setFaturaRevisaoResidencial((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaResidencial((Integer)objSE[11]);
								bean.setRegRecebidosResidencial((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoResidencial((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoResidencial((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoResidencial((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoResidencial((Integer)objSE[24]);
								bean.setAlteracaoCategoriaResidencial((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialResidencial((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialResidencial((Integer)objSE[27]);
								bean.setFaturamentoSuspensoResidencial((Integer)objSE[28]);
								
							break;
							
							case Categoria.COMERCIAL_INT:
								bean.setSuprExecutadasComercial((Integer)objSE[0]);
								bean.setReestabComercial((Integer)objSE[1]);
								bean.setClandCortadosComercial((Integer)objSE[2]);
								bean.setClandSuprimidosComercial((Integer)objSE[3]);
								bean.setAvisoCorteComercial((Integer)objSE[4]);
								bean.setRaPendentesComPrazoComercial((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoComercial((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoComercial((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoComercial((Integer)objSE[8]);
								bean.setFaturaRevisaoComercial((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaComercial((Integer)objSE[11]);
								bean.setRegRecebidosComercial((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoComercial((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoComercial((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoComercial((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoComercial((Integer)objSE[24]);
								bean.setAlteracaoCategoriaComercial((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialComercial((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialComercial((Integer)objSE[27]);
								bean.setFaturamentoSuspensoComercial((Integer)objSE[28]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setSuprExecutadasIndustrial((Integer)objSE[0]);
								bean.setReestabIndustrial((Integer)objSE[1]);
								bean.setClandCortadosIndustrial((Integer)objSE[2]);
								bean.setClandSuprimidosIndustrial((Integer)objSE[3]);
								bean.setAvisoCorteIndustrial((Integer)objSE[4]);
								bean.setRaPendentesComPrazoIndustrial((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoIndustrial((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoIndustrial((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoIndustrial((Integer)objSE[8]);
								bean.setFaturaRevisaoIndustrial((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaIndustrial((Integer)objSE[11]);
								bean.setRegRecebidosIndustrial((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoIndustrial((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoIndustrial((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoIndustrial((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoIndustrial((Integer)objSE[24]);
								bean.setAlteracaoCategoriaIndustrial((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialIndustrial((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialIndustrial((Integer)objSE[27]);
								bean.setFaturamentoSuspensoIndustrial((Integer)objSE[28]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setSuprExecutadasPublico((Integer)objSE[0]);
								bean.setReestabPublico((Integer)objSE[1]);
								bean.setClandCortadosPublico((Integer)objSE[2]);
								bean.setClandSuprimidosPublico((Integer)objSE[3]);
								bean.setAvisoCortePublico((Integer)objSE[4]);
								bean.setRaPendentesComPrazoPublico((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoPublico((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoPublico((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoPublico((Integer)objSE[8]);
								bean.setFaturaRevisaoPublico((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaPublico((Integer)objSE[11]);
								bean.setRegRecebidosPublico((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoPublico((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoPublico((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoPublico((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoPublico((Integer)objSE[24]);
								bean.setAlteracaoCategoriaPublico((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialPublico((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialPublico((Integer)objSE[27]);
								bean.setFaturamentoSuspensoPublico((Integer)objSE[28]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objSE[13]);
						bean.setGerenciaRegional((String)objSE[14]);
						bean.setUnidadeNegocioID((String)objSE[15]);
						bean.setUnidadeNegocio((String)objSE[16]);
						bean.setLocalidadeID((String)objSE[17]);
						bean.setLocalidade((String)objSE[18]);				
						bean.setMunicipioID((String)objSE[19]);
						bean.setMunicipio((String)objSE[20]);
						
						beans.add(bean);
						
					}
					
					
					//Resumo Faturamento
					Object[] objRF = null;
					if(colecaoDadosResumoFaturamento != null && colecaoDadosResumoFaturamento.size() > 0 && itResumoFaturamento.hasNext()){
						objRF = (Object[]) itResumoFaturamento.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setContasEmitidasResidencial((Integer)objRF[0]);	
							break;
							
							case Categoria.COMERCIAL_INT:
								bean.setContasEmitidasComercial((Integer)objRF[0]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setContasEmitidasIndustrial((Integer)objRF[0]);	
							break;
							case Categoria.PUBLICO_INT:
								bean.setContasEmitidasPublico((Integer)objRF[0]);	
							break;
						}
						
						bean.setGerenciaRegionalID((String)objRF[1]);
						bean.setGerenciaRegional((String)objRF[2]);
						bean.setUnidadeNegocioID((String)objRF[3]);
						bean.setUnidadeNegocio((String)objRF[4]);
						bean.setLocalidadeID((String)objRF[5]);
						bean.setLocalidade((String)objRF[6]);				
						bean.setMunicipioID((String)objRF[7]);
						bean.setMunicipio((String)objRF[8]);
						
						beans.add(bean);
						
					}
					
					
					//Desempenho micromedicao
					Object[] objDM = null;
					if(colecaoDadosDesempenhoMicromedicao != null && colecaoDadosDesempenhoMicromedicao.size() > 0 && itDesempenhoMicromedicao.hasNext()){
						objDM = (Object[]) itDesempenhoMicromedicao.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setLigAtivasHidrometroResidencial((Integer)objDM[0]);
								bean.setLigAtivasResidencial((Integer)objDM[1]);
								
							break;
							case Categoria.COMERCIAL_INT:
								bean.setLigAtivasHidrometroComercial((Integer)objDM[0]);
								bean.setLigAtivasComercial((Integer)objDM[1]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setLigAtivasHidrometroIndustrial((Integer)objDM[0]);
								bean.setLigAtivasIndustrial((Integer)objDM[1]);
								
							break;
							case Categoria.PUBLICO_INT:
								bean.setLigAtivasHidrometroPublico((Integer)objDM[0]);
								bean.setLigAtivasPublico((Integer)objDM[1]);
								
							break;
						}
						
						bean.setGerenciaRegionalID((String)objDM[2]);
						bean.setGerenciaRegional((String)objDM[3]);
						bean.setUnidadeNegocioID((String)objDM[4]);
						bean.setUnidadeNegocio((String)objDM[5]);
						bean.setLocalidadeID((String)objDM[6]);
						bean.setLocalidade((String)objDM[7]);				
						bean.setMunicipioID((String)objDM[8]);
						bean.setMunicipio((String)objDM[9]);
					
						beans.add(bean);
						
					}
					
					
					//Leitura Anormalidade
					Object[] objLA = null;
					if(colecaoDadosLeituraAnormalidade != null && colecaoDadosLeituraAnormalidade.size() > 0 && itLeituraAnormalidade.hasNext()){
						objLA = (Object[]) itLeituraAnormalidade.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setLeitEfetuadasResidencial((Integer)objLA[0]);
								bean.setLeitAnormalidadesResidencial((Integer)objLA[1]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setLeitEfetuadasComercial((Integer)objLA[0]);
								bean.setLeitAnormalidadesComercial((Integer)objLA[1]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setLeitEfetuadasIndustrial((Integer)objLA[0]);
								bean.setLeitAnormalidadesIndustrial((Integer)objLA[1]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setLeitEfetuadasPublico((Integer)objLA[0]);
								bean.setLeitAnormalidadesPublico((Integer)objLA[1]);
							break;
						}				
						
						bean.setGerenciaRegionalID((String)objLA[2]);
						bean.setGerenciaRegional((String)objLA[3]);
						bean.setUnidadeNegocioID((String)objLA[4]);
						bean.setUnidadeNegocio((String)objLA[5]);
						bean.setLocalidadeID((String)objLA[6]);
						bean.setLocalidade((String)objLA[7]);				
						bean.setMunicipioID((String)objLA[8]);
						bean.setMunicipio((String)objLA[9]);
						
						beans.add(bean);
						
					}
					
					
					//Consumo Água
					Object[] objCA = null;
					if(colecaoDadosConsumoAgua != null && colecaoDadosConsumoAgua.size() > 0 && itConsumoAgua.hasNext()){
						objCA = (Object[]) itConsumoAgua.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setEconomiaAtivaResidencial((Integer)objCA[0]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setEconomiaAtivaComercial((Integer)objCA[0]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setEconomiaAtivaIndustrial((Integer)objCA[0]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setEconomiaAtivaPublico((Integer)objCA[0]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objCA[1]);
						bean.setGerenciaRegional((String)objCA[2]);
						bean.setUnidadeNegocioID((String)objCA[3]);
						bean.setUnidadeNegocio((String)objCA[4]);
						bean.setLocalidadeID((String)objCA[5]);
						bean.setLocalidade((String)objCA[6]);				
						bean.setMunicipioID((String)objCA[7]);
						bean.setMunicipio((String)objCA[8]);
						
						beans.add(bean);
					}
					
					//Consumo Água 2
					Object[] objCA2 = null;
					if(colecaoDadosConsumoAgua2 != null && colecaoDadosConsumoAgua2.size() > 0 && itConsumoAgua2.hasNext()){
						objCA2 = (Object[]) itConsumoAgua2.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setLigacoesAtivasResidencial((Integer)objCA2[0]);
							break;
							case Categoria.COMERCIAL_INT:	
								bean.setLigacoesAtivasComercial((Integer)objCA2[0]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setLigacoesAtivasIndustrial((Integer)objCA2[0]);
							break;
							case Categoria.PUBLICO_INT:	
								bean.setLigacoesAtivasPublico((Integer)objCA2[0]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objCA2[1]);
						bean.setGerenciaRegional((String)objCA2[2]);
						bean.setUnidadeNegocioID((String)objCA2[3]);
						bean.setUnidadeNegocio((String)objCA2[4]);
						bean.setLocalidadeID((String)objCA2[5]);
						bean.setLocalidade((String)objCA2[6]);				
						bean.setMunicipioID((String)objCA2[7]);
						bean.setMunicipio((String)objCA2[8]);
						
						beans.add(bean);
					}
					
					//Ligacao Inativa
					Object[] objLI = null;
					if(colecaoDadosLigacaoInativa != null && colecaoDadosLigacaoInativa.size() > 0 && itLigacaoInativa.hasNext()){
						objLI = (Object[]) itLigacaoInativa.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setEconomiaInativaResidencial((Integer)objLI[0]);
								bean.setLigacoesInativasResidencial((Integer)objLI[1]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setEconomiaInativaComercial((Integer)objLI[0]);
								bean.setLigacoesInativasComercial((Integer)objLI[1]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setEconomiaInativaIndustrial((Integer)objLI[0]);
								bean.setLigacoesInativasIndustrial((Integer)objLI[1]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setEconomiaInativaPublico((Integer)objLI[0]);
								bean.setLigacoesInativasPublico((Integer)objLI[1]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objLI[2]);
						bean.setGerenciaRegional((String)objLI[3]);
						bean.setUnidadeNegocioID((String)objLI[4]);
						bean.setUnidadeNegocio((String)objLI[5]);
						bean.setLocalidadeID((String)objLI[6]);
						bean.setLocalidade((String)objLI[7]);				
						bean.setMunicipioID((String)objLI[8]);
						bean.setMunicipio((String)objLI[9]);
						
						beans.add(bean);
					}
					
					//Faturas em Revisão 1
					Object[] objFR1 = null;
					if(colecaoFaturasRevisao1 != null && colecaoFaturasRevisao1.size() > 0 && itFaturasRevisao1.hasNext()){
						
						objFR1 = (Object[]) itFaturasRevisao1.next();
						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setFaturaRevisaoReclamacaoConsumoResidencial((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoResidencial((Integer)objFR1[9]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setFaturaRevisaoReclamacaoConsumoComercial((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoComercial((Integer)objFR1[9]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setFaturaRevisaoReclamacaoConsumoIndustrial((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial((Integer)objFR1[9]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setFaturaRevisaoReclamacaoConsumoPublico((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoPublico((Integer)objFR1[9]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objFR1[1]);
						bean.setGerenciaRegional((String)objFR1[2]);
						bean.setUnidadeNegocioID((String)objFR1[3]);
						bean.setUnidadeNegocio((String)objFR1[4]);
						bean.setLocalidadeID((String)objFR1[5]);
						bean.setLocalidade((String)objFR1[6]);				
						bean.setMunicipioID((String)objFR1[7]);
						bean.setMunicipio((String)objFR1[8]);
						
						beans.add(bean);
						
					}
					
					//Faturas em Revisão 2
					Object[] objFR2 = null;

					if(colecaoFaturasRevisao2 != null && colecaoFaturasRevisao2.size() > 0 && itFaturasRevisao2.hasNext()){
						
						objFR2 = (Object[]) itFaturasRevisao2.next();
						
						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setFaturaRevisaoRecorrenciaResidencial((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaResidencial((Integer)objFR2[9]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setFaturaRevisaoRecorrenciaComercial((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaComercial((Integer)objFR2[9]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setFaturaRevisaoRecorrenciaIndustrial((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaIndustrial((Integer)objFR2[9]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setFaturaRevisaoRecorrenciaPublico((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaPublico((Integer)objFR2[9]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objFR2[1]);
						bean.setGerenciaRegional((String)objFR2[2]);
						bean.setUnidadeNegocioID((String)objFR2[3]);
						bean.setUnidadeNegocio((String)objFR2[4]);
						bean.setLocalidadeID((String)objFR2[5]);
						bean.setLocalidade((String)objFR2[6]);				
						bean.setMunicipioID((String)objFR2[7]);
						bean.setMunicipio((String)objFR2[8]);
						
						beans.add(bean);
						
					}
					
					//Faturas em Revisão 3
					Object[] objFR3 = null;
					
					if(colecaoFaturasRevisao3 != null && colecaoFaturasRevisao3.size() > 0 && itFaturasRevisao3.hasNext()){
						
						objFR3 = (Object[]) itFaturasRevisao3.next();
						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(categoria){
							case Categoria.RESIDENCIAL_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoResidencial((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial((Integer)objFR3[9]);
							break;
							case Categoria.COMERCIAL_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoComercial((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoComercial((Integer)objFR3[9]);
							break;
							case Categoria.INDUSTRIAL_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoIndustrial((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial((Integer)objFR3[9]);
							break;
							case Categoria.PUBLICO_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoPublico((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoPublico((Integer)objFR3[9]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objFR3[1]);
						bean.setGerenciaRegional((String)objFR3[2]);
						bean.setUnidadeNegocioID((String)objFR3[3]);
						bean.setUnidadeNegocio((String)objFR3[4]);
						bean.setLocalidadeID((String)objFR3[5]);
						bean.setLocalidade((String)objFR3[6]);				
						bean.setMunicipioID((String)objFR3[7]);
						bean.setMunicipio((String)objFR3[8]);
						
						beans.add(bean);
						
					}
					
				}
		}
		
		
		mudou = false;
		List<RelatorioDadosKitCASServicoBean> beans2 = new ArrayList<RelatorioDadosKitCASServicoBean>();
		
		while(itTipo.hasNext()){
						
			Integer tipo = (Integer)itTipo.next();
			//=================================================================================================================
			//TIPO 
			//=================================================================================================================
			
			//Instalação Hidrômetro
			Collection colecaoDadosInstalacaoHidrometro = 
					fachada.obterDadosKitCASInstalacaoHidrometro(helper,null,tipo);
			
			//Ligacao Economia
			Collection colecaoDadosLigacaoEconomia = 
					fachada.obterDadosKitCASLigacaoEconomia(helper,null,tipo);
			
			//Servicos
			Collection colecaoDadosServicos = fachada.obterDadosKitCASServicos(helper,null,tipo);
			
			//Resumo Faturamento
			Collection colecaoDadosResumoFaturamento = fachada.obterDadosKitCASResumoFaturamento(helper,null,tipo);
			
			//Desempenho micromedicao
			Collection colecaoDadosDesempenhoMicromedicao = fachada.obterDadosKitCASDesempenhoMicromedicao(helper,null,tipo);
			
			
			//Leitura Anormalidade
			Collection colecaoDadosLeituraAnormalidade = fachada.obterDadosKitCASLeituraAnormalidade(helper,null,tipo);
			
			//Consumo água
			Collection colecaoDadosConsumoAgua = fachada.obterDadosKitCASConsumoAgua(helper,null,tipo);
			
			//Consumo água 2
			Collection colecaoDadosConsumoAgua2 = fachada.obterDadosKitCASConsumoAgua2(helper,null,tipo);
			
			//Ligacao Inativa
			Collection colecaoDadosLigacaoInativa = fachada.obterDadosKitCASLigacaoInativaEconomia(helper,null,tipo);
			

			//Cartas de Negativação
			Collection colecaoDadosCartasNegativacao = fachada.obterDadosKitCASServicosCartasNegativacao(helper,null,tipo);
			
			//Faturas em Revisão
			Collection colecaoFaturasRevisao1 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,null,tipo,ContaMotivoRevisao.REVISAO_RECLAMACAO_CONSUMO);
			Collection colecaoFaturasRevisao2 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,null,tipo,ContaMotivoRevisao.REVISAO_RECORRENCIA);
			Collection colecaoFaturasRevisao3 = 
					fachada.obterDadosKitCASServicosTipoRevisao(helper,null,tipo,ContaMotivoRevisao.REVISAO_FATURAMENTO_INDEVIDO);
			
	
			//Variáveis para acumular
			//
			Integer hidSubstituidos = new Integer(0);
			Integer hidInstalados = new Integer(0);
			Integer cortesExecutados = new Integer(0);
			Integer suprExecutadas = new Integer(0);
			Integer religacoes = new Integer(0);
			Integer reestab = new Integer(0);
			Integer clandCortados = new Integer(0);
			Integer clandSuprimidos = new Integer(0);
			Integer contasEmitidas = new Integer(0);
			Integer leitEfetuadas = new Integer(0);
			Integer leitAnormalidades = new Integer(0);
			Integer avisoCorte = new Integer(0);
			BigDecimal percAnormalidade = new BigDecimal(0);
			BigDecimal percHidrometracao = new BigDecimal(0);
			Integer ligImplantadasAgua = new Integer(0);
			Integer ligImplantadasEsgoto = new Integer(0);
			Integer raPendentesComPrazo = new Integer(0);
			Integer raPendentesComForaPrazo = new Integer(0);
			Integer raPendentesOpPrazo = new Integer(0);
			Integer raPendentesOpForaPrazo = new Integer(0);
			Integer faturaRevisao = new Integer(0);
			Integer cartasNegativacao = new Integer(0);
			Integer economiaAtiva = new Integer(0);
			Integer economiaInativa = new Integer(0);
			Integer ligacoesAtivas = new Integer(0);
			Integer ligacoesInativas = new Integer(0);
			Integer total = new Integer(0);
			
			
			//Iterators
			//==================================================================================
			//
			Iterator itInstalacaoHidrometro = colecaoDadosInstalacaoHidrometro.iterator();
			Iterator itLigacaoEconomia = colecaoDadosLigacaoEconomia.iterator();
			Iterator itDadosServicos = colecaoDadosServicos.iterator();
			Iterator itResumoFaturamento = colecaoDadosResumoFaturamento.iterator();
			Iterator itDesempenhoMicromedicao = colecaoDadosDesempenhoMicromedicao.iterator();
			Iterator itLeituraAnormalidade = colecaoDadosLeituraAnormalidade.iterator();
			Iterator itConsumoAgua = colecaoDadosConsumoAgua.iterator();
			Iterator itConsumoAgua2 = colecaoDadosConsumoAgua2.iterator();
			Iterator itLigacaoInativa = colecaoDadosLigacaoInativa.iterator();
			Iterator itCartasNeg = colecaoDadosCartasNegativacao.iterator();
			Iterator itFaturasRevisao1 = colecaoFaturasRevisao1.iterator();
			Iterator itFaturasRevisao2 = colecaoFaturasRevisao2.iterator();
			Iterator itFaturasRevisao3 = colecaoFaturasRevisao3.iterator();
			
			while(itInstalacaoHidrometro.hasNext() ||
					itLigacaoEconomia.hasNext() ||
					itDadosServicos.hasNext() ||
					itResumoFaturamento.hasNext() ||
					itDesempenhoMicromedicao.hasNext() ||
					itLeituraAnormalidade.hasNext() ||
					itConsumoAgua.hasNext() ||
					itConsumoAgua2.hasNext() ||
					itLigacaoInativa.hasNext() ||
					itCartasNeg.hasNext() ||
					(itFaturasRevisao1.hasNext() &&
					 itFaturasRevisao2.hasNext() &&
					 itFaturasRevisao3.hasNext()
					)){
						
					
					//Ligacao Economia
					Object[] objLE = null; 
					if(colecaoDadosLigacaoEconomia != null && colecaoDadosLigacaoEconomia.size() > 0 && itLigacaoEconomia.hasNext()){
						
						objLE = 	(Object[]) itLigacaoEconomia.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							
							case ImovelPerfil.NORMAL_INT:
								bean.setCortesExecutadosNormal((Integer)objLE[0]);
								bean.setReligacoesNormal((Integer)objLE[1]);
								bean.setLigImplantadasAguaNormal((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoNormal((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setCortesExecutadosTarSocial((Integer)objLE[0]);
								bean.setReligacoesTarSocial((Integer)objLE[1]);
								bean.setLigImplantadasAguaTarSocial((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoTarSocial((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.GRANDE_INT:
								bean.setCortesExecutadosGrande((Integer)objLE[0]);
								bean.setReligacoesGrande((Integer)objLE[1]);
								bean.setLigImplantadasAguaGrande((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoGrande((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setCortesExecutadosCorporativo((Integer)objLE[0]);
								bean.setReligacoesCorporativo((Integer)objLE[1]);
								bean.setLigImplantadasAguaCorporativo((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoCorporativo((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setCortesExecutadosGrandeTelemedido((Integer)objLE[0]);
								bean.setReligacoesGrandeTelemedido((Integer)objLE[1]);
								bean.setLigImplantadasAguaGrandeTelemedido((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoGrandeTelemedido((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setCortesExecutadosCorpTelemedido((Integer)objLE[0]);
								bean.setReligacoesCorpTelemedido((Integer)objLE[1]);
								bean.setLigImplantadasAguaCorpTelemedido((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoCorpTelemedido((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setCortesExecutadosChafariz((Integer)objLE[0]);
								bean.setReligacoesChafariz((Integer)objLE[1]);
								bean.setLigImplantadasAguaChafariz((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoChafariz((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setCortesExecutadosMicroTelemedido((Integer)objLE[0]);
								bean.setReligacoesMicroTelemedido((Integer)objLE[1]);
								bean.setLigImplantadasAguaMicroTelemedido((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoMicroTelemedido((Integer)objLE[3]);	
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setCortesExecutadosCadastroProvisorio((Integer)objLE[0]);
								bean.setReligacoesCadastroProvisorio((Integer)objLE[1]);
								bean.setLigImplantadasAguaCadastroProvisorio((Integer)objLE[2]);
								bean.setLigImplantadasEsgotoCadastroProvisorio((Integer)objLE[3]);	
							break;
							
							
						}	
						
						bean.setGerenciaRegionalID((String)objLE[4]);
						bean.setGerenciaRegional((String)objLE[5]);
						bean.setUnidadeNegocioID((String)objLE[6]);
						bean.setUnidadeNegocio((String)objLE[7]);
						bean.setLocalidadeID((String)objLE[8]);
						bean.setLocalidade((String)objLE[9]);				
						bean.setMunicipioID((String)objLE[10]);
						bean.setMunicipio((String)objLE[11]);
						
						beans2.add(bean);
					
					}
					
					//Instalação Hidrômetro
					Object[] objIH = null;
					if(colecaoDadosInstalacaoHidrometro != null && colecaoDadosInstalacaoHidrometro.size() > 0 && itInstalacaoHidrometro.hasNext()){
						objIH = (Object[]) itInstalacaoHidrometro.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setHidSubstituidosRamalNormal((Integer)objIH[0]);
								bean.setHidSubstituidosPocoNormal((Integer)objIH[1]);
								bean.setHidInstaladosRamalNormal((Integer)objIH[2]);
								bean.setHidInstaladosPocoNormal((Integer)objIH[3]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setHidSubstituidosRamalTarSocial((Integer)objIH[0]);
								bean.setHidSubstituidosPocoTarSocial((Integer)objIH[1]);
								bean.setHidInstaladosRamalTarSocial((Integer)objIH[2]);
								bean.setHidInstaladosPocoTarSocial((Integer)objIH[3]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setHidSubstituidosRamalGrande((Integer)objIH[0]);
								bean.setHidSubstituidosPocoGrande((Integer)objIH[1]);
								bean.setHidInstaladosRamalGrande((Integer)objIH[2]);
								bean.setHidInstaladosPocoGrande((Integer)objIH[3]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setHidSubstituidosRamalCorporativo((Integer)objIH[0]);
								bean.setHidSubstituidosPocoCorporativo((Integer)objIH[1]);
								bean.setHidInstaladosRamalCorporativo((Integer)objIH[2]);
								bean.setHidInstaladosPocoCorporativo((Integer)objIH[3]);
							break;
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setHidSubstituidosRamalGrandeTelemedido((Integer)objIH[0]);
								bean.setHidSubstituidosPocoGrandeTelemedido((Integer)objIH[1]);
								bean.setHidInstaladosRamalGrandeTelemedido((Integer)objIH[2]);
								bean.setHidInstaladosPocoGrandeTelemedido((Integer)objIH[3]);
							break;
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setHidSubstituidosRamalCorpTelemedido((Integer)objIH[0]);
								bean.setHidSubstituidosPocoCorpTelemedido((Integer)objIH[1]);
								bean.setHidInstaladosRamalCorpTelemedido((Integer)objIH[2]);
								bean.setHidInstaladosPocoCorpTelemedido((Integer)objIH[3]);
							break;
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setHidSubstituidosRamalChafariz((Integer)objIH[0]);
								bean.setHidSubstituidosPocoChafariz((Integer)objIH[1]);
								bean.setHidInstaladosRamalChafariz((Integer)objIH[2]);
								bean.setHidInstaladosPocoChafariz((Integer)objIH[3]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setHidSubstituidosRamalMicroTelemedido((Integer)objIH[0]);
								bean.setHidSubstituidosPocoMicroTelemedido((Integer)objIH[1]);
								bean.setHidInstaladosRamalMicroTelemedido((Integer)objIH[2]);
								bean.setHidInstaladosPocoMicroTelemedido((Integer)objIH[3]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setHidSubstituidosRamalCadastroProvisorio((Integer)objIH[0]);
								bean.setHidSubstituidosPocoCadastroProvisorio((Integer)objIH[1]);
								bean.setHidInstaladosRamalCadastroProvisorio((Integer)objIH[2]);
								bean.setHidInstaladosPocoCadastroProvisorio((Integer)objIH[3]);
							break;
							
						}
						
						bean.setGerenciaRegionalID((String)objIH[4]);
						bean.setGerenciaRegional((String)objIH[5]);
						bean.setUnidadeNegocioID((String)objIH[6]);
						bean.setUnidadeNegocio((String)objIH[7]);
						bean.setLocalidadeID((String)objIH[8]);
						bean.setLocalidade((String)objIH[9]);				
						bean.setMunicipioID((String)objIH[10]);
						bean.setMunicipio((String)objIH[11]);
						
						beans2.add(bean);
						
					}
					
					
					//Cartas Negativação
					Object[] objCN = null;
					if(colecaoDadosCartasNegativacao != null && colecaoDadosCartasNegativacao.size() > 0 && itCartasNeg.hasNext()){
						objCN = (Object[]) itCartasNeg.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setCartasNegativacaoNormal((Integer)objCN[0]);	
							break;
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setCartasNegativacaoTarSocial((Integer)objCN[0]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setCartasNegativacaoGrande((Integer)objCN[0]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setCartasNegativacaoCorporativo((Integer)objCN[0]);
							break;
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setCartasNegativacaoGrandeTelemedido((Integer)objCN[0]);
							break;
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setCartasNegativacaoCorpTelemedido((Integer)objCN[0]);
							break;
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setCartasNegativacaoChafariz((Integer)objCN[0]);
							break;
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setCartasNegativacaoMicroTelemedido((Integer)objCN[0]);
							break;
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setCartasNegativacaoCadastroProvisorio((Integer)objCN[0]);
							break;
						}
					
						bean.setGerenciaRegionalID((String)objCN[1]);
						bean.setGerenciaRegional((String)objCN[2]);
						bean.setUnidadeNegocioID((String)objCN[3]);
						bean.setUnidadeNegocio((String)objCN[4]);
						bean.setLocalidadeID((String)objCN[5]);
						bean.setLocalidade((String)objCN[6]);				
						bean.setMunicipioID((String)objCN[7]);
						bean.setMunicipio((String)objCN[8]);
						
						beans2.add(bean);
						
					}
					
					
					//Servicos
					Object[] objSE = null;
					if(colecaoDadosServicos != null && colecaoDadosServicos.size() > 0 && itDadosServicos.hasNext()){
						objSE = (Object[]) itDadosServicos.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){	
							case ImovelPerfil.NORMAL_INT:
								bean.setSuprExecutadasNormal((Integer)objSE[0]);
								bean.setReestabNormal((Integer)objSE[1]);
								bean.setClandCortadosNormal((Integer)objSE[2]);
								bean.setClandSuprimidosNormal((Integer)objSE[3]);
								bean.setAvisoCorteNormal((Integer)objSE[4]);
								bean.setRaPendentesComPrazoNormal((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoNormal((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoNormal((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoNormal((Integer)objSE[8]);
								bean.setFaturaRevisaoNormal((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaNormal((Integer)objSE[11]);
								bean.setRegRecebidosNormal((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoNormal((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoNormal((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoNormal((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoNormal((Integer)objSE[24]);
								bean.setAlteracaoCategoriaNormal((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialNormal((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialNormal((Integer)objSE[27]);
								bean.setFaturamentoSuspensoNormal((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setSuprExecutadasTarSocial((Integer)objSE[0]);
								bean.setReestabTarSocial((Integer)objSE[1]);
								bean.setClandCortadosTarSocial((Integer)objSE[2]);
								bean.setClandSuprimidosTarSocial((Integer)objSE[3]);
								bean.setAvisoCorteTarSocial((Integer)objSE[4]);
								bean.setRaPendentesComPrazoTarSocial((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoTarSocial((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoTarSocial((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoTarSocial((Integer)objSE[8]);
								bean.setFaturaRevisaoTarSocial((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaTarSocial((Integer)objSE[11]);
								bean.setRegRecebidosTarSocial((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoTarSocial((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoTarSocial((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoTarSocial((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoTarSocial((Integer)objSE[24]);
								bean.setAlteracaoCategoriaTarSocial((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialTarSocial((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialTarSocial((Integer)objSE[27]);
								bean.setFaturamentoSuspensoTarSocial((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.GRANDE_INT:
								bean.setSuprExecutadasGrande((Integer)objSE[0]);
								bean.setReestabGrande((Integer)objSE[1]);
								bean.setClandCortadosGrande((Integer)objSE[2]);
								bean.setClandSuprimidosGrande((Integer)objSE[3]);
								bean.setAvisoCorteGrande((Integer)objSE[4]);
								bean.setRaPendentesComPrazoGrande((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoGrande((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoGrande((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoGrande((Integer)objSE[8]);
								bean.setFaturaRevisaoGrande((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaGrande((Integer)objSE[11]);
								bean.setRegRecebidosGrande((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoGrande((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoGrande((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoGrande((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoGrande((Integer)objSE[24]);
								bean.setAlteracaoCategoriaGrande((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialGrande((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialGrande((Integer)objSE[27]);
								bean.setFaturamentoSuspensoGrande((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setSuprExecutadasCorporativo((Integer)objSE[0]);
								bean.setReestabCorporativo((Integer)objSE[1]);
								bean.setClandCortadosCorporativo((Integer)objSE[2]);
								bean.setClandSuprimidosCorporativo((Integer)objSE[3]);
								bean.setAvisoCorteCorporativo((Integer)objSE[4]);
								bean.setRaPendentesComPrazoCorporativo((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoCorporativo((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoCorporativo((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoCorporativo((Integer)objSE[8]);
								bean.setFaturaRevisaoCorporativo((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaCorporativo((Integer)objSE[11]);
								bean.setRegRecebidosCorporativo((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoCorporativo((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoCorporativo((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoCorporativo((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoCorporativo((Integer)objSE[24]);
								bean.setAlteracaoCategoriaCorporativo((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialCorporativo((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialCorporativo((Integer)objSE[27]);
								bean.setFaturamentoSuspensoCorporativo((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setSuprExecutadasGrandeTelemedido((Integer)objSE[0]);
								bean.setReestabGrandeTelemedido((Integer)objSE[1]);
								bean.setClandCortadosGrandeTelemedido((Integer)objSE[2]);
								bean.setClandSuprimidosGrandeTelemedido((Integer)objSE[3]);
								bean.setAvisoCorteGrandeTelemedido((Integer)objSE[4]);
								bean.setRaPendentesComPrazoGrandeTelemedido((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoGrandeTelemedido((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoGrandeTelemedido((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoGrandeTelemedido((Integer)objSE[8]);
								bean.setFaturaRevisaoGrandeTelemedido((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaGrandeTelemedido((Integer)objSE[11]);
								bean.setRegRecebidosGrandeTelemedido((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido((Integer)objSE[24]);
								bean.setAlteracaoCategoriaGrandeTelemedido((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialGrandeTelemedido((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialGrandeTelemedido((Integer)objSE[27]);
								bean.setFaturamentoSuspensoGrandeTelemedido((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setSuprExecutadasCorpTelemedido((Integer)objSE[0]);
								bean.setReestabCorpTelemedido((Integer)objSE[1]);
								bean.setClandCortadosCorpTelemedido((Integer)objSE[2]);
								bean.setClandSuprimidosCorpTelemedido((Integer)objSE[3]);
								bean.setAvisoCorteCorpTelemedido((Integer)objSE[4]);
								bean.setRaPendentesComPrazoCorpTelemedido((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoCorpTelemedido((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoCorpTelemedido((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoCorpTelemedido((Integer)objSE[8]);
								bean.setFaturaRevisaoCorpTelemedido((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaCorpTelemedido((Integer)objSE[11]);
								bean.setRegRecebidosCorpTelemedido((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoCorpTelemedido((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoCorpTelemedido((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido((Integer)objSE[24]);
								bean.setAlteracaoCategoriaCorpTelemedido((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialCorpTelemedido((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialCorpTelemedido((Integer)objSE[27]);
								bean.setFaturamentoSuspensoCorpTelemedido((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setSuprExecutadasChafariz((Integer)objSE[0]);
								bean.setReestabChafariz((Integer)objSE[1]);
								bean.setClandCortadosChafariz((Integer)objSE[2]);
								bean.setClandSuprimidosChafariz((Integer)objSE[3]);
								bean.setAvisoCorteChafariz((Integer)objSE[4]);
								bean.setRaPendentesComPrazoChafariz((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoChafariz((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoChafariz((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoChafariz((Integer)objSE[8]);
								bean.setFaturaRevisaoChafariz((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaChafariz((Integer)objSE[11]);
								bean.setRegRecebidosChafariz((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoChafariz((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoChafariz((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoChafariz((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoChafariz((Integer)objSE[24]);
								bean.setAlteracaoCategoriaChafariz((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialChafariz((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialChafariz((Integer)objSE[27]);
								bean.setFaturamentoSuspensoChafariz((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setSuprExecutadasMicroTelemedido((Integer)objSE[0]);
								bean.setReestabMicroTelemedido((Integer)objSE[1]);
								bean.setClandCortadosMicroTelemedido((Integer)objSE[2]);
								bean.setClandSuprimidosMicroTelemedido((Integer)objSE[3]);
								bean.setAvisoCorteMicroTelemedido((Integer)objSE[4]);
								bean.setRaPendentesComPrazoMicroTelemedido((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoMicroTelemedido((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoMicroTelemedido((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoMicroTelemedido((Integer)objSE[8]);
								bean.setFaturaRevisaoMicroTelemedido((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaMicroTelemedido((Integer)objSE[11]);
								bean.setRegRecebidosMicroTelemedido((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoMicroTelemedido((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoMicroTelemedido((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido((Integer)objSE[24]);
								bean.setAlteracaoCategoriaMicroTelemedido((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialMicroTelemedido((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialMicroTelemedido((Integer)objSE[27]);
								bean.setFaturamentoSuspensoMicroTelemedido((Integer)objSE[28]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setSuprExecutadasCadastroProvisorio((Integer)objSE[0]);
								bean.setReestabCadastroProvisorio((Integer)objSE[1]);
								bean.setClandCortadosCadastroProvisorio((Integer)objSE[2]);
								bean.setClandSuprimidosCadastroProvisorio((Integer)objSE[3]);
								bean.setAvisoCorteCadastroProvisorio((Integer)objSE[4]);
								bean.setRaPendentesComPrazoCadastroProvisorio((Integer)objSE[5]);
								bean.setRaPendentesComForaPrazoCadastroProvisorio((Integer)objSE[6]);
								bean.setRaPendentesOpPrazoCadastroProvisorio((Integer)objSE[7]);
								bean.setRaPendentesOpForaPrazoCadastroProvisorio((Integer)objSE[8]);
								bean.setFaturaRevisaoCadastroProvisorio((Integer)objSE[9]);
								bean.setRegAnormalidadeInformadaCadastroProvisorio((Integer)objSE[11]);
								bean.setRegRecebidosCadastroProvisorio((Integer)objSE[12]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio((Integer)objSE[21]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio((Integer)objSE[22]);
								//Número de Imóveis
								bean.setAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio((Integer)objSE[23]);
								//Quantidade de Economias
								bean.setAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio((Integer)objSE[24]);
								bean.setAlteracaoCategoriaCadastroProvisorio((Integer)objSE[25]);
								bean.setInclusaoTarifaSocialCadastroProvisorio((Integer)objSE[26]);
								bean.setExclusaoTarifaSocialCadastroProvisorio((Integer)objSE[27]);
								bean.setFaturamentoSuspensoCadastroProvisorio((Integer)objSE[28]);
							break;
							
							
						}
						
						bean.setGerenciaRegionalID((String)objSE[13]);
						bean.setGerenciaRegional((String)objSE[14]);
						bean.setUnidadeNegocioID((String)objSE[15]);
						bean.setUnidadeNegocio((String)objSE[16]);
						bean.setLocalidadeID((String)objSE[17]);
						bean.setLocalidade((String)objSE[18]);				
						bean.setMunicipioID((String)objSE[19]);
						bean.setMunicipio((String)objSE[20]);
						
						beans2.add(bean);
						
					}
					
					
					//Resumo Faturamento
					Object[] objRF = null;
					if(colecaoDadosResumoFaturamento != null && colecaoDadosResumoFaturamento.size() > 0 && itResumoFaturamento.hasNext()){
						objRF = (Object[]) itResumoFaturamento.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setContasEmitidasNormal((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setContasEmitidasTarSocial((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.GRANDE_INT:
								bean.setContasEmitidasGrande((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setContasEmitidasCorporativo((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setContasEmitidasGrandeTelemedido((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setContasEmitidasCorpTelemedido((Integer)objRF[0]);	
							break;
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setContasEmitidasChafariz((Integer)objRF[0]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setContasEmitidasMicroTelemedido((Integer)objRF[0]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setContasEmitidasCadastroProvisorio((Integer)objRF[0]);
							break;
							
						}
						
						bean.setGerenciaRegionalID((String)objRF[1]);
						bean.setGerenciaRegional((String)objRF[2]);
						bean.setUnidadeNegocioID((String)objRF[3]);
						bean.setUnidadeNegocio((String)objRF[4]);
						bean.setLocalidadeID((String)objRF[5]);
						bean.setLocalidade((String)objRF[6]);				
						bean.setMunicipioID((String)objRF[7]);
						bean.setMunicipio((String)objRF[8]);
						
						beans2.add(bean);
					}
					
					//Desempenho micromedicao
					Object[] objDM = null;
					if(colecaoDadosDesempenhoMicromedicao != null && colecaoDadosDesempenhoMicromedicao.size() > 0 && itDesempenhoMicromedicao.hasNext()){
						objDM = (Object[]) itDesempenhoMicromedicao.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setLigAtivasHidrometroNormal((Integer)objDM[0]);
								bean.setLigAtivasNormal((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setLigAtivasHidrometroTarSocial((Integer)objDM[0]);
								bean.setLigAtivasTarSocial((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.GRANDE_INT:
								bean.setLigAtivasHidrometroGrande((Integer)objDM[0]);
								bean.setLigAtivasGrande((Integer)objDM[1]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setLigAtivasHidrometroCorporativo((Integer)objDM[0]);
								bean.setLigAtivasCorporativo((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setLigAtivasHidrometroGrandeTelemedido((Integer)objDM[0]);
								bean.setLigAtivasGrandeTelemedido((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setLigAtivasHidrometroCorpTelemedido((Integer)objDM[0]);
								bean.setLigAtivasCorpTelemedido((Integer)objDM[1]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setLigAtivasHidrometroChafariz((Integer)objDM[0]);
								bean.setLigAtivasChafariz((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setLigAtivasHidrometroMicroTelemedido((Integer)objDM[0]);
								bean.setLigAtivasMicroTelemedido((Integer)objDM[1]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setLigAtivasHidrometroCadastroProvisorio((Integer)objDM[0]);
								bean.setLigAtivasCadastroProvisorio((Integer)objDM[1]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objDM[2]);
						bean.setGerenciaRegional((String)objDM[3]);
						bean.setUnidadeNegocioID((String)objDM[4]);
						bean.setUnidadeNegocio((String)objDM[5]);
						bean.setLocalidadeID((String)objDM[6]);
						bean.setLocalidade((String)objDM[7]);				
						bean.setMunicipioID((String)objDM[8]);
						bean.setMunicipio((String)objDM[9]);
						
						beans2.add(bean);
					}
					
					
					//Leitura Anormalidade
					Object[] objLA = null;
					if(colecaoDadosLeituraAnormalidade != null && colecaoDadosLeituraAnormalidade.size() > 0 && itLeituraAnormalidade.hasNext()){
						objLA = (Object[]) itLeituraAnormalidade.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setLeitEfetuadasNormal((Integer)objLA[0]);
								bean.setLeitAnormalidadesNormal((Integer)objLA[1]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setLeitEfetuadasTarSocial((Integer)objLA[0]);
								bean.setLeitAnormalidadesTarSocial((Integer)objLA[1]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setLeitEfetuadasGrande((Integer)objLA[0]);
								bean.setLeitAnormalidadesGrande((Integer)objLA[1]);
							break;	
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setLeitEfetuadasCorporativo((Integer)objLA[0]);
								bean.setLeitAnormalidadesCorporativo((Integer)objLA[1]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setLeitEfetuadasGrandeTelemedido((Integer)objLA[0]);
								bean.setLeitAnormalidadesGrandeTelemedido((Integer)objLA[1]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setLeitEfetuadasCorpTelemedido((Integer)objLA[0]);
								bean.setLeitAnormalidadesCorpTelemedido((Integer)objLA[1]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setLeitEfetuadasChafariz((Integer)objLA[0]);
								bean.setLeitAnormalidadesChafariz((Integer)objLA[1]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setLeitEfetuadasMicroTelemedido((Integer)objLA[0]);
								bean.setLeitAnormalidadesMicroTelemedido((Integer)objLA[1]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setLeitEfetuadasCadastroProvisorio((Integer)objLA[0]);
								bean.setLeitAnormalidadesCadastroProvisorio((Integer)objLA[1]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objLA[2]);
						bean.setGerenciaRegional((String)objLA[3]);
						bean.setUnidadeNegocioID((String)objLA[4]);
						bean.setUnidadeNegocio((String)objLA[5]);
						bean.setLocalidadeID((String)objLA[6]);
						bean.setLocalidade((String)objLA[7]);				
						bean.setMunicipioID((String)objLA[8]);
						bean.setMunicipio((String)objLA[9]);
						
						beans2.add(bean);
					}
					
					
					//Consumo Água		
					Object[] objCA = null;
					if(colecaoDadosConsumoAgua != null && colecaoDadosConsumoAgua.size() > 0 && itConsumoAgua.hasNext()){
						objCA = (Object[]) itConsumoAgua.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setEconomiaAtivaNormal((Integer)objCA[0]);
								
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setEconomiaAtivaTarSocial((Integer)objCA[0]);
							
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setEconomiaAtivaGrande((Integer)objCA[0]);
								
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setEconomiaAtivaCorporativo((Integer)objCA[0]);
								
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setEconomiaAtivaGrandeTelemedido((Integer)objCA[0]);
							
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setEconomiaAtivaCorpTelemedido((Integer)objCA[0]);
								
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setEconomiaAtivaChafariz((Integer)objCA[0]);
								
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setEconomiaAtivaMicroTelemedido((Integer)objCA[0]);
								
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setEconomiaAtivaCadastroProvisorio((Integer)objCA[0]);
								
							break;
						}
						
						bean.setGerenciaRegionalID((String)objCA[1]);
						bean.setGerenciaRegional((String)objCA[2]);
						bean.setUnidadeNegocioID((String)objCA[3]);
						bean.setUnidadeNegocio((String)objCA[4]);
						bean.setLocalidadeID((String)objCA[5]);
						bean.setLocalidade((String)objCA[6]);				
						bean.setMunicipioID((String)objCA[7]);
						bean.setMunicipio((String)objCA[8]);
						
						beans2.add(bean);
					}
					
					
					//Consumo Água		
					Object[] objCA2 = null;
					if(colecaoDadosConsumoAgua2 != null && colecaoDadosConsumoAgua2.size() > 0 && itConsumoAgua2.hasNext()){
						objCA2 = (Object[]) itConsumoAgua2.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
							
								bean.setLigacoesAtivasNormal((Integer)objCA2[0]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								
								bean.setLigacoesAtivasTarSocial((Integer)objCA2[0]);
							break;
							case ImovelPerfil.GRANDE_INT:
								
								bean.setLigacoesAtivasGrande((Integer)objCA2[0]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								
								bean.setLigacoesAtivasCorporativo((Integer)objCA2[0]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								
								bean.setLigacoesAtivasGrandeTelemedido((Integer)objCA2[0]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
						
								bean.setLigacoesAtivasCorpTelemedido((Integer)objCA2[0]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								
								bean.setLigacoesAtivasChafariz((Integer)objCA2[0]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
							
								bean.setLigacoesAtivasMicroTelemedido((Integer)objCA2[0]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
		
								bean.setLigacoesAtivasCadastroProvisorio((Integer)objCA2[0]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objCA2[1]);
						bean.setGerenciaRegional((String)objCA2[2]);
						bean.setUnidadeNegocioID((String)objCA2[3]);
						bean.setUnidadeNegocio((String)objCA2[4]);
						bean.setLocalidadeID((String)objCA2[5]);
						bean.setLocalidade((String)objCA2[6]);				
						bean.setMunicipioID((String)objCA2[7]);
						bean.setMunicipio((String)objCA2[8]);
						
						beans2.add(bean);
					}
					
					//Ligacao Inativa
					Object[] objLI = null;
					if(colecaoDadosLigacaoInativa != null && colecaoDadosLigacaoInativa.size() > 0 && itLigacaoInativa.hasNext()){
						objLI = (Object[]) itLigacaoInativa.next();
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_PERFIL);
						
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setEconomiaInativaNormal((Integer)objLI[0]);
								bean.setLigacoesInativasNormal((Integer)objLI[1]);
							break;
							
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setEconomiaInativaTarSocial((Integer)objLI[0]);
								bean.setLigacoesInativasTarSocial((Integer)objLI[1]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setEconomiaInativaGrande((Integer)objLI[0]);
								bean.setLigacoesInativasGrande((Integer)objLI[1]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setEconomiaInativaCorporativo((Integer)objLI[0]);
								bean.setLigacoesInativasCorporativo((Integer)objLI[1]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setEconomiaInativaGrandeTelemedido((Integer)objLI[0]);
								bean.setLigacoesInativasGrandeTelemedido((Integer)objLI[1]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setEconomiaInativaCorpTelemedido((Integer)objLI[0]);
								bean.setLigacoesInativasCorpTelemedido((Integer)objLI[1]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setEconomiaInativaChafariz((Integer)objLI[0]);
								bean.setLigacoesInativasChafariz((Integer)objLI[1]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setEconomiaInativaMicroTelemedido((Integer)objLI[0]);
								bean.setLigacoesInativasMicroTelemedido((Integer)objLI[1]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setEconomiaInativaCadastroProvisorio((Integer)objLI[0]);
								bean.setLigacoesInativasCadastroProvisorio((Integer)objLI[1]);
							break;
						}
						
						bean.setGerenciaRegionalID((String)objLI[2]);
						bean.setGerenciaRegional((String)objLI[3]);
						bean.setUnidadeNegocioID((String)objLI[4]);
						bean.setUnidadeNegocio((String)objLI[5]);
						bean.setLocalidadeID((String)objLI[6]);
						bean.setLocalidade((String)objLI[7]);				
						bean.setMunicipioID((String)objLI[8]);
						bean.setMunicipio((String)objLI[9]);
						
						beans2.add(bean);
					}
		
					//Faturas em Revisão
					Object[] objFR1 = null;
					if(colecaoFaturasRevisao1 != null && colecaoFaturasRevisao1.size() > 0 && itFaturasRevisao1.hasNext()){
						
						objFR1 = (Object[]) itFaturasRevisao1.next();
						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setFaturaRevisaoReclamacaoConsumoNormal((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoNormal((Integer)objFR1[9]);
							break;
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setFaturaRevisaoReclamacaoConsumoTarSocial((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial((Integer)objFR1[9]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setFaturaRevisaoReclamacaoConsumoGrande((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoGrande((Integer)objFR1[9]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setFaturaRevisaoReclamacaoConsumoCorporativo((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo((Integer)objFR1[9]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setFaturaRevisaoReclamacaoConsumoGrandeTelemedido((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido((Integer)objFR1[9]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setFaturaRevisaoReclamacaoConsumoCorpTelemedido((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido((Integer)objFR1[9]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setFaturaRevisaoReclamacaoConsumoChafariz((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoChafariz((Integer)objFR1[9]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setFaturaRevisaoReclamacaoConsumoMicroTelemedido((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido((Integer)objFR1[9]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setFaturaRevisaoReclamacaoConsumoCadastroProvisorio((Integer)objFR1[0]);
								bean.setQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio((Integer)objFR1[9]);
							break;
							
						}
						
						bean.setGerenciaRegionalID((String)objFR1[1]);
						bean.setGerenciaRegional((String)objFR1[2]);
						bean.setUnidadeNegocioID((String)objFR1[3]);
						bean.setUnidadeNegocio((String)objFR1[4]);
						bean.setLocalidadeID((String)objFR1[5]);
						bean.setLocalidade((String)objFR1[6]);				
						bean.setMunicipioID((String)objFR1[7]);
						bean.setMunicipio((String)objFR1[8]);
						
						beans2.add(bean);
						
					}	
					
					
					//Faturas em Revisão
					Object[] objFR2 = null;

					if(colecaoFaturasRevisao2 != null && colecaoFaturasRevisao2.size() > 0 && itFaturasRevisao2.hasNext()){
						
						objFR2 = (Object[]) itFaturasRevisao2.next();

						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setFaturaRevisaoRecorrenciaNormal((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaNormal((Integer)objFR2[9]);
							break;
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setFaturaRevisaoRecorrenciaTarSocial((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaTarSocial((Integer)objFR2[9]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setFaturaRevisaoRecorrenciaGrande((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaGrande((Integer)objFR2[9]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setFaturaRevisaoRecorrenciaCorporativo((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaCorporativo((Integer)objFR2[9]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setFaturaRevisaoRecorrenciaGrandeTelemedido((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido((Integer)objFR2[9]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setFaturaRevisaoRecorrenciaCorpTelemedido((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido((Integer)objFR2[9]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setFaturaRevisaoRecorrenciaChafariz((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaChafariz((Integer)objFR2[9]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setFaturaRevisaoRecorrenciaMicroTelemedido((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido((Integer)objFR2[9]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setFaturaRevisaoRecorrenciaCadastroProvisorio((Integer)objFR2[0]);
								bean.setQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio((Integer)objFR2[9]);
							break;
							
						}
						
						bean.setGerenciaRegionalID((String)objFR2[1]);
						bean.setGerenciaRegional((String)objFR2[2]);
						bean.setUnidadeNegocioID((String)objFR2[3]);
						bean.setUnidadeNegocio((String)objFR2[4]);
						bean.setLocalidadeID((String)objFR2[5]);
						bean.setLocalidade((String)objFR2[6]);				
						bean.setMunicipioID((String)objFR2[7]);
						bean.setMunicipio((String)objFR2[8]);
						
						beans2.add(bean);
						
					}
					
					
					//Faturas em Revisão
					Object[] objFR3 = null;
					if(colecaoFaturasRevisao3 != null && colecaoFaturasRevisao3.size() > 0 && itFaturasRevisao3.hasNext()){
						
						objFR3 = (Object[]) itFaturasRevisao3.next();
						
						bean = new RelatorioDadosKitCASServicoBean();
						bean.setGrupoDados(GRUPO_CATEGORIA);
						
						
						switch(tipo){
							case ImovelPerfil.NORMAL_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoNormal((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoNormal((Integer)objFR3[9]);
							break;
							case ImovelPerfil.TARIFA_SOCIAL_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoTarSocial((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial((Integer)objFR3[9]);
							break;
							case ImovelPerfil.GRANDE_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoGrande((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoGrande((Integer)objFR3[9]);
							break;
							case ImovelPerfil.CORPORATIVO_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoCorporativo((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo((Integer)objFR3[9]);
							break;
							
							case ImovelPerfil.GRANDE_TELEMEDIDO_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoGrandeTelemedido((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido((Integer)objFR3[9]);
							break;
							
							case ImovelPerfil.CORPORATIVO_TELEMED_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoCorpTelemedido((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido((Integer)objFR3[9]);
							break;	
							
							case ImovelPerfil.CHAFARIZ_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoChafariz((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz((Integer)objFR3[9]);
							break;
							
							case ImovelPerfil.MICRO_TELEMEDIDO_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoMicroTelemedido((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido((Integer)objFR3[9]);
							break;
							
							case ImovelPerfil.CADASTRO_PROVISORIO_INT:
								bean.setFaturaRevisaoFaturamentoIndevidoCadastroProvisorio((Integer)objFR3[0]);
								bean.setQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio((Integer)objFR3[9]);
							break;
							
						}
						
						bean.setGerenciaRegionalID((String)objFR3[1]);
						bean.setGerenciaRegional((String)objFR3[2]);
						bean.setUnidadeNegocioID((String)objFR3[3]);
						bean.setUnidadeNegocio((String)objFR3[4]);
						bean.setLocalidadeID((String)objFR3[5]);
						bean.setLocalidade((String)objFR3[6]);				
						bean.setMunicipioID((String)objFR3[7]);
						bean.setMunicipio((String)objFR3[8]);
						
						beans2.add(bean);
						
					}
					
				}	
		}	
		
		//Lista de retorno do relatório
		List<RelatorioDadosKitCASServicoBean> listaFinalBeans = new ArrayList<RelatorioDadosKitCASServicoBean>();
		
		
		//Ordenando os Beans ....
		Collections.sort(beans);
		Collections.sort(beans2);
		
		
		// ...Depois agrupando ....
		beans = agruparBeans(beans,helper.getOpcaoTotalizacao(),GRUPO_CATEGORIA);
		beans2 = agruparBeans(beans2,helper.getOpcaoTotalizacao(),GRUPO_PERFIL);
		
		//...E alternando as ocorrências.
		for(int i = 0; i < beans.size(); i++){
			listaFinalBeans.add(beans.get(i));
			listaFinalBeans.add(beans2.get(i));
		}
		
		
		//caso a opcao de totalizacao for estado por Gerencia Regional/Unidade de Negocio, adiciona o total do estado,
		//soma os valores
		if (helper.getOpcaoTotalizacao().compareTo(TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL) == 0 
				|| helper.getOpcaoTotalizacao().compareTo(TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO) == 0){
			
			
			Iterator it = beans.iterator();
			while(it.hasNext()){
				RelatorioDadosKitCASServicoBean bean3 = (RelatorioDadosKitCASServicoBean)it.next();
				beanTotal = somarBeans(beanTotal,bean3);
			}
			
			Iterator it2 = beans2.iterator();
			while(it2.hasNext()){
				RelatorioDadosKitCASServicoBean bean3 = (RelatorioDadosKitCASServicoBean)it2.next();
				beanTotal2 = somarBeans(beanTotal2,bean3);
			}
			
			
			beanTotal.setGerenciaRegionalID("0");	
			beanTotal.setUnidadeNegocioID("0");	
			beanTotal.setLocalidadeID("0");
			beanTotal.setMunicipioID("0");
			beanTotal.setGrupoDados(GRUPO_CATEGORIA);
			beanTotal2.setGerenciaRegionalID("0");	
			beanTotal2.setUnidadeNegocioID("0");	
			beanTotal2.setLocalidadeID("0");
			beanTotal2.setMunicipioID("0");
			beanTotal2.setGrupoDados(GRUPO_PERFIL);
			
			listaFinalBeans.add(beanTotal); 
			listaFinalBeans.add(beanTotal2);
			
		}
		
		
			
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(helper.getAnoMesReferencia()));
		parametros.put("codOpcaoTotalizacao", helper.getOpcaoTotalizacao());
		parametros.put("dataInicialApuracao", Util.formatarData(Util.gerarDataInicialApartirAnoMesRefencia(helper.getAnoMesReferencia())));
		parametros.put("dataFinalApuracao", Util.formatarData(Util.gerarDataApartirAnoMesRefencia(helper.getAnoMesReferencia())));
		parametros.put("tipoFormatoRelatorio", "R1307");
		
		RelatorioDataSource ds = new RelatorioDataSource(listaFinalBeans);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DADOS_KIT_CAS_SERVICOS, parametros, ds, tipoRelatorio);
		
		return retorno;
	}

	
	
	
	private List<RelatorioDadosKitCASServicoBean> agruparBeans(List<RelatorioDadosKitCASServicoBean> beans, Integer opcaoTotalizacao, String grupo){
		List<RelatorioDadosKitCASServicoBean> retorno = new ArrayList<RelatorioDadosKitCASServicoBean>();
		Iterator iteratorBean = beans.iterator();
		RelatorioDadosKitCASServicoBean beanSoma = new RelatorioDadosKitCASServicoBean();
		RelatorioDadosKitCASServicoBean beanAnterior = new RelatorioDadosKitCASServicoBean();
		
		try {

			if(iteratorBean.hasNext()){
				beanAnterior = (RelatorioDadosKitCASServicoBean)iteratorBean.next();
				beanSoma = somarBeans(beanAnterior,beanSoma);
				
				while(iteratorBean.hasNext()){
					RelatorioDadosKitCASServicoBean bean = (RelatorioDadosKitCASServicoBean)iteratorBean.next();
					
					if(opcaoTotalizacao.compareTo(TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL) == 0){
						if(bean.getGerenciaRegionalID().compareTo(beanAnterior.getGerenciaRegionalID()) == 0){
							beanSoma = somarBeans(bean,beanSoma);
						}
						else{
							beanSoma.setGerenciaRegional(beanAnterior.getGerenciaRegional());
							beanSoma.setGerenciaRegionalID(beanAnterior.getGerenciaRegionalID());
							beanSoma.setGrupoDados(grupo);
							retorno.add(beanSoma);
							beanAnterior = (RelatorioDadosKitCASServicoBean) bean.clone();
							beanSoma = new RelatorioDadosKitCASServicoBean();
							beanSoma = somarBeans(beanSoma,bean);
						}
					}
					else if(opcaoTotalizacao.compareTo(TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO) == 0){
						if(bean.getUnidadeNegocioID().compareTo(beanAnterior.getUnidadeNegocioID()) == 0){
							beanSoma = somarBeans(bean,beanSoma);				
						}
						else{
							beanSoma.setUnidadeNegocio(beanAnterior.getUnidadeNegocio());
							beanSoma.setUnidadeNegocioID(beanAnterior.getUnidadeNegocioID());
							beanSoma.setGrupoDados(grupo);
							retorno.add(beanSoma);
							beanAnterior = (RelatorioDadosKitCASServicoBean) bean.clone();
							beanSoma = new RelatorioDadosKitCASServicoBean();
							beanSoma = somarBeans(beanSoma,bean);
						}
					}
					else{
						beanSoma = somarBeans(bean,beanSoma);
					}
				}
				
				beanSoma.setGrupoDados(grupo);
				beanSoma.setGerenciaRegional(beanAnterior.getGerenciaRegional());
				beanSoma.setGerenciaRegionalID(beanAnterior.getGerenciaRegionalID());
				beanSoma.setUnidadeNegocio(beanAnterior.getUnidadeNegocio());
				beanSoma.setUnidadeNegocioID(beanAnterior.getUnidadeNegocioID());
				beanSoma.setMunicipio(beanAnterior.getMunicipio());
				beanSoma.setMunicipioID(beanAnterior.getMunicipioID());
				beanSoma.setLocalidade(beanAnterior.getLocalidade());
				beanSoma.setLocalidadeID(beanAnterior.getLocalidadeID());
				retorno.add(beanSoma);
			}
		} 
		
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	private RelatorioDadosKitCASServicoBean somarBeans(RelatorioDadosKitCASServicoBean bean, RelatorioDadosKitCASServicoBean bean2){
		
		RelatorioDadosKitCASServicoBean retorno = new RelatorioDadosKitCASServicoBean();
	
		retorno.setHidSubstituidosRamalResidencial(
				 Util.somaInteiros(bean2.getHidSubstituidosRamalResidencial(),bean.getHidSubstituidosRamalResidencial()));
		retorno.setHidSubstituidosPocoResidencial(
				Util.somaInteiros(bean2.getHidSubstituidosPocoResidencial(),bean.getHidSubstituidosPocoResidencial()));
		retorno.setHidInstaladosRamalResidencial(Util.somaInteiros(bean.getHidInstaladosRamalResidencial(),bean2.getHidInstaladosRamalResidencial()));
		retorno.setHidInstaladosPocoResidencial(Util.somaInteiros(bean.getHidInstaladosPocoResidencial(),bean2.getHidInstaladosPocoResidencial()));
		retorno.setCortesExecutadosResidencial(Util.somaInteiros(bean.getCortesExecutadosResidencial(),bean2.getCortesExecutadosResidencial()));
		retorno.setSuprExecutadasResidencial(Util.somaInteiros(bean.getSuprExecutadasResidencial(),bean2.getSuprExecutadasResidencial()));
		retorno.setReligacoesResidencial(Util.somaInteiros(bean.getReligacoesResidencial(),bean2.getReligacoesResidencial()));
		retorno.setReestabResidencial(Util.somaInteiros(bean.getReestabResidencial(),bean2.getReestabResidencial()));
		retorno.setClandCortadosResidencial(Util.somaInteiros(bean.getClandCortadosResidencial(),bean2.getClandCortadosResidencial()));
		retorno.setClandSuprimidosResidencial(Util.somaInteiros(bean.getClandSuprimidosResidencial(),bean2.getClandSuprimidosResidencial()));
		retorno.setContasEmitidasResidencial(Util.somaInteiros(bean.getContasEmitidasResidencial(),bean2.getContasEmitidasResidencial()));
		retorno.setLeitEfetuadasResidencial(Util.somaInteiros(bean.getLeitEfetuadasResidencial(),bean2.getLeitEfetuadasResidencial()));
		retorno.setLeitAnormalidadesResidencial(Util.somaInteiros(bean.getLeitAnormalidadesResidencial(),bean2.getLeitAnormalidadesResidencial()));
		retorno.setAvisoCorteResidencial(Util.somaInteiros(bean.getAvisoCorteResidencial(),bean2.getAvisoCorteResidencial()));
		retorno.setPercAnormalidadeResidencial(Util.somaBigDecimal(bean.getPercAnormalidadeResidencial(),  bean2.getPercAnormalidadeResidencial()));
		retorno.setPercHidrometracaoResidencial(Util.somaBigDecimal(bean.getPercHidrometracaoResidencial(),  bean2.getPercHidrometracaoResidencial()));
		retorno.setLigImplantadasAguaResidencial(Util.somaInteiros(bean.getLigImplantadasAguaResidencial(),bean2.getLigImplantadasAguaResidencial()));
		retorno.setLigImplantadasEsgotoResidencial(Util.somaInteiros(bean.getLigImplantadasEsgotoResidencial(),bean2.getLigImplantadasEsgotoResidencial()));
		retorno.setRaPendentesComPrazoResidencial(Util.somaInteiros(bean.getRaPendentesComPrazoResidencial(),bean2.getRaPendentesComPrazoResidencial()));
		retorno.setRaPendentesComForaPrazoResidencial(Util.somaInteiros(bean.getRaPendentesComForaPrazoResidencial(),bean2.getRaPendentesComForaPrazoResidencial()));
		retorno.setRaPendentesOpPrazoResidencial(Util.somaInteiros(bean.getRaPendentesOpPrazoResidencial(),bean2.getRaPendentesOpPrazoResidencial()));
		retorno.setRaPendentesOpForaPrazoResidencial(Util.somaInteiros(bean.getRaPendentesOpForaPrazoResidencial(),bean2.getRaPendentesOpForaPrazoResidencial()));
		retorno.setFaturaRevisaoResidencial(Util.somaInteiros(bean.getFaturaRevisaoResidencial(),bean2.getFaturaRevisaoResidencial()));
		 retorno.setCartasNegativacaoResidencial(Util.somaInteiros(bean.getCartasNegativacaoResidencial(),bean2.getCartasNegativacaoResidencial()));
		 retorno.setEconomiaAtivaResidencial(Util.somaInteiros(bean.getEconomiaAtivaResidencial(),bean2.getEconomiaAtivaResidencial()));
		 retorno.setEconomiaInativaResidencial(Util.somaInteiros(bean.getEconomiaInativaResidencial(),bean2.getEconomiaInativaResidencial()));
		 retorno.setLigacoesAtivasResidencial(Util.somaInteiros(bean.getLigacoesAtivasResidencial(),bean2.getLigacoesAtivasResidencial()));
		 retorno.setLigacoesInativasResidencial(Util.somaInteiros(bean.getLigacoesInativasResidencial(),bean2.getLigacoesInativasResidencial()));
		 retorno.setRegAnormalidadeInformadaResidencial(Util.somaInteiros(bean.getRegAnormalidadeInformadaResidencial(),bean2.getRegAnormalidadeInformadaResidencial()));
		 retorno.setRegRecebidosResidencial(Util.somaInteiros(bean.getRegRecebidosResidencial(),bean2.getRegRecebidosResidencial()));
		 retorno.setLigAtivasHidrometroResidencial(Util.somaInteiros(bean.getLigAtivasHidrometroResidencial(),bean2.getLigAtivasHidrometroResidencial()));
		 retorno.setLigAtivasResidencial(Util.somaInteiros(bean.getLigAtivasResidencial(),bean2.getLigAtivasResidencial()));
		
		 retorno.setFaturaRevisaoReclamacaoConsumoResidencial(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoResidencial(),bean2.getFaturaRevisaoReclamacaoConsumoResidencial()));
		 retorno.setFaturaRevisaoRecorrenciaResidencial(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaResidencial(),bean2.getFaturaRevisaoRecorrenciaResidencial()));
		 retorno.setFaturaRevisaoFaturamentoIndevidoResidencial(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoResidencial(),bean2.getFaturaRevisaoFaturamentoIndevidoResidencial()));
		 
		 retorno.setQtImoveisFaturaRevisaoRecorrenciaResidencial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaResidencial(),bean2.getQtImoveisFaturaRevisaoRecorrenciaResidencial()));
		 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoResidencial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoResidencial(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoResidencial()));
		 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial()));
		 
		 retorno.setAlteracaoNumeroEconomiasAcrescidoResidencial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoResidencial(),bean2.getAlteracaoNumeroEconomiasAcrescidoResidencial()));
		 retorno.setAlteracaoQuantidadeEconomiasAcrescidoResidencial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoResidencial(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoResidencial()));
		 retorno.setAlteracaoNumeroEconomiasDecrescidoResidencial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoResidencial(),bean2.getAlteracaoNumeroEconomiasDecrescidoResidencial()));
		 retorno.setAlteracaoQuantidadeEconomiasDecrescidoResidencial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoResidencial(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoResidencial()));
		 retorno.setAlteracaoCategoriaResidencial(Util.somaInteiros(bean.getAlteracaoCategoriaResidencial(),bean2.getAlteracaoCategoriaResidencial()));
		 retorno.setInclusaoTarifaSocialResidencial(Util.somaInteiros(bean.getInclusaoTarifaSocialResidencial(),bean2.getInclusaoTarifaSocialResidencial()));
		 retorno.setExclusaoTarifaSocialResidencial(Util.somaInteiros(bean.getExclusaoTarifaSocialResidencial(),bean2.getExclusaoTarifaSocialResidencial()));
		 retorno.setFaturamentoSuspensoResidencial(Util.somaInteiros(bean.getFaturamentoSuspensoResidencial(),bean2.getFaturamentoSuspensoResidencial()));
		 
		
		 
	
		 
		 retorno.setHidSubstituidosRamalComercial(
				 Util.somaInteiros(bean2.getHidSubstituidosRamalComercial(),bean.getHidSubstituidosRamalComercial()));
		retorno.setHidSubstituidosPocoComercial(
				Util.somaInteiros(bean2.getHidSubstituidosPocoComercial(),bean.getHidSubstituidosPocoComercial()));
		retorno.setHidInstaladosRamalComercial(Util.somaInteiros(bean.getHidInstaladosRamalComercial(),bean2.getHidInstaladosRamalComercial()));
		retorno.setHidInstaladosPocoComercial(Util.somaInteiros(bean.getHidInstaladosPocoComercial(),bean2.getHidInstaladosPocoComercial()));
		 retorno.setCortesExecutadosComercial(Util.somaInteiros(bean.getCortesExecutadosComercial(),bean2.getCortesExecutadosComercial()));
		 retorno.setSuprExecutadasComercial(Util.somaInteiros(bean.getSuprExecutadasComercial(),bean2.getSuprExecutadasComercial()));
		 retorno.setReligacoesComercial(Util.somaInteiros(bean.getReligacoesComercial(),bean2.getReligacoesComercial()));
		 retorno.setReestabComercial(Util.somaInteiros(bean.getReestabComercial(),bean2.getReestabComercial()));
		 retorno.setClandCortadosComercial(Util.somaInteiros(bean.getClandCortadosComercial(),bean2.getClandCortadosComercial()));
		 retorno.setClandSuprimidosComercial(Util.somaInteiros(bean.getClandSuprimidosComercial(),bean2.getClandSuprimidosComercial()));
		 retorno.setContasEmitidasComercial(Util.somaInteiros(bean.getContasEmitidasComercial(),bean2.getContasEmitidasComercial()));
		 retorno.setLeitEfetuadasComercial(Util.somaInteiros(bean.getLeitEfetuadasComercial(),bean2.getLeitEfetuadasComercial()));
		 retorno.setLeitAnormalidadesComercial(Util.somaInteiros(bean.getLeitAnormalidadesComercial(),bean2.getLeitAnormalidadesComercial()));
		 retorno.setAvisoCorteComercial(Util.somaInteiros(bean.getAvisoCorteComercial(),bean2.getAvisoCorteComercial()));
		 retorno.setPercAnormalidadeComercial(Util.somaBigDecimal(bean.getPercAnormalidadeComercial(),  bean2.getPercAnormalidadeComercial()));
		 retorno.setPercHidrometracaoComercial(Util.somaBigDecimal(bean.getPercHidrometracaoComercial(),  bean2.getPercHidrometracaoComercial()));
		 retorno.setLigImplantadasAguaComercial(Util.somaInteiros(bean.getLigImplantadasAguaComercial(),bean2.getLigImplantadasAguaComercial()));
		 retorno.setLigImplantadasEsgotoComercial(Util.somaInteiros(bean.getLigImplantadasEsgotoComercial(),bean2.getLigImplantadasEsgotoComercial()));
		 retorno.setRaPendentesComPrazoComercial(Util.somaInteiros(bean.getRaPendentesComPrazoComercial(),bean2.getRaPendentesComPrazoComercial()));
		 retorno.setRaPendentesComForaPrazoComercial(Util.somaInteiros(bean.getRaPendentesComForaPrazoComercial(),bean2.getRaPendentesComForaPrazoComercial()));
		 retorno.setRaPendentesOpPrazoComercial(Util.somaInteiros(bean.getRaPendentesOpPrazoComercial(),bean2.getRaPendentesOpPrazoComercial()));
		 retorno.setRaPendentesOpForaPrazoComercial(Util.somaInteiros(bean.getRaPendentesOpForaPrazoComercial(),bean2.getRaPendentesOpForaPrazoComercial()));
		 retorno.setFaturaRevisaoComercial(Util.somaInteiros(bean.getFaturaRevisaoComercial(),bean2.getFaturaRevisaoComercial()));
		 retorno.setCartasNegativacaoComercial(Util.somaInteiros(bean.getCartasNegativacaoComercial(),bean2.getCartasNegativacaoComercial()));
		 retorno.setEconomiaAtivaComercial(Util.somaInteiros(bean.getEconomiaAtivaComercial(),bean2.getEconomiaAtivaComercial()));
		 retorno.setEconomiaInativaComercial(Util.somaInteiros(bean.getEconomiaInativaComercial(),bean2.getEconomiaInativaComercial()));
		 retorno.setLigacoesAtivasComercial(Util.somaInteiros(bean.getLigacoesAtivasComercial(),bean2.getLigacoesAtivasComercial()));
		 retorno.setLigacoesInativasComercial(Util.somaInteiros(bean.getLigacoesInativasComercial(),bean2.getLigacoesInativasComercial()));
		 retorno.setRegAnormalidadeInformadaComercial(Util.somaInteiros(bean.getRegAnormalidadeInformadaComercial(),bean2.getRegAnormalidadeInformadaComercial()));
		 retorno.setRegRecebidosComercial(Util.somaInteiros(bean.getRegRecebidosComercial(),bean2.getRegRecebidosComercial()));
		 retorno.setLigAtivasHidrometroComercial(Util.somaInteiros(bean.getLigAtivasHidrometroComercial(),bean2.getLigAtivasHidrometroComercial()));
		 retorno.setLigAtivasComercial(Util.somaInteiros(bean.getLigAtivasComercial(),bean2.getLigAtivasComercial()));
		 
		 retorno.setFaturaRevisaoReclamacaoConsumoComercial(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoComercial(),bean2.getFaturaRevisaoReclamacaoConsumoComercial()));
		 retorno.setFaturaRevisaoRecorrenciaComercial(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaComercial(),bean2.getFaturaRevisaoRecorrenciaComercial()));
		 retorno.setFaturaRevisaoFaturamentoIndevidoComercial(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoComercial(),bean2.getFaturaRevisaoFaturamentoIndevidoComercial()));
		 
		 retorno.setQtImoveisFaturaRevisaoRecorrenciaComercial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaComercial(),bean2.getQtImoveisFaturaRevisaoRecorrenciaComercial()));
		 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoComercial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoComercial(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoComercial()));
		 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoComercial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoComercial(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoComercial()));	 
		 
		 retorno.setAlteracaoNumeroEconomiasAcrescidoComercial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoComercial(),bean2.getAlteracaoNumeroEconomiasAcrescidoComercial()));
		 retorno.setAlteracaoQuantidadeEconomiasAcrescidoComercial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoComercial(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoComercial()));
		 retorno.setAlteracaoNumeroEconomiasDecrescidoComercial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoComercial(),bean2.getAlteracaoNumeroEconomiasDecrescidoComercial()));
		 retorno.setAlteracaoQuantidadeEconomiasDecrescidoComercial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoComercial(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoComercial()));
		 retorno.setAlteracaoCategoriaComercial(Util.somaInteiros(bean.getAlteracaoCategoriaComercial(),bean2.getAlteracaoCategoriaComercial()));
		 retorno.setInclusaoTarifaSocialComercial(Util.somaInteiros(bean.getInclusaoTarifaSocialComercial(),bean2.getInclusaoTarifaSocialComercial()));
		 retorno.setExclusaoTarifaSocialComercial(Util.somaInteiros(bean.getExclusaoTarifaSocialComercial(),bean2.getExclusaoTarifaSocialComercial()));
		 retorno.setFaturamentoSuspensoComercial(Util.somaInteiros(bean.getFaturamentoSuspensoComercial(),bean2.getFaturamentoSuspensoComercial()));
	
		 
		 retorno.setHidSubstituidosRamalIndustrial(
				 Util.somaInteiros(bean2.getHidSubstituidosRamalIndustrial(),bean.getHidSubstituidosRamalIndustrial()));
		retorno.setHidSubstituidosPocoIndustrial(
				Util.somaInteiros(bean2.getHidSubstituidosPocoIndustrial(),bean.getHidSubstituidosPocoIndustrial()));
		retorno.setHidInstaladosRamalIndustrial(Util.somaInteiros(bean.getHidInstaladosRamalIndustrial(),bean2.getHidInstaladosRamalIndustrial()));
		retorno.setHidInstaladosPocoIndustrial(Util.somaInteiros(bean.getHidInstaladosPocoIndustrial(),bean2.getHidInstaladosPocoIndustrial()));
		 retorno.setCortesExecutadosIndustrial(Util.somaInteiros(bean.getCortesExecutadosIndustrial(),bean2.getCortesExecutadosIndustrial()));
		 retorno.setSuprExecutadasIndustrial(Util.somaInteiros(bean.getSuprExecutadasIndustrial(),bean2.getSuprExecutadasIndustrial()));
		 retorno.setReligacoesIndustrial(Util.somaInteiros(bean.getReligacoesIndustrial(),bean2.getReligacoesIndustrial()));
		 retorno.setReestabIndustrial(Util.somaInteiros(bean.getReestabIndustrial(),bean2.getReestabIndustrial()));
		 retorno.setClandCortadosIndustrial(Util.somaInteiros(bean.getClandCortadosIndustrial(),bean2.getClandCortadosIndustrial()));
		 retorno.setClandSuprimidosIndustrial(Util.somaInteiros(bean.getClandSuprimidosIndustrial(),bean2.getClandSuprimidosIndustrial()));
		 retorno.setContasEmitidasIndustrial(Util.somaInteiros(bean.getContasEmitidasIndustrial(),bean2.getContasEmitidasIndustrial()));
		 retorno.setLeitEfetuadasIndustrial(Util.somaInteiros(bean.getLeitEfetuadasIndustrial(),bean2.getLeitEfetuadasIndustrial()));
		 retorno.setLeitAnormalidadesIndustrial(Util.somaInteiros(bean.getLeitAnormalidadesIndustrial(),bean2.getLeitAnormalidadesIndustrial()));
		 retorno.setAvisoCorteIndustrial(Util.somaInteiros(bean.getAvisoCorteIndustrial(),bean2.getAvisoCorteIndustrial()));
		 retorno.setPercAnormalidadeIndustrial(Util.somaBigDecimal(bean.getPercAnormalidadeIndustrial(),  bean2.getPercAnormalidadeIndustrial()));
		 retorno.setPercHidrometracaoIndustrial(Util.somaBigDecimal(bean.getPercHidrometracaoIndustrial(),  bean2.getPercHidrometracaoIndustrial()));
		 retorno.setLigImplantadasAguaIndustrial(Util.somaInteiros(bean.getLigImplantadasAguaIndustrial(),bean2.getLigImplantadasAguaIndustrial()));
		 retorno.setLigImplantadasEsgotoIndustrial(Util.somaInteiros(bean.getLigImplantadasEsgotoIndustrial(),bean2.getLigImplantadasEsgotoIndustrial()));
		 retorno.setRaPendentesComPrazoIndustrial(Util.somaInteiros(bean.getRaPendentesComPrazoIndustrial(),bean2.getRaPendentesComPrazoIndustrial()));
		 retorno.setRaPendentesComForaPrazoIndustrial(Util.somaInteiros(bean.getRaPendentesComForaPrazoIndustrial(),bean2.getRaPendentesComForaPrazoIndustrial()));
		 retorno.setRaPendentesOpPrazoIndustrial(Util.somaInteiros(bean.getRaPendentesOpPrazoIndustrial(),bean2.getRaPendentesOpPrazoIndustrial()));
		 retorno.setRaPendentesOpForaPrazoIndustrial(Util.somaInteiros(bean.getRaPendentesOpForaPrazoIndustrial(),bean2.getRaPendentesOpForaPrazoIndustrial()));
		 retorno.setFaturaRevisaoIndustrial(Util.somaInteiros(bean.getFaturaRevisaoIndustrial(),bean2.getFaturaRevisaoIndustrial()));
		 retorno.setCartasNegativacaoIndustrial(Util.somaInteiros(bean.getCartasNegativacaoIndustrial(),bean2.getCartasNegativacaoIndustrial()));
		 retorno.setEconomiaAtivaIndustrial(Util.somaInteiros(bean.getEconomiaAtivaIndustrial(),bean2.getEconomiaAtivaIndustrial()));
		 retorno.setEconomiaInativaIndustrial(Util.somaInteiros(bean.getEconomiaInativaIndustrial(),bean2.getEconomiaInativaIndustrial()));
		 retorno.setLigacoesAtivasIndustrial(Util.somaInteiros(bean.getLigacoesAtivasIndustrial(),bean2.getLigacoesAtivasIndustrial()));
		 retorno.setLigacoesInativasIndustrial(Util.somaInteiros(bean.getLigacoesInativasIndustrial(),bean2.getLigacoesInativasIndustrial()));
		 retorno.setRegAnormalidadeInformadaIndustrial(Util.somaInteiros(bean.getRegAnormalidadeInformadaIndustrial(),bean2.getRegAnormalidadeInformadaIndustrial()));
		 retorno.setRegRecebidosIndustrial(Util.somaInteiros(bean.getRegRecebidosIndustrial(),bean2.getRegRecebidosIndustrial()));
		 retorno.setLigAtivasHidrometroIndustrial(Util.somaInteiros(bean.getLigAtivasHidrometroIndustrial(),bean2.getLigAtivasHidrometroIndustrial()));
		 retorno.setLigAtivasIndustrial(Util.somaInteiros(bean.getLigAtivasIndustrial(),bean2.getLigAtivasIndustrial()));
		 
		 retorno.setFaturaRevisaoReclamacaoConsumoIndustrial(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoIndustrial(),bean2.getFaturaRevisaoReclamacaoConsumoIndustrial()));
		 retorno.setFaturaRevisaoRecorrenciaIndustrial(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaIndustrial(),bean2.getFaturaRevisaoRecorrenciaIndustrial()));
		 retorno.setFaturaRevisaoFaturamentoIndevidoIndustrial(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoIndustrial(),bean2.getFaturaRevisaoFaturamentoIndevidoIndustrial()));
		 
		 retorno.setQtImoveisFaturaRevisaoRecorrenciaIndustrial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaIndustrial(),bean2.getQtImoveisFaturaRevisaoRecorrenciaIndustrial()));
		 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial()));
		 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial()));	 
			 
		 retorno.setAlteracaoNumeroEconomiasAcrescidoIndustrial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoIndustrial(),bean2.getAlteracaoNumeroEconomiasAcrescidoIndustrial()));
		 retorno.setAlteracaoQuantidadeEconomiasAcrescidoIndustrial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoIndustrial(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoIndustrial()));
		 retorno.setAlteracaoNumeroEconomiasDecrescidoIndustrial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoIndustrial(),bean2.getAlteracaoNumeroEconomiasDecrescidoIndustrial()));
		 retorno.setAlteracaoQuantidadeEconomiasDecrescidoIndustrial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoIndustrial(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoIndustrial()));
		 retorno.setAlteracaoCategoriaIndustrial(Util.somaInteiros(bean.getAlteracaoCategoriaIndustrial(),bean2.getAlteracaoCategoriaIndustrial()));
		 retorno.setInclusaoTarifaSocialIndustrial(Util.somaInteiros(bean.getInclusaoTarifaSocialIndustrial(),bean2.getInclusaoTarifaSocialIndustrial()));
		 retorno.setExclusaoTarifaSocialIndustrial(Util.somaInteiros(bean.getExclusaoTarifaSocialIndustrial(),bean2.getExclusaoTarifaSocialIndustrial()));
		 retorno.setFaturamentoSuspensoIndustrial(Util.somaInteiros(bean.getFaturamentoSuspensoIndustrial(),bean2.getFaturamentoSuspensoIndustrial()));
	
		 
		 retorno.setHidSubstituidosRamalPublico(
				 Util.somaInteiros(bean2.getHidSubstituidosRamalPublico(),bean.getHidSubstituidosRamalPublico()));
		retorno.setHidSubstituidosPocoPublico(
				Util.somaInteiros(bean2.getHidSubstituidosPocoPublico(),bean.getHidSubstituidosPocoPublico()));
		retorno.setHidInstaladosRamalPublico(Util.somaInteiros(bean.getHidInstaladosRamalPublico(),bean2.getHidInstaladosRamalPublico()));
		retorno.setHidInstaladosPocoPublico(Util.somaInteiros(bean.getHidInstaladosPocoPublico(),bean2.getHidInstaladosPocoPublico()));
		 retorno.setCortesExecutadosPublico(Util.somaInteiros(bean.getCortesExecutadosPublico(),bean2.getCortesExecutadosPublico()));
		 retorno.setSuprExecutadasPublico(Util.somaInteiros(bean.getSuprExecutadasPublico(),bean2.getSuprExecutadasPublico()));
		 retorno.setReligacoesPublico(Util.somaInteiros(bean.getReligacoesPublico(),bean2.getReligacoesPublico()));
		 retorno.setReestabPublico(Util.somaInteiros(bean.getReestabPublico(),bean2.getReestabPublico()));
		 retorno.setClandCortadosPublico(Util.somaInteiros(bean.getClandCortadosPublico(),bean2.getClandCortadosPublico()));
		 retorno.setClandSuprimidosPublico(Util.somaInteiros(bean.getClandSuprimidosPublico(),bean2.getClandSuprimidosPublico()));
		 retorno.setContasEmitidasPublico(Util.somaInteiros(bean.getContasEmitidasPublico(),bean2.getContasEmitidasPublico()));
		 retorno.setLeitEfetuadasPublico(Util.somaInteiros(bean.getLeitEfetuadasPublico(),bean2.getLeitEfetuadasPublico()));
		 retorno.setLeitAnormalidadesPublico(Util.somaInteiros(bean.getLeitAnormalidadesPublico(),bean2.getLeitAnormalidadesPublico()));
		 retorno.setAvisoCortePublico(Util.somaInteiros(bean.getAvisoCortePublico(),bean2.getAvisoCortePublico()));
		 retorno.setPercAnormalidadePublico(Util.somaBigDecimal(bean.getPercAnormalidadePublico(),  bean2.getPercAnormalidadePublico()));
		 retorno.setPercHidrometracaoPublico(Util.somaBigDecimal(bean.getPercHidrometracaoPublico(),  bean2.getPercHidrometracaoPublico()));
		 retorno.setLigImplantadasAguaPublico(Util.somaInteiros(bean.getLigImplantadasAguaPublico(),bean2.getLigImplantadasAguaPublico()));
		 retorno.setLigImplantadasEsgotoPublico(Util.somaInteiros(bean.getLigImplantadasEsgotoPublico(),bean2.getLigImplantadasEsgotoPublico()));
		 retorno.setRaPendentesComPrazoPublico(Util.somaInteiros(bean.getRaPendentesComPrazoPublico(),bean2.getRaPendentesComPrazoPublico()));
		 retorno.setRaPendentesComForaPrazoPublico(Util.somaInteiros(bean.getRaPendentesComForaPrazoPublico(),bean2.getRaPendentesComForaPrazoPublico()));
		 retorno.setRaPendentesOpPrazoPublico(Util.somaInteiros(bean.getRaPendentesOpPrazoPublico(),bean2.getRaPendentesOpPrazoPublico()));
		 retorno.setRaPendentesOpForaPrazoPublico(Util.somaInteiros(bean.getRaPendentesOpForaPrazoPublico(),bean2.getRaPendentesOpForaPrazoPublico()));
		 retorno.setFaturaRevisaoPublico(Util.somaInteiros(bean.getFaturaRevisaoPublico(),bean2.getFaturaRevisaoPublico()));
		 retorno.setCartasNegativacaoPublico(Util.somaInteiros(bean.getCartasNegativacaoPublico(),bean2.getCartasNegativacaoPublico()));
		 retorno.setEconomiaAtivaPublico(Util.somaInteiros(bean.getEconomiaAtivaPublico(),bean2.getEconomiaAtivaPublico()));
		 retorno.setEconomiaInativaPublico(Util.somaInteiros(bean.getEconomiaInativaPublico(),bean2.getEconomiaInativaPublico()));
		 retorno.setLigacoesAtivasPublico(Util.somaInteiros(bean.getLigacoesAtivasPublico(),bean2.getLigacoesAtivasPublico()));
		 retorno.setLigacoesInativasPublico(Util.somaInteiros(bean.getLigacoesInativasPublico(),bean2.getLigacoesInativasPublico()));
		 retorno.setRegAnormalidadeInformadaPublico(Util.somaInteiros(bean.getRegAnormalidadeInformadaPublico(),bean2.getRegAnormalidadeInformadaPublico()));
		 retorno.setRegRecebidosPublico(Util.somaInteiros(bean.getRegRecebidosPublico(),bean2.getRegRecebidosPublico()));
		 retorno.setLigAtivasHidrometroPublico(Util.somaInteiros(bean.getLigAtivasHidrometroPublico(),bean2.getLigAtivasHidrometroPublico()));
		 retorno.setLigAtivasPublico(Util.somaInteiros(bean.getLigAtivasPublico(),bean2.getLigAtivasPublico()));
		 
		 retorno.setFaturaRevisaoReclamacaoConsumoPublico(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoPublico(),bean2.getFaturaRevisaoReclamacaoConsumoPublico()));
		 retorno.setFaturaRevisaoRecorrenciaPublico(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaPublico(),bean2.getFaturaRevisaoRecorrenciaPublico()));
		 retorno.setFaturaRevisaoFaturamentoIndevidoPublico(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoPublico(),bean2.getFaturaRevisaoFaturamentoIndevidoPublico()));
		 
		 retorno.setQtImoveisFaturaRevisaoRecorrenciaPublico(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaPublico(),bean2.getQtImoveisFaturaRevisaoRecorrenciaPublico()));
		 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoPublico(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoPublico(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoPublico()));
		 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoPublico(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoPublico(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoPublico()));	 
			 
		 retorno.setAlteracaoNumeroEconomiasAcrescidoPublico(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoPublico(),bean2.getAlteracaoNumeroEconomiasAcrescidoPublico()));
		 retorno.setAlteracaoQuantidadeEconomiasAcrescidoPublico(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoPublico(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoPublico()));
		 retorno.setAlteracaoNumeroEconomiasDecrescidoPublico(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoPublico(),bean2.getAlteracaoNumeroEconomiasDecrescidoPublico()));
		 retorno.setAlteracaoQuantidadeEconomiasDecrescidoPublico(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoPublico(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoPublico()));
		 retorno.setAlteracaoCategoriaPublico(Util.somaInteiros(bean.getAlteracaoCategoriaPublico(),bean2.getAlteracaoCategoriaPublico()));
		 retorno.setInclusaoTarifaSocialPublico(Util.somaInteiros(bean.getInclusaoTarifaSocialPublico(),bean2.getInclusaoTarifaSocialPublico()));
		 retorno.setExclusaoTarifaSocialPublico(Util.somaInteiros(bean.getExclusaoTarifaSocialPublico(),bean2.getExclusaoTarifaSocialPublico()));
		 retorno.setFaturamentoSuspensoPublico(Util.somaInteiros(bean.getFaturamentoSuspensoPublico(),bean2.getFaturamentoSuspensoPublico()));
		
		
		 retorno.setHidSubstituidosRamalNormal(
				 Util.somaInteiros(bean2.getHidSubstituidosRamalNormal(),bean.getHidSubstituidosRamalNormal()));
		retorno.setHidSubstituidosPocoNormal(
				Util.somaInteiros(bean2.getHidSubstituidosPocoNormal(),bean.getHidSubstituidosPocoNormal()));
		retorno.setHidInstaladosRamalNormal(Util.somaInteiros(bean.getHidInstaladosRamalNormal(),bean2.getHidInstaladosRamalNormal()));
		retorno.setHidInstaladosPocoNormal(Util.somaInteiros(bean.getHidInstaladosPocoNormal(),bean2.getHidInstaladosPocoNormal()));
		retorno.setCortesExecutadosNormal(Util.somaInteiros(bean.getCortesExecutadosNormal(),bean2.getCortesExecutadosNormal()));
		retorno.setSuprExecutadasNormal(Util.somaInteiros(bean.getSuprExecutadasNormal(),bean2.getSuprExecutadasNormal()));
		retorno.setReligacoesNormal(Util.somaInteiros(bean.getReligacoesNormal(),bean2.getReligacoesNormal()));
		retorno.setReestabNormal(Util.somaInteiros(bean.getReestabNormal(),bean2.getReestabNormal()));
		retorno.setClandCortadosNormal(Util.somaInteiros(bean.getClandCortadosNormal(),bean2.getClandCortadosNormal()));
		retorno.setClandSuprimidosNormal(Util.somaInteiros(bean.getClandSuprimidosNormal(),bean2.getClandSuprimidosNormal()));
		retorno.setContasEmitidasNormal(Util.somaInteiros(bean.getContasEmitidasNormal(),bean2.getContasEmitidasNormal()));
		retorno.setLeitEfetuadasNormal(Util.somaInteiros(bean.getLeitEfetuadasNormal(),bean2.getLeitEfetuadasNormal()));
		retorno.setLeitAnormalidadesNormal(Util.somaInteiros(bean.getLeitAnormalidadesNormal(),bean2.getLeitAnormalidadesNormal()));
		retorno.setAvisoCorteNormal(Util.somaInteiros(bean.getAvisoCorteNormal(),bean2.getAvisoCorteNormal()));
		retorno.setPercAnormalidadeNormal(Util.somaBigDecimal(bean.getPercAnormalidadeNormal(),  bean2.getPercAnormalidadeNormal()));
		retorno.setPercHidrometracaoNormal(Util.somaBigDecimal(bean.getPercHidrometracaoNormal(),  bean2.getPercHidrometracaoNormal()));
		retorno.setLigImplantadasAguaNormal(Util.somaInteiros(bean.getLigImplantadasAguaNormal(),bean2.getLigImplantadasAguaNormal()));
		retorno.setLigImplantadasEsgotoNormal(Util.somaInteiros(bean.getLigImplantadasEsgotoNormal(),bean2.getLigImplantadasEsgotoNormal()));
		retorno.setRaPendentesComPrazoNormal(Util.somaInteiros(bean.getRaPendentesComPrazoNormal(),bean2.getRaPendentesComPrazoNormal()));
		retorno.setRaPendentesComForaPrazoNormal(Util.somaInteiros(bean.getRaPendentesComForaPrazoNormal(),bean2.getRaPendentesComForaPrazoNormal()));
		retorno.setRaPendentesOpPrazoNormal(Util.somaInteiros(bean.getRaPendentesOpPrazoNormal(),bean2.getRaPendentesOpPrazoNormal()));
		retorno.setRaPendentesOpForaPrazoNormal(Util.somaInteiros(bean.getRaPendentesOpForaPrazoNormal(),bean2.getRaPendentesOpForaPrazoNormal()));
		retorno.setFaturaRevisaoNormal(Util.somaInteiros(bean.getFaturaRevisaoNormal(),bean2.getFaturaRevisaoNormal()));
		retorno.setCartasNegativacaoNormal(Util.somaInteiros(bean.getCartasNegativacaoNormal(),bean2.getCartasNegativacaoNormal()));
		retorno.setEconomiaAtivaNormal(Util.somaInteiros(bean.getEconomiaAtivaNormal(),bean2.getEconomiaAtivaNormal()));
		retorno.setEconomiaInativaNormal(Util.somaInteiros(bean.getEconomiaInativaNormal(),bean2.getEconomiaInativaNormal()));
		retorno.setLigacoesAtivasNormal(Util.somaInteiros(bean.getLigacoesAtivasNormal(),bean2.getLigacoesAtivasNormal()));
		retorno.setLigacoesInativasNormal(Util.somaInteiros(bean.getLigacoesInativasNormal(),bean2.getLigacoesInativasNormal()));
		retorno.setRegAnormalidadeInformadaNormal(Util.somaInteiros(bean.getRegAnormalidadeInformadaNormal(),bean2.getRegAnormalidadeInformadaNormal()));
		 retorno.setRegRecebidosNormal(Util.somaInteiros(bean.getRegRecebidosNormal(),bean2.getRegRecebidosNormal()));
		 retorno.setLigAtivasHidrometroNormal(Util.somaInteiros(bean.getLigAtivasHidrometroNormal(),bean2.getLigAtivasHidrometroNormal()));
		 retorno.setLigAtivasNormal(Util.somaInteiros(bean.getLigAtivasNormal(),bean2.getLigAtivasNormal()));
		 
		 retorno.setFaturaRevisaoReclamacaoConsumoNormal(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoNormal(),bean2.getFaturaRevisaoReclamacaoConsumoNormal()));
		 retorno.setFaturaRevisaoRecorrenciaNormal(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaNormal(),bean2.getFaturaRevisaoRecorrenciaNormal()));
		 retorno.setFaturaRevisaoFaturamentoIndevidoNormal(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoNormal(),bean2.getFaturaRevisaoFaturamentoIndevidoNormal()));
		 
		 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoNormal(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoNormal(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoNormal()));
		 retorno.setQtImoveisFaturaRevisaoRecorrenciaNormal(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaNormal(),bean2.getQtImoveisFaturaRevisaoRecorrenciaNormal()));
		 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoNormal(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoNormal(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoNormal()));
		 
		 retorno.setAlteracaoNumeroEconomiasAcrescidoNormal(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoNormal(),bean2.getAlteracaoNumeroEconomiasAcrescidoNormal()));
		 retorno.setAlteracaoQuantidadeEconomiasAcrescidoNormal(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoNormal(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoNormal()));
		 retorno.setAlteracaoNumeroEconomiasDecrescidoNormal(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoNormal(),bean2.getAlteracaoNumeroEconomiasDecrescidoNormal()));
		 retorno.setAlteracaoQuantidadeEconomiasDecrescidoNormal(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoNormal(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoNormal()));
		 retorno.setAlteracaoCategoriaNormal(Util.somaInteiros(bean.getAlteracaoCategoriaNormal(),bean2.getAlteracaoCategoriaNormal()));
		 retorno.setInclusaoTarifaSocialNormal(Util.somaInteiros(bean.getInclusaoTarifaSocialNormal(),bean2.getInclusaoTarifaSocialNormal()));
		 retorno.setExclusaoTarifaSocialNormal(Util.somaInteiros(bean.getExclusaoTarifaSocialNormal(),bean2.getExclusaoTarifaSocialNormal()));
		 retorno.setFaturamentoSuspensoNormal(Util.somaInteiros(bean.getFaturamentoSuspensoNormal(),bean2.getFaturamentoSuspensoNormal()));
		
		
			 retorno.setHidSubstituidosRamalTarSocial(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalTarSocial(),bean.getHidSubstituidosRamalTarSocial()));
			retorno.setHidSubstituidosPocoTarSocial(
					Util.somaInteiros(bean2.getHidSubstituidosPocoTarSocial(),bean.getHidSubstituidosPocoTarSocial()));
			retorno.setHidInstaladosRamalTarSocial(Util.somaInteiros(bean.getHidInstaladosRamalTarSocial(),bean2.getHidInstaladosRamalTarSocial()));
			retorno.setHidInstaladosPocoTarSocial(Util.somaInteiros(bean.getHidInstaladosPocoTarSocial(),bean2.getHidInstaladosPocoTarSocial()));
			retorno.setCortesExecutadosTarSocial(Util.somaInteiros(bean.getCortesExecutadosTarSocial(),bean2.getCortesExecutadosTarSocial()));
			retorno.setSuprExecutadasTarSocial(Util.somaInteiros(bean.getSuprExecutadasTarSocial(),bean2.getSuprExecutadasTarSocial()));
			 retorno.setReligacoesTarSocial(Util.somaInteiros(bean.getReligacoesTarSocial(),bean2.getReligacoesTarSocial()));
			 retorno.setReestabTarSocial(Util.somaInteiros(bean.getReestabTarSocial(),bean2.getReestabTarSocial()));
			 retorno.setClandCortadosTarSocial(Util.somaInteiros(bean.getClandCortadosTarSocial(),bean2.getClandCortadosTarSocial()));
			 retorno.setClandSuprimidosTarSocial(Util.somaInteiros(bean.getClandSuprimidosTarSocial(),bean2.getClandSuprimidosTarSocial()));
			 retorno.setContasEmitidasTarSocial(Util.somaInteiros(bean.getContasEmitidasTarSocial(),bean2.getContasEmitidasTarSocial()));
			 retorno.setLeitEfetuadasTarSocial(Util.somaInteiros(bean.getLeitEfetuadasTarSocial(),bean2.getLeitEfetuadasTarSocial()));
			 retorno.setLeitAnormalidadesTarSocial(Util.somaInteiros(bean.getLeitAnormalidadesTarSocial(),bean2.getLeitAnormalidadesTarSocial()));
			 retorno.setAvisoCorteTarSocial(Util.somaInteiros(bean.getAvisoCorteTarSocial(),bean2.getAvisoCorteTarSocial()));
			 retorno.setPercAnormalidadeTarSocial(Util.somaBigDecimal(bean.getPercAnormalidadeTarSocial(),  bean2.getPercAnormalidadeTarSocial()));
			 retorno.setPercHidrometracaoTarSocial(Util.somaBigDecimal(bean.getPercHidrometracaoTarSocial(),  bean2.getPercHidrometracaoTarSocial()));
			 retorno.setLigImplantadasAguaTarSocial(Util.somaInteiros(bean.getLigImplantadasAguaTarSocial(),bean2.getLigImplantadasAguaTarSocial()));
			 retorno.setLigImplantadasEsgotoTarSocial(Util.somaInteiros(bean.getLigImplantadasEsgotoTarSocial(),bean2.getLigImplantadasEsgotoTarSocial()));
			 retorno.setRaPendentesComPrazoTarSocial(Util.somaInteiros(bean.getRaPendentesComPrazoTarSocial(),bean2.getRaPendentesComPrazoTarSocial()));
			 retorno.setRaPendentesComForaPrazoTarSocial(Util.somaInteiros(bean.getRaPendentesComForaPrazoTarSocial(),bean2.getRaPendentesComForaPrazoTarSocial()));
			 retorno.setRaPendentesOpPrazoTarSocial(Util.somaInteiros(bean.getRaPendentesOpPrazoTarSocial(),bean2.getRaPendentesOpPrazoTarSocial()));
			 retorno.setRaPendentesOpForaPrazoTarSocial(Util.somaInteiros(bean.getRaPendentesOpForaPrazoTarSocial(),bean2.getRaPendentesOpForaPrazoTarSocial()));
			 retorno.setFaturaRevisaoTarSocial(Util.somaInteiros(bean.getFaturaRevisaoTarSocial(),bean2.getFaturaRevisaoTarSocial()));
			 retorno.setCartasNegativacaoTarSocial(Util.somaInteiros(bean.getCartasNegativacaoTarSocial(),bean2.getCartasNegativacaoTarSocial()));
			 retorno.setEconomiaAtivaTarSocial(Util.somaInteiros(bean.getEconomiaAtivaTarSocial(),bean2.getEconomiaAtivaTarSocial()));
			 retorno.setEconomiaInativaTarSocial(Util.somaInteiros(bean.getEconomiaInativaTarSocial(),bean2.getEconomiaInativaTarSocial()));
			 retorno.setLigacoesAtivasTarSocial(Util.somaInteiros(bean.getLigacoesAtivasTarSocial(),bean2.getLigacoesAtivasTarSocial()));
			 retorno.setLigacoesInativasTarSocial(Util.somaInteiros(bean.getLigacoesInativasTarSocial(),bean2.getLigacoesInativasTarSocial()));
			 retorno.setRegAnormalidadeInformadaTarSocial(Util.somaInteiros(bean.getRegAnormalidadeInformadaTarSocial(),bean2.getRegAnormalidadeInformadaTarSocial()));
			 retorno.setRegRecebidosTarSocial(Util.somaInteiros(bean.getRegRecebidosTarSocial(),bean2.getRegRecebidosTarSocial()));
			 retorno.setLigAtivasHidrometroTarSocial(Util.somaInteiros(bean.getLigAtivasHidrometroTarSocial(),bean2.getLigAtivasHidrometroTarSocial()));
			 retorno.setLigAtivasTarSocial(Util.somaInteiros(bean.getLigAtivasTarSocial(),bean2.getLigAtivasTarSocial()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoTarSocial(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoTarSocial(),bean2.getFaturaRevisaoReclamacaoConsumoTarSocial()));
			 retorno.setFaturaRevisaoRecorrenciaTarSocial(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaTarSocial(),bean2.getFaturaRevisaoRecorrenciaTarSocial()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoTarSocial(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoTarSocial(),bean2.getFaturaRevisaoFaturamentoIndevidoTarSocial()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaTarSocial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaTarSocial(),bean2.getQtImoveisFaturaRevisaoRecorrenciaTarSocial()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial()));
			
			 retorno.setAlteracaoNumeroEconomiasAcrescidoTarSocial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoTarSocial(),bean2.getAlteracaoNumeroEconomiasAcrescidoTarSocial()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoTarSocial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoTarSocial(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoTarSocial()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoTarSocial(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoTarSocial(),bean2.getAlteracaoNumeroEconomiasDecrescidoTarSocial()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoTarSocial(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoTarSocial(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoTarSocial()));
			 retorno.setAlteracaoCategoriaTarSocial(Util.somaInteiros(bean.getAlteracaoCategoriaTarSocial(),bean2.getAlteracaoCategoriaTarSocial()));
			 retorno.setInclusaoTarifaSocialTarSocial(Util.somaInteiros(bean.getInclusaoTarifaSocialTarSocial(),bean2.getInclusaoTarifaSocialTarSocial()));
			 retorno.setExclusaoTarifaSocialTarSocial(Util.somaInteiros(bean.getExclusaoTarifaSocialTarSocial(),bean2.getExclusaoTarifaSocialTarSocial()));
			 retorno.setFaturamentoSuspensoTarSocial(Util.somaInteiros(bean.getFaturamentoSuspensoTarSocial(),bean2.getFaturamentoSuspensoTarSocial()));
			 
			 
			 retorno.setHidSubstituidosRamalGrande(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalGrande(),bean.getHidSubstituidosRamalGrande()));
			retorno.setHidSubstituidosPocoGrande(
					Util.somaInteiros(bean2.getHidSubstituidosPocoGrande(),bean.getHidSubstituidosPocoGrande()));
			retorno.setHidInstaladosRamalGrande(Util.somaInteiros(bean.getHidInstaladosRamalGrande(),bean2.getHidInstaladosRamalGrande()));
			retorno.setHidInstaladosPocoGrande(Util.somaInteiros(bean.getHidInstaladosPocoGrande(),bean2.getHidInstaladosPocoGrande()));
			 retorno.setCortesExecutadosGrande(Util.somaInteiros(bean.getCortesExecutadosGrande(),bean2.getCortesExecutadosGrande()));
			 retorno.setSuprExecutadasGrande(Util.somaInteiros(bean.getSuprExecutadasGrande(),bean2.getSuprExecutadasGrande()));
			 retorno.setReligacoesGrande(Util.somaInteiros(bean.getReligacoesGrande(),bean2.getReligacoesGrande()));
			 retorno.setReestabGrande(Util.somaInteiros(bean.getReestabGrande(),bean2.getReestabGrande()));
			 retorno.setClandCortadosGrande(Util.somaInteiros(bean.getClandCortadosGrande(),bean2.getClandCortadosGrande()));
			 retorno.setClandSuprimidosGrande(Util.somaInteiros(bean.getClandSuprimidosGrande(),bean2.getClandSuprimidosGrande()));
			 retorno.setContasEmitidasGrande(Util.somaInteiros(bean.getContasEmitidasGrande(),bean2.getContasEmitidasGrande()));
			 retorno.setLeitEfetuadasGrande(Util.somaInteiros(bean.getLeitEfetuadasGrande(),bean2.getLeitEfetuadasGrande()));
			 retorno.setLeitAnormalidadesGrande(Util.somaInteiros(bean.getLeitAnormalidadesGrande(),bean2.getLeitAnormalidadesGrande()));
			 retorno.setAvisoCorteGrande(Util.somaInteiros(bean.getAvisoCorteGrande(),bean2.getAvisoCorteGrande()));
			 retorno.setPercAnormalidadeGrande(Util.somaBigDecimal(bean.getPercAnormalidadeGrande(),  bean2.getPercAnormalidadeGrande()));
			 retorno.setPercHidrometracaoGrande(Util.somaBigDecimal(bean.getPercHidrometracaoGrande(),  bean2.getPercHidrometracaoGrande()));
			 retorno.setLigImplantadasAguaGrande(Util.somaInteiros(bean.getLigImplantadasAguaGrande(),bean2.getLigImplantadasAguaGrande()));
			 retorno.setLigImplantadasEsgotoGrande(Util.somaInteiros(bean.getLigImplantadasEsgotoGrande(),bean2.getLigImplantadasEsgotoGrande()));
			 retorno.setRaPendentesComPrazoGrande(Util.somaInteiros(bean.getRaPendentesComPrazoGrande(),bean2.getRaPendentesComPrazoGrande()));
			 retorno.setRaPendentesComForaPrazoGrande(Util.somaInteiros(bean.getRaPendentesComForaPrazoGrande(),bean2.getRaPendentesComForaPrazoGrande()));
			 retorno.setRaPendentesOpPrazoGrande(Util.somaInteiros(bean.getRaPendentesOpPrazoGrande(),bean2.getRaPendentesOpPrazoGrande()));
			 retorno.setRaPendentesOpForaPrazoGrande(Util.somaInteiros(bean.getRaPendentesOpForaPrazoGrande(),bean2.getRaPendentesOpForaPrazoGrande()));
			 retorno.setFaturaRevisaoGrande(Util.somaInteiros(bean.getFaturaRevisaoGrande(),bean2.getFaturaRevisaoGrande()));
			 retorno.setCartasNegativacaoGrande(Util.somaInteiros(bean.getCartasNegativacaoGrande(),bean2.getCartasNegativacaoGrande()));
			 retorno.setEconomiaAtivaGrande(Util.somaInteiros(bean.getEconomiaAtivaGrande(),bean2.getEconomiaAtivaGrande()));
			 retorno.setEconomiaInativaGrande(Util.somaInteiros(bean.getEconomiaInativaGrande(),bean2.getEconomiaInativaGrande()));
			 retorno.setLigacoesAtivasGrande(Util.somaInteiros(bean.getLigacoesAtivasGrande(),bean2.getLigacoesAtivasGrande()));
			 retorno.setLigacoesInativasGrande(Util.somaInteiros(bean.getLigacoesInativasGrande(),bean2.getLigacoesInativasGrande()));
			 retorno.setRegAnormalidadeInformadaGrande(Util.somaInteiros(bean.getRegAnormalidadeInformadaGrande(),bean2.getRegAnormalidadeInformadaGrande()));
			 retorno.setRegRecebidosGrande(Util.somaInteiros(bean.getRegRecebidosGrande(),bean2.getRegRecebidosGrande()));
			 retorno.setLigAtivasHidrometroGrande(Util.somaInteiros(bean.getLigAtivasHidrometroGrande(),bean2.getLigAtivasHidrometroGrande()));
			 retorno.setLigAtivasGrande(Util.somaInteiros(bean.getLigAtivasGrande(),bean2.getLigAtivasGrande()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoGrande(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoGrande(),bean2.getFaturaRevisaoReclamacaoConsumoGrande()));
			 retorno.setFaturaRevisaoRecorrenciaGrande(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaGrande(),bean2.getFaturaRevisaoRecorrenciaGrande()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoGrande(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoGrande(),bean2.getFaturaRevisaoFaturamentoIndevidoGrande()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoGrande(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoGrande(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoGrande()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaGrande(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaGrande(),bean2.getQtImoveisFaturaRevisaoRecorrenciaGrande()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoGrande(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrande(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrande()));
			 
			 retorno.setAlteracaoNumeroEconomiasAcrescidoGrande(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoGrande(),bean2.getAlteracaoNumeroEconomiasAcrescidoGrande()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoGrande(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoGrande(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoGrande()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoGrande(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoGrande(),bean2.getAlteracaoNumeroEconomiasDecrescidoGrande()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoGrande(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoGrande(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoGrande()));
			 retorno.setAlteracaoCategoriaGrande(Util.somaInteiros(bean.getAlteracaoCategoriaGrande(),bean2.getAlteracaoCategoriaGrande()));
			 retorno.setInclusaoTarifaSocialGrande(Util.somaInteiros(bean.getInclusaoTarifaSocialGrande(),bean2.getInclusaoTarifaSocialGrande()));
			 retorno.setExclusaoTarifaSocialGrande(Util.somaInteiros(bean.getExclusaoTarifaSocialGrande(),bean2.getExclusaoTarifaSocialGrande()));
			 retorno.setFaturamentoSuspensoGrande(Util.somaInteiros(bean.getFaturamentoSuspensoGrande(),bean2.getFaturamentoSuspensoGrande()));
			 
			 
			 retorno.setHidSubstituidosRamalCorporativo(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalCorporativo(),bean.getHidSubstituidosRamalCorporativo()));
			retorno.setHidSubstituidosPocoCorporativo(
					Util.somaInteiros(bean2.getHidSubstituidosPocoCorporativo(),bean.getHidSubstituidosPocoCorporativo()));
			retorno.setHidInstaladosRamalCorporativo(Util.somaInteiros(bean.getHidInstaladosRamalCorporativo(),bean2.getHidInstaladosRamalCorporativo()));
			retorno.setHidInstaladosPocoCorporativo(Util.somaInteiros(bean.getHidInstaladosPocoCorporativo(),bean2.getHidInstaladosPocoCorporativo()));
			 retorno.setCortesExecutadosCorporativo(Util.somaInteiros(bean.getCortesExecutadosCorporativo(),bean2.getCortesExecutadosCorporativo()));
			 retorno.setSuprExecutadasCorporativo(Util.somaInteiros(bean.getSuprExecutadasCorporativo(),bean2.getSuprExecutadasCorporativo()));
			 retorno.setReligacoesCorporativo(Util.somaInteiros(bean.getReligacoesCorporativo(),bean2.getReligacoesCorporativo()));
			 retorno.setReestabCorporativo(Util.somaInteiros(bean.getReestabCorporativo(),bean2.getReestabCorporativo()));
			 retorno.setClandCortadosCorporativo(Util.somaInteiros(bean.getClandCortadosCorporativo(),bean2.getClandCortadosCorporativo()));
			 retorno.setClandSuprimidosCorporativo(Util.somaInteiros(bean.getClandSuprimidosCorporativo(),bean2.getClandSuprimidosCorporativo()));
			 retorno.setContasEmitidasCorporativo(Util.somaInteiros(bean.getContasEmitidasCorporativo(),bean2.getContasEmitidasCorporativo()));
			 retorno.setLeitEfetuadasCorporativo(Util.somaInteiros(bean.getLeitEfetuadasCorporativo(),bean2.getLeitEfetuadasCorporativo()));
			 retorno.setLeitAnormalidadesCorporativo(Util.somaInteiros(bean.getLeitAnormalidadesCorporativo(),bean2.getLeitAnormalidadesCorporativo()));
			 retorno.setAvisoCorteCorporativo(Util.somaInteiros(bean.getAvisoCorteCorporativo(),bean2.getAvisoCorteCorporativo()));
			 retorno.setPercAnormalidadeCorporativo(Util.somaBigDecimal(bean.getPercAnormalidadeCorporativo(),  bean2.getPercAnormalidadeCorporativo()));
			 retorno.setPercHidrometracaoCorporativo(Util.somaBigDecimal(bean.getPercHidrometracaoCorporativo(),  bean2.getPercHidrometracaoCorporativo()));
			 retorno.setLigImplantadasAguaCorporativo(Util.somaInteiros(bean.getLigImplantadasAguaCorporativo(),bean2.getLigImplantadasAguaCorporativo()));
			 retorno.setLigImplantadasEsgotoCorporativo(Util.somaInteiros(bean.getLigImplantadasEsgotoCorporativo(),bean2.getLigImplantadasEsgotoCorporativo()));
			 retorno.setRaPendentesComPrazoCorporativo(Util.somaInteiros(bean.getRaPendentesComPrazoCorporativo(),bean2.getRaPendentesComPrazoCorporativo()));
			 retorno.setRaPendentesComForaPrazoCorporativo(Util.somaInteiros(bean.getRaPendentesComForaPrazoCorporativo(),bean2.getRaPendentesComForaPrazoCorporativo()));
			 retorno.setRaPendentesOpPrazoCorporativo(Util.somaInteiros(bean.getRaPendentesOpPrazoCorporativo(),bean2.getRaPendentesOpPrazoCorporativo()));
			 retorno.setRaPendentesOpForaPrazoCorporativo(Util.somaInteiros(bean.getRaPendentesOpForaPrazoCorporativo(),bean2.getRaPendentesOpForaPrazoCorporativo()));
			 retorno.setFaturaRevisaoCorporativo(Util.somaInteiros(bean.getFaturaRevisaoCorporativo(),bean2.getFaturaRevisaoCorporativo()));
			 retorno.setCartasNegativacaoCorporativo(Util.somaInteiros(bean.getCartasNegativacaoCorporativo(),bean2.getCartasNegativacaoCorporativo()));
			 retorno.setEconomiaAtivaCorporativo(Util.somaInteiros(bean.getEconomiaAtivaCorporativo(),bean2.getEconomiaAtivaCorporativo()));
			 retorno.setEconomiaInativaCorporativo(Util.somaInteiros(bean.getEconomiaInativaCorporativo(),bean2.getEconomiaInativaCorporativo()));
			 retorno.setLigacoesAtivasCorporativo(Util.somaInteiros(bean.getLigacoesAtivasCorporativo(),bean2.getLigacoesAtivasCorporativo()));
			 retorno.setLigacoesInativasCorporativo(Util.somaInteiros(bean.getLigacoesInativasCorporativo(),bean2.getLigacoesInativasCorporativo()));
			 retorno.setRegAnormalidadeInformadaCorporativo(Util.somaInteiros(bean.getRegAnormalidadeInformadaCorporativo(),bean2.getRegAnormalidadeInformadaCorporativo()));
			 retorno.setRegRecebidosCorporativo(Util.somaInteiros(bean.getRegRecebidosCorporativo(),bean2.getRegRecebidosCorporativo()));
			 retorno.setLigAtivasHidrometroCorporativo(Util.somaInteiros(bean.getLigAtivasHidrometroCorporativo(),bean2.getLigAtivasHidrometroCorporativo()));
			 retorno.setLigAtivasCorporativo(Util.somaInteiros(bean.getLigAtivasCorporativo(),bean2.getLigAtivasCorporativo()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoCorporativo(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoCorporativo(),bean2.getFaturaRevisaoReclamacaoConsumoCorporativo()));
			 retorno.setFaturaRevisaoRecorrenciaCorporativo(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaCorporativo(),bean2.getFaturaRevisaoRecorrenciaCorporativo()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoCorporativo(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoCorporativo(),bean2.getFaturaRevisaoFaturamentoIndevidoCorporativo()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaCorporativo(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaCorporativo(),bean2.getQtImoveisFaturaRevisaoRecorrenciaCorporativo()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo()));
			 
			 retorno.setAlteracaoNumeroEconomiasAcrescidoCorporativo(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoCorporativo(),bean2.getAlteracaoNumeroEconomiasAcrescidoCorporativo()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoCorporativo(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoCorporativo(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoCorporativo()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoCorporativo(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoCorporativo(),bean2.getAlteracaoNumeroEconomiasDecrescidoCorporativo()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoCorporativo(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoCorporativo(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoCorporativo()));
			 retorno.setAlteracaoCategoriaCorporativo(Util.somaInteiros(bean.getAlteracaoCategoriaCorporativo(),bean2.getAlteracaoCategoriaCorporativo()));
			 retorno.setInclusaoTarifaSocialCorporativo(Util.somaInteiros(bean.getInclusaoTarifaSocialCorporativo(),bean2.getInclusaoTarifaSocialCorporativo()));
			 retorno.setExclusaoTarifaSocialCorporativo(Util.somaInteiros(bean.getExclusaoTarifaSocialCorporativo(),bean2.getExclusaoTarifaSocialCorporativo()));
			 retorno.setFaturamentoSuspensoCorporativo(Util.somaInteiros(bean.getFaturamentoSuspensoCorporativo(),bean2.getFaturamentoSuspensoCorporativo()));
			 
			 
			 retorno.setHidSubstituidosRamalGrandeTelemedido(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalGrandeTelemedido(),bean.getHidSubstituidosRamalGrandeTelemedido()));
			retorno.setHidSubstituidosPocoGrandeTelemedido(
					Util.somaInteiros(bean2.getHidSubstituidosPocoGrandeTelemedido(),bean.getHidSubstituidosPocoGrandeTelemedido()));
			retorno.setHidInstaladosRamalGrandeTelemedido(Util.somaInteiros(bean.getHidInstaladosRamalGrandeTelemedido(),bean2.getHidInstaladosRamalGrandeTelemedido()));
			retorno.setHidInstaladosPocoGrandeTelemedido(Util.somaInteiros(bean.getHidInstaladosPocoGrandeTelemedido(),bean2.getHidInstaladosPocoGrandeTelemedido()));
			 retorno.setCortesExecutadosGrandeTelemedido(Util.somaInteiros(bean.getCortesExecutadosGrandeTelemedido(),bean2.getCortesExecutadosGrandeTelemedido()));
			 retorno.setSuprExecutadasGrandeTelemedido(Util.somaInteiros(bean.getSuprExecutadasGrandeTelemedido(),bean2.getSuprExecutadasGrandeTelemedido()));
			 retorno.setReligacoesGrandeTelemedido(Util.somaInteiros(bean.getReligacoesGrandeTelemedido(),bean2.getReligacoesGrandeTelemedido()));
			 retorno.setReestabGrandeTelemedido(Util.somaInteiros(bean.getReestabGrandeTelemedido(),bean2.getReestabGrandeTelemedido()));
			 retorno.setClandCortadosGrandeTelemedido(Util.somaInteiros(bean.getClandCortadosGrandeTelemedido(),bean2.getClandCortadosGrandeTelemedido()));
			 retorno.setClandSuprimidosGrandeTelemedido(Util.somaInteiros(bean.getClandSuprimidosGrandeTelemedido(),bean2.getClandSuprimidosGrandeTelemedido()));
			 retorno.setContasEmitidasGrandeTelemedido(Util.somaInteiros(bean.getContasEmitidasGrandeTelemedido(),bean2.getContasEmitidasGrandeTelemedido()));
			 retorno.setLeitEfetuadasGrandeTelemedido(Util.somaInteiros(bean.getLeitEfetuadasGrandeTelemedido(),bean2.getLeitEfetuadasGrandeTelemedido()));
			 retorno.setLeitAnormalidadesGrandeTelemedido(Util.somaInteiros(bean.getLeitAnormalidadesGrandeTelemedido(),bean2.getLeitAnormalidadesGrandeTelemedido()));
			 retorno.setAvisoCorteGrandeTelemedido(Util.somaInteiros(bean.getAvisoCorteGrandeTelemedido(),bean2.getAvisoCorteGrandeTelemedido()));
			 retorno.setPercAnormalidadeGrandeTelemedido(Util.somaBigDecimal(bean.getPercAnormalidadeGrandeTelemedido(),  bean2.getPercAnormalidadeGrandeTelemedido()));
			 retorno.setPercHidrometracaoGrandeTelemedido(Util.somaBigDecimal(bean.getPercHidrometracaoGrandeTelemedido(),  bean2.getPercHidrometracaoGrandeTelemedido()));
			 retorno.setLigImplantadasAguaGrandeTelemedido(Util.somaInteiros(bean.getLigImplantadasAguaGrandeTelemedido(),bean2.getLigImplantadasAguaGrandeTelemedido()));
			 retorno.setLigImplantadasEsgotoGrandeTelemedido(Util.somaInteiros(bean.getLigImplantadasEsgotoGrandeTelemedido(),bean2.getLigImplantadasEsgotoGrandeTelemedido()));
			 retorno.setRaPendentesComPrazoGrandeTelemedido(Util.somaInteiros(bean.getRaPendentesComPrazoGrandeTelemedido(),bean2.getRaPendentesComPrazoGrandeTelemedido()));
			 retorno.setRaPendentesComForaPrazoGrandeTelemedido(Util.somaInteiros(bean.getRaPendentesComForaPrazoGrandeTelemedido(),bean2.getRaPendentesComForaPrazoGrandeTelemedido()));
			 retorno.setRaPendentesOpPrazoGrandeTelemedido(Util.somaInteiros(bean.getRaPendentesOpPrazoGrandeTelemedido(),bean2.getRaPendentesOpPrazoGrandeTelemedido()));
			 retorno.setRaPendentesOpForaPrazoGrandeTelemedido(Util.somaInteiros(bean.getRaPendentesOpForaPrazoGrandeTelemedido(),bean2.getRaPendentesOpForaPrazoGrandeTelemedido()));
			 retorno.setFaturaRevisaoGrandeTelemedido(Util.somaInteiros(bean.getFaturaRevisaoGrandeTelemedido(),bean2.getFaturaRevisaoGrandeTelemedido()));
			 retorno.setCartasNegativacaoGrandeTelemedido(Util.somaInteiros(bean.getCartasNegativacaoGrandeTelemedido(),bean2.getCartasNegativacaoGrandeTelemedido()));
			 retorno.setEconomiaAtivaGrandeTelemedido(Util.somaInteiros(bean.getEconomiaAtivaGrandeTelemedido(),bean2.getEconomiaAtivaGrandeTelemedido()));
			 retorno.setEconomiaInativaGrandeTelemedido(Util.somaInteiros(bean.getEconomiaInativaGrandeTelemedido(),bean2.getEconomiaInativaGrandeTelemedido()));
			 retorno.setLigacoesAtivasGrandeTelemedido(Util.somaInteiros(bean.getLigacoesAtivasGrandeTelemedido(),bean2.getLigacoesAtivasGrandeTelemedido()));
			 retorno.setLigacoesInativasGrandeTelemedido(Util.somaInteiros(bean.getLigacoesInativasGrandeTelemedido(),bean2.getLigacoesInativasGrandeTelemedido()));
			 retorno.setRegAnormalidadeInformadaGrandeTelemedido(Util.somaInteiros(bean.getRegAnormalidadeInformadaGrandeTelemedido(),bean2.getRegAnormalidadeInformadaGrandeTelemedido()));
			 retorno.setRegRecebidosGrandeTelemedido(Util.somaInteiros(bean.getRegRecebidosGrandeTelemedido(),bean2.getRegRecebidosGrandeTelemedido()));
			 retorno.setLigAtivasHidrometroGrandeTelemedido(Util.somaInteiros(bean.getLigAtivasHidrometroGrandeTelemedido(),bean2.getLigAtivasHidrometroGrandeTelemedido()));
			 retorno.setLigAtivasGrandeTelemedido(Util.somaInteiros(bean.getLigAtivasGrandeTelemedido(),bean2.getLigAtivasGrandeTelemedido()));
			
			 retorno.setFaturaRevisaoReclamacaoConsumoGrandeTelemedido(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoGrandeTelemedido(),bean2.getFaturaRevisaoReclamacaoConsumoGrandeTelemedido()));
			 retorno.setFaturaRevisaoRecorrenciaGrandeTelemedido(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaGrandeTelemedido(),bean2.getFaturaRevisaoRecorrenciaGrandeTelemedido()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(),bean2.getFaturaRevisaoFaturamentoIndevidoGrandeTelemedido()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido(),bean2.getQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido()));
			 
			 retorno.setAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido(),bean2.getAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido(),bean2.getAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido()));
			 retorno.setAlteracaoCategoriaGrandeTelemedido(Util.somaInteiros(bean.getAlteracaoCategoriaGrandeTelemedido(),bean2.getAlteracaoCategoriaGrandeTelemedido()));
			 retorno.setInclusaoTarifaSocialGrandeTelemedido(Util.somaInteiros(bean.getInclusaoTarifaSocialGrandeTelemedido(),bean2.getInclusaoTarifaSocialGrandeTelemedido()));
			 retorno.setExclusaoTarifaSocialGrandeTelemedido(Util.somaInteiros(bean.getExclusaoTarifaSocialGrandeTelemedido(),bean2.getExclusaoTarifaSocialGrandeTelemedido()));
			 retorno.setFaturamentoSuspensoGrandeTelemedido(Util.somaInteiros(bean.getFaturamentoSuspensoGrandeTelemedido(),bean2.getFaturamentoSuspensoGrandeTelemedido()));
			 
			 
			 retorno.setHidSubstituidosRamalCorpTelemedido(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalCorpTelemedido(),bean.getHidSubstituidosRamalCorpTelemedido()));
			retorno.setHidSubstituidosPocoCorpTelemedido(
					Util.somaInteiros(bean2.getHidSubstituidosPocoCorpTelemedido(),bean.getHidSubstituidosPocoCorpTelemedido()));
			retorno.setHidInstaladosRamalCorpTelemedido(Util.somaInteiros(bean.getHidInstaladosRamalCorpTelemedido(),bean2.getHidInstaladosRamalCorpTelemedido()));
			retorno.setHidInstaladosPocoCorpTelemedido(Util.somaInteiros(bean.getHidInstaladosPocoCorpTelemedido(),bean2.getHidInstaladosPocoCorpTelemedido()));
			 retorno.setCortesExecutadosCorpTelemedido(Util.somaInteiros(bean.getCortesExecutadosCorpTelemedido(),bean2.getCortesExecutadosCorpTelemedido()));
			 retorno.setSuprExecutadasCorpTelemedido(Util.somaInteiros(bean.getSuprExecutadasCorpTelemedido(),bean2.getSuprExecutadasCorpTelemedido()));
			 retorno.setReligacoesCorpTelemedido(Util.somaInteiros(bean.getReligacoesCorpTelemedido(),bean2.getReligacoesCorpTelemedido()));
			 retorno.setReestabCorpTelemedido(Util.somaInteiros(bean.getReestabCorpTelemedido(),bean2.getReestabCorpTelemedido()));
			 retorno.setClandCortadosCorpTelemedido(Util.somaInteiros(bean.getClandCortadosCorpTelemedido(),bean2.getClandCortadosCorpTelemedido()));
			 retorno.setClandSuprimidosCorpTelemedido(Util.somaInteiros(bean.getClandSuprimidosCorpTelemedido(),bean2.getClandSuprimidosCorpTelemedido()));
			 retorno.setContasEmitidasCorpTelemedido(Util.somaInteiros(bean.getContasEmitidasCorpTelemedido(),bean2.getContasEmitidasCorpTelemedido()));
			 retorno.setLeitEfetuadasCorpTelemedido(Util.somaInteiros(bean.getLeitEfetuadasCorpTelemedido(),bean2.getLeitEfetuadasCorpTelemedido()));
			 retorno.setLeitAnormalidadesCorpTelemedido(Util.somaInteiros(bean.getLeitAnormalidadesCorpTelemedido(),bean2.getLeitAnormalidadesCorpTelemedido()));
			 retorno.setAvisoCorteCorpTelemedido(Util.somaInteiros(bean.getAvisoCorteCorpTelemedido(),bean2.getAvisoCorteCorpTelemedido()));
			 retorno.setPercAnormalidadeCorpTelemedido(Util.somaBigDecimal(bean.getPercAnormalidadeCorpTelemedido(),  bean2.getPercAnormalidadeCorpTelemedido()));
			 retorno.setPercHidrometracaoCorpTelemedido(Util.somaBigDecimal(bean.getPercHidrometracaoCorpTelemedido(),  bean2.getPercHidrometracaoCorpTelemedido()));
			 retorno.setLigImplantadasAguaCorpTelemedido(Util.somaInteiros(bean.getLigImplantadasAguaCorpTelemedido(),bean2.getLigImplantadasAguaCorpTelemedido()));
			 retorno.setLigImplantadasEsgotoCorpTelemedido(Util.somaInteiros(bean.getLigImplantadasEsgotoCorpTelemedido(),bean2.getLigImplantadasEsgotoCorpTelemedido()));
			 retorno.setRaPendentesComPrazoCorpTelemedido(Util.somaInteiros(bean.getRaPendentesComPrazoCorpTelemedido(),bean2.getRaPendentesComPrazoCorpTelemedido()));
			 retorno.setRaPendentesComForaPrazoCorpTelemedido(Util.somaInteiros(bean.getRaPendentesComForaPrazoCorpTelemedido(),bean2.getRaPendentesComForaPrazoCorpTelemedido()));
			 retorno.setRaPendentesOpPrazoCorpTelemedido(Util.somaInteiros(bean.getRaPendentesOpPrazoCorpTelemedido(),bean2.getRaPendentesOpPrazoCorpTelemedido()));
			 retorno.setRaPendentesOpForaPrazoCorpTelemedido(Util.somaInteiros(bean.getRaPendentesOpForaPrazoCorpTelemedido(),bean2.getRaPendentesOpForaPrazoCorpTelemedido()));
			 retorno.setFaturaRevisaoCorpTelemedido(Util.somaInteiros(bean.getFaturaRevisaoCorpTelemedido(),bean2.getFaturaRevisaoCorpTelemedido()));
			 retorno.setCartasNegativacaoCorpTelemedido(Util.somaInteiros(bean.getCartasNegativacaoCorpTelemedido(),bean2.getCartasNegativacaoCorpTelemedido()));
			 retorno.setEconomiaAtivaCorpTelemedido(Util.somaInteiros(bean.getEconomiaAtivaCorpTelemedido(),bean2.getEconomiaAtivaCorpTelemedido()));
			 retorno.setEconomiaInativaCorpTelemedido(Util.somaInteiros(bean.getEconomiaInativaCorpTelemedido(),bean2.getEconomiaInativaCorpTelemedido()));
			 retorno.setLigacoesAtivasCorpTelemedido(Util.somaInteiros(bean.getLigacoesAtivasCorpTelemedido(),bean2.getLigacoesAtivasCorpTelemedido()));
			 retorno.setLigacoesInativasCorpTelemedido(Util.somaInteiros(bean.getLigacoesInativasCorpTelemedido(),bean2.getLigacoesInativasCorpTelemedido()));
			 retorno.setRegAnormalidadeInformadaCorpTelemedido(Util.somaInteiros(bean.getRegAnormalidadeInformadaCorpTelemedido(),bean2.getRegAnormalidadeInformadaCorpTelemedido()));
			 retorno.setRegRecebidosCorpTelemedido(Util.somaInteiros(bean.getRegRecebidosCorpTelemedido(),bean2.getRegRecebidosCorpTelemedido()));
			 retorno.setLigAtivasHidrometroCorpTelemedido(Util.somaInteiros(bean.getLigAtivasHidrometroCorpTelemedido(),bean2.getLigAtivasHidrometroCorpTelemedido()));
			 retorno.setLigAtivasCorpTelemedido(Util.somaInteiros(bean.getLigAtivasCorpTelemedido(),bean2.getLigAtivasCorpTelemedido()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoCorpTelemedido(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoCorpTelemedido(),bean2.getFaturaRevisaoReclamacaoConsumoCorpTelemedido()));
			 retorno.setFaturaRevisaoRecorrenciaCorpTelemedido(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaCorpTelemedido(),bean2.getFaturaRevisaoRecorrenciaCorpTelemedido()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoCorpTelemedido(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoCorpTelemedido(),bean2.getFaturaRevisaoFaturamentoIndevidoCorpTelemedido()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido(),bean2.getQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido()));
			 
			 retorno.setAlteracaoNumeroEconomiasAcrescidoCorpTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoCorpTelemedido(),bean2.getAlteracaoNumeroEconomiasAcrescidoCorpTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoCorpTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoCorpTelemedido(),bean2.getAlteracaoNumeroEconomiasDecrescidoCorpTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido()));
			 retorno.setAlteracaoCategoriaCorpTelemedido(Util.somaInteiros(bean.getAlteracaoCategoriaCorpTelemedido(),bean2.getAlteracaoCategoriaCorpTelemedido()));
			 retorno.setInclusaoTarifaSocialCorpTelemedido(Util.somaInteiros(bean.getInclusaoTarifaSocialCorpTelemedido(),bean2.getInclusaoTarifaSocialCorpTelemedido()));
			 retorno.setExclusaoTarifaSocialCorpTelemedido(Util.somaInteiros(bean.getExclusaoTarifaSocialCorpTelemedido(),bean2.getExclusaoTarifaSocialCorpTelemedido()));
			 retorno.setFaturamentoSuspensoCorpTelemedido(Util.somaInteiros(bean.getFaturamentoSuspensoCorpTelemedido(),bean2.getFaturamentoSuspensoCorpTelemedido()));
			 
			 retorno.setHidSubstituidosRamalChafariz(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalChafariz(),bean.getHidSubstituidosRamalChafariz()));
			retorno.setHidSubstituidosPocoChafariz(
					Util.somaInteiros(bean2.getHidSubstituidosPocoChafariz(),bean.getHidSubstituidosPocoChafariz()));
			retorno.setHidInstaladosRamalChafariz(Util.somaInteiros(bean.getHidInstaladosRamalChafariz(),bean2.getHidInstaladosRamalChafariz()));
			retorno.setHidInstaladosPocoChafariz(Util.somaInteiros(bean.getHidInstaladosPocoChafariz(),bean2.getHidInstaladosPocoChafariz()));
			 retorno.setCortesExecutadosChafariz(Util.somaInteiros(bean.getCortesExecutadosChafariz(),bean2.getCortesExecutadosChafariz()));
			 retorno.setSuprExecutadasChafariz(Util.somaInteiros(bean.getSuprExecutadasChafariz(),bean2.getSuprExecutadasChafariz()));
			 retorno.setReligacoesChafariz(Util.somaInteiros(bean.getReligacoesChafariz(),bean2.getReligacoesChafariz()));
			 retorno.setReestabChafariz(Util.somaInteiros(bean.getReestabChafariz(),bean2.getReestabChafariz()));
			 retorno.setClandCortadosChafariz(Util.somaInteiros(bean.getClandCortadosChafariz(),bean2.getClandCortadosChafariz()));
			 retorno.setClandSuprimidosChafariz(Util.somaInteiros(bean.getClandSuprimidosChafariz(),bean2.getClandSuprimidosChafariz()));
			 retorno.setContasEmitidasChafariz(Util.somaInteiros(bean.getContasEmitidasChafariz(),bean2.getContasEmitidasChafariz()));
			 retorno.setLeitEfetuadasChafariz(Util.somaInteiros(bean.getLeitEfetuadasChafariz(),bean2.getLeitEfetuadasChafariz()));
			 retorno.setLeitAnormalidadesChafariz(Util.somaInteiros(bean.getLeitAnormalidadesChafariz(),bean2.getLeitAnormalidadesChafariz()));
			 retorno.setAvisoCorteChafariz(Util.somaInteiros(bean.getAvisoCorteChafariz(),bean2.getAvisoCorteChafariz()));
			 retorno.setPercAnormalidadeChafariz(Util.somaBigDecimal(bean.getPercAnormalidadeChafariz(),  bean2.getPercAnormalidadeChafariz()));
			 retorno.setPercHidrometracaoChafariz(Util.somaBigDecimal(bean.getPercHidrometracaoChafariz(),  bean2.getPercHidrometracaoChafariz()));
			 retorno.setLigImplantadasAguaChafariz(Util.somaInteiros(bean.getLigImplantadasAguaChafariz(),bean2.getLigImplantadasAguaChafariz()));
			 retorno.setLigImplantadasEsgotoChafariz(Util.somaInteiros(bean.getLigImplantadasEsgotoChafariz(),bean2.getLigImplantadasEsgotoChafariz()));
			 retorno.setRaPendentesComPrazoChafariz(Util.somaInteiros(bean.getRaPendentesComPrazoChafariz(),bean2.getRaPendentesComPrazoChafariz()));
			 retorno.setRaPendentesComForaPrazoChafariz(Util.somaInteiros(bean.getRaPendentesComForaPrazoChafariz(),bean2.getRaPendentesComForaPrazoChafariz()));
			 retorno.setRaPendentesOpPrazoChafariz(Util.somaInteiros(bean.getRaPendentesOpPrazoChafariz(),bean2.getRaPendentesOpPrazoChafariz()));
			 retorno.setRaPendentesOpForaPrazoChafariz(Util.somaInteiros(bean.getRaPendentesOpForaPrazoChafariz(),bean2.getRaPendentesOpForaPrazoChafariz()));
			 retorno.setFaturaRevisaoChafariz(Util.somaInteiros(bean.getFaturaRevisaoChafariz(),bean2.getFaturaRevisaoChafariz()));
			 retorno.setCartasNegativacaoChafariz(Util.somaInteiros(bean.getCartasNegativacaoChafariz(),bean2.getCartasNegativacaoChafariz()));
			 retorno.setEconomiaAtivaChafariz(Util.somaInteiros(bean.getEconomiaAtivaChafariz(),bean2.getEconomiaAtivaChafariz()));
			 retorno.setEconomiaInativaChafariz(Util.somaInteiros(bean.getEconomiaInativaChafariz(),bean2.getEconomiaInativaChafariz()));
			 retorno.setLigacoesAtivasChafariz(Util.somaInteiros(bean.getLigacoesAtivasChafariz(),bean2.getLigacoesAtivasChafariz()));
			 retorno.setLigacoesInativasChafariz(Util.somaInteiros(bean.getLigacoesInativasChafariz(),bean2.getLigacoesInativasChafariz()));
			 retorno.setRegAnormalidadeInformadaChafariz(Util.somaInteiros(bean.getRegAnormalidadeInformadaChafariz(),bean2.getRegAnormalidadeInformadaChafariz()));
			 retorno.setRegRecebidosChafariz(Util.somaInteiros(bean.getRegRecebidosChafariz(),bean2.getRegRecebidosChafariz()));
			 retorno.setLigAtivasHidrometroChafariz(Util.somaInteiros(bean.getLigAtivasHidrometroChafariz(),bean2.getLigAtivasHidrometroChafariz()));
			 retorno.setLigAtivasChafariz(Util.somaInteiros(bean.getLigAtivasChafariz(),bean2.getLigAtivasChafariz()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoChafariz(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoChafariz(),bean2.getFaturaRevisaoReclamacaoConsumoChafariz()));
			 retorno.setFaturaRevisaoRecorrenciaChafariz(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaChafariz(),bean2.getFaturaRevisaoRecorrenciaChafariz()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoChafariz(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoChafariz(),bean2.getFaturaRevisaoFaturamentoIndevidoChafariz()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoChafariz(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoChafariz(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoChafariz()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaChafariz(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaChafariz(),bean2.getQtImoveisFaturaRevisaoRecorrenciaChafariz()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz()));
			
			 retorno.setAlteracaoNumeroEconomiasAcrescidoChafariz(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoChafariz(),bean2.getAlteracaoNumeroEconomiasAcrescidoChafariz()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoChafariz(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoChafariz(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoChafariz()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoChafariz(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoChafariz(),bean2.getAlteracaoNumeroEconomiasDecrescidoChafariz()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoChafariz(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoChafariz(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoChafariz()));
			 retorno.setAlteracaoCategoriaChafariz(Util.somaInteiros(bean.getAlteracaoCategoriaChafariz(),bean2.getAlteracaoCategoriaChafariz()));
			 retorno.setInclusaoTarifaSocialChafariz(Util.somaInteiros(bean.getInclusaoTarifaSocialChafariz(),bean2.getInclusaoTarifaSocialChafariz()));
			 retorno.setExclusaoTarifaSocialChafariz(Util.somaInteiros(bean.getExclusaoTarifaSocialChafariz(),bean2.getExclusaoTarifaSocialChafariz()));
			 retorno.setFaturamentoSuspensoChafariz(Util.somaInteiros(bean.getFaturamentoSuspensoChafariz(),bean2.getFaturamentoSuspensoChafariz()));
			 
			 
			 retorno.setHidSubstituidosRamalMicroTelemedido(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalMicroTelemedido(),bean.getHidSubstituidosRamalMicroTelemedido()));
			retorno.setHidSubstituidosPocoMicroTelemedido(
					Util.somaInteiros(bean2.getHidSubstituidosPocoMicroTelemedido(),bean.getHidSubstituidosPocoMicroTelemedido()));
			retorno.setHidInstaladosRamalMicroTelemedido(Util.somaInteiros(bean.getHidInstaladosRamalMicroTelemedido(),bean2.getHidInstaladosRamalMicroTelemedido()));
			retorno.setHidInstaladosPocoMicroTelemedido(Util.somaInteiros(bean.getHidInstaladosPocoMicroTelemedido(),bean2.getHidInstaladosPocoMicroTelemedido()));
			 retorno.setCortesExecutadosMicroTelemedido(Util.somaInteiros(bean.getCortesExecutadosMicroTelemedido(),bean2.getCortesExecutadosMicroTelemedido()));
			 retorno.setSuprExecutadasMicroTelemedido(Util.somaInteiros(bean.getSuprExecutadasMicroTelemedido(),bean2.getSuprExecutadasMicroTelemedido()));
			 retorno.setReligacoesMicroTelemedido(Util.somaInteiros(bean.getReligacoesMicroTelemedido(),bean2.getReligacoesMicroTelemedido()));
			 retorno.setReestabMicroTelemedido(Util.somaInteiros(bean.getReestabMicroTelemedido(),bean2.getReestabMicroTelemedido()));
			 retorno.setClandCortadosMicroTelemedido(Util.somaInteiros(bean.getClandCortadosMicroTelemedido(),bean2.getClandCortadosMicroTelemedido()));
			 retorno.setClandSuprimidosMicroTelemedido(Util.somaInteiros(bean.getClandSuprimidosMicroTelemedido(),bean2.getClandSuprimidosMicroTelemedido()));
			 retorno.setContasEmitidasMicroTelemedido(Util.somaInteiros(bean.getContasEmitidasMicroTelemedido(),bean2.getContasEmitidasMicroTelemedido()));
			 retorno.setLeitEfetuadasMicroTelemedido(Util.somaInteiros(bean.getLeitEfetuadasMicroTelemedido(),bean2.getLeitEfetuadasMicroTelemedido()));
			 retorno.setLeitAnormalidadesMicroTelemedido(Util.somaInteiros(bean.getLeitAnormalidadesMicroTelemedido(),bean2.getLeitAnormalidadesMicroTelemedido()));
			 retorno.setAvisoCorteMicroTelemedido(Util.somaInteiros(bean.getAvisoCorteMicroTelemedido(),bean2.getAvisoCorteMicroTelemedido()));
			 retorno.setPercAnormalidadeMicroTelemedido(Util.somaBigDecimal(bean.getPercAnormalidadeMicroTelemedido(),  bean2.getPercAnormalidadeMicroTelemedido()));
			 retorno.setPercHidrometracaoMicroTelemedido(Util.somaBigDecimal(bean.getPercHidrometracaoMicroTelemedido(),  bean2.getPercHidrometracaoMicroTelemedido()));
			 retorno.setLigImplantadasAguaMicroTelemedido(Util.somaInteiros(bean.getLigImplantadasAguaMicroTelemedido(),bean2.getLigImplantadasAguaMicroTelemedido()));
			 retorno.setLigImplantadasEsgotoMicroTelemedido(Util.somaInteiros(bean.getLigImplantadasEsgotoMicroTelemedido(),bean2.getLigImplantadasEsgotoMicroTelemedido()));
			 retorno.setRaPendentesComPrazoMicroTelemedido(Util.somaInteiros(bean.getRaPendentesComPrazoMicroTelemedido(),bean2.getRaPendentesComPrazoMicroTelemedido()));
			 retorno.setRaPendentesComForaPrazoMicroTelemedido(Util.somaInteiros(bean.getRaPendentesComForaPrazoMicroTelemedido(),bean2.getRaPendentesComForaPrazoMicroTelemedido()));
			 retorno.setRaPendentesOpPrazoMicroTelemedido(Util.somaInteiros(bean.getRaPendentesOpPrazoMicroTelemedido(),bean2.getRaPendentesOpPrazoMicroTelemedido()));
			 retorno.setRaPendentesOpForaPrazoMicroTelemedido(Util.somaInteiros(bean.getRaPendentesOpForaPrazoMicroTelemedido(),bean2.getRaPendentesOpForaPrazoMicroTelemedido()));
			 retorno.setFaturaRevisaoMicroTelemedido(Util.somaInteiros(bean.getFaturaRevisaoMicroTelemedido(),bean2.getFaturaRevisaoMicroTelemedido()));
			 retorno.setCartasNegativacaoMicroTelemedido(Util.somaInteiros(bean.getCartasNegativacaoMicroTelemedido(),bean2.getCartasNegativacaoMicroTelemedido()));
			 retorno.setEconomiaAtivaMicroTelemedido(Util.somaInteiros(bean.getEconomiaAtivaMicroTelemedido(),bean2.getEconomiaAtivaMicroTelemedido()));
			 retorno.setEconomiaInativaMicroTelemedido(Util.somaInteiros(bean.getEconomiaInativaMicroTelemedido(),bean2.getEconomiaInativaMicroTelemedido()));
			 retorno.setLigacoesAtivasMicroTelemedido(Util.somaInteiros(bean.getLigacoesAtivasMicroTelemedido(),bean2.getLigacoesAtivasMicroTelemedido()));
			 retorno.setLigacoesInativasMicroTelemedido(Util.somaInteiros(bean.getLigacoesInativasMicroTelemedido(),bean2.getLigacoesInativasMicroTelemedido()));
			 retorno.setRegAnormalidadeInformadaMicroTelemedido(Util.somaInteiros(bean.getRegAnormalidadeInformadaMicroTelemedido(),bean2.getRegAnormalidadeInformadaMicroTelemedido()));
			 retorno.setRegRecebidosMicroTelemedido(Util.somaInteiros(bean.getRegRecebidosMicroTelemedido(),bean2.getRegRecebidosMicroTelemedido()));
			 retorno.setLigAtivasHidrometroMicroTelemedido(Util.somaInteiros(bean.getLigAtivasHidrometroMicroTelemedido(),bean2.getLigAtivasHidrometroMicroTelemedido()));
			 retorno.setLigAtivasMicroTelemedido(Util.somaInteiros(bean.getLigAtivasMicroTelemedido(),bean2.getLigAtivasMicroTelemedido()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoMicroTelemedido(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoMicroTelemedido(),bean2.getFaturaRevisaoReclamacaoConsumoMicroTelemedido()));
			 retorno.setFaturaRevisaoRecorrenciaMicroTelemedido(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaMicroTelemedido(),bean2.getFaturaRevisaoRecorrenciaMicroTelemedido()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoMicroTelemedido(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoMicroTelemedido(),bean2.getFaturaRevisaoFaturamentoIndevidoMicroTelemedido()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido(),bean2.getQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido()));
			 
			 retorno.setAlteracaoNumeroEconomiasAcrescidoMicroTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoMicroTelemedido(),bean2.getAlteracaoNumeroEconomiasAcrescidoMicroTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoMicroTelemedido(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoMicroTelemedido(),bean2.getAlteracaoNumeroEconomiasDecrescidoMicroTelemedido()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido()));
			 retorno.setAlteracaoCategoriaMicroTelemedido(Util.somaInteiros(bean.getAlteracaoCategoriaMicroTelemedido(),bean2.getAlteracaoCategoriaMicroTelemedido()));
			 retorno.setInclusaoTarifaSocialMicroTelemedido(Util.somaInteiros(bean.getInclusaoTarifaSocialMicroTelemedido(),bean2.getInclusaoTarifaSocialMicroTelemedido()));
			 retorno.setExclusaoTarifaSocialMicroTelemedido(Util.somaInteiros(bean.getExclusaoTarifaSocialMicroTelemedido(),bean2.getExclusaoTarifaSocialMicroTelemedido()));
			 retorno.setFaturamentoSuspensoMicroTelemedido(Util.somaInteiros(bean.getFaturamentoSuspensoMicroTelemedido(),bean2.getFaturamentoSuspensoMicroTelemedido()));
			 
			 
			 retorno.setHidSubstituidosRamalCadastroProvisorio(
					 Util.somaInteiros(bean2.getHidSubstituidosRamalCadastroProvisorio(),bean.getHidSubstituidosRamalCadastroProvisorio()));
			retorno.setHidSubstituidosPocoCadastroProvisorio(
					Util.somaInteiros(bean2.getHidSubstituidosPocoCadastroProvisorio(),bean.getHidSubstituidosPocoCadastroProvisorio()));
			retorno.setHidInstaladosRamalCadastroProvisorio(Util.somaInteiros(bean.getHidInstaladosRamalCadastroProvisorio(),bean2.getHidInstaladosRamalCadastroProvisorio()));
			retorno.setHidInstaladosPocoCadastroProvisorio(Util.somaInteiros(bean.getHidInstaladosPocoCadastroProvisorio(),bean2.getHidInstaladosPocoCadastroProvisorio()));
			 retorno.setCortesExecutadosCadastroProvisorio(Util.somaInteiros(bean.getCortesExecutadosCadastroProvisorio(),bean2.getCortesExecutadosCadastroProvisorio()));
			 retorno.setSuprExecutadasCadastroProvisorio(Util.somaInteiros(bean.getSuprExecutadasCadastroProvisorio(),bean2.getSuprExecutadasCadastroProvisorio()));
			 retorno.setReligacoesCadastroProvisorio(Util.somaInteiros(bean.getReligacoesCadastroProvisorio(),bean2.getReligacoesCadastroProvisorio()));
			 retorno.setReestabCadastroProvisorio(Util.somaInteiros(bean.getReestabCadastroProvisorio(),bean2.getReestabCadastroProvisorio()));
			 retorno.setClandCortadosCadastroProvisorio(Util.somaInteiros(bean.getClandCortadosCadastroProvisorio(),bean2.getClandCortadosCadastroProvisorio()));
			 retorno.setClandSuprimidosCadastroProvisorio(Util.somaInteiros(bean.getClandSuprimidosCadastroProvisorio(),bean2.getClandSuprimidosCadastroProvisorio()));
			 retorno.setContasEmitidasCadastroProvisorio(Util.somaInteiros(bean.getContasEmitidasCadastroProvisorio(),bean2.getContasEmitidasCadastroProvisorio()));
			 retorno.setLeitEfetuadasCadastroProvisorio(Util.somaInteiros(bean.getLeitEfetuadasCadastroProvisorio(),bean2.getLeitEfetuadasCadastroProvisorio()));
			 retorno.setLeitAnormalidadesCadastroProvisorio(Util.somaInteiros(bean.getLeitAnormalidadesCadastroProvisorio(),bean2.getLeitAnormalidadesCadastroProvisorio()));
			 retorno.setAvisoCorteCadastroProvisorio(Util.somaInteiros(bean.getAvisoCorteCadastroProvisorio(),bean2.getAvisoCorteCadastroProvisorio()));
			 retorno.setPercAnormalidadeCadastroProvisorio(Util.somaBigDecimal(bean.getPercAnormalidadeCadastroProvisorio(),  bean2.getPercAnormalidadeCadastroProvisorio()));
			 retorno.setPercHidrometracaoCadastroProvisorio(Util.somaBigDecimal(bean.getPercHidrometracaoCadastroProvisorio(),  bean2.getPercHidrometracaoCadastroProvisorio()));
			 retorno.setLigImplantadasAguaCadastroProvisorio(Util.somaInteiros(bean.getLigImplantadasAguaCadastroProvisorio(),bean2.getLigImplantadasAguaCadastroProvisorio()));
			 retorno.setLigImplantadasEsgotoCadastroProvisorio(Util.somaInteiros(bean.getLigImplantadasEsgotoCadastroProvisorio(),bean2.getLigImplantadasEsgotoCadastroProvisorio()));
			 retorno.setRaPendentesComPrazoCadastroProvisorio(Util.somaInteiros(bean.getRaPendentesComPrazoCadastroProvisorio(),bean2.getRaPendentesComPrazoCadastroProvisorio()));
			 retorno.setRaPendentesComForaPrazoCadastroProvisorio(Util.somaInteiros(bean.getRaPendentesComForaPrazoCadastroProvisorio(),bean2.getRaPendentesComForaPrazoCadastroProvisorio()));
			 retorno.setRaPendentesOpPrazoCadastroProvisorio(Util.somaInteiros(bean.getRaPendentesOpPrazoCadastroProvisorio(),bean2.getRaPendentesOpPrazoCadastroProvisorio()));
			 retorno.setRaPendentesOpForaPrazoCadastroProvisorio(Util.somaInteiros(bean.getRaPendentesOpForaPrazoCadastroProvisorio(),bean2.getRaPendentesOpForaPrazoCadastroProvisorio()));
			 retorno.setFaturaRevisaoCadastroProvisorio(Util.somaInteiros(bean.getFaturaRevisaoCadastroProvisorio(),bean2.getFaturaRevisaoCadastroProvisorio()));
			 retorno.setCartasNegativacaoCadastroProvisorio(Util.somaInteiros(bean.getCartasNegativacaoCadastroProvisorio(),bean2.getCartasNegativacaoCadastroProvisorio()));
			 retorno.setEconomiaAtivaCadastroProvisorio(Util.somaInteiros(bean.getEconomiaAtivaCadastroProvisorio(),bean2.getEconomiaAtivaCadastroProvisorio()));
			 retorno.setEconomiaInativaCadastroProvisorio(Util.somaInteiros(bean.getEconomiaInativaCadastroProvisorio(),bean2.getEconomiaInativaCadastroProvisorio()));
			 retorno.setLigacoesAtivasCadastroProvisorio(Util.somaInteiros(bean.getLigacoesAtivasCadastroProvisorio(),bean2.getLigacoesAtivasCadastroProvisorio()));
			 retorno.setLigacoesInativasCadastroProvisorio(Util.somaInteiros(bean.getLigacoesInativasCadastroProvisorio(),bean2.getLigacoesInativasCadastroProvisorio()));
			 retorno.setRegAnormalidadeInformadaCadastroProvisorio(Util.somaInteiros(bean.getRegAnormalidadeInformadaCadastroProvisorio(),bean2.getRegAnormalidadeInformadaCadastroProvisorio()));
			 retorno.setRegRecebidosCadastroProvisorio(Util.somaInteiros(bean.getRegRecebidosCadastroProvisorio(),bean2.getRegRecebidosCadastroProvisorio()));
			 retorno.setLigAtivasHidrometroCadastroProvisorio(Util.somaInteiros(bean.getLigAtivasHidrometroCadastroProvisorio(),bean2.getLigAtivasHidrometroCadastroProvisorio()));
			 retorno.setLigAtivasCadastroProvisorio(Util.somaInteiros(bean.getLigAtivasCadastroProvisorio(),bean2.getLigAtivasCadastroProvisorio()));
			 
			 retorno.setFaturaRevisaoReclamacaoConsumoCadastroProvisorio(Util.somaInteiros(bean.getFaturaRevisaoReclamacaoConsumoCadastroProvisorio(),bean2.getFaturaRevisaoReclamacaoConsumoCadastroProvisorio()));
			 retorno.setFaturaRevisaoRecorrenciaCadastroProvisorio(Util.somaInteiros(bean.getFaturaRevisaoRecorrenciaCadastroProvisorio(),bean2.getFaturaRevisaoRecorrenciaCadastroProvisorio()));
			 retorno.setFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(Util.somaInteiros(bean.getFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(),bean2.getFaturaRevisaoFaturamentoIndevidoCadastroProvisorio()));
			 
			 retorno.setQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio(),bean2.getQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio()));
			 retorno.setQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio(),bean2.getQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio()));
			 retorno.setQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(Util.somaInteiros(bean.getQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(),bean2.getQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio()));
			
			 retorno.setAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio(),bean2.getAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio()));
			 retorno.setAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio(),bean2.getAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio()));
			 retorno.setAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio(Util.somaInteiros(bean.getAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio(),bean2.getAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio()));
			 retorno.setAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio(Util.somaInteiros(bean.getAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio(),bean2.getAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio()));
			 retorno.setAlteracaoCategoriaCadastroProvisorio(Util.somaInteiros(bean.getAlteracaoCategoriaCadastroProvisorio(),bean2.getAlteracaoCategoriaCadastroProvisorio()));
			 retorno.setInclusaoTarifaSocialCadastroProvisorio(Util.somaInteiros(bean.getInclusaoTarifaSocialCadastroProvisorio(),bean2.getInclusaoTarifaSocialCadastroProvisorio()));
			 retorno.setExclusaoTarifaSocialCadastroProvisorio(Util.somaInteiros(bean.getExclusaoTarifaSocialCadastroProvisorio(),bean2.getExclusaoTarifaSocialCadastroProvisorio()));
			 retorno.setFaturamentoSuspensoCadastroProvisorio(Util.somaInteiros(bean.getFaturamentoSuspensoCadastroProvisorio(),bean2.getFaturamentoSuspensoCadastroProvisorio()));
	
		
		return retorno;
		
	}
	
	
	public void agendarTarefaBatch() {
		
		//AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoBoletimMedicao", this);
	}
}