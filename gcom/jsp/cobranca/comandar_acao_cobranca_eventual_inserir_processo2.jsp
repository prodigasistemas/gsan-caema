<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cobranca.CobrancaAtividade" %>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@page import="gcom.cadastro.localidade.Localidade"  isELIgnored="false"%>
<%@page import="gcom.cadastro.localidade.SetorComercial"  isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"
	formName="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" 
	dynamicJavascript="false" 
	/>
	
<script language="JavaScript">
<!-- Begin


	$(document).ready(function() {
	
		validarRelacaoImoveis();
		validarPeriodoFiscalizacao();
		
		// 2.22. exibir com a op��o "Inclusive" selecionada 
		if (!$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").is(":checked")){
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('checked', true);
		}
		
		
		$("input[@name='periodoInicialFiscalizacao'],input[@name='periodoFinalFiscalizacao']").change(validarPeriodoFiscalizacao);
		$("input[@name='relacaoImoveis']").change(validarRelacaoImoveis);
		
	});


	function validarPeriodoFiscalizacao(){
		
		// 2.22. caso n�o informe o Per�odo de Fiscaliza��o do Im�vel 
		if($("input[@name='periodoInicialFiscalizacao']").val() == '' &&
		   $("input[@name='periodoFinalFiscalizacao']").val() == ''){
			
			// 2.22. exibir as op��es "Apenas" e "N�o Incluir" desabilitadas
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=2]').attr('disabled', true); //Apenas
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=3]').attr('disabled', true); //N�o Incluir
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('checked', true);
		}
		
		else{
			if($("input[@name='relacaoImoveis']").val() == ''){
				$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('checked', true);
				$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('disabled', false);
				$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=2]').attr('disabled', false); //Apenas
				$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=3]').attr('disabled', false); //N�o Incluir
			}
		}
	}
	
	function validarRelacaoImoveis(){
		var form = document.forms[0];
		if($("input[@name='relacaoImoveis']").val() == ''){
			
			$("input[@name='prazoExecucao']").attr('disabled', false);
			$("input[@name='valorLimiteObrigatoria']").attr('disabled', false);
			$("input[@name='indicadorImoveisDebito']").attr('disabled', false);
	        $("input[@name='indicadorImoveisDebito']").filter('[value=1]').attr('checked', true);
			$("input[@name='indicadorGerarBoletimCadastro']").attr('disabled', false);
			$("input[@name='numeroQuadraInicial']").attr('disabled', false);
			$("input[@name='numeroQuadraFinal']").attr('disabled', false);
			$("select[@name='clienteRelacaoTipo']").attr('disabled', false);
		    $("select[@name='cobrancaGrupo']").attr('disabled', false);
		    $("input[@name='periodoVencimentoContaInicial']").attr('disabled', false);		        
	        $("input[@name='periodoVencimentoContaFinal']").attr('disabled', false);		        
	  		$("input[@name='quantidadeDiasVencimento']").attr('disabled', false);
	        $("input[@name='periodoInicialConta']").attr('disabled', false);
	        $("input[@name='periodoFinalConta']").attr('disabled', false);
	        $("input[@name='consumoMedioInicial']").attr('disabled', false);		        
	        $("input[@name='consumoMedioFinal']").attr('disabled', false);		 
	        $("input[@name='periodoInicialFiscalizacao']").attr('disabled', false);		        
	        $("input[@name='periodoFinalFiscalizacao']").attr('disabled', false);		        
	        $("select[@name='situacaoFiscalizacao']").attr('disabled', false);		        
	        $("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").attr('disabled', false);	  
	        $("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('checked', true);
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('disabled', false);
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=2]').attr('disabled', false); //Apenas
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=3]').attr('disabled', false); //N�o Incluir
			
			$("input[@name='periodoVencimentoContaFinal']").val($("input[@name='periodoVencimentoContaFinalHidden']").val());
			$("input[@name='periodoFinalFiscalizacao']").val($("input[@name='periodoFinalFiscalizacaoHidden']").val());
			$("input[@name='periodoFinalConta']").val($("input[@name='periodoFinalContaHidden']").val());
			
			
			habilitar(document.forms[0].cobrancaAtividadeIndicadorExecucao.value);
			validarCliente(0);
			validarClienteSuperior(1);
			desabilitarLocalidadeCobrancaAcao();
			validarGrupoCobrancaParaBloquear();
			validarGerenciaRegionalParaBloquear();
			validarUnidadeNegocioParaBloquear();
			verificarCampoPrazoExecucao();
			validarClienteParaBloquear();
			validarHabilitarCampo();
			validarLogradouroParaBloquear();
			validarPeriodoFiscalizacao();
			
		}
		else{

			$("select[@name='gerenciaRegional']").attr('disabled', true);	
			$("select[@name='unidadeNegocio']").attr('disabled', true);
	    	$("input[@name='localidadeOrigemID']").attr('disabled', true);
			$("input[@name='localidadeDestinoID']").attr('disabled', true);
	        $("input[@name='setorComercialOrigemCD']").attr('disabled', true);
	        $("input[@name='setorComercialDestinoCD']").attr('disabled', true);
	        $("input[@name='imagem']").attr('disabled', true);
		    if(form.rotaInicial != undefined){
	    	   		$("input[@name='rotaInicial']").attr('disabled', true);
	    	   		$("input[@name='rotaFinal']").attr('disabled', true);
		    }
	        $("input[@name='idCliente']").attr('disabled', true);
	        $("select[@name='clienteRelacaoTipo']").attr('disabled', true);
	        $("select[@name='cobrancaGrupo']").attr('disabled', true);
	        
	        
	        $("input[@name='quantidadeMaximaDocumentos']").attr('disabled', true);
	        $("input[@name='valorLimiteObrigatoria']").attr('disabled', true);		         
	        $("input[@name='indicadorImoveisDebito']").attr('disabled', true);
	        $("input[@name='indicadorGerarBoletimCadastro']").attr('disabled', true);		        
	        $("input[@name='numeroQuadraInicial']").attr('disabled', true);		        
	        $("input[@name='numeroQuadraFinal']").attr('disabled', true);		        
	        $("input[@name='codigoClienteSuperior']").attr('disabled', true);		        
	        $("input[@name='periodoVencimentoContaInicial']").attr('disabled', true);		        
	        $("input[@name='periodoVencimentoContaFinal']").attr('disabled', true);		        
	  		$("input[@name='quantidadeDiasVencimento']").attr('disabled', true);
	        $("input[@name='consumoMedioInicial']").attr('disabled', true);		        
	        $("input[@name='consumoMedioFinal']").attr('disabled', true);		        
	        $("input[@name='periodoInicialFiscalizacao']").attr('disabled', true);		        
	        $("input[@name='periodoFinalFiscalizacao']").attr('disabled', true);		        
	        $("select[@name='situacaoFiscalizacao']").attr('disabled', true);		            
	        $("input[@name='logradouroId']").attr('disabled', true);
	        $("input[@name='periodoInicialConta']").attr('disabled', true);
	        $("input[@name='periodoFinalConta']").attr('disabled', true);
	        $("select[@name='tipoConsumo']").attr('disabled', true);
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=2]').attr('disabled', true); //Apenas
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=3]').attr('disabled', true); //N�o Incluir
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('disabled', true);
			
			
			//Valores Padr�es
			$("input[@name='imoveisSitLigacaoNaoAlteradaDeb']").filter('[value=1]').attr('checked', true);
			$("select[@name='gerenciaRegional']").val('-1');	
			$("select[@name='unidadeNegocio']").val('-1');
	    	$("input[@name='localidadeOrigemID']").val('');
			$("input[@name='localidadeDestinoID']").val('');
	        $("input[@name='setorComercialOrigemCD']").val('');
	        $("input[@name='setorComercialDestinoCD']").val('');
		    if(form.rotaInicial != undefined){
	    	   		$("input[@name='rotaInicial']").val('');
	    	   		$("input[@name='rotaFinal']").val('');
		    }
	        $("input[@name='idCliente']").val('');
	        $("select[@name='clienteRelacaoTipo']").val('-1');
	        $("select[@name='cobrancaGrupo']").val('-1');
	        
	        
	        $("input[@name='quantidadeMaximaDocumentos']").val('');
	        $("input[@name='valorLimiteObrigatoria']").val('');
	        $("input[@name='indicadorImoveisDebito']").filter('[value=2]').attr('checked', true);
	        $("input[@name='indicadorGerarBoletimCadastro']").filter('[value=2]').attr('checked', true);	        
	        $("input[@name='numeroQuadraInicial']").val('');	        
	        $("input[@name='numeroQuadraFinal']").val('');   
	        $("input[@name='codigoClienteSuperior']").val('');    
	        $("input[@name='periodoVencimentoContaInicial']").val(''); 
	        $("input[@name='periodoVencimentoContaFinal']").val('');       
	  		$("input[@name='quantidadeDiasVencimento']").val('');
	        $("input[@name='consumoMedioInicial']").val(''); 
	        $("input[@name='consumoMedioFinal']").val('');      
	        $("input[@name='periodoInicialFiscalizacao']").val('');       
	        $("input[@name='periodoFinalFiscalizacao']").val('');      
	        $("select[@name='situacaoFiscalizacao']").val('-1');           
	        $("input[@name='logradouroId']").val('');
	        $("input[@name='periodoInicialConta']").val('');
	        $("input[@name='periodoFinalConta']").val('');
	        $("select[@name='tipoConsumo']").val('-1');
	        $("input[@name='nomeLocalidadeOrigem']").val('');
	        $("input[@name='nomeLocalidadeDestino']").val('');
	        $("input[@name='nomeSetorComercialOrigem']").val('');
	        $("input[@name='nomeSetorComercialDestino']").val('');
	        $("input[@name='logradouroDescricao']").val('');
	        $("input[@name='nomeCliente']").val('');
	        $("input[@name='nomeClienteSuperior']").val(''); 
	           
		}
	}

	function caracteresespeciais () {
    	this.ag = new Array("titulo", "T�tulo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.aa = new Array("localidadeOrigemID", "Localidade Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("localidadeDestinoID", "Localidade Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("setorComercialOrigemCD", "Setor Comercial Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ad = new Array("setorComercialDestinoCD", "Setor Comercial Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ae = new Array("idCliente", "Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	
    }

    function IntegerValidations () {
    	this.ac = new Array("prazoExecucao", "Prazo de Execu��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("quantidadeMaximaDocumentos", "Quantidade m�xima de documentos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    	this.am = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.an = new Array("localidadeDestinoID", "Localidade Final deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ao = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ap = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("idCliente", "Cliente deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	/*this.af = new Array("valorLimiteObrigatoria", "Valor Limite para Emiss�o Obrigat�ria deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));*/
     	
    }
    
    function FloatValidations () {
	 	this.ap = new Array("valorLimiteObrigatoria", "Valor Limite para Emiss�o Obrigat�ria deve somente conter n�meros decimais positivos.",new Function ("varName", " return this[varName];"));
	}

    function required () {
    	this.ab = new Array("titulo", "Informe T�tulo.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("descricaoSolicitacao", "Informe Descri��o Solicita��o.", new Function ("varName", " return this[varName];"));     	
    	this.ah = new Array("cobrancaAtividade", "Informe Atividade de Cobran�a.", new Function ("varName", " return this[varName];"));
     	
    } 
    

	function validar(){

   		var form = document.forms[0];
	   	if(form.cobrancaAcao.value == "" || form.cobrancaAcao.value == "-1"){
	   		alert("Informe A��o de Cobran�a.");
	   		return false;
	   	}
   		
		return true;
	}
	
	function validarPeriodoVencimentoConta(){
		
		if($("input[@name='periodoVencimentoContaFinal']").val() == '' && 
				$("input[@name='periodoVencimentoContaInicial']").val() != ''){
			
			alert('Informe Per�odo de Refer�ncia das Contas Final');
			return false;
		}
		
		if($("input[@name='periodoVencimentoContaFinal']").val() != '' && 
				$("input[@name='periodoVencimentoContaInicial']").val() == ''){
			
			alert('Informe Per�odo de Refer�ncia das Contas Inicial');
			return false;
		}
		
		else{
			return true;
		}
		
	}
	
	function validarPeriodoFiscalizacaoImovel(){
		if($("input[@name='periodoFinalFiscalizacao']").val() == '' && 
				$("input[@name='periodoInicialFiscalizacao']").val() != ''){
			
			alert('Informe Per�odo de Fiscaliza��o do Im�vel Final');
			return false;
		}
		
		if($("input[@name='periodoFinalFiscalizacao']").val() != '' && 
				$("input[@name='periodoInicialFiscalizacao']").val() == ''){
			
			alert('Informe Per�odo de Fiscaliza��o do Im�vel Inicial');
			return false;
		}
		
		else{
			return true;
		}
	}
	
	function validarPeriodoConta(){
		
		if($("input[@name='periodoFinalConta']").val() == '' && 
				$("input[@name='periodoInicialConta']").val() != ''){
			
			alert('Informe Per�odo de Vencimento das Contas Final');
			return false;
		}
		
		if($("input[@name='periodoFinalConta']").val() != '' && 
				$("input[@name='periodoInicialConta']").val() == ''){
			
			alert('Informe Per�odo de Vencimento das Contas Inicial');
			return false;
		}
		
		else{
			return true;
		}
		
	}

  	function validarCliente(apagar){
    	var form = document.forms[0];
    	if (form.idCliente.value != ''){
      		if(apagar == 1) form.clienteRelacaoTipo.value = "";	
      		form.clienteRelacaoTipo.disabled = false;
      		form.codigoClienteSuperior.value = "";
      		form.codigoClienteSuperior.disabled = true;
    	}else{
      		form.clienteRelacaoTipo.disabled = true;
      		form.codigoClienteSuperior.disabled = false;
   	 	}
  	}
  	
  	 

	function desabilitarCobrancaAcao(){
  		var form = document.forms[0];
  		
  		if (form.localidadeOrigemID.value != ''  ){

	      	form.cobrancaGrupo.value = "";	
	      	form.cobrancaGrupo.disabled = true;
	      	form.gerenciaRegional.disabled = true;
	      	form.unidadeNegocio.disabled = true;
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
	        form.idCliente.disabled = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;
	        //form.logradouroId.value = "";
        	form.logradouroId.disabled = true;
        	//form.logradouroDescricao.value = "";
        	form.logradouroDescricao.disabled = true;      	
  		}else{
	     	form.cobrancaGrupo.disabled = false;
		    form.gerenciaRegional.disabled = false;
		    form.unidadeNegocio.disabled = false;      
		    form.idCliente.disabled = false;
		    form.clienteRelacaoTipo.disabled = false;  
		    //form.logradouroId.value = "";
        	form.logradouroId.disabled = false;
        	//form.logradouroDescricao.value = "";
        	form.logradouroDescricao.disabled = false;    
  		}
	}

	function desabilitarLocalidadeCobrancaAcao(){
	  	var form = document.forms[0];
	  	if (form.idCliente.value != '' || form.codigoClienteSuperior.value != '' ){
	
	      	form.cobrancaGrupo.value = "";	
	      	form.cobrancaGrupo.disabled = true;
	      	form.localidadeOrigemID.disabled = true;
	      	form.localidadeDestinoID.disabled = true;
		  	form.setorComercialOrigemCD.disabled = true;
	      	form.setorComercialDestinoCD.disabled = true;
	      	form.gerenciaRegional.disabled = true;
	      	form.unidadeNegocio.disabled = true;
	      	form.rotaInicial.disabled = true;
	      	form.rotaFinal.disabled = true;    
	      	//form.logradouroId.value = "";  
	      	form.logradouroId.disabled = true;
	      	//form.logradouroDescricao.value = "";
	      	form.logradouroDescricao.disabled = true;
	  	}else{
	      	form.cobrancaGrupo.disabled = false;
	      	form.localidadeOrigemID.disabled = false;
	      	form.localidadeDestinoID.disabled = false;
		  	form.setorComercialOrigemCD.disabled = false;
	      	form.setorComercialDestinoCD.disabled = false;
	      	form.gerenciaRegional.disabled = false;
	      	form.unidadeNegocio.disabled = false;  
	      	form.rotaInicial.disabled = false;
	      	form.rotaFinal.disabled = false;    
	      	//form.logradouroId.value = "";  
	      	form.logradouroId.disabled = false;
	      	//form.logradouroDescricao.value = "";
	      	form.logradouroDescricao.disabled = false;
	  	}
	}

	function verificarCobrancaAtividade(){
		///document.forms[0].cobrancaAtividade.options[document.forms[0].cobrancaAtividade.selectedIndex].id
    	var form = document.forms[0];
		if(form.cobrancaAtividade.value == 1){
			///		form.executarComando.disabled = true;	
		}else{
		}
	}

	function executarcomando(){
	
		var form = document.forms[0];
	
		if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form) 
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarQuadraDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& tratarExecutarComando()){	
			
		    form.action =  '/gsan/inserirComandoAcaoCobrancaEventualExecutarComandoAction.do'
			form.submit();
		}
	}

	function concluircomando(){
		var form = document.forms[0];
	
		if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form)
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarQuadraDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes() && verificarAtividadeEmitir() 
		&& validar()
		&& validarPeriodoFiscalizacaoImovel()
		&& validarPeriodoVencimentoConta()
		&& validarPeriodoConta){
			
			form.action =  'inserirComandoAcaoCobrancaEventualConcluirAction.do'
			botaoAvancarTelaEspera('inserirComandoAcaoCobrancaEventualConcluirAction.do'); 
	    
		}
	}

	function tratarExecutarComando(){
		var form = document.forms[0];
		if(form.cobrancaAtividade.value == 3){
			alert("O Caso de Uso - Encerrar Atividade de A��o de Cobran�a n�o foi implementado para esta itera��o.")
			form.cobrancaAtividade.focus();
			return false;
		}
		return true;
	}

	function validarLocalidadeDiferentes(){
		var form = document.forms[0];
	
		if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == ''){
			alert("Informe Localidade Final.");
			form.localidadeDestinoID.focus();
			return false;
		}
		if(form.localidadeOrigemID.value == '' && form.localidadeDestinoID.value != ''){
			alert("Informe Localidade Inicial.");
			form.localidadeOrigemID.focus();
			return false;
		}
		return true;
	}

function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	
	if(form.setorComercialOrigemCD.value!= '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	if(form.setorComercialOrigemCD.value == '' && form.setorComercialDestinoCD.value != ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
		return false;
	}
	return true;
}

