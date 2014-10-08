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

		if ( form.idTipoSolicitacaoRA.value != null && form.idTipoSolicitacaoRA.value != '-1') {
			if ( form.idEspecificacao.value != null && form.idEspecificacao.value != '-1') {
				botaoAvancarTelaEspera('/gsan/informarRetirarSituacaoFactivelFaturavelEsgotoAction.do');
			} else {
				alert("O campo Especificação é obrigatório");
			}
		} else {
			alert("O campo Tipo Solicitação (RA) é obrigatório");
		}
			
	}

	function validateInformarRetirarSituacaoFactivelFaturavelEsgotoActionForm(form) {                                                                   
		return true;
	} 

	function recarregarPagina() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir&pesquisarEspecificacao=sim');
		
	}

	function voltar() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?voltar=sim');
	}

	function limparDadosRetirarLigacao() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir&limpar=sim');
	}
	
	//function IntegerValidations () {
	//	this.aa = new Array("volumeMinimoFaturamento", "O campo Volume Mínimo deve conter apenas números.", new Function ("varName", " return this[varName];"));
	//}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" >
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
					<td class="parabg">Retirar Imóvel da Ligação Factível Faturável </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para retirar o(s) imóvel(is)  do(s) imóvel(eis) da ligação Factível Faturável, informe os dados do Registro de Atendimento abaixo:</td>
				</tr>

				
				<tr bgcolor="#cbe5fe">
					<td>
					<table border="0" width="100%">
						
						<tr>
							<td width="42%" height="10">
								<strong>Quantidade de Imóveis Pesquisados:<font color="#FF0000">*</font> </strong>
							</td>
							<td colspan="2"><strong><b> 
								<html:text property="qtdTotalImoveis" readonly="true"
										   style="background-color:#EFEFEF; border:0;" 
										   size="10"
										   maxlength="10" /> </b></strong>
							</td>
						</tr>
						
						<tr>
							<td width="30%">
								<strong>Tipo Solicitação (RA):<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:select property="idTipoSolicitacaoRA" tabindex="4" onchange="recarregarPagina();">
									<logic:notEmpty name="colecaoSolicitacaoTipo">
										<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
										<html:options collection="colecaoSolicitacaoTipo"
													  labelProperty="descricao" 
													  property="id" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td width="30%">
								<strong>Especificação:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:select property="idEspecificacao" tabindex="4" >
									<logic:notEmpty name="colecaoSolicitacaoTipoEspecificacao">
										<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
										<html:options collection="colecaoSolicitacaoTipoEspecificacao"
													  labelProperty="descricao" 
													  property="id" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
					
					</table>
					</td>
				</tr>

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
								name="ButtonVoltar" 
								class="bottonRightCol" 
								value="Voltar"
								onClick="javascript:voltar();">
									
							<input type="button"
								name="ButtonLimpar" 
								class="bottonRightCol" 
								value="Limpar"
								onClick="javascript:limparDadosRetirarLigacao();">
						</div>
						</td>
						<td valign="top">
						<div align="right">
							<input type="button"
								name="ButtonConcluir"
								align="right" 
								class="bottonRightCol" 
								value="Concluir"
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
