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
<html:javascript staticJavascript="false"
	formName="FiltrarSubsistemaAbastecimentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(){

	var form = document.forms[0];

	if (validateFiltrarSubsistemaAbastecimentoActionForm(form)){
		botaoAvancarTelaEspera('/gsan/filtrarSubsistemaAbastecimentoAction.do');
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
	
function setaFocus(){
	var form = document.FiltrarSubsistemaAbastecimentoActionForm;
	
	form.codigo.focus();
}

function limparForm() {
	var form = document.FiltrarSubsistemaAbastecimentoActionForm;

	form.indicadorAtualizar.checked = false;
	form.codigo.value = "";
	form.descricao.value = "";
	form.tipoPesquisa.checked = false;
	form.tipoPesquisa.value = "";
	form.descricaoAbreviada.value = "";
	form.idSistemaAbastecimento.value = "-1";
	form.indicadorUso.checked = false;
}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}'); setaFocus();">
<div id="formDiv"><html:form action="/filtrarSubsistemaAbastecimentoAction"
	name="FiltrarSubsistemaAbastecimentoActionForm"
	type="gcom.gui.operacional.FiltrarSubsistemaAbastecimentoActionForm"
	method="post"
	onsubmit="return validateFiltrarSubsistemaAbastecimentoActionForm(this);">


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
					<td class="parabg">Filtrar Subsistema de Abastecimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">



				<tr>
					<td width="100%" colspan=2>
					<table width="100%" border="0">
						<tr>
							<td>Para filtrar um subsistema de abastecimento no sistema, informe
							os dados abaixo:</td>
							<td align="right"><html:checkbox
							property="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td width="145"><strong>C&oacute;digo:</strong></td>
					<td width="470"><html:text property="codigo" size="5"
						maxlength="3" tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong>Descrição:</strong></td>
					<td><html:text property="descricao" size="22" maxlength="20"
						tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio
						property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td><strong>Descrição Abreviada:</strong></td>
					<td><html:text property="descricaoAbreviada" size="8" maxlength="6"
						tabindex="3" /></td>
				</tr>
				<tr>
					<td><strong>Sistema de Abastecimento:</strong></td>
					<td><html:select property="idSistemaAbastecimento" tabindex="4">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoSistemaAbastecimento"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" value="1" /><strong>Ativo <html:radio
						property="indicadorUso" value="2" />Inativo <html:radio
						property="indicadorUso" value="3" />Todos</strong></td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="javascript:window.location.href='/gsan/exibirFiltrarSubsistemaAbastecimentoAction.do?menu=sim'">
					</td>
					<td align="right"><INPUT type="button"
						onclick="validarForm();" class="bottonRightCol"
						value="Filtrar" tabindex="3" style="width: 70px;"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>