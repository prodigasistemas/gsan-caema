<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.Collection" %>
<%@ page import="gcom.cadastro.localidade.Localidade"%>
<%@ page import="gcom.cadastro.localidade.SetorComercial"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/grid.locale-pt-br.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.mouse.js"></script>

<html:javascript staticJavascript="false" formName="ImovelEmissaoOrdensSeletivasActionForm" dynamicJavascript="false" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>cadastro/imovel/imovel_emissao_ordens_seletivas.js"></script>

<%
	Collection<Localidade> localidades = (Collection<Localidade>) session.getAttribute("colecaoLocalidadesContasEmCobrancaPorEmpresa");
	Collection<SetorComercial> setores = (Collection<SetorComercial>) session.getAttribute("colecaoSetoresContasEmCobrancaPorEmpresa");
	String allLoca = null;
	String allSet = null;
	if(localidades !=null && !localidades.isEmpty()){
		for(Localidade loc : localidades){
			if(allLoca !=null){
				allLoca += loc.getId()+","+loc.getDescricao()+"::";
			}else{
				allLoca = "'"+loc.getId()+","+loc.getDescricao()+"::";
			}
		}
		allLoca+="'";
	}
	if(setores !=null && !setores.isEmpty()){
		for(SetorComercial set: setores){
			if(allSet !=null){
				allSet += set.getLocalidade().getId()+","+set.getCodigo()+","+
						  set.getDescricao()+","+set.getMunicipio().getNome()+","+
				          set.getIndicadorSetorAlternativo()+"::";
			}
			else{
				allSet = "'"+set.getLocalidade().getId()+","+set.getCodigo()+","+
						  set.getDescricao()+","+set.getMunicipio().getNome()+","+
				          set.getIndicadorSetorAlternativo()+"::";
			}
		}
		allSet +="'";
	}
