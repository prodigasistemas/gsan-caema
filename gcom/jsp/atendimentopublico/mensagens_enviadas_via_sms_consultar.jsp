<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ExibirConsultarMensagensEnviadasViaSmsActionForm" />

<script language="JavaScript">

function validarForm() {
	var form = document.forms[0];
	if (validateExibirConsultarMensagensEnviadasViaSmsActionForm(form)) {
		submeterFormPadrao(form);
	}
}
function extendeTabela(display){
	var form = document.forms[0];

	if(display){
	  	eval('layerHideDadosArquivos').style.display = 'none';
			eval('layerShowDadosArquivos').style.display = 'block';
	}else{
	  	eval('layerHideDadosArquivos').style.display = 'block';
			eval('layerShowDadosArquivos').style.display = 'none';
	}
}
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="consultarMensagensEnviadasViaSmsAction"
	name="ExibirConsultarMensagensEnviadasViaSmsActionForm"
	type="gcom.gui.atendimentopublico.ConsultarMensagensEnviadasViaSmsActionForm"	  
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
							<td class="parabg">Consultar Mensagens Enviadas Via SMS</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
		
					<!--Fim Tabela Reference a Páginação da Tela de Processo-->
					
					<table width="100%" border="0">
						<tr>
							<td colspan="3">Para consultar as mensagens para celular, informe os dados abaixo:</td>
						</tr>										
		
						<tr>
							<td><strong>Período do envio da mensagem:<font
										color="#FF0000">*</font></strong></td>

							<td colspan="2"><html:text
								name="ExibirConsultarMensagensEnviadasViaSmsActionForm"
								onkeyup="mascaraData(this, event);" property="dataInicial"
								size="8" maxlength="10"
								onkeypress="javascript:return isCampoNumerico(event);" /> <a
								href="javascript:abrirCalendario('ExibirConsultarMensagensEnviadasViaSmsActionForm', 'dataInicial', 'dataFinal')">
								<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								alt="Exibir Calendário" />
								</a> <strong>a</strong> <html:text
								name="ExibirConsultarMensagensEnviadasViaSmsActionForm"
								onkeyup="mascaraData(this, event);" property="dataFinal"
								size="8" maxlength="10"
								onkeypress="javascript:return isCampoNumerico(event);" /> <a
								href="javascript:abrirCalendario('ExibirConsultarMensagensEnviadasViaSmsActionForm', 'dataFinal')">
								<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								alt="Exibir Calendário" />
								</a> <strong>dd/mm/aaaa</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Situação de mensagem:</strong></td>
							<td colspan="2"><html:radio property="situacaoMensagem"
									value="<%=""+ConstantesSistema.TODOS%>" /> Todas <html:radio property="situacaoMensagem"
									value="<%=""+ConstantesSistema.NAO%>" /> Não Enviadas <html:radio
									property="situacaoMensagem" value="<%=""+ConstantesSistema.SIM%>" /> Enviadas</td>
						</tr>

						<tr>
							<td><strong>Tipo de SMS:</strong></td>
							<td><html:select property="tipoSms" tabindex="2">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoSmsTipo"
										labelProperty="descricaoTipo" property="id" />
								</html:select> <font size="1">&nbsp; </font></td>
						</tr>

						<tr>
							<td><p>&nbsp;</p></td>
							<td align="right">
								<div align="right">
									<strong><font color="#FF0000">*</font></strong> Campos
									obrigat&oacute;rios
								</div>
							</td>
						</tr>
										
						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirConsultarMensagensEnviadasViaSmsAction.do?menu=sim"/>'">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
						
							<td width="53" height="24" align="right"><input
								type="button" name="Button2" class="bottonRightCol"
								value="Pesquisar" onClick="javascript:validarForm();" /></td>
						</tr>
						
						
				
						<tr>
						  <logic:present name="colecaoMensagemSms">
						  
						 	 <tr>
								<td valign="baseline" bgcolor="#000000" height="2" colspan="5"></td>	
							</tr>
							<td width="100%" colspan="5">	
							
							<p>&nbsp;</p>
							<p>&nbsp;</p>
									<table width="100%" bgcolor="#99CCFF" border="0">
										<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
											<td width="20%" bgcolor="#90c7fc">
												<div align="center"><strong>Numero <br>Celular</strong></div>
											</td>
						
											<td width="20%" bgcolor="#90c7fc">
												<div align="center"><strong>Registro Atendimento</strong></div>
											</td>
					
											<td width="20%" bgcolor="#90c7fc">
												<div align="center"><strong>Data e Hora <br> de <br> envio</strong></div>
											</td>
				
											<td width="40%" bgcolor="#90c7fc">
												<div align="center"><strong>Tipo da Mensagem SMS</strong></div>
											</td>
										</tr>
									</table>
									
	 								<div style="height:200px;overflow:auto">
									<table width="100%" bgcolor="#99CCFF" border="0">
									<%int cont = 0;%>
								    <logic:iterate name="colecaoMensagemSms" id="mensagemSms">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="20%">
												<div align="center"><bean:write name="mensagemSms"
												property="numeroCelular" /></div>
											</td>
											<td width="20%">
												<div align="center">
													<logic:notEmpty name="mensagemSms" property="registroAtendimento">
											   			 <bean:write name="mensagemSms" property="registroAtendimento.id"/>
													</logic:notEmpty>
													<logic:empty name="mensagemSms" property="registroAtendimento">
														&nbsp;
													</logic:empty>
												</div>
											</td>
												
					
											<td width="20%">
												<div align="center"><bean:write name="mensagemSms"
												property="dataHoraEnvio" format="dd/MM/yyyy HH:mm:ss"/></div>
											</td>
				
											<td width="40%">
												<div align="center"><bean:write name="mensagemSms"
												property="smsTipo.descricaoTipo" /></div>
											</td>
										</tr>
								    </logic:iterate>
									</table>
								</div>
				              </td>
				          </logic:present>
				        </tr>						
								
				</table>
				
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
			
			</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>