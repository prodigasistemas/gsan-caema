<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.seguranca.acesso.usuario.SolicitacaoAcesso" %>

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
    <html:javascript staticJavascript="false"  formName="SubstituirValidadorAutorizadorActionForm"/>		
<script language="JavaScript">

function limparForm(campo){
	var form = document.forms[0];
	if(campo == "usuario"){
		form.codigoUsuario.value = "";
		form.nomeUsuario.value = "";
	}else{
		form.codigoNovoUsuario.value = "";
		form.nomeNovoUsuario.value = "";
	}
}
 
function limpar(){
	var form = document.forms[0];
	form.action = 'exibirSubstituirValidadorAutorizadorAction.do?menu=sim';
	form.submit();
	
}

function habilitarPesquisaUsuarioResponsavel(form) {
	
	form.tipoPesquisa.value = 'usuario';
	abrirPopup('exibirUsuarioPesquisar.do?mostrarLogin=S', 'usuario', null, null, 275, 500, '',form.nomeUsuario.value);
		
}

function validaEnterUsuarioResponsavel(tecla, caminhoActionReload, nomeCampo) {

	var form = document.SubstituirValidadorAutorizadorActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Usuário");
	
}
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

    if (form.tipoPesquisa.value == 'usuario') {
		form.codigoUsuario.value = codigoRegistro;
		form.nomeUsuario.value = descricaoRegistro;
		form.action= 'exibirSubstituirValidadorAutorizadorAction.do';
		form.submit();
	}
    if (form.tipoPesquisa.value == 'usuarioNovo') {
		form.codigoNovoUsuario.value = codigoRegistro;
		form.nomeNovoUsuario.value = descricaoRegistro;
		form.action= 'exibirSubstituirValidadorAutorizadorAction.do';
		form.submit();
	}

}
function habilitarPesquisaNovoUsuario(form) {
	
	form.tipoPesquisa.value = 'usuarioNovo';
	abrirPopup('exibirUsuarioPesquisar.do?mostrarLogin=S', 'usuario', null, null, 275, 500, '',form.nomeNovoUsuario.value);
	
}

function validaEnterNovoUsuario(tecla, caminhoActionReload, nomeCampo) {

	var form = document.SubstituirValidadorAutorizadorActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Novo Usuário");
	
}

function validar(){
	var form = document.forms[0];
	if(obrigatorios(form,"0")){

		form.action = 'exibirSubstituirValidadorAutorizadorAction.do?botao=pesquisar';
		form.submit();

	}	
}

function obrigatorios(form,atualizar){
	retorno = true;
	var tipo = form.usuarioTipo;
	var checado = false;
	for(var i = 0; i < form.usuarioTipo.length; i++) {
		if(tipo[i].checked) {
			checado = true;
		}
	}
	if(!checado ){
		alert("Informe Tipo de Usuário.");
		retorno = false;
	}else if ( form.codigoUsuario.value == ""){
		alert("Informe o código do usuário atual.");
		retorno = false;
	
	}
	if(atualizar == "1"){
		if ( form.codigoNovoUsuario.value == ""){
			alert("Informe o novo usuário.");
			retorno = false;
		}
	}
	return retorno;
}


function atualizar(){
	var form = document.forms[0];
	if(obrigatorios(form,"1")){ 
		form.submit();
	}	
}
 		
</script>

</head>

<body leftmargin="5" topmargin="5" onload=" setarFoco(document.forms[0].dataProgramacao);">
		<div>
			<html:form action="/substituirValidadorAutorizadorAction"
				name="SubstituirValidadorAutorizadorActionForm"
				type="gcom.gui.seguranca.acesso.SubstituirValidadorAutorizadorActionForm"
				method="post">
				
