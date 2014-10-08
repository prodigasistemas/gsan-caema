package gcom.gui.relatorio.atendimentopublico;

import java.util.Collection;

import gcom.atendimentopublico.bean.PesquisaSatisfacaoHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioPesquisaSatisfacaoAnalitico;
import gcom.relatorio.atendimentopublico.RelatorioPesquisaSatisfacaoSintentico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Davi Menezes
 * @date 27/02/2012
 *
 */
public class GerarRelatorioPesquisaSatisfacaoAction extends ExibidorProcessamentoTarefaRelatorio {

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
    	
    	ActionForward retorno = actionMapping.findForward("pesquisaSatisfacaoGerarRelatorio");
    	
    	GerarRelatorioPesquisaSatisfacaoActionForm form = (GerarRelatorioPesquisaSatisfacaoActionForm) actionForm;
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	PesquisaSatisfacaoHelper helper = new PesquisaSatisfacaoHelper();
    	
    	String dataPesquisaInicial = form.getDataPesquisaInicio();
    	String dataPesquisaFinal = form.getDataPesquisaFim();
    	
    	String tipo = form.getTipo();
    	
    	Integer dataInicial = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataPesquisaInicial);
    	Integer dataFinal = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataPesquisaFinal);
    	
    	if(dataInicial > dataFinal){
    		throw new ActionServletException("atencao.execucao.data_inicial_comando.maior_data_final_comando");
    	}
    	
    	helper.setDataPeriodoInicial(dataPesquisaInicial);
    	helper.setDataPeriodoFinal(dataPesquisaFinal);
    	
    	if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
    		Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
    		
    		if(imovel == null){
    			throw new ActionServletException("atencao.imovel.inexistente");
    		}
    		helper.setIdImovel(form.getIdImovel());
    	}
    	
    	if(form.getIdUnidade() != null && !form.getIdUnidade().equals("")){
    		UnidadeOrganizacional unidade = 
    			fachada.pesquisarUnidadeOrganizacional(Integer.parseInt(form.getIdUnidade()));
    		
    		if(unidade == null){
    			throw new ActionServletException("atencao.unidade.organizacional.inexistente");
    		}
    		
    		helper.setIdUnidade(form.getIdUnidade());
    	}
    	
    	if(form.getCriterio() != null && !form.getCriterio().equals("-1")){
    		helper.setCriterio(form.getCriterio());
    	}
    	else{
    		helper.setCriterio("0");
    	}
    	
    	String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
    	
    	Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
    	
    	TarefaRelatorio relatorio = null;
    	if(tipo != null && tipo.equals("1")){
    		relatorio = new RelatorioPesquisaSatisfacaoAnalitico(usuarioLogado);
    	}else{
    		relatorio = new RelatorioPesquisaSatisfacaoSintentico(usuarioLogado);
    	}
    	
    	relatorio.addParametro("usuarioLogado", usuarioLogado);
    	relatorio.addParametro("helperPesquisa", helper);
    	
    	if(tipoRelatorio == null){
    		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    	}
    	
    	relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
    	
    	retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
    		httpServletResponse, actionMapping);
    	
    	return retorno;
    }
}
