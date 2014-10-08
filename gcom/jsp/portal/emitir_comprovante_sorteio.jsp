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
		
		<logic:present name="mensagemEmailEnviado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "E-mail enviado com sucesso." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="mensagemEmailNaoCadastrado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "Não foi informado e-mail no cadastramento do Sorteio." + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="mensagemImovelNaoCadastradoSorteio" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + "Imóvel não cadastrado para o sorteio." + '</h3>'
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

			function emitirComprovante() {
				var form = document.forms[0];
				
				if(validateCadastrarImovelSorteioActionForm(form)){
					form.action = 'gerarComprovanteCadastramentoSorteioAction.do?idImovel='+ form.matricula.value;
					form.submit();
				}
			}
			
			function enviarComprovanteEmail() {
				var form = document.forms[0];

				if(validateCadastrarImovelSorteioActionForm(form)){
					form.action = 'gerarComprovanteCadastramentoSorteioAction.do?enviarEmail=sim&idImovel='+ form.matricula.value;
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
				
			} 
			
			function DateValidations () {

			}
			
			function IntegerValidations () { 
				
				this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
				
			} 
			
			function limparImovel() {
				var form = document.forms[0];

				form.action = 'exibirEmitirComprovanteCadastroSorteioAction.do?limparImovel=sim';
				form.submit();
				
			}

			function limparImovelTecla() {
				var textTargetInfo = document.getElementById("informacaoImovelInexistente"); 
				var textTargetInscricao = document.getElementById("inscricaoImovel");
				var textTargetNome = document.getElementById("nome");
				
				textTargetInfo.firstChild.nodeValue = null;
				textTargetInscricao.firstChild.nodeValue = null;
				textTargetNome.firstChild.nodeValue = null;
					
			}
			
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		    	var form = document.forms[0];
		   
		    	if (tipoConsulta == 'imovel') {
		    		form.matricula.value = codigoRegistro;
					form.inscricaoImovel.value = descricaoRegistro;
		    		form.action = 'exibirEmitirComprovanteCadastroSorteioAction.do';
					form.submit();
		    	}
		    }
		    
			$('.confirm').live('click', function(){
				$.unblockUI();
			});
		</script>
		 
	</head>
	
	<body>
	<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho_questionario_satisfacao.jsp"%>
    	<div id="barra-servicos" style="margin-bottom: 20px;">
				<h2>Serviços</h2>
			    <h4 style="color: #006BBB; right: 180px; font-size: 16px; font-weight: bold;">CAMPANHA CONTA EM DIA - SORTEIO DE PRÊMIOS</h4>
			    <logic:present name="idUsuarioLogado" scope="session">
			    	<a href="javascript:;" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
			    </logic:present>
		</div>
         <div id="fatura-email" class="serv-int" >
		        <br /><br />
		        <p class="info-3">O Nº da Matrícula pode ser encontrado no campo Matrícula em sua fatura mensal.</p>
				<html:form action="/exibirEmitirComprovanteCadastroSorteioAction.do"
					name="CadastrarImovelSorteioActionForm" 
					type="gcom.gui.portal.CadastrarImovelSorteioActionForm"
					method="post">

					<fieldset>
                    	<legend>CAMPANHA CONTA EM DIA - SORTEIO DE PRÊMIOS</legend>
                    	
                    	<table>
                    		<tr>
                    			<td style="text-align: left; ">
									<span class="cmp-text-2" style="width: 400px;">
										<label for="matricula">Matrícula:<font color="#F00">*</font></label>
										<logic:notPresent name="idUsuarioLogado" scope="session">
											<html:text property="matricula" size="10" maxlength="9" tabindex="1" style="text-transform: none;" 
												onkeypress="return isCampoNumerico(event);"
												onkeyup="validaEnterComMensagem(event, 'exibirEmitirComprovanteCadastroSorteioAction.do', 'matricula', 'Imóvel');" />
										</logic:notPresent>
										<logic:present name="idUsuarioLogado" scope="session">
											<html:text property="matricula" size="10" maxlength="9" tabindex="1" style="text-transform: none;" 
												onkeypress="limparImovelTecla();return isCampoNumerico(event);" 
												onkeyup="validaEnterComMensagem(event, 'exibirEmitirComprovanteCadastroSorteioAction.do', 'matricula', 'Imóvel');"/>
											<a
												href="javascript:abrirPopup('exibirPesquisarImovelAction.do?limpaForm=S', 495, 300);"><img
												src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
												height="21" alt="Pesquisar" border="0" title="Pesquisar Imóvel" /></a>	
											<html:hidden property="informacaoImovelInexistente" value="${CadastrarImovelSorteioActionForm.informacaoImovelInexistente}" />
											<logic:present name="imovelInexistente" scope="session">
												<em id="informacaoImovelInexistente" style="background-color:#CFCFCF; color: #ff0000">
													${CadastrarImovelSorteioActionForm.informacaoImovelInexistente}
												</em>
											</logic:present> 
											<logic:notPresent name="imovelInexistente" scope="session">
												<em id="informacaoImovelInexistente" style="background-color:#CFCFCF; color: #000000" title="Localidade.Setor.Quadra.Lote.Sublote">
													${CadastrarImovelSorteioActionForm.informacaoImovelInexistente}
												</em>
											</logic:notPresent>
											<a href="javascript:limparImovel();"> 
												<img border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Imóvel"> 
											</a>
										</logic:present>
									</span>
								</td>
                    		</tr>
							<logic:present name="idUsuarioLogado" scope="session">
	                    		<tr>
	                    			<td style="text-align: left; ">
										<span class="cmp-text-2" style="width: 400px;">
											<label for="nome">Inscrição do Imóvel:</label>
												<em id="inscricaoImovel" style="background-color:#CFCFCF; color: #000000" title="Localidade.Setor.Quadra.Lote.Sublote">
													${CadastrarImovelSorteioActionForm.inscricaoImovel}
												</em>
										</span>
									</td>
	                    		</tr>
	                    		<tr>
	                    			<td style="text-align: left; ">
										<span class="cmp-text-2" style="width: 400px;">
											<label for="nome">Nome do Cliente:</label>
												<em id="nome" style="background-color:#CFCFCF; color: #000000" title="Nome do Candidato">
													${CadastrarImovelSorteioActionForm.nome}
												</em>
										</span>
									</td>
	                    		</tr>
							</logic:present>
	                  		<tr>
	                  			<td>
								<span class="cmp-text-2">
									<label>&nbsp;</label>
								</span>
	                  			</td>
	                  		</tr>
	                  		<tr>
	                  			<td style="width: 400px;" >
								<span class="cmp-text-2">
									<label><font color="#F00" >* Campos obrigatórios</font></label>
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
                    			<td style="text-align: right">
                    				<p>
										<input type="button" name="Button" class="btn-emitir-comprovante" value="" style="margin-left: 120px;"
											onClick="javascript:emitirComprovante(); " />
										<input type="button" name="Button" class="btn-enviar-email" value="" style="margin-right: 120px;"
											onClick="javascript:enviarComprovanteEmail(); " />
									</p>
								</td>
							</tr>
						</table>
                    	<br />
                    	<br />
                    	<br />
  	                    	<!-- END campos do outro formulário -->
                    </fieldset>
				</html:form>
            </div>
    </div>
	</body>
</html:html>