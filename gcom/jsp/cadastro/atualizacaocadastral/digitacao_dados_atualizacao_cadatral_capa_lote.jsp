<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>



<html:javascript staticJavascript="false"  formName="EfetuarDigitacaoDadosAtualizacaoCadastralActionForm"/>

<script language="JavaScript">

	function validaForm(){

		var form = document.forms[0];

		if(validateEfetuarDigitacaoDadosAtualizacaoCadastralActionForm(form)){
			
			if(form.logradouroSelecionado[1] == null || 
				form.logradouroSelecionado[1].value == ''){

				alert('Favor adicionar logradouro');
				return false;
			}else{
		      	form.action='exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?inserir=sim';
		      	form.submit();
			}
		}
	}

	function limparDescricaoLocalidade() {
		var form = document.forms[0];
	
		form.idMunicipio.value = "";
		form.nomeMunicipio.value = "";
		
		var mensagemLocalidade = document.getElementById("mensagemLocalidade");
		
		if (mensagemLocalidade != null){
			mensagemLocalidade.innerHTML = "";
		}
		
	}

	function limparDescricaoSetor() {
		var form = document.forms[0];
	
		var mensagemSetorComercial = document.getElementById("mensagemSetorComercial");
		
		if (mensagemSetorComercial != null){
			mensagemSetorComercial.innerHTML = "";
		}
		
	}

	function limparDescricaoQuadra() {
		var form = document.forms[0];
	
		var mensagemQuadra = document.getElementById("mensagemQuadra");
		
		if (mensagemQuadra != null){
			mensagemQuadra.innerHTML = "";
		}
		
	}

	function limparPesquisaSetorComercial() {
		var form = document.forms[0];
	
		form.codigoSetorComercial.value = "";
		var mensagemSetorComercial = document.getElementById("mensagemSetorComercial");
		
		if (mensagemSetorComercial != null){
			mensagemSetorComercial.innerHTML = "";
		}
		
	}

	function limparPesquisaQuadra() {
    	var form = document.forms[0];

    	form.numeroQuadra.value = "";

		var mensagemQuadra = document.getElementById("mensagemQuadra");
	
		if (mensagemQuadra != null){
			mensagemQuadra.innerHTML = "";
		}
	}

	function reloadForm(){
		var form = document.forms[0];

      	form.action='exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?objetoConsulta=2';
      	form.submit();
	
	}

	function adicionar(){
		var form = document.forms[0];

		if(form.logradouroSelecao.value == '' ||
			form.logradouroSelecao.value == '-1'){

			alert('Informe o Logradouro!');
			return false;
			
		}else{
	      	form.action='exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?adicionar=true&objetoConsulta=reload';
	      	form.submit();
		}
	}

	function remover(){
		var form = document.forms[0];


		if(form.logradouroSelecionado.selectedIndex == 0 || 
			form.logradouroSelecionado.selectedIndex == -1){

			alert('Informe o Logradouro Para Remoção!');
			return false;
			
		}else{
	      	form.action='exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?remover=true&objetoConsulta=reload';
	      	form.submit();
		}
	}

	function limparPesquisaLogradouro(){
		var form = document.forms[0];

		form.logradouroSelecao.value = "";
		form.nomeLogradouroSelecao.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'logradouro') {
			form.logradouroSelecao.value = codigoRegistro;
			form.nomeLogradouroSelecao.value = descricaoRegistro;
			form.nomeLogradouroSelecao.style.color = "#000000";

	      	form.action='exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?objetoConsulta=reload';
	      	form.submit();
			
		}
	}

	function selecionarLogradouro(event){
		var form = document.forms[0];

		if(form.bairro.value == '-1'){
			limparPesquisaLogradouro();
			alert('Informe o Bairro!');
		}else{
			validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?objetoConsulta=reload', 'logradouroSelecao', 'Logradouro');
		}
	}

	function pesquisarLogradouro(){
		var form = document.forms[0];

		if(form.bairro.value == '-1'){
			limparPesquisaLogradouro();
			alert('Informe o Bairro!');
		}else{
			abrirPopup('/gsan/exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].idMunicipio.value+'&indicadorUsoTodos=1&primeriaVez=1', 465, 380);
		}
	}
	
	function limparBairro(){
		var form = document.forms[0];
		
		form.bairro.value = "-1";
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction"
	name="EfetuarDigitacaoDadosAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.EfetuarDigitacaoDadosAtualizacaoCadastralActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="idMunicipio"/>

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
			
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
				
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Efetuar Digitação de Dados para Atualização Cadastral</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para efetuar a digitação de dados para atualização cadastral,informe os dados abaixo:</td>
					</tr>
					
					
					<tr>
						<td><strong>Localidade:<font color="#FF0000">*</font></strong></td>
						
						<td>
							<html:text maxlength="3" 
								tabindex="1"
								property="idLocalidade" 
								size="4"
								onkeypress="javascript:limparDescricaoLocalidade();limparPesquisaSetorComercial();limparPesquisaQuadra();limparBairro();validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?objetoConsulta=1','idLocalidade','Localidade'); return isCampoNumerico(event);"/>
								
							<logic:present name="mensagemLocalidade" scope="request">
					 			
					 			<logic:present name="localidadeNaoEncontrada" scope="request">
									<span style="color:#ff0000" id="mensagemLocalidade">
										<bean:write scope="request" name="mensagemLocalidade"/>
									</span>
                      			</logic:present>
                      			
                      			<logic:notPresent name="localidadeNaoEncontrada" scope="request">
									<span style="color:#000000" id="mensagemLocalidade">
										<bean:write scope="request" name="mensagemLocalidade"/>
									</span>
                      			</logic:notPresent>
                      			
                      		</logic:present>
								
						</td>
					</tr>					
						
	             	<tr>
	                	<td><strong>Setor Comercial:<font color="#FF0000">*</font></strong></td>
	                   	<td>
	                   		<html:text maxlength="3" 
	                   			property="codigoSetorComercial" 
	                   			size="4"  
	                   			tabindex="2" 
	                   			onkeypress="javascript:limparDescricaoSetor();limparPesquisaQuadra(); return validaEnterDependencia(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);"/>
	                   			
							<logic:present name="mensagemSetorComercial" scope="request">
					 			
					 			<logic:present name="setorComercialNaoEncontrado" scope="request">
									<span style="color:#ff0000" id="mensagemSetorComercial">
										<bean:write scope="request" name="mensagemSetorComercial"/>
									</span>
                      			</logic:present>
                      			
                      			<logic:notPresent name="setorComercialNaoEncontrado" scope="request">
									<span style="color:#000000" id="mensagemSetorComercial">
										<bean:write scope="request" name="mensagemSetorComercial"/>
									</span>
                      			</logic:notPresent>

                      		</logic:present>
	                   			
	                      
	                   	</td>
	             	</tr>
	             	
	             	
	               	<tr>
	               		<td><strong>Quadra:<font color="#FF0000">*</font></strong></td>
	                   
	                   	<td>
	                 		<html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" 
	                 			property="numeroQuadra" 
	                 			size="4"  
	                 			tabindex="3"
	                   			onkeypress="javascript:limparDescricaoQuadra();return validaEnterDependencia(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do', this, document.forms[0].codigoSetorComercial.value, 'Setor Comercial');return isCampoNumerico(event);"/>
	                   			
							<logic:present name="mensagemQuadra" scope="request">
					 			
					 			<logic:present name="quadraNaoEncontrada" scope="request">
									<span style="color:#ff0000" id="mensagemQuadra">
										<bean:write scope="request" name="mensagemQuadra"/>
									</span>
                      			</logic:present>
                      			
                      			<logic:notPresent name="quadraNaoEncontrada" scope="request">
									<span style="color:#000000" id="mensagemQuadra">
										<bean:write scope="request" name="mensagemQuadra"/>
									</span>
                      			</logic:notPresent>

                      		</logic:present>
	                   			
	                   			
	                 	</td>
	              	</tr>
	              	
					<tr>
	
						<td><strong>Municipio Principal:</strong></td>
	
						<td>
							<html:text
								property="nomeMunicipio"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" 
								maxlength="20" />
						</td>
					</tr>	              	
					
					<tr>
						<td><strong>Bairro:<font color="#FF0000">*</font></strong></td>
						<td>
							<strong> 
							<html:select property="bairro" 
								style="width: 230px;" 
								tabindex="4"
								onchange="javascript:reloadForm();">
								
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoBairro" scope="request">
									<html:options collection="colecaoBairro"
										labelProperty="nome" 
										property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
					</tr>
				
					<tr>
						<table width="100%" border="0" bordercolor="#79bbfd">
							<tr>
								<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Logradouros </strong></td>
							</tr>
							
							<tr>
								<td><strong>Selecionar Logradouros<font color="#FF0000">*</font></strong></td>
								
								<td>
							
	                   				<html:text maxlength="9" 
	                   					property="logradouroSelecao" 
	                   					size="9"  
	                   					tabindex="5" 
	                   					onkeypress="javascript:selecionarLogradouro(event);"/>
							
									<a href="javascript:pesquisarLogradouro();">	
										<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" 
											height="21" 
											border="0" 
											title="Pesquisar Logradouro"></a>
											
									<logic:notPresent name="logradouroNaoEncontrado" scope="request">
										<html:text property="nomeLogradouroSelecao" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
			
									<logic:present name="logradouroNaoEncontrado" scope="request">
										<html:text property="nomeLogradouroSelecao" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />
									</logic:present>
											
									
									<a href="javascript:limparPesquisaLogradouro();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
											border="0" 
											title="Apagar"/></a>
								</td>								
								
								<td align="right">
									<input name="Button" 
										type="button" 
										class="bottonRightCol" 
										value="Adicionar" 
										onclick="javascript:adicionar();">
										
								</td>
							</tr>
							
							
							<tr>
	                			<td><strong>Logradouros Selecionados:</strong></td>
	                			<td><strong>
									<html:select property="logradouroSelecionado" 
										style="width: 250px; height:100px;" 
										multiple="true">
									<html:option value="">&nbsp;</html:option>
									<logic:present name="colecaoLogradourosSelecionados" scope="session">
										<html:options collection="colecaoLogradourosSelecionados" 
											labelProperty="descricaoFormatada" 
											property="id" />
									</logic:present>
									</html:select>                
	                  			</strong></td>
	                  			
	                  			<td align="right">
										
									<input name="Button" 
										type="button" 
										class="bottonRightCol" 
										value="Remover" 
										onclick="javascript:remover();">
										
								</td>
	              			</tr>
							
							<tr>
								<td><strong>Qtde Documentos:<font color="#FF0000">*</font></strong></td>
								
								<td>
									<html:text maxlength="3" 
										tabindex="6"
										property="quantidadeDocumentos" 
										size="4"
										onkeypress="javascript:return isCampoNumerico(event);"/>
								</td>
							</tr>
							
							
							<tr>
								<td></td>
								<td align="right">
									<div align="left">
									<strong><font color="#FF0000">*</font></strong>
									Campos obrigatórios</div>
								</td>
							</tr>
			
							<tr>
								<td colspan="2">
									<input name="Button" 
										type="button" 
										class="bottonRightCol" 
										value="Desfazer" 
										align="left"
										onclick="window.location.href='<html:rewrite page="/exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?menu=sim"/>'">
									
									<input name="Button" 
										type="button" 
										class="bottonRightCol" 
										value="Cancelar" 
										align="left"
										onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								</td>
								
								<td align="right">
									
									<input name="Button" 
										type="button" 
										class="bottonRightCol" 
										value="Avançar" 
										onclick="javascript:validaForm();">
									
										
								</td>
							</tr>							
							
						</table>
					</tr>

				

				
				</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
