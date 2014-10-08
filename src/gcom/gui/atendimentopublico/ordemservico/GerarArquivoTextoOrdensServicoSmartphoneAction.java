package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.mobile.ParametrosArquivoTextoOSCobranca;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction{
	
	private Fachada fachada = Fachada.getInstancia();	
	private GerarArquivoTextoOrdensServicoSmartphoneActionForm form = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		form = (GerarArquivoTextoOrdensServicoSmartphoneActionForm) actionForm;
		
		String comando = null;
		String identificadorOS = null;
		
		if(form.getIdComandoCorrecaoAnormalidade() != null)
			comando = form.getIdComandoCorrecaoAnormalidade().toString();
		
		ParametrosArquivoTextoOSCobranca parametros = criandoObjeto();
		
		//Recuperando a lista das OS selecionadas
		Collection<Object[]> listaOSSelecionadas = criarListaOSSelecionadas(form.getIdsRegistros(),form.getColecaoOS());		
		
		fachada.inserirParametrosArquivoTextoOSCobranca(parametros, form.getIdsServicoTipo(),listaOSSelecionadas,comando, form.getIdTipoOS());
		
		montarPaginaSucesso(httpServletRequest, "Arquivo Texto Criado com Sucesso.", "", "");
		
		return retorno;
	}
	

	/**
	 * 
	 * Método para selecionar as os a partir da opção do usuário
	 * 
	 * @param idsRegistros - Registros selecionados na tela principal
	 * @param colecaoOS - Todas as OSs selecionadas pelo filtro
	 * @return Coleção das OSs do filtro selecionadas
	 */
	private Collection<Object[]> criarListaOSSelecionadas(
			String[] idsRegistros, Collection<GerarArquivoTxtSmartphoneHelper> colecaoOS) {
	
		Collection<Object[]> retorno = new ArrayList<Object[]>();
		if(idsRegistros == null)
			return null;
		
		for(String id : idsRegistros){
			Iterator it = colecaoOS.iterator();
			while(it.hasNext()){
				GerarArquivoTxtSmartphoneHelper helper = (GerarArquivoTxtSmartphoneHelper)it.next();
				if(helper.getIdOs().equals(id))
						retorno.add(helper.getOs());
			}
		}
		
		return retorno;
	}

	private ParametrosArquivoTextoOSCobranca criandoObjeto(){
		
		ParametrosArquivoTextoOSCobranca parametros = new ParametrosArquivoTextoOSCobranca();
		
		Empresa empresa = new Empresa();
		empresa.setId(Integer.valueOf(form.getEmpresa()));
		parametros.setEmpresa(empresa);
		
		
		if(form.getIdTipoOS() != null && form.getIdTipoOS().equals(ConstantesSistema.IDENTIFICADOR_TIPO_OS_MICROMEDICAO.toString())){
			parametros.setCodigoTipoOS(Integer.parseInt(form.getIdTipoOS()));
		}
		else{
			parametros.setCodigoTipoOS(Integer.parseInt(ConstantesSistema.IDENTIFICADOR_TIPO_OS_COBRANCA.toString()));
		}
		
		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			
			GerenciaRegional gerenciaRegional = new GerenciaRegional();
			gerenciaRegional.setId(Integer.valueOf(form.getGerenciaRegional()));
			parametros.setGerenciaRegional(gerenciaRegional);
		}
		
		if (form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio.setId(Integer.valueOf(form.getUnidadeNegocio()));
			parametros.setUnidadeNegocio(unidadeNegocio);
		}
		
		if (form.getLocalidade() != null && !form.getLocalidade().equals("")){
			
			Localidade localidade = new Localidade();
			localidade.setId(Integer.valueOf(form.getLocalidade()));
			parametros.setLocalidade(localidade);
		}
		
		if (form.getCodigoSetorComercialOrigem() != null && !form.getCodigoSetorComercialOrigem().equals("")){
			
			parametros.setCodigoSetorComercialInicial(Integer.parseInt(form.getCodigoSetorComercialOrigem()));
			parametros.setCodigoSetorComercialFinal(Integer.parseInt(form.getCodigoSetorComercialOrigem()));
		}
		
		if (form.getCodigoSetorComercialDestino() != null && !form.getCodigoSetorComercialDestino().equals("")){
			
			parametros.setCodigoSetorComercialFinal(Integer.parseInt(form.getCodigoSetorComercialDestino()));
		}
		
		if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().equals("")){
			
			parametros.setNumeroQuadraInicial(Integer.parseInt(form.getCodigoQuadraInicial()));
			parametros.setNumeroQuadraFinal(Integer.parseInt(form.getCodigoQuadraInicial()));
		}
		
		if (form.getCodigoQuadraFinal() != null && !form.getCodigoQuadraFinal().equals("")){
			
			parametros.setNumeroQuadraFinal(Integer.parseInt(form.getCodigoQuadraFinal()));
		}
		
		if(form.getIdComandoCorrecaoAnormalidade() != null && !form.getIdComandoCorrecaoAnormalidade().equals("")){
			ComandoOrdemSeletiva coss = new ComandoOrdemSeletiva();
			coss.setId(form.getIdComandoCorrecaoAnormalidade());
			parametros.setComandoOSSeletiva(coss);
		}
		else{
			parametros.setComandoOSSeletiva(null);
		}
		
		
		return parametros;
	}	
}
