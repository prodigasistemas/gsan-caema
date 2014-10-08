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
<%@ page import="gcom.gerencial.bean.ResumoAnoMesRetornoFiscalizacaoHelper,gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper"%>

<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
	window.close();
}

function gerarRelatorio(){
	var form = document.forms[0];
	form.submit();
}

function extendeTabela(tabela,display){
	var form = document.forms[0];

	if(display){
			eval('layerHide'+tabela).style.display = 'none';
			eval('layerShow'+tabela).style.display = 'block';
	}else{
		eval('layerHide'+tabela).style.display = 'block';
			eval('layerShow'+tabela).style.display = 'none';
	}
}

</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirGerarRelatorioRetornoOSFiscalizacaoAction.do"
	name="ResumoAcaoCobrancaActionForm"
	type="gcom.gui.gerencial.cobranca.ResumoAcaoCobrancaActionForm"
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
					<td class="parabg">Resumo Mensal das Ordens de Serviços Fiscalizadas</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><strong>Ação de Cobrança:</strong></td>
					<td>${requestScope.cobrancaAcao}</td>
				</tr>
				<tr>
					<td><strong>Situação da Ação:</strong></td>
					<td>${requestScope.cobrancaAcaoSituacao}</td>
				</tr>
			</table>
			 <table width="100%">
				<logic:iterate name="colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes"
						id="resumoAnoMesRetornoFiscalizacaoHelper">
				<tr>
				<td>
				  <div id='layerHide<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="anoMes"/>' style="display:block">
      					<table width="100%" border="0" bgcolor="#79bbfd">
						<tr bgcolor="#79bbfd" align="center">
                				<td>             
                					<a href="javascript:extendeTabela('<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="anoMes"/>',true);">
                						<strong>
                						<%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getAnoMesFormatado()%>
                						</strong> - Quantidade: 
                						<strong>
                						  <bean:write name="resumoAnoMesRetornoFiscalizacaoHelper"property="quantidadeDocumento" />
                						</strong> (
                						<%=((ResumoAnoMesRetornoFiscalizacaoHelper) 
                								resumoAnoMesRetornoFiscalizacaoHelper).getPercentualQuantidade(""	+ request.getAttribute("quantidadeTotal"))%>
                						%) Valor: 
                						<strong>
                							<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="valorDocumento" formatKey="money.format"/>
                						</strong>
							            (<%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getPercentualValor(""	+ request.getAttribute("valorTotal"))%>
							            %)
                					</a>
                				</td>
               			</tr>
              			</table>
      			 </div>
      			 <div id='layerShow<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="anoMes"/>' style="display:none">
					    <table width="100%" bgcolor="#79bbfd">						
						  <tr bgcolor="#79bbfd" align="center">
						    <td bgcolor="#79bbfd">
						    <a href="javascript:extendeTabela('<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="anoMes"/>',false);">	
         						<strong>
         						<%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getAnoMesFormatado()%>
         						</strong> - Quantidade: 
         						<strong>
         						  <bean:write name="resumoAnoMesRetornoFiscalizacaoHelper"property="quantidadeDocumento" />
         						</strong> (
         						<%=((ResumoAnoMesRetornoFiscalizacaoHelper) 
         								resumoAnoMesRetornoFiscalizacaoHelper).getPercentualQuantidade(""	+ request.getAttribute("quantidadeTotal"))%>
         						 %) Valor: 
         						<strong>
         							<bean:write name="resumoAnoMesRetornoFiscalizacaoHelper" property="valorDocumento" formatKey="money.format"/>
         						</strong>
				                (<%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getPercentualValor(""	+ request.getAttribute("valorTotal"))%>
				                 %)
							</a>
							</td>
						  </tr>
					<tr>
					  <td>
					    <table width="100%" bgcolor="#90c7fc">
						  <tr>
							<td bgcolor="#79bbfd" align="center"><strong> Situação Encontrada</strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Qtd</strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Perc. (%)  </strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Perc. do Total (%)  </strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Perc. (%)</strong></td>
							<td bgcolor="#79bbfd" align="center"><strong> Perc. do Total (%)</strong></td>
						</tr>
						  <%String cor = "#FFFFFF";%>	
						  <logic:iterate name="resumoAnoMesRetornoFiscalizacaoHelper"
							property="colecaoResumoCobrancaAcaoSituacaoAcaoDetalhesHelper" id="resumoCobrancaAcaoSituacaoAcaoDetalhe"> 
							 															
							<tr>
								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
									cor = "#cbe5fe";
								} else {
									cor = "#FFFFFF";
								}
								%>

									<td width="38%" bgcolor="<%=cor%>" align="center"><bean:write
										name="resumoCobrancaAcaoSituacaoAcaoDetalhe" property="descricao" /></td>							
									<td width="10%" align="center" bgcolor="<%=cor%>">
									 <bean:write name="resumoCobrancaAcaoSituacaoAcaoDetalhe" property="quantidadeDocumento" formatKey="number.format" /></td>
									<td width="10%" align="right" bgcolor="<%=cor%>">
										<%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper) 
											resumoCobrancaAcaoSituacaoAcaoDetalhe)
											.getPercentualQuantidade(""+((ResumoAnoMesRetornoFiscalizacaoHelper)resumoAnoMesRetornoFiscalizacaoHelper).getQuantidadeDocumento())%>
									</td>
									<td width="10%" align="right" bgcolor="<%=cor%>">
										<%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper) 
											resumoCobrancaAcaoSituacaoAcaoDetalhe)
											.getPercentualQuantidade(""	+ request.getAttribute("quantidadeTotal"))%>
									</td>
									<td width="12%" align="right" bgcolor="<%=cor%>"><bean:write
										name="resumoCobrancaAcaoSituacaoAcaoDetalhe"
										property="valorDocumento" formatKey="money.format" /></td>
									<td width="10%" align="right" bgcolor="<%=cor%>"><%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper)
											resumoCobrancaAcaoSituacaoAcaoDetalhe)
											.getPercentualValor(""+((ResumoAnoMesRetornoFiscalizacaoHelper)resumoAnoMesRetornoFiscalizacaoHelper).getValorDocumento())%></td>
									<td width="10%" align="right" bgcolor="<%=cor%>"><%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper)
											resumoCobrancaAcaoSituacaoAcaoDetalhe)
											.getPercentualValor(""	+ request.getAttribute("valorTotal"))%></td>											
								</tr>	
							</logic:iterate>
						<tr>
							<td bgcolor="#cbe5fe" width="38%" align="center"><strong>TOTAL</strong></td>
							<td bgcolor="#cbe5fe" width="10%" align="center"><strong><bean:write name="resumoAnoMesRetornoFiscalizacaoHelper"
							property="quantidadeDocumento" /></strong></td>
							<td bgcolor="#cbe5fe" width="10%" align="right"><strong>100,00</strong></td>
							<td bgcolor="#cbe5fe" width="10%" align="right"><strong><%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getPercentualQuantidade(""	+ request.getAttribute("quantidadeTotal"))%>
				                </strong></td>
							<td bgcolor="#cbe5fe" width="12%" align="right"><strong><bean:write name="resumoAnoMesRetornoFiscalizacaoHelper"
							property="valorDocumento" formatKey="money.format"/></strong></td>
							<td bgcolor="#cbe5fe" width="10%" align="right"><strong>100,00</strong></td>
							<td bgcolor="#cbe5fe" width="10%" align="right"><strong><%=((ResumoAnoMesRetornoFiscalizacaoHelper) resumoAnoMesRetornoFiscalizacaoHelper).getPercentualValor(""	+ request.getAttribute("valorTotal"))%>
				                  </strong></td>
						</tr>	
						</table>																	
				  </table>
				 </div>
				</td>
				</tr>
				</logic:iterate>
				</table>			
			<table border="0" width="100%">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="left"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();">
					</td>
																	
					<td colspan="3" align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Gerar Relatório Analítico" onclick="javascript:gerarRelatorio()">
					</td>
				</tr>												
			</table>
			</td>
		</tr>

	</table>
</html:form>
<body>
</html:html>
