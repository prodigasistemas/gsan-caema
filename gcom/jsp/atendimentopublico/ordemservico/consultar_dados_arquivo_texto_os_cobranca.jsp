<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="true"
	formName="GerarArquivoTextoOrdensServicoSmartphoneActionForm" />

</head>
<body leftmargin="5" topmargin="5">

	<html:form
		action="/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do"
		name="ConsultarDadosArquivoTextoOSCobrancaSmartphoneActionForm"
		type="gcom.gui.mobile.ConsultarDadosArquivoTextoOSCobrancaSmartphoneActionForm"
		method="post">

		<input type="hidden" name="tipoPesquisa" />

		<%@ include file="/jsp/util/cabecalho.jsp"%>

		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellpadding="0" cellspacing="5">

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

					<table height="100%">

						<tr>

							<td></td>

						</tr>

					</table>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">

						<tr>

							<td width="11"><img
								src="<bean:message key="caminho.imagens"/>parahead_left.gif"
								border="0" /></td>

							<td class="parabg">Consultar Dados dos Arquivos Texto para a Execução das OS</td>

							<td width="11" valign="top"><img
								src="<bean:message key="caminho.imagens"/>parahead_right.gif"
								border="0" /></td>

						</tr>

					</table>

					<p>&nbsp;</p>

					<table width="100%" border="0">

						<tr>
							<td colspan="3">Para consultar os arquivos, selecione entre os filtros abaixo:</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
		
							<td width="25%">
								<strong>Empresa:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								
								<logic:present name="colecaoEmpresa"> 
									<html:select property="empresa"  style="width: 200px;font-size:11px;" tabindex="9">
										<html:option value="">&nbsp;</html:option>
										<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
									</html:select>
								</logic:present>
								
								<logic:notPresent name="colecaoEmpresa"> 
									
									<html:text property="descricaoEmpresa" size="28"
										maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								
								</logic:notPresent>
					
							</td>
		
						</tr>
						
						<tr>
		
							<td width="25%">
								<strong>Tipo da Ordem de Serviço:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								
								<html:hidden property="idTipoOS"/>
								<html:text property="descricaoTipoOS" size="28"
										maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										
							</td>
		
						</tr>
						
						

						<tr>

							<td>

								<p>&nbsp;</p>

							</td>

							<td colspan="2"><font color="#FF0000">*</font> Campos
								Obrigat&oacute;rios</td>

						</tr>

						<tr>

							<td>

								<p>&nbsp;</p>

							</td>

							<td colspan="2"></td>

						</tr>

					</table>

					<table width="100%">

						<tr>

							<td width="100%">

								<table width="100%" border="0" cellpadding="0" cellspacing="0">

									<tr>

										<td width="12"><input type="button"
											class="bottonRightCol" value="Cancelar"
											style="margin-right: 220px;"
											onclick="window.location.href='/gsan/telaPrincipal.do'" /></td>

										<td width="61">&nbsp;</td>

										<td width="61"><input type="button"
											class="bottonRightCol" onclick="javascript:gerarRelatorio();"
											value="Gerar Relat&oacute;rio" /></td>

										<td width="12"><input type="button"
											class="bottonRightCol" onclick="javascript:validarForm(1)"
											value="Gerar Arquivo TXT" /></td>
									</tr>
								</table>

							</td>

						</tr>

					</table>

					<p>&nbsp;</p>

				</td>

			</tr>

		</table>

		<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=?" />
		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>

</body>

</html:html>

