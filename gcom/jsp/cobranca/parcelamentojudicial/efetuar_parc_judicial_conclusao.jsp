 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<!--================================= SCRIPTS =============================================================-->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<html:javascript staticJavascript="false"  formName="EfetuarParcelamentoJudicialActionForm" dynamicJavascript="false" />
		<!--=======================================================================================================-->
		
		<script language="JavaScript">
		
		
		$(document).ready(function(){
			verificarIndicadoresSelecionados();
			verificarIndicadorParcelamentoComJuros();
			verificarIndicadorInformarValorParcela();
			verificarIndicadorEntradaParcelamento();
			
			
			$('[@name=indicadorValorCustas],[@name=indicadorValorHonorarios],[@name=indicadorParcelamentoComJuros],[@name=indicadorEntradaParcelamento]').bind('change',function(){
				verificarIndicadoresSelecionados();
				verificarIndicadorParcelamentoComJuros();
				verificarIndicadorInformarValorParcela();
				verificarIndicadorEntradaParcelamento();
				limparListaParcelas()
			});
			
			
			$('[@name=indicadorInformarValorParcela]').bind('change',function(){
				verificarIndicadorInformarValorParcela();
				limparListaParcelas();
			});
			
			
			
			$('[@name=diaVencimentoParcelas]').bind("keypress",function(event){
				//Se a tecla pressionada for igual a Enter
				if(event.keyCode == 13){
					if($('[@name=diaVencimentoParcelas]').val() == ""){
						alert("Informe Dia do vencimento das Parcelas");						
					}	
					else{
						calcularDataVencimentoPrimeiraParc();
					}
				}
			})
			.bind("blur",function(event){
				if($(this).val() != "")
					calcularDataVencimentoPrimeiraParc();						
			})
			.bind("change",function(event){
				if($(this).val() != ""){
					calcularDataVencimentoPrimeiraParc();
					limparListaParcelas();
				}
			});
			
			
			
			$('[@name=qtdParcelas],[@name=valorEntrada],[@name=percentualJuros]').bind("change",function(){
				limparListaParcelas();
			});
			
			
			$('#botaoCalcularParcelas').bind('click',function(){	
				if(validarCalcularParcelas()){
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialConclusaoAction&metodo='+$('[@name=metodoCalcularValorParcelas]').val());
				}
				
			});
			
			
			$('#botaoInformarParcelas').bind('click',function(){
				if(validarInformarParcelas()){
					abrirPopup('efetuarParcelamentoJudicialWizardAction.do?action=exibirInformarValorParcelasPopupAction'+
							'&dataVencimentoPrimeiraParcela='+$('[@name=dataVencimentoPrimeiraParcela]').val()+
							'&diaVencimentoParcelas='+$('[@name=diaVencimentoParcelas]').val(), 350, 650);
				}
			});
			
		});
		
		function validateEfetuarParcelamentoJudicialActionForm(form){
			return true;
		}		
		
		function verificarIndicadoresSelecionados(){
			if($('[@name=indicadorValorCustas]:checked').val() == $('[@name=nao]').val()
				  && $('[@name=indicadorValorHonorarios]:checked').val() == $('[@name=nao]').val()
				  && $('[@name=indicadorParcelamentoComJuros]:checked').val() == $('[@name=nao]').val()
				  && $('[@name=indicadorEntradaParcelamento]:checked').val() == $('[@name=nao]').val()){
				
				$('[@name=indicadorInformarValorParcela]').removeAttr('disabled');
			}
			else{
				$('[@name=indicadorInformarValorParcela][value='+$('[@name=nao]').val()+']').attr('checked','checked');
				$('[@name=indicadorInformarValorParcela]').attr('disabled','disabled');
			}			
		}
		
		function verificarIndicadorParcelamentoComJuros(){
			if($('[@name=indicadorParcelamentoComJuros]:checked').val() == $('[@name=nao]').val()){
				$('[@name=percentualJuros]').val("");
				$('[@name=percentualJuros]').addClass('campoBloqueado');
				$('[@name=percentualJuros]').attr('readonly',true);
			}
			else{
				$('[@name=percentualJuros]').attr('readonly',false);
				$('[@name=percentualJuros]').removeClass('campoBloqueado');
			}
		}
		
		
		function verificarIndicadorInformarValorParcela(){
			if($('[@name=indicadorInformarValorParcela]:checked').val() == $('[@name=nao]').val()){
				$('#botaoInformarParcelas').attr('disabled','disabled');
				$('#botaoCalcularParcelas').removeAttr('disabled');
				$('[@name=qtdParcelas]').attr('readOnly',false);
				$('[@name=qtdParcelas]').removeClass('campoBloqueado');
			}
			else{
				$('#botaoInformarParcelas').removeAttr('disabled');
				$('#botaoCalcularParcelas').attr('disabled','disabled');
				$('[@name=qtdParcelas]').attr('readOnly',true);
				if($('[@name=indicadorNaoApagarQtdParcelas]').val() == "")
					$('[@name=qtdParcelas]').val("");
				else
					$('[@name=indicadorNaoApagarQtdParcelas]').val("");
				$('[@name=qtdParcelas]').addClass('campoBloqueado');
			}
		}
		
		function verificarIndicadorEntradaParcelamento(){
			if($('[@name=indicadorEntradaParcelamento]:checked').val() == $('[@name=sim]').val()){
				$('[@name=dataVencimentoEntrada]').attr('readonly',false);
				$('[@name=dataVencimentoEntrada]').removeClass('campoBloqueado');
				$('[@name=valorEntrada]').attr('readonly',false);
				$('[@name=valorEntrada]').removeClass('campoBloqueado');
				$('#idCalendarioDataEntrada').attr("href","javascript:abrirCalendario('EfetuarParcelamentoJudicialActionForm', 'dataVencimentoEntrada');");
			}
			else{
				$('[@name=dataVencimentoEntrada]').val('');
				$('[@name=dataVencimentoEntrada]').addClass('campoBloqueado');
				$('[@name=valorEntrada]').val('');
				$('[@name=valorEntrada]').addClass('campoBloqueado');
				$('[@name=dataVencimentoEntrada]').attr('readonly',true);
				$('[@name=valorEntrada]').attr('readonly',true);
				$('#idCalendarioDataEntrada').attr('href','javascript:void(0);');
			}
		}
		
		function reexibir(){
			redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialConclusaoAction');
		}
		
		function validarCalcularParcelas(){
			if($('[@name=dataVencimentoPrimeiraParcela]').val() == ""){
				alert("Informe a Data de Vencimento da Primeira Parcela");
				return false;
			}
			
			if(!verificaDataSemMensagem($('[@name=dataVencimentoPrimeiraParcela]').get(0))){
				alert('Data de Vencimento da Primeira Parcela inválida');
				return false;
			}
			
			if($('[@name=qtdParcelas]').val() == ""){
				alert("Informe a Quantidade de Parcelas");
				return false;
			}
			
			if($('[@name=indicadorEntradaParcelamento]:checked').val() == $('[@name=sim]').val()){
				if($('[@name=valorEntrada]').val() == ""){
					alert("Informe o Valor da Entrada");
					return false;
				}
			}
				
			if($('[@name=indicadorParcelamentoComJuros]:checked').val() == $('[@name=sim]').val()){
				if($('[@name=percentualJuros]').val() == "" || 
						parseFloat($('[@name=percentualJuros]').val().replace(',','.')) == parseFloat("0")){
					alert("Informe o Percentual de Juros");
					return false;
				}
				if(parseFloat($('[@name=percentualJuros]').val().replace(',','.')) > parseFloat("100")){
					alert("Percentual dos Juros inválido.");
					return false;
				}			
			}
			
			return true;
		}
		
		function validarInformarParcelas(){
			if($('[@name=dataVencimentoPrimeiraParcela]').val() == ""){
				alert("Informe a Data de Vencimento da Primeira Parcela");
				return false;
			}
			
			if(!verificaDataSemMensagem($('[@name=dataVencimentoPrimeiraParcela]').get(0))){
				alert('Data de Vencimento da Primeira Parcela inválida');
				return false;
			}

			
			if($('[@name=diaVencimentoParcelas]').val() == ""){
				alert("Informe o Dia do Vencimento das Parcelas");
				return false;
			}
			
			return true;
		}
		
		function calcularDataVencimentoPrimeiraParc(){
			var theForm = $("form[name=EfetuarParcelamentoJudicialActionForm]");
			var params = theForm.serialize();
			var actionURL = 'efetuarParcelamentoJudicialWizardAction.do?action='+
							'exibirEfetuarParcelamentoJudicialConclusaoAction&metodo='+$('[@name=metodoCalcularDataVencPrimeiraParc]').val(); 
			$.ajax({
			    type:"POST",
			    url:actionURL,
			    data:params,
			    async:false,
			    success:function(data, textStatus, XMLHttpRequest){
			    	$('[@name=dataVencimentoPrimeiraParcela]').val(data);
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			        alert(XMLHttpRequest.responseText);
			    }
			});
		}
		
		function limparListaParcelas(){
			if($('[@name=existeListaParcelaJudicial]').val() == 'true'){
				$('#botaoConcluir').attr('disabled','disabled');
				var actionURL = 'efetuarParcelamentoJudicialWizardAction.do?action='+
								'exibirEfetuarParcelamentoJudicialConclusaoAction&metodo='+$('[@name=metodoLimparListaParcelas]').val(); 
				redirecionarSubmit("/gsan/"+actionURL);
			}
		}
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/efetuarParcelamentoJudicialWizardAction"
				   name="EfetuarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.EfetuarParcelamentoJudicialActionForm"
				   method="post">			
				   	   
				<html:hidden property="nao"/>
				<html:hidden property="sim"/> 
				<html:hidden property="metodoCalcularValorParcelas"/>
				<html:hidden property="metodoCalcularDataVencPrimeiraParc"/>
				<html:hidden property="metodoLimparListaParcelas"/>
				<input type="hidden" name="existeListaParcelaJudicial" 
									 value="${not empty EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial 
									 && fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial) gt 0 }" />
				<input type="hidden" name="indicadorNaoApagarQtdParcelas" value="${requestScope.indicadorNaoApagarQtdParcelas}" />					 
				   
			<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=4"/>
				   
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
					<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
								<td class="parabg">Efetuar Parcelamento Judicial</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<p>Para confirmar o Parcelamento Judicial, informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO =========================-->						
							<tr>
								<td colspan="4" width="100%">
									<table border="0" width="100%" bgcolor="#90c7fc">
										<tr bgcolor="#79bbfd" bordercolor="#79bbfd">
											<td colspan="5" align="center">
												<strong>Valores do Débito do Cliente</strong>
											</td>
										</tr>
										<tr>
											<td bgcolor="#90c7fc" align="center" width="25%"><strong>Débito</strong></td>
											<td bgcolor="#90c7fc" align="center" width="25%"><strong>Acordo</strong></td>
											<td bgcolor="#90c7fc" align="center" width="16%"><strong>Desconto</strong></td>
											<td bgcolor="#90c7fc" align="center" width="16%"><strong>Custas</strong></td>
											<td bgcolor="#90c7fc" align="center" width="16%"><strong>Honorários</strong></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="25%"><bean:write property="valorDebito" name="EfetuarParcelamentoJudicialActionForm"/></td>
											<td width="25%"><bean:write property="valorAcordo" name="EfetuarParcelamentoJudicialActionForm"/></td>
											<td width="16%"><bean:write property="percentualDesconto" name="EfetuarParcelamentoJudicialActionForm"/>%</td>
											<td width="16%"><bean:write property="valorCustas" name="EfetuarParcelamentoJudicialActionForm"/></td>
											<td width="16%"><bean:write property="valorHonorarios" name="EfetuarParcelamentoJudicialActionForm"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>		
							<tr>
								<td width="25%"><strong>Perde Desconto na Parcela Paga em Atraso:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="25%">
			               			<html:radio property="indicadorPerdeDesconto" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorPerdeDesconto" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td width="25%"><strong>Valor das Custas serão parcelados:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="25%">
			               			<html:radio property="indicadorValorCustas" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorValorCustas" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td width="25%"><strong>Valor dos Honorários serão parcelados:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="25%">
			               			<html:radio property="indicadorValorHonorarios" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorValorHonorarios" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td width="25%"><strong>Parcelamento com Juros:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="25%">
			               			<html:radio property="indicadorParcelamentoComJuros" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorParcelamentoComJuros" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td width="25%"><strong>Permite Informar o Valor da Parcela:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="75%">
			               			<html:radio property="indicadorInformarValorParcela" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorInformarValorParcela" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td width="25%"><strong>Parcelamento com Entrada:<font color="#FF0000">*</font></strong></td>
	                   			<td height="24" width="75%">
			               			<html:radio property="indicadorEntradaParcelamento" value="${EfetuarParcelamentoJudicialActionForm.sim}">Sim</html:radio>
			               			<html:radio property="indicadorEntradaParcelamento" value="${EfetuarParcelamentoJudicialActionForm.nao}">Não</html:radio>
			               		</td>
							</tr>
							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>
							<tr>
								<td width="22%"><strong>Data de Vencimento da Entrada:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="10" 
			               					   property="dataVencimentoEntrada"
			               					   size="10"  
			               					   styleClass="tipoData"/>
			               					   <a href="javascript:abrirCalendario('EfetuarParcelamentoJudicialActionForm', 'dataVencimentoEntrada')" 
			               					      onkeypress="return isCampoNumerico(event);" id="idCalendarioDataEntrada"><img align="absmiddle" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="9" /></a> (dd/mm/aaaa)
			               		</td>
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Valor da Entrada:<font color="#FF0000">*</font></strong></td>
	                   			<td width="25%" height="24" colspan="2">
			               			<html:text maxlength="20" 
			               					   property="valorEntrada" 
			               					   size="20" 
			               					   styleClass="tipoMonetario" />
			               		</td>
			               	</tr>
			               	<tr>
								<td width="22%"><strong>Dia do Vencimento das Parcelas:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="2" 
			               					   property="diaVencimentoParcelas"
			               					   size="2"  
			               					   styleClass="tipoInteiro"/>
			               		</td>
		               		</tr>
							<tr>
								<td width="22%"><strong>Data de Vencimento 1ª Parcela:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="10" 
			               					   property="dataVencimentoPrimeiraParcela"
			               					   size="10"  
			               					   styleClass="tipoData"
			               					   readonly="true"
			               					   style="background-color:#EFEFEF; border:0;"/>(dd/mm/aaaa)
			               		</td>
		               		</tr>
		               		<tr>
								<td width="22%"><strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="5" 
			               					   property="qtdParcelas"
			               					   size="5"  
			               					   styleClass="tipoInteiro"
			               					   tabindex="1"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="22%"><strong>Percentual de Juros:</strong></td>
	                   			<td width="81%" height="24" colspan="3">
			               			<html:text maxlength="6" 
			               					   property="percentualJuros"
			               					   size="6"  
			               					   styleClass="tipoPorcentagemJuros"
			               					   tabindex="1"/>
			               			<input type="button" value="Calcular Parcelas" class="bottonRightCol" id="botaoCalcularParcelas" style="margin-left:70px;margin-right:2px;"/>		   
			               			<input type="button" value="Informar Valor das Parcelas" class="bottonRightCol" id="botaoInformarParcelas"/>
			               		</td>
		               		</tr>
		               		
		               		<tr>
								<td colspan="4" width="100%">
									<table border="0" width="100%" bgcolor="#90c7fc">
										<tr bgcolor="#79bbfd" bordercolor="#79bbfd">
											<td colspan="3" align="center">
												<strong>Condições de Negociação</strong>
											</td>
										</tr>
										<tr bgcolor="#90c7fc">
											<td width="22%"><strong>Valor Parcelado:</strong></td>
				                   			<td width="78%" height="24" colspan="2">
						               			<html:text maxlength="15" 
						               					   property="valorParcelado"
						               					   size="15"  
						               					   readonly="true"
						               					   styleClass="campoBloqueado"/>
						               		</td>
						               	</tr>
						           	</table>    
						           	<div id="tabelaListaParcelaJudicial">	
						               	<c:if test="${not empty EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial 
												&& fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial) gt 0 }">									
											<table border="0" width="100%" bgcolor="#90c7fc">	
												<tr>
													<td bgcolor="#90c7fc" align="center" width="15%"><strong>Parcela</strong></td>
													<td bgcolor="#90c7fc" align="center" width="55%"><strong>Valor da Parcela</strong></td>
													<td bgcolor="#90c7fc" align="center" width="25%"><strong>Data de Vencimento</strong></td>
												</tr>
											</table>
											<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial) gt 7 }">																								 
												<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
											</c:if>		
											<table border="0" width="100%" bgcolor="#90c7fc">
												<c:set var="count" value="0"/>
												<logic:iterate name="EfetuarParcelamentoJudicialActionForm" 
															   property="listaParcelaJudicial" 
															   id="parcela">
													<c:choose>
														<c:when test="${count % 2 == 0 }">
															<tr bgcolor="#FFFFFF">		
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
																<td align="center" width="15%"><bean:write property="numeroParcelaFormatado" name="parcela"/></td>
																<td align="right" width="55%"><bean:write property="valorParcelaFormatado" name="parcela"/></td>
																<td align="center" width="25%"><bean:write property="dataVencimentoFormatada" name="parcela"/></td>
															</tr>
															<c:set var="count" value="${count+1}"/> 	
												</logic:iterate>
											</table>
											<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaParcelaJudicial) gt 7 }">																								 
												</DIV>
											</c:if>														
										</c:if>
									</div>
								</td>
							</tr>
							
		               		<tr>
								<td>&nbsp;</td>
							</tr>
                   			<tr>
								<td>&nbsp;</td>
								<td align="left" colspan="2"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							<tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0">
										<tr>
									        <td colspan="3">
												<div align="right">
													<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=4"/>
												</div>
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
		</html:form>
		</div>
		<%@ include file="/jsp/util/telaespera.jsp"%>
	</body>
</html:html>
