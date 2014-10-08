<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarComandosAcaoCobrancaEventualReabrirActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">    

function caracteresespeciais () {
	     this.aa = new Array("criterioCobranca", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
	     this.aa = new Array("criterioCobranca", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

function filtrar(){
	var form = document.forms[0];
	
	if(verificaDataMensagemPersonalizada(form.periodoEncerramentoComandoInicial,"Data do Período de Realização do Comando Inicial inválido")
		&& verificaDataMensagemPersonalizada(form.periodoEncerramentoComandoFinal,"Data do Período de Realização do Comando Final inválido")
	 	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.criterioCobranca, 'Critério de Cobrança') 	 	
	    && validateCaracterEspecial(form) 
	    && validateLong(form)
	    && validateRequired(form)
	    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoInicio, 'Período de Emissão Inválido')
	    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoFim, 'Período de Emissão Inválido')
	    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.periodoEncerramentoComandoInicial, 'Período de Encerramento do Comando Inválido')
	    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.periodoEncerramentoComandoFinal, 'Período de Encerramento do Comando Inválido')
	    ){    

		if (comparaData(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoInicio.value, ">",
				 document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoFim.value )){
					alert('Período de Emissão Final anterior ao Período de Emissão Inicial');
					return false;
		}
		
		if (comparaData(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.periodoEncerramentoComandoInicial.value, ">",
		 document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.periodoEncerramentoComandoFinal.value )){
			alert('Data do Período de Realização do Comando Final anterior à data do Período Realização do Comando Inicial');
			return false;
		}
		
		
		botaoAvancarTelaEspera('filtrarComandosAcaoCobrancaEventualReabrirAction.do?tipoConsulta=eventual');
	}
}
  
function duplicaPeriodoEncerramentoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoEncerramentoComandoFinal.value = formulario.periodoEncerramentoComandoInicial.value;
}  
 
function cancelar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/telaPrincipal.do'
   		formulario.submit();
}

function voltar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/exibirConsultarComandosAcaoCobrancaAction.do?carregando=SIM&tipoConsulta=reabrir';
   		formulario.submit();
}


function limpar(){
		var formulario = document.forms[0]; 
		
		formulario.grupoCobranca.selectedIndex = -1;
		formulario.acaoCobranca.selectedIndex = -1;
		formulario.periodoEncerramentoComandoInicial.value = "";
		formulario.periodoEncerramentoComandoFinal.value = "";	

		formulario.criterioCobranca.value = "";
		formulario.nomeCriterioCobranca.value = "";
		formulario.dataEmissaoInicio.value = "";
		formulario.dataEmissaoFim.value = "";
		

}

function desabilitarCobrancaGrupo(){
		var form = document.forms[0];
		form.grupoCobranca.disabled = true;
		form.grupoCobranca.value = "";
}


function validarGrupoCobranca(visulizar){

		var form = document.forms[0];
	
		var ok = true;

		if(ok == true){
			var grupoCobrancaSelecionado = false;
				for (i = 0; i < form.grupoCobranca.length; i++){
			   if((i != 0) && (form.grupoCobranca[i].selected)){
			   		grupoCobrancaSelecionado = true;
			   }
			}
			if(grupoCobrancaSelecionado == true){
				
		        form.imagem.enabled = true;
				
			}
		}
}



function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      form.action = 'exibirFiltrarComandosAcaoCobrancaEventualReabrirAction.do?idCobrancaAcaoAtividadeComando='+codigoRegistro
      form.submit();
    }
    

   	if (tipoConsulta == 'criterioCobranca') {
	   form.criterioCobranca.value = codigoRegistro;
       form.nomeCriterioCobranca.value = descricaoRegistro;
       form.nomeCriterioCobranca.style.color = "#000000";
       
    }

}

