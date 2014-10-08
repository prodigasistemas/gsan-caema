<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
	<script type="text/javascript">

	function fechar(){
		window.close();
	}

	function emitirGuiasAtraso(){
		var form = document.forms[0];
		if(verificarPreenchimentoIdGuiaAtraso()){
			form.action ='cancelarGuiaAtrasoParcelamentoJudicialAction.do';
			form.submit();
		}
		
	}

	function verificarPreenchimentoIdGuiaAtraso(){
		var form = document.forms[0];
		var idChecked = form.idsGuiaAtraso;
		var verificaChecked=false;

		if(idChecked.length>0){

			for (var i=0;i<idChecked.length;i++){  
				if (idChecked[i].checked == true){  

					verificaChecked=true;
				}  
			}

			if(verificaChecked==true){
				var retorno = true;
			}else{

				alert("Nenhuma guia de atraso selecionada para o cancelamento");
				var retorno = false;
			}
				
		
		}else{

				if (idChecked.checked == true){  

					verificaChecked=true;
				}  

				if(verificaChecked==true){

					var retorno = true;

				}else{

					alert("Nenhuma guia de atraso selecionada para o cancelamento");
					var retorno = false;
			}
				
		}

		return retorno;

	}
	

	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5">
		<div id="formDiv">
		<html:form action="/cancelarGuiaAtrasoParcelamentoJudicialAction"
		 name="ManterParcelamentoJudicialActionForm"
		 type="gcom.cobranca.parcelamentojudicial.ManterParcelamentoJudicialActionForm"
		 method="post">
			
		<table width=700 border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
					<table height="100%">
		
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Cancelar Guias de Atraso do Parcelamento Judicial</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center"><strong>Dados Gerais</strong></td>
					
					</tr>
					
					</table>
					
					<table>
					
					<logic:iterate name="colecaoObterListaParcelamentoJudicial" id="obter">
					
					<tr>
					
					<td><strong>N° Processo Judicial:</strong></td>
					<td><input type="text" size="23" disabled="disabled" value="<bean:write name="obter" property="numeroProcesso"/>"></td>
					
					</tr>
					
					<tr>
					
					<td><strong>Cliente Respons&aacute;vel:</strong></td>
					<td><input type="text" disabled="disabled" size="50%" value="<bean:write name="obter" property="clienteResponsavel"/>"></td>
					
					</tr>
					
					<tr>
					
					<td><strong>Im&oacute;vel Principal:</strong></td>
					<td><input type="text" disabled="disabled" value="<bean:write name="obter" property="idImovel"/>"></td>
					
					</tr>
					
					<tr>
					
					<td><strong>Data do Parcelamento:</strong></td>
					<td><input type="text" disabled="disabled" value="<bean:write name="obter" property="dataParcelamento"/>"></td>
					
					</tr>
					
					</logic:iterate>
					
					</table>
					
				
					<hr>
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center"><strong>Selecionar</strong></td>
					<td align="center"><strong>Valor</strong></td>
					<td align="center"><strong>Data Vencimento</strong></td>
					<td align="center"><strong>Observação</strong></td>
					
					</tr>
					
					<% String cor = "#cbe5fe";%>
						
					<logic:iterate name="colecaoGuiaAtraso" id="obter2">
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
						<%}%>
					
					
					<td align="center" width="10%"><html:multibox property="idsGuiaAtraso" value="${obter2.idGuia}"></html:multibox></td>
					<td align="center" width="10%"><bean:write name="obter2" property="valor"/></td>
					<td align="center" width="30%"><bean:write name="obter2" property="dataVencimento"/></td>
					<td align="center" width="60%"><bean:write name="obter2" property="observacao"/></td>
					
					</tr>
					
					</logic:iterate>
					</table>
					
					<br>
	
					<table width="100%">
					
					<tr>
					
					<td align="left"><input type="button" class="bottonRightCol" value="Cancelar" onclick="fechar();"></td>
					<td align="right"><input type="button" class="bottonRightCol" value="Concluir" onclick="emitirGuiasAtraso();"></td>
					
					</tr>
					
					
					
					</table>
					
					</table>
				
					
					</td>
					</tr>
			</table>
			</html:form>
			</div>
			</body>
			</html:html>