<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<%@ page import="gcom.util.ConstantesSistema"%>
		<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>
		
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
		</script>
		<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
		
		<script language="JavaScript"
			src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		
		<html:javascript staticJavascript="false"  formName="IncluirProgramacaoRoteirosDiasEspeciaisActionForm" dynamicJavascript="true"/>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js">
		</script><html:javascript staticJavascript="false"  formName="IncluirProgramacaoRoteirosDiasEspeciaisActionForm" dynamicJavascript="true"/>
		
		
		
		<script type="text/javascript">

			function validarForm(form){
				var form = document.forms[0];
				if(testarCampoValorZero(document.IncluirProgramacaoRoteirosDiasEspeciaisActionForm.idEmpresa, 'Empresa')  && 
				   testarCampoValorZero(document.IncluirProgramacaoRoteirosDiasEspeciaisActionForm.dataProgramacao, 'Data Programação')) {
					var camposInvalidos = "";
					
					if(form.dataProgramacao.value == null || form.dataProgramacao.value == ""){
						camposInvalidos += "Informe a Data de Programação.\n";
					}
					if(form.idEquipe.value == null || form.idEquipe.value == "-1"){
						camposInvalidos += "Informe a Equipe.\n";
					}
					/*if(form.idEmpresa.selectedIndex == 0){
						camposInvalidos += "Selecione a Empresa.";
					}*/
					if(camposInvalidos == ""){
						if (validaData(form.dataProgramacao)){
						    form.submit();
						
						}
					}else{
						alert(camposInvalidos);
					}
				}
			}
		
			function validarEnter(tecla){
	      	  
				var codigo = null;
  				if (document.all) {
  					codigo = event.keyCode;
				}else{
					codigo = tecla.which;
				}
  		  
				if (codigo == 13) {
	  				var form = document.forms[0]; 
	  				if(form.idEmpresa.value == "-1"){
						alert("Selecione a empresa.");
						return false;
				   }
    		    
    	  	   }

    			if(codigo == null){//tab
				  return true;
			  	}

				if((codigo > 47 && codigo < 58) || (codigo.value == 'undefined')){ // numeros de 0 a 9
				  return true;
				}
				
				   if (codigo == 8 || codigo == 13){ // backspace ou enter
				      return true;
				   }
			  
		      return false;
	      	  
	     	}
		
	</script>
	</head>
<style>
#demoDiv{
	position: absolute; 
	top: 515px; 
	left: 56px; 
	visibility:hidden
}
</style>
	<body leftmargin="5" topmargin="5" onload="setarFoco(document.forms[0].dataProgramacao);">
		<div id="formDiv">
			<html:form action="/incluirProgramacaoRoteirosDiasEspeciaisAction"
				name="IncluirProgramacaoRoteirosDiasEspeciaisActionForm"
				type="gcom.gui.atendimentopublico.ordemservico.IncluirProgramacaoRoteirosDiasEspeciaisActionForm"
				method="post"
				 styleId="form">
				
				<html:hidden property="confirmacao" />	
			
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
									<td class="parabg">Incluir Programação de Roteiros em dias Especiais</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							
							<table width="100%" border="0">
								<tr>
									<td colspan="2" width="30%">
										<strong>
											Data da Programa&ccedil;&atilde;o:
											<font color="#FF0000">*</font>
										</strong>
									</td>
					                <td colspan="6">
					                	<span class="style2">
					                	
					                	<strong> 
											
											<html:text property="dataProgramacao" 
												size="11" 
												maxlength="10" 
												tabindex="1" 
												onkeyup="mascaraData(this, event);"
												onkeypress="return validarEnter(event);return isCampoNumerico(event);"/>
											
											<a href="javascript:abrirCalendarioReplicando('IncluirProgramacaoRoteirosDiasEspeciaisActionForm', 'dataProgramacao');">
												<img border="0" 
													src="<bean:message key='caminho.imagens'/>calendario.gif" 
													width="16" 
													height="15" 
													border="0" alt="Exibir Calendário" 
													/></a>
											</strong>
											<strong>(dd/mm/aaaa)</strong>
					                  	</span>
					              	</td>
								</tr>
								<tr>
									<td colspan="2" width="30%">
										<strong>
											Empresa:
											<font color="#FF0000">*</font>
										</strong>
									</td>
									<td colspan="2">
										<html:select property="idEmpresa" tabindex="3">
										<!--<html:option value="-1">&nbsp;</html:option>-->
											<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td colspan="2" width="30%">
										<strong>
											Equipe:
											<font color="#FF0000">*</font>
										</strong>
									</td>
									<td colspan="2">
										<html:select style="width:95px" property="idEquipe" tabindex="3">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoEquipe" labelProperty="nome" property="id" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<input name="Button" type="button" class="bottonRightCol"
											value="Limpar" align="left" tabindex="5"
											onclick="window.location.href='<html:rewrite page="/exibirIncluirProgramacaoRoteirosDiasEspeciaisAction.do?menu=sim"/>'">
										<input type="button" name="ButtonCancelar" class="bottonRightCol"
											value="Cancelar" tabindex="6 "
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</td>
									<td align="right">
										<gsan:controleAcessoBotao name="Botao" value="Incluir"
											onclick="validarForm(document.forms[0]);"
											url="selecionarAcompanhamentoArquivosRoteiroAction.do" tabindex="7" />
									</td>
								</tr>
								
										</table>
							</tr>
						</table>
				
					<%@ include file="/jsp/util/rodape.jsp"%>	
				</html:form>
		</div>
		<div id="result" style="display:none;">
		</div>
	</body>
    <%@ include file="/jsp/util/tooltip.jsp" %>	
	
	<%@ include file="/jsp/util/telaespera.jsp"%>


</html:html>