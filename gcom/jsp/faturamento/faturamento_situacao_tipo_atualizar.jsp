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
	formName="AtualizarFaturamentoSituacaoTipoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
 

	
	if(validateAtualizarFaturamentoSituacaoTipoActionForm(formulario)){
 		
 		submeterFormPadrao(formulario);
	}
	
 }

function limparForm() {
		var form = document.AtualizarFaturamentoSituacaoTipoActionForm;
		form.codigo.value = "";
		form.descricao.value = "";
		
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarFaturamentoSituacaoTipoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerFaturamentoSituacaoTipo">
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
					<td class="parabg">Atualizar Tipo de Situa��o de Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar tipo da situa��o de faturamento, informe os
					dados abaixo:</td>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="codigo" /> 
					<bean:write	name="AtualizarFaturamentoSituacaoTipoActionForm" property="codigo" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Descri��o: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="20" maxlength="20" /> </span></td>
				</tr>

				<tr>
					<td><strong>Paralisa Faturamento?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorParalisacaoFaturamento" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorParalisacaoFaturamento" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Paralisa Leitura?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorParalisacaoLeitura" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorParalisacaoLeitura" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Valido para �gua?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorValidoAgua" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorValidoAgua" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Valido para Esgoto?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorValidoEsgoto" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorValidoEsgoto" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Informar Consumo Fixo?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorInformarConsumo" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorInformarConsumo" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Informar Volume Fixo?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorInformarVolume" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorInformarVolume" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Faturar "Tarifa Social"?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorCobrarConsumoMinimo" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorCobrarConsumoMinimo" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Sem Leitura:<font color="FF0000">*</font></strong></td>
					<td><html:select property="leituraAnormalidadeConsumoSemLeitura">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoSemLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Com Leitura:<font color="FF0000">*</font></strong></td>
					<td><html:select property="leituraAnormalidadeConsumoComLeitura">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoComLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
			
				<tr>
					<td><strong>Anormalidade de Leitura Faturar Sem Leitura:<font color="FF0000">*</font></strong></td>
					<td><html:select property="leituraAnormalidadeLeituraSemLeitura">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeLeituraSemLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Anormalidade de Leitura Faturar Com Leitura:<font color="FF0000">*</font></strong></td>
					<td><html:select property="leituraAnormalidadeLeituraComLeitura">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeLeituraComLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<!-- RM 5981 
					Desconsiderar o Alto Consumo e o Estouro de Consumo para Localidades pre-definidas
					Amelia Pessoa 
				-->
				<tr>
					<td><strong>Desconsiderar Estouro de Consumo e Alto Consumo?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorDesconsiderarEstouroAltoConsumo" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorDesconsiderarEstouroAltoConsumo" tabindex="3" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<!-- Fim RM 5981 -->
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="3" value="2" ><strong>Inativo</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
				</table>
			
			
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

