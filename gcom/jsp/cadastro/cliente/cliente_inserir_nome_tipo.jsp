<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ClienteActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

//******************************************************
// Autor: Ivan Sergio
// Data: 20/07/2009
// CRC2103
// Verifica se o Cliente foi inserido e retorna os dados
// para o action de imovel.
//******************************************************
if (<%=request.getParameter("concluir")%> != null) {
	if (<%=request.getParameter("concluir")%> == true) {
		opener.recuperarDadosClientePopUp(
			'<%=session.getAttribute("codigoCliente")%>',
			'<%=session.getAttribute("nomeCliente")%>');
		window.close();
	}
}
//******************************************************

function validateClienteActionForm(form) {
	if (bCancel){
		return true;
	}else{
		return validateRequired(form) 
		&& validateEmail(form)
		&& validateLong(form)
	    && validarDiaVencimento()
	    && validarVencimentoMesSeguinte()
	    && validarNomeExibidoConta()
	    && validandoCaracterEspecial();
	}
}

function validandoCaracterEspecial(){
	
	retorno = true;

	var validarCaracterEspecialNome = document.getElementById("validarCaracterEspecialNome");

	if (validarCaracterEspecialNome.value == "1"){

		var form = document.forms[0];
		var clienteNome = form.clienteNome;

		var indesejaveis = "~{}^%$[]@|`\\<¨\#?!*>\"\'+()&";

		for (var i=0; i < indesejaveis.length; i++) {
			if ((clienteNome.value.indexOf(indesejaveis.charAt(i))) != -1 ){
				retorno = false;
			}
	    }
	}

	if (!retorno){
		alert("Nome do Cliente possui caracteres especiais.");
	}

	return retorno;
}


