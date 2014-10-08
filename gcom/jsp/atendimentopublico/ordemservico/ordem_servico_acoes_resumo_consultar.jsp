<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.CobrancaDebitoSituacao" %>
<%@ page import="gcom.cobranca.bean.CobrancaDebitoSituacaoHelper" %>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServicoSituacao" %>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.ServicoTipoHelper" %>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OrdemServicoSituacaoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
	
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/consultarResumoAcaoOSWizardAction" 
	name="ResumoAcaoCobrancaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ResumoAcaoOSActionForm"
	method="post">
	
		<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=2" />
	
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
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Resumo das Ações de Ordem de Serviço</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%">
				   <tr>
					  <td align="center"><strong>Ações de Ordens de Serviço</strong></td>
					</tr>	
			        <tr>
						<td><strong>Mês/Ano:&nbsp;</strong><bean:write name="mesAnoReferencia" scope="session" />
					</tr>
					
					<logic:iterate name="colecaoServicoTipoHelper" id="resumoOrdemServico" type="ServicoTipoHelper" >
					<tr>
					  <td>
					   <div id='layerHide<bean:write name="resumoOrdemServico" property="id"/>' style="display:block">
           					<table width="100%" border="0" bgcolor="#79bbfd">
	    						<tr bgcolor="#79bbfd">
                     				<td>             
                     					<a href="javascript:extendeTabela('<bean:write name="resumoOrdemServico" property="id"/>',true);">
                     						<strong><bean:write name="resumoOrdemServico" property="descricao" /></strong>
                     					</a>
                     				</td>
                    			</tr>
                   			</table>
           				</div>
           				<div id='layerShow<bean:write name="resumoOrdemServico" property="id"/>' style="display:none">
					    <table width="100%" bgcolor="#79bbfd">						
						  <tr bgcolor="#79bbfd">
						    <td bgcolor="#79bbfd">
						    <a href="javascript:extendeTabela('<bean:write name="resumoOrdemServico" property="id"/>',false);">
							<strong><bean:write name="resumoOrdemServico" property="descricao" /></strong>
							</a>
							</td>
						  </tr>
					<tr>
					  <td>
					    <table width="100%" bgcolor="#90c7fc">
						  <logic:notEmpty name="resumoOrdemServico" property="colecaoSituacaoOS">
							 <tr bgcolor="#cbe5fe">
						      <td bgcolor="#cbe5fe" rowspan="2" width="32%">
						        <a href="javascript:abrirPopup('exibirConsultarResumoOrdemServicoPopupAction.do?idServicoTipo=<bean:write name="resumoOrdemServico" property="id"/>
								  							&quantidadeTotal=<bean:write name="resumoOrdemServico" property="quantidadeOS"/>&valorTotal=<bean:write name="resumoOrdemServico" property="valorContas"/>', 400, 600);">
								  							<strong>EMITIDOS</strong>
								</a> 
							  </td>
						      <td bgcolor="#cbe5fe" align="right"><strong> Quantidade</strong> </td>
						      <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
							  <td bgcolor="#cbe5fe" align="right"><strong> Valor     </strong> </td>
							  <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
							 </tr>
						     <tr>
							   <td bgcolor="#FFFFFF" width="17%" align="right">
									<bean:write name="resumoOrdemServico"
									property="quantidadeOS" formatKey="number.format"/>
							   </td>
							   <td bgcolor="#FFFFFF" width="17%" align="right">
									100,00
							   </td>
							   <td bgcolor="#FFFFFF" width="17%" align="right">
								    <bean:write name="resumoOrdemServico"
									property="valorContas" formatKey="money.format"/>
							   </td>
							   <td bgcolor="#FFFFFF" width="17%" align="right">
									 100,00
							   </td>										    
						    </tr>		

						  <%
						    // verificando se a acao de cobranca eh fiscalizacao para exibir o link 
						  	// de 'Retorno de fiscalizacao'
						  	boolean exibeSituacaoAcao = true;
						    
						  %>						    
						  
						  <logic:iterate name="resumoOrdemServico" property="colecaoSituacaoOS" 
						  		id="ordemServicoSituacao" type="OrdemServicoSituacaoHelper" >
							<%

							if (exibeSituacaoAcao){
							
							%>
							  <tr bgcolor="#90c7fc">
							    <td bgcolor="#90c7fc" align="center" colspan="5"><strong> Situação de Ação </strong></td>
							  </tr>
							  <tr>
							    <td rowspan="2" bgcolor="#cbe5fe" width="32%">
								  <a href="javascript:abrirPopup('exibirConsultarResumoOrdemServicoPopupAction.do?idServicoTipo=<bean:write name="resumoOrdemServico" property="id"/>&idOrdemServicoSituacao=<bean:write name="ordemServicoSituacao" property="id"/>
									  							&ordemServicoSituacao=<bean:write name="ordemServicoSituacao" property="descricao"/>
									  							&quantidadeTotal=<bean:write name="ordemServicoSituacao" property="quantidadeOS"/>&valorTotal=<bean:write name="ordemServicoSituacao" property="valorContas"/>', 400, 600);">
									<strong><bean:write name="ordemServicoSituacao"property="descricao"/></strong>
								  </a>
								 	
								 	<%
								 	if (((OrdemServicoSituacaoHelper) ordemServicoSituacao).getId().intValue() == OrdemServicoSituacao.FISCALIZADA) {
									%>
									&nbsp;&nbsp;
									<a href="javascript:abrirPopup('exibirConsultarResumoOrdemServicoSituacaoAcaoDetalhesPopupAction.do?tipoDetalhe=eF&idServicoTipo=<bean:write name="resumoOrdemServico" property="id"/>&idOrdemServicoSituacao=<bean:write name="ordemServicoSituacao" property="id"/>
								  							&ordemServicoSituacao=<bean:write name="ordemServicoSituacao" property="descricao"/>
								  							&quantidadeTotal=<bean:write name="ordemServicoSituacao" property="quantidadeOS"/>&valorTotal=<bean:write name="ordemServicoSituacao" property="valorContas"/>', 450, 600);"><img border="0" src="imagens/fiscalizada.jpg" width="20" height="22" title="Ordens de Serviço Fiscalizadas" /></a>
									<% } %>
								  
								  	<%
								  	if (((OrdemServicoSituacaoHelper) ordemServicoSituacao).getId().intValue() == OrdemServicoSituacao.ENCERRADO || 
								  		((OrdemServicoSituacaoHelper) ordemServicoSituacao).getId().intValue() == OrdemServicoSituacao.CANCELADA) {
								  	%>
								  	&nbsp;&nbsp;
								  	<a href="javascript:abrirPopup('exibirConsultarResumoOrdemServicoSituacaoAcaoDetalhesPopupAction.do?tipoDetalhe=E&idServicoTipo=<bean:write name="resumoOrdemServico" property="id"/>&idOrdemServicoSituacao=<bean:write name="ordemServicoSituacao" property="id"/>
									  						&ordemServicoSituacao=<bean:write name="ordemServicoSituacao" property="descricao"/>
									  						&quantidadeTotal=<bean:write name="ordemServicoSituacao" property="quantidadeOS"/>&valorTotal=<bean:write name="ordemServicoSituacao" property="valorContas"/>', 450, 600);"><img border="0" src="imagens/pastaAzul.jpg" width="27" height="23" title="Consultar Motivo de Encerramento"/></a>
									<% } %>  						
									  													  							  
								</td>
								<td bgcolor="#cbe5fe" align="right"><strong> Quantidade</strong></td>
								<td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
								<td bgcolor="#cbe5fe" align="right"><strong> Valor     </strong> </td>
								<td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
							  </tr>
							  <tr>
								<td bgcolor="#FFFFFF" width="17%" align="right">
										<bean:write name="ordemServicoSituacao"
										property="quantidadeOS" formatKey="number.format"/>
								</td>
								<td bgcolor="#FFFFFF" width="17%" align="right">
										<bean:write name="ordemServicoSituacao"
										property="percentualQuantidade" formatKey="money.format"/>
								</td>
								<td bgcolor="#FFFFFF" width="17%" align="right">
									    <bean:write name="ordemServicoSituacao"
										property="valorContas" formatKey="money.format"/>
								</td>
								<td bgcolor="#FFFFFF" width="17%" align="right">
										 <bean:write name="ordemServicoSituacao"
										property="percentualValor" formatKey="money.format"/>
								</td>										    
							  </tr>											
						    <%
							}
						    %>
						  <logic:notEmpty name="ordemServicoSituacao"
						    property="colecaoDebitos">
						  <tr>
						    <td bgcolor="#cbe5fe" align="center" colspan="5"><strong> Situação do Débito </strong></td>
						  </tr>
						  <%String cor = "#FFFFFF";%>		
						  <logic:iterate name="ordemServicoSituacao" property="colecaoDebitos" 
						  		id="cobrancaDebito" type="CobrancaDebitoSituacaoHelper" >  															
							<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<tr bgcolor="#FFFFFF">
							<%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#cbe5fe">
							<%}%>
							  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							   <logic:notEqual name="cobrancaDebito" property="id" value="<%=""+CobrancaDebitoSituacao.SEM_DEBITOS%>">
							    <a href="javascript:abrirPopup('exibirConsultarResumoOrdemServicoPopupAction.do?idServicoTipo=<bean:write name="resumoOrdemServico" property="id"/>&idOrdemServicoSituacao=<bean:write name="ordemServicoSituacao" property="id"/>
							  						&ordemServicoSituacao=<bean:write name="ordemServicoSituacao" property="descricao"/>&idCobrancaDebito=<bean:write name="cobrancaDebito" property="id"/>
							  						&cobrancaDebito=<bean:write name="cobrancaDebito" property="descricao"/>&quantidadeTotal=<bean:write name="cobrancaDebito" property="quantidadeOS"/>&valorTotal=<bean:write name="cobrancaDebito" property="valorContas"/>', 400, 600);">
								<strong>
									<bean:write name="cobrancaDebito" property="descricao"/>
								</strong>
							   </a>
							   </logic:notEqual>
							   <logic:equal name="cobrancaDebito" property="id" value="<%=""+CobrancaDebitoSituacao.SEM_DEBITOS%>">
							    <bean:write name="cobrancaDebito"
								   property="descricao"/>
							   </logic:equal>
							  </td>
							  <td width="17%" align="right">
							    <bean:write name="cobrancaDebito"
											property="quantidadeOS" formatKey="number.format"/> 		
							  </td>
							  <td width="17%" align="right">
							    <bean:write name="cobrancaDebito"
											property="percentualQuantidade" formatKey="money.format"/>
							  </td>
							  <td width="17%" align="right">
								<bean:write name="cobrancaDebito"
										  	property="valorContas" formatKey="money.format"/>
							  </td>																											
							  
							  <td width="17%" align="right">
								<bean:write name="cobrancaDebito"
											property="percentualValor" formatKey="money.format"/>
							  </td>									
						    </tr>
							</logic:iterate>
						   </logic:notEmpty>
						 </logic:iterate> 
						 </logic:notEmpty>						  
						</table>
						
						<tr>
				          <td align="right">
						  <div align="right">						  
						  <a href="gerarRelatorioResumoAcoesOrdensServicoAction.do?id=<bean:write name="resumoOrdemServico" property="id"/>">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Resumo Ações OS" /> 
						  </a>
						  </div>
						  </td>
					    </tr>
						
					  </td>
				    </tr>
				  </table>
				 </div>
			    </td>
			   </tr>					
			  </logic:iterate>
			</table>
			<table width="100%" border="0">
			  <tr>
				<td colspan="2">
				  <div align="right"><jsp:include
					page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=2" /></div>
				</td>
			  </tr>	
	        </table>
	      </td>
		</tr>												
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp" %>
</html:form>
</body>
</html:html>