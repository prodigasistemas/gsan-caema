<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.empresa.EmpresaCobrancaFaixa"%>

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
	formName="InserirEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		var submeterFormulario = true;
		
		if (validateInserirEmpresaActionForm(form)){

			if (comparaData(form.dataInicioContratoCobranca.value, ">", form.dataEncerramentoContratoCobranca.value )) {
				submeterFormulario = false;		
				alert('Data incial maior que Data final');
			}else if (form.indicadorEmpresaCobranca[0].checked){
				if (form.dataInicioContratoCobranca.value == "" 
					    && form.percentualPagamento.value == ""
					    && (form.percentualDaFaixaInformado.value != null
						&& form.percentualDaFaixaInformado.value != 'sim'
						&& form.percentualDaFaixaInformado.value != 'SIM')){
					submeterFormulario = false;
				    alert('Informe Data de Início do Contrato.\nInforme Percentual do Pagamento das Contas Cobradas.');	
				
				} 
				
				else if(form.dataInicioContratoCobranca.value == ""){
					submeterFormulario = false;
					alert('Informe Data de Início do Contrato.');
				}
				
				else if (form.percentualPagamento.value == ""
				        && (form.quantidadeMinimaContas.value == ""
					    && form.percentualDaFaixa.value == ""
					    && form.percentualImovelFaixa.value == "")
					    && (form.percentualDaFaixaInformado.value != null
						&& form.percentualDaFaixaInformado.value != 'sim'
						&& form.percentualDaFaixaInformado.value != 'SIM')){
						submeterFormulario = false;
					    alert('Informe Percentual do Pagamento das Contas Cobradas.');
				} 	  
				
				else if ((form.percentualPagamento.value != '') &&
				(form.percentualPagamento.value != null) && 
				(temLetra(form.percentualPagamento.value) == 1)){
			    submeterFormulario = false;
			    alert('Valor informado do percentual está inválido.');
		        }  
				
				else if ((form.quantidadeMesValidoPagamento.value == "") 
			 	    && (form.dataEncerramentoContratoCobranca.value != ""
			 	    && form.dataEncerramentoContratoCobranca.value != null)
					&& (form.quantidadeMesValidoPagamento.value != null
				    && form.quantidadeMesValidoPagamento.value != 'sim'
				    && form.quantidadeMesValidoPagamento.value != 'SIM')){
					submeterFormulario = false;
					alert('Informe Quantidade de meses para pagamento.');
					form.quantidadeMesValidoPagamento.disabled = false;
					form.quantidadeMesValidoPagamento.readOnly = false;
					
				}	
			    
			    else if (temLetra(form.quantidadeMesValidoPagamento.value) == 1){
				submeterFormulario = false;
				alert('Valor informado da quantidade de meses para pagamento está inválido.');
			    }
			    
			    else if (((form.quantidadeMinimaContas.value) != '') &&
		        ((temLetra(form.quantidadeMinimaContas.value) == 1) ||
		        (temCaractere(form.quantidadeMinimaContas.value) == 1))) {
		              submeterFormulario = false;
		              alert('Valor quantidade mínima de contas está inválida.');
		        }		
		        else if ((form.percentualDaFaixa.value != '' 
		        && !validaPercentual(form.percentualDaFaixa))
		        || (temLetra(form.percentualDaFaixa.value) == 1)) {
			    submeterFormulario = false;
			    alert('Valor informado do percentual está inválido.');
		        } else if (((form.percentualImovelFaixa.value != "") 
		        && (!validaPercentual(form.percentualImovelFaixa)))
		        || (temLetra(form.percentualImovelFaixa.value) == 1)){
			    submeterFormulario = false;
			    alert('Valor informado do percentual está inválido.');		
		        }
			    
			    else if (form.percentualPagamento.value == ""
				        && (form.quantidadeMinimaContas.value != ""
					    || form.percentualDaFaixa.value != ""
					    || form.percentualImovelFaixa.value != "")
					    && (form.percentualDaFaixaInformado.value != null
						&& form.percentualDaFaixaInformado.value != 'sim'
						&& form.percentualDaFaixaInformado.value != 'SIM')){
					submeterFormulario = false;
					alert('Informe pelo menos uma Faixa de Cobrança');
				}
				
				else if (form.percentualPagamento.value == ""
				        && (form.quantidadeMinimaContas.value != ""
					    || form.percentualDaFaixa.value != ""
					    || form.percentualImovelFaixa.value != "")
					    && (form.percentualDaFaixaInformado.value != null
						&& (form.percentualDaFaixaInformado.value == 'sim'
						|| form.percentualDaFaixaInformado.value == 'SIM'))){
					
					if (confirm("Existem dados informados para faixa de cobrança, mas que não foram adicionados. Estes serão desconsiderados. Dejesa prosseguir?")) {
                        submeterFormulario = true;
                    }else{
                        submeterFormulario = false;                    
                    }	
                 
				}
				
			    else{
					submeterFormulario = true;
				}
 			}
			
			if(form.dataEncerramentoContratoCobranca.value != null && form.dataEncerramentoContratoCobranca.value != ''){
				form.quantidadeMesValidoPagamento.disabled = false;
				form.quantidadeMesValidoPagamento.readOnly = false;
			}
 				
			if(submeterFormulario){
				//submeterFormPadrao(form);
				submitForm(form) 
			}			
		}	
}	
	
	//function required () {
	//	this.aa = new Array("dataInicioContratoCobranca", "Informe Data do Início do Contrato.", new Function ("varName", " return this[varName];"));
		//this.ab = new Array("percentualPagamento", "Informe Percentual do Pagamento das Contas Cobradas.", new Function ("varName", " return this[varName];"));
	//}
                                                          
	function limparForm() {
		var form = document.InserirEmpresaActionForm;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.email.value = "";
		form.indicadorEmpresaPrincipal[1].checked = "2" ;
		form.indicadorEmpresaCobranca[1].checked = "2" ;
		form.indicadorLeitura[1].checked = "1" ;
		form.dataInicioContratoCobranca.value = "";
		form.dataInicioContratoCobranca.style.color = "#000000";
		form.dataInicioContratoCobranca.style.background = "#EFEFEF";
		form.dataInicioContratoCobranca.disabled = true;		
		form.dataEncerramentoContratoCobranca.value = "";
		form.dataEncerramentoContratoCobranca.style.color ="#000000";
		form.dataEncerramentoContratoCobranca.style.background = "#EFEFEF";
		form.dataEncerramentoContratoCobranca.disabled = true;		
		form.percentualPagamento.value = "";
		form.percentualPagamento.style.color = "#000000";
		form.percentualPagamento.style.background = "#EFEFEF";
		form.percentualPagamento.disabled = true;
		form.quantidadeMesValidoPagamento.value = "";
		form.quantidadeMesValidoPagamento.style.color = "#000000";
		form.quantidadeMesValidoPagamento.style.background = "#EFEFEF";
		form.quantidadeMesValidoPagamento.disabled = true;		
		form.quantidadeMinimaContas.value = "";
		form.percentualDaFaixa.value = "";
		form.percentualImovelFaixa.value = "";
		form.action = 'exibirInserirEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}
	
	function bloqueiaDadosEmpresaCobranca(){
		var form = document.forms[0];
		
		if(form.indicadorEmpresaCobranca[0].checked){
			
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.readOnly = false;
			form.dataInicioContratoCobranca.style.backgroundColor = '';
			
			form.dataEncerramentoContratoCobranca.style.color = "#000000";
			form.dataEncerramentoContratoCobranca.readOnly = false;
			form.dataEncerramentoContratoCobranca.style.backgroundColor = '';			
				
			
			if (form.percentualDaFaixaInformado.value != null
					&& form.percentualDaFaixaInformado.value != 'sim'
					&& form.percentualDaFaixaInformado.value != 'SIM'){
					
	             form.percentualPagamento.style.color = "#000000";
			     form.percentualPagamento.readOnly = false;
			     form.percentualPagamento.style.backgroundColor = '';
			     
			     if ((form.percentualPagamento.value != '') &&
			     (form.percentualPagamento.value != null)){
			     
			     form.quantidadeMinimaContas.value = "";
			     form.quantidadeMinimaContas.style.color = "#000000";
			     form.quantidadeMinimaContas.readOnly = true;
			     form.quantidadeMinimaContas.style.backgroundColor = '#EFEFEF';
			
			     form.percentualDaFaixa.value = "";
			     form.percentualDaFaixa.style.color = "#000000";
			     form.percentualDaFaixa.readOnly = true;
			     form.percentualDaFaixa.style.backgroundColor = '#EFEFEF';
			
		         form.percentualImovelFaixa.value = "";
			     form.percentualImovelFaixa.style.color = "#000000";
			     form.percentualImovelFaixa.readOnly = true;
			     form.percentualImovelFaixa.style.backgroundColor = '#EFEFEF';
			     
			     } else {
			     
			     form.percentualDaFaixa.style.color = "#000000";
			     form.percentualDaFaixa.readOnly = false;
			     form.percentualDaFaixa.style.backgroundColor = '';
			
			     form.quantidadeMinimaContas.style.color = "#000000";
			     form.quantidadeMinimaContas.readOnly = false;
			     form.quantidadeMinimaContas.style.backgroundColor = '';
			
		   	     form.percentualImovelFaixa.style.color = "#000000";
			     form.percentualImovelFaixa.readOnly = false;
			     form.percentualImovelFaixa.style.backgroundColor = '';
			      
			     }
			     
	        	
			} else{
				 form.adicionarFaixa.disabled = false;
			     
			     form.percentualPagamento.value = "";
			     form.percentualPagamento.style.color = "#000000";
			     form.percentualPagamento.readOnly = true;
			     form.percentualPagamento.style.backgroundColor = '#EFEFEF';
			     
			     form.percentualDaFaixa.style.color = "#000000";
			     form.percentualDaFaixa.readOnly = false;
			     form.percentualDaFaixa.style.backgroundColor = '';
			
			     form.quantidadeMinimaContas.style.color = "#000000";
			     form.quantidadeMinimaContas.readOnly = false;
			     form.quantidadeMinimaContas.style.backgroundColor = '';
			
		   	     form.percentualImovelFaixa.style.color = "#000000";
			     form.percentualImovelFaixa.readOnly = false;
			     form.percentualImovelFaixa.style.backgroundColor = '';
					
			}
			
			if ((form.dataEncerramentoContratoCobranca.value != null) &&
	        (form.dataEncerramentoContratoCobranca.value != "")){
			     form.quantidadeMesValidoPagamento.style.color = "#000000";
			     form.quantidadeMesValidoPagamento.readOnly = false;
			     form.quantidadeMesValidoPagamento.style.backgroundColor = '';
	        } else {
	             form.quantidadeMesValidoPagamento.value = "";
			     form.quantidadeMesValidoPagamento.style.color = "#000000";
			     form.quantidadeMesValidoPagamento.readOnly = true;
			     form.quantidadeMesValidoPagamento.style.backgroundColor = '#EFEFEF';
	        }
			
			form.adicionarFaixa.disabled = false;
			
			var calendarioIni = document.getElementById('calendarioIni');
	 		var calendarioFim = document.getElementById('calendarioFim'); 

	 		calendarioIni.style.display = "";
	 		calendarioFim.style.display = "";
			
	 	 }else{
	 	 
	 	 	form.dataInicioContratoCobranca.value = "";
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.readOnly = true;
			form.dataInicioContratoCobranca.style.backgroundColor = '#EFEFEF';
	 	 
            form.dataEncerramentoContratoCobranca.value = "";
			form.dataEncerramentoContratoCobranca.style.color = "#000000";
			form.dataEncerramentoContratoCobranca.readOnly = true;
			form.dataEncerramentoContratoCobranca.style.backgroundColor = '#EFEFEF';	 	 
	 	 
			form.percentualPagamento.value = "";
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.readOnly = true;
			form.percentualPagamento.style.backgroundColor = '#EFEFEF'; 	 
	 	 
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.readOnly = true;
			form.percentualDaFaixa.style.backgroundColor = '#EFEFEF';
			
	 	    form.quantidadeMesValidoPagamento.value = "";
			form.quantidadeMesValidoPagamento.style.color = "#000000";
			form.quantidadeMesValidoPagamento.readOnly = true;
			form.quantidadeMesValidoPagamento.style.backgroundColor = '#EFEFEF';
	 	 
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.readOnly = true;
			form.quantidadeMinimaContas.style.backgroundColor = '#EFEFEF';
			
			form.percentualImovelFaixa.value = "";
			form.percentualImovelFaixa.style.color = "#000000";
			form.percentualImovelFaixa.readOnly = true;
			form.percentualImovelFaixa.style.backgroundColor = '#EFEFEF';
			
			var calendarioIni = document.getElementById('calendarioIni');
	 		var calendarioFim = document.getElementById('calendarioFim'); 

	 		calendarioIni.style.display = "none";
	 		calendarioFim.style.display = "none";
			
			form.adicionarFaixa.disabled = true;
	 	 }
	} 

	function adicionarEmpresaContratoCobranca(){
		var form = document.forms[0];
		
		if ((form.percentualPagamento.value == "") &&
		(form.quantidadeMinimaContas.value == "") &&
		(form.percentualDaFaixa.value == "") &&
		(form.percentualImovelFaixa.value == "") && 
		(form.percentualDaFaixaInformado.value != null) && 
		(form.percentualDaFaixaInformado.value != 'sim') &&
		(form.percentualDaFaixaInformado.value != 'SIM')){
		       alert("Informe Percentual da Faixa");
		} else
		
		if ((form.quantidadeMinimaContas.value == "") ||
		(form.quantidadeMinimaContas.value == null)){
		       alert('Informe quantidade mínima de contas.');
		} else
		
		if ((form.percentualDaFaixa.value == "") ||
		(form.percentualDaFaixa.value == null)){
		       alert('Informe Percentual da Faixa.');
		} else
		
        if (((form.quantidadeMinimaContas.value) != '') &&
		((temLetra(form.quantidadeMinimaContas.value) == 1) ||
		(temCaractere(form.quantidadeMinimaContas.value) == 1))) {
		      alert('Valor quantidade mínima de contas está inválida.');
		}		
		
		else if ((form.percentualDaFaixa.value != '' 
		&& !validaPercentual(form.percentualDaFaixa))
		|| (temLetra(form.percentualDaFaixa.value) == 1)) {
			alert('Valor informado do percentual está inválido.');
		} else if (((form.percentualImovelFaixa.value != "") 
		&& (!validaPercentual(form.percentualImovelFaixa)))
		|| (temLetra(form.percentualImovelFaixa.value) == 1)){
			alert('Valor informado do percentual está inválido.');		
		}		
		
		else {
			form.action = 'exibirInserirEmpresaAction.do?adicionarFaixa=sim';
			form.submit();
		}
	}

	function validaPercentual(percentual){
		if (obterNumerosSemVirgulaEPonto(percentual.value) > 10000
			|| obterNumerosSemVirgulaEPonto(percentual.value) <= 0 ) {
			return false;
		}
		
		return true;
	}
	
	function temCaractere(texto){
        var caracteres = "./,";
        var retorno;
        texto = texto.toLowerCase();
        for(i=0; i<texto.length; i++){
             if (caracteres.indexOf(texto.charAt(i),0)!=-1){
             retorno = 1;
             i = texto.length;
             }else{
             retorno = 0;
             }
        }
        return retorno;;
   }

   function temLetra(texto){
        var numeros = ",.0123456789";
        var retorno;	
        texto = texto.toLowerCase();
        for(i=0; i<texto.length; i++){
             if (numeros.indexOf(texto.charAt(i),0)!=-1){
             retorno = 0;
             }else{
             retorno = 1;
             i = texto.length;
             }
        }
        return retorno;
   }
	
	function bloqueiaCampos(desbloqueia){
		var form = document.forms[0];			
		
		if(form.indicadorEmpresaCobranca[0].checked == "true"){
		
		if (form.percentualPagamento.value != null && form.percentualPagamento.value != '') {
			form.adicionarFaixa.disabled = true;
			
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.readOnly = true;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '#EFEFEF';
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.readOnly = true;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '#EFEFEF';
			form.percentualImovelFaixa.value = "";
			form.percentualImovelFaixa.readOnly = true;
			form.percentualImovelFaixa.style.color = "#000000";
			form.percentualImovelFaixa.style.backgroundColor = '#EFEFEF';
			
			form.percentualPagamento.readOnly = false;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '';
			
			form.quantidadeMesValidoPagamento.readOnly = false;
			form.quantidadeMesValidoPagamento.style.color = "#000000";
			form.quantidadeMesValidoPagamento.style.backgroundColor = '';			
			
		} else if ((form.quantidadeMinimaContas.value != null && form.quantidadeMinimaContas.value != '')
			|| (form.percentualDaFaixa.value != null && form.percentualDaFaixa.value != '')
			|| (form.percentualImovelFaixa.value != null) && (form.percentualImovelFaixa.value != '')
			|| (form.percentualDaFaixaInformado.value != null
					&& (form.percentualDaFaixaInformado.value == 'sim'
						|| form.percentualDaFaixaInformado.value == 'SIM'))) {
			form.adicionarFaixa.disabled = false;
			
			form.percentualPagamento.value = "";
			form.percentualPagamento.readOnly = true;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '#EFEFEF';
			form.quantidadeMinimaContas.readOnly = false;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '';
			form.percentualDaFaixa.readOnly = false;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '';
			form.percentualImovelFaixa.readOnly = false;
			form.percentualImovelFaixa.style.color = "#000000";
			form.percentualImovelFaixa.style.backgroundColor = '';
			
		} else if (desbloqueia != null && desbloqueia == 'sim') {
			form.adicionarFaixa.disabled = false;
			
			form.percentualPagamento.readOnly = false;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '';
			form.quantidadeMinimaContas.readOnly = false;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '';
			form.percentualDaFaixa.readOnly = false;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '';
			form.percentualImovelFaixa.readOnly = false;
			form.percentualImovelFaixa.style.color = "#000000";
			form.percentualImovelFaixa.style.backgroundColor = '';
		
		}
		
		if ((form.dataEncerramentoContratoCobranca.value != null) &&
	        (form.dataEncerramentoContratoCobranca.value != "")){
			     form.quantidadeMesValidoPagamento.style.color = "#000000";
			     form.quantidadeMesValidoPagamento.readOnly = false;
			     form.quantidadeMesValidoPagamento.style.backgroundColor = '';
	        } else {
	             form.quantidadeMesValidoPagamento.value = "";
			     form.quantidadeMesValidoPagamento.style.color = "#000000";
			     form.quantidadeMesValidoPagamento.readOnly = true;
			     form.quantidadeMesValidoPagamento.style.backgroundColor = '#EFEFEF';
	        }
	}
}
	
	function limparFaixa(){
		var form = document.forms[0];
		
		form.action = 'exibirInserirEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}
	
	function desbloquearQtdParcelaMinima(){		
		var form = document.forms[0];			
		
		if (form.dataEncerramentoContratoCobranca.value != null && form.dataEncerramentoContratoCobranca.value != ""){
					 
					 form.quantidadeMesValidoPagamento.style.color = "#000000";
				     form.quantidadeMesValidoPagamento.readOnly = false;
				     form.quantidadeMesValidoPagamento.style.backgroundColor = '';
		        } else {
		    
		             form.quantidadeMesValidoPagamento.value = "";
				     form.quantidadeMesValidoPagamento.style.color = "#000000";
				     form.quantidadeMesValidoPagamento.readOnly = true;
				     form.quantidadeMesValidoPagamento.style.backgroundColor = '#EFEFEF';
		        }
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('descricao');bloqueiaDadosEmpresaCobranca();">
	
	<div id="formDiv">

	<html:form action="/inserirEmpresaAction.do"
		name="InserirEmpresaActionForm"
		type="gcom.gui.cadastro.InserirEmpresaActionForm" method="post"
		onsubmit="return validateInserirEmpresaActionForm(this);">

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

						<html:hidden property="percentualDaFaixaInformado" />

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
					</div></td>
				<td width="625" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table> <!--Início Tabela Reference a Páginação da Tela de Processo-->
					<table>
						<tr>
							<td></td>

						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" />
							</td>
							<td class="parabg">Inserir Empresa</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" />
							</td>
						</tr>

					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->

					<table width="100%" border="0">
						<tr>

							<td colspan="2">Para adicionar uma empresa, informe o dado
								abaixo:</td>
						</tr>
						<tr>
							<td HEIGHT="30"><strong>Nome: <font color="#FF0000">*</font>
							</strong>
							</td>
							<td colspan="2"><html:text property="descricao"
									maxlength="50" size="50" tabindex="1"
									onkeyup="limitTextArea(document.forms[0].descricao, 50, document.getElementById('utilizado'), document.getElementById('limite'));" />
								<br></td>
						</tr>
						<tr>
							<td><strong>Nome Abreviado:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="descricaoAbreviada" size="10" maxlength="10"
										tabindex="2" /> </span>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><strong>E-mail:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="email" size="60" maxlength="80" tabindex="3" /> </span>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><strong>Empresa Principal? <font color="FF0000">*</font>
							</strong>
							</td>
							<td><html:radio property="indicadorEmpresaPrincipal"
									value="1" tabindex="4" /><strong>Sim</strong> <html:radio
									property="indicadorEmpresaPrincipal" value="2" tabindex="5" /><strong>Não</strong>
							</td>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td><strong>Indicador Empresa Cobrança:<font color="FF0000">*</font></strong>
							</td>
							<td><html:radio property="indicadorEmpresaCobranca"
									value="1" onclick="javascript:bloqueiaDadosEmpresaCobranca();"
									tabindex="6" /><strong>Sim</strong> <html:radio
									property="indicadorEmpresaCobranca" value="2"
									onclick="javascript:bloqueiaDadosEmpresaCobranca();limparFaixa();"
									tabindex="7" /><strong>Não</strong>
							</td>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td><strong>Indicador Leitura? <font color="FF0000">*</font>
							</strong>
							</td>
							<td><html:radio property="indicadorLeitura" value="1"
									tabindex="8" /><strong>Sim</strong> <html:radio
									property="indicadorLeitura" value="2" tabindex="9" /><strong>Não</strong>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong>Indicador Atualiza Cadastro? <font color="FF0000">*</font>
							</strong>
							</td>
							<td>	
								<html:radio property="indicadorAtualizaCadastro" value="1" tabindex="8" />
								<strong>Sim</strong> 
								<html:radio	property="indicadorAtualizaCadastro" value="2" tabindex="9" />
								<strong>Não</strong>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong>Indicador Empresa PPP: </strong></td>
							<td>
								<html:radio property="indicadorEmpresaPPP" value="1" />
								<strong>Sim</strong>
								<html:radio property="indicadorEmpresaPPP" value="2" />
								<strong>Não</strong>								
							</td>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td><strong> Data do Início do Contrato:</strong>
							</td>

							<td colspan="2"><strong> <html:text
										property="dataInicioContratoCobranca" size="11" maxlength="10"
										tabindex="10" onkeyup="mascaraData(this, event);"
										onkeypress="mascaraData(this, event);return isCampoNumerico(event);" />
									<a id="calendarioIni"
									href="javascript:abrirCalendario('InserirEmpresaActionForm', 'dataInicioContratoCobranca');">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="16" height="15" border="0" alt="Exibir Calendário"
										tabindex="11" /></a> (dd/mm/aaaa) </strong></td>
						</tr>
						
						<tr>
							<td><strong> Data de encerramento do contrato:</strong>
							</td>

							<td colspan="2"><strong> <html:text
										property="dataEncerramentoContratoCobranca" size="11"
										maxlength="10" tabindex="12"
										onkeyup="mascaraData(this, event);"
										onblur="desbloquearQtdParcelaMinima();"
										onkeypress="mascaraData(this, event);return isCampoNumerico(event);" />
									<a id="calendarioFim"
									href="javascript:abrirCalendario('InserirEmpresaActionForm', 'dataEncerramentoContratoCobranca');">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										onclick="javascript:abrirCalendarioComFuncaoRetorno('AtualizarEmpresaActionForm', 'dataEncerramentoContratoCobranca', 'desbloquearQtdParcelaMinima()');"
										width="16" height="15" border="0" alt="Exibir Calendário"
										tabindex="13" /></a> (dd/mm/aaaa) </strong></td>
						</tr>

						<logic:present name="colecaoEmpresaCobrancaFaixa">
							<tr>
								<td><strong>Percentual do Pagamento das Contas
										Cobradas:</strong>
								</td>
								<td colspan="2"><span class="style2"> <html:text
											property="percentualPagamento" size="7" maxlength="7"
											onkeypress="return isCampoNumerico(event);"
											onblur="bloqueiaCampos('sim');"
											onkeyup="formataValorMonetario(this, 5);" tabindex="14"
											disabled="true" /> </span>
								</td>
								<td>&nbsp;</td>
							</tr>
						</logic:present>
						
						<logic:notPresent name="colecaoEmpresaCobrancaFaixa">
							<tr>
								<td><strong>Percentual do Pagamento das Contas
										Cobradas:</strong>
								</td>
								<td colspan="2"><span class="style2"> <html:text
											property="percentualPagamento" size="7" maxlength="7"
											onkeypress="return isCampoNumerico(event);"
											onblur="bloqueiaCampos('sim');"
											onkeyup="formataValorMonetario(this, 5);" tabindex="15" /> </span>
								</td>
								<td>&nbsp;</td>
							</tr>
						</logic:notPresent>
						
						<tr>
							<td><strong>Quantidade de meses para pagamento:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="quantidadeMesValidoPagamento" size="6" maxlength="2"
										onkeypress="return isCampoNumerico(event);" tabindex="16" />
							</span>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<td height="10" colspan="3">
						<hr>
					    </td>
						
						<tr>
							<td><strong>Quantidade mínima de contas:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="quantidadeMinimaContas" size="8" maxlength="8"
										onblur="bloqueiaCampos('sim');"
										onkeypress="return isCampoNumerico(event);" tabindex="18" />
							</span>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong>Percentual da Faixa:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="percentualDaFaixa" size="7" maxlength="7"
										onkeypress="return isCampoNumerico(event);"
										onblur="bloqueiaCampos('sim');" tabindex="19"
										onkeyup="formataValorMonetario(this, 5);"/> </span> 
							</td>
							<td>&nbsp;</td>
						</tr>
				
						  <tr>
							<td><strong>Percentual de Imóveis:</strong>
							</td>
							<td colspan="2"><span class="style2"> <html:text
										property="percentualImovelFaixa" size="7" maxlength="7"
										onkeypress="return isCampoNumerico(event);"
										onblur="bloqueiaCampos('sim');" 
										onkeyup="formataValorMonetario(this, 5);" tabindex="20"/>
							</span>
							</td>
						  </tr>
						  <tr">
						  <td>
						  </td>
						  <td align="right" colspan="3"> 
						  <input type="button" name="adicionarFaixa"
								class="bottonRightCol" value="Adicionar"
								onClick="javascript:adicionarEmpresaContratoCobranca();"
								tabindex="20">
						  </td>
						  </tr>
						  <tr>
					      <td colspan="2">
					      <strong>Faixa de Cobrança:</strong>
					      </td>
					      <td>
					      </td>	
						  </tr>							
					</table>
					
					<table width="100%" cellpadding="0" cellspacing="0">

						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="10%">
								<div align="center">
									<strong>Remover</strong>
								</div></td>
							<td bgcolor="#90c7fc" width="35%"><strong>Quantidade
									mínima de contas</strong>
							</td>
							<td bgcolor="#90c7fc" width="30%"><strong>Percentual
									da Faixa</strong>
							</td>
							<td bgcolor="#90c7fc" width="40%"><strong>Percentual
									de Imóveis</strong>
							</td>
						</tr>
						<logic:present name="colecaoEmpresaCobrancaFaixa">
							<tr>
								<td colspan="4">

									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" bgcolor="#99CCFF">
											<tr>
												<logic:iterate name="colecaoEmpresaCobrancaFaixa"
													id="empresaCobrancaFaixa" type="EmpresaCobrancaFaixa">
													<c:set var="count2" value="${count2+1}" />
													<c:choose>
														<c:when test="${count2%2 == '1'}">
															<tr bgcolor="#FFFFFF">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
													<td width="10%">
														<div align="center">
															<font color="#333333"> <img width="14" height="14"
																border="0"
																src="<bean:message key="caminho.imagens"/>Error.gif"
																onclick="removerEmpresaCobrancaFaixa('${count2}')" /> </font>
														</div></td>
													<td width="35%"><bean:write
															name="empresaCobrancaFaixa"
															property="numeroMinimoContasFaixa" /></td>
													<td width="30%"><bean:write
															name="empresaCobrancaFaixa" property="percentualFaixa"
															formatKey="money.format" /></td>
													<td width="25%"><bean:write
															name="empresaCobrancaFaixa" property="percentualImovelFaixa"
															formatKey="money.format" /></td>
												</logic:iterate>
											</tr>

										</table>
									</div></td>
							</tr>
						</logic:present>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">
							Campo Obrigatório
							<font color="#FF0000">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="3"><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="javascript:limparForm();"> 
								<input name="Button" type="button" class="bottonRightCol" value="Cancelar"
								align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
							    <input type="button" name="Button2" class="bottonRightCol"
								value="Inserir" onClick="javascript:validarForm(document.forms[0])" />
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
	</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
