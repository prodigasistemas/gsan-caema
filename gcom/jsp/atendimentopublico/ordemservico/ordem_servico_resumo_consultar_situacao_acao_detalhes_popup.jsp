<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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

<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}

</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoOrdemServicoSituacaoAcaoDetalhesPopupAction"
	name="InformarDadosGeracaoResumoOrdemServicoConsultaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarDadosGeracaoResumoOrdemServicoConsultaActionForm"
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
					<%
						String tipoDetalhe = (String) session.getAttribute("tipoDetalhe");
						if (tipoDetalhe.equalsIgnoreCase("F") || tipoDetalhe.equalsIgnoreCase("eF")){
					%>
						<td class="parabg">Consultar Ordens de Serviço Fiscalizadas</td>
					<%	
						} else {
					%>
						<td class="parabg">Consultar Motivo de Encerramento</td>
					<%	
						}
					%>
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
					<td><strong>OS Situação da Ação:</strong></td>
					<td>${requestScope.ordemServicoSituacao}</td>
				</tr>
			</table>
			<input type="hidden" name="servicoTipoId" value="${sessionScope.servicoTipoId}"/>
			<input type="hidden" name="idOrdemServicoSituacao" value="${sessionScope.idOrdemServicoSituacao}"/>
			<table bgcolor="#90c7fc" border="0">
				<tr>
						<%
						if (tipoDetalhe.equalsIgnoreCase("F") || tipoDetalhe.equalsIgnoreCase("eF")){
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Motivo de Fiscalização</strong></td>
						<%
						} else if (tipoDetalhe.equalsIgnoreCase("E") || tipoDetalhe.equalsIgnoreCase("eE")){
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Motivo de Encerramento</strong></td>
						<%
						} else {
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Situação da Ordem de Serviço</strong></td>
						<%
						}
						%>
					<td bgcolor="#79bbfd" align="center"><strong> Quantidade</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual</strong></td>
				</tr>								

				<%String cor = "#cbe5fe";%>
				<logic:notEmpty name="colecaoResumoOrdemServicoAcaoSituacaoDetalhes">


					<logic:iterate name="colecaoResumoOrdemServicoAcaoSituacaoDetalhes"
						id="resumoOrdemServicoAcaoSituacaoDetalhe">						
						
						<tr>
							<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";
							} else {
								cor = "#FFFFFF";
							}
							%>
							<td width="40%" bgcolor="<%=cor%>" align="center"><bean:write
								name="resumoOrdemServicoAcaoSituacaoDetalhe" property="descricao" /></td>							
							<td width="15%" align="center" bgcolor="<%=cor%>"><bean:write
								name="resumoOrdemServicoAcaoSituacaoDetalhe"
								property="quantidadeOS" formatKey="number.format" /></td>
							<td width="15%" align="right" bgcolor="<%=cor%>"><bean:write
								name="resumoOrdemServicoAcaoSituacaoDetalhe"
								property="percentualQuantidade" formatKey="money.format" /></td>
							<td width="15%" align="right" bgcolor="<%=cor%>"><bean:write
								name="resumoOrdemServicoAcaoSituacaoDetalhe"
								property="valorContas" formatKey="money.format" /></td>
							<td width="15%" align="right" bgcolor="<%=cor%>"><bean:write
								name="resumoOrdemServicoAcaoSituacaoDetalhe"
								property="percentualValor" formatKey="money.format" /></td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<tr>
					<%if (cor.equalsIgnoreCase("#FFFFFF")) {
						cor = "#cbe5fe";
					} else {
						cor = "#FFFFFF";
					}
					%>
					
					<td bgcolor="<%=cor%>" width="40%" align="center"><strong>TOTAL</strong></td>
					<td bgcolor="<%=cor%>" width="15%" align="center"><strong>${requestScope.quantidadeTotal}</strong></td>
					<td bgcolor="<%=cor%>" width="15%" align="right"><strong>100,00</strong></td>
					<td bgcolor="<%=cor%>" width="15%" align="right"><strong>${requestScope.valorTotalFormatado}</strong></td>
					<td bgcolor="<%=cor%>" width="15%" align="right"><strong>100,00</strong></td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>	
</html:form>
<body>
</html:html>
