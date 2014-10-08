<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
	  		form.descricaoLocalidade.style.color = "#000000";
	  		
	  		
		}
	}	
	
	function validarForm(){
		var form = document.forms[0];
		if(validateGerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm(form)){
			if(form.opcaoRelatorio[0].checked == true || form.opcaoRelatorio[1].checked == true){
				toggleBox('demodiv', 1);
			}else{
				alert("Informe a opção do relatório");
			}
		}
	}
	
	function limparLocalidade() {
	    var form = document.GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm;
	    
      	form.idLocalidade.value = '';
      	form.descricaoLocalidade.value = '';
	}
	
	function limparTipoServico(){
		var form = document.forms[0];
	    form.idTipoServico.value = "";
	    form.descricaoTipoServico.value = "";
	}
			
	function limparForm(){
		var form = document.forms[0];
		form.action = 'exibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction.do?limparForm=Sim';
		form.submit();
	}
	
	function verificarMesAno(mesAno){
		var form = document.forms[0];

	}
	
	//Replica Data de Geracao
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoOSFinal.value = form.periodoGeracaoOSInicial.value;
	}
	
	function carregarUnidade(){
		var form = document.forms[0];
		form.action = 'exibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction.do?carregarUnidade=Sim';
		form.submit();
	}


</script>

</head>

<html:javascript staticJavascript="false" formName="GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm" />

<body leftmargin="5" topmargin="5" onload="">

	<div id="formDiv">
		<html:form action="/exibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction" method="post" name="GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm"
			type="gcom.gui.mobile.GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm">


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

						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">
									Gerar Relatório de Acompanhamento de O.S. de Cobrança para Smartphone
								</td>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>

						<p>&nbsp;</p>

						<table width="100%" border="0">
							<tr>
								<td colspan="5">
									Para gerar o relatório de acompanhamento das ordens de serviços para Smartphone, informe os dados abaixo:
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
									<div align="left">
										<strong> </strong>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Tipo do Relatório:<font	color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:radio property="opcaoRelatorio" value="1"  /> <strong>Analítico</strong>&nbsp;
									<html:radio property="opcaoRelatorio" value="2" /> <strong>Sintético</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:select property="idEmpresa" tabindex="4" >
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
									</html:select> <font size="1">&nbsp; </font>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:text property="dataReferencia" size="7" maxlength="7" tabindex="3"
										onkeyup="mascaraAnoMes(this, event);verificarMesAno(this, event);" onkeypress="return isCampoNumerico(event);"/>
									mm/aaaa
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Gerência Regional:</strong>
								</td>
								<td colspan="2">
									<html:select property="idGerenciaRegional" tabindex="7" onchange="carregarUnidade();"> 
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
									</html:select>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Unidade de Negócio:</strong>
								</td>
								<td colspan="2">
									<html:select property="idUnidadeNegocio" tabindex="8">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoUnidadeNegocio" scope="request">
											<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Localidade:</strong>
								</td>

								<td colspan="4">
									<html:text property="idLocalidade" 
										size="4" 
										maxlength="4" 
										tabindex="10"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction.do?pesquisarLocalidade=OK', 'idLocalidade', 'Localidade'); return isCampoNumerico(event);"/>
									
									<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade"/>
									</a> 

									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" size="35" maxlength="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" size="35" maxlength="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>
									
									<a href="javascript:limparLocalidade();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>
							
							<tr>
								<td ><strong>Tipo de Servi&ccedil;o:</strong></td>
								<td><html:select property="idsTipoServico" multiple="mutiple" >
									<logic:notEmpty name="colecaoTipoServico">
										<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
										<html:options collection="colecaoTipoServico"
											labelProperty="descricao" property="id" />
									</logic:notEmpty>
								</html:select></td>
							</tr>
							
							<tr>
								<td><strong>Per&iacute;odo de Geração da Ordem de Serviço:</strong></td>
								
								<td colspan="4">
									<span class="style2"> <strong> <html:text
										property="periodoGeracaoOSInicial" size="11" maxlength="10"
										tabindex="3"
										onkeyup="mascaraData(this, event);replicaDataGeracao();" /> <a
										href="javascript:abrirCalendarioReplicando('GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm', 'periodoGeracaoOSInicial','periodoGeracaoOSFinal');">
									<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="16" height="15" border="0" title="Exibir Calendário"
										tabindex="4" /></a> a <html:text
										property="periodoGeracaoOSFinal" size="11" maxlength="10"
										tabindex="3" onkeyup="mascaraData(this, event)" /> <a
										href="javascript:abrirCalendario('GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm', 'periodoGeracaoOSFinal');">
									<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="16" height="15" border="0" title="Exibir Calendário"
										tabindex="4" /></a> </strong>(dd/mm/aaaa)<strong> </strong> 
									</span>
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
								<div align="left"><strong> </strong></div>
							</td>
							
							</tr>

							<tr>
								<td colspan="2">
									<div align="left">
										<input type="button" name="limpar" class="bottonRightCol" value="Limpar" tabindex="15" 
											onclick="javascript:limparForm();"> 
										<input type="button" name="adicionar" class="bottonRightCol" value="Cancelar" tabindex="16"
											onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</div>
								</td>

								<td>
									<div align="right">
										<input type="button" name="botaoConcluir" class="bottonRightCol" value="Gerar" tabindex="17"
											onclick="javascript:validarForm();">
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
			<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction.do" />
		</html:form>
	</div>

	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>

</html:html>