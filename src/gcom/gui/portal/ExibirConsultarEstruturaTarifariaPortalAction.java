package gcom.gui.portal;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1194] Consultar Estrutura Tarif�ria Loja Virtual
 * 
 * Classe respons�vel por configurar as cole��es da estrutura
 * tarif�ria para serem exibidas na tela 
 * informacoes_estrutura_tarifaria_portal_consultar.jsp
 * 
 * @author Diogo Peixoto
 * @since 14/07/2011
 *
 */
public class ExibirConsultarEstruturaTarifariaPortalAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarEstruturaTarifariaPortalAction");
		HttpSession sessao = request.getSession(false);
		request.setAttribute("voltarInformacoes", true);
		
		//Verifica se os downloads dos arquivos est�o dispon�veis.
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection<SistemaParametro> colecaoSistemaParametro = this.getFachada().pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = colecaoSistemaParametro.iterator().next();
		this.setarDownloadsLoja(sistemaParametro, request);
		
		String download = (String)request.getParameter("download");
		if(Util.verificarNaoVazio(download)){
			this.retornaArquivo(download, response, sistemaParametro, request);
		}
		
		String ip = request.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ESTRUTURA_TARIFARIA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		
		//Consumos N�o medidos
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> consumosNaoMedidos = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		ConsultarEstruturaTarifariaPortalHelper consumoNaoMedido = null;
		
		//Consumos Medidos Residencial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL);
		if(!Util.isVazioOrNulo(helperResidencial)){
			request.setAttribute("helperResidencial", helperResidencial);
			
			//------------------------- Carregando os consumos n�o medidos (Residencial) -------------------------//
			//Recupera os dados referentes ao consumo da tarifa social
			consumoNaoMedido = helperResidencial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper(
						"Residencial Tarifa Social", "por m�s", consumoNaoMedido.getValor(), 2));
			}
			
			//Recupera os dados referentes ao consumo da tarifa m�nima
			consumoNaoMedido = helperResidencial.get(1);
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Residencial", "por m�s", consumoNaoMedido.getValor(), 2));
			}
			//------------------------- Fim do carregar os consumos n�o medidos (Residencial) -------------------------//
		}
		
		//Consumos Medidos Comercial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperComercial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL);
		if(!Util.isVazioOrNulo(helperComercial)){
			request.setAttribute("helperComercial", helperComercial);
		
			//------------------------- Carregando os consumos n�o medidos (Residencial) -------------------------//
			consumoNaoMedido = helperComercial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Comercial", "por m�s", consumoNaoMedido.getValor(), 2));
			}
		}
		
		//Consumos Medidos Industrial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperIndustrial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL);
		if(!Util.isVazioOrNulo(helperIndustrial)){
			request.setAttribute("helperIndustrial", helperIndustrial);
			//------------------------- Carregando os consumos n�o medidos (Industrial) -------------------------//
			consumoNaoMedido = helperIndustrial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Industrial", "por m�s", consumoNaoMedido.getValor(), 2));
			}

		}
		
		//Consumos Medidos P�blica
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperPublica = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO);
		if(!Util.isVazioOrNulo(helperPublica)){
			request.setAttribute("helperPublica", helperPublica);
			
			//------------------------- Carregando os consumos n�o medidos (Publico) -------------------------//
			consumoNaoMedido = helperPublica.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("P�blico", "por m�s", consumoNaoMedido.getValor(), 2));
			}
		}
		
		//------------------------- Carregando os consumos n�o medidos  -------------------------//
		FiltroDebitoTipo filtroDebitoTipo  = new FiltroDebitoTipo();
		filtroDebitoTipo.adicionarParametro( new ParametroSimples(FiltroDebitoTipo.ID, new Integer(103)));
		
		Collection<DebitoTipo> colecaoDebitoTipo = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		String valorSugerido = "";
		if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty() ) {
			DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
			if (debitoTipo.getValorSugerido() != null ) {
				valorSugerido = debitoTipo.getValorSugerido().toString().replace(".", ",") ;
			}
		}
		
		/*
		 * RM 7925
		 * Alterado por Raphael Rossiter em 18/09/2012
		 * 
		 */
		//consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Fornecimento por Carros-pipa ", "por 1.000L", valorSugerido, 2));
		consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Fornecimento por Carros-pipa ", "por 1.000L", helperIndustrial.get(1).getValor(), 2));
		ConsultarEstruturaTarifariaPortalHelper  helperChafariz = this.getFachada().pesquisarEstruturaTarifariaChafarizPublico(); 
		consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Fornecimento por Carros-pipa �rg�os P�blicos  ", "por 1.000L", helperChafariz.getValor(), 2));
		consumosNaoMedidos.add(this.getFachada().pesquisarEstruturaTarifariaChafarizPublico());
		
		request.setAttribute("consumosNaoMedidos", consumosNaoMedidos);
		
		/*
		 * Serve para inicializar as tarifas de �gua bruta. O �ndice de consumo para a tarifa de �gua bruta
		 * � 3. Necessita iterar pelos 3 helper (Residencial, Comercial, Industrial)
		 */
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperAguaBruta = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		helperAguaBruta = this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.RESIDENCIAL);
		helperAguaBruta.addAll(this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.COMERCIAL));
		helperAguaBruta.addAll(this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.INDUSTRIAL));
		request.setAttribute("helperAguaBruta", helperAguaBruta);
		
		/*
		 * Vai colocar na sess�o o bean que ser� neces�rio para gerar o ralat�rio,
		 * caso o usu�rio selecione para gerar o relat�rio. 
		 */
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> estruturaTarifariaBeans = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		estruturaTarifariaBeans.addAll(helperResidencial);
		estruturaTarifariaBeans.addAll(helperComercial);
		estruturaTarifariaBeans.addAll(helperIndustrial);
		estruturaTarifariaBeans.addAll(helperPublica);
		estruturaTarifariaBeans.addAll(consumosNaoMedidos);
		estruturaTarifariaBeans.addAll(helperAguaBruta);
		sessao.setAttribute("estruturaTarifariaBeans", estruturaTarifariaBeans);
		
		return retorno;
	}

	//Verifica a existencias dos arquivos no banco	
	private void setarDownloadsLoja(SistemaParametro sistemaParametro, HttpServletRequest request){
		if(sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0){
			request.setAttribute("leiEstadual", true);
			if(Util.verificarNaoVazio(sistemaParametro.getDescricaoLeiEstTarif())){
				request.setAttribute("labelLeiEstadual", sistemaParametro.getDescricaoLeiEstTarif());
			}
		}
		
		if(sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0){
			request.setAttribute("decretoEstadual", true);
			if(Util.verificarNaoVazio(sistemaParametro.getDescricaoDecreto())){
				request.setAttribute("labelDecretoEstadual", sistemaParametro.getDescricaoDecreto());
			}
		}
	}
	
	//N�todo respons�vel por retornar o arquivo para o usu�rio caso ele aperte no link do pdf.
	private void retornaArquivo(String arquivo, HttpServletResponse response, SistemaParametro sistemaParametro, HttpServletRequest request){
		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;
		
		if(arquivo.equalsIgnoreCase("decretoEstadual")){
			file = sistemaParametro.getArquivoDecreto();
		}else if(arquivo.equalsIgnoreCase("leiEstadual")){
			file = sistemaParametro.getArquivoLeiEstTarif();
		}
		
		try {
			response.setContentType(mimeType);
			out = response.getOutputStream();
			out.write(file);
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			request.setAttribute("arquivoNaoEncontrado", true);
		}
		
	}
}