%>
<script type="text/javascript">

    jQuery(document).ready(function () {
    	 var grid = jQuery("#grid");
    	 var allLoca = []; 
         var allSet = []; 
         var s = [];
         var locas;
         var sets;         
         <%
         	if(allLoca !=null){
         %>
         	allLoca = null;
         	allSet = null;
         	allLoca = <%=allLoca %>;
         	allSet = <%=allSet %>;
         <% } %>
         grid.jqGrid({
             datatype: "local",
             colNames: ['Código','Localidade'],
             colModel: [
                        {name:'cod',index:'cod',width:90,sorttype:"int"},
                        {name:'desc',index:'desc',width:100, sorttype:"text"}
                    ],
			 //rowNum:10, 
			 //rowList:[10,20,30], 
			 pager: '#pager',
			 //sortname: 'numero', 
			 viewrecords: false, 
			 //sortorder: "asc",
			 multiselect: false,
			 subGrid : true,
			 //Caso aja uma subgrid
			 subGridRowExpanded: function(subgrid_id, row_id) {
			     var pager_id;
			     var dataSub = [];
			     subgrid_table_id = subgrid_id+"_t";
			     pager_id = "p_"+subgrid_table_id;
			     $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			    // $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table>");
			     jQuery("#"+subgrid_table_id).jqGrid({        
			         datatype: "local",
			         colNames: ['Setor','Quadra', 'Rota', 'Seq.'],
			         colModel: [
			             {name:'cod',index:'cod',width:50,sorttype:"int",sortable:false},			           
			             {name:'qdr',index:'qdr',width:110,sortable:false},
			             {name:'rot',index:'rot',width:110,sortable:false},
			             {name:'seq',index:'seq',width:110,sortable:false}
			         ],
			         rowNum:2000,
			         //pager: pager_id,
			         sortname: 'cod',
			         sortorder: "asc",
			         multiselect: true,
			         height: 'auto',
			         autowidth:true,
			         jsonReader: { repeatitems : false, root:"attribute" }
			     });
			     var contSub = 0;    
			     locas = allLoca.split("::");
			     sets = allSet.split("::");
			     index = parseInt(row_id)
			     if(locas[index]!=""){
			 		tempLoca = locas[index].split(",");
			 		for(j = 0; j < sets.length; j++){
			 			if(sets[j]!=""){
			 				tempSet = sets[j].split(",");
			 				if(tempLoca[0] == tempSet[0]){
				 				nameInput = tempSet[0]+"_"+tempSet[1];				 				
				 				quadra = 'Inicial: <input type="text" id="q_'+nameInput+'_qdrIni" '
				 				+'name="q_'+nameInput+'_qdrIni" maxlength="4" style="margin-left: 10px;" '
				 				+'size="5" disabled="true" class="salvaDigito" '
				 				+'onkeyup="replicarCampo(document.forms[0].q_'+nameInput+'_qdrFin, document.forms[0].q_'+nameInput+'_qdrIni);"'
				 				+'onkeypress="return isCampoNumerico(event);"'
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].q_'+nameInput+'_qdrIni, \'Quadra Inicial\');"'
				 				+' />'
				 				+'<br />'
				 				+'Final: <input type="text" id="q_'+nameInput+'_qdrFin" '
				 				+'name="q_'+nameInput+'_qdrFin" maxlength="4" size="5" style="margin-left: 15px;" disabled="true" class="salvaDigito" '
				 				+'onkeypress="return isCampoNumerico(event);"'			 				
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].q_'+nameInput+'_qdrFin, \'Quadra Final\');"'
				 				+'/>';

				 				rota = '<input type="text" id="r_'+nameInput+'_rotIni" name="r_'+nameInput+'_rotIni" maxlength="4" '+
				 				'style="margin-left: 30px;" size="5" disabled="true" class="rota salvaDigito"'
				 				+'onkeyup="replicarCampo(document.forms[0].r_'+nameInput+'_rotFin, document.forms[0].r_'+nameInput+'_rotIni);"'
				 				+'onkeypress="return isCampoNumerico(event);"'
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].r_'+nameInput+'_rotIni, \'Rota Inicial\');"'
				 				+'/>'
				 				+'<br />'
				 				+'<input type="text" id="r_'+nameInput+'_rotFin" '+
				 				'name="r_'+nameInput+'_rotFin" maxlength="4" size="5" style="margin-left: 30px;" disabled="true" class="salvaDigito" '
				 				+'onkeypress="return isCampoNumerico(event);"'
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].r_'+nameInput+'_rotFin, \'Rota Final\');"'
				 				+'/>';

				 				sequencial = '<input type="text" id="s_'+nameInput+'_seqIni" name="s_'+nameInput+'_seqIni" maxlength="6" '+
				 				'style="margin-left: 30px;" size="7" disabled="true" class="salvaDigito" '
								+'onkeyup="replicarCampo(document.forms[0].s_'+nameInput+'_seqFin, document.forms[0].s_'+nameInput+'_seqIni);"'
				 				+'onkeypress="return isCampoNumerico(event);"'
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].s_'+nameInput+'_seqIni, \'Seq. Inicial\');"'				 				
				 				+'/>'
				 				+'<br />'
				 				+'<input type="text" id="s_'+nameInput+'_seqFin" '+
				 				'name="s_'+nameInput+'_seqFin" maxlength="6" size="7" style="margin-left: 30px;" disabled="true" class="salvaDigito" '
				 				+'onkeypress="return isCampoNumerico(event);"'
				 				+'onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].s_'+nameInput+'_seqFin, \'Seq. Final\');"'
				 				+'/>'
			 					dataSub[contSub] = {
					 					cod: tempSet[1], 
					 					setCom: tempSet[2], 
					 					qdr: quadra, 
					 					rot: rota,
				 						seq: sequencial
				 				};		 					
			 					contSub++;
			 				}
			 			}
			 		}
			 		for(k =0; k < dataSub.length;k++) {
			 			jQuery("#"+subgrid_table_id).jqGrid('addRowData', k, dataSub[k]);
			 		}
			 		jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false});
			 		jQuery("#"+subgrid_table_id).trigger("reloadGrid");

			 		$("#grid_"+index+"_t tr:not(:first-child)").css('height', '50px');			 		
			 		
			 		/* Verifica quais setores foram
			         * selecionados ou deselecionados
			         * e atualiza os campos que armazenam
			         * esses índices através do evento
			         * de movimento do mouse
			         * sobre o checkbox 
			         */
			        $(".cbox").mousemove(function(){				        
			        	indSet = $("input[name='indiceSetoresSelecionadosComponent']").val();			        	
			        	indSetNotLoca="";			        	        	
			        	s = jQuery("#"+subgrid_table_id).jqGrid('getGridParam','selarrrow');
			        	loca = locas[index].split(",");	
			        	tempSet="";
			        	tempIndSet="";	
			        	if(s !=null && s !=""){
			        		for(i = 0; i < s.length; i++){
			        			tempIndSet +=s[i]+",";			
			        		}
			        		if(indSet !=null && indSet !=""){
			        			vt1 = tempIndSet.split(",");
			        			vt3 = indSet.split("::");
			        			vt3.shift();			        					        						
			        			for(i = 0; i < vt3.length; i++){				        			
			        				vt2 = vt3[i+1].split(",");
			        				if(vt2[0] !="" && (loca[0] == vt3[i])){
			        					if(!vt1.contains(vt2[0])){
			        						vt1.push(vt2[0]);
			        					}			        					
			        				}
			        				else if(vt2[0] !="" && (loca[0] != vt3[i])){
										indSetNotLoca += "::"+vt3[i]+"::"+vt3[i+1];					
				        			}
			        				
			        				i++;		        				
			        				
			        			}
			        			tempIndSet = "";
			        			for(i = 0; i < vt1.length; i++){
			        				if(vt1[i] !="" && vt1[i] !=","){
			        					tempIndSet += vt1[i]+",";
			        				}
			        			}
			        		}
			        		indSetSel = [];			        		
			        		css = 0;
			        		for(i = 0; i<sets.length;i++){
			        			if(sets[i]!=""){
					 				tempSet = sets[i].split(",");
					 				if(loca[0] == tempSet[0]){								
										indSetSel[css] = i;
										css++;
									}
			        			} 
				        	}
			        		vTemp = tempIndSet.split(",");
			        		for(i = 0; i< vTemp.length; i++){
			        			if(vTemp[i] !="" && vTemp[i]!=","){
			        				check = document.getElementById("jqg_grid_"+index+"_t_"+(parseInt(vTemp[i])));
			        				tr =  "#"+(parseInt(s[i]));
			        				if(check !=null && check.checked != true){
			        					vect = sets[indSetSel[parseInt(vTemp[i])]].split(",");
				        				nameInput = loca[0]+"_"+vect[1];
				        				$("#q_"+nameInput+"_qdrIni").attr('disabled',true);				        				
				        				$("#r_"+nameInput+"_rotIni").attr('disabled',true);
				        				$("#s_"+nameInput+"_seqIni").attr('disabled',true);
				        				$("#q_"+nameInput+"_qdrFin").attr('disabled',true);
				        				$("#r_"+nameInput+"_rotFin").attr('disabled',true);
				        				$("#s_"+nameInput+"_seqFin").attr('disabled',true);

				        				$("#q_"+nameInput+"_qdrIni").attr('value','');
				        				$("#r_"+nameInput+"_rotIni").attr('value','');
				        				$("#s_"+nameInput+"_seqIni").attr('value','');
				        				$("#q_"+nameInput+"_qdrFin").attr('value','');
				        				$("#r_"+nameInput+"_rotFin").attr('value','');
				        				$("#s_"+nameInput+"_seqFin").attr('value','');
			        					for(j = i; j < vTemp.length; j++){
			        						vTemp[j] = vTemp[j+1]; 
			        					}
			        					
			        					vTemp.pop();
			        					$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr');

			        					digiTemp = $("input[name='digitados']").val().split("::");
			    			    		dadoVai = "";			    		
			    			    		for(j = 0; j < digiTemp.length; j++){
			    				    		if(digiTemp[j] !=""){				    		
			    								dadoTemp = digiTemp[j].split("_");
			    								nome = dadoTemp[1]+"_"+dadoTemp[2];
			    								if(nameInput == nome){
			    									digiTemp.splice(j, 1)
			    								}
			    								else{
			    									dadoVai +="::"+dadoTemp[0]+"_"+dadoTemp[1]+"_"+dadoTemp[2]+"_"+dadoTemp[3]+"_"+dadoTemp[4];
			    								}
			    			    			}
			    				    	}			    				    	
			    				    	$("input[name='digitados']").attr('value', dadoVai);
			        				}
			        			}
			        		}
			        		
			        		setSel =  $("input[name='setoresSelecionadosComponent']").val().split("::");
							setSel.shift();
							setSelNotLoca ="";
							for(i =0; i <setSel.length; i++){
								vtt = setSel[i+1].split(",");
								if(vtt[0] !="" && (loca[0] != setSel[i])){
									setSelNotLoca += "::"+setSel[i]+"::"+setSel[i+1];					
			        			}
			        			i++;
							}
				        		
			        		tempSet="";
			        		tempIndSet = "";
			        		for(i = 0; i < vTemp.length; i++){
			        			if(vTemp[i] !="" && vTemp[i]!=","){
			        				tempIndSet += "::"+loca[0]+"::"+vTemp[i]+",";
			        				vect = sets[indSetSel[parseInt(vTemp[i])]].split(",");			
			        				tempSet +=  "::"+loca[0]+"::"+vect[1]+",";

			        				nameInput = loca[0]+"_"+vect[1];
			        				$("#q_"+nameInput+"_qdrIni").attr('disabled',false);
			        				$("#r_"+nameInput+"_rotIni").attr('disabled',false);			        				
			        				$("#q_"+nameInput+"_qdrFin").attr('disabled',false);
			        				$("#r_"+nameInput+"_rotFin").attr('disabled',false);			        					        							
			        			}
			        		}
			        		
			        				
			        		$("input[name='setoresSelecionadosComponent']").attr('value',setSelNotLoca+tempSet);
			        		$("input[name='indiceSetoresSelecionadosComponent']").attr('value',indSetNotLoca+tempIndSet);			        		
			        	}
	        			
			        });
			         /* Verifica quais setores foram
			          * selecionados ou deselecionados
			          * e atualiza os campos que armazenam
			          * esses índices através do evento
			          * de clique sobre o checkbox 
			          */
			        $(".cbox").click(function(){				        
			        	indSet = $("input[name='indiceSetoresSelecionadosComponent']").val();			        	
			        	indSetNotLoca="";			        	        	
			        	s = jQuery("#"+subgrid_table_id).jqGrid('getGridParam','selarrrow');
			        	loca = locas[index].split(",");	
			        	tempSet="";
			        	tempIndSet="";	
			        	if(s !=null && s !=""){
			        		for(i = 0; i < s.length; i++){
			        			tempIndSet +=s[i]+",";			
			        		}
			        		if(indSet !=null && indSet !=""){
			        			vt1 = tempIndSet.split(",");
			        			vt3 = indSet.split("::");
			        			vt3.shift();			        					        						
			        			for(i = 0; i < vt3.length; i++){				        			
			        				vt2 = vt3[i+1].split(",");
			        				if(vt2[0] !="" && (loca[0] == vt3[i])){
			        					if(!vt1.contains(vt2[0])){
			        						vt1.push(vt2[0]);
			        					}			        					
			        				}
			        				else if(vt2[0] !="" && (loca[0] != vt3[i])){
										indSetNotLoca += "::"+vt3[i]+"::"+vt3[i+1];					
				        			}
			        				
			        				i++;		        				
			        				
			        			}
			        			tempIndSet = "";
			        			for(i = 0; i < vt1.length; i++){
			        				if(vt1[i] !="" && vt1[i] !=","){
			        					tempIndSet += vt1[i]+",";
			        				}
			        			}
			        		}
			        		indSetSel = [];			        		
			        		css = 0;
			        		for(i = 0; i<sets.length;i++){
			        			if(sets[i]!=""){
					 				tempSet = sets[i].split(",");
					 				if(loca[0] == tempSet[0]){								
										indSetSel[css] = i;
										css++;
									}
			        			} 
				        	}
			        		vTemp = tempIndSet.split(",");
			        		for(i = 0; i< vTemp.length; i++){
			        			if(vTemp[i] !="" && vTemp[i]!=","){
			        				check = document.getElementById("jqg_grid_"+index+"_t_"+(parseInt(vTemp[i])));
			        				tr =  "#"+(parseInt(s[i]));
			        				if(check !=null && check.checked != true){
			        					vect = sets[indSetSel[parseInt(vTemp[i])]].split(",");
				        				nameInput = loca[0]+"_"+vect[1];
				        				$("#q_"+nameInput+"_qdrIni").attr('disabled',true);				        				
				        				$("#r_"+nameInput+"_rotIni").attr('disabled',true);
				        				$("#s_"+nameInput+"_seqIni").attr('disabled',true);
				        				$("#q_"+nameInput+"_qdrFin").attr('disabled',true);
				        				$("#r_"+nameInput+"_rotFin").attr('disabled',true);
				        				$("#s_"+nameInput+"_seqFin").attr('disabled',true);

				        				$("#q_"+nameInput+"_qdrIni").attr('value','');
				        				$("#r_"+nameInput+"_rotIni").attr('value','');
				        				$("#s_"+nameInput+"_seqIni").attr('value','');
				        				$("#q_"+nameInput+"_qdrFin").attr('value','');
				        				$("#r_"+nameInput+"_rotFin").attr('value','');
				        				$("#s_"+nameInput+"_seqFin").attr('value','');
			        					for(j = i; j < vTemp.length; j++){
			        						vTemp[j] = vTemp[j+1]; 
			        					}
			        					
			        					vTemp.pop();
			        					$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr');

			        					digiTemp = $("input[name='digitados']").val().split("::");
			    			    		dadoVai = "";			    		
			    			    		for(j = 0; j < digiTemp.length; j++){
			    				    		if(digiTemp[j] !=""){				    		
			    								dadoTemp = digiTemp[j].split("_");
			    								nome = dadoTemp[1]+"_"+dadoTemp[2];
			    								if(nameInput == nome){
			    									digiTemp.splice(j, 1)
			    								}
			    								else{
			    									dadoVai +="::"+dadoTemp[0]+"_"+dadoTemp[1]+"_"+dadoTemp[2]+"_"+dadoTemp[3]+"_"+dadoTemp[4];
			    								}
			    			    			}
			    				    	}			    				    	
			    				    	$("input[name='digitados']").attr('value', dadoVai);
			        				}
			        			}
			        		}
			        		
			        		setSel =  $("input[name='setoresSelecionadosComponent']").val().split("::");
							setSel.shift();
							setSelNotLoca ="";
							for(i =0; i <setSel.length; i++){
								vtt = setSel[i+1].split(",");
								if(vtt[0] !="" && (loca[0] != setSel[i])){
									setSelNotLoca += "::"+setSel[i]+"::"+setSel[i+1];					
			        			}
			        			i++;
							}
				        		
			        		tempSet="";
			        		tempIndSet = "";
			        		for(i = 0; i < vTemp.length; i++){
			        			if(vTemp[i] !="" && vTemp[i]!=","){
			        				tempIndSet += "::"+loca[0]+"::"+vTemp[i]+",";
			        				vect = sets[indSetSel[parseInt(vTemp[i])]].split(",");			
			        				tempSet +=  "::"+loca[0]+"::"+vect[1]+",";

			        				nameInput = loca[0]+"_"+vect[1];
			        				$("#q_"+nameInput+"_qdrIni").attr('disabled',false);
			        				$("#r_"+nameInput+"_rotIni").attr('disabled',false);			        				
			        				$("#q_"+nameInput+"_qdrFin").attr('disabled',false);
			        				$("#r_"+nameInput+"_rotFin").attr('disabled',false);			        					        							
			        			}
			        		}
			        		
			        				
			        		$("input[name='setoresSelecionadosComponent']").attr('value',setSelNotLoca+tempSet);
			        		$("input[name='indiceSetoresSelecionadosComponent']").attr('value',indSetNotLoca+tempIndSet);			        		
			        	}
		        			
				        });			        
			        
			        s = $("input[name='indiceSetoresSelecionadosComponent']").val().split("::");
			        s.shift();
			    	if(s !=null && s !=""){
			    		loca = locas[index].split(",");
			    		indSetSel = [];			        		
			        	css = 0;
			        	for(i = 0; i<sets.length;i++){
			        		if(sets[i]!=""){
					 			tempSet = sets[i].split(",");
				 				if(loca[0] == tempSet[0]){								
									indSetSel[css] = i;
									css++;
								}
	        				} 
			        	}
			    		for(i = 0; i < s.length; i++){
				    		vtt = s[i+1].split(",");				    		
			    			if(vtt[0] !="" && s[i] == loca[0]){
			    				check ="#jqg_grid_"+index+"_t_"+(parseInt(vtt[0]));
			    				tr =  "#grid_"+index+"_t > tbody > tr[id="+(parseInt(vtt[0]))+"]";			 
			    				$(check).attr('checked', true);
			    				$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr ui-state-highlight');
			    				vect = sets[indSetSel[parseInt(vtt[0])]].split(",");
			    				nameInput = loca[0]+"_"+vect[1];
			    				$("#q_"+nameInput+"_qdrIni").attr('disabled',false);
		        				$("#r_"+nameInput+"_rotIni").attr('disabled',false);		        				
		        				$("#q_"+nameInput+"_qdrFin").attr('disabled',false);
		        				$("#r_"+nameInput+"_rotFin").attr('disabled',false);

		        				
		        				digiTemp = $("input[name='digitados']").val().split("::");		        				
		    			        if(digiTemp !=""){					    					    		
		    			    		for(j = 0; j < digiTemp.length; j++){
		    				    		if(digiTemp[j] !=""){				    		
		    								dadoTemp = digiTemp[j].split("_");
		    								nome = dadoTemp[0]+"_"+dadoTemp[1]+"_"+dadoTemp[2]+"_";	
		    								term ="";
		    								usa = false;
		    								removeDigi = true;
		    								if(dadoTemp[3] == "qdrini"){
		    									term = "qdrIni";
		    									usa = true;
		    								}
		    								if(dadoTemp[3] == "qdrfin"){
		    									term = "qdrFin";
		    									usa = true;
		    								}									
		    								if(dadoTemp[3] == "rotini"){
		    									term = "rotIni";
		    									usa = true;
		    								}
		    								if(dadoTemp[3] == "rotfin"){
		    									term = "rotFin";
		    									usa = true;
		    								}
		    								if(dadoTemp[3] == "seqini"){
		    									term = "seqIni";
		    									usa = true;
		    								}
		    								if(dadoTemp[3] == "seqfin"){
		    									term = "seqFin";
		    									usa = true;
		    								}
		    								if(usa){
			    								nomeTest = nome+term;			    								
			    								if(nomeTest == "q_"+nameInput+"_qdrIni" || 
			    								   nomeTest == "q_"+nameInput+"_qdrFin" ||
			    								   nomeTest == "r_"+nameInput+"_rotIni" ||
			    								   nomeTest == "r_"+nameInput+"_rotFin" ||
			    								   nomeTest == "s_"+nameInput+"_seqIni" ||
			    								   nomeTest == "s_"+nameInput+"_seqFin" 
			    								   ){
			    									removeDigi = false;
		    										$("#"+nome+term).attr('value', dadoTemp[4]);
			    								}
		    								}else{
		    									nomeTest = nome+dadoTemp[3];
		    									if(nomeTest == "q_"+nameInput+"_qdrIni" || 
					    								   nomeTest == "q_"+nameInput+"_qdrFin" ||
					    								   nomeTest == "r_"+nameInput+"_rotIni" ||
					    								   nomeTest == "r_"+nameInput+"_rotFin" ||
					    								   nomeTest == "s_"+nameInput+"_seqIni" ||
					    								   nomeTest == "s_"+nameInput+"_seqFin" 
					    								   ){
		    										removeDigi = false;
		    										$("#"+nome+dadoTemp[3]).attr('value', dadoTemp[4]);
		    									}
		    								}
		    								if(removeDigi){
		    									digiTemp.remove(j);
			    							}
		    			    			}
		    				    	}
		    			        }

		        				if($("#r_"+nameInput+"_rotIni").val() !=""){
		        					$("#s_"+nameInput+"_seqIni").attr('disabled',false);
									$("#s_"+nameInput+"_seqFin").attr('disabled',false);
			        			}
		        				else{
		        					$("#s_"+nameInput+"_seqIni").attr('value','');
									$("#s_"+nameInput+"_seqFin").attr('value','');
		        					$("#s_"+nameInput+"_seqIni").attr('disabled',true);
									$("#s_"+nameInput+"_seqFin").attr('disabled',true);
			        			}		        				
			    			}
			    			i++;
			    		}				
			    	}
			    	$(".rota").keyup(function(){
				    	if($(this).val() !=""){
							nameInput = $(this).attr("name").split("_");
							nameInput = nameInput[1]+"_"+nameInput[2];
							$("#s_"+nameInput+"_seqIni").attr('disabled',false);
							$("#s_"+nameInput+"_seqFin").attr('disabled',false);
				    	}
				    	else{
				    		nameInput = $(this).attr("name").split("_");
							nameInput = nameInput[1]+"_"+nameInput[2];
							$("#s_"+nameInput+"_seqIni").attr('value','');
							$("#s_"+nameInput+"_seqFin").attr('value','');
							$("#s_"+nameInput+"_seqIni").attr('disabled',true);
							$("#s_"+nameInput+"_seqFin").attr('disabled',true);
					    }
			       	});

			    	$(".salvaDigito").keyup(function(){
			    		digiTemp = $("input[name='digitados']").val().split("::");
			    		dadoVai = "";			    		
			    		for(i = 0; i < digiTemp.length; i++){
				    		if(digiTemp[i] !=""){				    		
								dadoTemp = digiTemp[i].split("_");
								nome = dadoTemp[0]+"_"+dadoTemp[1]+"_"+dadoTemp[2]+"_"+dadoTemp[3];
								if($(this).attr("name") == nome){
									digiTemp.splice(i, 1)
								}
								else{
									dadoVai +="::"+dadoTemp[0]+"_"+dadoTemp[1]+"_"+dadoTemp[2]+"_"+dadoTemp[3]+"_"+dadoTemp[4];
								}
			    			}
				    	}
				    	if($(this).val() !=""){
			    			dadoVai += "::"+$(this).attr("name")+"_"+$(this).val();
			    			nomeReplica = $(this).attr("name").split("_");
			    			if(nomeReplica[3] == "qdrIni"){
			    				dadoVai +="::"+nomeReplica[0]+"_"+nomeReplica[1]+"_"+nomeReplica[2]+"_qdrFin_"+$(this).val();
				    		} 
				    		if(nomeReplica[3] == "rotIni"){
				    			dadoVai +="::"+nomeReplica[0]+"_"+nomeReplica[1]+"_"+nomeReplica[2]+"_rotFin_"+$(this).val();
					    	} 
					    	if(nomeReplica[3] == "seqIni"){
					    		dadoVai +="::"+nomeReplica[0]+"_"+nomeReplica[1]+"_"+nomeReplica[2]+"_seqFin_"+$(this).val();
						    }
					    	
			    			$("input[name='digitados']").attr('value', dadoVai);
				    	}
				    	else{
				    		$("input[name='digitados']").attr('value', dadoVai);
					    }
		
					});
			       	
			    	$("#cb_grid_"+index+"_t").hide();		        
			     }
			 },
			 //drag: true,
			 //resize: true,
			 width: 400, 
			 height: 220,
			 caption: "Localidades / Setores"
           });
         var myData = []; 
     	 var contData = 0;
	     if(allLoca !=null && allLoca !=""){
	 	    	locas = allLoca.split("::");
	 	    	for(i = 0; i < locas.length; i++){
	 	        	if(locas[i]!=""){
	 	    			tempLoca = locas[i].split(",");    		
	 					myData[contData] = {cod:tempLoca[0],desc: tempLoca[1]};
	 					contData++;
	 	        	}
	 	    	}
	     }
	     for(var i=0;i<myData.length;i++) {
             grid.jqGrid('addRowData', i, myData[i]);
         }
	     jQuery("#grid").jqGrid('navGrid','#pager',{add:false,edit:false,del:true,search:false},{},{},{
			    caption: "Delete",			    
			    msg: "Apagar localidade selecionada?",
			    bSubmit: "Apagar",
			    bCancel: "Cancelar",			    			    			    			    
			    url:'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros',			    
				    delData: {
				    	name: function() {				    		
				    		ind = jQuery("#grid").jqGrid('getGridParam','selrow');				    	
				    		jQuery("#gbox_grid").hide();
				    		var form = document.forms[0];
				    		form.action = 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&removeLocalidade=SIM&ind='+ind;				    		
				    		form.submit();	
	            	    }	            	    
			    	}		
	  			},{},{}
	  			
	  );
		  
	jQuery("#grid").trigger("reloadGrid");
         //Limpa a localidade do component quando se clica na 'borracha' 
         $("#limparLocalicade").click(function(){

        		//$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
        			limparLocalidade();
        			/*jQuery("#gbox_grid").hide();
        			var form = document.forms[0];
        			form.action = 'exibirInformarContasEmCobrancaAction.do';
        			form.submit();

        		});*/
        	});
     	
       	 //Limpa a localidade do component a medida que se vai digitando algo
         $("input[name='idLocalidade']").keyup(function(){
        		//$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
        			limparLocalidadeTecla();
        			/*jQuery("#gbox_grid").hide();		
        		});*/
        	});
     	
       	//Pesquisa a localidade quando se clica na 'lupa' 
        $("#pesquisaLocalidade").click(function(){
        		//$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
        			pesquisarLocalidade();
        		//});
        		
        	});
    	
    	 $("#exibeLocalidade").hide();
         $("#closeGrid").hide();
         $("#cb_grid").hide();
         $("#pager_center").hide();
         $("#refresh_grid").hide();
         $(".ui-pg-div span").attr('class','ui-icon ui-icon-circle-close');
         $("#alertcnt").html("<div>Selecione a Localidade que deseja excluir</div>");        
       	 $("#alertmod").css("top", "900px");
       	 $("input[name='digitados']").attr('value',$("input[name='digitados']").val().toLowerCase());
       	 $("#del_grid").attr('title','Apagar Localidade Selecionada');
       	 $("#del_grid").click(function(){
       		$(".jqmOverlay").hide();
         });	
 		var bloqueiaComp = false;
 		$("input[name='tipoOrdem']").each(function(index){ 						
			if($(this).attr("checked") == true && $(this).val() == 'INSPECAO'){	
				$("input[name='isInspecao']").attr("value","1");			
				bloqueiaComp = true;				
			}					
		 });
       	 if(bloqueiaComp){
  			var form = document.forms[0];
  			form.idLocalidade.value = "";
  			form.nomeLocalidade.value = "";
  			form.idLocalidade.disabled = true;  			
  			$("#addLocaComponent").hide();
         }
       	 else{
   			var form = document.forms[0];
   			if(form.localidadeInicial.value == '' || form.localidadeInicial.disabled == true){
   				form.idLocalidade.disabled = false;
   				$("#addLocaComponent").show();
   			}
       	 }
       	 
       	 $("input[name='tipoOrdem']").click(function(){       		
			if($(this).val() == "INSPECAO"){				
				var form = document.forms[0];
	    		form.action = 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&removeLocalidade=SIM&tudo=SIM';				    		
	    		form.submit();						
			}
			else{
				var form = document.forms[0];
				$("input[name='isInspecao']").attr("value","0");
				if(form.localidadeInicial.value == '' || form.localidadeInicial.disabled == true){
					form.idLocalidade.disabled = false;
	   				$("#addLocaComponent").show();
					
	   			}
	   			
			}			
       	 });
       	$("input[name='localidadeInicial']").keyup(function(){
			if($(this).val() ==""){
			 	$("input[name='idLocalidade']").attr('disabled', false);
			 	$("#addLocaComponent").show();
			}
			else{
				$("input[name='idLocalidade']").attr('disabled', true);
				$("#addLocaComponent").hide();
			}
         });
    	$("input[name='localidadeFinal']").keyup(function(){
			if($(this).val() ==""){
			 	$("input[name='idLocalidade']").attr('disabled', false);
			 	$("#addLocaComponent").show();
			}
			else{
				$("input[name='idLocalidade']").attr('disabled', true);
				$("#addLocaComponent").hide();
			}
         });

         if($("input[name='localidadeInicial']").val() != "" || $("input[name='localidadeFinal']").val() != ""){
        	 $("input[name='idLocalidade']").attr('disabled', true);
			 $("#addLocaComponent").hide();		
         }
         else{
        	 if(!bloqueiaComp){
        	 	$("input[name='idLocalidade']").attr('disabled', false);
			 	$("#addLocaComponent").show();
        	 }		
         }       
         
         habilitarDesabilitarDemaisCampos();
         habilitaDesabilitaAbaHidrometro();
     	 habilitaDesabilitaFirma();
     	 habilitaDesabilitaInscricao();
     	 controleSetorComercial();
     	 controleQuadra();
     	 
    });