function required () {
	this.aa = new Array("nome", "Informe Nome.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("tipoPessoa", "Informe Tipo do Cliente.", new Function ("varName", " return this[varName];"));
}

function email () {
	this.aa = new Array("email", "E-Mail inválido.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations () {
	this.aa = new Array("diaVencimento", "Dia do Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function validarDiaVencimento(temPermissao){
var form = document.forms[0];

	if( form.diaVencimento.value != null &&  form.diaVencimento.value != ''&&
		 (form.diaVencimento.value < 1 || form.diaVencimento.value > 31)){	
			alert('Dia do vencimento de ser entre 01 e 31.');
			form.diaVencimento.focus();
			return false;
	}else{
		return true;
	}
}

function validarVencimentoMesSeguinte() {
	var form = document.forms[0];

	if (form.diaVencimento.value != null &&  form.diaVencimento.value != '') {
		if (form.indicadorVencimentoMesSeguinte[0].checked == false && form.indicadorVencimentoMesSeguinte[1].checked == false) {
			alert("Informe se o vencimento é para o mês seguinte")
			return false;
		}
	}
	
	return true;
}

function validarDiaVencimentoTecla() {
	var form = document.forms[0];

	if (form.diaVencimento.value == null || form.diaVencimento.value == '') {
		form.indicadorVencimentoMesSeguinte[0].checked = false;
		form.indicadorVencimentoMesSeguinte[1].checked = false;
		form.indicadorVencimentoMesSeguinte[0].disabled = true;
		form.indicadorVencimentoMesSeguinte[1].disabled = true;
	} else {
		form.indicadorVencimentoMesSeguinte[0].disabled = false;
		form.indicadorVencimentoMesSeguinte[1].disabled = false;
	}
}

// Função para redirecionar para a atualização quando é feita a pesquisa pela lupa do nome do cliente
function redirecionarSubmitAtualizar(id) {
	var form = document.forms[0];
	redirecionarSubmit('inserirClienteWizardAction.do?destino=1&action=exibirInserirClienteNomeTipoAction&idCliente=' + id);
}

function reloadPesquisaCliente(parametro1,parametro2,parametro3){
	var form = document.forms[0];
	if(parametro1!= '' && parametro2!= '' && parametro3!= '' && (parametro1!= undefined || parametro1!='')&& (parametro2!= undefined || parametro2!='')&& (parametro3!= undefined || parametro3!='')){
		if ( parametro1 != '' ) {
			form.clienteNome.value = parametro1;
			
		}
		if(parametro3 == 1){
		form.indicadorPessoaFisicaJuridica[0].checked = true;
		}else if (parametro3 == 2){
		form.indicadorPessoaFisicaJuridica[1].checked = true;
		}
		form.tipoPessoa.value = parametro2;		
	}

}

function validarNomeExibidoConta() {
	var form = document.forms[0];
	if (form.indicadorExibicaoNomeConta != undefined && form.indicadorExibicaoNomeConta != 'undefined' && form.indicadorExibicaoNomeConta.value != null &&  form.indicadorExibicaoNomeConta.value != '') {
		if (form.indicadorExibicaoNomeConta.value == '-1') {
			alert("Informe Nome a Ser Exibido na Conta:")
			return false;
		}
	}
	
	return true;
}

function validarPermiteNegativacao() {
	var form = document.forms[0];
	form.indicadorPermiteNegativacao[1].checked = "true";
	
}
function validacaoCaracteresEspeciais(evento) {
	
    var tecla = null;
    if(window.event){ // Internet Explorer
  		tecla = evento.keyCode;
      }else if(evento.which){ // Nestcape ou Mozilla
    	tecla = evento.which;
       }
	  if(tecla == null){//tab
		  return true;
	  }
	  if(tecla == 199||tecla == 231){// ç
          return true;
      }
       if(tecla == 59 || tecla == 46){ // ponto(.) e ponto e virgula (;)
            return true;
      }      
	  if((tecla > 47 && tecla < 58) || (tecla.value == 'undefined')){ // numeros de 0 a 9
	    return true;
	  }
	  if (tecla == 8 || tecla == 13){ // backspace ou enter
	        return true;
      }
      if(tecla >=65 && tecla <=90){// capsLook
 			return true;
      }
      if(tecla == 32){// espace
           return true;
       }
      if (tecla>=97 && tecla<=122){ //Letras a-z
    	  return true;
      }
      
      return false;
}

function replicarNome(){
	var form = document.forms[0];

	if ( form.clienteNome.length < 50 ) {
		form.nome.value = form.clienteNome.value;
	} else {
		form.nome.value = form.clienteNome.value.substring(0,49);
	}
	
}

function habilitaNome() {
	var form = document.forms[0];
	
	if ( form.permissaoEspecialHabilitarNome.value != null && form.permissaoEspecialHabilitarNome.value == "1" ) {
		form.nome.disabled = false;
	} else {
		form.nome.disabled = true;
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');validarDiaVencimentoTecla();reloadPesquisaCliente('${requestScope.nome}','${requestScope.tipoPessoa}','${requestScope.indicadorPessoaFisicaJuridica}');validarPermiteNegativacao();habilitaNome();">
<div id="formDiv"><html:form action="/inserirClienteWizardAction"
	method="post" onsubmit="return validateClienteActionForm(this);">
	
	<!-- CASO POPUP: Reposiciona as Abas -->
	<logic:equal name="POPUP" value="true" scope="session">
	<div id='Layer1PopUp' style='position:absolute; left:; top:-67px; width:300px; height:12px; z-index:1'>
	</logic:equal>
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />
	<logic:equal name="POPUP" value="true" scope="session">
	</div>
	</logic:equal>
	
	<!-- CASO POPUP: Retira o Cabecalho e o Menu -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>
	<html:hidden property="permissaoEspecialHabilitarNome" />
	<input type="hidden" name="numeroPagina" value="1" />
	<!--<input type="hidden" name="indicadorExibicaoNomeConta" />
	--><table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<!-- CASO POPUP: Retira a coluna de Informacoes do Usuario -->
			<logic:equal name="POPUP" value="false" scope="session">
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
			</logic:equal>
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
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para adicionar o nome e tipo do cliente, informe os dados
					abaixo:
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteInserirAbaNomeTipo', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
			</tr>
			</table>

			<table width="100%" border="0">
			
				<logic:present name="indicadorNomeFantasia" scope="session">
			
					<tr>
						<td width="18%">
						<strong><bean:write name="descricaoNomeCliente" scope="session"/><font color="#FF0000">*</font></strong>
						
						</td>
						<td width="82%">
						<input type="hidden" id="validarCaracterEspecialNome" value="1"/>
						<html:text maxlength="100" property="clienteNome" size="50" tabindex="1" onkeypress="return validacaoCaracteresEspeciais(event);"
						 onkeyup="javascript:replicarNome();" onblur="replicarNome();"/>
							<!-- CASO POPUP: Retira o Pesquisar Cliente(Nao faz sentido no inserir) -->
							<logic:equal name="POPUP" value="false" scope="session">
							<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=ok&indicadorUsoTodos=1&consultaCliente=sim', 400, 800);">
							<img width="23" height="21" border="0" title="Pesquisar Cliente" src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif" /></a>
							</logic:equal>
						</td>
					</tr>
					
					<tr>
						<td width="18%">
						<strong><bean:write name="descricao" scope="session"/><font color="#FF0000">*</font></strong>
						
						</td>
						<td width="82%">
						<html:text maxlength="100" property="nome" size="50" tabindex="1" onkeypress="return validacaoCaracteresEspeciais(event);" />
							
						</td>
					</tr>
					
					
					<tr>
						<td>
						<strong><bean:write name="descricaoAbreviada" scope="session"/></strong>
						</td>
						<td><html:text maxlength="40" property="nomeAbreviado" size="45"
							tabindex="2" onkeypress="return validacaoCaracteresEspeciais(event);" /></td>
					</tr>
					
					<tr>
					<td><strong>Selecione o Nome a Ser Exibido na Conta:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="indicadorExibicaoNomeConta" tabindex="5">
								<html:option  value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:option  value="1">Nome de Fantasia</html:option>
								<html:option  value="2">Nome na Receita Federal</html:option>
							
						</html:select>
					</td>
				</tr>
					
					
				</logic:present>
				
				<logic:notPresent name="indicadorNomeFantasia" scope="session">

						<tr>
							<td width="18%"><strong><bean:write name="descricaoNomeCliente"
								scope="session" /><font color="#FF0000">*</font></strong></td>
							<td width="82%">
							<input type="hidden" id="validarCaracterEspecialNome" value="2"/>
							<html:text maxlength="100" property="clienteNome"
								size="50" tabindex="1" onkeyup="javascript:replicarNome();" onblur="replicarNome();"/>
								<logic:equal name="POPUP" value="false" scope="session">
								<a
									href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=ok&indicadorUsoTodos=1&consultaCliente=sim', 400, 800);">
								<img width="23" height="21" border="0" title="Pesquisar Cliente"
									src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif" /></a>
							</logic:equal> 
							</td>
						</tr>

						<tr>
							<td width="18%"><strong><bean:write name="descricao"
								scope="session" /><font color="#FF0000">*</font></strong></td>
							<td width="82%"><html:text maxlength="50" property="nome"
								size="50" tabindex="1" /> <!-- CASO POPUP: Retira o Pesquisar Cliente(Nao faz sentido no inserir) -->
							</td>
						</tr>
						
						<tr>
							<td><strong><bean:write name="descricaoAbreviada"
								scope="session" /></strong></td>
							<td><html:text maxlength="40" property="nomeAbreviado"
								size="45" tabindex="2" /></td>
						</tr>
				</logic:notPresent>
				
				
				
				<tr>
					<td><strong>Tipo de Pessoa:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<logic:equal name="POPUP" value="false" scope="session">
						<html:radio property="indicadorPessoaFisicaJuridica" value="1" tabindex="3" onchange="redirecionarSubmit('inserirClienteWizardAction.do?destino=1&action=exibirInserirClienteNomeTipoAction');" />Física 
						<html:radio property="indicadorPessoaFisicaJuridica" value="2" tabindex="4" onchange="redirecionarSubmit('inserirClienteWizardAction.do?destino=1&action=exibirInserirClienteNomeTipoAction');" />Jurídica
						</logic:equal>
						<logic:equal name="POPUP" value="true" scope="session">
						<html:radio property="indicadorPessoaFisicaJuridica" value="1" tabindex="3" onchange="redirecionarSubmit('exibirInserirClienteAction.do?POPUP=true');" />Física 
						<html:radio property="indicadorPessoaFisicaJuridica" value="2" tabindex="4" onchange="redirecionarSubmit('exibirInserirClienteAction.do?POPUP=true');" />Jurídica
						</logic:equal>
					</td>
				</tr>
			
				<tr>
					<td><strong>Tipo do Cliente:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:select property="tipoPessoa" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoTipoPessoa">
						<html:options collection="colecaoTipoPessoa"
							labelProperty="descricaoComId" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>E-Mail:</strong></td>
					<td><html:text maxlength="40" property="email" size="40"
						tabindex="6" style="text-transform: none;" /></td>
				</tr>
				
				<logic:equal name="temPermissaoVisualizarDiaVencimentoContaCliente"
					value="true">
					<tr>
						<td><strong>Dia do Vencimento da Conta:</strong></td>
						<td><html:text maxlength="2" property="diaVencimento" size="2"
							tabindex="7" onkeypress="return isCampoNumerico(event);" onkeyup="validarDiaVencimentoTecla();"/></td>
					</tr>
				</logic:equal>
				<logic:notEqual
					name="temPermissaoVisualizarDiaVencimentoContaCliente" value="true">
					<tr>
						<td><strong>Dia do Vencimento da Conta:</strong></td>
						<td><html:text maxlength="2" property="diaVencimento" size="2"
							tabindex="7" style="background-color:#EFEFEF; border:0; color: #ff0000" readonly="true" /></td>
					</tr>
				</logic:notEqual>
				<tr>
					<td><strong>Vencimento para Mês Seguinte?</strong></td>
					<td>
					<html:radio property="indicadorVencimentoMesSeguinte"
						value="<%=ConstantesSistema.SIM.toString()%>" tabindex="8" />
					<strong>Sim</strong> <html:radio property="indicadorVencimentoMesSeguinte"
						value="<%=ConstantesSistema.NAO.toString()%>" tabindex="8" />
					<strong>Não</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Permite Geração de Fatura Antecipada?<font color="#FF0000">*</font></strong></td>
					<td>
					<html:radio property="indicadorGeraFaturaAntecipada"
						value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" />
					<strong>Sim</strong> <html:radio property="indicadorGeraFaturaAntecipada"
						value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
					<strong>Não</strong>
					</td>
				</tr>
			
				<tr>
					<td><strong>Cliente bloqueado para negativação?<font color="#FF0000">*</font></strong></td>
					
					<logic:present name="permissaoEspecial" scope="session">
						<td>
						<html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9"/>
						<strong>Sim</strong> <html:radio property="indicadorPermiteNegativacao" 
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:present>
					
					<logic:notPresent name="permissaoEspecial" scope="session">
						<td>
						<html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" disabled="true"/>
						<strong>Sim</strong> <html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:notPresent>
				</tr>
				
				<tr>
					<td><strong>Negativação por Período?<font color="#FF0000">*</font></strong></td>
					<td>
					<html:radio property="indicadorNegativacaoPeriodo"
						value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" />
						<strong>Sim</strong>		
					<html:radio property="indicadorNegativacaoPeriodo"
						value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
					</td>
				</tr>

                <tr>
					<td><strong>Bloquear Despesas Postais?<font color="#FF0000">*</font></strong></td>
					<logic:present name="permissaoDebitoOriundoEntregaPostal" scope="session">
						<td>
						<html:radio property="indicadorBloquearDespesasPostais"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9"/>
						<strong>Sim</strong> <html:radio property="indicadorBloquearDespesasPostais" 
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:present>
					
					<logic:notPresent name="permissaoDebitoOriundoEntregaPostal" scope="session">
						<td>
						<html:radio property="indicadorBloquearDespesasPostais"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" disabled="true"/>
						<strong>Sim</strong> <html:radio property="indicadorBloquearDespesasPostais"
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:notPresent>
				</tr>
				
				<tr>
					<td><strong>Bloquear Alteração de Imóveis Associados?<font color="#FF0000">*</font></strong></td>			
					<logic:present name="permissaoIndicadorBloqueioImoveisAssociados" scope="session">
						<td>
						<html:radio property="indicadorBloquearAlterarImoveis"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9"/>
						<strong>Sim</strong> <html:radio property="indicadorBloquearAlterarImoveis" 
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:present>
					
					<logic:notPresent name="permissaoIndicadorBloqueioImoveisAssociados" scope="session">
						<td>
						<html:radio property="indicadorBloquearAlterarImoveis"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" disabled="true"/>
						<strong>Sim</strong> <html:radio property="indicadorBloquearAlterarImoveis"
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:notPresent>
				</tr>
		
				<tr>
					<td><strong></strong></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<!-- CASO POPUP: Retira o Rodape -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:equal>
	
	</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirClienteWizardAction.do?concluir=true&action=inserirClienteNomeTipoAction'); }

<logic:equal name="POPUP" value="true" scope="session">
//Altera o onclick do Cancelar caso seja chamado pelo popup
document.getElementById('botaoCancelar').onclick = function() { window.close(); }

//Altera o onclick do Desfazer caso seja chamado pelo popup
var botao = new String(document.getElementById('botaoDesfazer').onclick);
var acao = botao.substring( (botao.indexOf('"') + 1) , botao.lastIndexOf('"'));
acao = acao.replace('menu=sim', 'POPUP=true&desfazer=true');
document.getElementById('botaoDesfazer').onclick = function() { window.location.href = acao; }
</logic:equal>

</script>

</html:html>
