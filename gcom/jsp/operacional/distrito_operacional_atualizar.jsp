<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarDistritoOperacionalActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery-1.11.1.js"></script>
</head>

<script language="JavaScript">

	$(document).ready(function(){

		atualizarCoresTabela();
		
		$('[name=sistemaAbastecimento]').change(function() {
		  var theForm = $("form[name=AtualizarDistritoOperacionalActionForm]");
		  var params = theForm.serialize();
		  var actionURL = 'exibirAtualizarDistritoOperacionalAction.do?action=atualizarListaSubsistemaAbastecimento'
			$.ajax({
				type:"POST",
				url:actionURL,
				data:params,
				success:function(data, textStatus, XMLHttpRequest){
					var obj = JSON && JSON.parse(data) || $.parseJSON(data);
					
					$('[name=subsistemaAbastecimento]').get(0).options.length = 0;
					$('[name=subsistemaAbastecimento]').get(0).options[0] = new Option("", "-1"); 

					$('[name=setorAbastecimento]').get(0).options.length = 0;
					$('[name=setorAbastecimento]').get(0).options[0] = new Option("", "-1"); 
					
					$.each(obj, function(index, item) {
						$('[name=subsistemaAbastecimento]').get(0).options[$('[name=subsistemaAbastecimento]')
					                                          		.get(0).options.length] = new Option(item.descricao, item.id);
					});	  	
			    }
			});	
		});


		$('[name=subsistemaAbastecimento]').change(function() {
		  var theForm = $("form[name=AtualizarDistritoOperacionalActionForm]");
		  var params = theForm.serialize();
		  var actionURL = 'exibirAtualizarDistritoOperacionalAction.do?action=atualizarListaSetorAbastecimento'
			$.ajax({
				type:"POST",
				url:actionURL,
				data:params,
				success:function(data, textStatus, XMLHttpRequest){
					var obj = JSON && JSON.parse(data) || $.parseJSON(data);
					
					$('[name=setorAbastecimento]').get(0).options.length = 0;
					$('[name=setorAbastecimento]').get(0).options[0] = new Option("", "-1"); 
					
					$.each(obj, function(index, item) {
						$('[name=setorAbastecimento]').get(0).options[$('[name=setorAbastecimento]')
					                                          		.get(0).options.length] = new Option(item.descricao, item.id);
					});	  	
			    }
			});	
		});
	});

	function atualizarCoresTabela(){
		$("#tabelaSetoresAbastecimento tr:odd").css("background-color", "#cbe5fe");
		$("#tabelaSetoresAbastecimento tr:even").css("background-color", "#ffffff");
	}


	function adicionarSetorAbastecimento(){

		  var msg = "";	
		  if($("[name=sistemaAbastecimento]").val() == -1){
			msg += "Informe Sistema de Abastecimento \n"
		  }	

		  if($("[name=subsistemaAbastecimento]").val() == -1){
			msg += "Informe Subsistema de Abastecimento \n"
		  }
		  
		  if($("[name=setorAbastecimento]").val() == -1){
			msg += "Informe Setor de Abastecimento \n"
		  }
		  
		  if(msg != ""){
			  alert(msg);
		  }	
		
		  else{
			  document.forms[0].action = "exibirAtualizarDistritoOperacionalAction.do?action=inserirSetorAbastecimento";
			  document.forms[0].submit();
		  }
	}


	function removerSetorAbastecimento(id){
		 var theForm = $("form[name=AtualizarDistritoOperacionalActionForm]");
		  var params = theForm.serialize();
		  var actionURL = 'exibirAtualizarDistritoOperacionalAction.do?action=removerSetorAbastecimento&idSetorARemover='+id
			$.ajax({
				type:"POST",
				url:actionURL,
				data:params,
				success:function(data, textStatus, XMLHttpRequest){
					var obj = JSON && JSON.parse(data) || $.parseJSON(data);
					$("#tr"+obj.id).remove();
					atualizarCoresTabela();
			    }
			});
	}

	
	/*function validarForm() {
		var form = document.forms[0];
		if($("input[name=idSetorAbastecimentoPrincipal]").is(":checked")){
			if(validateAtualizarDistritoOperacionalActionForm(form)){
				form.action = "/gsan/atualizarDistritoOperacionalAction.do"	     
				submeterFormPadrao(form); 
			}
		}
		else{
			alert("Selecione ao menos um setor de abastecimento principal");
		}	
	}*/

	function validarForm() {
		var form = document.forms[0];

		if(validateAtualizarDistritoOperacionalActionForm(form)){
			form.action = "/gsan/atualizarDistritoOperacionalAction.do"	     
			submeterFormPadrao(form); 
		}	
	}
       
	 
</script>


<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">

