<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
 

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

	function pesquisar(){
		var form = document.forms[0];

		form.action = '/gsan/exibirManterParametrosPerdasSocietariasAction.do?pesquisar=sim';
		form.submit();
	}

	function verifica_data (data) { 

		if(data.value.length == 7){
	        mes = (data.value.substring(0,2)); 
	        ano = (data.value.substring(3,7)); 
	
	        // verifica se o mes e valido 
	        if (mes < 01 || mes > 12 ) { 
	            return false;
	        } 
	
	        if (ano  <=0 ) { 
	            return false;
	        } 
	
		}else{
			 return false;
		}
	
	        return true;
      } 

	function enter(tecla){

		var form = document.forms[0];
		
		if (document.all) {
		  var codigo = event.keyCode;
		} else {
		  var codigo = tecla.which;
		}
		
	    if (codigo == 13) {
	      form.action = "/gsan/exibirManterParametrosPerdasSocietariasAction.do?pesquisar=sim";
	      form.submit();
	    } else {
	      return true;
	    }
	
	}

	function checkIndicadores() {

		var form = document.forms[0];

		if (form.indicadorCategoriaResidencial.checked) {
			form.residencial.value = "1";
		} else {
			form.residencial.value = "";
		}

		if (form.indicadorCategoriaComercial.checked) {
			form.comercial.value = "1";
		} else {
			form.comercial.value = "";
		}

		if (form.indicadorCategoriaIndustrial.checked) {
			form.industrial.value = "1";
		} else {
			form.industrial.value = "";
		}

		if (form.indicadorCategoriaPublica.checked) {
			form.publica.value = "1";
		} else {
			form.publica.value = "";
		}

		if (form.indicadorEsferaParticular.checked) {
			form.particular.value = "1";
		} else {
			form.particular.value = "";
		}

		if (form.indicadorEsferaMunicipal.checked) {
			form.municipal.value = "1";
		} else {
			form.municipal.value = "";
		}

		if (form.indicadorEsferaEstadual.checked) {
			form.estadual.value = "1";
		} else {
			form.estadual.value = "";
		}

		if (form.indicadorEsferaFederal.checked) {
			form.federal.value = "1";
		} else {
			form.federal.value = "";
		}
	}

		
	function validarForm(){
		var form = document.forms[0];

		checkIndicadores();
		
		var retorno = true;
		var camposInvalidos ='';
		if(form.mesAnoReferenciaContabil.value == ""){
			camposInvalidos += 'Informe a Referência Contábil. \n'
			retorno = false;
		}

		if(form.mesAnoReferenciaBaixaInicial.value == ""){
			camposInvalidos += "Período Referência Inicial";
			retorno = false;
		}else {
			if(!verifica_data(form.mesAnoReferenciaBaixaInicial)){
				alert("Período Referência Inicial Inválido");
				retorno = false;
			}
		}
		
		if(form.mesAnoReferenciaBaixaFinal.value == ""){
			camposInvalidos += "Período Referência Final. \n";
			retorno = false;
		}else {
			if(!verifica_data(form.mesAnoReferenciaBaixaFinal)){
				alert("Período de Referência Final Inválido");
				retorno = false;
			}
		}
		
		if(form.mesReferenciaInferior.value == ""){
			camposInvalidos += "Informe Referência Inferior. \n";
			retorno = false;
		}

		if (comparaMesAno(form.mesAnoReferenciaBaixaInicial.value, '>', form.mesAnoReferenciaBaixaFinal.value)){
			camposInvalidos += "Período Referência Inicial maior que o Período Referência Final. \n";
			retorno = false;
		}
		if(form.mesReferenciaInferior.value == ""){
			camposInvalidos += "Informe o número de meses Referência Inferior. \n";
			retorno = false;
		}
		else if(parseInt(form.mesReferenciaInferior.value) > 120 || 
				parseInt(form.mesReferenciaInferior.value) < 1){
			
			camposInvalidos += 'Campo Número de meses não pode ser inferior a 1 e nem superior a 120 meses. \n'
				retorno  = false;
		}
			
		if ( form.indicadorCategoriaResidencial.checked == false  && form.indicadorCategoriaComercial.checked == false  && 
				form.indicadorCategoriaPublica.checked == false && form.indicadorCategoriaIndustrial.checked == false ) {

			camposInvalidos += "Selecione ao menos uma Categoria. \n"; retorno = false; 
		}

		if ( form.indicadorEsferaParticular.checked == false && form.indicadorEsferaMunicipal.checked == false &&
				form.indicadorEsferaEstadual.checked == false && form.indicadorEsferaFederal.checked == false ) {

			 camposInvalidos += "Selecione ao menos uma Esfera de Poder. \n"; 
			 retorno = false; 
		}
		if(retorno){

			form.action = '/gsan/manterParametrosPerdasSocietariasAction.do?pesquisar=sim';
			form.submit();
		}else{
			alert(camposInvalidos);
		}
		return retorno;
	}
	
	
	