function validarQuadraDiferentes(){
	var form = document.forms[0];
	
	if(form.numeroQuadraInicial.value!= '' && form.numeroQuadraFinal.value == ''){
		alert("Informe Quadra Final.");
		form.numeroQuadraFinal.focus();
		return false;
	}
	if(form.numeroQuadraInicial.value == '' && form.numeroQuadraFinal.value != ''){
		alert("Informe Quadra Inicial.");
		form.numeroQuadraInicial.focus();
		return false;
	}
	
	if(form.numeroQuadraInicial.value != '' && form.setorComercialOrigemCD.value == ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
		return false;
	}

	if(form.numeroQuadraFinal.value != '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	
	return true;
}

function validarRotasDiferentes(){
	var form = document.forms[0];
	
    if(form.rotaInicial != undefined){
		if(form.rotaInicial.value!= '' && form.rotaFinal.value == ''){
			alert("Informe Rota Final.");
			form.rotaFinal.focus();
			return false;
		}
		if(form.rotaInicial.value == '' && form.rotaFinal.value != ''){
			alert("Informe Rota Inicial.");
			form.rotaInicial.focus();
			return false;
		}
	}
	return true;
}

function verificarSetoresComerciaisMenores(){
	
	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value != '' && form.setorComercialDestinoCD.value != ''){
		if(form.setorComercialDestinoCD.value < form.setorComercialOrigemCD.value){
			alert("Setor Comercial Final menor que Setor Comercial Inicial.");	
			form.setorComercialDestinoCD.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}

function verificarLocalidadeMenores(){
	var form = document.forms[0];
	
	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value != ''){
		if(form.localidadeDestinoID.value < form.localidadeOrigemID.value){
			alert("Localidade Final menor que Localidade Inicial.");	
			form.localidadeDestinoID.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}



function validarCriterios(valor){

	var form = document.forms[0];

	if(valor == 1){ //validar a��o cobran�a
		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?validarCriterio=SIM&validar=Acao'
    	form.submit();
    }else if(valor == 2){//validar cobran�a atividade
		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?validarCriterio=SIM&validar=Atividade'
    	form.submit();
    	
    }
}

function verificar(){

	var form = document.forms[0];
	
	if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
///		form.executarComando.disabled = true;	
	}else if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
	///	form.executarComando.disabled = false;	
	}
}

function habilitar(valor){

	var form = document.forms[0];
	form.cobrancaAtividadeIndicadorExecucao.value = valor;
	if(valor == 1){//indicador de rota
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}else if(valor == 2){//indicador de comando
		form.indicador[1].checked = true;
		form.concluir.disabled  = true;
		form.Avancar.disabled = false;		
	}else if(valor == ""){
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}
}

function desabilitarCobrancaGrupo(){
	
	var form = document.forms[0];
	form.cobrancaGrupo.disabled = true;
	form.cobrancaGrupo.value = "";
}

function validarGrupoCobranca(visulizar){

	var form = document.forms[0];
	
///	if(form.cobrancaGrupo.value != ""){
		var ok = true;
		if(form.gerenciaRegional.value != "-1"){
			ok = false;
		}

		if(form.unidadeNegocio.value != "-1"){
			ok = false;
		}

		if(form.localidadeOrigemID.value != ""){
			ok = false;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = false;
		}
		if(ok == true){
			if(form.cobrancaGrupo.value != ""){
				form.gerenciaRegional.disabled = true;
				form.unidadeNegocio.disabled = true;				
		    	form.localidadeOrigemID.disabled = true;
				form.localidadeDestinoID.disabled = true;
		        form.setorComercialOrigemCD.disabled = true;
		        form.setorComercialDestinoCD.disabled = true;
		        form.imagem.enabled = true;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.disabled = true;
		    	   		form.rotaFinal.disabled = true;
			    }
			}else{
				form.gerenciaRegional.disabled = false;
				form.unidadeNegocio.disabled = false;				
		    	form.localidadeOrigemID.disabled = false;
				form.localidadeDestinoID.disabled = false;
		        form.setorComercialOrigemCD.disabled = false;
		        form.setorComercialDestinoCD.disabled = false;
		        form.imagem.enabled = false;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.disabled = false;
		    	   		form.rotaFinal.disabled = false;
			    }	
			}
		}else{
			if(visulizar == 1){
				alert("Ger�ncia Regional/Localidade/Setor Comercial/Rota informados");
				form.cobrancaGrupo.value = "";
			}
		}
	///}	
}


