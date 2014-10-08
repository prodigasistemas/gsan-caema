<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
<script type="text/javascript">
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled && !campo.readOnly){
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

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'imovel') {
  		form.idImovel.value = codigoRegistro;
  		form.descricaoImovel.value = descricaoRegistro;
  		form.descricaoImovel.style.color = "#000000";
	}else if (tipoConsulta == 'municipio') {
  		form.idMunicipio.value = codigoRegistro;
  		form.descricaoMunicipio.value = descricaoRegistro;
  		form.descricaoMunicipio.style.color = "#000000";
	}else if(tipoConsulta == 'logradouro'){
		form.idLogradouro.value = codigoRegistro;
  		form.descricaoLogradouro.value = descricaoRegistro;
  		form.descricaoLogradouro.style.color = "#000000";
	}else if(tipoConsulta == 'localidadeOrigem'){
		form.idLocalidadeInicial.value = codigoRegistro;
		form.descricaoLocalidadeInicial.value = descricaoRegistro;
 		form.descricaoLocalidadeInicial.style.color = "#000000";

 		form.idLocalidadeFinal.value = codigoRegistro;
		form.descricaoLocalidadeFinal.value = descricaoRegistro;
 		form.descricaoLocalidadeFinal.style.color = "#000000";
	}else if(tipoConsulta == 'localidadeDestino'){
		form.idLocalidadeFinal.value = codigoRegistro;
		form.descricaoLocalidadeFinal.value = descricaoRegistro;
 		form.descricaoLocalidadeFinal.style.color = "#000000";
	}
	habilitaCampos();
  }

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if(tipoConsulta == 'setorComercialOrigem'){
		form.codigoSetorComercialInicial.value = codigoRegistro;
		form.descricaoSetorComercialInicial.value = descricaoRegistro;
		form.descricaoSetorComercialInicial.style.color = "#000000";

		form.codigoSetorComercialFinal.value = codigoRegistro;
		form.descricaoSetorComercialFinal.value = descricaoRegistro;
		form.descricaoSetorComercialFinal.style.color = "#000000";
	}else if(tipoConsulta == 'setorComercialDestino'){
		form.codigoSetorComercialFinal.value = codigoRegistro;
		form.descricaoSetorComercialFinal.value = descricaoRegistro;
		form.descricaoSetorComercialFinal.style.color = "#000000";
	}	
	habilitaCampos();
}

function limparBorrachaOrigem(limpar){
	var form = document.forms[0];
	if(limpar==1){
		form.idMunicipio.value = ''
  		form.descricaoMunicipio.value = ''
	}else if(limpar==2){
		form.idLogradouro.value = ''
  		form.descricaoLogradouro.value = ''
	}else if(limpar==3){
		form.idLocalidadeInicial.value = ''
		form.descricaoLocalidadeInicial.value = ''
		form.idLocalidadeFinal.value = ''
		form.descricaoLocalidadeFinal.value = ''
	}else if(limpar==4){
		form.idLocalidadeFinal.value = ''
		form.descricaoLocalidadeFinal.value = ''
	}else if(limpar==5){
		form.codigoSetorComercialInicial.value='';
		form.descricaoSetorComercialInicial.value='';
		form.codigoSetorComercialFinal.value = '';
		form.descricaoSetorComercialFinal.value = '';
	}else if(limpar==6){
		form.codigoSetorComercialFinal.value = '';
		form.descricaoSetorComercialFinal.value = '';	
	}
	habilitaCampos();
}

function limparOrigem(limpar){
	var form = document.forms[0];
	if(limpar==1){
		form.descricaoLocalidadeInicial.value = '';
		form.idLocalidadeFinal.value = '';
		form.descricaoLocalidadeFinal.value = '';
	}else if(limpar==2){
		form.descricaoSetorComercialInicial.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.descricaoSetorComercialFinal.value = '';
	}
	habilitaCampos();
}

function replicarLocalidade(){
	var form = document.forms[0];
	form.idLocalidadeFinal.value = form.idLocalidadeInicial.value; 
	form.descricaoLocalidadeFinal.value = form.descricaoLocalidadeInicial.value;
}

function replicarSetorComercial(){
	var form = document.forms[0];
	form.codigoSetorComercialFinal.value = form.codigoSetorComercialInicial.value;
	form.descricaoSetorComercialFinal.value = form.descricaoSetorComercialInicial.value
}

