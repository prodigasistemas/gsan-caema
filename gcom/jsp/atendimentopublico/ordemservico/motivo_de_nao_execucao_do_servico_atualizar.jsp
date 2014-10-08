<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>regras_validator.js"></script>

<script language="JavaScript">

    function validarForm() {
    	var form = document.forms[0];
	  	form.action = 'atualizarMotivoDeNaoExecucaoDoServicoAction.do';	
    	submeterFormPadrao(form);
	 	
  	}
    
    function adicionarUnidadeRepavimentadora() {
		var form = document.forms[0];

		if (form.unidadeRepavimentadora.value == '' || form.unidadeRepavimentadora.value == '-1') {
			alert('Informe a Unidade Repavimentadora');
			return;
		} else {
			form.action = 'exibirAtualizarMotivoDeNaoExecucaoDoServicoAction.do?adicionar=sim';
			form.submit();
		}
	}
	
</script>
</head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarMotivoDeNaoExecucaoDoServicoActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/atualizarMotivoDeNaoExecucaoDoServicoAction.do"
		name="AtualizarMotivoDeNaoExecucaoDoServicoActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.AtualizarMotivoDeNaoExecucaoDoServicoActionForm"
		method="post"
		onsubmit="return validateAtualizarMotivoDeNaoExecucaoDoServicoActionForm(this);">

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
					</div></td>
					
				<td valign="top" class="centercoltext">
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Atualizar Motivo de não execução do Serviço de Repavimentação</td>
							<td width="11" valign="top"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para atualizar um motivo de não execução do serviço, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td width="162"><strong>Descrição:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td>
								<html:text property="descricao" size="45" maxlength="50" tabindex="3" />
							</td>
						</tr>
						<tr>
							<td><strong>Descrição Abreviada:</strong>
							</td>
							<td>
								<html:text property="descricaoAbreviada" size="10" maxlength="10" tabindex="4" />
							</td>
						</tr>					
						<tr>
							<td><strong>Unidade Repavimentadora:<font color="#FF0000">*</font></strong></td>
							<td>
								<html:select property="unidadeRepavimentadora" tabindex="5" style="width:200px;" >
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoUnidadeRepavimentadora" labelProperty="descricao" property="id" />
								</html:select><font size="1">&nbsp; </font>
							</td>
						</tr>						
						<tr>
							<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
							<td>
								<html:radio property="indicadorUso" value="1" tabindex="6" /><strong>Ativo 
								<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
							</td>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
							<td width="500" colspan="2">
								<div align="center">
									<font color="#FF0000">*</font> Campos obrigatórios
								</div>
							</td>
						</tr>
					</table>
					<table width="100%">
						<tr>
							<td width="40%" align="left"><input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Voltar"
								onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';">

								<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Desfazer"
								onclick="window.location.href='/gsan/exibirAtualizarMotivoDeNaoExecucaoDoServicoAction.do?desfazer=S&reloadPage=1&idServico=<bean:write name="AtualizarMotivoDeNaoExecucaoDoServicoActionForm" property="id" />';">
								
								<input type="button" name="ButtonCancelar"
								class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right" height="24"><input type="button"
								name="Button" class="bottonRightCol" value="Adicionar"
								onClick="adicionarUnidadeRepavimentadora();" />
							</td>
						</tr>
						<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="8%">
							<div align="center" class="style9">
								<strong>Remover</strong>
							</div>
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
										onclick="redirecionarSubmit('exibirAtualizarMotivoDeNaoExecucaoDoServicoAction.do?remover=sim&posicao=<bean:write name="helper" property="id"/>');"></div>
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
					<td colspan="5" align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar"
						onclick="validarForm();" />
					</td>
				</tr>
			</table>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</html:html>