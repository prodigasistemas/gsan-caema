<%@page import="gcom.cobranca.RdLimitacaoGeografica"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cobranca.LimitacaoGeograficaRDHelper" %>
<%@ page import="gcom.cobranca.RdRestricaoUsuario" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarResolucaoDiretoriaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){
				
		if(testarCampoValorZero(document.AtualizarResolucaoDiretoriaActionForm.numero, 'Número da RD') 
		&& testarCampoValorZero(document.AtualizarResolucaoDiretoriaActionForm.assunto, 'Assunto da RD')) { 		
			if(validateAtualizarResolucaoDiretoriaActionForm(form) && validateInteger(form)){			
				if(validaTodosRadioButton(form)){
	   				submeterFormPadrao(form);			
				}
			}
		}	
	}
	
	function IntegerValidations () { 
	    this.aa = new Array("idParcelasEmAtraso", "RD parcelas em atraso deve ser numérico positivo.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("idParcelamentoEmAndamento", "RD Parcelamento em Andamento deve ser numérico.", new Function ("varName", " return this[varName];"));
	}
	
	function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButton(form.indicadorParcelamentoUnico,"Indicador de Parcelamento Único.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorParcelamentoUnico,"Indicador de Parcelamento Único.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorUtilizacaoLivre,"Indicador de Utilização Livre.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorUtilizacaoLivre,"Indicador de Utilização Livre.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorValidoAcaoCobranca,"Indicador de Valido Ação Cobrança.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorValidoAcaoCobranca,"Indicador de Valido Ação Cobrança.")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	} 
	
	function habilitacaoIdParcelasEmAtraso(indicadorParcelasEmAtraso){

		var form = document.forms[0];
			if (indicadorParcelasEmAtraso[1].checked){
				form.idParcelasEmAtraso.disabled = true;
				form.idParcelasEmAtraso.value = ""; 
			}else{
				form.idParcelasEmAtraso.disabled = false;
			}
   	}
   	
   	function habilitacaoIdParcelamentoEmAndamento(indicadorParcelamentoEmAndamento){

		var form = document.forms[0];
			if (indicadorParcelamentoEmAndamento[1].checked){
				form.idParcelamentoEmAndamento.disabled = true;
				form.idParcelamentoEmAndamento.value = ""; 
			}else{
				form.idParcelamentoEmAndamento.disabled = false;
			}
   	}
   	
   	function desfazer(){
   		var form = document.forms[0];
   		form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?desfazer=sim';
   		form.submit();
   	}
   	
   	function addLimitacaoGeograficaChamarPopup() {
		var form  =   document.forms[0];
		
		abrirPopup('exibirAdicionarLimitacaoGeograficaRDPopupAction.do?limparForm=sim&numeroRD=' + form.numero.value +   
				'&retornarTela=exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true');
    }
    
    function addLimitacaoGeografica() {
		var form  =   document.forms[0];
		form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true&addLimitacaoGeografica=true';
		submeterFormPadrao(form);
    }
    
    /* Recupera Dados Popup */
   	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   	    var form = document.forms[0];
   	 	
   	 	if (tipoConsulta == 'limitacaoGeograficaRd') {
   	    	form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?inserirLim=sim';
   	        form.submit();
   	 	}else if(tipoConsulta == 'usuario'){
			form.loginUsuario.value = codigoRegistro;
			form.nomeUsuario.value = descricaoRegistro;		
			form.nomeUsuario.style.color = "#000000";
			form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true&tipoPesquisa=usuario';
			submeterFormPadrao(form);
		}
   	            
	}
   	
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
		form.loginUsuario.focus();
	}  
	
 	function addUsuario() {
		var form  =   document.forms[0];

		if (form.indicadorAcessoRestrito[0].checked){
			form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true&associarUsuario=true';
			submeterFormPadrao(form);
		}	
				
    }
 	
	function habilitaAcessoRestrito() {
		var form  = document.forms[0];
 	
		if (form.indicadorUtilizacaoLivre[0].checked){
			form.indicadorAcessoRestrito[0].checked = false;
			form.indicadorAcessoRestrito[1].checked = true;
			form.indicadorAcessoRestrito[0].disabled = true;
			form.indicadorAcessoRestrito[1].disabled = true;
			form.botaoAssociar.disabled = true;
			
	 	}else{
			form.indicadorAcessoRestrito[0].disabled = false;
			form.indicadorAcessoRestrito[1].disabled = false;
			
			if (form.indicadorAcessoRestrito[1].checked){
				form.botaoAssociar.disabled = true;
			}else{
				form.botaoAssociar.disabled = false;
			}
			
	 	}
		
	}
	
	
	function limparColecaoUsuario() {
        var form = document.forms[0];
        form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true&limparColecaoUsuario=true';
    	submeterFormPadrao(form);
    }
 	
    /* Remove Componente da grid */
    function removerUsuario(posicao) {
        var form = document.forms[0];

		var where_to = confirm("Deseja realmente remover este usuário ?");
        if (where_to == true) {
            form.action = 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true&removerUsuario=sim&idRdRestricaoUsuario=' + posicao;
            form.submit();
        }
        
    }
    
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