function validarGrupoCobrancaSelecionado(){

	var form = document.forms[0];
	
	if(form.cobrancaGrupo.value != "-1"){
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;		
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
        form.logradouroId.value = "";
        form.logradouroId.disabled = true;
        form.logradouroDescricao.value = "";
        form.logradouroDescricao.disabled = true;
        form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
        
	}else{
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = false;		
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
      
        form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        form.logradouroId.disabled = false;
        form.logradouroDescricao.disabled = false;
   		form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
	}
}



function validarLogradouroSelecionado(){

	var form = document.forms[0];
	if(form.logradouroId.value != ''){
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;		
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }
        //form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
        form.cobrancaGrupo.value != "-1";
        form.cobrancaGrupo.disabled = true;
        
	}else{
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = false;		
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
        //form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        form.cobrancaGrupo.value != "-1";
        form.cobrancaGrupo.disabled = false;
	}
}


function validarGerenciaRegionalSelecionado(){

	var form = document.forms[0];
	
	if(form.gerenciaRegional.value != "-1"){
		form.cobrancaGrupo.disabled = true;
    	form.localidadeOrigemID.readOnly = true;
		form.localidadeDestinoID.readOnly = true;
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.unidadeNegocio.disabled = true;
        form.unidadeNegocio.value = "-1";
        form.imagem.enabled = true;
   		form.rotaInicial.readOnly = true;
   		form.rotaFinal.readOnly = true;
   		form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
        form.idCliente.readOnly = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;

        form.idLocalidade.disabled = true;
        form.idLocalidade.value = "";
        form.descricaoLocalidade.value = "";
        
	}else{
		form.cobrancaGrupo.disabled = false;
    	form.localidadeOrigemID.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.unidadeNegocio.disabled = false;        
        form.imagem.enabled = false;
   		form.rotaInicial.readOnly = false;
   		form.rotaFinal.readOnly = false;
   		form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
        form.idCliente.readOnly = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;

        form.idLocalidade.disabled = false;
        form.idLocalidade.value = "";
        form.descricaoLocalidade.value = "";
	}
}

function validarUnidadeNegocioSelecionado(){

	var form = document.forms[0];
	
	if(form.unidadeNegocio.value != "-1"){
		form.cobrancaGrupo.disabled = true;
    	form.localidadeOrigemID.readOnly = true;
		form.localidadeDestinoID.readOnly = true;
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.gerenciaRegional.disabled = true;        
        form.imagem.enabled = true;
   		form.rotaInicial.readOnly = true;
   		form.rotaFinal.readOnly = true;
   		form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
        form.idCliente.readOnly = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;

        form.idLocalidade.disabled = true;
        form.idLocalidade.value = "";
        form.descricaoLocalidade.value = "";
	}else{
		form.cobrancaGrupo.disabled = false;
    	form.localidadeOrigemID.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.gerenciaRegional.disabled = false;        
        form.imagem.enabled = false;
   		form.rotaInicial.readOnly = false;
   		form.rotaFinal.readOnly = false;
   		form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
        form.idCliente.readOnly = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;

        form.idLocalidade.disabled = false;
        form.idLocalidade.value = "";
        form.descricaoLocalidade.value = "";
	}
}


function validarGrupoCobrancaParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(ok == 2){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
        	form.logradouroId.value = "";
       		form.logradouroId.disabled = true;
        	//form.logradouroDescricao.value = "";
       		form.logradouroDescricao.disabled = true;
		}
}

function validarClienteParaBloquear(){

	var form = document.forms[0];
	
		if(form.idCliente.value != ""){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
	    	form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
        	form.setorComercialOrigemCD.disabled = true;
	        form.setorComercialDestinoCD.disabled = true;
    	    form.imagem.enabled = true;
	    	if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
		    }
		}
}

function validarGerenciaRegionalParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
}

function validarLogradouroParaBloquear(){

	var form = document.forms[0];
		if(form.logradouroId.value != ""){
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
			form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
		}
}


function validarUnidadeNegocioParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
}

function validarHabilitarCampo(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.logradouroId.value != ""){
			ok = 2;
		}
		
		if(ok == 1){
			form.cobrancaGrupo.disabled = false;
		    form.gerenciaRegional.disabled = false;		    
		    form.unidadeNegocio.disabled = false;		    
			form.localidadeOrigemID.disabled = false;
			form.localidadeDestinoID.disabled = false;
			form.setorComercialOrigemCD.disabled = false;
		    form.setorComercialDestinoCD.disabled = false;
		    form.rotaInicial.disabled = false;
    	    form.rotaFinal.disabled = false;
        	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = false;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = false;
            form.logradouroId.disabled = false;
            //form.logradouroDescricao.disabled = false;
		}
}


function validarLocalidade(){

	var form = document.forms[0];

	if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		
	    form.setorComercialDestinoCD.value = "";
	    form.setorComercialDestinoID.value = "";		   
 	    form.nomeSetorComercialDestino.value = "";
		form.setorComercialOrigemCD.value = "";
		form.setorComercialOrigemID.value = "";
		form.nomeSetorComercialOrigem.value = "";
		alert("Para informar o Setor Comercial, a Localidade Inicial e Final precisam ser iguais.");
	}

}




function limparDestino(tipo){

	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";		
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
		    form.setorComercialDestinoCD.value = "";
		    desabilitarCobrancaAcao();
		case 2: //De setor para baixo
   	       form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
	   	   form.rotaInicial.value = "";
   	   	   form.rotaFinal.value = "";		   
	}
}

function limparOrigem(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidara pra baixo
			
			form.cobrancaGrupo.disabled = false;
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;			
	        form.idCliente.disabled = false;
    	    form.clienteRelacaoTipo.disabled = true;			
			
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
		case 2: //De setor para baixo
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
	   
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";		   
		   form.nomeSetorComercialDestino.value = "";
		   form.rotaInicial.value = "";
   		   form.rotaFinal.value = "";	
		case 3:
			form.idLocalidade.disabled = false;
			form.idLocalidade.value = "";
			form.descricaoLocalidade.value ="";
			selecionarSetorLocalidade();
				   
	}
}

function limparBorrachaDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";					
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
    		form.setorComercialDestinoCD.value = "";

		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
   		   form.setorComercialDestinoCD.value = "";
		   form.rotaInicial.value = "";
   		   form.rotaFinal.value = "";
	}
}

