<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>

<html:html>
<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet"
		href="<bean:message key="caminho.css"/>EstilosCompesa.css"
		type="text/css">
	
	<%@ page import="gcom.micromedicao.ArquivoTextoRoteiroEmpresa"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ConsultarArquivoTextoLeituraActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

  	function limparForm() {
  			
		var form = document.forms[0];
		
		form.dataInicio.value = "";
		form.dataFinal.value = "";
	}
	
	function pesquisar(){	
		var form = document.forms[0];
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		
		if (comparaData(form.dataInicio.value, ">", form.dataFinal.value )){
	  		alert('Data Final é anterior a Data Inicial.');
		}
		else if (comparaData(form.dataInicio.value, ">", DATA_ATUAL )){
			alert("Data Inicial posterior à Data corrente " + DATA_ATUAL + ".");
		}
		else if (comparaData(form.dataFinal.value, ">", DATA_ATUAL )){
			alert("Data Final posterior à Data corrente " + DATA_ATUAL + ".");
		}else{			
			form.action = 'exibirConsultarAtualizacaoSubSistemaAction.do?pesquisar=sim';
	  		form.submit();	
  		}
	}
	
</script>

</head>

	<body leftmargin="5" topmargin="5">
	
	<div id="formDiv">  
		<html:form action="/exibirConsultarAtualizacaoSubSistemaAction"
			name="ConsultarAtualizacaoSubSistemaActionForm"
			type="gcom.gui.cadastro.imovel.ConsultarAtualizacaoSubSistemaActionForm"
			method="post"
			onsubmit="return validateConsultarArquivoTextoLeituraActionForm(this);">

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
				
				<td width="615" valign="top" class="centercoltext">
					<table height="100%">
					<tr>
						<td></td>
					</tr>
					</table>

					<table>
						<tr>
							<td></td>
						</tr>
					</table>
				
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Consultar Atualiza&ccedil;&otilde;es do Subsistema de Esgoto </td>
							<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
						</tr>
	
					</table>
					
					<!--Fim Tabela Reference a Páginação da Tela de Processo-->
					
					<p>&nbsp;</p>
					
					<table width="100%" border="0">
						
						<tr>
							<td colspan="6">Para consultar as atualiza&ccedil;&otilde;es do subsistema de esgoto, informe os dados abaixo:</td>
							<td align="right"></td>
						</tr>

						<tr>
							<td width="10%">
								<strong>Período de execução:</strong>
							</td>
		
							<td width="70%" colspan="5"><strong> 
								
								<input type="hidden" 
									   id="DATA_ATUAL"
									   value="${requestScope.dataAtual}" />
								
								<html:text  property="dataInicio" 
									size="10"
									maxlength="10" 
									tabindex="2"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataFinal,this);"  
									onfocus="replicarCampo(document.forms[0].dataFinal,this);"/>
									
								<a href="javascript:abrirCalendarioReplicando('ConsultarAtualizacaoSubSistemaActionForm', 'dataInicio', 'dataFinal');" >
									<img border="0"
										 src="<bean:message key="caminho.imagens"/>calendario.gif"
										 width="20" 
										 border="0" 
										 align="absmiddle" 
										 title="Exibir Calendário"/></a> a</strong> 
								<html:text  property="dataFinal" 
									size="10"
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);" /> 
									
									<a href="javascript:abrirCalendario('ConsultarAtualizacaoSubSistemaActionForm', 'dataFinal')">
										<img border="0"
											 src="<bean:message key="caminho.imagens"/>calendario.gif"
											 width="20" 
											 border="0" 
											 align="absmiddle" 
											 title="Exibir Calendário" /></a>dd/mm/aaaa
							</td>
						</tr>
				
						<tr>
							<td width="100%" colspan="2" align="right">
								<input name="btnPesquisar" 
									type="button" 
									class="bottonRightCol" 
									value="Pesquisar" 
									onclick="javascript:pesquisar();" tabindex="3" />					 					 		
							</td>
						</tr>
				
						<tr>
							<td colspan="4" bgcolor="#000000" height="2" valign="baseline"></td>
						</tr>
						
						<tr>
							<td colspan="4">
							<div style="height:300px;overflow:auto">
							
							<table width="100%" bgcolor="#99CCFF" border="0">
			
								<tr bgcolor="#90c7fc">
									<td align="center" width="10%"><strong>Data</strong></td>
									<td align="center" width="30%"><strong>Arquivo</strong></td>
									<td align="center" width="15%"><strong>Quantidade Lidos</strong></td>
									<td align="center" width="15%"><strong>Imóveis Atualizados</strong></td>
									<td align="center" width="15%"><strong>Erros</strong></td>
								</tr>
								
								<logic:present name="colecaoSubSistemaEsgotoArquivoHelper" scope="session" >
									<%	String cor = "#cbe5fe";%>
									<logic:iterate name="colecaoSubSistemaEsgotoArquivoHelper" id="helper" >
						
									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF" height="18">	
									<%} else{	
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe" height="18">		
									<%}%>

									<td align="center" width="10%" >
										<bean:write name="helper" property="subSistemaEsgotoArquivoTexto.ultimaAlteracao" format="dd/MM/yyyy" />
									</td>
									
									<td align="center" width="30%" >
										<bean:write name="helper" property="subSistemaEsgotoArquivoTexto.descricaoArquivo" />
									</td>
									
									<td align="center" width="15%" >
										<bean:write name="helper" property="subSistemaEsgotoArquivoTexto.quantidadeImoveisLidos" />
									</td>
									
									<td align="center" width="15%" >
										<bean:write name="helper" property="subSistemaEsgotoArquivoTexto.quantidadeImoveisAtualizado" />
									</td>
									
									<td align="center" width="15%" >
										<logic:equal name="helper" property="quantidadeErros" value="0">
											<bean:write name="helper" property="quantidadeErros" />
										</logic:equal>
										
										<logic:notEqual name="helper" property="quantidadeErros" value="0">
											<a href="gerarRelatorioArquivosSubSistemaErroAction.do?idArquivo=<bean:write name='helper' property='subSistemaEsgotoArquivoTexto.id' />">
												<bean:write name="helper" property="quantidadeErros" />
											</a>
										</logic:notEqual>
									</td>							
									</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
					</table>
					
					<p>&nbsp;</p>
		
					<table width="100%">
						
						<tr>
							<td width="40%" align="left">				
								<input type="button" 
									name="ButtonReset" 
									class="bottonRightCol" 
									tabindex="5"
									value="Desfazer" 
									onClick="javascript:window.location.href='exibirConsultarAtualizacaoSubSistemaAction.do?menu=sim'"> 
								
								<input type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Cancelar" 
									tabindex="4"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							  </td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
		<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</div>
	
</body>
</html:html>