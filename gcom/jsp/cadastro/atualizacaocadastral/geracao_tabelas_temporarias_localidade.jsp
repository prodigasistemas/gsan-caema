<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.cadastro.empresa.Empresa"%>
<%@page import="gcom.gui.cadastro.atualizacaocadastral.GerarTabelasTemporariasPorLocalidadeActionForm"%>
<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>cadastro/geracao_tabelas_temporarias_localidade.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<html:javascript staticJavascript="false"
	formName="GerarTabelasTemporariasPorLocalidadeActionForm" />


<script>

	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarTabelasTemporariasPorLocalidadeActionForm(form) && 
			campoObrigatorio() && 
			validarCampos() ){

			if ( form.setorComercialSelecionados != undefined && form.setorComercialSelecionados.value != null ){
				enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
			}

			if ( form.quadraSelecionados != undefined && form.quadraSelecionados.value != null ) {
				enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','quadraSelecionados');
			}

			if ( form.rotaSelecionados != undefined && form.rotaSelecionados.value != null ) {
				enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','rotaSelecionados');
		    } 

			form.action = 'filtrarGerarTabelasTemporariasPorLocalidadeAction.do';
			
			form.submit();
		}
	}

	function campoObrigatorio(){
	
		var form = document.forms[0];
		var msg = "";
		
		if(form.empresa.value == "-1"){
			msg = "Informe a Empresa.";
		}else if( form.matriculaImovel.value == "" && form.localidade.value == ""  ){
			msg = "Informe a matricula do imóvel ou localidade."
		}
		
		if( msg != ""){
			alert(msg);
			return false;
		}
		
		return true;
	}	

	function validarCampos(){
		
		var form = document.forms[0];
	
		return true;
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


	function limpar(){

		var form = document.forms[0];
	  	
		form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?limparForm=S';
	    form.submit();
	}




	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.forms[0];
	
		if (tipoConsulta == 'imovel') {
      		
      		form.matriculaImovel.value = codigoRegistro;
	  		form.nomeImovel.value = descricaoRegistro;
	  		      		
	  		form.nomeImovel.style.color = "#000000";
		}
		habilitaOuDesabilitaCampos();
	}



	function limparImovel(){
		var form = document.forms[0];
		form.matriculaImovel.value = "";
		form.nomeImovel.value = "";
	}

	function habilitaOuDesabilitaCampos(){
		var form = document.forms[0];

		if ( form.empresa.value != null && form.empresa.value != '-1' ) {


			form.localidade.disabled = false;
			form.matriculaImovel.disabled = false;
			form.nomeImovel.disabled = false;
			
			if ( form.localidade.value != null && form.localidade.value != "-1" ) {
				form.matriculaImovel.disabled = true;
				form.nomeImovel.disabled = true;

				form.indicadorSelecaoQuadraRota[0].disabled = false;
				form.indicadorSelecaoQuadraRota[1].disabled = false;
				form.indicadorGeracaoSetor[0].disabled = false;
				form.indicadorGeracaoSetor[1].disabled = false;
			} else if (form.matriculaImovel.value != null && form.matriculaImovel.value != "" ) {
				form.localidade.value = "-1";
				form.localidade.disabled = true;
				form.indicadorSelecaoQuadraRota[0].disabled = true;
				form.indicadorSelecaoQuadraRota[1].disabled = true;
				form.indicadorGeracaoSetor[0].disabled = true;
				form.indicadorGeracaoSetor[1].disabled = true;
			} else {
				form.indicadorSelecaoQuadraRota[0].disabled = true;
				form.indicadorSelecaoQuadraRota[1].disabled = true;
				form.indicadorGeracaoSetor[0].disabled = true;
				form.indicadorGeracaoSetor[1].disabled = true;
			}
		} else {

			form.localidade.value = "-1";
			form.localidade.disabled = true;

			form.matriculaImovel.value = "";
			form.nomeImovel.value = "";
			form.matriculaImovel.disabled = true;
			form.nomeImovel.disabled = true;
			form.indicadorSelecaoQuadraRota[0].disabled = true;
			form.indicadorSelecaoQuadraRota[1].disabled = true;
			form.indicadorGeracaoSetor[0].disabled = true;
			form.indicadorGeracaoSetor[1].disabled = true;

		}

		if ( form.indicadorGeracaoSetor[0].checked ){

			form.indicadorSelecaoQuadraRota[0].unchecked = true;
			form.indicadorSelecaoQuadraRota[1].unchecked = true;
			form.indicadorSelecaoQuadraRota[0].disabled = true;
			form.indicadorSelecaoQuadraRota[1].disabled = true;
		} else {

			if ( form.localidade.value != null && form.localidade.value != "-1" ) {
				form.indicadorSelecaoQuadraRota[0].disabled = false;
				form.indicadorSelecaoQuadraRota[1].disabled = false;
			}
		}


		
	}


	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.setorComercial != undefined && form.setorComercial.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}

		if (form.quadra.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}

		if (form.rota.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.setorComercial != undefined && form.setorComercial.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}

		if (form.quadra.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}

		if (form.rota.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
		
	}

	function carregarLocalidade(){

		var form = document.forms[0];
		form.indicadorSelecaoQuadraRota[0].unchecked = true;
		form.indicadorSelecaoQuadraRota[1].unchecked = true;
		form.indicadorSelecaoQuadraRota[0].disabled = true;
		form.indicadorSelecaoQuadraRota[1].disabled = true;
		form.indicadorGeracaoSetor[0].unchecked = true;
		form.indicadorGeracaoSetor[1].unchecked = true;
		form.indicadorGeracaoSetor[0].disabled = true;
		form.indicadorGeracaoSetor[1].disabled = true;

		
		form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=localidade';
	    form.submit();

	}

	function verificaIndicador(){
		var form = document.forms[0];

		if ( form.indicadorGeracaoSetor[0].checked ){

			form.indicadorSelecaoQuadraRota[0].unchecked = true;
			form.indicadorSelecaoQuadraRota[1].unchecked = true;
			form.indicadorSelecaoQuadraRota[0].disabled = true;
			form.indicadorSelecaoQuadraRota[1].disabled = true;
			
			form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=total';
			form.submit();
			
		} else if (form.indicadorGeracaoSetor[1].checked) {
			
			form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=setorComercial';
			form.submit();
		}
	}

	function verificaIndicadorSelecao(){
		var form = document.forms[0];

		if ( form.setorComercialSelecionados != undefined && form.setorComercialSelecionados.value != null ) {
	
			if ( form.indicadorSelecaoQuadraRota[0].checked ) {
	
				 enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
				 if ( form.quadraSelecionados != undefined && form.quadraSelecionados.value != null ) {
					  	enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','quadraSelecionados');
				 }
				 form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=quadra';
				 form.submit();
			} else if ( form.indicadorSelecaoQuadraRota[1].checked ) {
	
				enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
				if ( form.rotaSelecionados != undefined && form.rotaSelecionados.value != null ) {
					enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','rotaSelecionados');
				 } 
				form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=rota';
				form.submit();
			}
		}

		
	}

	function carregarDependetes() {
		var form = document.forms[0];

		 if ( form.indicadorSelecaoQuadraRota[0].checked == true ) {

			 enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
			 if ( form.quadraSelecionados != undefined && form.quadraSelecionados.value != null ) {
			  	enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','quadraSelecionados');
		 	 }
			 form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=quadra';
			 form.submit();
			
		} else if ( form.indicadorSelecaoQuadraRota[1].checked == true ) {

			 enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
			 if ( form.rotaSelecionados != undefined && form.rotaSelecionados.value != null ) {
				enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','rotaSelecionados');
			 } 
			 form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=rota';
			 form.submit();

		}  else {
			 enviarSelectMultiplo('GerarTabelasTemporariasPorLocalidadeActionForm','setorComercialSelecionados');
			 form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=setorComercial';
			 form.submit();
		}
	}

	function limparDadosInscricao(){
		var form = document.forms[0];

		if ( (form.setorComercial != undefined && form.setorComercial.value != null) || form.setorComercialSelecionados.value != null ) {
		 form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=limparInscricao';
		 form.submit();
		}
	

	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="habilitaOuDesabilitaCampos();">
</body>

<html:form action="/filtrarGerarTabelasTemporariasPorLocalidadeAction.do"
	name="GerarTabelasTemporariasPorLocalidadeActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.GerarTabelasTemporariasPorLocalidadeActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>	

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
					<td class="parabg">Gerar Tabelas Temporarias Por Localidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar tabelas temporárias por localidade, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td>
						<strong>Empresa:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<strong> 
						<html:select property="empresa" style="width: 230px;" onchange="carregarLocalidade();habilitaOuDesabilitaCampos();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoEmpresa" scope="request">
								<html:options collection="colecaoEmpresa"
											  labelProperty="descricao" 
											  property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Localidade:</strong>
					</td>
					<td colspan="2">
						<strong> 
						<html:select  property="localidade" style="width: 230px;" onchange="habilitaOuDesabilitaCampos();limparDadosInscricao();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
							<logic:present name="colecaoLocalidade" scope="request">
								<html:options collection="colecaoLocalidade"
									          labelProperty="descricao" 
									          property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Imóvel:</strong>
					</td>
					<td >						
						<html:text maxlength="9" 
							tabindex="1"
							property="matriculaImovel" 
							size="10"
							onkeyup="habilitaOuDesabilitaCampos();"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=5','matriculaImovel','Imovel');
							            return isCampoNumerico(event);habilitaOuDesabilitaCampos();"/>
							            
							
						<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].matriculaImovel);" id="pesIm">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a>

						<logic:present name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present> 

						<logic:notPresent name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
		
						<a href="javascript:limparImovel();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr><!-- Fim campo imóvel -->				
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<strong>Informe os dados da inscrição:</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Geração do Setor:</strong></td>
					<td align="right" >
					<div align="left">
						<html:radio property="indicadorGeracaoSetor" onchange="verificaIndicador();"
									tabindex="14"
									value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
							<strong>Total</strong> 
						<html:radio property="indicadorGeracaoSetor"
									tabindex="15" 
									onchange="verificaIndicador();"
									value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" 
									/>
							<strong>Parcial</strong> 
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Seleção parcial por:</strong></td>
					<td align="right" >
					<div align="left">
						<html:radio property="indicadorSelecaoQuadraRota" onchange="verificaIndicadorSelecao();"
									tabindex="14"
									value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
							<strong>Quadra</strong> 
						<html:radio property="indicadorSelecaoQuadraRota"
									tabindex="15" 
									onchange="verificaIndicadorSelecao();"
									value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" 
									/>
							<strong>Rota</strong> 
					</div>
					</td>
				</tr>
				
				<logic:present name="colecaoSetorComercial" scope="request">
					<tr>
						<td width="120">
							<strong>Setor Comercial:</strong>
						</td>
						<td colspan="2">
						<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
							<tr>
								<td width="175">
								
									<div align="left"><strong>Disponíveis</strong></div>
	
									<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercial, 6);" >
									
										<html:select property="setorComercial" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
											<logic:present name="colecaoSetorComercial" scope="request">
												<html:options collection="colecaoSetorComercial" 
													labelProperty="codigo" 
													property="codigo"/>
											</logic:present>
											
											<logic:present name="colecaoSetorComercialEnviadosDinamico" scope="request">
											<font color="#FF0000">
											<html:options collection="colecaoSetorComercialEnviadosDinamico" 
													labelProperty="codigo" style="color: red;"
													property="codigo"/>
											</font>
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
													onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
													value=" &gt;&gt; ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
													value=" &nbsp;&gt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
													value=" &nbsp;&lt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
													value=" &lt;&lt; ">
											</td>
										</tr>
									</table>
								</td>
	
								<td>
									<div align="left">
										<strong>Selecionados</strong>
									</div>
									<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialSelecionados, 6);" >
									
										<html:select property="setorComercialSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
											<logic:present name="colecaoSetorComercialSelecionados" scope="request">
												<html:options collection="colecaoSetorComercialSelecionados" 
													labelProperty="codigo" 
													property="codigo"/>
											</logic:present>
										</html:select>
									</div>
								</td>
								<td>
									<div align="left">
										<strong>Enviados</strong>
									</div>
									<div id='enviados' style="OVERFLOW: auto;WIDTH: 85px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialEnviados, 6);" >
									
										<html:select property="setorComercialEnviados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('enviados'), 6);">
										<logic:present name="colecaoSetorComercialEnviados" scope="request">
												<html:options collection="colecaoSetorComercialEnviados" 
													labelProperty="codigo" 
													property="codigo"/>
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>
				<logic:notPresent name="colecaoSetorComercial" scope="request">
					
					<logic:present name="colecaoSetorComercialEnviados" scope="request">
					
						<tr>
							<td width="120">
								<strong>Setor Comercial:</strong>
							</td>
							<td colspan="2">
							<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
								<tr>
									<td width="175">
									
										<div align="left"><strong>Disponíveis</strong></div>
		
										<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercial, 6);" >
										
											<html:select property="setorComercial" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
												<logic:present name="colecaoSetorComercial" scope="request">
													<html:options collection="colecaoSetorComercial" 
														labelProperty="codigo" 
														property="codigo"/>
												</logic:present>
												<font color="#FF0000">
												<logic:present name="colecaoSetorComercialEnviadosDinamico" scope="request">
												<html:options collection="colecaoSetorComercialEnviadosDinamico" 
														labelProperty="codigo" style="color: red;"
														property="codigo"/>
												</logic:present>
												</font>
											</html:select>
											
										</div>
									</td>
		
									<td width="5" valign="center"><br>
										<table width="50" align="center">
											<tr>
												<td align="center">
													<input type="button" 
														class="bottonRightCol"
														onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
														value=" &gt;&gt; ">
												</td>
											</tr>
			
											<tr>
												<td align="center">
													<input type="button" 
														class="bottonRightCol"
														onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
														value=" &nbsp;&gt;  ">
												</td>
											</tr>
			
											<tr>
												<td align="center">
													<input type="button" 
														class="bottonRightCol"
														onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
														value=" &nbsp;&lt;  ">
												</td>
											</tr>
			
											<tr>
												<td align="center">
													<input type="button" 
														class="bottonRightCol"
														onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
														value=" &lt;&lt; ">
												</td>
											</tr>
										</table>
									</td>
		
									<td>
										<div align="left">
											<strong>Selecionados</strong>
										</div>
										<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialSelecionados, 6);" >
										
											<html:select property="setorComercialSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
											<logic:present name="colecaoSetorComercialSelecionados" scope="request">
													<html:options collection="colecaoSetorComercialSelecionados" 
														labelProperty="codigo" 
														property="codigo"/>
												</logic:present>
											</html:select>
										</div>
									</td>
									<td>
										<div align="left">
											<strong>Enviados</strong>
										</div>
										<div id='enviados' style="OVERFLOW: auto;WIDTH: 85px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialEnviados, 6);" >
										
											<html:select property="setorComercialEnviados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('enviados'), 6);">
											<logic:present name="colecaoSetorComercialEnviados" scope="request">
												<html:options collection="colecaoSetorComercialEnviados" 
														labelProperty="codigo" 
														property="codigo"/>
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</logic:present>
					
					<logic:notPresent name="colecaoSetorComercialEnviados" scope="request">
					
							<logic:present name="colecaoSetorComercialSelecionados" scope="request">
							<tr>
								<td width="120">
									<strong>Setor Comercial:</strong>
								</td>
								<td colspan="2">
								<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
									<tr>
										<td width="175">
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercial, 6);" >
											
												<html:select property="setorComercial" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoSetorComercial" scope="request">
														<html:options collection="colecaoSetorComercial" 
															labelProperty="codigo" 
															property="codigo"/>
													</logic:present>
													<font color="#FF0000">
													<logic:present name="colecaoSetorComercialEnviadosDinamico" scope="request">
													<html:options collection="colecaoSetorComercialEnviadosDinamico" 
															labelProperty="codigo" style="color: red;"
															property="codigo"/>
													</logic:present>
													</font>
												</html:select>
												
											</div>
										</td>
			
										<td width="5" valign="center"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'setorComercial', 'setorComercialSelecionados');carregarDependetes();"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>
			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialSelecionados, 6);" >
											
												<html:select property="setorComercialSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
												<logic:present name="colecaoSetorComercialSelecionados" scope="request">
														<html:options collection="colecaoSetorComercialSelecionados" 
															labelProperty="codigo" 
															property="codigo"/>
													</logic:present>
												</html:select>
											</div>
										</td>
										<td>
											<div align="left">
												<strong>Enviados</strong>
											</div>
											<div id='enviados' style="OVERFLOW: auto;WIDTH: 85px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorComercialEnviados, 6);" >
											
												<html:select property="setorComercialEnviados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('enviados'), 6);">
												<logic:present name="colecaoSetorComercialEnviados" scope="request">
													<html:options collection="colecaoSetorComercialEnviados" 
															labelProperty="codigo" 
															property="codigo"/>
													</logic:present>
												</html:select>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</logic:present>
					
					</logic:notPresent>
					
				</logic:notPresent>
				
				<logic:present name="colecaoQuadra" scope="request">
					<tr>
						<td width="120">
							<strong>Quadra:</strong>
						</td>
						<td colspan="2">
						<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
							<tr>
								<td width="175">
								
									<div align="left"><strong>Disponíveis</strong></div>
	
									<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadra, 6);" >
										<html:select property="quadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
											<logic:present name="colecaoQuadra" scope="request">
												<html:options collection="colecaoQuadra" 
													labelProperty="setorQuadraFormatado" 
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
													onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'quadra', 'quadraSelecionados');"
													value=" &gt;&gt; ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'quadra', 'quadraSelecionados');"
													value=" &nbsp;&gt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'quadra', 'quadraSelecionados');"
													value=" &nbsp;&lt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'quadra', 'quadraSelecionados');"
													value=" &lt;&lt; ">
											</td>
										</tr>
									</table>
								</td>
	
								<td>
									<div align="left">
										<strong>Selecionados</strong>
									</div>
									<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadraSelecionados, 6);" >
									
									
										<html:select property="quadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
											<logic:present name="colecaoQuadraSelecionadas" scope="request">
												<html:options collection="colecaoQuadraSelecionadas" 
													labelProperty="setorQuadraFormatado" 
													property="id"/>
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>
				
				<logic:present name="colecaoRota" scope="request">
					<tr>
						<td width="120">
							<strong>Rota:</strong>
						</td>
						<td colspan="2">
						<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
							<tr>
								<td width="175">
								
									<div align="left"><strong>Disponíveis</strong></div>
	
									<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].rota, 6);" >
										<html:select property="rota" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
											<logic:present name="colecaoRota" scope="request">
												<html:options collection="colecaoRota" 
													labelProperty="setorRotaFormatado" 
													property="codigo"/>
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
													onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'rota', 'rotaSelecionados');"
													value=" &gt;&gt; ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarTabelasTemporariasPorLocalidadeActionForm', 'rota', 'rotaSelecionados');"
													value=" &nbsp;&gt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'rota', 'rotaSelecionados');"
													value=" &nbsp;&lt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarTabelasTemporariasPorLocalidadeActionForm', 'rota', 'rotaSelecionados');"
													value=" &lt;&lt; ">
											</td>
										</tr>
									</table>
								</td>
	
								<td>
									<div align="left">
										<strong>Selecionados</strong>
									</div>
									<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].rotaSelecionados, 6);" >
									
										<html:select property="rotaSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
											<logic:present name="colecaoRotaSelecionadas" scope="request">
												<html:options collection="colecaoRotaSelecionadas" 
													labelProperty="setorRotaFormatado" 
													property="codigo"/>
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>
				
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right" colspan="2">
					<div align="left"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
								
				<tr>
				
					<td>
						<font color="#FF0000"> <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font>
					</td>
			
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
			          
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>


		
</html:form>
</html:html>