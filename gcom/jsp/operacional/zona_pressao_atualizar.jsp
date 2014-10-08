<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.operacional.ZonaAreaOperacional"%>
<%@ page import="gcom.gui.GcomAction"%>


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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarZonaPressaoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(formulario){
		if(validateAtualizarZonaPressaoActionForm(formulario)){
			botaoAvancarTelaEspera('/gsan/atualizarZonaPressaoAction.do');
		}
  	}

	function carregar(tipo){
		var form = document.forms[0];
		
		redirecionarSubmit('exibirAtualizarZonaPressaoAction.do?objetoConsulta=' + tipo);
	}
	
	function removerArea(identificacao){
		
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirAtualizarZonaPressaoAction.do?remover=" + identificacao;
		
		form.submit();	
	}
	
	function adicionarArea(){
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirAtualizarZonaPressaoAction.do?adicionar=OK";
		
		retorno = true;
	
		msg = "";
		retorno = true;

		if(form.idSistemaAbastecimento.value == -1){
			msg += "Informar Sistema de Abastecimento \n";
			retorno = false;
		}
		
		if(form.idSubsistemaAbastecimento.value == -1){
			msg += "Informar Subsistema de Abastecimento \n";
			retorno = false;
		}

		if(form.idSetorAbastecimento.value == -1){
			msg += "Informar Setor de Abastecimento \n";
			retorno = false;
		}

		if(form.idDistritoOperacional.value == -1){
			msg += "Informar Distrito Operacional \n";
			retorno = false;
		}

		if(form.idAreaOperacional.value == -1){
			msg += "Informar Área Operacional \n";
			retorno = false;
		}
		
		if (retorno){
			form.submit();
		}
		else{
			alert(msg);
		}	
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/atualizarZonaPressaoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerZonaPressao">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Zona de Press&atilde;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma zona de press&atilde;o, informe os
					dados abaixo:</td>
				<tr>
					<td width="40"><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarZonaPressaoActionForm" property="id" /></td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="6" maxlength="6" /> </span></td>
				</tr>
				
				<tr>
					<td><strong> Sistema de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idSistemaAbastecimento"
					style="width: 230px;" onchange="carregar(1);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSistemaAbastecimentoAtualizar" scope="session">
					<html:options collection="colecaoSistemaAbastecimentoAtualizar"
						labelProperty="descricao" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Subsistema de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idSubsistemaAbastecimento"
					style="width: 230px;" onchange="carregar(2);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSubsistemaSistemaAbastecimentoAtualizar" scope="session">
					<html:options collection="colecaoSubsistemaSistemaAbastecimentoAtualizar"
						labelProperty="subsistemaAbastecimento.descricao" property="subsistemaAbastecimento.id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Setor de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idSetorAbastecimento"
					style="width: 230px;" onchange="carregar(3);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSetorSubsistemaAbastecimentoAtualizar" scope="session">
					<html:options collection="colecaoSetorSubsistemaAbastecimentoAtualizar"
						labelProperty="setorAbastecimento.descricao" property="setorAbastecimento.id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					 <td  width="40%"class="style3"><strong>Distrito Operacional:<font color="#FF0000">*</font></strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="idDistritoOperacional" tabindex="5" style="width:230px;height:20px;" onchange="carregar(4);">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoDistritoOperacionalAtualizar" scope="session">
								<logic:iterate name="colecaoDistritoOperacionalAtualizar" id="distritoOperacional">
									<html:option value="${distritoOperacional[0]}"> ${distritoOperacional[1]} </html:option>
								</logic:iterate>
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> Área Operacional:</strong><span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td>
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<strong>
											<html:select property="idAreaOperacional" style="width: 230px;">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
												</html:option>
				
												<logic:present name="colecaoAreaOperacionalHelperAtualizar" scope="session">
													
													<html:options collection="colecaoAreaOperacionalHelperAtualizar"
													labelProperty="descricaoAreaOperacional" property="idAreaOperacional" />
							
												</logic:present>
											</html:select> 
										</strong>
									</td>
									<td align="right">
										<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" onclick="adicionarArea();">
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				
				<tr>
				<td colspan="2">									
				  <table width="100%" border="0">						
					<tr>
					  <td colspan="3">
						<table width="100%" cellpadding="0" cellspacing="0">					
							<tr>
								<td colspan="3">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" bgcolor="#99CCFF">
													<tr bgcolor="#99CCFF">
														<td width="20%">
															<div align="center"><strong>Remover</strong></div>
														</td>
														<td width="20%">
															<div align="center"><strong>Principal</strong></div>
														</td>
														<td width="60%">
															<div align="center"><strong>Área Operacional</strong></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			

									<logic:present name="colecaoZonaAreaOperacional" scope="session">

									<div style="width: 100%; height: 100; overflow: auto;">

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%String cor = "#cbe5fe";%>

												<table width="100%" align="center" bgcolor="#99CCFF">

													<logic:iterate name="colecaoZonaAreaOperacional" id="zonaAreaOperacional" type="ZonaAreaOperacional">


														<%if (cor.equalsIgnoreCase("#FFFFFF")) {
															cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%} else {
															cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td align="center" width="20%">																		
																<a href="javascript:removerArea('<%="" + GcomAction.obterTimestampIdObjeto(zonaAreaOperacional) %>')" title="Remover">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																</a>
															</td>
															<td width="20%" align="center">
																<strong> 
																<html:radio property="indicadorPrincipal"
																	value="<%=""+ GcomAction.obterTimestampIdObjeto(zonaAreaOperacional)%>"/>
																</strong>
															</td>
															<td width="60%" align="center" valign="middle"><bean:write
																	name="zonaAreaOperacional" property="areaOperacional.descricao" /></td>
														</tr>
													</logic:iterate>

												</table>
											</td>
										</tr>
									</table>
									</div>
									</logic:present>
								</td>
							</tr>							
					 	</table>
					  </td>
					</tr>																			
				  </table>						
				</td>
			  	</tr>

				<tr>
					<td><strong>Indicador de uso: <font	color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
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
					<td width="35%" align="left" ><input type="button" name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="javascript:window.location.href='/gsan/exibirAtualizarZonaPressaoAction.do?idRegistroAtualizacao=${AtualizarZonaPressaoActionForm.id}'"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

