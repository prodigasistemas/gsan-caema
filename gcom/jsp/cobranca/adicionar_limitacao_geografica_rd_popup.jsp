<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="java.util.Collection, java.util.Iterator, gcom.cobranca.LimitacaoGeograficaRDHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html> 
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">


function validarForm(){
	var form = document.AdicionarLimitacaoGeograficaRDPopupActionForm;
	if (form.dataVigenciaInicio.value == '' || form.dataVigenciaFim.value == ''){
		alert('Informe data de vigência.');	 
	} else {
		submeterFormPadrao(form);
	}
}
 
function pesquisaDados(tipo){
	var form = document.forms[0];
	
	form.action = "exibirAdicionarLimitacaoGeograficaRDPopupAction.do?tipoPequisa="+tipo;
	form.submit();
}



</script>

<script language="JavaScript">
function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}
</script>
</head>

<logic:equal name="fechaPopup" value="false" scope="session">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(750, 600);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>
<logic:equal name="fechaPopup" value="true" scope="session">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(750, 600);chamarReload('${sessionScope.retornarTela}');window.close();" >
</logic:equal>
</head>


 <html:form action="/adicionarLimitacaoGeograficaRDPopupAction"
	name="AdicionarLimitacaoGeograficaRDPopupActionForm"
	type="gcom.gui.cobranca.AdicionarLimitacaoGeograficaRDPopupActionForm"
	method="post">
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="500" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Limita&ccedil;&atilde;o Geogr&aacute;fica na Resolu&ccedil;&atilde;o Diretoria</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" border="1">
        <tr>
          <td colspan="4">Para limitar a resolu&ccedil;&atilde;o de
					diretoria, informe os dados abaixo:</td>
        </tr>
       <tr>
			<td width="218"><strong>N&uacute;mero RD:</strong></td>
			<td width="393"><html:text property="numero" size="15"
						maxlength="15" disabled="true"/></td>
		</tr>
        <tr>
			<td height="25"><strong>Data limite de vencimento da conta sobre valor d&eacute;bito para pagamento &agrave; vista:</strong></td>
			<td align="right">
				<div align="left"><html:text property="dataLimiteVencimentoContaVista" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AdicionarLimitacaoGeograficaRDPopupActionForm', 'dataLimiteVencimentoContaVista')" />
				dd/mm/aaaa</div>
			</td>
		</tr>
		
        <tr>
			<td height="25"><strong>Data limite de vencimento da conta sobre valor d&eacute;bito para pagamento parcelado:</strong></td>
			<td align="right">
				<div align="left"><html:text property="dataLimiteVencimentoContaParcelar" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AdicionarLimitacaoGeograficaRDPopupActionForm', 'dataLimiteVencimentoContaParcelar')" />
				dd/mm/aaaa</div>
			</td>
		</tr>
		
    	<tr>
			<td height="25"><strong>Per&iacute;odo de Vig&ecirc;ncia RD:<font
					color="#FF0000">*</font></strong></td>
					<td colspan="3"><strong> <html:text property="dataVigenciaInicio" 
						size="10" maxlength="10" onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataVigenciaFim,this);"/>
					a</strong> <html:text property="dataVigenciaFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);"
					 /> 
					dd/mm/aaaa</td>
		</tr>
		<tr>
		   <td width="30%"><strong>Ger&ecirc;ncia Regional:</strong> </td>
		   <td width="70%">
		       <html:select property="idGerenciaRegional" onchange="javascript:pesquisaDados(1);">
		        	<logic:notEmpty name="colecaoGerenciaRegional" scope="request">
			          	<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
			            <html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
		            </logic:notEmpty>
		       </html:select>
	    	</td>
		</tr>
		
		<tr>
		    <td><strong>Unidade de Neg&oacute;cio:</strong> </td>
		     <td>
		    <html:select property="idUnidadeNegocio" onchange="javascript:pesquisaDados(2);">
		         <logic:notEmpty name="colecaoUnidadeNegocio" scope="request">
				   	<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
				     <html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
		       	</logic:notEmpty>
		    </html:select>
		  	</td>
   	 	</tr>  
   	 	
   	 	<tr>
		   <td width="30%"><strong>Localidade:</strong> </td>
		   <td width="70%">
		       <html:select property="idLocalidade" style="width: 350px; height:100px;" multiple="true" onchange="javascript:pesquisaDados(3);">
		        	<logic:notEmpty name="colecaoLocalidade" scope="request">
			            <html:options collection="colecaoLocalidade" labelProperty="descricao" property="id" />
		            </logic:notEmpty>
		       </html:select>
	    	</td>
		</tr>
		<tr>
		   <td width="30%"><strong>Setor Comercial:</strong> </td>
		   <td width="70%">
		       <html:select property="codigoSetorComercial" style="width: 350px; height:100px;" multiple="true" onchange="javascript:pesquisaDados(4);">
		        	<logic:notEmpty name="colecaoSetorComercial" scope="request">
			            <html:options collection="colecaoSetorComercial" labelProperty="codigo" property="codigo" />
		            </logic:notEmpty>
		       </html:select>
	    	</td>
		</tr>
   	 	
   	 	<tr>
		   <td width="30%"><strong>Quadra:</strong> </td>
		   <td width="70%">
		       <html:select property="numeroQuadra" style="width: 350px; height:100px;" multiple="true">
		        	<logic:notEmpty name="colecaoQuadra" scope="request">
			            <html:options collection="colecaoQuadra" labelProperty="descricao" property="numeroQuadra" />
		            </logic:notEmpty>
		       </html:select>
	    	</td>
		</tr>
   	 
		<tr>
			<td><strong> <font color="#FF0000"></font></strong></td>
			<td align="right">
			<div align="left"><strong><font color="#FF0000">*</font></strong>
			Campos obrigat&oacute;rios</div>
			</td>
		</tr>
	
          	    
	        <tr>
    	      <td>&nbsp;</td>
	          <td>&nbsp;</td>
        	</tr>
    	
	  			 <tr>
					<td><font color="#FF0000"> 
					<input type="button" name="Button" class="bottonRightCol"
							value="Limpar"
							onclick="window.location.href='<html:rewrite page="/exibirAdicionarLimitacaoGeograficaRDPopupAction.do?limparForm=true"/>'">
					</font></td>
				
				
					 <td align="right">
						<input name="Button" 
							type="button"
							class="bottonRightCol" 
							value="Atualizar"
							onclick="validarForm(document.forms[0]);" />
		
					</td> 
				</tr>
	  </table>
	</td>
	</tr>
	</table>
</html:form>
</html:html> 


