<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.core.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.widget.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker-pt-BR.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<logic:present name="clienteCpfInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "O CPF informado é inválido." + '</h3>'
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
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "O CNPJ informado é inválido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="telefoneFixoInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "O Telefone Fixo informado é inválido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="telefoneCelularInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "O Telefone Celular informado é inválido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="dataInvalida" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "A Data de Nascimento informada é inválida." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="cepInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "O CEP informado é inválido." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="mensagemImovelNaoApto" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + ${mensagemValidacao} + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<script type="text/javascript">
		function getSelectedRadio(buttonGroup) {
		   if (buttonGroup[0]) { 
		      for (var i=0; i<buttonGroup.length; i++) {
		         if (buttonGroup[i].checked) {
		            return i
		         }
		      }
		   } else {
		      if (buttonGroup.checked) { return 0; }
		   }
		   return -1;
		} 

			function validarRequerido(form){
					
				var retorno = true;
				var msg = "";
				
				if (form.matricula.value.length < 1){
					msg = "Informe Matrícula.<br />";
					form.matricula.focus();
				}	
				if (form.nome.value.length < 1){
					if (msg.length > 0){
						msg = msg + "Informe Nome.<br />";
					}
					else{
						msg = "Informe Nome.<br />";
						form.nome.focus();
					}
				}	
				if (form.email.value.length > 0 && !$.validateEmail(form.email.value)){
					
					if (msg.length > 0){
						msg = msg + "Informe um email válido<br />";
					}
					else{
						msg = "Informe um email válido<br />";
						form.email.focus();
					}
				}	
				if (form.indicadorCpfCnpj[0].checked == true && form.dataNascimento.value.length < 1){
					if (msg.length > 0){
						msg = msg + "Informe Data de Nascimento.<br />";
					}
					else{
						msg = "Informe Data de Nascimento.<br />";
						form.nome.focus();
					}
				}
				if (form.indicadorCpfCnpj[0].checked == true && form.cpfCliente.value.length < 1){
					if (msg.length > 0){
						msg = msg + "Informe CPF.<br />";
					}
					else{
						msg = "Informe CPF.<br />";
						form.cpfCliente.focus();
					}
				}	
				if (form.indicadorCpfCnpj[1].checked == true && form.cnpjCliente.value.length < 1){
					if (msg.length > 0){
						msg = msg + "Informe CNPJ.<br />";
					}
					else{
						msg = "Informe CNPJ.<br />";
						form.cnpjCliente.focus();
					}
				}	
				if (form.idMunicipio.value == null || form.idMunicipio.value == '' || form.idMunicipio.value == '-1'){
					if (msg.length > 0){
						msg = msg + "Informe Município.<br />";
					}
					else{
						msg = "Informe Município.<br />";
						form.idMunicipio.focus();
					}
				}
				if (form.idBairro.value == null || form.idBairro.value == '' || form.idBairro.value == '-1'){
					if (msg.length > 0){
						msg = msg + "Informe Bairro.<br />";
					}
					else{
						msg = "Informe Bairro.<br />";
						form.idBairro.focus();
					}
				}
				if (form.logradouro.value.length < 1){
					if (msg.length > 0){
						msg = msg + "Informe Logradouro.<br />";
					}
					else{
						msg = "Informe Logradouro.<br />";
						form.logradouro.focus();
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
			
				if(validateCadastrarImovelSorteioActionForm(form)){

					form.action = 'cadastrarImovelSorteioAction.do';
		    		form.submit();
				}
			}

			var bCancel = false; 
			
			function validateCadastrarImovelSorteioActionForm(form) {                                                                   
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) /*&& validateCpf(form) && validateCnpj(form)*/; 
			} 
			
			function caracteresespeciais () { 
				var form =  document.forms[0];
				
				this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.ab = new Array("nome", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.ac = new Array("dataNascimento", "Data de Nascimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.ad = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));

				if (form.indicadorCpfCnpj[0].checked == true) {
					this.ae = new Array("cpfCliente", "Número de CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				} else {
					this.af = new Array("cnpjCliente", "Número de CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				}

				this.ag = new Array("logradouro", "Logradouro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.ah = new Array("numeroEndereco", "Número do Endereço possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.ai = new Array("complemento", "Complemento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				this.aj = new Array("cep", "CEP possui caracteres especiais.", new Function ("varName", " return this[varName];"));
				
				
			} 
			
			function DateValidations () {

				this.ac = new Array("dataNascimento", "Data de Nascimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
			}
			
			function IntegerValidations () { 

				this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
				this.ad = new Array("rg", "RG deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
				this.ah = new Array("numeroEndereco", "Número do Endereço deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
				
			} 
			
			function habilitarCampos() {
				var form =  document.forms[0];
				
				form.action = 'exibirCadastrarImovelSorteioAction.do?selecionaCpfCnpj=sim';
				form.submit();
			}

			function selecionarMunicipio() {
				var form =  document.forms[0];
				
				form.action = 'exibirCadastrarImovelSorteioAction.do?selecionarMunicipio=sim';
				form.submit();
			}
			
			function limparImovel() {
				var form = document.forms[0];

				form.action = 'exibirCadastrarImovelSorteioAction.do?limparImovel=sim';
				form.submit();
				
			}

			function limparImovelTecla() {
				var textTarget = document.getElementById("inscricaoImovel") 
				textTarget.firstChild.nodeValue = null;
					
			}

			function limparForm(form) {

				form.action = 'exibirCadastrarImovelSorteioAction.do?limpar=sim';
				form.submit();
			}

			function baixarRegulamento() {
				var form = document.forms[0];
				
				form.action = 'exibirCadastrarImovelSorteioAction.do?baixarRegulamento=sim';
				form.submit();
			}
			
			function habilitarBotaoCadastrar() {
				var form = document.forms[0];

				if (form.indicadorAceitaRegulamento.checked == true) {
					form.action = 'exibirCadastrarImovelSorteioAction.do?habilitaCadastrar=sim';
				} else {
					form.action = 'exibirCadastrarImovelSorteioAction.do?habilitaCadastrar=nao';
				}
				
				form.submit();
			}

			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		    	var form = document.forms[0];
		   
		    	if (tipoConsulta == 'imovel') {
		    		form.matricula.value = codigoRegistro;
					form.inscricaoImovel.value = descricaoRegistro;
					
					form.action = 'exibirCadastrarImovelSorteioAction.do';
					form.submit();
		    	}
		    }

		</script>
		 
		<script type="text/javascript">
		 $(document).ready(function(){

				$('[name=telefoneFixo]').setMask();
				
				$('[name=telefoneCelular]').setMask();
				
			
			
			$.datepicker.setDefaults($.datepicker.regional['pt-BR']);
			if (document.forms[0].dataNascimento.readOnly == false) {
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
			}
			
			$('.confirm').live('click', function(){
				$.unblockUI();
			});

			$.validateEmail = function (email) {
				var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
			    var check=/@[\w\-]+\./;
			    var checkend=/\.[a-zA-Z]{2,3}$/;
			    
			    if(((email.search(exclude) != -1)
			    		||(email.search(check)) == -1)
			    		||(email.search(checkend) == -1)) {
			    	return false;
			    } else {
			    	return true;
			    }
			};
			
		 });
			function setFocusData(){
				$('[name=dataNascimento]').focus();
			}
		
		</script>
	</head>
	
	<body onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	<div id="container">
	  <%@ include file="/jsp/portal/cabecalho_questionario_satisfacao.jsp"%>
	    	
	  <div id="content">
    	<div id="barra-servicos" style="margin-bottom: 20px;">
				<h2>Serviços</h2>
			    <h4 style="color: #006BBB; right: 180px; font-size: 16px; font-weight: bold;">CAMPANHA CONTA EM DIA - SORTEIO DE PRÊMIOS</h4>
				<logic:present name="idUsuarioLogado" scope="session">
			    	<a href="javascript:;" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
			    </logic:present>
		</div>
         <div id="fatura-email" class="serv-int" >
		        <h3>Dados do Cliente</h3>
		        <br /><br /><br />
		        <p class="info-3">O Nº da Matrícula pode ser encontrado no campo Matrícula em sua fatura mensal.</p>
				<html:form action="/cadastrarImovelSorteioAction.do"
					name="CadastrarImovelSorteioActionForm" 
					type="gcom.gui.portal.CadastrarImovelSorteioActionForm"
					method="post">

					<fieldset>
                    	<legend>CAMPANHA CONTA EM DIA - SORTEIO DE PRÊMIOS</legend>
                    	
                    	<table>
                    		<tr>
                    			<td style="text-align: left">
									<span class="cmp-text-2" style="width: 400px;">
										<label for="matricula">Matrícula:<font color="#F00">*</font></label>
										<logic:notPresent name="idUsuarioLogado" scope="session">
											<html:text property="matricula" size="10" maxlength="9" tabindex="1" style="text-transform: none;" 
												onkeypress="return isCampoNumerico(event);" />
										</logic:notPresent>
										<logic:present name="idUsuarioLogado" scope="session">
											<html:text property="matricula" size="10" maxlength="9" tabindex="1" style="text-transform: none;" 
												onkeypress="limparImovelTecla();return isCampoNumerico(event);"
												onkeyup="validaEnterComMensagem(event, 'exibirCadastrarImovelSorteioAction.do', 'matricula', 'Imóvel');"/>
											<a
												href="javascript:abrirPopup('exibirPesquisarImovelAction.do?limpaForm=S', 495, 300);"><img
												src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
												height="21" alt="Pesquisar" border="0" title="Pesquisar Imóvel" /></a>	
											<html:hidden property="inscricaoImovel" value="${CadastrarImovelSorteioActionForm.inscricaoImovel}" />
											<logic:present name="imovelInexistente" scope="session">
												<em id="inscricaoImovel" style="background-color:#CFCFCF; color: #ff0000">${CadastrarImovelSorteioActionForm.inscricaoImovel}</em>
											</logic:present> 
											<logic:notPresent name="imovelInexistente" scope="session">
												<em id="inscricaoImovel" style="background-color:#CFCFCF; color: #000000" title="Localidade.Setor.Quadra.Lote.Sublote">${CadastrarImovelSorteioActionForm.inscricaoImovel}</em>
											</logic:notPresent>
											<a href="javascript:limparImovel();"> 
												<img border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Imóvel"> 
											</a>
										</logic:present>
									</span>
								</td>
                    			<td style="text-align: left">
									<span>
										<label for="totalInscritos">
											Total de Inscritos:
										</label>
										 <em id="totalInscritos">${CadastrarImovelSorteioActionForm.totalInscritos}</em>
									</span>
                    			</td>
                    		</tr>
                   		</table>
                   		<table>
                    		<tr>
                    			<td style="text-align: left">
									<span class="cmp-text-6" >
										<label for="nome">Nome:<font color="#F00">*</font></label>
										<html:text property="nome" size="51" maxlength="50" tabindex="2" />
									</span>
									<span class="cmp-text-2">
										<label for="telefoneFixo">Telefone Fixo:</label>
										<html:text property="telefoneFixo" size="14" maxlength="13" tabindex="3" onkeypress="return isCampoNumerico(event);" alt="phone" />
									</span>
                    			</td>
                    		</tr>
                    		<tr>
                    			<td style="text-align: left">
									<span class="cmp-text-6" >
										<label for="email">E-mail:</label>
										<html:text property="email" size="41" maxlength="40" tabindex="4" style="text-transform: none;" />
									</span>
									<span class="cmp-text-2">
										<label for="telefoneCelular">Telefone Celular:</label>
										<html:text property="telefoneCelular" size="14" maxlength="13" tabindex="5" onkeypress="return isCampoNumerico(event);" alt="phone" />
									</span>
                    			</td>
                    		</tr>
                    		<tr>
                    			<td style="text-align: left">
									<logic:present name="exibirCpf" scope="session">
	                    				<span class="cmp-text-5" style="width: 135px">
			                            	<label for="dataNascimento" style="width:170px;">Data de Nascimento:<font color="#F00">*</font></label>
											<html:text property="dataNascimento" size="10" maxlength="10" style="margin-top: 5px;" tabindex="6" onkeyup="javascript:mascaraData(this, event);" />
										</span>
									</logic:present> 
									<logic:notPresent name="exibirCpf" scope="session">
										<span class="cmp-text-5-readonly" style="width: 135px">
			                            	<label for="dataNascimento" style="width:170px;">Data de Nascimento:</label>
											<html:text property="dataNascimento" readonly="true" size="10" maxlength="10" style="margin-top: 5px;" tabindex="6" onkeyup="javascript:mascaraData(this, event);" />
										</span>
									</logic:notPresent>
									
									<logic:present name="exibirCpf" scope="session">
										<span class="cmp-text-2" style="width: 200px; margin-left:50px;">
											<label for="rg">RG:</label>
											<html:text property="rg" size="14" maxlength="13" tabindex="7" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" />
										</span>
									</logic:present>
									<logic:notPresent name="exibirCpf" scope="session">
										<span class="cmp-text-2-readonly" style="width: 200px; margin-left:50px;">
											<label for="rg">RG:</label>
												<html:text property="rg" readonly="true" size="14" maxlength="13" tabindex="7" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" />
										</span>
									</logic:notPresent>
                    			</td>
                    		</tr>
                    		<tr>
	                    		<td style="text-align: left">
									<span class="cmp-text-2" style="width: 105px;">
											<label for="cpfCliente">Documento:<font color="#F00">*</font></label>
									</span>
									<span class="cmp-text-2" style="width: 70px;">
											<html:radio property="indicadorCpfCnpj" tabindex="8" value="1" style="width:auto;" styleId="sim" onchange="javascript:habilitarCampos();"/>
											<label for="indicadorCpfCnpj" style="float: left; padding-top: 6px; padding-left: 8px;">CPF</label>
									</span>
									<span class="cmp-text-2" style="width: 75px;">
											<html:radio property="indicadorCpfCnpj" tabindex="9" value="2" style="width:auto;" styleId="nao" onchange="javascript:habilitarCampos();"/>
											<label style="float: left; padding-top: 6px; padding-left: 8px;" for="indicadorCpfCnpj">CNPJ</label>
									</span>
									<span class="cmp-text-2" style="width: 80px;">
											<logic:present name="exibirCpf" scope="session">
												<html:text property="cpfCliente" size="15" maxlength="14" tabindex="10" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" onkeyup="javascript:mascaraCpf(this, event);" />
											</logic:present>
											<logic:notPresent name="exibirCpf" scope="session">
												<html:text property="cnpjCliente" size="19" maxlength="18" tabindex="10" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" onkeyup="javascript:mascaraCnpj(this, event);" />
											</logic:notPresent>
									</span>
								</td>
                    		</tr>
                    		<tr>
	                    		<td style="text-align: left">
									<span class="cmp-text-2" style="width: 105px;">
										<label for="indicadorTipoRelacao">Tipo de Relação com Imóvel:<font color="#F00">*</font></label>
									</span>
									<span class="cmp-text-2" style="width: 75px;">
											<html:radio property="indicadorTipoRelacao" tabindex="11" value="2" style="width:auto;" styleId="sim" />
											<label for="indicadorTipoRelacao" style="float: left; padding-top: 6px; padding-left: 8px;">Usuário</label>
									</span>
									<span class="cmp-text-2" style="width: 110px;">
											<html:radio property="indicadorTipoRelacao" tabindex="12" value="1" style="width:auto;" styleId="nao" />
											<label style="float: left; padding-top: 6px; padding-left: 8px;" for="indicadorTipoRelacao">Proprietário</label>
									</span>
								</td>
                    		</tr>
	                  		<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>
	                  		</table>
	                  		<br />
			        		<h3>Endereço do Imóvel</h3>
			        		<br />
	                  		<table>
	                  		<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>
	                  		<tr>
	                  			<td style="text-align: left">
								<span class="cmp-text-2" style="width: 265px;">
									<label for="idMunicipio">Município:<font color="#F00">*</font></label>
									<html:select property="idMunicipio" tabindex="13" style="width:280px" onchange="javascript:selecionarMunicipio();">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoMunicipio" labelProperty="nome" property="id" />
									</html:select>
								</span>
								<span class="cmp-text-2" style="width: 250px;">
									<label for="idBairro">Bairro:<font color="#F00">*</font></label>
									<logic:present name="colecaoBairro" scope="session">
										<html:select property="idBairro" tabindex="14" style="width:280px">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoBairro" labelProperty="nome" property="id" />
										</html:select>
									</logic:present>
									<logic:notPresent name="colecaoBairro" scope="session">
										<html:select property="idBairro" tabindex="14" style="width:280px" disabled="true">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										</html:select>
									</logic:notPresent>
								</span>
	                  			</td>
	                  		</tr>
	                   		<tr>
	                   			<td style="text-align: left">
									<span class="cmp-text-6">
										<label for="logradouro">Endereço (Rua, Avenida):<font color="#F00">*</font></label>
										<html:text property="logradouro" size="101" maxlength="100" tabindex="15" style="text-transform: none;" />
									</span>
	                   			</td>
	                   		</tr>
	                   		<tr>
	                   			<td style="text-align: left">
									<span class="cmp-text-4">
										<label for="numeroEndereco">Número:</label>
										<html:text property="numeroEndereco" size="6" maxlength="5" tabindex="16" style="text-transform: none;" onkeypress="return isCampoNumerico(event);"/>
									</span>
									<span class="cmp-text-2">
										<label for="complemento">Complemento:</label>
										<html:text property="complemento" size="26" maxlength="25" tabindex="17" style="text-transform: none;" />
									</span>
									<span class="cmp-text-2">
										<label for="cep">CEP:</label>
										<html:text property="cep" size="10" maxlength="9" tabindex="18" style="text-transform: none;" onkeypress="return isCampoNumerico(event);" onkeyup="javascript:mascaraCep(this, event);" />
									</span>
	                   			</td>
	                   		</tr>
	                  		<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>
	                   		<tr>
	                    		<td style="text-align: left">
									<span>
											<html:checkbox property="indicadorAceitaRegulamento" tabindex="19" onclick="javascript:habilitarBotaoCadastrar()" />
											<label style="float: left; padding-top: 6px; padding-left: 8px;">Li e Concordo com o <a href="javascript:baixarRegulamento();" alt="Regulamento do Sorteio" style="color: #008fd6;">Regulamento</a></label>
									</span>
								</td>
	                   		</tr>
	                  		<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>
	                  		<tr>
	                  			<td style="text-align: center; width: 400px;" >
								<span class="cmp-text-2">
									<label><font color="#F00">* Campos obrigatórios</font></label>
								</span>
	                  			</td>
	                  		</tr>
							<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>                    	
                    	</table>
                    	<table>
                    		<tr>
                    			<td>
									<input type="button" name="Button" class="btn-limpar" value=""
										onClick="javascript:limparForm(document.forms[0]);" />
									<logic:present name="habilitaCadastrar" scope="session">
										<input type="button" name="cadastrar" class="btn-cadastrar" value=""
											onClick="javascript:validarForm(document.forms[0]);validarRequerido(document.forms[0]);" />
									</logic:present>
									<logic:notPresent name="habilitaCadastrar" scope="session">
										<input type="button" name="cadastrar" class="btn-cadastrar-desabilitado" value="" 
											onClick="javascript:alert('Para concluir o cadastro, você deverá aceitar ler e concordar com o regulamento.'); " />
									</logic:notPresent>
								</td>
							</tr>
						</table>
                    
  	                    	<!-- END campos do outro formulário -->
                    </fieldset>
				</html:form>
            </div>
		</div>
    </div>
	</body>
</html:html>