function replicarQuadra(){
	var form = document.forms[0];
	form.quadraFinal.value = form.quadraInicial.value;
}

function replicarRota(){
	var form = document.forms[0];
	form.codigoRotaFinal.value = form.codigoRotaInicial.value;
}

function replicarRotaSequencial(){
	var form = document.forms[0];
	form.sequencialRotaFinal.value = form.sequencialRotaInicial.value;
}

function replicarPeriodo(){
	var form = document.forms[0];
	form.dataGeracaoFinal.value = form.dataGeracaoInicial.value;
} 

function pesquisarSetorComercialInicial(){
	var form = document.forms[0];
	if (!form.codigoSetorComercialInicial.readOnly) {
		if(form.idLocalidadeInicial.value!=""){
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', null, null, 275, 480, '',form.codigoSetorComercialInicial);
		}else{
			alert("Informe a Localidade Inicial");
		}
	}
}

function pesquisarSetorComercialFinal(){
	var form = document.forms[0];
	if (!form.codigoSetorComercialFinal.readOnly) {
		if(form.idLocalidadeFinal.value!=""){
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', null, null, 275, 480, '',form.codigoSetorComercialFinal);
		}else{
			alert("Informe a Localidade Final");
		}
	}
}

function informarQuadra(evento,quadra){
	var form = document.forms[0];
	var tecla = null;
	
	  if(window.event){ // Internet Explorer
	  		tecla = evento.keyCode;
	  }else if(evento.which){ // Nestcape ou Mozilla
	    	tecla = evento.which;
	  }
	  
	  if(tecla == null 
			  || (tecla > 47 && tecla < 58) || (tecla.value == 'undefined')
			  || tecla == 8 || tecla == 13){
		  if(quadra==1){
				if(form.idLocalidadeInicial.value!=""){
					if(form.codigoSetorComercialInicial.value!=""){
						return true;
					}else{
						alert("Informe a Setor Comercial Inicial");
						return false;
					}
				}else{
					alert("Informe a Localidade Inicial");
					return false;
				}
			}else if(quadra==2){
				if(form.idLocalidadeFinal.value!=""){
					if(form.codigoSetorComercialFinal.value!=""){
						return true;
					}else{
						alert("Informe o Setor Comercial Final")
						return false;
					}
				}else{
					alert("Informe a Localidade Final");
					return false;
				}
			}
	  }
	 return false;
}

function limparImovel(){
	var form = document.forms[0];
	form.idImovel.value = "";
	form.descricaoImovel.value = "";
	form.descricaoImovel.style.color = "#000000";
	
	habilitaCampos();
}

function habilitaCampos() {
	var form = document.forms[0];
	
	if (form.idImovel != undefined && form.idImovel.value != "") {
		form.descricaoMunicipio.value = "";
		form.idMunicipio.value = "";
		form.idMunicipio.readOnly = true;
		form.idMunicipio.style.backgroundColor = '#EFEFEF';
		
		form.descricaoLogradouro.value = "";
		form.idLogradouro.value = "";
		form.idLogradouro.readOnly = true;
		form.idLogradouro.style.backgroundColor = '#EFEFEF';
		
		bloquearInscricaoInicialFinal();
	} else if (form.idMunicipio != undefined && form.idMunicipio.value != "") {
		form.descricaoImovel.value = "";
		form.idImovel.value = "";
		form.idImovel.readOnly = true;
		form.idImovel.style.backgroundColor = '#EFEFEF';

		form.idLogradouro.readOnly = false;
		form.idLogradouro.style.backgroundColor = '';

		bloquearInscricaoInicialFinal();
	} else if (form.idLocalidadeInicial != undefined && form.idLocalidadeInicial.value != "") {
		form.descricaoImovel.value = "";
		form.idImovel.value = "";
		form.idImovel.readOnly = true;
		form.idImovel.style.backgroundColor = '#EFEFEF';
		
		form.descricaoMunicipio.value = "";
		form.idMunicipio.value = "";
		form.idMunicipio.readOnly = true;
		form.idMunicipio.style.backgroundColor = '#EFEFEF';
		
		form.descricaoLogradouro.value = "";
		form.idLogradouro.value = "";
		form.idLogradouro.readOnly = true;
		form.idLogradouro.style.backgroundColor = '#EFEFEF';

		if (form.codigoSetorComercialInicial != undefined && form.codigoSetorComercialInicial.value != "") {
			if (form.quadraInicial != undefined && form.quadraInicial.value != "") {
				desbloquearRota();
			} else {
				desbloquearQuadra();
				bloquearRota();
			}
		} else {
			desbloquearSetorComercial();
			bloquearQuadra();
			bloquearRota();
		}
	} else {

		form.idImovel.readOnly = false;
		form.idImovel.style.backgroundColor = '';

		form.idMunicipio.readOnly = false;
		form.idMunicipio.style.backgroundColor = '';
		
		form.descricaoLogradouro.value = "";
		form.idLogradouro.value = "";
		form.idLogradouro.readOnly = true;
		form.idLogradouro.style.backgroundColor = '#EFEFEF';
		
		desbloquearInscricaoInicialFinal();
	}
}

