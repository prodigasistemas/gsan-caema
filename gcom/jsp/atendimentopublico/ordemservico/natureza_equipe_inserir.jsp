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
	formName="InserirNaturezaEquipeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	
	function validarForm(form) {
		
		if(validateInserirNaturezaEquipeActionForm(form)){
			botaoAvancarTelaEspera('/gsan/inserirNaturezaEquipeAction.do');
		}
	}

	function limparForm(form) {

		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
	
	<html:form action="/inserirNaturezaEquipeAction" method="post"
		name="InserirNaturezaEquipeActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.InserirNaturezaEquipeActionForm"
		onsubmit="return validateInserirNaturezaEquipeActionForm(this);">
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
							<td class="parabg">Inserir Natureza de Equipe</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para inserir uma natureza de equipe, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td HEIGHT="30" width="30%">
								<strong>Descri&ccedil;&atilde;o:
									<font color="#FF0000">*</font> 
								</strong>
							</td>
							<td colspan="2"><html:text property="descricao"
									maxlength="50" size="50" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Descri&ccedil;&atilde;o Abreviada:</strong>
							</td>
							<td>
								<strong> 
									<html:text property="descricaoAbreviada" size="10" maxlength="10" /> 
								</strong>
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
							<td colspan="2" align="left" ><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="javascript:limparForm(document.forms[0]);">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right" height="24"><input type="button"
								name="Button" class="bottonRightCol" value="Inserir"
								onClick="javascript:validarForm(document.forms[0])" />
							</td>
						</tr>
					</table>
				</td>
			</tr>			
		</table>
		<tr>
			<td colspan="3">
				<%@ include file="/jsp/util/rodape.jsp"%>
			</td>
		</tr>
	</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>