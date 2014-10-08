<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript formName="InformarSistemaParametrosActionForm"
	dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>

	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel) {
			return true;
		} else {
			return  validateRequired(form) && 
				validateEmail(form) && 
				validateLong(form) &&
				validateInteger(form);
		}
	}
	
	function IntegerValidations () {
		var form = document.forms[0];
			
		this.aa = new Array("diasMaximoReativarRA", "Dias Máximo para Reativar RA deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("diasMaximoAlterarOS", "Dias Máximo para Alterar Dados da OS deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("ultimoIDGeracaoRA", "Último ID Utilizado para Geração do RA Manual deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("diasMaximoExpirarAcesso", "Dias Máximo para Expirar o Acesso deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diasMensagemExpiracaoSenha", "Dias para Começar Aparecer a	Mensagem de Expiração de Senha deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroMaximoTentativasAcesso", "Número Máximo de Tentativas de	Acesso deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroMaximoFavoritosMenu", "Número Máximo de Favoritos no	Menu do Sistema deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroDiasEncerramentoOrdemServico", "Número de Dias para Encerramento da OS deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("numeroDiasEncerramentoOSSeletiva", "Número de Dias para Encerramento da OS Seletiva deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("numeroDiasAlteracaoVencimentoPosterior", "Quantidade de dias de prorrogação do vencimento na retificação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		

		if(form.diasVencimentoCertidaoNegativa.value==""){
			this.ak = new Array("diasVencimentoCertidaoNegativa", "Número dias de Vencimento para gerar Certidão Negativa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		}

		this.al = new Array("ultimoDiaVencimentoAlternativo", "Ultimo Dia do Vencimento Alternativo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.am = new Array("diasAtualizarLigacaoAguaImovelFisc", "Número de Dias Limite para Atualizar a Situação da Ligação de Água do Imóvel Fiscalizado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.an = new Array("consumoAguaFixadoAnormalidadeHidro", "Consumo de água fixado para anormalidade hidrômetro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ao = new Array("qtdMesIstalSubistituicaoHidro", "Quantidade de meses da instalação e/ou substituição de hidrômetro  deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ap = new Array("qtdVezIncidenciaAnormalidadeHidro", "Quantidade de vezes da incidência de anormalidade de hidrômetro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("numeroDiasRevisaoConta", "Prazo para Revisão de Conta(nº dias após vencimento) deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ar = new Array("idUnidadeDestinoGrandeConsumidor", "Unidade de destino para imóveis com perfil de grande consumidor deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

		if(form.qtdeDiasValidadeOSFiscalizacao.value==""){
			this.as = new Array("qtdeDiasValidadeOSFiscalizacao", "Número de dias para validade ordem de fiscalização deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		}

		if(form.qtdeDiasEncerraOSFiscalizacao.value==""){
			this.at = new Array("qtdeDiasEncerraOSFiscalizacao", "Número máximo de dias para uma ordem de serviço ser fiscalizada deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		}		
			
		this.au = new Array("qtdeDiasEnvioEmailConta", "Número de dias para envio de conta por email deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.av = new Array("periodoRevalidarSenha", "Periodo de revalidação de senhas dos usuários subordinados deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ax = new Array("diasRevalidarSenha", "Quantidade de dias aviso para revalidar acesso deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aw = new Array("numeroDiasBloqueiaSenha", "Número mínimo de dias para bloqueio da senha deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		if(form.numeroLimiteOSCobranca.value!=""){
			this.aw = new Array("numeroLimiteOSCobranca", "Número máximo de OS por arquivo, para o sistema de cobrança via Smartphone", new Function ("varName", " return this[varName];"));
		}
	}

	function email () {
		this.aa = new Array("emailResponsavel", "E-Mail do Responsável inválido.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		this.aa = new Array("diasMaximoAlterarOS", "Informe Dias Máximo para Alterar Dados da OS.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("ultimoIDGeracaoRA", "Informe Último ID Utilizado para Geração do RA Manual.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("ipServidorSmtp", "Informe IP do Servidor SMTP.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("emailResponsavel", "Informe E-mail do Responsável.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("ipServidorGerencial", "Informe IP do Servidor Gerencial.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroDiasEncerramentoOrdemServico", "Informe Número de Dias para Encerramento da OS.", new Function ("varName", " return this[varName];"));		
		this.ah = new Array("numeroDiasAlteracaoVencimentoPosterior", "Informe Quantidade de dias de prorrogação do vencimento na retificação.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("diasAtualizarLigacaoAguaImovelFisc", "Informe Número de Dias Limite para Atualizar a Situação da Ligação de Água do Imóvel Fiscalizado.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("consumoAguaFixadoAnormalidadeHidro", "Informe Consumo de água fixado para anormalidade hidrômetro.", new Function ("varName", " return this[varName];"));
		this.al = new Array("qtdMesIstalSubistituicaoHidro", "Informe Quantidade de meses da instalação e/ou substituição de hidrômetro.", new Function ("varName", " return this[varName];"));
		this.am = new Array("qtdVezIncidenciaAnormalidadeHidro", "Informe Quantidade de vezes da incidência de anormalidade de hidrômetro.", new Function ("varName", " return this[varName];"));
		this.an = new Array("indicadorSugestaoTramite", "Informe Indicador de sugestão de tramite na empresa .", new Function ("varName", " return this[varName];"));
		this.ao = new Array("indicadorControleTramitacaoRA", "Informe Indicador de controle de autorização para a tramitação do RA .", new Function ("varName", " return this[varName];"));
		this.bp = new Array("indicadorBloqFuncInstalacaoSubtHidrometro", "Informe Indicador para bloquear funcionalidades de Instalação/Substituição de hidrômetro.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("indicadorValidacaoLocalidadeEncerramentoOS", "Informe Indicador de Validação da Localidade no Encerramento da OS Seletiva.", new Function ("varName", " return this[varName];"));
		this.ar = new Array("indicadorCertidaoNegativaEfeitoPositivo", "Informe Indicador Certidão Negativa com Efeito Positivo.", new Function ("varName", " return this[varName];"));
		this.as = new Array("diasVencimentoCertidaoNegativa", "Informe Número dias de Vencimento para gerar Certidão Negativa:.", new Function ("varName", " return this[varName];"));
		this.at = new Array("indicadorDocumentoValido", "Informe Indicador de Documento obrigatório para 2ª via da Conta:.", new Function ("varName", " return this[varName];"));
		this.au = new Array("indicadorPermiteCancelarDebito", "Informe Exigir RA no cancelamento do débito:.", new Function ("varName", " return this[varName];"));
		this.av = new Array("indicarControleExpiracaoSenhaPorGrupo", "Informe Indicador de controle de dias de expiração de senha por Grupo.", new Function ("varName", " return this[varName];"));
		this.ax = new Array("indicarControleBloqueioSenha", "Informe Indicador de controle de bloqueio de senhas usadas anteriormente.", new Function ("varName", " return this[varName];"));
		this.az = new Array("indicadorSenhaForte", "Informe Indicador de controle de senha forte.", new Function ("varName", " return this[varName];"));
		this.ba = new Array("indicadorPermissaoEspecialGrupo", "Informe Indicador de permissão especial para o grupo.", new Function ("varName", " return this[varName];"));
		this.bb = new Array("indicadorModuloSeguranca", "Informe Indicador de módulo de seguranca.", new Function ("varName", " return this[varName];"));
		this.bc= new Array("qtdeDiasEncerraOSFiscalizacao", "Informe Número máximo de dias para uma ordem de serviço ser fiscalizada .", new Function ("varName", " return this[varName];"));
		this.bd = new Array("qtdeDiasValidadeOSFiscalizacao", "Informe Número de dias para validade ordem de fiscalização.", new Function ("varName", " return this[varName];"));
		this.be = new Array("numeroDiasBloqueiaSenha", "Informe Número mínimo de dias para bloqueio da senha.", new Function ("varName", " return this[varName];"));
		this.bf = new Array("diasMaximoReativarRA", "Informe Dias Máximo para Reativar RA.", new Function ("varName", " return this[varName];"));
		this.bg = new Array("numeroMaximoFavoritosMenu", "Informe Número Máximo de Favoritos no Menu.", new Function ("varName", " return this[varName];"));
		this.bh = new Array("numeroDiasEncerramentoOSSeletiva", "Informe Número de Dias para Encerramento da OS Seletiva.", new Function ("varName", " return this[varName];"));
		this.aw = new Array("numeroLimiteOSCobranca", "Número máximo de OS por arquivo, para o sistema de cobrança via Smartphone", new Function ("varName", " return this[varName];"));
				
	}
	


	
	function limparUnidade() {
    	var form = document.forms[0];

      	form.idUnidadeDestinoGrandeConsumidor.value = "";
      	form.nomeUnidadeDestinoGrandeConsumidor.value = "";
  	}
  	
	function limparPesquisaDescricaoUnidade() {
    	var form = document.forms[0];
      	form.nomeUnidadeDestinoGrandeConsumidor.value = "";
  	}


	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    

	    if (tipoConsulta == 'unidadeOrganizacional') {	    	
			form.idUnidadeDestinoGrandeConsumidor.value = codigoRegistro;
			form.nomeUnidadeDestinoGrandeConsumidor.value = descricaoRegistro;
			form.nomeUnidadeDestinoGrandeConsumidor.style.color = "#000000";
	   }
		   

	}
	
	function adicionarArquivo(tipoArquivo){
	
		var form = document.forms[0];
		
		form.target = "";
		form.action = "informarParametrosSistemaWizardAction.do?destino=5&action=informarParametrosSistemaDadosGeraisEmpresaAction&adicionar=OK&tipoArquivo="+tipoArquivo;
	
		retorno = true;
	
		var campoArquivo;
		var descricaoArquivo;
		var msgCampo;
		var msgDescricao;
		
		switch (tipoArquivo){
			case 1:
				msgCampo = "Arquivo do Decreto"
				campo = form.arquivoDecreto;
				descricaoArquivo = form.descricaoDecreto;
		    	break;
		   	case 2:
		   		msgCampo = "Arquivo da Lei"
		   		campo = form.arquivoLeiTarifaria;
				descricaoArquivo = form.descricaoLeiTarifaria;
		    	break;
		}	
		
		if(campo.value.length == 0){
			alert("Informe "+msgCampo);
			campo.focus();
			retorno = false;

		}
	
		if (retorno){
			form.submit();
		}	
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'idServicoTipo') {      		
      		form.idServicoTipo.value = codigoRegistro;
	  		form.desServicoTipo.value = descricaoRegistro;
	  		form.idServicoTipo.style.color = "#000000";
			form.desServicoTipo.style.color = "#000000";
		}
	}

	function limparServicoTipo(){
		var form = document.forms[0];
		form.idServicoTipo.value = "";
		form.desServicoTipo.value = "";
	}

	function limparServicoTipoDescricao(){
		var form = document.forms[0];
		form.desServicoTipo.value="";
	}	
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/informarParametrosSistemaWizardAction"
		method="post"
		onsubmit="return validateInformarSistemaParametrosActionForm(this);"
		enctype="multipart/form-data">

		<jsp:include
			page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<input type="hidden" name="numeroPagina" value="5" />
		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="150" valign="top" class="leftcoltext">
					<div align="center">
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>

						<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>

						<%@ include file="/jsp/util/mensagens.jsp"%>

						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
					</div>
				</td>
				<td width="655" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Informar Parâmetros do Sistema</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td>Para informar parâmetros do sistema, informe os dados
								abaixo:
							<td align="right"><a
								href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
									style="font-weight: bold"><font color="#3165CE">Ajuda</font>
								</span> </a></td>
						</tr>
					</table>

					<table width="100%" border="0">


						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><strong>Parâmetros
									para Atendimento ao Público:</strong></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Sugestão de
									Trâmite:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorSugestaoTramite" value="1" /> Sim <html:radio
										property="indicadorSugestaoTramite" value="2" /> N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Controle de
									Autorização para a Tramitação do RA:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorControleTramitacaoRA" value="1" /> Sim <html:radio
										property="indicadorControleTramitacaoRA" value="2" />
									N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Calculo da
									Data Prevista do RA em Dias Úteis:</strong></td>
							<td><strong> <html:radio
										property="indicadorCalculoPrevisaoRADiasUteis" value="1" />
									Sim <html:radio property="indicadorCalculoPrevisaoRADiasUteis"
										value="2" /> N&atilde;o </strong>
							</td>
						</tr>



						<tr>
							<td width="40%" align="left"><strong>Dias Máximo
									para Reativar RA:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="diasMaximoReativarRA" size="2" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias Máximo
									para Alterar Dados da OS:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="diasMaximoAlterarOS" size="2" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número de Dias
									para Encerramento da OS:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="numeroDiasEncerramentoOrdemServico" size="2"
									maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número de Dias
									para Encerramento da OS Seletiva:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="numeroDiasEncerramentoOSSeletiva" size="2"
									maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Validação da
									Localidade no Encerramento da OS Seletiva:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorValidacaoLocalidadeEncerramentoOS"
										value="1" /> Sim <html:radio
										property="indicadorValidacaoLocalidadeEncerramentoOS"
										value="2" /> N&atilde;o </strong>
							</td>
						</tr>
						
						<tr>
							<td width="40%"><strong>Indicador para exibir o botão imprimir na tela de sorteio:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorImprimirExtratoSorteio"
										value="1" /> Sim <html:radio
										property="indicadorImprimirExtratoSorteio"
										value="2" /> N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Quantidade de
									dias de prorrogação do vencimento na retificação:<font
									color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									property="numeroDiasAlteracaoVencimentoPosterior" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Prazo para
									Revisão de Conta (nº dias após vencimento):<font
									color="#FF0000"></font>
							</strong>
							</td>

							<td width="75%"><html:text property="numeroDiasRevisaoConta"
									onkeyup="javascript:verificaNumeroInteiro(this);" size="2"
									maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Último ID
									Utilizado para Geração do RA Manual:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text property="ultimoIDGeracaoRA"
									size="5" maxlength="5"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador Certidão Negativa
									com Efeito Positivo:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorCertidaoNegativaEfeitoPositivo" value="1" />
									Sim <html:radio
										property="indicadorCertidaoNegativaEfeitoPositivo" value="2" />
									N&atilde;o </strong>
							</td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de D&eacute;bito
									a Cobrar v&aacute;lido Certidão Negativa:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDebitoACobrarValidoCertidaoNegativa"
										value="1" /> Sim <html:radio
										property="indicadorDebitoACobrarValidoCertidaoNegativa"
										value="2" /> N&atilde;o </strong>
							</td>

						</tr>
						<tr>
							<td width="25%" align="left"><strong>Número dias de
									Vencimento para gerar Certidão Negativa:</strong> <font color="#FF0000">*</font>
							</td>
							<td><html:text maxlength="3"
									onkeyup="somente_numero(this);"
									property="diasVencimentoCertidaoNegativa" size="3" />
							</td>
						</tr>
						<tr>
							<td><strong>Número de Dias para Encerramento Ordem Serviço Factível Faturável:<strong></td>
							<td><html:text maxlength="3" size="3"
									property="numeroDiasEncerramentoOrdemServicoFactivelFatural"
									onkeyup="somente_numero(this);"/></td>
						</tr>
						<tr>
							<td><strong>Serviço da Ordem de Serviço seletiva Factível Faturável:<strong></td>
							<td><html:text maxlength="4" size="4"
									property="idServicoTipo"
									onkeyup="somente_numero(this);limparServicoTipoDescricao();"
									onkeypress="javascript:validaEnterComMensagem(event, 'informarParametrosSistemaWizardAction.do?destino=5&action=informarParametrosSistemaDadosGeraisEmpresaAction&pesquisarServicoTipo=sim','idServicoTipo','Tipo de Serviço')"/>
							<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'idServicoTipo', null, null, 275, 480, '',document.forms[0].idServicoTipo);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
									
							<logic:notPresent name="servicoTipoInexistente" scope="request">
								<html:text property="desServicoTipo" 
									size="38"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> 
	
							<logic:present name="servicoTipoInexistente" scope="request">
								<html:text property="desServicoTipo" 
									size="38"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:present>
							<a href="javascript:limparServicoTipo();document.forms[0].idServicoTipo.focus()">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /> </a>
							</td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de Documento
									obrigatório para 2ª via da Conta:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorDocumentoValido" value="1" /> Sim <html:radio
										property="indicadorDocumentoValido" value="2" /> N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td><strong>Unidade de destino para imóveis com
									perfil de grande consumidor:</strong></td>
							<td><html:text maxlength="4"
									onkeyup="javascript:verificaNumeroInteiro(this);limparPesquisaDescricaoUnidade()"
									property="idUnidadeDestinoGrandeConsumidor" size="4"
									onkeypress="javascript:validaEnterComMensagem(event, 'informarParametrosSistemaWizardAction.do?destino=5&action=informarParametrosSistemaDadosGeraisEmpresaAction&pesquisaUnidade=sim&id='+this.value, 
									'idUnidadeDestinoGrandeConsumidor', 'Unidade Organizacional');" />
								<!--onkeyup="javascript:limparUnidadeTecla();"  --> <a
								href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
									<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Organizacional"/></a>
								 <logic:present name="idUnidadeDestinoGrandeConsumidor">
									<logic:present name="idUnidadeEncontrada" scope="request">
										<html:text maxlength="30"
											property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="38" />
									</logic:present>
									<logic:notPresent name="idUnidadeEncontrada" scope="request">
										<html:text maxlength="30"
											property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="38" />
									</logic:notPresent>
								</logic:present> <logic:notPresent name="idUnidadeDestinoGrandeConsumidor"
									scope="request">
									<html:text maxlength="30"
										property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="38" />
								</logic:notPresent> <a
								href="javascript:limparUnidade();document.forms[0].idUnidadeDestinoGrandeConsumidor.focus()">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /> </a>
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Último dia do
									Vencimento Alternativo:</strong>
							</td>

							<td width="75%"><html:text
									property="ultimoDiaVencimentoAlternativo" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número de dias
									para validade ordem de fiscalização:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									property="qtdeDiasValidadeOSFiscalizacao" size="2"
									onkeyup="somente_numero(this);" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número máximo
									de dias para uma ordem de serviço ser fiscalizada:<font
									color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="somente_numero(this);"
									property="qtdeDiasEncerraOSFiscalizacao" size="2" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número de dias
									para envio de conta por email:</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="qtdeDiasEnvioEmailConta" size="2" maxlength="2" />
							</td>
						</tr>

						<!-- RM 5759 -->
						<tr>
							<td><strong>Exigir RA no cancelamento do débito:<font
									color="#FF0000">*</font> </strong></td>

							<td><html:radio property="indicadorPermiteCancelarDebito"
									value="1" tabindex="32" /><strong>Sim</strong>&nbsp; <html:radio
									property="indicadorPermiteCancelarDebito" value="2"
									tabindex="33" /><strong>N&atilde;o</strong>
							</td>
						</tr>

						<!-- RM 3892  - Implementar Normas de Senhas no GSAN -->
						<tr>
							<td width="40%" align="left"><strong>Período de
									revalidação de senhas dos usuários subordinados:</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="periodoRevalidarSenha" size="3" maxlength="3" />
							</td>
						</tr>
						<tr>
							<td width="40%" align="left"><strong>Quantidade de
									dias aviso para revalidar acesso:</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="diasRevalidarSenha" size="3" maxlength="3" />
							</td>
						</tr>
						<!-- Fim RM 3892 -->
						<tr>
							<td width="40%" align="left"><strong>Número de Dias
									Limite para Atualizar a Situação da Ligação de Água do Imóvel
									Fiscalizado:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);" maxlength="2"
									property="diasAtualizarLigacaoAguaImovelFisc" size="2" />
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Tramitar Automaticamente RA
									de ESGOTO:</strong></td>
							<td><strong> <html:radio
										property="indicadorTramiteEsgoto" value="1" /> Sim <html:radio
										property="indicadorTramiteEsgoto" value="2" /> N&atilde;o </strong>
							</td>
						</tr>
						<tr>
							<td width="40%" align="left"><strong>Consumo de
									água fixado para anormalidade hidrômetro:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="consumoAguaFixadoAnormalidadeHidro" size="2"
									maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Quantidade de
									meses da instalação e/ou substituição de hidrômetro:<font
									color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="qtdMesIstalSubistituicaoHidro" size="2" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Quantidade de
									vezes da incidência de anormalidade de hidrômetro:<font
									color="#FF0000">*</font>
							</strong>
							</td>

							<td width="75%"><html:text
									onkeyup="javascript:verificaNumeroInteiro(this);"
									property="qtdVezIncidenciaAnormalidadeHidro" size="2"
									maxlength="2" />
							</td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador para bloquear
									funcionalidades de Instalação/Substituição de hidrômetro:<font
									color="#FF0000">*</font></strong></td>
							<td><strong> <html:radio
										property="indicadorBloqFuncInstalacaoSubtHidrometro" value="1" />Sim <html:radio
										property="indicadorBloqFuncInstalacaoSubtHidrometro" value="2" />N&atilde;o </strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td colspan="2" align="center"><strong>Parâmetros
									para Segurança:</strong></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Acesso por
									Usuário:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorLoginUnico" value="0" /> V&aacute;rios
									Acessos <html:radio property="indicadorLoginUnico" value="1" />
									&Uacute;nico Acesso </strong>
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias Máximo
									para Expirar o Acesso:</strong>
							</td>
							<td><html:text maxlength="2"
									property="diasMaximoExpirarAcesso" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias para
									Começar Aparecer a Mensagem de Expiração de Senha:</strong>
							</td>
							<td><html:text maxlength="2"
									property="diasMensagemExpiracaoSenha" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número Máximo
									de Tentativas de Acesso:</strong>
							</td>
							<td><html:text maxlength="2"
									property="numeroMaximoTentativasAcesso" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Número Máximo
									de Favoritos no Menu do Sistema:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:text maxlength="2"
									property="numeroMaximoFavoritosMenu" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>IP do Servidor
									SMTP:<font color="#FF0000">*</font> </strong>
							</td>
							<td><html:text maxlength="15" property="ipServidorSmtp"
									size="15" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>IP do Servidor
									Gerencial:<font color="#FF0000">*</font> </strong>
							</td>
							<td><html:text maxlength="30" property="ipServidorGerencial"
									size="31" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>URL do
									servidor HELP:</strong>
							</td>
							<td><html:text maxlength="60" property="urlHelp" size="55"
									style="text-transform: none;" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>E-mail do
									Responsável:<font color="#FF0000">*</font> </strong>
							</td>
							<td><html:text maxlength="80" property="emailResponsavel"
									size="35" style="text-transform: none;" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Mensagem do
									Sistema: </strong>
							</td>
							<td><html:textarea property="mensagemSistema" cols="40"
									rows="4"
									onkeyup="limitTextArea(document.forms[0].mensagemSistema, 200, document.getElementById('utilizado'), document.getElementById('limite'));" />
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									dias de expiração de senha por Grupo:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicarControleExpiracaoSenhaPorGrupo" value="1" />
									Sim <html:radio
										property="indicarControleExpiracaoSenhaPorGrupo" value="2" />
									N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									bloqueio de senhas usadas anteriormente:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicarControleBloqueioSenha" value="1" /> Sim <html:radio
										property="indicarControleBloqueioSenha" value="2" />
									N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									senha forte:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorSenhaForte" value="1" /> Sim <html:radio
										property="indicadorSenhaForte" value="2" /> N&atilde;o </strong>
							</td>
						</tr>
						<tr>
							<td width="40%" align="left"><strong>Número mínimo
									de dias para bloqueio da senha:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:text maxlength="2"
									property="numeroDiasBloqueiaSenha" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" />
							</td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de permissão
									especial para o grupo:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorPermissaoEspecialGrupo" value="1" /> Sim <html:radio
										property="indicadorPermissaoEspecialGrupo" value="2" />
									N&atilde;o </strong>
							</td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de módulo de
									segurança:<font color="#FF0000">*</font>
							</strong></td>
							<td><strong> <html:radio
										property="indicadorModuloSeguranca" value="1" /> Sim <html:radio
										property="indicadorModuloSeguranca" value="2" /> N&atilde;o </strong>
							</td>
						</tr>
						
						<tr>
							<td width="40%" align="left"><strong>Número máximo de OS por arquivo para o sistema de cobrança via smartphone:</strong>
							</td>
							<td><html:text maxlength="3"
									property="numeroLimiteOSCobranca" size="3"
									onkeyup="somente_numero(this);"/>
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td colspan="2" align="center"><strong>Parâmetros
									para Loja Virtual:</strong></td>
						</tr>

						<tr>
							<td><strong>Estrutura Tarifária:</strong></td>
						</tr>


						<tr>
							<td><strong>Descrição decreto:</strong></td>
							<td align="left"><html:text property="descricaoDecreto"
									size="30" maxlength="40" style="text-transform: none;" />
							</td>
						</tr>
						<tr>
							<td><strong>Arquivo decreto:</strong></td>
							<td><html:file property="arquivoDecreto" size="40" />
							</td>
						</tr>

						<tr>
							<td><strong>Descrição lei:</strong></td>
							<td align="left"><html:text property="descricaoLeiEstTarif"
									size="30" maxlength="40" style="text-transform: none;" />
							</td>
						</tr>
						<tr>
							<td><strong>Arquivo lei:</strong></td>
							<td><html:file property="arquivoLeiEstTarif" size="40" />
							</td>
						</tr>

						<!-- Normas de Instalação e Individualização Predial: - Inicio -->
						<tr>
							<td><strong>Normas de Instalação e Individualização
									Predial:</strong></td>
						</tr>

						<tr>
							<td><strong>Descrição lei:</strong></td>
							<td align="left"><html:text
									property="descricaoLeiIndividualizacao" size="30"
									maxlength="40" style="text-transform: none;" />
							</td>
						</tr>
						<tr>
							<td><strong>Arquivo lei:</strong></td>
							<td><html:file property="arquivoLeiIndividualizacao"
									size="40" />
							</td>
						</tr>

						<tr>
							<td><strong>Descrição Norma CO:</strong></td>
							<td align="left"><html:text property="descricaoNormaCO"
									size="30" maxlength="40" style="text-transform: none;" />
							</td>
						</tr>
						<tr>
							<td><strong>Arquivo Norma CO:</strong></td>
							<td><html:file property="arquivoNormaCO" size="40"
									accept="application/pdf" />
							</td>
						</tr>

						<tr>
							<td><strong>Descrição Norma CM:</strong></td>
							<td align="left"><html:text property="descricaoNormaCM"
									size="30" maxlength="40" style="text-transform: none;" />
							</td>
						</tr>
						<tr>
							<td><strong>Arquivo Norma CM:</strong></td>
							<td><html:file property="arquivoNormaCM" size="40" />
							</td>
						</tr>
						
						<!-- Normas de Instalação e Individualização Predial: - Fim -->

						<tr>
							<td></td>
							<td><strong><font color="#FF0000">*</font> </strong>Campo
								obrigat&oacute;rio</td>
						</tr>

						<tr>
							<td colspan="2">
								<div align="right"><jsp:include
										page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" /></div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
