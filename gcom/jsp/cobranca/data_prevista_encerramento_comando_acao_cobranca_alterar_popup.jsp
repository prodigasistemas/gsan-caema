<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html> 
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<html:javascript staticJavascript="false"  formName="ConsultarComandosAcaoCobrancaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if (validateConsultarComandosAcaoCobrancaActionForm(form)){
			submeterFormPadrao(form);
		}
	}
 
	function limpar(){
		var form = document.forms[0];
		form.dataPrevistaEncerramento.value = "";
	}

</script>

<logic:equal name="fechaPopup" value="false" scope="session">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(580, 300);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>

<logic:equal name="fechaPopup" value="true" scope="session">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(580, 300);chamarReload('${sessionScope.retornarTela}');window.close();" >
</logic:equal>

</head>


 <html:form action="/alterarDataPrevistaEncerramentoComandoAcaoCobrancaPopupAction"
	name="ConsultarComandosAcaoCobrancaActionForm"
	type="gcom.gui.cobranca.ConsultarComandosAcaoCobrancaActionForm"
	onsubmit="return validateConsultarComandosAcaoCobrancaActionForm(this);"
	method="post">
	
	<html:hidden property="comando" />
	
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="500" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Alterar Data Prevista para Encerramento Comando Ação Cobrança</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" border="1">
        <tr>
          <td colspan="4">Para alterar a data prevista, informe os dados abaixo:</td>
        </tr>
       	
       	<tr>
			<td height="25"><strong>Data Prevista para Encerramento:</strong><font color="#FF0000">*</font></td>
			<td align="right">
				<div align="left"><html:text property="dataPrevistaEncerramento" size="10"
						onkeypress="return isCampoNumerico(event);"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"
						onclick="javascript:abrirCalendario('ConsultarComandosAcaoCobrancaActionForm', 'dataPrevistaEncerramento')" />
				dd/mm/aaaa</div>
			</td>
		</tr>
		
        <tr>
			<td><strong> <font color="#FF0000"></font></strong></td>
			<td align="right">
			<div align="left"><strong><font color="#FF0000">*</font></strong>
			Campos obrigat&oacute;rios</div>
			</td>
		</tr>
	
        <tr>
   	      <td>&nbsp;</td>
          <td>&nbsp;</td>
       	</tr>
    	
  		<tr>
			<td colspan="2"> 
				<input type="button" name="Button" class="bottonRightCol"
					value="Limpar"
					onclick="limpar();">
				
				<input type="button" name="Button" class="bottonRightCol"
					value="Cancelar"
					onclick="window.close();">	
			</td>
		
			<td align="right">
				<input name="Button" 
					type="button"
					class="bottonRightCol" 
					value="Alterar"
					onclick="validarForm(document.forms[0]);" />
			</td> 
		</tr>
	  </table>
	</td>
	</tr>
	</table>
</html:form>
</html:html>