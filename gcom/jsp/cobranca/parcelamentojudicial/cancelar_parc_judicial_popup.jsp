<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page	import="gcom.cobranca.DocumentoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>	
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<html:javascript staticJavascript="false" formName="CancelarParcelamentoJudicialActionForm" />
		
		<script language="JavaScript">
			
		
			$(document).ready(function(){
				
			});
			
		</script>
	</head>
		<body leftmargin="5" topmargin="5">	
		<html:form action="/cancelarParcelamentoJudicialAction"
				   name="CancelarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.CancelarParcelamentoJudicialActionForm"
				   method="post">
				   
			<table width="625" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="635" valign="top" class="centercoltext">
						<table>
							<tr>
								<td></td>
							</tr>
						</table>
						<!-- ======================================== CABEÇALHO ======================================== -->
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Cancelar Parcelamento Judicial</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>	
							</tr>
						</table>			
						<!-- =========================================================================================== -->
						<table width="100%" border="0" align="center" cellpadding="0">
							<tr>
								<td width="30%"><strong>Nº do Processo Judicial:</strong></td>
	                   			<td width="70%" height="24">
			               			<html:text maxlength="30" 
			               					   property="numeroProcessoJudicial" 
			               					   size="30"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="30%"><strong>Cliente Responsável:</strong></td>
	                   			<td width="70%" height="24">
			               			<html:text maxlength="50" 
			               					   property="clienteResponsavel" 
			               					   size="50"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="30%"><strong>Imóvel Principal:</strong></td>
	                   			<td width="70%" height="24">
			               			<html:text maxlength="10" 
			               					   property="imovelPrincipal" 
			               					   size="10"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="30%"><strong>Data do Parcelamento:</strong></td>
	                   			<td width="70%" height="24">
			               			<html:text maxlength="10" 
			               					   property="dataParcelamento" 
			               					   size="10"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="30%"><strong>Data do Cancelamento:</strong></td>
	                   			<td width="70%" height="24">
			               			<html:text maxlength="10" 
			               					   property="dataCancelamento" 
			               					   size="10"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
		               			<bean:define id="listaMotivoCancelamento" property="listaMotivoCancelamento" name="CancelarParcelamentoJudicialActionForm"/>
								<td width="30%"><strong>Motivo do Cancelamento:<font color="#FF0000">*</font></strong></td>
	                   			<td width="70%" height="24">
	                   				<html:select property="motivoCancelamento">
	                   					<html:option value="-1">&nbsp;</html:option>
	                   					<html:options collection="listaMotivoCancelamento"
													  labelProperty="descricaoParcelamentoMotivoDesfazer" property="id"/>
	                   				</html:select>
	                   			</td>
	                   		</tr>	               		
	               		</table>
						<!-- ======================================== CAMPOS DA PESQUISA ======================================== -->
						<table width="100%" border="0">
							<tr>
								<td colspan="4" height="24">&nbsp;</td>
							</tr>										
							
							<tr>
								<td></td>
								<td align="left">
									<strong><font color="#FF0000">*</font></strong>Campo obrigatório
								</td>
							</tr>
							<tr>
								<td colspan="4">
								&nbsp;
								</td>
							</tr>
							
							<tr>
								<td colspan="2" >
									<input type="button" name="ButtonCancelar" class="bottonRightCol"
									       value="Cancelar"
										   onClick="javascript:window.close()">
								</td>
								<td colspan="2" align="right">
									<input type="submit"
										   name="concluirButton" class="bottonRightCol"
										   value="Concluir"
										   id="concluirButton"/>
								</td>
							</tr>				
						</table>
						<!-- ==================================================================================================== -->
						
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
