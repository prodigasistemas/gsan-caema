package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1221] � Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class ExibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2 extends GcomAction{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2");
		GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form = (GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2) formulario;
		HttpSession sessao = request.getSession();

		ComandoOrdemSeletiva comandoOS = null;
		
		@SuppressWarnings("unchecked")
		List<ComandoOrdemSeletiva> comandosOS = (List<ComandoOrdemSeletiva>) sessao.getAttribute("comandosOS");
		
		if(comandosOS.size() == 1){
			comandoOS = comandosOS.get(0);
		}

		//Verifica se houve mudan�as nos filtros, para recarregar novas cole��es.
		String mudouGerencia 	= request.getParameter("mudouGerencia");
		String mudouRegiao   	= request.getParameter("mudouRegiao");
		String mudouMicroregiao = request.getParameter("mudouMicroregiao");
		String mudouLocalidade  = request.getParameter("mudouLocalidade");
		String desfazer = request.getParameter("desfazer");
		
		//Caso tenha mudado a ger�ncia, carregar as unidades de neg�cio associadas � ger�ncia selecionada
		if(Util.verificarNaoVazio(mudouGerencia) && mudouGerencia.equalsIgnoreCase("sim")){
			if(Util.verificarIdNaoVazio(form.getIdGerencia())){
				sessao.setAttribute("filtroGerenciaLocalidade", true);
			}else{
				if(!(Util.verificarIdNaoVazio(form.getIdLocalidade()) && Util.verificarNaoVazio(form.getNomeLocalidade()))){
					sessao.removeAttribute("filtroGerenciaLocalidade");
				}
			}
			this.pesquisarUnidadeNegocio(form, comandoOS, sessao, form.getIdGerencia());
		//Caso tenha mudado a regi�o carregar as microregi�es associadas � regi�o selecionada
		}else if(Util.verificarNaoVazio(mudouLocalidade) && mudouLocalidade.equalsIgnoreCase("sim")){
			this.pesquisarLocalidade(form, request, sessao, comandoOS);
		}else if(Util.verificarNaoVazio(mudouRegiao) && mudouRegiao.equalsIgnoreCase("sim")){
			if(Util.verificarIdNaoVazio(form.getIdRegiao())){
				sessao.setAttribute("filtroRegiao", true);
				sessao.removeAttribute("filtroGerenciaLocalidade");
			}else{
				sessao.removeAttribute("filtroRegiao");
			}
			sessao.removeAttribute("colecaoMunicipio");
			form.setIdMicroregiao("-1");
			this.pesquisarMicroregiao(sessao, form.getIdRegiao());
		//Caso tenha mudado a microregi�o carregar os munic�pios associados � regi�o selecionada
		}else if(Util.verificarNaoVazio(mudouMicroregiao) && mudouMicroregiao.equalsIgnoreCase("sim")){
			this.pesquisarMunicipio(sessao, form.getIdMicroregiao());
			form.setIdMunicipio("-1");
		}else if(Util.verificarNaoVazio(desfazer) && desfazer.equalsIgnoreCase("sim")){
			limparCampos(form, comandoOS, sessao);
			
		}else{			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));
			if(comandoOS != null){
				sessao.setAttribute("periodoGeracao", sdf.format(comandoOS.getDataGeracao()));
			}
			form.setComandosOS(comandosOS);
			limparCampos(form, comandoOS, sessao);
			this.pesquisarGerencia(form, comandoOS, sessao);
			this.pesquisarLocalidade(form, request, sessao, comandoOS);	
			this.pesquisarRegiao(sessao);
		}
		
		return retorno;
	}
	
	private void limparCampos(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form, ComandoOrdemSeletiva comandoOS, HttpSession sessao){
		if(comandoOS == null || comandoOS.getGerenciaRegional() == null){
			form.setIdGerencia("-1");
			form.setIdUnidadeNegocio("-1");
			form.setIdLocalidade("");
			form.setNomeLocalidade("");
			form.setIdRegiao("");
			sessao.removeAttribute("filtroRegiao");
			sessao.removeAttribute("filtroGerenciaLocalidade");
			sessao.removeAttribute("colecaoMicroregiao");
			sessao.removeAttribute("colecaoMunicipio");
			sessao.removeAttribute("possuiGerencia");
			sessao.removeAttribute("possuiUnidade");
			
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = (Collection<UnidadeNegocio>) sessao.getAttribute("colecaoUnidade");
			if(colecaoUnidadeNegocio != null)
				colecaoUnidadeNegocio.clear();
			sessao.setAttribute("colecaoUnidade", colecaoUnidadeNegocio);
		}
		else{
			if(comandoOS.getUnidadeNegocio() == null){
				form.setIdUnidadeNegocio("-1");
			}
			
			form.setIdLocalidade("");
			form.setNomeLocalidade("");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));
		form.setPeriodoApuracao(sdf.format(new Date()));
	}
	
	/**
	 * M�todo auxiliar para pesquisar a localidade pelo ID (digitado pelo usu�rio)
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarLocalidade(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form, HttpServletRequest request,
			HttpSession sessao, ComandoOrdemSeletiva comandoOS){

		String idLocalidade = form.getIdLocalidade();

		//Verifica se o ID passado pelo usu�rio � v�lido para a pesquisa
		if (Util.verificarIdNaoVazio(idLocalidade)){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			/*
			 * Verifica se a localidade foi encontrada. Caso tenha sido encontrada, o nome da localidade
			 * � configurado. Caso contr�rio, o nome de LOCALIDADE INEXISTENTE � configurado e o 
			 * atributo para exibir a descri��o em vermelho � configurado.
			 */
			if (!Util.isVazioOrNulo(colecaoLocalidade)){
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());
				sessao.setAttribute("filtroGerenciaLocalidade", true);
			}else{
				form.setIdLocalidade("");
				form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
				request.setAttribute("localidadeInexistente", true);
				//Caso o �nico filtro tenha sido a localidade, desabilitar apenas o filtroGeranciaLocalidade
				if(!Util.verificarIdNaoVazio(form.getIdGerencia())){
					sessao.removeAttribute("filtroGerenciaLocalidade");
				}
			}
			request.setAttribute("nomeCampo", "idLocalidade");
		}else{
			form.setNomeLocalidade("");
		}
	}
	
	/**
	 * M�todo auxiliar para pesquisar todas as ger�ncias cadastradas
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarGerencia(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form,
			ComandoOrdemSeletiva comandoOS, HttpSession sessao){

		Collection<GerenciaRegional> colecaoGerencia = new ArrayList<GerenciaRegional>();
		String idGerencia = "";
		if(comandoOS != null && comandoOS.getGerenciaRegional() != null){
			idGerencia = comandoOS.getGerenciaRegional().getId().toString();
			colecaoGerencia.add(comandoOS.getGerenciaRegional());
			form.setIdGerencia(idGerencia);
			sessao.setAttribute("colecaoGerencia", colecaoGerencia);
			sessao.setAttribute("possuiGerencia", true);
			sessao.setAttribute("filtroGerenciaLocalidade", true);
		}else{
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, 1));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			colecaoGerencia = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("colecaoGerencia", colecaoGerencia);
		}
		this.pesquisarUnidadeNegocio(form, comandoOS, sessao, form.getIdGerencia());
	}
	
	/**
	 * M�todo auxiliar para pesquisar a unidade de neg�cio
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	public void pesquisarUnidadeNegocio(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form, 
			ComandoOrdemSeletiva comandoOS, HttpSession sessao, String idGerencia){
		Collection<UnidadeNegocio> colecaoUnidade = new ArrayList<UnidadeNegocio>();
		if(comandoOS != null && comandoOS.getUnidadeNegocio() != null){
			colecaoUnidade.add(comandoOS.getUnidadeNegocio());
			form.setIdUnidadeNegocio(comandoOS.getUnidadeNegocio().getId().toString());
			sessao.setAttribute("colecaoUnidade", colecaoUnidade);
			sessao.setAttribute("possuiUnidade", true);
		}else{
			if(Util.verificarIdNaoVazio(idGerencia)){
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, idGerencia));
				colecaoUnidade = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			}
			sessao.setAttribute("colecaoUnidade", colecaoUnidade);
		}
	}
	
	/**
	 * M�todo auxiliar para pesquisar TODAS as regi�es
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	private void pesquisarRegiao(HttpSession sessao){
		Collection<Regiao> colecaoRegiao = null;
		FiltroRegiao filtroRegiao = new FiltroRegiao();
		filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);
		colecaoRegiao = Fachada.getInstancia().pesquisar(filtroRegiao, Regiao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoRegiao)){
			sessao.setAttribute("colecaoRegiao", colecaoRegiao);
		}
	}
	
	/**
	 * M�todo auxiliar para pesquisar TODAS as microregi�es
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	public void pesquisarMicroregiao(HttpSession sessao, String idRegiao){
		Collection<Microrregiao> colecaoMicroregiao = null;
		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		filtroMicrorregiao.adicionarParametro(new ParametroSimples(FiltroMicrorregiao.REGIAO_ID, idRegiao));
		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
		colecaoMicroregiao = Fachada.getInstancia().pesquisar(filtroMicrorregiao, Microrregiao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMicroregiao)){
			sessao.setAttribute("colecaoMicroregiao", colecaoMicroregiao);
		}else{
			sessao.removeAttribute("colecaoMicroregiao");
			sessao.removeAttribute("colecaoMunicipio");
		}
	}
	
	/**
	 * M�todo auxiliar para pesquisar TODAS os munic�pios
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	public void pesquisarMunicipio(HttpSession sessao, String idMicroregiao){
		Collection<Municipio> colecaoMunicipio = null;
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.MICRORREGICAO_ID, idMicroregiao));
		filtroMunicipio.setCampoOrderBy(FiltroMunicipio.NOME);
		colecaoMunicipio = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMunicipio)){
			sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		}else{
			sessao.removeAttribute("colecaoMunicipio");
		}
	}
}