function bloquearInscricaoInicialFinal() {
	var form = document.forms[0];

	form.descricaoLocalidadeInicial.value = "";
	form.idLocalidadeInicial.value = "";
	form.idLocalidadeInicial.readOnly = true;
	form.idLocalidadeInicial.style.backgroundColor = '#EFEFEF';
	
	form.descricaoLocalidadeFinal.value = "";
	form.idLocalidadeFinal.value = "";
	form.idLocalidadeFinal.readOnly = true;
	form.idLocalidadeFinal.style.backgroundColor = '#EFEFEF';
	
	bloquearSetorComercial();
	bloquearQuadra();
	bloquearRota();
}

function desbloquearInscricaoInicialFinal() {
	var form = document.forms[0];
	
	form.idLocalidadeInicial.readOnly = false;
	form.idLocalidadeInicial.style.backgroundColor = '';
	
	form.idLocalidadeFinal.readOnly = false;
	form.idLocalidadeFinal.style.backgroundColor = '';
	
	bloquearSetorComercial();
	bloquearQuadra();
	bloquearRota();
}

function bloquearSetorComercial() {
	var form = document.forms[0];
	
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.codigoSetorComercialInicial.readOnly = true;
	form.codigoSetorComercialInicial.style.backgroundColor = '#EFEFEF';
	
	form.descricaoSetorComercialFinal.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.codigoSetorComercialFinal.readOnly = true;
	form.codigoSetorComercialFinal.style.backgroundColor = '#EFEFEF';
}

function desbloquearSetorComercial() {
	var form = document.forms[0];

	form.codigoSetorComercialInicial.readOnly = false;
	form.codigoSetorComercialInicial.style.backgroundColor = '';
	
	form.codigoSetorComercialFinal.readOnly = false;
	form.codigoSetorComercialFinal.style.backgroundColor = '';
}

function bloquearQuadra() {
	var form = document.forms[0];

	form.quadraInicial.value = "";
	form.quadraInicial.readOnly = true;
	form.quadraInicial.style.backgroundColor = '#EFEFEF';

	form.quadraFinal.value = "";
	form.quadraFinal.readOnly = true;
	form.quadraFinal.style.backgroundColor = '#EFEFEF';
}

function desbloquearQuadra() {
	var form = document.forms[0];

	form.quadraInicial.readOnly = false;
	form.quadraInicial.style.backgroundColor = '';
	
	form.quadraFinal.readOnly = false;
	form.quadraFinal.style.backgroundColor = '';
}

function bloquearRota() {
	var form = document.forms[0];

	form.codigoRotaInicial.value = "";
	form.codigoRotaInicial.readOnly = true;
	form.codigoRotaInicial.style.backgroundColor = '#EFEFEF';

	form.sequencialRotaInicial.value = "";
	form.sequencialRotaInicial.readOnly = true;
	form.sequencialRotaInicial.style.backgroundColor = '#EFEFEF';

	form.codigoRotaFinal.value = "";
	form.codigoRotaFinal.readOnly = true;
	form.codigoRotaFinal.style.backgroundColor = '#EFEFEF';

	form.sequencialRotaFinal.value = "";
	form.sequencialRotaFinal.readOnly = true;
	form.sequencialRotaFinal.style.backgroundColor = '#EFEFEF';
}

