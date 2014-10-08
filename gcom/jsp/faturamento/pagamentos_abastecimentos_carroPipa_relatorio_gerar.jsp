<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="PagamentosAbastecimentosCarroPipaRelatorioActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(form){
	
		if(validatePagamentosAbastecimentosCarroPipaRelatorioActionForm(form)){
			toggleBox('demodiv', 1);
		}
	} 
	
	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirPagamentosAbastecimentosCarroPipaRelatorioAction.do';
	    form.submit();
  	}
  	
  	function limparForm(){
  		var form = document.forms[0];
  		
  		form.idFaturamentoGrupo.value = "-1";
  		form.mesAnoReferencia.value = "";  		
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		desabilitarCampos();  		 		
  	} 

  	function desabilitarCampos(){
			var form = document.forms[0];
			
			if(form.idFaturamentoGrupo.value != "-1"){
					form.gerenciaRegional.disabled = true;
					form.unidadeNegocio.disabled = true;
					form.gerenciaRegional.value = "-1";
					form.unidadeNegocio.value = "-1";
					form.gerenciaRegional.style.background = "#EFEFEF";					
					form.unidadeNegocio.style.background = "#EFEFEF";	 
			}else{
					form.gerenciaRegional.disabled = false;
					form.unidadeNegocio.disabled = false;
					form.gerenciaRegional.style.background = "";					
					form.unidadeNegocio.style.background = "";	
			}
  	  	}  	 	
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); desabilitarCampos();">

<html:form action="/gerarRelatorioPagamentosAbastecimentosCarroPipaAction"
	name="PagamentosAbastecimentosCarroPipaRelatorioActionForm"
	type="gcom.gui.faturamento.PagamentosAbastecimentosCarroPipaRelatorioActionForm"
	method="post">

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
					<td class="parabg">Gerar Relatório de Pagamentos dos Abastecimentos de Carros-Pipa</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório, informe os dados abaixo:</td>
				</tr>
				<tr><td>&nbsp;</td></tr>				
				<tr>
					<td><strong>Referência do Faturamento:<font color="#FF0000">*</font></strong></td>
					<td align="left"><html:text property="mesAnoReferencia" size="10"
						tabindex="2" maxlength="7"
						onkeyup="mascaraAnoMes(this, event);somente_numero(this);"
						onkeypress="return isCampoNumerico(event);" />&nbsp;MM/AAAA</td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td width="150"><strong>Grupo de Faturamento:</strong></td>
					<td align="left"><html:select property="idFaturamentoGrupo" tabindex="1"
					onchange="javascript: desabilitarCampos();">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="faturamentosGrupos" property="id"
							labelProperty="descricao" /></html:select>
					</td>
				</tr>
				<tr><td></td></tr>				
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td><html:select property="gerenciaRegional"
						name="PagamentosAbastecimentosCarroPipaRelatorioActionForm"
						tabindex="4" onchange="javascript:reloadForm();">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:iterate name="colecaoGerenciaRegional"
							id="colecaoGerenciaRegional">
							<html:option value="${colecaoGerenciaRegional.id}">
								<bean:write name="colecaoGerenciaRegional"
									property="nomeAbreviado" /> 
					           - <bean:write name="colecaoGerenciaRegional"
									property="nome" />
							</html:option>
						</logic:iterate>
					</html:select></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td><html:select property="unidadeNegocio"
						name="PagamentosAbastecimentosCarroPipaRelatorioActionForm"
						tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:present name="colecaoUnidadeNegocio">
							<logic:iterate name="colecaoUnidadeNegocio"
								id="colecaoUnidadeNegocios">
								<html:option value="${colecaoUnidadeNegocios.id}">
									<bean:write name="colecaoUnidadeNegocios"
										property="nomeAbreviado" />
									<bean:write name="colecaoUnidadeNegocios" property="nome" />
								</html:option>
							</logic:iterate>
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos
					Obrigat&oacute;rios</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Cancelar"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" />
					</td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Limpar" onclick="limparForm();" />
					</td>
					<td width="500" align="right">&nbsp;</td>
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol" value="Gerar"
							onclick="validarForm(document.forms[0]);" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>	
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPagamentosAbastecimentosCarroPipaAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
