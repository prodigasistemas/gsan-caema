$(document).ready(function(){
	
	//Tipi Inteiro
	$('.tipoInteiro').bind("keypress drop blur",function(event){
		mascara(this,mascaraInteiro);
	});
	
	//Tipo AlfaNum�rico
	$('.tipoAlfaNumerico').bind("keypress drop blur",function(event){
		mascara(this,mascaraAlfaNumerico);
	});
	
	//Tipo AlfaNum�rico com espa�o
	$('.tipoAlfaNumericoComEspaco').bind("keypress drop blur",function(event){
		mascara(this,mascaraAlfaNumericoComEspaco);
	});
	
	//Per�odo Inicial
	$('.tipoPeriodoInicial').bind("keypress drop blur",function(event){
		mascara(this, mascaraAnoMesReferencia);
	})
	.bind("keyup",function(event){
		$(this).val($(this).val().slice(0,7));
		$('[@name='+ $(this).attr('name').replace('Inicial','Final') + ']').val($(this).val());	
	});
	
	
	//Per�odo Final
	$('.tipoPeriodoFinal').bind("keypress drop blur",function(event){
		mascara(this, mascaraAnoMesReferencia);
	})
	.bind("keyup",function(event){
		$(this).val($(this).val().slice(0,7));
	});
	
	//Tipo Monet�rio
	$('.tipoMonetario').bind("keyup dropend keypress blur",function(event){
		mascara(this,mascaraMoeda);
	});
	
	//Tipo Monet�rio Inicial
	$('.tipoMonetarioInicial').bind("dropend keypress blur",function(event){
		mascara(this,mascaraMoeda);
	})
	.bind("keyup",function(event){
		$('[@name='+ $(this).attr('name').replace('Inicial','Final') + ']').val($(this).val());	
	});
	
	//Tipo Popula��o
	$('.tipoPopulacao').bind("keyup dropend keypress blur",function(event){
		mascara(this,mascaraInteiroComMilhar);
	});

	
	//Tipo Processo Judicial
	$('.tipoProcessoJudicial').bind("keypress drop blur",function(event){
		$(this).val($(this).val().slice(0,$(this).attr('maxlength')));
		mascara(this,mascaraProcessoJudicial);
	})
	
	$('.tipoTextArea').bind("keypress keyup drop blur",function(){
		//alert($('#utilizado').html() + " - " + $('#utilizado').get().value + " - " + $('#limite').get().value)
		mascara(this,mascaraTextAreaSemEnter);
		limitTextArea(this, $('#limiteHidden').val(), $('#utilizado').get(0), $('#limite').get(0));
	});

	
	//Tipo data
	$('.tipoData').bind("keypress drop blur",function(event){
		return isCampoNumerico(event);
	})
	.bind("keyup",function(event){
		mascaraData(this, event);	
	});
	
	//Tipo Porcentagem Juros
	$('.tipoPorcentagemJuros').bind("keypress drop",function(event){
		mascara(this,mascaraMoeda);
		
	}).bind("blur",function(event){
		return validarPorcentagemComMsg(this,'Percentual dos Juros inv�lido.');
	});
	
	//Tipo Porcentagem
	$('.tipoPorcentagem').bind("keypress drop",function(event){
		mascara(this,mascaraMoeda);
		
	}).bind("blur",function(event){
		return validarPorcentagem(this);
	});

	//Per�odo Inicial
	$('.tipoPeriodoInicial').bind("keypress drop blur",function(event){
		return isCampoNumerico(event);
	})
	.bind("keyup",function(event){
		mascaraAnoMes(this, event);
		$('[@name='+ $(this).attr('name').replace('Inicial','Final') + ']').val($(this).val());	
	});
	
	
	//Inteiro Inicial
	$('.tipoInteiroInicial').bind("keypress drop blur",function(event){
		mascara(this,mascaraInteiro);
		
	})
	.bind("keyup",function(event){
		$('[@name='+ $(this).attr('name').replace('Inicial','Final') + ']').val($(this).val());	
	});
	
	//Tipo data inicial
	$('.tipoDataInicial').bind("keypress drop blur",function(event){
		mascara(this,mascaraCampoData);
	})
	.bind("keyup",function(event){	
		$('[@name='+ $(this).attr('name').replace('Inicial','Final') + ']').val($(this).val());	
	});

	//Tipo data final
	$('.tipoDataFinal').bind("keypress drop blur",function(event){
		mascara(this,mascaraCampoData);
	})
	
});


	function chamarAjax(form,url,callbackSucesso, callbackErro){
		var theForm = $("form[name="+form+"]");
		var params = theForm.serialize();
		var actionURL = url
		if(callbackErro == null){		 
		    $.ajax({
			    type:"POST",
				url:actionURL,
				data:params,
				success:callbackSucesso
			});
		}
		else{
			$.ajax({
			    type:"POST",
				url:actionURL,
				data:params,
				success:callbackSucesso,
				error: callbackErro
			});
		}
	}
	
	
	function apagarSelect(nome){
		$('[@name='+nome+']').get(0).options.length = 0;
		$('[@name='+nome+']').get(0).options[0] = new Option("", "-1"); 
	}

	
	function preencherSelect(objJSON, nome){
		apagarSelect(nome);
		$.each(objJSON, function(index, item) {
			$('[@name='+nome+']').get(0).options[$('[@name='+nome+']')
		                                           		.get(0).options.length] = new Option(item.descricao, item.id);
		});
	}