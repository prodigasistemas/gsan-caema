<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ExibirInserirSolicitacaoAcessoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript">
$(document).ready(function(){
	verificarAcessoAbrangencia();
	verificarFuncionario();

	$('[name=idTipoUsuario]').change(function(){
		limparCampos();
	});
	
});

function obterPermissoes(){
	var form = document.forms[0];
	if (form.grupo.value!= -1) {	
		//Obtem as permissoes especiais
		form.action = "exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=5&nivel="+form.nivel.value+"&especial="+form.grupo.value;
		form.submit();		
	}	
}

function verificarFuncionario(){
	var form = document.forms[0];
	if (form.idFuncionario.value!=null && form.idFuncionario.value!= ''){
		form.nomeUsuario.setAttribute("readOnly",true);		
	}
}
function limpaCampoPesquisa(campoDescricao){
	 campoDescricao.value = '';
}
function verificarAcessoAbrangencia(){
	var form = document.forms[0];
	var select = form.abrangencia.selectedIndex;
	var codigo = form.abrangencia[select].value;
	
	if (codigo==2){
		form.gerenciaRegional.disabled = false;
		form.gerenciaRegional.removeAttribute("disabled", 0);
		
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
		
	} else if (codigo==3){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
	} else if (codigo==4){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idLocalidade.disabled = false;
		form.idLocalidade.removeAttribute("disabled", false);
	} else if (codigo==5){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = false;
		form.unidadeNegocio.removeAttribute("disabled", 0);
		
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
	
	} else {
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';		
	}
}
function limparLocalidade() {
 	document.forms[0].idLocalidade.value = '';
 	document.forms[0].nomeLocalidade.value = '';
}

function validarForm() {
		var form = document.forms[0];
		var msg = '';
		
		if (validateExibirInserirSolicitacaoAcessoActionForm(form)) {

			if (form.dataInicial.value != '' && form.dataFinal.value != '') {

				if (form.dataInicial && form.dataFinal) {

					if (comparaData(form.dataInicial.value, "<",
							form.dataFinal.value)
							|| comparaData(form.dataInicial.value, "=",
									form.dataFinal.value)) {

						msg += validarAcessos();
						
						msg += validarIdadeUsuario();
						
						msg += verificarUsuarioTipo();
						
					} else {
						msg += 'Data final do período é anterior à data inicial. \n';
					}
					
					if((form.grupo.value == '-1' || form.grupo.value == '') && (form.nivel.value=='-1' || form.nivel.value=='')){
						msg += 'Informe Nível ou Especial. \n';
					}
					
					if (msg!=''){
						alert(msg);
					} else {
						botaoAvancarTelaEspera('/gsan/inserirSolicitacaoAcessoAction.do');
					}
				}
			}
		}
	}

