<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<html:javascript staticJavascript="false"  formName="FiltrarAlteracaoInscricaoImovelActionForm"  />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">

function validarForm() {
  	var form = document.forms[0];
  	if ( form.indicadorSelecionarPor[0].checked && form.idLocalidade.value == ""){
		alert('Informe a Localidade');
	}else{	
		if ( form.setorComercialSelecionados != undefined && form.setorComercialSelecionados.value != null ){
			enviarSelectMultiplo('FiltrarAlteracaoInscricaoImovelActionForm','setorComercialSelecionados');
		}

		if ( form.quadraSelecionados != undefined && form.quadraSelecionados.value != null ) {
			enviarSelectMultiplo('FiltrarAlteracaoInscricaoImovelActionForm','quadraSelecionados');
		}
  	  	
	  	form.action = "/gsan/filtrarAlteracaoInscricaoImovelAction.do";
		submeterFormPadrao(form);   
	}		  
}
  
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.FiltrarAlteracaoInscricaoImovelActionForm;
	
	if (tipoConsulta == 'localidade') {
      form.idLocalidade.value = codigoRegistro;
	  form.desLocalidade.value = descricaoRegistro;
	  form.desLocalidade.style.color = "#000000";
	 
	}

}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.FiltrarAlteracaoInscricaoImovelActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.codigoSetorComercial.value = codigoRegistro;
      form.idsSetorComercial.value = idRegistro;
	  form.desSetorComercial.value = descricaoRegistro;
	  form.desSetorComercial.style.color = "#000000";
	  
	  
	}

}

function limparBorracha(){
	var form = document.forms[0];	
			
	if( form.indicadorSelecionarPor[0].checked ){	    
		form.idLocalidade.value = "";
		form.desLocalidade.value = "";
		
		var form = document.forms[0];
		
		form.action='exibirFiltrarAlteracaoInscricaoImovelAction.do?limparCampos=S';
		
		form.submit();
	}
}

function habilitarPesquisaLocalidade(form) {
	if( form.indicadorSelecionarPor[0].checked ){
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idLocalidade.value);
	}
}
	

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}

	function carregarDependetes(tipo) {
		var form = document.forms[0];

		 enviarSelectMultiplo('FiltrarAlteracaoInscricaoImovelActionForm','setorComercialSelecionados');
		 
		 if(tipo == 'selecionar'){
		 	form.action='exibirFiltrarAlteracaoInscricaoImovelAction.do';
		 }else if(tipo == 'remover'){
		  form.action='exibirFiltrarAlteracaoInscricaoImovelAction.do?removerSetor=Sim';
		 }
		 
		 form.submit();
	}
	
	function habilitarCamposPesquisa(){
		var form = document.forms[0];
	
		if( form.indicadorSelecionarPor[0].checked ){
			document.getElementById('matriculaImovel1').value = "";
			document.getElementById('matriculaImovel2').value = "";
			document.getElementById('matriculaImovel3').value = "";
			document.getElementById('matriculaImovel4').value = "";
			document.getElementById('matriculaImovel5').value = "";
			document.getElementById('matriculaImovel6').value = "";
			document.getElementById('matriculaImovel7').value = "";
			document.getElementById('matriculaImovel8').value = "";
			document.getElementById('matriculaImovel9').value = "";
			document.getElementById('matriculaImovel10').value = "";
			document.getElementById('matriculaImovel11').value = "";
			document.getElementById('matriculaImovel12').value = "";
			
			document.getElementById('matriculaImovel1').disabled = true;
			document.getElementById('matriculaImovel2').disabled = true;
			document.getElementById('matriculaImovel3').disabled = true;
			document.getElementById('matriculaImovel4').disabled = true;
			document.getElementById('matriculaImovel5').disabled = true;
			document.getElementById('matriculaImovel6').disabled = true;
			document.getElementById('matriculaImovel7').disabled = true;
			document.getElementById('matriculaImovel8').disabled = true;
			document.getElementById('matriculaImovel9').disabled = true;
			document.getElementById('matriculaImovel10').disabled = true;
			document.getElementById('matriculaImovel11').disabled = true;
			document.getElementById('matriculaImovel12').disabled = true;

			document.getElementById('idLocalidade').disabled = false;
			document.getElementById('setorComercial').disabled = false;
			document.getElementById('setorComercialSelecionados').disabled = false;
			document.getElementById('quadra').disabled = false;
			document.getElementById('quadraSelecionados').disabled = false;
			document.getElementById('indicadorOrigemAtualizacao1').disabled = false;
			document.getElementById('indicadorOrigemAtualizacao2').disabled = false;
			document.getElementById('indicadorOrigemAtualizacao3').disabled = false;
			
		}else{
			document.getElementById('matriculaImovel1').disabled = false;
			document.getElementById('matriculaImovel2').disabled = false;
			document.getElementById('matriculaImovel3').disabled = false;
			document.getElementById('matriculaImovel4').disabled = false;
			document.getElementById('matriculaImovel5').disabled = false;
			document.getElementById('matriculaImovel6').disabled = false;
			document.getElementById('matriculaImovel7').disabled = false;
			document.getElementById('matriculaImovel8').disabled = false;
			document.getElementById('matriculaImovel9').disabled = false;
			document.getElementById('matriculaImovel10').disabled = false;
			document.getElementById('matriculaImovel11').disabled = false;
			document.getElementById('matriculaImovel12').disabled = false;
			
			form.idLocalidade.value = "";
			form.desLocalidade.value = "";					
			document.getElementById('setorComercial').options.length = 0;
			document.getElementById('setorComercialSelecionados').options.length = 0;
			document.getElementById('quadra').options.length = 0;
			document.getElementById('quadraSelecionados').options.length = 0;
			document.getElementById('indicadorOrigemAtualizacao3').checked = false;
			document.getElementById('indicadorOrigemAtualizacao3').checked = false;
			document.getElementById('indicadorOrigemAtualizacao3').checked = true;
			
			document.getElementById('idLocalidade').disabled = true;
			document.getElementById('setorComercial').disabled = true;
			document.getElementById('setorComercialSelecionados').disabled = true;
			document.getElementById('quadra').disabled = true;
			document.getElementById('quadraSelecionados').disabled = true;
			document.getElementById('indicadorOrigemAtualizacao1').disabled = true;
			document.getElementById('indicadorOrigemAtualizacao2').disabled = true;
			document.getElementById('indicadorOrigemAtualizacao3').disabled = true;
		}
	}
		 
