<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.gui.atendimentopublico.ordemservico.AssociarFotosOsRepavimentacaoPopUpActionForm"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="AssociarFotosOsRepavimentacaoPopUpActionForm"/>		
<script language="JavaScript">

 
function desfazer(){
	var form = document.forms[0];
    form.dataExecucao.value = ""; 
    form.idPavimentoRuaRet.value = "-1";
	form.areaPavimentoRuaRet.value = "";	
	form.outrosCustos.value = "";		
	form.observacao.value = "";		

}
function validaArquivo(){
	var form = document.forms[0];
	if(form.arquivo.value == "" ){
		alert("Informe o arquivo.");
		return false;
	}else{
		form.action = 'exibirAssociarFotosOSRepavimentacaoPopUpAction.do?botao=adicionar';
		form.submit();

	}

	
}
function fechar(){
	window.close();
} 	
function remover(objeto){
	var form = document.forms[0];
	if (confirm ("Confirma remoção?")) {
		redirecionarSubmit('removerAssociarFotosOSRepavimentacaoPopUpAction.do?idRemover='+objeto);
	}
}

function salvar(){
	
	redirecionarSubmit('inserirAssociarFotosOSRepavimentacaoPopUpAction.do');
	
}
 		
</script>

</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(650, 500);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="window.close()">
</logic:present>

<html:form action="/exibirAssociarFotosOSRepavimentacaoPopUpAction.do"
	name="AssociarFotosOsRepavimentacaoPopUpActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AssociarFotosOsRepavimentacaoPopUpActionForm"
	method="post"
	enctype="multipart/form-data">
	
	<html:hidden property="idRA"/>
		
	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Anexar Fotos Ordem Serviço </td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para anexar um ou vários arquivos, informe os dados abaixo:</td>
				</tr>							
			</table>
			<table width="100%" border="0">
				<tr>		
					<td width="25%"><strong>Arquivo: </strong><font color="#ff0000">*</font></td>
					<td><html:file property="arquivo" size="35"
							 />
					</td>
				</tr>
				
			
				<tr>
					<td width="15%"><strong>Observação:<font color="#FF0000"></font></strong></td>
					<td width="85%" colspan="2">
						<html:textarea property="observacao" cols="40" rows="5" /><br>		
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
					<table width="100%" border="0">
						<tr>
							<td width="183"><strong>Arquivos Informados </strong></td>
							<td width="432" align="right"><input type="button" tabindex="6"
								class="bottonRightCol" value="Adicionar" name="botaoAdicionar"
								onclick="javascript:validaArquivo();" />
							
						</tr>
						<tr>
							<td colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td height="0">
										<table width="100%" bgcolor="#99CCFF">
											<!--header da tabela interna -->
											<tr bgcolor="#90c7fc">
												<td width="18%" align="center">
													<strong>Remover</strong>
												</td>
												<td width="14%" align="center">
													<strong>Arquivo</strong>
												</td>
												<td width="80%" align="center">
													<strong>Observação</strong>
												</td>
												
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="100">
										<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" align="center" bgcolor="#99CCFF">
											<logic:present name="colecaoArquivos">
												<%int cont = 0;%>
												<%--Campo que vai guardar o valor do telefone a ser removido--%>
												
												<logic:iterate name="colecaoArquivos" id="arquivo" scope="session" type="RegistroAtendimentoAnexo">
													<input type="hidden" name="idRegistrosRemocao" value="" />
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
														<td width="10%" align="center">
															<strong> 
															<img src="<bean:message key='caminho.imagens'/>Error.gif" title="Remover"
																width="14" height="14" style="cursor:pointer;" alt="Remover"
																onclick="javascript:remover('<%=cont%>');">
															</strong>
														</td>
														
														<td width="10%" align="center">
															
															
															<logic:notEmpty name="arquivo" property="id" >
															
																<a href="javascript:window.location.href='/gsan/retornarFotoOsRepavimentacaoPopUpAction.do?idArquivo=${arquivo.id}'" >
																	<img src="<bean:message key='caminho.imagens'/>
																	<% if(arquivo.getNomeExtensaoDocumento().equals("DOC")){ %>
																	DOC.gif"
																	<% }else if(arquivo.getNomeExtensaoDocumento().equals("PDF")){ %>
																	PDF.gif"
																	<% }else if(arquivo.getNomeExtensaoDocumento().equals("JPG")){ %>
																	JPG.gif"
																	<%} %>
																	width="14" height="14" style="cursor:pointer;" alt="Download Arquivo" title="Download Arquivo"></a>
															</logic:notEmpty>
															
															<logic:notPresent name="arquivo" property="id" >
															
																	<img src="<bean:message key='caminho.imagens'/><%=request.getAttribute("extensao") %>.gif"
																		width="14" height="14" alt="Download Arquivo" title="Download Arquivo">
															</logic:notPresent>
															
														</td>
														<td width="40%" align="center">
															<bean:write	name="arquivo" property="descricaoDocumento" />
														
														</td>
														
													</tr>
												</logic:iterate>
											</logic:present>
										</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			
			<table width="100%" border="0">
				<tr>
					<td>
						<input name="Button" type="button" 
							   class="bottonRightCol" value="Voltar" 
							   align="left" onclick="javascript:redirecionarSubmit('exibirAtualizarOrdemProcessoRepavimentacaoPopupAction.do?anexo=sim&numeroOS=${sessionScope.numeroOS}&page.offset= ${sessionScope.manterPaginaAux}')" >
                    </td>
                    <td>
						<input name="Button" type="button" 
							   class="bottonRightCol" value="Desfazer" 
							   align="left" onclick="javascript:redirecionarSubmit('exibirAssociarFotosOSRepavimentacaoPopUpAction.do?desfazer=sim');" >
                    </td>
                    <td><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>

					<td width="100%" align="right">						
						<input  type="button" 
								class="bottonRightCol" 
							    value="Salvar" 
							    onClick="javascript:salvar();">
					</td>
					
                 
				</tr>
				
				
				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>