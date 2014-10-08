<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirLigacaoEsgotoSituacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];

		if ( (form.idMunicipio.value != null && form.idMunicipio.value != '-1') || (form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != '') ) {
			if ( (form.idLogradouro.value != null && form.idLogradouro.value != '-1') || (form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != '') ) {

				if (validarInscricaoInicialFinal()){
					form.indicadorLigacao.value = "1";
					form.indicadorCaixaGordura.value = "2";
					botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir');
				}
		
			} else {
				alert("O campo Logradouro é obrigatório");
			}
		} else {
			alert("Informe Município e Logradouro ou intervalo de inscrição.");
		}
	}

	function validateInformarRetirarSituacaoFactivelFaturavelEsgotoActionForm(form) {                                                                   
		return true;
	} 

	function recarregarPagina() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do');
		
	}

	function limparFormulario() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?menu=sim');
	}

	function habilitarCampos() {
		var form = document.forms[0];

		if ( form.idMunicipio.value != null && form.idMunicipio.value != '-1' ) {

			form.idLocalidadeInicial.disabled = true;
			form.idLocalidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true; 
			form.codigoSetorComercialFinal.disabled = true;
			form.numeroQuadraInicial.disabled = true; 
			form.numeroQuadraFinal.disabled = true;
			form.numeroRotaInicial.disabled = true;
			form.numeroRotaFinal.disabled = true;
			form.numeroSequencialRotaInicial.disabled = true; 
			form.numeroSequencialRotaFinal.disabled = true;
			form.idMunicipio.disabled = false;
			form.idLogradouro.disabled = false;
		} else if ( form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != '' ) {
			form.idMunicipio.disabled = true;
			form.idLogradouro.disabled = true;
			form.idLocalidadeInicial.disabled = false;
			form.idLocalidadeFinal.disabled = false;
			form.codigoSetorComercialInicial.disabled = false; 
			form.codigoSetorComercialFinal.disabled = false;
			form.numeroQuadraInicial.disabled = false; 
			form.numeroQuadraFinal.disabled = false;
			form.numeroRotaInicial.disabled = false;
			form.numeroRotaFinal.disabled = false;
			form.numeroSequencialRotaInicial.disabled = false; 
			form.numeroSequencialRotaFinal.disabled = false;

		} else {
			form.idLocalidadeInicial.disabled = false;
			form.idLocalidadeFinal.disabled = false;
			form.codigoSetorComercialInicial.disabled = false; 
			form.codigoSetorComercialFinal.disabled = false;
			form.numeroQuadraInicial.disabled = false; 
			form.numeroQuadraFinal.disabled = false;
			form.numeroRotaInicial.disabled = false;
			form.numeroRotaFinal.disabled = false;
			form.numeroSequencialRotaInicial.disabled = false; 
			form.numeroSequencialRotaFinal.disabled = false;
			form.idMunicipio.disabled = false;
			form.idLogradouro.disabled = false;

		}			
		

		
	}
	
	//function IntegerValidations () {
	//	this.aa = new Array("volumeMinimoFaturamento", "O campo Volume Mínimo deve conter apenas números.", new Function ("varName", " return this[varName];"));
	//}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="habilitarCampos();">
<div id="formDiv">
	<html:form action="/informarRetirarSituacaoFactivelFaturavelEsgotoAction.do"
		name="InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm"
		type="gcom.gui.atendimentopublico.InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm"
		method="post"
		onsubmit="return validateInformarRetirarSituacaoFactivelFaturavelEsgotoActionForm(this);">
	
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
			
			<html:hidden property="indicadorLigacao" />	
			<html:hidden property="indicadorCaixaGordura" />	
			
			<td width="625" valign="top" class="centercoltext">

			<table>
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
					<td class="parabg">Informar/Retirar Situação de Esgoto Factível Faturável </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) imóvel(is), informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo de Operação: <font color="#FF0000">*</font></strong>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorTipoOperacao"
										value="<%=""+ConstantesSistema.SIM%>" /> 
							<strong>Informar 
								<html:radio property="indicadorTipoOperacao"
											value="<%=""+ConstantesSistema.NAO%>" /> Retirar
							</strong> 
						</strong>
					</td>
				</tr>
				
				
				
				<tr>
					<td width="30%">
						<strong>Município:</strong>
					</td>
					<td>
						<html:select property="idMunicipio" tabindex="4" onchange="recarregarPagina();" >
							<logic:notEmpty name="colecaoMunicipio">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:options collection="colecaoMunicipio"
											  labelProperty="nome" 
											  property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="30%">
						<strong>Logradouro:</strong>
					</td>
					<td>
						<html:select property="idLogradouro" tabindex="4" >
							<logic:notEmpty name="colecaoLogradouro">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:options collection="colecaoLogradouro"
											  labelProperty="nome" 
											  property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<%@ include file="/jsp/util/filtro_inscricao_inicial_final.jsp"%>
				 
			

				<table width="100%" border="0">
				<p>&nbsp;</p>
					<tr>
						<td colspan="2">
						Campos Obrigatórios
						<font color="#FF0000">*</font>
						</td>
					</tr>
					<tr>
						<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonCancelar" 
								class="bottonRightCol" 
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								
							<input type="button"
								name="ButtonLimpar" 
								class="bottonRightCol" 
								value="Limpar"
								onClick="javascript:limparFormulario();">
						</div>
						</td>
						<td valign="top">
						<div align="right">
							<input type="button"
								name="ButtonConcluir" 
								class="bottonRightCol" 
								value="Pesquisar"
								onClick="javascript:validarForm();">
						</div>
						</td>
					</tr>	
				</table>
				<p>&nbsp;</p>
			</table>

			<tr>
				<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
			</tr>
			</td>
		</tr>
	</table>

	</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
