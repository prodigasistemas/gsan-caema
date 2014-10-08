<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarAnormalidadeLeituraActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(form){
				
		if(testarCampoValorZero(document.AtualizarAnormalidadeLeituraActionForm.descricao, 'Descrição')  && 
			testarCampoValorZero(document.AtualizarAnormalidadeLeituraActionForm.abreviatura, 'Descrição Abreviada')) {
			
			if(validateAtualizarAnormalidadeLeituraActionForm(form)){

				if(form.numeroFatorSemLeitura.value>=10 || form.numeroFatorComLeitura.value>=10){
					alert("Valor do Fator dever ser menor 10(dez).")
				}else{
					if(validarOSSeletiva()){
						//submeterFormPadrao(form);
   						submitForm(form);
   					}
				}			
			}
			
		}
	}
	
	function validarOSSeletiva(){
		var form = document.forms[0];
		
		if(form.tipoServico.value != "-1" && form.empresa.value == ""){
			alert("Informe Empresa.");
			return false;
		}else if(form.tipoServico.value == "-1" && form.empresa.value != ""){
			alert("Informe Tipo de Serviço da OS");
			return false;
		}else{
			return true;
		}
	}	

	function limparForm() {

		var form = document.AtualizarAnormalidadeLeituraActionForm;

		form.descricao.value = "";
		form.abreviatura.value = "";
	    form.indicadorRelativoHidrometro.value = "";
	    form.indicadorImovelSemHidrometro.value = "";
		form.usoRestritoSistema.value = "";
	    form.perdaTarifaSocial.value = "";
	    form.tipoServico.value = "";
	    form.consumoLeituraNaoInformado.value = "";
	    form.consumoLeituraInformado.value = "";
		form.leituraLeituraNaoturaInformado.value = "";
	    form.leituraLeituraInformado.value = "";
	    form.tipoSolicitacao.value = "";
	    form.especificacao.value = "";
	    form.numeroFatorSemLeitura.value = ""; 
	    form.numeroFatorComLeitura.value = "";
	    form.empresa = "";
			
	}
	
	function reload() {
		var form = document.AtualizarAnormalidadeLeituraActionForm;
		form.action = "/gsan/exibirAtualizarAnormalidadeLeituraAction.do";
		form.submit();
	} 

	function redirecionarSubmitAtualizar(idLocalidade) {
		urlRedirect = "/gsan/exibirAtualizarAnormalidadeLeituraAction.do?idRegistroInseridoAtualizar=" + idLocalidade;
		redirecionarSubmit(urlRedirect);
	}
	
	function carregarEspecificacao(){
		
		var form = document.forms[0];
		
		if (form.tipoSolicitacao.value > 0){
			redirecionarSubmit('exibirAtualizarAnormalidadeLeituraAction.do?pesquisarEspecificacao=OK');
		}
	}
	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" >
	
<div id="formDiv">

