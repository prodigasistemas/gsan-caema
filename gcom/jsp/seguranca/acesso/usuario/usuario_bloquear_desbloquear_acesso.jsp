<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>

<script language="JavaScript">

$(document).ready(function(){

	verificaAfastamento();
	
	if (document.forms[0].icAfastamentoTemporario[0].checked){
		document.forms[0].idUsuarioEspelho.disabled = 0;
		document.forms[0].dtAfastamentoFinal.disabled = 0;		
	} else {
		document.forms[0].idUsuarioEspelho.disabled = 1;
		document.forms[0].dtAfastamentoFinal.disabled = 1;
	}
	
	if (document.forms[0].erroUsuarioEspelho.value!=""){
		document.forms[0].idUsuarioEspelho.value="";
		document.forms[0].nomeUsuarioEspelho.value="";
		alert(document.forms[0].erroUsuarioEspelho.value);
	}	
	
	$('input[name=icAfastamentoTemporario]').click(function(){		
		document.forms[0].action = "/gsan/exibirBloquearDesbloquearAcessoUsuarioAction.do";
		document.forms[0].submit();
	});
	
});

function verificaAfastamento(){
	//Se afastamento = 3 desabilitar tudo
	var form = document.forms[0];
	if (form.icAfastamentoTemporario[2].checked){
		form.motivoAfastamento.disabled = true;
		form.motivoAfastamento.disabled = 1;
		form.motivoAfastamento.selectedIndex= '-1'; 
		
		form.dtAfastamentoInicial.disabled = true;
		form.dtAfastamentoInicial.value = "";
		
		form.dtAfastamentoFinal.disabled = true;
		form.dtAfastamentoFinal.value = "";
		
		form.idUsuarioEspelho.disabled = true;
		form.idUsuarioEspelho.value = "";
		form.nomeUsuarioEspelho.value = "";
						
		form.observacaoAfastamento.disabled = true;	
		form.observacaoAfastamento.value = "";

		form.usuarioSituacao.disabled = false;
	} else {	
		//Se afastamento for definitivo E estiver mantendo
		if (form.icAfastamentoTemporario[1].checked && (form.idAfastamento.value!="" && form.idAfastamento.value!="0")){
			disabledAfastamento(true);		
		} else {
			form.idUsuarioEspelho.disabled = 0;
			form.dtAfastamentoFinal.disabled = 0;
		}		
		/*alert(1);
		form.motivoAfastamento.disabled = false;
		form.dtAfastamentoInicial.disabled = false;
		form.dtAfastamentoFinal.disabled = false;
		form.idUsuarioEspelho.disabled = false;				
		form.observacaoAfastamento.disabled = false;*/

		//desabilita campo situação do usuario
		form.usuarioSituacao.disabled = true;
	}
}
function habilitaSituacaoUsuario(form){
	var form = document.forms[0];
		if (form.login.value.length > 0 ){
			form.login.disabled = true;

			if (form.idAfastamento.value==null || form.idAfastamento.value==""){
				form.login.disabled = false;
				form.login.value = '';
				form.usuarioSituacao.disabled = true;				
			}
			if (form.motivoAfastamento.selectedIndex!=-1 && form.motivoAfastamento.selectedIndex!=0 && 
					form.idAfastamento.value!=null && form.idAfastamento.value!="0"){
				
				form.dtAfastamentoInicial.disabled = true;
				form.usuarioSituacao.disabled = true;
				form.imagem.disabled = true;
				
				if (form.icAfastamentoTemporario[1].checked){
					disabledAfastamento(true);					
					alert("Usuário associado ao login informado foi afastado definitivamente do sistema GSAN.");
				} 
			} 
		}else{
			form.usuarioSituacao.disabled = true;					
		}
}

function disabledAfastamento(booleano){
	var form = document.forms[0];
	
	form.usuarioSituacao.disabled = booleano;
	form.icAfastamentoTemporario[0].disabled = booleano;
	form.icAfastamentoTemporario[1].disabled = booleano;
	form.icAfastamentoTemporario[2].disabled = booleano;
	form.motivoAfastamento.disabled = booleano;
	form.dtAfastamentoInicial.disabled = booleano;
	form.dtAfastamentoFinal.disabled = booleano;		
	form.observacaoAfastamento.disabled = booleano;
} 

