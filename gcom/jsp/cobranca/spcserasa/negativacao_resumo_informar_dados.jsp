<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="InformarDadosConsultaNegativacaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
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

function habilitarPesquisaEloPolo(form) {
	form.tipoPesquisa.value = 'eloPolo';
	chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idEloPolo.value);
}

function habilitarPesquisaLocalidade(form) {
	form.tipoPesquisa.value = 'localidade';
	chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idLocalidade.value);
}

function habilitarPesquisaQuadra(form) {
	if(form.idSetorComercial.value != ''){
		chamarPopup('exibirPesquisarQuadraAction.do', 'quadra', null, null, 275, 480, '',form.idQuadra.value);
	}else{
		alert('Informar Setor Comercial');
	}
}

function habilitarPesquisaSetorComercial(form) {   
	if (form.idLocalidade.value != ''){
		abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.idLocalidade.value+'&tipo=SetorComercial&indicadorUsoTodos=1',form.idLocalidade.value,'Localidade', 400, 800);
		//chamarPopup('exibirPesquisarSetorComercialAction.do', 
			//			'setorComercial', 'idLocalidade', form.idLocalidade.value, 275, 480, 
				//		'Informe a Localidade',form.idSetorComercial.value);
	} else {
	
	    alert("Informar a localidade!");
	
		//chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercial', null, null, 275, 480, '',form.idSetorComercial.value);
	}
	
}

function habilitarPesquisaTituloComando(form) {
	chamarPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 'comandoNegativacao', null, null, 420, 650, '',form.idSetorComercial.value);
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'localidade') {
		if (form.tipoPesquisa.value == 'eloPolo'){
			form.idEloPolo.value = codigoRegistro;
	   	    form.descricaoEloPolo.value = descricaoRegistro;
	   	    form.descricaoEloPolo.style.color = "#000000";
		}
		if (form.tipoPesquisa.value == 'localidade'){
			form.idLocalidade.value = codigoRegistro;
			form.descricaoLocalidade.value = descricaoRegistro;	
			form.descricaoLocalidade.style.color = "#000000";
			verificaLocalidade();   	  
		}
    }
    if (tipoConsulta == 'quadra') {
		form.idQuadra.value = codigoRegistro;   	    
    }
    if (tipoConsulta == 'setorComercial') {
		form.idSetorComercial.value = codigoRegistro;
		form.descricaoSetorComercial.value = descricaoRegistro;
		verificaSetorComercial();
		form.descricaoSetorComercial.style.color = "#000000";
    }
    if (tipoConsulta == 'comandoNegativacao') {
		form.idNegativacaoComando.value = codigoRegistro;
   	    form.tituloComando.value = descricaoRegistro;
    }
}

function validaEnterEloPolo(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Elo Pólo");
}

function validaEnterLocalidade(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Localidade");
}

function validaEnterQuadra(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Quadra");
}

function validaEnterSetorComercial(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Setor Comercial");
}

function limparForm(tipo){
    var form = document.forms[0];
	if(tipo == 'eloPolo') {
		form.idEloPolo.value = "";
   	    form.descricaoEloPolo.value = "";
	}
	if(tipo == 'localidade') {
	    form.idLocalidade.value = "";
   	    form.descricaoLocalidade.value = "";
        verificaLocalidade();   	  	   
	}
	if(tipo == 'quadra') {
		form.idQuadra.value = "";
	}
	if(tipo == 'setorComercial') {
		form.idSetorComercial.value = "";
   	    form.descricaoSetorComercial.value = "";
   	    verificaSetorComercial();   	    
	}
	if(tipo == 'tituloComando') {
	 	form.tituloComando.value = "";
		form.idComandoNegativacao.value = "";
  
	}
}


function validarForm() {                                                                   
      var form = document.forms[0];         

       //if(validateRequired(form) && validateDate(form)){
       var algumNegativadorSelecionado = false;
       if(validateInformarDadosConsultaNegativacaoActionForm(form) && validateDate(form) && validateLong(form)){
			for (var x = 0; x < form.arrayNegativador.length && algumNegativadorSelecionado == false; x++) {
				if (form.arrayNegativador[x].selected == true && form.arrayNegativador[x].value != '-1') {
					algumNegativadorSelecionado = true;
				}
			}
			
			if (algumNegativadorSelecionado) {
	    		toggleBox('demodiv', 1);
			}else {
				alert("Selecione um ou mais Negativadores.");
				return false;
			}
		}
       
} 

