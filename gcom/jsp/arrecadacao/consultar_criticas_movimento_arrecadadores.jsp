<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.arrecadacao.ArrecadadorMovimentoCriticas" %>
<%@ page import="java.text.SimpleDateFormat" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">			
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>		
<script type="text/javascript">
	function imprimirCriticas(){
		var form = document.forms[0];				
		toggleBoxCaminho('demodiv',1,'gerarRelatorioConsultaCriticasMovimentoArrecadadoresAction.do');
	}
</script>				
</head>


<body leftmargin="5" topmargin="5">

	<html:form action="/gerarRelatorioConsultaCriticasMovimentoArrecadadoresAction"
			name="ConsultarCriticasMovimentoArrecadadoresActionForm"
			type="gcom.gui.arrecadacao.ConsultarCriticasMovimentoArrecadadoresActionForm"
			method="post">
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
			<td width="602" valign="top" class="centercoltext">
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

					<logic:notPresent name="acao" scope="session">
						<td class="parabg">Consultar Críticas dos Movimentos Arrecadadores</td>
					</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>


			<table width="100%" cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Críticas
					encontradas:</strong> </font></td>
				</tr>
				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#90c7fc">

							<tr>
								<td width="15%" align="center" bgcolor="#90c7fc"><strong>Dt.Proc.</strong></td>
								<td width="50%" align="center" bgcolor="#90c7fc"><strong>Críticas</strong></td>	
								<td width="10%" align="center" bgcolor="#90c7fc"><strong>Arrec.</strong></td>
								<td width="15%" align="center" bgcolor="#90c7fc"><strong>Serviço</strong></td>
								<td width="10%" align="center" bgcolor="#90c7fc"><strong>NSA</strong></td>												
							</tr>

							<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="criticas">
								<% String cor = "#cbe5fe";%>

								<logic:iterate 			name="criticas"
											id="critica"
											type="ArrecadadorMovimentoCriticas"
											scope="session">

								<pg:item>
									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF" height="18">	
									<%} else{	
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe" height="18">		
									<%}%>
											<td width="15%" align="center">
												<%= new SimpleDateFormat("dd/MM/yyyy").format(critica.getDataProcessamento()) %>
											</td>
											<td width="50%" align="left">
												<%=critica.getArrecadadorCritica().getDescricaoCritica().toUpperCase() %>
											</td>
											<td width="10%" align="center">
												<%if (critica.getCodigoBanco() != null){%>
													<%=critica.getCodigoBanco() %>
												<%} else{%>
													&nbsp;
												<%}%>
											</td>
                            				<td width="15%" align="left">
												<%if (critica.getIdentificacaoServico() != null){%>
													<%=critica.getIdentificacaoServico() %>
												<%} else{%>
													&nbsp;
												<%}%>
											</td>
											<td width="10%" align="center">
												<%if (critica.getNsa() != null){%>
													<%=critica.getNsa() %>
												<%} else{%>
													&nbsp;
												<%}%>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
			<table width="100%">
				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol"
							value="Voltar Filtro" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirConsultarCriticasMovimentoArrecadadoresAction.do"/>'">							
					</td>
					<td>								
					  <div align="right">
						   <a href="javascript:imprimirCriticas();">
								<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
									title="Imprimir Críticas Movimento Arrecadador" /> 
							</a>
					  </div>
					</td>	
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioConsultaCriticasMovimentoArrecadadoresAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