function bloqueio(form) {
	form.usuarioSituacao.disabled = true;
	form.usuarioSituacao.selectedIndex = 0;	
}

function redirecionaSubmit(caminhoAction) {

   var form = document.forms[0];
   form.action = caminhoAction;
   form.submit();

   return true;

 }

function validarForm(form){
	//Validar login
	urlRedirect = "/gsan/bloquearDesbloquearAcessoUsuarioAction.do?afastamento=sim"
	if (form.login.value==""){
		alert("Informe Login.");
		return false;
	} 
	if(form.observacaoAfastamento.value.length > 300){
		alert('Quantidade de caracteres inválida');
		return false;
	}
	//Valida afastamento se o mesmo for informado
	if (!form.icAfastamentoTemporario[2].checked){
		if (validarAfastamento(form)){
			botaoAvancarTelaEspera(urlRedirect);
		} 
	} 
	//Valida bloqueio
	else {
		if (testarCampoValorZero(document.BloquearDesbloquearAcessoUsuarioActionForm.usuarioSituacao, 'Usuário Situação')){
			botaoAvancarTelaEspera(urlRedirect);
		}
	}	
}

function validarAfastamento(form){
	var msg = "";

	if (form.motivoAfastamento.selectedIndex==-1 || form.motivoAfastamento.selectedIndex==0){
		msg += "Informe Motivo Afastamento. \n";		
	} 
	if ((form.icAfastamentoTemporario[0].checked) && (form.dtAfastamentoInicial.value == '' || form.dtAfastamentoFinal.value == '')) {
		msg += "Informe Data Inicial e Final. \n";		
	} else if (form.icAfastamentoTemporario[1].checked && form.dtAfastamentoInicial.value == '') {
		msg += "Informe Data Inicial. \n";		
	} else {
		if (form.icAfastamentoTemporario[0].checked){
			if (comparaData(form.dtAfastamentoInicial.value, "<", form.dtAfastamentoFinal.value) || 
				comparaData(form.dtAfastamentoInicial.value, "=", form.dtAfastamentoFinal.value)) {

				//redirecionaSubmit(urlRedirect);
			} else {
				msg += 'Data final do período é anterior à data inicial. \n';				
			}
		}
	}
	msg += verificaDataInicial();
	
	if (msg!=""){		
		alert(msg);
		return false;
	} else {
		return true;
	}	
}


//Recupera Dados Popup
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
 	 if ('usuario' == tipoConsulta) {
 	 	if (form.tipoPesquisa.value == 'usuarioEspelho'){
		 	form.idUsuarioEspelho.value = codigoRegistro;
		 	form.nomeUsuarioEspelho.value = descricaoRegistro;
		 	form.tipoPesquisa.value = '';
		 	redirecionaSubmit("/gsan/exibirBloquearDesbloquearAcessoUsuarioAction.do?objetoConsulta=2");		 	
	 	} else {
	 		form.login.value = codigoRegistro;
			form.nomeUsuario.value = descricaoRegistro;
			form.tipoPesquisa.value = '';
			form.action = 'exibirBloquearDesbloquearAcessoUsuarioAction.do?objetoConsulta=1';
			form.submit();	
	 	}
	 		
	}
}
function limparUsuarioEspelho() {
	if (!document.forms[0].idUsuarioEspelho.disabled && !document.forms[0].idUsuarioEspelho.readOnly){
 		document.forms[0].nomeUsuarioEspelho.value = '';
 		document.forms[0].idUsuarioEspelho.value = '';
	}                  
}
function limparForm(){
	redirecionaSubmit("/gsan/exibirBloquearDesbloquearAcessoUsuarioAction.do?limpar=sim");	
} 

function validaDataFinal(){	
	var form = document.forms[0];
	//if (form.motivoAfastamento.selectedIndex!=-1 && form.motivoAfastamento.selectedIndex!=0 && 
		//	form.idAfastamento.value!=null && form.idAfastamento.value!="0"){
		if (comparaData(form.dtAfastamentoInicial.value, ">", form.dtAfastamentoFinal.value)) {
			alert('Data de afastamento final não pode ser inferior à data inicial. ');
			form.dtAfastamentoFinal.value = "";
			form.dtAfastamentoFinal.focus();
		}
	//}
}

