<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.math.BigDecimal,gcom.util.Util, gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>
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
-->		
}

function verificarExibicaoRelatorio() {
	
	toggleBox('demodiv',1);
	
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarDadosDiariosUnidadeNegocioAction.do"
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
	method="post">
	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="770" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Dados Di�rios da Arrecada��o - Unidade Neg�cio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10">
					<table width="100%" border="0">
						<%
						BigDecimal valorTotal = (BigDecimal) session.getAttribute("valorTotalGerencia");
						String referencia = (String)session.getAttribute("referencia");
						String idGerencia = (String)session.getAttribute("idGerencia");
						String nomeGerencia = (String)session.getAttribute("nomeGerencia");
						String dadosMesInformado = (String)session.getAttribute("dadosMesInformado");
						String dadosAtual = (String)session.getAttribute("dadosAtual");
						String faturamentoCobradoEmConta = (String)session.getAttribute("faturamentoCobradoEmConta");
						String arrecadador = (String)session.getAttribute("arrecadador");
						%>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>Processamento Definitivo: 
											<%= dadosMesInformado %>
											</strong>
									    </td>
										<td>
											<div align="right"><strong>M&ecirc;s/Ano:</strong><strong>
											<%= Util.formatarAnoMesParaMesAno(referencia) %></strong></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>�ltimo Processamento Atual: 
											<%= dadosAtual %>
											</strong>
									    </td>

									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>Arrecadador:
											<logic:empty name="arrecadador">TODOS</logic:empty>
											<logic:notEmpty name="arrecadador"><%=arrecadador%></logic:notEmpty>
											</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
	    	    		<tr bgcolor="#90c7fc">
	        	          	<td colspan=4>
								<table width="100%">
									<tr>
										<td>
				        	          		<strong>Faturamento Cobrado em Conta: 
			       	          	 					<%=faturamentoCobradoEmConta%>
			       	          				 </strong>
		       	          				 </td>
	       	          				 </tr>
       	          				 </table>
							</td>
						</tr>
						<tr>
			                <td width="15%"><strong>Ger&ecirc;ncia:</strong></td>
			                <td width="35%" align="left">
			                	 <strong> <%= nomeGerencia %></strong>
			                </td>
			                <td width="30%" align="right">
								<strong>Valor:</strong>
							</td>
							<td align="right" width="20%">
								<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%= referencia%>&idGerencia=<%= idGerencia%>', 475, 800);">
								 <strong><%= Util.formatarMoedaReal(valorTotal) %></strong>
								</a>
							</td>
						</tr>
					</table>
					<table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
						<!--header da tabela interna -->
	        	        <tr bordercolor="#FFFFFF" bgcolor="#90c7fc""> 
		                	<td  width="25%" nowrap="nowrap"> 
		                	<div class="style9"><font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif">
		                		<strong>Unidade de Neg�cio</strong></font></div></td>
		                  	<td width="14%" nowrap="nowrap"> <div align="center" class="style9">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>D�bitos</strong></font></div></td>
		                  	<td width="11%" nowrap="nowrap"> <div align="center" class="style9">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Descontos</strong></font></div></td>		                  	
		                  	<td width="14%" nowrap="nowrap"> <div align="center" class="style9">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Arrecadado</strong></font></div></td>
		                  	<td width="11%" nowrap="nowrap"> <div align="center" class="style9">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Devolu��o</strong></font></div></td>
		                  	<td width="14%" nowrap="nowrap"> <div align="center" class="style9">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Arrecada��o L�quida</strong></font></div></td>
		                  	<td width="8%" nowrap="nowrap"><div align="center">
		                  		<font color="#000000" style="font-size:10px"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>% M&ecirc;s</strong></font></div></td>
            		    </tr>

						<%String cor = "#cbe5fe"; %>
						<logic:present name="colecaoDadosDiariosUnidade" scope="session">
						<logic:iterate name="colecaoDadosDiariosUnidade" id="itemHelper" type="FiltrarDadosDiariosArrecadacaoHelper">
						<% 
						
						BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
						
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) itemHelper.getItemAgrupado();
						
						boolean ehTODOS = unidadeNegocio.getId().intValue() == -1;
						String negrito = ehTODOS ? "font-weight: bold;" : "";
						
						if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
								<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
								<%}%>

							  <td height="17">
			                  	<font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
			                  	 <a href="javascript:abrirPopup('exibirConsultarDadosDiariosEloAction.do?referencia=<%=referencia%>&idUnidadeNegocio=<%=unidadeNegocio.getId()%>&idGerencia=<%=idGerencia%>&valorTotalGerencia=<%=valorTotal%>&valorTotalUNEG=<%=itemHelper.getValorArrecadacaoLiquida()%>', 475, 800);">
							      	<%=unidadeNegocio.getNome()%></a>
			                  	</font></td>
			                  <td><div align="right">
			                  	<font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
			                  	<%=Util.formatarMoedaReal(itemHelper.getValorDebitos())%></font></div></td>
			                  <td><div align="right">
			                  	<font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
			                  	<%=Util.formatarMoedaReal(itemHelper.getValorDescontos())%></font></div></td>
			                  <td><div align="right">
				                  <font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
				                  <%=Util.formatarMoedaReal(valorArrecadado)%></font></div></td>
			                  <td><div align="right">
				                  <font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
				                  <%=Util.formatarMoedaReal(itemHelper.getValorDevolucoes())%></font></div></td>
			                  <td><div align="right">
				                  <font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
				                  <a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%=referencia%>&idGerencia=<%=idGerencia%>&idUnidadeNegocio=<%=unidadeNegocio.getId()%>', 475, 800);"><%=Util.formatarMoedaReal(itemHelper.getValorArrecadacaoLiquida())%></a></font></div></td>
			                  <td nowrap="nowrap"><div align="right">
				                  <font color="#000000" style="font-size:10px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
				                  <%= Util.formatarMoedaReal(itemHelper.getPercentual())%></font></div></td>
						</tr>
					</logic:iterate>
					</logic:present>
					</table>
		            <table width="100%" border="0">
						<tr>
							<td align="right">
								  <div align="right">
								   <a href="javascript:verificarExibicaoRelatorio();">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir" /> </a>
								  </div>
							</td>
						</tr>
					</table>
					<p></p>

					<p>&nbsp;</p>
					</td>
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
				<p>&nbsp;</p>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosDiariosUnidadeNegocioAction.do" />
</html:form>
<body>
</html:html>
