package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1598] Gerar Relatório Cobrança Por Resultado Comando/Mês
 * 
 * @author Ana Maria
 * @date 28/04/2014
 */
public class ExibirGerarRelatorioCobrancaPorResultadoComandoMesAction extends GcomAction {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioCobrancaPorResultadoComandoMes");
        
        Fachada fachada = Fachada.getInstancia();
        
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	GerarRelatorioCobrancaPorResultadoComandoMesActionForm form = (GerarRelatorioCobrancaPorResultadoComandoMesActionForm) actionForm;
    	
    	//Indicador de bloqueio 
    	String indicador = (String)httpServletRequest.getParameter("indicador");
    	
    	//Indicador do campo que realizou o reload da página
    	String campo = (String)httpServletRequest.getParameter("campo");
    	
    	//Campo de Região selecionado
    	if(campo != null && campo.equals("2")){
    		form.setIdsMunicipio(null);
    		form.setIdsMicroregiao(null);
    	}
    	
    	//Campo de microrregião selecionado
    	else if(campo != null && campo.equals("3")){
    		form.setIdsMunicipio(null);
    	}
    	
    	
    	if(indicador != null && !"".equals(indicador))
    		httpServletRequest.setAttribute("indicadorBloqueio", indicador);
        
        //Coleções da tela
        //=======================================
        
    	//Empresa
		if(sessao.getAttribute("colecaoEmpresasContratadas") == null){
			Collection colecaoEmpresasContratadas = fachada.obterColecaoEmpresasContratadasCobranca();
			sessao.setAttribute("colecaoEmpresasContratadas", colecaoEmpresasContratadas);
		}
		
		
        //Comando
		if(sessao.getAttribute("colecaoComando") == null || campo != null && (campo.equals("4") || campo.equals("0"))){
			if((form.getIdEmpresaContratada()!= null && !form.getIdEmpresaContratada().equals("")) && 
				(form.getPeriodoComandoInicial()!= null && !form.getPeriodoComandoInicial().equals("")) &&
				(form.getPeriodoComandoFinal()!= null && !form.getPeriodoComandoFinal().equals(""))){
		       Date dataInicialExecucao = Util.converteStringParaDate(form.getPeriodoComandoInicial());
		       Date dataFinalExecucao =  Util.converteStringParaDate(form.getPeriodoComandoFinal());
		        //Caso a data inicial ou a data final informadas não sejam datas válidas
		        if(!validarData(form.getPeriodoComandoInicial())){
		        	throw new ActionServletException(
							"atencao.data_invalida","Inicial de Execução do Comando");
		        }
		        if(!validarData(form.getPeriodoComandoFinal())){
		        	throw new ActionServletException(
							"atencao.data_invalida","Final de Execução do Comando");
		        }		        
		        
		        //Caso a data final informada seja menor que a data inicial informada
		        if(Util.compararData(dataInicialExecucao, dataFinalExecucao) == 1){
		        	throw new ActionServletException(
							"atencao.data_final_menor_data_inicial");
		        }
		       Collection colecaoComando = fachada.obterColecaoComandoEmpresaCobrancaConta(form.getIdEmpresaContratada(),
		    	   dataInicialExecucao, dataFinalExecucao);
	        sessao.setAttribute("colecaoComando", colecaoComando);
			}else{
				sessao.removeAttribute("colecaoComando");
			}
		}
    	
    	//Categoria
		if(sessao.getAttribute("colecaoCategoria") == null){
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		
			Collection colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		}
        
        //Gerência Regional
		if(sessao.getAttribute("colecaoGerenciaRegional") == null){
	        Collection colecaoGerencia = fachada.obterColecaoGerenciaRegional();
	        sessao.setAttribute("colecaoGerenciaRegional", colecaoGerencia);
		}
				
