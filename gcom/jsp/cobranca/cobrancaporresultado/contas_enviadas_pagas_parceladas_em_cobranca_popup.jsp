<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	
		<%@ include file="/jsp/util/titulo.jsp"%>
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script type="text/javascript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
	</head>
	<body leftmargin="5" topmargin="5">	
	<%@ include file="/jsp/util/cabecalho.jsp"%>	
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
		<td class="centercoltext" valign="top">		
			<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Situação das Contas em Empresas de Cobrança</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>		
				<p>&nbsp;</p>
				
				<table width="100%" align="center" border="0">
					<tr>
					<td>
					<logic:notEmpty name="contasEnviadasCobranca">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="7" bgcolor="#79bbfd" align="center">
								<b>Contas Enviadas Para Cobrança</b>
							</td>
						</tr>
							<tr bordercolor="#000000">
								<td bgcolor="#90c7fc"><b>Mês/Ano</b></td>
								<td bgcolor="#90c7fc"><b>Vl.Conta</b></td>
								<td bgcolor="#90c7fc"><b>Situação Atual</b></td>
							</tr>
						<% String colorContasEnviadas = "#cbe5fe"; %>
						<logic:iterate id="contaEnviada" name="contasEnviadasCobranca">
						<%if (colorContasEnviadas.equalsIgnoreCase("#cbe5fe")) {
							colorContasEnviadas = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else {
							colorContasEnviadas = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
								<td>
									<bean:write name="contaEnviada" property="mesAno" />
								</td>
								<td>
									<bean:write name="contaEnviada" property="valorConta" />
								</td>
								<td>
									<bean:write name="contaEnviada" property="situacao" />
								</td>
							</tr>
						</logic:iterate>
						<tr bgcolor="#FFFFFF">							
							<td bgcolor="#cbe5fe"><b>Total: </b></td>
							<td><%=session.getAttribute("totalEnviadas") %></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</logic:notEmpty>
			</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
			<td>
			<logic:notEmpty name="contasPagasCobranca">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#79bbfd">
						<td colspan="7" bgcolor="#79bbfd" align="center">
							<b>Contas Pagas Em Cobrança</b>
						</td>
					</tr>
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc"><b>Mês/Ano</b></td>
							<td bgcolor="#90c7fc"><b>Valor</b></td>
							<td bgcolor="#90c7fc"><b>Dt.Pag/Parc</b></td>
							<td bgcolor="#90c7fc"><b>Tp.Doc</b></td>
							<td bgcolor="#90c7fc"><b>Sit.Atual</b></td>
						</tr>
						<% String colorContasPagas = "#cbe5fe"; %>
						<logic:iterate id="contaPaga" name="contasPagasCobranca">
						<%if (colorContasPagas.equalsIgnoreCase("#cbe5fe")) {
							colorContasPagas = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else {
							colorContasPagas = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
								<td>
									<bean:write name="contaPaga" property="mesAno" />
								</td>
								<td>
									<bean:write name="contaPaga" property="valorConta" />
								</td>																
								<td>
									<bean:write name="contaPaga" property="dataPagamento" />
								</td>
								<td>
									<bean:write name="contaPaga" property="documentoTipo" />
								</td>
								<td>
									<bean:write name="contaPaga" property="situacao" />
								</td>
								
							</tr>
						</logic:iterate>
						<tr bgcolor="#FFFFFF">							
							<td bgcolor="#cbe5fe"><b>Total: </b></td>
							<td ><%=session.getAttribute("totalPagas") %></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
				</table>
				</logic:notEmpty>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		</table>
	</body>
</html:html>