<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper"  isELIgnored="false"%>


<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarImoveisPreGsanActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
<!--

var bCancel = false; 

function validateConsultarImoveisPreGsanActionForm(form) {                                                                   
	if (bCancel) 
    	return true; 
   	else 
    	return true; 
} 

function limparFormulario(form) {
	form.action = "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=limpar";
	form.submit();
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
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

	if (tipoConsulta == 'localidadeOrigem') {

  		form.idLocalidade.value = codigoRegistro;
  		form.descricaoLocalidade.value = descricaoRegistro;
  		
	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
	  	form.idSetorComercial.value = codigoRegistro;
	  	form.descricaoSetorComercial.value = descricaoRegistro;
	  	form.descricaoSetorComercial.style.color = "#000000"; 

	  	botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do?objetoConsulta=4');
	}
}	


function pesquisar(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
		botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar');
	}  else {
		alert("O campo Empresa é obrigatório")
	}
}

function validarCpfCnpjRF() {
	var form = document.forms[0];

	form.indicadorValidarCpfCnpjRF.value = "1";
	botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do');
	
}



function montarListaAlteracao(imac, tipo, imovelNovo,valor) {
	
	var form = document.forms[0];
	//carrega o select do imovel selecionado
	var select = document.getElementById('alteracao'+ imac);

	//Monta a situação, de acordo com a validacao do imovel.
	if ( tipo != null && tipo == "1" ) {
		//sem criticas
		var option = document.createElement("option");
		option.text = "";
		option.value = "-1";
		select.appendChild(option);
		
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);
		
		if(imovelNovo != "1"){
			var option2 = document.createElement("option");
			option2.text = "Retorna para o Campo";
			option2.value = "2";
			select.appendChild(option2);
		}

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);

	} else if ( tipo != null && tipo == "2" ) {
		//matricula visitada mais de 3 vezes
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "3" ) {
		//imovel cadastrado sem criticas
		var option = document.createElement("option");
		option.text = "";
		option.value = "-1";
		select.appendChild(option);
		
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);
		
		if(imovelNovo != "1"){
			var option2 = document.createElement("option");
			option2.text = "Retorna para o Campo";
			option2.value = "2";
			select.appendChild(option2);
		}
		
		var option3 = document.createElement("option");
		option3.text = "CNPJ/CPF Inconsistente";
		option3.value = "3";
		select.appendChild(option3);
		
		var option4 = document.createElement("option");
		option4.text = "Sem CPF, Atualizar GSAN";
		option4.value = "4";
		select.appendChild(option4);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "4" ) {
		//imovel novo com cpf/cnpj aceito
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "5" ) {
		//imovel novo com cpf/cnpj rejeitado
		var option3 = document.createElement("option");
		option3.text = "CNPJ/CPF Inconsistente";
		option3.value = "3";
		select.appendChild(option3);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "6" ) {
		//imovel novo com matricula associada
		var option = document.createElement("option");
		option.text = "";
		option.value = "-1";
		select.appendChild(option);
		
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);

		//remover a matricula associada
		var option13 = document.createElement("option");
		option13.text = "Remover Matrícula Indicada";
		option13.value = "13";
		select.appendChild(option13);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "7" ) {
		//Logradouro inexistente
		var option7 = document.createElement("option");
		option7.text = "Logradouro novo, inexistente no GSAN";
		option7.value = "7";
		select.appendChild(option7);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "8" ) {
		//setor comercial inexistente
		var option8 = document.createElement("option");
		option8.text = "Setor Comercial novo, inexistente no GSAN";
		option8.value = "8";
		select.appendChild(option8);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "9" ) {

		//quadra inexistente
		var option9 = document.createElement("option");
		option9.text = "Quadra nova, inexistente no GSAN";
		option9.value = "9";
		select.appendChild(option9);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if ( tipo != null && tipo == "10" ) {
		//imovel com inscricao em duplicidade
		
		var option10 = document.createElement("option");
		option10.text = "Imovel com inscrição em duplicidade";
		option10.value = "10";
		select.appendChild(option10);

		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
		
	} else if (tipo != null && tipo == "12" ) {
		//Caso o imovel tenha a inscricao em duplicidade no ambiente Virtual 2

		var option12 = document.createElement("option");
		option12.text = "Imóvel com inscrição em duplicidade no Ambiente Virtual 2";
		option12.value = "12";
		select.appendChild(option12);

		if(imovelNovo != "1"){
			var option2 = document.createElement("option");
			option2.text = "Retorna para o Campo";
			option2.value = "2";
			select.appendChild(option2);
		}
		
		var option11 = document.createElement("option");
		option11.text = "Remover Registro Atualização Cadastral";
		option11.value = "11";
		select.appendChild(option11);
	}

	select.value = valor;
}

