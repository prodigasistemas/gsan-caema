package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.FiltrarRelatorioComandosConexaoEsgotoHelper;
import gcom.relatorio.atendimentopublico.RelatorioComandosConexaoEsgotoAnalitico;
import gcom.relatorio.atendimentopublico.RelatorioComandosConexaoEsgotoSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
 * 
 * @author Mariana Victor
 * @date 20/08/2013
 * */
public class GerarRelatorioComandosConexaoEsgotoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		GerarRelatorioComandosConexaoEsgotoActionForm form =
				(GerarRelatorioComandosConexaoEsgotoActionForm) actionForm;

		boolean peloMenosUmParametroInformado = false;
		
		FiltrarRelatorioComandosConexaoEsgotoHelper helper = new FiltrarRelatorioComandosConexaoEsgotoHelper();
		
		if (form.getDescricaoComando() != null
				&& !form.getDescricaoComando().trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			helper.setDescricaoComando(
				form.getDescricaoComando());
		}
		
		if (form.getIndicadorExecucao() != null
				&& !form.getIndicadorExecucao().trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			helper.setIndicadorExecucao(
				new Short(form.getIndicadorExecucao()));
			
			if (form.getIndicadorExecucao().equals(form.getCompesa()+"")) {
				helper.setDescricaoExecucao("Compesa");
			} else {
				helper.setDescricaoExecucao("PPP");
			}
		}
		
		if (form.getIdImovel() != null
				&& !form.getIdImovel().trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(
				new Integer(form.getIdImovel()));
			
			//[FE0001] - Verificar Existência de Imóvel
			if(inscricao != null && !inscricao.trim().equals("")){
				peloMenosUmParametroInformado = true;
				helper.setIdImovel(
					new Integer(form.getIdImovel()));
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Imóvel");
			}
		}
		
		if (form.getIdMunicipio() != null
				&& !form.getIdMunicipio().trim().equals("")) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, form.getIdMunicipio()));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(
				filtroMunicipio, Municipio.class.getName());
			
			//[FE0002] - Validar Município
			if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
				Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
				peloMenosUmParametroInformado = true;
				helper.setIdMunicipio(
					new Integer(form.getIdMunicipio()));
				helper.setDescricaoMunicipio(municipio.getNome());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Município");
			}
		}
		
		if (form.getIdLogradouro() != null
				&& !form.getIdLogradouro().trim().equals("")) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID, form.getIdLogradouro()));
			filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_MUNICIPIO, form.getIdMunicipio()));
			filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.INDICADORUSO, ConstantesSistema.SIM));
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(
				filtroLogradouro, Logradouro.class.getName());
			
			//[FE0003] - Validar Logradouro 
			if(colecaoLogradouro != null && !colecaoLogradouro.isEmpty()){
				Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouro);
				peloMenosUmParametroInformado = true;
				helper.setIdLogradouro(
					new Integer(form.getIdLogradouro()));
				helper.setDescricaoLogradouro(logradouro.getNome());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Logradouro");
			}
		}
		
		if (form.getIdLocalidadeInicial() != null
				&& !form.getIdLocalidadeInicial().trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidadeInicial()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
			Collection<Localidade> colecaoLocalidade  = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());
			
			//[FE0004] - Verificar Existência Localidade
			if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
				peloMenosUmParametroInformado = true;
				helper.setIdLocalidadeInicial(
					new Integer(form.getIdLocalidadeInicial()));
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade Inicial");
			}
			
			if (form.getCodigoSetorComercialInicial() != null
					&& !form.getCodigoSetorComercialInicial().trim().equals("")) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidadeInicial()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialInicial()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
				Collection<SetorComercial> colecaoSetorComecial = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
				
				SetorComercial setorComercial = null;
				//[FE0005] - Verificar existência do Setor Comercial
				if(colecaoSetorComecial != null && !colecaoSetorComecial.isEmpty()){
					setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComecial);
					peloMenosUmParametroInformado = true;
					helper.setIdSetorComercialInicial(setorComercial.getId());
					helper.setCodigoSetorComercialInicial(setorComercial.getCodigo());
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Comercial Inicial");
				}

				if (form.getQuadraInicial() != null
						&& !form.getQuadraInicial().trim().equals("")) {
					
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
					filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, form.getQuadraInicial()));
					filtroQuadra.adicionarCaminhoParaCarregamentoEntidade(
						FiltroQuadra.ROTA);
					Collection<Quadra> colecaoQuadra = fachada.pesquisar(
						filtroQuadra, Quadra.class.getName());
					
					Quadra quadra = null;
					if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
						quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
						peloMenosUmParametroInformado = true;
						helper.setIdQuadraInicial(quadra.getId());
						helper.setQuadraInicial(quadra.getNumeroQuadra());
					} else {
						throw new ActionServletException("atencao.date", "Quadra Inicial");
					}
					
					if (form.getCodigoRotaInicial() != null
							&& !form.getCodigoRotaInicial().trim().equals("")) {
						
						FiltroRota filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, form.getCodigoRotaInicial()));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.ID_ROTA, quadra.getRota().getId()));
						Collection<Rota> colecaoRota = fachada.pesquisar(
							filtroRota, Rota.class.getName());
						
						// [SB0008] - Selecionar Rota Informada
						if(colecaoRota != null && !colecaoRota.isEmpty()){
							Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
							peloMenosUmParametroInformado = true;
							helper.setIdRotaInicial(rota.getId());
							helper.setCodigoRotaInicial(new Integer(rota.getCodigo()));
						} else {
							throw new ActionServletException("atencao.pesquisa_inexistente", "Rota Inicial");
						}

						if (form.getSequencialRotaInicial() != null
								&& !form.getSequencialRotaInicial().trim().equals("")) {
							peloMenosUmParametroInformado = true;
							helper.setSequencialRotaInicial(
								new Integer(form.getSequencialRotaInicial()));
						}
					}
				}
			}
		}
		
		if (form.getIdLocalidadeFinal() != null
				&& !form.getIdLocalidadeFinal().trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidadeFinal()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
			Collection<Localidade> colecaoLocalidade  = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());
			
			//[FE0004] - Verificar Existência Localidade
			if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
				peloMenosUmParametroInformado = true;
				helper.setIdLocalidadeFinal(
					new Integer(form.getIdLocalidadeFinal()));
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade Final");
			}
			
			if (form.getCodigoSetorComercialFinal() != null
					&& !form.getCodigoSetorComercialFinal().trim().equals("")) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidadeFinal()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialFinal()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
				Collection<SetorComercial> colecaoSetorComecial = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
				
				SetorComercial setorComercial = null;
				//[FE0005] - Verificar existência do Setor Comercial
				if(colecaoSetorComecial != null && !colecaoSetorComecial.isEmpty()){
					setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComecial);
					peloMenosUmParametroInformado = true;
					helper.setIdSetorComercialFinal(setorComercial.getId());
					helper.setCodigoSetorComercialFinal(setorComercial.getCodigo());
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Comercial Final");
				}
				
				if (form.getQuadraFinal() != null
						&& !form.getQuadraFinal().trim().equals("")) {
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
					filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, form.getQuadraFinal()));
					Collection<Quadra> colecaoQuadra = fachada.pesquisar(
						filtroQuadra, Quadra.class.getName());
					
					Quadra quadra = null;
					if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
						quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
						peloMenosUmParametroInformado = true;
						helper.setIdQuadraFinal(quadra.getId());
						helper.setQuadraFinal(quadra.getNumeroQuadra());
					} else {
						throw new ActionServletException("atencao.date", "Quadra Final");
					}

					if (form.getCodigoRotaFinal() != null
							&& !form.getCodigoRotaFinal().trim().equals("")) {
						FiltroRota filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, form.getCodigoRotaFinal()));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
						filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.ID_ROTA, quadra.getRota().getId()));
						Collection<Rota> colecaoRota = fachada.pesquisar(
							filtroRota, Rota.class.getName());
						
						// [SB0008] - Selecionar Rota Informada
						if(colecaoRota != null && !colecaoRota.isEmpty()){
							Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
							peloMenosUmParametroInformado = true;
							helper.setIdRotaFinal(rota.getId());
							helper.setCodigoRotaFinal(new Integer(rota.getCodigo()));
						} else {
							throw new ActionServletException("atencao.pesquisa_inexistente", "Rota Final");
						}
						
						if (form.getSequencialRotaFinal() != null
								&& !form.getSequencialRotaFinal().trim().equals("")) {
							peloMenosUmParametroInformado = true;
							helper.setSequencialRotaFinal(
								new Integer(form.getSequencialRotaFinal()));
						}
					}
				}
			}
		}
		
		if (form.getIdFaturamentoGrupo() != null
				&& !form.getIdFaturamentoGrupo().trim().equals("")
				&& !form.getIdFaturamentoGrupo().trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			helper.setIdFaturamentoGrupo(
				new Integer(form.getIdFaturamentoGrupo()));
			
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.ID, form.getIdFaturamentoGrupo()));
			Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) 
					Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
			helper.setDescricaoFaturamentoGrupo(
				faturamentoGrupo.getDescricao());
		}
		
		if (form.getIdSituacaoOrdemServico() != null
				&& !form.getIdSituacaoOrdemServico().trim().equals("")
				&& !form.getIdSituacaoOrdemServico().trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			helper.setIdSituacaoOrdemServico(
				new Integer(form.getIdSituacaoOrdemServico()));
			
			if (form.getIdSituacaoOrdemServico().trim().equals(OrdemServico.SITUACAO_PENDENTE + "")) {
				helper.setDescricaoSituacaoOS("PENDENTES");
			} else {
				helper.setDescricaoSituacaoOS("ENCERRADOS");
			}
		}
		
		//[FE0007] - Validar Data
		if (form.getDataGeracaoInicial() != null
				&& !form.getDataGeracaoInicial().trim().equals("")) {
			String formato = "dd/MM/yyyy";
			if (!Util.validarDataInvalida(form.getDataGeracaoInicial(), formato)) {
				throw new ActionServletException(
						"atencao.date", "Data Inicial");
			} else if (form.getDataGeracaoFinal() == null
					|| form.getDataGeracaoFinal().trim().equals("")){
				throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Data Final do Período");
			} else if (!Util.validarDataInvalida(form.getDataGeracaoFinal(), formato)) {
				throw new ActionServletException(
					"atencao.date", "Data Final");
			} else {
				Date dataInicial = Util.converteStringParaDate(form
					.getDataGeracaoInicial());
				Date dataFinal = Util.converteStringParaDate(form
					.getDataGeracaoFinal());
				
				//[FE0008] - Verificar data final menor que data inicial.
				if (Util.compararData(dataInicial, dataFinal) > 0) {
					throw new ActionServletException(
						"atencao.data_final_periodo.anterior.data_inicial_periodo");
				}
				
				peloMenosUmParametroInformado = true;
				helper.setDataGeracaoInicial(dataInicial);
				helper.setDataGeracaoFinal(dataFinal);
			}
		} else if (form.getDataGeracaoFinal() != null
				&& !form.getDataGeracaoFinal().trim().equals("")) {
			throw new ActionServletException(
				"atencao.campo_selecionado.obrigatorio", "Data Inicial do Período");
		}
		
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		TarefaRelatorio relatorio = null;
		
		// 4.1.	Caso o usuário selecionou relatório Sintético:
		if(form.getTipoRelatorioSinteticoAnalitico() != null
				&& form.getTipoRelatorioSinteticoAnalitico().trim().equals("" + ConstantesSistema.SIM)) {
			relatorio = new RelatorioComandosConexaoEsgotoSintetico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		} else {
			// 4.2.	Caso contrário, ou seja, relatório Analítico:
			relatorio = new RelatorioComandosConexaoEsgotoAnalitico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		}
		

		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorio.addParametro("filtrarRelatorioComandosConexaoEsgotoHelper", helper);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
	
}
