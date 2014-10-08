<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirMaterialActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	var bCancel = false; 

    function validateRegistrarPesquisaSatisfacaoActionForm(form) {                                                                   
    	if (bCancel) 
      		return true; 
        else 
       		return validateCaracterEspecial(form) && validateRequired(form) && validateRadioRequired(form) && validateDate(form) && validateInteger(form);
   	}

	function caracteresespeciais () { 
	    this.aa = new Array("codigoDDD", "Código DDD possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("telefone", "Telefone possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("idImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ad = new Array("dataAtendimento", "Data do Atendimento possui caracteres especiais.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	    this.ae = new Array("horaAtendimento", "Hora do Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.af = new Array("idUnidade", "Unidade de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ag = new Array("nome", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
    
    function RadioRequiredValidations(){
    	this.aa = new Array("avaliacaoAtendente", "Informe a Avaliação do Atendente.", new Function("varName", " return this[varName];"));
	    this.ab = new Array("agilidadeAtendimento", "Informe a Avaliação da Agilidade do Atendendimento.", new Function("varName", " return this[varName];"));
	    this.ac = new Array("tempoEspera", "Informe a Avaliação do Tempo de Espera.", new Function("varName", " return this[varName];"));
	    this.ad = new Array("confortoAmbiente", "Informe a Avaliação do Conforto do Ambiente.", new Function("varName", " return this[varName];"));
	    this.ae = new Array("localizacaoAmbiente", "Informe a Avaliação da Localização do Ambiente.", new Function("varName", " return this[varName];"));
	    this.af = new Array("seguranca", "Informe a Avaliação da Segurança.", new Function("varName", " return this[varName];"));
	    this.ag = new Array("estacionamento", "Informe a Avaliação do Estacionamento.", new Function("varName", " return this[varName];"));
    }

    function required () { 
	    this.aa = new Array("idUnidade", "Informe Unidade de Atendimento.", new Function ("varName", " return this[varName];"));
	} 

    function DateValidations () { 
    	this.aa = new Array("dataAtendimento", "Data do Atendimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

    function IntegerValidations () { 
	    this.aa = new Array("codigoDDD", "Código DDD deve ser numérico positivo.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("telefone", "Telefone deve ser numérico.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("idImovel", "Imóvel deve ser numérico.", new Function ("varName", " return this[varName];"));
	    this.ad = new Array("idUnidade", "Unidade de Atendimento deve ser numérico.", new Function ("varName", " return this[varName];"));
    }
    
    function validarForm(){
		var form = document.forms[0];
		var horaAtendimento = form.horaAtendimento.value;
		var ddd = form.codigoDDD.value;
		var telefone = form.telefone.value;
		
		if (form.email.value != "" && !$.validateEmail(form.email.value)){
			alert("Email inválido");
			return;
		}
		
		if(horaAtendimento != "" && validaHoraMinuto(horaAtendimento) == false){
			return;
		}
		
		if((ddd != "" && telefone == "") || (ddd == "" && telefone != "")){
			alert("Digite o DDD e o telefone");
			return;
		}
		
		if(validateRegistrarPesquisaSatisfacaoActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function limparForm() {
		var form = document.forms[0];
		
		form.nome.value = "";
		form.codigoDDD.value = "";
		form.telefone.value = "";
		form.email.value = "";
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idUnidade.value = "";
		form.descricaoUnidade.value = "";
		form.dataAtendimento.value = "";
		form.horaAtendimento.value = "";
		form.comentarios.value = "";
	
		formatarRadio();	
	}
	
	function formatarRadio(){
		var form = document.forms[0];
		
		form.avaliacaoAtendente[4].checked = true;
		form.agilidadeAtendimento[4].checked = true;
		form.tempoEspera[4].checked = true;
		form.confortoAmbiente[4].checked = true;
		form.localizacaoAmbiente[4].checked = true;
		form.seguranca[4].checked = true;
		form.estacionamento[4].checked = true;
	}
	
	function limparImovel() {
	 	document.forms[0].idImovel.value = '';
	 	document.forms[0].descricaoImovel.value = '';
	}
	
	function limparUnidadeAtendimento(){
		document.forms[0].idUnidade.value = '';
		document.forms[0].descricaoUnidade.value = '';
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
		form.action = 'exibirRegistrarPesquisaSatisfacaoAction.do';
	    if (tipoConsulta == 'imovel') {
		    form.idImovel.value = codigoRegistro;
		    form.descricaoImovel.value = descricaoRegistro;
		    form.descricaoImovel.style.color = "#000000";
			submeterFormPadrao(form);
	    }
	    
	    if(tipoConsulta == 'unidadeOrganizacional') {
	    	form.idUnidade.value = codigoRegistro;
      		form.descricaoUnidade.value = descricaoRegistro;
      		form.descricaoUnidade.style.color = "#000000";
	    	submeterFormPadrao(form);	
	    }    
	}
	
	//Função criada especialmente para o firefox
	function validarDataAtendimento(form){
		if (form.dataAtendimento.value.length > 0){
			if (verificaDataMensagemPersonalizada(form.dataAtendimento, "Data do Atendimento inválida.")){
				redirecionarSubmit('exibirRegistrarPesquisaSatisfacaoAction.do');
			}
		}
	}
	
</script>

<script type="text/javascript">
	$.validateEmail = function (email) {
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
		return (er.exec(email))? true : false;
	};
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/registrarPesquisaSatisfacaoAction" method="post"
	name="RegistrarPesquisaSatisfacaoActionForm"
	type="gcom.gui.atendimentopublico.RegistrarPesquisaSatisfacaoActionForm"
	onsubmit="return validateRegistrarPesquisaSatisfacaoActionForm(this);">
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
					<td class="parabg">Registrar Pesquisa de Satisfa&ccedil;&atilde;o do Cliente na Loja de Atendimento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="162"><strong>Nome:</strong></td>
					<td><strong><html:text property="nome" size="50" maxlength="50" /> </strong></td>
				</tr>
				<tr>
	                <td><strong> Código DDD:</strong></td>
	                <td><strong><html:text property="codigoDDD" size="2" maxlength="2" tabindex="3" onkeypress="return isCampoNumerico(event);"/></strong></td>
                </tr>
				<tr>
					<td width="162"><strong>Número do Telefone:</strong></td>
					<td><strong><html:text property="telefone" tabindex="4" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" /> </strong></td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td><strong><html:text property="email" size="40" maxlength="40" tabindex="6" style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>
					<td width="25%"><strong>Matrícula do Imóvel:</strong></td>
					<td width="75%"><html:text maxlength="9" name="RegistrarPesquisaSatisfacaoActionForm" property="idImovel" size="9"
						onkeypress="javascript:validaEnter(event, 'exibirRegistrarPesquisaSatisfacaoAction.do?tipoConsulta=imovel', 'idImovel');return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 410, 790);">
							<img width="23" title="Pesquisar Imóvel" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" />
						</a>
						<logic:present name="idImovelNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoImovel" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" size="36" />
						</logic:present> 
						<logic:notPresent name="idImovelNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoImovel" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" size="36" />
						</logic:notPresent>
						<a href="javascript:limparImovel();"> <img
							border="0" src="imagens/limparcampo.gif" title="Limpar" height="21" width="23">
						</a>
					</td>
				</tr>
				<tr>
			      	<td height="30"><strong>Data do Atendimento:</strong></td>
			        <td>
			        	<html:text property="dataAtendimento" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendario('RegistrarPesquisaSatisfacaoActionForm', 'dataAtendimento');">
						<img title="Calendário" border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="4" /></a>
						<strong>&nbsp;(dd/mm/aaaa)</strong>
					</td>
				</tr>
				<tr>
			      	<td height="30"><strong>Hora do Atendimento:</strong></td>
			        <td>
						<html:text property="horaAtendimento" size="10" maxlength="5" tabindex="5" onkeyup="mascaraHoraSemMensagem(this, event)" onkeypress="return isCampoNumerico(event);"/>
						<strong>&nbsp;(hh:mm)</strong>
					</td>
		      	</tr>
				<tr>
			    	<td HEIGHT="30"><strong>Unidade de Atendimento:<font color="#FF0000">*</font></strong></td>
			        <td colspan="2">
			        	<html:text property="idUnidade" size="5" maxlength="4" tabindex="8" onkeypress="validaEnter(event, 'exibirRegistrarPesquisaSatisfacaoAction.do?tipoConsulta=unidade', 'idUnidade');return isCampoNumerico(event);"/>
						<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento">
						</a>
						<logic:present name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoUnidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" />
						</logic:present> 
						<logic:notPresent name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoUnidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" size="40" />
						</logic:notPresent>
			        	<a href="javascript:limparUnidadeAtendimento();">
			        		<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar">
			        	</a>
					</td>
			    </tr>
				<tr>
			      	<td height="30" width="200"><strong>Avalia&ccedil;&atilde;o do Atendente<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="avaliacaoAtendente" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="avaliacaoAtendente" value="4" tabindex="2" />Bom
						<html:radio property="avaliacaoAtendente" value="3" tabindex="3" />Regular
						<html:radio property="avaliacaoAtendente" value="2" tabindex="4" />Ruim
						<html:radio property="avaliacaoAtendente" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
				<tr>
			      	<td height="30" width="200"><strong>Agilidade do Atendimento<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="agilidadeAtendimento" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="agilidadeAtendimento" value="4" tabindex="2" />Bom
						<html:radio property="agilidadeAtendimento" value="3" tabindex="3" />Regular
						<html:radio property="agilidadeAtendimento" value="2" tabindex="4" />Ruim
						<html:radio property="agilidadeAtendimento" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
		     	<tr>
			      	<td height="30" width="200"><strong>Tempo de Espera<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="tempoEspera" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="tempoEspera" value="4" tabindex="2" />Bom
						<html:radio property="tempoEspera" value="3" tabindex="3" />Regular
						<html:radio property="tempoEspera" value="2" tabindex="4" />Ruim
						<html:radio property="tempoEspera" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		   		</tr>
		     	<tr>
			      	<td height="30" width="200"><strong>Conforto e Limpeza do Ambiente<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="confortoAmbiente" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="confortoAmbiente" value="4" tabindex="2" />Bom
						<html:radio property="confortoAmbiente" value="3" tabindex="3" />Regular
						<html:radio property="confortoAmbiente" value="2" tabindex="4" />Ruim
						<html:radio property="confortoAmbiente" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
		     	<tr>
			      	<td height="30" width="200"><strong>Localiza&ccedil;&atilde;o do Atendentimento<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="localizacaoAmbiente" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="localizacaoAmbiente" value="4" tabindex="2" />Bom
						<html:radio property="localizacaoAmbiente" value="3" tabindex="3" />Regular
						<html:radio property="localizacaoAmbiente" value="2" tabindex="4" />Ruim
						<html:radio property="localizacaoAmbiente" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
		     	<tr>
			      	<td height="30" width="200"><strong>Seguran&ccedil;a<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="seguranca" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="seguranca" value="4" tabindex="2" />Bom
						<html:radio property="seguranca" value="3" tabindex="3" />Regular
						<html:radio property="seguranca" value="2" tabindex="4" />Ruim
						<html:radio property="seguranca" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
		     	<tr>
			      	<td height="30" width="200"><strong>Estacionamento<font color="#FF0000">*</font></strong></td>
			        <td>
						<html:radio property="estacionamento" value="5" tabindex="1" /><strong>Otimo
						<html:radio property="estacionamento" value="4" tabindex="2" />Bom
						<html:radio property="estacionamento" value="3" tabindex="3" />Regular
						<html:radio property="estacionamento" value="2" tabindex="4" />Ruim
						<html:radio property="estacionamento" value="1" tabindex="5" />N&atilde;o Informado</strong>
					</td>
		     	</tr>
				<tr>
			      	<td height="30"><strong>Comentários e sugestões para melhorias do servi&ccedil;os:</strong></td>
			        <td>     
						<html:textarea property="comentarios" cols="40" rows="4" /><br> 
						<!-- onkeyup="limitTextArea(document.forms[0].comentarios, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>-->
						<!-- <strong><span id="utilizado">0</span>/<span id="limite">400</span></strong>-->
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
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
							onclick="javascript:limparForm();">
						<input tabindex="10" name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Concluir"
						onclick="javascript:validarForm();" />
					</td>
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
</body>
</html:html>