function validaDataInicial(){
	var data = new Date();
	var form = document.forms[0];
	var mes = (data.getMonth()+1)<10?"0"+(data.getMonth()+1):(data.getMonth()+1);
	var ano = data.getYear();
	if (ano < 1900) ano +=1900;
	var hoje = data.getDate() + "/" + mes + "/" + (ano);	
	if (comparaData(form.dtAfastamentoInicial.value, "<", hoje)) {
		alert('Data de afastamento inicial não pode ser inferior à data atual. ');
		form.dtAfastamentoInicial.value = "";
		form.dtAfastamentoInicial.focus();
	}
}

function verificaDataInicial(){ //Retorna msg
	var data = new Date();
	var form = document.forms[0];
	var mes = (data.getMonth()+1)<10?"0"+(data.getMonth()+1):(data.getMonth()+1);
	var ano = data.getYear();
	if (ano < 1900) ano +=1900;
	var hoje = data.getDate() + "/" + mes + "/" + (ano);	
	if (comparaData(form.dtAfastamentoInicial.value, "<", hoje)) {
		form.dtAfastamentoInicial.value = "";
		return 'Data de afastamento inicial não pode ser inferior à data atual. \n';		
	} else {
		return '';
	}
}

function abrirPopupLocal(url, altura, largura){
	if (!document.forms[0].idUsuarioEspelho.disabled && !document.forms[0].idUsuarioEspelho.readOnly){
		document.forms[0].tipoPesquisa.value = 'usuarioEspelho';
		abrirPopup(url, altura, largura);
	}	
}

function chamarPopupPesquisaUsuario() {
	if (!document.forms[0].login.disabled && !document.forms[0].login.readOnly) {
		document.forms[0].tipoPesquisa.value = 'usuario';
		chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].login);
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, campo) {
	if (!campo.disabled && !campo.readOnly) {
		if (objeto == null || codigoObjeto == null) {
			if (tipo == "") {
				abrirPopup(url, altura, largura);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
						+ codigoObjeto, altura, largura);
			}
		}
	}
}

function limparPesquisaUsuario() {
	var form = document.forms[0];

	form.login.value = '';
	form.nome.value = '';	
}

function limparDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //Usuário			
			form.nomeUsuario.value = "";
			break;
		case 2: //Usuário Espelho		
			form.nomeUsuarioEspelho.value = "";
			break;				   
	}
}
</script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  
formName="BloquearDesbloquearAcessoUsuarioActionForm" />
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); javascript:habilitaSituacaoUsuario(document.forms[0]);">