<html:form action="/atualizarAnormalidadeLeituraAction" 
	name="AtualizarAnormalidadeLeituraActionForm"
	type="gcom.gui.micromedicao.leitura.AtualizarAnormalidadeLeituraActionForm" method="post">

	<INPUT TYPE="hidden" name="removerEndereco">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">
	<html:hidden property="idAnormalidadeAtualizar" />


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

			<td width="600" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Anormalidade de Leitura</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma Anormalidade de Leitura, informe
					os dados abaixo:</td>
					<!-- Descricao -->
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="26" maxlength="25" /> </span></td>
				</tr>

				<!-- Abreviatura -->

				<tr>
					<td><strong>Abreviatura:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="abreviatura" size="5" maxlength="5" /> </span></td>
				</tr>


				<tr>
					<td><strong>Anormalidade Relativa a Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorRelativoHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorRelativoHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Aceita para Ligação sem Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorImovelSemHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorImovelSemHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade de Uso Restrito do Sistema:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="usoRestritoSistema" value="1" />
					<strong>Sim <html:radio property="usoRestritoSistema" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				
				<tr>
					<td><strong>Anormalidade Acarreta Perda Tarifa Social:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="perdaTarifaSocial" value="1" />
					<strong>Sim <html:radio property="perdaTarifaSocial" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Exibir a anormalidade no relat&oacute;rio de leituras 
					e anormalidades informadas:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorExibirAnormalidadeRelatorio" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorExibirAnormalidadeRelatorio" 
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr>
			
				<tr>
					<td><strong>Exibir mensagem quando o hidr&ocirc;metro estiver na cal&ccedil;ada:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorExibirMensagemHidrometrosCalcada" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorExibirMensagemHidrometrosCalcada"
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr>
				<tr>
					<td><strong>Exibir mensagem quando hidr&ocirc;metro foi substitu&iacute;do:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorExibirMensagemHidrometrosSubstituidos" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorExibirMensagemHidrometrosSubstituidos"
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador Não Impressão Conta:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorNaoImprimirConta" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorNaoImprimirConta"
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr>

				<!--Consumo a Ser Cobrado (anormalidade informada e leitura não informada)-->
				
				<tr>
					<td width="40%"><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					não informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraNaoInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
			
				<tr>
					<td><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura não informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraNaoturaInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>
			
				<tr>
					<td><strong>Fator que deverá atualizar o consumo de imóveis com anormalidade (Sem leitura):<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroFatorSemLeitura" size="5" maxlength="4" onkeyup="javascript:formataValorMonetario(this,5);" onchange="javascript:formataValorMonetario(this,3);" onchange="javascript:formataValorMonetario(this,3);"/> </span></td>
				</tr>
			
				<tr>
					<td><strong>Fator que deverá atualizar o consumo de imóveis com anormalidade (Com leitura):<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroFatorComLeitura" size="5" maxlength="4" onkeyup="javascript:formataValorMonetario(this,5);" onchange="javascript:formataValorMonetario(this,3);"/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de obrigatoriedade da anormalidade de leitura:
						<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="indicadorLeitura">
						<html:option value="0">Leitura Opcional</html:option>
						<html:option value="1">Leitura Obrigatória</html:option>
						<html:option value="2">Não aceita Leitura</html:option>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Anormalidade impacta na leitura/faturamento:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorAnormalidadeImpactaLeitura" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorAnormalidadeImpactaLeitura"
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Número de vezes para suspender leitura:</strong>
					</td>
					<td colspan="2" align="left">
						<html:text property="numeroVezesSuspendeLeitura" size="5" maxlength="2" onkeyup="javascript:verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
			
				<tr>
					<td>
						<strong>Número de meses para manter leitura suspensa:</strong>
					</td>
					<td colspan="2" align="left">
						<html:text property="numeroMesesLeituraSuspensa" size="5" maxlength="2" onkeyup="javascript:verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
			        <td HEIGHT="30"><strong>Tipo de Solicitação:</strong></td>
			        <td>
			        	<logic:present name="generalizada">
							<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;" tabindex="10" disabled="true">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id"/>
							</html:select>		
						</logic:present>
					
						<logic:notPresent name="generalizada">
							<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;" tabindex="10" onchange="carregarEspecificacao()">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id"/>
							</html:select>
						</logic:notPresent>
					</td>
			    </tr>
			    
			    <tr>
			      <td HEIGHT="30"><strong>Especificação:</strong></td>
			        <td>
						<logic:present name="generalizada">
							<html:select property="especificacao" style="width: 350px;font-size:11px;" tabindex="11" disabled="true">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoSolicitacaoTipoEspecificacao">
									<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
								</logic:present>
							</html:select>
						</logic:present>
						
						<logic:notPresent name="generalizada">
							<html:select property="especificacao" style="width: 350px;font-size:11px;" tabindex="11" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoSolicitacaoTipoEspecificacao">
									<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
								</logic:present>
							</html:select>
						</logic:notPresent>
					</td>
			     </tr>
			     
			     <tr>
			     	<td colspan="3" ><hr></td>
			     </tr>
			      
			     <tr>
			     	<td colspan="2">Para criação automática de Ordem Seletiva, informe:</td>
			     </tr>
			     
			     <!-- Tipo de Serviço -->
				 
				 <tr>
					<td><strong>Tipo de Serviço:</strong></td>
					<td colspan="2" align="left"><html:select property="tipoServico">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				 </tr>
			      
			     <tr>
					<td width="30%"><strong>Firma:</strong></td>
					<td colspan="2">
						<html:select property="empresa" style="width: 230px;" tabindex="1" >
						<logic:present name="colecaoEmpresa" scope="session">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				 </tr>
				  
				 <tr>
			     	<td colspan="3" ><hr></td>
			     </tr>
			     
			     <tr>
					<td><strong>Indicador foto obrigat&oacute;ria:<font color="#FF0000">*</font></strong></td>
					<td>						
						<html:radio property="indicadorFotoObrigatoria" 
							value="<%=ConstantesSistema.SIM.toString()%>"/>
						<strong>Sim</strong>
						<html:radio	property="indicadorFotoObrigatoria"
							value="<%=ConstantesSistema.NAO.toString()%>"/>
						<strong>N&atilde;o </strong>
					</td>
				</tr> 
			  
				<tr>
					<td><strong>Indicador de uso:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:radio property="indicadorUso" value="1" tabindex="5"/><strong>Ativo
							<html:radio property="indicadorUso" value="2" tabindex="6"/>Inativo</strong> 
						</td>
				</tr>	
				
				<tr>
						<td align="center"><strong> <font color="#FF0000"></font></strong></td>
						<td align="center">
						<div align="center"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
					</td>
				</tr>
			</table>
			
			<table>	
				<tr>
					<td colspan="2"><font color="#FF0000"> 
					<logic:present name="manter"
						scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterAnormalidadeLeituraAction.do?'">
					</logic:present>
					<logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarAnormalidadeLeituraAction.do?menu=sim'">
					</logic:notPresent>
					<input type="button" 
						name="ButtonDesfazer"  class="bottonRightCol" value="Desfazer" align="left" 
						onclick="window.location.href='<html:rewrite page="/exibirAtualizarAnormalidadeLeituraAction.do?desfazer=S"/>'">
					<input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
				
		
					<td width="64%" colspan="2" align="right">
					  <gsan:controleAcessoBotao name="Button" value="Atualizar" onclick="javascript:validarForm(document.forms[0]);" url="atualizarAnormalidadeLeituraAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
	<script>

setCookie("desativaHistoryBack", "true", "1");

</script>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