function validarForm(){
	botaoAvancarTelaEspera('/gsan/atualizarImovelPreGsanAction.do');
}



function limpar(tipo){

	var form = document.forms[0];

	if ( tipo == '1') {

		form.action = "exibirConsultarImoveisPreGsanAction.do?limpar=localidade";
		form.submit();
		
	} else if ( tipo == '2' ) {
		form.action = "exibirConsultarImoveisPreGsanAction.do?limpar=setor";
		form.submit();
	}
	
}

function carregarSituacao(){
	var form = document.forms[0];
	form.action = "exibirConsultarImoveisPreGsanAction.do?carregar=situacao";
	form.submit();
}

function addElement() {
	var form = document.ConsultarImoveisPreGsanActionForm;

	if ( form.indicadorSituacaoTodosHabilitado != null && form.indicadorSituacaoTodosHabilitado.value != null &&
			form.indicadorSituacaoTodosHabilitado.value == '1' ) {
		var ni = document.getElementById('informarTodos');
	    
	    var select = document.createElement("select");
	    select.id = "arquivo1";
	    select.name = "arquivo1";
	    select.style.width = '105px';
	    select.onchange=carregarSituacao;
	    
	    var option = document.createElement("option");
		option.text = "";
		option.value = "-1";
		select.appendChild(option);
		
		var option1 = document.createElement("option");
		option1.text = "Liberado para Atualização GSAN";
		option1.value = "1";
		select.appendChild(option1);
		
		var option2 = document.createElement("option");
		option2.text = "Retorna para o Campo";
		option2.value = "2";
		select.appendChild(option2);

		var option3 = document.createElement("option");
		option3.text = "CNPJ/CPF Inconsistente";
		option3.value = "3";
		select.appendChild(option3);
		
		var option4 = document.createElement("option");
		option4.text = "Sem CPF, Atualizar GSAN";
		option4.value = "4";
		select.appendChild(option4);

		ni.appendChild(select);
		form.indicadorSituacaoTodosHabilitado.value = '2';
	} else {
		var ni = document.getElementById('informarTodos');
		var select = document.getElementById('arquivo1');
		ni.removeChild(select);
		form.indicadorSituacaoTodosHabilitado.value = '1';
	}
    

}

function gerarRelatorio(){
	var form = document.forms[0];
	form.action = "/gsan/gerarRelatorioCpfCnpjInconsistentesImoveisNovosAction.do";
	form.submit();
}

function gerarRelatorioImoveisLigadosQuadraSemRede(){
	var form = document.forms[0];
	form.action = "/gsan/gerarRelatorioImoveisLigadosParaQuadraSemRedeAction.do";
	form.submit();
}

function gerarRelatorioImoveisInconsistentes(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
		botaoAvancarTelaEspera('/gsan/gerarRelatorioImoveisInconsistentesAction.do');
	}  else {
		alert("O campo Empresa é obrigatório")
	}
}

function gerarRelatorioImoveisInconsistentesResumo(){
	var form = document.forms[0];
	if(form.indicadorTipoSelecao[0].checked){
		if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
			enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
			botaoAvancarTelaEspera('/gsan/gerarRelatorioImoveisInconsistentesAction.do?resumo=true');
		}  else {
			alert("O campo Empresa é obrigatório")
		}
	}else{
		alert("Relatório válido apenas para	Imóveis com Ocorrência Cadastro");
	}
}

function carregarCadastrador(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		form.action = "/gsan/exibirConsultarImoveisPreGsanAction.do?pesquisarCadastradores=sim";
		form.submit();
	}
}

function tipoSelecaoChange(){
	var form = document.forms[0];
	
	if(form.indicadorTipoSelecao[0].checked){
		document.getElementById("botaoResumo").disabled= false;			
	}else{
		document.getElementById("botaoResumo").disabled= true;
	}
}