<div id="formDiv">
<html:form action="/exibirBloquearDesbloquearAcessoUsuarioAction" method="post">
	<html:hidden property="idAfastamento"/>
	<html:hidden property="erroUsuarioEspelho"/>
	<input type="hidden" name="tipoPesquisa">
	<html:hidden property="dtAfastamentoFinalAux"/>
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Bloquear ou Desbloquear Acesso do Usuário</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para bloquear ou desbloquear o acesso do usuário ao
					sistema, informe os dados abaixo:</td>
				</tr>
				
				<!-- RM7146 - Pesquisar usuário com lupa -->
				<tr> 
		        	<td width="18%" >
		        		<strong>Usuário:<font color="#FF0000">*</font></strong>
		        	</td>
		        	<td width="82%" height="24" >
			            <html:text maxlength="11"
							property="login" size="10" tabindex="1" style="text-transform: none;" 
							onkeyup="limparDestino(1);"
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirBloquearDesbloquearAcessoUsuarioAction.do?objetoConsulta=1', 'login'); return isAlfaNumerico(event);" 
							/>
						<a 
						href="javascript:chamarPopupPesquisaUsuario();">
						<img name="imagem" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/>
						</a>
						 
						<logic:present name="usuarioPrincipalNaoEncontrado" scope="request">
						    <html:text maxlength="40" property="nomeUsuario" size="40" style="background-color:#EFEFEF; border:0; color: #ff0000" readonly="true"/>
                        </logic:present>
                        <logic:notPresent name="usuarioPrincipalNaoEncontrado" scope="request">
						    <html:text maxlength="40" property="nomeUsuario" size="40" style="background-color:#EFEFEF; border:0; color: #000000" readonly="true"/>
                        </logic:notPresent>
                        
						
						<a href="javascript:limparForm(); document.forms[0].login.focus();">
							<img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Usuário" /> </a>
						
		            </td>
		        </tr>
		        <!-- Fim RM7146 -->
		        				 
				<tr>
					<td><strong>Situação do Usuário:</strong></td>
					<td><html:select property="usuarioSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUsuarioSituacao"
							labelProperty="descricaoUsuarioSituacao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<!-- RM 3892 - Inclusão de dados de afastamento do usuário -->
				<tr>
					<td><strong>Tipo de afastamento:</strong></td>
					<td>
					<html:radio property="icAfastamentoTemporario" value="1">Temporário</html:radio>
					<html:radio property="icAfastamentoTemporario" value="2">Definitivo</html:radio>
					<html:radio property="icAfastamentoTemporario" value="3">Nenhum</html:radio>
					</td>
				</tr>
				<tr>
					<td>
					<strong>Motivo de afastamento:</strong></td>
					<td><html:select property="motivoAfastamento">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoMotivoAfastamento"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
					<td height="0"><strong>Período de Afastamento:</strong></td>
					<td colspan="3"><strong> <html:text
						property="dtAfastamentoInicial" size="10" maxlength="10"
						onkeyup="javascript:mascaraData(this,event);" 
						onkeypress="return isCampoNumerico(event);" onblur="validaDataInicial();" /> 
						<a
						href="javascript:abrirCalendario('BloquearDesbloquearAcessoUsuarioActionForm', 'dtAfastamentoInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a>
					</strong> <html:text property="dtAfastamentoFinal" size="10" onblur="validaDataFinal();"
						maxlength="10" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('BloquearDesbloquearAcessoUsuarioActionForm', 'dtAfastamentoFinal')"
						>
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				
				<tr>
					<td><strong>Usuário Espelho:</strong></td>
					<td colspan="3"><strong>
						<html:text maxlength="9" tabindex="9" property="idUsuarioEspelho" size="9" disabled="true" 
						onkeyup="limparDestino(2);"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirBloquearDesbloquearAcessoUsuarioAction.do?objetoConsulta=2', 'idUsuarioEspelho', 'Usuário que Gerou o Comando'); return isAlfaNumerico(event);" />
						<a href="javascript:abrirPopupLocal('exibirUsuarioPesquisar.do', 250, 495);">
						<img name="imagem" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário Espelho"/>
						</a>
												
		   		        <logic:present name="usuarioNaoEncontrado" scope="request">
						    <html:text maxlength="40" property="nomeUsuarioEspelho" size="40" style="background-color:#EFEFEF; border:0; color: #ff0000" readonly="true"/>
                        </logic:present>
                        <logic:notPresent name="usuarioNaoEncontrado" scope="request">
						    <html:text maxlength="40" property="nomeUsuarioEspelho" size="40" style="background-color:#EFEFEF; border:0; color: #000000" readonly="true"/>
                        </logic:notPresent>
					<a	href="javascript:limparUsuarioEspelho();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Usuário Espelho" /></a>
					</strong></td>
				</tr>
				<tr>
			      	<td width="25%"><strong>Observação:</strong></td>
			        <td width="75%">
						<html:textarea property="observacaoAfastamento" cols="40" rows="4" 
						onkeypress="return isAlfaNumericoComSpacePontuacao(event);"
						onkeyup="limitTextArea(document.forms[0].observacaoAfastamento, 300, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
						<strong><span id="utilizado">0</span>/<span id="limite">300</span></strong>
					</td>
		      	</tr>
				<!-- Fim RM 3892 -->
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						<input name="botaoLimpar" type="button"
							class="bottonRightCol" value="Limpar" align="right"
							onClick="javascript:limparForm();">					
						<input name="botaoCancelar" type="button"
							class="bottonRightCol" value="Cancelar" align="right"
							onClick="window.location.href='/gsan/telaPrincipal.do'"></td>
							
					<td align="right"><input name="botaoConcluir" type="button"
						class="bottonRightCol" value="Concluir" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>
				</tr>
				
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</div>

</body>
</html:html>

