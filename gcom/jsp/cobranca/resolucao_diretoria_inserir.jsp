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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirResolucaoDiretoriaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){
				
		if(testarCampoValorZero(document.InserirResolucaoDiretoriaActionForm.numero, 'Número da RD') 
		&& testarCampoValorZero(document.InserirResolucaoDiretoriaActionForm.assunto, 'Assunto da RD')) { 		
			if(validateInserirResolucaoDiretoriaActionForm(form)){			
				if(validaTodosRadioButton(form)){
					//submeterFormPadrao(form);
					submitForm(form);			
   				}
			}
		}	
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
		
		if(validaRadioButton(form.indicadorAcessoRestrito,"Indicador Acesso Restrito.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorAcessoRestrito,"Indicador Acesso Restrito.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorNegociacaoSoAVista,"Indicador de Negociação só a Vista.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorNegociacaoSoAVista,"Indicador de Negociação só a Vista.")+"\n";
		}

		if(validaRadioButton(form.indicadorNegociacaoSoAVista,"Indicador de válido para Ação de Cobrança.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorNegociacaoSoAVista,"Indicador de válido para Ação de Cobrança.")+"\n";
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

   	function addLimitacaoGeograficaChamarPopup() {
		var form  =   document.forms[0];
	
		abrirPopup('exibirAdicionarLimitacaoGeograficaRDPopupAction.do?limparForm=true&numeroRD=' + form.numero.value +   
				'&retornarTela=exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true');
    }

   	function addLimitacaoGeografica() {
		var form  =   document.forms[0];
	
		form.action = 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true&addLimitacaoGeografica=true';
		submeterFormPadrao(form);
    }



    /* Remove Componente da grid */
    function remover(posicao) {
        var form = document.forms[0];

		var where_to = confirm("Deseja realmente remover esta limitação geografica ?");
        if (where_to == true) {
            form.action = 'exibirInserirResolucaoDiretoriaAction.do?remover=sim&idRdLimitacao=' + posicao;
            form.submit();
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
			form.action = 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true&associarUsuario=true';
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
        form.action = 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true&limparColecaoUsuario=true';
    	submeterFormPadrao(form);
    }
 	
    /* Remove Componente da grid */
    function removerUsuario(posicao) {
        var form = document.forms[0];

		var where_to = confirm("Deseja realmente remover este usuário ?");
        if (where_to == true) {
            form.action = 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true&removerUsuario=sim&idRdRestricaoUsuario=' + posicao;
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
	
	
	/* Recupera Dados Popup */
   	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   	    var form = document.forms[0];
   	 	
   	 	 if(tipoConsulta == 'usuario'){
			form.loginUsuario.value = codigoRegistro;
			form.nomeUsuario.value = descricaoRegistro;
			form.action = 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true&tipoPesquisa=usuario';
			submeterFormPadrao(form);
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



<div id="formDiv">

<html:form action="/inserirResolucaoDiretoriaAction"
	name="InserirResolucaoDiretoriaActionForm"
	type="gcom.gui.cobranca.InserirResolucaoDiretoriaActionForm"
	method="post"
	onsubmit="return validarForm(this);">

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
					<td class="parabg">Inserir Resolu&ccedil;&atilde;o de Diretoria</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a resolu&ccedil;&atilde;o de
					diretoria, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="218"><strong>N&uacute;mero RD:<font color="#FF0000">*</font></strong></td>
					<td width="393"><html:text property="numero" size="15"
						maxlength="15" /></td>
				</tr>
				<tr>
					<td><strong>Assunto RD:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="assunto" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td height="25"><strong>Data In&iacute;cio Vig&ecirc;ncia RD:<font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataFim,this);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InserirResolucaoDiretoriaActionForm', 'dataInicio')" 
						onfocus="replicarCampo(document.forms[0].dataFim,this);"/>
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td height="25"><strong>Data T&eacute;rmino Vig&ecirc;ncia RD:</strong></td>
					<td align="right">
					<div align="left"><html:text property="dataFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InserirResolucaoDiretoriaActionForm', 'dataFim')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td><strong>Parcelamento &Uacute;nico? <font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorParcelamentoUnico"
						value="1" /> <strong>Sim <html:radio
						property="indicadorParcelamentoUnico"
						value="2" /> N&atilde;o</strong> </strong></td>
				</tr>
				
			 	<tr>
					<td><strong>Indicador de v&aacute;lido para A&ccedil;&atilde;o de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorValidoAcaoCobranca" value="1" /> 
						<strong>Sim 
							<html:radio property="indicadorValidoAcaoCobranca" value="2" /> Não
						</strong> 
						</strong>
					</td>
				</tr>
			
				<tr>
					<td><strong>Descontos e San&ccedil;&otilde;es? <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorDescontoSancoes"
						value="1" /> <strong>Sim <html:radio
						property="indicadorDescontoSancoes"
						value="2" /> N&atilde;o</strong> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Negociação só a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorNegociacaoSoAVista"
						value="1" /> <strong>Sim <html:radio
						property="indicadorNegociacaoSoAVista"
						value="2" /> N&atilde;o</strong> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Desconto só em Conta para Pagamento a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorDescontoSoEmContaAVista"
						value="1" /> <strong>Sim <html:radio
						property="indicadorDescontoSoEmContaAVista"
						value="2" /> N&atilde;o</strong> </strong></td>
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
						<html:radio property="indicadorParcelasEmAtraso" value="1" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> <strong>Sim 
						<html:radio property="indicadorParcelasEmAtraso" value="2" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelas em Atraso:</strong></td>
					<td width="393">
						<html:text property="idParcelasEmAtraso" size="10" maxlength="10" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Parcelamento em Andamento: <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorParcelamentoEmAndamento" value="1" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> <strong>Sim 
						<html:radio property="indicadorParcelamentoEmAndamento" value="2" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="200"><strong>RD Parcelamento em Andamento:</strong></td>
					<td width="363">
						<html:text property="idParcelamentoEmAndamento" size="10" maxlength="10" onkeyup="verificaNumeroInteiro(this);" />
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
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirInserirResolucaoDiretoriaAction.do?veioAdicionar=true', 'loginUsuario'); ">
						</html:text>
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limpaForm=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Usuário" border="0" height="21" width="23">
						</a> 
						
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
									<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="InserirResolucaoDiretoriaActionForm" property="loginUsuario">
								<html:text property="nomeUsuario" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirResolucaoDiretoriaActionForm" property="loginUsuario">
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
				
                <tr>
                    <td><strong> <font color="#000000">Limitação Geográfica</font> </strong></td>
                    <td align="right">
                    <div align="right">
                    	<input type="button" 	
                    		name="Submit24"
                        	class="bottonRightCol" 
                        	value="Adicionar"
                        	onclick="javascript:addLimitacaoGeografica();"></div>
                    </td>
                </tr>

				<tr>
					<td colspan="5"   align="center">
					<div style="min-height:60px;">
					
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#000000">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover </strong></td>

                           	<td width="7%" >
                           		<div align="center"><strong>Dt. limite pag. &agrave; vista</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Dt. limite pag. parcelado</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Dt. vig&ecirc;ncia in&iacute;cio</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Dt. vig&ecirc;ncia fim</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Ger&ecirc;ncia</strong></div>
                           	</td>
                           
                           	<td width="10" >
                           		<div align="center"><strong>Unidade</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Localidade</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Setor</strong></div>
                           	</td>
                           
                           	<td width="10%" >
                           		<div align="center"><strong>Quadra</strong></div>
                           	</td>
						</tr>
						
						<logic:present name="collectionLimitacaoGeograficaRDHelper" scope="session">
							<%int cont = 0;%>
							<logic:iterate name="collectionLimitacaoGeograficaRDHelper" 
								scope="session"
								id="limitacaoGeograficaRd" 
								type="LimitacaoGeograficaRDHelper">

								<%	cont = cont + 1;
								if (cont % 2 == 0) {	%>
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
											onclick="remover('<%=cont%>');" alt="Remover" />
									</font>
									</div>
								</td>
								
								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd"property="dataLimiteVencimentoContaVista" />
									</div>
								</td>

								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd"property="dataLimiteVencimentoContaParcelar" />
									</div>
								</td>
								
								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd" property="dataVigenciaInicio" />
									</div>
								</td>

								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd"property="dataVigenciaFim" />
									</div>
								</td>
								
								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd"property="idGerenciaRegional" />
									</div>
								</td>
								
								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd"property="idUnidadeNegocio" />
									</div>
								</td>

								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd" property="idLocalidade" />
									</div>
								</td>

								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd" property="codigoSetorComercial" />
									</div>
								</td>

								<td>
									<div align="center">
										<bean:write name="limitacaoGeograficaRd" property="numeroQuadra" />
									</div>
								</td>
								
								</tr>

							</logic:iterate>
						</logic:present>						
						
						
					</table>
					<div>
					</td>
				</tr>
				
				
				
				

				<tr>
					<td></td>
					<td align="right">
						<div align="left">
						<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><font color="#FF0000"> <input name="Button" type="button" class="bottonRightCol"
						 value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirResolucaoDiretoriaAction.do?desfazer=true"/>'">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right" >
						<gsan:controleAcessoBotao name="Button" 
					  		value="Inserir" 
					  		onclick="javascript:validarForm(document.forms[0]);" 
					  		url="inserirResolucaoDiretoriaAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Inserir" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
		</table>
	

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

	<script language="JavaScript">
	<!--
	habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);
	habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);
	//-->
	</script>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>
</html:html>
