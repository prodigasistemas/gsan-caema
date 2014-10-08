<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

	<head>

		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">			
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		
		<script type="text/javascript">
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
				
			    var form = document.forms[0];
			
			    if (tipoConsulta == 'arrecadador') {
			      form.codigoArrecadador.value = codigoRegistro;
			      form.nomeArrecadador.value = descricaoRegistro;
			      form.nomeArrecadador.style.color = "#000000";			    
			
			    }
			  }
			function limparPesquisaArrecadador(form) {
			    form.codigoArrecadador.value = "";
			    form.nomeArrecadador.value = "";			 
			}
			function limparPesquisaArrecadadorTecla(form) {			    
			    form.nomeArrecadador.value = "";			 
			}
			function validarForm(){
		    	var form = document.forms[0];
				if(form.periodoProcessamentoInicial.value != '') {
					form.submit();
				}else{
					alert("Informe o Período de Processamento");
				}
			}
		</script>
	
	</head>

	<body leftmargin="5" topmargin="5">

		<html:form action="/consultarCriticasMovimentoArrecadadoresAction"
			name="ConsultarCriticasMovimentoArrecadadoresActionForm"
			type="gcom.gui.arrecadacao.ConsultarCriticasMovimentoArrecadadoresActionForm"
			method="post">
	
	
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
				<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
	
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Consultar Críticas dos Movimentos Arrecadadores</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para consultar críticas de movimento do arrecadador, informe os dados abaixo: </td>					
					</tr>
					<tr>
						<td><strong>Arrecadador: </strong></td>
						<td><html:text property="codigoArrecadador" maxlength="3" size="4"
								onkeypress="validaEnterComMensagem(event, 'exibirConsultarCriticasMovimentoArrecadadoresAction.do?pesquisaArrecadador=true', 'codigoArrecadador', 'Arrecadador')
								limparPesquisaArrecadadorTecla(document.forms[0]);
								return isCampoNumerico(event);"
								/><a
							href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');"><img
							 width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Arrecadador" /></a>
							<logic:present name="arrecadadorNaoEncontrado">						
									<html:text property="nomeArrecadador" size="31" maxlength="30"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="arrecadadorNaoEncontrado">
									<html:text property="nomeArrecadador" size="31" maxlength="30"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
							<a href="javascript:limparPesquisaArrecadador(document.forms[0]);"><img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
						</td>				
 					</tr>
 					<tr>
 						<td><strong>Identificação do Serviço: </strong></td>
 						<td>
 							<html:select property="idServico">
 								<html:option value="-1">&nbsp;</html:option> 								
 								<html:option value="CODIGO DE BARRAS">CÓDIGO DE BARRAS</html:option>
 								<html:option value="DEBITO AUTOMATICO">DÉBITO AUTOMÁTICO</html:option>
 								<html:option value="FICHA DE COMPENSACAO">FICHA DE COMPENSAÇÃO</html:option>
 							</html:select>
 						</td>
 					</tr>
 					<tr>
 						<td><strong>Número Sequencial do Arquivo (NSA): </strong></td>
 						<td>
 							<html:text property="numeroSequencialArquivo" maxlength="9" size="10" 
 							onkeypress="return isCampoNumerico(event);"
 							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].numeroSequencialArquivo, 'Número Sequencial Arquivo');" />
 						</td>
 					</tr>
 					<tr>
 						<td><strong>Período de Geração do Movimento: </strong></td>
 						<td>
 							<html:text maxlength="10"
						property="periodoGeracaoInicial" size="10" tabindex="10"						
						onkeyup="replicarCampo(document.forms[0].periodoGeracaoFinal, this);return mascaraDataNova(this, event);"/>
					<a href="javascript:abrirCalendarioReplicando('ConsultarCriticasMovimentoArrecadadoresActionForm', 'periodoGeracaoInicial', 'periodoGeracaoFinal');"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a><strong> a </strong>
					 <html:text maxlength="10" property="periodoGeracaoFinal"
						tabindex="11" size="10" onkeyup="return mascaraDataNova(this, event);"/><a
						href="javascript:abrirCalendario('ConsultarCriticasMovimentoArrecadadoresActionForm', 'periodoGeracaoFinal')"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a>
					(dd/mm/aaaa)
 						</td>
 					</tr>
 					<tr>
 						<td><strong>Período de Processamento do Movimento: <font color="#FF0000">*</font></strong></td>
 						<td>
 							<html:text maxlength="10"
						property="periodoProcessamentoInicial" size="10" tabindex="10"						
						onkeyup="replicarCampo(document.forms[0].periodoProcessamentoFinal, this);return mascaraDataNova(this, event);" />
					<a href="javascript:abrirCalendarioReplicando('ConsultarCriticasMovimentoArrecadadoresActionForm', 'periodoProcessamentoInicial', 'periodoProcessamentoFinal');"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a><strong> a </strong>
					 <html:text maxlength="10" property="periodoProcessamentoFinal"
						tabindex="11" size="10" onkeyup="return mascaraDataNova(this, event);"/><a
						href="javascript:abrirCalendario('ConsultarCriticasMovimentoArrecadadoresActionForm', 'periodoProcessamentoFinal')"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a>
					(dd/mm/aaaa)
 						</td> 
 					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirConsultarCriticasMovimentoArrecadadoresAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td>								
							<input name="Button" type="button" class="bottonRightCol"
								value="Consultar" style="float: right;"
								onclick="javascript:validarForm();">
						</td>					
					</tr>
				</table>
				</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</body>
</html:html>
