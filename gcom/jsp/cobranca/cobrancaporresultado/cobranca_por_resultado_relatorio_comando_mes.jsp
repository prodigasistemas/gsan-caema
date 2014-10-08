<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">

	 
	 function validaForm(){
		 form = document.forms[0];
		 
		 if(form.idEmpresaContratada.value == -1){
			 alert('Informar Empresa');
			 return false;
		 }

		 if(form.periodoComandoInicial.value == '' && form.periodoComandoInicial.disabled == false &&
				 form.periodoApuracao.value == '' && form.periodoApuracao.disabled == false){
			 alert('Informar Per�odo de Execu��o do Comando ou Per�odo Apura��o');
			 return false;
		 }

		 if(form.periodoComandoInicial.value == '' && form.periodoApuracao.disabled == true){
			 alert('Informar Per�odo de Execu��o do Comando');
			 return false;
		 }

		 if(form.periodoApuracao.value == '' && form.periodoComandoInicial.disabled == true){
			 alert('Informar Per�odo Apura��o');
			 return false;
		 }

		 if(form.periodoComandoInicial.value != '' && form.periodoComandoFinal.value == ''){
			 alert('Informar Per�odo Final de de Execu��o do Comando');
			 return false;
		 }

		 if(form.periodoComandoFinal.value != '' && form.periodoComandoInicial.value == ''){
			 alert('Informar Per�odo Inicial de de Execu��o do Comando');
			 return false;
		 }

		 
			 //form.action = "gerarRelatorioCobrancaPorResultadoComandoMesAction.do";
			 //form.submit();

			 javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioCobrancaPorResultadoComandoMesAction.do');

	 }
	 
	 function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, campo){
			if(!campo.disabled){
				if (objeto == null || codigoObjeto == null){
			    	if(tipo == "" ){
			      		abrirPopup(url,altura, largura);
			    	}else{
			  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
					}
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	 
	 function campoNumerico(campo) {
		var value = campo.value;
	    var bool = isNaN(+value);
	    bool = bool || (value.indexOf('.') != -1);
	    bool = bool || (value.indexOf(",") != -1);
	    if(bool && value.indexOf("/") == -1)
	    	campo.value = '';
	}
	 
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			var form = document.forms[0];
			
		 	if(tipoConsulta == "localidade"){
		 		form.idLocalidade.value = codigoRegistro;
		 		form.descricaoLocalidade.value = descricaoRegistro;
		 		form.descricaoLocalidade.style.color = "#000000";
		 		habilitarDesabilitar(1);
		 	}
	 }
	 
	 function limparConjunto(id){
		    var form = document.forms[0];
		 	if(id == 1){
		 		form.idLocalidade.value = '';
		 		form.descricaoLocalidade.value = '';
		 		form.descricaoLocalidade.style.color = "#000000";
		 	}
	 }
	 
	 function reload(indicador,campo){
		 document.forms[0].action = 'exibirGerarRelatorioCobrancaPorResultadoComandoMesAction.do?indicador='+indicador+"&campo="+campo;
		 document.forms[0].submit();
	 }
	 
	 
	 function verificaData(event){
			
	       	var valor = null;
	     	if (event.which == null){
	        	valor= String.fromCharCode(event.keyCode);   
			}else if (event.which != 0 && event.charCode != 0){
				valor= String.fromCharCode(event.which);
			}   
			 
			if(valor != '/'){
				return isCampoNumerico(event);
			}
		
	    }
	 
	 function habilitarDesabilitar(indicador){
		 form = document.forms[0];
		 if(indicador == 2){
			 if(qtdSelecionados(form.idsRegiao) != 0 ||
				qtdSelecionados(form.idsMicroregiao) != 0 || 
				qtdSelecionados(form.idsMunicipio) != 0){
					 desselecionar(form.idsGerenciaRegional);
					 desselecionar(form.idsUnidadeNegocio);
					 form.idLocalidade.value = '';
					 form.descricaoLocalidade.value = '';
					 form.idsGerenciaRegional.disabled = true;
					 form.idsUnidadeNegocio.disabled = true;
					 form.idLocalidade.disabled = true;
					 document.getElementById("idLocalidadeLink").href = "javascript:void(0)";
					 form.periodoComandoInicial.disabled = true;
					 form.periodoComandoFinal.disabled = true;
					 form.periodoComandoInicial.value = '';
					 form.periodoComandoFinal.value = '';
					 form.idComando.disabled = true;
					 form.idsCategoria.disabled = true;	
			 }
			 else{
				 form.idsGerenciaRegional.disabled = false;
				 form.idsUnidadeNegocio.disabled = false;
				 form.idLocalidade.disabled = false;
				 document.getElementById("idLocalidadeLink").href = "javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);";
				 if(form.periodoApuracao.value == ''){
					 form.periodoComandoInicial.disabled = false;
					 form.periodoComandoFinal.disabled = false;
					 form.idComando.disabled = false;
					 form.idsCategoria.disabled = false;
				 }else{
					 form.periodoComandoInicial.disabled = true;
					 form.periodoComandoFinal.disabled = true;
					 form.periodoComandoInicial.value = '';
					 form.periodoComandoFinal.value = '';
					 form.idComando.disabled = true;
					 form.idsCategoria.disabled = true; 					
				}					 
			 }	 
		 }
		 else if(indicador == 1){
			 if(qtdSelecionados(form.idsGerenciaRegional) != 0 ||
				qtdSelecionados(form.idsUnidadeNegocio) != 0 || 
				form.idLocalidade.value != '' ){
					 desselecionar(form.idsRegiao);
					 desselecionar(form.idsMicroregiao);
					 desselecionar(form.idsMunicipio);
					 form.idsRegiao.disabled = true;
					 form.idsMicroregiao.disabled = true;
					 form.idsMunicipio.disabled = true;					 
					 form.periodoComandoInicial.disabled = true;
					 form.periodoComandoFinal.disabled = true;
					 form.periodoComandoInicial.value = '';
					 form.periodoComandoFinal.value = '';
					 form.idComando.disabled = true;
					 form.idsCategoria.disabled = true;
			 }
			 else{
				 form.idsRegiao.disabled = false;
				 form.idsMicroregiao.disabled = false;
				 form.idsMunicipio.disabled = false;
				 if(form.periodoApuracao.value == ''){
					 form.periodoComandoInicial.disabled = false;
					 form.periodoComandoFinal.disabled = false;
					 form.idComando.disabled = false;
					 form.idsCategoria.disabled = false;
				 }else{
					 form.periodoComandoInicial.disabled = true;
					 form.periodoComandoFinal.disabled = true;
					 form.periodoComandoInicial.value = '';
					 form.periodoComandoFinal.value = '';
					 form.idComando.disabled = true;
					 form.idsCategoria.disabled = true; 
				 }	 
			 }
		 } 
		 else if(indicador == 3){
			 if(qtdSelecionados(form.idsCategoria) != 0 ||
				form.periodoComandoInicial.value != '' ||	
				form.periodoComandoFinal.value != '' ||
				form.idComando.value != -1){

				 form.periodoApuracao.value = '';
				 form.periodoApuracao.disabled = true;
				 form.idLocalidade.value = '';
				 form.descricaoLocalidade = '';
				 form.idsGerenciaRegional.disabled = true;
				 form.idsUnidadeNegocio.disabled = true;
				 form.idLocalidade.disabled = true;
				 document.getElementById("idLocalidadeLink").href = "javascript:void(0)";
				 form.idsRegiao.disabled = true;
				 form.idsMicroregiao.disabled = true;
				 form.idsMunicipio.disabled = true;
				}
			 else{
				 form.periodoApuracao.disabled = false;
				 form.idsGerenciaRegional.disabled = false;
				 form.idsUnidadeNegocio.disabled = false;
				 form.idLocalidade.disabled = false;
				 document.getElementById("idLocalidadeLink").href = "javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);";
				 form.idsRegiao.disabled = false;
				 form.idsMicroregiao.disabled = false;
				 form.idsMunicipio.disabled = false; 
	 		}
		 }
		 else if(indicador == 4){
			 if(form.periodoApuracao.value != ''){
				 form.periodoComandoInicial.disabled = true;
				 form.periodoComandoFinal.disabled = true;
				 form.periodoComandoInicial.value = '';
				 form.periodoComandoFinal.value = '';
				 form.idComando.disabled = true;
				 form.idsCategoria.disabled = true; 
			 }
			 else{
				 form.periodoComandoInicial.disabled = false;
				 form.periodoComandoFinal.disabled = false;
				 form.idComando.disabled = false;
				 form.idsCategoria.disabled = false;	
			}
		 }
	 }
	 
	 
	 function qtdSelecionados(selObj) {
	   var totalChecked = 0;
	   for (i = 0; i < selObj.options.length; i++) {
	      if (selObj.options[i].selected && selObj.options[i].value != -1) {
	         totalChecked++;
	      }
	   }
	  return totalChecked;	
	}
	
	 
	 function qtdSelecionadosRadio(selObj) {
		   var totalChecked = 0;
		   for (i = 0; i < selObj.length; i++) {
		      if (selObj[i].checked) {
		         totalChecked++;
		      }
		   }
		  return totalChecked;	
		}
	 
	function desselecionar(selObj){
		for (i = 0; i < selObj.options.length; i++) {
	      if (selObj.options[i].selected) {
	    	  selObj.options[i].selected = false;
	      }
	   }
	} 
	
	function limparArrecadacao(){
		document.forms[0].encerramentoArrecadacao.value = "";
	}

	 function replicar(origem, destino){
		 destino.value = origem.value;
 	}
	 
	
</script>


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">  
<html:form action="/gerarRelatorioCobrancaPorResultadoComandoMesAction"
	name="GerarRelatorioCobrancaPorResultadoComandoMesActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.GerarRelatorioCobrancaPorResultadoComandoMesActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="cancelarValidacao" value="true" />
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<%@ include file="/jsp/util/mensagens.jsp"%>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relat�rio de Cobran�a por Resultado por Comando/M�s</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para gerar o relat�rio de cobran�a por resultado por comando/m�s, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>

					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="idEmpresaContratada"
						tabindex="4" onchange="reload(3,0)">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoEmpresasContratadas">					
								<html:options collection="colecaoEmpresasContratadas"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</td>

				</tr>
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="3"><strong>Op��o Totaliza��o Por M�s de Apura��o:</strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
               	   <td><strong>Per�odo de Execu��o do Comando:</strong></td>
	               <td colspan="2"><strong> 
						<html:text  property="periodoComandoInicial" 
									size="10"
									maxlength="10" 
									tabindex="9" 
									onkeypress="return verificaData(event);"
									onkeyup="mascaraData(this, event);replicar(this,document.forms[0].periodoComandoFinal);" 
									onchange="reload(3,4)"
									/> 
							<a href="javascript:abrirCalendarioReplicandoComFuncaoRetorno('GerarRelatorioCobrancaPorResultadoComandoMesActionForm', 'periodoComandoInicial', 'periodoComandoFinal', 'reload(3,4)');"  style="text-decoration:none;">
								<img border="0"
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle"
									 alt="Exibir Calend�rio" />
							</a> </strong>
							a
						<html:text property="periodoComandoFinal" 
								   size="10"
							 	   maxlength="10" 
							 	   tabindex="10" 
							 	   onkeypress="return verificaData(event);"
							 	   onkeyup="mascaraData(this, event);" 
							 	   onchange="reload(3,4)"/> 
							<a href="javascript:abrirCalendarioComFuncaoRetorno('GerarRelatorioCobrancaPorResultadoComandoMesActionForm', 'periodoComandoFinal','reload(3,4)');" style="text-decoration:none;">
								<img border="0"
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle"
									 alt="Exibir Calend�rio" />
						</a> (DD/MM/AAA) </td>
                </tr>

				<tr>

					<td><strong>Comando:</strong></td>
					<td colspan="2" align="left"><html:select property="idComando" tabindex="4" onchange="javascript:habilitarDesabilitar(3)">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoComando">					
								<html:options collection="colecaoComando"
									labelProperty="id" property="id" />
							</logic:present>
						</html:select>
					</td>

				</tr>
				
				<tr>
					<td width="30%"><strong>Categoia:</strong></td>
					<td colspan="2"><html:select property="idsCategoria" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(3)">
						<logic:notEmpty name="colecaoCategoria">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoCategoria"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td colspan="3"><strong>Op��o Totaliza��o Por Comando:</strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
	                <td><strong>Per�odo Apura��o:</strong></td>
		            <td colspan="2"><strong>
		                  <html:text maxlength="7"
		                  			 size="7"
		                  			 tabindex="1"
		                  			 property="periodoApuracao"
			                  			 onkeypress="javascript:validaEnterString(event, 'exibirGerarRelatorioCobrancaPorResultadoComandoMesAction.do?pesquisarDataEncerramento=OK&indicador=4', 'periodoApuracao');return verificaData(event);"
			                  			 onchange="campoNumerico(this);limparArrecadacao();habilitarDesabilitar(4);"
			                  			 onkeyup="mascaraAnoMes(this, event);"/>
		                    	   
		     			</strong>(MM/AAAA)
		     			 &nbsp;&nbsp;
			     		<strong>Encerramento da Arrecada��o:
		             	 <html:text
	     					   maxlength="10"
	     					   size="10"
	     					   readonly="true"
	     					   property="encerramentoArrecadacao"
	 		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
		   		     					   
						</strong>
					</td>
                 </tr>
                                 
             	<tr>
					<td width="30%"><strong>Ger�ncia Regional:</strong></td>
					<td colspan="2"><html:select property="idsGerenciaRegional" tabindex="3" multiple="mutiple" size="4" onclick="javascript:reload(1,1)">
						<logic:notEmpty name="colecaoGerenciaRegional">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Unidade Neg�cio:</strong></td>
					<td colspan="2"><html:select property="idsUnidadeNegocio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(1)" >
						<logic:notEmpty name="colecaoUnidadeNegocio">
						<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>	
              
	              <tr>
		                <td><strong>Localidade:</strong></td>
		                <td colspan="2"><strong>
		                  <html:text maxlength="3"
		                  			 size="3"
		                  			 tabindex="1"
		                  			 property="idLocalidade"
		                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioCobrancaPorResultadoComandoMesAction.do?pesquisarLocalidade=OK&indicador=1', 'idLocalidade','Localidade');return isCampoNumerico(event);" 
		                  			 onchange="campoNumerico(this);habilitarDesabilitar(1);"/>
		                <a id="idLocalidadeLink" href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							     title="Pesquisar Localidade" /></a>
						<logic:notPresent name="localidadeException" scope="request">	     
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoLocalidade"
	   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
		     			</logic:notPresent>
		     			<logic:present name="localidadeException" scope="request">
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoLocalidade"
			     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
		     			</logic:present>	     
		     			</strong>
		     			<a href="javascript:limparConjunto(1);habilitarDesabilitar(1);">
							<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
						</a>
		     		</td>
	             </tr>

				<tr>
					<td width="30%"><strong>Regi�o:</strong></td>
					<td colspan="2"><html:select property="idsRegiao" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(2)" onclick="javascript:reload(2,2)">
						<logic:notEmpty name="colecaoRegiao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoRegiao"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Microrregi�o:</strong></td>
					<td colspan="2"><html:select property="idsMicroregiao" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(2)" onclick="javascript:reload(2,3)">
						<logic:notEmpty name="colecaoMicroregiao">
						<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoMicroregiao"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr> 
				
				<tr>
					<td width="30%"><strong>Munic�pio:</strong></td>
					<td colspan="2"><html:select property="idsMunicipio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(2)">
						<logic:notEmpty name="colecaoMunicipio">
						<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoMunicipio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>                   
				
					
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left" colspan="3"><font color="#FF0000">*</font> Campo Obrigat�rio</td>
				</tr>
<!-- 				<tr>
					<!--<td colspan="4" bgcolor="#3399FF">
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
			    </tr> -->	
				<tr>
					<td>&nbsp;</td>
				</tr>
					
				              			
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioCobrancaPorResultadoComandoMesAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>

					<td align="right"><input type="button" name="gerar" class="bottonRightCol" value="Emitir" onClick="javascript:validaForm();" /></td>

				</tr>
				
					
				</table>
			</td></tr>
		</table>
	<p>&nbsp;</p>
<logic:present name="indicadorBloqueio" scope="request">
	<script>javascript:habilitarDesabilitar(${requestScope.indicadorBloqueio});</script>
</logic:present>
		
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>