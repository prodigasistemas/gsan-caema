package gcom.gui.relatorio.faturamento;

import java.util.ArrayList;
import java.util.Collection;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.FiltrarRelatorioSituacaoEspecialFaturamentoHelper;
import gcom.relatorio.faturamento.RelatorioSituacaoEspecialFaturamento;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioSituacaoEspecialFaturamentoAction 
extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno		
		ActionForward retorno = actionMapping.findForward("emitirRelatorioSituacaoEspecialFaturamento");
				
		// Form
		GerarRelatorioSituacaoEspecialFaturamentoActionForm form = 
				(GerarRelatorioSituacaoEspecialFaturamentoActionForm) actionForm;
						
		FiltrarRelatorioSituacaoEspecialFaturamentoHelper filtro = 	
				new FiltrarRelatorioSituacaoEspecialFaturamentoHelper();		
		
		String[] campo = new String[4];
		
		if ((form.getLocalidadeInicialID() != null) && 	
				(form.getLocalidadeInicialID() != "") &&
				(!form.getLocalidadeInicialID().trim().equals(""))){
			campo[0] = ((String)form.getLocalidadeInicialID().toString());
		}
		
		if ((form.getLocalidadeFinalID() != null) && 		
	    		(form.getLocalidadeFinalID() != "") &&
	    		(!form.getLocalidadeFinalID().trim().equals(""))){
			campo[1] = ((String)form.getLocalidadeFinalID().toString());
		}

		if ((form.getSetorComercialInicialID() != null) && 				
				(form.getSetorComercialInicialID() != "") &&
				(!form.getSetorComercialInicialID().trim().equals(""))){
			campo[2] = ((String)form.getSetorComercialInicialID().toString());
		}

		if ((form.getSetorComercialFinalID() != null) && 						
				(form.getSetorComercialFinalID() != "") &&
				(!form.getSetorComercialFinalID().trim().equals(""))){
			campo[3] = ((String)form.getSetorComercialFinalID().toString());
		}
		
		String numeros = "0123456789";
		int contCampo = 0;
    		
		while (contCampo <= 3){
			
			if ((campo[contCampo] != null) && (campo[contCampo] != "") && (!campo[contCampo].trim().equals(""))){
				
			for (int contCaractere = 0; contCaractere < campo[contCampo].length(); contCaractere++){
				if (!(numeros.indexOf(campo[contCampo].charAt(contCaractere), 0) != -1)){
					if (contCampo == 0){
						throw new ActionServletException("atencao.localidade_numeros_positivos", null, "Situação Especial Faturamento");
					} else if (contCampo == 1){
						throw new ActionServletException("atencao.localidade_numeros_positivos", null, "Situação Especial Faturamento");
					} else if (contCampo == 2){
						throw new ActionServletException("atencao.setor_comercial_numeros_positivos", null, "Situação Especial Faturamento");
					} else if (contCampo == 3){
						throw new ActionServletException("atencao.setor_comercial_numeros_positivos", null, "Situação Especial Faturamento");
					}
				}
			}
			}
			contCampo++;
		}
		
		if (((form.getLocalidadeInicialID() != null) &&	
				(!form.getLocalidadeInicialID().trim().equals(""))) &&
				((form.getLocalidadeFinalID() == null) ||
						(form.getLocalidadeFinalID().trim().equals("")))) {
			throw new ActionServletException("atencao.informe_localidade_final", null, "Situação Especial Faturamento");
		} else
			if (((form.getLocalidadeFinalID() != null) &&	
					(!form.getLocalidadeFinalID().trim().equals(""))) &&
					((form.getLocalidadeInicialID() == null) ||
							(form.getLocalidadeInicialID().trim().equals("")))) {
				throw new ActionServletException("atencao.informe_localidade_inicial", null, "Situação Especial Faturamento");
			} else 
				if ((form.getLocalidadeInicialID() != null) &&
						(!form.getLocalidadeInicialID().trim().equals("")) &&
						(form.getLocalidadeFinalID() != null) &&
						(!form.getLocalidadeFinalID().trim().equals("")) &&
						((Integer.valueOf(form.getLocalidadeInicialID().toString())) > 
				(Integer.valueOf(form.getLocalidadeFinalID().toString())))){
					throw new ActionServletException("atencao.localidade_inicial_maior_que_final", null, "Situação Especial Faturamento");
				}
		
		if (((form.getSetorComercialInicialID() != null) &&	
				(!form.getSetorComercialInicialID().trim().equals(""))) &&
				((form.getSetorComercialFinalID() == null) ||
						(form.getSetorComercialFinalID().trim().equals("")))) {
			throw new ActionServletException("atencao.informe_setor_comercial_final", null, "Situação Especial Faturamento");
		} else
			if (((form.getSetorComercialFinalID() != null) &&	
					(!form.getSetorComercialFinalID().trim().equals(""))) &&
					((form.getSetorComercialInicialID() == null) ||
							(form.getSetorComercialInicialID().trim().equals("")))) {
				throw new ActionServletException("atencao.informe_setor_comercial_inicial", null, "Situação Especial Faturamento");
			} else 
				if ((form.getSetorComercialInicialID() != null) &&
						(!form.getSetorComercialInicialID().trim().equals("")) &&
						(form.getSetorComercialFinalID() != null) &&
						(!form.getSetorComercialFinalID().trim().equals("")) &&
						((Integer.valueOf(form.getSetorComercialInicialID().toString())) > 
				(Integer.valueOf(form.getSetorComercialFinalID().toString())))){
					throw new ActionServletException("atencao.setor_comercial_inicial_maior_que_final", null, "Situação Especial Faturamento");
				} else 
					if (((form.getGerenciaRegional() == null) || 
							((form.getGerenciaRegional() != null) && (form.getGerenciaRegional().toString() == "-1"))) && 
							
							((form.getUnidadeNegocio() == null) || 
							((form.getUnidadeNegocio() != null) && (form.getUnidadeNegocio().toString() == "-1"))) &&
							
							((form.getLocalidadeInicialID().toString() == null) || (form.getLocalidadeInicialID().trim().equals(""))) &&
							((form.getLocalidadeFinalID().toString() == null) || (form.getLocalidadeFinalID().trim().equals("")))){
						throw new ActionServletException("atencao.selecione_filtro_localizacao_geografica", null, "Situação Especial Faturamento");
					}
	
		// Gerência Regional		
		if (form.getGerenciaRegional() != null && 		
				!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));			
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
				!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		// Localidade Inicial	
		if (form.getLocalidadeInicialID() != null && 	
				!form.getLocalidadeInicialID().equals("")){			
			filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicialID()));				
		}
			
		    // Localidade Final		
			if (form.getLocalidadeFinalID() != null && 		
		    		!form.getLocalidadeFinalID().equals("")){
			
		    	filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinalID()));		
			}
		
			// Setor Comercial Inicial				
			if (form.getSetorComercialInicialID() != null && 				
					!form.getSetorComercialInicialID().equals("")){
				filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicialID()));				
			}
		
		
			// Setor Comercial Final						
			if (form.getSetorComercialFinalID() != null && 						
					!form.getSetorComercialFinalID().equals("")){		
				filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinalID()));						
			}
			
		// Situação						
		if ((form.getSituacao() != null)){		
			String[] validaSituacao = form.getSituacao();
			if (validaSituacao[0].equals("-1")){
				filtro.setSituacao(null);
			} else {
				Collection<Integer> colecaoSituacao = null;
				colecaoSituacao = new ArrayList<Integer>();
				for (String idSituacao : form.getSituacao()) {
					colecaoSituacao.add(new Integer(idSituacao));
				}			
				filtro.setSituacao(colecaoSituacao);
			}
		} else {
			filtro.setSituacao(null);
		} 
		
		// Motivo						
		if (form.getMotivo() != null){	
			String[] validaMotivo = form.getMotivo();
			if (validaMotivo[0].equals("-1")){
				filtro.setMotivo(null);
			} else {
				Collection<Integer> colecaoMotivo = null;
				colecaoMotivo = new ArrayList<Integer>();
				for (String idMotivo : form.getMotivo()) {
					colecaoMotivo.add(new Integer(idMotivo));
				}
				filtro.setMotivo(colecaoMotivo);
			}		
		} else {
			filtro.setMotivo(null);
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		RelatorioSituacaoEspecialFaturamento relatorio = 
				new RelatorioSituacaoEspecialFaturamento(this.getUsuarioLogado(httpServletRequest));
		relatorio.addParametro("filtrarRelatorioSituacaoEspecialFaturamentoHelper", filtro);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);
		
		return retorno;
	}
}
