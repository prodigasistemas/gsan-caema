<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.micromedicao.hidrometro.Hidrometro"
	isELIgnored="false"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarHidrometroActionForm" dynamicJavascript="false" />
<script language="JavaScript">


   var bCancel = false;

   function naoTemZeroComMsg(campo, nomeCampo){
		if(campo.value == '0' || campo.value == '00'){
			alert(nomeCampo+" deve somente conter n�meros decimais positivos");
			return false;	
		}
		else{
			return true;
		}
	}

   
   

    function validateAtualizarHidrometroActionForm(form) {
        if (bCancel)
      		return true;
        else {
            var mensagem = validarCamposMacroMicro(form);
            
            if (mensagem != null && mensagem != '') {
                alert(mensagem);
                return false;
            }

           
    			
           
           
           return validateRequired(form) 
            && validateDate(form)
            && validarMacroFatorCorrecao(form) 
            && validateLong(form) 
            && validateCaracterEspecial(form) 
            && naoTemZeroComMsg(form.vazaoTransicao, 'Vaz�o Transi��o')     			
   			&& (form.vazaoTransicao == null 
   					|| form.vazaoTransicao.value == ''           				
   	       			|| testarCampoValorZeroDecimal(form.vazaoTransicao, 'Vaz�o Transi��o'))
   	       	&& naoTemZeroComMsg(form.vazaoNominal, 'Vaz�o Nominal')
       		&& (form.vazaoNominal == null 
               		|| form.vazaoNominal.value == ''
   				 	|| testarCampoValorZeroDecimal(form.vazaoNominal, 'Vaz�o Nominal'))
   			&& naoTemZeroComMsg(form.vazaoMinima, 'Vaz�o Minima')		
       		&& (form.vazaoMinima == null 
               		|| form.vazaoMinima.value == ''
                   	|| testarCampoValorZeroDecimal(form.vazaoMinima, 'Vaz�o Minima'))
      }           
   }

    function required () {
     this.ab = new Array("dataAquisicao", "Informe Data de Aquisi��o.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ac = new Array("anoFabricacao", "Informe Ano de Fabrica��o.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("idHidrometroMarca", "Informe Marca.", new Function ("varName", " return this[varName];"));
     this.af = new Array("idHidrometroDiametro", "Informe Di�metro.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idHidrometroCapacidade", "Informe Capacidade.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("idNumeroDigitosLeitura", "Informe N�mero de Digitos.", new Function ("varName", " return this[varName];"));
    }



    function validarMacroFatorCorrecao(form){
        
        
       if(form.indicadorMacromedidor[0].checked == true){
        
                 
   			if(naoTemZeroComMsg(form.hidrometroFatorCorrecao, 'Erro Macromedidor')){
   		
				if(testarCampoValorZeroDecimal(form.hidrometroFatorCorrecao, 'Erro Macromedidor')){
		
					return true;
				}else{
		
					return false;
				}
			}else{
				return false;
			}
		} else {
			return true;
		}
	}
        	    
    
    

	function validarCamposMacroMicro(form){
		var mensagem = "";
		if(form.indicadorMacromedidor[0].checked == true){
			if (form.tombamento.value == null 
					|| form.tombamento.value == '') {
				mensagem = mensagem + "Informe Tombamento.\n";
			}
	
			
			if (form.hidrometroClassePressao.value == null 
					|| form.hidrometroClassePressao.value == '')   {
				mensagem = mensagem + "Informe Classe Press�o.\n";
			}

			if (form.hidrometroFatorCorrecao.value == null 
					|| form.hidrometroFatorCorrecao.value == '')   {
				mensagem = mensagem + "Informe Erro do Macromedidor:.\n";
			}
			
		} else {
			if (form.numeroHidrometro.value == null 
					|| form.numeroHidrometro.value == '') {
				mensagem = mensagem + "Informe N�mero do Hidr�metro.\n";
			}
			if (form.idHidrometroTipo.value == null 
					|| form.idHidrometroTipo.value == '' 
				|| form.idHidrometroTipo.value == '-1') {
				mensagem = mensagem + "Informe Tipo de Fluxo.\n";
			}
		if (form.idHidrometroClasseMetrologica.value == null 
					|| form.idHidrometroClasseMetrologica.value == '' 
				|| form.idHidrometroClasseMetrologica.value == '-1') {
				mensagem = mensagem + "Informe Classe Metrol�gica.\n";
			}

		}

		return mensagem;
		
	}
    function DateValidations () {
     this.aa = new Array("dataAquisicao", "Data de Aquisi��o inv�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

    function caracteresespeciais () {
    	this.ab = new Array("dataAquisicao", "Data de Aquisi��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ac = new Array("anoFabricacao", "Ano de Fabrica��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ad = new Array("vazaoTransicao", "Vaz�o de Transi��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	
     	this.af = new Array("vazaoNominal", "Vaz�o Nominal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("vazaoMinima", "Vaz�o M�nima possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ai = new Array("notaFiscal", "Nota Fiscal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aj = new Array("anoFabricacao", "Ano de Fabrica��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     this.al = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     this.am = new Array("notaFiscal", "Nota Fiscal deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
   
    }

function validarForm(){
		
		var form = document.forms[0];
		
        if (validateAtualizarHidrometroActionForm(form)&& testarCampoValorZero(form.numeroHidrometro, 'N�mero Hidr�metro') && testarCampoValorZero(form.anoFabricacao, 'Data Fabrica��o')&& testarCampoValorZero(form.hidrometroClassePressao, 'Classe Press�o')){
               
	         submeterFormPadrao(form);
	  }
  	  
}
  //Validacao Adicionada por Romulo Aurelio 24/05/2007 
  //[FS0007]-Montar ano de fabricacao
function validarAnoFabricacao(){
		var form = document.forms[0];
		
		var dataAtual = new Date();
		var anoDataAtual = dataAtual.getFullYear();
		var anoFabricacao = trim(form.anoFabricacao.value);
		var numeroHidrometro = trim(form.numeroHidrometro.value);
		var anoAtualCompleto = ''+ anoDataAtual;
		var anoDataAtualFinal = parseInt(anoAtualCompleto.substring(2,4));

		var ano = parseInt(numeroHidrometro.substring(1,3));
		if(numeroHidrometro.substring(1,2) == '0'){
			ano = parseInt(numeroHidrometro.substring(2,3));
		}
		
		if(form.indicadorMacromedidor[1].checked == true 
		&& !((numeroHidrometro.substring(1,2)== '0' || numeroHidrometro.substring(1,2)== '1' ||
		numeroHidrometro.substring(1,2)== '2' ||numeroHidrometro.substring(1,2)== '3' ||
		numeroHidrometro.substring(1,2)== '4' || numeroHidrometro.substring(1,2)== '5' ||
		numeroHidrometro.substring(1,2)== '6' ||numeroHidrometro.substring(1,2)== '7' ||
		numeroHidrometro.substring(1,2)== '8' ||numeroHidrometro.substring(1,2)== '9')&& 
		(numeroHidrometro.substring(2,3)=='0' || numeroHidrometro.substring(2,3)== '1' ||
		numeroHidrometro.substring(2,3)== '2' ||numeroHidrometro.substring(2,3)== '3' ||
		numeroHidrometro.substring(2,3)== '4' || numeroHidrometro.substring(2,3)== '5' ||
		numeroHidrometro.substring(2,3)== '6' ||numeroHidrometro.substring(2,3)== '7' ||
		numeroHidrometro.substring(2,3)== '8' ||numeroHidrometro.substring(2,3)== '9')))   {
		
		alert('Informe ano num�rico');
			form.anoFabricacao.value = '';
		}else{
		
		//form.anoFabricacao.value = 1900 + ano;
		if(ano<60){
			form.anoFabricacao.value = 2000 + ano; 
		}else{
			form.anoFabricacao.value = 1900 + ano; 
		}
		if(ano >= 85){
		form.anoFabricacao.value = 1900 + ano; 
		}
		if(ano >= 00 &&  ano <= anoDataAtualFinal){
			form.anoFabricacao.value = 2000 + ano; 
		}
		
		if(anoDataAtualFinal < ano && form.anoFabricacao.value > parseInt(anoAtualCompleto)){
				form.anoFabricacao.value = '';
				alert('Ano de fabrica��o inv�lido');
		}else
		if(form.anoFabricacao.value  < 1985)	{
				form.anoFabricacao.value = '';
				alert('Ano de fabica��o de ser igual ou superior a 1985.');
			
			}
		}
}


	function bloqueiaDados(){
		
	
		var form = document.forms[0];
		
		if(form.indicadorMacromedidor[0].checked == true){
					
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = false;
			form.anoFabricacao.style.backgroundColor = '';
			
			form.idHidrometroRelojoaria.value = "-1";
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = true;
			form.idHidrometroRelojoaria.readOnly = true;
			form.idHidrometroRelojoaria.style.backgroundColor = '#EFEFEF';
	
			
			form.idHidrometroModeloSensor.style.color = "#000000";
			form.idHidrometroModeloSensor.disabled = false;
			form.idHidrometroModeloSensor.readOnly = false;
			form.idHidrometroModeloSensor.style.backgroundColor = '';
			
		} 
		
		if(form.indicadorMacromedidor[0].checked == false){
			form.indicadorMacromedidor[1].checked = true;
				
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = true;
			form.anoFabricacao.style.backgroundColor = '#EFEFEF';
	
			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = false;
			form.idHidrometroTipo.readOnly = false;
			form.idHidrometroTipo.style.backgroundColor = '';
	
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = false;
			form.idHidrometroRelojoaria.readOnly = false;
			form.idHidrometroRelojoaria.style.backgroundColor = '';		
			
			
			
		}  
	
	}

	function limparAnoFabricacao(){
		var form = document.forms[0];
		form.anoFabricacao.value = '';
		} 
			
//End 

--></script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloqueiaDados();">
	<html:form action="/atualizarHidrometroAction.do"
		name="AtualizarHidrometroActionForm"
		type="gcom.gui.micromedicao.hidrometro.AtualizarHidrometroActionForm"
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
					</div></td>
				<td valign="top" class="centercoltext">
					<table width="100%" height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img
								src="<bean:message key="caminho.imagens"/>parahead_left.gif"
								border="0" />
							</td>
							<td class="parabg">Atualizar Hidr&ocirc;metro</td>
							<td width="11" valign="top"><img
								src="<bean:message key="caminho.imagens"/>parahead_right.gif"
								border="0" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para atualizar o(s) hidr&ocirc;metros(s),
								informe os dados abaixo:</td>
							<logic:present scope="application" name="urlHelp">
								<td align="right"><a
									href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoHidrometroAtualizar', 500, 700);"><span
										style="font-weight: bold"><font color="#3165CE">Ajuda</font>
									</span> </a>
								</td>
							</logic:present>
							<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font
										color=#696969><u>Ajuda</u> </font> </span>
								</td>
							</logic:notPresent>
						</tr>
					</table>
					<table width="100%" border="0">

						<tr>
							<td colspan="2"><strong> <span class="style1">
										<html:radio property="indicadorMacromedidor" disabled="true"
											value="1" /> Macromedidor &nbsp;&nbsp;&nbsp; <html:radio
											property="indicadorMacromedidor" disabled="true" value="2" />
										Micromedidor </span> </strong></td>
						</tr>
						<tr>
							<td width="218"><strong>Matr�cula do Im�vel:</strong>
							</td>
							<td width="393"><html:text property="idImovel" size="10"
									maxlength="10" readonly="true"
									style="background-color:#EFEFEF; border:0;" />
							</td>
						</tr>
						<logic:equal name="indicadorMacromedidor" value="2">
							<tr>
								<td width="30%"><strong>N&uacute;mero do
										Hidr&ocirc;metro:<font color="#FF0000">*</font> </strong>
								</td>
								<td width="70%"><html:text property="numeroHidrometro"
										size="10" readonly="false"
										maxlength="10"
										tabindex="1" 
										onkeyup="javascript:limparAnoFabricacao();" 
										onblur="javascript:validarAnoFabricacao();"/>
								</td>

							</tr>
						</logic:equal>

						<logic:equal name="indicadorMacromedidor" value="1">
							<tr>	
								<td><strong>Tombamento:</strong>
								</td>
								<td><html:text maxlength="10" property="tombamento"
										size="10" tabindex="1"
										onchange="validaCampoSemCaractereEspecial(document.forms[0].tombamento, 'Tombamento');" />
								</td>
							</tr>
						</logic:equal>

						<tr>
							<td><strong>Capacidade:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:select property="idHidrometroCapacidade"
									tabindex="2"
									onchange="redirecionarSubmit('exibirAtualizarHidrometroAction.do');">>
					  <html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoHidrometroCapacidade"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Ano de Fabrica&ccedil;&atilde;o:<font
									color="#FF0000">*</font> </strong></td>
							<td><html:text maxlength="4" property="anoFabricacao"
									size="4" readonly="true"
									style="background-color:#EFEFEF; border:0;"
									onkeyup="return isCampoNumerico(event);"
									onkeypress="return isCampoNumerico(event);"
									onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].anoFabricacao, 'Ano de Fabrica��o');"
									onblur="return isCampoNumerico(event);" />aaaa</td>
						</tr>
						<tr>
							<td><strong>Marca:<font color="#FF0000">*</font> </strong>
							</td>
							<td><html:select property="idHidrometroMarca" tabindex="3">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoHidrometroMarca"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td height="24" colspan="2">
								<hr></td>
						</tr>
						<tr>
							<td><strong>Data de Aquisi&ccedil;&atilde;o:<font
									color="#FF0000">*</font> </strong></td>
							<td><html:text property="dataAquisicao" size="10"
									maxlength="10" onkeyup="mascaraData(this,event)" tabindex="4"
									onkeypress="mascaraData(this,event);"
									onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].dataAquisicao, 'Data de Aquisi��o');"
									onblur="mascaraData(this,event);" /> <a
								href="javascript:abrirCalendario('AtualizarHidrometroActionForm', 'dataAquisicao')">
									<img border="0"
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" />
							</a>dd/mm/aaaa</td>
						</tr>

						<tr>
							<td><strong>Finalidade:</strong>
							</td>
							<td><html:radio property="indicadorOperacional" tabindex="5"
									value="<%=(Hidrometro.INDICADOR_COMERCIAL).toString()%>" /><strong>Comercial</strong>
								<html:radio property="indicadorOperacional" tabindex="6"
									value="<%=(Hidrometro.INDICADOR_OPERACIONAL).toString()%>" /><strong>Operacional</strong>
							</td>
						</tr>
						<tr>
							<td><strong>Classe Metrol�gica:</strong>
							</td>
							<td><html:select property="idHidrometroClasseMetrologica"
									tabindex="7">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoHidrometroClasseMetrologica"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Di�metro:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:select property="idHidrometroDiametro"
									tabindex="8">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoHidrometroDiametro"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>


						<tr>
							<td><strong>N�mero de Digitos:<font color="#FF0000">*</font>
							</strong>
							</td>

							<td><logic:present name="colecaoIntervalo">
									<html:select property="idNumeroDigitosLeitura" tabindex="9"
										value="${hidrometro.numeroDigitosLeitura}">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>

										<html:options collection="colecaoIntervalo"
											labelProperty="numeroDigitosLeitura"
											property="numeroDigitosLeitura" />

									</html:select>

									<%--<html:select property="idNumeroDigitosLeitura" tabindex="10">
              			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> 
			  			<html:options collection="colecaoIntervalo" labelProperty="idNumeroDigitosLeitura" property="idNumeroDigitosLeitura"/>
            		  </html:select>--%>
								</logic:present> <logic:notPresent name="colecaoIntervalo">
									<html:select property="idNumeroDigitosLeitura" tabindex="9">
										<html:option value="-1">&nbsp;</html:option>
									</html:select>
								</logic:notPresent>
							</td>
						</tr>

						<tr>
							<td><strong>Tipo de Fluxo:</strong></td>
							
							<td><html:select property="idHidrometroTipo" tabindex="10">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoHidrometroTipo"
											labelProperty="descricao" property="id" />
									</html:select>
							</td>
						</tr>
							
                        <logic:equal name="indicadorMacromedidor" value="2">
							<tr>
								<td><strong>Tipo de Relojoaria:</strong>
								</td>
								<td><html:select property="idHidrometroRelojoaria"
										tabindex="10">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoHidrometroRelojoaria"
											labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
						</logic:equal>

						<tr>
							<td width="40%" align="left"><strong>Vaz�o
									Transi��o:</strong>
							</td>
							<td><html:text maxlength="6" property="vazaoTransicao"
									size="6" onkeyup="javascript:formataValorMonetario(this,13);" />
							</td>
						</tr>


						<tr>
							<td width="40%" align="left"><strong>Vaz�o Nominal:</strong>
							</td>
							<td><html:text maxlength="6" property="vazaoNominal"
									size="6" onkeyup="javascript:formataValorMonetario(this,13);" />
							</td>
						</tr>



						<tr>
							<td width="40%" align="left"><strong>Vaz�o M�nima:</strong>
							</td>
							<td><html:text maxlength="6" property="vazaoMinima" size="6"
									onkeyup="javascript:formataValorMonetario(this,13);" /></td>
						</tr>

						<tr>
							<td><strong>Nota Fiscal:</strong>
							</td>
							<td><html:text maxlength="9" property="notaFiscal" size="9"
									tabindex="2" onkeypress="return isCampoNumerico(event);" /></td>
						</tr>

						<tr>
							<td><strong>Tempo de Garantia em Anos:</strong>
							</td>
							<td><html:text maxlength="4" property="tempoGarantiaAnos"
									size="4" tabindex="2"
									onkeypress="return isCampoNumerico(event);" /></td>
						</tr>

						<logic:equal name="indicadorMacromedidor" value="1">
							<tr>
								<td width="40%" align="left"><strong>Erro do
										Macromedidor:</strong>
								</td>
								<td><html:text maxlength="6"
										property="hidrometroFatorCorrecao" size="6"
										onkeyup="javascript:formataValorMonetario(this,13);" /></td>
							</tr>

							<tr>
								<td><strong>Classe Press�o:</strong>
								</td>
								<td><html:text maxlength="2"
										property="hidrometroClassePressao" size="9" tabindex="2"
										onkeypress="return isCampoNumerico(event);" /></td>
							</tr>

							<tr>
								<td><strong>Modelo Sensor:<font color="#FF0000"></font>
								</strong>
								</td>
								<td><html:select property="idHidrometroModeloSensor"
										tabindex="5">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoHidrometroModeloSensor"
											labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<td>&nbsp;</td>
							<td align="left"><font color="#FF0000">*</font> Campo
								Obrigat�rio</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td><logic:present scope="session" name="manter">
									<input name="Submit222" class="bottonRightCol" value="Voltar"
										type="button"
										onclick="window.location.href='<html:rewrite page="/exibirManterHidrometroAction.do"/>'">
								</logic:present> <logic:notPresent scope="session" name="manter">
									<logic:present scope="session" name="filtrar_manter">
										<input name="Submit222" class="bottonRightCol" value="Voltar"
											type="button" onclick="javascript:history.back();">
									</logic:present>
									<logic:notPresent scope="session" name="filtrar_manter">
										<input name="Submit222" class="bottonRightCol" value="Voltar"
											type="button"
											onclick="window.location.href='/gsan/exibirFiltrarHidrometroAction.do?menu=sim';">
									</logic:notPresent>
								</logic:notPresent> <input name="Submit22" class="bottonRightCol" value="Desfazer"
								type="button"
								onclick="window.location.href='/gsan/exibirAtualizarHidrometroAction.do?idRegistroAtualizacao=<bean:write name="AtualizarHidrometroActionForm" property="idHidrometro" />';">
								<input name="Submit23" class="bottonRightCol" value="Cancelar"
								type="button"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><gsan:controleAcessoBotao name="Button"
									value="Atualizar"
									onclick="javascript:validarForm();"
									url="atualizarHidrometroAction.do" /> <!--
					<input type="button" class="bottonRightCol" tabindex="12"
						value="Atualizar" tabindex="13"
						onclick="validarForm(document.AtualizarHidrometroActionForm);">-->
							</td>
						</tr>
					</table>
					<p>&nbsp;</p></td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
		<script>

setCookie("desativaHistoryBack", "true", "1");

</script>
	</html:form>
</body>
</html:html>
