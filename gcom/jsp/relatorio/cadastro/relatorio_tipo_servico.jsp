 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cadastro.unidade.UnidadeOrganizacional"%>
<%@page import="gcom.seguranca.acesso.usuario.Usuario"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioResumoArrecadacaoActionForm" />
<script>

function gerarRelatorio(){
	var form = document.forms[0];
	var erros = "";
	
	if (form.tipoRelatorioEscolhido.value == null || form.tipoRelatorioEscolhido.value == '' || form.tipoRelatorioEscolhido.value == '-1') {
		erros += "Informe Tipo de Relatório. \n";
	} 
	
	if(form.idServico.value == ''|| form.idServico.value == '') {
		erros += "Informe Tipo de Serviço. \n";
	} 
	
	if(form.dataInicial.value == '') {
		erros += "Informe Data Inicial. \n"; 
	}else{
		
		if (!validaDataSemMensagem(form.dataInicial)) {
			erros += "Data Inicial do Período Inválida.\n";
		} 
	}
	
	if(form.dataFinal.value == '') {
		erros += "Informe Data Final.\n";
	} else{
		
		if (!validaDataSemMensagem(form.dataFinal)) {
			erros += "Data Final do Período Inválida.\n";
		}
	}
	
	 
	
	if(form.dataFinal.value != '' && form.dataInicial.value != '' ){
		if (!comparaData(form.dataInicial.value, "<=", form.dataFinal.value)) {
			erros += "Data final do período é anterior à data inicial do período. \n"; 
		} 
	}
	
	if (form.tipoRelatorioEscolhido.value == 2) {
		
		if(!verificaOpcaoTotalizacao()){
			erros += "Informe Opção de Totalização. \n";
		}else{
			
			//Gerencia Regional
			if(form.opcaoTotalizacao[4].checked == true){
				if(form.idGerenciaRegional.value == null || form.idGerenciaRegional.value == '-1'
						|| form.idGerenciaRegional.value == ''){
					
					erros += "Informe gerência regional para opção de totalização selecionada. \n";
				}
			}
			
			//Unidade de negocio
			if(form.opcaoTotalizacao[6].checked == true){
				if(form.idUnidadeNegocio.value == null || form.idUnidadeNegocio.value == '-1'
						|| form.idUnidadeNegocio.value == ''){
					
					erros += "Informe unidade de negócio para opção de totalização selecionada. \n";
				}
			}
			
			//Localidade
			if(form.opcaoTotalizacao[7].checked == true){
				if(form.idLocalidade.value == null || form.idLocalidade.value == '-1'
						|| form.idLocalidade.value == ''){
					
					erros += "Informe localidade para opção de totalização selecionada. \n";
				}
			}
			
			
			
		}
		
		
		
	} 
	
	
	
	if(erros != ""){
		alert(erros);
	}else{
		
		form.idUnidadeNegocio.disabled = false;
		form.idGerenciaRegionalporLocalidade.disabled = false;
		form.idGerenciaRegional.disabled = false;
		form.idLocalidade.disabled = false;
		
		form.action='/gsan/gerarRelatorioTipoServicoAction.do?gerarRelatorio=PDF';
	    form.submit();
	    
	    if(form.opcaoTotalizacao[4].checked == false){
			form.idGerenciaRegional.disabled = true;
	    }
		
		if(form.opcaoTotalizacao[5].checked == false){
			form.idGerenciaRegionalporLocalidade.disabled = true;
		}
		
		if(form.opcaoTotalizacao[6].checked == false){
			form.idUnidadeNegocio.disabled = true;
		}
		
		if(form.opcaoTotalizacao[7].checked == false){
			form.idLocalidade.disabled = true;
		}
	}
	
}

function verificaOpcaoTotalizacao(){
	
	var form = document.forms[0];
	var buttonGroup  = form.opcaoTotalizacao;
	var retorno = false;
			
	for (i=0; i < buttonGroup.length; i++) {
	  
	  if(buttonGroup[i].checked == true){
	    retorno =  true;
	  }

	}

	return retorno;
}

