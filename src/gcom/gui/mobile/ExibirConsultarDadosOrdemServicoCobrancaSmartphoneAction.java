package gcom.gui.mobile;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.mobile.ExecucaoOSCaixaProtecao;
import gcom.mobile.ExecucaoOSCorte;
import gcom.mobile.ExecucaoOSFiscalizacao;
import gcom.mobile.ExecucaoOSOrdemServico;
import gcom.mobile.ExecucaoOSRemocao;
import gcom.mobile.ExecucaoOSSituacoesEncontradas;
import gcom.mobile.ExecucaoOSSubstituicao;
import gcom.mobile.ExecucaoOSSupressao;
import gcom.mobile.FiltroExecucaoOSCaixaProtecao;
import gcom.mobile.FiltroExecucaoOSCorte;
import gcom.mobile.FiltroExecucaoOSFiscalizacao;
import gcom.mobile.FiltroExecucaoOSOrdemServico;
import gcom.mobile.FiltroExecucaoOSRemocao;
import gcom.mobile.FiltroExecucaoOSSituacoesEncontradas;
import gcom.mobile.FiltroExecucaoOSSubstituicao;
import gcom.mobile.FiltroExecucaoOSSupressao;
import gcom.seguranca.acesso.usuario.Usuario;
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

public class ExibirConsultarDadosOrdemServicoCobrancaSmartphoneAction extends GcomAction {

	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoCobrancaSmartphone");

        //Obtém a instância da sessão        
        HttpSession sessao = this.getSessao(httpServletRequest);
        
        ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form = 
        	(ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm) actionForm;

