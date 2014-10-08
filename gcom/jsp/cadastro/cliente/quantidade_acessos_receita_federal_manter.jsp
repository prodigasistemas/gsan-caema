<%@page import="gcom.cadastro.MensagemRetornoReceitaFederal"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<%@page import="gcom.cadastro.cliente.QuantidadeAcessosReceitaFederal"%>
<%@page import="gcom.cadastro.cliente.bean.FiltrarQuantidadeAcessosReceitaFederalHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@page import="gcom.cadastro.cliente.QuantidadeAcessosReceitaFederal;"%><html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false" formName="ManterQuantidadeAcessosReceitaFederalActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
function facilitador(objeto){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5">


<html:form action="/manterQuantidadeAcessosReceitaFederalAction"
	name="FiltrarQuantidadeAcessosReceitaFederalActionForm"
	type="gcom.gui.cadastro.cliente.FiltrarQuantidadeAcessosReceitaFederalActionForm"
	method="post">

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
				<!--Início Tabela Reference a Páginação da Tela de Processo-->
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Quantidade de Acessos a base da Receita Federal</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<table width="100%" border="0">				
					<tr>
						<td>
							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF">
									<td width="25%" align="center">
										<strong>Mensagem Retorno</strong>
									</td>
									<td width="15%" align="center">
										<strong>Quantidade</strong>
									</td>																																	
								</tr>								
							</table>														
						</td>						
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF">
							<%-- Esquema de paginação --%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecaoQuantidadeAcessosReceitaFederal">
								<%int cont = 0;%>
								<logic:iterate name="colecaoQuantidadeAcessosReceitaFederal" id="quantAcesRecFed" type="QuantidadeAcessosReceitaFederal">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe" />
										<%} else {%>
											<tr bgcolor="#FFFFFF" />
										<%}%>
											<td width="25%" align="center">
											<bean:write name="quantAcesRecFed" property="mensagemRetorno"/>
											</td>
											<td width="15%" align="center">
												<bean:write name="quantAcesRecFed" property="quantidadeAcessos"/>
											</td>																															
									</pg:item>
								</logic:iterate>
							</logic:present>
						</table>
							<%-- Fim do esquema de paginação --%>
									
								<%-- <logic:equal property="mensagemRetorno" value="-1"> --%>
									<table width="100%" bgcolor="#cbe5fe">
									
										<pg:param name="pg"/>
										<pg:param name="q"/>
										<%int cont = 0;%>
											<pg:item>
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe" />
										<%} else {%>
											<tr bgcolor="#FFFFFF" />
										<%}%>
										<tr>
											<td width="25%" align="center">
												<strong>Total</strong>
											</td>
											<td width="15%" align="center">
												<strong>${sessionScope.totalAcessoReceitaFederal}</strong>
											</td>
										</tr>
									</pg:item>
									
								</table>
								<%-- </logic:equal> --%>
								
						<%-- <% } %> --%>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
						</pg:pager>
						</td>				
					</tr>
					<tr>
					
						<td>
						<table width="100%">
							<tr>
								<td align="left" valign="top">																
									<input name="button" type="button" class="bottonRightCol"
										tabindex="2" value="Cancelar" align="left"
										onclick="window.location.href='/gsan/telaPrincipal.do'">
									<input type="button"
										name="buttonFiltro" class="bottonRightCol" value="Voltar Filtro"
										onClick="javascript:window.location.href='/gsan/exibirConsultarQuantidadeAcessosReceitaFederalAction.do?menu=sim'">
								</td>		
							</tr>
						</table>
						</td>
					</tr>									
			</table>
			</td>
		</tr>
		
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>