<html:form action="/atualizarDistritoOperacionalAction.do"
	name="AtualizarDistritoOperacionalActionForm"
	type="gcom.gui.opereracional.AtualizarDistritoOperacionalActionForm"
	method="post"
	onsubmit="return validateAtualizarDistritoOperacionalActionForm(this);">

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
			</div>
			</td>


			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Distrito Operacional</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o Distrito Operacional, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Descrição:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="descricao" size="35" maxlength="30" tabindex="1" /> </span></b></strong></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Descrição Abreviada:</strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="descricaoAbreviada" size="5" maxlength="3" tabindex="2" />
					</span></b></strong></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Sistema de Abastecimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select
						property="sistemaAbastecimento" tabindex="4" style="width:200px;height:20px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSistemaAbastecimentoAtualizar"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="40%" class="style3"><strong>Subsistema de Abastecimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select
						property="subsistemaAbastecimento" tabindex="3" style="width:200px;height:20px;">
						<html:option value="-1"> &nbsp; </html:option>
						<logic:present name="colecaoSubsistemaPrincipalAtualizar" scope="session">
							<html:options collection="colecaoSubsistemaPrincipalAtualizar" property="id"
								labelProperty="descricao" />
						</logic:present>	
					</html:select></td>
				</tr>
				
				<tr>
					<td width="40%" class="style3"><strong>Setor de Abastecimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2">
						<html:select
							property="setorAbastecimento" tabindex="3" style="width:200px;height:20px;">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoSetorAbastecimentoAtualizar" scope="session">
								<html:options collection="colecaoSetorAbastecimentoAtualizar" property="id"
									labelProperty="descricao" />
							</logic:present>	
						</html:select>
						<span style="padding-left:78px">
							<input type="button"
								name="ButtonAdicionarSetor" class="bottonRightCol" value="Adicionar"
								onClick="javascript:adicionarSetorAbastecimento()">
						</span>		
					</td>
				</tr>
				
				<tr> 
					<td height="24" colspan="3"><hr></td>
				</tr>
				
				<!-- TABELA DE SETORES DE ABASTECIMENTO FILTRADOS -->
					<tr>
						<td colspan="3">
							<table id=header width="100%" border="0" bgcolor="#90c7fc">
								
								<tr>
									<th bgcolor="#90c7fc" align="center" width="70">Remover</th>
									<th bgcolor="#90c7fc" align="center" width="70">Principal</th>
									<th bgcolor="#90c7fc" align="left">Setor Abastecimento</th>
								</tr>
							</table>
								<bean:define name="AtualizarDistritoOperacionalActionForm" 
											 property="setoresSelecionados" 
											 id="setoresSelecionados" />
							<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">						 
								<TABLE border="0" width="100%" bgcolor="#90c7fc" id="tabelaSetoresAbastecimento" cellpadding="1">
									<tr id="trTabelaClonar" style="display:none">
										<td width=68 align="center">
											<a href="" id="" class="linkClone" width="70">
												<img src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" />
											</a>
										</td>
										 <td align="center" width="70">
											<html:radio  property="idSetorAbastecimentoPrincipal" value="${setor.id}" />
										</td>
										<td>
										</td>						
									</tr>
									<c:if test="${AtualizarDistritoOperacionalActionForm.setoresSelecionados != null }">
										<logic:iterate name="setoresSelecionados" id="setor">
											<tr id="tr${setor.id}" style="display:block">
												<td width=68 align="center">
													<a href="javascript:removerSetorAbastecimento(${setor.id})" id="${setor.id}" class="linkClone" width="70">
														<img src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" />
													</a>
												</td>
												 <td align="center" width="70">
													<html:radio  property="idSetorAbastecimentoPrincipal" value="${setor.id}" />
												</td>
												<td>
													<bean:write name="setor" property="descricao"/>
												</td>						
											</tr>	
										</logic:iterate>
									</c:if>
								</table>
							</DIV>									 
						</td>
					</tr>
					<tr> 
			          <td><strong>Indicador de uso:<font color="#FF0000">*</font></strong></td>
			          <td colspan="2">
			          	<html:radio property="indicadorUso" value="1">Sim</html:radio>
			          	<html:radio property="indicadorUso" value="2">Não</html:radio>
			          </td>
			      	</tr>
				
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>

					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td width="40%" align="left">
					
						<input type="button"
							name="ButtonVoltar" class="bottonRightCol" value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarDistritoOperacionalAction.do?menu=sim';">						
						<input type="button"
							name="ButtonReset" class="bottonRightCol" value="Desfazer"
							onClick="javascript:window.location.href='/gsan/exibirAtualizarDistritoOperacionalAction.do?idDistritoOperacional=${AtualizarDistritoOperacionalActionForm.codigoDistritoOperacional}&menu=sim';">
						<input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar"
						onclick="javascript:validarForm();" tabindex="11" /></td>
				</tr>

			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>
