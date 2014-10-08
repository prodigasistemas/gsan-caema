<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ExibirInserirSolicitacaoAcessoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>

<script language="JavaScript">
function validateExibirDefinirUsuarioAcompanhamentoEquipesActionForm (){ return true;}

function chamarPopupPesquisaUsuario() {
	var form = document.forms[0];
	if (!form.idUsuario.disabled && !form.idUsuario.readonly) {
		chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',form.idUsuario);
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
		tipoConsulta) {
	var form = document.forms[0];

	if ('usuario' == tipoConsulta) {
		form.idUsuario.value = codigoRegistro;
		form.nomeUsuario.value = descricaoRegistro;
 		form.nomeUsuario.style.color = "#000000";
		form.action = 'exibirDefinirUsuarioAcompanhamentoEquipesAction.do?objeto=1';
		form.submit();		
	}	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, campo) {
	if (!campo.disabled && !campo.readonly) {
		if (objeto == null || codigoObjeto == null) {
			if (tipo == "") {
				abrirPopup(url, altura, largura);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
						+ codigoObjeto, altura, largura);
			}
		}
	}
}

function limparUsuario() {
 	var form = document.forms[0];
 	form.action = 'exibirDefinirUsuarioAcompanhamentoEquipesAction.do?menu=sim';
 	form.submit();                  
}

function validarForm() {
	var form = document.forms[0];
	
	if (form.idUsuario.value == '' || form.nomeUsuario.value == '') {
		alert('Informe Matrícula/CPF do Usuário.');
	} else {
		botaoAvancarTelaEspera('/gsan/definirUsuarioAcompanhamentoEquipesAction.do');
	}
}

