<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OsAcompanhamentoMotivoNaoAceite" %>


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
<html:javascript staticJavascript="false"
	formName="InserirMotivoNaoAceiteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	function validarForm(form) {

		if (validateInserirMotivoNaoAceiteActionForm(form)) {
			submeterFormPadrao(form);
		}
		
	}

	function limparForm(form) {

		form.indicadorObservacaoObrigatoriedade[0].checked = false;
		form.indicadorObservacaoObrigatoriedade[1].checked = false;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";

	}
	 
	function validarIndicadorObservacaoObrigatoriedade() {
		var form = document.forms[0];
		form.indicadorObservacaoObrigatoriedade[1].checked = "true";
		
	}

	//function letrasNumeros(){
		 	/*
		    * 48 a 57 - Numeros
		    * 65 a 90 - Letras Minusculas
		    * 97 a 122 - Letras Maiusculas
		    * 8  = [BackSpace]
		    * 9  = [TAB]
		    */
		//if ((tecla >= 48 && tecla <= 57) || (tecla >= 65 && tecla <= 90) || (tecla>=97 && tecla<=122) || (tecla == 8) || (tecla == 9)) {
	      //  return true;
	    //}
	    //else {
	      //  return false;
	    //}
		
	//}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); validarIndicadorObservacaoObrigatoriedade();">
	<!-- onKeyDown="return letrasNumeros(event.keyCode, event.which);"-->

	<html:form action="/inserirMotivoNaoAceiteAction" method="post"
		name="InserirMotivoNaoAceiteActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.InserirMotivoNaoAceiteActionForm"
		onsubmit="return validateMotivoNaoAceiteActionForm(this);">
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
					</table> <!--Início Tabela Reference a Páginação da Tela de Processo-->
					<table>
						<tr>
							<td></td>

						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Inserir Motivo de não aceite</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>

					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<table width="100%" border="0">
						<tr>

							<td colspan="2">Para inserir um motivo de não aceite,
								informe os dados abaixo:</td>
						</tr>

						<tr>
							<td HEIGHT="30" width="30%"><strong>Descri&ccedil;&atilde;o:
									<font color="#FF0000">*</font> </strong></td>
							<td colspan="2"><html:text property="descricao"
									maxlength="50" size="40" /></td>
						</tr>

						<tr>
							<td><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>
							<td><strong> <html:text
										property="descricaoAbreviada" size="7" maxlength="5" /> </strong></td>
						</tr>

						<tr>
							<td><strong>Indicador de observação de obrigatoriedade: <font color="#FF0000">*</font></strong></td>

							<td align="right">
       			    		<div align="left">
                        		<html:radio property="indicadorObservacaoObrigatoriedade" value="1" tabindex="12" /><strong>Sim</strong>
								<html:radio property="indicadorObservacaoObrigatoriedade" value="2" tabindex="13" /><strong>N&atilde;o</strong></div>
							</td>
						</tr>						
						<tr>
							<td height="19"><strong> <font color="#FF0000"></font>
							</strong></td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font> </strong> Campos
									obrigat&oacute;rios
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Desfazer" align="left"
								onclick="window.location.href='/gsan/exibirInserirMotivoNaoAceiteAction.do?menu=sim'">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right" height="24"><input type="button"
								name="Button" class="bottonRightCol" value="Inserir"
								onClick="javascript:validarForm(document.forms[0])" />
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<p>&nbsp;</p>
			</tr>
		</table>
		<tr>
			<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>
	</html:form>
</body>
</html:html>
