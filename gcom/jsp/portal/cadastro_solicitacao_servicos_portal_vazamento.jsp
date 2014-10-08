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

		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				habilitarDesabilitar();
				
				$('[name=telefoneContato]').setMask();
				
				var limitNum1 = 200;
				limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				$('[name=pontoReferencia]').bind("keyup change blur drop",function(){

					var value = $(this).val();
				    var tam = $(this).val().length;	

				    if(tam > limitNum1){
				    	$(this).val(value.substring(0, limitNum1));
				    }
				    
					limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				});
				
				$('[name=matricula]').bind("keyup change drop",function(e){
					
					 e.preventDefault();
					 $('#imovelException').fadeOut("slow");
					 somente_numero_zero_a_nove($(this).get(0));
					 var tam = $(this).val().length;
				    
				     if(tam == 0){
				    	$('[name=localOcorrencia]').attr("readonly",false);
				     }
				     else{
				    	$('[name=localOcorrencia]').attr("readonly",true);
				    	$('[name=localOcorrencia]').val("");
				     }
				}).bind("blur",function(e){
					
					 e.preventDefault();
					 $('#imovelException').fadeOut("slow");
					 somente_numero_zero_a_nove($(this).get(0));
					 var tam = $(this).val().length;
					 
					if(tam == 0 && $('[name=nomeUsuario]').val().length > 0){
				    	$('[name=localOcorrencia]').removeAttr("readonly");
						redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do?pesquisarMatricula=sim');
					}
					else if(tam > 0){
					    	$('[name=localOcorrencia]').attr("readonly",true);
					    	$('[name=localOcorrencia]').val("");
					}					
				});
				
				limitTextArea(document.forms[0].localOcorrencia, limitNum1, document.getElementById('utilizado'), document.getElementById('limite'));
				$('[name=localOcorrencia]').bind("keyup change blur drop",function(){

					var value = $(this).val();
				    var tam = $(this).val().length;

				    $('#imovelException').fadeOut("slow");
				    
				    if(tam == 0){
				    	$('[name=matricula]').attr("readonly",false);
				    }
				    else{
				    	$('[name=matricula]').attr("readonly",true);
				    	$('[name=matricula]').val("");
				    }
				    
				    if(tam > limitNum1){
				    	$(this).val(value.substring(0, limitNum1));
				    }
				    
					limitTextArea(document.forms[0].localOcorrencia, limitNum1, document.getElementById('utilizado'), document.getElementById('limite'));
				});
				
				var limitNum = 380;
				limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado3'), document.getElementById('limite3'));
				$('[name=observacoes]').bind("keyup change blur drop",function(){

					var value = $(this).val();
				    var tam = $(this).val().length;

				    if(tam > limitNum){
				    	$(this).val(value.substring(0, limitNum));
				    }
				    
				    limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado3'), document.getElementById('limite3'));
				});
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
			});
			
			function habilitarDesabilitar(){
				if($('[name=matricula]').val().length == 0 && $('[name=localOcorrencia]').val().length > 0 ){
					$('[name=matricula]').val("");
					$('[name=matricula]').attr("readonly",true);
				}
				else if($('[name=localOcorrencia]').val().length == 0 && $('[name=matricula]').val().length > 0 ){
					$('[name=localOcorrencia]').val("");
					$('[name=localOcorrencia]').attr("readonly",true);
				} else {
					$('[name=matricula]').attr("readonly",false);
					$('[name=localOcorrencia]').attr("readonly",false);
				}
			}
		
			var inputComFocus = null;
			
			$.validateEmail = function (email) {
				er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
				return (er.exec(email))? true : false;
			};			

			var bCancel = false; 
			
			function validateInserirSolicitacaoServicosPortalActionForm() {   
				var form = document.InserirSolicitacaoServicosPortalActionForm;                                                                
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validateCaracterEspecial(form) ; 
			} 
			
			function caracteresespeciais () { 
				
				this.aa = new Array("nomeSolicitante", "Nome Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function validarForm(){
				var form = document.InserirSolicitacaoServicosPortalActionForm;
				var retorno = true;
				var msg = '';

				if($('[name=matricula]').val().length < 1 && $('[name=localOcorrencia]').val().length < 1){
					if (msg.length > 0) 
						msg += '<br />';
					msg += ' - Imóvel ou Local de Ocorrência';
					$('[name=matricula]').focus(function(){
						inputComFocus = $(this);
					}).focus();
				}			
				
				if($('[name=nomeSolicitante]').val().length < 1){
					if (msg.length > 0) 
						msg += '<br />';
					msg += ' - Nome do solicitante';
					$('[name=nomeSolicitante]').focus(function(){
						inputComFocus = $(this);
					}).focus();
				}
				
				if ($('[name=telefoneContato]').val().length < 1 ){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=telefoneContato]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Telefone para contato';
				}
				
				if ($('[name=pontoReferencia]').val().length < 1 ){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=pontoReferencia]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Ponto de Referência';
				}

				if ($('[name=solicitacaoTipo] option:selected').val() <= 0){
					
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=solicitacaoTipo]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Tipo de solicitação';
				}
				
				if ($('[name=especificacao] option:selected').val() <= 0){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=solicitacaoTipo]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Especificação';
				}
				
				if(($('[name=solicitacaoTipo] option:selected').val() == 601 || $('[name=solicitacaoTipo] option:selected').val() == 701)){
					if($('[name=cpfCnpj]').val().length < 1){
						if (msg.length > 0) 
							msg += '<br />';
						msg += ' - CPF/CNPJ';
						$('[name=cpfCnpj]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					} 

					if ($('[name=municipio] option:selected').val() <= 0){
						
						if (msg.length > 0) {
							msg += '<br />';
						} else {
							$('[name=municipio]').focus(function(){
								inputComFocus = $(this);
							}).focus();
						}
						msg += ' - Município';
					}

					if ($('[name=bairro] option:selected').val() <= 0){
						
						if (msg.length > 0) {
							msg += '<br />';
						} else {
							$('[name=bairro]').focus(function(){
								inputComFocus = $(this);
							}).focus();
						}
						msg += ' - Bairro';
					}

					if ($('[name=pavimentoRua] option:selected').val() <= 0){
						
						if (msg.length > 0) {
							msg += '<br />';
						} else {
							$('[name=pavimentoRua]').focus(function(){
								inputComFocus = $(this);
							}).focus();
						}
						msg += ' - Pavimento Rua';
					}

					if ($('[name=pavimentoCalcada] option:selected').val() <= 0){
						
						if (msg.length > 0) {
							msg += '<br />';
						} else {
							$('[name=pavimentoCalcada]').focus(function(){
								inputComFocus = $(this);
							}).focus();
						}
						msg += ' - Pavimento Calçada';
					}
					
				}
				
				if (form.telefoneContato.value.length != 0 && !(form.telefoneContato.value.length >=13  && form.telefoneContato.value.length <=14) ){
					if (msg.length > 0){
						msg += '<br />';
						msg = msg + "Número do telefone para contado deve conter entre 9 e 11 dígitos. EX:(XX) XXXX-XXXX.<br />";
					}
					else{
						msg = "Número do telefone para contado deve conter entra 9 e 11 dígitos. EX:(XX) XXXX-XXXX.";
					}

					if (msg.length > 0){
						msg = "Número inválido.<br /><span style='font-weight:normal;'>" + msg + "</span>"; 
						$.blockUI({
							message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">' + msg + '</h3>'
									 +'<br /><a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
							theme : true,
							title : 'Aviso'
						});
						
						retorno = false;
					}
				}			
				
				if (msg.length > 0){
					msg = "Os campos abaixo são obrigatórios:<br /><span style='font-weight:normal;'>" + msg + "</span>"; 
					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">' + msg + '</h3>'
								 +'<br /><a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					
					retorno = false;
				}
				
				if(retorno && $.trim($('[name=email]').val()).length > 0 && !$.validateEmail($('[name=email]').val())) {
					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">O e-mail informado é inválido</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					
					inputComFocus = $('[name=email]');

					retorno = false;
				}
				
				$('.confirm').live('click', function(){
					$.unblockUI();
					inputComFocus.focus()
				});

				if (retorno) {
				retorno =	validateInserirSolicitacaoServicosPortalActionForm()
				}
				
				return retorno;
			}
		
			function carregarEspecificacao(){
				if (parseInt($('[name=solicitacaoTipo] option:selected').val()) > 0){
					redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do');
				}
			}
			
			function calcularDataPrevista(){
				if (parseInt($('[name=especificacao] option:selected').val()) > 0){
					redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do');
				}
			}

			function carregarBairro(){
				if (parseInt($('[name=municipio] option:selected').val()) > 0){
					redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do?carregarBairro=sim');
				}
			}
			
			function novoArquivo(){
				redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do?novoArquivo=ok');
			}

		    function addElement() {
		    	var form = document.InserirSolicitacaoServicosPortalActionForm;
	            var ni = document.getElementById('anexar');
	            var input = document.createElement("input");
	            input.type="file";

	        		
	            
				if ( form.arquivo1 == null ) {
					var newdivleft = document.createElement('div');
			        var divLeftIdName = 'my1DivLeft';
			        newdivleft.setAttribute('id',divLeftIdName);
					input.id="arquivo1" ;
		            input.name="arquivo1" ;
		            newdivleft.appendChild(input); 
		            newdivleft.innerHTML =  newdivleft.innerHTML + "<A HREF='javascript:void(0)'; onclick='remover("+1+");'  > Remover</A> <br><br>"
		            document.getElementById('anexarNovo').style.display = 'block';
		            ni.appendChild(newdivleft);
		    	} else if (form.arquivo2 == null )	{
		    		var newdivleft = document.createElement('div');
			        var divLeftIdName = 'my2DivLeft';
			        newdivleft.setAttribute('id',divLeftIdName);
		    		input.id="arquivo2" ;
		            input.name="arquivo2" ;
		            newdivleft.appendChild(input);
		    		newdivleft.innerHTML = newdivleft.innerHTML + "<A HREF='javascript:void(0)'; onclick='remover("+2+");' > Remover</A> <br><br>"
		    		document.getElementById('anexarNovo').style.display = 'block';
		    		ni.appendChild(newdivleft);
				} else if (form.arquivo3 == null )	{
					var newdivleft = document.createElement('div');
			        var divLeftIdName = 'my3DivLeft';
			        newdivleft.setAttribute('id',divLeftIdName);
					input.id="arquivo3" ;
		            input.name="arquivo3" ;
		            newdivleft.appendChild(input);
					newdivleft.innerHTML = newdivleft.innerHTML + "<A HREF='javascript:void(0)'; onclick='remover("+3+");' > Remover</A> <br><br>"
					document.getElementById('anexarNovo').style.display = 'block';
					ni.appendChild(newdivleft);
				} else if (form.arquivo4 == null )	{
					var newdivleft = document.createElement('div');
		            var divLeftIdName = 'my4DivLeft';
		            newdivleft.setAttribute('id',divLeftIdName);
					input.id="arquivo4" ;
		            input.name="arquivo4" ;
		            newdivleft.appendChild(input);
					newdivleft.innerHTML = newdivleft.innerHTML + "<A HREF='javascript:void(0)'; onclick='remover("+4+");' > Remover</A> <br><br>"
					document.getElementById('anexarNovo').style.display = 'block';
					ni.appendChild(newdivleft);
				} 

				if(form.arquivo1 != null && form.arquivo2 != null && form.arquivo3 != null && form.arquivo4 != null ) {
					document.getElementById('anexarNovo').style.display = 'none';
				}   
		    }

		    function remover(arquivo){
				var form = document.InserirSolicitacaoServicosPortalActionForm;
		        var ni = document.getElementById('anexar');
		        var newdivleft = document.getElementById('my' + arquivo + 'DivLeft');
		        if(window.event){ // Internet Explorer
		        	if ( arquivo == "1") {
						form.arquivo1.value = null;
					} else if ( arquivo == "2" ) {
						form.arquivo2.value = null;
					}  else if ( arquivo == "3" ) {
						form.arquivo3.value = null;
					}  else if ( arquivo == "4" ) {
						form.arquivo4.value = null;
					}
			 }else { // Nestcape ou Mozilla
				 if ( arquivo == "1") {
						form.arquivo1 = null;
					} else if ( arquivo == "2" ) {
						form.arquivo2 = null;
					}  else if ( arquivo == "3" ) {
						form.arquivo3 = null;
					}  else if ( arquivo == "4" ) {
						form.arquivo4 = null;
					}
			 }


		        
		        document.getElementById('anexarNovo').style.display = 'block';
		        ni.removeChild(newdivleft);
		        var newdivcenter = document.getElementById('my' + index + 'DivCenter');
		        
		        ni.removeChild(newdivcenter);
		        

		    }
		    
		    function exibirOuEsconderAnexo(){
		    	var form = document.InserirSolicitacaoServicosPortalActionForm;

		    	if ( form.especificacao.value != null && form.especificacao.value != "-1" ) {
		    	 	document.getElementById('anexarNovo').style.display = 'block';
		    	 	document.getElementById('textoAnexarArquivo').style.display = 'block';	
		   		} else {
		    	 	document.getElementById('anexarNovo').style.display = 'none';
		    	 	document.getElementById('textoAnexarArquivo').style.display = 'none';	    	 	
				}
			}
		    
		    function pesquisarMatricula(){
		    	redirecionarSubmit('exibirInserirSolicitacaoServicosPortalVazamentoAction.do?pesquisarMatricula=sim');	
		    }

		    function bloquearCampos(){
				if($('[name=matricula]').val().length == 0){
					$('[name=matricula]').val("");
					$('[name=bairro]').attr("disabled",false);
					$('[name=municipio]').attr("disabled",false);
					$('[name=pavimentoRua]').attr("disabled",false);
					$('[name=pavimentoCalcada]').attr("disabled",false);
				}else{
					$('[name=bairro]').attr("disabled",true);
					$('[name=municipio]').attr("disabled",true);
					$('[name=pavimentoRua]').attr("disabled",true);
					$('[name=pavimentoCalcada]').attr("disabled",true);
				}
			}

		</script>
		<logic:present name="RASolicitadaComSucesso" scope="session">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#RASolicitadaComSucesso'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						window.location.href = 'http://www.compesa.com.br';
					});
				});
			</script>
		</logic:present>
		<logic:present name="RAJaSolicitada" scope="session">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#RAJaSolicitada'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="exception" scope="session">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#exception'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
					
					$('#voltar').click(function(){
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#matricula').focus();
				});
			</script>
		</logic:present>
		
	</head>
	
	<logic:equal name="solicitacaoTp" value="aguaEsgoto">
		<body onload="exibirOuEsconderAnexo();bloquearCampos();">
	</logic:equal>
	<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
		<body onload="exibirOuEsconderAnexo();">
	</logic:notEqual>
	
	<
		<div id="container" >
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			

			<!-- Content - Start -->
			<div id="content">
		
				<%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
				
				<div id="solicitacao-serv" class="serv-int">
	            	<!-- <h3>Solicita&ccedil;&atilde;o de servi&ccedil;os<span>&nbsp;</span></h3> -->
	            	<c:if test="${requestScope.permissaoExibirNomeEndereco eq 'sim'}">
		            	<p>Nome do Usuário: <em>${InserirSolicitacaoServicosPortalActionForm.nomeUsuario}</em><br>
						   Endere&ccedil;o do im&oacute;vel: <em>${sessionScope.enderecoImovel}</em>
					</c:if>
					<p class="info">Fa&ccedil;a sua solicita&ccedil;&atilde;o ou reclama&ccedil;&atilde;o utilizando o formul&aacute;rio abaixo.</p>
	            	<html:form action="/inserirSolicitacaoServicosPortalVazamentoAction.do"
						name="InserirSolicitacaoServicosPortalActionForm"
						type="gcom.gui.portal.InserirSolicitacaoServicosPortalActionForm" method="post"
						onsubmit="return validarForm();" enctype="multipart/form-data" >
						
						<!--<html:hidden property="matricula" value="${ExibirServicosPortalCompesaActionForm.matricula}" />-->
						<html:hidden property="controle"  />
						<html:hidden property="nomeUsuario"  />
						<input type="hidden" name="permissaoExibirNomeEndereco" value="${requestScope.permissaoExibirNomeEndereco}" />
						
						<fieldset>
	                    	<legend>Solicita&ccedil;&atilde;o de servi&ccedil;os</legend>
	                    	
	                    	<!-- campos do outro formulário -->
	                    	<span id="form-matricula" style="margin-right:34px">
	                    		<p><font style="color:#000000;">Para informar VAZAMENTOS, opcionalmente digite a matrícula da <br />
	                    		   sua conta de água e clique em OK ou informe o local da ocorrência.</font></p>
		                    	<label for="matricula">Matr&iacute;cula</label>
		                    	<html:text property="matricula" styleId="matricula" styleClass="campo-text" size="11" maxlength="11" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);" />
		                        <input type="button" value="" class="btn-ok" onclick="pesquisarMatricula();" />
		                        <logic:present name="imovelInvalido" scope="request">
									<span style="display:block; margin-right:-15px;" id="imovelException">Matr&iacute;cula Inv&aacute;lida</span>
								</logic:present>
							</span>
							
							<span class="cmp-textarea">
								<label for="localOcorrencia">Local da ocorrência:<font color="#F00">*</font></label>
								<html:textarea property="localOcorrencia" cols="35" rows="5" tabindex="2" />
								<font><span id="utilizado">0</span>/<span id="limite">200</span></font>
							</span>											
							<span class="cmp-text-6">
								<label for="nomeSolicitante">Nome do solicitante:<font color="#F00">*</font></label>
								<html:text property="nomeSolicitante" maxlength="60" tabindex="3" />
							</span>
							<span class="cmp-text-2">
								<label for="telefoneContato">Telefone para contato:<font color="#F00">*</font></label>
								<html:text property="telefoneContato" tabindex="4" onkeypress="return isCampoNumerico(event);" alt="phone" />
							</span>
							<span class="cmp-text-7">
								<label for="email">E-mail do Solicitante:</label>
								<html:text property="email" maxlength="40" tabindex="5" style="text-transform:none;" />
							</span>
							<span class="select-3">
								<span class="cmp-text-6">
									<logic:equal name="solicitacaoTp" value="aguaEsgoto">
										<label for="cpfCnpj">CPF/CNPJ:<font color="#F00">*</font></label>
									</logic:equal>
									<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
										<label for="cpfCnpj">CPF/CNPJ:</label>
									</logic:notEqual>
									<html:text property="cpfCnpj" tabindex="6" maxlength="14" onkeypress="return isCampoNumerico(event);" alt="cpf/cnpj" />
								</span>
								<label for="solicitacaoTipo">Tipo de solicita&ccedil;&atilde;o:<font color="#F00">*</font></label>
                            	<div class="select-um" style="padding-bottom:3px;">
									<html:select property="solicitacaoTipo" onchange="carregarEspecificacao()" tabindex="7">
										<logic:present name="colecaoSolicitacaoTipo">
											<logic:equal name="solicitacaoTp" value="aguaEsgoto">
												<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id" />
											</logic:equal>
											<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
												<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id" />
											</logic:notEqual>
										</logic:present>
									</html:select>
								</div>
								<label for="especificacao">Especifica&ccedil;&atilde;o:<font color="#F00">*</font></label>
								<div>
									<html:select property="especificacao" tabindex="8" onchange="calcularDataPrevista();" >
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
										<logic:present name="colecaoEspecificacao">
											<html:options collection="colecaoEspecificacao" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</div>
								<logic:equal name="solicitacaoTp" value="aguaEsgoto">
									<label for="municipio">Município:<font color="#F00">*</font></label>
								</logic:equal>
								<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
									<label for="municipio">Município:</label>
								</logic:notEqual>
								<div>
									
										<html:select property="municipio" tabindex="9" onchange="carregarBairro();" styleId="municipio" >
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
											<logic:present name="colecaoMunicipio">
												<html:options collection="colecaoMunicipio" labelProperty="nome" property="id" />
											</logic:present>
										</html:select>
									
									
								</div>
								
									<label for="bairro">Bairro:<font color="#F00">*</font></label>
								
								<div>
									
										<html:select property="bairro" tabindex="10" styleId="bairro">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
											<logic:present name="colecaoBairro">
												<html:options collection="colecaoBairro" labelProperty="nome" property="id" />
											</logic:present>
										</html:select>
									
									
								</div>
								<logic:equal name="solicitacaoTp" value="aguaEsgoto">
									<label for="pavimentoRua">Pavimento Rua:<font color="#F00">*</font></label>
								</logic:equal>
								<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
									<label for="pavimentoRua">Pavimento Rua:</label>
								</logic:notEqual>
								<div>
									
										<html:select property="pavimentoRua" tabindex="11" styleId="pavimentoRua" >
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
											<logic:present name="colecaoPavimentoRua">
												<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id" />
											</logic:present>
										</html:select>
									
								</div>
								<logic:equal name="solicitacaoTp" value="aguaEsgoto">
									<label for="pavimentoCalcada">Pavimento Calçada:<font color="#F00">*</font></label>
								</logic:equal>
								<logic:notEqual name="solicitacaoTp" value="aguaEsgoto">
									<label for="pavimentoCalcada">Pavimento Calçada:</label>
								</logic:notEqual>
								<div>
									
										<html:select property="pavimentoCalcada" tabindex="12" styleId="pavimentoCalcada" >
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
											<logic:present name="colecaoPavimentoCalcada">
												<html:options collection="colecaoPavimentoCalcada" labelProperty="descricao" property="id" />
											</logic:present>
										</html:select>
									
								</div>
								<span class="cmp-text-none">
									<label for="dataSolicitacao">Data da solicita&ccedil;&atilde;o:</label>
									<logic:present scope="session" name="dataSolicitacao">
										<input type="text" tabindex="13" id="dataSolicitacao" name="dataSolicitacao" value="<bean:write name='dataSolicitacao' scope='session' />"/>
									</logic:present>
									<label for="dataPrevista">Data prevista:</label>
									<logic:present scope="session" name="dataPrevista">
										<input type="text" tabindex="14" id="dataPrevista" name="dataPrevista" value="<bean:write name='dataPrevista' scope='session' />" />
									</logic:present>
								</span>
							</span>
							
							<span class="cmp-textarea">
								<label for="pontoReferencia">Ponto de refer&ecirc;ncia:<font color="#F00">*</font></label>
								<html:textarea property="pontoReferencia" cols="35" rows="5" tabindex="15" />
								<font><span id="utilizado1">0</span>/<span id="limite1">200</span></font>
							</span>
							
							<span class="cmp-textarea">
								<label for="observacoes">Observa&ccedil;&otilde;es:</label>
								<html:textarea property="observacoes" cols="35" rows="5" tabindex="17" />
								<font><span id="utilizado3">0</span>/<span id="limite3">380</span></font>
							</span>
							
							<div id="anexar" align="left">
								
							</div>
							<div  id="anexarNovo" align="left" >
								<A HREF='javascript:addElement();'  style="color: rgb(0,0,156);  ">Anexar um arquivo</A>
							</div>
							
							<span id="textoAnexarArquivo" class="cmp-textarea">
								${sessionScope.mensagemAnexo}
							</span>
							
							<span class="cmp-textarea">
								<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigatórios</div>
							</span>
	                    	                   
							 </fieldset>
							  <input type="submit" name="Button" class="btn-enviar" value="" tabindex="8" style="margin-top:9px; margin-left:700px" />
					</html:form >
					
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
	</body>
	
	<logic:present name="RASolicitadaComSucesso" scope="session">
		<div id="RASolicitadaComSucesso" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	Registro de Atendimento Cadastrado<br />
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		Protocolo: <bean:write name="mensagemRA" scope="session" /><br />
	    		Previsão de Atendimento: <bean:write name="dataPrevistaAtendimentoRA" scope="session" />
	    	</font>
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="RAJaSolicitada" scope="session">
		<div id="RAJaSolicitada" style="display:none; cursor: default;"> 
	        <h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">
	        	Existe uma solicitação para esta especificação:
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		Protocolo:<b>&nbsp;<bean:write name="mensagemRA" scope="session" /></b><br />
	    		Status:<b>&nbsp; Pendente</b>
	    	</font>
	    	<br />
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="exception" scope="session">
		<div id="exception" style="display:none; cursor: default;"> 
	        <p style="text-align:justify; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="session" />
	        </p> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm" id="voltar">OK</a>
		</div>
	</logic:present>
	
	
</html:html>