function validarHabilitarCampo(){

	var form = document.forms[0];
	
		if(form.grupoCobranca.value != ""){

			form.grupoCobranca.disabled = false;

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


function mensagem(mensagem){
	if(mensagem.length > 0){
		alert(mensagem);
	}
}

function limparCriterioCobranca(){
   var form = document.forms[0];
   form.criterioCobranca.value = "";
   form.nomeCriterioCobranca.value = "";
}


function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function required () {
 	this.aa = new Array("periodoEncerramentoComandoInicial", "Informe Período de Encerramento do Comando Inicial", new Function ("varName", " return this[varName];"));
 	this.a = new Array("periodoEncerramentoComandoFinal", "Informe Período de Encerramento do Comando Final", new Function ("varName", " return this[varName];"));
   }
  

-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:mensagem('${requestScope.mensagem}');setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv"> 
	<form action="/exibirFiltrarComandosAcaoCobrancaEventualReabrirAction"
		name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
		method="post">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="115" valign="top" class="leftcoltext">

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
					</div></td>
				<td valign="top" class="centercoltext">
					<!--Início Tabela Reference a Páginação da Tela de Processo-->
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" />
							</td>
							<td class="parabg">Filtrar Comandos de A&ccedil;&atilde;o de
								Cobran&ccedil;a Encerrados - Comandos Eventuais</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" />
							</td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<p>&nbsp;</p>
					<table width="100%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td><strong>Per&iacute;odo de Emiss&atilde;o:</strong>
							</td>
							<td colspan="3"><strong> <html:text
										name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
										property="dataEmissaoInicio" size="10"
										onkeyup="mascaraData(this, event); replicaDados(document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoInicio, document.FiltrarComandosAcaoCobrancaEventualReabrirActionForm.dataEmissaoFim);"
										onkeypress="return isCampoNumerico(event);" 
										onchange="somente_numero(this);"
										onblur="somente_numero(this);"
										maxlength="10" />
									<a
									href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualReabrirActionForm', 'dataEmissaoInicio')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="absmiddle"
										title="Exibir Calendário" /> </a> a</strong> <html:text
									name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
									property="dataEmissaoFim" size="10" maxlength="10"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);" 
									onchange="somente_numero(this);"
									onblur="somente_numero(this);"/> <a
								href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualReabrirActionForm', 'dataEmissaoFim')">
									<img border="0"
									src="<bean:message key='caminho.imagens'/>calendario.gif"
									width="20" border="0" align="absmiddle"
									title="Exibir Calendário" /> </a> dd/mm/aaaa</td>
						</tr>

						<!-- <tr>
							<td><strong>T&iacute;tulo do Comando:</strong>
							</td>
							<td colspan="4">
								<div
									style="width: 450px; overflow-x: scroll; overflow: -moz-scrollbars-horizontal;">
									<html:select property="idCobrancaAcaoAtividadeComando"
										tabindex="1"
										name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
										multiple="mutiple" size="4">
										<logic:present name="colecaoCobrancaAcaoAtividadeComando">
											<html:option value="" />
											<html:options
												collection="colecaoCobrancaAcaoAtividadeComando"
												labelProperty="descricaoTitulo" property="id" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>-->
						<tr>
							<td><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:</strong>
							</td>
							<td colspan="4"><html:select property="acaoCobranca"
									tabindex="1"
									name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
									style="width: 400px;" multiple="mutiple" size="5">
									<logic:present name="colecaoAcaoCobranca">
										<html:option value="" />
										<html:options collection="colecaoAcaoCobranca"
											labelProperty="descricaoCobrancaAcao" property="id" />
									</logic:present>
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Crit&eacute;rio de Cobran&ccedil;a:</strong>
							</td>
							<td width="81%" align="right" colspan="4">
								<div align="left">
									<html:text maxlength="6" property="criterioCobranca" size="6"
										tabindex="5"
										name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
										onkeypress="validaEnter(event, 'exibirFiltrarComandosAcaoCobrancaEventualReabrirAction.do','criterioCobranca');return isCampoNumerico(event);somente_numero(this);"
										onchange="somente_numero(this);" />
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" height="21" style="cursor: hand; cursor: pointer;"
										name="imagem"
										onclick="chamarPopup('exibirPesquisarCriterioCobrancaAction.do?limpaForm=1', 'origem', null, null, 275, 480, '',document.forms[0].criterioCobranca);"
										title="Pesquisar Critério de Cobrança"> <strong>
										<logic:present name="corCriterioCobranca">
											<logic:equal name="corCriterioCobranca" value="exception">
												<html:text property="nomeCriterioCobranca" size="40"
													maxlength="40" readonly="true"
													name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:equal>

											<logic:notEqual name="corCriterioCobranca" value="exception">
												<html:text property="nomeCriterioCobranca" size="40"
													maxlength="40" readonly="true"
													name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEqual>

										</logic:present> <logic:notPresent name="corCriterioCobranca">

											<logic:empty
												name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
												property="criterioCobranca">
												<html:text property="nomeCriterioCobranca" value=""
													size="40" maxlength="40" readonly="true"
													name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											<logic:notEmpty
												name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
												property="criterioCobranca">
												<html:text property="nomeCriterioCobranca" size="40"
													maxlength="40" readonly="true"
													name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
													style="background-color:#EFEFEF; border:0; color: 	#000000" />
											</logic:notEmpty>
										</logic:notPresent> <img
										src="<bean:message key='caminho.imagens'/>limparcampo.gif"
										title="Apagar" style="cursor: hand; cursor: pointer;"
										onclick="limparCriterioCobranca()" name="imagem">
									</strong>
								</div></td>
						</tr>
						<tr>
							<td colspan="5">
								<hr></td>
						</tr>
						<tr>
							<td><strong>Grupo de Cobran&ccedil;a:</strong>
							</td>
							<td colspan="4"><html:select property="grupoCobranca"
									tabindex="7" onchange="validarGrupoCobranca(1);"
									name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
									style="width: 230px;" multiple="mutiple" size="5">
									<logic:present name="colecaoGrupoCobranca">
										<html:option value="" />
										<html:options collection="colecaoGrupoCobranca"
											labelProperty="descricao" property="id" />
									</logic:present>
								</html:select>
							</td>
						</tr>

						<tr>
							<td height="17"><strong>Per&iacute;odo de
									Encerramento do Comando</strong><strong>:<font color="#FF0000">*</font></strong>
							</td>
							<td colspan="4"><strong> <html:text maxlength="10"
										tabindex="19"
										name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
										property="periodoEncerramentoComandoInicial" size="10"
										onkeypress="return isCampoNumerico(event);"
										onkeyup="javascript:mascaraData(this, event);duplicaPeriodoEncerramentoComando();"
										onchange="somente_numero(this);" 
										onblur="somente_numero(this);"/>
									<a
									href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaEventualReabrirActionForm', 'periodoEncerramentoComandoInicial',
							'periodoEncerramentoComandoFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" title="Exibir Calendário" />
								</a> <strong> a</strong> <html:text maxlength="10" tabindex="20"
										name="FiltrarComandosAcaoCobrancaEventualReabrirActionForm"
										property="periodoEncerramentoComandoFinal" size="10"
										onkeyup="javascript:mascaraData(this, event);"
										onkeypress="return isCampoNumerico(event);" 
										onchange="somente_numero(this);"
										onblur="somente_numero(this);"/> <a
									href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualReabrirActionForm', 'periodoEncerramentoComandoFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" title="Exibir Calendário" />
								</a> </strong> dd/mm/aaaa
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<hr></td>
						</tr>
						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>
						
						<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
						<tr>
							<td colspan="3" align="left">
								<table border="0" align="left">
									<tr>
										<td align="left"><input name="Button32222" type="button"
											class="bottonRightCol" value="Limpar" tabindex="35"
											onClick="javascript:limpar()" />
										</td>
									</tr>
								</table></td>
							<td>
								<table border="0" align="right">
									<tr>
										<td align="right"><img src="imagens/voltar.gif"
											width="15" height="24" border="0" />
										</td>
										<td align="right"><input name="Button32222" type="button"
											tabindex="36" class="bottonRightCol" value="Voltar"
											tabindex="37" onClick="javascript:voltar();" />
										</td>
										<td align="right"><gsan:controleAcessoBotao
												name="concluir" value="Filtrar"
												onclick="javascript:filtrar();"
												url="filtrarComandosAcaoCobrancaEventualReabrirAction.do"
												tabindex="38" /></td>
									</tr>
								</table></td>
						</tr>
					</table>

					<p>&nbsp;</p></td>
			</tr>
		</table>
	</form>

	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/telaespera.jsp"%>
	</div>
</body>
</html:html>
