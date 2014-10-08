<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirMotivoDeNaoExecucaoDoServicoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	function validarForm(form) {
		
		if(form.descricao.value == ""){
			alert ('Informe Descrição');
			return;
		}
		
		form.action = 'inserirMotivoDeNaoExecucaoDoServicoAction.do';
		form.submit();
	}

	function limparForm(form) {

		form.descricao.value = "";
		form.descricaoAbrev.value = "";
		form.unidadeRepavimentadora.value = "-1";
	}

	function adicionarUnidadeRepavimentadora() {
		var form = document.forms[0];

		if (form.unidadeRepavimentadora.value == '' || form.unidadeRepavimentadora.value == '-1') {
			alert('Informe a Unidade Repavimentadora');
			return;
		} else {
			form.action = 'exibirInserirMotivoDeNaoExecucaoDoServicoAction.do?adicionar=sim';
			form.submit();
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	<!-- onKeyDown="return letrasNumeros(event.keyCode, event.which);"-->

	<html:form action="/inserirMotivoDeNaoExecucaoDoServicoAction" method="post"
		name="InserirMotivoDeNaoExecucaoDoServicoActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.InserirMotivoDeNaoExecucaoDoServicoActionForm"
		onsubmit="return validateMotivoDeNaoExecucaoDoServicoActionForm(this);">
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">

			<tr>
				<td width="150" valign="top" class="leftcoltext">
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
				<td width="625" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table> <!--Início Tabela Reference a Páginação da Tela de Processo-->
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Inserir Motivo de não Execução do Serviço de Repavimentação</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para inserir um motivo de não execução do serviço, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td HEIGHT="30" width="30%">
								<strong>Descri&ccedil;&atilde;o:
									<font color="#FF0000">*</font> 
								</strong>
							</td>
							<td colspan="2"><html:text property="descricao"
									maxlength="50" size="40" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Descri&ccedil;&atilde;o Abreviada:</strong>
							</td>
							<td>
								<strong> 
									<html:text property="descricaoAbreviada" size="7" maxlength="5" /> 
								</strong>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Unidade Repavimentadora:
									<font color="#FF0000">*</font>
								</strong>
							</td>
							<td><html:select property="unidadeRepavimentadora"
									tabindex="5" style="width:200px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoUnidadeRepavimentadora"
										labelProperty="descricao" property="id" />
								</html:select> <font size="1">&nbsp; </font>
							</td>
						</tr>
						<tr>
							<td height="19"><strong> <font color="#FF0000"></font>
							</strong></td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font> 
									</strong> 
									Campos obrigat&oacute;rios
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Desfazer" align="left"
								onclick="window.location.href='/gsan/exibirInserirMotivoDeNaoExecucaoDoServicoAction.do?menu=sim'">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right" height="24"><input type="button"
								name="Button" class="bottonRightCol" value="Adicionar"
								onClick="adicionarUnidadeRepavimentadora();" />
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="8%">
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td width="92%">
							<div align="center" class="style9">
								<strong>Unidade Repavimento</strong>
							</div>							
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoHelper" scope="session">
							<logic:iterate indexId="posicao"
								name="colecaoHelper" id="helper" >

								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
									cor = "#cbe5fe";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#FFFFFF";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td>
									<div align="center" class="style9"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;"
										onclick="redirecionarSubmit('exibirInserirMotivoDeNaoExecucaoDoServicoAction.do?remover=sim&posicao=<bean:write name="helper" property="id"/>');"></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="unidadeRepavimentadora" /></div>
									</td>									
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>				
				<td colspan="3" align="right" height="24"><input type="button"
					name="Button" class="bottonRightCol" value="Inserir"
					onClick="javascript:validarForm(document.forms[0])" />
				</td>
			</tr>
			</table>
				<p>&nbsp;</p>
			</tr>			
		</table>
		<tr>
			<td colspan="3">
				<%@ include file="/jsp/util/rodape.jsp"%>
			</td>
		</tr>
	</html:form>
</body>
</html:html>