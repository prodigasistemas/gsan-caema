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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioResumoFaturamentoPppActionForm" />
<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
	    if (tipoConsulta == 'localidade') {
	      form.codigoLocalidade.value = codigoRegistro;
	      form.descricaoLocalidade.value = descricaoRegistro;
	      form.descricaoLocalidade.style.color = "#000000";
	
	    }else if (tipoConsulta == 'municipio'){
	    	form.codigoMunicipio.value = codigoRegistro;
	    	form.descricaoMunicipio.value = descricaoRegistro;
	    	form.descricaoMunicipio.style.color = "#000000";
	    }
  	}

	function gerarRelatorio(){
		var form = document.forms[0];
		var opcaoRelatorio = verificaOpcaoRelatorio();
		var opcaoTotalizacao = verificaOpcaoTotalizacao();
		if(validaMesAno() && validarOpcaoTotalizacaoLocalidadeMunicipio()){
			if(opcaoRelatorio == 1){
				form.action = 'gerarRelatorioResumoFaturamentoPppAction.do'
				form.submit(form);
			}else if(opcaoRelatorio == 2){
				if(opcaoTotalizacao=='gerenciaRegional'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);	
				}else if(opcaoTotalizacao=='gerenciaRegionalLocalidade'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);	
				}else if(opcaoTotalizacao=='unidadeNegocio'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);
				}else if(opcaoTotalizacao=='localidade'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);
				}else if(opcaoTotalizacao=='municipio'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);
				}else if(opcaoTotalizacao=='sistema'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);
				}else if(opcaoTotalizacao=='subSistemaEsgoto'){
					var opcaoSelecionada = verificarOpcaoTotalizacaoSelected(opcaoTotalizacao);
					form.action = 'gerarRelatorioFaturamentoPppAction.do?opcaoTotalizacaoSelected='+opcaoSelecionada
					form.submit(form);
				}
			}
		}
	}

	function verificaOpcaoRelatorio(){
		var form = document.forms[0];
		var opcaoRelatorio = null;
		var RadioOpcaoRelatorio = null;
		RadioOpcaoRelatorio = form.opcaoRelatorio;

		for(var i=0;i<RadioOpcaoRelatorio.length;i++){
			if(RadioOpcaoRelatorio[i].checked){
				opcaoRelatorio = RadioOpcaoRelatorio[i].value;
			}
		}
		return opcaoRelatorio;
	}

	function verificaOpcaoTotalizacao(){
		var form = document.forms[0];
		var opcaoTotalizacao = null;
		var RadioOpcaoTotalizacao = form.opcaoTotalizacao;

		for(var i=0;i<RadioOpcaoTotalizacao.length;i++){
			if(RadioOpcaoTotalizacao[i].checked){
				opcaoTotalizacao = RadioOpcaoTotalizacao[i].value;
			}
		}
		return opcaoTotalizacao;	
	}

	function verificarOpcaoTotalizacaoSelected(opcaoTotalizacao){
		var form = document.forms[0];
		var opcaoSelecionada = null;

		if(opcaoTotalizacao=="gerenciaRegional"){
			var opcaoTotalizacaoSelected = form.gerenciaRegionalId.options;

			for(var i=0;i<opcaoTotalizacaoSelected.length;i++){
				if(opcaoTotalizacaoSelected[i].selected){
					opcaoSelecionada = opcaoTotalizacaoSelected[i].label;
				}
			}
		}else if(opcaoTotalizacao=="gerenciaRegionalLocalidade"){
			var opcaoTotalizacaoSelected = form.gerenciaRegionalporLocalidadeId.options;

			for(var i=0;i<opcaoTotalizacaoSelected.length;i++){
				if(opcaoTotalizacaoSelected[i].selected){
					opcaoSelecionada = opcaoTotalizacaoSelected[i].label;
				}
			}
		}else if(opcaoTotalizacao=="unidadeNegocio"){
			var opcaoTotalizacaoSelected = form.unidadeNegocioId.options;

			for(var i=0;i<opcaoTotalizacaoSelected.length;i++){
				if(opcaoTotalizacaoSelected[i].selected){
					opcaoSelecionada = opcaoTotalizacaoSelected[i].label;
				}
			}
		}else if(opcaoTotalizacao=="localidade"){

			opcaoSelecionada = form.codigoLocalidade;
			
		}else if(opcaoTotalizacao=="municipio"){

			opcaoSelecionada = form.codigoMunicipio;
			
		}else if(opcaoTotalizacao=="sistema"){
			var opcaoTotalizacaoSelected = form.sistemaEsgotoId.options;

			for(var i=0;i<opcaoTotalizacaoSelected.length;i++){
				if(opcaoTotalizacaoSelected[i].selected){
					opcaoSelecionada = opcaoTotalizacaoSelected[i].label;
				}
			}
		}else if(opcaoTotalizacao=="subSistemaEsgoto"){
			var opcaoTotalizacaoSelected = form.subSistemaEsgotoId.options;

			for(var i=0;i<opcaoTotalizacaoSelected.length;i++){
				if(opcaoTotalizacaoSelected[i].selected){
					opcaoSelecionada = opcaoTotalizacaoSelected[i].label;
				}
			}
		}
		return opcaoSelecionada;
	}
		
	function limparMunicipio(){
		var form = document.forms[0];
	
		form.codigoMunicipio.value="";
		form.descricaoMunicipio.value="";
	}

	function limparLocalidade() {
	    var form = document.forms[0];
	    
	      form.codigoLocalidade.value = '';
	      form.descricaoLocalidade.value = '';
	}
	
	function bloquearCamposOpcaoRelatorio(opcaoRelatorio){
		var form = document.forms[0];

		if(opcaoRelatorio.value==2){
			form.opcaoTotalizacao[0].disabled = true;
			form.opcaoTotalizacao[0].checked = false;
			form.opcaoTotalizacao[1].disabled = true;
			form.opcaoTotalizacao[1].checked = false;
			form.opcaoTotalizacao[2].disabled = true;
			form.opcaoTotalizacao[2].checked = false;
			form.opcaoTotalizacao[3].disabled = true;
			form.opcaoTotalizacao[3].checked = false;
			form.opcaoTotalizacao[4].disabled = true;
			form.opcaoTotalizacao[4].checked = false;
			form.opcaoTotalizacao[5].disabled = true;
			form.opcaoTotalizacao[5].checked = false;
			form.opcaoTotalizacao[6].disabled = true;
			form.opcaoTotalizacao[6].checked = false;

			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.unidadeNegocioId.value='-1';
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			limaparOpcaoTotalizacao();
			limparLocalidade();
			limparMunicipio();
			
		}else if(opcaoRelatorio.value==1){
			form.opcaoTotalizacao[0].disabled = false;
			form.opcaoTotalizacao[0].checked = false;
			form.opcaoTotalizacao[1].disabled = false;
			form.opcaoTotalizacao[1].checked = false;
			form.opcaoTotalizacao[2].disabled = false;
			form.opcaoTotalizacao[2].checked = false;
			form.opcaoTotalizacao[3].disabled = false;
			form.opcaoTotalizacao[3].checked = false;
			form.opcaoTotalizacao[4].disabled = false;
			form.opcaoTotalizacao[4].checked = false;
			form.opcaoTotalizacao[5].disabled = false;
			form.opcaoTotalizacao[5].checked = false;
			form.opcaoTotalizacao[6].disabled = false;
			form.opcaoTotalizacao[6].checked = false;

			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.unidadeNegocioId.value='-1';
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			limaparOpcaoTotalizacao();
			limparLocalidade();
			limparMunicipio();
		}
	}

	function limaparOpcaoTotalizacao(){
		var form = document.forms[0];
		var radioVetorOpcaoTotalizacao = form.opcaoTotalizacao;

		for(var a = 0; a<radioVetorOpcaoTotalizacao.length;a++){
			if(radioVetorOpcaoTotalizacao[a].checked){
				radioVetorOpcaoTotalizacao[a].checked = false;
			}
		}
		
	}
	
	function bloquearCampos(opcaoTotalizacao){
		var form = document.forms[0];

		if(opcaoTotalizacao.value=='estado'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoGerencia'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoUnidadeNegocio'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoLocalidade'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoMunicipio'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoSubSistemaEsgoto'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='estadoSistema'){
			
			form.sistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}
		
		if(opcaoTotalizacao.value=='sistema'){

			form.sistemaEsgotoId.disabled=false;
			form.sistemaEsgotoId.value='-1';
						
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='subSistemaEsgoto'){

			form.subSistemaEsgotoId.disabled=false;
			form.subSistemaEsgotoId.value='-1';

			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='gerenciaRegional'){

			form.gerenciaRegionalId.disabled=false;
			form.gerenciaRegionalId.value='-1';
					
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='gerenciaRegionalLocalidade'){

			form.gerenciaRegionalporLocalidadeId.disabled=false;
			form.gerenciaRegionalporLocalidadeId.value='-1';
						
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;		
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='unidadeNegocio'){

			form.unidadeNegocioId.disabled=false;
			form.unidadeNegocioId.value='-1';
					
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;		
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;		
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;			
		}

		if(opcaoTotalizacao.value=='localidade'){

			form.codigoLocalidade.disabled=false;
			form.codigoLocalidade.value='';
			
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;		
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;		
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
			form.codigoMunicipio.value='';
			form.codigoMunicipio.disabled=true;
		}

		if(opcaoTotalizacao.value=='municipio'){

			form.codigoMunicipio.disabled=false;			
			form.codigoMunicipio.value='';
			
			form.codigoLocalidade.value='';
			form.codigoLocalidade.disabled=true;
			form.unidadeNegocioId.value='-1';
			form.unidadeNegocioId.disabled=true;
			form.gerenciaRegionalporLocalidadeId.value='-1';
			form.gerenciaRegionalporLocalidadeId.disabled=true;		
			form.gerenciaRegionalId.value='-1';
			form.gerenciaRegionalId.disabled=true;		
			form.subSistemaEsgotoId.value='-1';
			form.subSistemaEsgotoId.disabled=true;
			form.sistemaEsgotoId.value='-1';
			form.sistemaEsgotoId.disabled=true;			
		}
		
		
	}

	function limparCampos(opcaoTotalizacao){
		var form = document.forms[0];
		
		if (opcaoTotalizacao.value != 'localidade'){
			limparLocalidade() ;
		}		
		if (opcaoTotalizacao.value != 'gerenciaRegional'){
			form.gerenciaRegionalId.value = "-1";
		}		
		if (opcaoTotalizacao.value != 'gerenciaRegionalLocalidade'){
			form.gerenciaRegionalporLocalidadeId.value = "-1" ;
		}		
		if (opcaoTotalizacao.value != 'unidadeNegocio'){
			form.unidadeNegocioId.value = "-1" ;
		}
		if(opcaoTotalizacao.value != 'municipio'){
			limparMunicipio();
		}
		if(opcaoTotalizacao.value !='sistema'){
			form.sistemaEsgotoId.value='-1';
		}
		if(opcaoTotalizacao.value!='subSistemaEsgoto'){
			form.subSistemaEsgotoId.value='-1';
		}
		
	}

	function bloquearCamposOnload(){
		var form = document.forms[0];
		var opcaoRelatorio = verificaOpcaoRelatorio();
		if(opcaoRelatorio ==1){
			form.opcaoTotalizacao[0].checked = false;
			form.opcaoTotalizacao[1].disabled = false;
			form.opcaoTotalizacao[1].checked = false;
			form.opcaoTotalizacao[2].disabled = false;
			form.opcaoTotalizacao[2].checked = false;
			form.opcaoTotalizacao[3].disabled = false;
			form.opcaoTotalizacao[3].checked = false;
			form.opcaoTotalizacao[4].disabled = false;
			form.opcaoTotalizacao[4].checked = false;
			form.opcaoTotalizacao[5].disabled = false;
			form.opcaoTotalizacao[5].checked = false;
			form.opcaoTotalizacao[6].disabled = false;
			form.opcaoTotalizacao[6].checked = false;
		}else if(opcaoRelatorio==2){
			form.opcaoTotalizacao[0].disabled = true;
			form.opcaoTotalizacao[0].checked = false;
			form.opcaoTotalizacao[1].disabled = true;
			form.opcaoTotalizacao[1].checked = false;
			form.opcaoTotalizacao[2].disabled = true;
			form.opcaoTotalizacao[2].checked = false;
			form.opcaoTotalizacao[3].disabled = true;
			form.opcaoTotalizacao[3].checked = false;
			form.opcaoTotalizacao[4].disabled = true;
			form.opcaoTotalizacao[4].checked = false;
			form.opcaoTotalizacao[5].disabled = true;
			form.opcaoTotalizacao[5].checked = false;
			form.opcaoTotalizacao[6].disabled = true;
			form.opcaoTotalizacao[6].checked = false;
		}
	}

	function validaMesAno(){
	  var form = document.forms[0];
	  var mesAno = form.mesAno.value;
	  if(mesAno.length < 7 || mesAno.substring(2,3) != "/" ||
	     mesAno.substring(0,2) < "01" || mesAno.substring(0,2) > "12"){
	     alert("Mês/Ano do Faturamento inválido.");
	     return false;
	  }else{
		  return true;
	  }
	}

	function validarOpcaoTotalizacaoLocalidadeMunicipio(){

		var form = document.forms[0]; 
	
 		 var opcao = '';
		
		 for(i=0; i < form.opcaoTotalizacao.length; i++) {
		 	if (form.opcaoTotalizacao[i].checked) {
		 		opcao = form.opcaoTotalizacao[i].value;
		 	}
		 }
		 
   		if (opcao == 'localidade') {
   			if (form.codigoLocalidade.value == '') {
   				alert('Preencha o código da localidade');
   				return false;
   			}
   		} else if(opcao == 'municipio'){
   			if(form.codigoMunicipio.value == ''){
   				alert('Preencha o código do município associado à localidade');
    			return false;
   			}
   		} else if (opcao == '') {
   			alert('Informe Opção de Totalização');
   			return false;
   		}	 

  		return true;    
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="bloquearCamposOnload();">
<html:form action="/gerarRelatorioResumoFaturamentoPppAction"
	name="GerarRelatorioResumoFaturamentoPppActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioResumoFaturamentoPppActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
					<td class="parabg">Relatório Resumo de Faturamento PPP</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat&oacute;rio resumo de
					faturamento, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="26%"><strong>Mês/Ano do Faturamento:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAno" size="7" maxlength="7"
					 onkeyup="mascaraAnoMes(this, event);"
					 onkeypress="return isCampoNumerico(event);"/>
					<strong>&nbsp; </strong>mm/aaaa</td>
				</tr>
				
				<tr>
					<td><strong>Op&ccedil;&atilde;o de Relat&oacute;rio:</strong></td>
					<td colspan="2" align="left"><html:radio
						property="opcaoRelatorio" value="1" onclick="bloquearCamposOpcaoRelatorio(this);"/> <strong>Sintetico</strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> <html:radio
						property="opcaoRelatorio" value="2" onclick="bloquearCamposOpcaoRelatorio(this);" />Analitico</strong></td>
				</tr>
				<p>&nbsp;</p>
				<tr>
					<td><strong>Opção de Totalização:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>PPP </strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"> 
						<html:radio property="opcaoTotalizacao" value="estadoGerencia" onclick = "limparCampos(this);bloquearCampos(this);"/>
						<strong>PPP por Gerência Regional</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"> 
						<html:radio property="opcaoTotalizacao" value="estadoUnidadeNegocio" onclick = "limparCampos(this);bloquearCampos(this);"/>
						<strong>PPP por Unidade de Negócio</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left">					
						<html:radio property="opcaoTotalizacao" value="estadoLocalidade" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>PPP por Localidade</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"> 
						<html:radio	property="opcaoTotalizacao" value="estadoMunicipio" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>PPP por Município</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"> 
						<html:radio	property="opcaoTotalizacao" value="estadoSistema" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>PPP Sistema</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"> 
						<html:radio	property="opcaoTotalizacao" value="estadoSubSistemaEsgoto" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>PPP Subsistema</strong>
					</td>
				</tr>
				
				
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);bloquearCampos(this);"/> <strong>Ger&ecirc;ncia Regional </strong>
					</td>
					<td width="38%" align="left"> 
						<html:select property="gerenciaRegionalId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						</html:select> </td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"> 
						<html:radio property="opcaoTotalizacao" value="gerenciaRegionalLocalidade" onclick = "limparCampos(this);bloquearCampos(this);"/> <strong>Ger&ecirc;ncia Regional por Localidade</strong>
					</td>
					<td align="left"> 
						<html:select property="gerenciaRegionalporLocalidadeId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						</html:select> 
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left"> 
							<html:radio property="opcaoTotalizacao" value="unidadeNegocio"  onclick = "limparCampos(this);bloquearCampos(this);"/>
							<strong>Unidade de Negócio </strong>
					</td>
					<td width="38%" align="left"> 
						<html:select property="unidadeNegocioId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</html:select> 
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"> 
						<html:radio property="opcaoTotalizacao"	value="localidade" onclick = "limparCampos(this);bloquearCampos(this);" /> 
						<strong>Localidade</strong>
					</td>
					<td align="left">
						<html:text property="codigoLocalidade" size="4" maxlength="3"
							onkeypress="validaEnter(event, 'exibirGerarRelatorioResumoFaturamentoPppAction.do?pesquisarLocalidade=OK', 'codigoLocalidade');" 
							onblur="javascript:mascara(this, mascaraInteiro);"
							onkeyup="somente_numero(this);"					
							onkeydown="somente_numero(this);"/>
							<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);"><img
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										width="23" height="21" border="0" title="Pesquisar" /></a>
							<a href="javascript:limparLocalidade();"><img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										width="23" height="21" border="0" title="Apagar" /></a>
							
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"> </td>
					<td align="left">
						<logic:present name="localidadeEncontrada" scope="request">
							<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="localidadeEncontrada" scope="request">
							<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left">
						<html:radio property="opcaoTotalizacao" value="municipio" onclick = "limparCampos(this);bloquearCampos(this);" />
						<strong>Município</strong>
					</td>
					<td align="left">
						<html:text property="codigoMunicipio" size="4" maxlength="4" 
							onkeypress="validaEnter(event, 'exibirGerarRelatorioResumoFaturamentoPppAction.do?pesquisarMunicipio=OK', 'codigoMunicipio');" 
							onblur="javascript:mascara(this, mascaraInteiro);"
							onkeyup="somente_numero(this);"					
							onkeydown="somente_numero(this);" 
							/>
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800);"><img
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								width="23" height="21" border="0" title="Pesquisar" /></a>
							<a href="javascript:limparMunicipio();"><img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								width="23" height="21" border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"> </td>
					<td align="left">
						<logic:present name="municipioEncontrado" scope="request">
							<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="municipioEncontrado" scope="request">
							<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
					</td>
				</tr>	
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="sistema" onclick = "limparCampos(this);bloquearCampos(this);"/> <strong>Sistema </strong>
					</td>
					
					<td width="38%" align="left">
						<html:select property="sistemaEsgotoId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoSistemaEsgoto" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				
				</tr>	
					
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left"> 
						<html:radio property="opcaoTotalizacao" value="subSistemaEsgoto" onclick = "limparCampos(this);bloquearCampos(this);"/> 
						<strong>Subsistema </strong>
					</td>
					
					<td width="38%" align="left"> 
						<html:select property="subSistemaEsgotoId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoSubSistemaEsgoto" labelProperty="descricao" property="id" />
						</html:select> 
					</td>
				
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</td>
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
					<td><input type="button" value="Cancelar" class="bottonRightCol"
					 onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td>
					<td><input type="button" value="Limpar" class="bottonRightCol"
					 onclick="javascript:window.location.href='/gsan/exibirGerarRelatorioResumoFaturamentoPppAction.do?menu=sim'"></td>
					<td>
					
					<table width="30%" border="0" align="right" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="61"></td>
							<td width="416">&nbsp;</td>
							<td width="12"></td>
							<td width="61">
							
							<input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="gerarRelatorio();">
							</td>
							<td width="82">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioResumoFaturamentoPppAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
