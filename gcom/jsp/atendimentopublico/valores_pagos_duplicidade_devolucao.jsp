<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.bean.PagamentoRADevolucaoValoresHelper"%>
<%@ page import="gcom.faturamento.conta.ContaGeral"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico"%>
<%@ page import="gcom.faturamento.bean.CreditosHelper"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"
	formName="FiltrarRegistroAtendimentoDevolucaoValoresActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--
	var tipoFuncionario = 1;
	
	function validarForm(form){
		if (validateFiltrarRegistroAtendimentoDevolucaoValoresActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function debitosCreditosSelecionados(form,urlTransferencia,complementoUrl){
	
	    retorno = false;
	
		for(indice = 0; indice < form.elements.length; indice++){
			
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
			
		}
		
		if (!retorno){
			alert('Não existem débitos/créditos selecionados.'); 
		}
		else{
		
			var idsConta = obterValorCheckboxMarcadoPorNome("conta");
			var idsPagamentos = obterValorCheckboxMarcadoPorNome("pagamento");
			
			var concatenador = false;
			if (idsConta != null && idsConta.length > 0){
				urlTransferencia = urlTransferencia + "conta=" + idsConta;
				concatenador = true;
				
			}
			if (idsPagamentos != null && idsPagamentos.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&pagamento=" + idsPagamentos;
				}
				else{
					urlTransferencia = urlTransferencia + "pagamento=" + idsPagamentos;
					concatenador = true;
				}
			}
			
			urlTransferencia = urlTransferencia + complementoUrl;
			form.action = urlTransferencia;
			
			botaoAvancarTelaEspera(form.action);
		}
	}
	
	
	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	   	    
	    if (tipoConsulta == 'imovel') {

		    form.idImovel.value = codigoRegistro;
		    form.inscricaoImovel.value = descricaoRegistro;
		    form.inscricaoImovel.style.color = "#000000";
	    	reloadImovel();
	    }

	  	if (tipoConsulta == 'tipoDebito') { 	
 	  		form.tipoDebito.value = codigoRegistro;
 	  		form.nomeTipoDebito.value = descricaoRegistro; 
	      	form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarTipoDebito=sim";
			submeterFormPadrao(form);
 	  	}
 	  	
	    if (tipoConsulta == 'funcionario') {

			if(tipoFuncionario == 1){

			 	form.idFuncionarioAnalista.value = codigoRegistro;
			 	form.nomeFuncionarioAnalista.value = descricaoRegistro;
		      	form.nomeFuncionarioAnalista.style.color = "#000000";
		      	form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarFuncAnalista=sim";
				submeterFormPadrao(form);

			}else if(tipoFuncionario == 2){

			 	form.idFuncionarioAutorizador.value = codigoRegistro;
			 	form.nomeFuncionarioAutorizador.value = descricaoRegistro;
		      	form.nomeFuncionarioAutorizador.style.color = "#000000";
		      	form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarFuncAutorizador=sim";
				submeterFormPadrao(form);
			}
      	}
      	
	}
		
	function reloadImovel() {
		var form = document.forms[0];
		form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?menu=sim";
		submeterFormPadrao(form);
	}
		
	function desfazer() {
		var form = document.forms[0];
		form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?desfazer=sim";
		submeterFormPadrao(form);
	}
  	
  	function limparImovel() {

		reloadImovel();
  	}
  
  	function limpar(){
  		limparImovel();
  	} 	
  	
  	
	String.prototype.trim = function() {
    	return this.replace(/^\s*/, "").replace(/\s*$/, "");
	}
  	
  	function validaCheck(){
     	validaCheckConta();

  	}
  	
  	
  	function validaCheckConta(){
    	var form = document.forms[0];  	
  	
  		var idContas = form.idsConta.value;
		myString = new String(idContas);
		splitString = myString.split(",");
		
		for (i=0; i< splitString.length; i++) {
			chave  = splitString[i];
			for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && 
					form.elements[indice].name == 'conta' && 
					form.elements[indice].value.trim() == chave.trim()) {

					form.elements[indice].checked = true;
				}
			}
		}
  	}

	function facilitador(objeto,nome){

		if (objeto.value == "0" || objeto.id == undefined){
	
			objeto.value = "1";
			marcarTodosExtrato(nome);
				
		} else{
				
			objeto.value = "0";
			desmarcarTodosExtrato(nome);
		}
	}
		
	//Ativa todos os elementos do tipo checkbox do formul?rio existente
	function marcarTodosExtrato(nome){
		
		var objetoTotalPagamentoSelecionado = document.getElementById("totalPagamentoSelecionado");
		objetoTotalPagamentoSelecionado.innerHTML = "0,00";
		
		var objetoTotalCreditoAbatido = document.getElementById("totalCreditoAbatido");
		objetoTotalCreditoAbatido.innerHTML = "0,00";
		
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				
				elemento.checked = true;
				
			}
		}
		
		totalizarDebitosSelecionados();
	}
	
	//Desativa todos os elementos do tipo checkbox do formul?rio existente
	function desmarcarTodosExtrato(nome) {
			
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				
				if (elemento.checked == true){
					elemento.checked = false;
					totalizarDebito(elemento);
				}
			}
		}
	}

	


