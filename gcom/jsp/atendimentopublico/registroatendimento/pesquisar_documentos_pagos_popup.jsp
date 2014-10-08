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
				habilitarDesabilitarPeriodoVencimento();
			});
		
			function validarForm(){
				var form = document.forms[0];
				
				if(!$("input[@name='tipoDocumento']").is(":checked")){
					alert('Informe Tipo do Documento.')
				}
				else if(form.dataPagamentoInicial.value == ''){
					alert('Informe Data de Pagamento Inicial.')
				}
				else if(form.dataPagamentoFinal.value == ''){
					alert('Informe Data de Pagamento Final.')
				}
				else{
					form.action = "/gsan/pesquisarDocumentosPagosAction.do";
					form.submit();
				}
			}
			
			function habilitarDesabilitarPeriodoVencimento(){
				var form = document.forms[0];
			
				if($('input[@name=tipoDocumento]:checked').val() == '<%=""+DocumentoTipo.DEBITO_A_COBRAR%>'){
					form.dataVencimentoInicial.value = '';
					form.dataVencimentoFinal.value = '';
					form.dataVencimentoInicial.readOnly = true;
					form.dataVencimentoFinal.readOnly = true;
					
					document.getElementById("linkVencimentoInicial").href = "javascript:void(0);";
					document.getElementById("linkVencimentoInicial").style.cursor = "default";
					document.getElementById("linkVencimentoFinal").href = "javascript:void(0);";
					document.getElementById("linkVencimentoFinal").style.cursor = "default";
				}
				else{
					form.dataVencimentoInicial.readOnly = false;
					form.dataVencimentoFinal.readOnly = false;
					
					document.getElementById("linkVencimentoInicial").style.cursor = "pointer";
					document.getElementById("linkVencimentoFinal").style.cursor = "pointer";
					document.getElementById("linkVencimentoInicial").href = "javascript:abrirCalendarioReplicando('PesquisarDocumentosPagosActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');";
					document.getElementById("linkVencimentoFinal").href = "javascript:abrirCalendario('PesquisarDocumentosPagosActionForm', 'dataVencimentoFinal')";
				}
			}
			
		</script>
	</head>
		<body leftmargin="5" topmargin="5">
		
		
		<logic:present name="identificadorPesquisa" scope="request">
			<body leftmargin="5" topmargin="5">
		</logic:present>
		
		<logic:notPresent name="identificadorPesquisa" scope="request">
		<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');">
		</logic:notPresent>
		
		<html:form action="/pesquisarDocumentosPagosAction.do"
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
												<div align="center"><span class="style2"><strong>Dados do Imóvel</strong> </span></div>
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
						
						
						<!-- ======================================== CAMPOS DA PESQUISA ======================================== -->
						<table width="100%" border="0">
							<tr>
								<td colspan="4" height="24">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">Para pesquisar documentos pagos, informe os dados abaixo: </td>					
							</tr>
							
							<tr>
								<td width="30%" colspan="1"><strong>Tipo do Documento:<font color="#ff0000">*</font></strong></td>
								<td width="70%">
									<html:radio property="tipoDocumento" onchange="habilitarDesabilitarPeriodoVencimento()" value="<%=""+DocumentoTipo.CONTA%>">Conta</html:radio>
									<html:radio property="tipoDocumento" onchange="habilitarDesabilitarPeriodoVencimento()" value="<%=""+DocumentoTipo.GUIA_PAGAMENTO%>">Guia de Pagamento</html:radio>
									<html:radio property="tipoDocumento" onchange="habilitarDesabilitarPeriodoVencimento()" value="<%=""+DocumentoTipo.DEBITO_A_COBRAR%>">Débito a Cobrar</html:radio>
								</td>
							</tr>
							
							<tr>
								<td width="30%"><strong>Período de Referência:</strong></td>
								<td width="70%">
									<strong>
										<html:text maxlength="7" 
												   property="referenciaInicial" 
												   size="7"
		 										   onkeyup="mascaraAnoMes(this, event); replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial);" 
		 										   onkeypress="return isCampoNumerico(event);somente_numero(this);"
		 										   onblur="somente_numero(this);replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial);"/> 
										<strong> a</strong>
										<html:text maxlength="7" 
												   property="referenciaFinal" 
												   size="7" 
												   onkeyup="mascaraAnoMes(this, event);" 
												   onkeypress="return isCampoNumerico(event);somente_numero(this);"
												   onblur="somente_numero(this)"/>
									</strong> (mm/aaaa)
								</td>
							</tr>
							
							<tr>
								<td width="30%"><strong>Período de Emissão:</strong></td>
								<td width="70%">
									<strong> <html:text maxlength="10"
										property="dataEmissaoInicial" size="7" tabindex="10"
										onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataEmissaoFinal, document.forms[0].dataEmissaoInicial);" 
										onblur="somente_numero(this);replicarCampo(document.forms[0].dataEmissaoFinal, document.forms[0].dataEmissaoInicial);"
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/>
									<a href="javascript:abrirCalendarioReplicando('PesquisarDocumentosPagosActionForm', 'dataEmissaoInicial', 'dataEmissaoFinal');">
										<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
									</a>
									a</strong> <html:text maxlength="10" property="dataEmissaoFinal"
										tabindex="11" size="7" onkeyup="somente_numero(this);mascaraData(this, event);" 
										onblur="somente_numero(this)"
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/> 
									<a href="javascript:abrirCalendario('PesquisarDocumentosPagosActionForm', 'dataEmissaoFinal')">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>calendario.gif"
										width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
									(dd/mm/aaaa)
								</td>
							</tr>
							
							<tr>
								<td width="30%"><strong>Período de Vencimento:</strong></td>
								<td width="70%">
									<strong> <html:text maxlength="10"
										property="dataVencimentoInicial" size="7" tabindex="10"
										onkeyup="somente_numero(this);mascaraData(this, event); replicarCampo(document.forms[0].dataVencimentoFinal, document.forms[0].dataVencimentoInicial);" 
										onblur="somente_numero(this);replicarCampo(document.forms[0].dataVencimentoFinal, document.forms[0].dataVencimentoInicial);"
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/>
									<a href="javascript:abrirCalendarioReplicando('PesquisarDocumentosPagosActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');" id="linkVencimentoInicial">
										<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
									</a>
									a</strong> <html:text maxlength="10" property="dataVencimentoFinal"
										tabindex="11" size="7" onkeyup="somente_numero(this);mascaraData(this, event);" 
										onblur="somente_numero(this)" 
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/> 
									<a href="javascript:abrirCalendario('PesquisarDocumentosPagosActionForm', 'dataVencimentoFinal')"  id="linkVencimentoFinal">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>calendario.gif"
										width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
									(dd/mm/aaaa)
								</td>
							</tr>
							
							<tr>
								<td width="30%"><strong>Período de Pagamento:<font color="#ff0000">*</font></strong></td>
								<td width="70%">
									<strong> <html:text maxlength="10"
										property="dataPagamentoInicial" size="7" tabindex="10"
										onkeyup="somente_numero(this);mascaraData(this, event); replicarCampo(document.forms[0].dataPagamentoFinal, document.forms[0].dataPagamentoInicial);" 
										onblur="somente_numero(this);replicarCampo(document.forms[0].dataPagamentoFinal, document.forms[0].dataPagamentoInicial);"
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/>
									<a href="javascript:abrirCalendarioReplicando('PesquisarDocumentosPagosActionForm', 'dataPagamentoInicial', 'dataPagamentoFinal');">
										<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
									</a>
									a</strong> <html:text maxlength="10" property="dataPagamentoFinal"
										tabindex="11" size="7" onkeyup="somente_numero(this);mascaraData(this, event);" 
										onblur="somente_numero(this)" 
										onkeypress="return isCampoNumerico(event);somente_numero(this);"/>
									 <a href="javascript:abrirCalendario('PesquisarDocumentosPagosActionForm', 'dataPagamentoFinal')">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>calendario.gif"
										width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
									(dd/mm/aaaa)
								</td>
							</tr>
							
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
										   onclick="window.location.href='<html:rewrite page="/exibirPesquisarDocumentosPagosAction.do?idImovel=${PesquisarDocumentosPagosActionForm.matriculaImovel}&limpar=sim"/>'">
									<input type="button" name="ButtonCancelar" class="bottonRightCol"
									       value="Fechar"
										   onClick="javascript:window.close()">
								</td>
								<td colspan="2" align="right">
									<input type="button"
										   name="pesquisarButton" class="bottonRightCol"
										   value="Pesquisar"
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
