<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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

	function validarForm(){
		var form = document.forms[0];
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
			
			camposInvalidos += 'Campo Número de meses não pode ser inferior a 1(um) e nem superior a 120 meses. \n'
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
			
			form.submit();
		}else if(camposInvalidos != ''){
			alert(camposInvalidos);
		}
		return retorno;
	}
	
	
	
</script>

</head>

<html:javascript staticJavascript="false" formName="ParametrosPerdasSocietariasActionForm" />

<body leftmargin="5" topmargin="5" onload="">

	<div id="formDiv">
		<html:form action="/inserirParametrosPerdasSocietariasAction" method="post" name="ParametrosPerdasSocietariasActionForm"
			type="gcom.gui.relatorio.atendimentopublico.ParametrosPerdasSocietariasActionForm">


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
									Inserir Parâmetros de Perdas Societárias
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
								<td width="30%">
									<strong>Vigência a partir da referência contábil:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:text property="mesAnoReferenciaContabil" size="7" maxlength="7" tabindex="3"
										onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"/>
									mm/aaaa
								</td>
							</tr>

							<tr>
								<td width="30%"><strong>Per&iacute;odo de Referência para Baixa:<font	color="#FF0000">*</font></strong></td>
								<td colspan="3">
									<div align="left">
										<html:text property="mesAnoReferenciaBaixaInicial" size="8" maxlength="7" 
											onkeyup="mascaraAnoMes(this,event);"
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
							      	<html:checkbox property="indicadorCategoriaResidencial" value="1" tabindex="5"/><strong>Residencial</strong>
							      &nbsp;
								  	<html:checkbox property="indicadorCategoriaComercial" value="1" tabindex="6"/><strong>Comercial</strong>
								  &nbsp;
								  	<html:checkbox property="indicadorCategoriaIndustrial" value="1" tabindex="7"/><strong>Industrial</strong>
								  &nbsp;
								  	<html:checkbox property="indicadorCategoriaPublica" value="1" tabindex="8"/><strong>Pública</strong>
								  </td>    
						      </tr>
						      
						      <tr>
						          <td align="left" ><strong>Esfera de Poder:</strong><font color="#FF0000">*</font></td>
							      <td width="100" align="left" colspan="2">
							      	<html:checkbox  property="indicadorEsferaParticular" value="1" tabindex="9"/><strong>Particular</strong>
							      &nbsp;
								  	<html:checkbox property="indicadorEsferaMunicipal" value="1" tabindex="10"/><strong>Municipal</strong>
								  &nbsp;
								  	<html:checkbox property="indicadorEsferaEstadual" value="1" tabindex="11"/><strong>Estadual</strong>
								  &nbsp;
								  	<html:checkbox property="indicadorEsferaFederal" value="1" tabindex="12"/><strong>Federal</strong>
								  </td>    
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
										<input type="button" name="limpar" class="bottonRightCol" value="Limpar" tabindex="15" 
											onClick="window.location.href='/gsan/exibirInserirParametrosPerdasSocietariasAction.do?objeto=autorizar&menu=sim'" > 
										<input type="button" class="bottonRightCol" value="Cancelar" tabindex="16"
											onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</div>
								</td>

								<td colspan="4">
									<div align="right">
										<input type="button" name="botaoConcluir" class="bottonRightCol" value="Inserir" tabindex="17"
											onclick="javascript:validarForm();">
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<%@ include file="/jsp/util/rodape.jsp"%>
			</table>
		</html:form>
	</div>

</body>

</html:html>