function calcular(){
	var form = document.forms[0];
	
	debitosCreditosSelecionados(form,'/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?','&calcular=sim&reloadPage=1');

}

function totalizarDebitosSelecionados(){
	
	form = document.forms[0];
	
	validaCheck();
	
	for(indice = 0; indice < form.elements.length; indice++){
		
		if ((form.elements[indice].type == "checkbox" && form.elements[indice].checked == true)) {
			
			totalizarDebito(form.elements[indice]);
		}
	}
}

function totalizarDebito(objeto){
	form = document.forms[0];
	var objetoTotalPagamentoSelecionado = document.getElementById("totalPagamentoSelecionado");
	var objetoTotalCreditoAbatido = document.getElementById("totalCreditoAbatido");
	
	//VALOR TOTAL PAGAMENTO
	var valorTotalSelecionado = objetoTotalPagamentoSelecionado.innerHTML;
	valorTotalSelecionado = valorTotalSelecionado.replace(".","");
	valorTotalSelecionado = valorTotalSelecionado.replace(",",".");

	//VALOR TOTAL CONTA 
	var valorTotalCreditoAbatido = objetoTotalCreditoAbatido.innerHTML;
	valorTotalCreditoAbatido = valorTotalCreditoAbatido.replace(".","");
	valorTotalCreditoAbatido = valorTotalCreditoAbatido.replace(",",".");

	//VALOR SELECIONADO
	var valorSelecionado =  objeto.alt;
	valorSelecionado = valorSelecionado.replace(".","");
	valorSelecionado = valorSelecionado.replace(",",".");
	
	
	if(objeto.name == 'conta'){
		if (objeto.checked == true){
			valorTotalCreditoAbatido = (valorTotalCreditoAbatido * 1) + (valorSelecionado * 1);
		}else{
			valorTotalCreditoAbatido = (valorTotalCreditoAbatido * 1) - (valorSelecionado * 1); 
		}
		
	}else if(objeto.name == 'pagamento'){
		if (objeto.checked == true){
			valorTotalSelecionado = (valorTotalSelecionado * 1) + (valorSelecionado * 1);
		}else{
			valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1); 
		}
	}
	
	objetoTotalCreditoAbatido.innerHTML = formatarValorMonetario(valorTotalCreditoAbatido);
	objetoTotalPagamentoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);
	

}


	
	function transferir() {
		var form = document.forms[0];
		var msg = '';
		if (form.indicadorGerarCreditoARealizar != null
				&& form.indicadorGerarCreditoARealizar.value != ''
				&& form.indicadorGerarCreditoARealizar[1].checked) {
				if (form.tipoDebito.value == null
						|| form.tipoDebito.value == '') {
					msg = msg + 'Informe Tipo do Débito.\n' 
				}
				if (form.idFuncionarioAnalista.value == null
						|| form.idFuncionarioAnalista.value == '') {
					msg = msg + 'Informe Funcionário Analista.\n' 
				}
				if (form.idFuncionarioAutorizador.value == null
						|| form.idFuncionarioAutorizador.value == '') {
					msg = msg + 'Informe Funcionário Autorizador.\n' 
				}
		}
		if (msg == null || msg == '') {
			if (form.indicadorGerarCreditoARealizar == null
				|| form.indicadorGerarCreditoARealizar.value == ''
				|| !form.indicadorGerarCreditoARealizar[1].checked
				|| (validarCampoNumericoComMensagemLimpandoCampo(form.tipoDebito, 'Tipo do Débito')
					&& validarCampoNumericoComMensagemLimpandoCampo(form.idFuncionarioAnalista, 'Funcionário Analista')
					&& validarCampoNumericoComMensagemLimpandoCampo(form.idFuncionarioAutorizador, 'Funcionário Autorizador'))) {
				botaoAvancarTelaEspera('/gsan/transferirDevolucaoValoresPagosDuplicidadeAction.do');
			}
		} else {
			alert(msg);
		}
	}

	function verificaSelecao(){
		var form = document.forms[0];
		if (form.indicadorGerarCreditoARealizar != null
				&& form.indicadorGerarCreditoARealizar.value != ''
				&& (form.indicadorGerarCreditoARealizar[0].checked
						|| form.indicadorGerarCreditoARealizar[1].checked)) {
			if(form.indicadorGerarCreditoARealizar[0].checked){
				form.botaoTransferir.disabled = false;
				document.getElementById('exibirCreditoARealizar').style.display = 'block';
				document.getElementById('exibirGuiaDevolucao').style.display = 'none';
			}else if(form.indicadorGerarCreditoARealizar[1].checked){
				form.botaoTransferir.disabled = false;
				document.getElementById('exibirCreditoARealizar').style.display = 'none';
				document.getElementById('exibirGuiaDevolucao').style.display = 'block';
			}
		}
	}

	function limparTipoDebito() {
		var form = document.forms[0];
		form.tipoDebito.value = "";
		form.nomeTipoDebito.value = "";
	}
	
    function limparDescricaoTipoDebito() {
		var form = document.forms[0];
		form.nomeTipoDebito.value = "";
	}
	
	function limparFuncionarioAnalista(){
  		var form = document.forms[0];
	  		
		form.idFuncionarioAnalista.value = "";
		form.nomeFuncionarioAnalista.value = "";
	  		
	}
	
    function limparNomeFuncionarioAnalista() {
		var form = document.forms[0];
		form.nomeFuncionarioAnalista.value = "";
	}
	
	function limparFuncionarioAutorizador(){
  		var form = document.forms[0];
	  		
		form.idFuncionarioAutorizador.value = "";
		form.nomeFuncionarioAutorizador.value = "";
	}
	
    function limparNomeFuncionarioAutorizador() {
		var form = document.forms[0];
		form.nomeFuncionarioAutorizador.value = "";
	}

	function habilitarPesquisa(objeto,action) {
		if (objeto.disabled == false) {
			abrirPopup(action, 600, 500);
		}	
	}
	
	function selecionarBloquearDocumentosPagos(){
		marcarTodosExtrato("pagamento");
		bloquearDesbloquear("pagamento",true);
	}
	
	function bloquearDesbloquear(nome,bloquear){
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				elemento.disabled = bloquear;	
			}
		}
	}
	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload=";validaCheck();verificaSelecao();">
	
