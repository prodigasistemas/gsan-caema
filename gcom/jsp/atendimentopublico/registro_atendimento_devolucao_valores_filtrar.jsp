<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarRegistroAtendimentoDevolucaoValoresActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

//Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.FiltrarRegistroAtendimentoDevolucaoValoresActionForm;
		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
			form.descricaoImovel.value = descricaoRegistro;
		}
	}
    
	function habilitarPesquisaImovel(form) {
		if (form.idImovel.disabled == false) {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}	
	}
	
	function duplicaDataAtendimento(form) {
		form.dataAtendimentoFim.value = form.dataAtendimentoInicio.value;
	}
    
    function validaEnterImovel(event, caminho, campo) {
		var form = document.FiltrarRegistroAtendimentoDevolucaoValoresActionForm;
		validaEnter(event, caminho, campo);
	}
	
    function limparImovel() {
		var form = document.FiltrarRegistroAtendimentoDevolucaoValoresActionForm;
		form.idImovel.value = "";
		form.descricaoImovel.value = "";

	}
	
    function limparDescricaoImovel() {
		var form = document.FiltrarRegistroAtendimentoDevolucaoValoresActionForm;
		form.descricaoImovel.value = "";
	}
   	
	function limparForm(form) {

		form.idSolicitacaoTipoEspecificacao.value = "-1";
		form.idImovel.value = "";
		form.idImovel.disabled = false;
		form.descricaoImovel.value = "";
		form.perfilImovel.selectedIndex = 0;
		form.dataAtendimentoInicio.value = "";
		form.dataAtendimentoFim.value = "";
		form.numeroRA.value = "";
	}
   
	function validarForm(form) {
   
		urlRedirect = "/gsan/filtrarRegistroAtendimentoDevolucaoValoresAction.do";
	
		if(form.idSolicitacaoTipoEspecificacao.value == null
				|| form.idSolicitacaoTipoEspecificacao.value == ''
				|| form.idSolicitacaoTipoEspecificacao.value == '-1') {
			alert('Informe Tipo de Especificação');
		} else if(validarCampoDataComMensagemLimpandoCampo(form.dataAtendimentoInicio, 'Data Inicial')
				&& validarCampoDataComMensagemLimpandoCampo(form.dataAtendimentoFim, 'Data Final')
				&& validarCampoNumericoComMensagemLimpandoCampo(form.numeroRA, 'Número do RA')
				&& validarCampoNumericoComMensagemLimpandoCampo(form.idImovel, 'Matrícula do Imóvel')) {
			if ((form.dataAtendimentoInicio.value != "" && form.dataAtendimentoFim.value != "") 
					&& comparaData(form.dataAtendimentoInicio.value, ">", form.dataAtendimentoFim.value)){
				alert('Data Final do Período de Atendimento é anterior à Data Inicial');
			} else {
				form.idImovelHidden.value = form.idImovel.value;
				form.action = urlRedirect;
				botaoAvancarTelaEspera(form.action);
			}
		}
	}
   
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
<html:form action="/exibirFiltrarRegistroAtendimentoDevolucaoValoresAction"
	name="FiltrarRegistroAtendimentoDevolucaoValoresActionForm" method="post"
	type="gcom.gui.atendimentopublico.FiltrarRegistroAtendimentoDevolucaoValoresActionForm">

	<html:hidden property="idImovelHidden" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">

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
			<td width="615" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar RA para Devolução de Valores</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="3">Para filtrar RA(s) para Devolução de Valores, informe os dados abaixo:</td>
					<%--<td align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td> --%>
				</tr>

				<tr>
					<td><strong>Tipo de Especificação:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="idSolicitacaoTipoEspecificacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Período de Atendimento:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataAtendimentoInicio" size="10"
						onkeyup="mascaraData(this, event); duplicaDataAtendimento(document.forms[0]);somente_numero(this);"
						onblur="somente_numero(this);"
						onkeypress="return isCampoNumerico(event);"
						onblur="javascript:verificaDataMensagemPersonalizada(this,'Data Inicial Inválida.')"
						onfocus="duplicaDataAtendimento(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarRegistroAtendimentoDevolucaoValoresActionForm', 'dataAtendimentoInicio', 'dataAtendimentoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataAtendimentoFim"
						size="10" onkeyup="mascaraData(this, event);somente_numero(this);" 
						onkeypress="return isCampoNumerico(event);"
						onblur="javascript:verificaDataMensagemPersonalizada(this,'Data Final Inválida.');somente_numero(this);"/> <a
						href="javascript:abrirCalendario('FiltrarRegistroAtendimentoDevolucaoValoresActionForm', 'dataAtendimentoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> (dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Número do RA:</strong></td>
					<td colspan="2">
						<html:text property="numeroRA" size="9"	maxlength="9"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="somente_numero_zero_a_nove(this);"
							onblur="somente_numero_zero_a_nove(this);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="2"><html:text property="idImovel" size="9"
						maxlength="9"
						onkeyup="somente_numero_zero_a_nove(this);limparDescricaoImovel();"
						onblur="somente_numero_zero_a_nove(this);limparDescricaoImovel();" 
						onkeypress="javascript:validaEnterImovel(event, 'exibirFiltrarRegistroAtendimentoDevolucaoValoresAction.do', 'idImovel');return isCampoNumerico(event);" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" title="Pesquisar Imóvel" /></a><logic:present
						name="matriculaInexistente" scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel" /></a></td>
				</tr>
				
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td><strong> <html:select property="perfilImovel" style="width: 200px;" multiple="mutiple" size="4">
						<logic:present name="colecaoPerfilImovel">
							<html:option value="" />
							<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				

				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
					<font color="#FF0000">*</font>
					Campos obrigat&oacute;rios
					</td>
				</tr>

				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
						<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					<td colspan="2" align="right">
					  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="filtrarRegistroAtendimentoDevolucaoValoresAction.do"/>
					  <%--<input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
					
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<p>&nbsp;</p>
</html:form>
</div>
</body>
</html>
