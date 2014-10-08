<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<%@ page import="gcom.operacional.AreaDistritoOperacional"%>
<%@ page import="gcom.gui.GcomAction"%>


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
	formName="InserirAreaOperacionalActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
</head>

<script language="JavaScript">
  
    function validarForm() {
    var form = document.forms[0];
	  if(validateInserirAreaOperacionalActionForm(form)){	     
		  submeterFormPadrao(form); 
		  }
   	  }
       
  	function limparForm() {
		var form = document.forms[0];
		form.descricao.value = "";
	    form.descricaoAbreviada.value = "";
        form.idSubsistemaAbastecimento.value = "-1";
	    form.idSistemaAbastecimento.value = "-1"; 
	    form.idDistritoOperacional.value = "-1";
		form.action = 'exibirInserirAreaOperacionalAction.do?menu=sim';
		form.submit(); 	
	 }

	 function pesquisarColecao(tipo){
		var form = document.forms[0];
		if(tipo == 'subsistema'){
			form.idSubsistemaAbastecimento.value = "-1";	
			form.idDistritoOperacional.value = "-1";
		}else{
			form.idDistritoOperacional.value = "-1";
		}
		form.action = "exibirInserirAreaOperacionalAction.do";
		
		form.submit();	
	 }

	function adicionarDistrito(){
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirInserirAreaOperacionalAction.do?adicionar=OK";
		
		retorno = true;

		if(form.idSistemaAbastecimento.value == -1){
			 alert('Informar Sistema de Abastecimento');
			 return false;
		}
		
		if(form.idSubsistemaAbastecimento.value == -1){
			 alert('Informar Subsistema de Abastecimento');
			 return false;
		}

		if(form.idDistritoOperacional.value == -1){
			 alert('Informar Distrito Operacional');
			 return false;
		}
		
		if (retorno){
			form.submit();
		}	
	} 

	function removerDistrito(identificacao){
		
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirInserirAreaOperacionalAction.do?remover=" + identificacao;
		
		form.submit();	
	}
	 
	 
</script>


<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">

<html:form action="/inserirAreaOperacionalAction.do"
	name="InserirAreaOperacionalActionForm"
	type="gcom.gui.opereracional.InserirAreaOperacionalActionForm"
	method="post"
	onsubmit="return validateInserirAreaOperacionalActionForm(this);">

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


			<td width="590" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Área Operacional</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a Área Operacional, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Descrição:<font
						color="#FF0000">*</font></strong></td>
					<td  width="60%" colspan="2"><span class="style2"> <html:text
						property="descricao" size="35" maxlength="30" /> </span></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Descrição Abreviada:</strong></td>
					<td  width="60%" colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="8" maxlength="6" /> </span></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Sistema de Abastecimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select
						property="idSistemaAbastecimento"  style="width:200px;"
						onchange="javascript:pesquisarColecao('subsistema');">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSistemaAbastecimento"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Subsistema de Abastecimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select
						property="idSubsistemaAbastecimento"  style="width:200px;"
						onchange="javascript:pesquisarColecao('distrito');">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSubsitemaAbastecimento" property="id"
							labelProperty="descricao" />
					</html:select></td>
				</tr>
				<tr>
					<td class="style3"><strong>Distrito Operacional:<font
						color="#FF0000">*</font></strong></td>
					<td><html:select
						property="idDistritoOperacional"  style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoDistritoOperacional" property="id"
							labelProperty="descricao" />
					</html:select></td>
					<td align="right"><input type="button" class="bottonRightCol"
								value="Adicionar" tabindex="11" style="width: 80px"
								onclick="javascript:adicionarDistrito();">
					</td> 	
				</tr>
				<tr>
				<td colspan="3">									
				  <table width="100%" border="0">						
					<tr>
					  <td colspan="3">
						<table width="100%" cellpadding="0" cellspacing="0">					
							<tr>
								<td colspan="3">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" bgcolor="#99CCFF">
													<tr bgcolor="#99CCFF">
														<td width="20%">
															<div align="center"><strong>Remover</strong></div>
														</td>
														<td width="20%">
															<div align="center"><strong>Principal</strong></div>
														</td>
														<td width="60%">
															<div align="center"><strong>Distrito Operacional</strong></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			

									<logic:present name="colecaoAreaDistritoOperacional" scope="session">

									<div style="width: 100%; height: 100; overflow: auto;">

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%String cor = "#cbe5fe";%>

												<table width="100%" align="center" bgcolor="#99CCFF">

													<logic:iterate name="colecaoAreaDistritoOperacional" id="areaDistritoOperacional" type="AreaDistritoOperacional">


														<%if (cor.equalsIgnoreCase("#FFFFFF")) {
															cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%} else {
															cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td align="center" width="20%">																		
																<a href="javascript:removerDistrito('<%="" + GcomAction.obterTimestampIdObjeto(areaDistritoOperacional) %>')" title="Remover">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																</a>
															</td>
															<td width="20%" align="center">
																<strong> 
																<html:radio property="indicadorPrincipal"
																	value="<%=""+ GcomAction.obterTimestampIdObjeto(areaDistritoOperacional)%>"/>
																</strong>
															</td>
															<td width="60%" align="center" valign="middle"><bean:write
																	name="areaDistritoOperacional" property="distritoOperacional.descricao" /></td>
														</tr>
													</logic:iterate>

												</table>
											</td>
										</tr>
									</table>
									</div>
									</logic:present>
								</td>
							</tr>							
					 	</table>
					  </td>
					</tr>																			
				  </table>						
				</td>
			  	</tr>
				<table width="100%">
					<tr>
						<td height="19"><strong> <font color="#FF0000"></font></strong></td>

						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>
					<tr>
						<td width="40%" align="left"><input type="button"
							name="ButtonReset" class="bottonRightCol" value="Desfazer"
							onClick="limparForm();"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>

						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Inserir"
							onclick="javascript:validarForm();" tabindex="11" /></td>
					</tr>
				</table>

			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>