<input type="hidden" name="tipoPesquisa"/>
			
				<%@ include file="/jsp/util/cabecalho.jsp"%>
				<%@ include file="/jsp/util/menu.jsp" %>
			
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
					  <td width="600" valign="top" class="centercoltext">
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
									<td class="parabg">Substituir Validador ou Autorizador</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">
							<tr>
								<td>Para substituir usuário validador e/ou autorizador, informe os dados abaixo:</td>
							</tr>							
						</table>
						<table width="100%" border="0">
							<tr >		
								<td width="25%"><strong>Tipo de usuário: </strong><font color="#ff0000">*</font></td>
								<td colspan="3"><html:radio property="usuarioTipo"  value="1" />Revalidador
								<html:radio property="usuarioTipo" value="2" />Autorizador
								<html:radio property="usuarioTipo" value="3"/>Todos		
								</td>
							</tr>
							
							 <tr> 
			                  	<td height="28"><strong>C&oacute;digo do Usu&aacute;rio:<font color="#ff0000">*</font></strong></td>
				                <td colspan="3">
									<html:text property="codigoUsuario" maxlength="11" size="11" 
									onkeyup="return validaEnterUsuarioResponsavel(event, 'exibirSubstituirValidadorAutorizadorAction.do', 'codigoUsuario'); " />
									<a href="javascript:habilitarPesquisaUsuarioResponsavel(document.forms[0]);" alt="Pesquisar Usuário Responsável">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
								
									<logic:present name="corUsuario">
										<logic:equal name="corUsuario" value="exception">
											<html:text property="nomeUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corUsuario" value="exception">
											<html:text property="nomeUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="corUsuario">
										<logic:empty name="SubstituirValidadorAutorizadorActionForm" property="codigoUsuario">
											<html:text property="nomeUsuario" size="38" value="" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="SubstituirValidadorAutorizadorActionForm" property="codigoUsuario">
											<html:text property="nomeUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent>
									<a href="javascript:limparForm('usuario');"> 
										<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
									</a>
								</td>
							</tr>
							
							<tr> 
			                  	<td height="28"><strong>C&oacute;digo do Novo Usu&aacute;rio :</strong></td>
				                <td colspan="3">
									<html:text property="codigoNovoUsuario" maxlength="11" size="11" 
									onkeyup="return validaEnterUsuarioResponsavel(event, 'exibirSubstituirValidadorAutorizadorAction.do', 'codigoNovoUsuario'); " />
									<a href="javascript:habilitarPesquisaNovoUsuario(document.forms[0]);" title="Pesquisar Código do Novo Usuário">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
								
									<logic:present name="corUsuarioNovo">
										<logic:equal name="corUsuarioNovo" value="exception">
											<html:text property="nomeNovoUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corUsuarioNovo" value="exception">
											<html:text property="nomeNovoUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="corUsuarioNovo">
										<logic:empty name="SubstituirValidadorAutorizadorActionForm" property="codigoNovoUsuario">
											<html:text property="nomeNovoUsuario" size="38" value="" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="SubstituirValidadorAutorizadorActionForm" property="codigoNovoUsuario">
											<html:text property="nomeNovoUsuario" size="38"	readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent>
									<a href="javascript:limparForm('usuarioNovo');"> 
										<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
									</a>
								</td>
							</tr>
							
							<tr>
								<td width="30%"><strong>Situação da Solicitação: </strong></td>
								<td width="70%"><html:select multiple="true" size="4"
									name="SubstituirValidadorAutorizadorActionForm" property="solicitacaoSituacao">
									<option value="">&nbsp;</option>
									<logic:notEmpty name="colecaoSolicitacaoAcessoSituacao">
										<html:options name="request" collection="colecaoSolicitacaoAcessoSituacao"
											labelProperty="descricao" property="id" />
									</logic:notEmpty>
								</html:select></td>
							</tr>
							
							<tr>
								<td colspan="3">
								<table width="100%" border="0">
									<tr>
										<td width="183"><strong>Relaçao das Solicitaçoes </strong></td>
										<td width="432" align="right"><input type="button" tabindex="6"
											class="bottonRightCol" value="Pesquisar" name="botaoPesquisar"
											onclick="javascript:validar();" />
										
									</tr>
									<tr>
										<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td height="0">
													<table width="100%" bgcolor="#99CCFF">
														<!--header da tabela interna -->
														<tr bgcolor="#90c7fc">
															<td width="14%" align="center">
																<strong>Usuário Solicitante</strong>
															</td>
															<td width="20%" align="center">
																<strong>Situação da Solicitação</strong>
															</td>
															<td width="19%" align="center">
																<strong>Data da solicitação</strong>
															</td>
															<td width="46%" align="center">
																<strong>Nome do usuário Solicitante</strong>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											
											 <tr>
												<td height="100">
													<div style="height:400px; overflow: auto;">
													<table width="100%" align="center" bgcolor="#99CCFF">
														<logic:present name="colecaoSolicitacaoAcesso">
															<%int cont = 0;%>
															<%--Campo que vai guardar o valor do telefone a ser removido--%>
															
															<logic:iterate name="colecaoSolicitacaoAcesso"
																id="solicitacaoAcesso" type="SolicitacaoAcesso">
																
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																<tr bgcolor="#cbe5fe">
																<%} else {%>
																<tr bgcolor="#FFFFFF">
																<%}%>
																	
																	<td width="14%" align="center">
																		<logic:notEmpty name="solicitacaoAcesso" property="usuarioSolicitante" >
																			<bean:write	name="solicitacaoAcesso" property="usuarioSolicitante.login" />
																		</logic:notEmpty>
																	
																	</td>
																	
																	<td width="19%" align="center">
																		<bean:write name="solicitacaoAcesso" property="solicitacaoAcessoSituacao.descricao"/>
																	
																	</td>
																	
																	<td width="20%" align="center">
																	<%=Util.formatarData(solicitacaoAcesso.getDataSolicitacao()) %>
																																			
																	</td>
																	
																	<td width="46%" align="center">
																		<bean:write	name="solicitacaoAcesso" property="nomeUsuario" />
																	
																	</td>
																	
																</tr>
															</logic:iterate>
														</logic:present>
													</table>
													</div>
												</td>
											</tr>
											<logic:present name="totalSolicitacoes">
												<tr>
													<td><strong>TOTAL DE SOLICITAÇÕES:</strong><bean:write name="totalSolicitacoes" /></td>
												</tr>
											</logic:present>
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
									<input name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
									onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" type="button">
			                    </td>
			                    <td>
									<input name="Button" type="button" 
										   class="bottonRightCol" value="Limpar" 
										   align="left" onclick="javascript:limpar()" >
			                    </td>
			                   
			
								<td width="100%" align="right">						
									<input  type="button" 
											class="bottonRightCol" 
										    value="Atualizar" 
										    onClick="javascript:atualizar();">
								</td>
								
			                 
							</tr>
							
							
							
						</table>
						<p>&nbsp;</p>
						</td>
					</tr>
				</table>
</html:form>
</div>
</body>
</html:html>