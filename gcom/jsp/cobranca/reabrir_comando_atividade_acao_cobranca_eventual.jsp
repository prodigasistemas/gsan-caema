<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.cobranca.ReabrirComandoAtividadeAcaoCobrancaHelper"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAcaoCobrancaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>	
<html:javascript staticJavascript="false"
	formName="ReabrirComandoAtividadeAcaoCobrancaActionForm" />	
<script language="JavaScript">
<!-- Begin
	
	var marcarDesmarcarVar = false;
	
	function marcarDesmarcar(){
		
		<!-- Marcar todos-->
		if(!marcarDesmarcarVar){
			$("input[@name='idsSelecionados']").attr('checked', true);
			marcarDesmarcarVar = true;
		}
		
		<!-- Desmarcar todos-->
		else{
			$("input[@name='idsSelecionados']").attr('checked', false);
			marcarDesmarcarVar = false;
		}
	}
	
	
	function totalizarOS(){
		
		var form = document.forms[0];
		
		<!-- Verifica se há algum campo marcado-->
		if(validarComandosSelecionados()){
			form.action="/gsan/exibirReabrirComandoAtividadeAcaoCobrancaAction.do?tipoConsulta=eventual&totalizarOS=sim";
			form.submit();
		}
	}
	
	
	function validarComandosSelecionados(){
		if($("input[@name='idsSelecionados']").is(":checked")){
			return true;
		}
		else{
			alert("Selecione pelo menos um comando de ação de cobrança");
			return false;
		}
	}
	
	
	function atualizar(){
		var form = document.forms[0];
		if(validarComandosSelecionados()){
			form.action="/gsan/reabrirComandoAtividadeAcaoCobrancaEventualAction.do";
			form.submit();
		}
	}
	
	

-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/reabrirComandoAtividadeAcaoCobrancaEventualAction.do"
	name="ReabrirComandoAtividadeAcaoCobrancaActionForm"
	type="gcom.gui.cobranca.ReabrirComandoAtividadeAcaoCobrancaActionForm"
	method="post">
	
	<input type="hidden" name="primeiroAcesso" value="${requestScope.primeiroAcesso}" />

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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Reabrir Comando de Ação de Cobrança Eventual</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" cellpadding="0"cellspacing="3">
				<tr>
					<td colspan="3"></td>
				</tr>
				
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					
						<tr bordercolor="#79bbfd">
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Comandos de Ações de Cobranças Eventuais</strong></td>
						</tr>
						
						<logic:notEmpty name="colecaoHelperEventual" scope="session">
							<tr>
							<%if (((Collection) session.getAttribute("colecaoHelperEventual")).size() <= 5) {%>
								<td colspan="5" >
							<%} else { %>
								<td height="120" colspan="5">
							<%} %>		
									<table>
										<tr bordercolor="#000000">
											<td width="4%" bgcolor="#90c7fc" align="center">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<strong><a onclick="marcarDesmarcar()" href="#">Todos</a></strong> </font></div>
											</td>
											<td width="49%" bgcolor="#90c7fc" align="center">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<strong>Descrição do Comando</strong> </font></div>
											</td>
											<td width="25%" bgcolor="#90c7fc" align="center">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<strong>Ação de Cobrança</strong> </font></div>
											</td>
											<td width="10%" bgcolor="#90c7fc" align="center">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<strong>Data Encer.</strong> </font></div>
											</td>
											<td width="17%" bgcolor="#90c7fc" align="center">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<strong>Qtd. de OS</strong> </font></div>
											</td>			
										</tr>
									</table>
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%">			
											<%String cor = "#cbe5fe";%>
											<logic:present name="colecaoHelperEventual">
							
												<logic:iterate name="colecaoHelperEventual" id="comandoAtividadeAcaoCobrancaHelper" indexId="ind">
													<%if (cor.equalsIgnoreCase("#cbe5fe")) {
														cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
														<%} else {
														cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														<%}%>
					
														<td width="5%">
															<div align="center" class="style9">
																<c:choose>
																	<c:when test="${comandoAtividadeAcaoCobrancaHelper.checked}">
																		<input type="checkbox" name="idsSelecionados" value="${comandoAtividadeAcaoCobrancaHelper.idAtividade}" checked="checked" />
																	</c:when>
																	<c:otherwise>
																		<input type="checkbox" name="idsSelecionados" value="${comandoAtividadeAcaoCobrancaHelper.idAtividade}" />
																	</c:otherwise>
																</c:choose>
															</div>
														</td>
														
														
														<td width="50%">
															<div align="left" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<bean:write name="comandoAtividadeAcaoCobrancaHelper" property="descricaoComando" />
															</font></div>
														</td>
														
														
														<td width="25%">
															<div align="left" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 											
																<bean:write name="comandoAtividadeAcaoCobrancaHelper" property="acaoCobranca" />
															</font></div>
														</td>
														
														
														<td width="10%">
															<div align="left" class="style9">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">										
																	<bean:write name="comandoAtividadeAcaoCobrancaHelper" property="dataEncerramento" />										
																</font>
															</div>
														</td>		
														
																					
														<td width="10%">
															<div align="center" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="comandoAtividadeAcaoCobrancaHelper" property="qtdOS" />
															</font></div>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>		 							
				
				
										</table>
									</div>
								</td>						
							</tr>											
						</logic:notEmpty>
						<tr>
							<td colspan="4" align="right" width="83%"><strong>Total de OSs encerradas por descurso de prazo:</strong></td>
							<td>
								<html:text property="totalOSDescursoPrazo" readonly="true" size="5" maxlength="5"/>
							</td>
						</tr>	
						<tr>
							<td colspan="3">
								<input name="totalizar" class="bottonRightCol" value="Totalizar"
									   type="button"
									   onclick="totalizarOS()" />
							</td>
						</tr>							
					</table>
				</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="left" width="29%"><strong>Nova data de encerramento:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><strong> <html:text maxlength="10" tabindex="15"
						property="novaDataEncerramento" size="10"
						onkeyup="somente_numero(this);mascaraData(this, event);"
						onblur="somente_numero(this);"
						onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('ReabrirComandoAtividadeAcaoCobrancaActionForm', 'novaDataEncerramento')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						dd/mm/aaaa</strong>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" colspan="5"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>	
				<tr>
				
					<td colspan="5"><font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirReabrirComandoAtividadeAcaoCobrancaAction.do?tipoConsulta=eventual&desfazer=sim';" />

					<input name="cancelar" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'" />
						
					<input name="voltar" class="bottonRightCol" value="Voltar"
						type="button"
						onclick="window.location.href='/gsan/exibirFiltrarComandosAcaoCobrancaEventualReabrirAction.do?ultimoacesso=-1&situacaoComando=reabrir&tipoComando=Eventual'" />
						
							<input name="botaoAtualizar" 
							   class="bottonRightCol" 
							   style="float:right;"
							   value="Atualizar"
							   type="button"
							   onclick="atualizar()" />
				  	</font>
				  </td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
