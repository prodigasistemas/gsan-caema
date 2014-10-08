package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1472] - Inserir Servi�o da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 16/05/2013
 *
 */
public class InserirServicoRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Exibi��o da Tela de Inser��o)
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Form
		InserirServicoRepavimentadoraActionForm form = (InserirServicoRepavimentadoraActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Usu�rio Logado
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Servi�o da Repavimentadora
		ServicoRepavimentadora servicoRepavimentadora = new ServicoRepavimentadora();
		
		String descricao = form.getDescricao();
		if(descricao != null && !descricao.equals("")){
			servicoRepavimentadora.setDescricao(descricao);
		}else{
			throw new ActionServletException("atencao.campo.informado", null, "Descri��o");
		}
		
		String descricaoAbreviada = form.getDescricaoAbreviada();
		if(descricaoAbreviada != null && !descricaoAbreviada.equals("")){
			servicoRepavimentadora.setDescricaoAbreviada(descricaoAbreviada);
		}
		
		String idUnidadeMaterial = form.getUnidadeMaterial();
		if(idUnidadeMaterial != null && !idUnidadeMaterial.equals("") && !idUnidadeMaterial.equals("-1")){
			MaterialUnidade materialUnidade = new MaterialUnidade();
			materialUnidade.setId(Integer.parseInt(idUnidadeMaterial));
			
			servicoRepavimentadora.setMaterialUnidade(materialUnidade);
		}else{
			throw new ActionServletException("atencao.campo.informado", null, "Unidade do Servi�o");
		}
		
		String idUnidadeRepavimentadora = form.getUnidadeRepavimentadora();
		if(idUnidadeRepavimentadora != null && !idUnidadeRepavimentadora.equals("") && !idUnidadeRepavimentadora.equals("-1")){
			UnidadeOrganizacional unidadeRepavimentadora = new UnidadeOrganizacional();
			unidadeRepavimentadora.setId(Integer.parseInt(idUnidadeRepavimentadora));
			
			servicoRepavimentadora.setUnidadeRepavimentadora(unidadeRepavimentadora);
		}else{
			throw new ActionServletException("atencao.campo.informado", null, "Unidade Repavimentadora");
		}
		
		String valorServico = form.getValorServico(); 
		if(valorServico != null && !valorServico.equals("")){
			valorServico = valorServico.replaceAll(",", ".");
			servicoRepavimentadora.setValorServico(Util.formatarMoedaRealparaBigDecimal(valorServico));
		}else{
			throw new ActionServletException("atencao.campo.informado", null, "Valor do Servi�o");
		}
		
		servicoRepavimentadora.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		servicoRepavimentadora.setUltimaAlteracao(new Date());
		
		Integer id = (Integer) fachada.inserir(servicoRepavimentadora);
		servicoRepavimentadora.setId(id);
		
		// ------------ REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_SERVICO_REPAVIMENTADORA,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_INSERIR_SERVICO_REPAVIMENTADORA);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        
        servicoRepavimentadora.setOperacaoEfetuada(operacaoEfetuada);
        servicoRepavimentadora.adicionarUsuario(usuario, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        
        registradorOperacao.registrarOperacao(servicoRepavimentadora);
        //------------ REGISTRAR TRANSA��O ----------------
		
        sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarServicoRepavimentadoraAction.do");
        
		// M�todo utilizado para montar a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, 
				" Servi�o da Repavimentadora de c�digo " + id +  " inserido com sucesso.",
				"Inserir outro Servi�o", "exibirInserirServicoRepavimentadoraAction.do?menu=sim", 
				"exibirAtualizarServicoRepavimentadoraAction.do?idRegistroInseridoAtualizar=" + id , "Atualizar Servi�o da Repavimentadora");
		
		return retorno;
	}
}