-->
</script>

</head>

<logic:notPresent name="addLimitacaoGeograficaChamarPopup" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}'); habilitaAcessoRestrito();">
</logic:notPresent>
<logic:present name="addLimitacaoGeograficaChamarPopup" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:addLimitacaoGeograficaChamarPopup(); habilitaAcessoRestrito();">
</logic:present>

<html:form action="/atualizarResolucaoDiretoriaAction"
	name="AtualizarResolucaoDiretoriaActionForm"
	type="gcom.gui.cobranca.AtualizarResolucaoDiretoriaActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Resolu&ccedil;&atilde;o de Diretoria</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar uma resolução de diretoria, informe
					os dados abaixo:</td>
				</tr>
				<tr>
					<td width="218"><strong>N&uacute;mero RD:</strong></td>
					<td width="393"><html:text property="numero" size="15"
						maxlength="15" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
				<tr>
					<td><strong>Assunto RD:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="assunto" size="50" maxlength="50" tabindex="1" /></td>
				</tr>
				<tr>
					<td height="25"><strong>Data In&iacute;cio Vig&ecirc;ncia RD:<font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0" tabindex="2"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" style="cursor: pointer;cursor:hand;"
						onclick="javascript:abrirCalendario('AtualizarResolucaoDiretoriaActionForm', 'dataInicio')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td height="25"><strong>Data T&eacute;rmino Vig&ecirc;ncia RD:</strong></td>
					<td align="right">
					<div align="left"><html:text property="dataFim" size="10" tabindex="3"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" title="Exibir Calendário" style="cursor: pointer;cursor:hand;"
						onclick="javascript:abrirCalendario('AtualizarResolucaoDiretoriaActionForm', 'dataFim')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td><strong>Parcelamento &Uacute;nico? <font color="#FF0000">*</font></strong></td>
					<td><strong> 
					<html:radio property="indicadorParcelamentoUnico" value="1" tabindex="4"/> <strong>Sim 
					<html:radio	property="indicadorParcelamentoUnico" value="2" tabindex="5" /> N&atilde;o</strong> </strong></td>
				</tr>

				<tr>
					<td><strong>Válido A&ccedil;&atilde;o de Cobran&ccedil;a<font
						color="#FF0000">*</font></strong></td>
					<td><strong>
						<html:radio property="indicadorValidoAcaoCobranca" value="1" tabindex="6" /> <strong>Sim
						<html:radio	property="indicadorValidoAcaoCobranca" value="2" tabindex="7" /> N&atilde;o</strong>
					</strong></td>
				</tr>
				<tr>
					<td><strong>Descontos e San&ccedil;&otilde;es? <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorDescontoSancoes"	value="1" tabindex="8"/> <strong>Sim 
						<html:radio	property="indicadorDescontoSancoes"	value="2" tabindex="9"/> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Negociação só a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorNegociacaoSoAVista"	value="1" tabindex="8"/> <strong>Sim 
						<html:radio	property="indicadorNegociacaoSoAVista"	value="2" tabindex="9"/> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Desconto só em Conta para Pagamento a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorDescontoSoEmContaAVista"	value="1" tabindex="10"/> <strong>Sim 
						<html:radio	property="indicadorDescontoSoEmContaAVista"	value="2" tabindex="11"/> N&atilde;o</strong> </strong></td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelamento para Loja Virtual: <font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorParcelamentoLojaVirtual" value="1" /> 
						<strong>Sim 
							<html:radio property="indicadorParcelamentoLojaVirtual" value="2" /> Não
						</strong> 
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelas em Atraso: <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorParcelasEmAtraso" value="1" tabindex="12" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> <strong>Sim 
						<html:radio property="indicadorParcelasEmAtraso" value="2" tabindex="13" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelas em Atraso:</strong></td>
					<td width="393">
						<html:text property="idParcelasEmAtraso" size="10" maxlength="10" tabindex="14" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Parcelamento em Andamento: <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorParcelamentoEmAndamento" value="1" tabindex="15" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> <strong>Sim 
						<html:radio property="indicadorParcelamentoEmAndamento" value="2" tabindex="16" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelamento em Andamento:</strong></td>
					<td width="393">
						<html:text property="idParcelamentoEmAndamento" size="10" maxlength="10" tabindex="17" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
			
				
				<tr><td colspan="2"><hr></td></tr>
				<tr>
					<td width="200"><strong>Valores:</strong></td>
					<td width="363">
					</td>
				</tr>
				<tr>
					<td><strong>Mínimo:</strong>
						<html:text property="valorDebitoMinimo" size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13)" />
					</td>
					<td><strong>Máximo:</strong>
						<html:text property="valorDebitoMaximo" size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13)" />
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td><strong>Utiliza&ccedil;&atilde;o Livre? <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorUtilizacaoLivre"
						value="1" onclick="habilitaAcessoRestrito();limparColecaoUsuario();" /> <strong>Sim <html:radio
						property="indicadorUtilizacaoLivre"
						value="2" onclick="habilitaAcessoRestrito()"/> N&atilde;o</strong> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Acesso Restrito? <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorAcessoRestrito" value="1" onclick="habilitaAcessoRestrito();" /> <strong>Sim 
						<html:radio	property="indicadorAcessoRestrito" value="2" onclick="limparColecaoUsuario();habilitaAcessoRestrito();" /> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr> 
	              <td><strong>Login do usuário:</strong></td>
	              <td colspan="3"><span class="style2"><strong>
						<html:text property="loginUsuario"  size="11" maxlength="11" tabindex="4" style="text-transform: none;" 
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirAtualizarResolucaoDiretoriaAction.do?veioAdicionar=true', 'loginUsuario'); ">
						</html:text>
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].loginUsuario)">
						<img width="23" 
							height="21" 
							border="0" 
							style="cursor:hand;"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar usuário"/></a>
					
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
									<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="AtualizarResolucaoDiretoriaActionForm" property="loginUsuario">
								<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarResolucaoDiretoriaActionForm" property="loginUsuario">
								<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparUsuario()"> 
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
					</td>
	           	</tr>
				
				<tr>
                    <td></td>
                    <td align="right">
                    <div align="right">
                    	<input type="button" name="botaoAssociar" class="bottonRightCol" value="Associar"
                        	onclick="javascript:addUsuario();"></div>
                    </td>
                </tr>
				
				<tr>
					<td colspan="3"   align="center">
					<div style="min-height:60px;">
					
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#000000">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover </strong></td>

                           	<td width="45%" >
                           		<div align="center"><strong>Especificação</strong></div>
                           	</td>
                           
                           	<td width="45%" >
                           		<div align="center"><strong>Unidade</strong></div>
                           	</td>

						</tr>
						
						<logic:present name="colecaoRdRestricaoUsuario" scope="session">
							<%int cont1 = 0;%>
							<logic:iterate name="colecaoRdRestricaoUsuario" 
								scope="session"
								id="rdRestricaoUsuario" 
								type="RdRestricaoUsuario">

								<%	cont1 = cont1 + 1;
								if (cont1 % 2 == 0) {	%>
								<tr bgcolor="#cbe5fe">
							<%	} else {	%>
								<tr bgcolor="FFFFFF">
							<%	}	%>
								
								<td width="7%">
									<div align="center">
									<font color="#333333"> 
										<img width="14"
											height="14" 
											border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
											onclick="removerUsuario('<%=cont1%>');" alt="Remover" />
									</font>
									</div>
								</td>
								
								<td>
									<div align="center">
										<bean:write name="rdRestricaoUsuario"property="usuario.nomeUsuario" />
									</div>
								</td>

								<td>
									<div align="center">
									<logic:notEmpty name="rdRestricaoUsuario" property="usuario.unidadeOrganizacional">
										<bean:write name="rdRestricaoUsuario"property="usuario.unidadeOrganizacional.descricao" />
									</logic:notEmpty>	
									</div>
								</td>
								
								
								</tr>

							</logic:iterate>
						</logic:present>						
						
						
					</table>
					</div>
					</td>
				</tr>
				
				
				
				
				<tr><td colspan="2"><hr></td></tr>
				
				
			</table>
						
						

				
						
						
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left" colspan="2"><strong>Limitação Geográfica</strong></td>
					<td align="right"><input type="button" name="Button" class="bottonRightCol"
							value="Adicionar" onClick="javascript:addLimitacaoGeografica();">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="5%" bgcolor="#90c7fc" rowspan="2">
									<div align="center"><strong>Rem.?</strong></div>
								</td>
								<td width="11%" bgcolor="#90c7fc" rowspan="2">
									<div align="center"><strong>Dt Lim Venc Conta A Vista</strong></div>
								</td>
								<td width="11%" bgcolor="#90c7fc" rowspan="2">
									<div align="center"><strong>Dt Lim Venc Conta Parc</strong></div>
								</td>
								<td width="20%" bgcolor="#90c7fc" colspan="2" >
									<div align="center"><strong>Data Vigência</strong></div>
							   	</td>
							   	<td width="10%" bgcolor="#90c7fc" rowspan="2" >
									<div align="center"><strong>Gerência Regional</strong></div>
							   	</td>
							   	<td width="10%" bgcolor="#90c7fc" rowspan="2" >
							  		<div align="center"><strong>Unidade Negócio</strong></div> 
							   	</td>
							    <td width="6%" bgcolor="#90c7fc" rowspan="2" >
							   		<div align="center"><strong>Local.</strong></div>
							   	</td>
							   	<td width="8%" bgcolor="#90c7fc" rowspan="2" >
							   		<div align="center"><strong>Setor Comercial</strong></div>
							   	</td>
							   	<td width="8%" bgcolor="#90c7fc" rowspan="2" >
							   		<div align="center"><strong>Quadra</strong></div>
							   	</td>
							</tr>
							<tr bordercolor="#000000">
								<td bgcolor="#cbe5fe">
									<div align="center"><strong>Inicio</strong></div>
								</td>
								<td bgcolor="#cbe5fe">
									<div align="center"><strong>Fim</strong></div>
								</td>
							</tr>
						</table>
						
						<div style="width: 100%; height: 100; overflow: auto;">
						
						<table width="100%" bgcolor="#99CCFF">
							<%String cor = "#cbe5fe";%>
							<logic:present name="collectionRDHelper" scope="session">
								<%int cont = 0;%>
								<logic:iterate name="collectionRDHelper" id="limitacaoGeograficaRd" type="LimitacaoGeograficaRDHelper">
									<%	cont = cont + 1; %>
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
									<%} else {
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
									<%}%>
									
									<td width="7%">
										<div align="center"><font color="#333333"> <img width="14"
							             height="14" border="0"
							             src="<bean:message key="caminho.imagens"/>Error.gif"
								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){
								             	redirecionarSubmit('exibirAtualizarResolucaoDiretoriaAction.do?remover=sim&rdLimitacao=<%=cont%>&veioAdicionar=true');}" />
							            </font></div>
							       	</td>
									<td width="12%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="dataLimiteVencimentoContaVista"/> 
										</strong></div>
									</td>
									<td width="12%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="dataLimiteVencimentoContaParcelar"/> 
										</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>											
											<bean:write name="limitacaoGeograficaRd" property="dataVigenciaInicio"/>
										</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="dataVigenciaFim"/>
										</strong></div>
									</td>
									<td width="12%">
										<div align="left"><strong>											
											<bean:write name="limitacaoGeograficaRd" property="idGerenciaRegional"/>
										</strong></div>									</td>
									<td width="10%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="idUnidadeNegocio"/>											
										</strong></div>
									</td>
									<td width="8%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="idLocalidade"/>
										</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="codigoSetorComercial"/>
										</strong></div>
									</td>
									<td width="8%">
										<div align="left"><strong>
											<bean:write name="limitacaoGeograficaRd" property="numeroQuadra"/>
										</strong></div>
									</td>
								</logic:iterate>
							</logic:present>
						</table>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><font color="#FF0000">
						<logic:equal name="tipoRetorno" value="1" scope="request"> 
							<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Voltar"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarResolucaoDiretoriaAction.do?paginacao=sim'">
						</logic:equal>
						<logic:equal name="tipoRetorno" value="2" scope="request">
							<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Voltar"
								onClick="javascript:window.location.href='/gsan/filtrarResolucaoDiretoriaAction.do?page.offset=1'">
						</logic:equal>
						<input type="button" name="ButtonReset"
							class="bottonRightCol" value="Desfazer"
							onClick="javascript:desfazer();"> 
						<input type="button" name="ButtonCancelar" 
							class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right">
					  <gsan:controleAcessoBotao name="Button" value="Atualizar" onclick="javascript:validarForm(document.forms[0]);" url="atualizarResolucaoDiretoriaAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
	<script language="JavaScript">
	<!--
	habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);
	habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);
	//-->
	</script>
	<script>
	
			setCookie("desativaHistoryBack", "true", "1");
		
		</script>
</body>
</html:html>
