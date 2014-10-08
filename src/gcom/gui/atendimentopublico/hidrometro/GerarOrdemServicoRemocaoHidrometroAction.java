package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Autor: Jonathan Marcos
 * Data: 25/11/2013
 * RM8974
 * UC0364
 * [SB0011] Gerar Ordem de Serviço Remocao
 */

public class GerarOrdemServicoRemocaoHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServicoEncerrada");
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Integer servicoTipoId =  Integer.valueOf(httpServletRequest.getParameter("servicoTipo").toString());
		ServicoTipo servicoTipo = new ServicoTipo();
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, servicoTipoId));
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
		Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		servicoTipo = (ServicoTipo)Util.retonarObjetoDeColecao(colecaoServicoTipo);
		
		ordemServico.setServicoTipo(servicoTipo);
		
		//PESQUISAR O COMANDO ORDEM SELETIVA POIS O OBJETO VEM POR DEFAULT NULL
		Integer idComandoOrdemSeletiva = fachada.pesquisarComandoOrdemSeletivaOS(ordemServico.getId());
		if(idComandoOrdemSeletiva!=null){
			
			ComandoOrdemSeletiva comandoOrdemSeletiva = new ComandoOrdemSeletiva();
			
			comandoOrdemSeletiva.setId(idComandoOrdemSeletiva);
			
			ordemServico.setComandoOrdemSeletiva(comandoOrdemSeletiva);
		}
		
		Integer idOrdemServicoGerada = fachada.gerarOrdemServico(ordemServico, usuario, null);
		
		//[SB0012] REABRIR REGISTRO ATENDIMENTO
		if(ordemServico.getRegistroAtendimento()!=null){
			
			RegistroAtendimento registroAtendimento = new RegistroAtendimento();
			
			registroAtendimento = fachada.pesquisarRegistroAtendimentoPorRA(ordemServico.getRegistroAtendimento().getId());
			
			//REMOVER RA_UNIDADE
			fachada.removerRAUnidade(registroAtendimento.getId());
			
			registroAtendimento.setAtendimentoMotivoEncerramento(null);
			registroAtendimento.setCodigoSituacao(ConstantesSistema.SIM);
			registroAtendimento.setDataEncerramento(null);
			
			fachada.atualizar(registroAtendimento);
		}
		
		//MONTAR TELA DE SUCESSO
		montarPaginaSucesso(httpServletRequest, "Ordem de Serviço de Remoção de Hidrômetro de código "+idOrdemServicoGerada+" gerada com sucesso","Voltar","");
		
		return retorno;
	
	}
}
