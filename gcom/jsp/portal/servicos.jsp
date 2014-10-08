<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Servi�os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<style type="text/css">
			em {
				color: #008FD6;
				font-style: normal;
				font-size: 12px;
				} 
		</style>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.info-serv').hide();
				$('#lista-servicos li, #lista-informacoes li').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('.info-serv').fadeIn(50);
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
					$(this).find('.info-serv').fadeOut(50);
				});
			
				$('.confirm').click(function(){
					$.unblockUI();
				});
			});
		</script>
		
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
		
		<logic:present name="cadastroEmAnalise" scope="request">
			<div id="cadastroEmAnalise" style="display:none; cursor: default;"> 
		        <img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
			        	<p>
			        		Seu cadastro encontra-se em an�lise, qualquer d�vida entrar em contato com a 
			        			central de atendimento pelo telefone 0800-081-0195.  
			        	</p>
		        	</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" >OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebito" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemPagamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemPagamentos'),
						theme : true,
						title : 'Aviso'
					});
				});		
			</script>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#debitoParceladoMesCorrente'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemQuitacaoAnual'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="esferaPoderResponsavel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#esferaPoderResponsavel'),
						theme : true,
						title : 'Aviso'
					});
				});	
			</script>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelParcelamentoAtivo'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelNaoPossuiPerfilParcelamento'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#quantidadeReparcelNaoPermiteParcel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#naoExistePerfilSituacaoImovel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSituacaoCobranca'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#numeroMesesMinimoVencimentoAlternativoSuperiorPermitido'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="ligacaoAguaEsgotoInativa" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#ligacaoAguaEsgotoInativa'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="ligacaoAguaEsgotoAtiva" scope="request">
			<script type="text/javascript">
			abrirPopup('gerarSegundaViadeContratoAdesaoTacita.do', 400, 800);
			</script>
		</logic:present>
		<logic:present name="imovelSemRA" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemRA'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="solicitarCpfCnpj" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#solicitarCpfCnpj'),
						theme : true,
						title : 'Confirma��o de Usu�rio',
						onBlock : function() {
							if ($.browser.msie && parseInt($.browser.version, 10) < 9) {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-25px;margin-right:5px;" href="javascript:void(0);" id="cancel">X</a>');
							 } else {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-3px;" href="javascript:void(0);" id="cancel">X</a>');
							 }
							 
							$('#matricula').val($('#matriculaAux').val());							
						}
					});
					
					$('[name=cpfCnpjSolicitante]').focus().select();
					
					$('#cancel').live('click', function(){
						$('[name=cpfCnpjSolicitante]').val('');
						$.unblockUI();	
						$('#matricula').focus().select();					
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
		
		<logic:present name="cadastroRejeitado" scope="request">
			<div id="cadastroRejeitado" style="display:none; cursor: default;"> 
		        <img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
			        	<p>
			        		Seu cadastro foi rejeitado, qualquer d�vida entrar em contato com a 
			        			central de atendimento pelo telefone 0800-081-0195.  
			        	</p>
		        	</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" >OK</a>
			</div>
		</logic:present>
		
		<logic:present name="solicitarCadastroVirtual" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#solicitarCadastroVirtual'),
						theme : true,
						title : 'Confirma��o de Usu�rio',
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
		
		 <logic:present name="solicitarCadastroVirtual" scope="request">
		    <div id="solicitarCadastroVirtual" style="display:none; cursor: default;">
		    	
		    	<html:form styleId="form-matricula" style="padding: 8px 0 0 0px; width: 100%;" action="/exibirServicosPortalCompesaAction.do?method=servicos&vcpf=true" method="post" 
					name="ExibirServicosPortalCompesaActionForm" type="gcom.gui.portal.ExibirServicosPortalCompesaActionForm" >
                    <fieldset>
                        <label for="cadastroVirtual" style="width: auto; height: auto; overflow: hidden" id="cpfOrCnpj">Esse � o seu primeiro acesso a loja virtual, para usar os nossos servi�os disponiveis � necess�rio atualizar seu cadastro.</label>
                        <br />
                        <html:hidden property="matricula" styleId="matriculaAux" />                   
                        <a href="javascript:void(0);" id="botaoSim" class="ui-corner-all button">Ok</a>
						<br />
                    </fieldset>
                </html:form>
		    </div>
		    
	    </logic:present>
		
		
		
		
		
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</head>
	
	<body>
		<div id="container"> 
		
			<logic:present name="solicitarCpfCnpj" scope="request">
			    <div id="solicitarCpfCnpj" style="display:none; cursor: default; ">
			    	
			    	<html:form styleId="form-matricula" style="padding: 8px 0 0 0px; width: 100%;"  action="/exibirServicosPortalCompesaAction.do?method=${metodo}" method="post" 
						name="ExibirServicosPortalCompesaActionForm" type="gcom.gui.portal.ExibirServicosPortalCompesaActionForm" >
	                    <fieldset>
	                        <label for="cpfOrCnpj" style="width:auto;" id="cpfOrCnpj">CPF/CNPJ do solicitante: </label>
	                        <html:hidden property="matricula" styleId="matriculaAux" />
	                        <html:text property="cpfCnpjSolicitante" style="width: 140px !important; background: url(/gsan/imagens/portal/forms/matricula-text.png) no-repeat !important; background-position: 0px -4px !important;" styleClass="campo-text cpfCnpjSolicitante" size="14" maxlength="14" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
	                        <input type="submit" value="" class="btn-ok"  />
	                        <logic:present name="cpfCnpjInvalido" scope="request">
								<span style="display: block; background: url('/gsan/imagens/portal/forms/cpf_cnpj_invalido.png') no-repeat scroll 0pt 0pt transparent; width: 100px; margin-right: -12px;" id="cpfCnpjError"></span>
							<script>
							
								$('.blockMsg').css("width","63%");
								$('.blockMsg').css("left","20%");
	
								</script>
							</logic:present>
	                        <logic:present name="cpfCnpjNaoCadastrado" scope="request">
								<span style="display: block; background: url('/gsan/imagens/portal/forms/cpf_cnpj_nao_cadastrado.png') no-repeat scroll 0pt 0pt transparent; width: 180px;  " id="cpfCnpjError"></span>
								<script>
	
								$('.blockMsg').css("width","63%");
								$('.blockMsg').css("left","20%");
	
								</script>
							</logic:present>
						</fieldset>
	                </html:form>
			    </div>
		    </logic:present>
		    
		   <html:form action="/exibirServicosPortalCompesaAction.do?method=servicos" method="post" 
				name="ExibirServicosPortalCompesaActionForm" type="gcom.gui.portal.ExibirServicosPortalCompesaActionForm" >
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	         <div id="content">
	         <%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
	            
	            <ul id="lista-informacoes">
	                <li>
	            		<a href="emitirSegundaViaContaAction.do">
	            			<span>2� Via da conta</span>
            			</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Este acesso permite solicitar a segunda via da sua conta, que poder� ser paga nos agentes recebedores da Compesa. Para acessar este servi�o, clique no assunto &quot;2� Via de conta&quot; e digite o n�mero da sua matr�cula, e aguarde o t�rmino da impress�o  para pagamento, ou se preferir dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco.</p>
	                        <p>A Compesa disponibiliza mais este servi�o sem custo adicional.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-2">
	                	<a href="exibirServicosPortalCompesaAction.do?method=declaracaoAnual">
	                		<span style="font-size:14px;">Declara��o anual de quita��o de d�bito</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Conforme determina o artigo 3� da lei federal 12.007 de 2009 a Compesa disponibiliza para voc� a declara��o de quita��o anual de d�bitos. Lembramos que para este acesso o cliente dever� estar em dia com suas contas referentes ao ano de 2012.</p>
                            <p>Para acessar este servi�o, clique no assunto declara��o anual de quita��o de d�bito ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco ou pelo e-mail: <a href = "mailto:dac0800@compesa.com"><em>dac0800@compesa.com</em></a></p>
                            <p>Para utilizar este servi�o � necess�rio informar o CPF ou CNPJ.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-3">
	                	<a href="exibirInserirCadastroEmailClientePortalAction.do?ok=sim">
	                		<span>Recebimento de fatura por e-mail</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>A Compesa disponibiliza para voc� a facilidade de receber suas faturas em seu e-mail, para acessar este servi�o, clique no assunto recebimento de fatura por email fa�a seu cadastro e receba suas contas sem sair de casa, ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco ou pelo e-mail dac0800@compesa.com.br</p>
	                        <p>A Compesa disponibiliza mais este servi�o sem custo adicional.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-4">
	                	<a href="exibirInserirCadastroContaBrailePortalAction.do">
	                		<span>Solicitar conta em braile</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Em atendimento a Lei Estadual n� 14.262 de 05 de janeiro de 2011, que  assegura aos portadores de defici�ncia visual o direito de receber os boletos de pagamento de suas contas de �gua, energia el�trica e telefonia, confeccionados em braille, estamos disponibilizando esta funcionalidade para emiss�o deste servi�o, o qual ser� solicitado sem custo adicional.</p>
	                        <p>Para acessar clique em solicitar conta em braille em solicitar conta em braille ou dirija-se a uma loja de atendimento ou entre em contato com o call center, atrav�s  do  n�mero 0800 081 0195.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-5">
	                	<a href="exibirInserirSolicitacaoServicosPortalAction.do?init=1">
	                		<span>Outros servi�os</span>
                		</a>
	                	<div class="info-serv">
	                        <p>Por este acesso, ser� poss�vel solicitar alguns servi�os. Fa�a sua op��o.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
	                
	                <logic:notPresent name="imovelCadastroVirtual" scope="session">
	                	<li id="serv-9">
	                		<a href="exibirServicosPortalCompesaAction.do?method=consultarPagamentos"> 
	                			<span>Consultar pagamentos</span>
	                		</a>
	                		<div class="info-serv" style="text-align:justify;">
	                			<p>Este acesso permite listar todo o historico de pagamentos referente ao im�vel.</p>
	                			<span id="bottom">&nbsp;</span>
	                			<img alt="Seta" src="imagens/portal/general/seta-info-servicos.gif" />
	                		</div>
	                	</li>
	                </logic:notPresent>
	               
	                <li id="serv-7">
	                	<a href="exibirAcompanhamentoRAPortalAction.do">
		                	<span style="font-size: 14px;">Acompanhar Registro de Atendimento</span>
	                	</a>
	                	<div class="info-serv" style="text-align:justify;">
		                        <p>Por este acesso, � poss�vel visualizar o acompanhamento dos Registros de Atendimento para o seu im�vel.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
	                </li>	               
	                <logic:notPresent name="imovelCadastroVirtual" scope="session">
		                <li id="serv-6">
		                	<a href="exibirEfetuarParcelamentoDebitosPortalAction.do?paginaServicos=SIM">
		                		<span>Negocia��o de d�bitos</span>
	                		</a>
		                	<div class="info-serv" style="text-align:justify;">
		                        <p>Este acesso permite simular as condi��es de regulariza��o de seu d�bito � vista ou a prazo. Ao final da negocia��o, ser� gerado documento pag�vel nos agentes arrecadadores da Compesa.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
	                </logic:notPresent>
                    <li id="serv-7">
	                	<a href="exibirServicosPortalCompesaAction.do?method=vencimentoAlternativo">
	                		<span>Alterar vencimento da conta</span>
                		</a>
	                	<div class="info-serv">
	                        <p>Conforme determina a lei federal Lei n� 9.791, 24 de Mar�o de 1999 que disp�e sobre a obrigatoriedade de as concession�rias de servi�os 
	                        p�blicos estabelecerem ao consumidor e ao usu�rio datas opcionais para o vencimento de seus d�bitos.
							Aqui o cliente pode solicitar altera��o da data de pagamento de sua Fatura.</p>
							<p>Para utilizar este servi�o � necess�rio informar o CPF ou CNPJ.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
	               
	               <logic:notPresent name="imovelCadastroVirtual" scope="session">
	               	<li id="serv-8">
	               		<a href="gerarCertidaoNegativaAction.do?acessoLoja=1">
	               			<span>Gerar certid�o negativa de d�bitos</span>
	               		</a>
	               		<div class="info-serv" style="text-align:justify;">
	                        <p>Este item possibilita ao usu�rio emitir uma certid�o negativa de d�bitos.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                </div>
	               	</li>
	               </logic:notPresent>
	                <li id="serv-2">
	                	<a href="exibirServicosPortalCompesaAction.do?method=contratoAdesao">
	                		<span style="font-size:14px;">Imprimir 2� via do contrato ades�o</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>O Contrato de Presta��o de Servi�os P�blicos de Abastecimento de �gua e/ou
								Esgotamento Sanit�rio por Ades�o T�cita, com o objetivo de dar continuidade a
								transpar�ncia dos direitos e deveres da Compesa e dos seus clientes, est� registrado no
								6324 cart�rio "1� REGISTRO DE T�TULOS, DOCUMENTOS E DE PESSOAS JUR�DICAS",
								situado a Av. Dantas Barreto, 160 - T�rreo - Recife - PE, sob o n� 788930 datado de 18 de
								junho de 2008.
								Por esse motivo, o Contrato que � por Ades�o T�cita, ou seja, a rela��o cliente/fornecedor
								j� existe, n�o precisa ser assinado, nem devolvido, estamos apenas oficializando e tornando
								p�blico.
								� a Compesa trabalhando para prestar seus servi�os com qualidade e transpar�ncia.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	               <!--  <li>
		            	<a href="exibirConsultarConsumoHistoricoAguaPortalCompesaAction.do">
							<span>Consultar hist&oacute;rico de consumo </span>
						</a>
					<div class="info-serv" style="text-align:justify;display:block;">
	                       <p></p>
	                       <p>Consulta do hist&oacute;rico do volume de &aacute;gua fornecido referentes nos &uacute;ltimos meses.</p>
	                       <p></p>
	                       <span id="bottom">&nbsp;</span>
	                       <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
	                </div>
	                </li>--> 
	            </ul>
	        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/rodape.jsp"%>
	       </html:form>
	    </div>
	    
		<!-- Avisos -->
		
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<div id="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"> Im�vel com vencimento alterado ha menos de <bean:write name="numeroMesesMinimo" scope="request" /> meses.</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebito" scope="request">
			<div id="imovelSemDebitos" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel sem d�bitos.</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemPagamento" scope="request">
			<div id="imovelSemPagamentos" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" style="float: left; padding-right:10px; margin-top: 10px;">
				<h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel sem pagamentos.</h3>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Im�vel sem declara��o anual de quita��o de d�bitos</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel j� possui um parcelamento n�o quitado/cobrado. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<div id="imovelSemDebitos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui d�bitos. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O d�bito deste im�vel j� foi parcelado no m�s de faturamento corrente. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<div id="quantidadeReparcelNaoPermiteParcel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Quantidade de reparcelamento do im�vel n�o permite um novo parcelamento. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<div id="naoExistePerfilSituacaoImovel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<div id="imovelSituacaoCobranca" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel com situa��o de cobran�a, n�o � possivel fazer o parcelamento de d�bitos.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="ligacaoAguaEsgotoInativa" scope="request">
			<div id="ligacaoAguaEsgotoInativa" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="padding-top:10px; padding-bottom: 10px;"></h3>
			        <p>
			        	Matr�cula do im�vel informado n�o tem situa��o da liga��o de �gua/esgoto ativa, condi��o n�o permite gera��o de contrato de ades�o t�cita.
			        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="esferaPoderResponsavel" scope="request">
			<div id="esferaPoderResponsavel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        	<h3 style="padding-top:10px; padding-bottom: 10px;"></h3>
			        <p>
			        	A esfera de poder associado ao cliente respons�vel n�o permite certid�o negativa para o im�vel.
			        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemRA" scope="request">
			<div id="imovelSemRA" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui Registros de Atendimento para serem acompanhados. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</body>
</html:html>