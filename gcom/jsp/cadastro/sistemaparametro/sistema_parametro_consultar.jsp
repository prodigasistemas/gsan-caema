<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarParametrosSistemaActionForm" dynamicJavascript="false" />--%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>
	function extendeTabela(tabela, display) {
		var form = document.forms[0];

		if (display) {
			eval('layerHide' + tabela).style.display = 'none';
			eval('layerShow' + tabela).style.display = 'block';
		} else {
			eval('layerHide' + tabela).style.display = 'block';
			eval('layerShow' + tabela).style.display = 'none';
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/exibirConsultarParametrosSistemaAction"
		type="gcom.gui.cadastro.sistemaparametro.ConsultarParametrosSistemaActionForm"
		method="post">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<input type="hidden" name="numeroPagina" value="1" />

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
					</div></td>
				<td width="625" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Consultar Parâmetros do Sistema</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td>Para consultar parâmetros do sistema, informe os dados
								abaixo:
							<td align="right"><a
								href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
									style="font-weight: bold"><font color="#3165CE">Ajuda</font>
								</span> </a>
							</td>
						</tr>
					</table>

					<table width="100%" border="0">

						<!-- --------------------------- 1ª Tabela ----------------------------------------------------------- -->
						<!-- Dados Gerais -->

						<tr bgcolor="#cbe5fe">
							<td align="center">
								<div id="layerHideLocal" style="display: block">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Local',true);" /> <b>Dados
														Gerais da Empresa</b> </a> </span></td>
										</tr>
									</table>
								</div>

								<div id="layerShowLocal" style="display: none">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Local',false);" /> <b>Dados
														Gerais da Empresa</b> </a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">



													<tr>
														<td width="25%" align="left"><strong>Nome do
																Estado: </strong></td>
														<td width="82%"><html:text maxlength="25"
																property="nomeEstado" size="25" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="28%" align="left"><strong>Nome da
																Empresa: </strong></td>
														<td><html:text maxlength="45" property="nomeEmpresa"
																size="50" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Abreviatura
																da Empresa: </strong></td>
														<td><html:text maxlength="10"
																property="abreviaturaEmpresa" size="10" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong> CNPJ:
														</strong></td>
														<td width="87%"><html:text property="cnpj" size="14"
																maxlength="14" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>
																Inscrição Estadual: </strong></td>
														<td width="87%"><html:text
																property="inscricaoEstadual" maxlength="20" size="22"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>
																Inscrição Municipal: </strong></td>
														<td width="87%"><html:text
																property="inscricaoMunicipal" maxlength="20" size="22"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong> Número
																do Contrato: </strong></td>
														<td width="87%"><html:text property="numeroContrato"
																maxlength="20" size="20" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Unidade
																Organizacional da Presidência:</strong></td>
														<td colspan="2"><html:text maxlength="5"
																property="unidadeOrganizacionalPresidencia" size="9"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text
																property="nomeUnidadeOrganizacionalPresidencia"
																size="45" maxlength="40" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td><strong>Presidente:</strong></td>
														<td><html:text maxlength="9" tabindex="1"
																property="presidente" size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text property="nomePresidente"
																size="45" maxlength="45" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td><strong>Diretor Comercial:</strong></td>
														<td><html:text maxlength="9" tabindex="1"
																property="diretorComercial" size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text property="nomeDiretorComercial"
																size="45" maxlength="45" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td><strong>Diretor de Gestão:</strong></td>
														<td><html:text maxlength="9" tabindex="1"
																property="diretorGestao" size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text property="nomeDiretorGestao"
																size="45" maxlength="45" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td colspan="3">
															<table width="100%" border="0">
																<tr>
																	<td><strong>Endereço:</strong></td>

																	<td align="right"><logic:present
																			name="colecaoEnderecos">
																			<input type="hidden" id="enderecoInformado">
																		</logic:present></td>
																</tr>
															</table></td>
													</tr>

													<tr>
														<td colspan="3" height="50" valign="top">
															<table width="100%" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table width="100%" border="0" bgcolor="#90c7fc">
																			<tr bgcolor="#90c7fc" height="18">

																				<td align="center"><strong>Endereço</strong>
																				</td>
																			</tr>
																		</table></td>
																</tr>

																<logic:present name="colecaoEnderecos">
																	<input type="hidden" id="enderecoInformado">

																	<tr>
																		<td>
																			<table width="100%" align="center" bgcolor="#99CCFF">
																				<!--corpo da segunda tabela-->

																				<%
																					String cor = "#cbe5fe";
																				%>

																				<logic:iterate name="colecaoEnderecos" id="endereco">

																					<%
																						if (cor.equalsIgnoreCase("#cbe5fe")) {
																											cor = "#FFFFFF";
																					%>
																					<tr bgcolor="#FFFFFF" height="18">
																						<%
																							} else {
																												cor = "#cbe5fe";
																						%>
																					
																					<tr bgcolor="#cbe5fe" height="18">
																						<%
																							}
																						%>

																						<td align="center"
																							style="background-color: #EFEFEF; border: 0; color: #000000">
																							<bean:write name="endereco"
																								property="enderecoFormatado" /></td>
																					</tr>
																				</logic:iterate>
																			</table></td>
																	</tr>
																</logic:present>
															</table></td>
													</tr>
													<tr>
														<td width="25%" align="left"><strong>Número
																do Telefone:</strong></td>
														<td><html:text maxlength="9"
																property="numeroTelefone" size="9"
																onkeyup="javascript:verificaNumeroInteiro(this);"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Ramal:</strong>
														</td>
														<td><html:text maxlength="4" property="ramal"
																size="4"
																onkeyup="javascript:verificaNumeroInteiro(this);"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Fax:</strong></td>
														<td><html:text maxlength="9" property="fax" size="9"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Site:</strong></td>
														<td><html:text maxlength="150" property="site"
																size="60" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>E-Mail:</strong>
														</td>
														<td><html:text maxlength="40" property="email"
																size="40" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																do Telefone de Atendimento:</strong></td>
														<td><html:text maxlength="12"
																property="numeroTelefoneAtendimento" size="15"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Quantidade
																de Dígitos da Quadra:</strong></td>
														<td><html:text property="qtdDigitosQuadra"
																maxlength="4" size="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador Quadra
																Face: </strong></td>
														<td><strong> <html:radio
																	property="indicadorQuadraFace" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorQuadraFace" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td><strong>Cliente Responsável Programa
																Especial:</strong></td>
														<td><html:text tabindex="1"
																property="idClienteResponsavelPrograma" size="9"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text
																property="nomeClienteResponsavelPrograma" size="45"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td><strong>Perfil Programa Especial:</strong></td>
														<td><html:text maxlength="15" tabindex="1"
																property="perfilProgramaEspecial" size="15"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp;</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador Popup de
																Atualização Cadastral:</strong></td>
														<td><strong> <html:radio
																	property="indicadorPopupAtualizacaoCadastral" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorPopupAtualizacaoCadastral" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>URL Segunda Via de Conta:</strong></td>
														<td><html:text maxlength="60" property="url2ViaConta" size="60" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" /></td>
													</tr>

													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para Relatório:</strong></td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Títulos
																de Relatório:</strong></td>
														<td><html:text maxlength="40"
																property="titulosRelatorio" size="55" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="25%" align="left"><strong>Caminho
																Imagem da Logomarca:</strong></td>
														<td><html:text maxlength="30"
																property="imagemLogomarca" size="32" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="25%" align="left"><strong>Caminho
														 		para Imagem ARPE:</strong></td>
														<td><html:text maxlength="30"
																property="imagemArpe" size="32" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="25%" align="left"><strong>Caminho
																para Imagem de rodapé da Conta:</strong></td>
														<td><html:text maxlength="30"
																property="imagemRodapeConta" size="32" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="25%" align="left"><strong>Caminho
																Imagem do Relatorio:</strong></td>
														<td><html:text maxlength="30"
																property="imagemRelatorio" size="32" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Caminho
																Imagem da Conta:</strong></td>
														<td><html:text maxlength="30" property="imagemConta"
																size="32" readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="25%" align="left"><strong>Execução
																do Resumo de Negativação:</strong></td>
														<td><html:text maxlength="2"
																property="numeroExecucaoResumoNegativacao" size="2"
																readonly="true"
																style="text-transform: none;background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%"><strong>Controlar os autos
																de infração:</strong></td>
														<td><strong> <html:radio
																	property="indicadorControlaAutoInfracao" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorControlaAutoInfracao" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador Exibir
																Mensagem:</strong></td>
														<td><strong> <html:radio
																	property="indicadorExibirMensagem" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorExibirMensagem" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Documento Principal
																Obrigatório:</strong></td>
														<td><strong> <html:radio
																	property="indicadorDocumentoObrigatorio" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorDocumentoObrigatorio" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Consulta ao SPC:</strong></td>
														<td><strong> <html:radio
																	property="indicadorConsultaSpc" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorConsultaSpc" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Documento Obrigatório Manter Cliente:</strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorDocumentoObrigatorioManterCliente" value="1" disabled="true" /> Sim 
															<html:radio property="indicadorDocumentoObrigatorioManterCliente" value="2" disabled="true" /> Não 
															</strong>
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Valor para Emissão
																de Extrato Tipo Ficha de Compensação:</strong></td>
														<td><html:text maxlength="13"
																property="valorExtratoFichaComp" size="13"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Valor
																para Emissão de Guia de Pagamento no Formato Ficha de
																Compensação:</strong></td>
														<td><html:text property="valorGuiaFichaComp" size="13"
																maxlength="13" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Valor
																para Emissão de Demonstrativo de Parcelamento no Formato
																Ficha de Compensação:</strong></td>
														<td><html:text
																property="valorDemonstrativoParcelamentoFichaComp"
																size="13" maxlength="13" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%">&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td colspan="2" align="center"><strong>Dados
																Gerais de Cadastro</strong>
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Usa Rota:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorUsaRota" value="1" disabled="true" />
																Sim <html:radio property="indicadorUsaRota" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="15%" align="left">
															<strong>Versão do celular:</strong>
															<font color="#FF0000"></font>
														</td>
														<td>
															<html:text maxlength="10" 
																property="versaoCelular"
																size="10" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias Bloqueio Celular:</strong></td>
														<td><html:text property="numeroDiasBloqueioCelular"
																maxlength="4" size="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																de Convergência da Repavimentação:</strong></td>
														<td><html:text
																property="percentualConvergenciaRepavimentacao" size="6"
																maxlength="6"
																onkeyup="javascript:formataValorMonetario(this, 5);"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Cadastrar Cliente em duplicidade:</strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorDuplicidadeCliente" value="1" disabled="true" /> Sim 
															<html:radio property="indicadorDuplicidadeCliente" value="2" disabled="true" /> N&atilde;o 
															</strong>
														</td>
													</tr>
													<tr>
														<td width="40%">
															<strong>Incluir Nome de Cliente com menos de 10 posições:</strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorNomeMenorDez" value="1" disabled="true" /> Sim 
															<html:radio property="indicadorNomeMenorDez" value="2" disabled="true" /> N&atilde;o 
															</strong>
														</td>
													</tr>
													<tr>
														<td width="40%">
															<strong>Incluir Nome de Cliente com descrição genérica:</strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorNomeClienteGenerico" value="1" disabled="true" /> Sim 
															<html:radio property="indicadorNomeClienteGenerico" value="2" disabled="true" /> N&atilde;o 
															</strong>
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de Uso do
																Nome Receita e Nome Fantasia em Substituição ao Nome e
																Nome Abreviado nas Telas Inserir e Manter Cliente:</strong></td>
														<td><strong> <html:radio
																	property="indicadorUsoNMCliReceitaFantasia" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorUsoNMCliReceitaFantasia" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
					                                    <td width="25%">
						                                    <strong>Mês/Ano Faturamento Início Contrato PPP:</strong>
					                                    </td>
					                                    <td>
						                                    <html:text maxlength="7" 
							                                     tabindex="1"
							                                     property="faturamentoInicioContratoPPP"		
							                                     onkeyup="mascaraAnoMes(this, event);"					
							                                     onkeypress="mascaraAnoMes(this, event);"
							                                     onkeydown="mascaraAnoMes(this, event);"
							                                     size="8" readonly="true"
							                                     style="background-color:#EFEFEF; border:0; color: #000000"/>mm/aaaa
					                                    </td>
				                                    </tr>
				                                    
				                                    <tr>
					                                    <td width="25%">
						                                    <strong>Percentual PPP:</strong>
					                                    </td>
					                                    <td><html:text maxlength="6" 
							                                property="percentualPPP" 
							                                size="8"
							                                onkeyup="javascript:formataValorMonetario(this, 5);"
							                                onkeypress="javascript:formataValorMonetario(this, 5);"
							                                readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					                                    </td>
				                                   </tr>
				                                   
				                                   <tr>
					                                    <td width="25%">
						                                    <strong>Quantidade de Dias Faturar Factível:</strong>
					                                    </td>
					                                    <td><html:text maxlength="3" 
							                                property="quantidadeDiasFaturarFactivel" 
							                                size="8" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					                                    </td>
				                                   </tr>
										
													<tr>
														<td width="40%"><strong>Variar Hierarquia da
																Unidade Organizacional:</strong></td>
														<td><strong> <html:radio
																	property="indicadorVariaHierarquiaUnidade" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorVariaHierarquiaUnidade" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													<tr>
														<td><strong>Cliente Fictício para Associar
																os Pagamentos Não Identíficados:</strong></td>
														<td><html:text maxlength="9" tabindex="1"
																property="clienteFicticioAssociarPagamentosNaoIdentificados"
																size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text maxlength="45"
																property="nomeClienteFicticioAssociarPagamentosNaoIdentificados"
																size="45" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Indicador de bloqueio funcionalidade inserir/atualizar usuário :</strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorBloquearFunUsuario" value="1" disabled="true"/>Sim
															<html:radio property="indicadorBloquearFunUsuario" value="2" disabled="true" />N&atilde;o 
									 						</strong>
														</td>
													</tr>
													
													<tr>
														<td width="25%" align="left">
															<strong>Domínio do E-mail corporativo:</strong>
														</td>
														<td>
															<html:text maxlength="20" 
																property="dominioEmailCorporativo" 
																size="40" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td><strong>Cliente Usuário Desconhecido:</strong></td>
														<td><html:text maxlength="9" tabindex="1"
																property="clienteUsuarioDesconhecido" size="9"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp; <html:text maxlength="45"
																property="nomeClienteUsuarioDesconhecido" size="45"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Data Limite para Cadastramento no Sorteio:</strong></td>
														<td><html:text property="dataLimiteCadastroSorteio" size="10" readonly="true"
															maxlength="10" style="background-color:#EFEFEF; border:0; color: #000000" />
														<strong>(dd/mm/aaaa)</strong></td>
													</tr>
													<tr>
														<td width="40%"><strong>Data do Sorteio:</strong></td>
														<td><html:text property="dataSorteio" size="10" readonly="true"
															maxlength="10" style="background-color:#EFEFEF; border:0; color: #000000" />
														<strong>(dd/mm/aaaa)</strong></td>
													</tr>
													
													<tr>
														<td width="40%" align="left">
															<strong>Tamanho Máximo de Anexo para Registro de Atendimento:</strong>
														</td>
														<td>
															<html:text maxlength="1" readonly="true"
																property="tamanhoMaximoAnexoRA" 
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="2" /> MB
														</td>
													</tr>
													
													
													<tr>
														<td width="40%" align="left">
															<strong>Redimensionar Imagem para:</strong>
														</td>
														<td>
															<html:text maxlength="4" 
																property="imagemResolucaoLargura" 
																size="4" 
																readonly="true"
																style="padding-right: 5px; background-color:#EFEFEF; border:0; color: #000000"/>
															X	
															<html:text maxlength="4" 
																property="imagemResolucaoAltura" 
																style="background-color:#EFEFEF; border:0; color: #000000"
																readonly="true"
																size="4" 
																onkeyup="javascript:verificaNumeroInteiro(this);"/> pixels	
														</td>	
													</tr>
													
													
												</table></td>
										</tr>
									</table>
								</div></td>
						</tr>



						<!-- --------------------------- 2ª tabela --------------------------------------------------------------- -->
						<!-- Faturamento Tarifa Social-->

						<tr bgcolor="#cbe5fe">
							<td align="center">
								<div id="layerHideFaturamento" style="display: block">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Faturamento',true);" /> <b>Faturamento
														Tarifa Social</b> </a> </span></td>
										</tr>
									</table>

								</div>

								<div id="layerShowFaturamento" style="display: none">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Faturamento',false);" /> <b>Faturamento
														Tarifa Social</b> </a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">

													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Mês e
																Ano de Referência:</strong></td>
														<td width="82%"><html:text
																property="mesAnoReferencia" size="7" maxlength="7"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															<strong>mm/aaaa</strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Menor
																Consumo para ser Grande Usuário:</strong></td>
														<td><html:text maxlength="9" property="menorConsumo"
																size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Menor
																Valor para Emissão de Contas:</strong></td>
														<td><html:text maxlength="13" property="menorValor"
																size="13" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Valor
																para Emissão de Conta no Formato Ficha de Compensação:</strong></td>
														<td><html:text maxlength="13"
																property="valorContaFichaComp" size="13" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong> Qtde
																de Economias para ser Grande Usuário:</strong></td>
														<td width="87%"><html:text property="qtdeEconomias"
																size="9" maxlength="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong> Quantidade de Dias de Débitos Vencidos para
					 											Suspensão da Geração das Doações:</strong></td>
														<td width="87%"><html:text property="qtdDiasDebitoVencidoParaSuspensaoGeracaoDeDoacoes"
																size="3" maxlength="3" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong> Meses
																para Cálculo de Média de Consumo:</strong></td>
														<td width="87%"><html:text
																property="mesesCalculoMedio" size="2" maxlength="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																Mínimo para Calcular Vencimento:</strong></td>
														<td><html:text property="diasMinimoVencimento"
																size="2" maxlength="5" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																Mínimo para Calcular Vencimento se entrega pelos
																Correios:</strong></td>
														<td><html:text property="diasMinimoVencimentoCorreio"
																maxlength="2" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias do
																Vencimento Alternativo:</strong></td>
														<td><html:text property="diasVencimentoAlternativo"
																size="50" maxlength="83" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de meses para validade da Conta:</strong></td>
														<td><html:text property="numeroMesesValidadeConta"
																maxlength="2" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de meses para alteração de um vencimento para outro:</strong></td>
														<td><html:text
																property="numeroMesesAlteracaoVencimento" maxlength="2"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																máximo de meses para retroagir o calculo da media:</strong></td>
														<td><html:text
																property="numeroMesesMaximoCalculoMedia" maxlength="2"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de meses para calcular correção monetária:</strong></td>
														<td><html:text property="numeroMesesCalculoCorrecao"
																maxlength="2" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Seguir
																Roteiro Empresa:</strong></td>
														<td><strong> <html:radio
																	property="indicadorRoteiroEmpresa" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorRoteiroEmpresa" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Alteração
																do Vencimento Mais de uma Vez:</strong></td>
														<td><strong> <html:radio
																	property="indicadorLimiteAlteracaoVencimento" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorLimiteAlteracaoVencimento" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Calcular Vencimento:</strong></td>
														<td><strong> <html:radio
																	property="indicadorCalculoVencimento" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorCalculoVencimento" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Tipo de
																Tarifa de Consumo: </strong></td>
														<td><strong> <html:radio
																	property="indicadorTarifaCategoria" value="1"
																	disabled="true" /> Por Categoria <html:radio
																	property="indicadorTarifaCategoria" value="2"
																	disabled="true" /> Por SubCategoria </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador
																Atualizaç&atilde;o Tarifária: </strong></td>
														<td><strong> <html:radio
																	property="indicadorAtualizacaoTarifaria" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorAtualizacaoTarifaria" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Mês e Ano de
																Atualização Tarifária:</strong></td>
														<td><html:text property="mesAnoAtualizacaoTarifaria"
																size="7" maxlength="7" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															<strong>mm/aaaa</strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																Faturamento Antecipado: </strong></td>
														<td><strong> <html:radio
																	property="indicadorFaturamentoAntecipado" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorFaturamentoAntecipado" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td width="40%"><strong>Retificar com um
																valor Menor: </strong></td>
														<td><strong> <html:radio
																	property="indicadorRetificacaoValorMenor" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorRetificacaoValorMenor" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Transferência com
																débito: </strong></td>
														<td><strong> <html:radio
																	property="indicadorTransferenciaComDebito" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorTransferenciaComDebito" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Não Medido por
																Tarifa de Consumo: </strong></td>
														<td><strong> <html:radio
																	property="indicadorNaoMedidoTarifa" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorNaoMedidoTarifa" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong> Qtde de contas retificadas:</strong></td>
														<td width="87%"><html:text property="qtdeContasRetificadas"
															size="9" maxlength="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" /></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Quantidade
																de dias de variação de consumo:</strong></td>
														<td><html:text maxlength="3"
																property="numeroDiasVariacaoConsumo" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Percentual do Bônus Social:</strong></td>
														<td><html:text maxlength="6" property="percentualBonusSocial"
															size="6" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador de
																bloqueio de recalculo e reemissao de conta na impressao
																simultanea:</strong></td>
														<td><strong> <html:radio
																	property="indicadorBloqueioContaMobile" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorBloqueioContaMobile" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Mensagem
																Pedido Conta BRAILE: </strong></td>
														<td><html:textarea property="mensagemContaBraile"
																cols="40" rows="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Código
																de Tipo de Cálculo de Não Medido:</strong></td>
														<td><html:text maxlength="15" tabindex="1"
																property="codigoTipoCalculoNaoMedido" size="28"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp;&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de meses para retificar uma conta:</strong></td>
														<td><html:text maxlength="4"
																property="numeroMesesRetificarConta" size="4"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Está na Norma de
																Retificação da Conta:</strong></td>
														<td><strong> <html:radio
																	property="indicadorNormaRetificacao" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorNormaRetificacao" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Nos casos dos poços medidos, o percentual de coleta deve incidir apenas sobre o volume de poço?: </strong>
														</td>
														<td><strong> 
															<html:radio property="indicadorPercentualColetaEsgoto" value="1" disabled="true" /> Sim 
															<html:radio property="indicadorPercentualColetaEsgoto" value="2" disabled="true" /> N&atilde;o 
														</strong></td>
													</tr>
													<tr>
														<td width="40%">
															<strong>Indicador para não efetuar mais o rateio de esgoto:</strong>
														</td>
														<td>
															<strong> 
																<html:radio property="indicadorDesconsiderarRateioEsgoto"
																value="<%=ConstantesSistema.SIM.toString()%>" disabled="true" /> Sim 
																<html:radio property="indicadorDesconsiderarRateioEsgoto"
																value="<%=ConstantesSistema.NAO.toString()%>" disabled="true" /> N&atilde;o 
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Quantidade máxima 
																de alterações no vinculo da conta:</strong></td>
														<td><html:text maxlength="4"
																property="numeroVezesAlterarVinculo" size="4"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Quantidade máxima de contas para retirar imóvel da 
														situação especial de faturamento por inadimplência:</strong></td>
														<td><html:text maxlength="2"
																property="quantidadeMaximaContasItensEspecialFaturamento" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left">
															<strong>Motivo retificação conta pagamento antecipado:</strong>
														</td>
														<td>
															<html:text maxlength="40" tabindex="1"
																property="motivoRetificacaoContaPagtoAntecipado" size="40"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left">
															<strong>Número limite de alterações do vencimento:</strong>
														</td>
												
														<td><html:text property="numeroLimiteAlteracoesVencimento" maxlength="2"
															size="2" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para Tarifa Social:</strong></td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Salário
																Mínimo:</strong></td>
														<td><html:text maxlength="9" property="salarioMinimo"
																size="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Área
																Máxima do Imóvel para a Tarifa:</strong></td>
														<td><html:text maxlength="7" property="areaMaxima"
																size="7" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Consumo
																de Energia Máxima para a Tarifa:</strong></td>
														<td><html:text maxlength="4" property="consumoMaximo"
																size="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de dias de prazo para entrada de recurso do auto de
																infração:</strong></td>
														<td><html:text maxlength="4"
																property="nnDiasPrazoRecursoAutoInfracao" size="4"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de vezes com consumo até 10m³ para suspender leitura:</strong></td>
														<td><html:text property="numeroVezesSuspendeLeitura"
																maxlength="2" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de meses para manter leitura suspensa:</strong></td>
														<td><html:text property="numeroMesesLeituraSuspensa"
																maxlength="2" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Intervalo
																de meses para considerar reincidência de consumo até
																10m³:</strong></td>
														<td><html:text
																property="numeroMesesReinicioSitEspFatu" maxlength="2"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Quantidade Mínima de Economias:<br>
															(Contrato de Demanda Residencial):</strong></td>
														<td><html:text
																property="qtdEconomiaContratoDemanda" maxlength="3"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
												
												</table></td>
										</tr>
									</table>
								</div></td>
						</tr>



						<!-- --------------------------- 3ª tabela --------------------------------------------------------------- -->
						<!-- Arrecadação Financeiro -->

						<tr bgcolor="#cbe5fe">
							<td align="center">
								<div id="layerHideArrecadacao" style="display: block">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Arrecadacao',true);" /> <b>Arrecadação
														Financeiro</b> </a> </span></td>
										</tr>
									</table>

								</div>

								<div id="layerShowArrecadacao" style="display: none">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Arrecadacao',false);" /> <b>Arrecadação
														Financeiro</b> </a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Mês e
																Ano de Referência:</strong></td>
														<td width="82%"><html:text
																property="mesAnoReferenciaArrecadacao" size="7"
																maxlength="7" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" /><strong>
																mm/aaaa</strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Código
																da Empresa para FEBRABAN:</strong></td>
														<td><html:text maxlength="4"
																property="codigoEmpresaFebraban" size="4"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																do Lay-out da FEBRABAN:</strong></td>
														<td><html:text maxlength="4" property="numeroLayOut"
																size="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Identificador
																da Conta Bancária para Devolução:</strong></td>
														<td><html:text maxlength="4"
																property="indentificadorContaDevolucao" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																do módulo verificador:</strong></td>
														<td><html:text maxlength="2"
																property="numeroModuloDigitoVerificador" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Número
																meses para pesquisa de imóveis com ramais suprimidos:</strong></td>
														<td><html:text maxlength="3"
																property="numeroMesesPesquisaImoveisRamaisSuprimidos"
																size="4"
																style="background-color:#EFEFEF; border:0; color: #000000"
																readonly="true" /></td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Número
																de anos para geração da declaração de quitação de
																débitos:</strong></td>
														<td><html:text maxlength="3"
																property="numeroAnoQuitacao" size="4"
																style="background-color:#EFEFEF; border:0; color: #000000"
																readonly="true" /></td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Quantidade
																de meses anteriores geração declaração de quitação de
																débitos:</strong></td>
														<td><html:text maxlength="3"
																property="numeroMesesAnterioresParaDeclaracaoQuitacao"
																size="4"
																style="background-color:#EFEFEF; border:0; color: #000000"
																readonly="true" /></td>
													</tr>

													<tr>
														<td width="40%"><strong>Contas parceladas
																para declaração de quitação de débitos: </strong></td>
														<td><strong> <html:radio
																	property="indicadorContaParcelada" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorContaParcelada" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Contas em cobrança
																judicial para declaração de quitação de débitos: </strong></td>
														<td><strong> <html:radio
																	property="indicadorCobrancaJudical" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorCobrancaJudical" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador do valor
																do movimento arrecadador: </strong></td>
														<td><strong> <html:radio
																	property="indicadorValorMovimentoArrecadador" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorValorMovimentoArrecadador" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>N&uacute;mero
																Conv&ecirc;ncio Ficha Compensa&ccedil;&atilde;o:</strong></td>
														<td><html:text maxlength="7"
																property="numeroConvenioFichaCompensacao" size="7"
																style="background-color:#EFEFEF; border:0; color: #000000"
																readonly="true" /></td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para o Financeiro:</strong></td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>
																Percentual de Entrada Mínima para Financiamento:</strong></td>

														<td width="87%"><html:text
																property="percentualEntradaMinima" size="5"
																maxlength="5" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Máximo
																de Parcelas para um Financiamento:</strong></td>
														<td><html:text maxlength="5"
																property="maximoParcelas" size="5" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																Máximo para Abatimento de um Serviço:</strong></td>
														<td><html:text maxlength="5"
																property="percentualMaximoAbatimento" size="5"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																de Taxa de Juros para Financiamento:</strong></td>
														<td><html:text maxlength="5"
																property="percentualTaxaFinanciamento" size="5"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Número
																Máximo para Parcela de Crédito:</strong></td>

														<td><html:text maxlength="3"
																property="numeroMaximoParcelaCredito" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																da Média do Índice para Cálculo do Parcelamento:</strong></td>
														<td><html:text maxlength="5"
																property="percentualCalculoIndice" size="5"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left">
															<strong>Código Relatório Dados Diários:</strong>
														</td>
														<td><html:text maxlength="40" tabindex="1"
																property="cdDadosDiarios" size="40"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

												</table></td>
										</tr>
									</table>
								</div></td>
						</tr>


						<!-- --------------------------- 4ª tabela --------------------------------------------------------------- -->
						<!-- Medicao Cobranca -->

						<tr bgcolor="#cbe5fe">
							<td align="center">
								<div id="layerHideMedicao" style="display: block">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Medicao',true);" /> <b>Medi&ccedil;&atilde;o
														Cobran&ccedil;a</b> </a> </span></td>
										</tr>
									</table>

								</div>

								<div id="layerShowMedicao" style="display: none">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Medicao',false);" /> <b>Medi&ccedil;&atilde;o
														Cobran&ccedil;a</b> </a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td>
												<table border="0" width="100%">

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Menor
																Capacidade de Hidrômetro para ser Grande Usuário:</strong></td>
														<td><html:text property="descricaoMenorCapacidade"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de Geração
																de Faixa Falsa:</strong></td>
														<td><strong> <html:radio
																	property="indicadorGeracaoFaixaFalsa" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorGeracaoFaixaFalsa" value="2"
																	disabled="true" /> N&atilde;o <html:radio
																	property="indicadorGeracaoFaixaFalsa" value="3"
																	disabled="true" /> De acordo com a Rota </strong></td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador do
																Percentual para Geração de Faixa Falsa:</strong></td>
														<td><strong> <html:radio
																	property="indicadorPercentualGeracaoFaixaFalsa"
																	value="1" disabled="true" /> Percentual Parâmetro <html:radio
																	property="indicadorPercentualGeracaoFaixaFalsa"
																	value="2" disabled="true" /> Percentual da Rota </strong></td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>
																Percentual de Geração de Faixa Falsa:</strong></td>
														<td><html:text property="percentualGeracaoFaixaFalsa"
																size="5" maxlength="5"
																onkeyup="javascript:formataValorMonetario(this, 5);"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de Geração
																de Fiscalização de Leitura :</strong></td>
														<td><strong> <html:radio
																	property="indicadorGeracaoFiscalizacaoLeitura"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorGeracaoFiscalizacaoLeitura"
																	value="2" disabled="true" /> Não <html:radio
																	property="indicadorGeracaoFiscalizacaoLeitura"
																	value="3" disabled="true" /> De acordo com a Rota </strong></td>

													</tr>

													<tr>
														<td width="40%"><strong>Indicador do
																Percentual Geração de Fiscalização de Leitura :</strong></td>
														<td><strong> <html:radio
																	property="indicadorPercentualGeracaoFiscalizacaoLeitura"
																	value="1" disabled="true" /> Percentual Parâmetro <html:radio
																	property="indicadorPercentualGeracaoFiscalizacaoLeitura"
																	value="2" disabled="true" /> Percentual da Rota </strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																de Geração de Fiscalização de Leitura:</strong></td>
														<td><html:text
																property="percentualGeracaoFiscalizacaoLeitura" size="5"
																maxlength="5" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Incremento
																Máximo de Consumo por economia em Rateio:</strong></td>
														<td width="87%"><html:text
																property="incrementoMaximoConsumo" size="9"
																maxlength="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Decremento
																Máximo de Consumo por economia em Rateio: </strong></td>
														<td><html:text property="decrementoMaximoConsumo"
																size="9" maxlength="9" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Percentual
																de Tolerância para o Rateio do Consumo: </strong></td>
														<td width="87%"><html:text
																property="percentualToleranciaRateioConsumo" size="5"
																maxlength="5" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para Cobrança:</strong>
														</td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias entre o Vencimento e o Início da Cobrança:</strong></td>
														<td><html:text maxlength="2"
																property="diasVencimentoCobranca" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																Máximo de Meses de Sanções:</strong></td>
														<td><html:text maxlength="2"
																property="numeroMaximoMesesSancoes" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Valor
																da Segunda Via:</strong></td>
														<td><html:text maxlength="13"
																property="valorSegundaVia" size="13" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																onkeyup="javascript:formataValorMonetario(this, 13);" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																Cobrança da Taxa de Extrato :</strong></td>
														<td><strong> <html:radio
																	property="indicadorCobrarTaxaExtrato" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorCobrarTaxaExtrato" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Código
																da Periodicidade da Negativaç&atilde;o:</strong></td>
														<td><html:text maxlength="2"
																property="codigoPeriodicidadeNegativacao" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													
													<tr>
														<td width="40%"><strong>Criticar
																Conte&uacute;do do Retorno Movimento
																Negativa&ccedil;&atilde;o Confirmado :</strong></td>
														<td><strong> <html:radio
																	property="indicadorCriticarConteudoRetornoMovimentoNegativacao"
																	value="<%=ConstantesSistema.SIM.toString()%>"
																	disabled="true" /> Sim <html:radio
																	property="indicadorCriticarConteudoRetornoMovimentoNegativacao"
																	value="<%=ConstantesSistema.NAO.toString()%>"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td colspan="3"><hr>
														</td>
													</tr>

												

													<tr>
														<td width="40%"><strong>Retirar Contas
																Vinculadas a Contrato de Parcelamento da Composição do
																Débito do Imóvel ou do Cliente?</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorBloqueioContasContratoParcelDebitos"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorBloqueioContasContratoParcelDebitos"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Retirar Guias
																Vinculadas a Contrato de Parcelamento da Composição do
																Débito do Imóvel ou do Cliente?</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorBloqueioGuiasOuAcresContratoParcelDebito"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorBloqueioGuiasOuAcresContratoParcelDebito"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Retirar os Débitos a Cobrar Vinculados ao Contrato de Parcelamento da Composição do Débito do Imóvel ou do Cliente?</strong>
														</td>
														<td>
															<strong> 
																<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelDebito" value="1" disabled="true" /> Sim
																<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelDebito" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Bloquear Contas
																Vinculadas a Contrato de Parcelamento na tela de Manter
																Conta?</strong></td>
														<td><strong> <html:radio
																	property="indicadorBloqueioContasContratoParcelManterConta"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorBloqueioContasContratoParcelManterConta"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Bloquear Guias de
																Juros ou de Acréscimos por Impontualidade Vinculadas a
																Contrato de Parcelamento na tela de Manter Guia?</strong></td>
														<td><strong> <html:radio
																	property="indicadorBloqueioGuiasOuAcresContratoParcelManterConta"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorBloqueioGuiasOuAcresContratoParcelManterConta"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Bloquear os Débitos a Cobrar Vinculados ao Contrato de Parcelamento na tela de Manter Débitos a Cobrar?</strong>
														</td>
														<td>
															<strong>
																<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelManterDebito" value="1" disabled="true" /> Sim
																<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelManterDebito" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias para Calculo de Adicionais de Impontualidade:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasCalculoAcrescimos" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias de Validade do Extrato de Débito:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasValidadeExtrato" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias de Validade do Extrato de Débito para quem
																possui Permissão Especial:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasValidadeExtratoPermissaoEspecial"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias para o Vencimento da Guia de pagamento de
																Entrada de Parcelamento:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasVencimentoEntradaParcelamento"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Resolução
																de Diretoria para Cálculo de Descontos para pagamento à
																vista:</strong></td>
														<td><html:text maxlength="2"
																property="idResolucaoDiretoria" size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>


													<tr>
														<td width="40%"><strong>Indicador
																Parcelamento Confirmado :</strong></td>
														<td><strong> <html:radio
																	property="indicadorParcelamentoConfirmado" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorParcelamentoConfirmado" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Número
																de dias úteis para que a OS de Fiscalização seja
																encerrada por Decurso de Prazo:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasEncerrarOsFiscalizacaoDecursoPrazo"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Cálculo
																Juros Parcelamento Pela Tabela Price :</strong></td>
														<td><strong> <html:radio
																	property="indicadorTabelaPrice" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorTabelaPrice" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td colspan="3"><hr></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																Máximo de Parcelas para os Contratos de Parcelamento por
																Cliente:</strong></td>
														<td><html:text maxlength="2"
																property="numeroMaximoParcelasContratosParcelamento"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador para
																exibir Filtro de Cobrança por resultado pelo valor total
																do débito:</strong></td>
														<td><strong> <html:radio
																	property="indicadorTotalDebito" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorTotalDebito" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>


													<tr>
														<td width="40%"><strong> Conta com vencimento
																alterado excluir da negativação?
														</td>
														<td><strong> <html:radio
																	property="indicadorCanceNegatContaVencAlter" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorCanceNegatContaVencAlter" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>


													<tr>
														<td width="40%" align="left"><strong>Número
																de dias de vencimento para envio das contas para as
																empresas de cobrança:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasEnvioContaEmpresaCobranca" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de dias para retirada das contas das empresas de
																cobrança: </strong></td>
														<td><html:text maxlength="2"
																property="numeroDiaRetiradaContaEmpresaCobraca" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong> Incluir contas fora
																do vencimento para cobrança:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorIncluirContasForaVenCobranca"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorIncluirContasForaVenCobranca"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Número de dias que permite 
														inserir processo comando de cobrança:</strong></td>
														<td><html:text maxlength="2"
																property="numeroDiasInserirProcessoCobranca"
																size="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%">
															<strong>Indicador de controle de dívida ativa:</strong>
														</td>
														<td>
															<strong>
																<html:radio property="indicadorControleDividaAtiva" value="1" disabled="true" /> Sim
																<html:radio property="indicadorControleDividaAtiva" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Indicador de emissão de extrato de débito na consulta:</strong>
														</td>
														<td>
															<strong>
																<html:radio property="indicadorEmissaoExtratoDebitoNaConsulta" value="1" disabled="true" /> Sim
																<html:radio property="indicadorEmissaoExtratoDebitoNaConsulta" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Indicador de inclusão de contas canceladas no boletim de cobrança por resultado:</strong>
														</td>
														<td>
															<strong>
																<html:radio property="indicadorInclusaoContasCanceladasBoletim" value="1" disabled="true" /> Sim
																<html:radio property="indicadorInclusaoContasCanceladasBoletim" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%">
															<strong>Indicador de inclusão de contas para cobrança por resultado:</strong>
														</td>
														<td>
															<strong>
																<html:radio property="indicadorIncluirContaEmCobranca" value="1" disabled="true" /> Sim
																<html:radio property="indicadorIncluirContaEmCobranca" value="2" disabled="true" /> N&atilde;o
															</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left">
															<strong>Número de dias para vencimento da conta em cobrança:</strong>
														</td>
												
														<td><html:text property="numeroDiasVencimentoCobrancaResultado" maxlength="2"
															size="2" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td colspan="3"><hr>
														</td>
													</tr>

												</table></td>
										</tr>
									</table>
								</div></td>
						</tr>

						<!-- AQUI FICA A QUINTA TABELA -->
						<!-- --------------------------- 5ª tabela --------------------------------------------------------------- -->
						<!-- Atendimento Seguranca -->

						<tr bgcolor="#cbe5fe">
							<td align="center">
								<div id="layerHideAtendimento" style="display: block">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Atendimento',true);" /> <b>Atendimento
														Seguran&ccedil;a</b> </a> </span></td>
										</tr>
									</table>

								</div>

								<div id="layerShowAtendimento" style="display: none">

									<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('Atendimento',false);" /> <b>Atendimento
														Seguran&ccedil;a</b> </a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">

											<td>
												<table border="0" width="100%">

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																Sugestão de Trâmite:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorSugestaoTramite" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorSugestaoTramite" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																Controle de Autorização para a Tramitação do RA:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorControleTramitacaoRA" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorControleTramitacaoRA" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador de Calculo da
																Data Prevista do RA em Dias Úteis:</strong></td>
														<td><strong> <html:radio
																	property="indicadorCalculoPrevisaoRADiasUteis" value="1" disabled="true" />
																Sim <html:radio property="indicadorCalculoPrevisaoRADiasUteis"
																	value="2" disabled="true" /> N&atilde;o </strong>
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																Máximo para Reativar RA:</strong></td>
														<td width="87%"><html:text
																property="diasMaximoReativarRA" size="2" maxlength="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																Máximo para Alterar Dados da OS:</strong></td>
														<td width="87%"><html:text
																property="diasMaximoAlterarOS" size="2" maxlength="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>



													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias para Encerramento da OS: </strong></td>

														<td width="87%"><html:text
																property="numeroDiasEncerramentoOrdemServico" size="2"
																maxlength="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																de Dias para Encerramento da OS Seletiva: </strong></td>

														<td width="87%"><html:text
																property="numeroDiasEncerramentoOSSeletiva" size="2"
																maxlength="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																Validação da Localidade no Encerramento da OS Seletiva:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorValidacaoLocalidadeEncerramentoOS"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorValidacaoLocalidadeEncerramentoOS"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador para exibir o botão imprimir na tela de sorteio:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorImprimirExtratoSorteio"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorImprimirExtratoSorteio"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>



													<tr>
														<td width="40%" align="left"><strong>Quantidade
																de dias de prorrogação do vencimento na retificação: </strong></td>

														<td width="87%"><html:text
																property="numeroDiasAlteracaoVencimentoPosterior"
																size="2" maxlength="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Prazo
																para Revisão de Conta(nº dias após vencimento): </strong></td>

														<td width="87%"><html:text
																property="numeroDiasRevisaoConta" size="2" maxlength="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Último
																ID Utilizado para Geração do RA Manual: </strong></td>
														<td width="87%"><html:text
																property="ultimoIDGeracaoRA" size="5" maxlength="5"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador Certidão
																Negativa com Efeito Positivo:</strong></td>
														<td><strong> <html:radio
																	property="indicadorCertidaoNegativaEfeitoPositivo"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorCertidaoNegativaEfeitoPositivo"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>


													<tr>
														<td width="40%"><strong>Indicador de
																D&eacute;bito a Cobrar v&aacute;lido Certidão Negativa:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorDebitoACobrarValidoCertidaoNegativa"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicadorDebitoACobrarValidoCertidaoNegativa"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>													
													
													<tr>
														<td colspan="3"><hr></td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																dias de Vencimento para gerar Certidão Negativa:</strong></td>
														<td><html:text maxlength="3"
																property="diasVencimentoCertidaoNegativa" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="25%" align="left"><strong>Número de Dias para Encerramento Ordem Serviço Factível Faturável:</strong></td>
														<td><html:text maxlength="3"
																property="numeroDiasEncerramentoOrdemServicoFactivelFatural" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td><strong>Serviço da Ordem de Serviço seletiva Factível Faturável:<strong></td>
														<td>
															<html:text maxlength="4" size="4"
																	   property="idServicoTipo"
																	   readonly="true"
																	   style="background-color:#EFEFEF; border:0; color: #000000"/>
															<html:text property="desServicoTipo" 
																	   size="38"
																	   maxlength="30" 
																	   readonly="true"
																	   style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador de
																Documento obrigatório para 2ª via da Conta:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorDocumentoValido" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorDocumentoValido" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td><strong>Unidade de destino para imóveis
																com perfil de grande consumidor:</strong></td>
														<td><html:text tabindex="1"
																property="idUnidadeDestinoGrandeConsumidor" size="7"
																readonly="true" maxlength="9"
																style="background-color:#EFEFEF; border:0; color: #000000" />
															&nbsp; <html:text
																property="nomeUnidadeDestinoGrandeConsumidor" size="40"
																readonly="true" maxlength="45"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Último
																dia do Vencimento Alternativo:</strong></td>
														<td><html:text maxlength="3"
																property="ultimoDiaVencimentoAlternativo" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																de dias para validade ordem de fiscalização:</strong></td>
														<td><html:text maxlength="2"
																property="qtdeDiasValidadeOSFiscalizacao" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																máximo de dias para uma ordem de serviço ser
																fiscalizada:</strong></td>
														<td><html:text maxlength="2"
																property="qtdeDiasEncerraOSFiscalizacao" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																de dias para envio de conta por email:</strong></td>
														<td><html:text maxlength="2"
																property="qtdeDiasEnvioEmailConta" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td><strong>Exigir RA no cancelamento do débito:</strong></td>
							
														<td><html:radio property="indicadorPermiteCancelarDebito"
																value="1" disabled="true" /><strong>Sim</strong>&nbsp; <html:radio
																property="indicadorPermiteCancelarDebito" value="2"
																disabled="true" /><strong>N&atilde;o</strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Período de
																revalidação de senhas dos usuários subordinados:</strong>
														</td>
							
														<td width="75%"><html:text
																property="periodoRevalidarSenha" size="3" maxlength="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Quantidade de
																dias aviso para revalidar acesso:</strong>
														</td>
							
														<td width="75%"><html:text
																property="diasRevalidarSenha" size="3" maxlength="3" 
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="25%" align="left"><strong>Número
																de dias limite para atualizar a situação da ligação de
																água do imóvel fiscalizado:</strong></td>
														<td><html:text maxlength="2"
																property="diasAtualizarLigacaoAguaImovelFisc" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Tramitar Automaticamente RA
																de ESGOTO:</strong></td>
														<td><strong> <html:radio
																	property="indicadorTramiteEsgoto" value="1" disabled="true" /> Sim <html:radio
																	property="indicadorTramiteEsgoto" value="2" disabled="true" /> N&atilde;o </strong>
														</td>
													</tr>
													<tr>
														<td width="40%" align="left"><strong>Consumo de
																água fixado para anormalidade hidrômetro:
														</strong>
														</td>
							
														<td width="75%"><html:text
																property="consumoAguaFixadoAnormalidadeHidro" size="2"
																maxlength="2" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
							
													<tr>
														<td width="40%" align="left"><strong>Quantidade de
																meses da instalação e/ou substituição de hidrômetro:
														</strong>
														</td>
							
														<td width="75%"><html:text
																property="qtdMesIstalSubistituicaoHidro" size="2" maxlength="2" 
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
							
													<tr>
														<td width="40%" align="left"><strong>Quantidade de
																vezes da incidência de anormalidade de hidrômetro:
														</strong>
														</td>
							
														<td width="75%"><html:text
																property="qtdVezIncidenciaAnormalidadeHidro" size="2"
																maxlength="2" 
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador para bloquear funcionalidades de Instalação/Substituição de hidrômetro:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorBloqFuncInstalacaoSubtHidrometro" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorBloqFuncInstalacaoSubtHidrometro" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													

													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para Segurança:</strong>
														</td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador de Acesso
																por Usuário:</strong></td>
														<td><strong> <html:radio
																	property="indicadorLoginUnico" value="0"
																	disabled="true" /> V&aacute;rios Acessos <html:radio
																	property="indicadorLoginUnico" value="1"
																	disabled="true" /> &Uacute;nico Acesso </strong></td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																Máximo para Expirar o Acesso:</strong></td>
														<td><html:text maxlength="2"
																property="diasMaximoExpirarAcesso" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Dias
																para Começar Aparecer a Mensagem de Expiração de Senha:</strong>
														</td>
														<td><html:text maxlength="2"
																property="diasMensagemExpiracaoSenha" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Número
																Máximo de Tentativas de Acesso:</strong></td>
														<td><html:text maxlength="2"
																property="numeroMaximoTentativasAcesso" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>


													<tr>
														<td width="40%" align="left"><strong>Número
																Máximo de Favoritos no Menu do Sistema:</strong></td>
														<td><html:text maxlength="2"
																property="numeroMaximoFavoritosMenu" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>IP do
																Servidor SMTP: </strong></td>
														<td><html:text maxlength="15"
																property="ipServidorSmtp" size="15" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>IP do
																Servidor Gerencial: </strong></td>
														<td><html:text maxlength="30"
																property="ipServidorGerencial" size="31" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>URL do
																servidor HELP:</strong>
														</td>
														<td><html:text maxlength="60" property="urlHelp" size="55"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>E-mail
																do Responsável: </strong></td>
														<td><html:text maxlength="80"
																property="emailResponsavel" size="35" readonly="true"
																style="text-transform: none; background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>

													<tr>
														<td width="40%" align="left"><strong>Mensagem
																do Sistema: </strong></td>
														<td><html:textarea property="mensagemSistema"
																cols="40" rows="4" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador de
																controle de dias de expiração de senha por Grupo:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicarControleExpiracaoSenhaPorGrupo"
																	value="1" disabled="true" /> Sim <html:radio
																	property="indicarControleExpiracaoSenhaPorGrupo"
																	value="2" disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																controle de bloqueio de senhas usadas anteriormente:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicarControleBloqueioSenha" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicarControleBloqueioSenha" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>

													<tr>
														<td width="40%"><strong>Indicador de
																controle de senha forte:</strong>
														</td>
														<td><strong> <html:radio
																	property="indicadorSenhaForte" value="1"
																	disabled="true" /> Sim <html:radio
																	property="indicadorSenhaForte" value="2"
																	disabled="true" /> N&atilde;o </strong></td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Número mínimo
																de dias para bloqueio da senha:
														</strong>
														</td>
														<td><html:text maxlength="2"
																property="numeroDiasBloqueiaSenha" size="2"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</td>
													</tr>
													<tr>
														<td width="40%"><strong>Indicador de permissão
																especial para o grupo:
														</strong></td>
														<td><strong> <html:radio
																	property="indicadorPermissaoEspecialGrupo" value="1" disabled="true" /> Sim <html:radio
																	property="indicadorPermissaoEspecialGrupo" value="2" disabled="true" />
																N&atilde;o </strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%"><strong>Indicador de módulo de
																segurança:
														</strong></td>
														<td><strong> <html:radio
																	property="indicadorModuloSeguranca" value="1" disabled="true" /> Sim <html:radio
																	property="indicadorModuloSeguranca" value="2" disabled="true" /> N&atilde;o </strong>
														</td>
													</tr>
													
													<tr>
														<td width="40%" align="left"><strong>Número máximo de OS por arquivo para o sistema de cobrança via smartphone:</strong>
														</td>
														<td><html:text maxlength="3"
																property="numeroLimiteOSCobranca" size="3"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"/>
														</td>
													</tr>
												
													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>
														<td colspan="2" align="center"><strong>Parâmetros
																para a Loja Virtual:</strong>
														</td>
													</tr>

													<tr>
														<td colspan="2" align="center"><br />

															<table width="84%" border="0">
																<tr>
																	<td colspan="2">

																		<table width="100%" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>

																					<table width="100%" bgcolor="#99CCFF">
																						<tr bgcolor="#99CCFF">
																							<td width="10%">
																								<div align="center">
																									<strong>Arquivo</strong>
																								</div></td>
																							<td style="width: 80%">
																								<div align="center">
																									<strong>Descrição</strong>
																								</div></td>
																						</tr>
																					</table></td>
																			</tr>
																			<tr>
																				<td>
																					<div
																						style="width: 100%; height: 100; overflow: auto;">

																						<table width="100%" align="center"
																							bgcolor="#99CCFF">

																							<logic:present name="arquivoDecreto"
																								scope="request">
																								<tr bgcolor="#cbe5fe">
																									<td align="center" width="10%" valign="middle"><a
																										href="exibirConsultarParametrosSistemaAction.do?modo=verDecreto"
																										title="Visualizar Arquivo" target="_blank">
																											<IMG
																											SRC="<bean:message key="caminho.imagens"/>PDF.gif"
																											width="35" height="35" BORDER="0" ALT="">
																									</a></td>

																									<td width="80%"><bean:write
																											property="descricaoDecreto"
																											name="ConsultarParametrosSistemaActionForm" />
																									</td>
																								</tr>
																							</logic:present>

																							<logic:present name="arquivoLeiTarifa"
																								scope="request">
																								<tr bgcolor="#cbe5fe">
																									<td align="center" width="10%" valign="middle"><a
																										href="exibirConsultarParametrosSistemaAction.do?modo=verLeiTarifa"
																										title="Visualizar Arquivo" target="_blank">
																											<IMG
																											SRC="<bean:message key="caminho.imagens"/>PDF.gif"
																											width="35" height="35" BORDER="0" ALT="">
																									</a></td>

																									<td width="80%"><bean:write
																											property="descricaoLeiEstTarif"
																											name="ConsultarParametrosSistemaActionForm" />
																									</td>
																								</tr>
																							</logic:present>

																							<logic:present name="arquivoLeiNormaMedicao"
																								scope="request">
																								<tr bgcolor="#cbe5fe">
																									<td align="center" width="10%" valign="middle"><a
																										href="exibirConsultarParametrosSistemaAction.do?modo=verLeiNormaMedicao"
																										title="Visualizar Arquivo" target="_blank">
																											<IMG
																											SRC="<bean:message key="caminho.imagens"/>PDF.gif"
																											width="35" height="35" BORDER="0" ALT="">
																									</a></td>

																									<td width="80%"><bean:write
																											name="ConsultarParametrosSistemaActionForm"
																											property="descricaoLeiIndividualizacao" /></td>
																								</tr>
																							</logic:present>

																							<logic:present name="arquivoNormaCM"
																								scope="request">
																								<tr bgcolor="#cbe5fe">
																									<td align="center" width="10%" valign="middle"><a
																										href="exibirConsultarParametrosSistemaAction.do?modo=verNormaCM"
																										title="Visualizar Arquivo" target="_blank">
																											<IMG
																											SRC="<bean:message key="caminho.imagens"/>PDF.gif"
																											width="35" height="35" BORDER="0" ALT="">
																									</a></td>

																									<td width="80%"><bean:write
																											property="descricaoNormaCM"
																											name="ConsultarParametrosSistemaActionForm" />
																									</td>
																								</tr>
																							</logic:present>

																							<logic:present name="arquivoNormaCO"
																								scope="request">
																								<tr bgcolor="#cbe5fe">
																									<td align="center" width="10%" valign="middle"><a
																										href="exibirConsultarParametrosSistemaAction.do?modo=verNormaCO"
																										title="Visualizar Arquivo" target="_blank">
																											<IMG
																											SRC="<bean:message key="caminho.imagens"/>PDF.gif"
																											width="35" height="35" BORDER="0" ALT="">
																									</a></td>

																									<td width="80%"><bean:write
																											property="descricaoNormaCO"
																											name="ConsultarParametrosSistemaActionForm" />
																									</td>
																								</tr>
																							</logic:present>

																						</table>
																					</div></td>
																			</tr>

																		</table></td>
																</tr>
															</table></td>
													</tr>

												</table> <br /> <br /></td>
										</tr>
									</table>
								</div></td>
						</tr>
					</table></td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
