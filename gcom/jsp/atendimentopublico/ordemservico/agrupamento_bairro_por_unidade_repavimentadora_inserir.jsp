<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.geografico.Bairro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validateInserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm(form)){
	   		if(form.nome.value == '' && form.idAgrupamento.value == '-1'){
	   			alert ('Informe o nome ou selecione um agrupamento');
	   			return false;
	   		}
	   		
	   		botaoAvancarTelaEspera('/gsan/inserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do');
		}
	}

	function limparForm(form) {
		form.nome.value = "";
		form.unidadeRepavimentadora.value = "-1"; 
		form.nomeMunicipio.value = "";
	}

	function facilitador(objeto){
	    if (objeto.id == "0" || objeto.id == undefined){ 
	        objeto.id = "1";
	        marcarTodosListBox("bairros");
	    } else{
	        objeto.id = "0";
	        desmarcarTodosListBox("bairros");
	    }
	}

	function limpaNomeMunicipio(){
		var form = document.forms[0];
		form.nomeMunicipio.value = "";
	}

	function limparPesquisaMunicipio() {
		var form = document.forms[0];
		form.idMunicipio.value = "";
		form.nomeMunicipio.value = "";
		desabilitaCampoAgrupamento();
		
		form.action = 'exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do';
		form.submit();
	}

 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	    if (tipoConsulta == 'municipio') {
	      form.idMunicipio.value = codigoRegistro;
	      form.nomeMunicipio.value = descricaoRegistro;
	      form.nomeMunicipio.style.color = "#000000";
	      
	      //Código responsável pela atualização automática da coleção
	      form.action = 'exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do';
		  form.submit();
	    }
	 }
	 
	 function desabilitaCampoAgrupamento(){
	    var form = document.InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm;
	    if (form.nome.value != ''){
	      form.idAgrupamento.value = -1;	
	      form.idAgrupamento.disabled = true;
	    }else{
	      form.idAgrupamento.disabled = false;
	    }
	 }

	function verficaAgrupamentoExistente(){
		var form = document.forms[0];
	  	colecaoAgrupamento = form.idAgrupamento;
	  	cont = 0;
	  	
	  	while (colecaoAgrupamento.options.length > cont){
	  		if (trim(colecaoAgrupamento.options[cont].innerHTML) == trim(form.nome.value.toUpperCase())){
	  			alert ('Agrupamento já existe. Selecionar a descrição da lista ao lado.');
	  			form.nome.value = "";
	  			colecaoAgrupamento.disabled = false;
	  			colecaoAgrupamento.focus();		
	  			break;
	  		}
	  		cont++;
	  	}
	}
	  
	function reload(){
	  	var form = document.forms[0];
	  	
	  	form.action = 'exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do?recarregar=sim';
		form.submit();
	}
 
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');desabilitaCampoAgrupamento();">

<div id="formDiv">
<html:form action="/inserirAgrupamentoBairroPorUnidadeRepavimentadoraAction" method="post"
	name="InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm"
	onsubmit="return validateInserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Agrupamento de Bairros Por Unidade Repavimentadora</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para informar agrupamento de bairros, informe os dados
					abaixo:</td>
				</tr>
								
				<tr>
					<td width="162"><strong>Nome do Agrupamento:<font
						color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="nome" size="25"
						maxlength="30" onkeyup="javaScript:desabilitaCampoAgrupamento();"
						onblur="verficaAgrupamentoExistente();" /> </strong></td>
					
					<td><html:select property="idAgrupamento" onchange="javascript:reload();" >
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoAgrupamentos" scope="request" >
							<html:options collection="colecaoAgrupamentos"
								labelProperty="descricaoAgrupamento" property="id" />
						</logic:present>
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Unidades Repavimentadoras:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="unidadeRepavimentadora">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoUnidadesRepavimentadoras"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="4" property="idMunicipio"
						onkeyup="limpaNomeMunicipio();" size="4"
						onkeypress="javascript:validaEnter(event, 'exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do', 'idMunicipio');return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioNaoEncontrado">
						<logic:equal name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado">
						<logic:empty name="InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparPesquisaMunicipio();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<table bordercolor="#000000" width="100%" cellspacing="0">
	        	<tr>
					<td colspan="3">
					
				<logic:present name="colecaoBairro" scope="request">
				<strong>Bairros Encontrados:</strong>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="2" bgcolor="#000000" height="2"></td>
								</tr>
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF" border="0">
											<tr bordercolor="#000000">
												<td width="10%" bgcolor="#90c7fc" align="center">
													<strong><a href="javascript:facilitador(this);">Todos</a></strong>
												</td>
												<td width="15%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Cód. Bairro</strong></div>
											   </td>
											   <td width="27%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Nome do Bairro</strong></div>
											   </td>
											   <td width="30%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Município</strong></div>
											   </td>
											   <td width="18%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Cód. Pref.</strong></div>
											   </td>
											</tr>
										</table>
									</td>
								</tr>
											
								<tr>
									<td height="120">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#99CCFF">
											
												<%int cont = 0;%>
                      							<logic:iterate name="colecaoBairro" id="bairro" scope="request" type="Bairro">
												
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
							                        <tr bgcolor="#cbe5fe">
							                        	<%} else {%>
							                        </tr>
							                       	<tr bgcolor="#FFFFFF">
							                        	<%}%>
												
														<td width="10%" align="center">
															<logic:empty name="bairro" property="pertenceAgrupamentoBairros">
						                              			<input type="checkbox" name="bairros" value="${bairro.id}" />
						                              		</logic:empty>
						                              		<logic:notEmpty name="bairro" property="pertenceAgrupamentoBairros">
						                              			<input type="checkbox" name="bairros" value="${bairro.id}" checked="checked" />
						                              		</logic:notEmpty>
						                            	</td>
						                            	<td width="15%" align="center">
						                            		<bean:write name="bairro" property="id"/>
						                            	</td>
						                            	<td width="30%" align="left">
						                            		<bean:write name="bairro" property="nome"/>
						                            	</td>
						                            	<td width="30%" align="left">
						                            		<bean:write name="bairro" property="municipio.nome"/>
						                            	</td>
						                            	<td width="16%" align="center">
						                            		<bean:write name="bairro" property="codigoBairroPrefeitura"/>
						                            	</td>	
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</logic:present>

				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='/gsan/exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do?menu=sim'"> <input
						name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Associar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

