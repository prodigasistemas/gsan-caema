<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.operacional.bean.ZonaPressaoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirZonaPressaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">


function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function remover(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "removerZonaPressaoAction.do"
			document.forms[0].submit();
		 }
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/removerZonaPressaoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Zona de Pressao</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="11"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Zona(s) de Press&atilde;o encontrada(s):</strong> </font></td>
				</tr>
			</table>
			
			
			
			<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	            <td bgcolor="#000000" height="2"></td>
	        </tr>
			<tr>
				<td>
					<table width="590" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
						<td align="center" width="50"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
						<td align="center" width="50"><FONT COLOR="#000000"><strong>Código</strong></FONT></td>
						<td align="center" width="170"><FONT COLOR="#000000"><strong>Descrição</strong></FONT></td>
						<td align="center" width="160"><FONT COLOR="#000000"><strong>Subsistema de Abastecimento</strong></FONT></td>
						<td align="center" width="160"><FONT COLOR="#000000"><strong>Setor de Abastecimento</strong></FONT></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
            	<td>
					<table width="590" bgcolor="#99CCFF">

					<% String cor = "#cbe5fe";%>
			
			        <%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"
					maxPageItems="10" items="${sessionScope.totalRegistros}">
						<pg:param name="pg" />
						<pg:param name="q" />

					<logic:iterate name="colecaoZonaPressao" id="zonaPressaoHelper" type="ZonaPressaoHelper">
			          <pg:item>

					<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
						cor = "#FFFFFF";%>
						<tr bgcolor="#FFFFFF" height="18">	
					<%} else{	
						cor = "#cbe5fe";%>
						<tr bgcolor="#cbe5fe" height="18">		
					<%}%>

					<logic:equal name="zonaPressaoHelper" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
	
					<td align="center" width="50"><input type="checkbox" name="idRegistrosRemocao" value="${zonaPressaoHelper.idZonaPressao}"></td>
					<td align="center" width="50">${zonaPressaoHelper.idZonaPressao}</td>
					<td align="left" width="170"><html:link page="/exibirAtualizarZonaPressaoAction.do"
													title="${zonaPressaoHelper.descricaoZonaPressao}"
													paramName="zonaPressaoHelper" paramProperty="idZonaPressao"
													paramId="idRegistroAtualizacao">${zonaPressaoHelper.descricaoZonaPressao}
													
													</html:link>
					</td>				
					<td align="left" width="160">${zonaPressaoHelper.subSistema}</td>
					<td align="left" width="160">${zonaPressaoHelper.setor}</td>
	
					</logic:equal>
	
					<logic:notEqual name="zonaPressaoHelper" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
	
					<td align="center" width="50"><input type="checkbox" name="idRegistrosRemocao" value="${zonaPressaoHelper.idZonaPressao}"></td>
					<td align="center" width="50"><span style="color: #CC0000;">${zonaPressaoHelper.idZonaPressao}</span></td>
					<td align="left" width="170"><html:link page="/exibirAtualizarZonaPressaoAction.do"
													title="${zonaPressaoHelper.descricaoZonaPressao}"
													paramName="zonaPressaoHelper" paramProperty="idZonaPressao"
													paramId="idRegistroAtualizacao"><span style="color: #CC0000;">${zonaPressaoHelper.descricaoZonaPressao}</span>
													</html:link>
					</td>				
					<td align="left" width="160"><span style="color: #CC0000;">${zonaPressaoHelper.subSistema}</span></td>
					<td align="left" width="160"><span style="color: #CC0000;">${zonaPressaoHelper.setor}</span></td>
					
					</logic:notEqual>

					</tr>


					</pg:item>
					</logic:iterate>
					</table>

				</td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td>
                	<table width="100%">
                    <tr>
                    	<td>
						<gsan:controleAcessoBotao name="Button" value="Remover"
						onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" url="removerZonaPressaoAction.do"/>
						
						<input name="button" type="button" class="bottonRightCol"
						tabindex="2" value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarZonaPressaoAction.do"/>'">
						</td>
                    	<td align="right" valign="top">
                           	<a href="javascript:toggleBox('demodiv',1);">
                        		<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Zonas de Pressão"/>
							</a>
                     	</td>
                    </tr>
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
			
			</td>
		</tr>
	</table>
	
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioZonaPressaoManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
