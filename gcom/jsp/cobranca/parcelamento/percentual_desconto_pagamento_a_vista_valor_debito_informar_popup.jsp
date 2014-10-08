<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.cobranca.parcelamento.DescontoValorDebitoPeriodo" %>
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
		form.action = "exibirInformarPercentualDescPagAVistaDebitoPopupAction.do?limpar=S";
		form.submit();
	}
	
	function fechar(){
		window.close();
	}
	
	function adicionar(form){
		if(validarForm(form)){
			form.action = "exibirInformarPercentualDescPagAVistaDebitoPopupAction.do?adicionar=S";
			form.submit();
		}
	}
	
	function inserir(form){
		form.action = "informarPercentualDescPagAVistaDebitoPopupAction.do";
		form.submit();
	}
	
	function validarForm(form){
		var msg = '';
		if(form.valorMaximoDebito.value == ''){
			msg = msg +  'Informe o Valor Máximo \n';
		}
		
		if(form.percentualDesconto.value == ''){
			msg = msg +  'Informe o Percentual de Desconto \n';
		}
		
		if(form.quantidadeMeses.value == ''){
			msg = msg +  'Informe a Quantidade de Meses \n';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}

</script>

</head>


<logic:present name="reloadPage">
	
	<logic:equal name="reloadPage" value="INSERIRPERFIL">
	
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirInserirPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="ATUALIZARPERFIL">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirAtualizarPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="FECHARINSERIR">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirInserirPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="FECHARATUALIZAR">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirAtualizarPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	
</logic:present>

<logic:notPresent name="reloadPage">
<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(500, 450);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/exibirInformarPercentualDescPagAVistaDebitoPopupAction"
	name="InformarPercentualDescPagAVistaDebitoPopupActionForm"
	type="gcom.gui.cobranca.parcelamento.InformarPercentualDescPagAVistaDebitoPopupActionForm"
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
					<td class="parabg">Percentual Desconto Pagamento à Vista por Valor de Débito</td>

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
					<p>Preencha os campos para inserir um Percentual Desconto para Pagamento à Vista por Valor de Débito:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Valor Máximo:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="valorMaximoDebito" size="14"
							tabindex="1" 
							onkeyup="formataValorMonetario(this, 14)" 
							style="text-align:right;" 
							maxlength="14"/>
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
					<td width="30%"><strong> Quantidade de Meses:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="quantidadeMeses" size="14"
							tabindex="5"  
							style="text-align:right;" 
							maxlength="5" />
					</td>
				</tr>
				
				<tr>
					<td width="30%" colspan="2"><strong> Lista de Percentual de Desconto:</strong></td>
					<td>
						<div align="right"><input name="botaoAdicionar" type="button"
						class="bottonRightCol" value="Adicionar"
						onclick="adicionar(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				
				<tr >
					<td colspan= "9">
					<div style="width: 100%; height: 80; overflow: auto;">
						<table width="100%" border="0" bgcolor="#90c7fc">
							<tr bgcolor="#90c7fc" height="18">
								<td align="center" width="6%"><strong>Remover</strong></td>
								<td width="15%" align="center"><strong>Valor Máximo</strong></td>
								<td width="15%" align="center"><strong>Percentual de Desconto</strong></td>
								<td width="15%" align="center"><strong>Quantidade de Meses</strong></td>
							</tr>									
							<logic:present name="collectionDescontoValorDebitoPeriodo">
								<%int cont = 1;%>
								<logic:iterate name="collectionDescontoValorDebitoPeriodo" 
								id="descontoValorDebitoPeriodo"
								type="DescontoValorDebitoPeriodo">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<td width="6%">
												<logic:equal name="readOnly" value="true">
													<div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif" />
									            </font></div>
												</logic:equal>
												<logic:notEqual name="readOnly" value="true">
													<div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
		 								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('exibirInformarPercentualDescPagAVistaDebitoPopupAction.do?remover=S&valorMaximoDebito=<bean:write name="descontoValorDebitoPeriodo" property="valorMaximoDebito"/>');}" />
										            </font></div>
												</logic:notEqual>
									       </td>
											
											<td width="15%" align="center">
												<div>${descontoValorDebitoPeriodo.valorMaximoDebito} &nbsp;</div>
											</td>
											
											<td width="15%" align="center">
												<div>${descontoValorDebitoPeriodo.percentualDesconto} &nbsp;</div>
											</td>
											
											<td width="15%" align="center">
												<div>${descontoValorDebitoPeriodo.qtdeMeses} &nbsp;</div>
											</td>

										</tr>
								</logic:iterate>
							</logic:present>

						</table>
						</div>
					</td>
					</tr>

			</table>
			
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Fechar" onClick="fechar();" tabindex="6"> &nbsp;
					<input name="button" type="button" class="bottonRightCol"
						value="Limpar" onclick="limpar();" tabindex="7" ></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="fechar();" tabindex="8"></div>
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