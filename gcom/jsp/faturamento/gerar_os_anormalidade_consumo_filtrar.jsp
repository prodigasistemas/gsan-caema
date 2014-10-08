<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarOsAnormalidadeConsumoFiltrarActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript">
<!-- Begin
var bCancel = false; 

function validate(form) {                                                                   
    if (bCancel) 
  return true; 
    else 
   return validateCaracterEspecial(form) && validateLong(form); 
} 

function caracteresespeciais () { 
 this.ab = new Array("localidadeOrigemID", "Localidade da inscrição inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.ac = new Array("setorComercialOrigemCD", "Setor Comercial da inscrição inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.ad = new Array("quadraOrigemNM", "Quadra da inscrição inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.af = new Array("localidadeDestinoID", "Localidade da inscrição final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.ag = new Array("setorComercialDestinoCD", "Setor Comercial da inscrição final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.ah = new Array("quadraDestinoNM", "Quadra da inscrição final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.ak = new Array("codigoRotaOrigem", "Rota inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.al = new Array("codigoRotaDestino", "Rota final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
 this.ab = new Array("localidadeOrigemID", "Localidade da inscrição inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.ac = new Array("setorComercialOrigemCD", "Setor Comercial da inscrição inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.ad = new Array("quadraOrigemNM", "Quadra da inscrição inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
this.af = new Array("localidadeDestinoID", "Localidade da inscrição final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.ag = new Array("setorComercialDestinoCD", "Setor Comercial da inscrição final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.ah = new Array("quadraDestinoNM", "Quadra da inscrição final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.ak = new Array("codigoRotaOrigem", "Rota inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 this.al = new Array("codigoRotaDestino", "Rota final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function reloadForm(){
		var form = document.forms[0];
	
		form.action='exibirFiltrarGerarOsAnormalidadeConsumoAction.do';
    form.submit();
	}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;

	
	if (tipoConsulta == 'setorComercialOrigem') {
		
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro
	  if(form.localidadeOrigemID.value == form.localidadeDestinoID.value &&  
			  form.localidadeOrigemID.value != "" && form.localidadeDestinoID.value != ""){
	     form.setorComercialDestinoID.value = idRegistro;
	  
	  	form.setorComercialDestinoCD.value = codigoRegistro;
	 
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000";	  
	  }
	  verificarBotao();
	  
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	     form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  if(form.setorComercialOrigemCD.value == "")
	  {
	  	form.setorComercialOrigemCD.value = codigoRegistro;
	  }
	  if(form.nomeSetorComercialOrigem.value == "")
	  {
	  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  	form.nomeSetorComercialOrigem.style.color = "#000000";	  
	  }
	  verificarBotao();
	  form.quadraDestinoNM.focus();
	}

}
function habilitarSetorQuadraOrigem(){

 	var form = document.forms[0];
    form.quadraOrigemNM.disabled = false;
	
}
function habilitarSetorQuadraDestino(){

 	var form = document.forms[0];
    form.quadraDestinoNM.disabled = false;
	
}
function replicaDados(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}

function chamarPesquisaLocalidadeInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaLocalidadeFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}
	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;

	if (tipoConsulta == 'localidade') {
	     
	    if (form.tipoPesquisa.value == 'inicial') {
	    	form.localidadeDestinoID.value = codigoRegistro;
	        form.nomeLocalidadeDestino.value = descricaoRegistro;
	  	  form.nomeLocalidadeDestino.style.color = "#000000";	  
		    form.localidadeOrigemID.value = codigoRegistro;
		    form.nomeLocalidadeOrigem.value = descricaoRegistro;
		    form.nomeLocalidadeOrigem.style.color = "#000000";
		    form.localidadeOrigemID.focus();
	    } else {
	 		form.localidadeDestinoID.value = codigoRegistro;
		    form.nomeLocalidadeDestino.value = descricaoRegistro;
		    form.nomeLocalidadeDestino.style.color = "#000000";
	    }
   }

	
	

}


function limparLocalidade(tipo) {
    var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;
   	switch (tipo){
   		case 1:
   	   		
	   		if (!form.localidadeOrigemID.readOnly) {
			    form.localidadeDestinoID.readOnly = false;
			}
	    	form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
	    	form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
		    form.localidadeOrigemID.value = "";
		    form.nomeLocalidadeOrigem.value = "";
		    form.setorComercialOrigemCD.value = "";
		    form.setorComercialOrigemID.value = "";
		    form.nomeSetorComercialOrigem.value = "";
		    form.quadraOrigemNM.value = "";
		    form.quadraOrigemID.value = "";
  	    break;   
		case 2:
   			form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
		    form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
  	    break;   
   }
}

function limpar(tipo){
	var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;
   
	switch (tipo){
	//savio
	
     case 1:
		   form.idImovel.value = "";
		   form.inscricaoImovel.value = "";
	   
		   break;   
	   case 2:
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		  
		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
		   //savio
	   case 3:
		   form.localidadeDestinoID.value = "";
		   form.nomeLocalidadeDestino.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.localidadeOrigemID.value = "";
		   form.nomeLocalidadeOrigem.value = "";
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   form.codigoRotaOrigem.value = "";
		   form.codigoRotaDestino.value = "";
	
		   
		   break;  
	   case 4:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";


		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		case 5:
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
	
		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break;
		case 6:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

	   
		   //Coloca o foco no objeto selecionado
		   form.quadraDestinoNM.focus();
		   break;
		case 7:
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   
		   limpar(13);
		   form.localidadeOrigemID.focus();
		   break;
		case 8:
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   limpar(14);

		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
		case 9:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		
		   //Coloca o foco no objeto selecionado
		   form.localidadeDestinoID.focus();
		   break;
		 case 10:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		   
		 case 11:
		   
		   form.codigoCliente.value = "";
		   
		   form.localidadeDestinoID.value = "";
		   form.nomeLocalidadeDestino.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.localidadeOrigemID.value = "";
		   form.nomeLocalidadeOrigem.value = "";
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   form.codigoRotaOrigem.value = "";
		   form.codigoRotaDestino.value = "";
		   
		   break;
		   
		case 12:

		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break; 
		   
		case 13:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		
		   break;
		   
		case 14:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		   break;
		   
		case 15:
			limpar(16)
			form.codigoRotaOrigem.focus();
			
			break;
			
		case 16:
			break;
			
		case 17:
			
			form.codigoRotaDestino.focus();
			
			break;
			
	   default:
          break;
	}
}

function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade pra baixo
		
				form.nomeLocalidadeOrigem.value = "";
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";
				form.localidadeOrigemID.value = "";
		}
	}
	
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){

	
	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto , altura, largura);
		}
	}
	
}

  
  
