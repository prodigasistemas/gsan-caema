<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
function filtrar(){
	var form = document.forms[0];
	
	if(verificaDataMensagemPersonalizada(form.periodoEncerramentoComandoInicial,"Período de Encerramento do Comando Inicial inválido")
	&& verificaDataMensagemPersonalizada(form.periodoEncerramentoComandoFinal,"Período de Encerramento do Comando Final inválido")
	&& verificaAnoMes(form.periodoReferenciaCobrancaInicial)
	&& verificaAnoMes(form.periodoReferenciaCobrancaFinal)
   	&& validateRequired(form)
   	&& validarAnoMes()
    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoEncerramentoComandoInicial, 
    	    'Período de Encerramento do Comando Inválido')
    && verificaDataMensagemPersonalizada(document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoEncerramentoComandoFinal, 
    	    'Período de Encerramento do Comando Inválido')){
	    	
		if (comparaData(document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoEncerramentoComandoInicial.value, ">", document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoEncerramentoComandoFinal.value )){
			alert('Período de Encerramento do Comando Final anterior ao Período de Encerramento do Comando Inicial');
			return false;
		}
		if ((document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoReferenciaCobrancaInicial.value != "" 
			&& document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoReferenciaCobrancaFinal.value != "" ) 
				&& (comparaMesAno(
						document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoReferenciaCobrancaInicial.value, 
						">", document.FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm.periodoReferenciaCobrancaFinal.value))){
				alert("Período Final de Referência da Cobrança  é anterior ao Período Inicial de Referência da Cobrança.");
				return false;
		}

		
		botaoAvancarTelaEspera('filtrarComandosAcaoCobrancaCronogramaReabrirAction.do?tipoConsulta=cronograma');
	}
}

function duplicaPeriodoReferenciaCobranca(){
	var formulario = document.forms[0]; 
	formulario.periodoReferenciaCobrancaFinal.value = formulario.periodoReferenciaCobrancaInicial.value;
}

function validarAnoMes(){
	var formulario = document.forms[0]; 
	if(formulario.periodoReferenciaCobrancaFinal.value == '' 
		&& formulario.periodoReferenciaCobrancaInicial.value !=''){

			alert('Período de Referência Inicial informado. Informar Período Final');
		return false;
	}else if(formulario.periodoReferenciaCobrancaInicial.value == '' 
		&& formulario.periodoReferenciaCobrancaFinal.value !=''){
			alert('Período de Referência Final informado. Informar Período Inicial');
			return false;
	}
	return true;
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
		formulario.periodoReferenciaCobrancaInicial.value = "";
		formulario.periodoReferenciaCobrancaFinal.value = "";
		formulario.grupoCobranca.selectedIndex = 0;
		formulario.acaoCobranca.selectedIndex = 0;
		formulario.periodoEncerramentoComandoInicial.value = "";
		formulario.periodoEncerramentoComandoFinal.value = "";
}

function required () {
 	this.aa = new Array("periodoEncerramentoComandoInicial", "Informe Período de Encerramento do Comando Inicial", new Function ("varName", " return this[varName];"));
 	this.a = new Array("periodoEncerramentoComandoFinal", "Informe Período de Encerramento do Comando Final", new Function ("varName", " return this[varName];"));
   }
  
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">  
	<form name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"><%@ include
			file="/jsp/util/cabecalho.jsp"%>
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
			</div>
			</td>
			<td  valign="top" bgcolor="#003399" class="centercoltext">
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
							<td class="parabg">Filtrar Comandos A&ccedil;&atilde;o de
								Cobran&ccedil;a Encerrados - Comandos do Cronograma</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" />
							</td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td width="29%"><strong>Per&iacute;odo de
									Refer&ecirc;ncia da Cobran&ccedil;a:</strong>
							</td>
							<td colspan="4"><strong><strong> <html:text
											maxlength="7"
											name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
											property="periodoReferenciaCobrancaInicial" size="7"
											onkeyup="javascript:validaAnoMesNumerico(this);mascaraAnoMes(this, event);duplicaPeriodoReferenciaCobranca();"
											onchange="javascript:validaAnoMesNumerico(this);verificaAnoMes(this);" />
								</strong> </strong>(mm/aaaa)<strong><strong><strong> a</strong>
										<html:text maxlength="7"
											name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
											property="periodoReferenciaCobrancaFinal" size="7"
											onkeyup="javascript:validaAnoMesNumerico(this);mascaraAnoMes(this, event);"
											onchange="javascript:validaAnoMesNumerico(this);verificaAnoMes(this);" /> </strong> </strong>(mm/aaaa)<strong>
							</strong>
							</td>
						</tr>
						<tr>
							<td><strong>Grupo de Cobran&ccedil;a:</strong>
							</td>
							<td colspan="4"><html:select property="grupoCobranca"
									name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
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
							<td><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:</strong>
							</td>
							<td colspan="4"><html:select property="acaoCobranca"
									name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
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
							<td colspan="5">
								<hr></td>
						</tr>
						<tr>
							<td height="17"><strong>Per&iacute;odo de
									Encerramento do Comando</strong><strong>:<font color="#FF0000">*</font></strong>
							</td>
							<td colspan="4"><strong> <html:text maxlength="10"
										name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
										property="periodoEncerramentoComandoInicial" size="10"
										onkeyup="javascript:mascaraData(this, event);duplicaPeriodoEncerramentoComando();"
										onchange="somente_numero(this);" />
									<a
									href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm', 
					'periodoEncerramentoComandoInicial','periodoEncerramentoComandoFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" />
								</a> <strong> a</strong> <html:text maxlength="10"
										name="FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm"
										property="periodoEncerramentoComandoFinal" size="10"
										onkeyup="javascript:mascaraData(this, event);" 
										onchange="somente_numero(this);"/> <a
									href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm', 'periodoEncerramentoComandoFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" />
								</a> dd/mm/aaaa</strong>
							</td>
						</tr>
						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>
						
						<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right" colspan="4">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

						<tr>
							<td colspan="3" align="left">
								<table border="0" align="left">
									<tr>
										<td align="left"><input name="Button32222" type="button"
											class="bottonRightCol" value="Limpar"
											onClick="javascript:limpar()" />
										</td>
										<td align="left">&nbsp;</td>
									</tr>
								</table></td>
							<td colspan="2">
								<table border="0" align="right">
									<tr>
										<td align="right"><img src="imagens/voltar.gif"
											width="15" height="24" border="0" />
										</td>
										<td align="right"><input name="Button32222" type="button"
											class="bottonRightCol" value="Voltar"
											onClick="javascript:voltar()" /></td>

										<td align="right"><gsan:controleAcessoBotao
												name="concluir" value="Filtrar"
												onclick="javascript:filtrar();"
												url="filtrarComandosAcaoCobrancaCronogramaReabrirAction.do" />
										</td>
									</tr>
								</table></td>
						</tr>

					</table>
					<p>&nbsp;</p></td>
			</tr>
		</table>


		<%@ include file="/jsp/util/rodape.jsp"%>
		<%@ include file="/jsp/util/telaespera.jsp"%>
		</form>
		</div>
</body>
</html>
