package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
/**
 * [UC 1513] - Manter Motivo de não execução do serviço 
 * 
 * @author Diogo Luiz
 * @date 01/07/2013
 *
 */
public class RemoverMotivoDeNaoExecucaoDoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        // Array de strings com todos os Ids que serão removidos da tela Manter Material
        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");
       
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        // mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }

        fachada.removerMotivoDeNaoExecucaoDoServico(ids,usuarioLogado);        
        
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,ids.length + " Motivo(s) de não execução do serviço removido(s) com sucesso.",
            		"Realizar outra Manutenção de Não Execução do Serviço","exibirFiltrarMotivoDeNaoExecucaoDoServicoAction.do?menu=sim");
        }

        return retorno;
	}
}