function validarAcessos(){
	var msg = '';
	var form = document.forms[0];
	var select = form.abrangencia.selectedIndex;
	var codigo = form.abrangencia[select].value;
	
	if (codigo==2 && form.gerenciaRegional.selectedIndex ==0){
		msg += 'Informe Gerencial Regional. \n'		
	} 
	if (codigo==4 && form.idLocalidade.value==''){
		msg += 'Informe Localidade. \n';		
	} 
	if (codigo==5 && form.unidadeNegocio.selectedIndex ==0){
		msg += 'Informe Unidade de Negócio. \n';		
	} 
	if (codigo==0){
		msg += 'Informe Abrangência de Acesso. \n';		
	}
	
	msg += verificarEmail();	
	
	return msg;
}
function verificarEmail(){
	var msg = '';
	var form = document.forms[0];
	if(form.idTipoUsuario.value==2){
		var mailSplip1 = form.email.value.split('@');
		var mailSplip2 = mailSplip1[1].split('.');
		var mail = "@"+mailSplip2[0];
		if (form.dominioEmail.value==mail || form.dominioEmail.value==mail.toUpperCase()){
			return msg;
		} else {
			msg += 'E-mail inválido. Necessário informar o e-mail corporativo da empresa. \n';			
		}
	} 
	
	return msg;	
}

	function limparPesquisaFuncionario(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: // Funcionario Superior
			form.idFuncionarioSuperior.value = "";
			form.nomeFuncionarioSuperior.value = "";
			break;

		case 2: // Funcionario
			if (!form.idFuncionario.disabled){
				form.idFuncionario.value = "";
				form.nomeFuncionario.value = "";
				limparCampos();
			}
			break;
		}
	}

	function limparPesquisaUsuarioSolicitante() {
		var form = document.forms[0];

		form.idFuncionarioSolicitante.value = "";
		form.nomeFuncionarioSolicitante.value = "";		
	}	
	
	function limparPesquisaUsuario() {
		var form = document.forms[0];

		form.loginUsuarioPesquisa.value = '';
		form.nomeUsuarioPesquisa.value = '';
		form.idTipoUsuario.value = '-1';

		form.idEmpresa.value = '-1';

		form.idFuncionario.value = '';
		form.nomeFuncionario.value = '';
		form.nomeUsuario.value = '';
		form.cpf.value = '';
		form.dataNascimento.value = '';
		form.idLotacao.value = '';
		form.nomeLotacao.value = '';
		form.login.value = '';	
		form.email.value = '';
		form.dataInicial.value = '';
		form.dataFinal.value = '';
		form.usuarioPesquisaNaoEncontrado.value = 'true';
		//desabilitarCamposUsuarioPesquisa();
		
		form.abrangencia.value = '';
		form.gerenciaRegional.value = '';
		form.unidadeNegocio.value='';
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
	}

	function limparPesquisaUsuario2() {
		var form = document.forms[0];

		//form.loginUsuarioPesquisa.value = '';
		form.nomeUsuarioPesquisa.value = '';
		form.idTipoUsuario.value = '-1';

		form.idEmpresa.value = '-1';

		form.idFuncionario.value = '';
		form.nomeFuncionario.value = '';
		form.nomeUsuario.value = '';
		form.cpf.value = '';
		form.dataNascimento.value = '';
		form.idLotacao.value = '';
		form.nomeLotacao.value = '';
		form.login.value = '';	
		form.email.value = '';
		form.dataInicial.value = '';
		form.dataFinal.value = '';
		form.usuarioPesquisaNaoEncontrado.value = 'true';
		//desabilitarCamposUsuarioPesquisa();
		
		form.abrangencia.value = '';
		form.gerenciaRegional.value = '';
		form.unidadeNegocio.value='';
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
	}
	
	function limparLotacao() {
		var form = document.forms[0];
		if (!form.idLotacao.disabled){
			form.idLotacao.value = "";
			form.nomeLotacao.value = "";
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'funcionario') {

			form.idFuncionario.value = codigoRegistro;
			form.nomeFuncionario.value = descricaoRegistro;
			form.nomeFuncionario.style.color = "#000000";
			form.action = 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=2';
			
			form.submit();
		} else if ('unidadeOrganizacional' == tipoConsulta) {

			form.idLotacao.value = codigoRegistro;
			form.nomeLotacao.value = descricaoRegistro;
			form.action = 'exibirInserirSolicitacaoAcessoAction.do';
			form.submit();
		} else if (tipoConsulta == 'idFuncionarioSuperior') {

			form.idFuncionarioSuperior.value = codigoRegistro;
			form.nomeFuncionarioSuperior.value = descricaoRegistro;
			form.nomeFuncionarioSuperior.style.color = "#000000";
			form.action = 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=1';
			form.submit();
		} else if ('unidadeEmpresa' == tipoConsulta) { 
		 	document.forms[0].idLotacao.value = codigoRegistro;
		 	document.forms[0].action = 'exibirFiltrarUsuarioAction.do';
		 	submeterFormPadrao(document.forms[0]);
	 	} else if ('localidadeDestino' == tipoConsulta) { 
		 	document.forms[0].idLocalidade.value = codigoRegistro;
		 	document.forms[0].nomeLocalidade.value = descricaoRegistro;
		 	form.action = 'exibirInserirSolicitacaoAcessoAction.do';
			form.submit();
	 	} else if ('usuario' == tipoConsulta) {
	 		if (form.tipoPesquisa.value == 'usuarioPesquisa') {
	 			form.loginUsuarioPesquisa.value = codigoRegistro;
				form.nomeUsuarioPesquisa.value = descricaoRegistro;
		 		form.nomeUsuarioPesquisa.style.color = "#000000";
				form.action = 'exibirInserirSolicitacaoAcessoAction.do?consultaPopup=true&objetoConsulta=3';
				form.submit();
			} else if ('usuarioResponsavel' == form.tipoPesquisa.value) {
				form.idUsuarioResponsavel.value = codigoRegistro;
				form.nomeUsuarioResponsavel.value = descricaoRegistro;
				form.action = 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=4';
				form.submit();
			} else if ('usuarioSolicitante' == form.tipoPesquisa.value) {
				form.idFuncionarioSolicitante.value = codigoRegistro;
				form.nomeFuncionarioSolicitante.value = descricaoRegistro;
				form.action = 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=6';
				form.submit();
			}
			
		}
		
	}

	function chamarPopupPesquisaFuncionario() {
		if (!document.forms[0].idFuncionario.disabled && !document.forms[0].idFuncionario.readOnly) {
			chamarPopup('exibirFuncionarioPesquisar.do?', 'idFuncionario', null, null, 495, 300, '',document.forms[0].idFuncionario);			
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
	function chamarPopupElo(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
		}			
	}
	
	function chamarPopupPesquisaUsuario() {
		if (!document.forms[0].loginUsuarioPesquisa.disabled && !document.forms[0].loginUsuarioPesquisa.readonly) {
			document.forms[0].tipoPesquisa.value = 'usuarioPesquisa';
			
			chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].loginUsuarioPesquisa);
		}
	}
	
	function chamarPopupPesquisaUsuarioResponsavel() {
		if (!document.forms[0].idUsuarioResponsavel.disabled && !document.forms[0].idUsuarioResponsavel.readonly) {
			document.forms[0].tipoPesquisa.value = 'usuarioResponsavel';
			
			chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].idUsuarioResponsavel); 
		}
	}
	
	function chamarPopupPesquisaUsuarioSolicitante() {
		if (!document.forms[0].idFuncionarioSolicitante.disabled && !document.forms[0].idFuncionarioSolicitante.readonly) {
			document.forms[0].tipoPesquisa.value = 'usuarioSolicitante';
			
			chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].idFuncionarioSolicitante); 
		}
	}		
	function limpaDataFinal() {
		var form = document.forms[0];

		if (form.dataInicial.value == '') {
			form.dataFinal.value = '';
		}
	}

	function validaFuncionario() {

		var form = document.forms[0];
		var retorno = true;

		if (form.cpf.value == null || form.cpf.value == '') {
			alert("Informe o CPF");
			retorno = false;
		}

		if (form.dataNascimento.value == null
				|| form.dataNascimento.value == '') {
			alert("Informe a Data de Nascimento ");
			retorno = false;
		}

		return retorno;
	}

	function desabilitaCampos() {
		var form = document.forms[0];

		//if (form.usuarioPesquisaNaoEncontrado.value == 'true'){
			
			var tipoUsuario = returnObject(form, "idTipoUsuario");
			var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;
		
			if (indicadorPrestadorServico.length > 0
					&& indicadorPrestadorServico == '8') {
	
				form.idFuncionario.setAttribute("readOnly",true);	//FUNCIONARIO			
				form.nomeUsuario.removeAttribute("readOnly", false);				
				
				form.idFuncionario.value = '';
				form.nomeFuncionario.value = '';
				form.idEmpresa.disabled = false;
				form.idEmpresa.removeAttribute("disabled", 0);
				//form.idEmpresa.value = '-1';				
			} else if (indicadorPrestadorServico > 0) {
				form.idFuncionario.removeAttribute("readOnly"); //FUNCIONARIO
				//form.idEmpresa.value = '-1';
				form.idEmpresa.value = '1';
				form.idEmpresa.disabled = true;
				form.nomeUsuario.setAttribute("readOnly",true);				
				//form.nomeUsuario.value='';
			} else {
				form.idFuncionario.removeAttribute("readOnly"); //FUNCIONARIO
				form.idEmpresa.value = '1';
				form.idEmpresa.disabled = true;
				form.nomeUsuario.setAttribute("readOnly",true);
				//form.nomeUsuario.value = '';
			}
	}

	function limparCampos(){
		var form = document.forms[0];

		form.loginUsuarioPesquisa.value = '';
		form.nomeUsuarioPesquisa.value = '';
		
		form.idFuncionario.value = '';
		form.nomeFuncionario.value = '';
		
		form.cpf.value = '';
		form.cpf.removeAttribute("readonly");
		form.dataNascimento.value = '';
		form.dataNascimento.removeAttribute("readonly");
		
		form.nomeUsuario.value = '';
		form.nomeUsuario.removeAttribute("readonly", 0);
				
		form.idLotacao.value = '';
		form.nomeLotacao.value = '';
		
		form.login.value = '';	
		form.login.removeAttribute("readonly");
		
		form.idEmpresa.value = '-1';
				
		form.dataInicial.value = '';
		form.dataFinal.value = '';
				
		form.email.value = '';
	}
	
	function verificarUsuarioTipo() {
		var form = document.forms[0];
		var msg = '';

		var tipoUsuario = returnObject(form, "idTipoUsuario");
		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

		var empresa = returnObject(form, "idEmpresa");
		var indicadorEmpresa = empresa.options[empresa.options.selectedIndex].value;

		if (indicadorPrestadorServico.length > 0
				&& indicadorPrestadorServico == '8') {

			if (indicadorEmpresa < 0 || indicadorEmpresa == '') {
				msg += "Informe a Empresa. \n";								
			}
			if (form.nomeUsuario.value == '') {
				msg += "Informe o Nome do Usuário. \n";				
			}
		} else if (indicadorPrestadorServico > 0) {

			if (form.idFuncionario.value == '') {
				msg += "Informe a Matrícula do Funcionário. \n";				
			}
		} else {
			msg += "Informe o Tipo de Usuário. \n";			
		}

		return msg;
	}

	function replicarLogin() {
		var form = document.forms[0];

		var tipoUsuario = returnObject(form, "idTipoUsuario");
		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

		if (indicadorPrestadorServico.length > 0
				&& indicadorPrestadorServico == '8') {
			form.login.value = form.cpf.value;
		} else {
			form.login.value = form.idFuncionario.value;
		}
	}

	function validarIdadeUsuario() {
		var form = document.forms[0];
		var retorno = '';

		if (form.dataNascimento.disabled == false
				&& form.dataNascimento.value.length > 0) {

			var dataAtual = document.getElementById("DATA_ATUAL").value;
			var idadeMinimaUsuario = document
					.getElementById("IDADE_MINIMA_USUARIO").value;

			var idadeUsuario = anosEntreDatas(form.dataNascimento.value,
					dataAtual);

			if (parseInt(idadeUsuario) < parseInt(idadeMinimaUsuario)) {

				retorno = "O usuário terá que possuir, no mínimo, " + idadeMinimaUsuario + " anos de idade. \n";				
				
			}
		}

		return retorno;
	}

	function reloadTipoUsuario() {
		var form = document.forms[0];
		if (!form.idFuncionario.disabled && !form.idFuncionario.readonly){
			limparCampos();
			if (form.idTipoUsuario != null || form.idTipoUsuario.value != '') {
	
				form.action = "/gsan/exibirInserirSolicitacaoAcessoAction.do?usuarioTipo="
						+ form.idTipoUsuario.value;
				form.submit();
			}
		}
	}

