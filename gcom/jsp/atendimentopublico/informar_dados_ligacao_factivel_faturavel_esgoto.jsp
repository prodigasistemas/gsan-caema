<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirLigacaoEsgotoSituacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

function validarForm(){
	var form = document.forms[0];

	if ( form.idTipoSolicitacaoRA.value != null && form.idTipoSolicitacaoRA.value != '-1') {
		if ( form.idEspecificacao.value != null && form.idEspecificacao.value != '-1') {
			if ( form.dataLigacao.value != null && form.dataLigacao.value != '-1') {
				botaoAvancarTelaEspera('/gsan/informarRetirarSituacaoFactivelFaturavelEsgotoAction.do');
			} else {
				alert("O campo Data da Ligação é obrigatório");
			}
		} else {
			alert("O campo Especificação é obrigatório");
		}
	} else {
		alert("O campo Tipo Solicitação (RA) é obrigatório");
	}
		
}

	function validateInformarRetirarSituacaoFactivelFaturavelEsgotoActionForm(form) {                                                                   
		return true;
	} 

	function recarregarPagina() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir&pesquisarEspecificacao=sim');
		
	}

	function voltar() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?voltar=sim');
	}

	function limparDadosLigacao() {
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir&limpar=sim');
	}

	
</script>

</head>

