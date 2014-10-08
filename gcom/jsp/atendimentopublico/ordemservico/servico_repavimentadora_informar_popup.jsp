<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.atendimentopublico.ordemservico.bean.ServicoRepavimentadoraHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript dynamicJavascript="false" staticJavascript="false"  formName="InformarServicoRepavimentadoraPopupActionForm"/>

<script>	
<!--

function validarForm(form){
	submeterFormPadrao(form);
}

function associar(form){
	if(form.servico.value == '-1'){
		alert ('Informe Serviço da Repavimentadora');
	}else if(form.quantidade.value == ''){
		alert ('Informe Quantidade');
	}else{
		form.action = 'exibirInformarServicosRepavimentadoraAction.do?associar=sim';
		form.submit();
	}
}	

-->
</script>

</head>
<body leftmargin="5" topmargin="5" onload="window.focus();setarFoco('${requestScope.nomeCampo}');resizePageSemLink(600, 500);">

<html:form action="/informarServicosRepavimentadoraAction" method="post"
	name="InformarServicoRepavimentadoraPopupActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarServicoRepavimentadoraPopupActionForm" >

	<html:hidden property="idOrdemServico" />
	<html:hidden property="idUnidadeRepavimentadora" />
	
<table cellSpacing=5 cellPadding=0 width=515 border=0>

	<tr>
		<td class=centercoltext valign=top width=505>
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

  			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	      		<tr> 
        			<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
        			<td class="parabg">Informar Serviços da Repavimentadora</td>
        			<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
	      		</tr>
   			</table>
			
			<table width="100%" border=0>
				<tr>
					<td height=234>
						<table width="100%" border=0 dwcopytype="CopyTableRow">
							<tr>
								<td colspan=3>Para informar serviços da repavimentadora, informe os dados abaixo:</td>
							</tr>
							
							<tr>
								<td colSpan=3 height=17>&nbsp;</td>
							</tr>
							
							<tr>
								<td width="29%" height=24><strong>Serviço:</strong></td>
								<td colspan=2>
               						<strong>
			       						<html:select property="servico" >
											<html:option value="-1">&nbsp;</html:option>
											<logic:notEmpty name="colecaoServicosRepavimentadora">
												<html:options collection="colecaoServicosRepavimentadora" labelProperty="descricao" property="id" />
											</logic:notEmpty>
										</html:select> 
               						</strong>
								</td>
							</tr>
							
							<tr>
								<td width="29%" height=24><strong>Quantidade:</strong></td>
								<td width="71%" colSpan=2>
									<html:text maxlength="12" size="12" onkeyup="formataValorMonetario(this, 9)" property="quantidade"/>
								</td>
							</tr>
							
							<tr>
								<td colspan="3" class="style1">
								<div align="right"><input type="button" name="ButtonAssociar"
									class="bottonRightCol" value="Associar"
									onclick="javascript:associar(document.forms[0]);">
								</div>
								</td>
							</tr>
							
							<tr>
								<td colspan="5"><div style="height:60px;overflow:auto" >
									<table width="100%" bgcolor="#99CCFF">
										<!--header da tabela interna -->
										<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
											<td width="10%">
											<div align="center" class="style9"><strong>Remover</strong></div>
											</td>
											<td width="60%">
											<div align="center" class="style9"><strong>Serviço</strong></div>
											<td width="30%">
											<div align="center"><strong>Quantidade</strong></div>
											</td>
										</tr>
										<%String cor = "#FFFFFF";%>
										<logic:notEmpty name="InformarServicoRepavimentadoraPopupActionForm" property="colecaoServicoRepavimentadoraHelper">
											<logic:iterate indexId="posicao" name="InformarServicoRepavimentadoraPopupActionForm" 
												property="colecaoServicoRepavimentadoraHelper" type="ServicoRepavimentadoraHelper" id="helper" scope="session" >
											
												<%if (cor.equalsIgnoreCase("#FFFFFF")) {
													cor = "#cbe5fe";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													cor = "#FFFFFF";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
				
													<td>
													<div align="center" class="style9"><img
														src="<bean:message key='caminho.imagens'/>Error.gif"
														width="14" height="14" style="cursor:hand;"
														onclick="redirecionarSubmit('exibirInformarServicosRepavimentadoraAction.do?remover=sim&posicao=<bean:write name="helper" property="idServico" />');"></div>
													</td>
													<td>
													<div align="center" class="style9"><bean:write
														name="helper" property="descricao" /></div>
													</td>
													<td>
													<div align="center" class="style9"><bean:write
														name="helper" property="quantidade" /></div>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
								</div></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>		
		
						</table>
						<hr>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<table width="100%" border=0>	
							<tr>
								<td align="left" >
									<input class="bottonRightCol" type="button" value="Fechar" name="ButtonFechar" onclick="javascript:window.close();"/>
								</td>
								<td align="right">
									<input class="bottonRightCol" type="button" value="Inserir" name="ButtonInserir" onclick="javascript:validarForm(document.forms[0]);"/> 
								</td>	
							</tr>
						</table>
					</td>
				</tr>
	
			</table>
		<P>&nbsp;</P>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>