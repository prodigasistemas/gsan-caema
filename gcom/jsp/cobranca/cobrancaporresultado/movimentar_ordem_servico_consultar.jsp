<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

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
 	
 	function reload(){
 		 	 document.forms[0].action = 'movimentarOrdemServicoWizardAction.do?destino=4&action=exibirMovimentarOrdemServicoConsultarOSAction';
 			 document.forms[0].submit();
 	}
 	
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
	 	if(tipoConsulta == "localidade"){
	 		form.idLocalidade.value = codigoRegistro;
	 		form.descricaoLocalidade.value = descricaoRegistro;
	 		form.descricaoLocalidade.style.color = "#000000";
	 	}
	 }
 	
 	function validateMovimentarOrdemServicoActionForm(form) {
		var form =  document.forms[0];
		
		return true;
    }
 	
 	function replicarData(parametro) {
		var form =  document.forms[0];
	
		if(parametro == "1")
			form.dataGeracaoFinalOS.value = form.dataGeracaoInicialOS.value;
		else
			form.dataEncerramentoFinalOS.value = form.dataEncerramentoInicialOS.value;
	}
 	
</script>

</head>

<body>

<html:form action="/movimentarOrdemServicoWizardAction"
	name="MovimentarOrdemServicoActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoActionForm"
	method="post"
	onsubmit="return validateMovimentarOrdemServicoActionForm(this);" >

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=4"/>

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
    	<input type="hidden" name="limparConsulta" value="false"/>	    
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Movimentar Ordem de Serviço - Consultar OS</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
      
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para Consultar OS para comandos de contas em cobrança por empresa, informe os dados abaixo:</td>
        </tr>

		<tr>
          <td width="26%"><strong>Comando de Conta de Cobrança:<font color="#ff0000"></font></strong></td>
          <td width="74%">
			<html:text maxlength="40" property="idComandoContaCobranca" size="20" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
          </td>
        </tr>
        
        <tr>
			<td colspan="2" width="100%">
			<hr>
			</td>
		</tr>
		
		<tr>
			<td width="30%"><strong>Gerência Regional:</strong></td>
			<td colspan="2"><html:select property="idsGerenciaRegional" tabindex="3" multiple="mutiple" size="4" onblur="javascript:reload()" >
				<html:option value="-1">TODAS</html:option>
				<logic:notEmpty name="colecaoGerenciaRegional">
					<html:options collection="colecaoGerenciaRegional"
						labelProperty="nome" property="id" />
				</logic:notEmpty>
			</html:select></td>
		</tr>
		<tr>
			<td width="30%"><strong>Unidade Negócio:</strong></td>
			<td colspan="2"><html:select property="idsUnidadeNegocio" tabindex="3" multiple="mutiple" size="4" onblur="javascript:reload()" >
				<html:option value="-1">TODAS</html:option>
				<logic:notEmpty name="colecaoUnidadeNegocio">
					<html:options collection="colecaoUnidadeNegocio"
						labelProperty="nome" property="id" />
				</logic:notEmpty>
			</html:select></td>
		</tr>	
			 
			 
		<tr>
			<td width="30%"><strong>Localidade:</strong></td>
			<td colspan="2"><html:select property="idsLocalidade" tabindex="3" multiple="mutiple" size="4" >
				<html:option value="-1">TODAS</html:option>
				<logic:notEmpty name="colecaoLocalidade">
					<html:options collection="colecaoLocalidade"
						labelProperty="descricao" property="id" />
				</logic:notEmpty>
			</html:select></td>
		</tr>	
				 
			<tr>
				<td width="30%"><strong>Situação da Ordem de Serviço:</strong></td>
				<td width="70%"><strong> <span class="style1"><strong> 
				<html:radio property="situacaoOS" value="1" tabindex="4" /> <strong>Pendentes 
				<html:radio property="situacaoOS" value="2" tabindex="5" /> Encerradas
				<html:radio property="situacaoOS" value="3" tabindex="6" /> Todas
				</strong></strong></span></strong></td>
			</tr>	 
				 
			<tr>
				<td width="120"><strong>Data da Geração da Ordem de Serviço:</strong></td>

				<td colspan="2"><strong> 
					<html:text maxlength="10"
						property="dataGeracaoInicialOS" size="10"
						onkeypress="javascript:return (isCampoNumerico(event) && verificaData(event));"
	           			onchange="campoNumerico(this);"
	           			onkeyup="mascaraData(this, event);replicarData(1);" 
					/> 
					<a href="javascript:abrirCalendarioReplicando('MovimentarOrdemServicoActionForm', 'dataGeracaoInicialOS', 'dataGeracaoFinalOS');">

				<img border="0"
					src="<bean:message key="caminho.imagens"/>calendario.gif"
					width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a 
					<html:text maxlength="10"
						property="dataGeracaoFinalOS" size="10"
						onkeypress="javascript:return (isCampoNumerico(event) && verificaData(event));"
	           			onchange="campoNumerico(this);"
	           			onkeyup="mascaraData(this, event);"/> 
				<a href="javascript:abrirCalendario('MovimentarOrdemServicoActionForm', 'dataGeracaoFinalOS');">
				<img border="0"
					src="<bean:message key="caminho.imagens"/>calendario.gif"
					width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>	
				</strong>
				dd/mm/aaaa</td>
			</tr>
			
			<tr>
				<td width="120"><strong>Data do Encerramento da Ordem de Serviço:</strong></td>

				<td colspan="2"><strong> <html:text maxlength="10"
					property="dataEncerramentoInicialOS" size="10"
					onkeypress="javascript:return (isCampoNumerico(event) && verificaData(event));"
           			onchange="campoNumerico(this);"
           			onkeyup="mascaraData(this, event);replicarData(2);"/> 
           		<a href="javascript:abrirCalendarioReplicando('MovimentarOrdemServicoActionForm', 'dataEncerramentoInicialOS', 'dataEncerramentoFinalOS');">
				<img border="0"
					src="<bean:message key="caminho.imagens"/>calendario.gif"
					width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
				a
				<html:text maxlength="10"
					property="dataEncerramentoFinalOS" size="10"
					onkeypress="javascript:return (isCampoNumerico(event) && verificaData(event));"
           			onchange="campoNumerico(this);"
           			onkeyup="mascaraData(this, event);"/> 
           		<a href="javascript:abrirCalendario('MovimentarOrdemServicoActionForm', 'dataEncerramentoFinalOS');">
				<img border="0"
					src="<bean:message key="caminho.imagens"/>calendario.gif"
					width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>	
				</strong>
				dd/mm/aaaa</td>
			</tr>	 
			
			<tr>
				<td width="30%"><strong>Tipo de Relatório:</strong></td>
				<td width="70%"><strong> <span class="style1"><strong> 
				<html:radio property="tipoRelatorio" value="1" tabindex="4" /> <strong>Sintético
				<html:radio property="tipoRelatorio" value="2" tabindex="5" /> Analítico
				</strong></strong></span></strong></td>
			</tr>
				        
			<tr>
	          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=4"/></div></td>
	        </tr>
        
       </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
