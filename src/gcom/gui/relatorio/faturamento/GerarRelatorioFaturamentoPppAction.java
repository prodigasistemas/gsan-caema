package gcom.gui.relatorio.faturamento;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.financeiro.FiltrarResumoFaturamentoPpp;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1481] Gerar Relatório de Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 29/05/2013
 */
public class GerarRelatorioFaturamentoPppAction extends
	ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
	
		GerarRelatorioResumoFaturamentoPppActionForm form = (GerarRelatorioResumoFaturamentoPppActionForm) actionForm;
		FiltrarResumoFaturamentoPpp filtroResumoFaturamentoPpp = new FiltrarResumoFaturamentoPpp();
		
		Fachada fachada = Fachada.getInstancia();
		
		String mesAno = form.getMesAno();
		Integer gerenciaRegional = null;
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		String opcaoTotalizacaoSelected = httpServletRequest.getParameter("opcaoTotalizacaoSelected");
		if(mesAno == null || mesAno.equals("")){
			throw new ActionServletException("atencao.required", null,
			"Mês/Ano da Arrecadação");
		}
		if (opcaoTotalizacao == null || opcaoTotalizacao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Opção de Totalização ");
		}
		if (opcaoTotalizacao.trim().equals("gerenciaRegional")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Gerência Regional");
			}else{
				filtroResumoFaturamentoPpp.setIdGerenciaRegional(String.valueOf(gerenciaRegional));
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalLocalidade")) {
			gerenciaRegional = Integer.parseInt(form
					.getGerenciaRegionalporLocalidadeId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Gerência Regional");
			}else{
				filtroResumoFaturamentoPpp.setIdGerenciaRegional(String.valueOf(gerenciaRegional));
			}
		}
		if (opcaoTotalizacao.trim().equals("localidade")) {
			String codigoLocalidade = form.getCodigoLocalidade();
			if (codigoLocalidade == null
					|| codigoLocalidade.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Localidade ");
			} else {
				filtroResumoFaturamentoPpp.setIdLocalidade(codigoLocalidade);
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				Localidade localidade = new Localidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(filtroLocalidade.ID, codigoLocalidade));
				Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
				Iterator iteratorLocalidade = colecaoLocalidade.iterator();
				while(iteratorLocalidade.hasNext()){
					localidade = (Localidade)iteratorLocalidade.next();
					opcaoTotalizacaoSelected = localidade.getId()+" - "+localidade.getDescricao();
				}
			}
		}
		if (opcaoTotalizacao.trim().equals("municipio")) {
			String codigoMunicipio = form.getCodigoMunicipio();
			if (codigoMunicipio == null || codigoMunicipio.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null, "Município ");
			} else {
				filtroResumoFaturamentoPpp.setIdMunicipio(String.valueOf(codigoMunicipio));
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
				Municipio municipio = new Municipio();
				filtroMunicipio.adicionarParametro(new ParametroSimples(filtroMunicipio.ID, codigoMunicipio));
				Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());
				Iterator iteratorMunicipio = colecaoMunicipio.iterator();
				while(iteratorMunicipio.hasNext()){
					municipio = (Municipio) iteratorMunicipio.next();
					opcaoTotalizacaoSelected = municipio.getId()+" - "+municipio.getNome();
				}
			}
		}
		if (opcaoTotalizacao.trim().equals("unidadeNegocio")) {
			String idUnidadeNegocio = form.getUnidadeNegocioId();
			if (idUnidadeNegocio == null
					|| idUnidadeNegocio
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade de Negócio ");
			}else{
				filtroResumoFaturamentoPpp.setIdUnidadeNegocio(form.getUnidadeNegocioId());
			}
		}
		if(opcaoTotalizacao.trim().equals("subSistemaEsgoto")){
			filtroResumoFaturamentoPpp.setIdSubSistemaEsgoto(form.getSubSistemaEsgotoId());
		}
		if(opcaoTotalizacao.trim().equals("sistema")){
			filtroResumoFaturamentoPpp.setIdSistema(form.getSistemaEsgotoId());			
		}
		
		String mesAnoString = mesAno.substring(3, 7)
				+ mesAno.substring(0, 2);
		filtroResumoFaturamentoPpp.setAnoMes(mesAnoString);
		filtroResumoFaturamentoPpp.setOpcaoTotalizacao(opcaoTotalizacao);
		
		// Verificar se o Ano/Mês está valido [FS0002]
		if (!Util.validarMesAno(mesAno)) {
			// Se deu algum erro neste ponto, indica falha no sistema
			throw new ActionServletException("erro.sistema");
		}
	
		
		// [FS0002] Verificar referência do faturamento
		fachada.verificarReferenciaFaturamentoCorrente(mesAno);
	
		// Parte que vai mandar o relatório para a tela
	
		// cria uma instância da classe do relatório
		RelatorioFaturamentoPpp relatorioFaturamentoPpp = new RelatorioFaturamentoPpp(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioFaturamentoPpp.addParametro("filtroResumoFaturamentoPpp", filtroResumoFaturamentoPpp);
		relatorioFaturamentoPpp.addParametro("nomeBusca", opcaoTotalizacaoSelected);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioFaturamentoPpp.addParametro("opcaoTotalizacao", opcaoTotalizacao);
		relatorioFaturamentoPpp.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioFaturamentoPpp,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
	
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
	
		}
	
		return retorno;
	}
}
