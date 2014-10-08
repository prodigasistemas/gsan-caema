<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<!--================================= SCRIPTS =============================================================-->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<!--=======================================================================================================-->
		
		<script language="JavaScript">	
			$(document).ready(function(){
				$('[@name=sistemaAgua]').change(ajaxSistemaAgua);
			});

			function ajaxSistemaAgua(){
				var theForm = $("form[name=FiltrarSetorAbastecimentoActionForm]");
				   var params = theForm.serialize();
				   var actionURL = 'exibirFiltrarSetorAbastecimentoAction.do?action=atualizarListaSubsistema'
				   $.ajax({
					    cache: true,
					    type:"POST",
					    url:actionURL,
					    data:params,
					    success:function(data, textStatus, XMLHttpRequest){
					    	var obj = JSON && JSON.parse(data) || $.parseJSON(data);
					    	
							$('[@name=subsistemaAgua]').get(0).options.length = 0;
							$('[@name=subsistemaAgua]').get(0).options[0] = new Option("", "-1"); 
							
							$.each(obj, function(index, item) {
								$('[@name=subsistemaAgua]').get(0).options[$('[@name=subsistemaAgua]')
							                                           		.get(0).options.length] = new Option(item.descricao, item.id);
							});
					    	
					    },
					    error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert("Ocorreu um erro, tente novamente.");
					    }
					});	
			}

			function redo(){
				ajaxSistemaAgua();
			}
		
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/filtrarSetorAbastecimentoAction"
				   name="FiltrarSetorAbastecimentoActionForm"
				   type="gcom.gui.operacional.FiltrarSetorAbastecimentoActionForm"
				   method="post">			
				   
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
					<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Filtrar Setor de Abastecimento</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<p>Preencha os campos para pesquisar um setor de abastecimento:</p>
									<p>&nbsp;</p>
								</td>
								<td width="100" align="right" colspan="2">
									<html:checkbox property="indicadorAtualizar" value="1"><strong>Atualizar</strong></html:checkbox>
									<html:hidden   property="indicadorAtualizar" value="2" />
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO =========================-->
							
							<tr> 
					          <td><strong>Código:</strong></td>
					          <td colspan="2"><html:text property="codigo" size="4" maxlength="4" tabindex="1" styleClass="tipoInteiro"/> (somente números)</td>
					      	</tr>
					      	
							<tr> 
					          <td><strong>Descrição:</strong></td>
					          <td colspan="2"><html:text property="descricao" size="20" maxlength="20" tabindex="2"/></td>
					      	</tr>
					      	<tr>
						      	<td>&nbsp;</td>
						        <td colspan="2">
						        	<html:radio property="indicadorPosicaoTexto" value="1">Iniciado pelo texto</html:radio>
						        	<html:radio property="indicadorPosicaoTexto" value="2">Contendo o texto</html:radio>
						        </td>
					      	</tr>
					      	
					      	<tr> 
					          <td><strong>Descrição Abreviada:</strong></td>
					          <td colspan="2"><html:text property="descricaoAbreviada" size="6" maxlength="6" tabindex="3"/></td>
					      	</tr>
							
						    <tr>
							   <td><strong>Sistema de Abastecimento:</strong></td>
							   <td>
									<html:select property="sistemaAgua" style="width: 200px;" tabindex="4">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSA" scope="session">
											<html:options collection="colecaoSA" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>					   
						   <tr>
							   <td><strong>Subsistema de Abastecimento:</strong></td>
							   <td>
									<html:select property="subsistemaAgua" style="width: 200px;" tabindex="5">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSubsistemaPrincipal" scope="session">
											<html:options collection="colecaoSubsistemaPrincipal" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>
						   
						   <tr>
						      	<td><strong>Indicador de uso:</strong></td>
						        <td colspan="2">
						        	<html:radio property="indicadorUso" value="1">Ativo</html:radio>
						        	<html:radio property="indicadorUso" value="2">Inativo</html:radio>
						        	<html:radio property="indicadorUso" value="3">Todos</html:radio>
						        </td>
					      	</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><input type="button" name="Button"
									class="bottonRightCol" value="Limpar" tabindex="33"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarSetorAbastecimentoAction.do?menu=sim'"
									style="width: 80px" /></td>
								<td align="right"><gsan:controleAcessoBotao name="Button"
									value="Filtrar" tabindex="31"
									onclick="javascript:document.forms[0].submit();"
									url="filtrarSetorAbastecimentoAction.do" />
							</tr>		
						</table>
					</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
		</div>
	</body>
</html:html>
