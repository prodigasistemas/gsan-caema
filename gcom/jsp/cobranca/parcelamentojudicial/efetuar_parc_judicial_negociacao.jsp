<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
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
			var todosSelecionados = false;
			
			function validateEfetuarParcelamentoJudicialActionForm(form){
				return true;
			}
				
			$(document).ready(function(){
				
				bloquearDesbloquearCustasHonorarios();
				
				//Valor Custas
				//========================================================================================================
				$('[@name=valorCustas],[@name=percentualCustas]').bind("keypress",function(event){
					if($(this).attr('readonly') == false){
						//Se a tecla pressionada for diferente de Enter
						if(event.keyCode != 13){
							validarExibicao(this);		
						}				
						//caso contrário
						else{
							if($("[@name=valorAcordo]").val() != ''){
								if($(this).attr('name') == 'valorCustas' && $(this).val() == ''){
									alert('Informe Valor Custas');
								}
								else if($(this).attr('name') == 'percentualCustas' && $(this).val() == ''){
									alert('Informe Percentual Custas');
								}
								else{
									calcularCustas($(this).attr('name'));
								}
							}
							else{
								alert('Informe Valor do Acordo');
							}
							validarExibicao(this);
						}
					}
				})
				.bind("blur",function(event){
					if($(this).attr('readonly') == false){
						if($(this).val() != ""){
							if($("[@name=valorAcordo]").val() != ''){
								calcularCustas($(this).attr('name'));
							}
							else{
								alert('Informe Valor do Acordo');
							}
							validarExibicao(this);
						}						
					}
				})
				.bind("keyup",function(event){
					if($(this).attr('readonly') == false){
						if(($(this).attr('name') == 'valorCustas' && $(this).val() == '')){
							$('[@name=percentualCustas]').val("");
						}
						else if($(this).attr('name') == 'percentualCustas' && $(this).val() == ''){
							$('[@name=valorCustas]').val("");
						}
						validarExibicao(this);
					}
				});
				//========================================================================================================
					
				//Valor Honorários
				//========================================================================================================
				$('[@name=valorHonorarios],[@name=percentualHonorarios]').bind("keypress",function(event){
					if($(this).attr('readonly') == false){
						//Se a tecla pressionada for diferente de Enter
						if(event.keyCode != 13){
							validarExibicao(this);		
						}				
						//caso contrário
						else{
							if($("[@name=valorAcordo]").val() != ''){
								if($(this).attr('name') == 'valorHonorarios' && $(this).val() == ''){
									alert('Informe Valor Honorários');
								}
								else if($(this).attr('name') == 'percentualHonorarios' && $(this).val() == ''){
									alert('Informe Percentual Honorários');
								}
								else{
									calcularHonorarios($(this).attr('name'));
								}
							}
							else{
								alert('Informe Valor do Acordo');
							}
							validarExibicao(this);
						}
					}
				})
				.bind("blur",function(event){
					if($(this).attr('readonly') == false){
						if($(this).val() != ""){
							if($("[@name=valorAcordo]").val() != ''){
								calcularHonorarios($(this).attr('name'));
							}
							else{
								alert('Informe Valor do Acordo');
							}
							validarExibicao(this);
						}
					}
				})
				.bind("keyup",function(event){
					if($(this).attr('readonly') == false){
						if(($(this).attr('name') == 'valorHonorarios' && $(this).val() == '')){
							$('[@name=percentualHonorarios]').val("");
						}
						else if($(this).attr('name') == 'percentualHonorarios' && $(this).val() == ''){
							$('[@name=valorHonorarios]').val("");
						}
						validarExibicao(this);
					}
				});
				//========================================================================================================	
					

				$('[@name=valorAcordo]').bind('keyup blur',function(){								
					if($('[@name=valorAcordo]').val() == ""){
						$('[@name=percentualDescontoComSimbolo]').val("");
					}
				});
			});
			
			
			function calcularDesconto(){
				if($("[@name=valorAcordo]").val() != ''){
					var theForm = $("form[name=EfetuarParcelamentoJudicialActionForm]");
					var params = theForm.serialize();
					var actionURL = 'efetuarParcelamentoJudicialWizardAction.do?action='+
									'exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo='+$('[@name=metodoCalcularDesconto]').val(); 
					$.ajax({
					    type:"POST",
					    url:actionURL,
					    data:params,
					    success:function(data, textStatus, XMLHttpRequest){
					    	$('[@name=percentualDescontoComSimbolo]').val(data+"%");
					    },
					    error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.responseText);
					    }
					});
				}
				else{
					alert('Informe Valor do Acordo');
				}
			}
			
			function calcularCustas(campo){
				var theForm = $("form[name=EfetuarParcelamentoJudicialActionForm]");
				var params = theForm.serialize();
				var actionURL = 'efetuarParcelamentoJudicialWizardAction.do?action='+
								'exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo='+$('[@name=metodoCalcularCustas]').val(); 
				if(campo == 'valorCustas')
					actionURL = actionURL + '&quemChamou=valor';		
				else			
					actionURL = actionURL + '&quemChamou=percentual';
				$.ajax({
				    type:"POST",
				    url:actionURL,
				    async:false,
				    data:params,
				    success:function(data, textStatus, XMLHttpRequest){
				    	if(campo == 'valorCustas'){
				    		var retorno = data.split("-");
				    		$('[@name=percentualCustas]').val(retorno[0]);
				    		$('[@name=bloqueioCustas]').val(retorno[1]);
				    	}
				    	else{
				    		var retorno = data.split("-");
				    		$('[@name=valorCustas]').val(retorno[0]);
				    		$('[@name=bloqueioCustas]').val(retorno[1]);				    		
				    	}
				    },
				    error:function(XMLHttpRequest, textStatus, errorThrown){
				        alert(XMLHttpRequest.responseText);
				    }
				});
			}
			
			function calcularHonorarios(campo){
				var theForm = $("form[name=EfetuarParcelamentoJudicialActionForm]");
				var params = theForm.serialize();
				var actionURL = 'efetuarParcelamentoJudicialWizardAction.do?action='+
								'exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo='+$('[@name=metodoCalcularHonorarios]').val(); 
				if(campo == 'valorHonorarios')
					actionURL = actionURL + '&quemChamou=valor';		
				else			
					actionURL = actionURL + '&quemChamou=percentual';
				$.ajax({
				    type:"POST",
				    url:actionURL,
				    data:params,
				    async:false,
				    success:function(data, textStatus, XMLHttpRequest){
				    	if(campo == 'valorHonorarios'){
				    		var retorno = data.split("-");
				    		$('[@name=percentualHonorarios]').val(retorno[0]);
				    		$('[@name=bloqueioHonorarios]').val(retorno[1]);
				    	}
				    	else{
				    		var retorno = data.split("-");
				    		$('[@name=valorHonorarios]').val(retorno[0]);
				    		$('[@name=bloqueioHonorarios]').val(retorno[1]);
				    	}
				    },
				    error:function(XMLHttpRequest, textStatus, errorThrown){
				        alert(XMLHttpRequest.responseText);
				    }
				});
			}
			
			
			function validarExibicao(obj){
				//CUSTAS
				//========================================================================================			
				if($(obj).attr('name') == 'valorCustas' && $(obj).attr('readonly') == false){
					if($(obj).val() == ''){
						$('[@name=percentualCustas]').attr('readonly',false);
						$('[@name=percentualCustas]').val("");
					}
					else{
						$('[@name=percentualCustas]').attr('readonly',true);
					}
				}
				else if($(obj).attr('name') == 'percentualCustas' && $(obj).attr('readonly') == false){
					if($(obj).val() == ''){
						$('[@name=valorCustas]').attr('readonly',false);
						$('[@name=valorCustas]').val("");
					}
					else{
						$('[@name=valorCustas]').attr('readonly',true);
					}
				}
				//========================================================================================
					
				//Honorários
				//========================================================================================
				else if($(obj).attr('name') == 'valorHonorarios' && $(obj).attr('readonly') == false){
					if($(obj).val() == ''){
						$('[@name=percentualHonorarios]').attr('readonly',false);
						$('[@name=percentualHonorarios]').val("");
					}
					else{
						$('[@name=percentualHonorarios]').attr('readonly',true);
					}
				}
				else if($(obj).attr('name') == 'percentualHonorarios' && $(obj).attr('readonly') == false){
					if($(obj).val() == ''){
						$('[@name=valorHonorarios]').attr('readonly',false);
						$('[@name=valorHonorarios]').val("");
					}
					else{
						$('[@name=valorHonorarios]').attr('readonly',true);
					}
				}
				//========================================================================================
			} 
			
			function bloquearDesbloquearCustasHonorarios(){
				if($('[@name=valorCustas]').val() != "" || $('[@name=percentualCustas]').val() != ""){
					if($('[@name=bloqueioCustas]').val() == $('[@name=bloqueioValor]').val()){
						$('[@name=valorCustas]').attr('readonly',true);
					}
					else{
						$('[@name=percentualCustas]').attr('readonly',true);
					}
				}
				
				if($('[@name=valorHonorarios]').val() != "" || $('[@name=percentualHonorarios]').val() != ""){
					if($('[@name=bloqueioHonorarios]').val() == $('[@name=bloqueioValor]').val()){
						$('[@name=valorHonorarios]').attr('readonly',true);
					}
					else{
						$('[@name=percentualHonorarios]').attr('readonly',true);
					}
				}		
			}
			
		
			function recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta){
				if(tipoConsulta == 'clienteResponsavel') {
					$('[@name=idClienteResponsavel]').val(codigoRegistro);
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo='+$('[@name=metodoPesquisarClienteResponsavel]').val());
				}
				else if(tipoConsulta == 'advogadoResponsavel') {
					$('[@name=idAdvogadoResponsavel]').val(codigoRegistro);
					redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo='+$('[@name=metodoPesquisarAdvogadoResponsavel]').val());
				}
			}
			
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/efetuarParcelamentoJudicialWizardAction"
				   name="EfetuarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.EfetuarParcelamentoJudicialActionForm"
				   method="post"
				   enctype="multipart/form-data">
					   
			<html:hidden property="metodoCalcularDesconto"/>
			<html:hidden property="metodoCalcularCustas"/>	
			<html:hidden property="metodoCalcularHonorarios"/>
			<html:hidden property="metodoPesquisarClienteResponsavel"/>	
			<html:hidden property="bloqueioValor"/>	
			<html:hidden property="bloqueioPercentual"/>	
			<html:hidden property="bloqueioCustas"/>	
			<html:hidden property="bloqueioHonorarios"/>	
			   
				   
			<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=3"/>
				   
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
									<p>Para inserir o Parcelamento Judicial, informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO ==============================================-->
							<tr>
								<td width="22%"><strong>Valor do Débito:</strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="15" 
			               					   property="valorDebito" 
			               					   size="15"  
			               					   tabindex="1" 
			               					   readonly="true" 
			               					   style="background-color:#EFEFEF; border:0;"/>
			               		</td>
		               		</tr>
		               		<tr>
								<td width="25%"><strong>Valor do Acordo:<font color="#FF0000">*</font></strong></td>
	                   			<td width="25%" height="24" colspan="2">
			               			<html:text maxlength="20" 
			               					   property="valorAcordo" 
			               					   size="20"  
			               					   tabindex="2" 
			               					   styleClass="tipoMonetario" />
									&nbsp;
			               			<input type="button" value="Calcular Desconto" class="bottonRightCol" onclick="calcularDesconto();"/>			              
			               			<html:text maxlength="6" 
			               					   property="percentualDescontoComSimbolo" 
			               					   size="6"  
			               					   readonly="true"
			               					   style="background-color:#EFEFEF; border:0;"/>
			               	    </td>
			               		
		               		</tr>
		               		<tr>
			               		<td><strong>Custas:<font color="#FF0000">*</font></strong></td>
		                   		<td height="24" colspan= "2">
				               		<strong><html:text maxlength="20" 
				               				   property="valorCustas" 
				               				   size="20"  
				               				   tabindex="3"
				               				   styleClass="tipoMonetario"/>
				               	
				               		Ou Percentual:</strong> <html:text maxlength="6" 
							               				      property="percentualCustas" 
							               				      size="6"  
							               				      styleClass="tipoMonetario"/>
								</td>
				            </tr>
				            <tr>
			               		<td><strong>Honorários:<font color="#FF0000">*</font></strong></td>
		                   		<td height="24" colspan="2">
				               		<strong><html:text maxlength="20" 
				               				   property="valorHonorarios" 
				               				   size="20"  
				               				   tabindex="4"
				               				   styleClass="tipoMonetario"/>
				               	
				               		Ou Percentual:</strong> <html:text maxlength="6" 
							               				      property="percentualHonorarios" 
							               				      size="6"  
							               				      styleClass="tipoMonetario"/>
				               	</td>				           	
				            </tr>
				            <tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>
							<tr>
								<td width="22%"><strong>Número do Processo Judicial:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="25" 
			               					   property="numeroProcessoJudicial" 
			               					   size="25"  
			               					   tabindex="5" 
			               					   styleClass="tipoProcessoJudicial" />
			               		</td>
		               		</tr>
							<tr>
		   						<td width="22%"><strong>Cliente Responsável:<font color="#FF0000">*</font></strong></td>
                   				<td width="81%" height="24" colspan="2">	               
	               						<html:text maxlength="9" property="idClienteResponsavel" size="9"  tabindex="1" styleClass="tipoInteiro" 
	                   					   		   onkeypress="return validaEnter(event, 'efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoPesquisarClienteResponsavel}', 'idClienteResponsavel');"/>
	                      				<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?tipoConsulta=clienteResponsavel', 400, 800);"><img border="0"
	                      					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
	                      					 
	                      				<logic:equal name="clienteResponsavelInexistenteException" value="sim">
	                         				<html:text property="descClienteResponsavel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
	                      				</logic:equal>
	                      				<logic:notEqual name="clienteResponsavelInexistenteException" value="sim">
	                      					<html:text property="descClienteResponsavel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	                      				</logic:notEqual>	
	                      				<a href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialNegociacaoAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoLimparClienteResponsavel}')"><img 
			                     				 src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>                   		
                         		</td>
			                </tr>
							<tr>
		   						<td width="22%"><strong>Advogado Responsável:<font color="#FF0000">*</font></strong></td>
                   				<td width="81%" height="24" colspan="2">	               
	               						<html:text maxlength="50" property="advogadoResponsavel" size="50"  tabindex="1" />
	                     		</td>
			                </tr>
							<tr>
								<td width="22%"><strong>Número da OAB:<font color="#FF0000">*</font></strong></td>
	                   			<td width="81%" height="24" colspan="2">
			               			<html:text maxlength="10" 
			               					   property="numeroOAB" 
			               					   size="10"  
			               					   tabindex="6" 
			               					   styleClass="tipoAlfaNumerico" />
			               		</td>
		               		</tr>
							<tr>
								<td width="25%"><strong>Observação:</strong></td>
		        				<td width="75%">
									<html:textarea property="observacao" cols="40" rows="4" styleClass="tipoTextArea"/><br>
									<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
									<input type="hidden" id="limiteHidden" value="100" />
								</td>
							</tr>
							<tr>
								<td width="25%"><strong>Anexar Documento do Acordo Judicial:</strong></td>
		        				<td width="75%">
									<html:file property="documentoAcordoJudicial" size="35"/>
								</td>
							</tr>	
							<c:if test="${not empty EfetuarParcelamentoJudicialActionForm.documentoAcordoJudicialCopia}">
								<tr>
									<td colspan="2">
										<table id=header width="100%" border="0" bgcolor="#90c7fc">
											<tr bgcolor="#79bbfd" bordercolor="#79bbfd">
												<td bgcolor="#90c7fc" align="center" width="5%"><strong>Remover</strong></td>
												<td bgcolor="#90c7fc" align="center" width="14%"><strong>Arquivo</strong></td>
											</tr>
											<tr bgcolor="#FFFFFF">
													<td align="center"><a href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?
																						action=exibirEfetuarParcelamentoJudicialNegociacaoAction
																						&metodo=${EfetuarParcelamentoJudicialActionForm.metodoRemoverDocumentoJudicial}')"><img src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" /></a></td>
													<td width="75%"><bean:write name="EfetuarParcelamentoJudicialActionForm" property="nomeArquivo"/></td>
											</tr>
										</table>
									</td>
								</tr>
							</c:if>				
							<!--===================================================================================-->		
							<tr>
								<td>&nbsp;</td>
							</tr>
                   			<tr>
								<td>&nbsp;</td>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							<tr>
								<td colspan="4">
									<table width="100%" border="0">
										<tr>
									        <td colspan="4">
												<div align="right">
													<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=3"/>
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
