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
		
		function validateEfetuarParcelamentoJudicialActionForm(form){
			return true;
		}
		
		$(document).ready(function(){
			
			$('[@name=idImovel]').bind("keyup blur",function(){
				if($('[@name=idImovel]').val() != ''){
					$('[@name=idClienteUsuario]').val('');
					$('[@name=idClienteUsuario]').attr('readonly',true);
					$('[@name=idClienteUsuario]').attr('style','background-color:#EFEFEF; border:0;');
					$('[@name=descClienteUsuario]').val('');
					$('#buscarCliente').attr('href','javascript:void(0)');
					$('#apagarCliente').attr('href','javascript:void(0)');
				}
				else{
					if($('[@name=bloquearCliente]').val() == ""){
						$('[@name=idClienteUsuario]').attr('readonly',false);
						$('[@name=idClienteUsuario]').removeAttr('style');
						$('#buscarCliente').attr('href',"javascript:abrirPopup('exibirPesquisarClienteAction.do', 400, 800);");
						$('#apagarCliente').attr('href',"javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo="+${EfetuarParcelamentoJudicialActionForm.metodoLimparClienteUsuario}+"');");
					}
				}
			});
			
			$('[@name=idClienteUsuario]').bind("keyup blur",function(){
				if($('[@name=idClienteUsuario]').val() != ''){
					$('[@name=idImovel]').val('');
					$('[@name=idImovel]').attr('readonly',true);
					$('[@name=idImovel]').attr('style','background-color:#EFEFEF; border:0;');
					$('[@name=descImovel]').val('');
					$('#buscarImovel').attr('href','javascript:void(0)');
					$('#apagarImovel').attr('href','javascript:void(0)');
					$('#adicionarImovel').attr('href','javascript:void(0)');
				}
				else{
					if($('[@name=bloquearImovel]').val() == ""){
						$('[@name=idImovel]').attr('readonly',false);
						$('[@name=idImovel]').removeAttr('style');
						$('#buscarImovel').attr('href',"javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);");
						$('#apagarImovel').attr('href',"javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo="+${EfetuarParcelamentoJudicialActionForm.metodoLimparImovel}+"')");
						$('#adicionarImovel').attr('href','javascript:adicionarImovel()');
					}
				}
			});
			
		});
		
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			if (tipoConsulta == 'imovel') {	
				$('[@name=idImovel]').val('');
				$('[@name=descImovel]').val('');
				$('[@name=idImovel]').val(codigoRegistro);
				redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo='+$('[@name=metodoPesquisarImovel]').val());
			}
			
			else if(tipoConsulta == 'cliente') {
				$('[@name=idClienteUsuario]').val('');
				$('[@name=descClienteUsuario]').val('');
				$('[@name=idClienteUsuario]').val(codigoRegistro);
				redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo='+$('[@name=metodoPesquisarClienteUsuario]').val());
			}
		}
		
		function adicionarImovel(){
			if($('[@name=idImovel]').val() != "")
				redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoAdicionarImovel}');
			else
				alert("Informe Matrícula do Imóvel.");
		}
		
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="">
		<div id="formDiv">
			<html:form action="/efetuarParcelamentoJudicialWizardAction"
				   name="EfetuarParcelamentoJudicialActionForm"
				   type="gcom.gui.cobranca.parcelamentojudicial.EfetuarParcelamentoJudicialActionForm"
				   method="post">
				   
			<html:hidden property="metodoPesquisarImovel"/>
			<html:hidden property="metodoPesquisarClienteUsuario"/>
			<input type="hidden" name="bloquearImovel" value="${sessionScope.bloquearImovel}"/>
			<input type="hidden" name="bloquearCliente" value="${sessionScope.bloquearCliente}"/>
				   
			<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=1"/>
				   
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
									<p>Para consultar débitos para parcelamento judicial, informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--===================== CORPO DA VISÃO =========================-->
							<tr>
								<td width="22%"><strong>Período de Referência do Débito:</strong> <font color="#FF0000">*</font> </td>
	                   			<td width="81%" height="24" colspan="3">
	                   				<html:text maxlength="7" 
											   property="amReferenciaInicial"
											   size="7" 
											   tabindex="7"
											   styleClass="tipoPeriodoInicial" />
									<strong> a</strong>
								    <html:text maxlength="7" 
											   property="amReferenciaFinal"
											   size="7" 
											   tabindex="8"
											   styleClass="tipoPeriodoFinal" />
									(mm/aaaa)		   
	                   			</td>
                   			</tr>
							
							
							<tr> 
								<td height="24" colspan="3"><hr></td>
							</tr>
							
							
						    <tr>
		   						<td width="22%"><strong>Código do Cliente Usuário:</strong></td>
                   				<td width="81%" height="24" colspan="2">
	                   				<logic:present name="bloquearCliente" scope="session">
		               					<html:text maxlength="9" property="idClienteUsuario" size="9"  tabindex="1" styleClass="tipoInteiro" readonly="true" style="background-color:#EFEFEF; border:0;" 
		               					   		   onkeypress="return validaEnter(event, 'efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoPesquisarClienteUsuario}', 'idClienteUsuario');"/>
		                   				<a href="javascript:void(0)" id="buscarCliente"><img border="0"
		                   					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
		                   					 
		                   				<logic:equal name="clienteInexistenteException" value="sim">
		                      				<html:text property="descClienteUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
		                   				</logic:equal>
		                   				<logic:notEqual name="clienteInexistenteException" value="sim">
		                   					<html:text property="descClienteUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		                   				</logic:notEqual>	
		                   				<a href="javascript:void(0)" id="apagarCliente"><img 
		                     					 src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
	                  				</logic:present>
	                      				 
	                      			<logic:notPresent name="bloquearCliente" scope="session">
	               						<html:text maxlength="9" property="idClienteUsuario" size="9"  tabindex="1" styleClass="tipoInteiro" 
	                   					   		   onkeypress="return validaEnter(event, 'efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoPesquisarClienteUsuario}', 'idClienteUsuario');"/>
	                      				<a id="buscarCliente" href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 400, 800);"><img border="0"
	                      					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
	                      					 
	                      				<logic:equal name="clienteInexistenteException" value="sim">
	                         				<html:text property="descClienteUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
	                      				</logic:equal>
	                      				<logic:notEqual name="clienteInexistenteException" value="sim">
	                      					<html:text property="descClienteUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	                      				</logic:notEqual>	
	                      				<a id="apagarCliente" href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoLimparClienteUsuario}')"><img 
			                     				 src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
                     				</logic:notPresent>
                         		</td>
			                </tr>
			                <tr> 
								<td height="24" colspan="3"><hr></td>
							</tr>
							<tr>
		   						<td width="22%"><strong>Matrícula do Imóvel:</strong></td>
                   				<td width="81%" height="24" colspan="3">
                   					<logic:present name="bloquearImovel" scope="session">
	                   					<html:text maxlength="9" property="idImovel" size="9"  tabindex="1" styleClass="tipoInteiro" readonly="true" style="background-color:#EFEFEF; border:0;"
	                   					   		   onkeypress="return validaEnter(event, 'efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoPesquisarImovel}', 'idImovel');"/>
	                      				<a href="javascript:void(0)" id="buscarImovel"><img border="0"
	                      					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
	                      				<logic:equal name="imovelInexistenteException" value="sim">
		                         			<html:text property="descImovel" size="46" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
	                      				</logic:equal>
	                      				<logic:notEqual name="imovelInexistenteException" value="sim">
	                      					<html:text property="descImovel" size="46" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	                      				</logic:notEqual>	 
	                         			<a href="javascript:void(0)" id="apagarImovel"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
	                         			<a href="javascript:void(0)" id="adicionarImovel"><img src="<bean:message key="caminho.imagens"/>adicaoVerde.gif" border="0" title="Adicionar" /></a>
                         			</logic:present>
                         			
                         			<logic:notPresent name="bloquearImovel" scope="session">
	                   					<html:text maxlength="9" property="idImovel" size="9"  tabindex="1" styleClass="tipoInteiro" 
	                   					   		   onkeypress="return validaEnter(event, 'efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoPesquisarImovel}', 'idImovel');"/>
	                      				<a id="buscarImovel" href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img border="0"
	                      					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
	                      				<logic:equal name="imovelInexistenteException" value="sim">
		                         			<html:text property="descImovel" size="46" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
	                      				</logic:equal>
	                      				<logic:notEqual name="imovelInexistenteException" value="sim">
	                      					<html:text property="descImovel" size="46" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	                      				</logic:notEqual>	 
	                         			<a id="apagarImovel" href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?action=exibirEfetuarParcelamentoJudicialImovelAction&metodo=${EfetuarParcelamentoJudicialActionForm.metodoLimparImovel}')"><img 
	                         				src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
	                         			<a id="adicionarImovel" href="javascript:adicionarImovel()"><img 
	                         				src="<bean:message key="caminho.imagens"/>adicaoVerde.gif" border="0" title="Adicionar" /></a>
                         			</logic:notPresent>		                   
								</td>
			                </tr>	  
							
							
							<!-- TABELA DE IMÓVEIS FILTRADOS -->
							<c:if test="${EfetuarParcelamentoJudicialActionForm.listaRegistroImovelHelper != null 
												&& not empty EfetuarParcelamentoJudicialActionForm.listaRegistroImovelHelper }">
								<tr>
									<td colspan="3">
										<table id=header width="100%" border="0" bgcolor="#90c7fc">
											<COL WIDTH=36 align="center"><COL WIDTH=54 align="center"><COL WIDTH=98 align="center"><COL WIDTH=296 align="center">
											<tr>
												<th bgcolor="#90c7fc" align="center">Remover</th>
												<th bgcolor="#90c7fc" align="center">Imóvel Principal</th>
												<th bgcolor="#90c7fc" align="center">Matrícula</th>
												<th bgcolor="#90c7fc" align="center">Cliente Usuário</th>
											</tr>
										</table>
											<bean:define name="EfetuarParcelamentoJudicialActionForm" 
														 property="listaRegistroImovelHelper" 
														 id="listaRegistroImovelHelper" />
														 
								<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaRegistroImovelHelper) gt 7 }">																								 
										<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
								</c:if>																 
											<TABLE border="0" width="100%" bgcolor="#90c7fc">
												<c:set var="count" value="0"/>
												<COL WIDTH=78 align="center"><COL WIDTH=75 align="center"><COL WIDTH=123 align="center"><COL WIDTH=407 align="center">
												<logic:iterate name="listaRegistroImovelHelper" id="helper">
												
													<c:choose>
														<c:when test="${count % 2 == 0 }">
															<tr bgcolor="#FFFFFF">		
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
														<td width=68 align="center">
															<a href="javascript:redirecionarSubmit('/gsan/efetuarParcelamentoJudicialWizardAction.do?
																				action=exibirEfetuarParcelamentoJudicialImovelAction
																				&metodo=${EfetuarParcelamentoJudicialActionForm.metodoRemoverImovel}
																				&id=${helper.idColecao}')"><img 
	                         									src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" /></a>
														</td>
														 <td align="center">
															<html:radio property="idRegistroPrincipal" value="${helper.idColecao}"/>
														</td>
														<td align="center">
															<bean:write name="helper" property="matriculaImovelFormatada"/>
														</td>
														<td align="center">
															<bean:write name="helper" property="nomeClienteUsuario"/>
														</td>
													</tr>	
													<c:set var="count" value="${count+1}"/>
												</logic:iterate>
											</table>
										<c:if test="${fn:length(EfetuarParcelamentoJudicialActionForm.listaRegistroImovelHelper) gt 7 }">																								 
											</DIV>
										</c:if>																 
									</td>
								</tr>	
							</c:if>
							
                   			<tr>
								<td>&nbsp;</td>
							</tr>
							
                   			<tr>
								<td>&nbsp;</td>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							<tr>
								<td colspan="3">
									<table width="100%" border="0">
										<tr>
									        <td colspan="3">
												<div align="right">
													<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=1"/>
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
