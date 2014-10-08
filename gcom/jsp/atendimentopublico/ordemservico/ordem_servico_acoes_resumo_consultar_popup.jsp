<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.cadastro.imovel.bean.ImovelPerfilHelper" %>
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoOrdemServicoPopupAction.do"
	name="ResumoAcaoOSActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ResumoAcaoOSActionForm"
	method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Resumo das Ações das Ordens de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td><strong>Tipo de Serviço:</strong></td>
					<td>${requestScope.servicoTipo}</td>
				</tr>
				<tr>
					<td><strong>Situação da Ordem de Serviço:</strong></td>
					<td>${requestScope.ordemServicoSituacao}</td>
				</tr>
				<logic:notEmpty name="idCobrancaDebito">
					<tr>
						<td><strong>Situação do Débito:</strong></td>
						<td>${requestScope.cobrancaDebito}</td>
					</tr>
				</logic:notEmpty>
			</table>

			<table bgcolor="#90c7fc" border="0">
				<tr>
					<td bgcolor="#79bbfd" align="center"><strong> Perfil do Imóvel</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Quantidade OS</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor Contas</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual</strong></td>
				</tr>
				
				<%String cor = "#cbe5fe";%>
				<logic:notEmpty name="colecaoImovelPerfilHelper">
					<logic:iterate name="colecaoImovelPerfilHelper" id="resumoImovelPerfil">
						<tr>
							<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<%} else {
								cor = "#FFFFFF";%>
							<%}%>
							
							<td bgcolor="<%=cor%>" width="17%" align="center"><bean:write
								name="resumoImovelPerfil" property="descricao" /></td>
							<td bgcolor="<%=cor%>" width="17%" align="center"><bean:write
								name="resumoImovelPerfil" property="quantidade"
								formatKey="number.format" /></td>
							<td bgcolor="<%=cor%>" width="17%" align="right"><bean:write
								name="resumoImovelPerfil"
								property="percentualQuantidade" formatKey="money.format" /></td>
							<td bgcolor="<%=cor%>" width="17%" align="right"><bean:write
								name="resumoImovelPerfil" property="valor"
								formatKey="money.format" /></td>
							<td bgcolor="<%=cor%>" width="15%" align="right"><bean:write
								name="resumoImovelPerfil"
								property="percentualValor" formatKey="money.format" /></td>

						</tr>
					
					</logic:iterate>
				</logic:notEmpty>
				<tr><%if (cor.equalsIgnoreCase("#FFFFFF")) {
						cor = "#cbe5fe";%>
					<%} else {
						cor = "#FFFFFF";%>
					<%}%>
				
					<td bgcolor="<%=cor%>" width="34%" align="center" ><strong>TOTAL</strong></td>
					<td bgcolor="<%=cor%>" width="17%" align="center"><strong>${requestScope.quantidadeTotal}</strong></td>
					<td bgcolor="<%=cor%>" width="17%" align="right"><strong>100,00</strong></td>
					<td bgcolor="<%=cor%>" width="17%" align="right"><strong>${requestScope.valorTotalFormatado}</strong></td>
					<td bgcolor="<%=cor%>" width="15%" align="right"><strong>100,00</strong></td>

				</tr>
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