-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');tipoSelecaoChange();">
<div id="formDiv">
<html:form action="/atualizarImovelPreGsanAction" 
	name="ConsultarImoveisPreGsanActionForm" 
	type="gcom.gui.cadastro.atualizacaocadastral.ConsultarImoveisPreGsanActionForm"
	method="post"
	onsubmit="return validateConsultarImoveisPreGsanActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorValidarCpfCnpjRF"/>
	<html:hidden property="indicadorSituacaoTodosHabilitado"/>
	

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
			
			<td width="600" valign="top" class="centercoltext">
			
			
				<table width="100%" 
					border="0" 
					align="center" 
					cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Imóveis no Ambiente Pré GSAN</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			
				<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
							<p>Para consultar os imóveis no ambiente Pré-GSAN, informe os dados abaixo:</p>
						</td>
					</tr>
	        	</table>
	        	<p>&nbsp;</p>
	        	
	        	<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								
								<td align="left">
									<html:select property="idEmpresa" onchange="carregarCadastrador();">
										
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoEmpresa" scope="request">					
											<html:options collection="colecaoEmpresa" 
														  labelProperty="descricao" 
														  property="id"/>
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td><strong>Localidade:</strong></td>
								
								<td>
									<html:text  maxlength="3" 
												tabindex="4"
												property="idLocalidade" 
												size="3"
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImoveisPreGsanAction.do?objetoConsulta=2', 'idLocalidade','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidade);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Localidade" /></a>
			
									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="30" 
											maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF;border:0;"/>
									</logic:notPresent>
									
									<a href="javascript:limpar(1);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Setor Comercial:</strong></td>
								
								<td>
									<html:text maxlength="3" 
										tabindex="5"
										property="idSetorComercial" 
										size="3"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImoveisPreGsanAction.do?limparQuadras=ok&objetoConsulta=3', 'idSetorComercial','Setor Comercial');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidade.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].idSetorComercial);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Setor Comercial" /></a>
											
									<logic:present name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="30" 
											maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
									
									<a href="javascript:limpar(2);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td width="120">
									<strong>Quadras:</strong>
								</td>
								<td colspan="2">
								<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
									<tr>
										<td width="110">
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadra, 6);" >
											
												<html:select property="idQuadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoQuadras" scope="request">	
														<html:options collection="colecaoQuadras" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
												
												
											</div>
										</td>
			
										<td width="5" valign="center"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>
			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											
											<div id='selecionados' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadraSelecionados, 6);" >
											
												<html:select property="idQuadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
													<logic:present name="colecaoQuadrasSelecionadas" scope="request">	
														<html:options collection="colecaoQuadrasSelecionadas" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
											
											</div>
											
										</td>
									</tr>
								</table>
								</td>
							</tr>
								
							
							<tr>
								<td>
									<strong>Ocorrência Cadastro:</strong>
								</td>
								<td align="left">
									<html:select property="idCadastroOcorrencia" tabindex="8" >
										<html:option value="-1">&nbsp;</html:option>
											<logic:present name="colecaoCadastroOcorrencia" scope="request">					
												<html:options collection="colecaoCadastroOcorrencia" 
															  labelProperty="descricao" 
															  property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>
						
							<tr>
								<td><strong>Tipo de Seleção:</strong></td>
								<td align="left">
									<html:radio property="indicadorTipoSelecao" 
												tabindex="9"
												value="2" onchange="tipoSelecaoChange();" />
										<strong>Imóveis com Ocorrência Cadastro</strong> 
									<html:radio property="indicadorTipoSelecao"
												tabindex="10" 
												value="1" onchange="tipoSelecaoChange()" />
										<strong>Imóveis Novos</strong>
										 
								</td>
							</tr>
							
							<tr>
			             		<td><strong>Cadastrador:</strong></td>
			             		<td>
			             			<html:select property="cadastrador" >
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoLeiturista" >
											<html:options collection="colecaoLeiturista"
													  labelProperty="descricao" 
													  property="id" />
										</logic:present>
									</html:select>	
			             		</td>
			             	</tr>
							
							<tr>
								<td valign="top" colspan="2">
									<div align="right">
										<input type="button"
											name="ButtonSelecionar" 
											class="bottonRightCol" 
											value="Selecionar"
											onclick="javascript:pesquisar();">
									</div>
								</td>
							</tr>	
							
							
							<tr>
			             		<td height="10" colspan="2"> 
				             		<div align="right"> 
				                 		<hr>
				               		</div>
				               		<div align="right"> </div>
			               		</td>
			           		</tr>
										
	           				
	           				<logic:present name="colecaoImovelPreGsan" scope="session">
								<tr>
									<td align="left" colspan="2">
											<strong>Imóveis Novos:</strong>
										</td>
								</tr>
								
								<table width="100%" cellpadding="0" cellspacing="0">
									
									<tr bordercolor="#000000" height="45px">
										<td width="10%" bgcolor="#90c7fc" align="center">
											<A HREF='javascript:void(0)'; onclick='addElement();'  > <strong>Situação</strong></A>
											<div id="informarTodos" align="left" ></div>
										</td>
									
										<td width="12%" bgcolor="#90c7fc" align="right">
											<strong>Setor</strong>
									   </td>
										<td width="12%" bgcolor="#90c7fc" align="center">
											<strong>Quadra</strong>
									   </td>
									   <td width="10%" bgcolor="#90c7fc" align="left">
											<strong>Lote</strong>
									   </td>
									   <td width="20%" bgcolor="#90c7fc" align="left" >
											<strong>Endereço</strong>
									   </td>
									   <td width="10%" bgcolor="#90c7fc" align="left" >
											<strong>Número</strong>
									   </td>
									   <td width="15%" bgcolor="#90c7fc" align="center" >
											<strong>Matrícula GSAN</strong>
									   </td>
									   <td width="10%" bgcolor="#90c7fc" align="center" >
											<strong>CPF Validado</strong>
									   </td>
									</tr>		
									
									<tr>
										<td height="250" colspan="8">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%"  bgcolor="#90c7fc">			
													<%	int cont = 0;	%> 
													<logic:iterate name="colecaoImovelPreGsan" 
														id="dados"
														scope="session" 
														type="DadosImovelPreGsanHelper" >
								                     
						                         		<%	cont = cont + 1;
														if (cont % 2 == 0) {	
															if ( ( dados.getIndicadorLogradouroNaoInformado() != null && dados.getIndicadorLogradouroNaoInformado().equals("1")) 
																	|| ( dados.getIndicadorCpfCnpjNaoInformado() != null && dados.getIndicadorCpfCnpjNaoInformado().equals("1")) 
																	|| ( dados.getIndicadorSetorComercialInexistente() != null && dados.getIndicadorSetorComercialInexistente().equals("1"))
																	|| ( dados.getIndicadorQuadraInexistente() != null && dados.getIndicadorQuadraInexistente().equals("1"))
																	) { %>
									               				<tr bgcolor="#cbe5fe" style="color:#ff0000 " >
									               			<%	} else {	%>
									               				<tr bgcolor="#cbe5fe" >
									               			<%	}	%>			
									             	<%	} else {
									             		if ( ( dados.getIndicadorLogradouroNaoInformado() != null && dados.getIndicadorLogradouroNaoInformado().equals("1")) 
																|| ( dados.getIndicadorCpfCnpjNaoInformado() != null && dados.getIndicadorCpfCnpjNaoInformado().equals("1"))
																|| ( dados.getIndicadorSetorComercialInexistente() != null && dados.getIndicadorSetorComercialInexistente().equals("1"))
																|| ( dados.getIndicadorQuadraInexistente() != null && dados.getIndicadorQuadraInexistente().equals("1"))
																
									             				) { %>
									               				<tr bgcolor="#FFFFFF" style="color:#ff0000 " >
									               			<%	} else {	%>
									               				<tr bgcolor="#FFFFFF" >
									               			<%	}	%>
									             	<%	}	%>
								                       		 
								                       		<td width="10%" align="center">
																<select id="alteracao<%="" + dados.getIdImovelAtualizacaoCadastral()%>" 
																name="alteracao<%="" + dados.getIdImovelAtualizacaoCadastral()%>"  style="width: 70;" >
																<script>
																	montarListaAlteracao(<%="" + dados.getIdImovelAtualizacaoCadastral()%>, <%="" + dados.getIndicadorHabilitaTipoSituacao()%>, <%="" + dados.getIndicadorImovelNovo()%>, <%="" + dados.getIndicadorValueSelect()%>);
																</script>
																   
																</select>
							                            	</td>
								                            
								                            <td width="10%" align="center"> 
								                            	<a href="javascript:abrirPopup('exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?idImovelAtualizacaoCadastral=${dados.idImovelAtualizacaoCadastral}&acao=pesquisarImovel',800, 600);" >
								                            		<bean:write name="dados" property="codigoSetorComercial"/>
								                            	</a>
								                            </td>
						
								                            <td width="10%" align="left" >
								                            	<bean:write name="dados" property="numeroQuadra" />
															</td>
															
															<td width="10%" align="left" >
																<bean:write name="dados" property="numeroLote" />
															</td>
															<td  width="20%" align="left" style="width: 100; ">
																<bean:write name="dados" property="enderecoFormatado" />
															</td>
															<td  width="10%" align="left">
																<bean:write name="dados" property="numero" />
															</td>
															
															
														<%	if (  dados.getColecaoMatriculaGsan() != null && dados.getColecaoMatriculaGsan().size() > Integer.valueOf(1)) { %>
								               				<td  width="15%" align="left" >
																<a href="javascript:abrirPopup('exibirInformarMatriculaDuplicadaPopupAction.do?idAtualizacaoCadastralDuplicado=${dados.idImovelAtualizacaoCadastral}',800, 600);" >
																	<bean:write name="dados" property="matriculaGsan" />
																</a>
																<img src="<bean:message key="caminho.imagens"/>alerta.jpg" />
															</td>
								               			<%	} else {	%>
					               							<td  width="15%" align="left" >
					               								<a href="javascript:abrirPopup('exibirInformarMatriculaDuplicadaPopupAction.do?idAtualizacaoCadastralDuplicado=${dados.idImovelAtualizacaoCadastral}',800, 600);" >
																	<bean:write name="dados" property="matriculaGsan" />
																</a>
															</td>
								               			<%	}	%>
															
															<td  width="10%" align="center">
															
															<%	if (  dados.getIndicadorCpfCnpjValidadoNaReceita() != null && dados.getIndicadorCpfCnpjValidadoNaReceita() == "1") { %>
															SIM
															<%	} else {	%>
															NÃO
															<%	}	%>
																
															</td>
								                      	</tr>
					                   				</logic:iterate>
				                   				</table>
			                   				</div>
		                   				</td>
		                   			</tr>
		                   			
		                   			<tr>
										<td valign="top" colspan="8">
										<div align="left" >
											<input type="button"
												name="ButtonCpfCnpj" 
												class="bottonRightCol" 
												value="Verifica CNPJ/CPF"
												onClick="javascript:validarCpfCnpjRF();">
												
											<input type="button"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Imprimir CNPJ/CPF Inconsist"
													onclick="gerarRelatorio();">
												
											<input type="button"
												align="right"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Imprimir Imóveis Lig Quadra sem Rede"
												onClick="javascript:gerarRelatorioImoveisLigadosQuadraSemRede();">
										</div>
										</td>
									</tr>
		                   		</table>
	           				</logic:present>
	           				
	           				
	           				<logic:present name="colecaoImovelCadastradoPreGsan" scope="session">
								<tr>
									<td align="left" colspan="2">
											<strong>Imóveis com Ocorrência de Cadastro:</strong>
										</td>
								</tr>
								
								<table width="100%" cellpadding="0" cellspacing="0" >
								
									<tr bordercolor="#000000" height="45px">
										<td width="15%" bgcolor="#90c7fc" align="center">
											<A HREF='javascript:void(0)'; onclick='addElement();'  > <strong>Situação</strong></A>
											<div id="informarTodos" align="left" ></div>
										</td>
										<td width="10%" bgcolor="#90c7fc" align="center">
											<strong>Setor</strong>
									   </td>
										<td width="10%" bgcolor="#90c7fc" align="center">
											<strong>Quadra</strong>
									   </td>
									   <td width="15%" bgcolor="#90c7fc" align="center">
											<strong>Matrícula</strong>
									   </td>
									   <td width="35%" bgcolor="#90c7fc" align="center" >
											<strong>Ocorrência Cadastro</strong>
									   </td>
									   <td width="15%" bgcolor="#90c7fc" align="left" >
											<strong>Número de Visitas</strong>
									   </td>
									  
									</tr>		
									<tr >
										<td height="250" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" bgcolor="#90c7fc" >			
													<%	int cont = 0;	%> 
													<logic:iterate name="colecaoImovelCadastradoPreGsan" 
														id="dados"
														scope="session" 
														type="DadosImovelPreGsanHelper">
								                    
								                    
								                    
								                    <%	cont = cont + 1;
														if (cont % 2 == 0) {	
															if ( ( dados.getIndicadorSetorComercialInexistente() != null && dados.getIndicadorSetorComercialInexistente().equals("1"))
																	|| ( dados.getIndicadorQuadraInexistente() != null && dados.getIndicadorQuadraInexistente().equals("1"))
																	) { %>
									               				<tr bgcolor="#cbe5fe" style="color:#ff0000 " >
									               			<%	} else {	%>
									               				<tr bgcolor="#cbe5fe" >
									               			<%	}	%>			
									             	<%	} else {
									             		if ( ( dados.getIndicadorSetorComercialInexistente() != null && dados.getIndicadorSetorComercialInexistente().equals("1"))
																|| ( dados.getIndicadorQuadraInexistente() != null && dados.getIndicadorQuadraInexistente().equals("1"))
																
									             				) { %>
									               				<tr bgcolor="#FFFFFF" style="color:#ff0000 " >
									               			<%	} else {	%>
									               				<tr bgcolor="#FFFFFF" >
									               			<%	}	%>
									             	<%	}	%>
								                    
								                       		<td width="15%" align="center" >
																<select id="alteracao<%="" + dados.getIdImovelAtualizacaoCadastral()%>" 
																		name="alteracao<%="" + dados.getIdImovelAtualizacaoCadastral()%>" 
																		style="width: 100; ">
																   <script>
																	montarListaAlteracao(<%="" + dados.getIdImovelAtualizacaoCadastral()%>, <%="" + dados.getIndicadorHabilitaTipoSituacao()%>, <%="" + dados.getIndicadorImovelNovo()%>, <%="" + dados.getIndicadorValueSelect()%>);
																</script>
																</select>
							                            	</td>
								                            
								                            <td width="10%" align="center"> 
								                            	<a href="javascript:abrirPopup('exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?idImovelAtualizacaoCadastral=${dados.idImovelAtualizacaoCadastral}&acao=desabilitar',800, 600);">
								                            		<bean:write name="dados" property="codigoSetorComercial"/>
								                            	</a>
								                            </td>
						
								                            <td width="10%" align="left" >
								                            	<bean:write name="dados" property="numeroQuadra" />
															</td>
															<td width="15%" align="left" >
																<bean:write name="dados" property="matricula" />
															</td>
															<td  width="35%" align="center">
																<bean:write name="dados" property="descricaoCadastroOcorrencia" />
															</td>
															<td  width="15%" align="left">
																<bean:write name="dados" property="numeroVisitas" />
															</td>
								                      	</tr>
					                   				</logic:iterate>
				                   				</table>
			                   				</div>
		                   				</td>
		                   			</tr>
		                   			<tr>
										<td valign="top" colspan="7">
										<div align="left" >
											<input type="button"
												name="ButtonCpfCnpj" 
												class="bottonRightCol" 
												value="Verifica CNPJ/CPF"
												onClick="javascript:validarCpfCnpjRF();">
												
											<input type="button"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Imprimir CNPJ/CPF Inconsist"
												onclick="gerarRelatorio();">
												
											<input type="button"
												align="right"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Imprimir Imóveis Lig Quadra sem Rede"
												onClick="javascript:gerarRelatorioImoveisLigadosQuadraSemRede();">
										</div>
										</td>
									</tr>
		                   		</table>
	           				</logic:present>
	           				
	           				
	           				
							<table width="100%" border="0">
								<tr>
									<td valign="top" align="left">
										<input type="button"
											name="ButtonCancelar" 
											class="bottonRightCol" 
											value="Cancelar"
											
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									
										<input type="button"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Limpar"
											onClick="javascript:limparFormulario(document.forms[0]);">
											
									</td>	
									<td valign="top" align="right">
										<input type="button"
											align="right"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Imprimir"
											onClick="javascript:gerarRelatorioImoveisInconsistentes();">
										<input
											id="botaoResumo"
											type="button"
											align="right"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Imprimir Resumo"
											onClick="javascript:gerarRelatorioImoveisInconsistentesResumo();">
										<input type="button"
											align="right"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Atualizar"
											onClick="javascript:validarForm();">
									</td>
								</tr>	
							</table>
						</table>
						</td>
					</tr>
					
				</table>
				
				
			</td>
		</tr>
	</table>


<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCpfCnpjInconsistentesImoveisNovosAction.do" />

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>