<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@page import="gcom.cadastro.cliente.ClienteAtualizacaoCadastral"  isELIgnored="false"%>
<%@page import="gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral"  isELIgnored="false"%>
<%@page import="gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral"  isELIgnored="false"%>
<%@page import="gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral"  isELIgnored="false"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

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
	
	function voltar(caminho){
		var form = document.forms[0];

		form.action = caminho;
		form.submit();
	}

	function pesquisarImovel(){
		var form = document.forms[0];

		if ( form.indicadorPesquisarImovel.value == "1" ) {
			redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirConsultarDadosImovelAmbienteVirtualPopupAction');	
		}
	}

	function limparImovel(){
		var form = document.forms[0];
		
		form.inscricaoImovel.value = "";
		form.idImovel.value = "";
	}


	function enviarDadosParametrosFecharPopupAmbienteVirtual() {
		var form = document.forms[0];
		
		opener.window.location.href =
		'/gsan/exibirConsultarImoveisPreGsanAction.do?idImovelAtlzCadastral=' + form.idImovelAtualizacaoCadastral.value +
		'&idImovel=' + form.idImovel.value;
		self.close();

	}

	function habilitarBotao() {
		var form = document.forms[0];
		if ( form.indicadorPesquisarImovel.value == "1" ) {
			form.ButtonAdicionariD.disabled = false;
			form.idImovel.disabled = false;
		} else {
			form.ButtonAdicionariD.disabled = true;
			form.idImovel.disabled = true;
		}
	}

	
</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(730, 550);javascript:setarFoco('${requestScope.nomeCampo}');habilitarBotao();">
	
