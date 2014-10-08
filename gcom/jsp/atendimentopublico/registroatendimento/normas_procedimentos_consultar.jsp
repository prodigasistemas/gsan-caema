<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="NormaProcedimentosActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validateNormaProcedimentosActionForm(form)){
			submeterFormPadrao(form);
		}
	}

</script>
</head>
<body leftmargin="5" topmargin="5"
onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/consultarNormasProcedimentosAction"
	name="NormaProcedimentosActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.NormaProcedimentosActionForm"
	method="post"
	onsubmit="return validateNormaProcedimentosActionForm(this);">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="120" valign="top" class="leftcoltext">
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
		<td valign="top" class="centercoltext">
            <table>
              <tr><td></td></tr>
              
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Consultar Normas e Procedimentos</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>

			<p>&nbsp;</p>
			<table width="100%" border="0">

				 <tr>
			          <td width="100%" colspan=2>
				          <table width="100%" border="0">
				          	<tr>
				          		<td>Para consultar normas e procedimentos no sistema, informe os dados abaixo:</td>
							</tr>
				          </table>
			          </td>
			     </tr>
				
				<tr> 
                	<td><strong>Tipo de Documento:</strong></td>
                	<td><html:select property="idTipoDocumento" tabindex="1">
                    	<html:option value="-1">&nbsp;</html:option>
                    	<html:options collection="colecaoNormaDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id" />
                    </html:select> <font size="1">&nbsp; </font></td>
              	</tr>

             	<tr> 
                	<td><strong> Título:</strong></td>
                	<td>
                		<html:text property="descTitulo" size="50" maxlength="50" tabindex="2"/>
                  	</td>
              	</tr>	
              	<tr>
					<td>&nbsp;</td>
					<td>
						<input type="radio" name="tipoPesquisaTitulo" value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" checked="checked">Iniciando pelo texto
						<input type="radio" name="tipoPesquisaTitulo" value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>">Contendo o texto
					</td>
				</tr>
         	
         	    <tr> 
                	<td><strong> Assunto:</strong></td>
                	<td>
                		<html:textarea property="descAssunto" cols="50" rows="4"  tabindex="3"
                				onkeypress="return desabilitarEnter(this, event)"
								onkeyup="limitTextArea(document.forms[0].descAssunto, 200, document.getElementById('utilizado'), document.getElementById('limite'));"  />
								<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
                  	</td>
              	</tr>	
              	<tr>
					<td>&nbsp;</td>
					<td>
						<input type="radio" name="tipoPesquisaAssunto" value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" checked="checked">Iniciando pelo texto
						<input type="radio" name="tipoPesquisaAssunto" value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>">Contendo o texto
					</td>
				</tr>

				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirConsultarNormasProcedimentosAction.do?desfazer=S"/>'" >
                   	</td>
                   	<td  align="right">
						<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.NormaProcedimentosActionForm);" url="consultarNormasProcedimentosAction.do"/>
					</td>
				</tr>


			</table>
			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