function desbloquearRota() {
	var form = document.forms[0];

	form.codigoRotaInicial.readOnly = false;
	form.codigoRotaInicial.style.backgroundColor = '';
	
	form.sequencialRotaInicial.readOnly = false;
	form.sequencialRotaInicial.style.backgroundColor = '';

	form.codigoRotaFinal.readOnly = false;
	form.codigoRotaFinal.style.backgroundColor = '';
	
	form.sequencialRotaFinal.readOnly = false;
	form.sequencialRotaFinal.style.backgroundColor = '';
}

</script>
</head>
<body leftmargin="5" topmargin="5" onload="habilitaCampos();">
<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
<div id="formDiv">
<html:form action="/gerarRelatorioComandosConexaoEsgotoAction"
	name="GerarRelatorioComandosConexaoEsgotoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarRelatorioComandosConexaoEsgotoActionForm">
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">
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

			<td width="625" valign="top" class="centercoltext">
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
						<td class="parabg">Gerar Relatório de Comandos de Conexão de Esgoto</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para gerar o relatório de comandos de conexão de esgoto informe os dados abaixo:</td>
					</tr>
					<tr>
						<td><strong>Descrição Comando:</strong></td>
						<td>
							<html:text maxlength="40" 
             					   property="descricaoComando"
             					   size="50"  
             					   styleClass="tipoAlfaNumericoComEspaco"
             					   tabindex="1"/>
						</td>
					</tr>
					<tr>
						<td><strong>Execução:</strong></td>
               			<td>
	               			<html:radio property="indicadorExecucao" tabindex="2" 
	               				value="${GerarRelatorioComandosConexaoEsgotoActionForm.compesa}">Compesa</html:radio>
	               			<html:radio property="indicadorExecucao" tabindex="3"
	               				value="${GerarRelatorioComandosConexaoEsgotoActionForm.ppp}">PPP</html:radio>
	               		</td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>					
					<tr>
   						<td><strong>Matrícula do Imóvel:</strong></td>
            			<td colspan="3">
      						<html:text maxlength="9" property="idImovel" size="8"  tabindex="5" styleClass="tipoInteiro" onchange="habilitaCampos();"
    					   		 onkeypress="return validaEnter(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarImovel', 'idImovel');"/>
               				<a id="buscarImovel" href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 400, 800, '', document.forms[0].idImovel);"><img border="0"
              					 src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Imóvel"/></a>
							<logic:present name="imovelInexistenteException">
                   				<html:text property="descricaoImovel" 
	                   				size="30" 
	                   				readonly="true" 
									style="background-color:#EFEFEF; border:0; color: red"/>
               				</logic:present>
							<logic:notPresent name="imovelInexistenteException">
               					<html:text property="descricaoImovel" 
	               					size="30" 
	               					readonly="true" 
	               					styleClass="campoBloqueado"
									style="background-color:#EFEFEF; border:0; color: #000000" />
               				</logic:notPresent>	 
                   			<a id="apagarImovel" href="javaScript:limparImovel()"><img 
                   				src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Imóvel" /></a>	             		                   
						</td>
	                </tr>
					<tr>
						<td><strong>Município:</strong></td>
						<td>
							<html:text property="idMunicipio" size="8" maxlength="5" tabindex="6" styleClass="tipoInteiro" onchange="habilitaCampos();"
								onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarMunicipio','idMunicipio','Município');return isCampoNumerico(event);"/>
							<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '',document.forms[0].idMunicipio);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Município" /></a>
								
							<logic:present name="municipioInexistenteException">
								<html:text property="descricaoMunicipio" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="municipioInexistenteException">
								<html:text property="descricaoMunicipio" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Município" /></a>
		        		</td>
					</tr>
					<tr>
						<td><strong>Logradouro:</strong></td>
						<td>
							<html:text property="idLogradouro" size="8" maxlength="9" styleClass="tipoInteiro" onchange="habilitaCampos();" tabindex="7"
								onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarLogradouro','idLogradouro','Logradouro');return isCampoNumerico(event);"/>
							<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do', 'logradouro', null, null, 275, 480, '',document.forms[0].idLogradouro);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Logradouro" /></a>
							
							<logic:present name="logradouroInexistente">
								<html:text property="descricaoLogradouro" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="logradouroInexistente">
								<html:text property="descricaoLogradouro" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Logradouro" /></a>
		        		</td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr bgcolor="#90c7fc">
						<td colspan="2"><strong>Inscrição Inicial</strong></td>
					</tr>
					<tr>
						<td><strong>Localidade Inicial:</strong></td>
						<td>
							<html:text property="idLocalidadeInicial" size="8" maxlength="3" tabindex="8" styleClass="tipoInteiro"
								onblur="replicarLocalidade();" onchange="habilitaCampos();"
								onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarLocalidadeInicial','idLocalidadeInicial','Localidade Incial');return isCampoNumerico(event);"/>
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
							
							<logic:present name="localidadeInicialInexistente">
								<html:text property="descricaoLocalidadeInicial" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="localidadeInicialInexistente">
								<html:text property="descricaoLocalidadeInicial" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(3);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Localidade" /></a>
						</td>
					</tr>
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td>
							<html:text property="codigoSetorComercialInicial" size="8" maxlength="3" tabindex="9" styleClass="tipoInteiro"
								onblur="replicarSetorComercial();" onchange="habilitaCampos();"
								onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarSetorComercialInicial','codigoSetorComercialInicial','Localidade Incial');return isCampoNumerico(event);"/>
							<a href="javascript:pesquisarSetorComercialInicial();">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
							
							<logic:present name="setorComercialInicialInexistente">
								<html:text property="descricaoSetorComercialInicial" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="setorComercialInicialInexistente">
								<html:text property="descricaoSetorComercialInicial" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(5);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Setor Comercial" /></a>
						</td>
					</tr>
					<tr>
						<td><strong>Quadra Inicial:</strong></td>
						<td>
							<html:text
								property="quadraInicial"
								size="8"
								maxlength="4"
								tabindex="10"
								styleClass="tipoInteiro"
								onblur="replicarQuadra();" 
								onchange="habilitaCampos();"
								onkeypress="javascript:replicarQuadra();validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarQuadraInicial','quadraInicial','Quadra Incial');return isCampoNumerico(event);"/>
								
							<logic:present name="msgQuadraInicial" scope="request">
								<span style="color:#ff0000" id="msgQuadraInicial">
									<bean:write scope="request" name="msgQuadraInicial" />
								</span>
							</logic:present> 
						</td>
						<tr>
						<td><strong>Rota Inicial:</strong></td>
						<td>
							<html:text 
								property="codigoRotaInicial"
								size="8"
								maxlength="5"
								tabindex="11"
								styleClass="tipoInteiro"
								onblur="replicarRota();"
								onkeypress="return isCampoNumerico(event);"/>
							&nbsp;
							seq.:
							&nbsp;
							<html:text 
								property="sequencialRotaInicial"
								size="6"
								maxlength="5"
								tabindex="12"
								styleClass="tipoInteiro"
								onblur="replicarRotaSequencial();"
								onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr bgcolor="#90c7fc">
						<td colspan="2"><strong>Inscrição Final</strong></td>
					</tr>
					<tr>
						<td><strong>Localidade Final:</strong></td>
						<td>
							<html:text property="idLocalidadeFinal" size="8" maxlength="3" tabindex="13" styleClass="tipoInteiro" onchange="habilitaCampos();"
								onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarLocalidadeFinal','idLocalidadeFinal','Localidade Final');return isCampoNumerico(event);"/>
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
							
							<logic:present name="localidadeFinalInexistente">
								<html:text property="descricaoLocalidadeFinal" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="localidadeFinalInexistente">
								<html:text property="descricaoLocalidadeFinal" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(4);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Localidade" /></a>
						</td>
					</tr>
					<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td>
							<html:text property="codigoSetorComercialFinal" size="8" maxlength="3" tabindex="14" styleClass="tipoInteiro" onchange="habilitaCampos();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarSetorComercialFinal','codigoSetorComercialFinal','Setor Comercial Final');return isCampoNumerico(event);"/>
							<a href="javascript:pesquisarSetorComercialFinal();">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
							
							<logic:present name="setorComercialFinalInexistente">
								<html:text property="descricaoSetorComercialFinal" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" 
									size="30"
									maxlength="30" />
							</logic:present>
							<logic:notPresent name="setorComercialFinalInexistente">
								<html:text property="descricaoSetorComercialFinal" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" 
									size="30"
									maxlength="30" />
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(6);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Setor Comercial" /></a>
						</td>
					</tr>
					<tr>
						<td><strong>Quadra Final:</strong></td>
						<td>
							<html:text 
							property="quadraFinal"
							size="8"
							maxlength="4"
							tabindex="15"
							styleClass="tipoInteiro"
							onchange="habilitaCampos();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioComandosConexaoEsgotoAction.do?objetoConsulta=pesquisarQuadraFinal','quadraFinal','Quadra Final');return isCampoNumerico(event);"/>
						
							<logic:present name="msgQuadraFinal" scope="request">
								<span style="color:#ff0000" id="msgQuadraFinal">
									<bean:write scope="request" name="msgQuadraFinal" />
								</span>
							</logic:present> 
						</td>
					</tr>
					<tr>
						<td><strong>Rota Final:</strong></td>
						<td>
							<html:text 
							property="codigoRotaFinal"
							size="8"
							maxlength="5"
							tabindex="16"
							styleClass="tipoInteiro"
							onkeypress="return isCampoNumerico(event);"/>
							&nbsp;
							seq.:
							&nbsp;
							<html:text 
							property="sequencialRotaFinal"
							size="6"
							maxlength="5"
							tabindex="17"
							styleClass="tipoInteiro"
							onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>	
					<tr>
						<td><strong>Grupo de Faturamento:</strong></td>
						<td>
							<html:select property="idFaturamentoGrupo" tabindex="4">
								<html:option value="${ConstantesSistema.NUMERO_NAO_INFORMADO}">&nbsp;</html:option>
								<logic:notEmpty name="colecaoFaturamentoGrupo">
									<html:options collection="colecaoFaturamentoGrupo"
										property="id"
										labelProperty="descricao" />
								</logic:notEmpty>	
							</html:select>
						</td>
					</tr>	
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td><strong>Situação da Ordem de Serviço:</strong></td>
						<td>
							<html:select property="idSituacaoOrdemServico" tabindex="18">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
	
								<html:option value="<%=""+OrdemServico.SITUACAO_PENDENTE%>">PENDENTES</html:option>
	
								<html:option value="<%=""+OrdemServico.SITUACAO_ENCERRADO%>">ENCERRADOS</html:option>
							</html:select> 				
						</td>
					</tr>
					<tr>
						<td><strong>Período da Geração:</strong></td>
						<td>
							<html:text
								property="dataGeracaoInicial"
								size="11" 
								maxlength="10" 
								tabindex="19" 
								styleClass="tipoDataInicial"
								onkeyup="javascript:replicarCampo( document.forms[0].dataGeracaoFinal, document.forms[0].dataGeracaoInicial );mascaraData(this, event);"
								onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioComandosConexaoEsgotoActionForm', 'dataGeracaoInicial', 'dataGeracaoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário"/></a>
							&nbsp;
							<strong>a</strong>
							<html:text 
								property="dataGeracaoFinal"
								size="11" 
								maxlength="10" 
								tabindex="20"
								styleClass="tipoDataFinal"
								onkeyup="mascaraData(this, event);"
								onkeypress="return isCampoNumerico(event);replicarPeriodo();"/>
							<a href="javascript:abrirCalendario('GerarRelatorioComandosConexaoEsgotoActionForm', 'dataGeracaoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário"/></a>
						<strong>(dd/mm/aaaa)</strong>
						</td>
					</tr>
					<tr>
						<td><strong>Tipo de Relatório:</strong><font color="red">*</font></td>
						<td>
							<html:radio property="tipoRelatorioSinteticoAnalitico" value="1" tabindex="21">Sintético</html:radio>
	               			<html:radio property="tipoRelatorioSinteticoAnalitico" value="2" tabindex="22">Analítico</html:radio>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><font color="red">*</font>Campos Obrigatórios</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td align="left">
							<input type="button" value="Limpar" class="bottonRightCol" 
								onClick="javascript:window.location.href='/gsan/exibirGerarRelatorioComandosConexaoEsgotoAction.do?menu=sim'">
							<input type="button" value="Cancelar" class="bottonRightCol" 
								onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right"><input type="button" value="Gerar" class="bottonRightCol" onclick="toggleBox('demodiv',1);"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioComandosConexaoEsgotoAction.do" />	
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
	</div>
	</body>
</html:html>