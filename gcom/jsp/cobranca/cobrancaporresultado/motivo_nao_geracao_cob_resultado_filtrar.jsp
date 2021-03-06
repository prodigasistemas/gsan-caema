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
	formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
 	function validarForm(){
 		var form = document.forms[0];
 		form.submit();	
 	}
</script>

</head>

<body>

	<html:form action="/filtrarMotivoNaoGeracaoCobrancaResultadoAction"
		name="FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm"
		type="gcom.gui.cobranca.cobrancaporresultado.FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm"
		method="post">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="123" valign="top" class="leftcoltext">
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
					</div></td>

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
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Filtrar Motivo N�o Gera��o Cobran�a por
								Resultado</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<table border="0" width="100%">
						<tr>
							<td><input type="checkbox" checked="checked"
								name="indicadorAtualizar" value="1" style="float: right;"><strong
								style="float: right;">Atualizar</strong>
							</td>
						</tr>
					</table>

					<table border="0" width="100%">
						<tr>
							<td colspan="2">Para filtrar o(s) motivo(s) de n�o gera��o
								cobran�a por resultado, informe os dados abaixo:</td>
						</tr>


						<tr>
							<td width="30%"><strong>C�digo:<font
									color="#ff0000"></font>
							</strong>
							</td>
							<td width="70%"><html:text maxlength="9" property="codigo"
									size="6" onkeypress="return isCampoNumerico(event);"
									onblur="somente_numero_zero_a_nove(this)" /></td>
						</tr>

						<tr>
							<td width="30%"><strong>Descri��o:<font
									color="#ff0000">*</font> </strong></td>
							<td width="70%">
								<!--<html:textarea cols="37" property="descricao" rows="4" onchange="limitarTextArea(this)"/>-->
								<html:textarea property="descricao" cols="40" rows="4"
									style="text-transform: uppercase"
									onkeyup="limitTextArea(document.forms[0].descricao, 100, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>
								<strong><span id="utilizado">0</span>/<span id="limite">100</span>
							</strong>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td width="100%" align="left"><html:radio
									property="tipoDescricao" value="1" /> <strong>Iniciando
									pelo texto</strong> <html:radio property="tipoDescricao" value="2" /> <strong>Contendo
									o texto</strong></td>
						</tr>

						<tr>
							<td width="30%"><strong>Descri��o Abreviada:</strong>
							</td>
							<td width="70%"><html:text maxlength="10"
									property="descricaoAbreviada" size="9" /></td>
						</tr>

						<tr>
							<td><strong>Tipo do Motivo:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:radio property="tipoMotivo" value="1" /> <strong>Im�vel</strong>
								<html:radio property="tipoMotivo" value="2" /> <strong>Conta</strong>
								<html:radio property="tipoMotivo" value="3" /> <strong>Pagamento</strong>
								<html:radio property="tipoMotivo" value="4" /> <strong>Todos</strong>
							</td>
						<tr>
							<td><strong>Indicador de Uso:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:radio property="indicadorUso" value="1" /> <strong>Ativo</strong>
								<html:radio property="indicadorUso" value="2" /> <strong>Inativo
							</strong> <html:radio property="indicadorUso" value="3" /> <strong>Todos</strong>
							</td>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font>
							</strong>
							</td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font>
									</strong> Campos obrigat&oacute;rios
								</div></td>
						</tr>

						<tr>
							<td colspan="2"><font color="#FF0000"> <input
									type="button" name="ButtonReset" class="bottonRightCol"
									value="Limpar"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarMotivoNaoGeracaoCobrancaResultadoAction.do?menu=sim'"">
									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font>
							</td>
							<td align="right"><input type="button" name="ButtonReset"
								class="bottonRightCol" value="Filtrar" onClick="validarForm()">
							</td>
						</tr>

					</table></td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>
