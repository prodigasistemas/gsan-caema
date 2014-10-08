package gcom.gui.portal;

import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.FiltroPessoaSexo;
import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por inserir o cliente no portal
 * 
 * @author Arthur Carvalho
 * @date 13/12/2011
 */
public class ExibirInserirClientePortalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirClientePortalAction");
				
		HttpSession sessao = httpServletRequest.getSession();
		
		InserirClientePortalActionForm form = (InserirClientePortalActionForm) actionForm;
		
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo(FiltroFoneTipo.DESCRICAO);
		filtroFoneTipo.adicionarParametro( new ParametroSimples(FiltroFoneTipo.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<FoneTipo> foneTipo = (Collection<FoneTipo>) Fachada.getInstancia().pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		httpServletRequest.setAttribute("tipoTelefone", foneTipo);
		
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
			
			Integer matricula = new Integer(form.getMatricula());
			//exibe o endereco formatado na tela.
			enviarEnderecoFormatado(matricula , sessao);
			//Primeira vez carrega a tela como pessoa fisica - indicador = 1
			habilitarCamposTipoPessoa("1", sessao, httpServletRequest,form);
		} else {
			//exibe o endereco formatado na tela.
			enviarEnderecoFormatado(new Integer(form.getMatricula()) , sessao);
		}
		//Exibe os campos a serem preenchidos de acordo com o tipo de pessoa(fisica ou juridica)
		if ( httpServletRequest.getParameter("tipoPessoa") != null ) {
			String tipoPessoa = (String)  httpServletRequest.getParameter("tipoPessoa");
			habilitarCamposTipoPessoa(tipoPessoa, sessao, httpServletRequest,form);
			
		} else if ( form.getIndicadorTipoPessoa() != null ) {

			habilitarCamposTipoPessoa(form.getIndicadorTipoPessoa(), sessao, httpServletRequest,form);
		} else if (httpServletRequest.getAttribute("tipoPessoa") != null ) {
			String tipoPessoa = (String) httpServletRequest.getAttribute("tipoPessoa");
			
			habilitarCamposTipoPessoa(tipoPessoa, sessao, httpServletRequest,form);
		}
		
		Collection<ClienteVirtual> colClienteVirtual = Fachada.getInstancia().pesquisarSituacaoClienteCadastradoPortal(new Integer(form.getMatricula()));
		
		if(!Util.isVazioOrNulo(colClienteVirtual)){
			ClienteVirtual clienteVirtual = (ClienteVirtual) Util.retonarObjetoDeColecao(colClienteVirtual);
			desabilitarCampos(httpServletRequest, clienteVirtual, form);
		}
			
		return retorno;
	}
	
	private void desabilitarCampos(HttpServletRequest httpServletRequest, ClienteVirtual clienteVirtual, InserirClientePortalActionForm form) {
		if(clienteVirtual != null){
			HttpSession sessao = httpServletRequest.getSession();
			
			httpServletRequest.setAttribute("bloquearCpf", true);
			httpServletRequest.setAttribute("bloquearIndicadorPessoa", true);
			
			if(clienteVirtual.getIndicadorPessoaFisicaJuridica().equals(ConstantesSistema.INDICADOR_PESSOA_FISICA) ){ 
				if(clienteVirtual.getCpf() != null){
					form.setCpf(clienteVirtual.getCpf());
					form.setIndicadorTipoPessoa("1");
					httpServletRequest.setAttribute("pessoa", "1");
					
					habilitarCamposTipoPessoa(form.getIndicadorTipoPessoa(), sessao, httpServletRequest, form);
				}
			}
			else if(clienteVirtual.getIndicadorPessoaFisicaJuridica().equals(ConstantesSistema.INDICADOR_PESSOA_JURIDICA)){ 
				if(clienteVirtual.getCnpj() != null){
					form.setCnpj(clienteVirtual.getCnpj());
					form.setIndicadorTipoPessoa("2");
					httpServletRequest.setAttribute("pessoa", "2");
					
					habilitarCamposTipoPessoa(form.getIndicadorTipoPessoa(), sessao, httpServletRequest, form);
				}
			}
		}
	}

	/**
	 * Metodo responsavel por habilitar os campos referentes a pessoa juridica ou pessoa fisica.
	 * @author Arthur Carvalho
	 * @param tipoPessoa
	 * @param sessao
	 */
	private void habilitarCamposTipoPessoa(String tipoPessoa, HttpSession sessao, HttpServletRequest httpServletRequest,InserirClientePortalActionForm form) {
		
		if ( tipoPessoa.equals("1") ) {
			//Pessoa Fisica
			httpServletRequest.setAttribute("tipoPessoaFisica", true);
			httpServletRequest.removeAttribute("tipoPessoaJuridica");
			
			FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA);
			filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<OrgaoExpedidorRg> orgaosExpedidores = Fachada.getInstancia().pesquisar(filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());
			sessao.setAttribute("orgaosExpedidores", orgaosExpedidores);
			
			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao(FiltroUnidadeFederacao.SIGLA);
			Collection<UnidadeFederacao> unidadesFederacao = Fachada.getInstancia().pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
			sessao.setAttribute("unidadesFederacao", unidadesFederacao);
			
			FiltroProfissao filtroProfissao = new FiltroProfissao(FiltroProfissao.DESCRICAO);
			filtroProfissao.adicionarParametro(new ParametroSimples(FiltroProfissao.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<Profissao> profissao = (Collection<Profissao>) Fachada.getInstancia().pesquisar(filtroProfissao, Profissao.class.getName());
			sessao.setAttribute("profissao", profissao);
			
			FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo(FiltroPessoaSexo.DESCRICAO);
			filtroPessoaSexo.adicionarParametro(new ParametroSimples(FiltroPessoaSexo.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<PessoaSexo> pessoaSexo = Fachada.getInstancia().pesquisar(filtroPessoaSexo, PessoaSexo.class.getName());
			sessao.setAttribute("pessoaSexo", pessoaSexo);
			
			limparForm(form, tipoPessoa);
			
		} else if ( tipoPessoa.equals("2") ) {
			
			httpServletRequest.setAttribute("tipoPessoaJuridica", true);
			httpServletRequest.removeAttribute("tipoPessoaFisica");
			
			
			FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade(FiltroRamoAtividade.DESCRICAO);
			filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<RamoAtividade> ramoAtividade = Fachada.getInstancia().pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());
			sessao.setAttribute("ramoAtividade", ramoAtividade);
			
			limparForm(form, tipoPessoa);
			
			
		} else {
			httpServletRequest.removeAttribute("tipoPessoaJuridica");
			httpServletRequest.removeAttribute("tipoPessoaFisica");
		}
	}
	
	/**
	 * Metodo responsavel por enviar o endereco formatado para ser exibido na tela.
	 * 
	 * @param matricula
	 * @param sessao
	 */
	private void enviarEnderecoFormatado(Integer matricula, HttpSession sessao) {
		String enderecoFormatado = "";
		try {
			enderecoFormatado = Fachada.getInstancia().pesquisarEnderecoFormatado(matricula);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	
		sessao.setAttribute("enderecoFormatado", enderecoFormatado);
	}
	
	/**
	 * Metodo responsavel por limpar o formulario.
	 * @param form
	 */
	private void limparForm(InserirClientePortalActionForm form, String tipoPessoa) {
		
		if ( tipoPessoa.equals("1") ) {
			form.setCnpj("");
			form.setRamoAtividade("");
		} else if (tipoPessoa.equals("2")) {
			form.setCpf("");
			form.setDataEmissao("");
			form.setOrgaoExpedidor("");
			form.setProfissao("");
			form.setRg("");
			form.setSexo("");
			form.setEstado("");
			form.setDataNascimento("");
			form.setNomeMae("");
		}
		
		
	
		
	
	}

}