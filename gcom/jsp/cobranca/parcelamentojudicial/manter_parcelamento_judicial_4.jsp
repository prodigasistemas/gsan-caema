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

	function validarIntervaloParcelas(){
		var form = document.forms[0];

		if(form.intervaloInicialPrestacao.value>form.intervaloFinalPrestacao.value){
			alert("Intervalo Inicial de Parcelas Maior que Intervalo Final");
			return false;
		}else{
				return true;
			}
	}

	function validarCamposIntervaloParcelasPreenchimento(numeroMaximoPrestacao){
		var form = document.forms[0];

		if(form.intervaloInicialPrestacao.value!="" && form.intervaloFinalPrestacao.value==""){
				alert("Informe o Intervalo de Prestação Final");
				return false;
		}
		if(form.intervaloFinalPrestacao.value!="" && form.intervaloInicialPrestacao.value==""){
			alert("Informe o Intervalo de Prestação Inicial");
			return false;
		}

		if((form.intervaloInicialPrestacao.value!="" && form.intervaloFinalPrestacao.value!="")
				&& ((form.intervaloInicialPrestacao.value<form.intervaloFinalPrestacao.value)
						 || (form.intervaloInicialPrestacao.value==form.intervaloFinalPrestacao.value))){

					if((form.intervaloInicialPrestacao.value > numeroMaximoPrestacao) 
							&& (form.intervaloFinalPrestacao.value>numeroMaximoPrestacao)){

						alert("Intervalo de prestação inicial e final maior que número máximo de parcelas");	
						return false;
					}else{

						if(form.intervaloInicialPrestacao.value > numeroMaximoPrestacao){
							alert("Intervalo de prestação inicial maior que número máximo de parcelas");
							return false;
						}

						if(form.intervaloFinalPrestacao.value > numeroMaximoPrestacao){
							alert("Intervalo de prestação final maior que número máximo de parcelas");
							return false;
						}				
							
					
					}

					if((form.intervaloInicialPrestacao.value <= numeroMaximoPrestacao)
							&& (form.intervaloFinalPrestacao.value <= numeroMaximoPrestacao)){
						return true;						
					}
			}	

	}
		
	
	function validarCamposIntervaloParcelasIntervaloValidoInicial(numeroMaximoPrestacao){
		var form = document.forms[0];

		if(form.intervaloInicialPrestacao.value > numeroMaximoPrestacao){
			alert("Quantidade de parcela informada maior que total do parcelamento.");
			form.intervaloInicialPrestacao.value="";
			return false;
		}else{
			return true;
		}
		
	}

	function validarCamposIntervaloParcelasIntervaloValidoFinal(numeroMaximoPrestacao){
		var form = document.forms[0];

		if(form.intervaloFinalPrestacao.value > numeroMaximoPrestacao){
			alert("Quantidade de parcela informada maior que total do parcelamento.");
			form.intervaloFinalPrestacao.value="";
			return false;
		}else{
			return true;
		}
		
	}

	function emitirParcelas(idParcelamentoJudicial, debitoTipo,numeroMaximoPrestacao){
		var form = document.forms[0];

		var msg="";
		var Radio=null;

		Radio= form.tipoParcelaEmissao;
		for(var i=0;i<Radio.length;i++) {
			if(Radio[i].checked) {

					if(form.intervaloInicialPrestacao.value!="" || form.intervaloFinalPrestacao.value!=""){
						if(validarIntervaloParcelas()){
							if(validarCamposIntervaloParcelasPreenchimento(numeroMaximoPrestacao)){

								form.action = 'emitirParcelasParcelamentoJudicialAction.do?idParcelamentoJudicial='+idParcelamentoJudicial+
								'&debitoTipo='+debitoTipo+'&tipoParcelaEmissao='+Radio[i].id+'&intervaloInicialPrestacao='+form.intervaloInicialPrestacao.value+
								'&intervaloFinalPrestacao='+form.intervaloFinalPrestacao.value;

								form.submit();
							}							
						}
						
					}else{
								form.action = 'emitirParcelasParcelamentoJudicialAction.do?idParcelamentoJudicial='+idParcelamentoJudicial+
								'&debitoTipo='+debitoTipo+'&tipoParcelaEmissao='+Radio[i].id+'&intervaloInicialPrestacao='+form.intervaloInicialPrestacao.value+
								'&intervaloFinalPrestacao='+form.intervaloFinalPrestacao.value;
		
								form.submit();
						}
				}
			}
		}
	
	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5">
		
		<div id="formDiv">
		<html:form action="/emitirParcelasParcelamentoJudicialAction"
		 name="ManterParcelamentoJudicialActionForm"
		 type="gcom.cobranca.parcelamentojudicial.ManterParcelamentoJudicialActionForm"
		 method="post">
			
		<table width="700" border="0" cellspacing="5" cellpadding="0">
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
							<td class="parabg">Emitir Parcelas Parcelamento Judicial</td>
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
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>
					
					<table>
						<tr>
						<td><strong>Cliente Respons&aacute;vel:</strong></td>
						<td><input type="text" disabled="disabled" value="${idCliente}"></td>
						<td><input type="text" disabled="disabled" size="50%" value="${nomeCliente}"></td>
						</tr>
						<tr>
						<td><strong>N° Processo Judicial:</strong></td>
						<td><input type="text" disabled="disabled" value="${numeroProcesso}"><td>
						<td></td>
						</tr>
						<tr>
						<td><strong>Data Parcelamento:</strong></td>
						<td><input type="text" disabled="disabled" value="${dataParcelamento}"></td>
						</tr>
					</table>
					
					<table>
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>					
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center"><strong>Dados do Parcelamento</strong></td>
					
					</tr>
					
					</table>
					
					<table>
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>
					
					<c:if test="${fn:length(colecaoParcelasParcelamentoJudicial) gt 9 }">																								 
										<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
					</c:if>
					<table width="100%" bgcolor="#90c7fc">
					
					<tr bgcolor="#90c7fc">
					
					<td align="center"><strong>Parcela N° Prestações</strong></td>
					<td align="center"><strong>Data Vencimento</strong></td>
					<td align="center"><strong>Valor da Prestação<strong></td>
					<td align="center"><strong>Situação</strong></td>
					
					</tr>
					
					
					<% String cor = "#cbe5fe";%>
						<logic:iterate name="colecaoParcelasParcelamentoJudicial" id="obter">
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe" height="18">		
					<%}%>
					<td align="center"><bean:write name="obter" property="numeroPrestacao"/>/<bean:write name="obter" property="numeroPrestacaoTotal"/></td>
					<td align="center"><bean:write name="obter" property="dataVencimento"/></td>
					<td align="center"><bean:write name="obter" property="valorPrestacao"/></td>
					<td align="center"><bean:write name="obter" property="situacao"/></td>
					</tr>
					</logic:iterate>
					
					</table>
					<c:if test="${fn:length(colecaoParcelasParcelamentoJudicial) gt 9 }">																								 
						</DIV>
					</c:if>
					<table>
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center"><strong>Dados para Emissão</strong></td>
					
					</tr>
					
					</table>
					
					<table>
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>
					
					<table>
					
					<tr>
					
					<td><strong>Parcelamento para Emissão:</strong></td>
					<td>
					<input type="radio" name="tipoParcelaEmissao" id="3" value="3">Parcelas A Vencer
					<input type="radio" name="tipoParcelaEmissao" id="2" value="2">Parcelas Vencidas não Pagas
					<input type="radio" name="tipoParcelaEmissao" id="1" value="1" checked="checked">Todas
					</td>
					
					</tr>
					
					<tr>
					<td><strong>Intervalo de Parcelas:</strong></td>
					<td><input type="text" size="4" id="intervaloInicialPrestacao"
					 onblur="somente_numero_zero_a_nove(this);" maxlength="3"
					 onkeyup="javaScript:somente_numero_zero_a_nove(this);validarCamposIntervaloParcelasIntervaloValidoInicial(${tamanhoColecaoParcelasParcelamentoJudicial});"><strong>a</strong>
					 <input type="text" size="4" onkeyup="javaScript:somente_numero_zero_a_nove(this);validarCamposIntervaloParcelasIntervaloValidoFinal(${tamanhoColecaoParcelasParcelamentoJudicial});"
					 onblur="somente_numero_zero_a_nove(this);"
					 id="intervaloFinalPrestacao" maxlength="3"></td>
					</tr>
					</table>
					
					<table>
					
					<tr>
					
					<td></td>
					
					</tr>
					
					</table>
					
					<hr>
					
					<table width="100%">
					
					<tr>
					
					<td colspan="3" align="left"><input type="button" value="Fechar" class="bottonRightCol" onclick="fechar();"></td>
					<td align="right"><input type="button" value="Emitir" class="bottonRightCol" onclick="emitirParcelas(${idParcelamentoJudicial},${debitoTipo},${tamanhoColecaoParcelasParcelamentoJudicial});"></td>
					</tr>
					
					</table>	
					
					
								
			</td>
			
			</tr>
			
			</table>
			 
		 </html:form>
		</div>
	</body>
</html:html>