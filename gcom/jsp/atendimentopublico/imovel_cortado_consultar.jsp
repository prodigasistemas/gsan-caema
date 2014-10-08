<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.atendimentopublico.bean.FiltrarImoveisCortadosHelper"  isELIgnored="false"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

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
	formName="FiltrarAcessoLojaVirtualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
<!--


function pesquisar(form) {
	var aux = true;
		if ( form.indicadorGerarOSFiscalizacao[1].checked == true ) {
				if ( (form.idGerenciaRegional.value == null || form.idGerenciaRegional.value == "-1") && 
						(form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == "")){
						alert("Para gerar ordem de serviço é necessario informar Gerencia Regional ou Localidade ");
						aux = false;
				} else {
					if ( validarCampos(form) ) {
						botaoAvancarTelaEspera('/gsan/exibirConsultarImovelCortadoAction.do?acao=pesquisa');
					} else {
						aux = false;
					}
				}
			
		} else {
			if ( validarCampos(form) ) {

				if ( (form.idGerenciaRegional.value == null || form.idGerenciaRegional.value == "-1") && 
						(form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == "")){

					alert("Para gerar o relatório é necessario informar Gerencia Regional ou Localidade ");
					aux = false;
				} else {
					form.action = 'gerarRelatorioImoveisCortadosAction.do';
				}


			} else {
				aux = false;
			}
		}
	if ( validateFiltrarImovelCortadoActionForm(form) && aux) {
		if ( validarCampos(form)  ) {
			form.submit();
		}
	}
}

var bCancel = false; 

function validateFiltrarImovelCortadoActionForm(form) {                                                                   
	if (bCancel) 
    	return true; 
   	else 
    	return validateDate(form); 
} 

