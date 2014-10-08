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
		<html:javascript staticJavascript="false" formName="EfetuarParcelamentoJudicialActionForm" />
		
		<script language="JavaScript">
			
		
			$(document).ready(function(){
				$('#adicionarButton').bind("click",function(){
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?'+
							'action=exibirInformarValorParcelasPopupAction'+
							'&metodo='+${EfetuarParcelamentoJudicialActionForm.metodoAdicionarParcela});				
				});
				
				$('#desfazerButton').bind("click",function(){
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?'+
							'action=exibirInformarValorParcelasPopupAction'+
							'&metodo='+${EfetuarParcelamentoJudicialActionForm.metodoDesfazerParcelasInformadas});				
				});
				
				$('#informarButton').bind("click",function(){
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?'+
									   'action=informarValorParcelasPopupAction');
										
				});
				
			});
			
		</script>
	</head>
		<body leftmargin="5" topmargin="5">	
		<html:form action="/efetuarParcelamentoJudicialWizardAction"
				   name="EfetuarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.EfetuarParcelamentoJudicialActionForm"
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
								<td class="parabg">Informar Valor da Parcela de Parcelamento Judicial</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>	
							</tr>
						</table>			
						<!-- =========================================================================================== -->
						<table width="100%" border="0" align="center" cellpadding="0">
							<tr>
								<td width="22%"><strong>Valor do Débito:</strong></td>
	                   			<td width="81%" height="24">
			               			<html:text maxlength="15" 
			               					   property="valorDebito" 
			               					   size="15"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Valor do Acordo:</strong></td>
	                   			<td width="25%" height="24">
			               			<html:text maxlength="20" 
			               					   property="valorAcordo" 
			               					   size="20"  
			               					   tabindex="2" 
			               					   readonly="true" 
			               					   styleClass="campoBloqueado"/>
			               	    </td>			               	
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
	                   			<td width="25%" height="24">
			               			<html:text maxlength="5" 
			               					   property="qtdParcelasInformar" 
			               					   size="5"  
			               					   tabindex="3"
			               					   styleClass="tipoInteiro"/>
			               	    </td>			               	
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Parcela:<font color="#FF0000">*</font></strong></td>
	                   			<td width="25%" height="24">
			               			<html:text maxlength="5"
			               					   size="5"  
			               					   tabindex="4"
			               					   property="parcelaInicial"
			               					   styleClass="tipoInteiroInicial"/>
			               					   a 
               					   <html:text maxlength="5" 
			               					  size="5"  
			               					  tabindex="5"
			               					  property="parcelaFinal"
			               					  styleClass="tipoInteiro"/>
			               					   
			               	    </td>			               	
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Valor da Parcela:<font color="#FF0000">*</font></strong></td>
	                   			<td width="25%" height="24" colspan="2">
			               			<html:text maxlength="20" 
			               					   property="valorParcelaInformar" 
			               					   size="20"  
			               					   tabindex="6" 
			               					   styleClass="tipoMonetario" />
			               			<input type="button" value="Adicionar" class="bottonRightCol" id="adicionarButton" style="margin-left:181px;"/>			   
			               		</td>
			               	</tr>
			               	<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>
		               		<c:if test="${not empty EfetuarParcelamentoJudicialActionForm.listaParcelaJudicialInformar 
											&& fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicialInformar) gt 0 }">
							<tr>
								<td colspan="3" width="100%">
									<table border="0" width="100%" bgcolor="#90c7fc">
										<tr>
											<td bgcolor="#90c7fc" align="center" width="11%"><strong>Remover</strong></td>
											<td bgcolor="#90c7fc" align="center" width="5%"><strong>Parcela</strong></td>
											<td bgcolor="#90c7fc" align="center" width="61%"><strong>Valor da Parcela</strong></td>
										</tr>
									</table>	
									<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicialInformar) gt 7 }">																								 
										<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
									</c:if>																 
									<table border="0" width="100%" bgcolor="#90c7fc">
										<c:set var="count" value="0"/>
									<logic:iterate name="EfetuarParcelamentoJudicialActionForm" 
												   property="listaParcelaJudicialInformar" 
												   id="parcela">
										<c:choose>
											<c:when test="${count % 2 == 0 }">
												<tr bgcolor="#FFFFFF">		
											</c:when>
											<c:otherwise>
												<tr bgcolor="#cbe5fe">
											</c:otherwise>
										</c:choose>
											<td align="center" width="12%">
											<a href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?
																			action=exibirInformarValorParcelasPopupAction
																			&metodo=${EfetuarParcelamentoJudicialActionForm.metodoRemoverParcela}
																			&id=${parcela.numeroParcela}')"><img 
	                        									src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" /></a>
											
											</td>
											<td align="center" width="10%"><bean:write property="numeroParcela" name="parcela"/></td>
											<td align="right" width="70%"><bean:write property="valorParcelaFormatado" name="parcela"/></td>
										</tr>
										<c:set var="count" value="${count+1}"/> 	
								</logic:iterate>
									</table>
								<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaContaParcelamentoJudicialHelper) gt 7 }">																								 
									</DIV>
								</c:if>										
								</td>
							</tr>	
										
								<tr>
									<td height="24" colspan="4">
										<hr>
									</td>
								</tr>										
							</c:if>		             
							<tr>
								<td height="24"><strong>Total do Valor das Parcelas:</strong></td>
								<td>
			               			<html:text maxlength="20" 
			               					   property="totalParcelasInformar"
			               					   size="20"  
			               					   tabindex="7" 
			               					   readonly="true" 
			               					   styleClass="campoBloqueado"/>
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
									<input name="Button" type="button" class="bottonRightCol"
										   value="Desfazer" align="left" id="desfazerButton"/>
									<input type="button" name="ButtonCancelar" class="bottonRightCol"
									       value="Fechar"
										   onClick="javascript:window.close()">
								</td>
								<td colspan="2" align="right">
									<input type="button"
										   name="pesquisarButton" class="bottonRightCol"
										   value="Informar"
										   id="informarButton"/>
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
