<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioSituacaoEspecialFaturamentoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript">

function validarForm(){
		
		var form = document.forms[0];
		
		if (((form.unidadeNegocio.value == '-1') ||
		(form.unidadeNegocio.value == '') ||
		(form.unidadeNegocio.value == null)) &&
		
		((form.gerenciaRegional.value == '-1') ||
		(form.gerenciaRegional.value == '') ||
		(form.gerenciaRegional.value == null))){
		
		      if (((form.localidadeInicialID.value == '') ||
		      (form.localidadeInicialID.value == null)) &&
		      
		      ((form.localidadeFinalID.value == '') ||
		      (form.localidadeFinalID.value == null))){
		            alert('Selecione pelo menos um campo de localização geográfica.');
		      } else
		      
		      if (((form.localidadeInicialID.value == '') ||
		      (form.localidadeInicialID.value == null)) &&
		      
		      ((form.localidadeFinalID.value != '') &&
		      (form.localidadeFinalID.value != null))){
		            form.localidadeFinalID.value = "";
		            form.localidadeFinalDesc.value = "";
		            alert('Informe Localidade Inicial.');
		      } 
		      
		      else 
		      
		      if (((form.localidadeInicialID.value != '') &&
		      (form.localidadeInicialID.value != null)) &&
		      
		      ((form.localidadeFinalID.value == '') ||
		      (form.localidadeFinalID.value == null))){
		            alert('Informe Localidade Final.');
		      }
		      
		      else
		      
		      if (((form.localidadeInicialID.value != '') &&
		      (form.localidadeInicialID.value != null)) &&
		      
		      ((form.localidadeFinalID.value != '') &&
		      (form.localidadeFinalID.value != null)) &&
		      
		      (form.localidadeInicialID.value > form.localidadeFinalID.value)){
		            alert('Localidade Inicial não pode ser superior a Localidade Final.');
		      }
		      
		      else
		      
		      if (((form.setorComercialInicialID.value == '') ||
		      (form.setorComercialInicialID.value == null)) &&
		      
		      ((form.setorComercialFinalID.value != '') &&
		      (form.setorComercialFinalID.value != null))){
		            form.setorComercialFinalID.value = "";
		            form.setorComercialFinalDesc.value = "";
		            alert('Informe o Setor Comercial Inicial.');
		      }  
		      
		      else
		      
		      if (((form.setorComercialInicialID.value != '') &&
		      (form.setorComercialInicialID.value != null)) &&
		      
		      ((form.setorComercialFinalID.value == '') ||
		      (form.setorComercialFinalID.value == null))){
		            alert('Informe o Setor Comercial Final.');
		      }
		
		      else
		      
		      if (((form.setorComercialInicialID.value != '') &&
		      (form.setorComercialInicialID.value != null)) &&
		      
		      ((form.setorComercialFinalID.value != '') &&
		      (form.setorComercialFinalID.value != null)) &&
		      
		      (form.setorComercialInicialID.value > form.setorComercialFinalID.value)){
		            alert('Setor Comercial Inicial não pode ser superior ao Setor Comercial Final.');
		      } else{
		            form.action='gerarRelatorioSituacaoEspecialFaturamentoAction.do';
	   		        submitForm(form)
		      }   
		} else{
		       form.action='gerarRelatorioSituacaoEspecialFaturamentoAction.do';
	   		   submitForm(form)
		}
	}

function validarCampos(){
		var form = document.forms[0];
		msg = verificarAtributosInicialFinal(form.localidadeInicialID,form.localidadeFinalID,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.setorComercialInicialID,form.setorComercialFinalID,"Setor Comercial");
			if( msg != ""){
				alert(msg);
				return false;
			}
		}
		return true;
	}
	
	function informeLocalidadeInicial(){
	      var form = document.forms[0];
	      
	      if ((form.localidadeInicialID.value == "") || (form.localidadeInicialID.value == null)){
	            alert('Informe Localidade Inicial.');
	            form.setorComercialInicialID.value = "";
	            form.setorComercialInicialDesc.value = "";
	            form.localidadeInicialID.focus();
	      }
	}
	
	function informeSetorComercialInicial(){
	      var form = document.forms[0];
	      
	      if ((form.setorComercialInicialID.value == "") || (form.setorComercialInicialID.value == null)){
	            alert('Informe o Setor Comercial Inicial.');
	            form.setorComercialInicialID.value = "";
	            form.localidadeInicialID.focus();
	      }
	}