function selecionarFiltro(tipo){
	var form = document.forms[0];
	if(tipo==1){
		form.tipoRelatorioEscolhido.value = 1;
		document.getElementById('tipoUsuario').style.display = 'block';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}else if(tipo==2){
		form.tipoRelatorioEscolhido.value = 2;
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'block';
		
		verificarOpcaoTotalizacao();
		
		if(verificaLocalidade()){
			form.idLocalidade.disabled = false;
		}
		
	}else if(tipo==3){
		form.tipoRelatorioEscolhido.value = 3;
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}
}

function verificaSelecao(){
	var form = document.forms[0];
	var campo = "${requestScope.nomeCampo}";
	
	if(form.tipoRelatorioEscolhido.value==1){
		document.getElementById('tipoUsuario').style.display = 'block';
		document.getElementById('tipoLocalidade').style.display = 'none';
		
					
		
	}else if(form.tipoRelatorioEscolhido.value==2){
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'block';
	
		verificarOpcaoTotalizacao();
		
		if(verificaLocalidade()){
			form.idLocalidade.disabled = false;
		}
		
		
		
	}else if(form.tipoRelatorioEscolhido.value==3){
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}
	
	setarFoco(campo);
}

function validarExibicaoUnidade(){

	var form = document.forms[0];
	
	if(form.idUnidadeSuperior.value!=''){
		form.idUnidadeOrganizacional.value = "";
		form.descUnidadeOrganizacional.value = "";
		form.idUnidadeOrganizacional.disabled = true;
	}else{
		form.idUnidadeOrganizacional.disabled = false;
	}
		
	if(form.idUnidadeOrganizacional.value != ''){
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
		form.idUnidadeSuperior.disabled = true;
	}else{
		form.idUnidadeSuperior.disabled = false;
	}
	
	
	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function limpar(tipo) {

	var form = document.forms[0];
	
	
	if (tipo == 'unidadeOrganizacional') {
		form.idUnidadeOrganizacional.value = "";
		form.descUnidadeOrganizacional.value = "";
	}

	if (tipo == 'unidadeSuperior') {
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
	}
	
	if (tipo == 'usuario') {
		form.idUsuario.value = "";
		form.descUsuario.value = "";
	}
	
	if (tipo == 'localidade') {
		form.idLocalidade.value = "";
		form.descLocalidade.value = "";
	}

	validarExibicaoUnidade();

}


function limparForm(){
	limparUnidadeOrganizacional();
	limparUsuarioResponsavel();
	limparUnidadeSuperior();
	limparMeio();
	limparData();
	limparServico();
	limparGerenciaRegionalLocalidade();
	limparGerenciaRegional();
	limparUnidadeNegocio();
	limparTipoRelatorio();
	limpar('localidade');
	var form = document.forms[0];
	form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do?limpar=sim';
    form.submit();
}

function limparMeio(){

	var selects = document.getElementsByName("meio");
	for (var i=0; i<selects.length; i++){
		selects.item(i).selectedIndex = false;
		selects.item(i).selected = false;
	}
	
}


function limparTipoRelatorio(){

	var form = document.forms[0];
	var buttonGroup  = form.opcaoTipoRelatorio;
		
	for (i=0; i < buttonGroup.length; i++) {
		buttonGroup[i].checked = false;
	} 
	
}



function limparServico(){

	var selects = document.getElementsByName("idServico");
	for (var i=0; i<selects.length; i++){
		selects.item(i).selectedIndex = false;
		selects.item(i).selected = false;
	}
	
}

function limparGerenciaRegional(){
	var selects = document.getElementsByName("idGerenciaRegional");
	for (var i=0; i<selects.length; i++){
		selects.item(i).selectedIndex = false;
		selects.item(i).selected = false;
	}
	
}


function limparGerenciaRegionalLocalidade(){
	var selects = document.getElementsByName("idGerenciaRegionalporLocalidade");
	for (var i=0; i<selects.length; i++){
		selects.item(i).selectedIndex = false;
		selects.item(i).selected = false;
	}
	
}

function limparUnidadeNegocio(){
	var selects = document.getElementsByName("idUnidadeNegocio");
	for (var i=0; i<selects.length; i++){
		selects.item(i).selectedIndex = false;
		selects.item(i).selected = false;
	}

}


function limparUnidadeOrganizacional() {
	var form = document.forms[0];
	form.idUnidadeOrganizacional.value = "";
	form.descUnidadeOrganizacional.value = "";	
}

function limparUsuarioResponsavel() {
	var form = document.forms[0];
	form.idUsuario.value = "";
	form.descUsuario.value = "";
}

function limparUnidadeSuperior() {
	var form = document.forms[0];
	form.idUnidadeSuperior.value = "";
	form.descUnidadeSuperior.value = "";
}

function limparData() {
	var form = document.forms[0];
	form.dataInicial.value="";
	form.dataFinal.value="";
}




function abrirPopupValidando(caminho, altura, largura){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.disabled==false){
		abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
	}	
}

