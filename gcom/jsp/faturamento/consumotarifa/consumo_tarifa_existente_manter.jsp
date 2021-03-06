<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script><html:javascript staticJavascript="true"  formName="InserirConsumoTarifaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

   var bCancel = false; 

    function validateInserirConsumoTarifaActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validarTarifaConsumo(form) && validateRequired(form) && validateDate(form); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("descTarifa", "Descri��o da Tarifa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function required () { 
     this.ab = new Array("idTarifaTipoCalculo", "Informe Tarifa Tipo Calculo.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("dataVigencia", "Informe Data de Vig�ncia.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

    function DateValidations () { 
     this.aa = new Array("dataVigencia", "Data de Vig�ncia inv�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
    
    function validarTarifaConsumo () { 
     var form = document.InserirConsumoTarifaActionForm;
     if (form.descTarifa.value == ''){
        alert('Informe Descricao da Tarifa.');
  		form.descTarifa.focus();
  		return false;
     }else{
       return true;
     }
    }  

 function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	for (i=0; i < form.elements.length; i++) {
    
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		
			switch (form.elements[i].id){
			
				case "ValorConMinimo.":
				
					if (form.elements[i].value.length < 1){
						alert("Informe Consumo Minimo.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
						alert("Consumo Minimo deve somente conter numeros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(form.elements[i], "Consumo Minimo")){
						form.elements[i].focus();
						camposValidos = false;
					}
					break;

				case "ValorTarMin.":
				
                    var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Tarifa Minima.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Tarifa Minima deve somente conter numeros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Tarifa Minima")){
						form.elements[i].focus();
						camposValidos = false;
					}
					break;
			}
		}    	
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}
				

function abrirCategoria(){
  	var form = document.InserirConsumoTarifaActionForm;
  	if (form.descTarifa.value == ''){
  		alert ('Informe Descricao da Tarifa.');
 		form.descTarifa.focus();
  	} else {
  	    if (validateRequired(form) && validateCaracterEspecial(form) && validateDate(form)){  	    	
  	    	
  	    	if(form.indicadorContratoDemanda[0].value == 1){
  	    		form.indicadorContratoDemanda.value = '1';  	    		
  	    	}else{
  	    		form.indicadorContratoDemanda.value = '2';  	    		
  	    	}
  	    	
  	      window.open('exibirManterCategoriaConsumoTarifaAction.do?limpa=1&parametroVigencia='+form.dataVigencia.value+'&parametroDescricao='+form.descTarifa.value+'&parametroIndContratoDemanda='+form.indicadorContratoDemanda.value+'&parametroIdPerfilLigacao='+form.idLigacaoAguaPerfil.value+'&parametroIdTarifaTipoCalculo='+form.idTarifaTipoCalculo.value+'&parametroSelect=', 'pesquisar','location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=630,height=400');
  	      
  	    }
  	}
 }

function reload() {
	alert("reload");
   	redirecionarSubmit("exibirManterConsumoTarifaAction.do");
      
 }

  function desabilitaCampoTarifa(){
    var form = document.InserirConsumoTarifaActionForm;
    if (form.descTarifa.value != ''){
      form.slcDescTarifa.value = -1;	
      form.slcDescTarifa.disabled = true;
    }else{
      form.slcDescTarifa.disabled = false;
    }
  }


  
  function verficaConsumoExistente(){
  	var form = document.InserirConsumoTarifaActionForm;
  	colecaoConsumoTarifa = form.slcDescTarifa;
  	cont = 0;
  	
  	while (colecaoConsumoTarifa.options.length > cont){
  		if (trim(colecaoConsumoTarifa.options[cont].innerHTML) == trim(form.descTarifa.value.toUpperCase())){
  			alert ('Tarifa de Consumo j� existe. Selecionar a descri��o da lista ao lado.');
  			form.descTarifa.value = "";
  			colecaoConsumoTarifa.disabled = false;
  			colecaoConsumoTarifa.focus();		
  			break;
  		}
  		cont++;
  	}
  	
 }
 function desfazer(){
 	redirecionarSubmit('exibirManterConsumoTarifaExistenteAction.do');
 }
</script>
</head>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirConsumoTarifaActionForm" />
<body leftmargin="5" topmargin="5">
<html:form action="/manterConsumoTarifaExistenteAction"
	name="InserirConsumoTarifaActionForm"
	onsubmit="return validateInserirConsumoTarifaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.InserirConsumoTarifaActionForm"
	method="post">

	<input type="hidden" name="idLigacaoAguaPerfilSelecionado" value="<%=session.getAttribute("idLigacaoAguaPerfil")%>" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">

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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Tarifa de Consumo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%">
				<tr>
					<td>Para alterar a tarifa de consumo, informe os dados
					abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumoAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>											
				</tr>
				</table>
				<table width="100%">
				<tr>
					<td width="184" class="style1"><strong>Descri&ccedil;&atilde;o da Tarifa:<font
						color="#FF0000">*</font></strong></td>
					<td width="210" class="style1"><logic:present
						name="colecaoVigencia">
						<logic:iterate indexId="posicao" name="colecaoVigencia"
							id="vigencia">
							<input type="text" name="descTarifa" size="30" maxlength="30"
								value="<bean:write name="vigencia" property="consumoTarifa.descricao"/>">

						</logic:iterate>
					</logic:present></td>
					<td></td>
				</tr>
				<tr>
					<td class="style1"><strong>Data de Vig&ecirc;ncia:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style1"><logic:present
						name="colecaoVigencia">
						<logic:iterate indexId="posicao" name="colecaoVigencia"
							id="vigencia">
							<input type="text" maxlength="10" name="dataVigencia" size="10"
								value="<bean:write name="vigencia" property="dataVigencia" format="dd/MM/yyyy" />"
								onkeyup="mascaraData(this, event);" />
							
						</logic:iterate>
					</logic:present> 
					<a
								href="javascript:abrirCalendario('InserirConsumoTarifaActionForm', 'dataVigencia')">
								<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>&nbsp; dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td width="184" class="style1" ><strong>Perfil da Liga&ccedil;&atilde;o:</strong></td>
					<td colspan="2" class="style1">
						<html:select value="${sessionScope.selectLigacaoAguaPerfil}" property="idLigacaoAguaPerfil">
								<html:option value="" >&nbsp; </html:option>
								<html:options collection="colecaoLigacaoAguaPerfil" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo Calculo Tarifa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style1">
						<html:select value="${sessionScope.selectTarifaTipoCalculo}" property="idTarifaTipoCalculo">
								<html:option value="">&nbsp; </html:option>
								<html:options collection="colecaoTarifaTipoCalculo" labelProperty="descricaoTarifaTipoCalculo" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Tarifa  Para Contrato de Demanda: </strong></td>
					<td>
						<strong> 
						<html:radio property="indicadorContratoDemanda" value="1" /> Sim 
						<html:radio property="indicadorContratoDemanda" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td class="style1">Categorias e Economias:<strong><font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style1">
					<div align="right"><input type="button" name="adicionar2"
						class="bottonRightCol" value="Adicionar"
						onClick="abrirCategoria();"></div>
					</td>
				</tr>
				<tr>
					<td height="31" colspan="3">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="9%">
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td width="34%">
							<div align="center" class="style9"><strong>Categoria</strong></div>
							</td>
							<td width="19%">
							<div align="center" class="style9"><strong>Consumo M&iacute;nimo
							</strong></div>
							</td>
							<td width="23%">
							<div align="center"><strong>Tarifa M&iacute;nima</strong></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe";%>
						<logic:present name="colecaoCategoria" scope="session">
							<logic:iterate indexId="posicao" name="colecaoCategoria"
								id="helper">

								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td>


									<div align="center"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;" alt="Remover"
										onclick="javascript:redirecionarSubmit('excluirCategoriaConsumoTarifaAction.do?manter=1&posicao=<%=""+GcomAction.obterTimestampIdObjeto(helper)%>');">
									</div>

									</td>
									<td>
									<div align="left" class="style9"><u><a
									   href="javascript:abrirPopup('exibirManterCategoriaConsumoTarifaAction.do?trava=sim&posicao=<%=""+GcomAction.obterTimestampIdObjeto(helper)%>', 'pesquisar','location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=630,height=400');">
									<bean:write name="helper"
										property="consumoTarifaCategoria.categoria.descricao" /></a></u></div>
									</td>

									<td>
									<div align="center" class="style9"><INPUT type="text" id="ValorConMinimo."
										maxlength="6" size="6" name="ValorConMinimo.<bean:write
										name="helper" property="consumoTarifaCategoria.categoria.descricao" />"
										value="<bean:write
										name="helper" property="consumoTarifaCategoria.numeroConsumoMinimo" />"></div>
									</td>
									<td>
									<div align="center" class="style9"><INPUT type="text" onkeyup="formataValorMonetario(this, 17)" style="text-align:right;" size="17"
										maxlength="17" id="ValorTarMin."
										name="ValorTarMin.<bean:write
										name="helper" property="consumoTarifaCategoria.categoria.descricao" />"
										value="<bean:write
										name="helper" property="consumoTarifaCategoria.valorTarifaMinima" formatKey="money.format"/>"></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
				<tr>
					<td colspan="2">
					<logic:present scope="session" name="atualizar">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarConsumoTarifaAction.do';">					</logic:present>
					<logic:notPresent scope="session" name="atualizar">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/exibirManterConsumoTarifaAction.do';">

					</logic:notPresent>
					
					<input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						 onclick="desfazer()">
						
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </td>

					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Atualizar"
							  onclick="javascript:if(validateInserirConsumoTarifaActionForm(document.forms[0]) && validarCamposDinamicos(document.forms[0])){submeterFormPadrao(document.forms[0]);}" 
							  url="manterConsumoTarifaExistenteAction.do"/>
					<!--<input name="botao" class="bottonRightCol"
						value="Atualizar" onclick="javascript:if(validateInserirConsumoTarifaActionForm(document.forms[0]) && validarCamposDinamicos(document.forms[0])){submeterFormPadrao(document.forms[0]);}" type="button"> --></td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
<script>
var form = document.InserirConsumoTarifaActionForm;
var idLigacaoAguaPerfilSelecionado = form.idLigacaoAguaPerfilSelecionado.value;
if (idLigacaoAguaPerfilSelecionado!=''){
	form.idLigacaoAguaPerfil.value = idLigacaoAguaPerfilSelecionado;
}

</script>
</html:form>
</html:html>