        //Unidade de negócio
		if(sessao.getAttribute("colecaoUnidadeNegocio") == null || campo != null && campo.equals("1")){
	        Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio(form.getIdsGerenciaRegional());
	        sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
		
        //Região
		if(sessao.getAttribute("colecaoRegiao") == null){
	        Collection colecaoRegiao = fachada.obterColecaoRegioes();
	        sessao.setAttribute("colecaoRegiao", colecaoRegiao);
		}
		
        //Microregião
		if(sessao.getAttribute("colecaoMicroregiao") == null || campo != null && campo.equals("2")){
	        Collection colecaoMicroregiao = fachada.obterColecaoMicroRegioes(form.getIdsRegiao());
	        sessao.setAttribute("colecaoMicroregiao", colecaoMicroregiao);
		}
		
        //Municipio
		if(sessao.getAttribute("colecaoMunicipio") == null || campo != null && (campo.equals("2") || campo.equals("3"))){
	        Collection colecaoMunicipio = fachada.obterColecaoMunicipios(form.getIdsRegiao(),form.getIdsMicroregiao());
	        sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		}
      		
  		//Busca da Localidade através do Enter
  		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
  		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){
  			Integer idLocalidade = new Integer(form.getIdLocalidade());
  			
  			//[FS0002] - Verificar existência da localidade
  			Localidade localidade = fachada.pesquisarLocalidadeDigitada(idLocalidade);
  			
  			if(localidade != null){
				
				localidade = null;
				
				//[FS0003] - Verificar localidade relacionada à gerência regional e unidade de negócio informada
				if(form.getIdsUnidadeNegocio() != null && form.getIdsUnidadeNegocio().length > 0){
					
					//Caso tenha(m) sido informada(s) unidade(s) de negócio
					localidade = fachada.obterColecaoLocalidade(idLocalidade, null,form.getIdsUnidadeNegocio());
					
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{				
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
						throw new ActionServletException("atencao.localidade_nao_pertence_unidade_negocio");
					}
				
				}
				else if(form.getIdsGerenciaRegional() != null && form.getIdsGerenciaRegional().length > 0){
					//Caso não tenha sido informada nenhuma unidade de negócio, mas tenha(m) sido informada(s) gerência(s) regional(ais)
					localidade = fachada.obterColecaoLocalidade(idLocalidade, form.getIdsGerenciaRegional(),null);
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
						throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_regional");
					}
				}
				else{
					localidade = fachada.obterColecaoLocalidade(idLocalidade, null,null);
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{
		  				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
					}
				}
				
			}
			else{
				sessao.removeAttribute("localidadeBoletimMedicao");
  				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
  				form.setIdLocalidade("");
  				httpServletRequest.setAttribute("localidadeException","ok");
			}
  			
  		}
  		
  		//Pesquisar Data de encerramento da Arrecadação
  		String pesquisarDataEncerramento = httpServletRequest.getParameter("pesquisarDataEncerramento");
  		if(pesquisarDataEncerramento != null && !"".equals(pesquisarDataEncerramento)){
  			
  			
  	        if(!this.validarMesAno(form.getPeriodoApuracao()))
  	        	throw new ActionServletException("atencao.data_invalida", null,form.getPeriodoApuracao());
  			
  			
  			//Somando um mês ao período de referência
  			String mesSomado = Util.somaMesMesAnoComBarra(form.getPeriodoApuracao(), 1);
  			
  			//Número do Processo
  			Integer idProcesso = new Integer(50);
  			
  			Date dataEncerramento = fachada.obterDataEncerramentoProcesso(idProcesso, mesSomado);
  			
  			if(dataEncerramento != null){
  				form.setEncerramentoArrecadacao(Util.formatarData(dataEncerramento));
  			}
  			else {
  				form.setEncerramentoArrecadacao("");
  		      	//throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
  			}
  
  		}
        
        
        return retorno;
    }
    
    
    private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12 || !barra.equals("/")) {
					mesAnoValido = false;
				}
			} catch (NumberFormatException e) {
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}
    
    //Método pra validar a data
    private boolean validarData(String inDate) {
		if (inDate == null) {
			return false;	
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;	
		}
		
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
			} catch (ParseException pe) {
				return false;
		}
		return true;
	}
    
}
