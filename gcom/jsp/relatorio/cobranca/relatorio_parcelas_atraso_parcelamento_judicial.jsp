<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>
	<%@ include file="/jsp/util/titulo.jsp"%>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
	<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm"/>
	
	
	<SCRIPT LANGUAGE="JavaScript">
		
		function limparCliente(){
			var form = document.forms[0];
	
			form.idClienteResponsavel.value = '';
			form.nomeClienteResponsavel.value = '';
		}
	
		function limparImovel(){
			var form = document.forms[0];
	
			form.idImovelPrincipal.value = '';
			form.descricaoImovelPrincipal.value = '';
		}
	
		function limparClienteTecla(){
			var form = document.forms[0];
	
			form.nomeClienteResponsavel.value = '';
		}
	
		function limparImovelTecla(){
			var form = document.forms[0];
	
			form.descricaoImovelPrincipal.value = '';
		}
		
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		    var form = document.forms[0];
	
		    if (tipoConsulta == 'cliente') {
	    		form.idClienteResponsavel.value = codigoRegistro;
				form.nomeClienteResponsavel.value = descricaoRegistro;
				form.nomeClienteResponsavel.style.color = "#000000";
	    	} else if (tipoConsulta == 'imovel') {
	    		form.idImovelPrincipal.value = codigoRegistro;
				form.descricaoImovelPrincipal.value = descricaoRegistro;
				form.descricaoImovelPrincipal.style.color = "#000000";
	    	}
	
		}

		function validar() {
			var form = document.forms[0];

			if(validateGerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm(form)){
				if (form.dataParcelamentoInicial.value != null
						&& form.dataParcelamentoInicial.value != ''
						&& !validaDataSemMensagem(form.dataParcelamentoInicial)) {
					alert('Data Inicial inválida');
				} else if (form.dataParcelamentoFinal.value != null
						&& form.dataParcelamentoFinal.value != ''
							&& !validaDataSemMensagem(form.dataParcelamentoFinal)) {
					alert('Data Final inválida');
				} else {
					toggleBox('demodiv',1);
				}
			}
		}

		function validarQuantidadeDiasAtraso(){
			var form = document.forms[0];

			if(form.quantidadeDiasAtraso.value=="0"){
				form.quantidadeDiasAtraso.value="";
				alert("Quantidade Máx. Dias em Atraso inválido");
				return false;
			}else{
				return true;
			}


		}
	
	</script>

</head>

<body leftmargin="5" topmargin="5" >
	<div id="formDiv">
		<html:form action="/gerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction"
			name="GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm"
			type="gcom.gui.relatorio.cobranca.GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm"
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
							<td class="parabg">Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para gerar um relatório com as parcelas em atraso dos Parcelamentos Judiciais, informe os dados abaixo:</td>
						</tr>
						
						<tr>
							<td><strong>Cliente Responsável:</strong></td>
							<td>
								<html:text maxlength="9" property="idClienteResponsavel" size="9"
									tabindex="1"  styleClass="tipoInteiro"
									onkeyup="validaEnterComMensagem(event, 'exibirGerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction.do', 'idClienteResponsavel', 'Cliente Responsável'); limparClienteTecla();" />
								<a
									href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limpaForm=S', 495, 300);"><img
									src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
									height="21" border="0" title="Pesquisar Cliente Responsável"></a> <logic:present
									name="clienteInexistente" scope="session">
									<html:text property="nomeClienteResponsavel" size="40" maxlength="40"
										readonly="true"
										style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
								</logic:present> <logic:notPresent name="clienteInexistente"
									scope="session">
									<html:text property="nomeClienteResponsavel" size="40" maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent> <a href="javascript:limparCliente();" title="Limpar Cliente Responsável"> <img
									border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
							</td>
						</tr>
						<tr>
				   			<td><strong>Imóvel Principal:</strong></td>
		                   	<td>
		                 		<html:text maxlength="9" property="idImovelPrincipal" size="9"  
	                 				tabindex="2" styleClass="tipoInteiro" 
		                 			onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction.do', 'idImovelPrincipal', 'Imóvel Principal'); limparImovelTecla();"/>
		                    	<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img border="0"
		                    		 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Imóvel Principal"/></a>
		                    	<logic:present name="imovelInexistente" scope="session">
		                        	<html:text property="descricaoImovelPrincipal" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
		                    	</logic:present>
		                    	<logic:notPresent name="imovelInexistente" scope="session">
		                    		<html:text property="descricaoImovelPrincipal" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		                    	</logic:notPresent><a href="javascript:limparImovel();" title="Limpar Imóvel Principal"> <img
									border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
							</td>
		                </tr>
		                <tr>
							<td>
								<strong>Nº Processo Judicial:</strong>
							</td>
		                 	<td>
		               			<html:text maxlength="25" 
		               					   property="numeroProcessoJudicial" 
		               					   size="25"  
		               					   tabindex="5" 
		               					   styleClass="tipoProcessoJudicial" />
		               		</td>
		              	</tr>
		              	<tr>
							<td>
								<strong>Quantidade Máx. Dias em Atraso:</strong>
							</td>
		                 	<td>
		               			<html:text maxlength="9" 
		               					   property="quantidadeDiasAtraso" 
		               					   size="9"  
		               					   tabindex="5"
		               					   onblur="validarQuantidadeDiasAtraso();"
		               					   onkeypress="validarQuantidadeDiasAtraso();" 
		               					   styleClass="tipoInteiro" />
		               		</td>
		              	</tr>	
		              	<tr>
							<td>
								<strong>Período Parcelamento:</strong>
							</td>
		                 	<td>
		               			<html:text property="dataParcelamentoInicial" 
									styleClass="tipoDataInicial"
									size="11" 
									maxlength="10" 
									tabindex="3" 
									value=""/>
								<a href="javascript:abrirCalendarioReplicando('GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm', 'dataParcelamentoInicial', 'dataParcelamentoFinal')">			
									<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
								a 
								<html:text property="dataParcelamentoFinal" 
									styleClass="tipoDataFinal"
									size="11" 
									maxlength="10" 
									tabindex="3" 
									value=""/>
								<a href="javascript:abrirCalendario('GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm', 'dataParcelamentoFinal')">			
									<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a> (dd/mm/aaaa)
								
		               		</td>
		              	</tr>	
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
		
						<tr>
							<td width="27%">
								<input name="Button" type="button" class="bottonRightCol"
									value="Limpar" align="left"
									onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction.do?menu=sim"/>'">
								<input name="Button" type="button" class="bottonRightCol"
									value="Cancelar" align="left"
									onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<input type="button" name="gerar" 
									class="bottonRightCol" value="Gerar"
									onClick="validar();" />
							</td>
						</tr>
		
					</table>
		
			</table>
		
			<jsp:include
					page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction.do" 
			/>	
			<%@ include file="/jsp/util/rodape.jsp"%> 
		</html:form>
	</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>