function habilitarRota(){
	var form = document.forms[0];

	if(form.setorComercialDestinoCD.value == form.setorComercialOrigemCD.value){
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.disabled = false;
 	   form.rotaFinal.disabled = false;	
	}else{	
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.disabled = true;
   	   form.rotaFinal.disabled = true;	

	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
      form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=origem'
      form.submit();
      
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  if(form.setorComercialDestinoCD.value == ""){
	      form.setorComercialDestinoCD.value = codigoRegistro;
    	  form.setorComercialDestinoID.value = idRegistro;
		  form.nomeSetorComercialDestino.value = descricaoRegistro;
		  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  }
	  
	}

	if (tipoConsulta == 'setorComercialDestino') {
	
//		if(form.setorComercialOrigemID.value != ""){
///			if(form.setorComercialOrigemCD.value < codigoRegistro){
	///			alert("Setor Comercial Final Maior que a Inicial");
		///	}else{
	///	}else{
	      form.setorComercialDestinoCD.value = codigoRegistro;
    	  form.setorComercialDestinoID.value = idRegistro;
      form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=destino'
      form.submit();

		  form.nomeSetorComercialDestino.value = descricaoRegistro;
		  form.nomeSetorComercialDestino.style.color = "#000000"; 
///	  }
	}

}


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
    	if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?idCobrancaAcaoAtividadeComando='+codigoRegistro
      		form.submit();
    	}

		if (tipoConsulta == 'localidadeOrigem') {
      		form.localidadeOrigemID.value = codigoRegistro;
	  		form.nomeLocalidadeOrigem.value = descricaoRegistro;
      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem'
      		form.submit();
	  		form.nomeLocalidadeOrigem.style.color = "#000000";
	  		if(form.localidadeDestinoID.value == ""){
	      		form.localidadeDestinoID.value = codigoRegistro;
		  		form.nomeLocalidadeDestino.value = descricaoRegistro;
		  		form.nomeLocalidadeDestino.style.color = "#000000";
	  		}
		}

		if (tipoConsulta == 'localidade') {
			
			if(form.tipoLocalidade.value == 'I'){
	      		
	      		form.localidadeOrigemID.value = codigoRegistro;
		  		form.nomeLocalidadeOrigem.value = descricaoRegistro;
		  		form.nomeLocalidadeOrigem.style.color = "#000000";
		  		
	      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem'
	      		form.submit();

		  		if(form.localidadeDestinoID.value == ""){
		      		form.localidadeDestinoID.value = codigoRegistro;
			  		form.nomeLocalidadeDestino.value = descricaoRegistro;
			  		form.nomeLocalidadeDestino.style.color = "#000000";
		  		}
		  		
			} else if ( form.tipoLocalidade.value == 'P' ) {

				form.idLocalidade.value = codigoRegistro;
		  		form.descricaoLocalidade.value = descricaoRegistro;
		  		form.descricaoLocalidade.style.color = "#000000";

		  		selecionarSetorLocalidade();
		  		habilitarLocalidadeSetores();
				
			} else {

    			form.localidadeDestinoID.value = codigoRegistro;
	    		form.nomeLocalidadeDestino.value = descricaoRegistro;
	 			form.nomeLocalidadeDestino.style.color = "#000000";    		
        	
	        	form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=destino'
	        	form.submit();
	    		
 			}
		}
	
     	if (tipoConsulta == 'cliente') {
	 	if (form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteSuperior') {
	 		form.codigoClienteSuperior.value = codigoRegistro;
      		form.nomeClienteSuperior.value = descricaoRegistro;
      		form.nomeClienteSuperior.style.color = "#000000";
      		validarClienteSuperior(1);	
      		desabilitarLocalidadeCobrancaAcao();
	 	} else {
	        form.idCliente.value = codigoRegistro;
      		form.nomeCliente.value = descricaoRegistro;
      		form.nomeCliente.style.color = "#000000";
     	 	form.clienteRelacaoTipo.disabled = false;
     	 	validarCliente(1);	
     	 	desabilitarLocalidadeCobrancaAcao()
        }
        
     }
     
      if (tipoConsulta == 'logradouro') {
  	      	form.logradouroId.value = codigoRegistro;
	      	form.logradouroDescricao.value = descricaoRegistro;
	      	form.logradouroDescricao.style.color = '#000000';
	      	
	      	validarLogradouroSelecionado();
	   } 	
      		

}


function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	if(destino == "inicial"){
		form.rotaInicial.value = codigoRota;		
		form.rotaFinal.value = codigoRota;				
	}else if(destino == "final"){
		form.rotaFinal.value = codigoRota;		
	}

}

function avancar(){
	var form = document.forms[0]; 
		
	if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form) 
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes() && validar() && verificarAtividadeEmitir()){
			
			form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioComandoAction.do?idCobrancaAtividade='+form.cobrancaAtividade.value+'&idCobrancaAcao='+form.cobrancaAcao.value
   			form.submit();
   	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){


	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirInserirComandoAcaoCobrancaAction.do?menu=sim'
	formulario.submit();
}

function mensagem(mensagem){
	if(mensagem.length > 0){
		alert(mensagem);
	}
}

function validarExistenciaLocalidadeInicial(){
	var form = document.forms[0]; 
	if(form.localidadeOrigemID.value == ""){
		form.localidadeDestinoID.value = "";
///		alert("Informe a Localidade Inicial.");
		form.localidadeOrigemID.focus();
		
		
		
	}
}

function validarExistenciaSetorComercialInicial(){
	var form = document.forms[0]; 
	if(form.setorComercialOrigemCD.value == ""){
		form.setorComercialDestinoCD.value = "";
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
	}
}


function habilitacaoCampoRota(){

	var form = document.forms[0];
	
	var setorComercialOrigem = trim(form.setorComercialOrigemCD.value);
	var setorComercialDestino = trim(form.setorComercialDestinoCD.value);
	
	if (setorComercialOrigem.length > 0 && setorComercialDestino.length > 0){
		
		if (setorComercialOrigem != setorComercialDestino){
			form.rotaInicial.disabled = true;
			form.rotaFinal.disabled = true;
		}
		else{
			form.rotaInicial.disabled = false;
			form.rotaFinal.disabled = false;
		}
		
	}
}

function replicarSetorComercial(){
	var form = document.forms[0];
	if(form.setorComercialDestinoCD.value == ""){
		form.setorComercialDestinoCD.value = form.setorComercialOrigemCD.value;
	}	
}

function replicarLocalidade(){
	var form = document.forms[0];
	if(form.localidadeDestinoID.value == ""){
		form.localidadeDestinoID.value = form.localidadeOrigemID.value;	
	}
	
}


function desabilitaIntervaloDiferente(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade 
		    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		        form.setorComercialOrigemCD.value = "";
			 	form.setorComercialDestinoCD.value = "";
		        form.setorComercialOrigemCD.disabled = true;
			 	form.setorComercialDestinoCD.disabled = true;
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
             	}
			 
			  }else{
		        form.setorComercialOrigemCD.disabled = false;
			 	form.setorComercialDestinoCD.disabled = false;
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;
             	}
			  }
			break;					
		case 2: //De setor Comercial		   
		    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
             	}
  			  }else{
				   if(form.rotaInicial != undefined){
					  	form.rotaInicial.disabled = false;
			 			form.rotaFinal.disabled = false;
			 		}
			  }
			break;
		case 3://De Rota 

		   if(form.rotaInicial != undefined){
		   
		     if(form.rotaInicial.value != form.rotaFinal.value){
			  	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
             	form.rotaInicial.value = "";
             	form.rotaFinal.value = "";
			  }else{
			  	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			  }

			}
			break;
		}
}

function validarAbrirPoupPesquisarCliente(){
	var form = document.forms[0];	
							
	if(form.idCliente.disabled == false){
	    form.tipoPesquisa.value = 'cliente';
	    chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'cliente', null, null, 275, 480, '',form.idCliente.value);
	}
	
}



function validarLimparCliente(){
	var form = document.forms[0];						
	if(form.idCliente.disabled == false){
		limparCliente();
	}
}






function duplicarRota(){
	var formulario = document.forms[0]; 
	formulario.rotaFinal.value = formulario.rotaInicial.value;
}  

function carregarRotaInicial(){

	var formulario = document.forms[0]; 
	
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaInicial.options.length == 1){
	   		formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}

function limparRota(){
	var formulario = document.forms[0]; 
	formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?limparRota=CARREGAR'
	formulario.submit();	
}

function carregarRotaFinal(){
	var formulario = document.forms[0]; 
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaFinal.options.length == 1){
	   		formulario.action =  '/gsan/exibirFiltrarComandosAcaoCobrancaEventualAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}

function validarPreenchimento(){

	var formulario = document.forms[0]; 
    if(formulario.localidadeOrigemID.value == ''){

    } 
    if(formulario.localidadeDestinoID.value == ''){

    }
	if(formulario.setorComercialOrigemCD.value == ''){

	}
	if(formulario.setorComercialDestinoCD.value == ''){

	}

}

function validarSetorFinal(){
	var formulario = document.forms[0]; 
	if(formulario.setorComercialDestinoCD.value == ''){
///		limparRota();
	}
}

function validarInscricoes(){
   var form = document.forms[0];
   var msg = '';
   if(form.setorComercialOrigemCD.value != "" && form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value != "" && form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.rotaInicial.value != "" && form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.rotaFinal.value != "" &&form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){
   		alert(msg);
		return false;
   }else{
		return true;
   }
}


function pesquisarRota(destino){
   var form = document.forms[0];
   var msg = '';
   if(form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){

   }else{
		var msgDois = '';
		
	   if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		   msgDois = 'Localidades diferentes.\n';
	   }
	   if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
		   msgDois = msgDois + 'Setores Comeriais diferentes.\n';
	   }
	   if( msgDois != '' ){
		}else{
			abrirPopup('exibirPesquisarRotaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value+'&destino='+destino, 250, 495);
		}
	}
}

