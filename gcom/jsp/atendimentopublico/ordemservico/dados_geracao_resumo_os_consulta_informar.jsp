<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.localidade.GerenciaRegional" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InformarDadosGeracaoResumoOrdemServicoConsultaActionForm"
	dynamicJavascript="false" />
<SCRIPT LANGUAGE="JavaScript">
<!--

     var bCancel = false; 

    function validateInformarDadosGeracaoResumoOrdemServicoConsultaActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateRequired(form) && validateLong(form) && validateMesAno(form); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("mesAnoReferencia", "Mês/Ano de Referência possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("eloPolo", "Elo Pólo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("localidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("setorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function required () { 
     this.aa = new Array("mesAnoReferencia", "Informe Mês/Ano de Referencia.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("eloPolo", "Elo Pólo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("localidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("setorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
    
    function MesAnoValidations () {
     this.aa = new Array("mesAnoReferencia", "Mês/Ano de Referência inválido.", new Function ("varName", " return this[varName];"));
    }

	function validarForm(form){
		if (validateInformarDadosGeracaoResumoOrdemServicoConsultaActionForm(form)){
			submeterFormPadrao(form);
		}
	}

function limparEloPolo(){
	var form = document.forms[0];
	
	form.eloPolo.disabled = false;
	
	form.eloPolo.value = "";
	form.descricaoEloPolo.value = "";
}

function limparLocalidade(){
	var form = document.forms[0];
	
	form.localidade.disabled = false;
	
	form.localidade.value = "";
	form.descricaoLocalidade.value = "";
	
}

function limparSetorComercial(){
	var form = document.forms[0];
	
	form.setorComercial.disabled = false;
	
	form.setorComercial.value = "";
	form.idSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";
	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

	if (tipoConsulta == 'elo') {
    	limparEloPolo();
      	form.eloPolo.value = codigoRegistro;
      	form.descricaoEloPolo.value = descricaoRegistro;
      	form.descricaoEloPolo.style.color = "#000000";
    
    	if (form.localidade.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.localidade.focus();
	  	}
    }
    
    if (tipoConsulta == 'localidade') {
    	limparLocalidade();
      	form.localidade.value = codigoRegistro;
      	form.descricaoLocalidade.value = descricaoRegistro;
      	form.descricaoLocalidade.style.color = "#000000";
    
    	if (form.setorComercial.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.setorComercial.focus();
	  	}
    }

    if (tipoConsulta == 'setorComercial') {
      	limparSetorComercial();
      	form.setorComercial.value = codigoRegistro;
      	form.descricaoSetorComercial.value = descricaoRegistro;
      	form.descricaoSetorComercial.style.color = "#000000";
    
    	form.perfilImovel.focus();
    }
    
}

function gerenciadorHabilitacaoImagemPesquisa(tipoPesquisa){

	switch (tipoPesquisa) { 
    case "EloPolo":
    
    	 abrirPopup('exibirPesquisarEloAction.do', 320, 810);
		 
        
       break; 
    case "Localidade": 
        
         abrirPopup('exibirPesquisarLocalidadeAction.do', 320, 810);
		
       break; 
    case "SetorComercial": 
        
        	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial',document.forms[0].localidade.value,'Localidade', 400, 800);
			
		
		
       break;
    
    default:
    
    }
}


function gerenciadorHabilitacaoImagemLimpar(tipoPesquisa){

	switch (tipoPesquisa) { 
    case "EloPolo":
    
    		limparEloPolo();
		 
        
       break; 
    case "Localidade": 
        
        	limparLocalidade();
        	limparSetorComercial();
        	
		
		
       break; 
    case "SetorComercial": 
        
        	limparSetorComercial();
			
		
       break;
    
    default:
    
    }
}



//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); ">

<html:form action="/informarDadosGeracaoResumoOrdemServicoConsultaAction"
	 name="InformarDadosGeracaoResumoOrdemServicoConsultaActionForm"
	 type="gcom.gui.atendimentopublico.ordemservico.InformarDadosGeracaoResumoOrdemServicoConsultaActionForm"
	 method="post"
	 onsubmit="return validateInformarDadosGeracaoResumoOrdemServicoConsultaActionForm(this);" >

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Resumo das Ações de Ordem de Serviço</td>
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para gerar o resumo das ordens de serviço, informe os dados abaixo:</td>
      </tr>
	  <tr>
      	<td HEIGHT="30"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:text property="mesAnoReferencia" size="8" maxlength="7" tabindex="1" onkeypress="return isCampoNumerico(event);" onkeyup="mascaraAnoMes(this, event);"/>&nbsp;MM/AAAA
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Tipo de Serviço:</strong></td>
        <td>
			<html:select property="servicoTipo" style="width: 200px;height: 80px" tabindex="15" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoServicoTipo" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Gerência Regional:</strong></td>
        <td>
        
        <html:select property="gerencialRegional" style="width: 200px;height: 80px" tabindex="15" multiple="true">
			<html:option value="-1">&nbsp;</html:option>
			<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" type="GerenciaRegional">
				<html:option value="<%=""+ gerenciaRegional.getId()%>">
				<%= gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome()%></html:option>
			</logic:iterate>
		</html:select>
		</td>
      </tr>
      
		<tr>
           <td><strong> <font color="#FF0000"></font></strong><strong> <strong>Unidade de Neg&oacute;cio: </strong></strong></td>
           <td>
       	   	  <logic:present name="colecaoUnidadeNegocio">  
           	  	<html:select property="unidadeNegocio" style="width: 200px;height: 80px" tabindex="7" multiple="true" >
   					<html:option value="-1">&nbsp;</html:option>
					<logic:iterate name="colecaoUnidadeNegocio" id="unidadeNegocio" type="UnidadeNegocio" >
	 					<html:option value="<%=""+ unidadeNegocio.getId()%>">
            			<%= unidadeNegocio.getNomeAbreviado() + " - " + unidadeNegocio.getNome()%></html:option>
 					</logic:iterate>
				</html:select>
         	</logic:present>
			</td>
		</tr>
      
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade Pólo:</strong></td>
        <td width="432">
        	
        	<html:text property="eloPolo" size="4" maxlength="3" tabindex="5" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoResumoOSConsultaAction.do?pesquisarEloPolo=OK', 'eloPolo', 'Elo Pólo');return isCampoNumerico(event);"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('EloPolo')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:notPresent name="eloPoloInexistente" scope="request">
				<html:text property="descricaoEloPolo" size="35" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notPresent> 

			<logic:present name="eloPoloInexistente" scope="request">
				<html:text property="descricaoEloPolo" size="35" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #FF0000" />
			</logic:present>
			
			<a href="javascript:gerenciadorHabilitacaoImagemLimpar('EloPolo')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade:</strong></td>
        <td width="432">
        	
        	<html:text property="localidade" size="4" maxlength="3" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoResumoOSConsultaAction.do?pesquisarLocalidade=OK', 'localidade', 'Localidade');return isCampoNumerico(event);"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('Localidade')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:notPresent name="localidadeInexistente" scope="request">
				<html:text property="descricaoLocalidade" size="35" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notPresent>
			
			<logic:present name="localidadeInexistente" scope="request">
				<html:text property="descricaoLocalidade" size="35" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #FF0000" />
			</logic:present>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar('Localidade')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Setor Comercial:</strong></td>
        <td width="432">
        	
        	<html:hidden property="idSetorComercial"/>
        	<html:text property="setorComercial" size="4" maxlength="3" tabindex="7" onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarDadosGeracaoResumoOSConsultaAction.do?pesquisarSetorComercial=OK', this, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial');return isCampoNumerico(event);"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('SetorComercial')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>
			
			<logic:notPresent name="setorComercialInexistente" scope="request">
				<html:text property="descricaoSetorComercial" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
			</logic:notPresent>
			
			<logic:present name="setorComercialInexistente" scope="request">
				<html:text property="descricaoSetorComercial" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			</logic:present>
			
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar('SetorComercial')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Perfil do Imóvel:</strong></td>
        <td>
			<html:select property="perfilImovel" style="width: 200px;height: 80px" tabindex="9" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoImovelPerfil" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Água:</strong></td>
        <td>
			<html:select property="situacaoLigacaoAgua" style="width: 200px;height: 80px" tabindex="11" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Esgoto:</strong></td>
        <td>
			<html:select property="situacaoLigacaoEsgoto" style="width: 200px;height: 80px" tabindex="12" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoEsgotoSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Categoria:</strong></td>
        <td>
			<html:select property="categoria" style="width: 200px;height: 80px" tabindex="13" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoCategoria" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Esfera de Poder:</strong></td>
        <td>
			<html:select property="esferaPoder" style="width: 200px;height: 80px" tabindex="14" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Empresa Unidade Atual:</strong></td>
        <td>
			<html:select property="empresa" style="width: 200px;height: 80px" tabindex="15" multiple="true">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      
      <tr>
      	<td></td>
      	<td><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
      </tr>
      <tr>
      	<td colspan="2">&nbsp;</td>
      </tr>
      <tr>
      	<td align="left" colspan="2" >
      		<input type="button" onclick="window.location.href='exibirInformarDadosGeracaoResumoOSConsultaAction.do?menu=sim'" style="width: 70px;" name="botaoLimpar" class="bottonRightCol" value="Limpar" tabindex="15">
      		<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
      	</td>
      	<td align="right">
      		<input type="button" onclick="validarForm(document.forms[0]);" name="botaoGerar" class="bottonRightCol" value="Gerar" tabindex="16">
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
</html:html>
