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
		<!--=======================================================================================================-->
		
		<html:javascript formName="InserirSetorAbastecimentoActionForm" />
		<script language="JavaScript">	
			$(document).ready(function(){

				$('[@name=sistemaAgua1]').change(function() {
					carregarSubsistemaPrincipal();
			    });

				$('[@name=subsistemaPrincipal]').change(function() {
					carregarSistema2();
			    });
				
				$('[@name=sistemaAgua2]').change(function() {
					carregarSubsistemasSecundiarios();
				});
			});


			function carregarSubsistemaPrincipal(){
			   var theForm = $("form[name=InserirSetorAbastecimentoActionForm]");
			   var params = theForm.serialize();
			   var actionURL = 'exibirInserirSetorAbastecimentoAction.do?action=atualizarListaSubsistemaPrincipal'
				
				apagarSelect('subsistemaPrincipal');
			    apagarSelect('sistemaAgua2');
			    apagarSelect('subsistemasSecundarios');
					 
			   $.ajax({
				    type:"POST",
				    url:actionURL,
				    data:params,
				    success:function(data, textStatus, XMLHttpRequest){
				    	var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				    	
						preencherSelect(obj,'subsistemaPrincipal');
						
						 if(item.selected == "true")
						 	$("[@name=subsistemaPrincipal] option[value='" + item.id + "']").attr("selected", true);
						 else
							$("[@name=subsistemaPrincipal] option[value='" + item.id + "']").attr("selected", false);					             

				    }   
				});	
			}

			function carregarSistema2(){
			   var theForm = $("form[name=InserirSetorAbastecimentoActionForm]");
			   var params = theForm.serialize();
			   var actionURL = 'exibirInserirSetorAbastecimentoAction.do?action=atualizarListaSistemaSecundario'
			   
			   apagarSelect('sistemaAgua2');
			   apagarSelect('subsistemasSecundarios');
				   
			   $.ajax({
				    type:"POST",
				    url:actionURL,
				    data:params,
				    success:function(data, textStatus, XMLHttpRequest){
				    	var obj = JSON && JSON.parse(data) || $.parseJSON(data);

						preencherSelect(obj,'sistemaAgua2');
				    	
				    }
				});	
			}			

			function carregarSubsistemasSecundiarios(){
				   var theForm = $("form[name=InserirSetorAbastecimentoActionForm]");
				   var params = theForm.serialize();
				   var actionURL = 'exibirInserirSetorAbastecimentoAction.do?action=atualizarListaSubsistemasSecundarios'
   				   apagarSelect('subsistemasSecundarios');
				   
				   $.ajax({
					    type:"POST",
					    url:actionURL,
					    data:params,
					    success:function(data, textStatus, XMLHttpRequest){
					    	var obj = JSON && JSON.parse(data) || $.parseJSON(data);
					    	
							preencherSelect(obj,'subsistemasSecundarios');
					    	
					    }
					});	   
			}	

			
			function apagarSelect(nome){
				$('[@name='+nome+']').get(0).options.length = 0;
				$('[@name='+nome+']').get(0).options[0] = new Option("", "-1"); 
			}

			
			function preencherSelect(objJSON, nome){
				$.each(objJSON, function(index, item) {
					$('[@name='+nome+']').get(0).options[$('[@name='+nome+']')
				                                           		.get(0).options.length] = new Option(item.descricao, item.id);
				});
			}	
			
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="" >
		<div id="formDiv">
			<html:form action="/inserirSetorAbastecimentoAction"
				   name="InserirSetorAbastecimentoActionForm"
				   type="gcom.gui.operacional.InserirSetorAbastecimentoActionForm"
				   method="post"
				   onsubmit="return validateInserirSetorAbastecimentoActionForm(this)">			
				   
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
								<td class="parabg">Inserir Setor de Abastecimento</td>
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
									<p>Para inserir um setor de abastecimento, informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO =========================-->
							
							<tr> 
					          <td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					          <td colspan="2"><html:text property="descricao" size="20" maxlength="20" tabindex="1"/></td>
					      	</tr>
					      	<tr> 
					          <td><strong>Descrição Abreviada:<font color="#FF0000"></font></strong></td>
					          <td colspan="2"><html:text property="descricaoAbreviada" size="6" maxlength="6" tabindex="1"/></td>
					      	</tr>
							
						    <tr>
							   <td><strong>Sistema de Abastecimento Principal:<font color="#FF0000">*</font></strong></td>
							   <td>
									<html:select property="sistemaAgua1" style="width: 200px;height:20px;" tabindex="13">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSA" scope="session">
											<html:options collection="colecaoSA" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>					   
						   <tr>
							   <td><strong>Subsistema de Abastecimento Principal:<font color="#FF0000">*</font></strong></td>
							   <td>
									<html:select property="subsistemaPrincipal" style="width: 200px;height:20px;" tabindex="13">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSubsistemaPrincipal" scope="session">
											<html:options collection="colecaoSubsistemaPrincipal" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>
						   
						   <tr>
							   <td><strong>Sistema de Abastecimento Secundário:</strong></td>
							   <td>
									<html:select property="sistemaAgua2" style="width: 200px;height:20px;" tabindex="13">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSAS" scope="session">
											<html:options collection="colecaoSAS" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>
						    
						    <tr>
							   <td><strong>Subsistemas de Abastecimento Secundários:</strong></td>
							   <td>
									<html:select property="subsistemasSecundarios" style="width: 200px;height:200px;" tabindex="14" multiple="true">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoSubsistemaSecundario" scope="session">
											<html:options collection="colecaoSubsistemaSecundario" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
							   </td>
						   </tr>
						    
                   			<tr>
								<td>&nbsp;</td>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							<tr>
								<td colspan="2"><input type="button" name="Button"
									class="bottonRightCol" value="Desfazer" tabindex="33"
									onClick="javascript:window.location.href='/gsan/exibirInserirSetorAbastecimentoAction.do?menu=sim'"
									style="width: 80px" />&nbsp; <input type="button" name="Button"
									class="bottonRightCol" value="Cancelar" tabindex="32"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
									style="width: 80px" /></td>
								<td align="right">
								<input type="submit" name="Button"
									   class="bottonRightCol" value="Inserir" tabindex="32" style="width: 80px" />
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