function verificarAtividadeEmitir(){
var form = document.forms[0];
 if(form.cobrancaAtividade.value == form.idAtividadeCobrancaEmitir.value){
  if(form.prazoExecucao.value == ''){
   alert("Informe Prazo de Execu��o.");
   return false;
  }else{
   return true;
  }
 }else{
  return true;
 }
}

function verificarCampoPrazoExecucao(){
var form = document.forms[0];
 if(form.cobrancaAtividade != null && form.cobrancaAtividade.value != form.idAtividadeCobrancaEmitir.value){
  form.prazoExecucao.value = '';
  form.prazoExecucao.disabled = true; 
 }else{
  form.prazoExecucao.disabled = false; 
 }
}

function  validarAbrirPoupPesquisarClienteSuperior() {
	var form = document.forms[0];
	if (form.codigoClienteSuperior.readOnly == false) {
		form.tipoPesquisa.value = 'clienteSuperior';
		chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
	}	
}

function validarLimparClienteSuperior(){
	var form = document.forms[0];						
	if(form.codigoClienteSuperior.disabled == false){
		limparClienteSuperior();
	}
}

function limparClienteSuperior(){
  	  var form = document.forms[0];
      form.nomeClienteSuperior.value = ""
      form.codigoClienteSuperior.value = ""
      form.nomeCliente.value = "";
      form.idCliente.disabled = false;
}


function limparCliente(){
  	  var form = document.forms[0];
       form.nomeCliente.value = ""
       form.idCliente.value = ""
       form.clienteRelacaoTipo.value = "";
       form.clienteRelacaoTipo.disabled = true;
       form.nomeClienteSuperior.value = "";
       form.codigoClienteSuperior.disabled = false;
}

function validarClienteSuperior(apagar){
    	var form = document.forms[0];
    	if (form.codigoClienteSuperior.value != ''){
      		if(apagar == 1) form.clienteRelacaoTipo.value = "";	
      		form.clienteRelacaoTipo.disabled = true;
      		form.idCliente.disabled = true;
      		form.idCliente.value = "";
      		form.nomeCliente.value = "";
    	}else{
      		form.clienteRelacaoTipo.disabled = false;
      		form.idCliente.disabled = false;
   	 	}
  	}

/* Limpar Logradouro */
function limparLogradouro() {
		var form = document.forms[0];
		
      	form.logradouroId.value = '';
	    form.logradouroDescricao.value = '';
	
		form.logradouroId.focus();
}

function validarExibicaoTipoCosumo(){

	var form = document.forms[0];

	if(form.consumoMedioInicial.value!="" && form.consumoMedioFinal.value!=""){
		form.tipoConsumo.disabled = false;
	}else{
		form.tipoConsumo.value = '1';
		form.tipoConsumo.disabled = true;;
	}
	
}

	
	function validarQuadra(nomeCampo, idDependencia, nomeDependencia){
      
		var form = document.forms[0];
		
		var objetoCampo = nomeCampo;
		var valorCampo = nomeCampo.value;
	
		if(idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
		   idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			
			alert('Informe ' + nomeDependencia);
			form.setorComercialOrigemCD.focus();
			
		}
	}
	
	function limparDescricao(campo){
		campo.value = "";
	}

	function selecionarLocalidadesGerencia(){
		var form = document.forms[0];

		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=5&inscricaoTipo=gerencia'
		form.submit();	
		
	}

	function selecionarSetorLocalidade(){
		var form = document.forms[0];

		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=6&inscricaoTipo=setor'
		form.submit();	
		
	}

	function habilitaLocalidade(){
		var form = document.forms[0];

		if ( form.idLocalidade.value != null && form.idLocalidade.value != '' ) {

			form.idLocalidade.disabled = true;
			
		} else { 
			form.idLocalidade.disabled = false;
			if ( form.gerenciaRegional.value != "-1" ){

				form.idLocalidade.disabled = true;
				
			} else {
				form.idLocalidade.disabled = false;
			}
		}

		
	}

	function habilitarLocalidadeSetores(){

		var form = document.forms[0];
		
		if(form.idLocalidade.value != ""){
			form.gerenciaRegional.disabled = true;
			form.cobrancaGrupo.disabled = true;
	    	form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
	        form.setorComercialOrigemCD.disabled = true;
	        form.setorComercialDestinoCD.disabled = true;
	        form.unidadeNegocio.disabled = true;        
	        form.imagem.enabled = true;
		    if(form.rotaInicial != undefined){
	    	   		form.rotaInicial.disabled = true;
	    	   		form.rotaFinal.disabled = true;
		    }
	        form.nomeCliente.value = ""
	        form.idCliente.value = ""
	        form.idCliente.disabled = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;
	        form.logradouroId.value = "";
	        form.logradouroId.disabled = true;
	        form.logradouroDescricao.value = "";
	        form.logradouroDescricao.disabled = true;
	        form.numeroQuadraInicial.readOnly = true;
	        form.numeroQuadraFinal.readOnly = true;
	        form.numeroQuadraInicial.value = "";
	        form.numeroQuadraFinal.value = "";
	        
		}else{
			form.gerenciaRegional.disabled = false;
			form.cobrancaGrupo.disabled = false;
	    	form.localidadeOrigemID.disabled = false;
			form.localidadeDestinoID.disabled = false;
	        form.setorComercialOrigemCD.disabled = false;
	        form.setorComercialDestinoCD.disabled = false;
	        form.unidadeNegocio.disabled = false;        
	        form.imagem.enabled = false;
		    if(form.rotaInicial != undefined){
	    	   		form.rotaInicial.disabled = false;
	    	   		form.rotaFinal.disabled = false;
		    }	
	        form.idCliente.value = ""
	        form.idCliente.disabled = false;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = false;
	        form.logradouroId.disabled = false;  
	        form.logradouroDescricao.disabled = false;
	   		form.numeroQuadraInicial.readOnly = false;
	        form.numeroQuadraFinal.readOnly = false;
	       
		}
	}

	function habilitarCampos(){
		var form = document.forms[0];
		
		if(form.idLocalidade != undefined && form.idLocalidade.value != ""){
			form.gerenciaRegional.disabled = true;
			form.cobrancaGrupo.disabled = true;
	    	form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
	        form.setorComercialOrigemCD.disabled = true;
	        form.setorComercialDestinoCD.disabled = true;
	        form.unidadeNegocio.disabled = true;        
	        form.imagem.enabled = true;
		    if(form.rotaInicial != undefined){
	    	   		form.rotaInicial.disabled = true;
	    	   		form.rotaFinal.disabled = true;
		    }
	        form.nomeCliente.value = ""
	        form.idCliente.value = ""
	        form.idCliente.disabled = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;
	        form.logradouroId.value = "";
	        form.logradouroId.disabled = true;
	        form.logradouroDescricao.value = "";
	        form.logradouroDescricao.disabled = true;
	        form.numeroQuadraInicial.readOnly = true;
	        form.numeroQuadraFinal.readOnly = true;
	        form.numeroQuadraInicial.value = "";
	        form.numeroQuadraFinal.value = "";
	        
		}else if(form.gerenciaRegional != undefined && form.gerenciaRegional.value != "-1"){
			form.cobrancaGrupo.disabled = true;
	    	form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
	        form.setorComercialOrigemCD.readOnly = true;
	        form.setorComercialDestinoCD.readOnly = true;
	        form.unidadeNegocio.disabled = true;
	        form.imagem.enabled = true;
	   		form.rotaInicial.readOnly = true;
	   		form.rotaFinal.readOnly = true;
	   		form.numeroQuadraInicial.readOnly = true;
	        form.numeroQuadraFinal.readOnly = true;
	        form.numeroQuadraInicial.value = "";
	        form.numeroQuadraFinal.value = "";
	        form.idCliente.readOnly = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;

	        form.idLocalidade.disabled = true;
	        form.idLocalidade.value = "";
	        form.descricaoLocalidade.value = "";
	        
		}else if(form.unidadeNegocio != undefined && form.unidadeNegocio.value != "-1"){
			form.cobrancaGrupo.disabled = true;
	    	form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
	        form.setorComercialOrigemCD.readOnly = true;
	        form.setorComercialDestinoCD.readOnly = true;
	        form.gerenciaRegional.disabled = true;        
	        form.imagem.enabled = true;
	   		form.rotaInicial.readOnly = true;
	   		form.rotaFinal.readOnly = true;
	   		form.numeroQuadraInicial.readOnly = true;
	        form.numeroQuadraFinal.readOnly = true;
	        form.numeroQuadraInicial.value = "";
	        form.numeroQuadraFinal.value = "";
	        form.idCliente.readOnly = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;

	        form.idLocalidade.disabled = true;
	        form.idLocalidade.value = "";
	        form.descricaoLocalidade.value = "";								
		}else {
			form.gerenciaRegional.disabled = false;
			form.cobrancaGrupo.disabled = false;
	    	form.localidadeOrigemID.disabled = false;
			form.localidadeDestinoID.disabled = false;
	        form.setorComercialOrigemCD.disabled = false;
	        form.setorComercialDestinoCD.disabled = false;
	        form.unidadeNegocio.disabled = false;        
	        form.imagem.enabled = false;
		    if(form.rotaInicial != undefined){
	    	   		form.rotaInicial.disabled = false;
	    	   		form.rotaFinal.disabled = false;
		    }	
	        form.idCliente.value = ""
	        form.idCliente.disabled = false;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = false;
	        if(form.logradouroId != undefined){
	        	form.logradouroId.disabled = false; 
	        }
	        if(form.logradouroDescricao != undefined){
	        	form.logradouroDescricao.disabled = false;
	        }
	   		form.numeroQuadraInicial.readOnly = false;
	        form.numeroQuadraFinal.readOnly = false;
	       
		}
	}


