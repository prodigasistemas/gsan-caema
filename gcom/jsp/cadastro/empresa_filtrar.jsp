<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarEmpresaActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
			if(validateFiltrarEmpresaActionForm(formulario)){    		
	    		if(validateInteger(formulario)){	     
		  			submeterFormPadrao(formulario);
		  		}
		}
	}


    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	
	
	function limparForm() {
		var form = document.AtualizarEmpresaActionForm;
		form.codigo.value = "";
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.email.value = "";
	
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('id');">
<html:form action="/filtrarEmpresaAction"
	name="FiltrarEmpresaActionForm"
	type="gcom.gui.cadastro.FiltrarEmpresaActionForm" method="post"
	onsubmit="return validateFiltrarEmpresaActionForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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



			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar a(s) empresa(s), informe o dado
					abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" tabindex="1"/><strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="8" maxlength="8" tabindex="2"/><font
						size="1">&nbsp;(somente n�meros)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="50" maxlength="50" tabindex="3"/> </span></td>
					<td width="15%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="10" maxlength="10" tabindex="6"/> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td colspan="2"><span class="style2"> <html:text property="email"
						size="60" maxlength="80" tabindex="7"/> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Empresa Principal?</strong></td>
					<td><html:radio property="indicadorEmpresaPrincipal" tabindex="8"
						value="1">
						<strong>Sim</strong>
					</html:radio> <html:radio property="indicadorEmpresaPrincipal"
						tabindex="9" value="2">
						<strong>N�o</strong>
					</html:radio> <html:radio property="indicadorEmpresaPrincipal"
						tabindex="10" value="">
						<strong>Todos</strong>
					</html:radio></td>
					<td>&nbsp;</td>
				</tr>

			

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="11" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="12" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="13" value="" /><strong>Todos</strong>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Indicador Empresa PPP:</strong></td>
					<td><html:radio property="indicadorEmpresaPPP" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorEmpresaPPP" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorEmpresaPPP" value="" /><strong>Todos</strong>
					</td>
				</tr>
								
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarEmpresaAction.do?menu=sim'"
						tabindex="8"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9" /></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
