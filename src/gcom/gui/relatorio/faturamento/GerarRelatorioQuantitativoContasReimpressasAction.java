package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioQuantitativoContasReimpressas;
import gcom.relatorio.faturamento.RelatorioQuantitativoContasReimpressasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1293] - Relatorio Quantitativo de Contas Reimpressas
 * 
 * @author Davi Menezes
 * @date 07/03/2012
 *
 */
public class GerarRelatorioQuantitativoContasReimpressasAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	//ActionForward retorno = actionMapping.findForward("quantitativoContasReimpressasGerarRelatorio");
    	ActionForward retorno = null;
    	
    	GerarRelatorioQuantitativoContasReimpressasActionForm form = 
    		(GerarRelatorioQuantitativoContasReimpressasActionForm) actionForm;
    	
    	RelatorioQuantitativoContasReimpressasHelper helper = new RelatorioQuantitativoContasReimpressasHelper();
    	
    	httpServletRequest.setAttribute("telaSucessoRelatorio",true);
    	
    	 httpServletRequest.setAttribute("caminhoRetornoNovoRelatorio","exibirGerarRelatorioQuantitativoContasReimpressasAction.do");
    	
    	Fachada fachada = this.getFachada();
    	
    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
    	
    	Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia());
    	Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
    	
    	if(anoMesReferencia > anoMesFaturamento){
    		throw new ActionServletException("atencao.data_referencia_maior_que_data_faturamento");
    	}else{
    		helper.setAnoMesReferencia(anoMesReferencia);
    	}
    	
    	if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
    		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
    		
    		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
    			filtroLocalidade.adicionarParametro(new ParametroSimples(
    				FiltroLocalidade.ID_UNIDADE_NEGOCIO, form.getUnidadeNegocio()));
    		}
    		
    		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
    			filtroLocalidade.adicionarParametro(new ParametroSimples(
    				FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
    		}
    		
    		Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
    		
    		if(Util.isVazioOrNulo(colLocalidade)){
    			throw new ActionServletException("atencao_localidade_inexistente_parametros_informados");
    		}else{
    			helper.setLocalidade(form.getLocalidade());
    		}
    	}
    	
    	if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
    		FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
    		filtroSetor.adicionarParametro(new ParametroSimples(
    			FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
    		
    		if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
    			filtroSetor.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
    		}else{
    			throw new ActionServletException("atencao.informe_localidade_para_informar_setor");
    		}
    		
    		Collection<?> colSetor = fachada.pesquisar(filtroSetor, SetorComercial.class.getName());
    		
    		if(Util.isVazioOrNulo(colSetor)){
    			throw new ActionServletException("atencao.setor_comercial.inexistente");
    		}else{
    			helper.setSetorComercial(form.getSetorComercial());
    		}
    	}
    	
    	if(form.getRota() != null && !form.getRota().equals("")){
    		FiltroRota filtroRota = new FiltroRota();
    		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRota()));
    		
    		if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
    			filtroRota.adicionarParametro(new ParametroSimples(
    				FiltroRota.SETOR_COMERCIAL_CODIGO, form.getSetorComercial()));
    		}else{
    			throw new ActionServletException("atencao.informe_setor_comercial_para_informar_rota");
    		}
    		
    		if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
    			filtroRota.adicionarParametro(new ParametroSimples(
    				FiltroRota.LOCALIDADE_ID, form.getLocalidade()));
    		}else{
    			throw new ActionServletException("atencao.informe_localidade_para_informar_rota");
    		}
    		
    		Collection<?> colRota = fachada.pesquisar(filtroRota, Rota.class.getName());
    		
    		if(Util.isVazioOrNulo(colRota)){
    			throw new ActionServletException("atencao.pesquisa.rota_inexistente");
    		}else{
    			helper.setRota(form.getRota());
    		}
    	}
    	
    	if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
    		helper.setGerenciaRegional(form.getGerenciaRegional());
    	}
    	
    	if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
    		helper.setUnidadeNegocio(form.getUnidadeNegocio());
    	}
    	
    	if(form.getEmpresa() != null && !form.getEmpresa().equals("-1")){
    		helper.setEmpresa(form.getEmpresa());
    	}
    	
    	String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
    	
    	Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
    	
    	TarefaRelatorio relatorio = new RelatorioQuantitativoContasReimpressas(usuarioLogado);
    	
    	if(tipoRelatorio == null){
    		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    	}
    	
    	relatorio.addParametro("usuarioLogado", usuarioLogado);
    	relatorio.addParametro("helper", helper);
    	relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
    	
    	retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
    			httpServletResponse, actionMapping);
    	
    	return retorno;
    }
}
