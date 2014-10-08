<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.DocumentoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>	
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<html:javascript staticJavascript="false" formName="PesquisarDocumentosPagosActionForm" />
		
		<script language="JavaScript">
		
			var marcar = true;
			
			function validarForm(){
				var form = document.forms[0];
				if(!$("input[@name='idsSelecionados']").is(":checked")){
					alert('Selecione pelo menos um documento.');
				}
				else{
					form.submit();
				}
			}
			
			function marcarDesmarcar(){
				if(marcar){
					$("input[@name='idsSelecionados']").attr('checked', true);
					marcar = false;
				}
				else{
					$("input[@name='idsSelecionados']").attr('checked', false);
					marcar = true;
				}
			}
			
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(690, 400);">
		
		
		<logic:present name="identificadorPesquisa" scope="request">
			<body leftmargin="5" topmargin="5">
		</logic:present>
		
		<logic:notPresent name="identificadorPesquisa" scope="request">
		<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');">
		</logic:notPresent>
		
		<html:form action="/pesquisarDocumentosPagosAction.do?selecionou=sim"
				   name="PesquisarDocumentosPagosActionForm"
				   type="gcom.gui.atendimentopublico.registroatendimento.PesquisarDocumentosPagosActionForm"
				   method="post">
				   
			<table width="625" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="635" valign="top" class="centercoltext">
						<table>
							<tr>
								<td></td>
							</tr>
						</table>
						<!-- ======================================== CABEÇALHO ======================================== -->
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Pesquisar Documentos Pagos</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>	
							</tr>
						</table>			
						<!-- =========================================================================================== -->
						
						
						<!-- ======================================== DADOS DO IMÓVEL ======================================== -->
						<table width="100%" border="0">
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="2">
									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr>
											<td height="18" colspan="2">
												<div align="center"><span class="style2"><strong>Dados do Imóvel</strong></span></div>
											</td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">
													<tr>
														<td width="37%" height="10"><strong>Matrícula do Imóvel:</strong></td>
														<td width="58%">
															<html:text property="matriculaImovel"
																	   readonly="true"
																	   style="background-color:#EFEFEF; border:0; color: #000000"
																	   size="15" maxlength="20" />
														</td>
													</tr>
													<tr>
														<td><strong> Inscrição:</strong></td>
														<td>
															<html:text property="inscricaoImovel" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="40" maxlength="40" />
														</td>	
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- =========================================================================================== -->
						
						<table>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
						
						<!-- ======================================== CAMPOS DA PESQUISA ======================================== -->
						<table width="99%" align="center" bgcolor="#90c7fc" border="0">
							<logic:notEmpty name="colecaoDocumentosPagos" scope="session">
								<tr>
									<c:choose>
										<c:when test="${!(fn:length(sessionScope.colecaoDocumentosPagos) gt 5)}">	
											<td colspan="6">
										</c:when>
										<c:otherwise>
											<td height="120" colspan="6">
										</c:otherwise>	
									</c:choose>
											
									<table>
										<tr bordercolor="#000000">
											<td width="10%" bgcolor="#90c7fc" align="center">
												<strong><a onclick="marcarDesmarcar()" href="#">Todos</a></strong>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
												<strong>Mês/Ano</strong>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
												<strong>Data Emissão</strong>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
												<strong>Data Vencimento</strong>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
												<strong>Valor Documento</strong>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
												<strong>Data Pagamento</strong>
											</td>			
										</tr>
									</table>
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%">			
											<logic:present name="colecaoDocumentosPagos">
												<logic:iterate name="colecaoDocumentosPagos" id="documentosPagosHelper" indexId="ind">
													<c:set var="count" value="${count+1}"/>
						                       		<c:choose>
						                       			<c:when test="${count%2 == '1'}">
						                       				<tr bgcolor="#FFFFFF">
						                       			</c:when>
						                       			<c:otherwise>
						                       				<tr bgcolor="#cbe5fe">
						                       			</c:otherwise>
						                       		</c:choose>
													
														<td width="10%">
															<div align="center" class="style9">
																<input type="checkbox" name="idsSelecionados" value="${documentosPagosHelper.idPagamento}" />
															</div>
														</td>
														
														
														<td width="17%">
															<div align="center" class="style9">
																<bean:write name="documentosPagosHelper" property="amReferenciaDocumentoFormatado" />
															</div>
														</td>
														
														
														<td width="17%">
															<div align="center" class="style9">
																<bean:write name="documentosPagosHelper" property="dataEmissaoDocumentoFormatado" />
															</div>
														</td>
														
														
														<td width="17%">
															<div align="center" class="style9">
																<bean:write name="documentosPagosHelper" property="dataVencimentoDocumentoFormatado" />																							
															</div>
														</td>		
														
																					
														<td width="17%">
															<div align="center" class="style9">
																<bean:write name="documentosPagosHelper" property="valorPagamentoFormatado" />
															</div>
														</td>
														<td width="17%">
															<div align="center" class="style9">
																<bean:write name="documentosPagosHelper" property="dataPagamentoDocumentoFormatado" />
															</div>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>		 							
										</table>
									</div>
									</td>						
								</tr>											
							</logic:notEmpty>
						<!-- ==================================================================================================== -->
					</td>
				</tr>
			</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="4">
								&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="2" >
									<input name="Button" type="button" class="bottonRightCol"
										   value="Voltar Pesquisa" align="left"
										   onclick="window.location.href='<html:rewrite page="/exibirPesquisarDocumentosPagosAction.do?idImovel=${PesquisarDocumentosPagosActionForm.matriculaImovel}"/>'">
								</td>
								<td colspan="2" align="right">
									<input type="button"
										   name="pesquisarButton" class="bottonRightCol"
										   value="Selecionar Documentos"
										   id="selecionarButton"
										   onclick="validarForm()"/>
								</td>
							</tr>	
						</table>
					
					</td>
				</tr>
			</table>	

		</html:form>
	</body>
</html:html>