-->
</script>
</head>

<div id="formDiv">
<body leftmargin="5" topmargin="5"
	onload="javascript:mensagem('${requestScope.mensagem}');setarFoco('${requestScope.nomeCampo}');habilitarCampos();">
<html:form action="/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do"
   name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
   type="gcom.gui.cobranca.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
   method="post"
   onsubmit="return validateInserirComandoAcaoCobrancaEventualCriterioRotaActionForm(this);"
   enctype="multipart/form-data">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoLocalidade" />
	
	<input type="hidden" name="idAtividadeCobrancaEmitir" value= "<%=CobrancaAtividade.EMITIR %>"/>
	<input type="hidden" name="tipoPesquisa" />
	<input type="hidden" name="cancelarValidacao" value="true" />
	
	<input type="hidden" name="periodoFinalContaHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoFinalConta"/>' />
	<input type="hidden" name="periodoVencimentoContaFinalHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoVencimentoContaFinal"/>' />
	<input type="hidden" name="periodoFinalFiscalizacaoHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoVencimentoContaFinal"/>' />
	

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


			<td valign="top" class="centercoltext"><!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a - Grupo de Cobran�a</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0"  cellpadding="0" cellspacing="3">
				<tr>
					<td colspan="4">Para determinar a a&ccedil;&atilde;o de
					cobran&ccedil;a a ser comandada, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="16%"><strong>T�tulo:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="titulo" size= "60" maxlength="100" tabindex="1"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Descri��o da Solicita��o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:textarea property="descricaoSolicitacao" rows="5" cols="45" tabindex="2"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="cobrancaAcao" style="height: 100px" multiple="true" tabindex="3">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAcao">
							<html:options name="session" collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Atividade de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="cobrancaAtividade" onchange="validarCriterios(2);" tabindex="4">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAtividade">

							<html:options name="session"
								collection="colecaoCobrancaAtividade"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>

					</html:select> </td>
				</tr>
				<tr>
					<logic:equal value="2" property="cobrancaAtividade" name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm">
						<td width="16%"><strong>Prazo de Execu��o:<font color="#FF0000">*</font></strong></td>
					</logic:equal>
					<logic:notEqual value="2" property="cobrancaAtividade" name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm">
						<td width="16%"><strong>Prazo de Execu��o:</strong></td>
					</logic:notEqual>
					<td colspan="3"><html:text property="prazoExecucao" tabindex="3" size="3" maxlength="4" onkeypress="return isCampoNumerico(event);" onkeyup="somente_numero_zero_a_nove(this);" onblur="somente_numero_zero_a_nove(this);"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Quantidade M�xima de Documentos:</strong></td>
					<td colspan="3"><html:text property="quantidadeMaximaDocumentos" 
											   tabindex="4" 
											   size="4" 
											   maxlength="4" 
											   onkeypress="return isCampoNumerico(event);"
											   onkeyup="somente_numero_zero_a_nove(this);"
											   onblur="somente_numero_zero_a_nove(this);"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Valor Limite para Emiss�o Obrigat�ria:</strong></td>
					<td colspan="3"><html:text property="valorLimiteObrigatoria" size="9"
							maxlength="9" tabindex="5" 
							onkeyup="formataValorMonetario(this, 9)" 
							style="text-align:right;" onkeypress="return isCampoNumerico(event);"
							onblur="somente_numero_zero_a_nove(this);"/>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Apenas para Im�veis com D�bito:</strong></td>
					<td colspan="3"><html:radio property="indicadorImoveisDebito" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorImoveisDebito" value="2" /> 
					                N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<html:hidden
						property="cobrancaAtividadeIndicadorExecucao" />
					<td width="16%"><strong>Gerar Boletins de Cadastro:</strong></td>
					<td ><html:radio property="indicadorGerarBoletimCadastro" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorGerarBoletimCadastro" value="2" /> 
					                N&atilde;o</strong>
					
					<div align="right"><input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comandos"
						onClick="javascript:abrirPopup('exibirPesquisarComandoAcaoCobrancaAction.do', 400, 750);" /></div>
				</tr>
				
				
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
					<td colspan="3"><html:select property="cobrancaGrupo" onchange="validarGrupoCobrancaSelecionado();" tabindex="3">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaGrupo">
							<html:options name="session" collection="colecaoCobrancaGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Ger&ecirc;ncia Regional:<strong></strong></strong></td>
					<td width="60%" colspan="3">
						<div align="left">
							<strong> 
								<html:select tabindex="4"
											 name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
											 onchange="javaScript:validarGerenciaRegionalSelecionado();selecionarLocalidadesGerencia();"
											 property="gerenciaRegional">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:present scope="session" name="colecaoGerenciaRegional">
					         			<logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciasRegionais">
		 										<html:option value="${colecaoGerenciasRegionais.id}">
						           				<bean:write name="colecaoGerenciasRegionais" property="nomeAbreviado"/> - <bean:write name="colecaoGerenciasRegionais" property="nome"/>
						          			</html:option>
					         			</logic:iterate>
									</logic:present>
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				
				<logic:present name="colecaoLocalidadeGerencia" scope="session">
					<tr>
						<td align="left" colspan="4">
							<strong>Localidades:</strong>
						</td>
					</tr>
					<tr>
					<td colspan="4">			
						<table width="100%" cellpadding="0" cellspacing="0" >
					
							<tr bordercolor="#000000" >
								<td width="10%" bgcolor="#90c7fc" align="center">
									
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
									<strong>C�digo</strong>
							   </td>
								<td width="80%" bgcolor="#90c7fc" align="center">
									<strong>Descri��o</strong>
							   </td>
							</tr>	
							<tr>
								<td height="100" colspan="4">
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" bgcolor="#90c7fc" >			
											<%	int cont = 0;	%> 
											<logic:iterate name="colecaoLocalidadeGerencia" 
												id="dados"
												scope="session" 
												type="Localidade">
									                    
							                    <%	cont = cont + 1;
													if (cont % 2 == 0) {	%>
								               			<tr bgcolor="#cbe5fe"  >
								             	<%	} else {	%>
								               			<tr bgcolor="#FFFFFF" >
								             	<%	}	%>
								                            <td width="10%" align="center" >
								                            	<input type="checkbox" name="idRegistrosLocalidade" value="${dados.id}" />
															</td>
															<td width="10%" align="right" >
																<bean:write name="dados" property="id" />
															</td>
															<td  width="80%" align="center">
																<bean:write name="dados" property="descricao" />
															</td>
								                      	</tr>
				                   				</logic:iterate>
			                   				</table>
		                   				</div>
	                   				</td>
	                   			</tr>
	                   		</table>
               			</td>
               		</tr>
		        </logic:present>

				<tr>
					<td height="24"><strong>Unidade Neg�cio:<strong></strong></strong></td>
					<td width="60%" colspan="3">
					<div align="left"><strong> <html:select tabindex="4"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						onchange="javaScript:validarUnidadeNegocioSelecionado();"
						property="unidadeNegocio">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoUnidadeNegocio">
				         <logic:iterate name="colecaoUnidadeNegocio" id="colecaoUnidadeNegocios">
								<html:option value="${colecaoUnidadeNegocios.id}">
					           <bean:write name="colecaoUnidadeNegocios" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoUnidadeNegocios" property="nome"/>
					          </html:option>
				         </logic:iterate>
						</logic:present>
					</html:select></strong></div>
					</td>

				</tr>
				
				<tr>

					<td><strong>Localidade:</strong></td>
					<td width="81%" align="right" colspan="2">
					<div align="left">
						<html:text maxlength="3"
							property="idLocalidade" 
							size="3" 
							tabindex="5"
							onchange="limparDescricao(document.forms[0].descricaoLocalidade);"
							onkeypress="limparDescricao(document.forms[0].descricaoLocalidade);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=6&inscricaoTipo=localidade', 'idLocalidade', 'Localidade');return isCampoNumerico(event);"
							onkeyup="javaScript:somente_numero_zero_a_nove(this);habilitarLocalidadeSetores();"
							onblur="javascript:somente_numero_zero_a_nove(this);habilitarLocalidadeSetores();"/> 
							
						<a href="javascript:document.forms[0].tipoLocalidade.value='P';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'localidade', null, null, 275, 480, '',document.forms[0].idLocalidade);">
                       		<img border="0" 
                       			src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
                       			border="0"/></a>

						<strong> 
						<logic:present name="corIdLocalidade">
							<logic:equal name="corIdLocalidade" value="exception">
								<html:text property="descricaoLocalidade" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corIdLocalidade" value="exception">
								<html:text property="descricaoLocalidade" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> 
						
						<logic:notPresent name="corIdLocalidade">
							<logic:empty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="idLocalidade">
							
							<html:text property="descricaoLocalidade" 
								value="" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="idLocalidade">
								
								<html:text property="descricaoLocalidade" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparOrigem(3);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  					
						</strong>
						</div>
					</td>

				</tr>
				
				<logic:present name="colecaoSetorComercialLocalidade" scope="session">
					<tr>
						<td align="left" colspan="4">
							<strong>Setor Comercial:</strong>
						</td>
					</tr>
					<tr>
					<td colspan="4">			
						<table width="100%" cellpadding="0" cellspacing="0" >
					
							<tr bordercolor="#000000" >
								<td width="10%" bgcolor="#90c7fc" align="center">
									
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
									<strong>C�digo</strong>
							   </td>
								<td width="40%" bgcolor="#90c7fc" align="center">
									<strong>Descri��o</strong>
							   </td>
							   <td width="20%" bgcolor="#90c7fc" align="center">
									<strong>Munic�pio</strong>
							   </td>
							   <td width="20%" bgcolor="#90c7fc" align="center">
									<strong>Set. Alternativo</strong>
							   </td>
							</tr>		
							<tr>
								<td height="100" colspan="5">
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" bgcolor="#90c7fc" >			
											<%	int cont = 0;	%> 
											<logic:iterate name="colecaoSetorComercialLocalidade" 
												id="dados"
												scope="session" 
												type="SetorComercial">
									                    
							                    <%	cont = cont + 1;
													if (cont % 2 == 0) {	%>
								               			<tr bgcolor="#cbe5fe"  >
								             	<%	} else {	%>
								               			<tr bgcolor="#FFFFFF" >
								             	<%	}	%>
								                            <td width="10%" align="center" >
								                            	<input type="checkbox" name="idRegistrosSetorComercial" value="${dados.id}" />
															</td>
															<td width="10%" align="right" >
																<bean:write name="dados" property="codigo" />
															</td>
															<td  width="40%" align="center">
																<bean:write name="dados" property="descricao" />
															</td>
															<td  width="20%" align="center">
																<bean:write name="dados" property="municipio.nome" />
															</td>
															<td  width="20%" align="center">
																<bean:write name="dados" property="descricaoIndicadorSetorAlternativo" />
															</td>
								                      	</tr>
				                   				</logic:iterate>
			                   				</table>
		                   				</div>
	                   				</td>
	                   			</tr>
						
                   			</table>
                   		</td>
                   	</tr>
          		</logic:present>
				
				
				

				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				
				<html:hidden property="inscricaoTipo" />
				<tr>

					<td><strong>Localidade Inicial:</strong></td>
					<td width="81%" align="right" colspan="2">
					<div align="left">
						<html:text maxlength="3"
							property="localidadeOrigemID" 
							size="3" 
							tabindex="5"
							onchange="limparDescricao(document.forms[0].nomeLocalidadeOrigem);"
							onkeypress="limparDescricao(document.forms[0].nomeLocalidadeOrigem);limparDestino(1);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem&validarCriterio=naoAcao', 'localidadeOrigemID', 'Localidade Inicial');return isCampoNumerico(event);"
							onkeyup="javaScript:desabilitarCobrancaAcao();somente_numero_zero_a_nove(this);"
							onblur="javascript:somente_numero_zero_a_nove(this);replicarLocalidade();"/> 
							
						<a href="javascript:document.forms[0].tipoLocalidade.value='I';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);">
                       		<img border="0" 
                       			src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
                       			border="0"/></a>

						<strong> 
						<logic:present name="corLocalidadeOrigem">
							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> 
						
						<logic:notPresent name="corLocalidadeOrigem">
							<logic:empty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="localidadeOrigemID">
							
							<html:text property="nomeLocalidadeOrigem" 
								value="" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="localidadeOrigemID">
								
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparOrigem(1);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  					
						</strong></div>
					</td>

				</tr>

				<tr>
					<td><strong>Setor Comercial Inicial: </strong></td>
					<td align="right" colspan="2">
					<div align="left"><html:text maxlength="3" tabindex="6"
						property="setorComercialOrigemCD" size="3"
						onblur="javascript:somente_numero_zero_a_nove(this);replicarSetorComercial();"
						onkeyup="javascript:validarPreenchimento();somente_numero_zero_a_nove(this);"
						onchange="limparDescricao(document.forms[0].nomeSetorComercialOrigem);"
						onkeypress="limparDescricao(document.forms[0].nomeSetorComercialOrigem);
									limparDestino(2);
						            validaEnterDependenciaComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=origem&validarCriterio=naoAcao', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial', 'Setor Comercial Inicial');
						            return isCampoNumerico(event);"
						 /> 
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeOrigemID.value+'&tipo=setorComercialOrigem&indicadorUsoTodos=1',document.forms[0].localidadeOrigemID.value,'Localidade da inscri��o de origem', 400, 800);">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
					<logic:present name="corSetorComercialOrigem">
						<logic:equal name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corSetorComercialOrigem">
						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
						
					</logic:notPresent> 
						<a href="javascript:limparOrigem(2);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  										
					<html:hidden property="setorComercialOrigemID" /></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					<td align="left" colspan="3">
						<html:text maxlength="5" 
							property="numeroQuadraInicial" 
							size="5"  
							tabindex="3"
                   			onblur="validarQuadra(this, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial');"
                   			onkeypress="return isCampoNumerico(event);"
                   			onkeyup="somente_numero_zero_a_nove(this);"
                   			onblur="somente_numero_zero_a_nove(this);" 
                   			/>
                   </td>					
				</tr>


				<tr>
					<td><strong>Rota Inicial:<font color="#FF0000"> </font></strong></td>
					<td align="left" colspan="3">
						<html:text maxlength="4" tabindex="7"
						property="rotaInicial" size="4"
						onchange="validaNumero(this);duplicarRota();" 
						onkeypress="return isCampoNumerico(event);"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						onkeyup="somente_numero_zero_a_nove(this);"
						onblur="somente_numero_zero_a_nove(this);"/> 
						<a href="javascript:pesquisarRota('inicial');">
						<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Rota" /></a>						
					</td>
				</tr>

				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>


				<tr>
					<td><strong>Localidade Final:<font color="#FF0000"> </font></strong></td>
					<td width="37%" align="left" colspan="2"><html:text maxlength="3"
						property="localidadeDestinoID" size="3" tabindex="8"
						onchange="limparDescricao(document.forms[0].nomeLocalidadeDestino);"
						onkeyup="somente_numero_zero_a_nove(this);desabilitaIntervaloDiferente(1);validarPreenchimento();"
						onkeypress="limparDescricao(document.forms[0].nomeLocalidadeDestino);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=destino&validarCriterio=naoAcao', 'localidadeDestinoID', 'Localidade Final');return isCampoNumerico(event);"
						onblur="somente_numero_zero_a_nove(this);"
						/>
						<a href="javascript:document.forms[0].tipoLocalidade.value='F';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
						<logic:present name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeDestino">

						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent>
						<a href="javascript:limparBorrachaDestino(1);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  					
					</td>
				</tr>

				<tr>
					<td><strong>Setor Comercial Final: </strong></td>
					<td align="left" colspan="2"><html:text maxlength="3"
						property="setorComercialDestinoCD" size="3" tabindex="9"
						onchange="limparDescricao(document.forms[0].nomeSetorComercialDestino);"
						onkeyup="somente_numero_zero_a_nove(this);desabilitaIntervaloDiferente(2);habilitarRota();validarPreenchimento();validarSetorFinal();"
						onkeypress="limparDescricao(document.forms[0].nomeSetorComercialDestino);validaEnterDependenciaComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=destino&validarCriterio=naoAcao', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final', 'Setor Comercial Final');
						return isCampoNumerico(event);"
						onblur="somente_numero_zero_a_nove(this);" /> 
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeDestinoID.value+'&tipo=setorComercialDestino&indicadorUsoTodos=1',document.forms[0].localidadeDestinoID.value,'Localidade da inscri��o de destino', 400, 800);">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
						 <logic:present name="corSetorComercialDestino">

						<logic:equal name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercialDestino">

						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <html:hidden property="setorComercialDestinoID" />
						<a href="javascript:limparBorrachaDestino(2);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  
					</td>
				</tr>
				
				<tr>
					<td><strong>Quadra Final:</strong></td>
					<td align="left" colspan="3">
						<html:text maxlength="5" 
							property="numeroQuadraFinal" 
							size="5"  
							tabindex="3"
                   			onblur="somente_numero_zero_a_nove(this);validarQuadra(this, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final');"
                   			onkeypress="return isCampoNumerico(event);"
                   			onkeyup="somente_numero_zero_a_nove(this);"/>
                   </td>					
				</tr>
				
				<tr>
					<td><strong>Rota Final:<font color="#FF0000"> </font></strong></td>
					<td align="right" colspan="3">
						<div align="left">
							<html:text maxlength="4" tabindex="10"
								property="rotaFinal" size="4"
								name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="somente_numero_zero_a_nove(this);"
								onblur="somente_numero_zero_a_nove(this);"/> 
								<a href="javascript:pesquisarRota('final');">
								<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Rota" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>C�digo do Cliente Superior:</strong></td>
					<td colspan="3">
						<html:text property="codigoClienteSuperior" maxlength="9" size="9" 
						onchange="limparDescricao(document.forms[0].nomeClienteSuperior);"
						onkeyup="javaScript:somente_numero_zero_a_nove(this);validarClienteSuperior(1);desabilitarLocalidadeCobrancaAcao();"
						onkeypress="javascript:limparDescricao(document.forms[0].nomeClienteSuperior);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=3&inscricaoTipo=superior&validarCriterio=naoAcao', 'codigoClienteSuperior');return isCampoNumerico(event);"
						onblur="somente_numero_zero_a_nove(this);" 
						/>
						<a
						href="javascript:validarAbrirPoupPesquisarClienteSuperior();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> 
						<logic:present
						  name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<input type="text" name="nomeClienteSuperior" size="40" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE SUPERIOR INEXISTENTE" />
					     </logic:present> 
					   <logic:notPresent
						name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<html:text property="nomeClienteSuperior" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> 
					<a href="javascript:validarLimparClienteSuperior();desabilitarLocalidadeCobrancaAcao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Cliente:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="9"
						property="idCliente" size="9" tabindex="11"
						onchange="limparDescricao(document.forms[0].nomeCliente);"
						onkeyup="javaScript:somente_numero_zero_a_nove(this);validarCliente(1);desabilitarLocalidadeCobrancaAcao();"
						onkeypress="javascript:limparDescricao(document.forms[0].nomeCliente);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=3&validarCriterio=naoAcao', 'idCliente', 'Cliente');return isCampoNumerico(event);"
						onblur="somente_numero_zero_a_nove(this);" 
						/>

					<a
						href="javascript:validarAbrirPoupPesquisarCliente();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> 
						<logic:present
						  name="codigoClienteNaoEncontrado" scope="request">
						<input type="text" name="nomeCliente" size="40" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE INEXISTENTE" />
					     </logic:present> 
					   <logic:notPresent
						name="codigoClienteNaoEncontrado" scope="request">
						<html:text property="nomeCliente" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> 
					<a href="javascript:validarLimparCliente();desabilitarLocalidadeCobrancaAcao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>Tipo de Rela&ccedil;&atilde;o:</strong></td>
					<td colspan="3"><strong> <html:select tabindex="12"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						property="clienteRelacaoTipo" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options name="request"
							collection="colecaoClienteRelacaoTipo" labelProperty="descricao"
							property="id" />
					</html:select> </strong></td>
				</tr>
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Refer&ecirc;ncia das Contas</strong><strong>:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="7"
						property="periodoInicialConta" size="7" tabindex="13"
						onkeyup="somente_numero(this);mascaraAnoMes(this, event);replicarCampo(document.forms[0].periodoFinalConta,this);" 
						onkeypress="return isCampoNumerico(event);"
						onblur="somente_numero(this);replicarCampo(document.forms[0].periodoFinalConta,this);"/><strong> a</strong> <html:text
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" tabindex="14"
						maxlength="7" property="periodoFinalConta" size="7"
						onkeyup="somente_numero(this);mascaraAnoMes(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="somente_numero(this);"/> </strong>mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Vencimento das Contas:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="10" tabindex="15"
						property="periodoVencimentoContaInicial" size="10"
						onkeyup="somente_numero(this);mascaraData(this, event);replicarCampo(document.forms[0].periodoVencimentoContaFinal,this);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="somente_numero(this);replicarCampo(document.forms[0].periodoVencimentoContaFinal,this);"/> <a
						href="javascript:abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoVencimentoContaInicial')"
						>
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a><strong>
					a</strong> <html:text maxlength="10" tabindex="16"
						property="periodoVencimentoContaFinal" size="10"
						onkeyup="somente_numero(this);mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="somente_numero(this);"/> <a
						href="javascript:abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoVencimentoContaFinal')"
						>
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a>
					</strong>dd/mm/aaaa</td>
				</tr>
				<tr>
					<td width="16%"><strong>Quantidade de Dias de Vencimento:</strong></td>
					<td colspan="3">
						<html:text property="quantidadeDiasVencimento" 
											   tabindex="4" 
											   size="3" 
											   maxlength="3" 
											   onkeypress="return isCampoNumerico(event);"
											   onkeyup="somente_numero_zero_a_nove(this);"
											   onblur="somente_numero_zero_a_nove(this);"/>
						</td>
				</tr>	
				
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
					<tr>
						<td><strong>Consumo M�dio do Im�vel:</strong></td>
						<td colspan="3"><strong> <html:text maxlength="7"
							property="consumoMedioInicial" size="7" tabindex="13"
							onkeyup="replicarCampo(document.forms[0].consumoMedioFinal,this);validarExibicaoTipoCosumo();"
							onkeypress="return isCampoNumerico(event);"
							onblur="somente_numero_zero_a_nove(this);validaNumero(this);replicarCampo(document.forms[0].consumoMedioFinal,this);"/><strong> a</strong> <html:text
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" tabindex="14"
							maxlength="7" property="consumoMedioFinal" size="7"					
							onkeypress="validarExibicaoTipoCosumo();return isCampoNumerico(event);" 
							onkeyup="somente_numero_zero_a_nove(this);"
							onblur="somente_numero_zero_a_nove(this);"/> </strong>
						</td>
					</tr>
					<tr>
						<td><strong>Tipo de Consumo:</strong></td>
						<td colspan="3"><strong> 
							<html:select tabindex="12"
								name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="tipoConsumo" disabled="true">
								<html:option value="1">MEDIDO</html:option>
								<html:option value="2">N�O MEDIDO</html:option>
								<html:option value="3">AMBOS</html:option>
							</html:select> </strong>
						</td>
					</tr>
				<logic:present scope="session" name="colecaoFiscalizacaoSituacao">					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Per�odo de Fiscaliza��o do Im�vel:</strong></td>
						<td colspan="3"><strong> <html:text maxlength="10" tabindex="15"
							property="periodoInicialFiscalizacao" size="10"
							onkeyup="somente_numero(this);replicarCampo(document.forms[0].periodoFinalFiscalizacao,this);mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" 
							onblur="somente_numero(this);replicarCampo(document.forms[0].periodoFinalFiscalizacao,this);"/> <a
							href="javascript:abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoInicialFiscalizacao')"
							>
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a><strong>
						a</strong> <html:text maxlength="10" tabindex="16"
							property="periodoFinalFiscalizacao" size="10"
							onkeyup="somente_numero(this);mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" 
							onblur="somente_numero(this);"/> <a
							href="javascript:abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoFinalFiscalizacao')"
							>
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a>
						</strong>dd/mm/aaaa</td>
					</tr>		
					<tr>
						<td width="16%"><strong>Situa��o de Fiscaliza��o:</strong></td>
						<td colspan="3"><html:select property="situacaoFiscalizacao" style="height: 100px" multiple="true" tabindex="3">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present scope="session" name="colecaoFiscalizacaoSituacao">
								<html:options name="session" collection="colecaoFiscalizacaoSituacao"
									labelProperty="descricaoFiscalizacaoSituacao" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:present>
				
				<tr>
					<td width="30%"><strong>Selecionar Im�veis Com Situa��o da Liga��o N�o Alterada por D�bito:<font color="#FF0000">*</font></strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="imoveisSitLigacaoNaoAlteradaDeb" value="1" tabindex="4" /> Inclusive 
					<html:radio property="imoveisSitLigacaoNaoAlteradaDeb" value="2" tabindex="4" /> Apenas
					<html:radio property="imoveisSitLigacaoNaoAlteradaDeb" value="3" tabindex="4" /> N�o Incluir
					</strong></span></strong></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Rela��o de Im�veis:</strong></td>
					<td width="70%">
						<html:file property="relacaoImoveis" size="9"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
			    <tr> 
                	<td><strong>Logradouro:</strong></td>
                		<td colspan="6"><span class="style2"><strong> 
							<html:text property="logradouroId" size="9" maxlength="9"
							   onchange="limparDescricao(document.forms[0].logradouroDescricao);"
							   onkeypress="javascript:limparDescricao(document.forms[0].logradouroDescricao);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=4&validarCriterio=naoAcao&inscricaoTipo=origem', 'logradouroId','Logradouro');return isCampoNumerico(event);"
							   onkeyup="javascript:somente_numero_zero_a_nove(this);validarLogradouroSelecionado();"
							   onblur="somente_numero_zero_a_nove(this);"	
							   />
						
						<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do?indicadorUsoTodos=1&primeriaVez=1', 'origem', null, null, 275, 480,'',document.forms[0].logradouroId);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0"></a>

						<logic:present name="logradouroEncontrada" scope="session">
							<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
						</logic:present> 
	
						<logic:notPresent name="logradouroEncontrada" scope="session">
							<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:notPresent>

						<a href="javascript:limparLogradouro();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  	</strong></span></td>
                </tr>
                <tr>
				 	<td colspan="4">
				 	<hr>
				 	</td>
			    </tr>
			    <tr>
					<td><strong>Indicador do Crit&eacute;rio:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><strong> <html:radio property="indicador" tabindex="17"
						value="Rota" onclick="habilitar('1');" /> Usa Crit&eacute;rio da
					Rota </strong> 
						<html:radio property="indicador" value="Comando" tabindex="18"
							onclick="habilitar('2');" />
						<strong>Usa <strong><strong>Crit&eacute;rio</strong> do Comando</strong>
						</strong>
						</td>
				 </tr>
				<tr>
					<td height="17"><strong></strong></td>
					<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td height="17" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
					<table border="0"  width="100%">
						<tr>
							 <td align="left"><!--<input name="executarComando"
								type="button" class="bottonRightCol" onClick="executarcomando();"
								value="Executar Comando" />--></td>
							<td align="right" width="60%"><img src="imagens/voltar.gif" width="15"
								height="24" border="0" /></td>
							<td align="right" width="5%"><input name="Button32222" type="button"
								class="bottonRightCol" value="Voltar" tabindex="19"
								onClick="javascript:voltar()" /></td>
							<td align="right" width="5%">
							  <input name="Avancar" type="button" class="bottonRightCol" disabled value="Avan�ar" tabindex="20"
								onClick="avancar();" /></td>
							<td align="left" width="30%"><img src="imagens/avancar.gif" width="15"
								height="24" border="0" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%" height="1px" bgcolor="#000000"></td>
				</tr>				
				<tr>
					<td colspan="1"><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirComandoAcaoCobrancaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right">
					  <gsan:controleAcessoBotao name="concluir" 
					  	value="Concluir" 
					  	onclick="javascript:concluircomando();" 
					  	url="inserirComandoAcaoCobrancaEventualConcluirAction.do"/>				
					  
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
</div>
</html:html>
<script language="JavaScript">
<!-- Begin
	habilitar(document.forms[0].cobrancaAtividadeIndicadorExecucao.value);
///	validarGrupoCobranca(0);
	validarCliente(0);
	validarClienteSuperior(1);
	desabilitarLocalidadeCobrancaAcao();
	validarGrupoCobrancaParaBloquear();
	validarGerenciaRegionalParaBloquear();
	validarUnidadeNegocioParaBloquear();
	verificarCampoPrazoExecucao();
	
//// habilitacaoCampoRota();
	validarClienteParaBloquear();
	validarHabilitarCampo();
	validarLogradouroParaBloquear();
	
-->
</script>
