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
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		
		<!--<logic:present name="imovelInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#link-3 a').focus();
					$('#matricula').focus();
				});
			</script>
		</logic:present>-->
		
		<style>
			em {
    			color: #008FD6;
				font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font {
   				color: #008FD6;
    			float: none;
   				margin: 0;
    			padding-bottom: 10px;
    			text-indent: 0;
				font-style:italic;
    			float: right;
    		}
    		#atualizacao{
    			line-height:2.3em;
    			padding:0 15px; 
    			position:relative;    			
    			float:left; 
    			font-size:11px;
    			height:33px;
    			width: 315px;    			
    		}	
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        	<div id="teleatend" class="serv-int" style="width:890px;">	
	    	    			<a class="adobe-reader" target="_blank" title="Fa�a o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCompesaAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/general/btn-voltar.gif">
							</a>
							<p>&nbsp;</p>
						
	        				<h3>
								Teleatendimento<span>&nbsp;</span>
							</h3>
						
							<br>&nbsp;</br>
							 <p>&nbsp;</p>
							
							<div>                  
				               <em>Teleatendimento (Call Center) 0800-081-0195 / 0800 081 0185</em>             
		                
				        		<p>&nbsp;</p>
				        		<p style="text-align:justify;">
				                	O Call Center Compesa vem inovando a cada dia, na presta��o de servi�os aos seus clientes, onde a telefonia � integrada ao computador e conectada a rede da empresa, atrav�s de uma ampla variedade de aplica��es, convergentes em voz e dados, remotamente. 
				                	O nosso teleatendimento funciona 24 horas por dia, 7 dias por semana, inclusive s�bados, domingos e feriados.
				               	 </p>
		        		       	 <p>&nbsp;</p> 
		        		       	 	O nosso teleatendimento encontra-se dispon�vel pelo <b>telefone: </b><em>0800 081 0195</em> para servi�os
									comerciais e operacionais, pelo <b>telefone: </b><em>0800 081 0185</em> exclusivamente para servi�os de vazamento
									de ramal de �gua e extravasamento de esgoto, atrav�s do <b>site: </b><em><a href="http://www.compesa.com.br">www.compesa.com.br</a></em>, <b>e-mail: </b> <em><a href="mailto:dac0800@compesa.com.br">dac0800@compesa.com.br</a>.</em>
		           			      <p>&nbsp;</p>
		           			      <p>&nbsp;</p> 
		                
						           <p style="text-align: justify;">
								<img style="float: left; margin-right: 25px;" src="/gsan/imagens/portal/general/teleatendimento.bmp">
								O Call Center Compesa oferece aos seus clientes, os servi�os de Telemarketing Receptivo para atender as solicita��es quanto aos servi�os operacionais e t�cnicos, e o Telemarketing Ativo nas A��es de Cobran�a e Auditoria da Qualidade dos servi�os prestados pela empresa.
								<br />
								<br />
								Ao discar para o <em>0800-081-0195</em> / <em>0800 081 0185</em> o cliente disp�e  do atendimento eletr�nico pr�vio &quot;URA &ndash; UNIDADE DE RESPOSTA AUD�VEL&quot; que oferece menus interativos, agilizam o atendimento atrav�s de consultas eletr�nicas em bancos de dados e servem tamb�m para direcionar as chamadas que requerem atendimento humano para o agente com maior capacidade para atend�-las.
								Dentro das op��es dispon�veis na URA, o nosso cliente poder� contar com o servi�o de 2� via de conta &quot;via fax&quot;, desde que o cliente esteja originando a op��o desejada atrav�s de um telefone-fax, assim como entre outras op��es.
								</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
						
								<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/JUN/2007</span></font>
						
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								
								<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o (segunda, 26 de Janeiro de 2009)</span>
								</div>
								
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
	                		</div>			                   
		       		</div><!-- Content - End -->
	   	    	</div>
	    		 <%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>