function preencherDadosFuncionario() {
	var form = document.forms[0];

	if (form.idFuncionario.value != '') {

		if (form.login.value == '') {
			form.login.value = form.idFuncionario.value;
		}
	}
}

function abrirCalendarioLocal(formName, fieldName, campo){
	var form = document.forms[0];	
	if (!form.dataNascimento.disabled && !form.dataNascimento.readonly){
		abrirCalendario(formName,fieldName);
	}
}

function limparUsuarioResponsavel() {
 	document.forms[0].nomeUsuarioResponsavel.value = '';
 	document.forms[0].idUsuarioResponsavel.value = '';                  
}

function limparDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //Funcionario Responsavel			
			form.nomeFuncionarioSuperior.value = "";
			break;
		case 2: //Usuario Responsavel revalidacao'		
			form.nomeUsuarioResponsavel.value = "";
			break;
		case 3: //Usuario Pesquisa	
			form.nomeUsuarioPesquisa.value = "";
			limparPesquisaUsuario2();
			break;
		case 4: //Matricula do funcionario
			form.nomeFuncionario.value = "";
			break;
		case 5: //Unidade de Lotacao
			form.nomeLotacao.value = "";
			break;
		case 6: //Usuario Solicitante
			form.nomeFuncionarioSolicitante.value = "";
			break;			
	}
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="desabilitaCampos();preencherDadosFuncionario();setarFoco('${requestScope.nomeCampo}');">	

	<div id="formDiv">
		<html:form action="/inserirSolicitacaoAcessoAction.do"
			name="ExibirInserirSolicitacaoAcessoActionForm"
			type="gcom.gui.seguranca.acesso.usuario.ExibirInserirSolicitacaoAcessoActionForm"
			method="post">

			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>

			<INPUT TYPE="hidden" ID="DATA_ATUAL"
				value="${requestScope.dataAtual}" />
			<INPUT TYPE="hidden" ID="IDADE_MINIMA_USUARIO"
				value="${requestScope.idadeMinimaUsuario}" />
			<input type="hidden" name="tipoPesquisa">
			<html:hidden property="usuarioPesquisaNaoEncontrado" />
			<html:hidden property="dominioEmail" />
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
								<td class="parabg">Inserir Solicitação de Acesso</td>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">

							<tr>
								<td colspan="5">Usuário Solicitante</td>
							</tr>

							<tr>
							<td width="26%" colspan="2"><strong>Login:<font color="#FF0000">*</font> </strong>
							</td>
							<logic:present name="grupoSeguranca" scope="request">
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="11" property="idFuncionarioSolicitante" size="8"  onkeyup="limparDestino(6);" tabindex="1"  
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=6', 'idFuncionarioSolicitante'); return isCampoNumerico(event);"
										/>

									<a href="javascript:chamarPopupPesquisaUsuarioSolicitante();">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Usuário" /></a>
									
									<logic:present name="usuarioSolicitanteNaoEncontrado" scope="request">
									    <html:text property="nomeFuncionarioSolicitante" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			                        </logic:present>
			                        <logic:notPresent name="usuarioSolicitanteNaoEncontrado" scope="request">
									    <html:text property="nomeFuncionarioSolicitante" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			                        </logic:notPresent>
			                        	
									<a href="javascript:limparPesquisaUsuarioSolicitante();document.forms[0].idFuncionarioSolicitante.focus();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
								</td>							
							</logic:present>
							<logic:notPresent name="grupoSeguranca" scope="request">
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="11" property="idFuncionarioSolicitante" size="8" readonly="true"
										tabindex="1" style="background-color:#EFEFEF; border:0; color: #000000" />

									<html:text property="nomeFuncionarioSolicitante" size="40"
										readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</td>
							</logic:notPresent>
							</tr>
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="5">Funcionário Responsável pela Autorização - Superior
									Hierárquico</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula:<font color="#FF0000">*</font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="11" property="idFuncionarioSuperior" size="8"
										tabindex="1" 
										onkeyup="limparDestino(1);"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=1', 'idFuncionarioSuperior'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', 'login' , null, 495, 300, '', document.forms[0].idFuncionarioSuperior);">
									
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Funcionário" /></a> 
									
									<logic:present name="funcionarioInexistente1" scope="request">
										<input type="text" name="nomeFuncionarioSuperior" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.funcionario.inexistente"/>" />											
									</logic:present>
									<logic:notPresent name="funcionarioInexistente1" scope="request">
										<logic:present name="funcionarioNaoUsuario" scope="request">
											<input type="text" name="nomeFuncionarioSuperior" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="funcionario.nao.usuario"/>" />													
										</logic:present>
										<logic:notPresent name="funcionarioNaoUsuario"
											scope="request">
											<html:text property="nomeFuncionarioSuperior" size="40"
											readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notPresent>
									</logic:notPresent>
									 
									<a href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td colspan="2"><strong>Notificar Responsável por
										E-mail:<font color="#FF0000"></font> </strong></td>
								<td colspan="3"><span class="style2"> <strong>
											<html:radio property="icNotificar" value="0" onclick="" />
											Sim <html:radio property="icNotificar" value="1" onclick="" />
											Não </strong> </span>
								</td>
							</tr>
							
							<!-- RM3892.2 - Implementar Normas de Senhas no GSAN -->
							<tr>
								<td colspan="5"><hr></td>
							</tr>
							<tr>
								<td colspan="5">Usuário responsável por revalidar o usuário que está sendo inserido</td>
							</tr>
							
							<tr>
								<td width="26%" colspan="2"><strong>Matrícula/CPF:<font color="#FF0000">*</font></strong></td>
								<td width="74%" height="24" colspan="3"><strong>
									<html:text maxlength="11" tabindex="9" property="idUsuarioResponsavel" size="9" 
									onkeyup="limparDestino(2);"
									onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=4', 'idUsuarioResponsavel'); return isCampoNumerico(event);"
									/>
									<a
									href="javascript:chamarPopupPesquisaUsuarioResponsavel();">
									<img name="imagem" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/></a>
															
					   		        <logic:present name="usuarioNaoEncontrado" scope="request">
									    <html:text maxlength="40" property="nomeUsuarioResponsavel" size="40" style="background-color:#EFEFEF; border:0; color: #ff0000" readonly="true"/>
			                        </logic:present>
			                        <logic:notPresent name="usuarioNaoEncontrado" scope="request">
									    <html:text maxlength="40" property="nomeUsuarioResponsavel" size="40" style="background-color:#EFEFEF; border:0; color: #000000" readonly="true"/>
			                        </logic:notPresent>
								<a	href="javascript:limparUsuarioResponsavel();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
								</strong></td>
							</tr>
							<!-- Fim RM3892 -->

							<tr>
								<td colspan="5"><hr></td>
							</tr>
							
							<tr>
								<td colspan="5">Pesquisa do usu&aacute;rio para preenchimento dos campos</td>
							</tr>
							
							<tr> 
					        	<td width="26%" colspan="2">
					        		<strong>Matrícula/CPF:</strong>
					        	</td>
					        	<td width="74%" height="24" colspan="3">
						            <html:text maxlength="11"
										property="loginUsuarioPesquisa" size="10" tabindex="1" style="text-transform: none;" 
										onkeyup="limparDestino(3);"
										onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=3', 'loginUsuarioPesquisa'); return isCampoNumerico(event);" 
										/>
									<a 
									href="javascript:chamarPopupPesquisaUsuario();">
									<img name="imagem" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/></a>
									
									<logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="usuarioPesquisaNaoEncontrado" value="false">
										<html:text property="nomeUsuarioPesquisa" size="38" readonly="true"
											style="background-color:#EFEFEF; border:0" 
											/>
									</logic:equal>
									
									<logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="usuarioPesquisaNaoEncontrado" value="true">
										<html:text property="nomeUsuarioPesquisa" readonly="true"
											style="background-color:#EFEFEF; border:0; color:#ff0000"
											size="38"
											/>
									</logic:equal>
									
									<a href="javascript:limparPesquisaUsuario(); document.forms[0].loginUsuarioPesquisa.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> </a>
									
					            </td>
					        </tr>
							
							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="5">Dados do Usuário</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Tipo de
										Usuário:<font color="#FF0000">*</font> </strong></td>
								<td colspan="3">
									<html:select property="idTipoUsuario"
										style="width: 230px;" size="1" tabindex="1"
										onchange="desabilitaCampos();reloadTipoUsuario();">

										<logic:notEmpty name="colecaoUsuarioTipo" scope="session">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoUsuarioTipo"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

										<font size="1">&nbsp; </font>
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Empresa:<font
										color="#FF0000"></font> </strong></td>
								<td colspan="3"><html:select property="idEmpresa"
										style="width: 230px;" size="1" tabindex="1">

										<logic:notEmpty name="colecaoEmpresa" scope="session">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoEmpresa"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

										<font size="1">&nbsp; </font>
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000"></font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="11" property="idFuncionario" size="8" tabindex="1"
										onkeyup="limparDestino(4);"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=2', 'idFuncionario'); return isCampoNumerico(event); reloadTipoUsuario(); " />
									<html:link 
									href="javascript:javascript:chamarPopupPesquisaFuncionario(); reloadTipoUsuario(); ">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Funcionário" /> </html:link> 
									<logic:present
										name="funcionarioInexistente" scope="request">
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
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Nome do
										Usuário:<font color="#000000"></font> </strong></td>
								<td width="74%" height="24" colspan="3"><html:text
										property="nomeUsuario" size="50" maxlength="50" 
										onkeypress="return campoTexto(event, this);"/>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Número do CPF:<font
										color="#ff0000">*</font> </strong></td>
								<td width="74%" colspan="3"><html:text property="cpf"
										size="12" maxlength="11"
										onkeypress="return isCampoNumerico(event);"
										onkeyup="javascript:replicarLogin();"
										onclick="javascript:replicarLogin();" />
								</td>
							</tr>

							<tr>
								<td height="10" colspan="2"><strong>Data de
										Nascimento:<font color="#FF0000">*</font> </strong></td>
								<td colspan="3"><html:text property="dataNascimento"
										size="11" maxlength="10" tabindex="4"
										onkeyup="mascaraData(this, event);"
										onkeypress="return isCampoNumerico(event);" /> <a
									href="javascript:abrirCalendarioLocal('ExibirInserirSolicitacaoAcessoActionForm', 'dataNascimento')">
										<img border="0"
										src="<bean:message 
							 key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="absmiddle"
										alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Unidade de
										Lotação:<font color="#FF0000">*</font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="4" property="idLotacao" size="9" tabindex="1"
										onkeyup="limparDestino(5);"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idLotacao'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limpaForm=S', null, null, null, 495, 300, '', document.forms[0].idLotacao);">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Unidade Organizacional" /> </a> <logic:present
										name="lotacaoInexistente" scope="request">
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
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td colspan="2"><strong>Login:<font
										color="#ff0000">*</font> </strong></td>
								<td colspan="3"><html:text property="login" size="11" onkeyup="return validaCampoSemCaractereEspecial('login', 'Login');"
										maxlength="11" style="text-transform: none;" />
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><strong>E-Mail:<font
										color="#ff0000">*</font> </strong></td>
								<td colspan="3"><html:text property="email" size="40"
										maxlength="70" style="text-transform: none;" />
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Período de
										Cadastramento:<font color="#ff0000">*</font> </strong></td>
								<td width="74%" colspan="3"><html:text
										property="dataInicial" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);limpaDataFinal();"
										onkeypress="return isCampoNumerico(event);" /> <a
									href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataInicial')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" /></a>&nbsp; <html:text property="dataFinal" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);"
										onkeypress="return isCampoNumerico(event);" /> <a
									href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
							</tr>
							<tr>
							  <td colspan="2"><strong>Competência para Retificação:</strong></td>
					          <td colspan="3">
					          <logic:present name="desabilitaCompetenciaRetificacao">
					          	<html:text property="competenciaRetificacao" size="10" maxlength="6" readonly="true"/>
					          </logic:present>
					          <logic:notPresent name="desabilitaCompetenciaRetificacao">
					          	<html:text property="competenciaRetificacao" size="10" maxlength="6"
									onkeyup="formataValorMonetario(this, 5);" style="text-align: right;"
									onkeypress="javascript:return isCampoNumerico(event);"
									/>&nbsp;(Número de vezes a média de consumo)
					          </logic:notPresent>
								
					          </td>
							</tr>


							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<!-- RM 3892 Implantar norma de senhas no GSAN  -->
							<tr>
								<td colspan="5">Acesso do Usuário</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Abrangência do
										Acesso:<font color="#FF0000">*</font>
								</strong>
								</td>
								<td colspan="3"><html:select property="abrangencia"
										style="width: 230px;" size="1" tabindex="1"
										onchange="javascript:verificarAcessoAbrangencia();">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUsuarioAbrangencia"
											labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="22%" colspan="2"><strong>Gerência
										Regional:</strong>
								</td>
								<td colspan="3"><html:select property="gerenciaRegional"
										style="width: 230px;" size="1" tabindex="1"
										onmousedown="teste1(document.forms[0].gerenciaRegional)"
										disabled="true">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoGerenciaRegional" labelProperty="nome"
											property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="22%" colspan="2"><strong>Unidade
										Negócio:</strong>
								</td>
								<td colspan="3"><html:select disabled="true"
										property="unidadeNegocio" style="width: 230px;" size="1"
										tabindex="1">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUnidadeNegocio" labelProperty="nome"
											property="id" />
									</html:select>
								</td>
							</tr>

							
							
							<tr>
								<td width="26%" colspan="2"><strong>Localidade:</strong>
								</td>
								<td width="74%" height="24" colspan="3">
								<html:text property="idLocalidade" maxlength="9" size="9" tabindex="1" 
								disabled="true" name="ExibirInserirSolicitacaoAcessoActionForm"
								onkeypress="validaEnterComMensagem(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idLocalidade', 'Localidade'); return isCampoNumerico(event);"
								onkeyup="limpaCampoPesquisa(document.forms[0].nomeLocalidade);" />
								<a
									href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidade);">
										<img width="23" height="21" border="0" title="Pesquisar Localidade"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										style="cursor: hand;" alt="Pesquisar" /></a> <logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="false">
										<html:text property="nomeLocalidade" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:equal> 
									<logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="true">
										<html:text property="nomeLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" maxlength="40" />
									</logic:equal> <a href="javascript:limparLocalidade();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</a>
								</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Nível: </strong></td>
								<td width="74%" colspan="3"><html:select property="nivel" onchange="javascript:obterPermissoes();"
										size="1" tabindex="2" style="width: 320px;">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoNivel" labelProperty="descricao"
											property="id" />
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Especial: </strong></td>
								<td width="74%" colspan="3"><html:select property="grupo" onchange="javascript:obterPermissoes();"
										size="1" tabindex="2" style="width: 320px;">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoGrupo" labelProperty="descricao"
											property="id" />
									</html:select>
								</td>
							</tr>
							
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<logic:present name="colecaoPermissaoEspecial">
					        <tr>
								<td colspan="5"><strong>Permissões Especiais:</strong>								 
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="6">
									<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0" cellspacing="0">
										<tr bordercolor="#000000">
											<td width="18%" bgcolor="#90c7fc">
												<div align="center"><strong>Marca/Desmarca</strong>
												</div>
											</td>
											<td width="80%" bgcolor="#90c7fc"><strong>Descrição</strong>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div style="width: 100%; height: 100%; overflow: auto;">
													<table border="0" bgcolor="#99CCFF" width="100%">
														<%int cont = 0;%>
														
															<logic:iterate name="colecaoPermissaoEspecial"
																id="permissaoEspecial">
															<%cont = cont + 1;
															if (cont % 2 == 0) {%>
																<tr bgcolor="#cbe5fe">
															<%} else {%>
																<tr bgcolor="#FFFFFF">
															<%}%>
																	<td width="18%">
																		<div align="center"><strong> <html:multibox
																			property="permissoesEspeciais"
																			value="${permissaoEspecial.id}" /> </strong></div>
																	</td>
																	<td width="80%"><bean:write name="permissaoEspecial"
																		property="descricao" /></td>
																</tr>
															</logic:iterate>
														
														
														<logic:present name="colecaoPermissaoEspecialDesalibitado">
															<logic:iterate name="colecaoPermissaoEspecialDesalibitado"
																id="permissaoEspecial">
															<%cont = cont + 1;
															if (cont % 2 == 0) {%>
																<tr bgcolor="#cbe5fe">
															<%} else {%>
																<tr bgcolor="#FFFFFF">
															<%}%>
																	<td width="18%">
																		<div align="center">
																			<strong> 
																				<html:multibox property="permissoesEspeciais"
																					value="${permissaoEspecial.id}" disabled="true" /> 
																			</strong>
																		</div>
																	</td>
																	<td width="80%">
																		<bean:write name="permissaoEspecial" property="descricao" />
																	</td>
																</tr>
															</logic:iterate>
														</logic:present>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							</logic:present>
		
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
									onClick="window.location.href='/gsan/exibirInserirSolicitacaoAcessoAction.do?menu=sim'" />

									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
								</td>

								<td align="right"><input type="button" name="Button"
									class="bottonRightCol" value="Concluir"
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
