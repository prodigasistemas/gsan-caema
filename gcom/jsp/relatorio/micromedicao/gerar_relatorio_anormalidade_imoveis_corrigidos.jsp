<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<html:javascript staticJavascript="false" formName="GerarRelatorioAnormalidadeImoveisCorrigidosActionForm" />
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
	<script language="JavaScript">

	function recarregarForm(){
	
		var form = document.forms[0];
  	
  		form.action='exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do';
	    form.submit();
	
	}

	function validarForm(){

			var form = document.forms[0];
		
			if(validarLocalizacaoGeografica()){
				if(validadarLocalidade()){
					if(validarSetorComercial()){
						if(validarPeriodo() && validateGerarRelatorioAnormalidadeImoveisCorrigidosActionForm(form)){
							form.action = 'gerarRelatorioAnormalidadeImoveisCorrigidosAction.do'						
								submitForm(form);
						}
		

					}
						
				}

			}
		}


	function habilitarDesabilitarCampos(){
		var form = document.forms[0];

		if(form.idGerenciaRegional.value!="-1"){
			form.idUnidadeNegocio.disabled=true;
			form.localidadeInicial.disabled=true;
			form.setorComercialInicial.disabled=true;
			form.localidadeFinal.disabled=true;
			form.setorComercialFinal.disabled=true;
		}


		if(form.idGerenciaRegional.value=="-1" && form.idUnidadeNegocio.value=="-1"){		
			form.idGerenciaRegional.disabled=false;
			form.idUnidadeNegocio.disabled=false;
			form.localidadeInicial.disabled=false;
			form.setorComercialInicial.disabled=false;
			form.localidadeFinal.disabled=false;
			form.setorComercialFinal.disabled=false;
		}

		if(form.idUnidadeNegocio.value!="-1"){
			form.idGerenciaRegional.disabled=true;
			form.localidadeInicial.disabled=true;
			form.setorComercialInicial.disabled=true;
			form.localidadeFinal.disabled=true;
			form.setorComercialFinal.disabled=true;
		}

		if(form.localidadeInicial.value>0 || form.localidadeFinal.value>0){
			form.idGerenciaRegional.disabled=true;
			form.idUnidadeNegocio.disabled=true;
			
		}
	}

	function validarLocalizacaoGeografica(){

			var form = document.forms[0];

			if(form.idGerenciaRegional.value=="-1"
				&& form.idUnidadeNegocio.value=="-1" 
				&& (form.localidadeInicial.value.length==0 && 
						form.nomeLocalidadeInicial.value.length==0 &&
						 form.localidadeFinal.value.length==0 && 
						 form.nomeLocalidadeFinal.value.length==0)){

				alert("Selecione pelo menos um campo de localização geográfica");
				
				return false;					

				}else{
						
					return true

				}


		}

	function campoObrigatorio(){

		var form = document.forms[0];

		if(form.idGerenciaRegional.value=="-1"){
			alert("Informe a Gerencia Regional");

			return false;
			
			}else{

				return true;
						
				}

		
		}

	function validadarLocalidade(){
		
		var form = document.forms[0];

		if(form.localidadeInicial.value.length>0 || form.nomeLocalidadeInicial.value.length>0 || form.localidadeFinal.value.length>0 || form.nomeLocalidadeFinal.value.length>0){
			if(validarCamposLocalidadeInicial()){
				if(validarCamposLocalidadeFinal()){
						return true;
					}
				}

			}else{

				return true;
				
				}

		}

	function validarCamposLocalidadeInicial(){
		
		var form = document.forms[0];
		var nomeDoCampo = "Localidade Inicial";


			if(validarCampoNumericoComMensagem(form.localidadeInicial,nomeDoCampo)){
				
				if(form.localidadeInicial.value.length>0 && form.nomeLocalidadeInicial.value.length>0){
		
						return true;
		
					}else{
		
						alert("Informe a Localidade Inicial");
						
						return false;
				
						}
					}else{
		
						return false;
					
						}		
		}

	function validarCamposLocalidadeFinal(){
		var form = document.forms[0];
		var nomeDoCampo = "Localidade final";
		if(validarCampoNumericoComMensagem(form.localidadeFinal,nomeDoCampo)){

		if(form.localidadeFinal.value.length>0 && form.nomeLocalidadeFinal.value.length>0){
					if(form.localidadeFinal.value<form.localidadeInicial.value){

						alert("Localidade Final tem que ser maior que a Localidade Inicial");
						
						return false;
						
						}else{

								return true;
							}

				}else{

					alert("Informe a Localidade Final");	
					
					return false;
		
					}

			}else{

				return false;

				}
		
		}

	function validarSetorComercial(){

		var form = document.forms[0];

		if(form.setorComercialInicial.value.length==0 && form.nomeSetorComercialInicial.value.length==0 && form.setorComercialFinal.value.length==0 && form.nomeSetorComercialFinal.value.length==0){

			return true;				

		}else{

			if(form.setorComercialInicial.value.length>0 && form.nomeSetorComercialInicial.value.length>0){

				if(form.setorComercialFinal.value.length>0 && form.nomeSetorComercialFinal.value.length>0){

						return true;

					}else{

						alert("Informe o Setor Comercial Final");

						return false;
						
					}
				
			}else{

				alert("Informe o Setor Comercial Inicial");
					
				return false;				
					
			}
			

		}

	}

	function validarPeriodo(){
		var form = document.forms[0];
		var perIni = form.periodoDiaMesAnoInicial;
		var perFin = form.periodoDiaMesAnoFinal;

		if(form.periodoDiaMesAnoInicial.value.length>0 && form.periodoDiaMesAnoFinal.value.length>0){

			if(validaData(perIni) && validaData(perFin)){
				if(comparaData(perIni.value, '<=', perFin.value)){
					return true;
				}
				else{
					alert('Data Final do Período é anterior à Data Inicial do Período.');
					return false;
				}
			}
				
		}else if(form.periodoDiaMesAnoInicial.value.length==0 && form.periodoDiaMesAnoFinal.value.length==0){

			alert("Informe o Perído Inicial e Final");
						
			return false;
			
		}else if(form.periodoDiaMesAnoInicial.value.length>0 && form.periodoDiaMesAnoFinal.value.length==0){

			alert("Informe o Perído Final");
			
			return false;
			
		}else if(form.periodoDiaMesAnoInicial.value.length==0 && form.periodoDiaMesAnoFinal.value.length>0){

			alert("Informe o Perído Inicial");
			
			return false;
			
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
	
  	function limpar(){

  		var form = document.forms[0];

		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.nomeLocalidadeInicial.style.color = "#000000";
	  	form.nomeLocalidadeFinal.style.color = "#000000";

		form.setorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		form.nomeSetorComercialInicial.style.color = "#000000";
	  	form.nomeSetorComercialFinal.style.color = "#000000";

		form.idGerenciaRegional.value = "-1";
  		form.idUnidadeNegocio.value = "-1";

  		form.idsAnormalidadeInformada.value = "-1";

  		form.periodoDiaMesAnoInicial.value= "";
   		form.periodoDiaMesAnoFinal.value="";
  		
  		form.action='exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do?menu=sim';
	    form.submit();
  	}
  	
  	function replicarLocalidade(){
		var formulario = document.forms[0]; 

		if(formulario.localidadeFinal.value.lenght==0 && formulario.nomeLocalidadeFinal.value.lenght==0){
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.setorComercialInicial.focus;

		}
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinal.value = formulario.setorComercialInicial.value;
		formulario.nomeSetorComercialFinal.value = formulario.nomeSetorComercialInicial.value;
		formulario.idsAnormalidadeInformada.focus;
	}

	function replicarPeriodo(){
		var form = document.forms[0];

		form.periodoDiaMesAnoFinal.value = form.periodoDiaMesAnoInicial.value;

		} 
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
		    form.setorComercialFinal.value = "";
		  	
		    
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.setorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";
		 	    
			
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2: //De setor para baixo
		     	
		     	form.setorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.setorComercialFinal.value = "";
		     	form.nomeSetorComercialFinal.value = "";
		}

		habilitarDesabilitarCampos();
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.setorComercialFinal.value = "";
				
			case 2: //De setor para baixo		   
		   		form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
		}

		habilitarDesabilitarCampos();
		
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.setorComercialInicial.focus();
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";

	  		form.setorComercialFinal.focus();
		}
	}
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		}
	}	
  	
	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5" onload="habilitarDesabilitarCampos();">
		
		<div id="formDiv">
		<html:form action="/gerarRelatorioAnormalidadeImoveisCorrigidosAction"
				   name="GerarRelatorioAnormalidadeImoveisCorrigidosActionForm"
				   type="gcom.gui.relatorio.micromedicao.GerarRelatorioAnormalidadeImoveisCorrigidosActionForm"
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
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Relatório de Anormalidade de Imóveis Corrigidos </td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					
					<table width="100%">
					
						<tr>
						
							<td colspan="2">
								Para gerar o relat&oacute;rio, informe os dados abaixo:
							</td>
							
						</tr>
						
						<tr>
						
							<td>
							
								<strong>Ger&ecirc;ncia Regional:</strong>
							
							</td>
							
							<td>
							
								<html:select property="idGerenciaRegional" onchange="recarregarForm();" style="width:300px;" >
								
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
									
									<logic:present name="colecaoGerenciaRegional">
									
										<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id"></html:options>
									
									</logic:present>	
								
								</html:select>
								
							
							</td>
							
						</tr>
						
						<tr>
								
							<td>
							
								<strong>Unidade Neg&oacute;cio:</font></strong>
							
							</td>
							
							<td>
								
								<html:select property="idUnidadeNegocio" style="width:300px;" onchange="habilitarDesabilitarCampos();">
								
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
									
									<logic:present name="colecaoUnidadeNegocio">
									
										<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"></html:options>
									
									</logic:present>
																		
								</html:select>
								
									
							</td>
							
						</tr>
						
						<tr>
						
							<td>
							
								<strong>Localidade Inicial:</strong>
							
							</td>
							
							<td>
								
							<html:text maxlength="4" onchange="habilitarDesabilitarCampos();"
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();" 
							onkeyup="javascript:limparOrigem(1);"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
								
								
								
								
							</td>
							
						</tr>
						
						<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="setorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do?objetoConsulta=2','setorComercialInicial','Setor Comercial Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);
						         limparOrigem(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>

					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="4" onchange="habilitarDesabilitarCampos();"
							tabindex="1"
							property="localidadeFinal" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');return isCampoNumerico(event);"/>
							
						
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 

						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="4" property="setorComercialFinal"
							size="3"
							tabindex="8"
							onblur="javascript:replicarSetorComercial();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeImoveisCorrigidosAction.do?objetoConsulta=4','setorComercialFinal','Setor Comercial Final');return isCampoNumerico(event);"/>
								
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);
							        limparDestino(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="33"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
				
					<td>
					
						<strong>Anormalidade de Leitura Informada:</strong>
					
					</td>
				
				
					<td>
					
					
								<html:select property="idsAnormalidadeInformada" multiple="true" style="width:300px;">
								
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
									
									<logic:present name="colecaoAnormalidade">
									
									<html:options collection="colecaoAnormalidade" labelProperty="descricao" property="id"></html:options>
									
									</logic:present>
								</html:select>
					
					
					</td>
					
					
					</tr>
					
					<tr>
					
					<td>
					
						<strong>Per&iacute;odo de Correção:<font color="#FF0000">*</font></strong>
					
					</td>
					
					<td>
					
					<html:text property="periodoDiaMesAnoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="1" 
									onkeyup="javascript:replicarCampo( document.forms[0].periodoDiaMesAnoFinal, document.forms[0].periodoDiaMesAnoInicial );mascaraData(this, event);"
									onkeypress="return isCampoNumerico(event);"/>
						
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioAnormalidadeImoveisCorrigidosActionForm', 'periodoDiaMesAnoInicial', 'periodoDiaMesAnoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>
						
						&nbsp;				
						<strong>a</strong>
						&nbsp;
						<html:text property="periodoDiaMesAnoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="1"
									onkeyup="mascaraData(this, event);"
									onkeypress="return isCampoNumerico(event);replicarPeriodo();"/>
									 
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioAnormalidadeImoveisCorrigidosActionForm', 'periodoDiaMesAnoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>
						<strong>(dd/mm/aaaa)</strong>
					</td>
					
					</tr>
										
					<tr>
					
					
						<td colspan="2" align="center">
						
							<font color="#FF0000">*</font> Campos Obrigat&oacute;rios
						
						</td>
						
						
						<td>
						
						
						</td>
										
					</tr>
					
											
					
					
					<tr>
						<td height="24" >
				          	<input type="button" 
				          		class="bottonRightCol" 
				          		value="Limpar" 
				          		onclick="javascript:limpar();"/>
				          	<font color="#FF0000"> <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font>
						</td>
					
						<td align="right">
							<input type="button" 
								name="Button" 
								class="bottonRightCol" 
								value="Gerar" 
								onClick="javascript:validarForm()" />
						</td>
					</tr>
				
				</table>
						
					</td>
				
				</tr>
			</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 		
		</html:form>
		</div>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioAnormalidadeImoveisCorrigidosAction.do"/>
	</body>
</html:html>