</script>
<script>
	var bCancel = false;
	var exibeTabelaLocalidade = <%=session.getAttribute("exibeTabelaLocalidade") %> 	
    function validateImovelEmissaoOrdensSeletivasActionForm(form) {
    
    	if ( form.usuarioSemPermissaoGerarOS.value == '2' ) {
			form.sugestao[1].disabled = true;
		}
       
        if (bCancel) {
      		return true; 
        } 
        else {
			
			//if (validateInteger(form)) {

				if ( (form.sugestao[0].checked) && !form.tipoRelatorio[0].checked && !form.tipoRelatorio[1].checked) {
					alert('Informe tipo relatório.');
					return false;
				}
				
				if ( (form.sugestao[1].checked) && (form.firma.selectedIndex == 0) ) {
					alert('Informe a Firma.');
					form.firma.focus();
					return false;
				}
				
				if ( parseInt(form.localidadeFinal.value) < parseInt(form.localidadeInicial.value) ) {
					alert('A Localidade Final deve ser Maior ou Igual a Inicial.');
					form.localidadeFinal.focus();
					form.nomeLocalidadeFinal.value = '';
					return false;
				}
				else {
					
					if ( parseInt(form.codigoSetorComercialFinal.value) < parseInt(form.codigoSetorComercialInicial.value) ) {
						alert('O Código do Setor Comercial Final deve ser Maior ou Igual ao Inicial.');
						form.codigoSetorComercialFinal.focus();
						form.nomeSetorComercialFinal.value = '';
						return false;
					}
					else {
						
						if ( parseInt(form.quadraFinal.value) < parseInt(form.quadraInicial.value) ) {
							alert('A Quadra Final deve ser Maior ou Igual a Inicial.');
							form.quadraFinal.focus();
							return false;
						}
						else{
							
							if ( parseInt(form.rotaFinal.value) < parseInt(form.rotaInicial.value) ) {
								alert('A Rota Final deve ser Maior ou Igual a Inicial.');
								form.rotaFinal.focus();
								return false;
							}
							else{
								
								if ( parseInt(form.rotaSequenciaFinal.value) < parseInt(form.rotaSequenciaInicial.value) ) {
									alert('O Sequencial Final deve ser Maior ou Igual a Inicial.');
									form.form.rotaSequenciaFinal.focus();
									return false;
								}
							}
						}
					}
				}
				
				form.nomeFirma.value = form.firma.options[form.firma.selectedIndex].text;
				
				return true;
	       	/*}
	       	else {
	       		return false;
	       	}*/
		}
   	} 

    function IntegerValidations () { 
	     this.aa = new Array("quantidadeMaxima", "Quantidade Máxima deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("elo", "Elo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("logradouro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("localidadeInicial", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("localidadeFinal", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("codigoSetorComercialInicial", "Código do Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("codigoSetorComercialFinal", "Código do Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("quadraInicial", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ai = new Array("quadraFinal", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aj = new Array("rotaInicial", "Rota Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.al = new Array("rotaSequenciaInicial", "Sequencial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.am = new Array("rotaFinal", "Rota Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.an = new Array("rotaSequenciaFinal", "Sequencial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    
    function habilitaDesabilitaAbaHidrometro() {
		var form = document.forms[0];
		
		if (form.tipoOrdem[0].checked) {
			document.getElementById('2').style.display = 'none';
		}
		
		if (form.tipoOrdem[1].checked || form.tipoOrdem[2].checked || form.tipoOrdem[3].checked || form.tipoOrdem[4].checked) {
			document.getElementById('2').style.display = '';
		}
	}
    
	function habilitaDesabilitaAbaHidrometroTeste() {
		var form = document.forms[0];

		
		if (form.tipoOrdem.value == 304 || form.tipoOrdem.value == -1) {
			document.getElementById('2').style.display = 'none';
		}else{
			document.getElementById('2').style.display = '';
		}
	}
	
	function duplicarReferenciaCobranca(){
		var formulario = document.forms[0]; 
		formulario.referenciaCobrancaFinal.value = formulario.referenciaCobrancaInicial.value;
	}
	
	function habilitaDesabilitaFirma() {
		var form = document.forms[0];
		
		if ( form.usuarioSemPermissaoGerarOS.value == '2' ) {
			form.sugestao[1].disabled = true;
			form.sugestao[1].checked = false;
			form.sugestao[0].checked = true;			
		}
		

		if (form.sugestao[0].checked) { 
			form.firma.disabled = true;
			form.firma.selectedIndex = 0;
			form.tipoRelatorio[0].disabled = false;
			if(form.tipoRelatorio[0].checked==false &&form.tipoRelatorio[1].checked==false){
				form.tipoRelatorio[0].checked = true;
				form.tipoRelatorio.value = 1;
			}
			form.tipoRelatorio[1].disabled = false;
		}
		
		if (form.sugestao[1].checked) {
			form.firma.disabled = false;
			form.tipoRelatorio[0].disabled = true;
			form.tipoRelatorio[1].disabled = true;
			form.tipoRelatorio[0].checked = false;
			form.tipoRelatorio[1].checked = false;
		}
	}
	
	function habilitaDesabilitaInscricao() {
		
		var form = document.forms[0];		
		
		if (form.gerenciaRegional.value != "-1" || form.unidadeNegocio.value != "-1" || form.elo.value.length > 0 ||
			form.logradouro.value.length > 0 || (form.idLocalidade.value.length > 0 || exibeTabelaLocalidade == true)) {

			if(form.idLocalidade.value.length > 0 || exibeTabelaLocalidade == true){

				form.idImovel.value = "";
				form.idImovel.disabled = true;
				
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
				
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
					
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
				
			else if (form.gerenciaRegional.value != "-1"){

				form.idLocalidade.value = "";
				form.idLocalidade.disabled = true;
				document.getElementById("addLocaComponent").style.display="none";
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
					
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.unidadeNegocio.value != "-1"){

				form.idLocalidade.value = "";
				form.idLocalidade.disabled = true;
				document.getElementById("addLocaComponent").style.display="none";
				
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.elo.value.length > 0){

				form.idLocalidade.value = "";
				form.idLocalidade.disabled = true;
				document.getElementById("addLocaComponent").style.display="none";
				
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.logradouro.value.length > 0){

				form.idLocalidade.value = "";
				form.idLocalidade.disabled = true;
				document.getElementById("addLocaComponent").style.display="none";
					
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
				
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
			}
			
			form.localidadeInicial.value = "";
			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			
			form.setorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
			form.codigoSetorComercialInicial.value = "";
			form.codigoSetorComercialFinal.value = "";
			form.nomeSetorComercialInicial.value = "";
			form.nomeSetorComercialFinal.value = "";
			
			form.quadraInicial.value = "";
			form.quadraFinal.value = "";
			form.idQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			
			form.rotaInicial.value = "";
			form.rotaSequenciaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaSequenciaFinal.value = "";
			
			form.localidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			
		}
		else {
			
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;
			form.elo.disabled = false;
			form.logradouro.disabled = false;
			if(form.localidadeInicial.value == "" && form.localidadeFinal.value == "" && form.isInspecao.value == "0"){
				form.idLocalidade.disabled = false;
				document.getElementById("addLocaComponent").style.display="inline";
			}
			form.idImovel.disabled = false;
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
			
			controleSetorComercial();
			
			controleQuadra();
		}
	}
	
	function controleSetorComercial() {
		
		var form = document.forms[0];
			
		if (form.localidadeInicial.value.length > 0) {
		
			form.codigoSetorComercialInicial.disabled = false;
			
			if(form.localidadeFinal.value.length > 0){
			
				form.codigoSetorComercialFinal.disabled = false;
				
				if(form.localidadeInicial.value != form.localidadeFinal.value){
				
					form.codigoSetorComercialInicial.disabled = true;
					form.codigoSetorComercialFinal.disabled = true;
						
					form.codigoSetorComercialInicial.value = "";
					form.codigoSetorComercialFinal.value = "";
						
					form.setorComercialInicial.value = "";
					form.setorComercialFinal.value = "";
						
					form.nomeSetorComercialInicial.value = "";
					form.nomeSetorComercialFinal.value = "";
						
					document.getElementById("btPesqSetorComercialInicial").disabled = true;
					document.getElementById("btPesqSetorComercialFinal").disabled = true;
				
					controleQuadra();
		   		}
		   		else{
		   			
		   			form.codigoSetorComercialInicial.disabled = false;
					form.codigoSetorComercialFinal.disabled = false;
					
					document.getElementById("btPesqSetorComercialInicial").disabled = false;
					document.getElementById("btPesqSetorComercialFinal").disabled = false;
				
					controleQuadra();
		   		}
			}
			
		}
		else {
			
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
				
			form.codigoSetorComercialInicial.value = "";
			form.codigoSetorComercialFinal.value = "";
				
			form.setorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
				
			form.nomeSetorComercialInicial.value = "";
			form.nomeSetorComercialFinal.value = "";
				
			document.getElementById("btPesqSetorComercialInicial").disabled = true;
			document.getElementById("btPesqSetorComercialFinal").disabled = true;
				
			controleQuadra();
		}
	}
	
	function controleQuadra() {
		var form = document.forms[0];
		
		if (form.codigoSetorComercialInicial.value.length > 0) {
		
			if(form.codigoSetorComercialFinal.value.length > 0){
			   
			   	if (form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value){
			   		
			   		form.quadraInicial.value = "";
					form.quadraFinal.value = "";
					form.idQuadraInicial.value = "";
					form.idQuadraFinal.value = "";
					form.rotaInicial.value = "";
					form.rotaSequenciaInicial.value = "";
					form.rotaFinal.value = "";
					form.rotaSequenciaFinal.value = "";
				
					form.quadraInicial.disabled = true;
					form.quadraFinal.disabled = true;
					form.rotaInicial.disabled = true;
					form.rotaSequenciaInicial.disabled = true;
					form.rotaFinal.disabled = true;
					form.rotaSequenciaFinal.disabled = true;
			   	}
			   	else{
			   		
			   		form.quadraInicial.disabled = false;
					form.quadraFinal.disabled = false;
					form.rotaInicial.disabled = false;
					form.rotaSequenciaInicial.disabled = false;
					form.rotaFinal.disabled = false;
					form.rotaSequenciaFinal.disabled = false;
			   	}
			}
		}
		else {
			
			form.quadraInicial.value = "";
			form.quadraFinal.value = "";
			form.idQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			form.rotaInicial.value = "";
			form.rotaSequenciaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaSequenciaFinal.value = "";
			
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			
		}
	}
	
	

function limparBorrachaOrigem(tipo){
	
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidara pra baixo
		    
		    if(!form.localidadeInicial.disabled){
				
				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				if(form.localidadeInicial.value == "" && form.localidadeFinal.value == "" && form.isInspecao.value == "0"){
					form.idLocalidade.disabled = false;
					document.getElementById("addLocaComponent").style.display="inline";
				}
				controleSetorComercial();
			}
			
			break;
			
		case 2: //De setor para baixo
		    
		    if(!form.codigoSetorComercialInicial.disabled){
				
				form.codigoSetorComercialInicial.value = "";
				form.setorComercialInicial.value = "";
				form.nomeSetorComercialInicial.value = "";
				
				form.codigoSetorComercialFinal.value = "";
				form.setorComercialFinal.value = "";		   
				form.nomeSetorComercialFinal.value = "";
				
				controleQuadra();
		    }
		    
		    break;
	}
}

/*
Limpa os campos da faixa final pelo click na borracha(conforme parametro passado)
*/
function limparBorrachaDestino(tipo){
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
			
			if(!form.localidadeFinal.disabled){
				
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
   		   		form.codigoSetorComercialFinal.value = "";
   		   		
   		   		form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";
				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";
				
				form.codigoSetorComercialFinal.disabled = true;
				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;
				if(form.localidadeInicial.value == "" && form.localidadeFinal.value == "" && form.isInspecao.value == "0"){
					form.idLocalidade.disabled = false;
					document.getElementById("addLocaComponent").style.display="inline";
				}
				form.localidadeFinal.focus();
			}
			
			break;
			
		case 2: //De setor para baixo		 
		
			if(!form.codigoSetorComercialInicial.disabled){
				
				form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
   		   		form.codigoSetorComercialFinal.value = "";
   		   		
   		   		form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";
				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";
				
				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;
				
				form.codigoSetorComercialFinal.focus();
			}
			
   		   	break;
   		   	
   		default:
   			break
     }
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;
	
	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeInicial.value = codigoRegistro;
	  form.nomeLocalidadeInicial.value = descricaoRegistro;
	  form.nomeLocalidadeInicial.style.color = "#000000";
	  
	  form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.codigoSetorComercialInicial.focus();
      
      controleSetorComercial();
	}
	
	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
   	  form.nomeLocalidadeFinal.style.color = "#000000";
   	  form.codigoSetorComercialFinal.focus();
   	  
   	  controleSetorComercial();
	}

	if(tipoConsulta == 'localidade'){
		form.idLocalidade.value = codigoRegistro;
		form.nomeLocalidade.value = descricaoRegistro;
		form.nomeLocalidade.style.color = "#000000";
		habilitaDesabilitaInscricao();
	}
	
	if (tipoConsulta == 'elo') {
		form.elo.value = codigoRegistro;
		form.nomeElo.value = descricaoRegistro;
		form.nomeElo.style.color = "#000000";
		form.elo.focus();
		
		habilitaDesabilitaInscricao();
	}
	
	if (tipoConsulta == 'logradouro') {
      	form.logradouro.value = codigoRegistro;
      	form.descricaoLogradouro.value = descricaoRegistro;
      	form.descricaoLogradouro.style.color = "#000000";
      	form.logradouro.focus();
      	
      	habilitaDesabilitaInscricao();
    }
    
    if (tipoConsulta == 'imovel'){
    	form.idImovel.value = codigoRegistro;
    	form.inscricaoImovel.value = descricaoRegistro;
    	form.inscricaoImovel.style.color = "#000000";
    	
    	habilitarDesabilitarDemaisCampos();
    }
	
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.codigoSetorComercialInicial.value = codigoRegistro;
      form.setorComercialInicial.value = idRegistro;
	  form.nomeSetorComercialInicial.value = descricaoRegistro;
	  form.nomeSetorComercialInicial.style.color = "#000000";
	  
	  form.codigoSetorComercialFinal.value = codigoRegistro;
      form.setorComercialFinal.value = idRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000";
	  
	  controleQuadra();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.codigoSetorComercialFinal.value = codigoRegistro;
      form.setorComercialFinal.value = idRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000"; 
	  
	  controleQuadra();
	}
}

	//Caso tenha informado o imovel, bloquear demais campos
	function habilitarDesabilitarDemaisCampos(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;
		if (form.idImovel.value == ''){
			form.quantidadeMaxima.disabled = false;
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;
			form.elo.disabled = false;
			form.logradouro.disabled = false;
			//form.idLocalidade.disabled = false;
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
		}		
		else{
			//bloqueia campos
			//form.firma.disabled = true;
			form.quantidadeMaxima.disabled = true;
			form.gerenciaRegional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.elo.disabled = true;
			form.nomeElo.disabled = true;
			form.logradouro.disabled = true;
			form.descricaoLogradouro.disabled = true;
			form.idLocalidade.disabled = true;
			
			form.localidadeInicial.disabled = true;
			form.nomeLocalidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.nomeLocalidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.nomeSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.nomeSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			//limpa campos
			//form.firma.value = "-1";
			form.unidadeNegocio.value = "-1";
			form.gerenciaRegional.value = "-1";
			form.quantidadeMaxima.value = '';
			form.elo.value = '';
			form.logradouro.value = '';
			form.descricaoLogradouro.value = '';
			form.idLocalidade.value = '';
			form.localidadeInicial.value = '';
			form.nomeLocalidadeInicial.value = '';
			form.localidadeFinal.value = '';
			form.nomeLocalidadeFinal.value = '';
			form.codigoSetorComercialInicial.value = '';
			form.nomeSetorComercialInicial.value = '';
			form.codigoSetorComercialFinal.value = '';
			form.nomeSetorComercialFinal.value = '';
			form.quadraInicial.value = '';
			form.quadraFinal.value = '';
			form.rotaInicial.value = '';
			form.rotaFinal.value = '';
			form.rotaSequenciaInicial.value = '';
			form.rotaSequenciaFinal.value = '';
		}
		if(exibeTabelaLocalidade == true){
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			
			form.gerenciaRegional.value = "-1"
			form.gerenciaRegional.disabled = true;
				
			form.unidadeNegocio.value = "-1"
			form.unidadeNegocio.disabled = true;
				
			form.elo.value = "";
			form.nomeElo.value = "";
			form.elo.disabled = true;
			
			form.logradouro.value = "";
			form.descricaoLogradouro.value = "";
			form.logradouro.disabled = true;
		}
	}
	
	function limparImovel(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;
		form.inscricaoImovel.value ='';
		form.idImovel.value = '';
		form.inscricaoImovel.style.color = "#000000";
	}
	function limparLocalidade() {
		var form = document.forms[0];
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";	
		habilitaDesabilitaInscricao();				
		
	}	
	function limparLocalidadeTecla() {
		var form = document.forms[0];
		form.nomeLocalidade.value = "";
		habilitaDesabilitaInscricao();			
			
	}	
	function pesquisarLocalidade() {
		var form = document.forms[0];

		if (form.idLocalidade.disabled == false)  {			
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function adicionarLocalidade(){
		var form = document.forms[0];
		if(form.idLocalidade.value !=null && form.idLocalidade.value !=''){					
			form.action = 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&pesquisaLocalidade=SIM';
			form.submit();			
		}
		else{
			alert('Informe a Localidade');
		}
	}
	function pesquisarImovel(){
		var form = document.forms[0];

		if (form.idImovel.disabled == false)  {
			abrirPopup('exibirPesquisarImovelAction.do', 490, 800)
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function pesquisarElo(){
		var form = document.forms[0];

		if (form.elo.disabled == false)  {
			chamarPopup('exibirPesquisarEloAction.do', 'origem', null, null, 275, 480, '');
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function pesquisarLogradouro(){
		var form = document.forms[0];

		if (form.logradouro.disabled == false)  {		
			abrirPopup('exibirPesquisarLogradouroAction.do?indicadorUsoTodos=1&primeriaVez=1', 400, 800);
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function pesquisarLocalidadeInicial(){
		var form = document.forms[0];

		if (form.localidadeInicial.disabled == false)  {		
			chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function pesquisarSetorInicial(){
		var form = document.forms[0];

		if (form.codigoSetorComercialInicial.disabled == false)  {	
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.ImovelEmissaoOrdensSeletivasActionForm.localidadeInicial.value, 275, 480, 'Informe Localidade Inicial.');
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	
	function pesquisarLocalidadeFinal(){
		var form = document.forms[0];

		if (form.localidadeFinal.disabled == false)  {		
			chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '');
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function pesquisarSetorFinal(){
		var form = document.forms[0];

		if (form.codigoSetorComercialFinal.disabled == false)  {	
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.ImovelEmissaoOrdensSeletivasActionForm.localidadeFinal.value, 275, 480, 'Informe Localidade Final.');
		}
		else{
			alert('pesquisa bloqueada');
		}
	}

	function desabilitaCamposCasoInscricaoEstejaPreenchida(){
		var form = document.forms[0];
		if(form.localidadeInicial.value.length>0
		    || form.localidadeFinal.value.length>0){
		    
			form.gerenciaRegional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.elo.disabled = true;
			form.logradouro.disabled = true;
		}else{
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;
			form.elo.disabled = false;
			form.logradouro.disabled = false;
		}
	}
	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:desabilitaCamposCasoInscricaoEstejaPreenchida();//setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarImovelEmissaoOrdensSeletivasWizardAction"
	type="gcom.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm"
	onsubmit="return validateImovelEmissaoOrdensSeletivasActionForm(this);"
	name="ImovelEmissaoOrdensSeletivasActionForm" method="post">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_tela_espera.jsp?numeroPagina=1"/>	

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="numeroPagina" value="1" />
	<input type="hidden" name="isInspecao" value="0" />
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
	
		<html:hidden property="nomeFirma"/>
		<html:hidden property="usuarioSemPermissaoGerarOS"/>
		<html:hidden property="setoresSelecionadosComponent"/>
		<html:hidden property="indiceSetoresSelecionadosComponent"/>
		<html:hidden property="digitados" />
		
		
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

			<td width="625" valign="top" class="centercoltext">
				
				<!--
				******************************************************************
				** Início do Formulário ******************************************
				******************************************************************
				-->
				
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Filtrar Ordens de Serviço Seletivas</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) im&oacute;vel(is), informe os dados abaixo:</td>
				</tr>
					
				 <tr>
					<td width="22%"><strong>Tipo da Ordem:</strong></td>
					<td align="left">
						<table width="100%" border="0">
						
								<tr>
								<logic:present name="tipoOrdem">
									<td>
									    <html:radio value="INSTALACAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Instala&ccedil;&atilde;o Hidr&ocirc;metro
									    <html:radio value="SUBSTITUICAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Substitui&ccedil;&atilde;o Hidr&ocirc;metro
									    <html:radio value="REMOCAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>Remoção Hidr&ocirc;metro
									</td>
								</logic:present>
								<logic:notPresent name="tipoOrdem">
									<td>
									    <input type="radio" name="tipoOrdem" value="INSTALACAO" checked="checked" onclick="habilitaDesabilitaAbaHidrometro();">Instala&ccedil;&atilde;o Hidr&ocirc;metro
									    <input type="radio" name="tipoOrdem" value="SUBSTITUICAO" onclick="habilitaDesabilitaAbaHidrometro();">Substitui&ccedil;&atilde;o Hidr&ocirc;metro
									    <input type="radio" name="tipoOrdem" value="REMOCAO" onclick="habilitaDesabilitaAbaHidrometro();">Remoção Hidr&ocirc;metro
									</td>
								</logic:notPresent>
								</tr>
								<tr>
								<logic:present name="tipoOrdem">
										<td>
									    <html:radio value="INSPECAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>Inspeção de Anormalidade
									    <html:radio value="SUBSTITUICAOREM" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Substituição com Remoção
									</td>
								</logic:present>
								
								<logic:notPresent name="tipoOrdem">
									<td>
									    <input type="radio" name="tipoOrdem" value="INSPECAO" onclick="habilitaDesabilitaAbaHidrometro();">Inspeção de Anormalidade
									    <input type="radio" name="tipoOrdem" value="SUBSTITUICAOREM" onclick="habilitaDesabilitaAbaHidrometro();">Substituição com Remoção
									</td>
								</logic:notPresent>
								</tr>
								<tr>
								<logic:present name="tipoOrdem">
										<td>
									    <html:radio value="INSTALACAOPROTECAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Instalação de Caixa de Proteção
									</td>
								</logic:present>
								
								<logic:notPresent name="tipoOrdem">
									<td>
									    <input type="radio" name="tipoOrdem" value="INSTALACAOPROTECAO" onclick="habilitaDesabilitaAbaHidrometro();">Instalação de Caixa de Proteção
									</td>
								</logic:notPresent>
							</tr>
						</table>
					</td>
				</tr>
				
				<!-- 	
				<tr>
					<td width="22%"><strong>Tipo da Ordem:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="tipoOrdem" style="width: 240px;" onclick="habilitaDesabilitaAbaHidrometro();">
							<html:option value="-1">&nbsp;</html:option>
						</html:select>
						
					</td>
				</tr>
				-->	
					
					
				<tr>
					<td><strong>Tipo de Emissão:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									
									<logic:present name="sugestao">
									
										<html:radio value="1" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Relatório
									
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
										
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();" disabled="true"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
									</logic:present>
								
									<logic:notPresent name="sugestao">
										
										<input type="radio" name="sugestao" value="1" onclick="habilitaDesabilitaFirma();" checked="checked">&nbsp;Relatório
									
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();" disabled="true"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
									</logic:notPresent>
								
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr id="tipoRelatorio">
					<td>
						&nbsp;
					</td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>			
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="1" property="tipoRelatorio"></html:radio>&nbsp;Sintético
											<html:radio value="2" property="tipoRelatorio"></html:radio>&nbsp;Analítico
										</logic:equal>
										
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="1" property="tipoRelatorio"  disabled="true"></html:radio>&nbsp;Sintético
											<html:radio value="2" property="tipoRelatorio"  disabled="true"></html:radio>&nbsp;Analítico
										</logic:equal>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
					
				<tr>
					<td ><strong>Descrição Comando:</strong></td>
					<td align="left"><html:text property="descricaoComando" size="50" maxlength="50" ></html:text></td>
				</tr>	
					
				<tr>
					<td><strong>Firma:</strong></td>
					<td>
						<logic:present name="sugestao">
						
							<logic:equal name="sugestao" value="1">
								<html:select property="firma" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
								</html:select>
							</logic:equal>
							
							<logic:equal name="sugestao" value="2">
								<html:select property="firma">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
								</html:select>
							</logic:equal>
						
						</logic:present>
									
						<logic:notPresent name="sugestao">
						
							<html:select property="firma">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
							</html:select>
						
						</logic:notPresent>
						
					</td>
				</tr>

				<tr>
					<td ><strong>Quantidade Máxima:</strong></td>
					<td align="left"><html:text property="quantidadeMaxima" size="5" maxlength="4" 
					onkeypress="return isCampoNumerico(event);"
					onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].quantidadeMaxima, 'Quantidade Maxima');"></html:text></td>
				</tr>
				
			<tr>
		      	<td><strong>Matr&iacute;cula do  Im&oacute;vel:</strong></td>
		        <td align = "left">
					<html:text property="idImovel" size="10" maxlength="9" tabindex="1" 
						onkeypress="validaEnterComMensagem(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&pesquisarImovel=OK', 'idImovel', 'Matrícula do Imóvel');
						return isCampoNumerico(event);"
						onkeyup="habilitarDesabilitarDemaisCampos();"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idImovel, 'Matrícula do Imóvel');"
						/>
					<a href="javascript:pesquisarImovel();">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" title="Pesquisar" border="0"></a>
		
					<logic:present name="corImovel">
		
						<logic:equal name="corImovel" value="exception">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="corImovel" value="exception">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
		
					</logic:present>
		
					<logic:notPresent name="corImovel">
		
						<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="idImovel">
							<html:text property="inscricaoImovel" value="" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
						
		
					</logic:notPresent>
		        	
		        	<a href="javascript:limparImovel();habilitarDesabilitarDemaisCampos();">
		        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" title="Apagar" border="0"></a>
		
		       	</td>
	  		</tr>
				
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td>
						
						<html:select property="gerenciaRegional" onchange="habilitaDesabilitaInscricao();" 
						style="width: 240px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
						</html:select>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td>
						
						<html:select property="unidadeNegocio" onchange="habilitaDesabilitaInscricao();"
						style="width: 240px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" labelProperty="nome" />
						</html:select>
						
					</td>
				</tr>
					
				<tr>
					<td><strong>Localidade Pólo:</strong></td>
					<td>
						<html:text tabindex="7" maxlength="3" property="elo" size="5"
						onkeypress="validaEnterComMensagem(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=4&inscricaoTipo=origem', 'elo', 'Localidade Pólo');
						return isCampoNumerico(event);"
						onkeyup="habilitaDesabilitaInscricao();"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].elo, 'Localidade Pólo');"
						/>
						
						<a href="javascript:pesquisarElo();" id="btPesqElo">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar" /></a>
						
						<logic:present name="corEloOrigem">
							<logic:equal name="corEloOrigem" value="exception">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
		
							<logic:notEqual name="corEloOrigem" value="exception">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
							
						<logic:notPresent name="corEloOrigem">
							<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="nomeElo" value="" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
							
						<a href="javascript:limparBorrachaElo(); habilitaDesabilitaInscricao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Logradouro:</strong></td>
					<td><html:text property="logradouro" maxlength="9" size="9" 
						onkeypress="javascript:validaEnterComMensagem(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=5&inscricaoTipo=origem', 'logradouro', 'Logradouro');
						return isCampoNumerico(event);"
						onkeyup="habilitaDesabilitaInscricao();"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].logradouro, 'Logradouro');"
						/>
					
						<a href="javascript:pesquisarLogradouro();"><img
						src="/gsan/imagens/pesquisa.gif" width="23"
						height="21" border="0" style="cursor:hand;" alt="Pesquisar" title="Pesquisar"></a>  
						
						<logic:present name="corLogradouro">
							<logic:equal name="corLogradouro" value="exception">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
		
							<logic:notEqual name="corLogradouro" value="exception">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
							
						<logic:notPresent name="corLogradouro">
							<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="descricaoLogradouro" value="" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						
					 	<a href="javascript:limparBorrachaLogradouro(); habilitaDesabilitaInscricao();">
						<img src="/gsan/imagens/limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				</table>
				
				<%--<div id="exibeLocalidade">  --%>	
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
					
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td><strong>Inscrição Inicial</strong></td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
								
									<table width="100%" border="0">
								
									<tr>
										<td width="21%"><strong>Localidade:</strong></td>
										<td>
											<html:text tabindex="8" maxlength="3" property="localidadeInicial" size="5"
											onkeypress="return isCampoNumerico(event);"
											onkeyup="duplicarLocalidade(); controleSetorComercial(); validaEnter(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=1&inscricaoTipo=origem', 'localidadeInicial');return isCampoNumerico(event);"
											onclick="javascript:validarLocalidade();" onblur="validaNumero(this);duplicarLocalidade(); desabilitaCamposCasoInscricaoEstejaPreenchida();"/>
											
											<a href="javascript:pesquisarLocalidadeInicial();controleSetorComercial();" id="btPesqLocalidadeInicial">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar" /></a>
											
											<logic:present name="corLocalidadeOrigem">
												<logic:equal name="corLocalidadeOrigem" value="exception">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeOrigem" value="exception">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corLocalidadeOrigem">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
													property="localidadeInicial">
													<html:text property="nomeLocalidadeInicial" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
				
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
													property="localidadeInicial">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaOrigem(1);">
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
										</td>
									</tr>
									<tr>
										<td><strong>Setor Comercial:</strong></td>
										<td>
											<html:text tabindex="9" maxlength="3"
											property="codigoSetorComercialInicial" size="5"
											onkeypress="return isCampoNumerico(event);"
											onkeyup="duplicarSetorComercial(); controleQuadra(); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=2&inscricaoTipo=origem', document.forms[0].codigoSetorComercialInicial, document.forms[0].localidadeInicial.value, 'Localidade Inicial.');return isCampoNumerico(event);"
											onblur="validaNumero(this);duplicarSetorComercial(); controleQuadra();"/>
											
											<a href="javascript:pesquisarSetorInicial(); controleQuadra();" id="btPesqSetorComercialInicial">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
											
											<logic:present name="corSetorComercialOrigem">
												<logic:equal name="corSetorComercialOrigem" value="exception">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
					
												<logic:notEqual name="corSetorComercialOrigem" value="exception">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
					
											</logic:present>
											
											<logic:notPresent name="corSetorComercialOrigem">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialInicial">
													<html:text property="nomeSetorComercialInicial" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialInicial">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent>
											<a href="javascript:limparBorrachaOrigem(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
											</a>
											<html:hidden property="setorComercialInicial"/>
										</td>
									</tr>
									<tr>
										<td><strong>Quadra:</strong></td>
										<td colspan="3">
										
											<html:text tabindex="10" maxlength="4" property="quadraInicial" size="5"
											onkeypress="return isCampoNumerico(event);"
											onkeyup="validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=3&inscricaoTipo=origem', document.forms[0].quadraInicial, document.forms[0].codigoSetorComercialInicial.value, 'Setor Comercial Inicial.');return isCampoNumerico(event);"
											onblur="validaNumero(this);duplicarQuadra();"/> 
											
											<logic:present name="corQuadraOrigem" scope="request">
												<span style="color:#ff0000" id="msgQuadraInicial">
													<bean:write scope="request" name="msgQuadraInicial" />
												</span>
											</logic:present> 
											
											<logic:notPresent name="corQuadraOrigem" scope="request"></logic:notPresent> 
											
											<html:hidden property="idQuadraInicial" />
										
										</td>
									</tr>
									
									<tr>
										<td><strong>Rota inicial:</strong></td>
										<td align="left"><html:text property="rotaInicial" size="5" maxlength="4" disabled="true" 
											onkeypress="return isCampoNumerico(event);"
											onkeyup="return isCampoNumerico(event);"
											onblur="validaNumero(this);"></html:text>
										seq.:
										<html:text property="rotaSequenciaInicial" size="7" maxlength="6" disabled="true" 
											onkeypress="return isCampoNumerico(event);"onblur="validaNumero(this);"></html:text></td>
									</tr>
									</table>
									
								</td>
							</tr>
							</table>
						
						</td>
					</tr>
				</table>		
				
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
					
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td><strong>Inscrição Final</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
								
									<table width="100%" border="0">
									<tr>
										<td width="21%"><strong>Localidade:</strong></td>
										<td>
											<html:text maxlength="3" property="localidadeFinal" size="5"
											onkeypress="return isCampoNumerico(event);"
											onblur="validaNumero(this);"
											onkeyup="controleSetorComercial(); limparDestino(1); validaEnter(event,'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=1&inscricaoTipo=destino', 'localidadeFinal');return isCampoNumerico(event);"
											tabindex="11"/>
											<a href="javascript:pesquisarLocalidadeFinal(); limparDestino(1);" id="btPesqLocalidadeFinal">
												<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
											<logic:present name="corLocalidadeDestino">
												<logic:equal name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corLocalidadeDestino">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="localidadeFinal">
													<html:text property="nomeLocalidadeFinal" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="localidadeFinal">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaDestino(1);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
											</a>
										</td>
									</tr>
									<tr>
										<td><strong>Setor Comercial :</strong></td>
										<td>
											<html:text maxlength="3" property="codigoSetorComercialFinal" size="5"
												onkeypress="limparDestino(2);return isCampoNumerico(event);"
												onblur="validaNumero(this);"
												onkeyup="controleQuadra(); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=2&inscricaoTipo=destino', document.forms[0].codigoSetorComercialFinal, document.forms[0].localidadeFinal.value, 'Localidade Final.');return isCampoNumerico(event);"
												tabindex="12" />
											<a href="javascript:pesquisarSetorFinal(); limparDestino(2);" id="btPesqSetorComercialFinal">
												<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													title="Pesquisar" /></a>
											
											<logic:present name="corSetorComercialDestino">
												<logic:equal name="corSetorComercialDestino" value="exception">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
				
												<logic:notEqual name="corSetorComercialDestino" value="exception">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corSetorComercialDestino">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialFinal">
													<html:text property="nomeSetorComercialFinal" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialFinal">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaDestino(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" title="Apagar" /></a>
													
											<html:hidden property="setorComercialFinal" />
										</td>
									</tr>
									<tr>
										<td><strong>Quadra:</strong></td>
										<td><html:text maxlength="4" property="quadraFinal" size="5"
											onkeypress="return isCampoNumerico(event);"
											onblur="validaNumero(this);"
											onkeyup="limparDestino(3); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=3&inscricaoTipo=destino', document.forms[0].quadraFinal, document.forms[0].codigoSetorComercialFinal.value, 'Setor Comercial Final.');return isCampoNumerico(event);"
											tabindex="13"/> 
											
											<logic:present name="corQuadraDestino" scope="request">
												<span style="color:#ff0000" id="msgQuadraFinal">
													<bean:write scope="request" name="msgQuadraFinal" />
												</span>
											</logic:present> 
											
											<logic:notPresent name="corQuadraDestino" scope="request"></logic:notPresent> 
											
											<html:hidden property="idQuadraFinal" />
										</td>
									</tr>
									
									<tr>
										<td><strong>Rota Final:</strong></td>
										<td align="left"><html:text property="rotaFinal" size="5" maxlength="4" disabled="true" 
											onkeypress="return isCampoNumerico(event);"
											onkeyup="return isCampoNumerico(event);"
											onblur="validaNumero(this);"></html:text>
										seq.:
										<html:text property="rotaSequenciaFinal" size="7" maxlength="6" disabled="true"
											onkeypress="return isCampoNumerico(event);" 
											onkeyup="return isCampoNumerico(event);"
											onblur="validaNumero(this);"></html:text></td>
									</tr>
									</table>
								
								</td>
							</tr>
							</table>
						</td>
					</tr>
				</table>
				<%--</div> --%>
				<%--<div id="exibeComponent">--%>
				<table width="100%" border="0">
				<tr>				
					<%--
						onkeyup="javascript:replicarCampo(form.idLocalidadeDestino, form.idLocalidadeOrigem);limparLocalidadeOrigemTecla();bloqueiaDados();"
					 	onchange="javascript:limparTotalizacao();"
					 	onkeyup="javascript:limparLocalidadeTecla();"
					 --%>
					<td><strong>Localidade :</strong></td>
					<td><html:text maxlength="3" property="idLocalidade" size="3"
						onkeypress="validaEnterComMensagem(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=6&inscricaoTipo=origem', 'idLocalidade' ,'Localidade');
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idLocalidade, 'Localidade');"
						onkeyup="habilitaDesabilitaInscricao();"
						tabindex="4"
						  
						 />
					<a style="cursor: pointer;" id="pesquisaLocalidade"> <img width="20"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeInexistente" scope="request">
						<html:text property="nomeLocalidade" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeInexistente" scope="request">
						<html:text property="nomeLocalidade" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a style="cursor: pointer;" id="limparLocalicade">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a>
																
						<input type="button" 
       						name="ButtonReset"
       						id="addLocaComponent" 
       						class="bottonRightCol"
							value="Adicionar"
							onClick="adicionarLocalidade();">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<logic:present name="exibeTabelaLocalidade" scope="session">
						<table id="grid"></table>
						<div id="pager"></div>
					</logic:present>
					</td>
				</tr>
				</table>	
				<%--</div>--%>
				<table width="100%" border="0">	
					<tr>
						<td colspan="4">
							<div align="right">
								<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar_tela_espera.jsp?numeroPagina=1"/>
							</div>
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
<logic:present name="tipoOrdem">
	<logic:equal name="tipoOrdem" value="INSTALACAO">
		<script>document.getElementById('2').style.display = 'none';</script>
	</logic:equal>
	<logic:notEqual name="tipoOrdem" value="INSTALACAO">
		<script>document.getElementById('2').style.display = '';</script>
	</logic:notEqual>
</logic:present>
<logic:notPresent name="tipoOrdem">
	<script>document.getElementById('2').style.display = 'none';</script>
</logic:notPresent>
</body>
</html:html>