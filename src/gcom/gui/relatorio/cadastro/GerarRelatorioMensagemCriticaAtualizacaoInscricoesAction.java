package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.atualizacaocadastral.LimparMensagemCriticaAtualizacaoInscricoesActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.RelatorioMensagemCriticaAtualizacaoInscricoes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições
 * 
 * [SB 0001] - Gerar Relatório
 * 
 * @author Davi Menezes
 * @date 12/11/2012
 *
 */
public class GerarRelatorioMensagemCriticaAtualizacaoInscricoesAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
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
		
		// cria a variável de retorno
		ActionForward retorno = null;
		
		LimparMensagemCriticaAtualizacaoInscricoesActionForm form = 
				(LimparMensagemCriticaAtualizacaoInscricoesActionForm) actionForm;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		Fachada fachada = Fachada.getInstancia();
		
		String idLocalidade = "";
		String descricaoLocalidade = "";
		
		String descricaoSetorComercial = "";
		String codigoSetorComercial = "";
		
		//Validar Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			idLocalidade = form.getIdLocalidade();
			
			Localidade localidade = this.pesquisarLocalidade(form, fachada);
			
			if(localidade != null){
				descricaoLocalidade = localidade.getDescricao();
			}
			
			//Validar Setor Comercial
			if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
				codigoSetorComercial = form.getCodigoSetorComercial();
				
				SetorComercial setorComercial = this.pesquisarSetorComercial(form, fachada);
				
				if(setorComercial != null){
					descricaoSetorComercial = setorComercial.getDescricao();
				}
			}
		}
		
		String mensagemCritica = "";
		if(form.getMensagemCritica() != null && !form.getMensagemCritica().equals("") && !form.getMensagemCritica().equals("-1")){
			mensagemCritica = form.getMensagemCritica();
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

    	Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
    	
    	TarefaRelatorio relatorio = new RelatorioMensagemCriticaAtualizacaoInscricoes(usuarioLogado);
    	
    	if(tipoRelatorio == null){
    		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    	}
    	
    	relatorio.addParametro("usuarioLogado", usuarioLogado);
    	relatorio.addParametro("idLocalidade", idLocalidade);
    	relatorio.addParametro("descricaoLocalidade", descricaoLocalidade);
    	relatorio.addParametro("codigoSetorComercial", codigoSetorComercial);
    	relatorio.addParametro("descricaoSetorComercial", descricaoSetorComercial);
    	relatorio.addParametro("mensagemCritica", mensagemCritica);
    	relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
    	
    	retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
    			httpServletResponse, actionMapping);
		
		return retorno;
	}
	
	/**
	 * Validar a Localidade Informada
	 * 
	 * @author Davi Menezes
	 * @date 12/11/2012
	 * 
	 * @param form
	 * @param fachada
	 * @return
	 */
	private Localidade pesquisarLocalidade(LimparMensagemCriticaAtualizacaoInscricoesActionForm form, Fachada fachada){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
		
		Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if(!Util.isVazioOrNulo(colLocalidade)){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			
			return localidade;
		}else{
			throw new ActionServletException("pesquisa.localidade.inexistente");
		}
	}
	
	/**
	 * Validar o Setor Comercial Informado
	 * 
	 * @author Davi Menezes
	 * @date 12/11/2012
	 * 
	 * @param form
	 * @param fachada
	 * @return
	 */
	private SetorComercial pesquisarSetorComercial(LimparMensagemCriticaAtualizacaoInscricoesActionForm form, Fachada fachada){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
		
		Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(!Util.isVazioOrNulo(colSetorComercial)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
			
			return setorComercial;
		}else{
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
	}
	
}
