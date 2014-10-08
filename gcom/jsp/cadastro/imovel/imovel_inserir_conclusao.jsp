]<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.cadastro.imovel.ImovelFotoHelper"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript formName="InserirImovelActionForm" dynamicJavascript="false" staticJavascript="false" page="3" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>	
<script>


$(document).ready(function(){
	
		$('[name=sistemaAbastecimento]').change(function() {
			
			apagarSelect('subsistemaAbastecimento');
			apagarSelect('setorAbastecimento');
			apagarSelect('distritoOperacional');
			apagarSelect('areaOperacional');
			apagarSelect('zonaPressao');
			
			chamarAjax('InserirImovelActionForm',
					  	 'inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction&metodo=1',
					     sucessoAtSubsistema,null);		
		});
	
	
		$('[name=subsistemaAbastecimento]').change(function() {

			apagarSelect('setorAbastecimento');
			apagarSelect('distritoOperacional');
			apagarSelect('areaOperacional');
			apagarSelect('zonaPressao');
			
			chamarAjax('InserirImovelActionForm',
				  	 	'inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction&metodo=2',
			    	 	sucessoAtSetor,null);
		});

		$('[name=setorAbastecimento]').change(function() {

			apagarSelect('distritoOperacional');
			apagarSelect('areaOperacional');
			apagarSelect('zonaPressao');
			
			chamarAjax('InserirImovelActionForm',
				  	 	'inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction&metodo=3',
			    	 	sucessoAtDistrito,null);
		});

		$('[name=distritoOperacional]').change(function() {

			apagarSelect('areaOperacional');
			apagarSelect('zonaPressao');
			
			chamarAjax('InserirImovelActionForm',
				  	 	'inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction&metodo=4',
			    	 	sucessoAtArea,null);
		});

		$('[name=areaOperacional]').change(function() {
			
			apagarSelect('zonaPressao');
			
			chamarAjax('InserirImovelActionForm',
				  	 	'inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction&metodo=5',
				  	 	sucessoAtZona,null);
		});
		
	});

	function sucessoAtSubsistema(data){
		var obj = JSON && JSON.parse(data) || $.parseJSON(data);
		preencherSelect(obj,'subsistemaAbastecimento');
	}
	
	function sucessoAtSetor(data){
		var obj = JSON && JSON.parse(data) || $.parseJSON(data);
		preencherSelect(obj,'setorAbastecimento');
	}

	function sucessoAtDistrito(data){
		var obj = JSON && JSON.parse(data) || $.parseJSON(data);
		preencherSelect(obj,'distritoOperacional');
	}

	function sucessoAtArea(data){
		var obj = JSON && JSON.parse(data) || $.parseJSON(data);
		preencherSelect(obj,'areaOperacional');
	}

	function sucessoAtZona(data){
		var obj = JSON && JSON.parse(data) || $.parseJSON(data);
		preencherSelect(obj,'zonaPressao');
	}
	
	
	function validarUTM(){
    	var form = document.forms[0];
		var retorno = true;
	    
	    if( trim(form.cordenadasUtmX.value).length > 0 && trim(form.cordenadasUtmY.value).length == 0){
			retorno = false;
   	      	alert("Informar ambas ou nenhuma das coordenadas UTM");
	 	}else if (trim(form.cordenadasUtmY.value).length > 0  && trim(form.cordenadasUtmX.value).length == 0){
			retorno = false;	      
	      	alert("Informar ambas ou nenhuma das coordenadas UTM");
	    }
	    
	    return retorno;
	}
    
    var bCancel = false;

    function validateInserirImovelActionForm(form) {
    	if (bCancel)
      		return true;
        else
       		return testarCampoValorZero(document.InserirImovelActionForm.numeroPontos, 'Número de Pontos') && 
       			testarCampoValorZero(document.InserirImovelActionForm.numeroMoradores, 'Número de Moradores') && 
       			testarCampoValorZero(document.InserirImovelActionForm.numeroContratoCelpe, 'Contrato Companhia de Energia')	&& 
       			testarCampoValorZero(document.InserirImovelActionForm.idImovel, 'Imóvel Principal') && 
       			validateCaracterEspecial(form) && 
       			validateRequired(form) && 
       			validateLong(form) && 
       			validateDecimal(form) && 
       			validateDate(form) && 
       			validarUTM() && 
       			validateBigInteger(form) &&
        		validateDecimalNegativoZeroPositivo(form); 
   	}

    function caracteresespeciais () {
     	this.ad = new Array("cordenadasUtmX", "Cordenadas Utm X deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ae = new Array("cordenadasUtmY", "Cordenadas Utm Y deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.af = new Array("numeroPontos", "Número de Pontos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("numeroMoradores", "Número de Moradores deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("numeroIptu", "Número de IPTU deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ai = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.at = new Array("idImovel", "Imóvel Principal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.av = new Array("sequencialRotaEntrega", "Sequência da Rota de Entrega deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.aq = new Array("idFuncionario", "Funcionário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.aq = new Array("informacoesComplementares", "Informações Complementares deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ar = new Array("numeroMedidorEnergia", "Medidor de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.as = new Array("codigoRotaAlternativa", "Rota Alternativa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {
    }
    
    function BigIntegerValidations () {
		this.az = new Array("numeroIptu", "Número de IPTU deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
    

    function IntegerValidations () {
    	this.aj = new Array("codigoRotaAlternativa", "Rota Alternativa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    	this.al = new Array("numeroPontos", "Número de Pontos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.am = new Array("numeroMoradores", "Número de Moradores deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ao = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.au = new Array("idImovel", "Imóvel Principal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ap = new Array("numeroMedidorEnergia", "Medidor de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.az = new Array("idFuncionario", "Funcionário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function FloatValidations () {
//     	this.an = new Array("cordenadasUtmX", "Cordenadas Utm X deve somente conter números decimais positivos ou zero.", new Function ("varName", " return this[varName];"));
//     	this.ap = new Array("cordenadasUtmY", "Cordenadas Utm Y deve somente conter números decimais positivos ou zero.", new Function ("varName", " return this[varName];"));
    }

    function DecimalNegativoZeroPositivoValidations () {
    	this.an = new Array("cordenadasUtmX", "Cordenadas Utm X deve ser um número decimal.", new Function ("varName", " return this[varName];"));
     	this.ap = new Array("cordenadasUtmY", "Cordenadas Utm Y deve ser um número decimal.", new Function ("varName", " return this[varName];"));
    }
    
    function InteiroZeroPositivoValidations () {
    	this.au = new Array("codigoRotaAlternativa", "Código da Rota Alternativa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    	this.ax = new Array("sequencialRotaEntrega", "Sequêncial da Rota de Entrega deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function DateValidations () { 
    	this.aa = new Array("dataVisitaComercial", "Data da Visita Comercial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

//End -->
</script>

<script>
	var tipoPesquisaRota = "ENTREGA";
  //Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

    	if (tipoConsulta == 'imovel') {
      		
      		limparPesquisaImovel();
      		
      		form.idImovel.value = codigoRegistro;
      		form.action='inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&pesquisar=SIM';
      		form.submit();
      		
    	}else if ('funcionario' == tipoConsulta) {
			
		 	form.idFuncionario.value = codigoRegistro;
		 	form.action = 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';
		 	form.submit();
    	}
  	}

	function limparPesquisaImovel() {
    	var form = document.forms[0];

      	form.idImovel.value = "";
  	}

  	function limpaImovelPrincipal(){
    	var form = document.InserirImovelActionForm;
    	var id = form.idImovel.value;
		
		if(confirm('Confirma remoção ?')){
			form.idImovel.value= "";
          	form.action = "removerInserirImovelPrincipalAction.do";
	      	form.submit()	
    	}
  	}
	
	function limparCamposFuncionario(){
		var form = document.forms[0];
 		form.nomeFuncionario.value = '';
	}

	function limparFuncionario() {
		document.forms[0].idFuncionario.value = '';
		document.forms[0].nomeFuncionario.value = '';
	}
	
	function limparRota(ehAlternativa) {
    	var form = document.forms[0];
    	if(ehAlternativa){
      		form.idRotaAlternativa.value = "";
      		form.codigoRotaAlternativa.value = "";
    	}else{
      		form.idRota.value = "";
      		form.codigoRota.value = "";
      	}
  	}
	
	function receberRota(codigoRota,destino) {
 		var form = document.forms[0];
 	  
 	  	form.action = 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';

 	  	if(tipoPesquisaRota == 'ALTERNATIVA'){
 	   	  	form.idRotaAlternativa.value = codigoRota;
 	  	}else{
 	   	  	form.idRota.value = codigoRota;
 	  	}

 	  	form.submit();
	}
	
	//Integração com o GIS
	function respostaGis(){
	     redirecionarSubmit('inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction');		
	}
	
	function pesquisarRota(){
		tipoPesquisaRota='ALTERNATIVA';
		
		abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&destinoRota=Final', 400, 800);
	}
	
	function limiteCaracteres(obj,limit){
	 var limite = limit;
	 var form = obj;
	 var tamanho = form.value.length;
	 var restantes = document.forms[0].restantes;
	 
	 if(form.value.length > limite){
 	   form.value = form.value.substring(0,tamanho-(tamanho-limite));
 	   restantes.value = limite;
	   alert("Você só pode digitar no máximo "+limite+" caracteres");	 
	 }else {	  
	    restantes.value = limite - tamanho;
	 }	 
	}
	
	function validarEnvioEmail() {
		
		var form = document.forms[0];
		
		form.action='inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';
		form.submit();
		
	}
	
	function removerArquivo(identificacao){
		var form = document.forms[0];
		if(confirm('Confirma remoção?')){
			form.target = "";
			form.action = "inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&remover=" + identificacao;
			
			form.submit();
		}	
	}
	
	function visualizarArquivo(identificacao){
		var form = document.forms[0];
		form.target = "_new";
		form.action = "inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&visualizar=" + identificacao;
		
		form.submit();	
	}
	
	function validarAdicionarFoto(){
		var form = document.forms[0];
		if(adicionarFoto()){
			form.target= "";
			form.action='inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&adicionarFoto=OK';
			form.submit();
		}
	}
	
	function adicionarFoto(){
	   var form = document.forms[0];

	   if(form.idTipoFoto.value == "" || form.idTipoFoto.value == "-1") {
	   		alert("Informe o tipo de Foto");
	   		return false;
	   }		
	   		
	   if(form.documento.value != null && form.documento.value != ""){
			if(comprova_extensao() == true){
				return true;
			}else{
				return false;
			}
	   }else{
		   	alert("Informe o Arquivo");
			return false;
		}

	   return false;
	}
	
	function comprova_extensao() {
		var form = document.forms[0];
		var arquivo = form.documento.value;
		var meuerro = ""; 

	   var   extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase(); 

	   var permitida = false; 

        if (extensao == ".jpg" || extensao == ".JPG" || extensao == ".jpeg" || extensao == ".JPEG" ){ 
	         permitida = true; 
        }else{
        	 alert ("Arquivo inválido. Só é permitido enviar arquivos com extensões .jpg ou .jpeg ");
        } 

	   return permitida; 
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5"  onload="limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));
											limitTextArea(document.forms[0].observacoes, 200, document.getElementById('utilizado2'), document.getElementById('limite2'));">
<div id="formDiv">
<html:form action="/inserirImovelWizardAction" method="post" 
	onsubmit="return validateInserirImovelActionForm(this);" enctype="multipart/form-data">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=6" />



	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="7" />
		<tr>
			<td valign="top" class="leftcoltext">
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
			<td width="603" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
							<tr>
					<td>Para concluir o cadastro, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInserirAbaConclusao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>   
				</tr>
			</table>
			<br>
			<table width="100%" border="0">
				<!-- <tr>
	      <tdcolspan="2">&nbsp;</td>
	    </tr> -->
				<tr>
					<td width="35%" height="24"><strong>N&uacute;mero de Pontos:<font
						color="#FF0000"></font></strong></td>
					<td width="65%"><html:text maxlength="4" size="4"
						property="numeroPontos"
						onkeypress="return isCampoNumerico(event)" /></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de Moradores:<font
						color="#FF0000"></font></strong></td>
					<td><html:text maxlength="4" size="4" property="numeroMoradores" 
					onkeypress="return isCampoNumerico(event)"/></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de IPTU:</strong></td>
					<td><html:text maxlength="20" size="20" property="numeroIptu" 
					onkeypress="return isCampoNumerico(event)"/></td>
				</tr>
				<tr>
					<td height="24"><strong>Contrato Cia Energia:</strong></td>
					<td><html:text maxlength="10" size="10"
						property="numeroContratoCelpe" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Medidor de Energia:</strong></td>
					<td><html:text maxlength="10" size="10"
						property="numeroMedidorEnergia" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Data Visita Comercial:</strong></td>
					<td><html:text size="10" maxlength="10" tabindex="10"
						onkeyup="mascaraData(this, event);"
						property="dataVisitaComercial" 
						onkeypress="return isCampoNumerico(event)"/>
						<a href="javascript:abrirCalendario('InserirImovelActionForm', 'dataVisitaComercial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="4"/></a>
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<!-- ********* IMOVEL CONTA ENVIO **************** -->
				<logic:equal name="colecaoImovelEnvioContaVazia" value="false">
	
					<logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
					<tr>
						<td height="24"><strong>Envio da Conta:<font color="#FF0000">*</font></strong></td>
						<td><html:select property="imovelContaEnvio"
							onchange="validarEnvioEmail();">
							<html:option value="-1">&nbsp;</html:option>
			                   <html:options collection="colecaoImovelEnnvioConta" 
			                   labelProperty="descricao" property="id"/>
						</html:select></td>
					</tr>
					</logic:equal>
					<logic:equal name="contaEnvioObrigatorio" value="opcional">
						<tr>
							<td height="24"><strong>Envio da Conta:</strong></td>
							<td><html:select property="imovelContaEnvio"
								onchange="validarEnvioEmail();">
								<html:option value="-1">&nbsp;</html:option>
				                   <html:options collection="colecaoImovelEnnvioConta" 
				                   labelProperty="descricao" property="id"/>
							</html:select></td>
						</tr>
					</logic:equal>
					
				</logic:equal>
				
				<logic:equal name="colecaoImovelEnvioContaVazia" value="true">
					
					<logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
						<tr>
							<td height="24"><strong>Envio da Conta:<font color="#FF0000">*</font></strong></td>
							<td><html:select property="imovelContaEnvio"
									onchange="validarEnvioEmail();"
									disabled="true">
							</html:select></td>
						</tr>
					</logic:equal>
					<logic:equal name="contaEnvioObrigatorio" value="opcional">
						<tr>
							<td height="24"><strong>Envio da Conta:</strong></td>
							<td><html:select property="imovelContaEnvio"
									onchange="validarEnvioEmail();"
									disabled="true">
								</html:select></td>
						</tr>
					</logic:equal>
					
				</logic:equal>
				
				<!--  ********* FIM IMOVEL CONTA ENVIO ********* -->
					
				<logic:equal name="envioContaListar" value="listar">
					<tr>
						<td height="24"><strong>Extrato para Respons&aacute;vel:</strong></td>
						<td>
						<p><label> <html:radio value="1" property="extratoResponsavel" />
						Emitir</label> <label> <html:radio value="2"
							property="extratoResponsavel" /> Não Emitir</label> <br>
						<label> </label></p>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				
				<logic:equal name="envioContaListar" value="listar">
				
					<tr>
						<td height="24"><strong>Quadra de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td><html:text maxlength="4" size="4" property="numeroQuadraEntrega" 
						onkeypress="return isCampoNumerico(event)"/></td>
					</tr>
					
					<tr>
						<td height="24"><strong>Código da Rota de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td>
	
						<html:hidden property="idRota"/>
						<html:text maxlength="4" 
							size="4" 
							property="codigoRota" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000" />
							
						<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 400, 800);">
						
							<img border="0" title="Pesquisar"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
						</a> 
						
						<a href="javascript:limparRota(false)"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
						</td>
					</tr>
					
					<tr>
						<td height="24"><strong>Sequencial da Rota de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td><html:text maxlength="4" size="4" property="sequencialRotaEntrega" 
						onkeypress="return isCampoNumerico(event)"/></td>
					</tr>
					<tr>
						<td height="24" colspan="3">
						<hr>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual name="envioContaListar" value="listar">
					<html:hidden property="sequencialRotaEntrega" />
				</logic:notEqual>
				
			 <tr>
					<td height="24"><strong>Código Rota Alternativa:<font
						color="#FF0000"></font></strong></td>
					<td>
						<html:hidden property="idRotaAlternativa"/>
						
						<html:text maxlength="4" 
							size="4" 
							property="codigoRotaAlternativa" 
							style="background-color:#EFEFEF; border:0; color: #000000" />
							
						<a href="javascript:pesquisarRota();">
							<img border="0" title="Pesquisar Rota"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
								border="0"/></a> 
							
						<a href="javascript:limparRota(true)"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
<!-- ----------------------------------Coordenadas Gis-------------------------------------------- -->
				<logic:notPresent name="permissaoEspecialDigitarCoordenadasImovel">
					<tr>
						<td height="24"><strong>Coordenada X (Leste):</strong></td>
						<td><html:text maxlength="25" size="27" property="cordenadasUtmX"
							style="text-align: right; background-color:#EFEFEF; border:0; color: #000000" readonly="true"
							onkeypress="return isCampoNumericoNegativoComVirgula(event);" /></td>
					</tr>
					<tr>
						<td height="24"><strong>Coordenada Y (Norte):</strong></td>
						<td><html:text maxlength="25" size="27" property="cordenadasUtmY"
							style="text-align: right; background-color:#EFEFEF; border:0; color: #000000" readonly="true"
							onkeypress="return isCampoNumericoNegativoComVirgula(event);" /></td>
						<td>
							<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
						</td>
					</tr>
				</logic:notPresent>
				
				<logic:present name="permissaoEspecialDigitarCoordenadasImovel">
					<tr>
						<td height="24"><strong>Coordenada X (Leste):</strong></td>
						<td><html:text maxlength="25" size="27" property="cordenadasUtmX"
							onkeypress="return isCampoNumericoNegativoComVirgula(event);" /></td>
					</tr>
					<tr>
						<td height="24"><strong>Coordenada Y (Norte):</strong></td>
						<td><html:text maxlength="25" size="27" property="cordenadasUtmY"
							onkeypress="return isCampoNumericoNegativoComVirgula(event);" /></td>
					</tr>
				</logic:present>
				
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
<!-- --------------------------------------------------------------------------------------------- -->
				<tr>
					<td width="100%" colspan="3">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="25%"><strong>Imóvel Principal:</strong></td>
								<td width="75%"><html:text property="idImovel" maxlength="9"
									size="6"
									onkeypress="javascript:return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&pesquisar=SIM', 'idImovel');"
									onkeyup="somente_numero(this);" />
								<a
									href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
								<img width="23" height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Imóvel" /></a>
					   		        <logic:present name="idImovelPrincipalNaoEncontrado" scope="request">
			                        	<input type="text" name="matriculaImovelPrincipal" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"
			                        		value="${valorMatriculaImovelPrincipal}">
			                        </logic:present>
			                        <logic:notPresent name="idImovelPrincipalNaoEncontrado" scope="request">
			                        	<logic:present name="valorMatriculaImovelPrincipal" scope="request">
			                        	<input type="text" name="matriculaImovelPrincipal" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
			    	                    	value="<bean:write name="valorMatriculaImovelPrincipal" scope="request" />">
			                        	</logic:present>
			                        	<logic:notPresent name="valorMatriculaImovelPrincipal" scope="request">
			                        	<input type="text" name="matriculaImovelPrincipal" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
				                        	value="">
			                        	</logic:notPresent>
			                        </logic:notPresent>
									<a href="javascript:limpaImovelPrincipal()"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>						
			                        
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<!-- ************************************************************************ -->
				<tr>
					<td width="100%" colspan="3">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="0"
								bgcolor="#90c7fc" bordercolor="#90c7fc">
								<!--header da tabela interna -->
								<tr>
									<td align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
							</table>
							</td>
						</tr>

						<logic:present name="imoveisPrincipal">

							<tr>
								<td height="40">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->

									<%String cor = "#FFFFFF";%>

									<logic:iterate name="imoveisPrincipal" id="endereco">

										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td align="center"><bean:write name="endereco"
												property="enderecoFormatado" /></td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>

						</logic:present>

					</table>
					</td>
				</tr>
				
				<tr>
					
				<tr>
					<td width="100%" colspan="3">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="25%"><strong>Funcionário:</strong></td>
								<td width="75%">
									
									<html:text maxlength="9" 
										property="idFuncionario" 
										size="6"
										onkeypress="javascript:return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction', 'idFuncionario');"
										onkeyup="limparCamposFuncionario(); somente_numero(this);" /> 
									
									<a href="javascript:abrirPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300);">
										<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
											width="23" 
											height="21" 
											title="Pesquisar Funcionário" 
											border="0"></a> 
			
					   		        <logic:present name="idImovelPrincipalEncontrado" scope="request">
											
										<html:text property="nomeFuncionario" 
											size="20"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
											
									</logic:present> 
									
									<logic:notPresent name="idImovelPrincipalEncontrado" scope="request">						
			
										<html:text property="nomeFuncionario" 
											size="20"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />								
											
									</logic:notPresent> 
									
									<a href="javascript:limparFuncionario();"> 
										<img border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Limpar" >
									</a>
								</td>
							</tr>
							<tr>
								<td align="left"><strong>Informações Compl.:</strong></td>
								<td>
									<html:textarea property="informacoesComplementares" cols="50" rows="6" onkeyup="limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
									<strong><span id="utilizado">0</span>/<span id="limite">750</span></strong>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<!-- ============================================ SISTEMAS DE ABASTECIMENTO ============================================ -->
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
				   <td><strong>Sistema de Abastecimento:</strong></td>
				   <td>
						<html:select property="sistemaAbastecimento" style="width: 200px;" tabindex="13">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoSistemaAbastecimento" labelProperty="descricao" property="id"/>
						</html:select>
				   </td>
			   	</tr>
				<tr>
					<td width="40%" class="style3"><strong>Subsistema de Abastecimento:</strong></td>
					<td width="60%" colspan="2"><html:select
						property="subsistemaAbastecimento" tabindex="3" style="width:200px;height:20px;">
						<html:option value="-1"> &nbsp; </html:option>
						<logic:present name="colecaoSubsistemaPrincipal" scope="session">
							<html:options collection="colecaoSubsistemaPrincipal" property="id"
								labelProperty="descricao" />
						</logic:present>	
					</html:select></td>
				</tr>
				<tr>
					 <td  width="40%"class="style3"><strong>Setor Abastecimento:</strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="setorAbastecimento" tabindex="5" style="width:200px;height:20px;">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoSetorAbastecimento" scope="session">
								<html:options collection="colecaoSetorAbastecimento" property="id" labelProperty="descricao"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					 <td  width="40%"class="style3"><strong>Distrito Operacional:</strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="distritoOperacional" tabindex="5" style="width:200px;height:20px;">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoDistritoOperacional" scope="session">
								<html:options collection="colecaoDistritoOperacional" property="id" labelProperty="descricao"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					 <td  width="40%"class="style3"><strong>Área Operacional:</strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="areaOperacional" tabindex="5" style="width:200px;height:20px;">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoAreaOperacional" scope="session">
								<html:options collection="colecaoAreaOperacional" property="id" labelProperty="descricao"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					 <td  width="40%"class="style3"><strong>Zona de Pressão:</strong></td>
					 <td  width="60%" colspan="2">
			  			<html:select property="zonaPressao" tabindex="5" style="width:200px;height:20px;">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoZonaPressao" scope="session">
								<html:options collection="colecaoZonaPressao" property="id" labelProperty="descricaoZonaPressao"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<!-- =================================================================================================================== -->
				
				<tr>
					<td colspan="3">Para anexar um ou vários arquivos, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td align="left" ><strong>Tipo de Foto:</strong></td>
					<td>
						<html:select property="idTipoFoto" >
		                  <html:option value="-1">&nbsp;</html:option>
		                  <html:options collection="colTipoFoto" labelProperty="descricao" property="id"/>
		                </html:select>
					</td>
				</tr>
				<tr>
					<td align="left"><strong>Arquivo:<font color="#FF0000">**</font></strong></td>
					<td><input type="file" name="documento" id="file" size="17" /></td>
				</tr>
				<tr>
					<td align="left"><strong>Observações.:</strong></td>
					<td>
						<html:textarea property="observacoes" cols="40" rows="3" onkeyup="limitTextArea(document.forms[0].observacoes, 200, document.getElementById('utilizado2'), document.getElementById('limite2'));"/><br>
						<strong><span id="utilizado2">0</span>/<span id="limite2">200</span></strong>	
					</td>
				</tr>
				<tr>
	            	<td height="24">&nbsp;</td>
	                <td> <strong><font color="#FF0000">**</font></strong>Serão aceitos os arquivos nos formatos: JPG ou JPEG.</td>
               </tr>
               <tr>
               		<td colspan="2"><strong>Arquivo(s) informado(s):</strong></td>
               		<td align="right">
                  		<html:button styleClass="bottonRightCol" value="Adicionar" property="botaoAdicionar" onclick="javascript:validarAdicionarFoto();"/>
	                </td>
              </tr>
              <tr>
              	<td colspan="3">
              		<table width="100%" cellpadding="0" cellspacing="0">
              			<tr>
              				<td>
              					<table width="100%" bgcolor="#90c7fc">
		                          <!--header da tabela interna -->
		                          <tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
		                            <td width="10%"><div align="center"><strong>Remover</strong></div></td>
		                            <td width="10%"><div align="center"><strong>Arquivo</strong></div></td>
		                            <td width="30%"><div align="left"><strong>Tipo de Foto</strong></div></td>
		                            <td width="50%"><div align="left"><strong>Observação</strong></div></td>
		                          </tr>
		                        </table>
              				</td>
              			</tr>
              			<tr>
              				<td height="83px">
              					<logic:present name="colecaoFoto" scope="session">
	              					<div style="width: 100%; height: 100%; max-width: 1000px; overflow: auto;">
	              						<table width="100%" align="center" bgcolor="#99CCFF">
	              							<%int cont=0;%>
                          						<logic:iterate name="colecaoFoto" id="foto" type="ImovelFotoHelper">
	              								<%
				                                   cont = cont+1;
				                                   if (cont%2==0){%>
				                                     <tr bgcolor="#cbe5fe">
				                                <% }else{ %>
				                                     <tr bgcolor="#FFFFFF">
				                                <% }%>
						                                <td width="10%" align="center" valign="middle">
					                                    	<a href="javascript:removerArquivo('<%="" + cont %>')" title="Remover">
				                                        		<img title="Remover" border="0" src="/gsan/imagens/Error.gif"/>
				                                        	</a>
					                                    </td>
						                                
						                                <logic:notEmpty name="foto" property="hint">
							                                <td width="10%" align="center" valign="middle" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape('<bean:write name="foto" property="hint"/>');" >
											  					<a href="javascript:visualizarArquivo('<%="" + cont %>')" >
						                                 			<img border="0" src="/gsan/imagens/JPG.gif"/>
						                                 		</a>
							                                </td>
														</logic:notEmpty>
														<logic:empty name="foto" property="hint">
															<td width="10%" align="center" valign="center" >
											  					<a href="javascript:visualizarArquivo('<%="" + cont %>')" >
						                                 			<img border="0" src="/gsan/imagens/JPG.gif"/>
						                                 		</a>
							                                </td>
														</logic:empty>
														
														<td width="30%" >
										  					<bean:write name="foto" property="descricao"/>
					                                    </td>
														<td width="50%" >
									  						<% if (cont % 2 == 0) {%>
				                                            	<TEXTAREA ROWS="3" COLS="40" style="cursor:hand; border:0px solid; background-color: #cbe5fe" readonly><bean:write name="foto" property="observacao"/></TEXTAREA>
				                                            <%} else {%>
				                                            	<TEXTAREA ROWS="3" COLS="40" style="cursor:hand; border:0px solid;" readonly><bean:write name="foto" property="observacao"/></TEXTAREA>
				                                        	<% } %>
					                                    </td>
												</tr>
											</logic:iterate>
	              						</table>
	              					</div>
              					</logic:present>
              				</td>
              			</tr>
              		</table>
              	</td>
             </tr>
              
             <logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
			       <tr>
		                <td height="24">&nbsp;</td>
		                <td> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
	               </tr>				
			 </logic:equal>
				<tr>
					<td colspan="3">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=6" />
					</div>
					</td>
				</tr>
								

				<!-- ************************************************************************ -->
			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>



</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirImovelWizardAction.do?concluir=true&action=inserirImovelConclusaoAction'); }
</script>


</html:html>