</script>

</head>

<html:javascript staticJavascript="false" formName="ParametrosPerdasSocietariasActionForm" />

<body leftmargin="5" topmargin="5" >

	<div id="formDiv">
		<html:form action="/manterParametrosPerdasSocietariasAction" method="post" name="ParametrosPerdasSocietariasActionForm"
			type="gcom.gui.relatorio.atendimentopublico.ParametrosPerdasSocietariasActionForm">

			<html:hidden property="residencial"/>
			<html:hidden property="comercial"/>
			<html:hidden property="industrial"/>
			<html:hidden property="publica"/>
			<html:hidden property="particular"/>
			<html:hidden property="municipal"/>
			<html:hidden property="federal"/>
			<html:hidden property="estadual"/>

			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>
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
					<td width="625" valign="top" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>

						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">
									Manter Parâmetros de Perdas Societárias
								</td>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>

						<p>&nbsp;</p>

						<table width="100%" border="0">
							<tr>
								<td colspan="3">
									Para atualizar os Parâmetros de Perdas Societárias, informe a referência (mês/ano):
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
									<div align="left">
										<strong> </strong>
									</div>
								</td>
							</tr>

							<tr>
								<td width="35%">
									Vigência a partir da referência contábil:<font color="#FF0000">*</font>
								</td>
								<td colspan="2">
									<html:text property="mesAnoReferenciaContabil" size="7" maxlength="7" tabindex="3"
										onkeyup="mascaraAnoMes(this, event);" onkeypress="enter(event);return isCampoNumerico(event);"
								/>
									mm/aaaa
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
									<div align="right">
										<input type="button" class="bottonRightCol" value="Pesquisar" tabindex="17"
											onclick="javascript:pesquisar();">
									</div>
								</td>
							</tr>
							<tr>
							<td height="24" colspan="4">
								<hr>
								</td>
							</tr>
							<logic:present name="parametrosPerdasSocietarias">
								<tr>
									<td width="30%"><strong>Per&iacute;odo de Referência para Baixa:<font	color="#FF0000">*</font></strong></td>
									<td colspan="3">
										<div align="left">
											<html:text property="mesAnoReferenciaBaixaInicial" size="8" maxlength="7" 
												onkeyup="mascaraAnoMes(this,event);replicaDados(document.forms[0].mesAnoReferenciaInicial, document.forms[0].mesAnoReferenciaFinal);"
												onkeypress="return isCampoNumerico(event);"/> 
					
											<strong> a </strong>
					
											<html:text property="mesAnoReferenciaBaixaFinal" size="8" maxlength="7"
												onkeyup="mascaraAnoMes(this,event)"  
												onkeypress="return isCampoNumerico(event);"/>(mm/aaaa)
										</div>
									</td>
								</tr>
								
								<tr>
									<td width="30%" ><strong>Imóveis que tenham contas com Baixa Contábil com referências inferiores a:<font	color="#FF0000">*</font></strong></td>
									<td><html:text size="6" maxlength="3" property="mesReferenciaInferior" onkeyup="javascript:verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiro(this);"></html:text>
								</tr>
								
								 <tr>
							          <td align="left" ><strong>Categoria:</strong><font color="#FF0000">*</font></td>
								      <td width="100" align="left" colspan="2">
								       
								      	  <logic:notEqual value="true" name="residencial">
								      		<html:checkbox property="indicadorCategoriaResidencial"  value=""/><strong>Residencial</strong>
								      	</logic:notEqual>
								       		
								       	 <logic:equal value="true" name="residencial">
								      	 <html:checkbox property="indicadorCategoriaResidencial" value="1" /><strong>Residencial</strong>
								      	 </logic:equal>
								      &nbsp;
								      <logic:notEqual value="true" name="comercial">
									  	<html:checkbox property="indicadorCategoriaComercial" value="" /><strong>Comercial</strong>
									 </logic:notEqual>
									 <logic:equal value="true" name="comercial">
									 	  	<html:checkbox property="indicadorCategoriaComercial" value="1" /><strong>Comercial</strong>
									
									 </logic:equal>
									  &nbsp;
									  
									  <logic:notEqual value="true" name="industrial">
									  	<html:checkbox property="indicadorCategoriaIndustrial" value=""/><strong>Industrial</strong>
									 </logic:notEqual>
									 <logic:equal value="true" name="industrial">
									  	<html:checkbox property="indicadorCategoriaIndustrial" value="1" /><strong>Industrial</strong>
									 </logic:equal>
									  &nbsp;
									  
									   <logic:equal value="true" name="publica">
									  	<html:checkbox property="indicadorCategoriaPublica" value="1" /><strong>Pública</strong>
									  </logic:equal>
									  
									   <logic:notEqual value="true" name="publica">
									  	<html:checkbox property="indicadorCategoriaPublica" value="" /><strong>Pública</strong>
									  </logic:notEqual>
									  </td>    
							      </tr>
							      
							      <tr>
							          <td align="left" ><strong>Esfera de Poder:</strong><font color="#FF0000">*</font></td>
								      <td width="100" align="left" colspan="2">
								      <logic:equal value="true" name="particular">
								      	<html:checkbox property="indicadorEsferaParticular" value="1" /><strong>Particular</strong>
								     </logic:equal>
								     
								     <logic:notEqual value="true" name="particular">
								      	<html:checkbox property="indicadorEsferaParticular" value=""/><strong>Particular</strong>
								     </logic:notEqual>
								     
								      &nbsp;
								       <logic:equal value="true" name="municipal">
									  		<html:checkbox property="indicadorEsferaMunicipal" value="1"/><strong>Municipal</strong>
									  </logic:equal>
									  
									  <logic:notEqual value="true" name="municipal">
									  		<html:checkbox property="indicadorEsferaMunicipal" value="" /><strong>Municipal</strong>
									  </logic:notEqual>
									  &nbsp;
									  
									  <logic:equal value="true" name="estadual">
									  		<html:checkbox property="indicadorEsferaEstadual" value="1"/><strong>Estadual</strong>
									 	</logic:equal>
									 	
									 	<logic:notEqual value="true" name="estadual">
									  		<html:checkbox property="indicadorEsferaEstadual" value="" /><strong>Estadual</strong>
									 	</logic:notEqual>
									  &nbsp;
									  
									  <logic:equal value="true" name="federal">
									  	<html:checkbox property="indicadorEsferaFederal" value="1" /><strong>Federal</strong>
									  </logic:equal>
									  
									  <logic:notEqual value="true" name="federal">
									  	<html:checkbox property="indicadorEsferaFederal" value=""/><strong>Federal</strong>
									  </logic:notEqual> 
							      </tr>
								
								<tr>
									<td>&nbsp;</td>
									<td colspan="2" align="right">
									<div align="left"><strong> </strong></div>
								</td>
								
								</tr>
	
								<tr>
									<td>
										<div align="left">
											<input type="button" name="limpar" class="bottonRightCol" value="Cancelar" tabindex="15" 
												onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" > 
												<input name="Button" type="button" 
												   class="bottonRightCol" value="Desfazer" 
												   align="left" onclick="javascript:redirecionarSubmit('exibirManterParametrosPerdasSocietariasAction.do?desfazer=sim');" >
                    
										</div>
									</td>
	
									<td colspan="4">
										<div align="right">
											<input type="button" name="botaoConcluir" class="bottonRightCol" value="Manter" tabindex="17"
												onclick="javascript:validarForm();">
										</div>
									</td>
								</tr>
							</logic:present>
						</table>
					</td>
				</tr>

				<%@ include file="/jsp/util/rodape.jsp"%>
			</table>
		</html:form>
	</div>

</body>

</html:html>