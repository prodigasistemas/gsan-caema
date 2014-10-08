<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<!--================================= SCRIPTS =============================================================-->
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script type="text/javascript"
	src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script type="text/javascript"
	src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarOrdemServicoConexaoEsgotoActionForm"
	dynamicJavascript="false" />
<!--=======================================================================================================-->

<script language="JavaScript">
	/*
	* Autor: Jonathan Marcos
	* Data: 05/09/2013
	*/
	function validarComEnterLocalidadeFinal(event){
		var form = document.forms[0];
		if(form.idLocalidadeInicial.value!=''){
			return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLocalidadeFinal', 'idLocalidadeFinal');
		}else{
			alert("Informe a Localidade Inicial");
			form.idLocalidadeFinal.value = '';
		}

	}
	/*
	* Autor: Jonathan Marcos
	* Data: 05/09/2013
	*/
	function limparImovel(){
		var formulario = document.forms[0];
		formulario.descricaoImovel.value = "";
	}
	
	function limparMunicipioTecla(){

		var form = document.forms[0];
		form.descricaoMunicipio.valeu = "";

		}


	function limparLogradouroTecla(){

		var form = document.forms[0];
		form.descricaoLogradouro.valeu = "";

		}

	function limparLocalidadeOrigemTecla() {
		var form = document.forms[0];
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeInicial.value = "";
		form.descricaoLocalidadeFinal.value = "";
		
		
	}



	function limparLocalidadeDestinoTecla() {
		var form = document.forms[0];
		
		form.descricaoLocalidadeFinal.value = "";
		limparSetorComercialOrigemTecla();
		
	}



	function limparSetorComercialOrigemTecla() {
		var form = document.forms[0];
		form.codSetorComercialInicial.valeu = "";
		form.codSetorComercialFinal.valeu = "";
		form.descricaoSetorComercialInicial.value = "";
		form.descricaoSetorComercialFinal.value = "";
	}



	function limparSetorComericalDestinoTecla() {
		var form = document.forms[0];
		form.descricaoSetorComercialFinal.value = "";
	}
	
	function limparQuadraDestinoTecla() {
		var form = document.forms[0];
		form.quadraFinal.value = "";
	}
	

	function validarLocalidade(){
		var form = document.forms[0];
		if(form.idLocalidadeInicial.readOnly == false ){
			
				
				if(form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != '' && form.idLocalidadeInicial.disabled == false 
					&& (form.idLocalidadeFinal.value == null || form.idLocalidadeFinal.value == '')){
						
					alert('Informe Localidade Final.');	
					return false;	
						
				}else if ((form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != '' && 
							form.idLocalidadeFinal.disabled == false) && (form.idLocalidadeInicial.value == null || 
									form.idLocalidadeInicial.value == '')){		
					
					alert('Informe Localidade Inicial.');	
					return false;
					
				}else{
					return true;
				}
				
			}else{
					return true;
				}
	}
	
	function validarSetorComercial(){
		var form = document.forms[0];
	
		if(form.codSetorComercialInicial.readOnly == false){
			
			
				if(form.codSetorComercialInicial.value != null && form.codSetorComercialInicial.value != '' 
						&& (form.codSetorComercialFinal.value == null || form.codSetorComercialFinal.value == '')){
						
					alert('Informe Setor Comercial Final.');
					return false;		
						
				}else if (form.codSetorComercialFinal.value != null && form.codSetorComercialFinal.value != '' 
						&& (form.codSetorComercialInicial.value == null || form.codSetorComercialInicial.value == '')){		
					
					alert('Informe Setor Comercial Inicial.');	
					return false;
					
				}else{
					return true;
				}
			}else{
					return true;
				}
		
	}


	function validarQuadra(){
		var form = document.forms[0];
	
		if(form.quadraInicial.readOnly == false){
			
			
				if(form.quadraInicial.value != null && form.quadraInicial.value != '' 
						&& (form.quadraFinal.value == null || form.quadraFinal.value == '')){
						
					alert('Informe Quadra Final.');
					return false;		
						
				}else if (form.quadraFinal.value != null && form.quadraFinal.value != '' 
						&& (form.quadraInicial.value == null || form.quadraInicial.value == '')){		
					
					alert('Informe Quadra Inicial.');	
					return false;
					
				}else{
					return true;
				}
			}else{
					return true;
				}
		
	}




	function validarRota(){
		var form = document.forms[0];
	
		if(form.rotaInicial.readOnly == false){
			
			
				if(form.rotaInicial.value != null && form.rotaInicial.value != '' 
						&& (form.rotaFinal.value == null || form.rotaFinal.value == '')){
						
					alert('Informe Rota Final.');
					return false;		
						
				}else if (form.rotaFinal.value != null && form.rotaFinal.value != '' 
						&& (form.rotaInicial.value == null || form.rotaInicial.value == '')){		
					
					alert('Informe Rota Inicial.');	
					return false;
					
				}else{
					return true;
				}
			}else{
					return true;
				}
		
	}
	
	function validarSquencialRota(){
		var form = document.forms[0];
	
		if(form.rotaSequenciaInicial.readOnly == false){
			
			
				if(form.rotaSequenciaInicial.value != null && form.rotaSequenciaInicial.value != '' 
						&& (form.rotaSequenciaFinal.value == null || form.rotaSequenciaFinal.value == '')){
						
					alert('Informe Sequencial de Rota Final.');
					return false;		
						
				}else if (form.rotaSequenciaFinal.value != null && form.rotaSequenciaFinal.value != '' 
						&& (form.rotaSequenciaInicial.value == null || form.rotaSequenciaInicial.value == '')){		
					
					alert('Informe Sequencial Rota Inicial.');	
					return false;
					
				}else{
					return true;
				}
			}else{
					return true;
				}
		
	}	


	function validateGerarOrdemServicoConexaoEsgotoActionForm(form) {
		return true;
	}



	function setarEncerramentoAutomatico(indicadorPermissaoEspecialEncerramentoAutomatico,indicadorPermissao){
	
		var form = document.forms[0];
		var condicaoBloqueio = indicadorPermissaoEspecialEncerramentoAutomatico;
		if(indicadorPermissao=='1'){
			if(condicaoBloqueio=='1'){
				form.indicadorEncerramentoAutomatico[0].checked = true;
				form.indicadorEncerramentoAutomatico[1].checked = false;
			}else{
				form.indicadorEncerramentoAutomatico[0].checked = false;
				form.indicadorEncerramentoAutomatico[1].checked = true;
			}
		}		
		
		verificarGrupoFaturamento();
		
	}
	function verificarBloqueioGrupoFaturamento(){

		var form = document.forms[0];
		var valorIndicador;
		if(form.indicadorEncerramentoAutomatico[0].checked==true){
			valorIndicador = 1;
		}else{
			valorIndicador = 2;
		}
		form.action = 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?indicadorPermissaoEspecialEncerramentoAutomatico='+valorIndicador;
		//form.action = 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do;
		form.submit();

	}

	function validarForm(form) {

		if(validarLocalidade() && validarSetorComercial() &&  validarQuadra() && validarRota()){
			submeterFormPadrao(form);
		}
	}
	
	function verificarGrupoFaturamento(){
		if($('[@name=idGrupoFaturamento]').val() == "-1" && $('[@name=idGrupoFaturamento]').attr("disabled") != "disabled"){
			$('[name=indicadorEncerramentoAutomatico][value=1]').attr("disabled","disabled");
			$('[name=indicadorEncerramentoAutomatico][value=2]').attr("checked","checked");
			
		}		
		else{
			$('[name=indicadorEncerramentoAutomatico][value=1]').attr("disabled",false);
			
		}	
	}
	

	$(document)
			.ready(
					function() {
						
						$('[@name=idGrupoFaturamento]')
						.bind("change",function(event) {
							if($('[@name=idGrupoFaturamento]').val() == "-1" && $('[@name=idGrupoFaturamento]').attr("disabled") != "disabled"){
								$('[name=indicadorEncerramentoAutomatico][value=1]').attr("disabled","disabled");
								$('[name=indicadorEncerramentoAutomatico][value=2]').attr("checked","checked");
								
							}		
							else{
								$('[name=indicadorEncerramentoAutomatico][value=1]').attr("disabled",false);
								
							}
						});
						
						
						$('[@name=idPerfilLigacao]')
								.bind(
										"change",
										function(event) {
											if ($("[@name=idPerfilLigacao]")
													.val() != '-1') {
												var theForm = $("form[name=FiltrarOrdemServicoConexaoEsgotoActionForm]");
												var params = theForm
														.serialize();
												var actionURL = 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=selecionarPercentualEsgoto';
												$
														.ajax({
															type : "POST",
															url : actionURL,
															data : params,
															success : function(
																	data,
																	textStatus,
																	XMLHttpRequest) {
																$(
																		'[@name=percentualEsgoto]')
																		.val(
																				data);
															},
															error : function(
																	XMLHttpRequest,
																	textStatus,
																	errorThrown) {
																alert('Ocorreu um erro, tente novamente');
															}
														});
											} else {
												$('[@name=percentualEsgoto]')
														.val('');
											}
										});

						$('[@name=idMunicipio]')
								.bind(
										"blur keyup",
										function(event) {
											if ($(this).attr('readonly') == false) {
												if ($(this).val() == '') {

													$(
															'[@name=idLocalidadeInicial]')
															.removeClass(
																	'campoBloqueado');
													$(
															'[@name=idLocalidadeInicial]')
															.attr('readonly',
																	false);
													$(
															'#buscarLocalidadeInicial')
															.attr('href',
																	"javascript:chamarLupaLocalidade('origem');");
													$(
															'#apagarLocalidadeInicial')
															.attr('href',
																	"javascript:apagarLocalidadeInicial();");

													$(
															'[@name=idLocalidadeFinal]')
															.removeClass(
																	'campoBloqueado');
													$(
															'[@name=idLocalidadeFinal]')
															.attr('readonly',
																	false);
													$('#buscarLocalidadeFinal')
															.attr('href',
																	"javascript:chamarLupaLocalidade('destino');");
													$('#apagarLocalidadeFinal')
															.attr('href',
																	"javascript:apagarLocalidadeFinal();");
													apagarMunicipio();
													

												} else {
													$(
															'[@name=idLocalidadeInicial]')
															.val('');
													$(
															'[@name=idLocalidadeInicial]')
															.addClass(
																	'campoBloqueado');
													$(
															'[@name=idLocalidadeInicial]')
															.attr('readonly',
																	true);
													$(
															'#buscarLocalidadeInicial')
															.attr('href',
																	"javascript:void(0);");
													$(
															'#apagarLocalidadeInicial')
															.attr('href',
																	"javascript:void(0);");

													$(
															'[@name=idLocalidadeFinal]')
															.val('');
													$(
															'[@name=idLocalidadeFinal]')
															.addClass(
																	'campoBloqueado');
													$(
															'[@name=idLocalidadeFinal]')
															.attr('readonly',
																	true);
													$('#buscarLocalidadeFinal')
															.attr('href',
																	"javascript:void(0);");
													$('#apagarLocalidadeFinal')
															.attr('href',
																	"javascript:void(0);");
												}
											}
										});

						$(
								'[@name=idLocalidadeInicial]')
								.bind(
										"blur keyup",
										function(event) {
											if ($(this).attr('readonly') == false) {
												if ($(this).val() == '') {

													$('[@name=idImovel]')
														.removeClass(
															'campoBloqueado');
													$('[@name=idImovel]')
														.attr('readonly',
															false);


													
													$('[@name=idMunicipio]')
															.removeClass(
																	'campoBloqueado');
													$('[@name=idMunicipio]')
															.attr('readonly',
																	false);
													$('#buscarMunicipio')
															.attr('href',
																	"javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800);");
													$('#apagarMunicipio')
															.attr('href',
																	"javascript:apagarMunicipio();");

													apagarLocalidadeInicial();

												} else {
													$('[@name=idMunicipio]')
															.val('');
													$('[@name=idMunicipio]')
															.addClass(
																	'campoBloqueado');
													$('[@name=idMunicipio]')
															.attr('readonly',
																	true);
													$('#buscarMunicipio')
															.attr('href',
																	"javascript:void(0);");
													$('#apagarMunicipio')
															.attr('href',
																	"javascript:void();");
												}
											}
										});
						
					});

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		if (tipoConsulta == 'imovel') {
			$('[@name=idImovel]').val(codigoRegistro);
			$('[@name=descricaoImovel]').val('');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarImovel');
		}

		else if (tipoConsulta == 'municipio') {
			apagarLogradouro();

			$('[@name=idMunicipio]').val(codigoRegistro);
			$('[@name=descricaoMunicipio]').val(descricaoRegistro);
			$('[@name=descricaoMunicipio]').removeClass(
					'campoInexistenteBloqueado');
			$('[@name=descricaoMunicipio]').addClass('campoBloqueado');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarMunicipio');
		}

		else if (tipoConsulta == 'logradouro') {
			$('[@name=idLogradouro]').val(codigoRegistro);
			$('[@name=descricaoLogradouro]').val(descricaoRegistro);
			$('[@name=descricaoMunicipio]').removeClass(
					'campoInexistenteBloqueado');
			$('[@name=descricaoLogradouro]').addClass('campoBloqueado');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLogradouro');
		}

		else if (tipoConsulta == 'localidadeOrigem') {
			$('[@name=idLocalidadeInicial]').val(codigoRegistro);
			$('[@name=descricaoLocalidadeInicial]').val('');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLocalidadeInicial');
		}

		else if (tipoConsulta == 'localidadeDestino') {
			$('[@name=idLocalidadeFinal]').val(codigoRegistro);
			$('[@name=descricaoLocalidadeFinal]').val('');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLocalidadeFinal');
		}
	}

	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro,
			codigoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'setorComercialOrigem') {
			$('[@name=codSetorComercialInicial]').val(codigoRegistro);
			$('[@name=descricaoSetorComercialInicial]').val('');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarSetorComercialInicial');
		}

		if (tipoConsulta == 'setorComercialDestino') {
			$('[@name=codSetorComercialFinal]').val(codigoRegistro);
			$('[@name=descricaoSetorComercialFinal]').val('');
			redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarSetorComercialFinal');
		}
	}

	function apagarImovel() {
		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarImovel');
	}

	function apagarMunicipio() {
		apagarLogradouro();

		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarMunicipio');
	}

	function apagarLogradouro() {
		$('[@name=idLogradouro]').val('');
		$('[@name=descricaoLogradouro]').val('');
		$('[@name=descricaoMunicipio]')
				.removeClass('campoInexistenteBloqueado');
		$('[@name=descricaoLogradouro]').addClass('campoBloqueado');
	}

	function apagarLocalidadeInicial() {
		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarLocalidadeInicial');
	}

	function apagarLocalidadeFinal() {
		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarLocalidadeFinal');
	}

	function apagarSCInicial() {
		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarSetorComercialInicial');
	}

	function apagarSCFinal() {
		redirecionarSubmit('/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=apagarSetorComercialFinal');
	}

	function chamarLupaLogradouro() {
		abrirPopupDependencia(
				'exibirPesquisarLogradouroAction.do?tipo=logradouro&codigoMunicipio='
						+ $('[@name=idMunicipio]').val(), $(
						'[@name=idMunicipio]').val(), 'Município', 400, 800);
	}

	/*
	* Autor: Jonathan Marcos
	* Data: 05/09/2013
	*/
	function chamarLupaLocalidade(tipo) {
		var form = document.forms[0];
		if(tipo=='origem'){
			abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=' + tipo, 400, 800);
		}else if(tipo=='destino'){
			if(form.idLocalidadeInicial.value!=''){
				abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=' + tipo, 400, 800);
			}else{
				alert("Informe a Localidade Inicial");
			}
		}
	}

	function chamarLupaSetorComercial(tipo) {
		var localidade;
		if (tipo == 'setorComercialOrigem')
			localidade = $('[@name=idLocalidadeInicial]').val();
		else
			localidade = $('[@name=idLocalidadeFinal]').val();

		abrirPopup('exibirPesquisarSetorComercialAction.do?tipo=' + tipo
				+ '&idLocalidade=' + localidade, 400, 800);
	}
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarEncerramentoAutomatico(${indicadorPermissaoEspecialEncerramentoAutomatico},${indicadorPermissao});">
	<div id="formDiv">
		<html:form action="/filtrarOrdemServicoConexaoEsgotoAction"
			name="FiltrarOrdemServicoConexaoEsgotoActionForm"
			type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemServicoConexaoEsgotoActionForm"
			method="post">


			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>
			<table width="770" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="115" valign="top" class="leftcoltext">
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
					<td width="600" valign="top" bgcolor="#003399"
						class="centercoltext">
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
								<td class="parabg">Gerar Ordem de Servico Conexão de Esgoto</td>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<p>Para filtrar o(s) imóvel(is), informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--================================ CORPO DA VISÃO =================================================-->
							<tr>
								<td width="25%"><strong>Descrição comando:<font
										color="#FF0000">*</font></strong></td>
								<td width="75%" height="24" colspan="2"><html:text
										maxlength="40" property="descricaoComando" size="40"
										styleClass="tipoAlfaNumericoComEspaco" tabindex="1" /></td>
							</tr>
							<tr>
								<td width="20%"><strong>Execução:<font
										color="#FF0000">*</font></strong></td>
								<td height="24" width="80%"><html:radio
										property="indicadorExecucao"
										value="${FiltrarOrdemServicoConexaoEsgotoActionForm.compesa}">Compesa</html:radio>
									<html:radio property="indicadorExecucao"
										value="${FiltrarOrdemServicoConexaoEsgotoActionForm.ppp}">PPP</html:radio>
								</td>
							</tr>


							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>


							<tr>
								<td width="20%"><strong>Matrícula do Imóvel:</strong></td>
								<td width="80%" height="24" colspan="3"><c:choose>
										<c:when
											test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearImovel eq 
                   									FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
											<html:text maxlength="9" property="idImovel" size="9"
												tabindex="1" styleClass="tipoInteiro campoBloqueado"
												readonly="true"
												onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarImovel', 'idImovel');"/>
											<a id="buscarImovel"
												href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<logic:equal name="imovelInexistenteException" value="sim">
												<html:text property="descricaoImovel" size="40"
													readonly="true" styleClass="campoInexistenteBloqueado" />
											</logic:equal>
											<logic:notEqual name="imovelInexistenteException" value="sim">
												<html:text property="descricaoImovel" size="40"
													readonly="true" styleClass="campoBloqueado" />
											</logic:notEqual>
											<a id="apagarImovel" href="javaScript:apagarImovel()"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</c:when>
										<c:otherwise>


											<html:text maxlength="9" property="idImovel" size="9"
												tabindex="1" styleClass="tipoInteiro"
												onkeyup="javascript:limparImovel();"
												onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarImovel', 'idImovel');" />
											<a id="buscarImovel"
												href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<logic:equal name="imovelInexistenteException" value="sim">
												<html:text property="descricaoImovel" size="40"
													readonly="true" styleClass="campoInexistenteBloqueado" />
											</logic:equal>
											<logic:notEqual name="imovelInexistenteException" value="sim">
												<html:text property="descricaoImovel" size="40"
													readonly="true" styleClass="campoBloqueado" />
											</logic:notEqual>
											<a id="apagarImovel" href="javaScript:apagarImovel()"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>



										</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<td width="20%"><strong>Município:</strong></td>
								<td width="80%" height="24" colspan="3"><c:choose>
										<c:when
											test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearMunicipio eq 
                   									FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
											<html:text maxlength="9" property="idMunicipio" size="9"
												tabindex="1" styleClass="tipoInteiro campoBloqueado"
												readonly="true" />
											<a id="buscarMunicipio" href="javascript:void(0);"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<html:text property="descricaoMunicipio" size="40"
												readonly="true" styleClass="campoInexistenteBloqueado" />
											<a id="pagarMunicipio" href="javaScript:void(0)"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</c:when>
										<c:otherwise>
											<html:text maxlength="9" property="idMunicipio" size="9" onkeyup="javascript:limparMunicipioTecla();"
												tabindex="1" styleClass="tipoInteiro"
												onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarMunicipio', 'idMunicipio');" />
											<a id="buscarMunicipio"
												href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800);"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<logic:equal name="municipioInexistenteException" value="sim">
												<html:text property="descricaoMunicipio" size="40"
													readonly="true" styleClass="campoInexistenteBloqueado" />
											</logic:equal>
											<logic:notEqual name="municipioInexistenteException"
												value="sim">
												<html:text property="descricaoMunicipio" size="40"
													readonly="true" styleClass="campoBloqueado" />
											</logic:notEqual>
											<a id="apagarMunicipio" href="javaScript:apagarMunicipio()"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<td width="20%"><strong>Logradouro:</strong></td>
								<td width="80%" height="24" colspan="3"><c:choose>
										<c:when
											test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearLogradouro eq 
                   									FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
											<html:text maxlength="9" property="idLogradouro" size="9"
												tabindex="1" styleClass="tipoInteiro campoBloqueado"
												readonly="true" />
											<a id="buscarLogradouro" href="javascript:void(0)"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<html:text property="descricaoLogradouro" size="40"
												readonly="true" styleClass="campoInexistenteBloqueado" />
											<a id="apagarLogradouro" href="javaScript:void(0)"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</c:when>
										<c:otherwise>
											<html:text maxlength="9" property="idLogradouro" size="9"
												tabindex="1" styleClass="tipoInteiro" onkeyup="javascript:limparLogradouroTecla();"
												onkeypress="return validaEnterDependencia(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLogradouro', document.forms[0].idLogradouro, document.forms[0].idMunicipio.value, 'Município');" />
											<a id="buscarLogradouro"
												href="javascript:chamarLupaLogradouro();"><img
												border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar" /></a>
											<logic:equal name="logradouroInexistenteException"
												value="sim">
												<html:text property="descricaoLogradouro" size="40"
													readonly="true" styleClass="campoInexistenteBloqueado" />
											</logic:equal>
											<logic:notEqual name="logradouroInexistenteException"
												value="sim">
												<html:text property="descricaoLogradouro" size="40"
													readonly="true" styleClass="campoBloqueado" />
											</logic:notEqual>
											<a id="apagarLogradouro" href="javaScript:apagarLogradouro()"><img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
										</c:otherwise>
									</c:choose></td>
							</tr>


							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>
							<!-- ------------------------------------------------ INSCRIÇÃO INICIAL ------------------------------------------------ -->
							<tr>
								<td colspan="2">
									<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td><strong>Inscrição Inicial</strong></td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td width="100%" align="center">

												<table width="100%" border="0">
													<c:choose>
														<c:when
															test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearInscricaoInicial eq 
                   													FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
															<tr>
																<td width="20%"><strong>Localidade:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="idLocalidadeInicial" size="9"
																		tabindex="1" styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /> <a href="javascript:void(0)"
																	id="btPesqLocalidadeInicial" tabindex="13"> <img
																		width="23" height="21" border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		title="Pesquisar" /></a> <html:text
																		property="descricaoLocalidadeInicial" size="46"
																		readonly="true" styleClass="campoInexistenteBloqueado" />
																	<a href="javaScript:void(0)"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>
															<tr>
																<td width="20%"><strong>Setor Comercial:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="codSetorComercialInicial"
																		size="9" tabindex="1"
																		styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /> <a href="javascript:void(0)"
																	id="btPesqSCInicial" tabindex="13"><img border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		border="0" title="Pesquisar" /></a> <html:text
																		property="descricaoSetorComercialInicial" size="46"
																		readonly="true" styleClass="campoInexistenteBloqueado" />
																	<a href="javaScript:void(0)"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>
															<tr>
																<td width="20%"><strong>Quadra:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="4" property="quadraInicial" size="5"
																		tabindex="1" styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /></td>
															</tr>

															<tr>
																<td><strong>Rota inicial:</strong></td>
																<td align="left"><html:text property="rotaInicial"
																		tabindex="19" size="5" maxlength="4" readonly="true"
																		styleClass="tipoInteiro campoBloqueado" /> <strong> seq.: </strong> <html:text
																		property="rotaSequenciaInicial" tabindex="20" size="7"
																		maxlength="6" readonly="true"
																		styleClass="tipoInteiro campoBloqueado" /></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td width="20%"><strong>Localidade:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="idLocalidadeInicial" size="9"
																		tabindex="1" styleClass="tipoInteiro" onkeyup="javascript:limparLocalidadeOrigemTecla();"
																		onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarLocalidadeInicial', 'idLocalidadeInicial');" />
																	<a href="javascript:chamarLupaLocalidade('origem');"
																	id="buscarLocalidadeInicial" tabindex="13"> <img
																		width="23" height="21" border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		title="Pesquisar" /></a> <logic:equal
																		name="localidadeInicialInexistenteException"
																		value="sim">
																		<html:text property="descricaoLocalidadeInicial"
																			size="46" readonly="true"
																			styleClass="campoInexistenteBloqueado" />
																	</logic:equal> <logic:notEqual
																		name="localidadeInicialInexistenteException"
																		value="sim">
																		<html:text property="descricaoLocalidadeInicial"
																			size="46" readonly="true" styleClass="campoBloqueado" />
																	</logic:notEqual> <a href="javaScript:apagarLocalidadeInicial()"
																	id="apagarLocalidadeInicial"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>

															<tr>
																<c:choose>
																	<c:when
																		test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearSetorComercial eq 
                   																FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																		<td width="20%"><strong>Setor Comercial:</strong></td>
																		<td width="80%" height="24" colspan="3"><html:text
																				maxlength="9" property="codSetorComercialInicial"
																				size="9" tabindex="1"
																				styleClass="tipoInteiro campoBloqueado"
																				readonly="true" /> <a href="javascript:void(0)"
																			tabindex="13"><img border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																				border="0" title="Pesquisar" /></a> <html:text
																				property="descricaoSetorComercialInicial" size="46"
																				readonly="true" styleClass="campoBloqueado" /> <a
																			href="javaScript:void(0)"><img
																				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																				border="0" title="Apagar" /></a></td>
																	</c:when>
																	<c:otherwise>
																		<td width="20%"><strong>Setor Comercial:</strong></td>
																		<td width="80%" height="24" colspan="3"><html:text
																				maxlength="9" property="codSetorComercialInicial"
																				size="9" tabindex="1" styleClass="tipoInteiro" onkeyup="javascript:limparSetorComercialOrigemTecla();"
																				onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarSetorComercialInicial', 'codSetorComercialInicial');" />
																			<a
																			href="javascript:chamarLupaSetorComercial('setorComercialOrigem')"
																			id="btPesqSCInicial" tabindex="13"><img
																				border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																				border="0" title="Pesquisar" /></a> <logic:equal
																				name="setorComercialInicialInexistenteException"
																				value="sim">
																				<html:text property="descricaoSetorComercialInicial"
																					size="46" readonly="true"
																					styleClass="campoInexistenteBloqueado" />
																			</logic:equal> <logic:notEqual
																				name="setorComercialInicialInexistenteException"
																				value="sim">
																				<html:text property="descricaoSetorComercialInicial"
																					size="46" readonly="true"
																					styleClass="campoBloqueado" />
																			</logic:notEqual> <a href="javaScript:apagarSCInicial()"><img
																				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																				border="0" title="Apagar" /></a></td>
																	</c:otherwise>
																</c:choose>
															</tr>
															<tr>
																<td width="20%"><strong>Quadra:</strong></td>
																<td width="80%" height="24" colspan="3"><c:choose>
																		<c:when
																			test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearQuadra eq 
	                   																FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																			<html:text maxlength="4" property="quadraInicial"
																				size="5" tabindex="1" styleClass="campoBloqueado"
																				readonly="true" />
																		</c:when>
																		<c:otherwise>
																			<html:text maxlength="4" property="quadraInicial"
																				size="5" tabindex="1" styleClass="tipoInteiro" onkeyup="javascript:limparQuadraDestinoTecla();"
																				onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarQuadraInicial', 'quadraInicial');" />
																			<logic:equal name="quadraInicialInexistenteException"
																				value="sim">
																				<span style="color: #ff0000"> QUADRA
																					INEXISTENTE </span>
																			</logic:equal>
																		</c:otherwise>
																	</c:choose></td>
															</tr>

															<c:choose>
																<c:when
																	test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearRota eq 
	                   																FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																	<tr>
																		<td><strong>Rota inicial:</strong></td>
																		<td align="left"><html:text
																				property="rotaInicial" tabindex="19" size="5"
																				maxlength="4" readonly="true"
																				styleClass="tipoInteiro campoBloqueado" /> <strong>seq.:</strong> <html:text
																				property="rotaSequenciaInicial" tabindex="20"
																				size="7" maxlength="6" readonly="true"
																				styleClass="tipoInteiro campoBloqueado" /></td>
																	</tr>
																</c:when>
																<c:otherwise>
																	<tr>
																		<td><strong>Rota inicial:</strong></td>
																		<td align="left"><html:text
																				property="rotaInicial" tabindex="19" size="5"
																				maxlength="4" styleClass="tipoInteiro" /> <strong>seq.: </strong><html:text
																				property="rotaSequenciaInicial" tabindex="20"
																				size="7" maxlength="6" styleClass="tipoInteiro" /></td>
																	</tr>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ------------------------------------------------------------------------------------------------------------------- -->

							<!-- ------------------------------------------------ INSCRIÇÃO FINAL ------------------------------------------------ -->
							<tr>
								<td colspan="2">
									<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td><strong>Inscrição Final</strong></td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td width="100%" align="center">

												<table width="100%" border="0">
													<c:choose>
														<c:when
															test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearInscricaoFinal eq 
                   													FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
															<tr>
																<td width="20%"><strong>Localidade:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="idLocalidadeFinal" size="9"
																		tabindex="1" styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /> <a href="javascript:void(0)"
																	tabindex="13"><img width="23" height="21"
																		border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		title="Pesquisar" /></a> <html:text
																		property="descricaoLocalidadeFinal" size="46"
																		readonly="true" styleClass="campoInexistenteBloqueado" />
																	<a href="javaScript:void(0)"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>

															<tr>
																<td width="20%"><strong>Setor Comercial:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="codSetorComercialFinal"
																		size="9" tabindex="1"
																		styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /> <a href="javascript:void(0)"
																	id="btPesqSCFinal" tabindex="13"><img border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		border="0" title="Pesquisar" /></a> <html:text
																		property="descricaoSetorComercialFinal" size="46"
																		readonly="true" styleClass="campoInexistenteBloqueado" />
																	<a href="javaScript:void(0)"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>
															<tr>
																<td width="20%"><strong>Quadra:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="4" property="quadraFinal" size="5"
																		tabindex="1" styleClass="tipoInteiro campoBloqueado"
																		readonly="true" /></td>
															</tr>

															<tr>
																<td><strong>Rota Final:</strong></td>
																<td align="left"><html:text property="rotaFinal"
																		tabindex="19" size="5" maxlength="4" readonly="true"
																		styleClass="tipoInteiro campoBloqueado" /> seq.: <html:text
																		property="rotaSequenciaFinal" tabindex="20" size="7"
																		maxlength="6" readonly="true"
																		styleClass="tipoInteiro campoBloqueado" /></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td width="20%"><strong>Localidade:</strong></td>
																<td width="80%" height="24" colspan="3"><html:text
																		maxlength="9" property="idLocalidadeFinal" size="9" onkeyup="javascript:limparLocalidadeDestinoTecla();"
																		tabindex="1" styleClass="tipoInteiro"
																		onkeypress="return validarComEnterLocalidadeFinal(event);" />
																	<a
																	href="javascript:javascript:chamarLupaLocalidade('destino');"
																	id="buscarLocalidadeFinal" tabindex="13"> <img
																		width="23" height="21" border="0"
																		src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																		title="Pesquisar" /></a> <logic:equal
																		name="localidadeFinalInexistenteException" value="sim">
																		<html:text property="descricaoLocalidadeFinal"
																			size="46" readonly="true"
																			styleClass="campoInexistenteBloqueado" />
																	</logic:equal> <logic:notEqual
																		name="localidadeFinalInexistenteException" value="sim">
																		<html:text property="descricaoLocalidadeFinal"
																			size="46" readonly="true" styleClass="campoBloqueado" />
																	</logic:notEqual> <a href="javaScript:apagarLocalidadeFinal()"
																	id="apagarLocalidadeFinal"><img
																		src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a></td>
															</tr>

															<tr>
																<c:choose>
																	<c:when
																		test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearSetorComercial eq 
                   																FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																		<td width="20%"><strong>Setor Comercial:</strong></td>
																		<td width="80%" height="24" colspan="3"><html:text
																				maxlength="9" property="codSetorComercialFinal"
																				size="9" tabindex="1"
																				styleClass="tipoInteiro campoBloqueado"
																				readonly="true" /> <a href="javascript:void(0)"
																			tabindex="13"><img border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																				border="0" title="Pesquisar" /></a> <html:text
																				property="descricaoSetorComercialFinal" size="46"
																				readonly="true" styleClass="campoBloqueado" /> <a
																			href="javaScript:void(0)"><img
																				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																				border="0" title="Apagar" /></a></td>
																	</c:when>
																	<c:otherwise>
																		<td width="20%"><strong>Setor Comercial:</strong></td>
																		<td width="80%" height="24" colspan="3"><html:text
																				maxlength="9" property="codSetorComercialFinal"
																				size="9" tabindex="1" styleClass="tipoInteiro" onkeyup="javascript:limparSetorComericalDestinoTecla();"
																				onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarSetorComercialFinal', 'codSetorComercialFinal');" />
																			<a
																			href="javascript:javascript:chamarLupaSetorComercial('setorComercialDestino')"
																			id="btPesqSCFinal" tabindex="13"><img border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																				border="0" title="Pesquisar" /></a> <logic:equal
																				name="setorComercialFinalInexistenteException"
																				value="sim">
																				<html:text property="descricaoSetorComercialFinal"
																					size="46" readonly="true"
																					styleClass="campoInexistenteBloqueado" />
																			</logic:equal> <logic:notEqual
																				name="setorComercialFinalInexistenteException"
																				value="sim">
																				<html:text property="descricaoSetorComercialFinal"
																					size="46" readonly="true"
																					styleClass="campoBloqueado" />
																			</logic:notEqual> <a href="javaScript:apagarSCFinal()"><img
																				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																				border="0" title="Apagar" /></a></td>
																	</c:otherwise>
																</c:choose>
															</tr>
															<tr>
																<td width="20%"><strong>Quadra:</strong></td>
																<td width="80%" height="24" colspan="3"><c:choose>
																		<c:when
																			test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearQuadra eq 
		                   																FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																			<html:text maxlength="4" property="quadraFinal"
																				size="5" tabindex="1" styleClass="campoBloqueado"
																				readonly="true" />
																		</c:when>
																		<c:otherwise>
																			<html:text maxlength="4" property="quadraFinal"
																				size="5" tabindex="1" styleClass="tipoInteiro"
																				onkeypress="return validaEnter(event, 'exibirFiltrarOrdemServicoConexaoEsgotoAction.do?action=pesquisarQuadraFinal', 'quadraFinal');" />
																			<logic:equal name="quadraFinalInexistenteException"
																				value="sim">
																				<span style="color: #ff0000"> QUADRA
																					INEXISTENTE </span>
																			</logic:equal>
																		</c:otherwise>
																	</c:choose></td>
															</tr>


															<c:choose>
																<c:when
																	test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearRota eq 
		                   													FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">
																	<tr>
																		<td><strong>Rota Final:</strong></td>
																		<td align="left"><html:text property="rotaFinal"
																				tabindex="19" size="5" maxlength="4" readonly="true"
																				styleClass="tipoInteiro campoBloqueado" /> <strong> seq.: </strong> <html:text
																				property="rotaSequenciaFinal" tabindex="20" size="7"
																				maxlength="6" readonly="true"
																				styleClass="tipoInteiro campoBloqueado" /></td>
																	</tr>
																</c:when>
																<c:otherwise>
																	<tr>
																		<td><strong>Rota Final:</strong></td>
																		<td align="left"><html:text property="rotaFinal"
																				tabindex="19" size="5" maxlength="4"
																				styleClass="tipoInteiro" /> <strong>seq.: </strong> <html:text
																				property="rotaSequenciaFinal" tabindex="20" size="7"
																				maxlength="6" readonly="true"
																				styleClass="tipoInteiro" /></td>
																	</tr>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ----------------------------------------------------------------------------------------------------------------- -->



							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>

							<tr>
								<td><strong>Grupo de Faturamento:</strong></td>
								<td><strong> <c:choose>

											<c:when
												test="${FiltrarOrdemServicoConexaoEsgotoActionForm.bloquearGrupoFaturamento eq 
                   										   FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">

												<html:select property="idGrupoFaturamento" tabindex="8"
													disabled="true">
													<html:option value="-1">&nbsp;</html:option>
												</html:select>

											</c:when>

											<c:otherwise>
												<html:select property="idGrupoFaturamento" tabindex="8">
													<html:option value="-1">&nbsp;</html:option>
													<logic:notEmpty name="colecaoGrupoFaturamento">
														<html:options collection="colecaoGrupoFaturamento"
															property="id" labelProperty="descricao" />
													</logic:notEmpty>
												</html:select>
											</c:otherwise>
										</c:choose>
								</strong></td>
							</tr>

							<tr>
								<td height="24" colspan="4">
									<hr>
								</td>
							</tr>

							<!-- ----------------------------------- DADOS DA LIG. DE ESGOTO ----------------------------------- -->
							<tr>
								<td colspan="2">
									<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td><strong>Dados da Ligação de Esgoto</strong></td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td width="100%" align="center">
												<table width="100%" border="0">
													<tr>
														<td><strong>Diâmetro da Ligação:<font
																color="#FF0000">*</font></strong></td>
														<td><strong> <html:select
																	property="idDiametroLigacao" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoDiametroLigacao">
																		<html:options collection="colecaoDiametroLigacao"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td><strong>Material da Ligação:<font
																color="#FF0000">*</font></strong></td>
														<td><strong> <html:select
																	property="idMaterialLigacao" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoMaterialLigacao">
																		<html:options collection="colecaoMaterialLigacao"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td><strong>Perfil da Ligação:<font
																color="#FF0000">*</font></strong></td>
														<td><strong> <html:select
																	property="idPerfilLigacao" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoPerfilLigacao">
																		<html:options collection="colecaoPerfilLigacao"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td width="30%"><strong>Percentual de
																Coleta:<font color="#FF0000">*</font>
														</strong></td>
														<td width="70%" height="24" colspan="2"><html:text
																maxlength="6" property="percentualColeta" size="6"
																styleClass="tipoPorcentagem" tabindex="1" /></td>
													</tr>
													<tr>
														<td width="30%"><strong>Percentual de
																Esgoto:<font color="#FF0000">*</font>
														</strong></td>
														<td width="70%" height="24" colspan="2"><html:text
																maxlength="6" property="percentualEsgoto" size="6"
																styleClass="tipoPorcentagem campoBloqueado"
																readonly="true" tabindex="1" /></td>
													</tr>
													<tr>
														<td><strong>Ligação Origem:</strong></td>
														<td><strong> <html:select
																	property="idLigacaoOrigem" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoLigacaoOrigem">
																		<html:options collection="colecaoLigacaoOrigem"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td width="25%"><strong>Com Caixa de
																Gordura?<font color="#FF0000">*</font>
														</strong></td>
														<td height="24" width="75%"><html:radio
																property="indicadorCaixaGordura"
																value="${FiltrarOrdemServicoConexaoEsgotoActionForm.sim}">Sim</html:radio>
															<html:radio property="indicadorCaixaGordura"
																value="${FiltrarOrdemServicoConexaoEsgotoActionForm.nao}">Não</html:radio>
														</td>
													</tr>
													<tr>
														<td width="25%"><strong>Ligação:<font
																color="#FF0000">*</font></strong></td>
														<td height="24" width="75%"><html:radio
																property="indicadorLigacao"
																value="${FiltrarOrdemServicoConexaoEsgotoActionForm.disponivel}">Disponível</html:radio>
															<html:radio property="indicadorLigacao"
																value="${FiltrarOrdemServicoConexaoEsgotoActionForm.efetivado}">Efetivado</html:radio>
														</td>
													</tr>
													<tr>
														<td><strong>Condição do Esgotamento:</strong></td>
														<td><strong> <html:select
																	property="idCondicaoEsgotamento" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoCondicaoEsgotamento">
																		<html:options collection="colecaoCondicaoEsgotamento"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td><strong>Situação da Caixa de Inspeção:</strong></td>
														<td><strong> <html:select
																	property="idSituacaoCaixaInspecao" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoSituacaoCaixaInspecao">
																		<html:options
																			collection="colecaoSituacaoCaixaInspecao"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td><strong>Destino Dejetos:</strong></td>
														<td><strong> <html:select
																	property="idDestinoDejetos" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoDestinoDejetos">
																		<html:options collection="colecaoDestinoDejetos"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
													<tr>
														<td><strong>Destino Águas Pluviais:</strong></td>
														<td><strong> <html:select
																	property="idDestinoAguasPluviais" tabindex="8">
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:notEmpty name="colecaoDestinoAguasPluviais">
																		<html:options collection="colecaoDestinoAguasPluviais"
																			property="id" labelProperty="descricao" />
																	</logic:notEmpty>
																</html:select>
														</strong></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ----------------------------------------------------------------------------------------------- -->

							<!-- ----------------------------------- DADOS DO ENCERRAMENTO DA OS ----------------------------------- -->
							<tr>
								<td colspan="2">
									<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td><strong>Dados do Encerramento das Ordens de
													Serviço</strong></td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td width="100%" align="center">

												<table width="100%" border="0">

													<tr>
														<td width="30%"><strong>Investimento
																(milhões):<font color="#FF0000">*</font>
														</strong></td>
														<td width="70%" height="24" colspan="2"><html:text
																maxlength="17" property="investimento" size="17"
																styleClass="tipoMonetario" tabindex="1" /></td>
													</tr>
													<tr>
														<td width="30%"><strong>População:<font
																color="#FF0000">*</font></strong></td>
														<td width="70%" height="24" colspan="2"><html:text
																maxlength="8" property="populacao" size="8"
																styleClass="tipoPopulacao" tabindex="1" /></td>
													</tr>
													
													
													<tr>
														<td width="25%"><strong>Encerramento Automático:<font color="#FF0000">*</font></strong></td>
														<c:choose>
															<c:when test="${FiltrarOrdemServicoConexaoEsgotoActionForm.indicadorPermissaoEspecialEncerramentoAutomatico eq 
		                   												FiltrarOrdemServicoConexaoEsgotoActionForm.nao}">
									                   			<td height="24" width="75%">
											               			<html:radio property="indicadorEncerramentoAutomatico" value="${FiltrarOrdemServicoConexaoEsgotoActionForm.sim}" disabled="true">Sim</html:radio>
											               			<html:radio property="indicadorEncerramentoAutomatico" value="${FiltrarOrdemServicoConexaoEsgotoActionForm.nao}" disabled="true">Não</html:radio>
											               		</td>
											               	</c:when>
											               	<c:otherwise>
											               		<td height="24" width="75%">
											               			<html:radio property="indicadorEncerramentoAutomatico" value="${FiltrarOrdemServicoConexaoEsgotoActionForm.sim}"  onchange="javascript:verificarBloqueioGrupoFaturamento();">Sim</html:radio>
											               			<html:radio property="indicadorEncerramentoAutomatico" value="${FiltrarOrdemServicoConexaoEsgotoActionForm.nao}" onchange="javascript:verificarBloqueioGrupoFaturamento();">Não</html:radio>
											               		</td>
												            </c:otherwise>	
									               		</c:choose>
													</tr>
													
													<tr>
														<td width="25%"><strong>Parecer do
																Encerramento:<font color="#FF0000">*</font>
														</strong></td>
														<td width="75%"><html:textarea
																property="parecerEncerramento" cols="40" rows="4"
																styleClass="tipoTextArea" /><br> <strong><span
																id="utilizado">0</span>/<span id="limite">200</span></strong> <input
															type="hidden" id="limiteHidden" value="200" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- --------------------------------------------------------------------------------------------------- -->

							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><strong><font
										color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</td>
							</tr>
							<tr>
								<td height="10" colspan="3"><hr></td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%">
										<tr>
											<td colspan="2" align="left"><input type="button"
												class="bottonRightCol" value="Limpar" tabindex="43"
												onclick="window.location.href='/gsan/exibirFiltrarOrdemServicoConexaoEsgotoAction.do?menu=sim';">
												<input type="button" name="Button" tabindex="44"
												class="bottonRightCol" value="Cancelar"
												onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
											</td>
											<td align="right"
												style="padding-left: 0px; padding-right: 0px"><input
												type="button" name="Button" tabindex="42"
												class="bottonRightCol" value="Concluir"
												onclick="javascript:validarForm(document.forms[0])">
											</td>
										<tr>
									</table>
								</td>
							</tr>
							<!--=================================================================================================-->
						</table>
					</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
