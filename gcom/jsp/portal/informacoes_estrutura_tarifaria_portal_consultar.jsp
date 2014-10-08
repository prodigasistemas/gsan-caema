<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.portal.ConsultarEstruturaTarifariaPortalHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Servi�os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
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
		
		
		<logic:present name="cpfInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O CPF do respons�vel pelo parcelamento deve constar no nosso cadastro.');
				});
			</script>
		</logic:present>
		
			<logic:present name="arquivoNaoEncontrado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O arquivo n�o foi encontrado.');
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
			
		</script>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/cabecalhoInformacoes.jsp"%>
				
				<div id="parc-debito" class="serv-int">
	            	<h3>Estrutura Tarif�ria<span>&nbsp;</span></h3>

	            	<form>

						<!-- In�cio dos links para download -->
						<div id="links" class="subTopicos">
							<logic:present name="leiEstadual" scope="request">
								<div>
									<a href="exibirConsultarEstruturaTarifariaPortalAction.do?download=leiEstadual" target="_blank">
										<img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/>
									</a>
									<em style="height: 34px; width: 300px;"><%=request.getAttribute("labelLeiEstadual")%></em>
								</div>
							</logic:present>
							
							<logic:present name="decretoEstadual" scope="request">
								<div>
									<a href="exibirConsultarEstruturaTarifariaPortalAction.do?download=decretoEstadual" target="_blank">
										<img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/>
									</a>
									<em style="height: 34px; width: 300px;"><%=request.getAttribute("labelDecretoEstadual")%></em>
								</div>
							</logic:present>
							
							<div id="atualizacao" class="pontilhada">
								<a href="gerarRelatorioEstruturaTarifariaAction.do">
									<img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/>
								</a>
								<em style="height: 34px; width: 300px;">
									Estrutura Tarif�ria
								</em>
								<h3 style="width: 480px">Resolu��o ARPE N� 89/2014 - Publicada no DOE n�.25 de 06/02/2014<span>&nbsp;</span></h3>
							</div>
						</div>
						<!-- Fim dos links para download -->
						
						<!-- In�cio dos consumidores medidos -->
						<div id="consumidoresMedidos" class="subTopicos">
							<h3>Consumidores Medidos<span style="padding:0 0 10px 0; width: 10px">&nbsp;</span></h3>
							<br>
							<br>
							<br>
							<em>Residencial:</em>
							
							<!-- In�cio da estrutura tarif�ria residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%int cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Consumo</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencial" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="estruturaTarifariaResidencial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencial" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria residencial -->
							
							
							<em style="clear: both; margin-top: 30px">Comercial:</em>
								
							<!-- In�cio da estrutura tarif�ria comercial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperComercial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Consumo</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperComercial" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="estruturaTarifariaComercial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaComercial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaComercial" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria comercial -->
							
							
							<!-- In�cio da estrutura tarif�ria industrial -->
							<em style="clear: both; margin-top: 30px">Industrial:</em>
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperIndustrial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Consumo</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperIndustrial" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="estruturaTarifariaIndustrial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaIndustrial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaIndustrial" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria industrial -->
							
							<!-- In�cio da estrutura tarif�ria publica -->
							<em style="clear: both; margin-top: 30px">P�blica:</em>
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublica" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Consumo</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublica" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="estruturaTarifariaPublica">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaPublica" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaPublica" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria publica -->
						</div>
						<!-- Fim dos consumidores medidos -->
						
						<!-- In�cio dos consumidores n�o medidos -->
						<div id="consumidoresNaoMedidos" class="subTopicos">
							<h3>Consumidores n�o medidos<span style="padding:0 0 10px 0; width: 10px">&nbsp;</span></h3>
							
							<!-- In�cio da estrutura tarif�ria residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="consumosNaoMedidos" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Consumo</th>
						                            <th width="25%">Valor (R$)</th>
						                            <th width="25%">Quantidade</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="consumosNaoMedidos" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="consumosNaoMedidos">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="consumosNaoMedidos" property="categoria" />
														</td>
														<td width="20%" style="border-right: 1px solid #C8C8C8;">
															<bean:write name="consumosNaoMedidos" property="valor" />
														</td>
														<td width="25%" >
															<bean:write name="consumosNaoMedidos" property="quantidade" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria residencial -->
						</div>
						<!-- Fim dos consumidores medidos -->
						
						<!-- In�cio dos consumidores n�o medidos -->
						<div id="helperAguaBruta" class="subTopicos">
							<h3>�gua Bruta<span style="padding:0 0 10px 0; width: 10px">&nbsp;</span></h3>
							
							<!-- In�cio da estrutura tarif�ria residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<%String categoria = "";%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperAguaBruta" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="20%">Tipo</th>
						                            <th width="70%">Consumo</th>
						                            <th width="15%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperAguaBruta" type="ConsultarEstruturaTarifariaPortalHelper" 
													id="helperAguaBruta">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<%if(!categoria.equals(helperAguaBruta.getCategoria())){ %>
														<td width="20%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="helperAguaBruta" property="categoria" />
														</td>
														<%} else {%>
														<td width="20%" style="border-right: 1px solid #C8C8C8;">
												           <span>&nbsp;</span>
														</td>
														<%} %>
														<td width="70%" style="border-right: 1px solid #C8C8C8;text-align: left">
															<bean:write name="helperAguaBruta" property="quantidade" />
														</td>
														<td width="15%" style="border-right: 1px solid #C8C8C8;">
															<bean:write name="helperAguaBruta" property="valor" />
														</td>
													</tr>
													<%categoria = helperAguaBruta.getCategoria();%>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria residencial -->
						</div>
						<!-- Fim dos consumidores medidos -->
						
						<!-- In�cio do esgotamento sanit�rio -->
						<div id="esgotamentoSanitario" class="subTopicosSemBorda">
							<h3>Esgotamento Sanit�rio<span style="padding:0 0 10px 0; width: 10px">&nbsp;</span></h3>
							
							<!-- In�cio da estrutura tarif�ria residencial -->
							<table summary="Tabela de contas" style="width: 96%;">
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas" style="width:91%; margin-top: 0px;" >
										<thead>
					                    	<tr>
					                        	<th width="30%">Tipo</th>
					                            <th width="70%">Valor</th>
					                        </tr>
					                    </thead>
											
										<tbody>
											<tr class="tr-1" id="tr-1-2">
												<td width="20%" style="border-right: 1px solid #C8C8C8;">
										           <span style="width: auto; text-align:left; margin-top: 10px" >Sistema Convencional</span>
												</td>
												<td width="80%" >
													<span style="width:auto; text-align:left; margin-top: 10px">Liga��o Convecional ou ramal de cal�ada - 100% da tarifa de �gua</span>
													<span style="width:auto; text-align:left; margin-top: -10px">Ramal Condominal (operado p/Comunidade) - 50% da tarifa de �gua</span>
												</td>
											</tr>
											<tr class="tr-2" id="tr-2-2">
												<td width="20%" style="border-right: 1px solid #C8C8C8;">
										           <span style="width:auto; text-align:left; margin-top: 10px">Sistema Simplificado</span>
												</td>
												<td width="80%" >
													<span style="width:auto; text-align:left; margin-top: 10px">Liga��o Convecional ou ramal de cal�ada - 80% da tarifa de �gua</span>
													<span style="width:auto; text-align:left; margin-top: -10px">Ramal Condominal (operado p/Comunidade) - 40% da tarifa de �gua</span>
												</td>
											</tr>
											<tr class="tr-1" id="tr-1-2">
												<td width="20%" style="border-right: 1px solid #C8C8C8;">
										           <span style="width: auto; text-align:left; margin-top: 10px">Dreno</span>
												</td>
												<td width="80%" >
													<span style="width:auto; text-align:left; margin-top: 10px">Liga��o Convecional ou ramal de cal�ada - 50% da tarifa de �gua</span>
													<span style="width:auto; text-align:left; margin-top: -10px">Ramal Condominal (operado p/Comunidade) - 30% da tarifa de �gua</span>
												</td>
											</tr>
											<tr class="tr-2" id="tr-2-2">
												<td width="20%" style="border-right: 1px solid #C8C8C8; ">
										           <span style="width: auto; text-align:left; margin-top: 10px">Pr�dios em constru��o</span>
												</td>
												<td width="80%" >
													<span style="width:auto; text-align:left; margin-top: 10px">50% do valor dos servi�os de esgotos estipulados
													no momento da liga��o, cobrados at� a concess�o do habite-se</span>
												</td>
											</tr>
										</tbody>										
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarif�ria residencial -->
						</div>
						<!-- Fim do esgotamento sanit�rio -->
						
						<div id="atualizacao" class="pontilhada">
							<h3>�ltima atualiza��o (Quarta, 20 de Mar�o de 2014)<span>&nbsp;</span></h3>
						</div>
					</form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</body>
</html:html>