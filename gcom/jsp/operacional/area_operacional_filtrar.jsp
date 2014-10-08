<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false"  formName="FiltrarAreaOperacionalActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
	
	
<script language="JavaScript">
  
    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
		
	function setaFocus(){
		var form = document.FiltrarAreaOperacionalActionForm;		
		form.descricao.focus();
	}
		

    function validarForm(form) {
	  if(validateFiltrarAreaOperacionalActionForm(form)){	     
		  submeterFormPadrao(form); 
		  }
   	  }	

	 function pesquisarColecao(tipo){
			var form = document.forms[0];
			if(tipo == 'subsistema'){
				form.idSubsistemaAbastecimento.value = "-1";	
				form.idDistritoOperacional.value = "-1";
			}else{
				form.idDistritoOperacional.value = "-1";
			}
			form.action = "exibirFiltrarAreaOperacionalAction.do";
			
			form.submit();	
	 }
		 
</script>

</head>

<body leftmargin="5" topmargin="5" 
		onload="javascript:setaFocus('${requestScope.nomeCampo}'); verificarChecado('${sessionScope.indicadorAtualizar}');">

<html:form action="/filtrarAreaOperacionalAction"
	name="FiltrarAreaOperacionalActionForm"
	type="gcom.gui.operacional.FiltrarAreaOperacionalActionForm"
	method="post"
	onsubmit="return validateFiltrarAreaOperacionalActionForm(this);">
	
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
					<td class="parabg">Filtrar Área Operacional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar a(s) Areas(s) Operacional (is), informe os dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" 
								onclick="javascript:verificarValorAtualizar();" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				
				<tr>
			  		<td  width="40%" class="style3"><strong>Descrição:</strong></td>
			  		<td  width="60%" colspan="2"><strong><b><span class="style2"> 
			  			<html:text property="descricao" size="33" maxlength="30" tabindex="1"/> </span></b></strong></td>
				</tr>				

				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"	tabindex="3"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio	property="tipoPesquisa" tabindex="4"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>				
				<tr>
					<td width="40%" class="style3"><strong>Sistema de Abastecimento:</strong></td>
					<td width="60%" colspan="2"><html:select
						property="idSistemaAbastecimento" tabindex="4" style="width:200px;"
						onchange="javascript:pesquisarColecao('subsistema');">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSistemaAbastecimento"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Subsistema de Abastecimento:</strong></td>
					<td width="60%" colspan="2"><html:select
						property="idSubsistemaAbastecimento"  style="width:200px;"
						onchange="javascript:pesquisarColecao('distrito');">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSubsitemaAbastecimento" property="id"
							labelProperty="descricao" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Distrito Operacional:</strong></td>
					<td width="60%" colspan="2"><html:select
						property="idDistritoOperacional"  style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoDistritoOperacional" property="id"
							labelProperty="descricao" />
					</html:select></td>	
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"/><strong>Ativo</strong>
						<html:radio	property="indicadorUso" tabindex="3" value="2"/><strong>Inativo</strong>
						<html:radio	property="indicadorUso" tabindex="4" value=""/><strong>Todos</strong>
					</td>
				</tr>				
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarAreaOperacionalAction.do?menu=sim'">
					</td>
					<td align="right"><INPUT type="button"
						onclick="validarForm(document.forms[0]);" class="bottonRightCol"
						value="Filtrar" tabindex="12" style="width: 70px;"></td>
				</tr>								

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>

