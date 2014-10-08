<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
	<title>COMPESA - GSAN</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
	<script type="text/javascript">

	function voltar(){
		var form = document.forms[0];

		form.action='exibirManterParcelamentoJudicialAction.do?menu=sim';
		form.submit();
	}

	function emitirGuiasParcelamentoJudicial(idParcelamentoJudicial,debitoTipo){
		var form = document.forms[0];

		form.action='emitirGuiasParcelamentoJudicialAction.do?idParcelamentoJudicial='+idParcelamentoJudicial+'&debitoTipo='+debitoTipo;
		form.submit();
	}

	function abrirPopupEmitirParcelasParcelamentoJudicial(idParcelamentoJudicial,debitoTipo){

		abrirPopup("exibirEmitirParcelasParcelamentoJudicialAction.do?idParcelamentoJudicial="+idParcelamentoJudicial+"&debitoTipo="+debitoTipo+"&idCliente="+idCliente.value
			+"&nomeCliente="+nomeCliente.value+"&numeroProcesso="+numeroProcesso.value+"&dataParcelamento="+dataParcelamento.value, 500, 720);
	}

	function abrirPopupCancelarGuiaAtrasoParcelamentoJudicial(idParcelamentoJudicial,debitoTipo){

		abrirPopup("exibirCancelarGuiaAtrasoParcelamentoJudicialAction.do?idParcelamentoJudicial="+idParcelamentoJudicial+"&debitoTipo="+debitoTipo, 400, 720); 
		
	}
	
	function abrirPopupCancelarParcelamentoJudicial(idParcelamentoJudicial){
		abrirPopup("exibirCancelarParcelamentoJudicialAction.do?idParcelamentoJudicial="+idParcelamentoJudicial, 325, 633); 
	}
	
	function reexibir(){
		redirecionarSubmit('manterParcelamentoJudicialConsultarParcelamentoJudicialAction.do?id='+document.forms[0].idParcelamentoJudicial.value);
	}
	
	function imprimirResumo(idParcelamentoJudicial){
		var form = document.forms[0];
		form.action='gerarRelatorioResumoParcelamentoJudicialAction.do?idParcelamentoJudicial='+idParcelamentoJudicial;
		form.submit();
	}

	function visualizarArquivo(identificacao){

		abrirPopup("exibirManterParcelamentoJudicialAction.do?visualizar=" + identificacao, 400, 720);
	}

	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5">
		<div id="formDiv">
		<html:form action="/emitirGuiasParcelamentoJudicialAction"
		 name="ManterParcelamentoJudicialActionForm"
		 type="gcom.cobranca.parcelamentojudicial.ManterParcelamentoJudicialActionForm"
		 method="post">
			
		<input type="hidden" name="tipoPesquisa" />
		
			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>
			
			<table width="770" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="150" valign="top" class="leftcoltext">
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
							<td class="parabg">Consultar Parcelamento Judicial </td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					
					<table>
					
					<td></td>
					
					</table>
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center"><strong>Dados do Cliente</strong></td>
					
					</tr>
					
					</table>
					
					<table>
					
					<td></td>
					
					</table>
					
				<table width="100%">
						
						<logic:iterate name="colecaoManterParcelamentoJudicialConsultarParcelamentoJudicial" id="obter">
						
						<tr>
						<td><strong>Cliente Respons&aacute;vel:</strong></td>
						<td><input type="text" size="26" disabled="disabled" id="idCliente" value="<bean:write name="obter" property="idCliente"/>"></td>
						<td align="left"><input type="text" disabled="disabled" id="nomeCliente" size="40%" value="<bean:write name="obter" property="nomeCliente"/>"></td>
						</tr>
						<tr>
						<td><strong>Situação:</strong></td>
						<td colspan="2"><input type="text"  size="26" disabled="disabled" value="<bean:write name="obter" property="situacao"/>"></td>
						</tr>
						<tr>
						<td><strong>Data do Parcelamento:</strong></td>
						<td colspan="2"><input type="text"   size="26" disabled="disabled" id="dataParcelamento" value="<bean:write name="obter" property="dataParcelamento"/>"></td>
						</tr>
						<tr>
						<td><strong>Advogado Respons&aacute;vel:</strong></td>
						<td colspan="2"><input type="text" disabled="disabled" size="60%" value="<bean:write name="obter" property="nomeAdvogado"/>"></td>
						</tr>
						<tr>
						<td><strong>N° Processo Judicial:</strong></td>
						<td><input type="text" size="26" disabled="disabled" id="numeroProcesso" value="<bean:write name="obter" property="numeroProcesso"/>"></td>
						<td align="left" width="10%" valign="middle">
						  <a href="javascript:visualizarArquivo('<bean:write name="obter" property="idParcelamentoJudicial"/>')" title="Visualizar Arquivo">
						  <IMG SRC="<bean:message key="caminho.imagens"/>PDF.gif" width="35" height="35" BORDER="0" ALT=""></a>	
						</td>
						</tr>
						
					</table>
					
					<table>
					
					<td></td>
					
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
					
					<table width="100%" bgcolor="#90c7fc">
					
					<tr>
					
					<td align="center" width="25%"><strong>Débitos Atualizados</strong></td>
					<td align="center" width="25%"><strong>Valor do Acordo</strong></td>
					<td align="center" width="25%"><strong>Desconto</strong></td>
					<td align="center" width="25%"><strong>Juros</strong></td>
					</tr>
					
					<tr bgcolor="#FFFFFF">
					
					<td align="center"><bean:write name="obter" property="debitoAtualizado"/></td>
					<td align="center"><bean:write name="obter" property="valorAcordo"/></td>
					<td align="center"><bean:write name="obter" property="desconto"/></td>
					<td align="center"><bean:write name="obter" property="juros"/></td>
					
					</tr>
					
					<tr>
					<td align="center"><strong>Valor Entrada</strong></td>
					<td align="center"><strong>Valor Custas</strong></td>
					<td align="center"><strong>Vl. Honor&aacute;rios</strong></td>
					<td align="center"><strong>Valor Parcelamento</strong></td>
					
					</tr>
					
					<tr bgcolor="#FFFFFF">
					<td align="center"><bean:write name="obter" property="valorEntrada"/></td>
					<td align="center"><bean:write name="obter" property="custas"/></td>
					<td align="center"><bean:write name="obter" property="honorarios"/></td>
					<td align="center"><bean:write name="obter" property="valorParcelamento"/></td>
					
					</tr>
					
					</table>
					
					<table width="100%">
					
					<td></td>
					
					</table>
					
					<!--[if IE]>
								
								<input type="button" class="bottonRightCol" style="width: 100px" value="Emitir Custas" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,121);">
								<input type="button" class="bottonRightCol" style="width: 120px" value="Emitir Honorários" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,122);">
								<input type="button" class="bottonRightCol" style="width: 180px" value="Emitir Adicionais por Atraso" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,123);">
								<input type="button" class="bottonRightCol" style="width: 100px" value="Emitir Entrada" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,118);">
								<input type="button" class="bottonRightCol" style="width: 100px" value="Emitir Parcelas" onclick="abrirPopupEmitirParcelasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,119);">
								<input type="button" class="bottonRightCol" style="width: 110px" value="Imprimir Resumo" onclick="javascript:imprimirResumo(<bean:write name="obter" property="idParcelamentoJudicial"/>);">
								<input type="button" class="bottonRightCol" style="width: 160px" value="Cancelar Parcelamento">
								<input type="button" class="bottonRightCol" style="width: 180px" value="Cancelar Adicionais Atraso" onclick="abrirPopupCancelarGuiaAtrasoParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,123);">
								
					<![endif]-->
					
					<![if !IE]>
								<input type="hidden" name="idParcelamentoJudicial" value="<bean:write name="obter" property="idParcelamentoJudicial"/>" />
								<input type="button" class="bottonRightCol" value="Emitir Custas" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,121);">
								<input type="button" class="bottonRightCol" value="Emitir Honorários" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,122);">
								<input type="button" class="bottonRightCol" value="Emitir Adicionais por Atraso" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,123);">
								<input type="button" class="bottonRightCol" value="Emitir Entrada" onclick="javascript:emitirGuiasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,118);">
								<input type="button" class="bottonRightCol" value="Emitir Parcelas" onclick="abrirPopupEmitirParcelasParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,119);">
								<input type="button" class="bottonRightCol" value="Imprimir Resumo" onclick="javascript:imprimirResumo(<bean:write name="obter" property="idParcelamentoJudicial"/>);">
								<input type="button" class="bottonRightCol" value="Cancelar Parcelamento" onclick="abrirPopupCancelarParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>)"/>
								<input type="button" class="bottonRightCol" value="Cancelar Adicionais Atraso" onclick="abrirPopupCancelarGuiaAtrasoParcelamentoJudicial(<bean:write name="obter" property="idParcelamentoJudicial"/>,123);">
					<![endif]>
					
					<p>&nbsp;</p>
					
					<input type="button" class="bottonRightCol" value="Voltar" onclick="javascript:voltar();">
					<input type="button" class="bottonRightCol" value="Cancelar" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					
					<p>&nbsp;</p>
					
					</logic:iterate>
					
					
							
			</td>
			
			</tr>
			
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%> 
		 </html:form>
		</div>
	</body>
</html:html>