<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
 
<script language="JavaScript" >
 	function validarForm(){
 		var form = document.forms[0];
 		form.submit();	
 	}
 	
</script>

</head>

<body>

<html:form action="/atualizarMotivoNaoGeracaoCobrancaResultadoAction"
	name="AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm"
	method="post">
	<html:hidden property="id"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="123" valign="top" class="leftcoltext">
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
    
    <td width="625" valign="top" class="centercoltext">
    
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar Motivo Não Geração Cobrança por Resultado</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
      
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para filtrar o(s) motivo(s) de não geração cobrança por resultado, informe os dados abaixo:</td>
        </tr>

		<tr>
          <td width="30%"><strong>Código:<font color="#ff0000"></font></strong></td>
          <td width="70%">
			<html:text  maxlength="2" property="codigo" size="2" readonly="true" style="background-color: #EFEFEF"/>
          </td>
        </tr>
        
        <tr>
	        <td width="30%"><strong>Descrição:<font color="#ff0000">*</font></strong></td>
	        <td width="70%">
			<!--<html:textarea cols="37" property="descricao" rows="4" onchange="limitarTextArea(this)"/>-->
				<html:textarea property="descricao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].descricao, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
				<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
			</td>
        </tr>
        <tr>
          <td width="30%"><strong>Descrição Abreviada:<font color="#ff0000"></font></strong></td>
          <td width="70%">
			<html:text maxlength="10" property="descricaoAbreviada" size="9" />
          </td>
        </tr>
    	
    	<tr>
			<td><strong>Tipo do Motivo:<font color="#ff0000">*</font></strong></td>
			     <td>
			         <html:radio property="tipoMotivo" value="1" /> <strong>Imóvel</strong>
			         <html:radio property="tipoMotivo" value="2" /> <strong>Conta</strong>
			         <html:radio property="tipoMotivo" value="3" /> <strong>Pagamento</strong>
			   </td>
		</tr>
			<tr>
				<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
				<td>						
					<html:radio property="indicadorUso" 
						value="1"/>
					<strong>Ativo</strong>
					<html:radio	property="indicadorUso"
						value="2"/>
					<strong>Inativo </strong>
				</td>
				
			    <tr>
		          <td>&nbsp;</td>
		        </tr>
   		    
			    <tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2"><font color="#FF0000"> 
					 <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Voltar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarMotivoNaoGeracaoCobrancaResultadoAction.do?menu=sim'" />
					<input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="javascript:window.location.href='/gsan/exibirAtualizarMotivoNaoGeracaoCobrancaResultadoAction.do?idMotivoAtualizar=<bean:write name="AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm" property="id" />'" />
					<input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right">
					  <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Atualizar"
						onClick="validarForm()">
					</td>
				</tr>
		
       </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
