<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>

<%@ page import="gcom.seguranca.acesso.usuario.Usuario"%>
<%@ page import="gcom.seguranca.acesso.usuario.bean.ExibirRevalidarUsuarioHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>


<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>

function validaForm(objeto) {
	if (CheckboxNaoVazioRevalida(objeto)){
	 	var form = document.forms[0];
	 	form.action = 'revalidarUsuarioAction.do';
	 	form.submit(); 	
	}
 }

function CheckboxNaoVazioRevalida(campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert('Nenhum registro selecionado.'); 
	}

	return retorno;
}
	
function abre(img,id){
	
	for(var i =0;i<document.getElementsByName("idPai").length;i++){
	  	var element = document.getElementsByName(id+"_"+document.getElementsByName("idPai")[i].value);
	  	alert(element.length)
	  	for(var j =0;j<element.length;j++){
	  	if(element[j].style.visibility == "visible"){
	  		element[j].style.display="none";
	  		element[j].style.visibility="hidden";
	      	img.src = "./imagens/nolines_plus.gif";
	     }else{
	   		element[j].style.visibility="visible";
	      	element[j].style.display="";
	      	img.src = "./imagens/nolines_minus.gif";
	     }
	  	}
	}

}

function facilitador(objeto){
    if (objeto.id == "0" || objeto.id == undefined){
        objeto.id = "1";
        marcarTodos();
    }
    else{
        objeto.id = "0";
        desmarcarTodos();
    }
}

function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerUsuarioTipoAction.do"
			document.forms[0].submit();
		 }
	}
 }

</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirRevalidarUsuarioAction" method="post"
	name="ExibirRevalidarUsuarioActionForm"
	type="gcom.gui.seguranca.acesso.usuario.ExibirRevalidarUsuarioActionForm"
>

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
			<td width="602" valign="top" class="centercoltext">
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

					<logic:notPresent name="acao" scope="session">
						<td class="parabg">Revalidar Usuário</td>
					</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
						<logic:present name="usuarioPai"  scope="session">
							<strong>Usuário superior dos usuários listados abaixo: <bean:write name="usuarioPai" property="login"/> / <bean:write name="usuarioPai" property="nomeUsuario"/>	</strong> 
						</logic:present> </font></td>
				</tr>

				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>



				<tr>
					<td>
						<table width="100%" bgcolor="#90c7fc">

							<tr>
								<td width="7%">
								<div align="center"><strong><a
									href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
								</td>
								<td width="20%" align="center" bgcolor="#90c7fc"><strong>Login</strong></td>
								<td width="20%" align="center" bgcolor="#90c7fc"><strong>Nome</strong></td>																					
								<td width="20%" align="center" bgcolor="#90c7fc"><strong>Data de Expiração</strong></td>																	
								<td width="20%" align="center" bgcolor="#90c7fc"><strong>Dias Restantes</strong></td>																					
							</tr>
						

							<logic:present name="colecaoUsuario" scope="session">
								<% String cor = "#cbe5fe";%>
								
								<logic:iterate name="colecaoUsuario" id="exibirRevalidarUsuarioHelper" type="ExibirRevalidarUsuarioHelper">
									<input type="hidden" name="idPai" id="idPai[]"  value="${exibirRevalidarUsuarioHelper.usuario.id}">
									
										<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF" height="18">	
										<%} else{	
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe" height="18">		
										<%}%>
												<td width="7%">
	                              					<div align="center">
	                              					<input type="checkbox" name="ids" value="${exibirRevalidarUsuarioHelper.usuario.id}"/>
	                              					</div>
	                            				</td>
	                            				<td width="15%" align="center">
	                               					<a href="exibirRevalidarUsuarioAction.do?id=${exibirRevalidarUsuarioHelper.usuario.id}">
	                               						${exibirRevalidarUsuarioHelper.usuario.login}
	                               					</a>
	                            				</td>
	                            				<td width="20%" align="center">
	                               					${exibirRevalidarUsuarioHelper.usuario.nomeUsuario}
	                            				</td>
	                            				<td width="20%" align="center">
	                            				<%="" + Util.formatarData(exibirRevalidarUsuarioHelper.getUsuario().getDataExpiracaoAcesso()) %>
	                            				</td>
	                            				<td width="20%" align="center">
	                            				<% if(exibirRevalidarUsuarioHelper.getDiasRestantes() <= 0){ %>
	                            					Expirado
	                            				<%} else {%>
	                               					${exibirRevalidarUsuarioHelper.diasRestantes}
	                               				<% } %>
	                            				</td>
											</tr>
												
								</logic:iterate>
								
							</logic:present>
							</div>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%">
				<tr>
					<td>
					 <input name="button" type="button"
						class="bottonRightCol" value="Voltar"
						onclick="history.back()" align="left" style="width: 80px;"></td>
					<td align="right">      
					<div align="right">
						<input type="button" class="bottonRightCol"	value="Revalidar Usuários" onclick="validaForm(ids)" /></div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">

				<tr>
					<td>
					
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<script>

setCookie("desativaHistoryBack", "true", "1");

</script>
</html:form>
</body>
</html:html>