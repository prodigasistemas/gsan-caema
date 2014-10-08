<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<html:html>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
	function enviarDadosLocal(codigoRegistro, descricaoRegistro, tipoConsulta){
		if (confirm("Confirma N�o Autoriza��o pelo motivo: "+descricaoRegistro+"?")){
			enviarDados(codigoRegistro, descricaoRegistro, tipoConsulta);
		//window.close();
		}
	}
</script>
</head>
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(680, 430);">
<table width="630" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="630" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Selecione motivo de n�o autoriza��o</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" bgcolor="#90c7fc">
        <tr align="left">
          <td width="8%" align="center"><strong>C&oacute;digo</strong></td>
          <td width="62%" align="center"><strong>Descri��o</strong> </td>
         
        </tr>
        <%--Esquema de pagina��o--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="q" />
					<%int cont = 0;%>
			        <logic:iterate name="motivos" id="motivo">
					<pg:item>
					<%cont = cont + 1;
					if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">								
					<%} else {	%>
						<tr bgcolor="#FFFFFF">
					<%}%>

				           <td align="center"> <bean:write name="motivo" property="id"/> </td>
				           <td align="center">
				         	<a href="javascript:enviarDadosLocal('<bean:write name="motivo" property="id"/>', '<bean:write name="motivo" property="descricao"/>', 'motivo');">
				         	<bean:write name="motivo" property="descricao" /> 
							</a>					            
				           </td>         
			         	</tr>			        
					</pg:item>
	      			</logic:iterate>
					</table>
					 <table width="100%" border="0">
						<tr>
							<td>
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</pg:pager>
						<%-- Fim do esquema de pagina��o --%>
						</tr>
					</table>
			      <table width="100%" border="0">
			         <tr>
			          <td height="24"><input type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();"/></td>
		        	</tr>
			       </table>
      </td>
  </tr>
</table>
</body>
</html:html>