function validarForm(form){

	
	var obrigatorio =false;
	var msg = "";
	var foco = 0;
	
	if(form.mes.value != "" ){
		
		 if(!verificaAnoMes(form.mes)){				
			  return false;			
		 }
	}else{
		alert("Informe o Ano/Mês Referência.")
	}
  	  
	  // Campos relacionados a rota
	  var rotaInicial = form.codigoRotaOrigem;
	  var rotaFinal = form.codigoRotaDestino;
	  
	  if(form.localidadeOrigemID.value != form.localidadeDestinoID.value &&  
			  form.localidadeOrigemID.value != "" && form.localidadeDestinoID.value != ""){

		  if(form.setorComercialOrigemCD.value != "" || form.setorComercialDestinoCD.value != ""){
			alert("Não é possível pesquisar por setor caso as localidades sejam diferentes.");
			return false;
		  }
	  }
	  
	  if(form.localidadeOrigemID.value != "" && form.localidadeDestinoID.value != ""){
		 
		    obrigatorio = validarInscricao(form);
		    
	  }
	    
    	if(form.codigoRotaOrigem.value != "" || form.codigoRotaDestino.value != ""){
    		
    		
    		var msg = "";
    	  	var foco = 0;
    	  	var obrigatorio = false;
    	  	
    	  	if (form.localidadeOrigemID.value == ""){
    	  		msg = "Informe Localidade da inscrição inicial";	
    	  		foco = 1;
    	  		obrigatorio = true;
    	  	}
    	  	
    	  	if (form.localidadeDestinoID.value == ""){
    	  		msg = msg + "\nInforme Localidade da inscrição final";	
    	  		
    	  		if (foco == 0){
    	  			foco = 2;
    	  		}
    	  		obrigatorio = true;
    	  	}
    	  	
    	  	if (form.setorComercialOrigemCD.value == ""){
    	  		msg = msg +  "\nInforme Setor Comercial da inscrição inicial";	
    	  		
    	  		if (foco == 0){
    	  			foco = 3;
    	  		}
    	  		obrigatorio = true;
    	  	}
    	  	
    	  	if (form.setorComercialDestinoCD.value == ""){
    	  		msg = msg + "\nInforme Setor Comercial da inscrição final";	
    	  		
    	  		if (foco == 0){
    	  			foco = 4;
    	  		}
    	  		obrigatorio = true;
    	  	}
    		
    		if (obrigatorio){
    			
    			alert(msg);
  		
		  		switch (foco) { 
	    			case 1: 
	       				form.codigoRotaOrigem.focus();
	       				break; 
	    			case 2: 
	       				form.codigoRotaDestino.focus();
	       				break; 
	    			case 3: 
	       				form.setorComercialOrigemCD.focus();
	       				break; 
	    			default: 
	       				form.setorComercialDestinoCD.focus();
	       				break;
				}
    		}
    		
    		else if(!obrigatorio && validate(form) && form.mes.value != ""){
    			form.submit();
    		}
    	}
    	else if (!obrigatorio && validate(form) && form.mes.value != ""){
    		form.submit();
    	}
	   
	  else if (!obrigatorio && validate(form) && form.mes.value != ""){
  		form.submit();
  	}
  	

	 
	  
	  	
	  	
	    
	  
	 	
	  
	
}