function DateValidations () {
	this.aa = new Array("periodoCorteInicial", "Data de Corte Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("periodoCorteFinal", "Data de Corte Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function gerarOS(form) {
	if ( validarCampos(form) ) {
		botaoAvancarTelaEspera('/gsan/gerarOrdemServicoFiscalizacaoImoveisCortadosAction.do');
	}
}

function limparFormulario(form) {
	form.action = 'exibirConsultarImovelCortadoAction.do?acao=limpar';
	form.submit();
}

function validarCampos(form) {
 var retorno = true;

 if (form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != "" ) {

		if ( form.idLocalidadeFinal.value == null || form.idLocalidadeFinal.value == "" ) {
			alert("Informe Localidade Final");
			retorno = false;
		} else if ( parseInt(form.idLocalidadeInicial.value) > parseInt(form.idLocalidadeFinal.value) ) {
			alert("Localidade Final deve ser maior que Localidade Inicial.");
			retorno = false;
		}

		if (form.idSetorComercialInicial.value != null && form.idSetorComercialInicial.value != "" ) {

			if ( form.idSetorComercialFinal.value == null || form.idSetorComercialFinal.value == "" ) {
				alert("Informe Setor Comercial Final");
				retorno = false;
			} else if ( parseInt(form.idSetorComercialInicial.value) > parseInt(form.idSetorComercialFinal.value) ) {
				alert("Setor Comercial Final deve ser maior que Setor Comercial Inicial.");
				retorno = false;
			}
			
			if (form.idQuadraInicial.value != null && form.idQuadraInicial.value != "" ) {

				if ( form.idQuadraFinal.value == null || form.idQuadraFinal.value == "" ) {
					alert("Informe Quadra Final");
					retorno = false;
				} else if ( parseInt(form.idQuadraInicial.value) > parseInt(form.idQuadraFinal.value) ) {
					alert("Quadra Final deve ser maior que Quadra Inicial.");
					retorno = false;
				}
			}
		}	
	}

 	if ( form.valorDebitoInicial.value != null && form.valorDebitoInicial.value != "" &&( form.valorDebitoFinal.value ==null || form.valorDebitoFinal.value == "") ) {
 		alert("Informe Valor Débito Final");
		retorno = false;
	} else if ( form.valorDebitoFinal.value != null && form.valorDebitoFinal.value != "" &&( form.valorDebitoInicial.value ==null || form.valorDebitoInicial.value == "") ) {
 		alert("Informe Valor Débito Inicial");
 		retorno = false;
	}	
	
	return retorno;
}

function replicarData() {
	var form =  document.forms[0];

	form.periodoCorteFinal.value = form.periodoCorteInicial.value; 
}

function replicarDebitos() {
	var form =  document.forms[0];

	form.valorDebitoFinal.value = form.valorDebitoInicial.value; 
}

function facilitador(objeto){
	
	if (objeto.value == "0" || objeto.value == undefined ){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
  		if (objeto == null || codigoObjeto == null){
     		if(tipo == "" ){
      			abrirPopup(url,altura, largura);
     		}else{
	  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	 		}
 		}else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function validaNumero(campo){
   	var valorAuxiliar = "";
	digitosValidos = "0123456789/" ;
	var numeroInvalido  = false;
	
	valor = campo.value;
	//retira digitos terao numericos
		for (i=0;i<valor.length;i++){
			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
				valorAuxiliar += valor.charAt(i);
			} else {
				numeroInvalido  = true;
			}
		}

	
	//retira zeros desnecessarios ao inicio do numero
	while (valorAuxiliar.length > 1 && valorAuxiliar.charAt(0) == "0")
		valorAuxiliar = valorAuxiliar.substring(1);

	valor = valorAuxiliar;
	if(numeroInvalido) {
		campo.value = "";
	}

}

function pesquisarUnidadeNegocioDependente() {
	var form = document.forms[0];
	form.action = "exibirConsultarImovelCortadoAction.do?unidadeNegocio=pesquisar";
	form.submit();
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'localidadeOrigem') {

  		form.idLocalidadeInicial.value = codigoRegistro;
  		form.descricaoLocalidadeInicial.value = descricaoRegistro;
  		
  		form.idLocalidadeFinal.value = codigoRegistro;
  		form.descricaoLocalidadeFinal.value = descricaoRegistro;
  		
  		form.descricaoLocalidadeInicial.style.color = "#000000";
  		form.descricaoLocalidadeFinal.style.color = "#000000";
  		
  		form.idSetorComercialInicial.focus();
  		habilitaDesabilitaCampos();
	}

	if (tipoConsulta == 'localidadeDestino') {
	
  		form.idLocalidadeFinal.value = codigoRegistro;
  		form.descricaoLocalidadeFinal.value = descricaoRegistro;
  		form.descricaoLocalidadeFinal.style.color = "#000000";

  		form.idSetorComercialFinal.focus();
  		habilitaDesabilitaCampos();
  		 
	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
	  	form.idSetorComercialInicial.value = codigoRegistro;
	  	form.descricaoSetorComercialInicial.value = descricaoRegistro;
	  	form.descricaoSetorComercialInicial.style.color = "#000000"; 
	  	
	  	form.idSetorComercialFinal.value = codigoRegistro;
	  	form.descricaoSetorComercialFinal.value = descricaoRegistro;
	  	form.descricaoSetorComercialFinal.style.color = "#000000";
	  	habilitaDesabilitaCampos();
	}

	if (tipoConsulta == 'setorComercialDestino') {
	  	form.idSetorComercialFinal.value = codigoRegistro;
	  	form.descricaoSetorComercialFinal.value = descricaoRegistro;
	  	form.descricaoSetorComercialFinal.style.color = "#000000";
	  	habilitaDesabilitaCampos();
	}
}	

function limpar(objetoConsulta) {
	var form = document.forms[0];

	if ( objetoConsulta == 1) {
		form.idLocalidadeInicial.value = "";
		form.descricaoLocalidadeInicial.value = "";

		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";

		form.idSetorComercialInicial.value = "";
	  	form.descricaoSetorComercialInicial.value = "";

		form.idSetorComercialFinal.value = "";
	  	form.descricaoSetorComercialFinal.value = "";

	  	form.idQuadraInicial.value = "";
	  	form.idQuadraFinal.value = "";
	  	limparQuadraInicial();
		limparQuadraFinal();		
	} else if ( objetoConsulta == 2 ) {

		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";

		form.idSetorComercialInicial.value = "";
	  	form.descricaoSetorComercialInicial.value = "";

		form.idSetorComercialFinal.value = "";
	  	form.descricaoSetorComercialFinal.value = "";

	  	form.idQuadraInicial.value = "";
	  	form.idQuadraFinal.value = "";
	  	limparQuadraInicial();
		limparQuadraFinal();		
		
	} else if ( objetoConsulta == 3 ) {

		form.idSetorComercialInicial.value = "";
	  	form.descricaoSetorComercialInicial.value = "";
	  	
		form.idSetorComercialFinal.value = "";
	  	form.descricaoSetorComercialFinal.value = "";

	  	form.idQuadraInicial.value = "";
	  	form.idQuadraFinal.value = "";
	  	limparQuadraInicial();
		limparQuadraFinal();		
		
	} else if ( objetoConsulta == 4 ) {

		form.idSetorComercialFinal.value = "";
	  	form.descricaoSetorComercialFinal.value = "";

	  	form.idQuadraInicial.value = "";
	  	form.idQuadraFinal.value = "";
	  	limparQuadraInicial();
		limparQuadraFinal();		
		
	} else if ( objetoConsulta == 5 ) {

		if ( form.idQuadraInicial.value == null || form.idQuadraInicial.value == "" ) {
		  	form.idQuadraInicial.value = "";
		  	form.idQuadraFinal.value = "";
		  	limparQuadraInicial();
			limparQuadraFinal();	
		}	
		
	} else if ( objetoConsulta == 6 ) {

		if ( form.idQuadraFinal.value == null || form.idQuadraFinal.value == "" ) {
		  	form.idQuadraFinal.value = "";
			limparQuadraFinal();		
		}
	}

	habilitaDesabilitaCampos();
		
}

function limparQuadras(objeto) {
	var form = document.forms[0];
	if ( objeto == 1 ) {
		if ( form.idQuadraInicial.value == null || form.idQuadraInicial.value == "") {
			limparQuadraInicial();
			limparQuadraFinal();
			form.idQuadraFinal.value = "";			
		}
	} else if ( objeto == 2 ) {
		if ( form.idQuadraFinal.value == null || form.idQuadraFinal.value == "" ) {
			limparQuadraFinal();
			form.idQuadraFinal.value = "";
		}
	}
}

function habilitaDesabilitaCampos() {

	var form = document.forms[0];

	if ( form.idGerenciaRegional.value != null && form.idGerenciaRegional.value != "-1" ) {
		form.idUnidadeNegocio.disabled = false;

		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;

		form.idSetorComercialInicial.disabled = true; 
	  	form.idSetorComercialFinal.disabled = true;

	  	form.idQuadraInicial.disabled = true;
	  	form.idQuadraFinal.disabled = true;
		
	} else {

		form.idUnidadeNegocio.disabled = true;
		form.idUnidadeNegocio.value = "-1";
		
		form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;

		if ( form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != ""  &&
				form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != "") {
			form.idSetorComercialInicial.disabled = false; 
		  	form.idSetorComercialFinal.disabled = false;

		  	form.idQuadraInicial.disabled = true;
		  	form.idQuadraFinal.disabled = true;
		  	
		  	if ( form.idSetorComercialInicial.value != null && form.idSetorComercialInicial.value != "" &&
		  			form.idSetorComercialFinal.value != null && form.idSetorComercialFinal.value != "" ) {

				form.idQuadraInicial.disabled = false;
			  	form.idQuadraFinal.disabled = false;
			} else if ( form.idSetorComercialInicial.value == null || form.idSetorComercialInicial.value == "" &&
					form.idSetorComercialFinal.value != null && form.idSetorComercialFinal.value != "") {
				form.idSetorComercialFinal.value = "";
			 	form.descricaoSetorComercialFinal.value = "";
			 	form.descricaoSetorComercialInicial.value = "";
			 	form.idQuadraInicial.value = "";
			  	form.idQuadraFinal.value = "";
			  	limparQuadraFinal();
			  	limparQuadraInicial();
			} else if ( form.idSetorComercialFinal.value == null || form.idSetorComercialFinal.value == "") {
			 	form.idQuadraInicial.value = "";
			  	form.idQuadraFinal.value = "";
			  	form.descricaoSetorComercialFinal.value = "";
			  	limparQuadraFinal();
			  	limparQuadraInicial();
			}
		} else {
			
			form.idSetorComercialInicial.disabled = true; 
		  	form.idSetorComercialFinal.disabled = true;

		  	form.idQuadraInicial.disabled = true;
		  	form.idQuadraFinal.disabled = true;

		  	form.idSetorComercialInicial.value = ""; 
		  	form.idSetorComercialFinal.value = "";
		  	form.idQuadraInicial.value = "";
		  	form.idQuadraFinal.value = "";
		}
	}
}
function verificaIndicador(){
	var form = document.forms[0];

	if ( form.indicadorGerarOSFiscalizacao[0].checked == false && form.indicadorGerarOSFiscalizacao[1].checked == false ) {
		form.indicadorGerarOSFiscalizacao[0].checked = true
		form.botaoGerarOS.disabled = true;
	} 

	if ( form.botaoGerarOrdemServico.value != null && form.botaoGerarOrdemServico.value == "habilita" ) {
		form.botaoGerarOS.disabled = false;
	} else if (form.indicadorGerarOSFiscalizacao[1].checked == true ) {
		form.botaoGerarOS.disabled = false;
	} else if (form.indicadorGerarOSFiscalizacao[0].checked == true ) {
		form.botaoGerarOS.disabled = true;
	}
	

}

function limparQuadraFinal(){
	var msgQuadraFinal = document.getElementById('msgQuadraFinal');
	msgQuadraFinal.innerHTML =  "";
	
}

function limparQuadraInicial(){
	var ni = document.getElementById('msgQuadra');
	ni.innerHTML =  "";
}

function mensagemQuadra(){
	var form = document.forms[0];

	if (form.idQuadraFinal.value == null || form.idQuadraFinal.value == "" ) {
		var nif = document.getElementById('msgQuadraFinal');
		nif.innerHTML =  "";
		form.mensageQuadraFinal.value = "";
	}

	if ( form.idQuadraInicial.value != null && form.idQuadraInicial.value != "" ) {
		var ni = document.getElementById('msgQuadra');
		ni.innerHTML =  form.mensagemQuadra.value;
	}

	if ( form.idQuadraFinal.value != null && form.idQuadraFinal.value != "" ) {
		var ni = document.getElementById('msgQuadraFinal');
		ni.innerHTML =  form.mensageQuadraFinal.value;
	}

	if ( form.idQuadraInicial.value == null || form.idQuadraInicial.value == "" ) {
		var ni = document.getElementById('msgQuadra');
		ni.innerHTML =  "";
		form.mensagemQuadra.value == "";
		form.mensageQuadraFinal.value = "";
		var nif = document.getElementById('msgQuadraFinal');
		nif.innerHTML =  "";
	}
	
}
-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificaIndicador(); habilitaDesabilitaCampos();mensagemQuadra();">

<div id="formDiv"><html:form action="/emitirOrdemFiscalizacaoAction" 
 		   name="FiltrarImovelCortadoActionForm" 
		   type="gcom.gui.atendimentopublico.FiltrarImovelCortadoActionForm"
		   method="post"
		   onsubmit="return validateFiltrarImovelCortadoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="botaoGerarOrdemServico" />
	<html:hidden property="mensagemQuadra" />
	<html:hidden property="mensageQuadraFinal" />

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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Consultar Imóveis Cortados</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para consultar os imóveis cortados, informe os dados abaixo:</p>
					</td>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
	        </table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Per&iacute;odo do Corte:<font color="#FF0000">*</font></strong>
								</td>
             				    <td colspan="6">
									<html:text property="periodoCorteInicial" 
											size="11" 
											maxlength="10" 
											tabindex="1" 
											onblur="validaNumero(this);"
											onkeyup="mascaraData(this, event);replicarData();" 
											onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendarioReplicando('FiltrarImovelCortadoActionForm', 'periodoCorteInicial', 'periodoCorteFinal' );">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a> a <html:text property="periodoCorteFinal" 
												size="11" 
												maxlength="10" 
												tabindex="2" 
												onblur="validaNumero(this);"
												onkeyup="mascaraData(this, event)"  
												onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendario('FiltrarImovelCortadoActionForm', 'periodoCorteFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
									</a>
							</tr>
									
							<tr>
								<td>
									<strong>Ger&ecirc;ncia Regional:</strong>
								</td>
			
								<td>
									<strong> 
										<html:select property="idGerenciaRegional" 
													 style="width: 230px;" 
													 tabindex="3"
													 onchange="javascript:pesquisarUnidadeNegocioDependente();">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp; </html:option>
											<logic:present name="colecaoGerenciaRegional" scope="request">
												<html:options   collection="colecaoGerenciaRegional"
																labelProperty="nome" 
																property="id" />
											</logic:present>
										</html:select> 														
									</strong>
								</td>
							</tr>
			
							<tr>
								<td>
									<strong>Unidade de Neg&oacute;cio:</strong>
								</td>
			
								<td>
									<strong> 
									<html:select property="idUnidadeNegocio" 
												tabindex="4"
												style="width: 230px;" >
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp; </html:option>
										<logic:present name="colecaoUnidadeNegocio" scope="request">
											<html:options   collection="colecaoUnidadeNegocio"
															labelProperty="nome" 
															property="id" />
										</logic:present>
									</html:select> 														
									</strong>
								</td>
							</tr>			
		              	
							<tr>
								<td><strong>Localidade Inicial:</strong></td>
								
								<td>
									<html:text  maxlength="3" 
												tabindex="5"
												property="idLocalidadeInicial" 
												size="3"
												onblur="habilitaDesabilitaCampos();validaNumero(this);"
												onkeyup="habilitaDesabilitaCampos();"
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=1', 'idLocalidadeInicial','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Localidade" /></a>
			
									<logic:present name="localidadeEncontrada" scope="request">
										
										<html:text property="descricaoLocalidadeInicial"  readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidadeInicial" 
															size="30"
															maxlength="30" 
															readonly="true"
															style="background-color:#EFEFEF;border:0;"
															 />
									</logic:notPresent>
										
									
									<a href="javascript:limpar(1);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Setor Comercial Inicial:</strong></td>
								
								<td>
									
									<html:text maxlength="3" 
										tabindex="6"
										property="idSetorComercialInicial" 
										size="3"
										onblur="habilitaDesabilitaCampos();validaNumero(this);"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=3', 'idSetorComercialInicial','Setor Comercial');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].idSetorComercialInicial);
									         limpar(3);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Setor Comercial" /></a>
											
									<logic:present name="setorEncontrado" scope="request">
										
										<html:text property="descricaoSetorComercialInicial"  readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercialInicial" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
										
									
									<a href="javascript:limpar(3);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
								<tr>
			                   <td><strong>Quadra Inicial:</strong></td>
			                   <td height="24">
			                   
			                   		<html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" 
			                   			property="idQuadraInicial" size="3" tabindex="7" 
			                   			onblur="limpar(5);validaNumero(this);mensagemQuadra();"
			                   			onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=5', 'idQuadraInicial','Quadra');return isCampoNumerico(event);"/>
			                   		<b id="msgQuadra"></b>
			                   		
			                      
			                   </td>
			                </tr>
							<tr>
								<td><strong>Localidade Final:</strong></td>
								
								<td>
									<html:text  maxlength="3" 
												tabindex="8"
												property="idLocalidadeFinal" 
												size="3"
												onblur="habilitaDesabilitaCampos();validaNumero(this);"
												onkeyup="habilitaDesabilitaCampos();"
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=2', 'idLocalidadeFinal','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Localidade" /></a>
									
									
									<logic:present name="localidadeFinalEncontrado" scope="request">
										
										<html:text  property="descricaoLocalidadeFinal"   readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="localidadeFinalEncontrado" scope="request">
										
										<html:text property="descricaoLocalidadeFinal" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; " />
										
									</logic:notPresent>
									<a href="javascript:limpar(2);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							<tr>
								<td><strong>Setor Comercial Final:</strong></td>
								<td>
								
									<html:text maxlength="3" 
										tabindex="9"
										property="idSetorComercialFinal" 
										size="3"
										onblur="habilitaDesabilitaCampos();validaNumero(this);"
										onkeyup="habilitaDesabilitaCampos();"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=4', 'idSetorComercialFinal','Setor Comercial');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].idLocalidadeFinal.value , 275, 480, 'Informe Localidade Final',document.forms[0].idSetorComercialFinal);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Setor Comercial" /></a>
									<logic:present name="setorFinalEncontrado" scope="request">
										
										<html:text  property="descricaoLocalidadeFinal"   readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="setorFinalEncontrado" scope="request">
										
										<html:text property="descricaoSetorComercialFinal" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
										
									</logic:notPresent>
										
									
									<a href="javascript:limpar(4);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							<tr>
			                   <td><strong>Quadra Final:</strong></td>
			                   <td height="24">
			                   
			                   		<html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" 
			                   			property="idQuadraFinal" size="3" tabindex="10"  
			                   			onblur="limpar(6);validaNumero(this);mensagemQuadra();"
			                   			onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImovelCortadoAction.do?objetoConsulta=6', 'idQuadraFinal','Quadra');return isCampoNumerico(event);"/>
			                   		<b id="msgQuadraFinal"></b>
			                      
			                   </td>
			                </tr>
							
							<tr>
								<td>
									<strong>Motivo de Corte:</strong>
								</td>
			
								<td>
									<strong> 
										<html:select property="idMotivoCorte" 
													tabindex="11"
													 style="width: 230px;" >
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp; </html:option>
											<logic:present name="colecaoMotivoCorte" scope="request">
												<html:options   collection="colecaoMotivoCorte"
																labelProperty="descricao" 
																property="id" />
											</logic:present>
										</html:select> 														
									</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Valor do Débito:</strong>
								</td>
             				    <td colspan="6">
									<html:text property="valorDebitoInicial" 
											size="11" 
											maxlength="10" 
											onclick="verificaIndicador();"
											onblur="formataValorMonetario(this, 11);verificaIndicador();"
											tabindex="12"  onkeyup="formataValorMonetario(this, 11);replicarDebitos();"/>
									a <html:text property="valorDebitoFinal" 
												size="11" 
												maxlength="10" 
												onblur="formataValorMonetario(this, 11);verificaIndicador();"
												tabindex="13" onkeyup="formataValorMonetario(this, 11);"/>
									
							</tr>
							<tr>
								<td><strong>Tipo de Geração:</strong></td>
								<td align="right">
								<div align="left">
									<html:radio property="indicadorGerarOSFiscalizacao" onchange="verificaIndicador();"
												tabindex="14"
												value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
										<strong>Relatório</strong> 
									<html:radio property="indicadorGerarOSFiscalizacao"
												tabindex="15" 
												onchange="verificaIndicador();"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" 
												/>
										<strong>O.S de Fiscalização</strong> 
								</div>
								</td>
							</tr>
							
							<tr>
							<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td width="65" align="right">
									<input name="Button2" 
										   type="button"
										   class="bottonRightCol" 
										   value="Pesquisar" 
										   align="right"
										   onClick="javascript:pesquisar(document.forms[0]);" tabindex="9" /></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000">
							<td width="13" bgcolor="#90c7fc" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong>
							</td>
							<td width="13" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Matricula</strong></div>
						   </td>
							<td width="19" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Categoria</strong></div>
						   </td>
						   <td width="15" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Perfil</strong></div>
						   </td>
						   <td width="15" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Sit.Água</strong></div>
						   </td>
						   <td width="15" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Sit.Esgoto</strong></div>
						   </td>
						   <td width="15" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Valor Débito</strong></div>
						   </td>
						  
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				  <td colspan="3">
				  <table width="100%" bgcolor="#99CCFF">
                    
                      <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">

							<pg:param name="pg" />
							<pg:param name="q" />

							<%int cont = 0;%>
 <logic:present name="colecaoHelper" scope="request">
                      <logic:iterate name="colecaoHelper" id="helper" scope="request" type="FiltrarImoveisCortadosHelper">
                       	<pg:item>
                          <%cont = cont + 1;
							if (cont % 2 == 0) {%>
	                          <tr bgcolor="#cbe5fe">
	                            <%} else {%>
	                          </tr>
	                          <tr bgcolor="#FFFFFF">
	                            <%}%>
	                            
	                            <td width="11%" align="center">
                              		<input type="checkbox" name="idRegistro" value="${helper.matricula}" />
                            	</td>
	                            <td width="17%" align="center"> 
	                            	<bean:write name="helper" property="matricula"/>
	                            </td>
	                            
	                            <td width="15%" align="left" >
										   <bean:write name="helper" property="categoria" />
								</td>
								
								<td width="15%" align="left" >
										   <bean:write name="helper" property="imovelPerfil" />
								</td>
								<td width="15%" align="left" >
										   <bean:write name="helper" property="situacaoAgua" />
								</td>
								<td width="15%" align="left" >
										   <bean:write name="helper" property="situacaoEsgoto" />
								</td>
								<td width="15%" align="right" >
										   <bean:write name="helper" property="valorDebito" />
								</td>
	                          </tr>
                       </pg:item>
                      </logic:iterate>
                        <tr>
							<td colspan="6">
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</tr>
                    </logic:present>
                  </table>
               
					</pg:pager>
                  </td>
				</tr>
				<tr>
					<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</div>
					</td>
					
					<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonLimpar" class="bottonRightCol" value="Limpar"
								onClick="javascript:limparFormulario(document.forms[0]);">
						</div>
					</td>
					
					<td valign="top">
						<div align="left">
							<input type="button"
								name="botaoGerarOS" class="bottonRightCol" value="Gerar Ordem de Serviço"
								onClick="javascript:gerarOS(document.forms[0]);">
						</div>
					</td>
					
				</tr>
			</table>
			
	</tr>
</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcessoLojaVirtualAction.do"/>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form></div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>