<body leftmargin="5" topmargin="5" >
<div id="formDiv">
	<html:form action="/informarRetirarSituacaoFactivelFaturavelEsgotoAction.do"
		name="InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm"
		type="gcom.gui.atendimentopublico.InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm"
		method="post"
		onsubmit="return validateInformarRetirarSituacaoFactivelFaturavelEsgotoActionForm(this);">
	
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
			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Informar Dados da Ligação Factível Faturável </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para efetuar a ligação(ões)  do(s) imóvel(eis), informe os dados abaixo:</td>
				</tr>

				
				<tr bgcolor="#cbe5fe">
					<td>
					<table border="0" width="100%">
						
						<tr>
							<td width="42%" height="10">
								<strong>Quantidade de Imóveis Pesquisados:<font color="#FF0000">*</font> </strong>
							</td>
							<td colspan="2"><strong><b> 
								<html:text property="qtdTotalImoveis" readonly="true"
										   style="background-color:#EFEFEF; border:0;" 
										   size="10"
										   maxlength="10" /> </b></strong>
							</td>
						</tr>

						<tr>
							<td width="30%">
								<strong>Tipo Solicitação (RA):<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:select property="idTipoSolicitacaoRA" tabindex="4" onchange="recarregarPagina();">
									<logic:notEmpty name="colecaoSolicitacaoTipo">
										<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
										<html:options collection="colecaoSolicitacaoTipo"
													  labelProperty="descricao" 
													  property="id" 
													  />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td width="30%">
								<strong>Especificação:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:select property="idEspecificacao" tabindex="4" >
									<logic:notEmpty name="colecaoSolicitacaoTipoEspecificacao">
										<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
										<html:options collection="colecaoSolicitacaoTipoEspecificacao"
													  labelProperty="descricao" 
													  property="id" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
					
						<tr>
							<td width="30%">
								<strong>Data da Ligação:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:text property="dataLigacao" 
										size="11" 
										maxlength="10" 
										tabindex="3" 
										onkeyup="mascaraData(this, event)" 
										onkeypress="return isCampoNumerico(event);"/>
										<a href="javascript:abrirCalendario('InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm', 'dataLigacao');">
											<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="4"/></a>(dd/mm/aaaa)
							</td>
						</tr>
						
						<tr>
							<td height="24" colspan="2" bordercolor="#000000" class="style1"> 
						    	<hr>
						  	</td>
						</tr>
						
						<tr>
							<td>
								<strong> Diametro da Liga&ccedil;&atilde;o:<span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong>
							</td>
							<td colspan="2"><strong> <!--Campo Seleção Diametro de Ligação -->
								<html:select property="diametroLigacao" style="width: 230px;">
									<logic:present name="colecaoDiametroLigacao" scope="session">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoDiametroLigacao"
													  labelProperty="descricao" 
													  property="id" />
									</logic:present>
								</html:select> </strong>
							</td>
						</tr>
						<tr>
							<td class="style3"><strong>Material da Liga&ccedil;&atilde;o:<span class="style2"><strong>
								<font color="#FF0000">*</font></strong></span></strong>
							</td>
							<td colspan="2"><strong> <!-- Campo Material Ligação -->
								<html:select property="materialLigacao" style="width: 230px;">
									<logic:present name="colecaoMaterialLigacao" scope="session">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoMaterialLigacao"
													  labelProperty="descricao" 
													  property="id" />
									</logic:present>
								</html:select> </strong>
							</td>
						</tr>
						<tr>
							<td class="style3">
								<strong>Perfil da Liga&ccedil;&atilde;o:<span class="style2"><strong><strong><strong><font
								color="#FF0000">*</font></strong></strong></strong></span></strong>
							</td>
							<td colspan="2"><strong> 
								<html:select property="perfilLigacao"
											 onchange="redirecionarSubmit('exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?acao=concluir&ligacaoPerfil=sim');" 
											 style="width: 230px;" >
									<logic:present name="colecaoPerfilLigacao" scope="session">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoPerfilLigacao"
											 		  labelProperty="descricao" 
											 		  property="id" />
									</logic:present>
								</html:select> </strong>
							</td>
						</tr>
						<tr>
							<td class="style3">
								<b>Percentual de Coleta:<font	color="#ff0000">*</font></b>
							</td>

							<td colspan="2">
								<html:text property="percentualColeta"
										   onkeyup="formataValorMonetario(this, 6);"
										   readonly="true"
										   disabled="true"
										   style="background-color:#EFEFEF; border:0;" 
										   size="7" 
										   maxlength="6" /> %
							</td>
						</tr>

						<tr>
							<td class="style3"><b>Percentual de Esgoto:</b></td>

							<td colspan="2">
								<html:text property="percentualEsgoto"
										   size="7"
										   disabled="true"
										   readonly="true"
										   style="background-color:#EFEFEF; border:0;"  
										   maxlength="6" /> %
							</td>
						</tr>
						<tr>
							<td class="style3">
								<strong>Ligação Origem :</strong>
							</td>

							<td colspan="2">
								<html:select property="idLigacaoOrigem" style="width: 230px;">

								<logic:present name="colecaoLigacaoOrigem" scope="session">

									<html:option value="">&nbsp;</html:option>

									<html:options collection="colecaoLigacaoOrigem"
										labelProperty="descricao" property="id" />

								</logic:present>
							</html:select></td>
						</tr>
				
						<tr>
							<td><strong>Com Caixa de Gordura?<font color="#FF0000">*</font></strong></td>
							<td><strong> <html:radio property="indicadorCaixaGordura"
								value="1" /> SIM<html:radio
								property="indicadorCaixaGordura" value="2" /> NÃO</strong></td>

						</tr>

						<tr>
							<td>
								<strong>Ligação:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<strong> 
									<html:radio property="indicadorLigacao" value="2" /> Disponível
									<html:radio property="indicadorLigacao" value="1" /> Efetivado
								</strong>
							</td>
						</tr>

						<tr>
							<td class="style3"><strong>Condi&ccedil;&atilde;o do
							Esgotamento:</strong></td>

							<td colspan="2"><html:select
								property="condicaoEsgotamento" style="width: 230px;">

								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoLigacaoEsgotoEsgotamento">
									<html:options
										collection="colecaoLigacaoEsgotoEsgotamento"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></td>
						</tr>

						<tr>
							<td class="style3"><strong>Situa&ccedil;&atilde;o da
							Caixa de Inspe&ccedil;&atilde;o:</strong></td>

							<td colspan="2"><html:select
								property="situacaoCaixaInspecao" style="width: 230px;">

								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoSituacaoCaixaInspecao">
									<html:options collection="colecaoSituacaoCaixaInspecao"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></td>
						</tr>

						<tr>
							<td class="style3"><strong>Destino Dejetos:</strong></td>

							<td colspan="2"><html:select property="destinoDejetos"
								style="width: 230px;">

								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoDestinoDejetos">
									<html:options collection="colecaoDestinoDejetos"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></td>
						</tr>

						<tr>
							<td class="style3"><strong>Destino de &Aacute;guas
							Pluviais:</strong></td>

							<td colspan="2"><html:select
								property="destinoAguasPluviais" style="width: 230px;">

								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoDestinoAguasPluviais">
									<html:options collection="colecaoDestinoAguasPluviais"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></td>
						</tr>

					</table>
					</td>
				</tr>

				<table width="100%" border="0">
				<p>&nbsp;</p>
				<tr>
							<td colspan="2">
							Campos Obrigatórios
							<font color="#FF0000">*</font>
							</td>
						</tr>
					<tr>
						<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonCancelar" 
								class="bottonRightCol" 
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								
							<input type="button"
								name="ButtonVoltar" 
								class="bottonRightCol" 
								value="Voltar"
								onClick="javascript:voltar();">
									
							<input type="button"
								name="ButtonLimpar" 
								class="bottonRightCol" 
								value="Limpar"
								onClick="javascript:limparDadosLigacao();">
						</div>
						</td>
						<td valign="top">
						<div align="right">
							<input type="button"
								name="ButtonConcluir"
								align="right" 
								class="bottonRightCol" 
								value="Concluir"
								onClick="javascript:validarForm();">
						</div>
						</td>
					</tr>	
				</table>
				<p>&nbsp;</p>
			</table>

			<tr>
				<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
			</tr>
			</td>
		</tr>
	</table>

	</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
