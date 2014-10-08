<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarMotivoNaoAutorizacaoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
function validarForm(form){

	if(validateAtualizarMotivoNaoAutorizacaoActionForm(form)){
		submeterFormPadrao(form);
	}
}

function validateCaracterEspecial(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oCaracterEspecial = new caracteresespeciais();
    for (x in oCaracterEspecial) {
        if ((form[oCaracterEspecial[x][0]].type == 'text' ||
             form[oCaracterEspecial[x][0]].type == 'textarea' ||
             form[oCaracterEspecial[x][0]].type == 'password') &&
            (form[oCaracterEspecial[x][0]].value.length > 0)) {
            if (validacaoCaracterEspecial(form[oCaracterEspecial[x][0]].value)) {
                if (i == 0) {
                    focusField = form[oCaracterEspecial[x][0]];
                }
                fields[i++] = oCaracterEspecial[x][1];
                bValid = false;
            }
        }
    }
    if (fields.length > 0) {
        focusField.focus();
        alert(fields.join('\n'));
    }
    return bValid;
}

function validacaoCaracterEspecial(c){
var achou = false;

var indesejaveis = "~{}^%$[]@|`\\<¨\#?!;*>\"\'+()&,.:;/-_°ºª´=¬¢£³²¹§";

for (var i=0; i<indesejaveis.length; i++) {
if ((c.indexOf(indesejaveis.charAt(i))) != -1 ){
	achou = true;
}
	}

return achou;
}

function limparForm(form) {
	form.descricao.value = "";
}
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');"
	onKeyDown="if (event.keyCode == '13'){ return false }"
	onSubmit="return validacaoCaracterEspecial()">

	<html:form action="/atualizarMotivoNaoAutorizacaoAction" method="post"
		name="AtualizarMotivoNaoAutorizacaoActionForm"
		type="gcom.gui.seguranca.AtualizarMotivoNaoAutorizacaoActionForm">

		<INPUT TYPE="hidden" name="atualizarMotivoNaoAutorizacao">
		<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="130" valign="top" class="leftcoltext">
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
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
							<td class="parabg">Atualizar Motivo de Não Autorização</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">Para atualizar o motivo de não autorização, informe os dados abaixo:</td>
								
						<tr>
							<td HEIGHT="30"><strong>Descrição: <font color="#FF0000">*</font></strong></td>
							<td colspan="2"><html:text property="descricao" maxlength="50"
								size="40"/>
							</td>
						</tr>

						<tr>
							<td><strong>Indicador de uso:<font color="#FF0000">*</font></strong></td>
							<td><html:radio property="indicadorUso" value="1"
									tabindex="11" /><strong>Ativo <html:radio
										property="indicadorUso" value="2" tabindex="12" />Inativo
							</strong></td>
						</tr>


						<tr>
							<td><strong> <font color="#FF0000"></font></strong></td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font></strong> Campos
									obrigat&oacute;rios
								</div>
							</td>
						</tr>

						<tr>
							<td colspan="2" width="40%" align="left"><input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Voltar"
								onClick="window.history.go(-1)"> <input type="button"
								name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onClick="(document.forms[0]).reset()"> <input
								type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><input type="button" name="botaoAtualizar"
								class="bottonRightCol" value="Atualizar"
								onClick="javascript:validarForm(document.forms[0]);"></td>
						</tr>
					</table>


				</td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>