function abrirPopupValidandoLocalidade(caminho, altura, largura){
	var form = document.forms[0];

	if(form.idLocalidade.disabled==false){
		abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
	}
}



function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'unidadeOrganizacional') {

    	//form.descUnidadeOrganizacional.style.color = "#000000";
    	form.idUnidadeOrganizacional.value = codigoRegistro;
    	form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do';
    	form.submit();
    	//form.descUnidadeOrganizacional.value = descricaoRegistro;
    	
	}

    if (tipoConsulta == 'unidadeSuperior') {

      	form.idUnidadeSuperior.value = codigoRegistro;
      	form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do';
      	form.submit();
      	//form.descUnidadeSuperior.value = descricaoRegistro;
		//form.descUnidadeSuperior.style.color = "#000000";
		

    }

    if (tipoConsulta == 'usuario') {
    	form.descUsuario.style.color = "#000000";
  		form.idUsuario.value = codigoRegistro;
   		form.descUsuario.value = descricaoRegistro;
	}
	
    if (tipoConsulta == 'localidade') {
    	form.descLocalidade.style.color = "#000000";
  		form.idLocalidade.value = codigoRegistro;
   		form.descLocalidade.value = descricaoRegistro;
   		selecionarOpcao(7);
   		limparCampos(form.opcaoTotalizacao[7]);
   	}

    validarExibicaoUnidade();
} 

function adicionarUnidadeOrganizacional(){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.value!=""){
		form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do?adicionarUnidadeOrganizacional=OK';
        form.submit();
	}else{
		alert("Informe a Unidade Organizacional para adicionar ao filtro.");
	}
}

function adicionarUsuario(){
	var form = document.forms[0];

	if(form.idUsuario.value!=""){
		form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do?adicionarUsuario=OK';
        form.submit();
	}else{
		alert("Informe o Usuário para adicionar ao filtro.");
	}
}
function verificarColUnidadeOrganizacional(flagColecaoVazia){

	var form = document.forms[0];

	if(flagColecaoVazia=='nao'){
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
		form.idUnidadeSuperior.disabled = true;
	}else{
		validarExibicaoUnidade();	
	}
}

function removerUnidade(count) {
	if(confirm('Confirma exclusão da unidade organizacional?')) {
		redirecionarSubmitSemUpperCase('/gsan/exibirGerarRelatorioTipoServicoAction.do?removerUnidadeOrganizacional=OK&idRegistro='+count);
	}
}

function removerUsuario(count) {
	if(confirm('Confirma exclusão do usuário?')) {
		redirecionarSubmitSemUpperCase('/gsan/exibirGerarRelatorioTipoServicoAction.do?removerUsuario=OK&idRegistro='+count);
	}
}

function replicaData() {
		var form = document.forms[0];
		form.dataFinal.value = form.dataInicial.value;
}
	
