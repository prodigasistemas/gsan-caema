<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util" %>
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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirPrestacoesParcelamentoPerfilActionForm" />

<script language="JavaScript">
	function limpar(){
		form = document.forms[0];
		form.percentualEntrada.value = '';
		form.percentualDesconto.value = '';
		form.quantidadeParcelas.value = '';
		form.valorMinimoParcela.value = '';
		form.quantidadeMeses.value = '';
	}
	
	function voltar(){
		form = document.forms[0];
		form.action ="exibirInserirPrestacoesParcelamentoPerfilAction.do";
		submeterFormPadrao(form);
	}

	function validarForm(form){
		var msg = '';
		if(form.percentualEntrada.value == ''){
			msg = msg +  'Informe o Percentual de Entrada \n';
		}
		
		if(form.percentualDesconto.value == ''){
			msg = msg +  'Informe o Percentual de Desconto \n';
		}
		
		if(form.quantidadeParcelas.value == ''){
			msg = msg +  'Informe a Quantidade de Parcelas \n';
		}
		
		if(form.valorMinimoParcela.value == ''){
			msg = msg +  'Informe o Valor Mínimo da Parcela \n';
		}
		
		if(form.quantidadeMeses.value == ''){
			msg = msg +  'Informe a Quantidade de Meses \n';
		}
		
		if( msg != '' ){
			alert(msg);
		}else{
			form.submit();
		}
	}

</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(600, 350);javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/adicionarPercentualDescontoPopupAction"
	name="InserirPrestacoesParcelamentoPerfilActionForm"
	type="gcom.gui.cobranca.parcelamento.InserirPrestacoesParcelamentoPerfilActionForm"
	method="post">

	<table width="450" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Percentual de Desconto por Percentual de Entrada</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Preencha os campos para inserir um percentual de desconto:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Percentual de Entrada:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="percentualEntrada" size="14"
							tabindex="1" 
							onkeyup="formataValorMonetario(this, 14)" 
							style="text-align:right;" 
							maxlength="6" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Percentual de Desconto:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="percentualDesconto" size="14"
							tabindex="2" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;"
							maxlength="6" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="quantidadeParcelas" size="14"
							tabindex="3"
							style="text-align:right;"
							maxlength="5" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Valor Mínimo da Parcela:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="valorMinimoParcela" size="14"
							tabindex="4" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;"
							maxlength="6" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Quantidade de Meses:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="quantidadeMeses" size="14"
							tabindex="5"  
							style="text-align:right;"
							maxlength="5" />
					</td>
				</tr>

			</table>
			
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Voltar" onClick="voltar();" tabindex="6"> &nbsp;
					<input name="button" type="button" class="bottonRightCol"
						value="Limpar" onclick="limpar();" tabindex="7" ></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>
	</html:form>
</body>
</html:html>