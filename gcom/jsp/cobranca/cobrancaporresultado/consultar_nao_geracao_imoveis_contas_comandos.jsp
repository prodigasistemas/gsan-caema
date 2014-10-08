<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@page import="gcom.cobranca.ImovelContaNaoGeracaoCobrancaResultadoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css" />	
   	<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/jquery-ui-1.8.2.custom.css" />
  	<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css" />
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>	
	<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.js"></script>
	
	
	<script type="text/javascript">
		function voltar() {
			var form = document.forms[0];			
			window.location.href='/gsan/exibirConsultarNaoGeracaoImoveisContasComandosAction.do?selecionarComandos=sim';			
		}
	</script>
		
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/consultarNaoGeracaoImoveisContasComandosAction"
	name="ConsultarNaoGeracaoImoveisContasComandosForm"
	type="gcom.gui.cobranca.cobrancaporresultado.ConsultarNaoGeracaoImoveisContasComandosForm"
	method="post">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
	
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Imóveis/Contas Não Geradas na Cobrança Por Resultado</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>				
				<table width="100%" border="0">				
					<tr>
						<td><strong style="font-size: 14px;">Comando: </strong><input type="text" size="5" disabled="disabled" value="<%=request.getAttribute("comando") %>" /></td>						
					</tr>
					<tr><td>&nbsp;</td></tr>					
				</table>
				<hr size="3" width="100%" color="#000000" NOSHADE style="margin-top: 5px;"/>
					<table width="100%" bgcolor="#99CCFF" style="margin-top: -5px;">
						<tr>
							<td style="font-size: 14px;"><br /><strong>Motivo da Não Geração Por Imóvel</strong><br /></td>						
						</tr>
					</table>
					<logic:present name="imoveis">
						<div style="width: 100%; height: 300; overflow: auto;">
						<table width="100%" bgcolor="#99CCFF">		
						<c:set var="motivoAnt"/>
						<%int cont = 1;%>
							<logic:iterate id="ob" name="imoveis" type="ImovelContaNaoGeracaoCobrancaResultadoHelper" scope="session">																  		
								<c:if test="${motivoAnt != ob.descricaoMotivo }">
									<c:set var="motivoAnt" value="${ob.descricaoMotivo }" />
									<%
										if (cont % 2 == 0) {
										cont++;
									%>
											<tr bgcolor="#cbe5fe">
									<%
										} 
										else {
										cont++;
									%>
											<tr bgcolor="#FFFFFF">
									<%
										}
									%>
								
										<td width="30%"><strong><c:out value="${motivoAnt}"/></strong></td>
									</tr>
								</c:if>
								<% if (cont % 2 == 0) {
									cont++;
								%>
											<tr bgcolor="#cbe5fe">
								<%
									} 
									else {
										cont++;
									%>
											<tr bgcolor="#FFFFFF">
									<%
										}
									%>
									<td>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a style="cursor: pointer;" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=false;return escape( '<c:out value="${ob.hint}"/>' ); "><c:out value="${ob.idImovel}"/> </a>
										</td>
									</tr>
							</logic:iterate>
						</table>
						</div>
					</logic:present>					
					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td>&nbsp;</td>						
						</tr>
					</table>
					<br />
					<br />
					<hr size="3" width="100%" color="#000000" NOSHADE style="margin-top: 5px;"/>
					<table width="100%" bgcolor="#99CCFF" style="margin-top: -5px;">
						<tr>
							<td style="font-size: 14px;"><br /><strong>Motivo da Não Geração Por Contas</strong><br /></td>						
						</tr>
					</table>
					<logic:present name="contas">
						<div style="width: 100%; height: 300; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
								<c:set var="motivoAntC"/>
								<c:set var="idImovel" value="0"/>
								<c:set var="idImovel2" value="0"/>
								<%int cont = 1;%>
								<logic:iterate id="ob" name="contas" type="ImovelContaNaoGeracaoCobrancaResultadoHelper" scope="session">
									<c:if test="${idImovel != ob.idImovel }">
										<c:set var="idImovel" value="${ob.idImovel }"/>
										<%
											if (cont % 2 == 0) {
											cont++;
										%>
												<tr bgcolor="#cbe5fe">
										<%
											} 
											else {
											cont++;
										%>
												<tr bgcolor="#FFFFFF">
										<%
											}
										%>
											<td>	
												<strong>Matrícula: </strong>
												<a style="cursor: pointer;" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=false;return escape( '<c:out value="${ob.hint}"/>' ); "><c:out value="${ob.idImovel}"/></a>
											</td>
										</tr>
									</c:if>
									<c:if test="${motivoAntC != ob.descricaoMotivo }">
										<c:set var="motivoAntC" value="${ob.descricaoMotivo }" />
										<c:set var="idImovel2" value="${idImovel }" />										
									<%
										if (cont % 2 == 0) {
											cont++;
									%>
											<tr bgcolor="#cbe5fe">
									<%
										} 
										else {
											cont++;
									%>
											<tr bgcolor="#FFFFFF">
									<%
										}
									%>	
									<td width="40%">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;											
										<strong><c:out value="${motivoAntC }"/></strong>
										</td>
									</tr>		
									</c:if>
									<c:if test="${idImovel != idImovel2 }">
										<c:set var="motivoAntC" value="${ob.descricaoMotivo }" />
										<c:set var="idImovel2" value="${idImovel }" />										
									<%
										if (cont % 2 == 0) {
											cont++;
									%>
											<tr bgcolor="#cbe5fe">
									<%
										} 
										else {
											cont++;
									%>
											<tr bgcolor="#FFFFFF">
									<%
										}
									%>	
									<td width="40%">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;											
										<strong><c:out value="${motivoAntC }"/></strong>
										</td>
									</tr>		
									</c:if>
									<%									
										if (cont % 2 == 0) {
										cont++;
									%>
											<tr bgcolor="#cbe5fe">
									<%
									} 
									else {
										cont++;
									%>
										<tr bgcolor="#FFFFFF">
									<%
									}
									%>		
											<td>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;											
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<strong>Mês/Ano: </strong><c:out value="${ob.mesAnoConta }"/>
												<strong> - Valor R$:</strong><c:out value="${ob.valorConta }" /> 
											</td>
										</tr>
								</logic:iterate>
								<tr><td>&nbsp;</td></tr>
							</table>
						</div>		
					</logic:present>					
					
					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td>&nbsp;</td>						
						</tr>
					</table>	
					<table width="100%" border="0">
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="bottonRightCol"
									value="Voltar"
									onclick="javascript:voltar();"/>							
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
						</tr>
					</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>