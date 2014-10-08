package gcom.gui.mobile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.atendimentopublico.RelatorioAcompanhamentoOSCobrancaSmartphoneHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoOSCobrancaSmartphone;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
 * 
 * @author Anderson Cabral
 * @since 19/08/2013
 */
public class GerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm form = (GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm) actionForm;
		String nomeRelatorio = null;	
		
		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		
		if(form.getOpcaoRelatorio().equalsIgnoreCase("1")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_ANALITICO;
		}else if(form.getOpcaoRelatorio().equalsIgnoreCase("2")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_SINTETICO;
		}
		
		RelatorioAcompanhamentoOSCobrancaSmartphone relatorioAcompanhamentoOSCobrancaSmartphone = new RelatorioAcompanhamentoOSCobrancaSmartphone(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);
		
		//Empresa
		Empresa empresa = null;
		if(Util.verificarIdNaoVazio( form.getIdEmpresa())){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
			empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
		}else{
			throw new ActionServletException("atencao.empresa.inexistente");
		}
		
		//Gerencia Regional
		GerenciaRegional gerenciaRegional = null;
		if(Util.verificarIdNaoVazio(form.getIdGerenciaRegional())){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getIdGerenciaRegional()));
			Collection<GerenciaRegional> gerenciasRegional = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(gerenciasRegional);
		}
		
		//Unidade de Negocio
		UnidadeNegocio unidadeNegocio = null;
		if(Util.verificarIdNaoVazio(form.getIdUnidadeNegocio())){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, form.getIdUnidadeNegocio()));
			Collection<UnidadeNegocio> unidadeNegocios = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(unidadeNegocios);
		}
		
		//Localidade
		String idLocalidade = form.getIdLocalidade();
		Localidade localidade = null;
		if(Util.verificarIdNaoVazio(idLocalidade)){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			Collection<Localidade> colecaolocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (!Util.isVazioOrNulo(colecaolocalidade)){
				localidade = (Localidade) colecaolocalidade.iterator().next();
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
			}
		}
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}	
		
		RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper = new RelatorioAcompanhamentoOSCobrancaSmartphoneHelper();
		
		helper.setTipoRelatorio(form.getOpcaoRelatorio());
		helper.setEmpresa(empresa);
		helper.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getDataReferencia()).toString());
		helper.setGerenciaRegional(gerenciaRegional);
		helper.setUnidadeNegocio(unidadeNegocio);
		helper.setLocalidade(localidade);
		
		if(form.getIdsTipoServico() != null && form.getIdsTipoServico().length > 0){		
			ArrayList<String> colecaoIdsTipoServico = new ArrayList<String>();
			Collections.addAll(colecaoIdsTipoServico, form.getIdsTipoServico());
			if(colecaoIdsTipoServico.contains("-1")){
				colecaoIdsTipoServico.remove("-1");
			}
			helper.setIdsTipoServico(colecaoIdsTipoServico);
		}
	
		if(form.getPeriodoGeracaoOSInicial() != null && !form.getPeriodoGeracaoOSInicial().equals("") &&
				form.getPeriodoGeracaoOSFinal() != null && !form.getPeriodoGeracaoOSFinal().equals("")){
			helper.setPeriodoGeracaoInicial(Util.converteStringParaDate(form.getPeriodoGeracaoOSInicial()));
			helper.setPeriodoGeracaoFinal(Util.converteStringParaDate(form.getPeriodoGeracaoOSFinal()));
			
			if(Util.compararData(helper.getPeriodoGeracaoInicial(), helper.getPeriodoGeracaoFinal()) == 1){
				throw new ActionServletException("atencao.periodoinvalido");
			}
		}
	
		relatorioAcompanhamentoOSCobrancaSmartphone.addParametro("helper", helper);
		relatorioAcompanhamentoOSCobrancaSmartphone.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		try {
			retorno = processarExibicaoRelatorio(relatorioAcompanhamentoOSCobrancaSmartphone, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		return retorno;
	}
}