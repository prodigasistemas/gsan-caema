<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="gcom.util.ConstantesSistema" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>


<html:javascript staticJavascript="true"  formName="GerarArquivoTextoOrdensServicoSmartphoneActionForm" />
<script type="text/javascript">

	var bCancel = false; 
	
	function validateGerarArquivoTextoOrdensServicoSmartphoneActionForm(form) {                                                                   
	    if (bCancel) 
	  return true; 
	    else 
	   return validateRequired(form); 
	} 
	
	function required () { 
	 this.aa = new Array("empresa", "Informe Empresa.", new Function ("varName", " return this[varName];"));
	 this.ab = new Array("idTipoOS", "Informe Tipo da Ordem de Serviço.", new Function ("varName", " return this[varName];"));
	} 

	function pesquisarUnidadeNegocio(){
		var form = document.forms[0];
	
		form.action='exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=gerenciaRegional';
		submeterFormPadrao(form);
	}

	function pesquisarLocalidade(){
		var form = document.forms[0];

		if (form.localidade.disabled == false)  {			
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function limparLocalidade(){
		var form = document.forms[0];
		form.localidade.value = "";
		form.nomeLocalidade.value = "";
		form.qtdOsSeletiva.value = "";
		limparSetorComercialOrigem();
		limparColecaoOSAjax();
		
	}
	
	function limparSetorComercialOrigem(){
		var form = document.forms[0];
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		form.qtdOsSeletiva.value = "";
		limparQuadraInicial();
		limparColecaoOSAjax();
	}
	
	function limparSetorComercialOrigemTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialDestino.value = "";
		form.qtdOsSeletiva.value = "";
		limparQtdOsSeletiva();
		limparColecaoOSAjax();
	}
	
	function limparSetorComercialDestino(){
		var form = document.forms[0];
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";		
		form.codigoQuadraFinal.value = "";
		form.qtdOsSeletiva.value = "";
		limparQtdOsSeletiva();
		limparColecaoOSAjax();
	}
	
	function limparSetorComercialDestinoTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialDestino.value = "";
		form.qtdOsSeletiva.value = "";
		limparQtdOsSeletiva();
		limparColecaoOSAjax();
	}
	
	function limparQuadraInicial(){
		var form = document.forms[0];
		form.codigoQuadraInicial.value = "";	
		form.codigoQuadraFinal.value = "";
		document.getElementById('qdrIniIne').style.visibility = 'hidden';
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		form.qtdOsSeletiva.value = "";
		limparQtdOsSeletiva();
		limparColecaoOSAjax();

	}
	
	function limparQuadraInicialTecla(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value = "";
		document.getElementById('qdrIniIne').style.visibility = 'hidden';
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		limparColecaoOSAjax();
	
	}
	
	function limparQuadraFinal(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value = "";		
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		form.codigoQuadraFinal.value = "";
		limparColecaoOSAjax();
	
	}
	
	function limparQuadraFinalTecla(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value = "";
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		limparColecaoOSAjax();
		
	}

	function pesquisarSetorComercialOrigem(){
		var form = document.forms[0];

		if (form.codigoSetorComercialOrigem.disabled == false) {
			form.tipoPesquisa.value = 'origem';			
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		}
	}
	function pesquisarSetorComercialDestino(){
		var form = document.forms[0];
		form.tipoPesquisa.value = 'destino';
		abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		
	}
	
	function pesquisarQuadraInicial(){
		var form = document.forms[0];

		if (form.codigoQuadraInicial.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do?consulta=sim&codigoSetorComercial='+form.codigoSetorComercialOrigem.value+'&idLocalidade='+form.localidade.value, 
			form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraFinal(){
		var form = document.forms[0];

		if (form.codigoQuadraFinal.disabled == false){
			form.tipoPesquisa.value = 'destino';			
			abrirPopupDependencia('exibirPesquisarQuadraAction.do?consulta=sim&codigoSetorComercial='+form.codigoSetorComercialOrigem.value+'&idLocalidade='+form.localidade.value, 
			form.codigoSetorComercialDestino.value, 'Setor Comercial Final', 275, 480);
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta){
		var form = document.forms[0];		
		if (tipoConsulta == 'localidade'){
	      form.localidade.value = codigoRegistro;
		  form.nomeLocalidade.value = descricaoRegistro;	     
		  form.nomeLocalidade.style.color = "#000000";
		}
		
		else if (tipoConsulta == 'setorComercial'){
			if (form.tipoPesquisa.value == 'origem'){
	    			form.codigoSetorComercialOrigem.value = codigoRegistro;
					form.descricaoSetorComercialOrigem.value = descricaoRegistro;
					form.descricaoSetorComercialOrigem.style.color = "#000000";
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					verificaIgualdadeSetor();					
				} else if(form.tipoPesquisa.value == 'destino'){
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					verificaIgualdadeSetor();
				}
		}
		else if (tipoConsulta == 'quadra'){
    		if (form.tipoPesquisa.value == 'origem') {
    			form.codigoQuadraInicial.value = codigoRegistro;
				form.codigoQuadraFinal.value = codigoRegistro;							
			} else if(form.tipoPesquisa.value == 'destino'){
				form.codigoQuadraFinal.value = codigoRegistro;				
			}
		}
	}
	function verificaIgualdadeSetor(){
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		if(codSetOri.value != codSetDest.value){
			form.codigoQuadraInicial.value='';
			form.codigoQuadraFinal.value='';
			form.codigoQuadraInicial.readOnly = true;
			form.codigoQuadraFinal.readOnly = true;
		}
		else{
			form.codigoQuadraInicial.readOnly = false;
			form.codigoQuadraFinal.readOnly = false;
		}
	}
	function limparTudo(){
		var form = document.forms[0];
		form.nomeLocalidade.value = "";
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		form.codigoQuadraInicial.value='';
		form.codigoQuadraFinal.value='';
		form.qtdOsSeletiva.value='';
		limparColecaoOSAjax();
	}
	
	function limparQtdOsSeletiva(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value='';
	}
	
	function validarForm(id){		
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		var qdrIni = form.codigoQuadraInicial;
		var qdrFin = form.codigoQuadraFinal;
		var loc = form.localidade;
		var comando = form.comando;
		var identificadorOS = form.identificadorOS;
		//var unidadeNegocio = form.unidadeNegocio;
		//var gerenciaRegional = form.gerenciaRegional;
		 
		if (validateGerarArquivoTextoOrdensServicoSmartphoneActionForm(form)){

			/*if((gerenciaRegional.value == "" || gerenciaRegional.value == "-1") &&
			   (unidadeNegocio.value == "" || unidadeNegocio.value == "-1") && loc.value == ""){
				alert('Informe Gerência Regional, Unidade de Negócio ou Localidade');
			}*/
			if(loc.value == ""){
				alert('Informe Localidade');
			}
			else if(loc.value != ""){

  				if (codSetOri.value == "" || codSetDest.value== ""){
					alert('Informar intervalo dos setores comerciais');
				}
				else if(parseInt(codSetOri.value) > parseInt(codSetDest.value)){
					alert('Setor Comercial Final deve ser maior ou igual ao Setor Comercial Inicial');
				}
/* 				else if((codSetOri.value == codSetDest.value) && (qdrIni.value == '' || qdrFin.value == '')){
						alert('Informar intervalo das quadras');			
				}		
 */				else if(parseInt(qdrIni.value) > parseInt(qdrFin.value)){
					alert('Quadra Inicial maior que a Quadra Final');	
				}				
				else{
					if(id == 1){
						if(!verificarSelecaoCheckBox()){
							alert('Selecione pelo menos uma Ordem de Serviço');	
						}
						else{
							botaoAvancarTelaEspera('/gsan/gerarArquivoTextoOrdensServicoSmartphoneAction.do?comando='+comando.value+'&identificadorOS='+identificadorOS.value);				
						}
					}
					else if(id == 2){
						botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQdt&comando='+comando.value+'&identificadorOS='+identificadorOS.value);
						//form.action='exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQdt';
						//submeterFormPadrao(form);
					}
					
					else if(id == 3){
						toggleBox('demodiv',1);
					}
					
					else if(id == 4){
						botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarOS&comando='+comando.value+'&identificadorOS='+identificadorOS.value);
					}
					
 				}
			}		
			else{
				if(id == 1){
					botaoAvancarTelaEspera('/gsan/gerarArquivoTextoOrdensServicoSmartphoneAction.do?comando='+comando.value+'&identificadorOS='+identificadorOS.value);				
					//form.action='gerarArquivoTextoOrdensServicoSmartphoneAction.do';
					//submeterFormPadrao(form);
				}
				else if(id == 2){
					botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQdt&comando='+comando.value+'&identificadorOS='+identificadorOS.value);
					//form.action='exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQdt';
					//submeterFormPadrao(form);
				}
				else if(id == 3){
					toggleBox('demodiv',1);
				}
			}
		}
	}

	function gerarRelatorio(){
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		var qdrIni = form.codigoQuadraInicial;
		var qdrFin = form.codigoQuadraFinal;

		if(codSetOri.value !='' && codSetDest.value == ''){
			alert('Preencha o Setor Comercial Final');	
		}
		else if(codSetOri.value =='' && codSetDest.value != ''){
			alert('Preencha o Setor Comercial Inicial');
		}
		else if(parseInt(codSetOri.value) > parseInt(codSetDest.value)){
			alert('Setor Comercial Final deve ser maior ou igual ao Setor Comercial Inicial');
		}
		else if(qdrIni.value !='' && qdrFin.value == ''){
			alert('Preencha a Quadra Final');
		}
		else if(qdrIni.value =='' && qdrFin.value != ''){
			alert('Preencha a Quadra Inicial');
		}
		else if(parseInt(qdrIni.value) > parseInt(qdrFin.value)){
			alert('Quadra Inicial maior que a Quadra Final');	
		}				
		else{
			toggleBox('demodiv',1);
		}
	}
	
	function contarCheckboxMarcados(){
		var checks = document.forms[0].idsRegistros;
		if(checks != null){
			contador = 0;
			for(i = 0; i < checks.length; i++){
				if(checks[i].checked)
					contador++;
			}
			document.getElementById('spanTotalId').innerHTML = contador;
		}
	}
	
	function verificarSelecaoCheckBox(){
		var checks = document.forms[0].idsRegistros;
		if(checks != null){
			for(i = 0; i < checks.length; i++){
				if(checks[i].checked)
					return true;
			}
			return false;
		}
		else
			return true;
	}
	
	function limparColecaoOSAjax(){
		if(document.getElementById('divColecaoOS').innerHTML.trim() != ""){
			document.getElementById('divColecaoOS').innerHTML = "";
			var theForm = $("form[name=GerarArquivoTextoOrdensServicoSmartphoneActionForm]");
			var params = theForm.serialize();
			var actionURL = 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=excluirColecaoOS'; 
			$.ajax({
			    type:"POST",
			    url:actionURL,
			    data:params
			});
		}
	}
	
	function facilitador(objeto) {
		if (objeto.id == "0" || objeto.id == undefined) {
			objeto.id = "1";
			marcarTodos();
		} else {
			objeto.id = "0";
			desmarcarTodos();
		}
		contarCheckboxMarcados();
	}
	
	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:verificaIgualdadeSetor();contarCheckboxMarcados();">
<div id="formDiv">
<html:form action="/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do"
	name="GerarArquivoTextoOrdensServicoSmartphoneActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarArquivoTextoOrdensServicoSmartphoneActionForm"
	method="post">

	<input type="hidden" name="tipoPesquisa" />
	<input type="hidden" name="comando" value='<c:out value="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.idComandoCorrecaoAnormalidade}"/>' />
	<input type="hidden" name="identificadorOS" value='<c:out value="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.idTipoOS}"/>' />

	<%@ include file="/jsp/util/cabecalho.jsp"%>

	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellpadding="0" cellspacing="5">

		<tr>

			<td width="120" valign="top" class="leftcoltext">

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

			<td valign="top" class="centercoltext">

			<table height="100%">

				<tr>

					<td></td>

				</tr>

			</table>

			<table width="100%" border="0" align="center" cellpadding="0"

				cellspacing="0">

				<tr>

					<td width="11"><img

						src="<bean:message key="caminho.imagens"/>parahead_left.gif"

						border="0" /></td>
					
					<td class="parabg">Gerar Arquivo Texto de Ordens de Serviço para Smartphone</td>
					
					<td width="11" valign="top"><img

						src="<bean:message key="caminho.imagens"/>parahead_right.gif"

						border="0" /></td>

				</tr>

			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td colspan="3">Para gerar o arquivo texto de ordens de serviço para smartphone, informe os dados abaixo:</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>

					<td width="25%">
						<strong>Empresa:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						
						<logic:present name="colecaoEmpresa"> 
							<html:select property="empresa"  style="font-size:11px;" tabindex="9">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
							</html:select>
						</logic:present>
						
						<logic:notPresent name="colecaoEmpresa"> 
							
							<html:hidden property="empresa"/>
							<html:text property="descricaoEmpresa" size="54"
								maxlength="54" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						
						</logic:notPresent>
			
					</td>

				</tr>
				
				<tr>

					<td width="25%">
						<strong>Tipo da Ordem de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						
						<html:hidden property="idTipoOS"/>
						<html:text property="descricaoTipoOS" size="54"
								maxlength="54" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								
					</td>

				</tr>
				
				<c:if test="${not empty GerarArquivoTextoOrdensServicoSmartphoneActionForm.idComandoCorrecaoAnormalidade}">
					<tr>
						<td width="25%">
							<strong>Comando para Correção de Anormalidade:</strong>
						</td>
						<td>
							<html:hidden property="idComandoCorrecaoAnormalidade"/>
							<html:text property="descricaoComandoCorrecaoAnormalidade" size="54"
									maxlength="54" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</td>
					</tr>
				</c:if>
				
				<tr><td>&nbsp;</td></tr>
				<tr bgcolor="#99CCFF" >					
					<td height="18" colspan="2">
						<div align="left">
							<strong>
								<span class="style2"> Filtro pra Geração do Arquivo TXT </span>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>

					<td width="25%">
						<strong>Localidade:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="3" property="localidade"
						size="3"
						onkeypress="validaEnter(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=localidade', 'localidade');
						return isCampoNumerico(event);"
						onkeyup="limparTudo(); return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].localidade, 'Localidade');"						
						tabindex="1" /> 
						<a href="javascript:pesquisarLocalidade();"> 
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
						<logic:present name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="41" maxlength="30" readonly="true" 
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="41"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparLocalidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade" />
						</a>
					
					</td>

				</tr>

			<tr>
					<td><strong>Setor Comercial Inicial:<font color="#FF0000">*</font></strong></td>
					<td>					
					<html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=setorComercial&tipoSetor=1', document.forms[0].codigoSetorComercialOrigem, 'Setor Comercial Inicial', document.forms[0].localidade.value, 'Localidade');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);
						verificaIgualdadeSetor();
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=setorComercial&tipoSetor=1', document.forms[0].codigoSetorComercialOrigem, 'Setor Comercial Inicial', document.forms[0].localidade.value, 'Localidade');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);
						verificaIgualdadeSetor();"
						tabindex="2" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
						/>
					<a href="javascript:pesquisarSetorComercialOrigem();"> 
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial Inicial" /></a>
					<logic:present
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="41"
							maxlength="30" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> 
					<logic:notPresent
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="41"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Inicial" /></a></td>
				</tr>
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					<td><html:text maxlength="3" property="codigoQuadraInicial" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=quadra&tipoQuadra=1', document.forms[0].codigoQuadraInicial, 'Quadra Inicial', document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial');
						replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial); 
						return isCampoNumerico(event);"
						onchange="validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=quadra&tipoQuadra=1', document.forms[0].codigoQuadraInicial, 'Quadra Inicial', document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial');
						replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);"
						tabindex="3"
						onkeyup="javascript:replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);
						limparQuadraInicialTecla();
						limparQtdOsSeletiva();"						
						 />
						 <%--
						<a href="javascript:pesquisarQuadraInicial();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
						--%> 
						<logic:present name="quadraInicialInexistente" scope="request">
							<font color="#ff0000" id="qdrIniIne">QUADRA INEXISTENTE</font>
						</logic:present>
						<%--
						<logic:notPresent name="quadraInicialInexistente" scope="request">
							<html:text property="descricaoQuadraInicial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						 <a
							href="javascript:limparQuadraInicial();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Quadra Inicial" /></a>
							--%>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="3" property="codigoSetorComercialDestino"
						size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=setorComercial&tipoSetor=2', document.forms[0].codigoSetorComercialDestino, 'Setor Comercial Final', document.forms[0].localidade.value, 'Localidade '); 
						verificaIgualdadeSetor();
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=setorComercial&tipoSetor=2', document.forms[0].codigoSetorComercialDestino, 'Setor Comercial Final', document.forms[0].localidade.value, 'Localidade '); 
						verificaIgualdadeSetor();"						
						tabindex="4" onkeyup="limparSetorComercialDestinoTecla();" /> <a
						href="javascript:pesquisarSetorComercialDestino();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial Final" /></a> <logic:present
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="41"
							maxlength="30" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="41"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Final" /></a></td>
				</tr>
				
				<tr>
					<td><strong>Quadra Final:</strong></td>
					<td><html:text maxlength="3" property="codigoQuadraFinal" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=quadra&tipoQuadra=2', document.forms[0].codigoQuadraFinal, 'Quadra Final', document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final');
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=quadra&tipoQuadra=2', document.forms[0].codigoQuadraFinal, 'Quadra Final', document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final');"
						tabindex="5" onkeyup="javascript:limparQuadraFinalTecla();limparQtdOsSeletiva();" 
						 />
						 <logic:present name="quadraFinalInexistente" scope="request">
							<font color="#ff0000" id="qdrFinIne">QUADRA INEXISTENTE</font>
						</logic:present>
						<%--
						<a href="javascript:pesquisarQuadraFinal();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
							
						<logic:present name="quadraFinalInexistente" scope="request">
						<html:text property="descricaoQuadraFinal" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present>
						<logic:notPresent name="quadraFinalInexistente" scope="request">
							<html:text property="descricaoQuadraFinal" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						 <a
							href="javascript:limparQuadraFinal();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Quadra Final" /></a>
							--%>
					</td>
				</tr>
				
				
				<c:choose>
					<c:when test="${not empty GerarArquivoTextoOrdensServicoSmartphoneActionForm.idComandoCorrecaoAnormalidade}">
						<tr>
							<td width="25%">
								<strong>Tipo de Serviço:</strong>
							</td>
							<td>		
								<html:hidden property="idTipoServicoComando"/>	
								<html:text property="descricaoTipoServicoComando" size="55"
								maxlength="55" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</td>
						</tr>	
					</c:when>
					<c:otherwise>
						<tr>
							<td width="25%">
								<strong>Tipo de Serviço:</strong>
							</td>
							<td>
								
								<html:select property="idsServicoTipo"  style="width: 300px;font-size:11px; height: 100px" tabindex="6" multiple="true">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoServicoTipo" labelProperty="descricao" property="id"/>
								</html:select>
					
							</td>
						</tr>			
					</c:otherwise>
				</c:choose>
				<tr>
					<td>
						<p>&nbsp;</p>
					</td>
					<td colspan="2"> 
					<font color="#FF0000">*</font>Campos Obrigat&oacute;rios</td>
				</tr>	
				
				<tr>

					<td>

					<p>&nbsp;</p>

					</td>

					<td colspan="2"></td>

				</tr>

			</table>				
			
			<table width="100%">				
				<tr>
					<td colspan="3" align="left">
						<input type="button" value="Consultar Quantidade" onclick="javascript:validarForm(2)" class="bottonRightCol" tabindex="7">
						<html:text property="qtdOsSeletiva" size="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						&nbsp;&nbsp;<strong>Qtd M&aacute;x. OS:</strong>&nbsp;&nbsp;
						<strong><span style="font-size:20px;"><bean:write name="GerarArquivoTextoOrdensServicoSmartphoneActionForm" property="qtdMaxOS"/></span></strong>
					</td>	
					<td align="right">							
						<input type="button" class="bottonRightCol" onclick="javascript:validarForm(4)" value="Consultar OS" tabindex="8"/>							
					</td>				
				</tr>
			</table>
			
			<div id="divColecaoOS">
				<table>
					<c:if test="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS != null 
															&& not empty GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS }">
						<tr>
							<td>
								<p>&nbsp;</p>
							</td>
						</tr>									
															
						<tr>
							<td width="100%" colspan="5">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
									<tr bgcolor="#99CCFF">
				                   		<td align="left">
				           					<span class="style2">        		
				             						<b>Ordens de Serviço</b>          				
				           					</span>
				               			</td>
				              		</tr>
				              		
										<tr>
											<td colspan="3">
												<table id=header width="100%" border="0" bgcolor="#90c7fc">
													<COL WIDTH=50 align="center"><COL WIDTH=135 align="center"><COL WIDTH=80 align="center"><COL WIDTH=100 align="center"><COL WIDTH=296 align="center">
													<tr>
														<th bgcolor="#90c7fc" align="center">
														<a href="javascript:facilitador(this);">Marcar</a></th>
														<th bgcolor="#90c7fc" align="center">Inscrição</th>
														<th bgcolor="#90c7fc" align="center">Matrícula</th>
														<th bgcolor="#90c7fc" align="center">Ordem de Serviço</th>
														<th bgcolor="#90c7fc" align="center">Tipo de Serviço</th>
													</tr>
												</table>
												<bean:define name="GerarArquivoTextoOrdensServicoSmartphoneActionForm" 
															 property="colecaoOS" 
															 id="colecaoOS" />
																 
										<c:if test="${fn:length(GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS) gt 7 }">																								 
												<DIV STYLE="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
										</c:if>																 
													<TABLE border="0" width="100%" bgcolor="#90c7fc">
														<c:set var="count" value="0"/>
														<COL WIDTH=25 align="center"><COL WIDTH=75 align="center"><COL WIDTH=78 align="center"><COL WIDTH=123 align="center"><COL WIDTH=407 align="center">
														<logic:iterate name="colecaoOS" id="helper">
														
															<c:choose>
																<c:when test="${count % 2 == 0 }">
																	<tr bgcolor="#FFFFFF">		
																</c:when>
																<c:otherwise>
																	<tr bgcolor="#cbe5fe">
																</c:otherwise>
															</c:choose>
																<td width=68 align="center">
																	<html:multibox property="idsRegistros"
																	value="${helper.idOs}" disabled="false"
																	onchange="javascript:contarCheckboxMarcados();" />
																</td>
																<td align="center">
																	<c:out value="${helper.inscricaoImovel}"/>
																</td>
																 <td align="center">
																	<c:out value="${helper.idImovel}"/>
																</td>
																<td align="center">
																	<c:out value="${helper.idOs}"/>
																</td>
																<td align="center">
																	<c:out value="${helper.tipoServico}"/>
																</td>
															</tr>	
															<c:set var="count" value="${count+1}"/>
														</logic:iterate>
													</table>
												<c:if test="${fn:length(GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS) gt 7 }">																								 
													</DIV>
												</c:if>																 
											</td>
										</tr>
										<tr bgcolor="#90c7fc">
											<td>
												<table width="98%">
													<tr>
														<td colspan="3" align="right">
															<strong>Total Selecionado: <span id="spanTotalId">0</span></strong>
														</td>
													</tr>
												</table>
											</td>
										</tr> 	
								</table>					
						    </td>
					    </tr>
					</c:if>
				</table>
			</div>
			<p>&nbsp;</p>
			<hr />
			
			<table width="100%">

				<tr>

					<td width="100%">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>

							<td width="70">
								<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer"
								onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?identificadorOS=${GerarArquivoTextoOrdensServicoSmartphoneActionForm.idTipoOS}&comando=${GerarArquivoTextoOrdensServicoSmartphoneActionForm.idComandoCorrecaoAnormalidade}&menu=sim"/>'">
							</td>
							<td>
								<input type="button" class="bottonRightCol" value="Cancelar"
								onclick="window.location.href='/gsan/telaPrincipal.do'" />
								
							</td>
							
							<td width="200">&nbsp;</td>
							
							<td>							
							<input type="button" class="bottonRightCol" onclick="javascript:validarForm(3)" value="Gerar Relat&oacute;rio" tabindex="8"/>							
							</td>
							
							<td>								
								<input type="button" class="bottonRightCol" onclick="javascript:validarForm(1);" value="Gerar Arquivo TXT" tabindex="9"/>
								
							</td>
						</tr>
					</table>

					</td>

				</tr>

			</table>

			<p>&nbsp;</p>

			</td>

		</tr>

	</table>
	
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioQuantitativoImoveisTipoServicoAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>

