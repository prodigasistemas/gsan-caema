<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>
function setCookie(c_name,value,exdays)
{
var exdate=new Date();
exdate.setDate(exdate.getDate() + exdays);
var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
document.cookie=c_name + "=" + c_value;
}

</script>
<script>

setCookie("desativaHistoryBack", "true", "1");

</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(700, 530);">

<html:form action="/exibirDetalharMaterialUtilizadoPopupAction" 
	name="DetalharMaterialUtilizadoPopupActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.DetalharMaterialUtilizadoPopupActionForm"
	method="post">

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Detalhamento do Material Utilizado</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
           		<!-- Dados das Atividades -->
				<tr>
					<td>

                    	<table width="100%" border="0" bgcolor="#99CCFF">

                       		<tr bgcolor="#cbe5fe">
								<td>
									<table width="100%" bgcolor="#99CCFF">
						 				<tr bordercolor="#90c7fc" bgcolor="#90c7fc"> 
											<td width="50%" ><div align="center"><strong>Descri&ccedil;&atilde;o da Atividade da Ordem de Servi&ccedil;o</strong></div></td>
										</tr>
										<tr>
											<c:set var="count" value="0"/>
											<logic:iterate id="helper" name="DetalharMaterialUtilizadoPopupActionForm" property="colecaoOSAtividade" type="gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper" scope="session">
			                     		  		<c:set var="count" value="${count+1}"/>
				                        		<c:choose>
				                        			<c:when test="${count%2 == '1'}">
				                        				<tr bgcolor="#FFFFFF">
				                        			</c:when>
				                        			<c:otherwise>
				                        				<tr bgcolor="#cbe5fe">
				                        			</c:otherwise>
				                        		</c:choose>											
													<td bordercolor="#90c7fc">
							                        	<div align="center">
															<bean:write name="helper" property="materialUtilizado.descricao"/>
														</div>
													</td>
													<td bordercolor="#90c7fc">
							                        	<div align="center">
															<bean:write name="helper" property="qtdeMaterial"/> <bean:write name="helper" property="materialUnidade.descricao"/>
														</div>
													</td>
												</tr>
											</logic:iterate>
									</table>
								</td>       						
                       		</tr>

						</table>
   						
  					</td>
      			</tr>						      			

				<tr>
					<td>
						<table width="100%">
			              	<tr>
			            		<td>
		              				<div align="left">
	                  					<input name="ButtonVoltar" type="button" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();">
		              				</div>
			              		</td>
			            		<td>
		              				<div align="right">
	                  					<input name="ButtonFechar" type="button" class="bottonRightCol" value="Fechar" onclick="javascript:window.close();">
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
<!-- Fim do Corpo - Leonardo Regis -->
</html:form>
</body>
</html:html>