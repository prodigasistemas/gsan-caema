<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarContratoDemandaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript">

function validarForm(form){

		if(validateAtualizarContratoDemandaActionForm(form)){
			if (comparaData(form.dataInicioContrato.value, ">", form.dataFimContrato.value)) {
				alert('Data de Fim do Contrato tem que ser superior a Data de Início');
			} else if (form.dataEncerramento.value != '' 
					&& comparaData(form.dataInicioContrato.value, ">", form.dataEncerramento.value)) {
				alert('Data de Encerramento do Contrato tem que ser superior a Data de Início');
			} else { 
				if (form.idMotivoEncerramento.value != '-1' && form.dataEncerramento.value == '') {
					alert('Informe Data de Encerramento');
				} else if (form.dataEncerramento.value != '' && form.idMotivoEncerramento.value == '-1') {
					alert('Informe Motivo do Cancelamento');
				} else {
	    			form.action = 'atualizarContratoDemandaAction.do';
	    			form.submit();
	    		}
	    	}
		}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
     if(tipoConsulta == 'imovel'){
        form.idImovel.value = codigoRegistro;
        form.inscricaoImovel.value = descricaoRegistro;
        form.inscricaoImovel.style.color = "#000000";
    }
}

function habilitarPesquisaImovel(form) {
	if (form.idImovel.disabled == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.idImovel.value);
	}	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function limparForm(tipo){
     var form = document.forms[0];
    if(tipo == 'imovel')
    {
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
	}
}

function limparImovelTecla() {
     var form = document.forms[0];
     form.inscricaoImovel.value = "";
}

function adicionarImoveis() {
	var form = document.forms[0];
	
	if(form.idImovel.value == ''){
		alert('Informe o imóvel');
		return;
	}else{
		form.action = 'exibirAtualizarContratoDemandaAction.do?adicionar=sim';
		form.submit();
	}
}

function bloquearCampoVolumeEsgoto(){
	var form = document.forms[0];
	
	if(form.percentualEsgoto.value != ''){
		form.tipoEsgoto.value = "1";
		form.volumeEsgoto.value = "";
		form.volumeEsgoto.disabled = true;
	}else{
		form.volumeEsgoto.disabled = false;
	}
}

