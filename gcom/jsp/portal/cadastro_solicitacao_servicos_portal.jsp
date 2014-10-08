<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
				
				$('[name=telefoneContato]').setMask();
				
				$('[name=nomeSolicitante]').focus();
				
				var limitNum1 = 200;
				limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				$('[name=pontoReferencia]').keyup(function(){

					var value = $(this).val();
				    var tam = $(this).val().length;

				    if(tam > limitNum1){
				    	$(this).val(value.substring(0, limitNum1));
				    }
				    
					limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				});
				
				var limitNum = 400;
				limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado'), document.getElementById('limite'));
				$('[name=observacoes]').keyup(function(){

					var value = $(this).val();
				    var tam = $(this).val().length;

				    if(tam > limitNum){
				    	$(this).val(value.substring(0, limitNum));
				    }
				    
				    limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado'), document.getElementById('limite'));
				});
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
			});
		
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
				
				if($('[name=nomeSolicitante]').val().length < 1){
					msg += ' - Nome do solicitante';
					$('[name=nomeSolicitante]').focus(function(){
						inputComFocus = $(this);
					}).focus();
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

				if ( !(form.telefoneContato.value.length >=13  && form.telefoneContato.value.length <=14) ){
					if (msg.length > 0){
						msg += '<br />';
						msg = msg + "Número do telefone para contado deve conter entre 9 e 11 dígitos. EX:(XX) XXXX-XXXX.<br />";
					}
					else{
						msg = "Número do telefone para contado deve conter entra 9 e 11 dígitos. EX:(XX) XXXX-XXXX.<br />";
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
					redirecionarSubmit('exibirInserirSolicitacaoServicosPortalAction.do');
				}
			}
			
			function calcularDataPrevista(){
				if (parseInt($('[name=especificacao] option:selected').val()) > 0){
					redirecionarSubmit('exibirInserirSolicitacaoServicosPortalAction.do');
				}
			}

			function novoArquivo(){
				redirecionarSubmit('exibirInserirSolicitacaoServicosPortalAction.do?novoArquivo=ok');
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
		   		} else {
		    	 	document.getElementById('anexarNovo').style.display = 'none';
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
						window.location.href = '/gsan/exibirServicosPortalCompesaAction.do?method=voltarServico';
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
						$('[name=especificacao]').focus();
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
		
		<logic:present name="colecaoEspecificacao">
			<script type="text/javascript">
				$(document).ready(function(){
					$('.btn-enviar').focus();
					if(parseInt($('[name=especificacao] option:selected').val()) < 1){
						$('[name=especificacao]').focus();
					} else {
						$('[name=telefoneContato]').focus();
					}
				});
			</script>
		</logic:present>
	</head>
	<body onload="exibirOuEsconderAnexo();">
		<div id="container" >
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			

			<!-- Content - Start -->
			<div id="content">
		
				<%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
				
				<div id="solicitacao-serv" class="serv-int">
	            	<h3>Solicita&ccedil;&atilde;o de servi&ccedil;os<span>&nbsp;</span></h3>
					<p>Endere&ccedil;o do im&oacute;vel: <em><%= session.getAttribute("enderecoImovel") %></em></p>
					<p class="info">Fa&ccedil;a sua solicita&ccedil;&atilde;o ou reclama&ccedil;&atilde;o utilizando o formul&aacute;rio abaixo.</p>
	            	<html:form action="/inserirSolicitacaoServicosPortalAction.do?lojaVirtual=S"
						name="InserirSolicitacaoServicosPortalActionForm"
						type="gcom.gui.portal.InserirSolicitacaoServicosPortalActionForm" method="post"
						onsubmit="return validarForm();validateInserirSolicitacaoServicosPortalActionForm();" enctype="multipart/form-data" >
						
						<html:hidden property="matricula" value="${ExibirServicosPortalCompesaActionForm.matricula}" />
						<html:hidden property="controle"  />
						<fieldset>
	                    	<legend>Solicita&ccedil;&atilde;o de servi&ccedil;os</legend>
	                    	
	                    	<!-- campos do outro formulário -->
							<span class="cmp-text-6">
								<label for="nomeSolicitante">Nome do solicitante<font color="#F00">*</font>:</label>
								<html:text property="nomeSolicitante" maxlength="60" tabindex="1" />
							</span>
							<span class="cmp-text-2">
								<label for="telefoneContato">Telefone para contato<font color="#F00">*</font>:</label>
								<html:text property="telefoneContato" tabindex="4" onkeypress="return isCampoNumerico(event);" alt="phone" />
							</span>
							<span class="cmp-text-7">
								<label for="email">E-mail do Solicitante:</label>
								<html:text property="email" maxlength="40" tabindex="5" style="text-transform:none;" />
							</span>
							<span class="select-3">
								<label for="solicitacaoTipo">Tipo de solicita&ccedil;&atilde;o<font color="#F00">*</font>:</label>
                            	<div class="select-um" style="padding-bottom:3px;">
									<html:select property="solicitacaoTipo" onchange="carregarEspecificacao()" tabindex="2">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoSolicitacaoTipo">
											<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</div>
								<label for="especificacao">Especifica&ccedil;&atilde;o<font color="#F00">*</font>:</label>
								<div>
									<html:select property="especificacao" tabindex="3" onchange="calcularDataPrevista();" >
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp;</html:option>
										<logic:present name="colecaoEspecificacao">
											<html:options collection="colecaoEspecificacao" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</div>
							</span>
							<span class="cmp-textarea">
								<label for="pontoReferencia">Ponto de refer&ecirc;ncia<font color="#F00">*</font>:</label>
								<html:textarea property="pontoReferencia" cols="35" rows="5" tabindex="6" />
								<font><span id="utilizado1">0</span>/<span id="limite1">200</span></font>
							</span>
							
							<span class="cmp-text-none">
								<label for="dataSolicitacao">Data da solicita&ccedil;&atilde;o:</label>
								<input type="text" id="dataSolicitacao" name="dataSolicitacao" value="<bean:write name='dataSolicitacao' scope='session' />"/>
								<label for="dataPrevista">Data prevista:</label>
								<logic:present scope="session" name="dataPrevista">
									<input type="text" id="dataPrevista" name="dataPrevista" value="<bean:write name='dataPrevista' scope='session' />" />
								</logic:present>
							</span>
							<span class="cmp-textarea">
								<label for="observacoes">Observa&ccedil;&otilde;es:</label>
								<html:textarea property="observacoes" cols="35" rows="5" tabindex="7" />
								<font><span id="utilizado">0</span>/<span id="limite">400</span></font>
							</span>
							<div id="anexar" align="left">
								
							</div>
							<div  id="anexarNovo" align="left" >
								<A HREF='javascript:addElement();'  style="color: rgb(0,0,156);  ">Anexar um arquivo</A>
							</div>
							<em >INSTL. RAMAL PREDL DE AGUA C /INSTAL DE HIDRÔMETRO - O Cliente deverá apresentar Escritura do Imóvel, Recibo ou Promessa de Compra e Venda registrada em cartório, ou outro documento comprobatório de propriedade do imóvel, CPF e RG.  </em>
							<br>
							<em >INSTALACAO RAMAL DE ESGOTO - O Cliente deverá apresentar Escritura do Imóvel, Recibo ou Promessa de Compra e Venda registrada em cartório, ou outro documento comprobatório de propriedade do imóvel, CPF e RG.    </em>
							
	                   
	                    	<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigatórios</div>
	                   
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