<html:form action="/exibirConsultarDadosImovelAmbienteVirtualPopupAction" 
	name="ConsultarDadosImovelAmbienteVirtualPopupActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.ConsultarDadosImovelAmbienteVirtualPopupActionForm"
	method="post">
	
	<html:hidden property="idImovelAtualizacaoCadastral"/>
	<html:hidden property="indicadorPesquisarImovel"/>
	
	<table width="100%" border="0" cellspacing="5" cellpadding="0">		
		<tr>
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
						<td class="parabg">Consultar Dados do Im�vel no Ambiente Virtual 2</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
					<tr>
	      				<td colspan="2">Consulta dados do im�vel no movimento atualizado:</td>
	      			</tr>
	      			
	      			<tr>
	      				<td>&nbsp;</td>
	      			</tr>
	      			
	      			<tr>
	      				<td align="center">
	      					<table width="100%" border="0">
			      				<tr>
				      				<td width="150px;"><strong>Matr�cula do Im�vel:</strong></td>
					                <td> 
										<html:text property="matricula" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="9"
											maxlength="9" />
				                    </td>
				                    <td width="150px;"><strong>Data da Visita:</strong></td>
					                <td> 
										<html:text property="dataVisita" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="10"
											maxlength="10" />
				                    </td>
				            	</tr>
			      				<tr>
				      				<td width="150px;"><strong>Cadastrador:</strong></td>
					                <td colspan="4"> 
										<html:text property="cadastrador" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="65"
											maxlength="65" />
				                    </td>
				            	</tr>
				            	
				            	<tr>
				      				<td width="150px;"><strong>Dados da Ocorr�ncia:</strong></td>
					        			<logic:present name="listaCadastroOcorrencia">
										<td colspan="4">
											<table width="79%">
												<logic:iterate name="listaCadastroOcorrencia" id="cadastroOcorrencia">
													<tr bgcolor="#FFFFFF"  height="18">
														<td><bean:write name="cadastroOcorrencia" property="descricao"  /></td>
													</tr>
												</logic:iterate>
											</table>
										</logic:present>
										<logic:notPresent name="listaCadastroOcorrencia">
											<td colspan="2" style="background-color: #EFEFEF;"> &nbsp;
										</logic:notPresent>
				                    </td>
				            	</tr>
				            	
			                </table>
			        	</td>   
	      			</tr>
	      			
	      			<tr>
	      				<td>&nbsp;</td>
	      			</tr>
	      			
	      			<!-- Dados da Localidade  -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideLocalidade" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Localidade',true);">
	                     						<b>Dados da Localidade</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowLocalidade" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Localidade',false);">
	                     						<b>Dados da Localidade</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Localidade</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="localidade"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Setor Comercial</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="setorComercial"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Quadra</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="quadra"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Lote</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="lote"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Sub Lote</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="subLote"/>
	                                				</td>
	      										</tr>
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim da Localidade  -->
	      			
	      			<!-- Dados do Endere�o -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideEndereco" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Endereco',true);">
	                     						<b>Dados do Endere�o</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowEndereco" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Endereco',false);">
	                     						<b>Dados do Endere�o</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Tipo do Logradouro</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="tipoLogradouro"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>T�tulo do Logradouro</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="tituloLogradouro"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Descri��o do Logradouro</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="descricaoLogradouro"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>N�mero de Im�vel</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="numeroImovel"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Complemento do Endere�o</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="complementoLogradouro"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Refer�ncia</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="descricaoReferencia"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Bairro</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="bairro"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>C�digo Cep</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="cep"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Munic�pio</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="municipio"/>
	                                				</td>
	      										</tr>
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim do Endere�o  -->
	      			
	      			<!-- Dados Clientes -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideCliente" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Cliente',true);">
	                     						<b>Dados dos Clientes</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowCliente" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Cliente',false);">
	                     						<b>Dados dos Clientes</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										<logic:present name="colecaoCliente" scope="request">
	      											<logic:iterate name="colecaoCliente" id="cliente" scope="request" type="ClienteAtualizacaoCadastral">
														<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Nome do Cliente</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="nomeCliente"/>
			                                				</td>
			      										</tr>
			      										
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Tipo de Rela��o do Cliente</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="descricaoRelacaoTipoCliente"/>
			                                				</td>
			      										</tr>
			      										
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Data Rela��o fim</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="dataRelacaoFimFormatada"/>
			                                				</td>
			      										</tr>
			      										
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>CPF / CNPJ</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="cpfCnpj"/>
			                                				</td>
			      										</tr>
			      										
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>N�mero do RG</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="rgFormatado"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Data Emiss�o RG</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="dataEmissaoRg" formatKey="date.format"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Sexo</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="sexoFormatado"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Nome da M�e</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="nomeMae"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Data Nascimento</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="dataNascimento" formatKey="date.format"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Profiss�o</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="descricaoProfissao"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Telefone</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="telefoneFormatadoCliente"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Email</strong>
			                                				</td>
			      											<td width="50%" align="center">  
			                                					<bean:write name="cliente" property="email"/>
			                                				</td>
			      										</tr>
			      										
			      										<tr bgcolor="#FFFFFF">
			      											<td colspan="2">
			                                				
			                                				</td>
			      										</tr>
	      											
	      											</logic:iterate>
	      										</logic:present>
	      										
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim dos Clientes -->
	      			
	      			<!-- Dados Economias -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideEconomia" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Economia',true);">
	                     						<b>Dados das Economias</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowEconomia" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Economia',false);">
	                     						<b>Dados das Economias</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										
	      										
      											<logic:present name="colecaoSubCategoria" scope="request">
      											<% String cor = "#cbe5fe";%>
	      											<logic:iterate name="colecaoSubCategoria" id="subcategoria" scope="request" type="ImovelSubcategoriaAtualizacaoCadastral">
	      											
	      											<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">		
													<%}%>
			      											<td width="50%">
			                                					<strong>Categoria</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="subcategoria" property="descricaoCategoria"/>
			                                				</td>
			      										</tr>
			      										
			      									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">		
													<%}%>
			      											<td width="50%">
			                                					<strong>Subcategoria</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="subcategoria" property="descricaoSubcategoria"/>
			                                				</td>
			      										</tr>
			      										
			      									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">		
													<%}%>
			      											<td width="50%">
			                                					<strong>Quantidade de Economias</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="subcategoria" property="quantidadeEconomias"/>
			                                				</td>
			      										</tr>
	      											
	      											</logic:iterate>
	      										</logic:present>
	      										
	      										
	      						
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim das Economias -->
	      			
	      			<!-- Dados Caracter�sticas -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideCaracteristica" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Caracteristica',true);">
	                     						<b>Dados Caracter�sticas</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowCaracteristica" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Caracteristica',false);">
	                     						<b>Dados Caracter�sticas</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Perfil do Im�vel</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="imovelPerfil"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>�rea Constru�da</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="areaConstruida"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>N�mero do medidor de energia</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="numeroMedidorEnergia"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Contrato Energia</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="contratoEnergia"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Pavimento Cal�ada</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="pavimentoCalcada"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Pavimento Rua</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="pavimentoRua"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Fonte Abastecimento</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="fonteAbastecimento"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Analisar Tarifa Social</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="alertaTarifaSocial"/>
	                                				</td>
	      										</tr>
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim das Caracter�sticas -->
	      			
	      			<!-- Dados da Liga��o -->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideLigacao" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Ligacao',true);">
	                     						<b>Dados da Liga��o</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowLigacao" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Ligacao',false);">
	                     						<b>Dados da Liga��o</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<tr bordercolor="#000000">	
	      											<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
	      											Atributo</strong></td>
													<td bgcolor="#90c7fc" width="50%" align="center" ><strong>
													Conte�do</strong></td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Situa��o da Liga��o de �gua</strong>
	                                				</td>
	                                				<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="situacaoLigacaoAgua"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#cbe5fe">
	      											<td width="50%">
	                                					<strong>Situa��o da Liga��o de Esgoto</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="situacaoLigacaoEsgoto"/>
	                                				</td>
	      										</tr>
	      										
	      										<logic:present name="colecaoHidrometro" scope="request">
	      											<logic:iterate name="colecaoHidrometro" id="hidrometro" scope="request" type="HidrometroInstalacaoHistoricoAtualizacaoCadastral">
	      										
	      												<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Tipo de Medi��o</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="hidrometro" property="descricaoTipoMedicao"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>N�mero do Hidr�metro</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="hidrometro" property="numeroHidrometro"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Data de Instala��o</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="hidrometro" property="dataInstalacaoHidrometroFormatada"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Local de Instala��o</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="hidrometro" property="hidrometroLocalInstalacao.descricao"/>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#FFFFFF">
			      											<td width="50%">
			                                					<strong>Tipo de Prote��o</strong>
			                                				</td>
			                                				<td width="50%" align="center"> 
			                                					<logic:notEmpty name="hidrometro" property="hidrometroProtecao"> 
			                                						<bean:write name="hidrometro" property="hidrometroProtecao.descricao"/>
			                                					</logic:notEmpty>
			                                				</td>
			      										</tr>
			      										<tr bgcolor="#cbe5fe">
			      											<td width="50%">
			                                					<strong>Leitura</strong>
			                                				</td>
			                                				<td width="50%" align="center">  
			                                					<bean:write name="hidrometro" property="numeroInstalacaoHidrometro"/>
			                                				</td>
			      										</tr>
	      											</logic:iterate>
	      										</logic:present>
	      										<logic:notPresent name="colecaoHidrometro" scope="request">
      												<tr bgcolor="#FFFFFF">
		      											<td width="50%">
		                                					<strong>Tipo de Medi��o</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
		      										<tr bgcolor="#cbe5fe">
		      											<td width="50%">
		                                					<strong>N�mero do Hidr�metro</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
		      										<tr bgcolor="#FFFFFF">
		      											<td width="50%">
		                                					<strong>Data de Instala��o</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
		      										<tr bgcolor="#cbe5fe">
		      											<td width="50%">
		                                					<strong>Local de Instala��o</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
		      										<tr bgcolor="#FFFFFF">
		      											<td width="50%">
		                                					<strong>Tipo de Prote��o</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
		      										<tr bgcolor="#cbe5fe">
		      											<td width="50%">
		                                					<strong>Leitura</strong>
		                                				</td>
		                                				<td width="50%" align="center">  
		                                					
		                                				</td>
		      										</tr>
	      										
	      										</logic:notPresent>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Indetifica��o de Po�o</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="identificacaoPoco"/>
	                                				</td>
	      										</tr>
	      										
	      										<tr bgcolor="#FFFFFF">
	      											<td width="50%">
	                                					<strong>Observa��es</strong>
	                                				</td>
	      											<td width="50%" align="center">  
	                                					<bean:write name="ConsultarDadosImovelAmbienteVirtualPopupActionForm" property="observacao"/>
	                                				</td>
	      										</tr>
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim da Liga��o -->
	      			
	      			<!-- Dados da foto-->
	      			
	      			<tr bgcolor="#cbe5fe">
	      				<td align="center">
	      					<div id="layerHideFoto" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Foto',true);">
	                     						<b>Fotos</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
           					</div>
	      					
	      					<div id="layerShowFoto" style="display:none">
	      						<table width="100%" border="0" bgcolor="#99CCFF">
	      							<tr bgcolor="#99CCFF">
		                     			<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Foto',false);">
	                     						<b>Fotos</b>
	                     					</a>
	                    					</span>
		                     			</td>
	                    			</tr>
	      							
	      							<tr bgcolor="#cbe5fe">
										<td>
											<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
	      										<logic:present name="colecaoFoto">
													<%int cont = 1;%>
														<tr>
															<td colspan="2" height="7">
																<div id="gallery">
																    <ul>
																    	<table width="100%" border="0" >
																	    	<logic:iterate name="colecaoFoto" id="foto" type="gcom.cadastro.atualizacaocadastral.ImovelFotoAtualizacaoCadastral">
																		    		<%
																						  cont = cont + 1;
																						  if (cont % 2 == 0) {
																					  %>
																					<tr bgcolor="#cbe5fe">
																						<%}%>
																						<td >
																						<div id="gallery">
																							<table width="100%" border="0">
																								<tr>
																									<td align="center">
																										<strong><bean:write name="foto" property="fotoSituacao.descricao"/></strong>
																									</td>
																								</tr>
																								<tr>
																									<td align="center">
																									
																											<img width="220px" height="170px" 
																												src="/gsan/jsp/cadastro/atualizacaocadastral/imovel_foto.jsp?idFoto=<bean:write name="foto" property="id"/>"/>
																									</td>
																								</tr>
																							</table>
																							</div>
																							</td>
																							<%
																							  if (cont % 2 != 0) {
																						 	 %>
																							</tr>
																							<%}%>
																				</logic:iterate>
																		</table>
																    </ul>
																</div>
															</td>
														</tr>
													</logic:present>
													<logic:notPresent name="colecaoFoto">
														<tr>
															<td colspan="2" height="7" align="center">
																<div>
																	Nenhuma foto registrada.
																</div>
															</td>
														</tr>
													</logic:notPresent>
	      									</table>
	      								</td>
	      						</table>		
	      					</div>
	      				</td>
	      			</tr>
	      			
	      			<!-- Fim da fotos -->
	      			
	      			<tr>
	      				<td>
	      					<table width="100%" border="0">
	      						<tr>
									<td><strong>Im�vel:</strong><font color="#FF0000">*</font></td>
									<td colspan="3" align="right">
										<div align="left">
											
											<html:text styleId="idImovel" property="idImovel" size="9" 
												onkeyup="limparImovelInscricao();"
												maxlength="9" tabindex="4"  
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?objetoConsulta=1', 'idImovel','Imovel');return isCampoNumerico(event);"/>
												<a href="javascript:pesquisarImovel();"> <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Im�vel" /></a> 
					
											<logic:present name="imovelInexistente">
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													size="40" maxlength="45" />
											</logic:present>
											<logic:notPresent name="imovelInexistente">
												<html:text property="inscricaoImovel" size="40"
													maxlength="40" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notPresent> 
											
											<a href="javascript:limparImovel();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Tipo de D�bito" /> </a>
										</div>
									</td>
									<td>
										<input name="ButtonAdicionariD" type="button" class="bottonRightCol" id="ButtonAdicionariD"
	      									value="Adicionar Im�vel Duplicado" onclick="enviarDadosParametrosFecharPopupAmbienteVirtual();" style="width: 170px;">
									</td>
								</tr>
	      					</table>
	      				</td>
	      			</tr>
	      			
	      			
	      			
	      			
	      			<tr>
	      				<td>
	      					<table width="100%" border="0">
	      						<tr>
	      							<td align="left">
	      								<input name="ButtonFechar" type="button" class="bottonRightCol" 
	      									value="Fechar" onclick="window.close();" style="width: 70px;">
	      							</td>
	      							<td align="right">
									   <a href="/gsan/gerarRelatorioConsultaImovelPreGsanAction.do">
											<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
												title="Imprimir" /> 
										</a>
	      							</td>
	      						</tr>
	      					</table>
	      				</td>
	      			</tr>
	      			
	      		</table>
			</td>
		</tr>
	</table>
</html:form>

</body>

</html>