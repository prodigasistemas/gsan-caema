<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.bean.ContaValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.OpcoesParcelamentoHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>


		
		<logic:present name="exibirDetalhesDebito" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('table').each(function(){
						$(this).children('tbody').children('tr:last').addClass('last-tr');
					});
				});
				
			</script>
		</logic:present>
		
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
		
		<logic:present name="matriculaInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Matrícula informada está inválida, verifique sua conta de água.');
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelNaoPossuiPerfilParcelamento'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#debitoParceladoMesCorrente'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelParcelamentoAtivo'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#quantidadeReparcelNaoPermiteParcel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#naoExistePerfilSituacaoImovel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSituacaoCobranca'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<script type="text/javascript">
			$(document).ready(function(){
				if('<%= request.getParameter("calcularParcelas") %>' == 'SIM'){
					$('[name=valorEntradaInformado]').focus();					
				}
				
				$('#botaoConfirmar').click(function(){
					$.blockUI({
						message : $('#alertConfirmacao'),
						theme : true,
						title : 'Confirmar'
					});
					
					$('#botaoSim').click(function(){
						efetuarParcelamento();
						$.unblockUI();
					});
					$('#botaoNao').click(function(){
						$.unblockUI();
					});
					
				});
				
				$('#btn-a-vista').click(function(){
					$.blockUI({
						message : $('#alertConfirmacaoAVista'),
						theme : true,
						title : 'Confirmar'
					});
					
					$('#botaoSimAVista').click(function(){
						
							gerarDocumentoExtrato();
							$.unblockUI();
						
					});
					$('#botaoNaoAVista').click(function(){
						$.unblockUI();
					});
				});
			});
		</script>
		
		<script type="text/javascript">
			function showMessage(message){
				$('#message h3').text(message);
				$.blockUI({
					message : $('#message'),
					theme : true,
					title : 'Aviso'
				});
				
				$('.confirm').live('click', function(){
					$.unblockUI();
				});
			}

			$(document).ready(function(){
				$('.info-serv').hide();
				$('#lista-servicos li, #lista-informacoes li').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('.info-serv').fadeIn(50);
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
					$(this).find('.info-serv').fadeOut(50);
				});
			
				$('.confirm').click(function(){
					$.unblockUI();
				});
			});
			
			function caracteresespeciais () { 
				this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfCliente", "CPF do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function IntegerValidations () { 
				this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfCliente", "CPF do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
			} 
		
			function limparForm(form){
				limparConfirmarCpfCliente();
			}
			
			function recalcularParcelmamento(){
				var form = document.forms[0];
				if(form.cpfCliente.value.length != 0){
					form.action = "exibirConsultarPagamentoPortalAction.do?method=pesquisarImovel";
					form.submit();
				}
			}
			
			function calcularOpcaoParcelamento(){
				var form = document.forms[0];
				form.action = "exibirConsultarPagamentoPortalAction.do?method=pesquisarImovel";
				form.submit();
			}
			
			function efetuarParcelamento(){
				var form = document.forms[0];
				form.submit();
			}
			
			function gerarDocumentoExtrato(){
				window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoAction.do?pagamentoPortal=SIM&parcelamento=1&indicadorContasRevisao=1&RD="/><bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="resolucaoDiretoria"/>'
			}
			
			function gerarDocumentosParcelamento(){
				window.location.href='gerarRelatorioDocumentosParcelamentoPortalAction.do'
			}

			function exibirEfetuarParcelamento(){
				var form = document.forms[0];
				form.action = "exibirConsultarPagamentoPortalAction.do?method=pesquisarImovel";
				form.submit();
			}

			function isCampoNumericoSemEnter(evento) {
				  var tecla = null;
					
				  if(window.event){ // Internet Explorer
				  		tecla = evento.keyCode;
				  }else if(evento.which){ // Nestcape ou Mozilla
				    	tecla = evento.which;
				  }
				  
				  if(tecla == null){//tab
					  return true;
				  }

				  if((tecla > 47 && tecla < 58) || (tecla.value == 'undefined')){ // numeros de 0 a 9
				    return true;
				  }
				  
			      if (tecla == 8 ){ // backspace ou enter
				        return true;
			      }
				  
			      return false;
			}
		</script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				var matricula = $('#matricula').html();
				var length = matricula.length;
				$('#matricula').html(matricula.substr(0, length - 1) + '.' + matricula.substr(length - 1, 1)); 
			});
		</script>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<div id="barra-debitos">
			<h2>Débitos</h2>
		    <h3>Bem-vindo(a) ${ConsultarPagamentoDebitosPortalActionForm.nomeCliente}</h3>
		    <h4><label style="font-size: 13px;">Matrícula: </label><span id="matricula">${ConsultarPagamentoDebitosPortalActionForm.matriculaImovel}</span></h4>
		    <a href="exibirServicosPortalCompesaAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
		</div>
		<!-- Botão download Adobe Reader - Start -->
		<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
				
				<div id="parc-debito" class="serv-int">
	                	<h4>Para obter o valor para pagamento à vista do seu débito digite a matrícula da sua conta de água e clique no botão Pesquisar.</h4>

	            	<html:form action="/exibirConsultarPagamentoPortalAction.do"
						name="ConsultarPagamentoDebitosPortalActionForm"
						type="gcom.gui.portal.ExibirConsultarPagamentoPortalAction" method="post">

						<fieldset>
	                    	<legend>Matrícula</legend>
	                    	
							<div>
								<span class="cmp-text-2">
									<label for="matriculaImovel">Matrícula:</label>
									<html:text property="matriculaImovel" size="10" maxlength="10" tabindex="1" onkeypress="return isCampoNumericoSemEnter(event);" />
								</span>
								<input type="button" value="" class="btn-pesquisar" tabindex="2" 
									onclick="javascript:exibirEfetuarParcelamento();"/>
							</div>
							
							<logic:present name="exibirDetalhesDebito" scope="request">
							<p>Endereço do imóvel: <em id="enderecoImovel">${ConsultarPagamentoDebitosPortalActionForm.enderecoImovel}</em></p>
								<!-- Início Resumo dos débitos -->								
								<h4 class="resumo">Resumo do débito<span>&nbsp;</span></h4>
								<ul class="lista-resumo">
									
									<li>
										<h6>Período do débito:</h6>
										<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="periodoDebitos" />
									</li>
									<li>
										<h6>Quantidade Contas:</h6>
										<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="quantidadeDebitos" />
									</li>
									
									<li>
										<p></p>
									</li>
									
									<li>
				                		<h6>Contas:</h6>
				                		R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorTotalContaValores" />
				                	</li>
				                	<li>
				                		<h6>Guias de pagamento:</h6>
				                		R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorGuiasPagamento" />
				                	</li>
		                			<li>
				                		<h6>Acréscimos por impontualidade:</h6>
				                		<logic:notEqual name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorAcrescimosImpontualidade" value="0,00">
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm"
												property="valorAcrescimosImpontualidade" formatKey="money.format" />
										</logic:notEqual>
										<logic:equal name="ConsultarPagamentoDebitosPortalActionForm"
											property="valorAcrescimosImpontualidade" value="0,00">
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm"
												property="valorAcrescimosImpontualidade" formatKey="money.format" />
										</logic:equal>
				                	</li>
				                	<li>
				                		<h6>Débitos a cobrar:</h6>
				                		<span>Serviços:</span>
				                		<logic:notEqual name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorDebitoACobrarServico" value="0,00">
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoACobrarServico"  formatKey="money.format"/>
										</logic:notEqual>
										<logic:equal name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorDebitoACobrarServico" value="0,00">
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoACobrarServico" formatKey="money.format" />
										</logic:equal>
				                		<br />
				                		<span>Parcelamento:</span>
				                		<logic:notEqual name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorDebitoACobrarParcelamento" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
												<bean:define name="ConsultarPagamentoDebitosPortalActionForm" property="matriculaImovel" id="imovel" />
												<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoACobrarParcelamento"  formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorDebitoACobrarParcelamento" value="0,00">
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoACobrarParcelamento" formatKey="money.format" />
										</logic:equal>
				                	</li>
				                	<li>
				                		<h6>Créditos a realizar:</h6>
				                		<logic:notEqual name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorCreditoARealizar" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="ConsultarPagamentoDebitosPortalActionForm" property="matriculaImovel" id="imovel" />
												<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorCreditoARealizar" formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="ConsultarPagamentoDebitosPortalActionForm" 
											property="valorCreditoARealizar" value="0,00">
											R$<bean:write	name="ConsultarPagamentoDebitosPortalActionForm" property="valorCreditoARealizar" formatKey="money.format" />
										</logic:equal>
										<br />&nbsp;
				                	</li>
				                	<li>
				                		<h6>Débito total atualizado:</h6>
				                		R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoTotalAtualizado" />
				                		<br />&nbsp;
				                	</li>
				                	<li>
				                		
				                	</li>
								</ul>	
								<!-- Fim Resumo dos débitos -->	
								
								<!-- Início Forma de pagamento a vista -->
								<div id="pagto">
									<h5>Pagamento à vista:</h5>
									<ul class="lista-resumo">
										<li>
					                		<h6>Valor atualizado:</h6>
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDebitoTotalAtualizado"  
												formatKey="money.format"/>
					                		<br />
										</li>
										<li>
					                		<h6>Valor dos impostos:</h6>
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorTotalImpostos" 
												formatKey="money.format"/>
											<br />
										</li>
										<li>
					                		<h6>Valor do desconto:</h6>
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorDescontoPagamentoAVista" 
												formatKey="money.format"/>
											<br />
										</li>
										<li>
					                		<h6>Valor pagamento à vista:</h6>
											R$<bean:write name="ConsultarPagamentoDebitosPortalActionForm" property="valorPagamentoAVista" 
												formatKey="money.format"/>
												<br/>
										</li>
									</ul>
									
									<logic:present name="exibirBotaoImprimirExtrato" scope="request">
					                		<tr>
							                	<td width="20"><input type="button" value="" class="btn-confirmar" id="btn-a-vista"
							                		style="margin: 2px 10px 0 0px;"/></td>
							                	<td style="text-align: left;"><em style="padding:28px 0px 0px 0px; clear: none; text-align: left;">Gerar Extrato Pagamento à Vista</em></td>
						                	</tr>
					                	</logic:present>
					                	
					                	<logic:notPresent name="exibirBotaoImprimirExtrato" scope="request">
					                		<tr>
							                	<td style="text-align: left;" colspan="4">
							                		<em style="padding:28px 0px 0px 0px; clear: none; text-align: left; color: rgb(255, 0, 0);">Fique Legal - Procure uma loja de atendimento da Compesa ou ligue 0800-081-0195</em>
							                	</td>
						                	</tr>
					                	</logic:notPresent>
				                	<table >
					                	
				                		
				                	</table>
								</div>
								<!-- Fim Forma de pagamento a vista -->
								
							</logic:present>
	                    </fieldset>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<logic:present name="imovelSemDebitos" scope="request">
			<div id="imovelSemDebitos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Imóvel informado não possui débitos. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Não existe perfil de parcelamento correspondente à situação do imóvel. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O débito deste imóvel já foi parcelado no mês de faturamento corrente. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Imóvel já possui um parcelamento não quitado/cobrado. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
			<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<div id="quantidadeReparcelNaoPermiteParcel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Quantidade de reparcelamento do imóvel não permite um novo parcelamento. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<div id="naoExistePerfilSituacaoImovel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Não existe perfil de parcelamento correspondente à situação do imóvel.</h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<div id="imovelSituacaoCobranca" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Imóvel com situação de cobrança, não é possivel fazer o parcelamento de débitos.</h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
		
		<div id="alertConfirmacao" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja confirmar parcelamento dos débitos?</h3> 
			<a href="javascript:void(0);" id="botaoSim" class="ui-corner-all button">Sim</a>&nbsp;
			<a href="javascript:void(0);" id="botaoNao" class="ui-corner-all button">Não</a>
		</div>
		
		<div id="alertConfirmacaoAVista" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja gerar extrato para pagamento à vista?</h3> 
			<a href="javascript:void(0);" id="botaoSimAVista" class="ui-corner-all button">Sim</a>&nbsp;
			<a href="javascript:void(0);" id="botaoNaoAVista" class="ui-corner-all button">Não</a>
		</div>
		
		<div id="alertReativacaoRamal" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" id="botaoOkReativacao" class="ui-corner-all button">OK</a>
		</div>
	</body>
</html:html>