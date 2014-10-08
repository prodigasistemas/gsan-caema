<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<!--================================= SCRIPTS =============================================================-->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<html:javascript staticJavascript="false"  formName="EfetuarParcelamentoJudicialActionForm" dynamicJavascript="false" />
		<!--=======================================================================================================-->
		
		<script language="JavaScript">
			var todosSelecionados = false;
			
			function validateEfetuarParcelamentoJudicialActionForm(form){
				return true;
			}
			
			function selecionarTodos(){
				if(!todosSelecionados){
					$('[@name=idsContasSelecionadas]').attr('checked',true);
					todosSelecionados = true;
				}
				else{
					$('[@name=idsContasSelecionadas]').attr('checked',false);
					todosSelecionados = false;
				}
			}
		
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/efetuarParcelamentoJudicialWizardAction"
				   name="EfetuarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.EfetuarParcelamentoJudicialActionForm"
				   method="post">
				   
				   <html:hidden property="idsContasSelecionadas" value="-1"/>
				   
			<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=2"/>
				   
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
								<td class="parabg">Efetuar Parcelamento Judicial</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
									<p>Para selecionar os débitos do(s) imóvel(is), informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO =========================-->
							<tr>
								<td width="22%"><strong>Matrícula do Imóvel:</strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="9" 
			               					   property="matriculaImovelFormatada" 
			               					   size="9"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>	
		               		<tr> 
								<td height="24" colspan="4"><hr></td>
							</tr>
		               		<tr>
								<td width="25%"><strong>Situação de Ligação de Água:</strong></td>
	                   			<td height="24" width="25%">
			               			<html:text maxlength="10" 
			               					   property="descSituacaoLigacaoAgua" 
			               					   size="10"  
			               					   tabindex="2" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
			               		
			               		<td width="25%"><strong>Situação de Ligação de Esgoto:</strong></td>
	                   			<td height="24" width="25%">
			               			<html:text maxlength="10" 
			               					   property="descSituacaoLigacaoEsgoto" 
			               					   size="10"  
			               					   tabindex="3" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>		
		               		<tr bgcolor="#cbe5fe">
		               			<td align="center" colspan="4">
		               				<table width="100%" border="0" bgcolor="#99CCFF">
		               					<tbody>
		               						<tr bgcolor="#99CCFF">
		               							<td height="18" colspan="2">
		               								<div align="center"><strong>Endereço</strong></div>
		               							</td>
		               						</tr>
		               						<tr bgcolor="#FFFFFF">
		               							<td align="center" colspan="4">
		               								<bean:write property="enderecoImovel" name="EfetuarParcelamentoJudicialActionForm"/>
		               							</td>
		               						</tr>
		               					</tbody>
		               				</table>
		               			</td>
		               		</tr>			   		   						
							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>		
							<!--===================== CONTAS =========================-->
							<c:if test="${not empty EfetuarParcelamentoJudicialActionForm.listaContaParcelamentoJudicialHelper 
												&& fn:length(EfetuarParcelamentoJudicialActionForm.listaContaParcelamentoJudicialHelper) gt 0 }">
								<tr>
									<td colspan="4" width="100%">
										<table border="0" width="100%" bgcolor="#90c7fc">
											<tr bgcolor="#79bbfd" bordercolor="#79bbfd">
												<td colspan="7" align="center">
													<strong>Contas</strong>
												</td>
											</tr>
											<tr>
												<td bgcolor="#90c7fc" align="center" width="5%"><strong><html:link href="javascript:selecionarTodos();">Todos</html:link></strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Imóvel</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Mês/Ano</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Vencimento</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Vl. Conta</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Acrésc. por Impont.</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Situação</strong></td>
											</tr>
										</table>
										<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaContaParcelamentoJudicialHelper) gt 7 }">																								 
											<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
										</c:if>																 
										<table border="0" width="100%" bgcolor="#90c7fc">
											<c:set var="count" value="0"/>
											<logic:iterate name="EfetuarParcelamentoJudicialActionForm" 
														   property="listaContaParcelamentoJudicialHelper" 
														   id="contasParc">
												<c:choose>
													<c:when test="${count % 2 == 0 }">
														<tr bgcolor="#FFFFFF">		
													</c:when>
													<c:otherwise>
														<tr bgcolor="#cbe5fe">
													</c:otherwise>
												</c:choose>
															<td width="7%" align="center"><html:multibox property="idsContasSelecionadas" value="${contasParc.idConta}"></html:multibox></td>
															<td width="16%" align="center"><bean:write property="matriculaImovelFormatada" name="contasParc"/></td>
															<td width="16%" align="center">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="contasParc" property="idConta" />&tipoConsulta=conta', 600, 800);">
																	<bean:write property="anoMesConta" name="contasParc"/>
																</a>
															</td>
															<td width="16%" align="center"><bean:write property="vencimentoConta" name="contasParc"/></td>
															<td width="16%" align="right"><bean:write property="valorContaFormatado" name="contasParc"/></td>
															<td width="16%" align="center">
																<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contasParc" property="valorMulta" />&juros=<bean:write name="contasParc" property="valorJurosMora" />&atualizacao=<bean:write name="contasParc" property="valorAtualizacaoMonetaria" />', 300, 650);">
																	<bean:write property="acrescimoImpontualidadeFormatado" name="contasParc"/>
																</a>
															</td>
															<td width="16%" align="center"><bean:write property="situacaoConta" name="contasParc"/></td>
														</tr>
														<c:set var="count" value="${count+1}"/> 	
											</logic:iterate>
										</table>
										<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaContaParcelamentoJudicialHelper) gt 7 }">																								 
											</DIV>
										</c:if>	
									</td>
								</tr>
							</c:if>
							<!--======================================================-->
							<tr>
								<td>&nbsp;</td>
							</tr>
                   			<tr>
								<td>&nbsp;</td>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							<tr>
								<td colspan="4">
									<table width="100%" border="0">
										<tr>
									        <td colspan="4">
												<div align="right">
													<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=2"/>
												</div>
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
		</div>
		<%@ include file="/jsp/util/telaespera.jsp"%>
	</body>
</html:html>
