<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.imovel.Categoria" %>
<%@ page import="gcom.cadastro.imovel.Subcategoria" %>
<%@ page import="gcom.faturamento.debito.DebitoCobrado" %>
<%@ page import="gcom.faturamento.credito.CreditoRealizado" %>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro" %>
<%@ page import="gcom.financeiro.FinanciamentoTipo" %>
<%@ page import="gcom.faturamento.consumotarifa.ConsumoTarifa" %>

<%@ page import="gcom.cadastro.cliente.ClienteConta" %>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao" %>
<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao" %>
<%@ page import="gcom.gui.GcomAction" %>

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
		<html:javascript staticJavascript="false" formName="InformarDadosDevValorFatPagoIndevActionForm" />
		
		<script language="JavaScript">
			
			$(document).ready(function(){
				
				$("input[@name='idSituacaoAguaConta']").change(recarregarSituacaoAguaConta);
				
				$(".campoNumerico").bind("blur drop",function(e){
						e.preventDefault();
						verificaNumeroInteiro(e.currentTarget);
					})
					.bind("keypress",function(e){
						return isCampoNumerico(e);
				});
					
				
				$(".campoPorcentagem").bind("keyup drop",function(e){
					e.preventDefault();
					formataValorMonetario(e.currentTarget, 5);
				});
				
				$(".campoMoeda").bind("keyup drop",function(e){
					e.preventDefault();
					formataValorMonetario(e.currentTarget, 11);
				});
			});
		
			
			function recarregarSituacaoAguaConta(){
				var form = document.forms[0];
				redirecionarSubmit('exibirInformarDadosDevValorFatPagoIndevAction.do?idImovel='
										+form.idImovel.value+'&idPagamento='+form.idPagamento.value+
										'&idDocumento'+form.idDocumento.value+'&recarregarSituacaoAgua=ok');
			}
			
			function validarForm(){
				var form = document.forms[0];
				form.action = "/gsan/informarDadosDevValorFatPagoIndevContaAction.do";
				form.submit();
			}
			
			function abrirPopupCategoriaEconomia(){
				var form = document.forms[0];
				url = 'exibirAdicionarCategoriaContaAction.do?idPagamento='+form.idPagamento.value+'&idImovel='+form.idImovel.value+'&idDocumento='+form.idDocumento.value
				win= window.open(url,"child","Width=300,Height=450,Scrollbars=yes");
				win.focus(); 
			}
			
			function abrirPopupDebitosCobrados(){
				var form = document.forms[0];
				url = "exibirAdicionarDebitoCobradoContaAction.do?imovel=" + document.forms[0].idImovel.value+"&idPagamento="+form.idPagamento.value+"&idDocumento="+form.idDocumento.value
				win= window.open(url,"child","Width=300,Height=450,Scrollbars=yes");
				win.focus(); 
			}
			
			function abrirPopupCreditosRealizados(){
				var form = document.forms[0];
				url = "exibirAdicionarCreditoRealizadoContaAction.do?imovel=" + document.forms[0].idImovel.value+"&idPagamento="+form.idPagamento.value+"&idDocumento="+form.idDocumento.value
				win= window.open(url,"child","Width=300,Height=450,Scrollbars=yes");
				win.focus(); 
			}
			
			
			function removerCategoria(idCategoria){	
				var form = document.forms[0];
			 	if (validarCamposDinamicos(form)){
			 		url = 'exibirInformarDadosDevValorFatPagoIndevAction.do?reloadPage=ok&idImovel='+form.idImovel.value+'&idPagamento='+form.idPagamento.value+'&idDocumento'+form.idDocumento.value+"&removerCategoria=ok&idCategoria="+idCategoria
			 		form.action = "/gsan/"+url;
			 		if (confirm("Confirma remoção?")){
						form.submit();
					}
				}
			 }
			
			function removerDebitosCobrados(debitoCobradoUltimaAlteracao){	
				var form = document.forms[0];
			 	if (validarCamposDinamicos(form)){
			 		url = 'exibirInformarDadosDevValorFatPagoIndevAction.do?reloadPage=ok&idImovel='+form.idImovel.value+'&idPagamento='+form.idPagamento.value+'&idDocumento'+form.idDocumento.value+"&removerDebito=ok&debitoCobradoUltimaAlteracao="+debitoCobradoUltimaAlteracao
			 		form.action = "/gsan/"+url;
			 		if (confirm("Confirma remoção?")){
						form.submit();
					}
				}
			 }
			
			function removerCreditoRealizado(creditoRealizadoUltimaAlteracao){	
				var form = document.forms[0];
			 	if (validarCamposDinamicos(form)){
			 		url = 'exibirInformarDadosDevValorFatPagoIndevAction.do?reloadPage=ok&idImovel='+form.idImovel.value+'&idPagamento='+form.idPagamento.value+'&idDocumento'+form.idDocumento.value+"&removerCredito=ok&creditoRealizadoUltimaAlteracao="+creditoRealizadoUltimaAlteracao
			 		form.action = "/gsan/"+url;
			 		if (confirm("Confirma remoção?")){
						form.submit();
					}
				}
			 }
			
			function calcular(){
				var form = document.forms[0];
				url = 'exibirInformarDadosDevValorFatPagoIndevAction.do?reloadPage=ok&idImovel='+form.idImovel.value+'&idPagamento='+form.idPagamento.value+'&idDocumento'+form.idDocumento.value+"&calcularTotalConta=ok";
		 		form.action = "/gsan/"+url;
				form.submit();
			}
			
			function validarCamposDinamicos(form){
			 	var camposValidos = true;
			 	for (i=0; i < form.elements.length; i++) {
			    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
						switch (form.elements[i].id){
						
							case "categoria":
								
								if (form.elements[i].value.length < 1){
									alert("Informe Quantidade de Economias.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
									alert("Quantidade de Economias deve conter apenas valores inteiros.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (!testarCampoValorZero(form.elements[i], "Quantidade de Categorias")){
									form.elements[i].focus();
									camposValidos = false;
								}
															
								break;
								
							case "debito":
							
								var value = form.elements[i].value;
								value = value.replace(/\./g, '');
								value = value.replace(/,/g, '.');
							
								if (value.length < 1){
									alert("Informe Valor do Débito.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (isNaN(value) || value < 0){
									alert("Valor do Débito deve somente conter números positivos.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Débito")){
									form.elements[i].focus();
									camposValidos = false;
								}
								
								break;
								
							case "credito":
							
								var value = form.elements[i].value;
								value = value.replace(/\./g, '');
								value = value.replace(/,/g, '.');
							
								if (value.length < 1){
									alert("Informe Valor do Crédito.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (isNaN(value) || value < 0){
									alert("Valor do Crédito deve somente conter numéros positivos.");
									form.elements[i].focus();
									camposValidos = false;
								}
								else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Crédito")){
									form.elements[i].focus();
									camposValidos = false;
								}
						
								break;
								
							default:
								break;
						}	
			    	}
			    	
			    	if (!camposValidos){
			    		break;
			    	}
			    }
			    
			    return camposValidos;
			}
			
			
			function atualizarSituacaoEsgoto(){
				var form = document.forms[0];
				redirecionarSubmit('exibirInformarDadosDevValorFatPagoIndevAction.do?idImovel='
										+form.idImovel.value+'&idPagamento='+form.idPagamento.value+
										'&idDocumento'+form.idDocumento.value+'&reloadPage=ok&atualizarSituacaoEsgoto=ok');
			}

			function habilitarCampos(){
				var form = document.forms[0];
				
				if(form.percentualColeta.value != ''){

					form.percentualColeta.readOnly = false;
					form.percentualColeta.style.backgroundColor = '';
					form.consumoEsgoto.readOnly = true;
					form.consumoEsgoto.style.backgroundColor = '#EFEFEF';
					
				} else if(form.consumoEsgoto.value != ''){
					
					if (form.consumoEsgoto.readOnly) {
						form.consumoEsgoto.readOnly = false;
						form.consumoEsgoto.value = '';
						form.consumoEsgoto.style.backgroundColor = '';
					} else {
						form.percentualColeta.readOnly = true;
						form.percentualColeta.style.backgroundColor = '#EFEFEF';
					}
					
				} else if (!form.percentualColeta.readOnly
						|| !form.consumoEsgoto.readOnly) {
					
					form.percentualColeta.readOnly = false;
					form.percentualColeta.value = '';
					form.percentualColeta.style.backgroundColor = '';
					form.consumoEsgoto.readOnly = false;
					form.consumoEsgoto.value = '';
					form.consumoEsgoto.style.backgroundColor = '';
					
				}
				
			}
			
			
		</script>
	</head>
	
	<logic:notPresent name="msgAlert">
		<body leftmargin="5" 
			topmargin="5" 
			onload="resizePageSemLink(690, 650);habilitarCampos();">
	</logic:notPresent>
	
	
	<logic:present name="msgAlert">
		<body leftmargin="5" 
			topmargin="5" 
			onload="resizePageSemLink(690, 650);habilitarCampos();alert('${requestScope.msgAlert}');">
	</logic:present>
		
		<logic:present name="identificadorPesquisa" scope="request">
			<body leftmargin="5" topmargin="5">
		</logic:present>
		
		<logic:notPresent name="identificadorPesquisa" scope="request">
		<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');">
		</logic:notPresent>
		
		<html:form action="/informarDadosDevValorFatPagoIndevContaAction.do"
				   name="InformarDadosDevValorFatPagoIndevActionForm"
				   type="gcom.gui.atendimentopublico.registroatendimento.InformarDadosDevValorFatPagoIndevActionForm"
				   method="post">
				   
			<input type="hidden" name="situacaoAguaCortado" value="${InformarDadosDevValorFatPagoIndevActionForm.situacaoAguaCortado}"/>
			<input type="hidden" name="situacaoAguaLigado" value="${InformarDadosDevValorFatPagoIndevActionForm.situacaoAguaLigado}"/>
			<input type="hidden" name="idImovel" value="${requestScope.idImovel}"/>
			<input type="hidden" name="idPagamento" value="${requestScope.idPagamento}"/>
			<input type="hidden" name="idDocumento" value="${requestScope.idDocumento}"/>
			<input type="hidden" name="habilitarAlteracaoVolumeEsgoto" value="${requestScope.habilitarAlteracaoVolumeEsgoto}"/>
			<input type="hidden" name="volumeEsgotoPreenchimentoObr" value="${requestScope.volumeEsgotoPreenchimentoObr}"/>
			<input type="hidden" name="percentualColetaPreenchimentoObr" value="${requestScope.percentualColetaPreenchimentoObr}"/>
			<input type="hidden" name="consumoAguaPreenchimento" value="${requestScope.consumoAguaPreenchimento}"/>
				   
			<table width="625" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="635" valign="top" class="centercoltext">
						<table>
							<tr>
								<td></td>
							</tr>
						</table>
						<!-- ======================================== CABEÇALHO ============================================= -->
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Informar Dados para Devolução de Valor Faturado e Pago Indevidamente</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>	
							</tr>
						</table>			
						<!-- ================================================================================================ -->
						
						
						<!-- ======================================== DADOS DO IMÓVEL ======================================= -->
						<table width="100%" border="0">
							<tr>
								<td colspan="4" height="24">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">Informe os dados abaixo para o cálculo do valor correto da conta: </td>					
							</tr>
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
																	   size="15" maxlength="15" />
														</td>
													</tr>
													<tr>
														<td><strong>Situação de Água:</strong></td>
														<td>
															<html:text property="situacaoAgua" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
													<tr>
														<td><strong>Situação de Esgoto:</strong></td>
														<td>
															<html:text property="situacaoEsgoto" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
													<tr>
														<td><strong> Mês e Ano da Conta:</strong></td>
														<td>
															<html:text property="anoMesDocumento" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- ================================================================================================ -->
	
						<table>
							<tr>
								<td rowspan="10">&nbsp;</td>
							</tr>
						</table>
						
						<!-- ======================================== DADOS DA CONTA ======================================== -->
						<table width="100%" border="0">
							<tr>
								<td colspan="4" height="24">&nbsp;</td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="2">
									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr>
											<td height="18" colspan="2">
												<div align="center"><span class="style2"><strong>Dados da Conta</strong></span></div>
											</td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">
													<tr> 
											          	<td height="10"><strong>Motivo da Cobrança Indevida:<font color="#FF0000">*</font></strong></td>
											          	<td>
															<html:select property="idMotivoCobrancaIndevida" style="width: 400px;" tabindex="3">
																<logic:present name="colecaoMotivoRetificacaoConta">
																	<html:options collection="colecaoMotivoRetificacaoConta" labelProperty="descricao" property="id" />
																</logic:present>
															</html:select>
													  	</td>
											      	</tr>	
													<tr> 
											          	<td height="10"><strong>Tarifa de Consumo:</strong></td>
														<td>		      								
															<html:select property="idConsumoTarifa" style="width: 190px;" tabindex="5">
																<logic:present name="colecaoConsumoTarifa">										
																	<html:options collection="colecaoConsumoTarifa" labelProperty="descricao" property="id" />													      															
																</logic:present>									
															</html:select>
														</td>
													</tr>
													<tr> 
														<td height="10" width="140"><strong>Situação de Água:</strong></td>
														<td>
															<html:select property="idSituacaoAguaConta" style="width: 190px;" tabindex="5" >
																<logic:present name="colecaoSituacaoLigacaoAgua">
																	<html:options collection="colecaoSituacaoLigacaoAgua" labelProperty="descricao" property="id" />
																</logic:present>
															</html:select>	
														</td>
													</tr>
													<tr>
														<td height="10"><strong>Consumo de Água:</strong></td>
														<td>
															<c:choose>
																<c:when test="${requestScope.consumoAguaPreenchimento eq InformarDadosDevValorFatPagoIndevActionForm.sim}">
																	<html:text property="consumoAgua" size="10" maxlength="6" 
																			   tabindex="6" style="text-align: right;" 
																			   styleClass="campoNumerico"/>
																			   
																</c:when>
																<c:otherwise>
																	<html:text property="consumoAgua" size="10" maxlength="6" 
																			   tabindex="6" style="text-align: right;" 																	
																			   styleClass="campoNumerico"
																			   readonly="true"/>
																</c:otherwise>
															</c:choose>		   
														</td>
													</tr>
													<tr> 
														<td height="10" width="140"><strong>Situação de Esgoto:</strong></td>
														<td>
															<html:select property="idSituacaoEsgotoConta" 
																		 style="width: 190px;" 
																		 tabindex="7"
																		 onchange="atualizarSituacaoEsgoto()">
																<logic:present name="colecaoSituacaoLigacaoEsgoto">
																	<html:options collection="colecaoSituacaoLigacaoEsgoto" labelProperty="descricao" property="id" />
																</logic:present>
															</html:select>	
														</td>
													</tr>	
													<tr> 
														<td height="10"><strong>Volume de Esgoto:</strong></td>
														<td>
															<c:choose>
																<c:when test="${requestScope.habilitarAlteracaoVolumeEsgoto eq InformarDadosDevValorFatPagoIndevActionForm.sim}">
																	<html:text property="consumoEsgoto" size="10" maxlength="6" 
																			   tabindex="6" style="text-align: right;" 
																			   styleClass="campoNumerico"
																			   onkeyup = "habilitarCampos();"
																			   />
																</c:when>
																<c:otherwise>
																	<html:text property="consumoEsgoto" size="10" maxlength="6" 
																				   tabindex="6" 
																				   style="text-align: right; background-color:#EFEFEF;"
																		   		   styleClass="campoNumerico" 
																				   readonly="true"
																			   	   onkeyup = "habilitarCampos();"/>
																</c:otherwise>		   
															</c:choose>		   
														</td>
													</tr>
													<tr> 
														<td height="10"><strong>Percentual de Esgoto:</strong></td>
														<td>
															<c:choose>
																<c:when test="${requestScope.habilitarAlteracaoVolumeEsgoto eq InformarDadosDevValorFatPagoIndevActionForm.sim}">
																	<html:text property="percentualEsgoto" 
																			   size="10" 
																			   maxlength="6" 
																			   tabindex="9" 
																			   onkeyup="formataValorMonetario(this, 5);" 
																			   styleClass="campoPorcentagem"
																			   style="text-align: right;"/>%
																</c:when>
																<c:otherwise>
																
																	<html:text property="percentualEsgoto" 
																			   size="10" 
																			   maxlength="6" 
																			   tabindex="9" 
																			   onkeyup="formataValorMonetario(this, 5);" 
																			   style="text-align: right; background-color:#EFEFEF; "
																			   styleClass="campoPorcentagem"
																			   readonly="true"/>%
																
																</c:otherwise>		   
															</c:choose>		   
														</td>
													</tr>
													<tr> 
												      	<td height="10"><strong>Volume do Poço:</strong></td>
														<td>
															<c:choose>
																<c:when test="${requestScope.habilitarAlteracaoVolumeEsgoto eq InformarDadosDevValorFatPagoIndevActionForm.sim}">
																	<html:text property="consumoFaturadoPoco" 
																		   size="10" 
																		   maxlength="6" 
																		   tabindex="10" 
																		   style="text-align: right;" 
																		   styleClass="campoNumerico"
																		   onkeypress="javascript:return isCampoNumerico(event);"/>
																</c:when>
																<c:otherwise>
																	<html:text property="consumoFaturadoPoco" 
																		   size="10" 
																		   maxlength="6" 
																		   tabindex="10" 
																		   style="text-align: right; background-color:#EFEFEF; "
																		   styleClass="campoNumerico" 
																		   readonly="true"/>
																</c:otherwise>		   
															</c:choose>
														</td>
													</tr>
													<tr> 
														<td height="10"><strong>Percentual de Coleta:</strong></td>
														<c:choose>
															<c:when test="${requestScope.habilitarAlteracaoVolumeEsgoto eq InformarDadosDevValorFatPagoIndevActionForm.sim}">
																<td>
																	<html:text property="percentualColeta" 
																		size="10" 
																		maxlength="6" 
																		tabindex="11" 
																		style="text-align: right;" 																	
																		styleClass="campoPorcentagem"
																		onkeyup = "habilitarCampos();"
																		/>%
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<html:text property="percentualColeta" 
																		size="10" 
																		maxlength="6" 
																		tabindex="11" 
																		style="text-align: right; background-color:#EFEFEF;"
																		readonly="true"
																		styleClass="campoPorcentagem"
																		onkeyup = "habilitarCampos();"
																		/>%
																</td>
															</c:otherwise>
														</c:choose>
													</tr>	
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- ================================================================================================ -->

						<table>
							<tr>
								<td rowspan="10">&nbsp;</td>
							</tr>
						</table>

						<!-- ======================================== CATEGORIA E ECONOMIA ================================== -->
						<table width="100%" border="0">
							<tr>
								<td height="17" colspan="3"><strong>Categorias e Economias:</strong></td>
								<td align="right">
									<input type="button" class="bottonRightCol"
									value="Adicionar" tabindex="11" style="width: 80px"
									onclick="javascript:abrirPopupCategoriaEconomia();">	
								</td>
							</tr>
							<tr>
								<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table width="100%" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
	
													<td align="center" width="10%"><strong>Remover</strong></td>
													<td width="60%">
														<div align="center"><strong>Categoria</strong></div>
													</td>
													<td width="30%">
														<div align="center"><strong>Quantidade de Economias</strong></div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<logic:present name="colecaoCategoria">
										<tr>
											<td>
												<div style="width: 100%; height: 100; overflow: auto;">
													<table width="100%" align="center" bgcolor="#99CCFF">
														<logic:iterate name="colecaoCategoria" id="categoria"
															type="Categoria">
																<c:set var="count" value="${count+1}"/>
									                       		<c:choose>
									                       			<c:when test="${count%2 == '1'}">
									                       				<tr bgcolor="#FFFFFF">
									                       			</c:when>
									                       			<c:otherwise>
									                       				<tr bgcolor="#cbe5fe">
									                       			</c:otherwise>
									                       		</c:choose>
			
																<td align="center" width="10%" valign="middle">
																	<a href="javascript:removerCategoria(${categoria.id})" >
																		<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																	</a>
																</td>
			
																<td width="60%">
																	<bean:write name="categoria" property="descricao" /></td>
																<td width="30%">
																	<div align="center">
																		<input type="text" name="categoria${categoria.id}"
																			   size="6" maxlength="4"
																			   value="${categoria.quantidadeEconomiasCategoria}"
																			   style="text-align: right;"
																			   class="campoNumerico"/>
																	</div>
																</td>
															</tr>
															</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</logic:present>
									</table>
								</td>
							</tr>							
						</table>
						<!-- ================================================================================================ -->
						
						<!-- ======================================== DÉBITOS COBRADOS ====================================== -->
						<table width="100%" border="0">
							<tr>
								<td height="17" colspan="3"><strong>Débitos Cobrados:</strong></td>
								<td align="right">
									<input type="button" class="bottonRightCol" value="Adicionar"
										   tabindex="11" style="width: 80px" onclick="abrirPopupDebitosCobrados();">
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" bgcolor="#99CCFF">
													<tr bgcolor="#99CCFF">
														<td align="center" width="10%"><strong>Remover</strong></td>
														<td align="center" width="30%"><strong>Tipo do Débito</strong></td>
														<td align="center" width="20%"><strong>Mês/Ano do Débito</strong></td>
														<td align="center" width="20%"><strong>Mês/Ano da Cobrança</strong></td>
														<td align="center" width="20%"><strong>Valor do Débito</strong></td>
													</tr>
												</table>
											</td>
										</tr>	
										<logic:present name="colecaoDebitoCobrado">
											<tr>
												<td>
													<div style="width: 100%; height: 100; overflow: auto;">
														<table width="100%" align="center" bgcolor="#99CCFF">
														<logic:iterate name="colecaoDebitoCobrado" id="debitoCobrado" type="DebitoCobrado">
															<c:set var="count" value="${count+1}"/>
								                       		<c:choose>
								                       			<c:when test="${count%2 == '1'}">
								                       				<tr bgcolor="#FFFFFF">
								                       			</c:when>
								                       			<c:otherwise>
								                       				<tr bgcolor="#cbe5fe">
								                       			</c:otherwise>
								                       		</c:choose>
								                       		
																<td align="center" width="10%" valign="middle">
																	<a href="javascript:removerDebitosCobrados(${gsan:obterTimestampIdObjeto(debitoCobrado)})">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0"></a>
																</td>
																
																<td width="30%">
																	<bean:write name="debitoCobrado" property="debitoTipo.descricao" />
																</td>
																
																<td align="center" width="20%">
																	<c:out value="${gsan:formatarMesAnoReferencia(debitoCobrado.anoMesReferenciaDebito)}" />
																</td>
																
																<td align="center" width="20%">
																	<c:out value="${gsan:formatarMesAnoReferencia(debitoCobrado.anoMesCobrancaDebito)}" />
																</td>
																
																<td align="center" width="20%">
																	<input type="text"  name="debitoCobrado${gsan:obterTimestampIdObjeto(debitoCobrado)}"
																	size="14" maxlength="14" value="${gsan:formatarMoedaReal(debitoCobrado.valorPrestacao)}"
																	style="text-align: right;"
																	class="campoMoeda"/>
																</td>
															</tr>
														</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</logic:present>
									</table>
								</td>
							</tr>
						</table>
						<!-- ================================================================================================ -->

						<!-- ======================================== CRÉDITOS REALIZADOS =================================== -->
						<table width="100%" border="0">
					  		<tr> 
				          		<td height="17" colspan="3"><strong>Créditos Realizados:</strong></td>
				          		<td align="right">
				          			<input type="button" class="bottonRightCol" value="Adicionar" 
				          				   tabindex="11" style="width: 80px" onclick="abrirPopupCreditosRealizados();">						 
				      			</td>
				      		</tr>
				      		
				      		<tr> 
				          		<td colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr> 
					                		<td> 
												<table width="100%" bgcolor="#99CCFF">
						                    		<tr bgcolor="#99CCFF"> 
						
														<td align="center" width="10%"><strong>Remover</strong></td>
														<td width="30%"><div align="center"><strong>Tipo do Crédito</strong></div></td>
														<td width="15%"><div align="center"><strong>Mês/Ano do Crédito</strong></div></td>
														<td width="15%"><div align="center"><strong>Mês/Ano da Cobrança</strong></div></td>
														<td width="20%"><div align="center"><strong>Valor do Crédito</strong></div></td>
													</tr>
					                    		</table>									
											</td>
					            		</tr>
				            		
					            		<logic:present name="colecaoCreditoRealizado">			       
						            		<tr> 
												<td> 									
													<div style="width: 100%; height: 100; overflow: auto;">																					
													<table width="100%" align="center" bgcolor="#99CCFF">	
						
														<logic:iterate name="colecaoCreditoRealizado" id="creditoRealizado" type="CreditoRealizado">
					                            			<c:set var="count" value="${count+1}"/>
								                       		<c:choose>
								                       			<c:when test="${count%2 == '1'}">
								                       				<tr bgcolor="#FFFFFF">
								                       			</c:when>
								                       			<c:otherwise>
								                       				<tr bgcolor="#cbe5fe">
								                       			</c:otherwise>
								                       		</c:choose>
															 
																<td align="center" width="10%" valign="middle">
																	<a href="javascript:removerCreditoRealizado(${gsan:obterTimestampIdObjeto(creditoRealizado)})">
																		<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																	</a>
																</td>
																<td width="30%">	
																	<bean:write name="creditoRealizado" property="creditoTipo.descricao"/>															
																</td>
																<td width="20%">
																	<div align="center">
																		<c:out value="${gsan:formatarMesAnoReferencia(creditoTipo.anoMesReferenciaCredito)}"/>
																	</div>
																</td>
																<td width="20%">
																	<div align="center">
																		<c:out value="${gsan:formatarMesAnoReferencia(creditoTipo.anoMesCobrancaCredito)}"/>										
																	</div>
																</td>
																<td width="20%">
																	<div align="center">
																		<input type="text" name="creditoRealizado${gsan:obterTimestampIdObjeto(creditoRealizado)}" 
																			   size="14" maxlength="14" value="${gsan:formatarMoedaReal(creditoRealizado.valorCredito)}" 
																			   class="campoMoeda" style="text-align: right;"/>																	
																	</div>
																</td>
															</tr>													
														</logic:iterate>
														</table>
													</div>
												</td>
						            		</tr>				       
					            		</logic:present>			            		
				            		</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="right">
									<input type="button" class="bottonRightCol" value="Calcular" tabindex="10"
										onclick="calcular();" style="width: 80px">
								</td>
							</tr>				      		
			      		</table>
						<!-- ================================================================================================ -->

						<!-- ======================================== VALORES DA CONTA ====================================== -->
						<table width="100%" border="0">
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="2">
									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr>
											<td height="18" colspan="2">
												<div align="left"><span class="style2"><strong>Valores da Conta</strong></span></div>
											</td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td colspan="3">
												<table border="0" width="100%">
													<tr>
														<td width="37%" height="10"><strong>Valor de Água:</strong></td>
														<td width="58%">
															<html:text property="valorAgua"
																	   readonly="true"
																	   style="background-color:#EFEFEF; border:0; color: #000000"
																	   size="15" maxlength="15" />
														</td>
													</tr>
													<tr>
														<td><strong>Valor de Esgoto:</strong></td>
														<td>
															<html:text property="valorEsgoto" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
													<tr>
														<td><strong>Valor dos Débitos:</strong></td>
														<td>
															<html:text property="valorDebitos" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
													<tr>
														<td><strong>Valor dos Créditos:</strong></td>
														<td>
															<html:text property="valorCreditos" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
													<tr>
														<td><strong>Valor Total da Conta:</strong></td>
														<td>
															<html:text property="valorTotalConta" readonly="true" 
															style="background-color:#EFEFEF; border:0; color: #000000"
															size="15" maxlength="15" />
														</td>	
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
						<!-- ================================================================================================ -->

						<!-- ======================================== BOTÕES ================================================ -->
						<table width="100%" border="0">
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
						<!-- ================================================================================================ -->
						
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