function validarRota(){

	var rotaInicial = form.codigoRotaOrigem;
	var rotaFinal = form.codigoRotaDestino;

	var obrigatorio = false;
	  	
	if (rotaInicial.value != ""){
		obrigatorio = campoObrigatorio(rotaFinal, rotaInicial, "Informe Rota final");
	}
	
	if (rotaFinal.value != ""){
		obrigatorio = campoObrigatorio(rotaInicial, rotaFinal, "Informe Rota inicial");
	}
	  	
	return obrigatorio;
}

function validarInscricao(form){

	// Campos relacionados a inscrição de origem
   	var localidadeOrigem = form.localidadeOrigemID;
   	var setorComercialOrigem = form.setorComercialOrigemCD;
   	var quadraOrigem = form.quadraOrigemNM;

   	// Campos relacionados a inscrição de destino
  	var localidadeDestino = form.localidadeDestinoID;
   	var setorComercialDestino = form.setorComercialDestinoCD;
   	var quadraDestino = form.quadraDestinoNM;
	  
	
	var obrigatorio = true;

	obrigatorio = campoObrigatorio(setorComercialOrigem, quadraOrigem, "Informe Setor Comercial da inscrição inicial");
	

	//Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial da inscrição final");
		
	}

	//Origem - Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, setorComercialOrigem, "Informe Setor Comercial da inscrição final");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, quadraOrigem, "Informe Quadra da inscrição final");
			
		}
	}
	
	//Destino - Origem
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialOrigem, setorComercialDestino, "Informe Setor Comercial da inscrição inicial");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraOrigem, quadraDestino, "Informe Quadra da inscrição inicial");
			
		}
	}
	
	return obrigatorio;
}  