function facilitador(objeto, valor){
	if (objeto.value == "0"){
		objeto.value = "1";
		
		if(valor == "1"){
			marcarTodosListBox("unidadesMarcadas");
		}else if(valor == "2"){
			marcarTodosListBox("empresasMarcadas");
		}else{
			marcarTodosListBox("naturezaEquipeMarcadas");
		}
	}
	else{
		objeto.value = "0";
		
		if(valor == "1"){
			desmarcarTodosListBox("unidadesMarcadas");
		}else if(valor == "2"){
			desmarcarTodosListBox("empresasMarcadas");
		}else{
			desmarcarTodosListBox("naturezaEquipeMarcadas");
		}
	}	
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">	

	<div id="formDiv">
		<html:form action="/definirUsuarioAcompanhamentoEquipesAction.do"
			name="ExibirDefinirUsuarioAcompanhamentoEquipesActionForm"
			type="gcom.gui.atendimentopublico.ordemservico.ExibirDefinirUsuarioAcompanhamentoEquipesActionForm"
			method="post">

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
					<td width="600" valign="top" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>

						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Definir Usuário para Acompanhamento das Equipes</td>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">

							<tr>
								<td colspan="5">Para definir o usuário para acompanhamento das equipes, informe os dados abaixo:</td>
							</tr>
							
							<tr>
								<td width="26%" colspan="2"><strong>Matrícula/CPF do Usuário:<font color="#FF0000">*</font></strong></td>
								<td width="74%" height="24" colspan="3"><strong>
									<html:text maxlength="11" tabindex="9" property="idUsuario" size="9" 
									onkeyup="limparDestino(2);"
									onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirDefinirUsuarioAcompanhamentoEquipesAction.do?objeto=1', 'idUsuario'); return isCampoNumerico(event);"
									/>
									<a
									href="javascript:chamarPopupPesquisaUsuario();">
									<img name="imagem" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/>
									</a>
															
					   		        <logic:present name="usuarioNaoEncontrado" scope="request">
									    <html:text maxlength="40" property="nomeUsuario" size="40" style="background-color:#EFEFEF; border:0; color: #ff0000" readonly="true"/>
			                        </logic:present>
			                        <logic:notPresent name="usuarioNaoEncontrado" scope="request">
									    <html:text maxlength="40" property="nomeUsuario" size="40" style="background-color:#EFEFEF; border:0; color: #000000" readonly="true"/>
			                        </logic:notPresent>
								<a	href="javascript:limparUsuario();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
								</strong></td>
							</tr>
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>
							
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<logic:present name="colecaoUnidadesOrganizacionais">
						        <tr>
									<td colspan="5"><strong>Selecione as Unidades onde o usuário terá acesso:</strong>								 
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" colspan="6">
										<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0" cellspacing="0">
											<tr bordercolor="#000000">
												<td width="18%" bgcolor="#90c7fc">
													<div align="center"><strong><a href="javascript:facilitador(this,1);">Todos</a></strong>
													</div>
												</td>
												<td width="80%" bgcolor="#90c7fc"><strong>Unidade Organizacional</strong>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<div style="width: 600px; height: 150px; overflow: auto;">
														<table border="0" bgcolor="#99CCFF" width="100%">
															<%int cont = 0;%>
															
																<logic:iterate name="colecaoUnidadesOrganizacionais"
																	id="unidade">
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																	<tr bgcolor="#cbe5fe">
																<%} else {%>
																	<tr bgcolor="#FFFFFF">
																<%}%>
																		<td width="18%">
																			<div align="center"><strong> <html:multibox
																				property="unidadesMarcadas"
																				value="${unidade.id}" /> </strong></div>
																		</td>
																		<td width="80%"><bean:write name="unidade"
																			property="descricao" /></td>
																	</tr>
																</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:present>
		
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							 <tr>
								<td colspan="5"><html:checkbox property="excluirUnidade">
									<strong>Excluir Todas as Unidades Administrativas</strong></html:checkbox>
								</td>
							</tr>
							
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>
							
							<logic:present name="colecaoEmpresas">
						        <tr>
									<td colspan="5"><strong>Selecione as Empresas que o usuário terá acesso:</strong></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" colspan="6">
										<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0" cellspacing="0">
											<tr bordercolor="#000000">
												<td width="18%" bgcolor="#90c7fc">
													<div align="center"><strong><a href="javascript:facilitador(this,2);">Todos</a></strong>
													</div>
												</td>
												<td width="80%" bgcolor="#90c7fc"><strong>Empresa</strong></td>
											</tr>
											<tr>
												<td colspan="2">
													<div style="width: 600px; height: 100px; overflow: auto;">
														<table border="0" bgcolor="#99CCFF" width="100%">
															<%int cont = 0;%>
															
																<logic:iterate name="colecaoEmpresas" id="empresa">
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																	<tr bgcolor="#cbe5fe">
																<%} else {%>
																	<tr bgcolor="#FFFFFF">
																<%}%>
																		<td width="18%">
																			<div align="center"><strong> <html:multibox
																				property="empresasMarcadas"
																				value="${empresa.id}" /> </strong></div>
																		</td>
																		<td width="80%"><bean:write name="empresa"
																			property="descricao" /></td>
																	</tr>
																</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:present>
		
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							 <tr>
								<td colspan="5"><html:checkbox property="excluirEmpresa">
									<strong>Excluir Todas as Empresas</strong></html:checkbox>
								</td>
							</tr>
							
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>
							
							<logic:present name="colecaoNaturezaEquipe">
						        <tr>
									<td colspan="5"><strong>Selecione as Natureza de Equipe que o usuário terá acesso:</strong></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" colspan="6">
										<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0" cellspacing="0">
											<tr bordercolor="#000000">
												<td width="18%" bgcolor="#90c7fc">
													<div align="center"><strong><a href="javascript:facilitador(this,3);">Todos</a></strong>
													</div>
												</td>
												<td width="80%" bgcolor="#90c7fc"><strong>Natureza de Equipe</strong></td>
											</tr>
											<tr>
												<td colspan="2">
													<div style="width: 600px; height: 150px; overflow: auto;">
														<table border="0" bgcolor="#99CCFF" width="100%">
															<%int cont = 0;%>
															
																<logic:iterate name="colecaoNaturezaEquipe" id="naturezaEquipe">
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																	<tr bgcolor="#cbe5fe">
																<%} else {%>
																	<tr bgcolor="#FFFFFF">
																<%}%>
																		<td width="18%">
																			<div align="center"><strong> <html:multibox
																				property="naturezaEquipeMarcadas"
																				value="${naturezaEquipe.id}" /> </strong></div>
																		</td>
																		<td width="80%"><bean:write name="naturezaEquipe"
																			property="descricao" /></td>
																	</tr>
																</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:present>
		
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							 <tr>
								<td colspan="5"><html:checkbox property="excluirNaturezaEquipe">
									<strong>Excluir Todas as Naturezas de Equipe</strong></html:checkbox>
								</td>
							</tr>
							
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td colspan="2">&nbsp;</td>
								<td align="left" colspan="3"><font color="#FF0000">*</font>
									Campo Obrigatório</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td align="left" colspan="4"><input type="button"
									class="bottonRightCol" value="Limpar"
									onClick="window.location.href='/gsan/exibirDefinirUsuarioAcompanhamentoEquipesAction.do?menu=sim'" />

									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
								</td>

								<td align="right"><input type="button" name="Button"
									class="bottonRightCol" value="Atualizar"
									onClick="javascript:validarForm()" />
								</td>

							</tr>
						</table>
						<p>&nbsp;</p>
					</td>
				</tr>
			</table>

			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</div>
</body>
</html:html>
