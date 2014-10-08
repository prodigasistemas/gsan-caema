<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<!--================================= SCRIPTS =============================================================-->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<!--=======================================================================================================-->
		
		<script language="JavaScript">

			var selecionados = false;
						
			$(document).ready(function(){
				
				

			});

			function selecionarTodos(){
				if(selecionados){
					$('[@name=itensSelecionados]').attr('checked', false);
					selecionados = false;
				}
				else{
					$('[@name=itensSelecionados]').attr('checked', true);
					selecionados = true;
				}
			}

			function excluirSetorAbastecimento(){
				if($('[@name=itensSelecionados]:checked').length == 0){
					alert('Selecione ao menos um setor de abastecimento');
				}
				else{
					if (confirm ("Confirma remoção?")) {
						document.forms[0].action = "/gsan/exibirManterSetorAbastecimentoAction.do?action=excluirSetoresAbastecimento"
							document.forms[0].submit();
					}
				}
			}
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/filtrarSetorAbastecimentoAction"
				   name="FiltrarSetorAbastecimentoActionForm"
				   type="gcom.gui.operacional.FiltrarSetorAbastecimentoActionForm"
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
					<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Manter Setor de Abastecimento</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>
										Setor(es) de abastecimento cadastrado(s):
									</strong>	
								</td>
								<logic:present scope="application" name="urlHelp">
										<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}setorAbastecimentoManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
								</logic:present>
								<logic:notPresent scope="application" name="urlHelp">
										<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
								</logic:notPresent>
							</tr>
							 <tr>
					            <td bgcolor="#000000" height="2" colspan="2"></td>
					        </tr>
							<!--===================== CORPO DA VISÃO =========================-->
							<tr>
								<td colspan="2">
									<table width="100%" bgcolor="#90c7fc">
										<tr>
											<td bgcolor="#90c7fc" align="center"><strong><a href="#" onclick="javascript:selecionarTodos()">Todos</a></strong></td>
											<td bgcolor="#90c7fc" align="center"><strong>Código</strong></td>
											<td bgcolor="#90c7fc" align="center"><strong>Descrição</strong></td>
											<td bgcolor="#90c7fc" align="center"><strong>Descrição Abreviada</strong></td>
											<td bgcolor="#90c7fc" align="center"><strong>Subsistema de Abastecimento</strong></td>
										</tr>
										<tr>
										
											<pg:pager isOffset="true" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
											  		  index="half-full" maxPageItems="10" items="${sessionScope.totalRegistros}">
												<pg:param name="q" />
												<pg:param name="pg" />
												<c:set var="cor" value="0"/>							
												<logic:iterate name="filtrarSetorAbastecimento" id="obter">
													<pg:item>
														<c:choose>
															<c:when test="${count % 2 == 0 }">
																<tr bgcolor="#ffffff">		
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${obter[6] == 1 }">
																	<c:set var="corFonte" value="#000000"/>	
															</c:when>
															<c:otherwise>
																	<c:set var="corFonte" value="#ff0000"/>
															</c:otherwise>
														</c:choose>
														<td align="center"><html:checkbox property="itensSelecionados" value="${obter[0]}"/> </td>							
														<td align="center"><font color="${corFonte}"><c:out value="${obter[0]}"/></font></td>
														<td align="left"><a href="/gsan/exibirAtualizarSetorAbastecimentoAction.do?idSetorAbastecimento=${obter[0]}"><font color="${corFonte}"><c:out value="${obter[1]}"/></font></a></td>
														<td align="center"><font color="${corFonte}"><c:out value="${obter[2]}"/></font></td>
														<td align="center"><font color="${corFonte}"><c:out value="${obter[3]}"/></font></td>
													</pg:item>
													<c:set var="count" value="${count+1}"/>
												</logic:iterate>
												<table align="center">
													<tr align="center">
														<td align="center">
															<div align="center">
																<strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%></strong>
															</div>
														</td>
													</tr>
												</table>
											</pg:pager>
										</tr>
										<tr>
											<td>
												<input type="button" name="Button"
													class="bottonRightCol" value="Remover" tabindex="33"
													onClick="javascript:excluirSetorAbastecimento()"
													style="width: 80px" />&nbsp; 
												<input type="button" name="Button"
													class="bottonRightCol" value="Voltar Filtro" tabindex="32"
													onClick="javascript:window.location.href='/gsan/exibirFiltrarSetorAbastecimentoAction.do'"
													style="width: 80px" />
											 </td>	
											 <td align="right" valign="top">
		                                    	<a href="javascript:toggleBox('demodiv',1);">
		                                        	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Setor de Abastecimento"/>
		                                        </a>
		                                    </td>							
										</tr>
									</table>
								</td>
							</tr>		
						</table>
					</td>
				</tr>
			</table>
			<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioSetorAbastecimentoManterAction.do"/>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
		</div>
	</body>
</html:html>