function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}
	else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}



  function bloquearLocalidadeDestino(){
    var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;
      if(form.localidadeOrigemID.value.length > 0){
        form.localidadeDestinoID.readOnly = true;
      }else{
        form.localidadeDestinoID.readOnly = false;
      }
  }
  
  function verificarBotao(){
	  var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;
   
  	 if(form.localidadeOrigemID.value != "" && form.localidadeOrigemID.value != "" &&
  		form.setorComercialOrigemCD.value != "" && form.setorComercialDestinoCD.value != "" && 
  		form.setorComercialOrigemCD.value == form.setorComercialDestinoCD.value){
  		form.quadraOrigemNM.disabled = false;
  	}else{
  		form.quadraOrigemNM.disabled = true;
  	}
  	if(form.localidadeDestinoID.value != "" && form.localidadeDestinoID.value != "" &&
  		form.setorComercialDestinoCD.value != "" && form.setorComercialDestinoCD.value != "" && 
  		form.setorComercialDestinoCD.value == form.setorComercialOrigemCD.value){
  		form.quadraDestinoNM.disabled = false;
  	}else{
  		form.quadraDestinoNM.disabled = true;
  	}
  }
  
  function chmarPopupQuadra() {
  	var form = document.GerarOsAnormalidadeConsumoFiltrarActionForm;
     if(form.localidadeOrigemID.value == "" && form.localidadeDestinoID.value == ""){
		alert(" Informe Localidade ");      
     }else if(form.setorComercialOrigemCD.value == "" && form.setorComercialDestinoCD.value == ""){
     	alert(" Informe SetorComercial "); 
     }else{
	    abrirPopup('exibirSelecionarQuadraImovelInserirManterContaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value, 275, 480);	
	}
  }

  function limparLocalidadeAjax(tipo){
		var form = document.forms[0];
		if(tipo==1){
			var url = 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?limpaLocalidade=1';
		}else{
			var url = 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?limpaLocalidade=2';
		}
		$.ajax({
		   type: "POST",
		   url: url,
		   data: "",
		   success: function(msg){
			   
		   }
		 	});

	}

  function limparSetorAjax(tipo){
		var form = document.forms[0];
		if(tipo==1){
			var url = 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?limpaSetor=1';
		}else{
			var url = 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?limpaSetor=2';
		}
		$.ajax({
		   type: "POST",
		   url: url,
		   data: "",
		   success: function(msg){
			   
		   }
		 	});

	}

  
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="verificarBotao();">
<html:form action="/filtrarGerarOsAnormalidadeConsumoAction"
	name="GerarOsAnormalidadeConsumoFiltrarActionForm"
	type="gcom.gui.faturamento.GerarOsAnormalidadeConsumoFiltrarActionForm"
	method="post"
	onsubmit="return validateGerarOsAnormalidadeConsumoFiltrarActionForm(this);"
	>

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoPesquisa" />

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Imóveis para Gerar OS Anormalidade Consumo</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    		<strong> 
								<html:text property="mes" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" size="9" maxlength="7"></html:text>
							</strong>                
						</span>
					</td>
				</tr>
				<tr>
					<td><strong>Grupo:</strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    		<strong> 
								<html:select property="idGrupo" style="width: 230px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoGrupo" labelProperty="descricao" property="id" />
								</html:select>
							</strong>                
						</span>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Gerência Regional:</strong></td>
					<td colspan="3">
						<html:select property="idGerenciaRegional" tabindex="1" onchange="reloadForm()">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
						<html:select property="idUnidadeNegocio" tabindex="2" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" 
							labelProperty="nome" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Opção para Anormalidade de Consumo</strong></td>
					<td colspan="2">
						
							<html:radio property="anormalidadeConsumo" tabindex="2" value="1" />Estouro Consumo
							<html:radio property="anormalidadeConsumo" tabindex="3" value="2" />Baixo Consumo
							<html:radio property="anormalidadeConsumo" tabindex="3" value="3" />Ambos
									
					</td>
				</tr>
				<tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o inicial:</strong>
								</td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3">
												<html:text maxlength="3"
													property="localidadeOrigemID" size="3"
													onkeypress="limpar(7);validaEnterComMensagem(event, 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?objetoConsulta=1&inscricaoTipo=origem','localidadeOrigemID','Código da localidade de origem');return isCampoNumerico(event);"
													onkeyup="replicaDados(document.forms[0].localidadeOrigemID, document.forms[0].localidadeDestinoID);verificarBotao();"
													tabindex="1" /> 
													<a href="javascript:chamarPesquisaLocalidadeInicial();"><img
													   src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
													   width="23" height="21" title="Pesquisar Localidade">
													</a> 
												<logic:present name="corLocalidadeOrigem">
						
													<logic:equal name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
							
													<logic:notEqual name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
						
												</logic:present>
												<logic:notPresent name="corLocalidadeOrigem">
						
												<logic:empty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeOrigemID">
													<html:text property="nomeLocalidadeOrigem" value="" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:empty>
												<logic:notEmpty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeOrigemID">
													<html:text property="nomeLocalidadeOrigem" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent> 
												<a href="javascript:limparLocalidade(1);verificarBotao();limparLocalidadeAjax(1);"> 
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /> 
												</a>
										</td>
										</tr>
										<tr>
											<td><strong>Setor Comercial :</strong></td>
											<td colspan="3">
												<html:text  maxlength="3"
															property="setorComercialOrigemCD" 
															size="3"
															onkeypress="limpar(8); validaEnterDependenciaComMensagem(event, 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'localidade da inscrição inicial', 'Setor comercial inicial');return isCampoNumerico(event);"
															onkeyup="replicaDados(document.forms[0].setorComercialOrigemCD, document.forms[0].setorComercialDestinoCD);verificarBotao();"
															tabindex="2" /> 
													<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.GerarOsAnormalidadeConsumoFiltrarActionForm.localidadeOrigemID.value, 275, 480, 'Informe a Localidade da inscrição inicial');">
														<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
															 width="23" height="21" title="Pesquisar Setor Comercial">
													</a> 
												<logic:present name="corSetorComercialOrigem">
						
													<logic:equal name="corSetorComercialOrigem" value="exception">
														<html:text property="nomeSetorComercialOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
							
													<logic:notEqual name="corSetorComercialOrigem" value="exception">
														<html:text property="nomeSetorComercialOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
						
												</logic:present> 
												<logic:notPresent name="corSetorComercialOrigem">
						
												<logic:empty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="setorComercialOrigemCD">
													<html:text property="nomeSetorComercialOrigem" value="" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:empty>
												<logic:notEmpty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="setorComercialOrigemCD">
													<html:text property="nomeSetorComercialOrigem" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
						
											</logic:notPresent> <a
												href="javascript:limpar(2);verificarBotao();limparSetorAjax(1)"> <img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /> </a> <html:hidden
												property="setorComercialOrigemID" /></td>
										</tr>
										<tr>
										<tr>
											<td><strong>Quadra:</strong></td>
											<td colspan="2">
												<html:text maxlength="4" 
														   property="quadraOrigemNM"
														   size="4" 
														   tabindex="3" 
														   onkeypress="return isCampoNumerico(event);limpar(12);"
														   onkeyup="replicaDados(document.forms[0].quadraOrigemNM, document.forms[0].quadraDestinoNM);verificarBotao();" />
												<html:hidden property="quadraOrigemID" />
											</td>
											
										</tr>
				
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o Final:</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3" height="24">
											<html:text  maxlength="3"
														property="localidadeDestinoID" 
														size="3" 
														tabindex="6"
														onkeypress="limpar(13);validaEnterComMensagem(event, 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?objetoConsulta=1&inscricaoTipo=destino','localidadeDestinoID','Código da localidade de destino');return isCampoNumerico(event);" /> 
														<a href="javascript:chamarPesquisaLocalidadeFinal();"><img
													   src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
													   width="23" height="21" title="Pesquisar Localidade">
													</a> 
											<logic:present name="corLocalidadeDestino">
						
												<logic:equal name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
						
											</logic:present> 
											<logic:notPresent name="corLocalidadeDestino">
						
												<logic:empty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeDestinoID">
													<html:text property="nomeLocalidadeDestino" value="" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:empty>
												<logic:notEmpty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeDestinoID">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent> 
											<a href="javascript:limparLocalidade(2);limparLocalidadeAjax(2);">  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													 border="0" 
													 title="Apagar" /> 
											</a>
											</td>
										</tr>
										<tr>
											<td><strong>Setor Comercial :</strong></td>
											<td colspan="3">
											<html:text  maxlength="3"
														property="setorComercialDestinoCD" size="3"
														onkeyup="verificarBotao();"
														onkeypress="limpar(10); validaEnterDependenciaComMensagem(event, 'exibirFiltrarGerarOsAnormalidadeConsumoAction.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'localidade da inscrição final', 'Setor comercial final');return isCampoNumerico(event);"
														tabindex="7" /> 
												<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.GerarOsAnormalidadeConsumoFiltrarActionForm.localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição final');"><img
												   src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
												   border="0"
												   width="23" 
												   height="21" 
												   title="Pesquisar Setor Comercial">
												</a> 
												<logic:present name="corSetorComercialDestino">
						
													<logic:equal name="corSetorComercialDestino" value="exception">
														<html:text property="nomeSetorComercialDestino" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
							
													<logic:notEqual name="corSetorComercialDestino" value="exception">
														<html:text property="nomeSetorComercialDestino" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
						
												</logic:present> 
												<logic:notPresent name="corSetorComercialDestino">
							
													<logic:empty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
														property="setorComercialDestinoCD">
														<html:text property="nomeSetorComercialDestino" value=""
															size="45" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:empty>
													<logic:notEmpty name="GerarOsAnormalidadeConsumoFiltrarActionForm"
														property="setorComercialDestinoCD">
														<html:text property="nomeSetorComercialDestino" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
							
												</logic:notPresent> 
													<a href="javascript:limpar(4);verificarBotao();limparSetorAjax(2)"> 
														<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															 border="0" title="Apagar" /> 
													</a> <html:hidden property="setorComercialDestinoID" />
											</td>
										</tr>
										<tr>
											<td><strong>Quadra:</strong></td>
											<td colspan="3">
											<html:text maxlength="4" 
													   property="quadraDestinoNM"
													   size="4" 
													   tabindex="8" 
													   onkeypress="return isCampoNumerico(event);"/> 
												<html:hidden property="quadraDestinoID" onkeyup="verificarBotao();" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
					
				<tr>
					<td colspan="4" HEIGHT="15"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Inicial:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><html:text  maxlength="4" 
 											        property="codigoRotaOrigem"
													size="5" tabindex="12"
													onkeyup="replicaDados(document.forms[0].codigoRotaOrigem, document.forms[0].codigoRotaDestino);"
													onkeypress="return isCampoNumerico(event);limpar(15);" />
									</td>
								</tr>


							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>

				<!-- FIM ROTA INICIAL -->

				<tr>
					<td colspan="4" HEIGHT="5"></td>
				</tr>

				<!-- ROTA FINAL -->

				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Final:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><html:text  maxlength="4" 
													property="codigoRotaDestino"
													size="5" 
													tabindex="14" 
													onkeypress="return isCampoNumerico(event);limpar(17);"
													/>
									</td>
								</tr>
 
							</table>

							</td>
						</tr>
					</table>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
	        <tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campo
				Obrigat&oacute;rio</td>
			</tr>
					</td>
				</tr>

				<!-- FIM ROTA FINAL -->


			
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
						<input name="Submit22"
							   class="bottonRightCol" 
							   value="Limpar" 
							   tabindex="16" 
							   type="button"
							   onclick="window.location.href='/gsan/exibirFiltrarGerarOsAnormalidadeConsumoAction.do?menu=sim';">
					&nbsp; 
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol"
							   value="Cancelar" 
							   tabindex="17"
							   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
							   style="width: 80px" />
					</td>
					<td align="right">
						<input type="button" name="Button" value="Filtrar" 
							class="bottonRightCol"	tabindex="18" onclick="javascript:validarForm(document.forms[0]);"
								 />
								
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
