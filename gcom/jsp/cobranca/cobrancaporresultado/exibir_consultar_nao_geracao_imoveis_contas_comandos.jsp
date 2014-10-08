<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.gui.cobranca.cobrancaporresultado.ConsultarNaoGeracaoImoveisContasComandosHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		
	<script type="text/javascript">
		function limparEmpresa() {
			var form = document.forms[0];
			
			form.idEmpresa.value = "";
			form.descricaoEmpresa.value = "";	
			form.dataInicial.value = '';
			form.dataFinal.value = '';
			
			form.action = 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do?limpar=sim';
			
			submeterFormPadrao(form);
		}
		
		function limparEmpresaTecla() {
			var form = document.forms[0];
			form.descricaoEmpresa.value = "";	
		}
		
		function pesquisarEmpresa() {
			var form = document.forms[0];
	
			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 300, 500);
		}
	
		function limparPeriodoFinal() {
			var form = document.forms[0];		
			form.dataFinal.value = '';		
		}
		
		function limparComandos() {
			var form = document.forms[0];
			
			form.action = 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do?limpar=sim';
			
			submeterFormPadrao(form);
		}
		
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    	var form = document.forms[0];
	   
		    if (tipoConsulta == 'empresa') {
				form.idEmpresa.value = codigoRegistro;
				form.descricaoEmpresa.value = descricaoRegistro;
				form.descricaoEmpresa.style.color = "#000000";
				form.action = 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do?limpar=sim';
	    		submeterFormPadrao(form);
	    	}
	    
	    }
		
		function validaForm() {
			var form =  document.forms[0];
			if(form.idEmpresa.value == null || form.idEmpresa.value == ""){
				alert('Informe Empresa');
			}				
			else{
				if(CheckboxNaoVazio(document.forms[0].idRegistro)){											
						submeterFormPadrao(form);									
				}
			}
		
		}

		function CheckboxNaoVazio(campo){
			  form = document.forms[0];
			  retorno = false;
				
			  for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
					form.comando.value = form.elements[indice].value;
					retorno = true;
					break;
				}
			  }
				
			  if (!retorno){
				alert('Informe o comando desejado.');
			  }
				
			  return retorno;
			} 
		
		function selecionarComandos(){
			var form =  document.forms[0];			
			//var perIni = true;
			//var perFin = true;
			if(form.idEmpresa.value !=null && form.idEmpresa.value !="" && form.idEmpresa.value !=" "){
				form.action = 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do?selecionarComandos=sim';
	    		form.submit();
				/*if(form.dataInicial.value !='' && form.dataInicial.value.length < 10){
					perIni = false;
					alert('Informe um período inicial válido');				
				}
				if(form.dataFinal.value !='' && form.dataFinal.value.length < 10){
					perFin = false;
					alert('Informe um período final válido');				
				}
				if(perIni && perFin){
					form.action = 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do?selecionarComandos=sim';
		    		form.submit();
				}*/
			}
			else{
				alert('Informe a Empresa');
			}
			
		}
	</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/consultarNaoGeracaoImoveisContasComandosAction"
	name="ConsultarNaoGeracaoImoveisContasComandosForm"
	type="gcom.gui.cobranca.cobrancaporresultado.ConsultarNaoGeracaoImoveisContasComandosForm"
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
					<td class="parabg">Consultar Motivo de Não Geração Cobrança por Resultado</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<input type="hidden" name="comando" value="" />			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para selecionar os comandos, informar os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14" 
						onkeypress="validaEnter(event, 'exibirConsultarNaoGeracaoImoveisContasComandosAction.do', 'idEmpresa');
						limparEmpresaTecla();
						return isCampoNumerico(event);"
						onchange="limparComandos();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" title="Pesquisar Empresa" border="0" ></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="descricaoEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="descricaoEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar"> </a>
					</td>
				</tr>
				<tr>
					<td><strong>Período de Início do Ciclo:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataInicial" size="10" tabindex="10"						
						onkeyup="replicarCampo(document.forms[0].dataFinal, this);return mascaraDataNova(this, event);"
						onchange="limparComandos();" />
					<a href="javascript:abrirCalendarioReplicando('ConsultarNaoGeracaoImoveisContasComandosForm', 'dataInicial', 'dataFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a>
					</strong> <html:text maxlength="10" property="dataFinal"
						tabindex="11" size="10" onkeyup="return mascaraDataNova(this, event);"
						onchange="limparComandos();" /> <a
						href="javascript:abrirCalendario('ConsultarNaoGeracaoImoveisContasComandosForm', 'dataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" title="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>


				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol"
						value="Selecionar Comandos"
						onClick="javascript:selecionarComandos();" /></td>
				</tr>
				<tr>
					<td colspan="2" align="left"><strong>Comandos de Contas em
					Cobrança:</strong></td>
				</tr>
				</table>
			 	<hr size="3" width="100%" color="#000000" style="margin-top: 5px;" NOSHADE/>
				<table width="100%" bgcolor="#99CCFF" style="margin-top: -5px;">
					<tr bordercolor="#000000">
						<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2">
							<br />
							<strong>Selecionar</strong>							
						</td>
						<td width="10%" bordercolor="#000000" bgcolor="#90c7fc" align="left" rowspan="2">
							<br />
							<div align="center"><strong>Comando</strong></div>							
						</td>
					</tr>
				</table>
				<logic:present name="colecaoConsultarNaoGeracaoImoveisContasComandosHelper">
					<table width="100%" bgcolor="#99CCFF">
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							
								<%int cont = 0;%>
								<logic:iterate
											name="colecaoConsultarNaoGeracaoImoveisContasComandosHelper"
											id="helper"
											type="ConsultarNaoGeracaoImoveisContasComandosHelper"
											scope="session">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												</tr>
												<tr bgcolor="#FFFFFF">
													<%}%>											
													<td width="10%">
														<div align="center"><html:radio property="idRegistro"
															value="${helper.idComando}" />
														</div>
													</td>
													
													<td align="center" width="10%">
														<a href="javascript:abrirPopup('exibirConsultarComandosContasCobrancaEmpresaPopupAction.do?pesquisa=nao&idComandoEmpresaCobrancaConta=<%=helper.getIdComando()%>', 475, 600);">
														<%=helper.getIdComando()%>
														</a>
													</td>
									</pg:item>
								</logic:iterate>
								<tr>
									<td colspan="2">
									<div align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></div>										
									</td>
								</tr>						
						</pg:pager>				
					</table>			
				</logic:present>
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
								onclick="window.location.href='<html:rewrite page="/exibirConsultarNaoGeracaoImoveisContasComandosAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td>								
							<input name="Button" type="button" class="bottonRightCol"
								value="Consultar Motivo de Não Geração" style="float: right;"
								onclick="javascript:validaForm();">
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