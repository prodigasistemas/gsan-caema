<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Serviços</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){
			
			});
			
		</script>
		
		<logic:present name="exception" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#exception'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
					
					$('.confirm').click(function() {
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
	</head>
	
	<body>
		
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
			
				<%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
				
				<div class="serv-int" id="consulta-pagamento">
	            	<h3>Consultar Pagamentos<span>&nbsp;</span></h3>
	            	
	            	<div>&nbsp;</div>
	            	<div>&nbsp;</div>
	            	
	            	<div>
	            		<a href="gerarRelatorioPagamentoPortalAction.do">
							<img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/>
						</a>
	            		<em style="height: 34px; width: 300px;">
	            			<a href="gerarRelatorioPagamentoPortalAction.do">
								Relatório de histórico de pagamento
							</a>
							<br>
						</em>
	            	</div>
	                
	                <!-- Início do detalhamento das contas -->
					<div id="contas" class="subTopicos">
						<em>Pagamentos das Contas:</em>
						<br>
						
						<table summary="Tabela de contas">
							<%int cont = 0;%>
							<tr>
								<td colspan="4" height="300">
									<div style="width: 103%; height: 100%; overflow: auto;">
										<table summary="Tabela de contas">
											<logic:notEmpty name="colecaoPagamentosConta" scope="request">
												<thead>
							                    	<tr>
							                        	<th width="100">Mês / Ano</th>
							                            <th width="100">Valor (R$)</th>
							                            <th width="150">Data do Pagamento</th>
							                            <th width="200">Arrecadador</th>
							                        </tr>
							                    </thead>
												
												<tbody>
													<logic:iterate name="colecaoPagamentosConta" id="pagamentoHelper">
													<%cont = cont + 1;
													
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="referenciaConta" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="referenciaConta" />
															</logic:equal>
														</td>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.valorPagamento" formatKey="money.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.valorPagamento" formatKey="money.format" />
															</logic:equal>
														</td>
														<td width="150">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.dataPagamento" formatKey="date.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.dataPagamento" formatKey="date.format" />
															</logic:equal>
														</td>
														<td width="200">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<logic:notEmpty name="pagamentoHelper" property="pagamento.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamento.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<logic:notEmpty name="pagamentoHelper" property="pagamentoHistorico.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamentoHistorico.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
														</td>
													</logic:iterate>
												</tbody>	
											</logic:notEmpty>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<!-- Início do detalhamento das guias de pagamento -->
					<div id="guias" class="subTopicos">
						<em>Pagamentos das Guias de Pagamento:</em>
						<br>
						
						<table summary="Tabela de Guias de Pagamento">
							<%cont = 0;%>
							<tr>
								<td colspan="4" height="200">
									<div style="width: 103%; height: 100%; overflow: auto;">
										<table summary="Tabela de guias de pagamento">
											<logic:notEmpty name="colecaoPagamentosGuia" scope="request">
												<thead>
							                    	<tr>
							                        	<th width="100">Tipo do Débito</th>
							                            <th width="100">Valor (R$)</th>
							                            <th width="150">Data do Pagamento</th>
							                            <th width="200">Arrecadador</th>
							                        </tr>
							                    </thead>
												
												<tbody>
													<logic:iterate name="colecaoPagamentosGuia" id="pagamentoHelper">
													<%cont = cont + 1;
													
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.debitoTipo.descricao" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.debitoTipo.descricao" />
															</logic:equal>
														</td>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.valorPagamento" formatKey="money.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.valorPagamento" formatKey="money.format" />
															</logic:equal>
														</td>
														<td width="150">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.dataPagamento" formatKey="date.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.dataPagamento" formatKey="date.format" />
															</logic:equal>
														</td>
														<td width="200">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<logic:notEmpty name="pagamentoHelper" property="pagamento.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamento.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<logic:notEmpty name="pagamentoHelper" property="pagamentoHistorico.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamentoHistorico.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
														</td>
													</logic:iterate>
												</tbody>	
											</logic:notEmpty>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<!-- Início do detalhamento dos debitos a cobrar -->
					<div id="debitos" class="subTopicos">
						<em>Pagamentos dos Debitos a Cobrar:</em>
						<br>
						
						<table summary="Tabela de debitos">
							<%cont = 0;%>
							<tr>
								<td colspan="4" height="200">
									<div style="width: 103%; height: 100%; overflow: auto;">
										<table summary="Tabela de debitos">
											<logic:notEmpty name="colecaoPagamentosDebito" scope="request">
												<thead>
							                    	<tr>
							                        	<th width="100">Tipo do Débito</th>
							                            <th width="100">Valor (R$)</th>
							                            <th width="150">Data do Pagamento</th>
							                            <th width="200">Arrecadador</th>
							                        </tr>
							                    </thead>
												
												<tbody>
													<logic:iterate name="colecaoPagamentosDebito" id="pagamentoHelper">
													<%cont = cont + 1;
													
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.debitoTipo.descricao" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.debitoTipo.descricao" />
															</logic:equal>
														</td>
														<td width="100">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.valorPagamento" formatKey="money.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.valorPagamento" formatKey="money.format" />
															</logic:equal>
														</td>
														<td width="150">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<bean:write name="pagamentoHelper" property="pagamento.dataPagamento" formatKey="date.format" />
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<bean:write name="pagamentoHelper" property="pagamentoHistorico.dataPagamento" formatKey="date.format" />
															</logic:equal>
														</td>
														<td width="200">
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamento" >
																<logic:notEmpty name="pagamentoHelper" property="pagamento.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamento.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
															<logic:equal name="pagamentoHelper" property="tipoPagamento" value="pagamentoHistorico">
																<logic:notEmpty name="pagamentoHelper" property="pagamentoHistorico.avisoBancario">
																	<bean:write name="pagamentoHelper" property="pagamentoHistorico.avisoBancario.arrecadador.cliente.nome" />
																</logic:notEmpty>
															</logic:equal>
														</td>
													</logic:iterate>
												</tbody>	
											</logic:notEmpty>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
	        </div>
			
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
	</body>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
</html:html>