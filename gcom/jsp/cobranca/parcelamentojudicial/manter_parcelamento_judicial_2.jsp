<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
	<title>COMPESA - GSAN</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
	<script type="text/javascript">

	function novoFiltro(){
		var form = document.forms[0];

		form.action='exibirManterParcelamentoJudicialAction.do?menu=sim';
		form.submit();
	}

	function consultarParcelamentoJudicial(id){
		var form = document.forms[0];

		form.action='manterParcelamentoJudicialConsultarParcelamentoJudicialAction.do?id='+id;
		form.submit();
	}
	

	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5">
		
		<div id="formDiv">
		<html:form action="/manterParcelamentoJudicialConsultarParcelamentoJudicialAction"
		 name="ManterParcelamentoJudicialActionForm"
		 type="gcom.cobranca.parcelamentojudicial.ManterParcelamentoJudicialActionForm"
		 method="post">
			
		<input type="hidden" name="tipoPesquisa" />
		
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
							<td class="parabg">Manter Parcelamento Judicial </td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					
					<table width="100%" bgcolor="#90c7fc">
					
						<tr width="7%">
						
							<td bgcolor="#90c7fc" align="center"><strong>Cliente Respons&aacute;vel</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Data Parcelamento</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>N° de Parcelas</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Situação</strong></td>
						
						</tr>
						<tr>
						<pg:pager isOffset="true" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
									  index="half-full" maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="q" />
								<pg:param name="pg" />
								<%--Esquema de paginação--%>
						
						<% String cor = "#cbe5fe";%>
						
						<logic:iterate name="colecaoObterListaPrcelamentoJudicial" id="obter">
						<pg:item>
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
						<%}%>
							<td align="center"><a href="javascript:consultarParcelamentoJudicial(<bean:write name="obter" property="id"/>);"><bean:write name="obter" property="nomeClienteResponsavel"/></a></td>
							<td align="center"><bean:write name="obter" property="dataParcelamento"/></td>
							<td align="center"><bean:write name="obter" property="numeroParcelas"/></td>
							<td align="center"><bean:write name="obter" property="situacao"/></td>
						</pg:item>
						</logic:iterate>
						</tr>
						</table>
					<%-- Fim do esquema de paginação --%>
						<table align="center">
						<tr align="center">
								<td align="center">
									<div align="center">
										<strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%>
										</strong>
									</div>
								</td>
							</tr>
						</table>
						</pg:pager>
					<table width="100%">
					
					<tr height="10%">
					
						<td>&nbsp;</td>
					
					</tr>
					
					<tr>
					
						<td align="right"><input type="button" class="bottonRightCol"
						 value="Novo Filtro" onclick="javascript:novoFiltro();"></td>
					
					</tr>
					
					</table>
								
			</td>
			
			</tr>
			
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%> 
		 </html:form>
		</div>
	</body>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:html>