package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
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

/**
 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
 * 
 * @author Mariana Victor
 * @date 20/08/2013
 * */
public class ExibirGerarRelatorioComandosConexaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioComandosConexaoEsgoto");

        HttpSession sessao = httpServletRequest.getSession(false);
        
		GerarRelatorioComandosConexaoEsgotoActionForm form =
				(GerarRelatorioComandosConexaoEsgotoActionForm) actionForm;
		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		
		if (objetoConsulta != null) {
			if(objetoConsulta.equals("pesquisarImovel")) {
				//[FE0002] - Validar Município
				this.pesquisarImovel(form, httpServletRequest);
			} else if(objetoConsulta.equals("pesquisarMunicipio")) {
				//[FE0002] - Validar Município
				this.pesquisarMunicipio(form, httpServletRequest);
			} else if(objetoConsulta.equals("pesquisarLogradouro")) {
				//[FE0003] - Validar Logradouro 
				this.pesquisarLogradouro(form, httpServletRequest);
			} else if(objetoConsulta.equals("pesquisarLocalidadeInicial") || objetoConsulta.equals("pesquisarLocalidadeFinal")) {
				//[FE0004] - Verificar Existência Localidade 
				this.pesquisarLocalidade(form, httpServletRequest, objetoConsulta);
			} else if(objetoConsulta.equals("pesquisarSetorComercialInicial") || objetoConsulta.equals("pesquisarSetorComercialFinal")) {
				//[FE0005] - Verificar existência do Setor Comercial
				this.pesquisarSetorComercial(form, httpServletRequest, objetoConsulta);
			} else if(objetoConsulta.equals("pesquisarSetorComercialInicial") || objetoConsulta.equals("pesquisarSetorComercialFinal")) {
				//[FE0005] - Verificar existência do Setor Comercial
				this.pesquisarSetorComercial(form, httpServletRequest, objetoConsulta);
			} else if(objetoConsulta.equals("pesquisarQuadraInicial") || objetoConsulta.equals("pesquisarQuadraFinal")) {
				//[FE0006] - Verificar existência da quadra
				this.pesquisarQuadra(form, httpServletRequest, objetoConsulta);
			}
		}
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").trim().equalsIgnoreCase("sim")) {
			
			//[SB0009] - Selecionar Grupo de Faturamento
			this.pesquisarGrupoFaturamento(form, sessao);
			
			form.setIndicadorExecucao(
				String.valueOf(GerarRelatorioComandosConexaoEsgotoActionForm.COMPESA));
			form.setTipoRelatorioSinteticoAnalitico(
				String.valueOf(ConstantesSistema.SIM));
		}
		
		return retorno;
	}
	
	public void pesquisarImovel(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpServletRequest httpServletRequest){
		Fachada fachada = Fachada.getInstancia();
		String inscricao = fachada.pesquisarInscricaoImovel(
			new Integer(form.getIdImovel()));
		
		if(inscricao == null || inscricao.trim().equals("")){
			form.setIdImovel("");
			form.setDescricaoImovel("IMÓVEL INEXISTENTE");
			httpServletRequest.setAttribute("imovelInexistenteException", true);
		}else{
			form.setDescricaoImovel(inscricao);
		}
	}
		
	public void pesquisarMunicipio(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpServletRequest httpServletRequest){
		Fachada fachada = Fachada.getInstancia();
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
			FiltroMunicipio.ID, form.getIdMunicipio()));
		filtroMunicipio.adicionarParametro(new ParametroSimples(
			FiltroMunicipio.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
			Municipio municipio = (Municipio)Util.retonarObjetoDeColecao(colecaoMunicipio);
			form.setIdMunicipio(String.valueOf(municipio.getId()));
			form.setDescricaoMunicipio(municipio.getNome());
		}else{
			form.setIdMunicipio("");
			form.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
			httpServletRequest.setAttribute("municipioInexistenteException", true);
		}
	}
	
	public void pesquisarLogradouro(GerarRelatorioComandosConexaoEsgotoActionForm 
			form,HttpServletRequest httpServletRequest){
		Fachada fachada = Fachada.getInstancia();
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarParametro(new ParametroSimples(
			FiltroLogradouro.ID, form.getIdLogradouro()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
			FiltroLogradouro.ID_MUNICIPIO, form.getIdMunicipio()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
			FiltroLogradouro.INDICADORUSO, ConstantesSistema.SIM));
		Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		if(colecaoLogradouro != null && !colecaoLogradouro.isEmpty()){
			Logradouro logradouro = (Logradouro)Util.retonarObjetoDeColecao(colecaoLogradouro);
			form.setIdLogradouro(String.valueOf(logradouro.getId()));
			form.setDescricaoLogradouro(logradouro.getNome());
		}else{
			form.setIdLogradouro("");
			form.setDescricaoLogradouro("LOGRADOURO INEXISTENTE");
			httpServletRequest.setAttribute("logradouroInexistente", true);
		}
	}
	
	public void pesquisarLocalidade(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpServletRequest httpServletRequest,String objetoConsulta){
		Fachada fachada = Fachada.getInstancia();
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		String localidadeInicialFinal = form.getIdLocalidadeInicial();
		
		if(objetoConsulta.equals("pesquisarLocalidadeFinal")){
			localidadeInicialFinal = form.getIdLocalidadeFinal();
		}
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID, localidadeInicialFinal));
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
		Collection<Localidade> colecaoLocalidade  = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			if(objetoConsulta.equals("pesquisarLocalidadeFinal")){
				form.setIdLocalidadeFinal(String.valueOf(localidade.getId()));
				form.setDescricaoLocalidadeFinal(localidade.getDescricao());
			}else{
				form.setIdLocalidadeInicial(String.valueOf(localidade.getId()));
				form.setDescricaoLocalidadeInicial(localidade.getDescricao());	
				form.setIdLocalidadeFinal(String.valueOf(localidade.getId()));
				form.setDescricaoLocalidadeFinal(localidade.getDescricao());
			}
		}else{
			if(objetoConsulta.equals("pesquisarLocalidadeFinal")){
				form.setIdLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);
			}else{
				form.setIdLocalidadeInicial("");
				form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
				form.setIdLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("");
				httpServletRequest.setAttribute("localidadeInicialInexistente", true);
			}
		}
	}
	
	public void pesquisarSetorComercial(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpServletRequest httpServletRequest,String objetoConsulta){
		Fachada fachada = Fachada.getInstancia();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		String idLocalidade = form.getIdLocalidadeInicial();
		String codigoSetorComercial = form.getCodigoSetorComercialInicial();
		
		if(objetoConsulta.equals("pesquisarSetorComercialFinal")){
			codigoSetorComercial = form.getCodigoSetorComercialFinal();
			idLocalidade = form.getIdLocalidadeFinal();
		}
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.LOCALIDADE_ID, idLocalidade));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
		Collection<SetorComercial> colecaoSetorComecial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(colecaoSetorComecial != null && !colecaoSetorComecial.isEmpty()){
			SetorComercial setorComercial = (SetorComercial)Util.retonarObjetoDeColecao(colecaoSetorComecial);
			if(objetoConsulta.equals("pesquisarSetorComercialFinal")){
				form.setCodigoSetorComercialFinal(String.valueOf(setorComercial.getCodigo()));
				form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
			}else{
				form.setCodigoSetorComercialInicial(String.valueOf(setorComercial.getCodigo()));
				form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
				form.setCodigoSetorComercialFinal(String.valueOf(setorComercial.getCodigo()));
				form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
			}
		}else{
			if(objetoConsulta.equals("pesquisarSetorComercialFinal")){
				form.setCodigoSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialFinalInexistente", true);
			}else{
				form.setCodigoSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				form.setCodigoSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				httpServletRequest.setAttribute("setorComercialInicialInexistente", true);
			}
		}
	}
	
	public void pesquisarQuadra(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpServletRequest httpServletRequest,String objetoConsulta){
		Fachada fachada = Fachada.getInstancia();
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		String numeroQuadra = form.getQuadraInicial();
		String codigoSetorComercial = form.getCodigoSetorComercialInicial();
		
		if(objetoConsulta.equals("pesquisarQuadraFinal")){
			numeroQuadra = form.getQuadraFinal();
			codigoSetorComercial = form.getCodigoSetorComercialFinal();
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercial));
		filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.NUMERO_QUADRA, numeroQuadra));
		filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.INDICADORUSO, ConstantesSistema.SIM));
		Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
		
		if(colecaoQuadra == null || colecaoQuadra.isEmpty()){
			if(objetoConsulta.equals("pesquisarQuadraFinal")){
				form.setQuadraFinal("");
				httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");
			}else{
				form.setQuadraInicial("");
				form.setQuadraFinal("");
				httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
			}
		}
	}
	
	public void pesquisarGrupoFaturamento(GerarRelatorioComandosConexaoEsgotoActionForm
			form,HttpSession sessao){
		Fachada fachada = Fachada.getInstancia();
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
			FiltroFaturamentoGrupo.DESCRICAO);
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada.pesquisar(
			filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		if (colecaoFaturamentoGrupo != null
				&& !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		} else {
			sessao.removeAttribute("colecaoFaturamentoGrupo");
		}
	}
	
}
