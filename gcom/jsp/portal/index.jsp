<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		
		<style type="text/css">
		
			#navegadores{
			position: relative;
			z-index: 9999;
			top: 2px;
			height: 1px;
			margin-left: 56px;
			}
			
			
			#nomeExplorerMF{
			position: absolute;
			z-index: 9996;
			width: 120px;
			top: 214px;
			margin-left: 197px;
			
			}
	
			#nomeFirefoxMF{
			position: absolute;
			z-index: 9995;
			width: 120px;
			top: 215px;
			margin-left: 350px;
			
			}
			
			#nomeExplorerIE{
			position: absolute;
			z-index: 9998;
			width: 120px;
			top: 180px;
			margin-left: 202px;
			
			}
	
			#nomeFirefoxIE{
			position: absolute;
			z-index: 9997;
			width: 120px;
			top: 180px;
			margin-left: 356px;
			
			}
			
	
		</style>
			
		<logic:present name="imovelInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#link-3 a').focus();
					$('#matricula').focus();
				});
			</script>
		</logic:present>
		
		<logic:present name="cadastroEmAnalise" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#cadastroEmAnalise'),
						theme : true,
						title : 'Aviso'
					});
					
					$('.confirm').click(function(){
						$.unblockUI();
					});
				});	
			</script>
		</logic:present>
		
		<logic:present name="cadastroRejeitado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#cadastroRejeitado'),
						theme : true,
						title : 'Aviso'
					});
					
					$('.confirm').click(function(){
						$.unblockUI();
					});
				});	
			</script>
		</logic:present>
		
		<logic:present name="solicitarCadastroVirtual" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#solicitarCadastroVirtual'),
						theme : true,
						title : 'Confirmação de Usuário',
						onBlock : function() {
							if ($.browser.msie && parseInt($.browser.version, 10) < 9) {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-25px;margin-right:5px;" href="javascript:void(0);" id="cancel">X</a>');
						
							 } else {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-3px;" href="javascript:void(0);" id="cancel">X</a>');
							 }
							 
							$('#matricula').val($('#matriculaAux').val());							
						}
					});
					$('#cancel').live('click', function(){
						$.unblockUI();	
						$('#matricula').focus().select();					
					});		
					$('#botaoSim').click(function(){
						efetuarCadastroVirtual();
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
		<script type="text/javascript">

			function efetuarCadastroVirtual() {
				var form = document.forms[0];
				form.action = "exibirInserirClientePortalAction.do?menu=sim&tipoPessoa=1";
				form.submit();
			}
			
		</script>
	</head>
	
	<body>
		
		<!--[if IE]>
			<div id="nomeExplorerIE">Internet Explorer</div>
			<div id="nomeFirefoxIE">Mozilla Firefox</div> 
		<![endif]-->
		
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
		
		<![if !IE]>
			<div id="nomeExplorerMF">Internet Explorer</div>
			<div id="nomeFirefoxMF">Mozilla Firefox</div>
		<![endif]>
			
	        <!-- Content - Start -->
	        <map name="navegadoresMap">
		        <area shape="rect" coords="103,2,254,38" href="http://windows.microsoft.com/pt-BR/internet-explorer/download-ie" target="new">
		        <area shape="rect" coords="254,2,402,39" href="http://www.mozilla.org/pt-BR/firefox/fx/" target="new">
	        </map>
	        
	        <div id="navegadores"><img src="imagens/portal/general/navegadores.png" usemap="#navegadoresMap"></div>
	        
	        <div id="content">
	        	<div id="text-top" style="text-align:justify;">
	        		A Compesa, objetivando facilitar cada vez mais o acesso do cliente aos seus produtos e servi&ccedil;os, criou este atendimento para assegurar mais rapidez, segurança e transpar&ecirc;ncia nos processos de negocia&ccedil;&atilde;o e fornecimento de informa&ccedil;&otilde;es.
	            </div>
	            <div id="info-index" class="box">
	            	<a href="exibirInformacoesPortalCompesaAction.do" title="Informações | Tire suas dúvidas e entenda sua conta">
	                    <span id="text-box" style="text-align:justify;">
	                    	Este acesso permitir&aacute; esclarecimentos sobre diversos servi&ccedil;os que a Compesa oferece aos seus clientes. Para conhecer estes serviços ou tirar suas dúvidas clique no link informa&ccedil;&otilde;es.
	                    </span>
	                </a>
	            </div>
	            <div id="servicos-index" class="box">
                    <span id="text-box" style="text-align:justify;" title="Serviços | Os melhores serviços para sua comodidade">
						Para acessar o menu de op&ccedil;&otilde;es digite a matr&iacute;cula da sua conta de &aacute;gua e clique em OK
					</span>
				    <html:form styleId="form-matricula" action="/exibirServicosPortalCompesaAction.do?method=servicos" method="post" 
						name="ExibirServicosPortalCompesaActionForm" type="gcom.gui.portal.ExibirServicosPortalCompesaActionForm" >
	                    <fieldset>
	                        <label for="matricula">Matr&iacute;cula</label>
	                        <input type="text" name="matricula" id="matricula" class="campo-text" size="11" maxlength="11" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
	                        <input type="submit" value="" class="btn-ok"  />
	                        <logic:present name="imovelInvalido" scope="request">
								<span style="display:block; margin-right:-15px;">Matr&iacute;cula Inv&aacute;lida</span>
							</logic:present>
	                    </fieldset>
	                </html:form>                
	            </div>
	        </div>
	        
	        
	
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/rodape.jsp"%>
	    </div>
	    
	    <logic:present name="solicitarCadastroVirtual" scope="request">
		    <div id="solicitarCadastroVirtual" style="display:none; cursor: default;">
		    	
		    	<html:form styleId="form-matricula" style="padding: 8px 0 0 0px; width: 100%;" action="/exibirServicosPortalCompesaAction.do?method=servicos&vcpf=true" method="post" 
					name="ExibirServicosPortalCompesaActionForm" type="gcom.gui.portal.ExibirServicosPortalCompesaActionForm" >
                    <fieldset>
                        <label for="cadastroVirtual" style="width: auto; height: auto; overflow: hidden" id="cpfOrCnpj">Esse é o seu primeiro acesso a loja virtual, para usar os nossos serviços disponiveis é necessário atualizar seu cadastro.</label>
                        <br />
                        <html:hidden property="matricula" styleId="matriculaAux" />                   
                        <a href="javascript:void(0);" id="botaoSim" class="ui-corner-all button">Ok</a>
						<br />
                    </fieldset>
                </html:form>
		    </div>
		    
	    </logic:present>
	    
	    <logic:present name="cadastroEmAnalise" scope="request">
			<div id="cadastroEmAnalise" style="display:none; cursor: default;"> 
		        <img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
			        	<p>
			        		Seu cadastro encontra-se em análise, qualquer dúvida entrar em contato com a 
			        			central de atendimento pelo telefone 0800-081-0195.  
			        	</p>
		        	</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" >OK</a>
			</div>
		</logic:present>
		
		<logic:present name="cadastroRejeitado" scope="request">
			<div id="cadastroRejeitado" style="display:none; cursor: default;"> 
		        <img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
			        	<p>
			        		Seu cadastro foi rejeitado, qualquer dúvida entrar em contato com a 
			        			central de atendimento pelo telefone 0800-081-0195.  
			        	</p>
		        	</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" >OK</a>
			</div>
		</logic:present>
		
	</body>
</html:html>