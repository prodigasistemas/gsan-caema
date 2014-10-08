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
	formName="InserirSubsistemaAbastecimentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">

$(document).ready(function(){
	
	   $('[@name=idSistemaAbastecimentoPrincipal]').change(function() {

		   $("[@name=idSistemaAbastecimentoSecundario] option[value='"+ $('[@name=idSistemaAbastecimentoPrincipal]').val() + "']").attr('selected', false );
			
		   var theForm = $("form[name=InserirSubsistemaAbastecimentoActionForm]");
		   var params = theForm.serialize();
		   var actionURL = 'exibirInserirSubsistemaAbastecimentoAction.do?action=atualizarListaAbastecimentoSecundario'
		   $.ajax({
			    type:"POST",
			    url:actionURL,
			    data:params,
			    success:function(data, textStatus, XMLHttpRequest){
			    	var obj = JSON && JSON.parse(data) || $.parseJSON(data);
			    	
					$('[@name=idSistemaAbastecimentoSecundario]').get(0).options.length = 0;
					$('[@name=idSistemaAbastecimentoSecundario]').get(0).options[0] = new Option("", "-1"); 
					
					$.each(obj, function(index, item) {
						$('[@name=idSistemaAbastecimentoSecundario]').get(0).options[$('[@name=idSistemaAbastecimentoSecundario]')
					                                            					    .get(0).options.length] = new Option(item.descricao, item.id);
					});
			    	
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			        alert(XMLHttpRequest.responseText);
			    }
			});	

		   
	   });   	
	});


	function validarForm(){
		var form = document.forms[0];
		if (validateInserirSubsistemaAbastecimentoActionForm(form)){
			botaoAvancarTelaEspera('/gsan/inserirSubsistemaAbastecimentoAction.do');
		}	
	}
	
	function limparForm() {
		var form = document.InserirSubsistemaAbastecimentoActionForm;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.idSistemaAbastecimentoPrincipal.value = "-1";
		form.idSistemaAbastecimentoSecundario.value = "-1";
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">

<div id="formDiv">
<html:form action="/inserirSubsistemaAbastecimentoAction.do"
	name="InserirSubsistemaAbastecimentoActionForm"
	type="gcom.gui.operacional.InserirSubsistemaAbastecimentoActionForm"
	method="post"
	onsubmit="return validateInserirSubsistemaAbastecimentoActionForm(this);">

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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Subsistema de Abastecimento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar um subsistema de abastecimento, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="20"
						size="22" />
						<br>
					</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Descrição Abreviada: <font color="#FF0000"></font></strong></td>
					<td colspan="2"><html:text property="descricaoAbreviada" maxlength="6"
						size="8" />
						<br>
					</td>
				</tr>
				<tr>
					<td><strong> Sistema de Abastecimento Principal:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idSistemaAbastecimentoPrincipal"
					style="width: 230px;">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSistemaAbastecimento" scope="session">
					<html:options collection="colecaoSistemaAbastecimento"
						labelProperty="descricao" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Sistemas de Abastecimento Secundários:</strong> <span class="style2">
					<strong> <font color="#FF0000"></font> </strong> </span></td>
					<td><strong> <html:select property="idSistemaAbastecimentoSecundario"
					style="width: 230px; height: 200px" multiple="true">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSASecundario" scope="session">
					<html:options collection="colecaoSASecundario"
						labelProperty="descricao" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="javascript:limparForm();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
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

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