</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitarCamposPesquisa();" >

	<html:form action="/filtrarAlteracaoInscricaoImovelAction"
		name="FiltrarAlteracaoInscricaoImovelActionForm"
		type="gcom.gui.cadastro.FiltrarAlteracaoInscricaoImovelActionForm"
		method="post"
		onsubmit="return validateFiltrarAlteracaoInscricaoImovelActionForm(this);">
		
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
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
							<td class="parabg">Filtrar Imóvel(eis) excluído(s) ou com a Inscrição(ões) alterada(s)</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						</tr>
					</table>
					
					<p>&nbsp;</p>
		
					<table width="100%" border="0">
		
						<tr>
							<td width="100%" colspan="3">
							<table width="100%">
								<tr>
									<td width="80%">Para filtrar o(s) imóvel(s), informe os dados abaixo:</td>
									<td align="right">
									</td>
									<logic:present scope="application" name="urlHelp">
										<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroGeograficoMunicipioFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
									</logic:present>
									<logic:notPresent scope="application" name="urlHelp">
										<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
									</logic:notPresent>
								</tr>
							</table>
							</td>
						</tr>
					</table>
		
					<table width="100%" border="0">
						<tr>
							<td>
								<strong>Selecionar Por:<font color="#FF0000">*</font></strong>
							</td>
							<td><html:radio property="indicadorSelecionarPor"  value="1" onchange="habilitarCamposPesquisa()" /><strong>Localização Geográfica</strong>
							<html:radio property="indicadorSelecionarPor"  value="2" onchange="habilitarCamposPesquisa()" /><strong>Matrículas</strong>
							</td>
						</tr>
					
						<tr>
							<td width="30%"><strong>Localidade:</strong></td>
								<td>
									<html:text styleId="idLocalidade" tabindex="8" maxlength="3" property="idLocalidade" size="5"
									onkeypress="validaEnter(event, 'exibirFiltrarAlteracaoInscricaoImovelAction.do?objetoConsulta=1', 'idLocalidade');return isCampoNumerico(event);"
									/>
									
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);">
									<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
									
									<logic:present name="corLocalidadeOrigem">
										<logic:equal name="corLocalidadeOrigem" value="exception">
											<html:text styleId="desLocalidade" property="desLocalidade" size="35" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
				
										<logic:notEqual name="corLocalidadeOrigem" value="exception">
											<html:text styleId="desLocalidade" property="desLocalidade" size="35" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present>
									
									<logic:notPresent name="corLocalidadeOrigem">
										<logic:empty name="FiltrarAlteracaoInscricaoImovelActionForm"
											property="idLocalidade">
											<html:text styleId="desLocalidade" property="desLocalidade" value="" size="35" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
		
										<logic:notEmpty name="FiltrarAlteracaoInscricaoImovelActionForm"
											property="idLocalidade">
											<html:text styleId="desLocalidade" property="desLocalidade" size="35" readonly="true"
												style="background-color:#EFEFEF; border:0; color: 	#000000" />
										</logic:notEmpty>
									</logic:notPresent>
									
									<a href="javascript:limparBorracha();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
								</td>
							</tr>
							<tr>
								<td width="120">
									<strong>Setor Comercial:</strong>
								</td>
								<td colspan="2">
								<table width="120" border=0 cellpadding=0 cellspacing=0 align="left" >
									<tr>
										<td>
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercial, 6);" >
											
												<html:select styleId="setorComercial" property="setorComercial" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoSetorComercial" scope="session">
														<html:options collection="colecaoSetorComercial" 
															labelProperty="codigo" 
															property="id"/>
													</logic:present>
												</html:select>
												
											</div>
										</td>
			
										<td width="100%" valign="middle"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarAlteracaoInscricaoImovelActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes('selecionar');"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarAlteracaoInscricaoImovelActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes('selecionar');"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarAlteracaoInscricaoImovelActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes('remover');"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
												
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAlteracaoInscricaoImovelActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes('remover');"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>
			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											<div id='selecionados' style="OVERFLOW: auto; HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialSelecionados, 6);" >
											
												<html:select styleId="setorComercialSelecionados" property="setorComercialSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
												<logic:present name="colecaoSetorComercialSelecionados" scope="session">
														<html:options collection="colecaoSetorComercialSelecionados" 
															labelProperty="codigo" 
															property="id"/>
													</logic:present>
												</html:select>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td width="120">
									<strong>Quadra:</strong>
								</td>
								<td colspan="2">
								<table width="120" border=0 cellpadding=0 cellspacing=0 align="left">
									<tr>
										<td>
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadra, 6);" >
												<html:select  styleId="quadra" property="quadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoQuadra" scope="request">
														<html:options collection="colecaoQuadra" 
															labelProperty="setorQuadraFormatado" 
															property="id"/>
													</logic:present>
												</html:select>
											</div>
										</td>
			
										<td width="5" valign="middle"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarAlteracaoInscricaoImovelActionForm', 'quadra', 'quadraSelecionados');"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarAlteracaoInscricaoImovelActionForm', 'quadra', 'quadraSelecionados');"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarAlteracaoInscricaoImovelActionForm', 'quadra', 'quadraSelecionados');"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAlteracaoInscricaoImovelActionForm', 'quadra', 'quadraSelecionados');"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											<div id='selecionadas' style="OVERFLOW: auto;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadraSelecionados, 6);" >
											
											
												<html:select styleId="quadraSelecionados" property="quadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionadas'), 6);">
													<logic:present name="colecaoQuadraSelecionadas" scope="request">
														<html:options collection="colecaoQuadraSelecionadas" 
															labelProperty="setorQuadraFormatado" 
															property="id"/>
													</logic:present>
												</html:select>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Seleção dos Imóveis:</strong>
								</td>
								<td><html:radio  property="indicadorImovelAlteradoExcluido"  value="2" /><strong>Alterados</strong>
								<html:radio property="indicadorImovelAlteradoExcluido"  value="1" /><strong>Excluídos</strong>
								<html:radio property="indicadorImovelAlteradoExcluido"  value="" /><strong>Todos</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Origem da atualização:</strong>
								</td>
								<td><html:radio styleId="indicadorOrigemAtualizacao1" property="indicadorOrigemAtualizacao"  value="1" /><strong>Gsan</strong>
								<html:radio styleId="indicadorOrigemAtualizacao2" property="indicadorOrigemAtualizacao"  value="2" /><strong>Ressetorização</strong>
								<html:radio styleId="indicadorOrigemAtualizacao3" property="indicadorOrigemAtualizacao"  value="" /><strong>Todos</strong>
								</td>
							</tr>
							<tr>
								<td>
									<strong>Matrículas:</strong>
								</td>
								<td>
									<html:text 
										styleId="matriculaImovel1"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel1" 
										size="10"
										disabled="true" 
										onkeypress="return isCampoNumerico(event);"/>
									<html:text 
										styleId="matriculaImovel2"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel2" 
										size="10" 
										disabled="true" 
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel3"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel3" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel4"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel4" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text
										styleId="matriculaImovel5" 
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel5" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel6"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel6" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text
										styleId="matriculaImovel7"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel7" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text
										styleId="matriculaImovel8" 
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel8" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel9"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel9" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel10"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel10" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel11"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel11" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
									<html:text 
										styleId="matriculaImovel12"
										maxlength="9" 
										tabindex="1"
										property="matriculaImovel12" 
										size="10" 
										disabled="true"
										onkeypress="return isCampoNumerico(event);" />
								</td>
							</tr>
								
							<tr>
								<td>
									<input name="Button" 
										type="button" 
										class="bottonRightCol"
										value="Limpar" 
										align="left"
										onclick="window.location.href='/gsan/exibirFiltrarAlteracaoInscricaoImovelAction.do?menu=sim'">
										<input name="Submit23" 
										class="bottonRightCol" 
										value="Cancelar" 
										align="right"
										type="button" tabindex="15" 
										onclick="window.location.href='/gsan/telaPrincipal.do'"> 
								</td>
								<td align="right">
									<input type="button"
										onclick="validarForm(document.forms[0]);" 
										class="bottonRightCol"
										value="Filtrar" 
										tabindex="12" 
										style="width: 70px;">
								</td>
							</tr>
		
					</table>
				<p>&nbsp;</p>
				</td>
			</tr>
		</table>
	<%@ include file="/jsp/util/rodape.jsp"%>	
	</html:form>
</body>
</html:html>