<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioTipo"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAbrangencia"%>
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
<html:javascript staticJavascript="false"
	formName="FiltrarSolicitacaoAcessoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	window.onmousemove = verificarAcessoAbrangencia;

	function limparDestino(tipo) {
		var form = document.forms[0];

		switch (tipo) {

		case 1: //Funcionario Solicitante	
			form.nomeFuncionarioSolicitante.value = "";
			break;
		case 2: //Funcionario Superior
			form.nomeFuncionarioSuperior.value = "";
			break;
		case 3: //Funcionario
			form.nomeFuncionario.value = "";
			break;
		case 4: //Unidade de Lotacao
			form.nomeLotacao.value = "";
			break;
		}
	}

	function validarForm() {
		var form = document.forms[0];

		if (form.dataInicial.value != '' && form.dataFinal.value != '') {

			if (validaData(form.dataInicial) && validaData(form.dataFinal)) {

				if (comparaData(form.dataInicial.value, "<",
						form.dataFinal.value)
						|| comparaData(form.dataInicial.value, "=",
								form.dataFinal.value)) {

					if (form.idsSituacao.selectedIndex > 0) {
						for (i = 0; i < document.forms[0].elements.length; i++) {
							if (document.forms[0].elements[i].disabled) {
								document.forms[0].elements[i].disabled = false;
							}
						}
						submeterFormPadrao(form);
					} else {
						alert('Informe a Situação.');
					}

				} else {
					alert('Data final do período é anterior à data inicial.');
				}
			}
		} else {
			if (form.idsSituacao.selectedIndex > 0) {
				for (i = 0; i < document.forms[0].elements.length; i++) {
					if (document.forms[0].elements[i].disabled) {
						document.forms[0].elements[i].disabled = false;
					}
				}
				submeterFormPadrao(form);
			} else {
				alert('Informe a Situação.');
			}
		}
	}

	function limparPesquisaFuncionario(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: // Funcionario Superior

			form.idFuncionarioSuperior.value = "";
			form.nomeFuncionarioSuperior.value = "";
			break;

		case 2: // Funcionario

			form.idFuncionario.value = "";
			form.nomeFuncionario.value = "";
			break;

		case 3: // Funcionario Solicitante

			form.idFuncionarioSolicitante.value = "";
			form.nomeFuncionarioSolicitante.value = "";
			break;
		}
	}

	// Valida campo login do usuario aceitando letras
	function validaEnterNaTela(tecla, caminhoActionReload, nomeCampo) {

		var form = document.forms[0];

		var objetoCampo = eval("form." + nomeCampo);
		var valorCampo = eval("form." + nomeCampo + ".value");

		if (document.all) {
			var codigo = event.keyCode;
		} else {
			var codigo = tecla.which;
		}

		if (codigo == 13) {
			if (valorCampo.length > 0
					&& valorCampo.indexOf(',') == -1
					&& valorCampo.indexOf('.') == -1) {

				// Retira os espacos em branco da string passada.
				objetoCampo.value = trim(valorCampo);
				if (objetoCampo.value.length > 0) {
					return pesquisaEnterNatela(tecla, caminhoActionReload,
							objetoCampo);
				}
			}
		} else {
			return true;
		}
	}

	function pesquisaEnterNatela(tecla, caminhoActionReload, objetoPesquisa) {

		var form = document.forms[0];

		if (document.all) {
	        var codigo = event.keyCode;
		}
		else{
	        var codigo = tecla.which;
	    }
	    if (codigo == 13) {
	    
	    
	    	 
			desabilitaCampos();
	        form.action = caminhoActionReload;	
	        toUpperCase(form);
	        form.submit();
	    }
		else{
	        return true;
	    }
	}
	function limparLotacao() {
		var form = document.forms[0];

		form.idLotacao.value = "";
		form.nomeLotacao.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'idFuncionario') {

			form.idFuncionario.value = codigoRegistro;
			form.nomeFuncionario.value = descricaoRegistro;
			form.nomeFuncionario.style.color = "#000000";
			form.action = 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio';
			form.submit();
		} else if (tipoConsulta == 'unidadeOrganizacional') {

			form.idLotacao.value = codigoRegistro;
			form.nomeLotacao.value = descricaoRegistro;
			form.action = 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio';
			form.submit();
		} else if (tipoConsulta == 'idFuncionarioSuperior') {

			form.idFuncionarioSuperior.value = codigoRegistro;
			form.nomeFuncionarioSuperior.value = descricaoRegistro;
			form.nomeFuncionarioSuperior.style.color = "#000000";
			form.action = 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio';
			form.submit();
		} else if (tipoConsulta == 'usuario') {

			form.idFuncionarioSolicitante.value = codigoRegistro;
			form.nomeFuncionarioSolicitante.value = descricaoRegistro;
			form.nomeFuncionarioSolicitante.style.color = "#000000";
			form.action = 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio';
			form.submit();
		} else if (tipoConsulta == 'localidade') {

			form.idLocalidade.value = codigoRegistro;
			form.nomeLocalidade.value = descricaoRegistro;
			form.nomeLocalidade.style.color = "#000000";
			form.action = 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio';
			form.submit();
		}
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,
			campo) {
		if (!campo.disabled) {
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

	function limpaDataFinal() {
		var form = document.forms[0];

		if (form.dataInicial.value == '') {
			form.dataFinal.value = '';
		}
	}

	function limparLocalidade() {
		document.forms[0].idLocalidade.value = '';
		document.forms[0].nomeLocalidade.value = '';
	}

	function desabilitaCampos() {
		var form = document.forms[0];

	}

	function verificarAcessoAbrangencia() {
		var form = document.forms[0];
		//caso a abrangência não tenha valor
		if (form.abrangencia.value == ''
				|| form.abrangencia.value == form.estado.value) {
			form.gerenciaRegional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.gerenciaRegional.value = '';
			form.unidadeNegocio.value = '';
			form.idLocalidade.value = '';
			form.nomeLocalidade.value = '';
		} else {
			if (form.abrangencia.value == form.gerenciaRegionalConstante.value) {
				form.gerenciaRegional.disabled = false;
				form.unidadeNegocio.disabled = true;
				form.idLocalidade.disabled = true;
				form.unidadeNegocio.value = '';
				form.idLocalidade.value = '';
				form.nomeLocalidade.value = '';

			}
			if (form.abrangencia.value == form.unidadeNegocioConstante.value) {
				form.unidadeNegocio.disabled = false;
				form.gerenciaRegional.disabled = true;
				form.idLocalidade.disabled = true;
				form.gerenciaRegional.value = '';
				form.idLocalidade.value = '';
				form.nomeLocalidade.value = '';

			}
			if (form.abrangencia.value == form.localidade.value) {
				form.gerenciaRegional.disabled = true;
				form.unidadeNegocio.disabled = true;
				form.idLocalidade.disabled = false;
				form.unidadeNegocio.value = '';
				form.gerenciaRegional.value = '';
			}
		}
	}

	function limpaCampoPesquisa(campoDescricao) {
		campoDescricao.value = '';
	}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="desabilitaCampos();verificarAcessoAbrangencia();setarFoco('${requestScope.nomeCampo}');">

	<div id="formDiv">
		<html:form action="/filtrarSolicitacaoAcessoAction.do"
			name="FiltrarSolicitacaoAcessoActionForm"
			type="gcom.gui.seguranca.acesso.usuario.FiltrarSolicitacaoAcessoActionForm"
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
								<td class="parabg">Filtrar Solicitação de Acesso <input
									type="hidden" name="estado"
									value="<%=UsuarioAbrangencia.ESTADO%>" /> <input type="hidden"
									name="gerenciaRegionalConstante"
									value="<%=UsuarioAbrangencia.GERENCIA_REGIONAL%>" /> <input
									type="hidden" name="eloPolo"
									value="<%=UsuarioAbrangencia.ELO_POLO%>" /> <input
									type="hidden" name="localidade"
									value="<%=UsuarioAbrangencia.LOCALIDADE%>" /> <input
									type="hidden" name="unidadeNegocioConstante"
									value="<%=UsuarioAbrangencia.UNIDADE_NEGOCIO_INT%>" />
								</td>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">

							<tr>
								<logic:equal name="objeto" value="autorizar" scope="session">
									<td colspan="5">Para Autorizar, informe os dados abaixo:</td>
								</logic:equal>

								<logic:equal name="objeto" value="atualizar" scope="session">
									<td colspan="5">Para Atualizar, informe os dados abaixo:</td>
								</logic:equal>

								<logic:equal name="objeto" value="cadastrar" scope="session">
									<td colspan="5">Para Cadastrar, informe os dados abaixo:</td>
								</logic:equal>

								<logic:equal name="objeto" value="relatorio" scope="session">
									<td colspan="5">Para Gerar o Relatório, informe os dados
										abaixo:</td>
								</logic:equal>

							</tr>
							<td align="right" colspan="5"><logic:equal name="objeto"
									value="atualizar" scope="session">
									<html:checkbox property="atualizar" value="1" tabindex="1" />
									<strong>Atualizar</strong>
								</logic:equal></td>
							<tr>

							</tr>

							<tr>
								<td colspan="5">Solicitante</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Login do
										Usuário:<font color="#FF0000"></font>
								</strong></td>

								<td width="74%" height="24" colspan="3"><html:text
										maxlength="11" property="idFuncionarioSolicitante" size="8"
										tabindex="2" onkeyup="limparDestino(1);"
										onkeypress="validaEnterNaTela(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=1', 'idFuncionarioSolicitante');" />
									<a
									href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'idFuncionarioSolicitante', null, null, 495, 300, '',document.forms[0].idFuncionarioSolicitante);"
									tabindex="3"> <img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Usuário" /></a> <logic:present
										name="funcionarioInexistente2" scope="request">
										<input type="text" name="nomeFuncionarioSolicitante" size="40"
											readonly="true" tabindex="4"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
									</logic:present> <logic:notPresent name="funcionarioInexistente2"
										scope="request">
										<html:text property="nomeFuncionarioSolicitante" size="40"
											readonly="true" tabindex="4"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparPesquisaFuncionario(3);document.forms[0].idFuncionarioSolicitante.focus();"
									tabindex="5"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Período de
										Solicitação:<font color="#ff0000"></font>
								</strong></td>
								<td width="74%" colspan="3"><html:text
										property="dataInicial" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);limpaDataFinal();"
										onkeypress="return isCampoNumerico(event);" tabindex="4" /> <a
									href="javascript:abrirCalendario('FiltrarSolicitacaoAcessoActionForm', 'dataInicial')"
									tabindex="5"> <img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" /></a>&nbsp;

									<html:text property="dataFinal" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);"
										onkeypress="return isCampoNumerico(event);" tabindex="6" /> <a
									href="javascript:abrirCalendario('FiltrarSolicitacaoAcessoActionForm', 'dataFinal')"
									tabindex="7"> <img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
									dd/mm/aaaa</td>
							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="6">Responsável pela Autorização</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000"></font>
								</strong></td>

								<logic:equal name="objeto" value="relatorio" scope="session">
									<td width="74%" height="24" colspan="3"><html:text
											maxlength="8" onkeyup="limparDestino(2);"
											property="idFuncionarioSuperior" size="8" tabindex="7"
											onkeypress="javascript:validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=3', 'idFuncionarioSuperior'); return isCampoNumerico(event);" />
										<a
										href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', null, null, 495, 300, '',document.forms[0].idFuncionarioSuperior); "><img border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											border="0" title="Pesquisar Funcionário" /></a><logic:present name="funcionarioInexistente1" 
											scope="request"><input type="text" name="nomeFuncionarioSuperior" size="40"
												readonly="true" tabindex="7"
												style="background-color: #EFEFEF; border: 0; color: #ff0000"
												value="<bean:message key="pesquisa.funcionario.inexistente"/>" /></logic:present><logic:notPresent name="funcionarioInexistente1"
											scope="request"><html:text property="nomeFuncionarioSuperior" size="40"
												readonly="true" tabindex="7"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notPresent><a
										href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();"
										tabindex="8"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a></td>
								</logic:equal>

								<logic:notEqual name="objeto" value="relatorio" scope="session">

									<logic:equal name="objeto" value="atualizar" scope="session">
										<td width="74%" height="24" colspan="3"><html:text
												maxlength="8" property="idFuncionarioSuperior" size="8"
												tabindex="1" onkeyup="limparDestino(2);"
												onkeypress="javascript:validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=3', 'idFuncionarioSuperior'); return isCampoNumerico(event);" />
											<a
											href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', null, null, 495, 300, '',document.forms[0].idFuncionarioSuperior); ">
												<img border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar Funcionário" /></a> <logic:present name="funcionarioInexistente1" scope="request">
												<input type="text" name="nomeFuncionarioSuperior" size="40"
													readonly="true"
													style="background-color: #EFEFEF; border: 0; color: #ff0000"
													value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
											</logic:present> <logic:notPresent name="funcionarioInexistente1"
												scope="request">
												<html:text property="nomeFuncionarioSuperior" size="40"
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notPresent><a
											href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();">
												<img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</td>
									</logic:equal>

									<logic:notEqual name="objeto" value="atualizar" scope="session">

										<logic:equal name="objeto" value="cadastrar" scope="session">
											<td width="74%" height="24" colspan="3"><html:text
													maxlength="8" property="idFuncionarioSuperior" size="8"
													onkeyup="limparDestino(2);" tabindex="1"
													onkeypress="javascript:validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=3', 'idFuncionarioSuperior'); return isCampoNumerico(event);" />
												<a
												href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', null, null, 495, 300, '',document.forms[0].idFuncionarioSuperior); ">
													<img border="0"
													src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													border="0" title="Pesquisar Funcionário" /></a> <logic:present name="funcionarioInexistente1"
													scope="request">
													<input type="text" name="nomeFuncionarioSuperior" size="40"
														readonly="true"
														style="background-color: #EFEFEF; border: 0; color: #ff0000"
														value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
												</logic:present> <logic:notPresent name="funcionarioInexistente1"
													scope="request">
													<html:text property="nomeFuncionarioSuperior" size="40"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notPresent><a
												href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();">
													<img
													src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" title="Apagar" /></a>
											</td>
										</logic:equal>

										<logic:notEqual name="objeto" value="cadastrar"
											scope="session">
											<td width="74%" height="24" colspan="3"><html:text
													maxlength="8" property="idFuncionarioSuperior" size="8"
													tabindex="1" onkeyup="limparDestino(2);"
													style="background-color:#EFEFEF; border:0; color: #000000" />

												<html:text property="nomeFuncionarioSuperior" size="40"
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</td>
										</logic:notEqual>

									</logic:notEqual>
								</logic:notEqual>
							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="5">Dados do Funcionário</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Empresa:<font
										color="#FF0000"></font></strong></td>
								<td colspan="3"><html:select property="idEmpresa"
										style="width: 230px;" size="1" tabindex="1">

										<logic:notEmpty name="colecaoEmpresa" scope="session">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoEmpresa"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

										<font size="1">&nbsp; </font>
									</html:select></td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000"></font>
								</strong></td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="8" property="idFuncionario" size="8" tabindex="1"
										onkeyup="limparDestino(3);"
										onkeypress="javascript:validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=2', 'idFuncionario'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionario', null, null, 495, 300, '',document.forms[0].idFuncionario); ">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Funcionário" /></a> <logic:present name="funcionarioInexistente" scope="request">
										<input type="text" name="nomeFuncionario" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
									</logic:present> <logic:notPresent name="funcionarioInexistente"
										scope="request">
										<html:text property="nomeFuncionario" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparPesquisaFuncionario(2);document.forms[0].idFuncionario.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</a></td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Nome do
										Usuario:<font color="#FF0000"></font>
								</strong></td>
								<td width="74%" height="24" colspan="3"><html:text
										property="nomeUsuario" size="50" maxlength="50"
										onkeypress="return campoTexto(event, this);" /></td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Unidade de
										Lotação:<font color="#FF0000"></font>
								</strong></td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="4" property="idLotacao" size="4" tabindex="1"
										onkeyup="limparDestino(4);"
										onkeypress="javascript:validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do', 'idLotacao'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?objeto=relatorio&limpaForm=S', null, null, null, 495, 300, '', document.forms[0].nomeLotacao);">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Unidade Organizacional" /></a> <logic:present name="lotacaoInexistente" scope="request">
										<input type="text" name="nomeLotacao" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.lotacao.inexistente"/>" />
									</logic:present> <logic:notPresent name="lotacaoInexistente" scope="request">
										<html:text property="nomeLotacao" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparLotacao();document.forms[0].idLotacao.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</a></td>
							</tr>

							<tr>
								<td width="22%" colspan="1"><strong>Situação
										Solicitação Acesso: <font color="#FF0000">*</font>
								</strong></td>
								<td colspan="4"><logic:equal name="objeto"
										value="relatorio" scope="session">
										<html:select property="idsSituacao" size="4" tabindex="9"
											multiple="true" style="width: 320px;">

											<logic:notEmpty name="colecaoSituacao">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="colecaoSituacao"
													labelProperty="descricao" property="id" />
											</logic:notEmpty>
										</html:select>
									</logic:equal> <logic:equal name="objeto" value="atualizar" scope="session">
										<html:select property="idsSituacao" size="4" tabindex="9"
											multiple="true" style="width: 320px;">

											<logic:notEmpty name="colecaoSituacao">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="colecaoSituacao"
													labelProperty="descricao" property="id" />
											</logic:notEmpty>
										</html:select>
									</logic:equal> <logic:notEqual name="objeto" value="relatorio"
										scope="session">
										<logic:notEqual name="objeto" value="atualizar"
											scope="session">
											<html:select property="idsSituacao" size="4" tabindex="9"
												style="width: 320px;">

												<logic:notEmpty name="colecaoSituacao">
													<html:option
														value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="colecaoSituacao"
														labelProperty="descricao" property="id" />
												</logic:notEmpty>
											</html:select>
										</logic:notEqual>
									</logic:notEqual></td>

							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td width="22%" colspan="1"><strong>Abrangência do
										Acesso:</strong></td>
								<td colspan="4"><html:select property="abrangencia"
										onchange="javascript:verificarAcessoAbrangencia();">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUsuarioAbrangencia"
											labelProperty="descricao" property="id" />
									</html:select></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Gerência
										Regional:</strong></td>
								<td colspan="4"><html:select property="gerenciaRegional">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoGerenciaRegional" labelProperty="nome"
											property="id" />
									</html:select></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Unidade
										Negócio:</strong></td>
								<td colspan="4"><html:select property="unidadeNegocio">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUnidadeNegocio" labelProperty="nome"
											property="id" />
									</html:select></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Localidade:</strong></td>
								<td colspan="4"><html:text property="idLocalidade" size="5"
										maxlength="3"
										onkeypress="validaEnter(event, 'exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&objetoConsulta=4', 'idLocalidade', 'Localidade');"
										onkeyup="limpaCampoPesquisa(document.forms[0].nomeLocalidade);" />
									<a
									href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'idLocalidade', null, null, 495, 300, '',document.forms[0].idLocalidade); ">
										<img width="23" height="21" border="0" title="Pesquisar Localidade"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										style="cursor: hand;" alt="Pesquisar" /></a> <logic:equal name="FiltrarSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="false">
										<html:text property="nomeLocalidade" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:equal> <logic:equal name="FiltrarSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="true">
										<html:text property="nomeLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" maxlength="40" />
									</logic:equal> <a href="javascript:limparLocalidade();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
							</tr>
							<tr>
								<td colspan="5"><hr></td>
							</tr>
							<tr>
								<td colspan="1"><strong>Login: </strong></td>
								<td colspan="4"><html:text property="login" size="11"
										maxlength="11" style="text-transform: none;" /></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Data de
										Nascimento:</strong></td>
								<td colspan="4"><html:text property="dataNascimento"
										size="11" maxlength="10" tabindex="4"
										onkeyup="mascaraData(this, event);" /><a
									href="javascript:abrirCalendario('FiltrarSolicitacaoAcessoActionForm', 'dataNascimento')"><img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="absmiddle"
										alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
							</tr>

							<tr>
								<td width="22%" colspan="1"><strong>Tipo de
										Usuário: </strong></td>
								<td colspan="4"><html:select property="usuarioTipo">

										<logic:notEmpty name="colecaoUsuarioTipo">
											<html:option value="">&nbsp;</html:option>
											<html:options collection="colecaoUsuarioTipo"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>
									</html:select></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Número do CPF:</strong></td>
								<td colspan="4"><html:text property="cpf" size="12"
										maxlength="11" /></td>
							</tr>
							<tr>
								<td width="22%" colspan="1"><strong>Situação do
										Usuário: </strong></td>
								<td colspan="4"><html:select property="usuarioSituacao">

										<logic:notEmpty name="colecaoUsuarioSituacao">
											<html:option value="">&nbsp;</html:option>
											<html:options collection="colecaoUsuarioSituacao"
												labelProperty="descricaoUsuarioSituacao" property="id" />
										</logic:notEmpty>
									</html:select></td>
							</tr>
							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Nível: </strong></td>
								<td width="74%" colspan="3"><html:select property="nivel"
										size="1" tabindex="2" style="width: 320px;">
										<option value="">&nbsp;</option>
										<html:options name="request" collection="colecaoNivel"
											labelProperty="descricao" property="id" />
									</html:select></td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Especial: </strong></td>
								<td width="74%" colspan="3"><html:select property="grupo"
										size="1" tabindex="2" style="width: 320px;">
										<option value="">&nbsp;</option>
										<html:options name="request" collection="colecaoGrupo"
											labelProperty="descricao" property="id" />
									</html:select></td>
							</tr>
							<tr>
								<td colspan="5"><hr></td>
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
								<td align="left" colspan="4"><logic:equal name="objeto"
										value="autorizar" scope="session">
										<input type="button" class="bottonRightCol" value="Limpar"
											onClick="window.location.href='/gsan/exibirFiltrarSolicitacaoAcessoAction.do?objeto=autorizar&menu=sim'" />
									</logic:equal> <logic:equal name="objeto" value="atualizar" scope="session">
										<input type="button" class="bottonRightCol" value="Limpar"
											onClick="window.location.href='/gsan/exibirFiltrarSolicitacaoAcessoAction.do?objeto=atualizar&menu=sim'" />
									</logic:equal> <logic:equal name="objeto" value="cadastrar" scope="session">
										<input type="button" class="bottonRightCol" value="Limpar"
											onClick="window.location.href='/gsan/exibirFiltrarSolicitacaoAcessoAction.do?objeto=cadastrar&menu=sim'" />
									</logic:equal> <logic:equal name="objeto" value="relatorio" scope="session">
										<input type="button" class="bottonRightCol" value="Limpar"
											onClick="window.location.href='/gsan/exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&menu=sim'" />
									</logic:equal> <input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
								</td>

								<td align="right"><input type="button" name="Button"
									class="bottonRightCol" value="Filtrar"
									onClick="javascript:validarForm()" /></td>

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
