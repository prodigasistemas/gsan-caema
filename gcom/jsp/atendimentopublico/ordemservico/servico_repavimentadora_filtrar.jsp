<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>


<SCRIPT LANGUAGE="JavaScript">
<!--
	function verificarChecado(valor){
		var form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function validarForm(){
		var form = document.FiltrarServicoRepavimentadoraActionForm;
		form.action = 'filtrarServicoRepavimentadoraAction.do?indicadorAtualizar=' + form.indicadorAtualizar.value;
		if(validateFiltrarServicoRepavimentadoraActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
	
	function verificarValorAtualizar(){
		var form = document.FiltrarServicoRepavimentadoraActionForm;
       	
       	if (form.indicadorAtualizar.checked == true) {
       		form.indicadorAtualizar.value = '1';
       	} else {
       		form.indicadorAtualizar.value = '';
       	}
       	
	}
	
	function limparForm(){
		var form = document.forms[0];
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.unidadeMaterial.value = "-1";
		form.unidadeRepavimentadora.value = "-1";
		form.indicadorUso[0].checked = false;
		form.indicadorUso[1].checked = false;
		form.indicadorUso[2].checked = true;
	}
	
	
//-->
</SCRIPT>

</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarServicoRepavimentadoraActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${indicadorAtualizar}');">

<html:form action="/filtrarServicoRepavimentadoraAction"
	name="FiltrarServicoRepavimentadoraActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarServicoRepavimentadoraActionForm"
	method="post"
	onsubmit="return validateFiltrarServicoRepavimentadoraActionForm(this);">


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Filtrar Servi�o Repavimentadora</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o(s) servi�os da repavimentadora, informe os dados
							abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1"
								onclick="javascript:verificarValorAtualizar();" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="162"><strong>Descri��o:</strong></td>
					<td colspan="2"><html:text property="descricao" size="45"
						maxlength="50" tabindex="1" /></td>
				</tr>

				<tr>
					<td><strong>Descri��o Abreviada:</strong></td>
					<td colspan="2"><html:text property="descricaoAbreviada"
						size="10" maxlength="10" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Unidade do Servi�o:</strong></td>
					<td>
						<html:select property="unidadeMaterial" tabindex="3" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMaterialUnidade" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Repavimentadora:</strong></td>
					<td><html:select property="unidadeRepavimentadora" tabindex="3" style="width:200px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeRepavimentadora"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="4" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="5" />Inativo
					<html:radio property="indicadorUso" value="3" tabindex="6" />Todos</strong>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="javascript:limparForm();" tabindex="7"></td>
					<td align="right" colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Filtrar" align="left"
						onclick="javascript:validarForm();" tabindex="8"></td>
					<td align="right"></td>
				</tr>

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>

