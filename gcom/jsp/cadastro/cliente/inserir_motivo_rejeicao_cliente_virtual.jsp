<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script type="text/javascript">
	var bCancel = false;
	
	function validateInserirMotivoRejeicaoClienteVirtualActionForm(form) {
		if (bCancel)
			return true;
		else
		    return validateRequired(form);
	}
	
	function required () {
		this.aa = new Array("motivoRejeicao", "Informe Motivo.", new Function ("varName", " return this[varName];"));
	}
	
	function validarForm(form){
		if(validateInserirMotivoRejeicaoClienteVirtualActionForm(form)){
			submeterFormPadrao(form);
		}
	}

</script>

</head>

<html:html>

<body leftmargin="5" topmargin="5">
<div id="motivoDiv">
	<html:form action="/inserirMotivoRejeicaoClienteVirtualAction" method="post"
	 	name="InserirMotivoRejeicaoClienteVirtualActionForm" 
		type="gcom.gui.cadastro.InserirMotivoRejeicaoClienteVirtualActionForm"
		onsubmit="return validateInserirMotivoRejeicaoClienteVirtualActionForm(this);">
		
		<input type="hidden" property="idClienteVirtual">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="550" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Inserir Motivo Rejeição Cliente Virtual</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td>Para rejeitar o cliente virtual, informe os dados abaixo:</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><strong>Motivo de Rejeição:<font color="#FF0000">*</font></strong>
						<td><html:select property="motivoRejeicao" tabindex="5">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoMotivos" scope="request">
									<html:options collection="colecaoMotivos"
										labelProperty="descricaoMotivo" property="id" />
								</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="25%"><strong>Observação:</strong></td>
        				<td width="75%">
							<html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
								<strong><span id="utilizado">0</span>/<span id="limite">400</span></strong>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="24" colspan="2">
			          		<input type="button" name="Button" class="bottonRightCol" value="Cancelar" align="left"
								tabindex="7" onClick="javascript:window.close();"/>	
						</td>
						
						<td align="right">
							<input name="Button2" type="button" class="bottonRightCol" value="Concluir" 
								onClick="javascript:validarForm(document.forms[0]);" tabindex="8" />
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				</td>
			</tr>
		</table>
	</html:form>
</div>
</body>

</html:html>
