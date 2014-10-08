<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.NormaArquivos"%>

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirNormasProcedimentosActionForm"/>

<script language="JavaScript">
	
	function validarForm(form){
		if(validateInserirNormasProcedimentosActionForm(form) && validateCaracterEspecial(form)) {	     		  		
			submeterFormPadrao(form);
		}
	}
	
	function caracteresespeciais () {
    	this.aa = new Array("descricaoTitulo", "Título possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("descricaoAssunto", "Assunto possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

	function adicionarArquivo(){
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirAtualizarNormasProcedimentosAction.do?reloadPage=1&adicionar=OK";
		
		retorno = true;
		
		if(form.arquivo.value.length == 0){
			alert("Informe o Arquivo");
			form.arquivo.focus();
			retorno = false;
		}
		
		if (retorno){
			form.submit();
		}	
	} 

	function removerArquivo(identificacao){
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirAtualizarNormasProcedimentosAction.do?reloadPage=1&remover=" + identificacao;
		
		form.submit();	
	}

	function desfazer(){
		var form = document.forms[0];
		form.action = 'exibirAtualizarNormasProcedimentosAction.do?menu=sim';
		form.submit();

	}
			
	
</script>

</head>					

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].descricaoAssunto, 200, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">

<html:form action="/atualizarNormasProcedimentosAction" name="InserirNormasProcedimentosActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.InserirNormasProcedimentosActionForm" method="post"
	enctype="multipart/form-data"
	onsubmit="return validateInserirNormasProcedimentosActionForm(this);">

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

			<td width="615" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Normas e Procedimentos</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
			  <tr>
					<td height="10" colspan="3">Para atualizar normas e procedimentos, informe os dados abaixo:</td>
			  </tr>
			  <tr>
					<td colspan="3">&nbsp;</td>
   			  </tr>

			  <tr>
					<td><strong>Tipo de Documento:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoDocumentoTipo" size="54" maxlength="50" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000"/> </span></td>
			  </tr>
			   	  
		   	  <tr>
					<td><strong>Título:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoTitulo" size="54" maxlength="50" /> </span></td>
			  </tr>
		      <tr>
					<td class="style1"><strong>Assunto:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style2">
						<html:textarea property="descricaoAssunto" cols="40" rows="4"
						onkeypress="return desabilitarEnter(this, event)" 
						onkeyup="limitTextArea(document.forms[0].descricaoAssunto, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
						<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
					</td>
		      </tr>
	      	  <tr>
					<td colspan="3">
					<hr>
					</td>
			  </tr>
			  <tr>
				    <td><strong>Adicionar Arquivo:</strong></td>
			        <td height = 17 align="left">
						<input TYPE="file" NAME="arquivo" size="15" />
					</td>
					<td align="right"><input type="button" class="bottonRightCol"
								value="Adicionar" tabindex="11" style="width: 80px"
								onclick="javascript:adicionarArquivo();">
					</td> 	
		      </tr>	
		      <tr>
		      	<td colspan="3" height="10"></td>
		      </tr>	
		      <tr>
				<td colspan="3" align="center">
					<strong><font color="#FF0000">*</font></strong>
					Serão aceitos arquivos no formato PDF
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
														<td width="80%">
															<div align="center"><strong>Arquivo</strong></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			

									<logic:present name="colecaoAnexo" scope="session">

									<div style="width: 100%; height: 100; overflow: auto;">

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%String cor = "#cbe5fe";%>

												<table width="100%" align="center" bgcolor="#99CCFF">

													<logic:iterate name="colecaoAnexo" id="normaArquivos" type="NormaArquivos">


														<%if (cor.equalsIgnoreCase("#FFFFFF")) {
															cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%} else {
															cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%}%>
				
															<td align="center" width="20%">																		
																<a href="javascript:removerArquivo('<%="" + GcomAction.obterTimestampIdObjeto(normaArquivos) %>')" title="Remover">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																</a>
															</td>
															<td align="center" valign="middle"><bean:write
																	name="normaArquivos" property="descricaoArquivoTexto" /></td>
															</td>
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
		      <tr>
			  	<td colspan="3">&nbsp;</td>
			  </tr>
			  <tr>
				<td colspan="3">
					<hr></td>
			  </tr>     
			  <tr>
				<td colspan="3" align="center">
				<strong><font color="#FF0000">*</font></strong>
				Campos obrigat&oacute;rios
			  	</td>
			  </tr>
				
			<table width="100%">
				<tr>
					<td colspan="3">
						<logic:present name="voltar">
							<logic:equal name="voltar" value="filtrar">
								<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarNormasProcedimentosAction.do?desfazer=N"/>'">
							</logic:equal>
							<logic:equal name="voltar" value="manter">
								<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirManterNormasProcedimentosAction.do"/>'">
							</logic:equal>
						</logic:present>
						<logic:notPresent name="voltar">
							<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirManterNormasProcedimentosAction.do"/>'">
						</logic:notPresent>
						
						<input name="Button" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirAtualizarNormasProcedimentosAction.do?desfazer=S&reloadPage=1"/>'">
						<input name="Button" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'">
					</td>
						
					<td align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Atualizar" align="right" onClick="validarForm(document.InserirNormasProcedimentosActionForm)">	  
					</td>
				</tr>
			</table>
				
				
				
				
			  <tr>
		      	<td colspan="3" height="10"></td>
		      </tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
