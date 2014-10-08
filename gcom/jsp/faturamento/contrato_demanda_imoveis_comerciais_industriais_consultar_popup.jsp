<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarImoveisContratoDemandaPopupAction" method="post"
	name="ConsultarImoveisContratoDemandaPopupActionForm"
	type="gcom.gui.faturamento.ConsultarImoveisContratoDemandaPopupActionForm"
	onsubmit="return validateConsultarImoveisContratoDemandaPopupActionForm(this);">
	
	<table width="710" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="700" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Imóveis do Contrato de Demanda</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td width="170" height="22"><strong>Volume de Água Contratado:</strong></td>
					<td ><strong> 
				       <html:text property="volumeAgua" size="5"
						maxlength="5" disabled="true" /></strong>m³
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<table width="100%" bgcolor="#99CCFF" border="0">

				<tr bgcolor="#90c7fc">
					<td align="center" width="10%"><strong>Matrícula</strong></td>
					<td align="center" width="40%"><strong>Nome do Cliente Usuário</strong></td>
					<td align="center" width="20%"><strong>Inscrição</strong></td>
					<td align="center" width="20%"><strong>Principal Categoria</strong></td>
					<td align="center" width="10%"><strong>Quantidade Economias</strong></td>
				</tr>
				
				<logic:present name="colecaoContratoDemandaImovelHelper" scope="session" >
					<% String cor = "#cbe5fe";%>
					<logic:iterate name="colecaoContratoDemandaImovelHelper" id="helper" >
					
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe" height="18">		
						<%}%>
								<td width="10%" >
									<bean:write name="helper" property="idImovel" />
								</td>
								<td width="40%" >
									<bean:write name="helper" property="nomeClienteUsuario" />
								</td>
								<td width="20%" >
									<bean:write name="helper" property="inscricaoImovel" />
								</td>
								<td width="20%" >
									<bean:write name="helper" property="descricaoCategoria" />
								</td>
								<td width="10%" >
									<bean:write name="helper" property="quantidadeEconomias" />
								</td>
							
							</tr>
					</logic:iterate>
				</logic:present>
				</table>
				<table border="0" width="100%"> 			
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
			
			</table>
			</td>
		</tr>
	</table>

</html:form>
<body>
</html:html>