function IntegerValidations () {
	this.aa = new Array("idEloPolo", "Localidade Pólo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}
       

function DateValidations () { 
     var form = document.forms[0];
	 
	 this.aa = new Array("periodoEnvioNegativacaoInicio", "Data Inicial do Período de Envio da Negativação não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("periodoEnvioNegativacaoFim", "Data Final do Período de Envio da Negativação não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];")); 
     
     if(form.periodoExclusaoNegativacaoInicio != undefined){
     	this.ba = new Array("periodoExclusaoNegativacaoInicio", "Data Inicial do Período de Exclusão de Negativação não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     	this.bb = new Array("periodoExclusaoNegativacaoFim", "Data Final do Período de Exclusão da Negativação não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     } 
} 

function required () {     
     this.aa = new Array("idNegativador","Informe o Negativador.", new Function ("varName", " return this[varName];"));
} 





function verificaLocalidade(){
	var form = document.forms[0];
		
	if (form.idLocalidade.value == ''){

		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.idQuadra.value = "";
		form.idSetorComercial.disabled = true;
		form.idQuadra.disabled = true;
		
	} else {

		form.idSetorComercial.disabled = false;
	}
}

function verificaSetorComercial(){
	var form = document.forms[0];
	
	
	if (form.idSetorComercial.value == ''){

		form.idQuadra.value = "";
		form.idQuadra.disabled = true;
	} else {
		form.idQuadra.disabled = false;
	}
}

	function submeter(){
		var formulario = document.forms[0]; 
		  formulario.action =  'exibirInformarDadosConsultaNegativacaoAction.do';
		  formulario.submit();
    }

function limparTodoForm(){
	var form = document.forms[0];
	
	form.tipoRelatorioNegativacao.value = "1";
	form.tipoRelatorioNegativacao[0].checked = "checked";
	form.arrayNegativador.value = "-1";
	form.periodoEnvioNegativacaoInicio.value = "";
	form.periodoEnvioNegativacaoFim.value = "";
	
	if(form.periodoExclusaoNegativacaoInicio != undefined){
		form.periodoExclusaoNegativacaoInicio.value = "";
		form.periodoExclusaoNegativacaoFim.value = "";
		form.idNegativadorExclusaoMotivo.value = "-1";
	}
	
	form.tituloComando.value = "";
	form.arrayCobrancaGrupo.value = "-1";
	form.arrayGerenciaRegional.value = "-1";
	form.arrayUnidadeNegocio.value = "-1";
	form.idEloPolo.value = "";
	form.descricaoEloPolo.value = "";
	form.idLocalidade.value = "";
	form.descricaoLocalidade.value = "";
	form.idSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";
	form.idQuadra.value = "";
	form.arrayImovelPerfil.value = "-1";
	form.arrayCategoria.value = "-1";
	form.arrayTipoCliente.value = "-1";
	form.arrayEsferaPoder.value = "-1";
	form.arrayLigacaoAguaSituacao.value = "-1";
	form.arrayLigacaoEsgotoSituacao.value = "-1";
	
	if(form.indicadorApenasNegativacoesRejeitadas != undefined){
		form.indicadorApenasNegativacoesRejeitadas.value = 2;
		form.indicadorApenasNegativacoesRejeitadas[1].checked = "checked";
	}
	
	if(form.arrayMotivoRejeicao != undefined){
		form.arrayMotivoRejeicao.value = "-1";
	}	
}

function replicarDataEnvio(){
	var form = document.forms[0];
	
	form.periodoEnvioNegativacaoFim.value = form.periodoEnvioNegativacaoInicio.value;
}

function replicarDataExclusao(){
	var form = document.forms[0];
	
	form.periodoExclusaoNegativacaoFim.value = form.periodoExclusaoNegativacaoInicio.value;
}


-->
</script>
</head>


<body leftmargin="5" topmargin="5" onload="verificaLocalidade();verificaSetorComercial();">
<div id="formDiv">
<html:form action="/informarDadosConsultaNegativacaoAction"   
	name="InformarDadosConsultaNegativacaoActionForm"
	type="gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm"
  	method="post">  

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>
<html:hidden property="idNegativacaoComando"/>
<html:hidden property="okEloPolo"/>
<html:hidden property="okLocalidade"/>
<html:hidden property="okSetorComercial"/>
<html:hidden property="okQuadra"/>



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
		<td width="655" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->

              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                  <td class="parabg" >Consulta do Resumo da Negativação/Relatório</td>
                  <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
                </tr>
              </table>

              <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <p>&nbsp;</p>
              <table width="100%" border="0" >
                <tr> 
                  <td colspan="2">Para gerar o relat&oacute;rio/consulta do resumo da negativa&ccedil;&atilde;o, informe os dados abaixo:</td>
                </tr>
                
                <tr>
					<td><strong>Tipo Relatório: </strong></td>
						<td><html:radio property="tipoRelatorioNegativacao" tabindex="1" value="<%=ConstantesSistema.SIM.toString()%>"  /><strong>Analítico</strong>
							<html:radio property="tipoRelatorioNegativacao" tabindex="2" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>Sintético</strong>
					<td>&nbsp;</td>
				</tr>
                
                <tr>
                  <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                  <td>
                  	<logic:present name="collNegativador">  
                   	   <html:select property="arrayNegativador" multiple="true" tabindex="7" onmouseup="submeter();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="collNegativador">
								<html:options collection="collNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>  
                  </td>
                </tr>
                <tr>

                  <td width="35%"><strong>Per&iacute;odo do Envio da Negativa&ccedil;&atilde;o:<font color="#FF0000"></font></strong></td>
                  <td width="65%">
                  	<html:text property="periodoEnvioNegativacaoInicio" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event);replicarDataEnvio();" />
					<a	href="javascript:abrirCalendarioReplicando('InformarDadosConsultaNegativacaoActionForm', 'periodoEnvioNegativacaoInicio', 'periodoEnvioNegativacaoFim');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"/></a>
					a <html:text property="periodoEnvioNegativacaoFim" size="10"
						maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);" /> 
					<a	href="javascript:abrirCalendario('InformarDadosConsultaNegativacaoActionForm', 'periodoEnvioNegativacaoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                
                    
               <logic:equal name="InformarDadosConsultaNegativacaoActionForm" property="indicadorRelExclusao" value="sim">
                <tr>
                  <td width="35%"><strong>Período da Exclusão da Negativação:<font color="#FF0000"></font></strong></td>
                  <td width="65%">
                  	<html:text property="periodoExclusaoNegativacaoInicio" size="10"
						maxlength="10" tabindex="4"
						onkeyup="mascaraData(this, event);replicarDataExclusao();" />
					<a	href="javascript:abrirCalendarioReplicando('InformarDadosConsultaNegativacaoActionForm', 'periodoExclusaoNegativacaoInicio', 'periodoExclusaoNegativacaoFim');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário"/></a>
					a <html:text property="periodoExclusaoNegativacaoFim" size="10"
						maxlength="10" tabindex="5" onkeyup="mascaraData(this, event);" /> 
					<a	href="javascript:abrirCalendario('InformarDadosConsultaNegativacaoActionForm', 'periodoExclusaoNegativacaoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                
                
                 <tr>
                  <td><strong>Motivo da Exclusão da Negativação:</strong></td>
                  <td>
                   
                   	   <html:select property="idNegativadorExclusaoMotivo" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="collNegativadorExclusaoMotivo">
								<html:options collection="collNegativadorExclusaoMotivo" labelProperty="descricaoExclusaoMotivo" property="id"/>
							</logic:present>
						</html:select>
                  </td>
                </tr>
                
                </logic:equal>
                
             
                <tr>
                  <td><strong> T&iacute;tulo do Comando:</strong></td>
                  <td><strong><b><span class="style2">
                    <html:textarea property="tituloComando" cols="38" rows="2" readonly="true"></html:textarea>
                  </span></b></strong>
                  	<a href="javascript:habilitarPesquisaTituloComando(document.forms[0]);" title="Pesquisar Título Comando">
                  		<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>
                  	<a href="javascript:limparForm('tituloComando');">
	                  	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" width="23" height="21" border="0" title="Limpar" ></a> 	
                  </td>
                  	
                </tr>
                <tr> 
                  <td><strong> <strong>Grupo de Cobran&ccedil;a:</strong></strong></td>
                  <td>
                  <logic:present name="collCobrancaGrupo">
                   	   <html:select property="arrayCobrancaGrupo" tabindex="7" multiple="true" size="4">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="collCobrancaGrupo">
							  <html:options collection="collCobrancaGrupo" labelProperty="descricao" property="id"/>
                            </logic:present>
						</html:select>
                	</logic:present>
				  </td>
                </tr>

                <tr> 
                  <td><strong> <font color="#FF0000"></font></strong><strong> 
                    <strong>Ger&ecirc;ncia Regional: </strong></strong></td>
                  <td>
                  <logic:present name="collGerenciaRegional">  
                   	   <html:select property="arrayGerenciaRegional" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						    <logic:present name="collGerenciaRegional">
						    	<html:options collection="collGerenciaRegional" labelProperty="nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                  </td>
                </tr>
                <tr>
                  <td><strong> <font color="#FF0000"></font></strong><strong> <strong>Unidade de Neg&oacute;cio: </strong></strong></td>
                  <td>
              	   	  <logic:present name="collUnidadeNegocio">  
                   	   <html:select property="arrayUnidadeNegocio" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>						    
							<logic:present name="collUnidadeNegocio">
						    	<html:options collection="collUnidadeNegocio" labelProperty="nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
				  </td>
                </tr>
                <tr> 
                  <td><strong> Localidade Pólo:</strong></td>
                  <td>
                  	<html:text property="idEloPolo" maxlength="3" size="3" onkeyup="return validaEnterEloPolo(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idEloPolo'); " />
					<a href="javascript:habilitarPesquisaEloPolo(document.forms[0]);" title="Pesquisar Localidade Pólo">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corEloPolo">
						<logic:equal name="corEloPolo" value="exception">
							<html:text property="descricaoEloPolo" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corEloPolo" value="exception">
							<html:text property="descricaoEloPolo" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corEloPolo">
						<html:text property="descricaoEloPolo" size="38"	readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent>
					<a href="javascript:limparForm('eloPolo');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Limpar" /> 
					</a>
                  </td>
                </tr>
                <tr> 
                  <td><strong> <font color="#FF0000"></font></strong><strong> 
                    Localidade:</strong></td>
                  <td>
                  	<html:text property="idLocalidade" maxlength="3" size="3" onkeyup="return validaEnterLocalidade(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idLocalidade'); " />
					<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);" title="Pesquisar Localidade">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corLocalidade">
						<logic:equal name="corLocalidade" value="exception">
							<html:text property="descricaoLocalidade" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corLocalidade" value="exception">
							<html:text property="descricaoLocalidade" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corLocalidade">
						<html:text property="descricaoLocalidade" size="38"	readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent>
					<a href="javascript:limparForm('localidade');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Limpar" /> 
					</a>
                  </td>
                </tr>
                <tr> 
                  <td><strong> <font color="#FF0000"></font></strong><strong> 
                    Setor Comercial:</strong></td>
                  <td>
                  	<html:text property="idSetorComercial" maxlength="3" size="3" onkeyup="return validaEnterSetorComercial(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idSetorComercial'); " />
					<a href="javascript:habilitarPesquisaSetorComercial(document.forms[0]);" title="Pesquisar Setor Comercial">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corSetorComercial">
						<logic:equal name="corSetorComercial" value="exception">
							<html:text property="descricaoSetorComercial" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corSetorComercial" value="exception">
							<html:text property="descricaoSetorComercial" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corSetorComercial">
						<html:text property="descricaoSetorComercial" size="38" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent>
					<a href="javascript:limparForm('setorComercial');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Limpar" /> 
					</a>
                  </td>
                </tr>


				 
                <tr> 
                  <td><strong> <font color="#FF0000"></font></strong><strong> 
                    Quadra:</strong></td>
                
                  <td>
                  	<html:text property="idQuadra" maxlength="4" size="4" onkeyup="return validaEnterQuadra(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idQuadra'); " />
				  	<logic:present name="codigoQuadraNaoEncontrada" scope="request">
						<span style="color:#ff0000" id="msgQuadra"><bean:write
							scope="request" name="msgQuadra" /></span>
					</logic:present> <logic:notPresent name="codigoQuadraNaoEncontrada"
						scope="request">
					</logic:notPresent>
				  </td>
				<!-- 	
						<a href="javascript:habilitarPesquisaQuadra(document.forms[0]);" alt="Pesquisar Setor Comercial">
						<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
										
					<logic:present name="corQuadra">
						<logic:equal name="corQuadra" value="exception">
							<html:text property="descricaoQuadra" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corQuadra" value="exception">
							<html:text property="descricaoQuadra" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corQuadra">
						<logic:empty name="InformarDadosConsultaNegativacaoActionForm"	property="idQuadra">
							<html:text property="descricaoQuadra" size="38" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InformarDadosConsultaNegativacaoActionForm" property="idQuadra">
							<html:text property="descricaoQuadra" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('quadra');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
					-->  
                  
                </tr>        
                
                <tr> 
                  <td><strong> Perfil do Im&oacute;vel:<font color="#FF0000"></font></strong></td>

                  <td align="left"><strong>           
                	  <logic:present name="collImovelPerfil">  
                   	   <html:select property="arrayImovelPerfil" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>						    
						  	<logic:present name="collImovelPerfil">
						    	<html:options collection="collImovelPerfil" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>    	
				  </td>
                </tr>
                <tr> 
                  <td><strong> Categoria:<font color="#FF0000"></font></strong></td>
                  <td align="left"> 
                 	  <logic:present name="collCategoria">  
                   	   <html:select property="arrayCategoria" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>						    
						    <logic:present name="collCategoria">
						    	<html:options collection="collCategoria" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                    </td>
                </tr>
                <tr>
                  <td><strong> Tipo de Cliente:<font color="#FF0000"></font></strong></td>
                  <td align="left">			
                	<logic:present name="collClienteTipo">  
                   	   <html:select property="arrayTipoCliente" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>						    
						    <logic:present name="collClienteTipo">
						    	<html:options collection="collClienteTipo" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                	
                	               
                  </td>
                </tr>
                <tr> 
                  <td><strong> Esfera de Poder:<font color="#FF0000"></font></strong></td>
                  <td align="left">
                	 <logic:present name="collEsferaPoder">  
                   	   <html:select property="arrayEsferaPoder" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>					    
						    <logic:present name="collEsferaPoder">
						    	<html:options collection="collEsferaPoder" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                  </td>
                </tr>
                
                <tr> 
                  <td><strong> Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o de &Aacute;gua:<font color="#FF0000"></font></strong></td>
                  <td align="left">
                	 <logic:present name="collLigacaoAguaSituacao">  
                   	   <html:select property="arrayLigacaoAguaSituacao" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						    <logic:present name="collLigacaoAguaSituacao">
						    	<html:options collection="collLigacaoAguaSituacao" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                  </td>
                </tr>
                
                <tr> 
                  <td><strong> Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o de Esgoto:<font color="#FF0000"></font></strong></td>
                  <td align="left">
                	 <logic:present name="collLigacaoEsgotoSituacao">  
                   	   <html:select property="arrayLigacaoEsgotoSituacao" tabindex="7" multiple="true" size="4">
						    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>						    
						    <logic:present name="collLigacaoEsgotoSituacao">
						    	<html:options collection="collLigacaoEsgotoSituacao" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                  </td>
                </tr>
                
                <logic:equal name="InformarDadosConsultaNegativacaoActionForm" property="indicadorRelAcompanhamentoClientesNegativados" value="sim">
                
	                 <tr>
						<td><strong>Apenas Negativações Rejeitadas? <font color="#FF0000">*</font></strong></td>
						<td><html:radio property="indicadorApenasNegativacoesRejeitadas" tabindex="8" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
						<html:radio property="indicadorApenasNegativacoesRejeitadas" tabindex="9" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>Não</strong>
						<td>&nbsp;</td>
					</tr>
					<logic:present name="collMotivoRejeicao"> 
						<tr> 
		                  <td><strong>Motivos de Rejeição:<font color="#FF0000"></font></strong></td>
		                  <td>
	                   	   <html:select property="arrayMotivoRejeicao" tabindex="10" multiple="true" size="4" style="width:350px;">
							    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>							    
								<logic:present name="collMotivoRejeicao">
							    	<html:options collection="collMotivoRejeicao" labelProperty="descricaoRetornocodigo" property="id"/>
								</logic:present>
							</html:select>
		                  </td>
		                </tr>
               		</logic:present>
                </logic:equal>
                
                
                <tr> 
                  <td>&nbsp;</td>
                  <td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio                  </td>
                </tr>
                <tr> 
                  <td><p>&nbsp;</p></td>
                  <td> </td>

                </tr>
              </table>
              <table> 
	              <tr> 
		              <td width="100%">
			          	<input type="button" onclick="javascript:limparTodoForm();" value="Limpar" class="bottonRightCol">
			          	<input align="left" type="button" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" value="Cancelar" class="bottonRightCol" name="Button">									          		
             		 </td>
             		 <td>	
             			<input type="button" name="botaoInserir" class="bottonRightCol" value="Gerar Consulta" onclick="javascript:validarForm();"  />
					</td>
				</tr>
			</table>	
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=informarDadosConsultaNegativacaoAction.do" />

</html:form>
</div>
</body>


<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>