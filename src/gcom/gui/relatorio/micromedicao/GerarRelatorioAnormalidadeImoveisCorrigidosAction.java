package gcom.gui.relatorio.micromedicao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;

public class GerarRelatorioAnormalidadeImoveisCorrigidosAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
				// Seta o mapeamento de retorno
				ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction");
				
				// Form
				GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form = 
					(GerarRelatorioAnormalidadeImoveisCorrigidosActionForm) actionForm;
				
				FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper filtro = 
					new FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper();
				
				// Gerência Regional
				if (form.getIdGerenciaRegional() != null && 
					!form.getIdGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
					
					filtro.setIdGerenciaRegional(new String(form.getIdGerenciaRegional()));
				}
				
				// Unidade de Negocio
				if (form.getIdUnidadeNegocio()!= null && 
					!form.getIdUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
					
					filtro.setIdUnidadeNegocio(new String(form.getIdUnidadeNegocio()));
				}
					
				// Localidade Inicial
				if (form.getLocalidadeInicial() != null && 
					!form.getLocalidadeInicial().equals("") ) {
						
					filtro.setLocalidadeInicial(new String(form.getLocalidadeInicial()));
				}
				
				// Setor Comercial Inicial
				if (form.getSetorComercialInicial() != null && 
					!form.getSetorComercialInicial().equals("") ) {
						
					filtro.setSetorComercialInicial(new String(form.getSetorComercialInicial()));
				}

				// Localidade Final
				if (form.getLocalidadeFinal() != null && 
					!form.getLocalidadeFinal().equals("") ) {
						
					filtro.setLocalidadeFinal(new String(form.getLocalidadeFinal()));
				}

				// Setor Comercial Final
				if (form.getSetorComercialFinal() != null && 
					!form.getSetorComercialFinal().equals("") ) {
						
					filtro.setSetorComercialFinal(new String(form.getSetorComercialFinal()));
				}
				
				
				//Período Inicial
				if(form.getPeriodoDiaMesAnoInicial()!=null && !form.getPeriodoDiaMesAnoInicial().equals("")){
					
					filtro.setPeriodoDiaMesAnoInicial(new String(form.getPeriodoDiaMesAnoInicial()));
					
				}
				
				//Período Final
				
				if(form.getPeriodoDiaMesAnoFinal() !=null && !form.getPeriodoDiaMesAnoFinal().equals("")){
					
					filtro.setPeriodoDiaMesAnoFinal(new String(form.getPeriodoDiaMesAnoFinal()));
					
					
				}
				
				//Anormalidade
				
				if(form.getIdsAnormalidadeInformada() !=null && !form.getIdsAnormalidadeInformada().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
					String colecaoIdsAnormalidade[] = form.getIdsAnormalidadeInformada();
					String colecaoRepositorioAnormalidade="";
					
					for(int a=0;colecaoIdsAnormalidade.length>a;a++){
							
							if(colecaoIdsAnormalidade.length-1==a){
								
								colecaoRepositorioAnormalidade +=colecaoIdsAnormalidade[a];
								
							}else if(colecaoIdsAnormalidade.length>(a+1)) {
								
								colecaoRepositorioAnormalidade +=colecaoIdsAnormalidade[a]+",";
								
							}
						
							
						
					}
					
					
					filtro.setIdsAnormalidadeInformada(colecaoRepositorioAnormalidade);
					
				}
				
				
				httpServletRequest.setAttribute("telaSucessoRelatorio", true);
				
				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				
				RelatorioAnormalidadeImoveisCorrigidos relatorio = 
					new RelatorioAnormalidadeImoveisCorrigidos(this.getUsuarioLogado(httpServletRequest));
				
				relatorio.addParametro("filtrarRelatorioAnormalidadeImoveisCorrigidosHelper", filtro);
				if (tipoRelatorio  == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}

				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				
				retorno = 
					processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);

				return retorno;
	}
}
