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
		
			function emitirComprovante() {
				var form = document.forms[0];

				form.action = 'gerarComprovanteCadastramentoSorteioAction.do?confirmacao=sim&idImovel='+ form.matricula.value;
				form.submit();
			}
			
			function enviarComprovanteEmail() {
				var form = document.forms[0];

				form.action = 'gerarComprovanteCadastramentoSorteioAction.do?confirmacao=sim&enviarEmail=sim&idImovel='+ form.matricula.value;
				form.submit();
			}
			
		</script>
		 
		<script type="text/javascript">
		 $(document).ready(function(){

				$('[name=telefoneFixo]').setMask();
				
				$('[name=telefoneCelular]').setMask();
				
			
			
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
			
			$('.confirm').live('click', function(){
				$.unblockUI();
			});
		 });
			function setFocusData(){
				$('[name=dataNascimento]').focus();
			}
		
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
				<html:form action="/cadastrarImovelSorteioAction.do"
					name="CadastrarImovelSorteioActionForm" 
					type="gcom.gui.portal.CadastrarImovelSorteioActionForm"
					method="post">

                	<html:hidden property="matricula" value="${CadastrarImovelSorteioActionForm.matricula}" />
                	
					<fieldset>
                    	<legend>CAMPANHA CONTA EM DIA - SORTEIO DE PRÊMIOS</legend>
                    	<br />
                    	<p style="color: #006BBB; right: 180px; font-size: 18px; font-weight: bold; text-align: center;">CADASTRAMENTO EFETUADO COM SUCESSO</p>
                    	<br />
                    	<p style="color: #006BBB; right: 180px; font-size: 16px; font-weight: bold; text-align: center;">IMÓVEL APTO PARA SORTEIO</p>
                    	<p style="color: #006BBB; right: 180px; font-size: 14px; font-weight: bold; text-align: center;">MATRÍCULA DO IMÓVEL: <em>${CadastrarImovelSorteioActionForm.matricula}</em></p>
                    	<p style="color: #006BBB; right: 180px; font-size: 14px; font-weight: bold; text-align: center;">NÚMERO DO SORTEIO: <em>${CadastrarImovelSorteioActionForm.numeroSorteio}</em></p>
                    	<p></p>
                    	<table>
                    		<tr>
                    			<td style="text-align: right">
                    				<p>
										<input type="button" name="Button" class="btn-emitir-comprovante" value=""
											onClick="javascript:emitirComprovante(); " />
										<input type="button" name="Button" class="btn-enviar-email" value=""
											onClick="javascript:enviarComprovanteEmail(); " />
									</p>
								</td>
							</tr>
						</table>
                    	<p></p>
  	                    	<!-- END campos do outro formulário -->
                    </fieldset>
				</html:form>
            </div>
    </div>
	</body>
</html:html>