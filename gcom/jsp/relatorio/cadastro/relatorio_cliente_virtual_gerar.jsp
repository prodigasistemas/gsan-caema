<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.cliente.ClienteVirtual"  isELIgnored="false"%>

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
	formName="FiltrarClienteVirtualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
<!--
	
	var bCancel = false; 

    function validateFiltrarClienteVirtualActionForm(form) {                                                                   
    	if (bCancel) 
      		return true; 
        else 
       		return validateCaracterEspecial(form) && validateDate(form) && validateInteger(form);
   	}

	function caracteresespeciais () { 
	    this.aa = new Array("matriculaImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("periodoAtendimentoInicial", "Período de Atendimento Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("periodoAtendimentoFinal", "Período de Atendimento Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}
    
    function DateValidations () { 
    	this.aa = new Array("periodoAtendimentoInicial", "Data de Início inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    	this.ab = new Array("periodoAtendimentoFinal", "Data Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

    function IntegerValidations () { 
	    this.aa = new Array("matriculaImovel", "Imóvel deve ser numérico.", new Function ("varName", " return this[varName];"));
	}

	function validarForm(form) {
	
		if( validateFiltrarClienteVirtualActionForm(form) && validarPeriodo(form)){
			if(form.periodoAtendimentoInicial.value != "" && form.periodoAtendimentoFinal.value != ""){
				if ( comparaData(form.periodoAtendimentoInicial.value,"<=",form.periodoAtendimentoFinal.value) ) {
					javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioClienteVirtualAction.do');
				} else {
					alert("Período final deve ser superior ao período inicial");
				}
			}else{
				javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioClienteVirtualAction.do');
			}
		}
	}
	
	function validarPeriodo(form){
		if(form.matriculaImovel.value != ""){
			return true;
		}else{
			if(form.periodoAtendimentoInicial.value != "" && form.periodoAtendimentoFinal.value == ""){
				alert ("Informe Período Atendimento Final");
				return false;
			}else if(form.periodoAtendimentoInicial.value == "" && form.periodoAtendimentoFinal.value != ""){
				alert ("Informe Período Atendimento Inicial");
				return false;	
			}else if(form.periodoAtendimentoInicial.value == "" && form.periodoAtendimentoFinal.value == ""){
				alert("Informe Período Atendimento ou Imóvel");
				return false;
			}else{
				return true;
			}
		}
	}

	function replicarData() {
		var form =  document.forms[0];
	
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value; 
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'imovel') {
	    	form.matriculaImovel.value = codigoRegistro;
	    	form.descricaoImovel.value = descricaoRegistro;
	    	form.situacaoCliente.focus();
	    }
	}
	
	function limparImovel() {
		var form = document.forms[0];
		form.matriculaImovel.value = "";
		form.descricaoImovel.value = "";
	}
	
-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/gerarRelatorioClienteVirtualAction" 
 		   name="FiltrarClienteVirtualActionForm" 
		   type="gcom.gui.cadastro.cliente.FiltrarClienteVirtualActionForm"
		   method="post"
		   onsubmit="return validateFiltrarClienteVirtualActionForm(this);">

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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Gerar Relatório de Clientes Alterados a partir do Ambiente Virtual</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para filtrar, informe os dados abaixo:</p>
					</td>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
	        </table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td style="width: 150px;">
									<strong>Per&iacute;odo de Atendimento:</strong>
								</td>
             				   <td colspan="6">
									<html:text property="periodoAtendimentoInicial" 
											size="11" 
											maxlength="10" 
											tabindex="3" 
											onkeyup="mascaraData(this, event);replicarData();" 
											onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendarioReplicando('FiltrarClienteVirtualActionForm', 'periodoAtendimentoInicial', 'periodoAtendimentoFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" />
									</a>
									a <html:text property="periodoAtendimentoFinal" 
												size="11" 
												maxlength="10" 
												tabindex="3" 
												onkeyup="mascaraData(this, event)"  
												onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendario('FiltrarClienteVirtualActionForm', 'periodoAtendimentoFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" />
									</a>
									dd/mm/yyyy
							</tr>
							<tr>
								<td><strong>Matrícula do Imóvel:</strong></td>
								<td colspan="3"><html:text property="matriculaImovel" size="9"
									maxlength="9"
									onkeyup="validaEnterComMensagem(event, 'exibirGerarRelatorioClienteVirtualAction.do?pesquisarImovel=sim', 'matriculaImovel');" />
									<a
										href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 495, 300);"><img
										width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										style="border:0;" title="Pesquisar" />
									</a>
									<logic:present name="matriculaInexistente" scope="request">
										<html:text property="descricaoImovel" size="17" maxlength="22"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="matriculaInexistente" scope="request">
										<html:text property="descricaoImovel" size="17" maxlength="22"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparImovel();"><img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
							<tr>
								<td><strong>Situação do Cliente:</strong></td>
								<td>
									<html:select property="situacaoCliente">
										<html:option value=""> </html:option>
										<html:option value="1">Pendente</html:option>
										<html:option value="2">Atualizado</html:option>
										<html:option value="3">Rejeitado</html:option>
										<html:option value="4">Em Analise</html:option>
										<html:option value="5">Removido de Em Analise</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td><strong>Cadastro em menos de 30 dias</strong></td>
								<td>
									<html:radio property="tempoCadastro" value="1">Sim</html:radio>
									<html:radio property="tempoCadastro" value="2">Nao</html:radio>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
							</tr>
							
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td height="24" colspan="2">
					          		<input type="button" name="Button" class="bottonRightCol" value="Cancelar" align="left"
										tabindex="7" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>	
									<input name="Button" type="button" class="bottonRightCol"value="Limpar" align="left" tabindex="8"
										onclick="window.location.href='/gsan/exibirGerarRelatorioClienteVirtualAction.do?limparFormulario=sim'">
								</td>
								
								<td align="right">
									<input name="Button2" type="button" class="bottonRightCol" value="Gerar" 
										onClick="javascript:validarForm(document.forms[0]);" tabindex="9" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>