function limparCampos(opcaoTotalizacao){
	
	var form = document.forms[0];
	
	
	if (opcaoTotalizacao.value != 'localidade'){
		limpar('localidade') ;
		form.idLocalidade.disabled = true;
   	}else{
   		form.idLocalidade.disabled = false;
	}
	
	if (opcaoTotalizacao.value != 'gerenciaRegional'){
		form.idGerenciaRegional.value = "-1";
		form.idGerenciaRegional.disabled=true;
	} else {
		form.idGerenciaRegional.disabled=false;
	}	
	
	if (opcaoTotalizacao.value != 'gerenciaRegionalLocalidade'){
		form.idGerenciaRegionalporLocalidade.value = "-1";
		form.idGerenciaRegionalporLocalidade.disabled=true;
	} else {
		form.idGerenciaRegionalporLocalidade.disabled=false;
	}	
	
	if (opcaoTotalizacao.value != 'unidadeNegocio'){
		form.idUnidadeNegocio.value = "-1";
		form.idUnidadeNegocio.disabled=true;
	} else {
		form.idUnidadeNegocio.disabled=false;
	}	
	
}

function selecionarOpcao(posicao){
	var opcaoTot = document.forms[0].opcaoTotalizacao;
	
	for (i = 0; i < opcaoTot.length; i++) {
		if (i != posicao) {
			opcaoTot[i].checked = false;
		}
	}
	opcaoTot[posicao].checked = true;
}

function verificaLocalidade(){
	var form = document.forms[0];
	var opcaoTot = form.opcaoTotalizacao[7];	
	var retorno = false;
	
	
	if (form.tipoRelatorioEscolhido.value == 2 && opcaoTot.checked == true){
   		retorno = true;
	}
	
	return retorno;
}

function marcarMeioSolicitacao(){
	var form = document.forms[0];
	form.action='/gsan/exibirGerarRelatorioTipoServicoAction.do?CarregarMeioSolicitacao=OK';
    form.submit();
    
	
}


function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //localidade 
			form.descLocalidade.value = "";
			break;
		  	
		case 2: //usario
			form.descUsuario.value = "";
			break;
		
		case 3: //unidade superiro
			form.descUnidadeSuperior.value = "";
			break;
			
		case 4: //unidade organizacional
			form.descUnidadeOrganizacional.value = "";
			break;
			
		}
	}
	
function verificarOpcaoTotalizacao(){
	
	var form = document.forms[0];
	
	
	if(form.opcaoTotalizacao[4].checked == true){
		form.idGerenciaRegional.disabled = false;
	}
	
	if(form.opcaoTotalizacao[5].checked == true){
		form.idGerenciaRegionalporLocalidade.disabled = false;
	}
	
	if(form.opcaoTotalizacao[6].checked == true){
		form.idUnidadeNegocio.disabled = false;
	}
	
}
	

</script>


</head>

