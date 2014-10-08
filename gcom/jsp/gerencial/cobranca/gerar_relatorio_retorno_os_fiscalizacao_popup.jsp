<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection, java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	session.removeAttribute("todasAsSituacoes");
	session.removeAttribute("mesAno");
%>
<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%@ page
	import="gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper, gcom.gerencial.bean.ResumoAnoMesRetornoFiscalizacaoHelper"%>
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
function gerarRelatorio(){
	toggleBox('demodiv',1);
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/gerarRelatorioRetornoOSFiscalizacaoAction.do"
	name="GerarRelatorioRetornoOSFiscalizacaoActionForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioRetornoOSFiscalizacaoActionForm"
	method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Gerar Relatório Analítico</td>
						<td bgcolor="#79bbfd" align="center"><strong> </strong></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table border="0">
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td width="25%"><strong>Mês/Ano de Referência::<font
						color="#FF0000">*</font></strong></td>
					<td width="74%">
					<html:select property="mesAno" tabindex="3">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoMesAno"> 
							<logic:iterate name="colecaoMesAno" id="var" type="java.lang.String">	
								<html:option value="${var}">
									<bean:write name="var" />
								</html:option>
							</logic:iterate>		
						</logic:present>	
					</html:select>
					mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Situações Encontradas: </strong></td>
					<td>
						<html:select property="situacoes" tabindex="3" multiple="mutiple" size="4">
							<logic:notEmpty name="situacoes">		
							    <html:option value="">&nbsp;</html:option>					
								<html:options collection="situacoes"
									labelProperty="descricaoFiscalizacaoSituacao" property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td><td><font color="#FF0000">*</font> Campos Obrigatórios</td></tr>
			</table>
			
			
			<table border="0" width="100%">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>	
					<td colspan="3" align="left">
					<input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();">
				    <input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="javascript:history.back();">
					</td>										
					<td colspan="3" align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Gerar Relatório" onclick="javascript:gerarRelatorio()">
					</td>						
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRetornoOSFiscalizacaoAction.do"/>
</html:form>
<body>
</html:html>
