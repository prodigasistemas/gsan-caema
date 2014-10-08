package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.bean.FiltrarImoveisCortadosHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.FiltrarImovelCortadoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioImoveisCortados;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  [UC1279] - Consultar Imóveis Com Situação da Ligação de Água Cortado
 *  SB0006 - Gerar Relatorio Imoveis Cortados
 * 
 * 
 * @author Arthur Carvalho
 * @data 24/02/2012
 *
 */
public class GerarRelatorioImoveisCortadosAction extends ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		FiltrarImovelCortadoActionForm form = (FiltrarImovelCortadoActionForm) actionForm;
		
		FiltrarImoveisCortadosHelper filtro = new FiltrarImoveisCortadosHelper();
		
		//FS0008 - Valida periodo de corte
		this.validaTipoCorte(form);
		
		filtro.setDataCorteInicial(form.getPeriodoCorteInicial());
		filtro.setDataCorteFinal(form.getPeriodoCorteFinal());
			
		filtro.setGerenciaRegional(form.getIdGerenciaRegional());
		filtro.setUnidadeNegocio(form.getIdUnidadeNegocio());
		
		if ( form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdLocalidadeInicial()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Localidade Inicial");
			}
			validarLocalidade(form);
		}
		
		if ( form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdLocalidadeFinal()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Localidade Final");
			}
			validarLocalidadeFinal(form);
		}
		
		if ( form.getIdSetorComercialInicial() != null && !form.getIdSetorComercialInicial().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdSetorComercialInicial()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Setor Comercial Inicial");
			}
			validarSetorComercialInicial(form);
		}
		
		if ( form.getIdSetorComercialFinal() != null && !form.getIdSetorComercialFinal().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdSetorComercialFinal()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Setor Comercial Final");
			}
			validarSetorComercialFinal(form);
		}
		
		if ( form.getIdQuadraInicial() != null && !form.getIdQuadraInicial().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdQuadraInicial()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Quadra Inicial");
			}
			if ( form.getIdSetorComercialFinal() != null && !form.getIdSetorComercialFinal().equals("") ) {
				validarQuadra(form);
			}
		}
		
		if ( form.getIdQuadraFinal() != null && !form.getIdQuadraFinal().equals("") ) {
			if ( !Util.validarStringNumerica(form.getIdQuadraFinal()) ) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
						"Quadra Final");
			}
			if ( form.getIdSetorComercialFinal() != null && !form.getIdSetorComercialFinal().equals("") ) {
				validarQuadraFinal(form);
			}
		}
		
		String[] mensagem = new String[2];
		mensagem[0] = "Valor Débito Inicial";
		mensagem[1] = "Valor Débito Final";
		if (form.getValorDebitoInicial() != null && !form.getValorDebitoInicial().equals("") ) {
			if( Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoInicial()).compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoFinal())) > 0 ) {
				throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial", null, mensagem );
			}
		}
		
		filtro.setLocalidadeInicial(form.getIdLocalidadeInicial());
		filtro.setLocalidadeFinal(form.getIdLocalidadeFinal());
		filtro.setSetorComercialInicial(form.getIdSetorComercialInicial());
		filtro.setSetorComercialFinal(form.getIdSetorComercialFinal());
		filtro.setQuadraInicial(form.getIdQuadraInicial());
		filtro.setQuadraFinal(form.getIdQuadraFinal());
		filtro.setIdMotivoCorte(form.getIdMotivoCorte());
		filtro.setValorDebitoInicial(form.getValorDebitoInicial());
		filtro.setValorDebitoFinal(form.getValorDebitoFinal());
		filtro.setIndicadorGerarOSFiscalizacao(form.getIndicadorGerarOSFiscalizacao());
		
		 //Dados do formulário
        TarefaRelatorio relatorio = new RelatorioImoveisCortados(usuario);
        
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		
		relatorio.addParametro("filtro", filtro);
		
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
	
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		return retorno;
	}
	
	
	private void validarLocalidade(FiltrarImovelCortadoActionForm form) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Localidade Inicial");
		}
	}

	private void validarLocalidadeFinal(FiltrarImovelCortadoActionForm form) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Localidade Final");
		}
	}

	private void validarSetorComercialInicial(FiltrarImovelCortadoActionForm form) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialInicial()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, form.getIdLocalidadeInicial()));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Setor Comercial Inicial");

		}
	}
	
	private void validarSetorComercialFinal(FiltrarImovelCortadoActionForm form) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialFinal()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, form.getIdLocalidadeFinal()));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Setor Comercial Final");

		}
	}
	
	private void validarQuadra(FiltrarImovelCortadoActionForm form) {

		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraInicial())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras == null || colecaoQuadras.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Quadra Inicial");
		
		}
	}
	
	private void validarQuadraFinal(FiltrarImovelCortadoActionForm form) {
		
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraFinal())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras == null || colecaoQuadras.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Quadra Final");
		}
	}
		
	private void validaTipoCorte(FiltrarImovelCortadoActionForm form) {
		Date periodoInicial = null;
		if ( form.getPeriodoCorteInicial() != null && !form.getPeriodoCorteInicial().equals("") ) {
			periodoInicial = Util.converteStringParaDate(form.getPeriodoCorteInicial());
		}
		
		Date periodoFinal = null;
		if ( form.getPeriodoCorteFinal() != null && !form.getPeriodoCorteFinal().equals("") ) {
			periodoFinal = Util.converteStringParaDate(form.getPeriodoCorteFinal());
		}
		
		if ( periodoInicial == null && periodoFinal == null ) {
			throw new ActionServletException("atencao.campo_texto.obrigatorio",
					"Periodo de Corte");
		} else if ( periodoInicial != null && periodoFinal == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Corte Final");
		} else if ( periodoFinal != null && periodoInicial == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Corte Inicial");
		} else if ( Util.compararData(periodoFinal, new Date()) > 0) {
			throw new ActionServletException("atencao.data_final_menor_data_atual",
					"Periodo de Corte Final");
		} else if ( periodoInicial != null && periodoFinal != null && Util.compararData(periodoInicial, periodoFinal) > 0 ) {
			String[] mensagem = new String[2];
			mensagem[0] = "Periodo de Corte Inicial";
			mensagem[1] = "Periodo de Corte Final";
			throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial",null, mensagem);
		}
	}
}
