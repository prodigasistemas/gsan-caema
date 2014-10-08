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
			});
		</script>
	</head>
	
	<body>
		<div id="container"> 
		   <html:form action="/exibirInformacoesPortalCompesaAction.do" method="post" 
				name="ExibirInformacoesPortalCompesaActionForm" type="gcom.gui.portal.ExibirInformacoesPortalCompesaActionForm" >
		    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
		        
		        <!-- Content - Start -->
		         <div id="content">
		            <%@ include file="/jsp/portal/cabecalhoInformacoes.jsp"%>`
		            <ul id="lista-informacoes">
		            	<li>
		            		<a href="exibirInformacoesPortalCompesaAction.do?method=negociacaoDebitos">
								<span>Negocia��o de d�bito</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>Este acesso lhe fornecer� informa��es sobre a documenta��o necess�ria para efetivar uma
									negocia��o de d�bitos com a Compesa.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	<li>
		            		<a href="exibirConsultarEstruturaTarifariaPortalAction.do">
								<span>Estrutura tarif�ria</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>Objetivando reduzir suas d�vidas quanto aos valores cobrados nas suas contas, este acesso
									permite detalhar o valor cobrado para cada categoria de consumidor medido e n�o medido.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	<li>
		            		<a href="exibirInformacoesTarifaSocialPortalCompesaAction.do">
								<span>Tarifa social</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>A tarifa social, institu�da em novembro de 2003, tem o objetivo de assistir as fam�lias de baixa
									renda. Este acesso lhe dar� mais informa��es sobre este benef�cio.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	<li>
		            		<a href="exibirInformacoesPortalCompesaAction.do?method=normas">
								<span>Normas de instala��o</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>Este acesso disponibiliza informa��es sobre as normas de instala��o de ramal predial de �gua e de esgoto.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		               
		            </ul>
		        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/rodape.jsp"%>
	       </html:form>
	    </div>
	</body>
</html:html>