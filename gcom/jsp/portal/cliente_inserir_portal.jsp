<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.core.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.widget.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker-pt-BR.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
		
		<logic:present name="clienteCpfInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "CPF não é válido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="clienteCnpjInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "CNPJ não é válido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="dddInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "DDD não é válido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="nomeGenericoInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "Nome do Cliente contém alguma descrição que não pode ser cadastrada." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="nomeMenorDez" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "Nome do Cliente não pode ter menos que 10 caracteres." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="mensagemAvisoCadastro" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#mensagemAvisoCadastro'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="mensagemSucesso" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#mensagemSucesso'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
				});
			</script>
		</logic:present>
		<script type="text/javascript">
			$(document).ready(function(){
				var matricula = $('#matricula').html();
				var length = matricula.length;
				$('#matricula').html(matricula.substr(0, length - 1) + '.' + matricula.substr(length - 1, 1)); 
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function(){


				$('[name=numeroTelefone]').setMask();
				
				$('.confirm').live('click', function(){
					$.unblockUI();
				});
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
				$.datepicker.setDefaults($.datepicker.regional['pt-BR']);
				$('[name=dataNascimento]').datepicker({
					showOn : 'both',
					buttonImage : '/gsan/imagens/portal/icons/data.gif',
					maxDate : '+0d',
					buttonImageOnly : 'true',
					changeMonth: true,
					changeYear: true,
					buttonText : 'Clique no calendário para selecionar a Data de Nascimento'
				});

				if ($.browser.msie) {
					$('[name=dataNascimento]').parent().append('<input type="button" style="background: no-repeat url(/gsan/imagens/portal/icons/data.gif); border:none;position:absolute;right:-80px;top:26px;" id="imageDatepicker" onclick="javascript:setFocusData();" />');
					$('[name=dataNascimento]').parent().children('img:first').remove();
				}

				$('[name=dataEmissao]').datepicker({
					showOn : 'both',
					buttonImage : '/gsan/imagens/portal/icons/data.gif',
					maxDate : '+0d',
					buttonImageOnly : 'true',
					changeMonth: true,
					changeYear: true,
					buttonText : 'Clique no calendário para selecionar a Data de Emissão'
				});

				if ($.browser.msie) {
					$('[name=dataEmissao]').parent().append('<input type="button" style="background: no-repeat url(/gsan/imagens/portal/icons/data.gif); border:none;position:absolute;right:-80px;top:26px;" id="imageDatepicker" onclick="javascript:setFocusData();" />');
					$('[name=dataEmissao]').parent().children('img:first').remove();
				}

				$('#cadastroConfirmado').click(function(){
					var form =  document.forms[0];
					window.location = '/gsan/exibirServicosPortalCompesaAction.do?cadastroVirtual=sucesso&matricula='+form.matricula.value;
				});
				
				$('#cadastroConfirmadoComAviso').click(function(){
					var form =  document.forms[0];
					window.location = '/gsan/exibirServicosPortalCompesaAction.do';
				});
				
			});

			$.validateEmail = function (email) {
				er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
				return (er.exec(email))? true : false;
			};

			
			function setFocusData(){
				$('[name=dataNascimento]').focus();
			}

			function setFocusData(){
				$('[name=dataEmissao]').focus();
			}
			
			function showMessage(message){
				$('#message h3').text(message);
				$.blockUI({
					message : $('#message'),
					theme : true,
					title : 'Aviso',
					onBlock : function() {
						$('.ui-widget-overlay').removeClass('ui-widget-overlay');
					}
				});
			}
			
			var bCancel = false; 
			
			function validateInserirClientePortalActionForm(form) {                                                                   
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validarRequerido(form) && validateCaracterEspecial(form) && validateDate(form) &&  validateLong(form); 
			} 
			
			function caracteresespeciais () { 
				
				this.ab = new Array("nomeContato", "Nome do Contato possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				
				var form =  document.forms[0];
				if (  form.indicadorTipoPessoa.value == "1" ) {
					
					this.ac = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
					this.ad = new Array("nomeMae", "Nome da mae possui caracteres especiais.", new Function ("varName", " return this[varName];"));
					
				}	
			} 
			

			function DateValidations () {
				

				var form =  document.forms[0];
				if (  form.indicadorTipoPessoa.value == "1" ) {
					this.aa = new Array("dataNascimento", "Data de Nascimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
					this.ab = new Array("dataEmissao", "Data de Emissão inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
				}
			}

			function IntegerValidations () {
				var form =  document.forms[0];
				if (  form.ramalTelefone != null && form.ramalTelefone.value != null && form.ramalTelefone.value != "" ) {
			     	this.aa = new Array("ramalTelefone", "Ramal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
				}

				if (  form.indicadorTipoPessoa.value == "1" ) {
					this.ac = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				}	
			}
		
				
			function validarRequerido(form){
					
				var retorno = true;
				var msg = "";
					
				if (form.matricula.value.length < 1){
					msg = "Informe Matrícula <br />";
					form.matricula.focus();
				}
				
				if (form.nome.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o nome do cliente.<br />";
					}
					else{
						msg = "Informe o nome do cliente.<br />";
						form.nome.focus();
					}
				}

				if (form.email.value.length < 1 || !$.validateEmail(form.email.value)){
					
					if (msg.length > 0){
						msg = msg + "Informe um email válido<br />";
					}
					else{
						msg = "Informe um email válido<br />";
						form.email.focus();
					}
				}

				if (form.tipoTelefone.value == '-1'){
					
					if (msg.length > 0){
						msg = msg + "Informe o tipo do telefone<br />";
					}
					else{
						msg = "Informe o tipo do telefone<br />";
						form.tipoTelefone.focus();
					}
				}


				if (form.numeroTelefone.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o Numero<br />";
					}
					else{
						msg = "Informe o Numero<br />";
						form.numeroTelefone.focus();
					}
				}

				if (form.nomeContato.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o nome de contato.<br />";
					}
					else{
						msg = "Informe o nome de contato.<br />";
						form.nomeContato.focus();
					}
				}

				
				if (!(form.numeroTelefone.value.length >= 13 && form.numeroTelefone.value.length <= 14)) {
					if (msg.length > 0){
						msg = msg + "Número do Telefone deve conter entre 9 e 11 dígitos. EX:(XX) XXXX-XXXX.<br />";
					}
					else{
						msg = "Número do Telefone deve conter entra 9 e 11 dígitos. EX:(XX) XXXX-XXXX.<br />";
						form.nomeContato.focus();
					}

				} 

				if ( form.indicadorTipoPessoa.value == "1" ) {
					if (form.cpf.value.length < 1){
						
						if (msg.length > 0){
							msg = msg + "Informe o CPF<br />";
						}
						else{
							msg = "Informe o CPF<br />";
							form.cpf.focus();
						}
					}
					if (form.sexo.value == '-1'){
						
						if (msg.length > 0){
							msg = msg + "Informe o Sexo<br />";
						}
						else{
							msg = "Informe o Sexo<br />";
							form.sexo.focus();
						}
					}

					if (form.nomeMae.value.length < 1){
						
						if (msg.length > 0){
							msg = msg + "Informe o nome da mãe<br />";
						}
						else{
							msg = "Informe o nome da mãe<br />";
							form.nomeMae.focus();
						}
					}
					
					if (form.dataNascimento.value.length < 10 ){
						
						if (msg.length > 0){
							msg = msg + "Informe a Data de Nascimento <br />";
						}
						else{
							msg = "Informe a Data de Nascimento <br />";
							form.dataNascimento.focus();
						}
					}

					if (form.profissao.value == '-1'){
						
						if (msg.length > 0){
							msg = msg + "Informe a Profissão<br />";
						}
						else{
							msg = "Informe a Profissão<br />";
							form.profissao.focus();
						}
					}
					if (form.rg.value.length < 1 ){
						
						if (msg.length > 0){
							msg = msg + "Informe o Registro Geral <br />";
						}
						else{
							msg = "Informe o Registro Geral <br />";
							form.rg.focus();
						}
					}
					if (form.dataEmissao.value.length < 10 ){
						
						if (msg.length > 0){
							msg = msg + "Informe a Data de Emissão <br />";
						}
						else{
							msg = "Informe a Data de Emissão <br />";
							form.dataEmissao.focus();
						}
					}
					if(form.orgaoExpedidor.value == '-1'){
						
						if (msg.length > 0){
							msg = msg + "Informe o Órgão Expedidor <br />";
						}
						else{
							msg = "Informe o Órgão Expedidor <br />";
							form.orgaoExpedidor.focus();
						}
					}
					
					if(form.estado.value == '-1'){
						
						if (msg.length > 0){
							msg = msg + "Informe o Estado <br />";
						}
						else{
							msg = "Informe o Estado <br />";
							form.estado.focus();
						}
					}

					if(comparaData(form.dataEmissao.value,"<",form.dataNascimento.value)){
						if (msg.length > 0){
							msg = msg + "Data de Emissão não pode ser inferior a data de nascimento.  <br />";
						}
						else{
							msg = "Data de Emissão não pode ser inferior a data de nascimento. <br />";
							form.dataEmissao.focus();
						}
					}

					if ( comparaDataComDataAtual(form.dataEmissao.value , ">") ) {
						if (msg.length > 0){
							msg = msg + "Data de Emissão não pode ser superior a data atual.  <br />";
						}
						else{
							msg = "Data de Emissão não pode ser superior a data atual. <br />";
							form.dataEmissao.focus();
						}
					}

					if ( comparaDataComDataAtual(form.dataNascimento.value , ">") ) {
						if (msg.length > 0){
							msg = msg + "Data de Nascimento não pode ser superior a data atual.  <br />";
						}
						else{
							msg = "Data de Nascimento não pode ser superior a data atual. <br />";
							form.dataNascimento.focus();
						}
					}

				} else if ( form.indicadorTipoPessoa.value == "2" ) {

					if (form.cnpj.value.length < 1){
						
						if (msg.length > 0){
							msg = msg + "Informe o CNPJ<br />";
						}
						else{
							msg = "Informe o CNPJ<br />";
							form.cnpj.focus();
						}
					}
					if (form.ramoAtividade.value == '-1'){
						
						if (msg.length > 0){
							msg = msg + "Informe o Ramo de Atividade<br />";
						}
						else{
							msg = "Informe o o Ramo de Atividade<br />";
							form.ramoAtividade.focus();
						}
					}

				}
				

				if (msg.length > 0){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + msg + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					retorno = false;
				}
				
				return retorno;
			}
			
			function validarForm(form){
				 if ( validateInserirClientePortalActionForm(form) ) {
					desabilitaBotao();
					form.submit();
				 }
			}
			
			function habilitaBotao(){
				document.getElementById('btnEnviar').style.display = 'block';	
			}
			
			function desabilitaBotao(){
				document.getElementById('btnEnviar').style.display = 'none';
			}

			function habilitarCampos() {
				var form =  document.forms[0];
				
				form.action = 'exibirInserirClientePortalAction.do?tipoPessoa=' + form.indicadorTipoPessoa.value;
				form.submit();
			}

			
			function click(event) {
				if (event.button==2) {
					alert("Botão direito do mouse desabilitado.");
				}
			}
			
			document.onmousedown=click;

		</script>
		
		
	</head>
	<body onload="habilitaBotao();">
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
		
					<div id="barra-servicos">
						<h2>Serviços</h2>
					    <h3>Bem-vindo(a) </h3>
					    <h4><label style="font-size: 13px;">Matrícula: </label>
					   		<span id="matricula">${InserirClientePortalActionForm.matricula}</span>
					   
					    </h4>
					    <a href="exibirServicosPortalCompesaAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
					</div>
					<!-- Botão download Adobe Reader - Start -->
					<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
					<!-- Botão download Adobe Reader - End -->
					<logic:present scope="request" name="voltarServicos">
						<div id="seg-via-conta" class="serv-int" style="margin: 0px;">
							<a href="exibirServicosPortalCompesaAction.do?method=voltarServico" title="Voltar e selecionar outro serviço" class="btn-voltar-servicos">
						    	<img src="/gsan/imagens/portal/general/btn-voltar-servicos.gif" alt="Voltar e selecionar outro serviço" />
						    </a>
						</div>
					</logic:present>
				
				<div id="cadastro-cliente" class="serv-int" >
					
					<h3>Cadastro de Cliente<span>&nbsp;</span></h3><br><br>
                	<p>Endereco do imovel: <em><%= session.getAttribute("enderecoFormatado") %></em></p>
					
					<br>
	            	<html:form action="/inserirClientePortalAction.do"
						name="InserirClientePortalActionForm"
						type="gcom.gui.portal.InserirClientePortalAction" method="post"
						onsubmit="return validateInserirClientePortalActionForm(this);">
						
						<html:hidden property="matricula" value="${InserirClientePortalActionForm.matricula}" />
						
						<fieldset>
	                    	<legend>Cadastro de Cliente</legend>
	                    	
	                    	<table>
	                    		<tr>
	                    			<td style="text-align: left" >
										<span class="cmp-text-6">
											<label for="nome">Nome<font color="#F00">*</font>:</label>
											<html:text property="nome" size="51" maxlength="100" tabindex="1" 
											onkeypress="return campoTextoSemAcento(event, this);"
											onmousedown="click(event);"
											/>
										</span>
									</td>
									<td style="text-align: left">
										<span class="cmp-text-6">
											<label for="email">E-mail<font color="#F00">*</font>:</label>
											<html:text property="email" size="41" maxlength="40" tabindex="2"  alt="email" />
										</span>
	                    			</td>
	                    		</tr>
	                    		
	                    		<tr>
	                    			<td style="text-align: left" colspan="2">
										<span class="select-2" style="width:auto; ">
					                        <label for="tipoTelefone">Tipo de Telefone<font color="#F00">*</font>:</label>
												<html:select property="tipoTelefone" tabindex="3" style="width:140px">
													<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="tipoTelefone" labelProperty="descricao" property="id" />
												</html:select>
										</span>
										
										<span class="cmp-text-2">
											<label for="numeroTelefone">Telefone<font color="#F00">*</font>:</label>
											<html:text property="numeroTelefone" tabindex="5" onkeypress="return isCampoNumerico(event);" alt="phone" />
										</span>
										<span class="cmp-text-4">
											<label for="ramalTelefone">Ramal:</label>
											<html:text property="ramalTelefone" size="5" maxlength="4" tabindex="6" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" />
										</span>
	                    			</td>
	                    		</tr>
	                    		<tr>
	                    			<td style="text-align: left">
										<span class="cmp-text-6">
											<label for="nomeContato">Nome do Contato<font color="#F00">*</font>:</label>
											<html:text property="nomeContato" size="51" maxlength="50" 
											onkeypress="return campoTexto(event, this);"
											tabindex="7" />
										</span>
									</td>
								</tr>
	                    		<tr>
		                    		<td style="text-align: left">
			                    		<span class="select-3" style="width:auto;" >
					                        <label for="indicadorTipoPessoa">Tipo de Pessoa<font color="#F00">*</font>:</label>
												<logic:notPresent name="bloquearIndicadorPessoa" scope="request" >
													<html:select property="indicadorTipoPessoa" tabindex="8" onchange="javascript:habilitarCampos();" >
														<html:option value="1">FISICA</html:option>
														<html:option value="2">JURIDICA</html:option>
													</html:select>
												</logic:notPresent>
												
												<logic:present name="bloquearIndicadorPessoa" scope="request" >
													<html:select property="indicadorTipoPessoa" tabindex="8" disabled="true" >
														<logic:equal name="pessoa" value="1" scope="request">
															<html:option value="1">FISICA</html:option>
														</logic:equal>
														<logic:equal name="pessoa" value="2" scope="request">
															<html:option value="2">JURIDICA</html:option>
														</logic:equal>
													</html:select>
												</logic:present>
										</span>
									</td>
	                    		</tr>
	                    		
	                    		<logic:present name="tipoPessoaFisica" scope="request">
		                    		
		                    		<tr>
		                    			<td style="text-align: left" colspan="2">
											<span class="cmp-text-2">
												<label for="cpf">CPF<font color="#F00">*</font>:</label>
												<logic:notPresent name="bloquearCpf" scope="request">
													<html:text property="cpf" size="12" maxlength="11" tabindex="9" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" />
												</logic:notPresent>
												<logic:present name="bloquearCpf" scope="request">
													<html:text property="cpf" size="12" maxlength="11" tabindex="9" style="text-transform: none;" disabled="true" />
												</logic:present>
											</span>
											<span class="select-2" style="width:auto; " >
						                        <label for="sexo" >Sexo<font color="#F00">*</font>:</label>
												<html:select property="sexo" tabindex="10" style="width:170px" >
													<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="pessoaSexo" labelProperty="descricao" property="id" />
												</html:select>
											</span>
											<span class="select-3" style="width:auto;">
					                        <label for="profissao">Profissão<font color="#F00">*</font>:</label>
											<html:select property="profissao" tabindex="11" >
												<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="profissao" labelProperty="descricao" property="id" />
											</html:select>
										</span>
		                    			</td>
	                    			</tr>
	                    			<tr>
	                    			<td style="text-align: left" colspan="2">
	                    				
	                    				<span class="cmp-text-5" style="width: 130px">
			                            	<label for="dataNascimento" style="width:170px;">Data de Nascimento<font color="#F00">*</font>:</label>
											<html:text property="dataNascimento" size="10" maxlength="10" style="margin-top: 5px;" tabindex="12" onkeyup="javascript:mascaraData(this, event);" /> 
										</span>
										
										<span class="cmp-text-6">
												<label for="nomeMae">Nome da Mãe<font color="#F00">*</font>:</label>
												<html:text property="nomeMae" size="51" maxlength="50" 
												onkeypress="return campoTexto(event, this);"
												tabindex="13" />
											</span>
									</td>
									</tr>
	                    			
		                    		<tr>
		                    			<td style="text-align: left"colspan="2">
		                    				<span class="cmp-text-5">
												<label for="email">RG<font color="#F00">*</font>:</label>
												<html:text property="rg" size="14" maxlength="13" tabindex="14"  style="text-transform: none;" onkeypress="return isCampoNumerico(event);" />
											</span>
											
											<span class="cmp-text-5" style="width: 130px">
				                            	<label for="dataEmissao" style="width:170px;">Data de emissão<font color="#F00">*</font>:</label>
												<html:text property="dataEmissao" size="10" maxlength="10" style="margin-top: 5px;" tabindex="15" onkeyup="javascript:mascaraData(this, event);" /> 
											</span>
											
											<span class="select-2" style="width:auto;">
						                        <label for="orgaoExpedidor">Orgão Expedidor<font color="#F00">*</font>:</label>
													<html:select property="orgaoExpedidor" tabindex="16" style="width:135px">
														<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
														<html:options collection="orgaosExpedidores" labelProperty="descricaoAbreviada" property="id" />
													</html:select>
											</span>
											
											<span class="select-2" style="width:auto;">
						                        <label for="estado">Estado<font color="#F00">*</font>:</label>
													<html:select property="estado" tabindex="17" style="width:135px">
														<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
														<html:options collection="unidadesFederacao" labelProperty="sigla" property="id" />
													</html:select>
											</span>
		                    			</td>
		                    		</tr>
		                    		
	                    		</logic:present>
	                    		
	                    		<logic:present name="tipoPessoaJuridica" scope="request">
	                    			<tr>
		                    			<td style="text-align: left">
		                    				<span class="cmp-text-2">
												<label for="cnpj">CNPJ<font color="#F00">*</font>:</label>
												<logic:notPresent name="bloquearCpf" scope="request">
													<html:text property="cnpj" size="15" maxlength="14" tabindex="18" style="text-transform: none;" onkeypress="return isCampoNumerico(event);"/>
												</logic:notPresent>
												<logic:present name="bloquearCpf" scope="request">
													<html:text property="cnpj" size="15" maxlength="14" tabindex="18" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" disabled="true" />
												</logic:present>
											</span>
		                    			
		                    				<span class="select-2" style="width:auto;">
						                        <label for="ramoAtividade">Ramo de Atividade<font color="#F00">*</font>:</label>
													<html:select property="ramoAtividade" tabindex="19" style="width:135px">
														<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
														<html:options collection="ramoAtividade" labelProperty="descricao" property="id" />
													</html:select>
											</span>
											
		                    			</td>
		                    		</tr>
	                    		</logic:present>
	                    	</table>
							<input type="button" name="Button" class="btn-enviar" value="" id="btnEnviar"
								onClick="javascript:validarForm(document.forms[0]);" />
							
	                    
   	                    	<!-- END campos do outro formulário -->
	                    </fieldset>
						<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigatórios</div>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
				
		<logic:present name="mensagemSucesso" scope="request">
			<div id="mensagemSucesso" style="display:none; cursor: default;"> 
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
		        	<bean:write name="mensagemSucesso" scope="request" />
		        </h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" id="cadastroConfirmado">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="mensagemAvisoCadastro" scope="request">
			<div id="mensagemAvisoCadastro" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;"> 
			        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
			        	<bean:write name="mensagemAvisoCadastro" scope="request" />
			        </h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" id="cadastroConfirmadoComAviso">OK</a>
			</div>
		</logic:present>
	</body>
</html:html>