<body leftmargin="5" topmargin="5" onload=" verificaSelecao();validarExibicaoUnidade();verificarColUnidadeOrganizacional('${requestScope.colecaoUnidadeVazia}');">
<html:form action="/exibirGerarRelatorioTipoServicoAction.do"
	name="GerarRelatorioTipoServicoActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioTipoServicoActionForm"
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
			<html:hidden property="tipoRelatorioEscolhido"/>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relatório por tipo de serviço</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório de alterações de serviços, informe os dados abaixo:</td>
				</tr>				
				<tr>
					<td width="18%"><strong>Op&ccedil;&atilde;o de Tipo de Relatório:<font color="#FF0000">*</font></strong></td>
					<td colspan="1">
						<html:radio property="opcaoTipoRelatorio"
						    value="1" 
							onclick="selecionarFiltro(this.value);"/>
							Usuário
						<html:radio property="opcaoTipoRelatorio"
						    value="2" 
							onclick="selecionarFiltro(this.value);"/>
							Localidade
						<html:radio property="opcaoTipoRelatorio"
						    value="3" 
							onclick="selecionarFiltro(this.value);"/>
							Meio de Solicitação
							</td>
				</tr>
				
				<tr>
					<td colspan="1"><strong>Serviço: <font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:select property="idServico" onchange="marcarMeioSolicitacao()">
							<html:option value=""></html:option>
							<html:option value="1">Parcelamento</html:option>
							<html:option value="2">Segunda via de conta</html:option>
							<html:option value="3">Extrato de débito</html:option>
							<html:option value="4">Averbação</html:option>
							<html:option value="5">Revisão de consumo</html:option>
						
						</html:select> 
						
						</td>
				</tr>
					
				<tr>
					<td>
						<strong>Período: <font color="#FF0000">*</font></strong>
					</td>
          				    <td colspan="6">
						<html:text property="dataInicial" size="9" maxlength="10" tabindex="2" 
							onkeyup="mascaraData(this, event);replicaData(document.forms[0].dataInicial,document.forms[0].dataFinal);" onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioTipoServicoActionForm', 'dataInicial', 'dataFinal' );">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" />
						</a> a 
						<html:text property="dataFinal" size="9" maxlength="10" tabindex="3" 
							onkeyup="mascaraData(this, event)" onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendario('GerarRelatorioTipoServicoActionForm', 'dataFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" />
						</a> dd/mm/aaaa
				</tr>
				
				
				
				
				<tr>
					<td width="30%"><strong>Meio de Solicitação: </strong></td>
					<td width="70%"><html:select multiple="true" size="3"
						name="GerarRelatorioTipoServicoActionForm" property="meio">
						<option value="">&nbsp;</option>
						<logic:notEmpty name="colecaoMeiosSolicitacao">
							<html:options name="request" collection="colecaoMeiosSolicitacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<div id="tipoUsuario" style="display:none;">
							<table border="0" width="100%">
							<tr>
								<td>
									<strong>
										Unidade Superior:
									</strong>
								</td>					
								<td colspan="6">
									<html:text property="idUnidadeSuperior"
										size="5" maxlength="5" tabindex="6" 
										onkeyup="validarExibicaoUnidade(); limparOrigem(3)"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioTipoServicoAction.do', 'idUnidadeSuperior'); return isCampoNumerico(event);" />
							
									<a href="javascript:chamarPopup('exibirPesquisarUnidadeSuperiorAction.do?tipoUnidade=unidadeSuperior', 'unidadeSuperior', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
										<img width="23" height="21" border="0" 
											src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Superior" style="cursor:hand;"/></a> 
									
									<logic:present name="unidadeSuperiorEncontrada" scope="session">
										<html:text property="descUnidadeSuperior" readonly="true"
											style="background-color:#EFEFEF; border:0" size="50"
											maxlength="40" />
									</logic:present>
				
									<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
										<html:text property="descUnidadeSuperior" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="50" maxlength="40" />
									</logic:notPresent>
													
									<a href="javascript:limpar('unidadeSuperior');">
										<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Limpar Unidade Superior" /> 
									</a> 
								</td>	
							</tr>			
							<tr>
								<td>
									<strong>
										Unidade Organizacional:
									</strong>
								</td>					
								<td colspan="6">
									<html:text property="idUnidadeOrganizacional"
										size="5" maxlength="5" tabindex="6"
										onkeyup="validarExibicaoUnidade(); limparOrigem(4)"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioTipoServicoAction.do', 'idUnidadeOrganizacional'); return isCampoNumerico(event); " />
							
									<a href="javascript:abrirPopupValidando('exibirPesquisarUnidadeOrganizacionalAction.do',  250, 495);">
										<img width="23" height="21" border="0" 
											src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" style="cursor:hand;" title="Pesquisar Unidade Organizacional"/></a> 
									
									<logic:present name="unidadeOrganizacionalEncontrada" scope="session">
										<html:text property="descUnidadeOrganizacional" readonly="true"
											style="background-color:#EFEFEF; border:0" size="50"
											maxlength="40" />
									</logic:present>
				
									<logic:notPresent name="unidadeOrganizacionalEncontrada" scope="session">
										<html:text property="descUnidadeOrganizacional" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="50" maxlength="40" />
									</logic:notPresent>
													
									<a href="javascript:limpar('unidadeOrganizacional');">
										<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Limpar Unidade Organizacional" /> 
									</a> 
								</td>	
							</tr>
							<tr>
								<td align="left">
									&nbsp;
								</td>
								<td align="right" colspan="6">
									<input type="button" name="botaoAdicionar"
										class="bottonRightCol" value="Adicionar"
										onclick="adicionarUnidadeOrganizacional();"/>
								</td>
							</tr>	
							<tr>
								<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc">
									<tr bgcolor="#79bbfd">
										<td width="5%" bgcolor="#90c7fc">
										<div align="center"><strong>Remover</strong></div>
										</td>
										<td width="85%" bgcolor="#90c7fc">
										<div align="center"><strong>Unidade Organizacional</strong></div>
										</td>
									</tr>	
										<logic:present name="colecaoUnidadeOrganizacional" scope="session">
											<logic:notEmpty name="colecaoUnidadeOrganizacional" scope="session">
												<%int cont = 0;%>
												<logic:iterate name="colecaoUnidadeOrganizacional" scope="session" 
												id="unidade" type="UnidadeOrganizacional">
													<c:set var="cont" value="${cont+1}" />
													<c:choose>
														<c:when test="${cont%2 == '0'}">
															<tr bgcolor="#cbe5fe">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#FFFFFF">
														</c:otherwise>
													</c:choose>
															<td>
																<div align="center"><font color="#333333"> <img width="14"
																	height="14" border="0"
																	src="<bean:message key="caminho.imagens"/>Error.gif"
																	onclick="javascript:removerUnidade('${cont}');" />
																</font></div>								
															</td>
															<td width="85%" align="center">
																<bean:write name="unidade" property="descricao" />
															</td>
														</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
								</table>
								</td>
							</tr>
							<tr>
								<td>
									<strong>
										Usuário: 
									</strong>
								</td>					
								<td>
									<html:text property="idUsuario"
										size="5" maxlength="5" tabindex="6"
										onkeyup="limparOrigem(2);"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioTipoServicoAction.do?pesquisarUsuario=ok', 'idUsuario'); return isCampoNumerico(event);" />
							
									<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?indicadorUsoTodos=1', 250, 495);">
										<img width="23" height="21" border="0" 
											src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Usuário"/></a> 
									
									<logic:present name="usuarioEncontrado" scope="session">
										<html:text property="descUsuario" readonly="true"
											style="background-color:#EFEFEF; border:0" size="50"
											maxlength="40" />
									</logic:present>
				
									<logic:notPresent name="usuarioEncontrado" scope="session">
										<html:text property="descUsuario" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="50" maxlength="40" />
									</logic:notPresent>
													
									<a href="javascript:limpar('usuario');">
										<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Limpar Usuário" /> 
									</a> 
								</td>	
							</tr>
							<tr>
								<td align="left">
									&nbsp;
								</td>
								<td align="right" colspan="6">
									<input type="button" name="botaoAdicionar"
										class="bottonRightCol" value="Adicionar"
										onclick="adicionarUsuario();"/>
								</td>
							</tr>
							<tr>
								<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc">
									<tr bgcolor="#79bbfd">
										<td width="5%" bgcolor="#90c7fc">
										<div align="center"><strong>Remover</strong></div>
										</td>
										<td width="85%" bgcolor="#90c7fc">
										<div align="center"><strong>Usuário</strong></div>
										</td>
									</tr>	
										<logic:present name="colecaoUsuario" scope="session">
											<logic:notEmpty name="colecaoUsuario" scope="session">
												<%int cont2 = 0;%>
												<logic:iterate name="colecaoUsuario" scope="session" 
												id="usuario" type="Usuario">
														<c:set var="cont2" value="${cont2+1}" />
														<c:choose>
															<c:when test="${cont2%2 == '0'}">
																<tr bgcolor="#cbe5fe">
															</c:when>
															<c:otherwise>
																<tr bgcolor="#FFFFFF">
															</c:otherwise>
														</c:choose>
															<td>
																<div align="center"><font color="#333333"> <img width="14"
																	height="14" border="0"
																	src="<bean:message key="caminho.imagens"/>Error.gif"
																	onclick="javascript:removerUsuario('${cont2}');" />
																</font></div>								
															</td>
															<td width="85%" align="center">
																<bean:write name="usuario" property="nomeUsuario" />
															</td>
														</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
								</table>
								</td>
							</tr>
							</table>
						</div>
						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="tipoLocalidade" style="display:none;">
							<table border="0">
							<tr>
								<td><strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font color="#FF0000">*</font> 
								<td colspan="2" align="left"><html:radio
									property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);"/> <strong>Estado </strong></td>
							</tr>
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="estadoGerencia" onclick = "limparCampos(this);"/> Estado por
								Ger&ecirc;ncia Regional </strong></td>
							</tr>
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> 
									<html:radio property="opcaoTotalizacao" value="estadoUnidadeNegocio" 
									onclick = "limparCampos(this);"/>
								<strong>Estado por Unidade de Negócio</strong></strong></td>
							</tr>
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="estadoLocalidade" onclick = "limparCampos(this);"/> <strong>Estado</strong>
								por Localidade</strong></td>
							</tr>
							
							
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td width="36%" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
								Regional </strong></strong></td>
								<td width="38%" align="left"><strong><strong> <html:select
									property="idGerenciaRegional" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoGerenciaRegional"
										labelProperty="nome" property="id" />
								</html:select> </strong></strong></td>
							</tr>
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td align="left"><strong> <html:radio property="opcaoTotalizacao"
									value="gerenciaRegionalLocalidade" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
								Regional</strong> por Localidade</strong></td>
								<td align="left"><strong><strong> <strong> <html:select
									property="idGerenciaRegionalporLocalidade" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoGerenciaRegional"
										labelProperty="nome" property="id" />
								</html:select> </strong> </strong></strong></td>
							</tr>
							
									<tr>
									<td><strong> <font color="#FF0000"></font></strong></td>
									<td width="36%" align="left"><strong> 
										<html:radio property="opcaoTotalizacao" value="unidadeNegocio" 
										onclick = "limparCampos(this);"/>
									<strong>Unidade de Negócio </strong></strong></td>
									<td width="38%" align="left"><strong><strong> 
										<html:select property="idUnidadeNegocio" disabled="true">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
									</html:select> </strong></strong></td>
								</tr>
								
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td align="left"><strong> <html:radio property="opcaoTotalizacao"
									value="localidade" onclick = "limparCampos(this);" /> Localidade</strong></td>
								<td align="left"><html:text
									property="idLocalidade" size="3" maxlength="3"
									onkeyup="limparOrigem(1);"
									disabled="true"
									onkeypress="validaEnter(event, 'exibirGerarRelatorioTipoServicoAction.do', 'idLocalidade'); return isCampoNumerico(event);" />
								<a href="javascript:abrirPopupValidandoLocalidade('exibirPesquisarLocalidadeAction.do', 400, 800);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" style="cursor:hand;" title="Pesquisar Localidade"/></a> 	
								
									<logic:present name="localidadeEncontrada" scope="session">
										<html:text property="descLocalidade"  readonly="true"
											style="background-color:#EFEFEF; border:0" size="30"
											maxlength="30" />
									</logic:present>
				
									<logic:notPresent name="localidadeEncontrada" scope="session">
										<html:text property="descLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="30" maxlength="30" />
									</logic:notPresent>	
									
									<a href="javascript:limpar('localidade');">
										<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Limpar Localidade" /> 
									</a></td>
							</tr>
							
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="1"><font color="#ff0000">
					
					<input type="button" tabindex="5" onclick="javascript:limparForm();" value="Limpar" class="bottonRightCol" name="limpar">
					
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></td>
					<td colspan="4" align="right">
						<table border="0" width="100%">
							<tr>
								<td align="right">&nbsp;</td>
								<td align="right">	
								<input type="button" class="bottonRightCol"
									value="Gerar Relat&oacute;rio"
									onclick="gerarRelatorio();">
								</td>
								
							</tr>
						</table>
					</td>
				</tr>
				<tr>
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
