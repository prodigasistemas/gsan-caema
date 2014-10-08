<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/telaespera.jsp"%>
	
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarComandosContasCobrancaEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function proximoSorteio() {

		var form = document.forms[0];
		 	
		form.action = 'exibirEfetuarSorteioPremiosAction.do';
		submitForm(form);

		
	 }

	 function sortearPremio() {
		var form = document.forms[0];
		 	
		form.action = 'efetuarSorteioPremiosAction.do';
		submitForm(form);
	 }
	 
	 function gerarArquivoTxt(){
		var form = document.forms[0];
		
		form.action = 'gerarRelatorioSorteioPremiosArquivoTextoAction.do';

		submitForm(form);

		

	}

	 function voltarTela() {
		 var form = document.forms[0];
	 	
		form.action = 'exibirEfetuarSorteioPremiosAction.do?menu=sim';
		submeterFormPadrao(form);

		
	 }


</script>

</head>

<body leftmargin="5" topmargin="5">

<div id="formDiv">
<html:form action="/exibirEfetuarSorteioPremiosAction"
	name="EfetuarSorteioPremiosActionForm"
	type="gcom.gui.atendimentopublico.EfetuarSorteioPremiosActionForm"
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
			
			<html:hidden property="idPremio"/>
			<html:hidden property="quantidadePremio"/>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Efetuar Sorteio Prêmios</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr align="right">
					<td colspan="3">
						<font size="4">
							<bean:write name="EfetuarSorteioPremiosActionForm" property="dataAtual" />
						</font>
					</td>
				</tr>
				
				<logic:present name="selecionarSorteio">
			        <tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					
					<tr>
						<td width="40%" align="center" colspan="3">
							<font size="3">
								<strong>
									Tipo do Sorteio:
								</strong>&nbsp;
								<html:select property="idSorteio">
									<html:options collection="colecaoSorteios"
										labelProperty="descricao" property="id" />
								</html:select>
							</font>
						</td>
			        </tr>
			        <tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
			        <tr>
			            <td colspan="1">
							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td colspan="2" align="right">
							<input type="button" name="efetuarSorteio" id="botaoEfetuarSorteio"
								class="bottonRightCol" value="Efetuar Sorteio"
								onClick="javascript:proximoSorteio();" />
			            </td>
					</tr>
				</logic:present>
				
				<logic:present name="efetuarSorteio">
			        <tr align="center">
			          <td width="26%" colspan="3">
			          	<font size="6">
				          	<strong>
				          		<bean:write name="EfetuarSorteioPremiosActionForm" property="ordemPremio" />º Prêmio:
				          		<bean:write name="EfetuarSorteioPremiosActionForm" property="descricaoPremio" />
			          		</strong>
		          		</font>
			          </td>
			        </tr>
			        <tr align="center">
			          <td width="26%" colspan="3">
			          	<font size="5">
				          		<bean:write name="EfetuarSorteioPremiosActionForm" property="descricaoSorteio" />
		          		</font>
			          </td>
			        </tr>
			        
					<logic:notPresent name="colecaoImovelSorteadoHelper">
						<tr>
				            <td width="74%" align="right" colspan="3">
								<input type="button" name="sortear"
									class="bottonRightCol" value="Sortear"
									onClick="javascript:sortearPremio();" />
				            </td>
						</tr>
					</logic:notPresent>
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					
					<tr>
						<td width="35%"></td>
						<td width="30%" align="center" bgcolor="#87CEFA" height="50">
							<font size="4">
								<strong>
									SORTEIO
								</strong>
							</font>
						</td>
						<td width="35%"></td>
					</tr>
					
					<logic:present name="colecaoImovelSorteadoHelper">
						<logic:iterate name="colecaoImovelSorteadoHelper"
							id="imovelSorteadoHelper">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
							
							<tr align="center">
								<td></td>
								<td>
									<table style="border: 3px solid #FFFFFF;">
										<tr align="center">
											<td>
						          				<font size="3">
													<strong>
														<bean:write name="imovelSorteadoHelper" property="numeroSorteado" />
													</strong>
												</font>
											</td>
										</tr>
									</table>
								</td>
								<td></td>
							</tr>
							
							<tr align="center">
								<td width="30%" align="right">
									Matrícula:
								</td>
								<td colspan="2" align="left">
									<bean:write name="imovelSorteadoHelper" property="matricula" />
								</td>
							</tr>
							<tr align="center">
								<td width="30%" align="right">
									Gerência Regional:
								</td>
								<td colspan="2" align="left">
									<bean:write name="imovelSorteadoHelper" property="gerenciaRegional" />
								</td>
							</tr>
							<tr align="center">
								<td width="30%" align="right">
									Localidade:
								</td>
								<td colspan="2" align="left">
									<bean:write name="imovelSorteadoHelper" property="localidade" />
								</td>
							</tr>
							<tr align="center">
								<td width="30%" align="right">
									Cliente:
								</td>
								<td colspan="2" align="left">
									<strong>
										<bean:write name="imovelSorteadoHelper" property="nomeClienteUsuario" />
									</strong>
								</td>
							</tr>
							<logic:notEmpty name="imovelSorteadoHelper" property="cpfClienteUsuario" >
								<tr align="center">
									<td width="30%" align="right">
										CPF:
									</td>
									<td colspan="2" align="left">
										<bean:write name="imovelSorteadoHelper" property="cpfClienteUsuario" />
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="imovelSorteadoHelper" property="cpfCnpjCliente" >
								<tr align="center">
									<td width="30%" align="right">
										CPF/CNPJ:
									</td>
									<td colspan="2" align="left">
										<bean:write name="imovelSorteadoHelper" property="cpfCnpjCliente" />
									</td>
								</tr>
							</logic:notEmpty>
							<tr align="center">
								<td width="30%" align="right">
									Endereço:
								</td>
								<td colspan="2" align="left">
									<bean:write name="imovelSorteadoHelper" property="endereco" />
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
	
					<logic:present name="colecaoImovelSorteadoHelper">
						<tr>
							<td align="left" colspan="1">
								<input type="button" name="voltar"
									class="bottonRightCol" value="Voltar"
									onClick="javascript:voltarTela();" />
				            </td>
							<td align="right" colspan="2">
								<input type="button" name="confirmar" 
									class="bottonRightCol" value="Próximo Sorteio"
									onClick="javascript:proximoSorteio();" />
							</td>
						</tr>
					</logic:present>
	
					<logic:notPresent name="colecaoImovelSorteadoHelper">
						<tr>
							<td align="left" colspan="1">
								<input type="button" name="voltar"
									class="bottonRightCol" value="Voltar"
									onClick="javascript:voltarTela();" />
				            </td>
							<td align="right" colspan="2">
								<input type="button" name="confirmar" disabled="disabled"
									class="bottonRightCol" value="Próximo Sorteio"
									onClick="" />
							</td>
						</tr>
					</logic:notPresent>
				</logic:present>
				
				
				<logic:present name="sorteioFinalizado">
				
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td width="30%"></td>
						<td width="40%" align="center" bgcolor="#87CEFA">
							<font size="5">
								<strong>
									SORTEIO FINALIZADO
								</strong>
							</font>
						</td>
						<td width="30%"></td>
			        </tr>
			        
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					
					<tr>
						<td width="40%" align="center" colspan="3">
							<font size="4">
								<strong>
									Data do Sorteio:
								</strong>
								&nbsp;<bean:write name="EfetuarSorteioPremiosActionForm" property="dataSorteio" />
							</font>
						</td>
			        </tr>
			        
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					
					<tr>
						<td colspan="3">
						&nbsp;
						</td>
					</tr>
					
					<logic:present name="colecaoNumeroPremioSorteio" scope="request">
					
						<tr>
						  <td colspan="3" align="center">
							<strong>
								Número Sorteio
							</strong>
							<html:select property="idNumeroPremioSorteio">
								<html:option value=""></html:option>
								<html:options
									collection="colecaoNumeroPremioSorteio"
									property="id"
									labelProperty="id"
								/>
							</html:select>
						</td>
					</tr>
					
					</logic:present>
					
			        <tr>
						<td align="left">
							<input type="button" name="gerarRelatorio" 
								class="bottonRightCol" value="Gerar Relatório"
								onClick="javascript:toggleBox('demodiv',1);" />
						</td>
						<td></td>
						<td align="right">
							<input type="button" name="gerarArquivoTexto" 
								class="bottonRightCol" value="Gerar Arquivo TXT"
								onClick="gerarArquivoTxt();" />
						</td>
					</tr>
				</logic:present>
				
				</table>

			</table>
			<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioSorteioPremiosAction.do"/>
			<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
	</div>
	
</body>
</html:html>