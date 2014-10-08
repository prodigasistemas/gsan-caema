<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirZonaPressaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirZonaPressaoActionForm(form)){
			botaoAvancarTelaEspera('/gsan/inserirZonaPressaoAction.do');
		}
	}

	function carregar(tipo){
		var form = document.forms[0];
		
		redirecionarSubmit('exibirInserirZonaPressaoAction.do?objetoConsulta=' + tipo);
	}

	function removerArea(identificacao){
		
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirInserirZonaPressaoAction.do?remover=" + identificacao;
		
		form.submit();	
	}

	function adicionarArea(){
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirInserirZonaPressaoAction.do?adicionar=OK";

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
	
	function redirecionarSubmitAtualizar(idZonaPressao) {
		urlRedirect = "/gsan/exibirAtualizarZonaPressaoAction.do?idRegistroInseridoAtualizar=" + idZonaPressao;
		redirecionarSubmit(urlRedirect);
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<div id="formDiv">
<html:form action="/inserirZonaPressaoAction.do"
	name="InserirZonaPressaoActionForm"
	type="gcom.gui.operacional.InserirZonaPressaoActionForm"
	method="post"
	onsubmit="return validateInserirZonaPressaoActionForm(this);">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Zona de Press&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir a zona de press&atilde;o, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					
    				<td height="30" width="200"><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="descricao" maxlength="30"
						size="30" tabindex="1"/><br>
					</td>
				</tr>
				<tr>
					
    				<td><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>
					
    				<td><strong><html:text property="descricaoAbreviada" size="6"
						maxlength="6" tabindex="2"/> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Sistema de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idSistemaAbastecimento"
					style="width: 230px;" onchange="carregar(1);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSistemaAbastecimento" scope="session">
					<html:options collection="colecaoSistemaAbastecimento"
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

					<logic:present name="colecaoSubsistemaSistemaAbastecimento" scope="session">
					<html:options collection="colecaoSubsistemaSistemaAbastecimento"
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

					<logic:present name="colecaoSetorSubsistemaAbastecimento" scope="session">
					<html:options collection="colecaoSetorSubsistemaAbastecimento"
						labelProperty="setorAbastecimento.descricao" property="setorAbastecimento.id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					 <td  width="40%"class="style3"><strong>Distrito Operacional:<font color="#FF0000">*</font></strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="idDistritoOperacional" tabindex="5" style="width:230px;height:20px;" onchange="carregar(4);">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoDistritoOperacionalInserir" scope="session">
								<logic:iterate name="colecaoDistritoOperacionalInserir" id="distritoOperacional">
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
				
												<logic:present name="colecaoAreaOperacionalHelper" scope="session">
													
													<html:options collection="colecaoAreaOperacionalHelper"
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
					
				    <td>&nbsp;</td>
									
				    <td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				<tr>
					<td><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirZonaPressaoAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					
    				<td height="24" align="right">
						<input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