        String idOrdemServico = "", matricula = "", idArquivo = "";
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("true")) {

        	idOrdemServico = form.getOrdemServico();
        	matricula = form.getMatricula();
        	idArquivo = form.getIdArquivo();
        }
        
        this.carregarCampos(sessao, 
        		httpServletRequest, 
        		form, 
        		idOrdemServico, 
        		matricula,
        		idArquivo);
        
        //Monta o Status do Wizard
        StatusWizard statusWizard = 
    		new StatusWizard(
    		"consultarDadosOrdemServicoCobrancaSmartphoneWizardAction", 
    		"encerrarOrdemServicoIndividualCobrancaSmartphoneAction",
    		null,
    		null);
        
        statusWizard.inserirNumeroPaginaCaminho(
        	statusWizard.new StatusWizardItem(
        			1, 
        			"abaOSA.gif", 
        			"abaOSB.gif",
                    "exibirConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction",
                    "consultarDadosOrdemServicoCobrancaSmartphoneRetornoAction"));
        
        //monta a segunda aba do processo,de foto
        statusWizard.inserirNumeroPaginaCaminho(
        	statusWizard.new StatusWizardItem(
        		2, 
        		"AbaFotosA.gif", 
        		"AbaFotosD.gif",
                "exibirConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction",
                "consultarDadosOrdemServicoCobrancaSmartphoneFotosAction"));

        statusWizard.setNomeBotaoConcluir("Encerrar O.S.");
        
    	statusWizard.setCaminhoActionVoltarFiltro("exibirConsultarOrdemServicoCobrancaSmartphoneAction");
        
        if (sessao.getAttribute("ordemServicoEncerrada") != null && 
        	!sessao.getAttribute("ordemServicoEncerrada").toString().trim().equals("")){
        	
        	statusWizard.setBotaoConcluirDesabilitado("sim");
        } else {
        	statusWizard.setBotaoConcluirDesabilitado("");
        }
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);

        //retorna o mapeamento contido na variável retorno
        return retorno;
    }

    public void carregarCampos( HttpSession sessao, HttpServletRequest httpServletRequest, 
    		ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form, String idOrdemServico, String idImovelConsultado, String idArquivoConsultado) {

    	Fachada fachada = Fachada.getInstancia();
    	
    	Integer idOS = null;
    	
		// 1. Empresa
		Usuario usuario = (Usuario)sessao.getAttribute("usuarioLogado");
		form.setEmpresa( usuario.getEmpresa().getId()+"" );
		form.setDescricaoEmpresa( usuario.getEmpresa().getDescricao() );
		
		// 2. Tipo da Ordem de Servico					
		form.setIdTipoOrdemServico(1+"");
		form.setDescricaoTipoOrdemServico(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);
    	
        // 3. Ordem de Serviço 
        if (httpServletRequest.getParameter("ordemServico") != null
        		&& !httpServletRequest.getParameter("ordemServico").equals("")) {

        	idOS = new Integer(httpServletRequest.getParameter("ordemServico"));
        	form.setOrdemServico(idOS.toString());
        } else if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

        	idOS = new Integer(idOrdemServico);
        	form.setOrdemServico(idOrdemServico);
        }
        
        // 4. Matrícula
        if (httpServletRequest.getParameter("matricula") != null
        		&& !httpServletRequest.getParameter("matricula").equals("")) {
        	
        	Integer idImovel = new Integer(httpServletRequest.getParameter("matricula"));
        	form.setMatricula(idImovel.toString());
        	
        } else if (idImovelConsultado != null && !idImovelConsultado.trim().equals("")) {

        	form.setMatricula(idImovelConsultado);
        }
        
        // 5. Tipo de Servico
        FiltroExecucaoOSOrdemServico filtro = new FiltroExecucaoOSOrdemServico();
        filtro.adicionarCaminhoParaCarregamentoEntidade( "servicoTipo" );
        filtro.adicionarCaminhoParaCarregamentoEntidade( "atendimentoMotivoEncerramento" );
        filtro.adicionarParametro( new ParametroSimples( FiltroExecucaoOSOrdemServico.ID_ORDEM_SERVICO, idOS ) );
        ExecucaoOSOrdemServico os = ( ExecucaoOSOrdemServico )Util.retonarObjetoDeColecao( fachada.pesquisar(filtro, ExecucaoOSOrdemServico.class.getName() ) );
        
        form.setDescricaoServicoTipo( os.getServicoTipo().getDescricao() );
        
        // 6 Tipo de Encerramento
        form.setDescricaoMotivoEncerramento( os.getAtendimentoMotivoEncerramento().getDescricao() );
        
        /*if (os.getAtendimentoMotivoEncerramento().getIndicadorExecucao() == ConstantesSistema.SIM.shortValue()) {
        	httpServletRequest.setAttribute("exibirDebitos", "1");
        }*/
        
        // 7 Data da Execucao
        form.setDataExecucao( Util.formatarData( os.getDataExecucao() ) );
        
        // 8 Hora da Execução
        form.setHoraExecucao( Util.formatarHoraSemData( os.getDataExecucao() ) );
        
        // 9 Parecer
        form.setParecer( os.getParecerEncerramento() );
        
        // 10. id do Arquivo
        Integer idArquivo = null;
        if ( httpServletRequest.getParameter("idArquivo") != null && 
        	!httpServletRequest.getParameter("idArquivo").equals("")) {
        	
        	idArquivo = new Integer( httpServletRequest.getParameter("idArquivo"));
        	form.setIdArquivo(idArquivo.toString());
        	
        } else if ( idArquivoConsultado != null && !idImovelConsultado.trim().equals( "" ) ){
        	form.setIdArquivo(idArquivoConsultado);
        }
        
        if (httpServletRequest.getParameter("calcularValores") == null) {
        	form.setPercentualCobranca("100");
        	form.setQuantidadeParcelas("1");
        }
        
        sessao.removeAttribute("execucaoOSCorte");
        sessao.removeAttribute("execucaoOSSupressao");
        sessao.removeAttribute("execucaoOSFiscalizacao");
        sessao.removeAttribute("colecaoSituacoesEncontradas");
        sessao.removeAttribute("execucaoOSSubstituicao");
        sessao.removeAttribute("execucaoOSRemocao");
        sessao.removeAttribute("execucaoOSCaixaProtecao");
        
        // Adicionamos agora um objeto especifico para cada tipo de OS
        FiltroExecucaoOSCorte filtroCorte = new FiltroExecucaoOSCorte();
        filtroCorte.adicionarParametro( new ParametroSimples( FiltroExecucaoOSCorte.ID_ORDEM_SERVICO, idOS ) );
        filtroCorte.adicionarParametro( new ParametroSimples( FiltroExecucaoOSCorte.ID_ARQUIVO, idArquivo ) );
        filtroCorte.adicionarCaminhoParaCarregamentoEntidade( "motivoCorte" );
        filtroCorte.adicionarCaminhoParaCarregamentoEntidade( "corteTipo" );
        ExecucaoOSCorte osCorte = ( ExecucaoOSCorte )Util.retonarObjetoDeColecao( fachada.pesquisar(filtroCorte, ExecucaoOSCorte.class.getName() ) );
        
        if ( osCorte != null ){
        	form.setDescricaoMotivoCorte( osCorte.getMotivoCorte().getDescricao() );
        	form.setDescricaoTipoCorte( osCorte.getCorteTipo().getDescricao() );
        	form.setLeituraCorte( osCorte.getLeituraCorte()+"" );
        	
        	sessao.setAttribute("execucaoOSCorte", osCorte);
    		
        } else {
	        // Adicionamos agora um objeto especifico para cada tipo de OS
	        FiltroExecucaoOSSupressao filtroSupressao = new FiltroExecucaoOSSupressao();
	        filtroSupressao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSSupressao.ID_ORDEM_SERVICO, idOS ) );
	        filtroSupressao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSSupressao.ID_ARQUIVO, idArquivo ) );
	        filtroSupressao.adicionarCaminhoParaCarregamentoEntidade( "supressaoMotivo" );
	        filtroSupressao.adicionarCaminhoParaCarregamentoEntidade( "supressaoTipo" );
	        ExecucaoOSSupressao osSupressao = ( ExecucaoOSSupressao )Util.retonarObjetoDeColecao( fachada.pesquisar(filtroSupressao, ExecucaoOSSupressao.class.getName() ) );
	        
	        if ( osSupressao != null ){
	        	form.setDescricaoMotivoSupressao( osSupressao.getSupressaoMotivo().getDescricao() );
	        	form.setDescricaoTipoSupressao( osSupressao.getSupressaoTipo().getDescricao() );
	        	form.setLeituraSupressao( osSupressao.getLeituraSupressao()+"" );
	        	
	        	//TODO Verificar como iremos pegar o percentual de cobrança
	        	form.setPercentualCobranca( "100,00%" );
	        	
	        	sessao.setAttribute("execucaoOSSupressao", osSupressao);        	
	        } else {
		        // Adicionamos o objeto específico de os de fiscalização
		        FiltroExecucaoOSFiscalizacao filtroFiscalizacao = new FiltroExecucaoOSFiscalizacao();
		        filtroFiscalizacao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSFiscalizacao.ID_ORDEM_SERVICO, idOS ) );
		        filtroFiscalizacao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSFiscalizacao.ID_ARQUIVO, idArquivo ) );
		        ExecucaoOSFiscalizacao osFiscalizacao = ( ExecucaoOSFiscalizacao )Util.retonarObjetoDeColecao( fachada.pesquisar(filtroFiscalizacao, ExecucaoOSFiscalizacao.class.getName() ) );
		        
		        if ( osFiscalizacao != null ){
		        	// Adicionamos o documento entregue
		        	if ( osFiscalizacao.getCodigoDocumentoEntregue() != null ){
		        		if ( osFiscalizacao.getCodigoDocumentoEntregue().equals( 1 ) ){
		        			form.setDescricaoDocumentoEntregue( "SOL. COMPARECIMENTO" );
		        		} else if ( osFiscalizacao.getCodigoDocumentoEntregue().equals( 2 ) ){
		        			form.setDescricaoDocumentoEntregue( "AUTO DE INFRACAO" );
		        		} else if ( osFiscalizacao.getCodigoDocumentoEntregue().equals( 3 ) ){
		        			form.setDescricaoDocumentoEntregue( "INFRACAO DE ESGOTO" );
		        		}
		        	}
		        	
		        	sessao.setAttribute("execucaoOSFiscalizacao", osFiscalizacao);
		        	
			        FiltroExecucaoOSSituacoesEncontradas filtroSituacoesEncontradas = new FiltroExecucaoOSSituacoesEncontradas();
			        filtroSituacoesEncontradas.adicionarParametro( new ParametroSimples( FiltroExecucaoOSSituacoesEncontradas.ID_ORDEM_SERVICO, idOS ) );
			        filtroSituacoesEncontradas.adicionarCaminhoParaCarregamentoEntidade( "comp_id.fiscalizacaoSituacao" );
			        Collection<?> colSituacoesEncontroladas = (Collection<?>) fachada.pesquisar(filtroSituacoesEncontradas, ExecucaoOSSituacoesEncontradas.class.getName() );
			        
			        if ( colSituacoesEncontroladas != null && colSituacoesEncontroladas.size() > 0 ){
			        	sessao.setAttribute("colecaoSituacoesEncontradas", colSituacoesEncontroladas);
			        }		        	
		        } else {
		        	// [SB0005] Exibir Dados Referentes a Substituição de Hidrômetro
		        	// [IT0010] Obter Dados da Substituição de Hidrômetro 
		        	FiltroExecucaoOSSubstituicao filtroSubstituicao = new FiltroExecucaoOSSubstituicao();
			        filtroSubstituicao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSSubstituicao.ID_ORDEM_SERVICO, idOS));
			        filtroSubstituicao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSSubstituicao.ID_ARQUIVO, idArquivo));
			        filtroSubstituicao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSSubstituicao.HIDROMETRO_SITUACAO);
			        filtroSubstituicao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSSubstituicao.HIDROMETRO_LOCAL_ARMAZENAGEM);
			        filtroSubstituicao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSSubstituicao.HIDROMETRO);
			        ExecucaoOSSubstituicao osSubstituicao = (ExecucaoOSSubstituicao)
			        		Util.retonarObjetoDeColecao(fachada.pesquisar(filtroSubstituicao, ExecucaoOSSubstituicao.class.getName()));
			        
			        if (osSubstituicao != null){
			        	form.setNumeroLeitura(osSubstituicao.getNumeroLeitura().toString());
			        	form.setSituacaoHidrometro(osSubstituicao.getHidrometroSituacao().getDescricao());
			        	form.setLocalArmazenagem(osSubstituicao.getHidrometroLocalArmazenagem().getDescricao());
			        	
			        	if (osSubstituicao.getIndicadorTipoHidrometro().compareTo(ConstantesSistema.SIM) == 0) {
			        		form.setTipoHidrometro("MACROMEDIDOR");
				        	form.setNumeroTombamento(osSubstituicao.getHidrometro().getTombamento());
				        	form.setNumeroHidrometro("");
			        	} else {
			        		form.setTipoHidrometro("MICROMEDIDOR");
				        	form.setNumeroHidrometro(osSubstituicao.getHidrometro().getNumero());
				        	form.setNumeroTombamento("");
			        	}
			        	
			        	if (osSubstituicao.getNumeroLeituraInstalacao() != null) {
			        		form.setNumeroLeituraInstalacao(osSubstituicao.getNumeroLeituraInstalacao().toString());
			        	} else {
			        		form.setNumeroLeituraInstalacao("");
			        	}
			        	
			        	if (osSubstituicao.getNumeroSelo() != null) {
			        		form.setNumeroSelo(osSubstituicao.getNumeroSelo());
			        	} else {
			        		form.setNumeroSelo("");
			        	}
			        	
		        		form.setCavalete(osSubstituicao.getIndicadorCavalete().toString());
		        		
			        	sessao.setAttribute("execucaoOSSubstituicao", osSubstituicao);
			        	
			        } else {
			        	// [SB0006] Exibir Dados Referentes a Remoção de Hidrômetro
			        	// [IT0011] Obter Dados da Remoção de Hidrômetro
			        	FiltroExecucaoOSRemocao filtroRemocao = new FiltroExecucaoOSRemocao();
				        filtroRemocao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSRemocao.ID_ORDEM_SERVICO, idOS));
				        filtroRemocao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSRemocao.ID_ARQUIVO, idArquivo));
				        filtroRemocao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSRemocao.HIDROMETRO_LOCAL_INSTALACAO);
				        filtroRemocao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSRemocao.HIDROMETRO_PROTECAO);
				        ExecucaoOSRemocao osRemocao = (ExecucaoOSRemocao)
				        		Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRemocao, ExecucaoOSRemocao.class.getName()));
				        
				        if (osRemocao != null){
				        	form.setLocalInstalacaoHidrometro(osRemocao.getHidrometroLocalInstalacao().getDescricao());
				        	form.setProtecaoHidrometro(osRemocao.getHidrometroProtecao().getDescricao());
			        		form.setCavalete(osRemocao.getIndicadorCavalete().toString());
			        		
				        	sessao.setAttribute("execucaoOSRemocao", osRemocao);
				        	
				        } else {
				        	// [SB0007] Exibir Dados Referentes a Instalação Caixa de Proteção
				        	// [IT0012] Obter Dados da Instalação Caixa de Proteção
				        	FiltroExecucaoOSCaixaProtecao filtroCaixaProtecao = new FiltroExecucaoOSCaixaProtecao();
					        filtroCaixaProtecao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCaixaProtecao.ID_ORDEM_SERVICO, idOS));
					        filtroCaixaProtecao.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCaixaProtecao.ID_ARQUIVO, idArquivo));
					        filtroCaixaProtecao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSCaixaProtecao.HIDROMETRO_LOCAL_INSTALACAO);
					        filtroCaixaProtecao.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSCaixaProtecao.HIDROMETRO_PROTECAO);
					        ExecucaoOSCaixaProtecao osCaixaProtecao = (ExecucaoOSCaixaProtecao)
					        		Util.retonarObjetoDeColecao(fachada.pesquisar(filtroCaixaProtecao, ExecucaoOSCaixaProtecao.class.getName()));
					        
					        if (osCaixaProtecao != null){
					        	form.setLocalInstalacaoHidrometro(osCaixaProtecao.getHidrometroLocalInstalacao().getDescricao());
					        	form.setProtecaoHidrometro(osCaixaProtecao.getHidrometroProtecao().getDescricao());
				        		form.setTrocaProtecao(osCaixaProtecao.getIndicadorTrocaProtecao().toString());
				        		form.setTrocaRegistro(osCaixaProtecao.getIndicadorTrocaRegistro().toString());
				        		
					        	sessao.setAttribute("execucaoOSCaixaProtecao", osCaixaProtecao);
					        }
				        }
			        }
		        }
		    }
        }
    }
}