function bloquearCampoPercentualEsgoto(){
	var form = document.forms[0];
	
	if(form.volumeEsgoto.value != ''){
		form.tipoEsgoto.value = "2";
		form.percentualEsgoto.value = "";
		form.percentualEsgoto.disabled = true;
	}else{
		form.percentualEsgoto.disabled = false;
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearCampoVolumeEsgoto();bloquearCampoPercentualEsgoto();">

<html:form action="/atualizarContratoDemandaAction" method="post"
	name="AtualizarContratoDemandaActionForm"
	type="gcom.gui.faturamento.AtualizarContratoDemandaActionForm"
	onsubmit="return validateAtualizarContratoDemandaActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="tipoEsgoto" /> 

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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Contrato de Demanda</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="3">Para atualizar o contrato de demanda, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="150"><strong>Número do Contrato:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> 
				       <html:text property="numeroContrato" size="11"
						maxlength="10" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Imóvel:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><html:text property="idImovel" size="10" maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);limparImovelTecla();"
					onkeypress="javascript:return validaEnter(event, 'exibirAtualizarContratoDemandaAction.do?objetoConsulta=2', 'idImovel');"/>  
							<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" title="Pesquisar Imóvel">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
							<logic:present name="existeImovel">	
    	                  		<logic:equal name="existeImovel" value="exception">							 							  
						        	<html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				 		        </logic:equal>
							    <logic:notEqual name="existeImovel"	value="exception">
			 					    <html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
						        </logic:notEqual>
						    </logic:present>

							<logic:notPresent name="existeImovel">
								<logic:empty name="AtualizarContratoDemandaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="AtualizarContratoDemandaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
						<a href="javascript:limparForm('imovel');" title="Apagar Imóvel" > 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						
						</a>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
					<div align="right"><input type="button" name="adicionar"
						class="bottonRightCol" value="Adicionar"
						onClick="adicionarImoveis();">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="10%">
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td width="10%">
							<div align="center" class="style9"><strong>Matrícula</strong></div>
							<td width="25%">
							<div align="center" class="style9"><strong>Inscrição</strong></div>
							<td width="55%">
							<div align="center"><strong>Endereço</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoContratoDemandaImovel" scope="session">
							<logic:iterate indexId="posicao"
								name="colecaoContratoDemandaImovel" id="helper" >

								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
									cor = "#cbe5fe";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#FFFFFF";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td>
									<div align="center" class="style9"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;"
										onclick="redirecionarSubmit('exibirAtualizarContratoDemandaAction.do?remover=sim&posicao=<bean:write name="helper" property="idImovel" />');"></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="matricula" /></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="inscricaoImovel" /></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="enderecoImovel" /></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Data de Inicio do Contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text property="dataInicioContrato" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" /> 
						</strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataInicioContrato');" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
					<td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text property="dataFimContrato" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
						</strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataFimContrato')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
					<td><strong>Data de Encerramento do Contrato:</strong></td>
					<td colspan="2"><strong> <html:text property="dataEncerramento" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);" /> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataEncerramento')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
			  		<td class="style3"><strong>Motivo de Encerramento:</strong></td>
			  		<td colspan="2">
			  			<html:select property="idMotivoEncerramento" tabindex="4" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoEncerramento" property="id" labelProperty="descricao"/>
						</html:select></td>
			    </tr>				
				<tr>
					<td colspan="4" >
						<table width="100%" >	
							<tr>
								<td style="width: 146px;"><strong>Tarifa Consumo:<font color="#FF0000">*</font></strong></td>
								<td><strong><html:select property="tarifaConsumo" style="width: 230px;" tabindex="1" >
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:present name="colecaoConsumoTarifa" scope="session">
										<html:options collection="colecaoConsumoTarifa"
											labelProperty="descricao" property="id" />
									</logic:present>
								</html:select></strong></td>
								<td><strong><a href="/gsan/exibirInserirConsumoTarifaAction.do?tipoContratoDemanda=2">Inserir Tarifa Consumo</a></strong></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td><strong>Volume Água:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="volumeAgua" size="7"
						maxlength="7" onkeypress="return isCampoNumerico(event);" />&nbsp;m³
					</strong></td>
				</tr>
				<tr>
					<td><strong>Percentual de Coleta:</strong></td>
					<td><strong><html:text property="percentualEsgoto" size="7"
						maxlength="6" onkeyup="formataValorMonetario(this, 5);bloquearCampoVolumeEsgoto();" onkeypress="return isCampoNumerico(event);" />
					</strong></td>
				</tr>
				<tr>
					<td><strong>Volume Mín Fixado:</strong></td>
					<td><strong><html:text property="volumeEsgoto" size="7"
						maxlength="7" onkeyup="bloquearCampoPercentualEsgoto();" onkeypress="return isCampoNumerico(event);" />&nbsp;m³
					</strong></td>
				</tr>
				<tr>
					<td><strong>Percentual de Esgoto:</strong></td>
					<td><strong><html:text property="percentualValorTarifaAgua" size="7"
						maxlength="6" onkeyup="formataValorMonetario(this, 5);" onkeypress="return isCampoNumerico(event);" />
					</strong></td>
				</tr>
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
					   Campos obrigat&oacute;rios</div>					</td>
				</tr>
				<tr>
							<td colspan="2"><strong><logic:present name="filtrar">
								<logic:present name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarContratoDemandaAction.do?menu=sim'">
								</logic:present>
								<logic:notPresent name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarContratoDemandaAction.do'">
								</logic:notPresent>
							</logic:present><logic:notPresent name="filtrar">
								<input type="button" name="ButtonReset" class="bottonRightCol"
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirManterContratoDemandaAction.do'">
							</logic:notPresent><input type="button" name="Submit22"
								class="bottonRightCol" value="Desfazer"
								onClick="javascript:window.location.href='/gsan/exibirAtualizarContratoDemandaAction.do?desfazer=sim'">
							<input type="button" name="Submit23" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</strong></td>
							<td align="right">
								<logic:notPresent name="desabilitarAtualizar" scope="session">
									<gsan:controleAcessoBotao
										name="button" value="Atualizar"
										onclick="javascript:validarForm(document.forms[0]);"
										url="atualizarContratoDemandaAction.do" />
								</logic:notPresent>
								<logic:present name="desabilitarAtualizar" scope="session">
									<input type="button" name="ButtonAtualizar" class="bottonRightCol"
										value="Atualizar" disabled="disabled"
										onclick="javascript:validarForm(document.forms[0]);">
								</logic:present>
							</td>
						</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

