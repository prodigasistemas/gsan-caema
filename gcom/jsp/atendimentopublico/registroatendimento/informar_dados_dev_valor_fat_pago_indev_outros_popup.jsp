<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page	import="gcom.cobranca.DocumentoTipo"%>

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
		
			$(document).ready(function(){
				
				$(".campoMoeda").bind("keyup drop change",function(e){
					e.preventDefault();
					formataValorMonetario(e.currentTarget, 8);
				});
			});
			
			function validarForm(){
				var form = document.forms[0];
				if(form.valorCorrigido.value == ''){
					alert('Informe Valor Corrigido');
				}
				else{
					form.submit();
				}
			}
			
		</script>
	</head>
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(690, 400);setarFoco('valorCorrigido');" >
		
		
		<logic:present name="identificadorPesquisa" scope="request">
			<body leftmargin="5" topmargin="5">
		</logic:present>
		
		<logic:notPresent name="identificadorPesquisa" scope="request">
		<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');">
		</logic:notPresent>
		
		<html:form action="/informarDadosDevValorFatPagoIndevOutrosAction.do"
				   name="InformarDadosDevValorFatPagoIndevActionForm"
				   type="gcom.gui.atendimentopublico.registroatendimento.InformarDadosDevValorFatPagoIndevActionForm"
				   method="post">
				   
			<table width="625" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="635" valign="top" class="centercoltext">
						<table>
							<tr>
								<td></td>
							</tr>
						</table>
						<!-- ======================================== CABEÇALHO ============================================== -->
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Informar Dados para Devolução de Valor Faturado e Pago Indevidamente</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>	
							</tr>
						</table>			
						<!-- ================================================================================================= -->
						
						
						<!-- ======================================== DADOS DO IMÓVEL ======================================== -->
						<table width="100%" border="0">
							<tr>
								<td colspan="4" height="24">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">Informe abaixo o valor correto do documento: </td>					
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="2">
									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr>
											<td height="18" colspan="2">
												<div align="center"><span class="style2"> Dados do Imóvel </span></div>
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
																	   size="25" maxlength="25" />
														</td>
													</tr>
													<tr>
														<td><strong> Tipo de Documento:</strong></td>
														<td>
															<html:text property="tipoDocumento" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="25" maxlength="25" />
														</td>	
													</tr>
													<tr>
														<td><strong> Mês e ano do Documento:</strong></td>
														<td>
															<html:text property="anoMesDocumento" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="25" maxlength="25" />
														</td>	
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- ================================================================================================= -->
	
						<table>
							<tr>
								<td rowspan="10">&nbsp;</td>
							</tr>
						</table>
						
						<!-- ======================================== CAMPOS DA PESQUISA ======================================== -->
						<table width="100%" border="0" cellspacing="0">
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="3">
									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr>
											<td height="18" align="left" colspan="3">
												<div align="left"><span class="style2"><strong>Valor do Documento</strong></span></div>
											</td>
										</tr>
										<tr>	
											<td width="10%">&nbsp;</td>
											<td height="18" align="center" width="25%">
												<span class="style2"><strong>Faturado</strong></span>
											</td>
											<td height="18" align="center" width="33%">
												<span class="style2"><strong>Corrigido</strong></span>
											</td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td colspan="3">
												<table border="0" width="100%" cellspacing="0">
													<tr>
														<td width="22%" height="10"><strong>Valor:<font color="#FF0000">*</font></strong></td>
														<td width="43%">
															<html:text property="valorFaturado"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000; text-align:right;"
															size="14" maxlength="8" />
														</td>
														<td width="40%">
															<html:text property="valorCorrigido"
															size="14" maxlength="8"
															styleClass="campoMoeda"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- ==================================================================================================== -->

						<!-- ======================================== BOTÕES ==================================================== -->
						<table width="100%" border="0">
							<tr>
								<td></td>
								<td align="left">
									<strong><font color="#FF0000">*</font></strong>Campos obrigatórios
								</td>
							</tr>
							<tr>
								<td colspan="4">
								&nbsp;
								</td>
							</tr>
							
							<tr>
								<td colspan="2" >
									<input name="Button" type="button" class="bottonRightCol"
										   value="Desfazer" align="left"
										   onclick="window.location.href='<html:rewrite page="/exibirInformarDadosDevValorFatPagoIndevAction.do?limpar=sim&idPagamento=${requestScope.idPagamento}&idImovel=${requestScope.idImovel}&idDocumento=${requestScope.idDocumento}"/>'">
									<input type="button" name="ButtonCancelar" class="bottonRightCol"
									       value="Cancelar"
										   onClick="javascript:window.close()">
								</td>
								<td colspan="2" align="right">
									<input type="button"
										   name="pesquisarButton" class="bottonRightCol"
										   value="Concluir"
										   id="pesquisarButton"
										   onclick="validarForm()"/>
								</td>
							</tr>				
						</table>
						<!-- ==================================================================================================== -->
						
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
