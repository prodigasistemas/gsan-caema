<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


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
	formName="AtualizarTipoDebitoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
function validaIndicadores(){
	
			var form = document.AtualizarTipoDebitoActionForm;
    			
    			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Gera��o do D�bito Autom�tica');
    			}
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Gera��o do D�bito em Conta');
    			}
    			
    			if(form.indicadorDebitoCartaoCredito[0].checked == false &&	form.indicadorDebitoCartaoCredito[1].checked == false){

    			alert('Indicador de Cart�o de Cr�dito');

    			}
    			
    			if(form.indicadorJurosParCliente[0].checked == false &&	form.indicadorJurosParCliente[1].checked == false){

    			alert('Juros de Contrato de Parcelamento por Cliente');

    			}
	}
function validarForm(form){
	if(validateAtualizarTipoDebitoActionForm(form)){
			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Gera��o do D�bito Autom�tica');
    			}else
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Gera��o do D�bito em Conta');
    			}else

    			if(form.indicadorDebitoCartaoCredito[0].checked == false &&	form.indicadorDebitoCartaoCredito[1].checked == false){

    			alert('Indicador de Cart�o de Cr�dito');

    			}else{
			//form.descricao.value = form.descricao.value.toUpperCase();
			//form.descricaoAbreviada.value = form.descricaoAbreviada.value.toUpperCase();
			//form.caminhoMenu.value = form.caminhoMenu.value.toUpperCase();
    		 submeterFormPadrao(form)
    		}
		}
	
		/*if(validateAtualizarTipoDebitoActionForm(form)){
			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Gera��o do D�bito Autom�tica');
    			}else
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Gera��o do D�bito em Conta');
    			}else{
    		form.submit();
    		}
		}*/
}
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/atualizarTipoDebitoAction.do" method="post"
	name="AtualizarTipoDebitoActionForm"
	type="gcom.gui.faturamento.debito.AtualizarTipoDebitoActionForm"
	onsubmit="return validateAtualizarTipoDebitoActionForm(this);">
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
			</table>

			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Tipo de D�bito</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o tipo de d�bito, informe os dados
					gerais abaixo:</td>
				</tr>
				<tr>
					<td><strong>C�digo:</strong></td>
					<td><html:hidden property="idTipoDebito" /> <bean:write
						name="AtualizarTipoDebitoActionForm" property="codigo" /></td>
				</tr>
				<tr>
					<td><strong>Descri��o do Tipo de D�bito:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="descricao" size="50"
						maxlength="30"  /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Descri��o do Tipo de D�bito Abreviada:</strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="28"
						maxlength="18" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Tipo do Lan�amento do Item Cont�bil:<font
						color="#FF0000">*</font></strong></td>
					<td><html:select property="lancamentoItemContabil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLancamentoItemContabil"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Tipo de Financiamento:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="financiamentoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFinanciamentoTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Indicador de Gera��o do D�bito Autom�tica:<font
						color="#FF0000">*</font></strong></td>
					<td><strong><html:radio property="indicadorGeracaoDebitoAutomatica"
						value="1" />Sim <html:radio
						property="indicadorGeracaoDebitoAutomatica" value="2" />
					N&atilde;o</strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Gera��o do D�bito em Conta:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoDebitoConta"
						value="1" /> <strong>Sim <html:radio
						property="indicadorGeracaoDebitoConta" value="2" /> N&atilde;o</strong>
					</strong></td>
				</tr>
				<tr>
					<td><strong>Valor Limite do D�bito:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="valorLimiteDebito" size="17"
						maxlength="17"
						onkeyup="javascript:formataValorMonetario(this,15);"
						style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Valor Sugerido:</strong></td>
					<td><strong> <html:text property="valorSugerido" size="17"
						maxlength="17"
						onkeyup="javascript:formataValorMonetario(this,15);"
						style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>

					<td><strong>Indicador de Cart�o de Cr�dito:<font

						color="#FF0000">*</font></strong></td>

					<td><strong> <html:radio property="indicadorDebitoCartaoCredito"

						value="1" /> <strong>Sim <html:radio

						property="indicadorDebitoCartaoCredito" value="2" /> N&atilde;o</strong>

					</strong></td>

				</tr>
				
				<tr>

					<td><strong>Juros de Contrato de Parcelamento por Cliente:<font color="#FF0000">*</font></strong></td>

					<td><strong> <html:radio property="indicadorJurosParCliente" value="1" />

					<strong>Sim <html:radio property="indicadorJurosParCliente" value="2"  />

					N&atilde;o</strong> </strong></td>

				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorUso" value="1" /> <strong>Ativo
					<html:radio property="indicadorUso" value="2" /> Inativo</strong>
					</strong></td>
				</tr>

				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><logic:present name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterTipoDebitoAction.do'">
					</logic:present> <logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarTipoDebitoAction.do'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarTipoDebitoAction.do?desfazer=S&idTipoDebito=${requestScope.idTipoDebito}"/>'">

					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Atualizar" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>
				</tr>
				<tr>
				<td> &nbsp;</td>
				</tr>
				<tr>
				<td> &nbsp;</td>
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

