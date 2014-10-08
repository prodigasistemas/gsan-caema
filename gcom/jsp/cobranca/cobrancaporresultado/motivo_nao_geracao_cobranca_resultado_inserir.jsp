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
	formName="InserirMotivoNaoGeracaoCobrancaResultadoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

function validateInserirMotivoNaoGeracaoCobrancaResultadoAction(){
		var form = document.forms[0];
		retorno = true;
		if(form.descricao.value.length > 100){
			alert("Campo Descrição excedeu limite de 100 caracteres");
			retorno = false;
		}
		if(retorno){
			form.submit();
		}

	}

    

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
	<html:form action="/inserirMotivoNaoGeracaoCobrancaResultadoAction.do"
		name="InserirMotivoNaoGeracaoCobrancaResultadoActionForm"
		type="gcom.gui.cobranca.cobrancaporresultado.InserirMotivoNaoGeracaoCobrancaResultadoActionForm"
		method="post"
		onsubmit="return false;">

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
							<td class="parabg">Inserir Motivo de Não Geração Cobrança
								por Resultado</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" />
							</td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->

					<table width="100%" border="0">
						<tr>
                             <td colspan="2">Para inserir o(s) motivo(s) de não geração cobrança por resultado, informe os dados abaixo:</td>
                       </tr>

						<tr>
							<td width="30%"><strong>Descrição:<font
									color="#ff0000">*</font> </strong>
							</td>
							<td width="70%">
								<!--<html:textarea cols="37" property="descricao" rows="4" onchange="limitarTextArea(this)"/>-->
								<html:textarea property="descricao" cols="40" rows="4" style="text-transform: uppercase"
									onkeyup="limitTextArea(document.forms[0].descricao, 100, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>
								<strong><span id="utilizado">0</span>/<span id="limite">100</span>
							</strong></td>
						</tr>

						<tr>
							<td width="30%"><strong>Descrição Abreviada:<font
									color="#ff0000"></font> </strong></td>
							<td width="70%"><html:text maxlength="10"
									property="descricaoAbreviada" size="9" />
							</td>
						</tr>


						<tr>
							<td><strong>Tipo do Motivo:<font color="#ff0000">*</font></strong></td>
							<td><html:radio property="tipoMotivo" value="1" /> <strong>Imóvel</strong>
								<html:radio property="tipoMotivo" value="2" /> <strong>Conta</strong>
								<html:radio property="tipoMotivo" value="3" /> <strong>Pagamento</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Indicador de Uso:<font color="#FF0000">*</font>	</strong></td>
							<td><html:radio property="indicadorUso" value="<%=ConstantesSistema.SIM+"" %>" /> <strong>Ativo</strong>
								<html:radio property="indicadorUso" value="<%=ConstantesSistema.NAO+"" %>" /> <strong>Inativo
							</strong></td>
						</tr>	
						<tr>
							<td>&nbsp;</td>
							<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
						</tr>
						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirInserirMotivoNaoGeracaoCobrancaResultadoAction.do?menu=sim"/>'">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td width="53" height="24" align="right"><input
								type="button" name="Button2" class="bottonRightCol"
								value="Inserir" onClick="javascript:validateInserirMotivoNaoGeracaoCobrancaResultadoAction();" />
							</td>
							<td width="12">&nbsp;</td>
						</tr>
					</table>
					<p>&nbsp;</p>
			</tr>
		</table>
		<tr>
			<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>

	</html:form>

</body>
</html:html>
