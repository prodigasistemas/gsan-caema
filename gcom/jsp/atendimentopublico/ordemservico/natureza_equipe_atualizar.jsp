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

    function validarForm(form) {
    	if(validateAtualizarNaturezaEquipeActionForm(form)){
			botaoAvancarTelaEspera('/gsan/atualizarNaturezaEquipeAction.do');
		}
	}
    
</script>
</head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarNaturezaEquipeActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
	<html:form action="/atualizarNaturezaEquipeAction.do"
		name="AtualizarNaturezaEquipeActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.AtualizarNaturezaEquipeActionForm"
		method="post"
		onsubmit="return validateAtualizarNaturezaEquipeActionForm(this);">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>
		
		<html:hidden property="id" />

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
							<td class="parabg">Atualizar Natureza de Equipe</td>
							<td width="11" valign="top"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para atualizar a natureza de equipe, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td width="162"><strong>Descrição:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td>
								<html:text property="descricao" size="50" maxlength="50" tabindex="3" />
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
							<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
							<td>
								<html:radio property="indicadorUso" value="1" tabindex="6" /><strong>Ativo 
								<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
							</td>
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
							<td width="60%" align="left"><input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Voltar"
								onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';">

								<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Desfazer"
								onclick="window.location.href='/gsan/exibirAtualizarNaturezaEquipeAction.do?desfazer=S&reloadPage=1&idNatureza=<bean:write name="AtualizarNaturezaEquipeActionForm" property="id" />';">
								
								<input type="button" name="ButtonCancelar"
								class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Atualizar"
								onclick="validarForm(document.forms[0]);" />
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