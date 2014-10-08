package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.ComandoOSConexaoEsgoto;
import gcom.batch.Processo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarOrdemServicoConexaoEsgotoAction extends GcomAction{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		String descricaoComando = form.getDescricaoComando();
		String indicadorExecucao = form.getIndicadorExecucao();
		
		String idImovel = form.getIdImovel();
		String idMunicipio = form.getIdMunicipio();
		String idLogradouro = form.getIdLogradouro();
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idSetorComercialInicial = form.getIdSetorComercialInicial();
		String codSetorComercialInicial = form.getCodSetorComercialInicial();
		String quadraInicial = form.getQuadraInicial();
		String rotaInicial = form.getRotaInicial();
		String rotaSequencialInicial = form.getRotaSequenciaInicial();
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String idSetorComercialFinal = form.getIdSetorComercialFinal();
		String codSetorComercialFinal = form.getCodSetorComercialFinal();
		String quadraFinal = form.getQuadraFinal();
		String rotaFinal = form.getRotaFinal();
		String rotaSequencialFinal = form.getRotaSequenciaFinal();
		
		String dataLigacao = form.getDataLigacao();
		String diametroLigacao = form.getIdDiametroLigacao();
		String materialLigacao = form.getIdMaterialLigacao();
		String perfilLigacao = form.getIdPerfilLigacao();
		String percentualColeta = form.getPercentualColeta();
		String percentualEsgoto = form.getPercentualEsgoto();
		String ligacaoOrigem = form.getIdLigacaoOrigem();
		String indicadorCaixaGordura = form.getIndicadorCaixaGordura();
		String indicadorLigacao = form.getIndicadorLigacao();
		String condicaoEsgotamento = form.getIdCondicaoEsgotamento();
		String situacaoCaixaInspecao = form.getIdSituacaoCaixaInspecao();
		String destinoDejetos = form.getIdDestinoDejetos();
		String destinoAguasPluviais = form.getIdDestinoAguasPluviais();
		
		String investimento = form.getInvestimento();
		String populacao = form.getPopulacao();
		String indicadorEncerramentoAutomatico = form.getIndicadorEncerramentoAutomatico();
		String parecerEncerramento = form.getParecerEncerramento();
		
		String grupoFaturamento = form.getIdGrupoFaturamento();
		
		String incadorPermissaoEspecialEncerramentoAutomatico = form.getIndicadorPermissaoEspecialEncerramentoAutomatico();
		
		Rota objRotaInicial = null;
		Rota objRotaFinal = null;
		Quadra objQuadraInicial = null;
		Quadra objQuadraFinal = null;
		boolean parametrosObrigatorios = false;
		
		BigDecimal percentualColetaBigDecimal = Util.formatarMoedaRealparaBigDecimalComErro(percentualColeta);
		BigDecimal percentualEsgotoBigDecimal = Util.formatarMoedaRealparaBigDecimalComErro(percentualEsgoto);
		
		
		//Validando os campos
		//========================================================================
		
		//1.2 Descrição do Comando
		if(descricaoComando == null || descricaoComando.trim().trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Descrição do Comando");
		}
		
		//1.4 Imóvel
		if(idImovel != null && !idImovel.trim().equals("")){
			
			parametrosObrigatorios = true;
			
			//1.4.2 [FE0001] Verificar Existência de Imóvel
			//---------------------------------------------------------------------------------------------------------
			Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(idImovel));
			if(imovel != null){
				//Caso a situação da ligação de esgoto não seja factível ou potencial
				if(imovel.getLigacaoEsgotoSituacao() == null || (
				   imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.FACTIVEL) != 0 && 
				   imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.POTENCIAL) != 0)){
					//exibir a mensagem "A situação da ligação de esgoto deve ser Potencial ou Factível"
					throw new ActionServletException("atencao.situacao_esgoto_potencial_factivel");
				}
				
				//Caso o imóvel esteja excluído
				if(imovel.getIndicadorExclusao().compareTo(ConstantesSistema.SIM) == 0){
					
					//exibir a mensagem "Imóvel <<IMOV_ID>> excluído."
					throw new ActionServletException("atencao.pesquisa.imovel.matricula.excluido",null,imovel.getId().toString());
				}
				
				/*//Caso o imóvel não possua perfil com geração de dados para leitura
				if(imovel.getImovelPerfil() == null || (
						imovel.getImovelPerfil().getIndicadorGerarDadosLeitura().compareTo(ConstantesSistema.NAO) == 0)){
					
					//exibir a mensagem "Imóvel <<IMOV_ID>> com perfil << IPER_DSIMOVELPERFIL>> não gera dados para leitura."
					throw new ActionServletException("atencao.imovel_nao_gera_dados_leitura", 
															imovel.getId().toString(),
															imovel.getImovelPerfil().getDescricao());
					
				}*/
				if(grupoFaturamento != null && !grupoFaturamento.equalsIgnoreCase("-1")){
					boolean indicadorMedicaoTelemedido = this.getFachada().verificarIndicadorTelemedido(imovel.getId());
					if(indicadorMedicaoTelemedido){
						throw new ActionServletException(
								"atencao.imovel_telemedido", null, imovel
										.getId().toString());
					}
				}
				
				// Caso o imovel possua ordem de servido de conexao de esgoto pendente
				Integer idOrdemServico = this.getFachada().pesquisarOSConexaoEsgotoPendente(imovel.getId());
				if(idOrdemServico !=null){
					throw new ActionServletException("atencao.imovel_com_os_conexao_esgoto_pendente", 
							imovel.getId().toString(),idOrdemServico.toString());
				}
				
				
				
				// Caso a situacao de ligacao de agua do imovel seja potencial ou
				// factivel
				if (imovel.getLigacaoAguaSituacao() != null
						&& (imovel.getLigacaoAguaSituacao().getId().toString()
								.equals(LigacaoAguaSituacao.POTENCIAL.toString()) || imovel
								.getLigacaoAguaSituacao().getId().toString()
								.equals(LigacaoAguaSituacao.FACTIVEL.toString()))) {

					throw new ActionServletException(
							"atencao.situacao_agua_potencial_factivel", imovel
									.getId().toString(), imovel
									.getLigacaoAguaSituacao().getDescricao());
				}

				// Caso a categoria do imovel seja publica
				if (imovel.getCategoriaPrincipalId().toString()
						.equals(Categoria.PUBLICO.toString())) {

					throw new ActionServletException(
							"atencao.imovel_categoria_publico", null, imovel
									.getId().toString());
				}
				
			}
			
			else{
				throw new ActionServletException("atencao.label_inexistente",null,"Imóvel");
			}
			//---------------------------------------------------------------------------------------------------------
		}
		else{
			
			//1.5 Município
			if(idMunicipio != null && !idMunicipio.trim().equals("")){
				
				parametrosObrigatorios = true;
				
				Municipio mun = this.getFachada().pesquisarMunicipio(Integer.parseInt(idMunicipio));
				if(mun == null){
					throw new ActionServletException("atencao.label_inexistente",null,"Município");
				}
			}
			/*else if((idLocalidadeInicial == null || idLocalidadeInicial.trim().equals("")) && 
					(grupoFaturamento == null || grupoFaturamento.equals("-1"))){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Município");
			}*/
			
			//1.6 Logradouro
			if(idLogradouro != null && !idLogradouro.trim().equals("")){
				FiltroLogradouro filtro = new FiltroLogradouro();
				filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, idLogradouro));
				filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID_MUNICIPIO, idMunicipio));
				filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<Logradouro> colecaoLog = this.getFachada().pesquisar(filtro,Logradouro.class.getName());
				if(colecaoLog == null || colecaoLog.size() != 1){
					throw new ActionServletException("atencao.label_inexistente",null,"Logradouro");
				}
			}
			
			//1.7 Inscrição Inicial
			//-----------------------------------------------------------------------------------------------------------
			//1.7.1 Localidade
			if(idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")){
				
				//Informou os parâmetros iniciais, mas não os finais
				if(idLocalidadeFinal == null || idLocalidadeFinal.trim().equals("")){
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Localidade Final");
				}
				
				parametrosObrigatorios = true;
				
				Localidade loc = this.getFachada().pesquisarLocalidade(Integer.parseInt(idLocalidadeInicial));
				if(loc == null){
					throw new ActionServletException("atencao.label_inexistente",null,"Localidade Inicial");
				}
				
				//1.7.6 Setor Comercial
				if(codSetorComercialInicial != null && !codSetorComercialInicial.trim().equals("")){
					
					//Informou os parâmetros iniciais, mas não os finais
					if(codSetorComercialFinal == null || codSetorComercialFinal.trim().equals("")){
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Setor Comercial Final");
					}
					
					SetorComercial stcm = this.getFachada().obterSetorComercialLocalidade(idLocalidadeInicial,codSetorComercialInicial);
					if(stcm == null){
						throw new ActionServletException("atencao.label_inexistente",null,"Setor Comercial Inicial");
					}
					
					//1.7.11 Quadra
					if(quadraInicial != null && !quadraInicial.trim().equals("")){
						
						//Informou os parâmetros iniciais, mas não os finais
						if(quadraFinal == null || quadraFinal.trim().equals("")){
							throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Quadra Final");
						}
						
						Quadra quadra = this.getFachada().obterQuadraSetorComercial(Integer.parseInt(idSetorComercialInicial), Integer.parseInt(quadraInicial));
						if(quadra == null){
							throw new ActionServletException("atencao.label_inexistente",null,"Quadra Inicial");
						}else{
							objQuadraInicial = quadra;
							
						}
						//1.7.14 Rota
						if(rotaInicial != null && !rotaInicial.trim().equals("")){
							
							//Informou os parâmetros iniciais, mas não os finais
							if(rotaFinal == null || rotaFinal.trim().equals("")){
								throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Rota Final");
							}
							
							FiltroRota filtroRota = new FiltroRota();
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, rotaInicial));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, quadra.getRota().getId()));
							Collection colecaoRota = (Collection)this.getFachada().pesquisar(filtroRota, Rota.class.getName());
							if(colecaoRota == null || colecaoRota.size() == 0){
								throw new ActionServletException("atencao.label_inexistente",null,"Rota Inicial informada");
							}
							else{
								objRotaInicial = (Rota)Util.retonarObjetoDeColecao(colecaoRota);							
							}
						}
					}
				}
			}
			
			//[FE0009] - Validar parâmetros de pesquisa
			if(!parametrosObrigatorios){
				throw new ActionServletException("atencao.informe_imov_mun_inscricao");
			}
			
			//-----------------------------------------------------------------------------------------------------------
			
			
			
			
			
			//1.8 Inscrição Final
			//-----------------------------------------------------------------------------------------------------------
			//1.8.1 Localidade
			if(idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")){
				Localidade loc = this.getFachada().pesquisarLocalidade(Integer.parseInt(idLocalidadeFinal));
				if(loc == null){
					throw new ActionServletException("atencao.label_inexistente",null,"Localidade Final");
				}
				
				//1.7.6 Setor Comercial
				if(codSetorComercialFinal != null && !codSetorComercialFinal.trim().equals("")){
					SetorComercial stcm = this.getFachada().obterSetorComercialLocalidade(idLocalidadeFinal,codSetorComercialFinal);
					if(stcm == null){
						throw new ActionServletException("atencao.label_inexistente",null,"Setor Comercial Final");
					}
					
					//1.7.11 Quadra
					if(quadraFinal != null && !quadraFinal.trim().equals("")){
						Quadra quadra = this.getFachada().obterQuadraSetorComercial(Integer.parseInt(idSetorComercialFinal), Integer.parseInt(quadraFinal));
						if(quadra == null){
							throw new ActionServletException("atencao.label_inexistente",null,"Quadra Final");
						}else{
							objQuadraFinal = quadra;
						}
						//1.7.14 Rota
						if(rotaFinal != null && !rotaFinal.trim().equals("")){
							FiltroRota filtroRota = new FiltroRota();
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, rotaFinal));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, quadra.getRota().getId()));
							Collection colecaoRota = (Collection)this.getFachada().pesquisar(filtroRota, Rota.class.getName());
							if(colecaoRota == null || colecaoRota.size() == 0){
								throw new ActionServletException("atencao.label_inexistente",null,"Rota Final informada");
							}
							else{
								objRotaFinal = (Rota)Util.retonarObjetoDeColecao(colecaoRota);							
							}
						}
					}
				}
			}
			
		}
		//========================================================================
		
		
		
		
		//-----------------------------------------------------------------------------------------------------------
		
		//1.9.3 Diâmetro da Ligação
		if(diametroLigacao == null || diametroLigacao.equals("-1")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Diâmetro da Ligação");
		}
		
		//1.9.4 Material da Ligação
		if(materialLigacao == null || materialLigacao.equals("-1")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Material da Ligação");
		}
		
		//1.9.5 Perfil da Ligação
		if(perfilLigacao == null || perfilLigacao.equals("-1")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Perfil da Ligação");
		}
		
		//1.9.6 Percentual de Coleta
		if(percentualColeta == null || percentualColeta.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Percentual de Coleta");
		}
		
		//Caso o usuário informe o valor do percentual de coleta maior que 100%
		if(percentualColetaBigDecimal.compareTo(new BigDecimal("100")) > 0){
			throw new ActionServletException("atencao.percentual_coleta_maximo");
		}
		
		//1.9.7 Percentual de Esgoto
		if(percentualEsgoto == null || percentualEsgoto.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Percentual de Esgoto");
		}
		
		//1.9.8.2 Com Caixa de Gordura?
		if(indicadorCaixaGordura == null || indicadorCaixaGordura.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Com Caixa de Gordura?");
		}
		
		//1.9.8.3 Ligação
		if(indicadorLigacao == null || indicadorLigacao.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Ligação");
		}
		
		//1.10.1 Investimento
		if(investimento == null || investimento.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Investimento");
		}
		
		//1.10.2 População
		if(populacao == null || populacao.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"População");
		}
		
		if(parecerEncerramento == null || parecerEncerramento.trim().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Parecer do Encerramento");
		}
		
		//1.10.3 Encerramento Automático
		if(incadorPermissaoEspecialEncerramentoAutomatico!=null && incadorPermissaoEspecialEncerramentoAutomatico.equals(ConstantesSistema.SIM.toString())){
			if(indicadorEncerramentoAutomatico == null || indicadorEncerramentoAutomatico.trim().equals("")){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Encerramento Automático");
			}			
		}
		else{
			indicadorEncerramentoAutomatico = ConstantesSistema.NAO.toString();
		}
		
		//1.10.5 Grupo de Faturamento
		/*if(indicadorEncerramentoAutomatico.equals(ConstantesSistema.SIM.toString()) &&
				(grupoFaturamento == null || grupoFaturamento.equals("-1"))){
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Grupo de Faturamento");
		}*/
		
		//Caso seja informado o grupo de faturamento e não tenha informado 
		//município ou dados de inscrição inicial e inscrição final
		if(grupoFaturamento != null && !grupoFaturamento.equals("-1") && (form.getIdImovel() == null || form.getIdImovel().equals("")) 
				&& (idMunicipio == null || idMunicipio.trim().equals("")) && 
				(idLocalidadeInicial == null || idLocalidadeInicial.equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Imóvel, Município ou Inscrição Inicial e Final");
		}	
		
		// --------------------------------------------------------------------------------------------------
		
		
		//[IT0017] Inserir Ordem Serviço Conexão Esgoto
		ComandoOSConexaoEsgoto comandoOS = new ComandoOSConexaoEsgoto();
		comandoOS.setDescricaoComando(descricaoComando);
		comandoOS.setIndicadorExecutor(new Short(indicadorExecucao));
		
		if(Util.verificarIdNaoVazio(idImovel)){
			Imovel imov = new Imovel();
			imov.setId(Integer.parseInt(idImovel));
			comandoOS.setImovel(imov);
		}
		else
			comandoOS.setImovel(null);
		
		if(Util.verificarIdNaoVazio(idMunicipio)){
			Municipio mun = new Municipio();
			mun.setId(Integer.parseInt(idMunicipio));
			comandoOS.setMunicipio(mun);
		}
		else
			comandoOS.setMunicipio(null);
		
		if(Util.verificarIdNaoVazio(idLogradouro)){
			Logradouro log = new Logradouro();
			log.setId(Integer.parseInt(idLogradouro));
			comandoOS.setLogradouro(log);
		}
		else
			comandoOS.setLogradouro(null);
		
		
		if(Util.verificarIdNaoVazio(idLocalidadeInicial)){
			Localidade loci = new Localidade();
			loci.setId(Integer.parseInt(idLocalidadeInicial));
			comandoOS.setLocalidadeInicial(loci);
		}
		else
			comandoOS.setLocalidadeInicial(null);
		
		if(Util.verificarIdNaoVazio(idLocalidadeFinal)){
			Localidade loci = new Localidade();
			loci.setId(Integer.parseInt(idLocalidadeFinal));
			comandoOS.setLocalidadeFinal(loci);
		}
		else
			comandoOS.setLocalidadeFinal(null);
		
		
		if(Util.verificarIdNaoVazio(idSetorComercialInicial)){
			SetorComercial sc = new SetorComercial();
			sc.setId(Integer.parseInt(idSetorComercialInicial));
			comandoOS.setSetorComercialInicial(sc);
		}
		else
			comandoOS.setSetorComercialInicial(null);
		
		
		if(Util.verificarIdNaoVazio(idSetorComercialFinal)){
			SetorComercial sc = new SetorComercial();
			sc.setId(Integer.parseInt(idSetorComercialFinal));
			comandoOS.setSetorComercialFinal(sc);
		}
		else
			comandoOS.setSetorComercialFinal(null);
		
		
		if(Util.verificarIdNaoVazio(quadraInicial)){
			comandoOS.setQuadraInicial(objQuadraInicial);
		}
		else
			comandoOS.setQuadraInicial(null);
		
		
		if(Util.verificarIdNaoVazio(quadraFinal)){
			comandoOS.setQuadraFinal(objQuadraFinal);
		}
		else
			comandoOS.setQuadraFinal(null);
		
		
		if(Util.verificarIdNaoVazio(rotaInicial)){
			comandoOS.setRotaInicial(objRotaInicial);
		}
		else
			comandoOS.setRotaInicial(null);
		
		
		if(Util.verificarIdNaoVazio(rotaFinal)){
			comandoOS.setRotaFinal(objRotaFinal);
		}
		else
			comandoOS.setRotaFinal(null);
		
		if(Util.verificarIdNaoVazio(rotaSequencialInicial)){
			comandoOS.setNumeroSequencialRotaInicial(Integer.parseInt(rotaSequencialInicial));
		}
		else{
			comandoOS.setNumeroSequencialRotaInicial(null);
		}
		
		if(Util.verificarIdNaoVazio(rotaSequencialFinal)){
			comandoOS.setNumeroSequencialRotaFinal(Integer.parseInt(rotaSequencialFinal));
		}
		else{
			comandoOS.setNumeroSequencialRotaFinal(null);
		}
		
		LigacaoEsgotoDiametro lig = new LigacaoEsgotoDiametro(); 
		lig.setId(Integer.parseInt(diametroLigacao));
		comandoOS.setDiametroLigacaoEsgoto(lig);
	
		LigacaoEsgotoMaterial mat = new LigacaoEsgotoMaterial(); 
		mat.setId(Integer.parseInt(materialLigacao));
		comandoOS.setMaterialLigacaoEsgoto(mat);
		
		LigacaoEsgotoPerfil per = new LigacaoEsgotoPerfil(); 
		per.setId(Integer.parseInt(perfilLigacao));
		comandoOS.setPerfilLigacaoEsgoto(per);
		
		comandoOS.setPercentualColeta(percentualColetaBigDecimal);
		comandoOS.setPercentualEsgoto(percentualEsgotoBigDecimal);
		
		if(Util.verificarIdNaoVazio(ligacaoOrigem)){
			LigacaoOrigem ligacao = new LigacaoOrigem();
			ligacao.setId(Integer.parseInt(ligacaoOrigem));
			comandoOS.setOrigemLigacao(ligacao);
		}
		else{
			comandoOS.setOrigemLigacao(null);
		}
		
		comandoOS.setIndicadorCaixaGordura(Short.valueOf(indicadorCaixaGordura));
		comandoOS.setIndicadorLigacaoEsgoto(Short.valueOf(indicadorLigacao));
		
		if(Util.verificarIdNaoVazio(condicaoEsgotamento)){
			LigacaoEsgotoEsgotamento cond = new LigacaoEsgotoEsgotamento();
			cond.setId(Integer.parseInt(condicaoEsgotamento));
			comandoOS.setCondicaoEsgotamento(cond);
		}
		else{
			comandoOS.setCondicaoEsgotamento(null);
		}
		
		if(Util.verificarIdNaoVazio(situacaoCaixaInspecao)){
			LigacaoEsgotoCaixaInspecao caixa = new LigacaoEsgotoCaixaInspecao();
			caixa.setId(Integer.parseInt(condicaoEsgotamento));
			comandoOS.setSituacaoCaixaInspecao(caixa);
		}
		else{
			comandoOS.setSituacaoCaixaInspecao(null);
		}
		
		if(Util.verificarIdNaoVazio(destinoDejetos)){
			LigacaoEsgotoDestinoDejetos dejetos = new LigacaoEsgotoDestinoDejetos();
			dejetos.setId(Integer.parseInt(condicaoEsgotamento));
			comandoOS.setDestinoDejetos(dejetos);
		}
		else{
			comandoOS.setDestinoDejetos(null);
		}
		
		if(Util.verificarIdNaoVazio(destinoAguasPluviais)){
			LigacaoEsgotoDestinoAguasPluviais aguas = new LigacaoEsgotoDestinoAguasPluviais();
			aguas.setId(Integer.parseInt(condicaoEsgotamento));
			comandoOS.setDestinoAguasPluviais(aguas);
		}
		else{
			comandoOS.setDestinoAguasPluviais(null);
		}
		
		comandoOS.setValorInvestimento(Util.formatarMoedaRealparaBigDecimal(investimento));
		comandoOS.setValorPopulacao(new Integer (populacao.replace(".", "")));
		comandoOS.setIndicadorEncerramentoAutomatico(new Short(indicadorEncerramentoAutomatico));
		comandoOS.setParecerEncerramentoAutomatico(parecerEncerramento);
		
		FaturamentoGrupo fat = null; 
		if(Util.verificarIdNaoVazio(grupoFaturamento)){
			
			FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();
			filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupoFaturamento));
			Collection col = this.getFachada().pesquisar(filtro, FaturamentoGrupo.class.getName());
			fat = (FaturamentoGrupo)Util.retonarObjetoDeColecao(col);
			
			fat.setId(Integer.parseInt(grupoFaturamento));
			comandoOS.setGrupoFaturamento(fat);
			
			comandoOS.setReferenciaGrupoFaturamento(fat.getAnoMesReferencia());
		}
		else{
			comandoOS.setGrupoFaturamento(null);
		}
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		comandoOS.setServicoTipo(sistemaParametro.getServicoTipo());
		
		comandoOS.setUltimaAlteracao(new Date());
		comandoOS.setDataCriacaoComando(new Date());
		comandoOS.setDataExecucaoComando(null);
		
		if(fat != null)
			comandoOS.setReferenciaGrupoFaturamento(fat.getAnoMesReferencia());
		else
			comandoOS.setReferenciaGrupoFaturamento(null);
		
		//Inserindo o objeto
		Integer idComOsConexaoEsgoto = (Integer) this.getFachada().inserir(comandoOS);
		
		if(comandoOS.getGrupoFaturamento() == null){
			//Chamar Batch
			Map parametros = new HashMap();
			parametros.put("idComOsConexaoEsgoto",idComOsConexaoEsgoto);
			parametros.put("usuario",this.getUsuarioLogado(httpServletRequest));
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
	     	Processo.GERAR_ORDEM_SERVICO_CONEXAO_ESGOTO, this.getUsuarioLogado(httpServletRequest));
			
			//Mensagem de Sucesso
			montarPaginaSucesso(httpServletRequest,
					"Comando informado enviado para Processamento", 
					"Voltar",
					"exibirFiltrarOrdemServicoConexaoEsgotoAction.do?menu=sim");
			
		}else{
			//Mensagem de Sucesso
			montarPaginaSucesso(httpServletRequest, "Comando de conexão de esgoto de codigo "
					+ idComOsConexaoEsgoto + " inserido com sucesso.",
					"Inserir outro Comando",
					"exibirFiltrarOrdemServicoConexaoEsgotoAction.do?menu=sim",
					null, null);
		}
		
		
		
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		return retorno;
	}	
}