<div id="formDiv">

<html:form action="/transferirDevolucaoValoresPagosDuplicidadeAction"
	  	   name="FiltrarRegistroAtendimentoDevolucaoValoresActionForm"
	       type="gcom.gui.atendimentopublico.FiltrarRegistroAtendimentoDevolucaoValoresActionForm"
	       method="post">

	<html:hidden property="idsConta" />
	<input type="hidden" name="checkConta" value="0">
	<input type="hidden" name="valorConta" value="0">

	<html:hidden property="dataAtendimentoInicio"/>
	<html:hidden property="dataAtendimentoFim"/>
	<html:hidden property="codigoTipoDevolucao" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">
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

			<td width="615" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Devolução de Valores</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><strong>Para devolução de valores, informe os dados abaixo:</strong></td>
				</tr>

			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Número do RA:</strong></td>
					<td><html:text property="idRAConsulta" size="50" maxlength="50"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td><strong>Tipo de Especificação:</strong></td>
					<td><html:text property="descricaoTipoSolicitacao" size="50"
						maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td><strong>Imóvel:</strong></td>
					<td><html:text property="idImovelSelecionado" size="50"
						maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td><strong>Cliente:</strong></td>
					<td><html:text property="nomeClienteUsuarioImovelSelecionado"
						size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
			</table>
			<!-- Tabela de pagamentos em duplicidade --> 
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalPagamento = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="6" height="20"><strong>Documentos Pagos</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">

									<td align="center" width="8%"><strong>Marcar</strong></td>
									<td width="20%">
									<div align="center"><strong>Documento</strong></div>
									</td>
									<td width="12%">
									<div align="center"><strong>Mês/Ano</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Dt Pagamento</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Valor Faturado</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Valor Crédito</strong></div>
									</td>

								</tr>
							</table>

							</td>
						</tr>
					<logic:present name="colecaoPagamentoValoresPagos">

							<logic:notEmpty name="colecaoPagamentoValoresPagos">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoPagamentoValoresPagos")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>

									<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoPagamentoValoresPagos" 
											id="pagamentoHelper" 
											type="PagamentoRADevolucaoValoresHelper">

											<%valorTotalPagamento = valorTotalPagamento.add(pagamentoHelper.getValorDevolucao());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>


												<td align="center" width="8%"><INPUT TYPE="checkbox"
													NAME="pagamento"
													value="<%="" + pagamentoHelper.getIdPagamento().intValue() %>"
													alt="<%="" + Util.formatarMoedaReal(pagamentoHelper.getValorDevolucao()).trim()%>"
													onclick="totalizarDebito(this);"></td>
				
												<td width="20%">
													<div align="center"><span style="color: #000000;"> <%=
															pagamentoHelper.getDocumento() %>
													</span></div>
												</td>
												
												<logic:empty name="pagamentoHelper" property="idConta">
													<td width="12%" align="center">
														<font color="#000000"> 
															<%="" + Util.formatarMesAnoReferencia(pagamentoHelper.getAnoMes())%>
														</font>
													</td>
												</logic:empty>
												
												<logic:notEmpty name="pagamentoHelper" property="idConta">
													<logic:equal name="pagamentoHelper" property="indicadorContaHistorico" value="1">
														<td width="12%" align="center">
															<font color="#000000"> <a
																href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + pagamentoHelper.getIdConta() %>&tipoConsulta=contaHistorico', 600, 800);">
																<%="" + Util.formatarMesAnoReferencia(pagamentoHelper.getAnoMes())%>
															</a> </font>
														</td>
													</logic:equal>
													
													<logic:equal name="pagamentoHelper" property="indicadorContaHistorico" value="2">
														<td width="12%" align="center">
															<font color="#000000"> <a
																href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + pagamentoHelper.getIdConta() %>&tipoConsulta=conta', 600, 800);">
																<%="" + Util.formatarMesAnoReferencia(pagamentoHelper.getAnoMes())%>
															</a> </font>
														</td>
													</logic:equal>
												</logic:notEmpty>
										
												<td width="20%">
													<div align="center"><span style="color: #000000;"> <%=""
													+ Util.formatarData(pagamentoHelper.getDataPagamento()) %>
													</span></div>
												</td>
												
												<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(pagamentoHelper.getValorPago()).trim() %>
													</span></div>
												</td>
													
												<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(pagamentoHelper.getValorDevolucao()).trim()%>
													</span></div>
												</td>
											

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="8%" height="20"></td>
											<td width="20%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="12%"></td>
											<td width="20%"></td>
											<td width="20%">
											<div align="right"><strong> </strong></div>
											</td>
											<td width="20%"><div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalPagamento).trim()%></strong></div></td>

										</tr>

									</table>
								<%if (((Collection) session.getAttribute("colecaoPagamentoValoresPagos")).size() >= 4) {%>
								</div>
								<%}%>
										

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>

					</td>
				</tr>
			</table>
			<!-- Tabela de pagamentos em duplicidade -->
			
				<table width="100%" border="0">
				<tr>
					<td HEIGHT="20" align="right" ><strong>Total de Crédito:</strong></td>
					<td>
					<div align="right" style="font-size: 12"><strong><span
						id="totalPagamentoSelecionado">0,00</span></strong></div>
					</td>

				</tr>
			
			</table>
			

			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<!-- Tabela de CONTAS a pagar-->
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="5" height="20"><strong>Contas</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">

									<td width="5%" height="20"><strong>Marcar</strong></td>
									<td width="20%">
									<div align="center"><strong>Mês/Ano</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Vencimento</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Valor</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Situação</strong></div>
									</td>

								</tr>
							</table>

							</td>
						</tr>

						<logic:present name="colecaoConta">

							<logic:notEmpty name="colecaoConta">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoConta")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>
										<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoConta" id="conta" type="ContaValoresHelper">

											<%valorTotalConta = valorTotalConta.add(conta.getValorTotalConta());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>


												<td align="center" width="5%"><INPUT TYPE="checkbox" NAME="conta"
													value="<%="" + conta.getConta().getId().intValue() %>"
													alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>"
													onclick="totalizarDebito(this);"></td>

													<td width="20%" align="center"><font color="#000000"> <a
														href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
													<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%>
													</a> </font></td>
													
													<td width="25%">
													<div align="center">
													<logic:present name="conta"
														property="conta.dataVencimentoConta">
														<span style="color: #000000;"> <%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%></span>
													</logic:present> 
													<logic:notPresent name="conta" property="conta.dataVencimentoConta">
														&nbsp;
													</logic:notPresent></div>
													</td>
													
													<td width="25%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
													</span></div>
													</td>

													<td width="25%">
													<div align="center">
													<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
														<font color="#000000"> <bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" /></font>
													</logic:present> 
													<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
														&nbsp;
													</logic:notPresent></div>
													</td>

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="5%" height="20"></td>
											<td width="20%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="25%"></td>
											<td width="25%">
											<div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalConta).trim()%>
											</strong></div>
											</td>
											<td width="25%">
											<div align="right"><strong> </strong></div>
											</td>
										

										</tr>

									</table>
									<%if (((Collection) session.getAttribute("colecaoConta")).size() >= 4) {%>
									</div>
									<%}%>
								

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>
					</td>
				</tr>
			</table>
			<!-- tabela de Contas a pagar -->
			<table width="100%" border="0">
				<tr>
					<td align="right" HEIGHT="20"><strong>Total do Crédito Abatido:</strong></td>
					<td>
					<div align="right" style="font-size: 12"><strong><span
						id="totalCreditoAbatido">0,00</span></strong></div>
					</td>
					<td align="right"><input type="button" name="" value="Calcular"
						class="bottonRightCol"
						onclick="javascript:calcular();"/></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Para retificação da conta, informe os dados abaixo:</strong></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<!-- tabela de Contas a ser retificada -->
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalContaRetificada = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="6" height="20"><strong>Conta a ser Retificada</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">
		
									<td width="15%"><div align="center"><strong>Mês/Ano</strong></div></td>
									<td width="10%"><div align="center"><strong>Vencimento</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Original</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Crédito</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Atual</strong></div></td>
									<td width="30%"><div align="center"><strong>Situação</strong></div></td>

								</tr>
							</table>

							</td>
						</tr>

						<logic:present name="colecaoContaASerRetificada">

							<logic:notEmpty name="colecaoContaASerRetificada">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoContaASerRetificada")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>
										<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoContaASerRetificada" id="conta" type="ContaValoresHelper">

											<%valorTotalContaRetificada = valorTotalContaRetificada.add(conta.getValorTotalConta());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="15%" align="center"><font color="#000000"> <a
													href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
												<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%>
												</a> </font></td>
												
												<td width="10%">
												<div align="center">
												<logic:present name="conta"
													property="conta.dataVencimentoConta">
													<span style="color: #000000;"> <%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%></span>
												</logic:present> 
												<logic:notPresent name="conta" property="conta.dataVencimentoConta">
													&nbsp;
												</logic:notPresent></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
												</span></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(conta.getValorCreditoConta()).trim()%>
												</span></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(conta.getValorAtualConta()).trim()%>
												</span></div>
												</td>

												<td width="30%">
												<div align="center">
												<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
													<font color="#000000"> <bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" /></font>
												</logic:present> 
												<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
													&nbsp;
												</logic:notPresent></div>
												</td>

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="15%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="10%"></td>
											<td width="15%">
											<div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalContaRetificada).trim()%>
											</strong></div>
											</td>
											<td width="15%">
											<td width="15%">
											<td width="30%">
											<div align="right"><strong> </strong></div>
											</td>
										

										</tr>

									</table>
									<%if (((Collection) session.getAttribute("colecaoContaASerRetificada")).size() >= 4) {%>
									</div>
									<%}%>
								

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>
					</td>
				</tr>
			</table>
			<!-- tabela de Contas a ser retificada -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<!-- tabela de credito a ser transferido -->
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
					<c:choose>
						<c:when test="${FiltrarRegistroAtendimentoDevolucaoValoresActionForm.codigoTipoDevolucao eq 3}">
							<td colspan="4" height="20"><strong>Crédito / Débito</strong></td>
						</c:when>
						<c:otherwise>
							<td colspan="4" height="20"><strong>Crédito a ser Transferido</strong></td>
						</c:otherwise>
					</c:choose>
						
					</tr>
					<tr bgcolor="#90c7fc">
				
						<td width="25%"><div align="center"><strong>Tipo do Crédito</strong></div></td>
						<td width="45%"><div align="center"><strong>Origem</strong></div></td>
						<td width="15%"><div align="center"><strong>Valor</strong></div></td>
						<td width="15%"><div align="center"><strong>Referência</strong></div></td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalCreditoASerTransferido = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoCreditoASerTransferido">
			
			<logic:notEmpty name="colecaoCreditoASerTransferido">

			<tr>
				<td>
										
					<% String cor2 = "#cbe5fe";%>
					<%if (((Collection) session.getAttribute("colecaoCreditoASerTransferido")).size() >= 4) {%>
					<div style="width: 100%; height: 100; overflow: auto;">
					<%}%>
					
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoCreditoASerTransferido" id="credito" type="CreditosHelper">

						<%valorTotalCreditoASerTransferido = valorTotalCreditoASerTransferido.add(credito.getValorCredito()); %>
						
						<%	if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="25%" align="left">
							<bean:define name="credito" property="tipoCredito" id="tipoCredito" />
							<bean:write name="tipoCredito" property="descricao" />
						</td>
						
						<td width="45%" align="left">
							<bean:define name="credito" property="origemCredito" id="origemCredito" />
							<bean:write name="origemCredito" property="descricaoCreditoOrigem" />
						</td>
	
						<td width="15%">
							<div align="right">
								<%="" + Util.formatarMoedaReal(credito.getValorCredito()).trim()%>
							</div>
						</td>
						<td width="15%">
						<div align="center">
						
							<logic:present name="credito" property="referenciaCredito">
								<span style="color: #000000;">
									<%=""+ Util.formatarMesAnoReferencia(credito.getReferenciaCredito())%>
								</span>
							</logic:present> 
							
							<logic:notPresent name="credito" property="referenciaCredito">
								&nbsp;
							</logic:notPresent>	
						
						</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="25%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="45%"></td>
									<td width="15%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalCreditoASerTransferido).trim()%>
								</strong>				
							</div>
						</td>
						<td width="15%"></td>

					</tr>
										
					</table>
										
					<%if (((Collection) session.getAttribute("colecaoCreditoASerTransferido")).size() >= 4) {%>
					</div>
					<%}%>
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			<!-- tabela de crédito a ser transferido -->
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="40%" align="left">
						<strong>Saldo do Crédito a Transferir:&nbsp;</strong>
					</td>
					<td width="20%" align="left">
						<strong>
							<html:text property="saldoCreditoATransferir" size="10"
								readonly="true" style="background-color: transparent; border:0; color: #000000;" ></html:text> 
						</strong>	
					</td>
						
					<td width="40%" align="left">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<logic:equal name="habilitarRadioGerar" value="1">
										<strong><html:radio property="indicadorGerarCreditoARealizar" 
												value="1" onclick="verificaSelecao();" >
												&nbsp;Gerar Crédito a Realizar
										</html:radio></strong>
									</logic:equal>
									<logic:equal name="habilitarRadioGerar" value="2">
										<strong><html:radio property="indicadorGerarCreditoARealizar" 
												value="1" onclick="verificaSelecao();" disabled="true" >
												&nbsp;Gerar Crédito a Realizar
										</html:radio></strong>
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td>
									<strong>
									
										<logic:equal name="habilitarRadioGerar" value="1">
											<logic:present name="permissaoEspecialGuiaDevolucao" scope="session">
												<html:radio property="indicadorGerarCreditoARealizar" 
													value="2" onclick="verificaSelecao();" >
													&nbsp;Gerar Guia de Devolução
												</html:radio>
											</logic:present>
											
											<logic:notPresent name="permissaoEspecialGuiaDevolucao" scope="session">
												<html:radio property="indicadorGerarCreditoARealizar" 
													value="2" onclick="verificaSelecao();" disabled="true">
													&nbsp;Gerar Guia de Devolução
												</html:radio>
											</logic:notPresent>
										</logic:equal>
										
										<logic:equal name="habilitarRadioGerar" value="2">
											<html:radio property="indicadorGerarCreditoARealizar" 
												value="2" onclick="verificaSelecao();" disabled="true">
												&nbsp;Gerar Guia de Devolução
											</html:radio>
										</logic:equal>
									</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>
			
			<!-- tabela de credito a realizar -->
			<div id="exibirCreditoARealizar" style="display:none;">
				<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr bgcolor="#79bbfd">
						
							<c:choose>
								<c:when test="${FiltrarRegistroAtendimentoDevolucaoValoresActionForm.codigoTipoDevolucao eq 3}">
									<td colspan="4" height="20"><strong>Crédito / Débito</strong></td>
								</c:when>
								<c:otherwise>
									<td colspan="4" height="20"><strong>Crédito a Realizar</strong></td>
								</c:otherwise>
							</c:choose>					
						</tr>
						<tr bgcolor="#90c7fc">
					
							<td width="25%">
								<div align="center"><strong>Tipo do Crédito</strong></div>
							</td>
							<td width="45%">
								<div align="center"><strong>Origem</strong></div>
							</td>
							<td width="15%">
								<div align="center"><strong>Valor</strong></div>
							</td>
							<td width="15%">
								<div align="center"><strong>Referência</strong></div>
							</td>
	
						</tr>
						</table>
	
					</td>
				</tr>
				
				<%BigDecimal valorTotalCredito = new BigDecimal("0.00");%>
				
				<logic:present name="colecaoCreditoARealizar">
				
				<logic:notEmpty name="colecaoCreditoARealizar">
	
				<tr>
					<td>
											
						<% String cor2 = "#cbe5fe";%>
	
						<%if (((Collection) session.getAttribute("colecaoCreditoARealizar")).size() >= 4) {%>
						<div style="width: 100%; height: 100; overflow: auto;">
						<%}%>
											
						<table width="100%" align="center" bgcolor="#90c7fc">
											
						<logic:iterate name="colecaoCreditoARealizar" id="credito" type="CreditosHelper">
	
							<%valorTotalCredito = valorTotalCredito.add(credito.getValorCredito()); %>
							
							<%	if (cor2.equalsIgnoreCase("#cbe5fe")){
								cor2 = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
							<%} else{
								cor2 = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
							<%}%>
													
	
							<td width="25%" align="left">
								<bean:define name="credito" property="tipoCredito" id="tipoCredito" />
								<bean:write name="tipoCredito" property="descricao" />
							</td>
							
							<td width="45%" align="left">
								<bean:define name="credito" property="origemCredito" id="origemCredito" />
								<bean:write name="origemCredito" property="descricaoCreditoOrigem" />
							</td>
		
							<td width="15%">
								<div align="right">
									<%="" + Util.formatarMoedaReal(credito.getValorCredito()).trim()%>
								</div>
							</td>
							<td width="15%">
							<div align="center">
							
								<logic:present name="credito" property="referenciaCredito">
									<span style="color: #000000;">
										<%=""+ Util.formatarMesAnoReferencia(credito.getReferenciaCredito())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="credito" property="referenciaCredito">
									&nbsp;
								</logic:notPresent>	
							
							</div>
							</td>
						</tr>
				
						</logic:iterate>
						
							<%if (cor2.equalsIgnoreCase("#cbe5fe")){
								cor2 = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
							<%} else{
								cor2 = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
							<%}%>
	
							<td width="25%">
								<div align="center"><strong>Total:</strong></div>
							</td>
							<td width="45%"></td>
							<td width="15%">
								<div align="right">
									<strong>				
									<%="" + Util.formatarMoedaReal(valorTotalCredito).trim()%>
									</strong>				
								</div>
							</td>
							<td width="15%"></td>
						</tr>
											
						</table>
											
						<%if (((Collection) session.getAttribute("colecaoCreditoARealizar")).size() >= 4) {%>
						</div>
						<%}%>
						<input type="hidden" name="valorTotalCredito" ID="valorTotalCredito" value="<%="" + valorTotalCredito %>">
					</td>
				</tr>
	
				</logic:notEmpty>
				
				</logic:present>
	
				</table>
			</div>
			<!-- tabela de crédito a realizar -->

			<div id="exibirGuiaDevolucao" style="display:none;">
				<table width="100%" bgcolor="#90c7fc" >
					<tr bgcolor="#79bbfd">
						<td colspan="2" height="20"><strong>Guia de Devolução</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">
						<td colspan="2" >&nbsp;</td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td><strong>Registro de Atendimento:</strong></td>
						<td><html:text property="idRAConsulta" size="63" maxlength="63"
							readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
	
					<tr bgcolor="#cbe5fe">
						<td><strong>Tipo de Documento:</strong></td>
						<td><html:text property="tipoDocumento" size="63"
							maxlength="63" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
	
					<tr bgcolor="#cbe5fe">
						<td><strong>Tipo do Débito:<font color="#FF0000">*</font></strong></td>
						<td>
							<span class="style2"> 
								<html:text property="tipoDebito"
										   size="5" 
										   maxlength="4"
										   onkeyup="javascript:verificaNumeroInteiro(this);limparDescricaoTipoDebito();"
										   onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Tipo do Débito');limparDescricaoTipoDebito();"
										   onkeypress="javascript:validaEnterComMensagem(event, 'exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarTipoDebito=sim', 'tipoDebito','Tipo do Débito');return isCampoNumerico(event);" />
								<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 400, 800);">
									<img src="/gsan/imagens/pesquisa.gif" 
										 alt="Pesquisar" 
										 title="Pesquisar Tipo de Débito"
										 border="0"
										 height="21" 
										 width="23"></a> 
								
								<logic:present
									name="debitoTipoEncontrado" scope="session">
									<html:text property="nomeTipoDebito" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="47" />
								</logic:present> 
								<logic:notPresent name="debitoTipoEncontrado"
									scope="session">
									<html:text property="nomeTipoDebito" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="47" />
								</logic:notPresent> 
								<a href="javascript:limparTipoDebito();"> 
								<img border="0" 
									 title="Apagar Tipo de Débito"
									 src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
							</span>
						</td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td><strong>Valor da Devolução:</strong></td>
						<td><html:text property="valorGuiaDevolucao"
							size="63" maxlength="63" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td>
							<strong>Funcion&aacute;rio Analista:<font color="#FF0000">*</font></strong>
						</td>
						
						<td colspan="2">
							<div>
								<html:text property="idFuncionarioAnalista" 
									size="9" 
									maxlength="9"
								    onkeyup="javascript:verificaNumeroInteiro(this);limparNomeFuncionarioAnalista();"
								    onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Funcionário Analista');limparNomeFuncionarioAnalista();"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarFuncAnalista=sim', 'idFuncionarioAnalista','Funcionário Analista');return isCampoNumerico(event);" />
								
								<a href="javascript:javascript:tipoFuncionario=1;habilitarPesquisa(document.forms[0].idFuncionarioAnalista,'exibirFuncionarioPesquisar.do');">
									<img width="23" 
										height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										alt="Pesquisar" 
										title="Pesquisar Funcionário Analista"
										border="0" /></a>
								
								<logic:present name="funcionarioAnalistaEncontrado" scope="session">
									<html:text property="nomeFuncionarioAnalista" 
										size="43"
										maxlength="43" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
	
								<logic:notPresent name="funcionarioAnalistaEncontrado" scope="session">
									<html:text property="nomeFuncionarioAnalista" 
										size="43"
										maxlength="43" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
	
								<a href="javascript:limparFuncionarioAnalista();">
									<img border="0" 
										title="Apagar Funcionário Analista"
										src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
							</div>
						</td>
					</tr>
					
					<tr bgcolor="#cbe5fe">
						<td>
							<strong>Funcion&aacute;rio Autorizador:<font color="#FF0000">*</font></strong>
						</td>
						
						<td colspan="2">
							<div>
								<html:text property="idFuncionarioAutorizador" 
									size="9" 
									maxlength="9"
								    onkeyup="javascript:verificaNumeroInteiro(this);limparNomeFuncionarioAutorizador();"
								    onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Funcionário Autorizador');limparNomeFuncionarioAutorizador();"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?pesquisarFuncAutorizador=sim', 'idFuncionarioAutorizador','Funcionário Autorizador');return isCampoNumerico(event);" />
								
								<a href="javascript:javascript:tipoFuncionario=2;habilitarPesquisa(document.forms[0].idFuncionarioAnalista,'exibirFuncionarioPesquisar.do');">
									<img width="23" 
										height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										alt="Pesquisar" 
										 title="Pesquisar Funcionário Autorizador"
										border="0" /></a>
								
								<logic:present name="funcionarioAutorizadorEncontrado" scope="session">
									<html:text property="nomeFuncionarioAutorizador" 
										size="43"
										maxlength="43" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
	
								<logic:notPresent name="funcionarioAutorizadorEncontrado" scope="session">
									<html:text property="nomeFuncionarioAutorizador" 
										size="43"
										maxlength="43" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
	
								<a href="javascript:limparFuncionarioAutorizador();">
									<img border="0" 
										title="Apagar Funcionário Autorizador"
										src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<SCRIPT LANGUAGE="JavaScript">
				<!--
					totalizarDebitosSelecionados();
				//-->
				</SCRIPT>

			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>


			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>


			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left" onclick="javascript:desfazer();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						<input name="Button" type="button" class="bottonRightCol"
						value="Voltar" align="left"
						onclick="javascript:window.location.href='/gsan/filtrarRegistroAtendimentoDevolucaoValoresAction.do'">
						
					</td>

					<td align="right">
						 
						 <logic:empty name="habilitarBotaoTransferir">
							<input type="button" class="bottonRightCol" 
								name="botaoTransferir"  id="botaoTransferir" 
								disabled="disabled"
								value="Transferir" onclick="javascript:transferir();"
							 />
						 </logic:empty>
						 
						<logic:equal name="habilitarBotaoTransferir" value="1">
							<input type="button" class="bottonRightCol" 
								name="botaoTransferir"  id="botaoTransferir" 
								value="Transferir" onclick="javascript:transferir();"
							 />
						 </logic:equal>
						 
						<logic:equal name="habilitarBotaoTransferir" value="2">
							<input type="button" class="bottonRightCol" 
								name="botaoTransferir"  id="botaoTransferir" 
								disabled="disabled"
								value="Transferir" onclick="javascript:transferir();"
							 />
						 </logic:equal>
						
					</td>
				</tr>

			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
	
	<c:if test="${FiltrarRegistroAtendimentoDevolucaoValoresActionForm.codigoTipoDevolucao eq 3}">
		<script>
			selecionarBloquearDocumentosPagos();
		</script>
	</c:if>

</html:form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