function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinalID.value = formulario.localidadeInicialID.value;
		formulario.setorComercialInicialID.focus;
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
	
	function bloqueia(valor){
	    var form = document.forms[0];
	    var campo = null;
	    
	    if (valor == "1"){
	         campo = form.gerenciaRegional.value;
	         form.unidadeNegocio.value = "-1";
	    } else if (valor == "2"){
	         campo = form.unidadeNegocio.value;
	         form.gerenciaRegional.value = "-1";
	    } else if (valor == "3"){
	        if (form.gerenciaRegional.value != "-1") {
	             form.unidadeNegocio.disabled = true;
	             
	             form.localidadeInicialID.disabled = true;
	             form.localidadeFinalID.disabled = true;
	             form.localidadeInicialDesc.disabled = true;
		         form.localidadeInicialDesc.disabled = true;
	             
	             form.setorComercialInicialID.disabled = true;
		         form.setorComercialFinalID.disabled = true;
		         form.setorComercialInicialDesc.disabled = true;
		         form.setorComercialFinalDesc.disabled = true; 
		         		   
	        } else if (form.unidadeNegocio.value != "-1"){
	             form.gerenciaRegional.disabled = true;
	             
	             form.localidadeInicialID.disabled = true;
	             form.localidadeFinalID.disabled = true;
	             form.localidadeInicialDesc.disabled = true;
		         form.localidadeInicialDesc.disabled = true;
	             
	             form.setorComercialInicialID.disabled = true;
		         form.setorComercialFinalID.disabled = true;
		         form.setorComercialInicialDesc.disabled = true;
		         form.setorComercialFinalDesc.disabled = true;

	        } else if ((form.localidadeInicialID.value == "") &&  
	         (form.setorComercialInicialID.value == "")){
	               form.gerenciaRegional.disabled = false;
	               form.unidadeNegocio.disabled = false;
	               
	               form.setorComercialInicialID.disabled = true;
		           form.setorComercialFinalID.disabled = true;
		           form.setorComercialInicialDesc.disabled = true;
		           form.setorComercialFinalDesc.disabled = true;
	        
	         } else {
	               form.gerenciaRegional.disabled = true;
	               form.unidadeNegocio.disabled = true;
	               
	               form.setorComercialInicialID.disabled = false;
		           form.setorComercialFinalID.disabled = false;
		           form.setorComercialInicialDesc.disabled = false;
		           form.setorComercialFinalDesc.disabled = false;
	         }
	    }
	    
	    if ((valor == "1") || (valor == "2")) {
	     
	 		 procuraSetorComercialFinal
	 		 
	         if (campo != "-1"){
	               form.localidadeInicialID.value = "";
		           form.localidadeFinalID.value = "";
		           form.localidadeInicialDesc.value = "";
		           form.localidadeFinalDesc.value = "";
		     
		           form.setorComercialInicialID.value = "";
		           form.setorComercialFinalID.value = "";
		           form.setorComercialInicialDesc.value = "";
		           form.setorComercialFinalDesc.value = "";
	               
	               if (valor == "1"){
	                    form.unidadeNegocio.disabled = true;
	               } else if (valor == "2"){
	                    form.gerenciaRegional.disabled = true;
	               }	     
		           
		           form.localidadeInicialID.disabled = true;
                   form.localidadeFinalID.disabled = true;
             
                   form.setorComercialInicialID.disabled = true;
                   form.setorComercialFinalID.disabled = true;
	         } else {
	               form.localidadeInicialID.value = "";
		           form.localidadeFinalID.value = "";
		           form.localidadeInicialDesc.value = "";
		           form.localidadeFinalDesc.value = "";
		     
		           form.setorComercialInicialID.value = "";
		           form.setorComercialFinalID.value = "";
		           form.setorComercialInicialDesc.value = "";
		           form.setorComercialFinalDesc.value = "";
		           
		           if (valor == "1"){
		                form.unidadeNegocio.disabled = false;
		           } else if(valor == "2"){
		                form.gerenciaRegional.disabled = false;
		           }
		     
	      	       form.localidadeInicialID.disabled = false;
                   form.localidadeFinalID.disabled = false;
             
                   form.setorComercialInicialID.disabled = false;
                   form.setorComercialFinalID.disabled = false;
	         } 
	     }
	  }    
  	
  	function limpar(){
  		var form = document.forms[0];
  		form.action='exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?limpar=sim';
	    form.submit();
	    limparOrigem(1);
	    
  	}
	
	function limparOrigem(tipo){
		var form = document.forms[0];

		switch(tipo){
		
		case 1: //De localidade pra baixo

			if (!form.localidadeInicialID.disabled){
				form.localidadeInicialDesc.value = "";
				form.localidadeFinalID.value = "";
				form.localidadeFinalDesc.value = "";
				form.setorComercialInicialID.value = "";
				form.setorComercialInicialDesc.value = "";
			    form.setorComercialFinalID.value = "";
			    form.setorComercialFinalDesc.value = "";
			  	form.situacao.value = "-1";
			   	form.motivo.value = "-1";
			}
		   	break;
		    
		case 2: //De setor para baixo

			if (!form.setorComercialInicialID.disabled){
			   form.setorComercialInicialDesc.value = "";
			   form.setorComercialFinalID.value = "";
			   form.setorComercialFinalDesc.value = "";
			   form.situacao.value = "-1";
			   form.motivo.value = "-1";
			}
		    break;				
		}
	}
	
	function limparDestino(tipo){
		var form = document.GerarRelatorioSituacaoEspecialFaturamentoActionForm;

		if (!form.localidadeFinalID.disabled || !form.setorComercialFinalID.disabled){
			switch(tipo){
				case 1:
				        form.localidadeFinalDesc.value = "";  
				break;
					
				case 2: 
					 	form.setorComercialFinalDesc.value = "";
				break;  
			}
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				if (!form.localidadeInicialID.disabled){
					form.localidadeInicialID.value = "";
					form.localidadeInicialDesc.value = "";
					form.localidadeFinalID.value = "";
					form.localidadeFinalDesc.value = "";
					
					form.setorComercialInicialID.value = "";
			     	form.setorComercialInicialDesc.value = "";
			     	form.setorComercialFinalID.value = "";
			     	form.setorComercialFinalDesc.value = "";
			     	
			     	form.gerenciaRegional.disabled = false;
			     	form.unidadeNegocio.disabled = false;
			     	
			     	form.localidadeInicialID.focus();
				}
				break;
			case 2: //De setor para baixo
				if (!form.setorComercialInicialID.disabled){
			     	form.setorComercialInicialID.value = "";
			     	form.setorComercialInicialDesc.value = "";
			     	form.setorComercialFinalID.value = "";
			     	form.setorComercialFinalDesc.value = "";
			  
			     	form.setorComercialInicialID.focus();
				}
		     	break;
		}
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinalID.value = formulario.setorComercialInicialID.value;
		formulario.localidadeFinalID.focus;
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				if (!form.localidadeFinalID.disabled){
					form.localidadeFinalID.value = "";
					form.localidadeFinalDesc.value = "";
					form.setorComercialFinalID.value = ""; 
			   		form.setorComercialFinalDesc.value = "";
					form.localidadeFinalID.focus();
				}
				break;
				
			case 2: //De setor para baixo
				if (!form.setorComercialFinalID.disabled){	   
			   		form.setorComercialFinalID.value = ""; 
			   		form.setorComercialFinalDesc.value = "";
			   		form.setorComercialFinalID.focus();
				}
		   		break;		   
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
        
		var form = document.forms[0];

		if ((tipoConsulta == 'localidadeOrigem') && (form.gerenciaRegional.value == "-1") && (form.unidadeNegocio.value == "-1")) {
		    form.localidadeInicialID.value = codigoRegistro;
	  		form.localidadeInicialDesc.value = descricaoRegistro;
	  		form.localidadeFinalID.value = codigoRegistro;
      		form.localidadeFinalDesc.value = descricaoRegistro;
	  		form.localidadeInicialDesc.style.color = "#000000";
	  		form.localidadeFinalDesc.style.color = "#000000";
		    form.action='exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?campoDesabilita=1';
	   		form.submit();
		}

		if ((tipoConsulta == 'localidadeDestino') && (form.gerenciaRegional.value == "-1") && (form.unidadeNegocio.value == "-1")) {
      		form.localidadeFinalID.value = codigoRegistro;
      		form.localidadeFinalDesc.value = descricaoRegistro;
	  		form.localidadeFinalDesc.style.color = "#000000";	
	  		
	   		form.action='exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?campoDesabilita=1';
	   		form.submit();
		}
	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if ((tipoConsulta == 'setorComercialOrigem') && (form.gerenciaRegional.value == "-1") && (form.unidadeNegocio.value == "-1")) {
		  	form.setorComercialInicialID.value = codigoRegistro;
		  	form.setorComercialInicialDesc.value = descricaoRegistro;
		  	form.setorComercialInicialDesc.style.color = "#000000"; 
		  	
		  	form.setorComercialFinalID.value = codigoRegistro;
		  	form.setorComercialFinalDesc.value = descricaoRegistro;
		  	form.setorComercialFinalDesc.style.color = "#000000";	
		  	
		  	form.action='exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?campoDesabilita=2';
	   		form.submit();
		}

		if ((tipoConsulta == 'setorComercialDestino') && (form.gerenciaRegional.value == "-1") && (form.unidadeNegocio.value == "-1")) {
		  	form.setorComercialFinalID.value = codigoRegistro;
		  	form.setorComercialFinalDesc.value = descricaoRegistro;
		  	form.setorComercialFinalDesc.style.color = "#000000";
		  	
		  	form.action='exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?campoDesabilita=2';
	   		form.submit();
		}
	}
	
	function pesquisarLocalidadeOrigem() {
		var form = document.forms[0];

		if (((form.gerenciaRegional.value == "-1") || (form.gerenciaRegional.value == null)) && 
		((form.unidadeNegocio.value == "-1") || (form.unidadeNegocio.value == null))) {
              chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicialID);
              limparOrigem(1);			
		}
	}

    function pesquisarLocalidadeDestino() {
		var form = document.forms[0];

		if (((form.gerenciaRegional.value == "-1") || (form.gerenciaRegional.value == null)) && 
		((form.unidadeNegocio.value == "-1") || (form.unidadeNegocio.value == null))) {
              chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'idLocalidade', document.forms[0].localidadeInicialID.value, 275, 480, 'Informe Localidade Inicial.',document.forms[0].localidadeFinalID);			
		}
	}
	
	function pesquisarSetorComercialOrigem() {
		var form = document.forms[0];

		if (((form.gerenciaRegional.value == "-1") || (form.gerenciaRegional.value == null)) && 
		((form.unidadeNegocio.value == "-1") || (form.unidadeNegocio.value == null))) {
		      chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', null, 275, 480, null, document.forms[0].setorComercialInicialID); 
		      limparOrigem(2);     			
		}
	}
	
	function pesquisarSetorComercialDestino() {
		var form = document.forms[0];

		if (((form.gerenciaRegional.value == "-1") || (form.gerenciaRegional.value == null)) && 
		((form.unidadeNegocio.value == "-1") || (form.unidadeNegocio.value == null))) {
		      chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].setorComercialInicialID.value, 275, 480, 'Informe o Setor Comercial Inicial.',document.forms[0].setorComercialFinalID);
			  limparDestino(2);     			
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="bloqueia('3');">
<div id="formDiv">
<html:form action="/gerarRelatorioSituacaoEspecialFaturamentoAction"
	name="GerarRelatorioSituacaoEspecialFaturamentoActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioSituacaoEspecialFaturamentoActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
					<td class="parabg">Gerar Relat&oacute;rio de Situação Especial de Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<strong>Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegional" 
							style="width: 230px;" tabindex="1"
                            onchange="javascript:bloqueia('1');"
                            onfocus="javascript:bloqueia('3');">
							
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
							
							<logic:present name="colecaoGerenciaRegional" scope="request">
								<html:options collection="colecaoGerenciaRegional"
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
						<html:select property="unidadeNegocio" style="width: 230px;" 
						onchange="javascript:bloqueia('2');" 
						onfocus="javascript:bloqueia('3');"
						tabindex="2">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
							
							<logic:present name="colecaoUnidadeNegocio" scope="request">
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>				
              	
              	<td height="10">
				</td>
              	
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="3"
							property="localidadeInicialID" 
							size="3"
							onchange="javascript:bloqueia('3');"
							onblur="javascript:replicarLocalidade();bloqueia('3');"
							onkeyup="javascript:limparOrigem(1);bloqueia('3');"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?objetoConsulta=1&campoDesabilita=1','localidadeInicialID','Localidade Inicial');return isCampoNumerico(event);"/>
							
							<a tabindex="4" href="javascript:pesquisarLocalidadeOrigem();">
							<img width="23"
							    id="procuraLocalidadeInicial" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="localidadeInicialDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="5"								
                 				style="background-color:#EFEFEF; border:0; color: #000000; width : 260px;" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="localidadeInicialDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="5"  
								style="background-color:#EFEFEF; border:0; color: red; width : 260px;" />                                   
						</logic:notPresent>
						
						<a tabindex="6" href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0"
								title="Apagar" id="limpaLocalidadeInicial"/></a>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="7"
							property="setorComercialInicialID" 
							size="3"
							onblur="javascript:replicarSetorComercial();bloqueia('3');"
							onkeyup="javascript:limparOrigem(2);bloqueia('3');"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?objetoConsulta=2&campoDesabilita=2','setorComercialInicialID','Setor Comercial Inicial');return isCampoNumerico(event);"
							onchange="javascript:bloqueia('3');"/>
								
							<a tabindex="8" href="javascript:pesquisarSetorComercialOrigem();">
							<img 
							    id="procuraSetorComercialInicial"
							    width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="setorComercialInicialDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="9"
								style="background-color:#EFEFEF; border:0; color: #000000; width : 260px;" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="setorComercialInicialDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="9"
								style="background-color:#EFEFEF; border:0; color: red; width : 260px;" />
						</logic:notPresent>
						
						<a tabindex="10" href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								id="limpaSetorComercialInicial"
								border="0" 
								title="Apagar" /></a>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						<html:text maxlength="3" 
							tabindex="11"
							property="localidadeFinalID" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?objetoConsulta=3&campoDesabilita=1','localidadeFinalID','Localidade Final');return isCampoNumerico(event);"
							onkeyup="javascript:informeLocalidadeInicial();limparDestino(1);"
							onclick="javascript:informeLocalidadeInicial();"
							onchange="limparDestino(1);"
							/>
							
							<a tabindex="12" href="javascript:pesquisarLocalidadeDestino();">
							<img 
							    id="procuralocalidadeFinal"
							    width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 
						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="localidadeFinalDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="13"
								style="background-color:#EFEFEF; border:0; color: #000000; width : 260px;" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="localidadeFinalDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="13"
								style="background-color:#EFEFEF; border:0; color: red; width : 260px;" />
						</logic:notPresent>
						
						<a tabindex="14" href="javascript:limparBorrachaDestino(1);"> 
							<img id="limpalocalidadeFinal" 
							    src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" property="setorComercialFinalID"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioSituacaoEspecialFaturamentoAction.do?objetoConsulta=4&campoDesabilita=2', 'setorComercialFinalID', 'Localidade Final');return isCampoNumerico(event);"
							onkeyup = "javascript:informeSetorComercialInicial();limparDestino(2);"
							onclick="javascript:informeSetorComercialInicial();"
							onchange="limparDestino(2);"
							tabindex="15"/>
							
							<a tabindex="16" href="javascript:pesquisarSetorComercialDestino();">
							<img
							    id="procuraSetorComercialFinal" 
							    width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="setorComercialFinalDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="17"
								style="background-color:#EFEFEF; border:0; color: #000000; width : 260px;" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="setorComercialFinalDesc" 
								size="30"
								maxlength="30" 
								readonly="true"
								tabindex="17"
								style="background-color:#EFEFEF; border:0; color: red; width : 260px;"/>
						</logic:notPresent>
						
						<a tabindex="18" href="javascript:limparBorrachaDestino(2);"> 
							<img
							    id="limpaSetorComercialFinal" 
							    src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
						
					</td>
				</tr>
				
				<td height="10">
				</td>
				
				<tr> 
                	<td><strong>Situa&ccedil;&atilde;o:</strong></td>
	                <td colspan="6"><span class="style2"><strong>
						<html:select property="situacao" 
									 style="width: 420px; height:100px;" 
									 multiple="true" 
									 tabindex="19">
											
						    <logic:notEmpty name="colecaoFaturamentoSituacaoTipo" scope="session">
						            	<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
						            	<html:options collection="colecaoFaturamentoSituacaoTipo" labelProperty="descricao" property="id" />
					        </logic:notEmpty>
							
						</html:select>                
	                  </strong></span>
	                </td>
                </tr>
                
                <td height="10">
				</td>
                
                <tr> 
                	<td><strong>Motivo:</strong></td>
	                <td colspan="6"><span class="style2"><strong>
						<html:select property="motivo" 
									 style="width: 420px; height:100px;" 
									 multiple="true"
									 tabindex="20">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						    <logic:present name="colecaoFaturamentoSituacaoMotivo" scope="session">
						            	<html:options collection="colecaoFaturamentoSituacaoMotivo" labelProperty="descricao" property="id" />
					        </logic:present>
						</html:select>                
	                  </strong></span>
	                </td>
                </tr>			
		
		        <td height="10">
				</td>
		
				<tr>
					<td height="24" colspan="2">
			   
			            <input type="button" tabindex="23"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
			   
			            &nbsp;
			            
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar"
			          		tabindex="22" 
			          		onclick="javascript:limpar();"/>
			          		       			
					</td>
				
					<td align="right">
						<input type="button"
						    tabindex="21" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()"/>
					
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>	
</body>
</html:html>