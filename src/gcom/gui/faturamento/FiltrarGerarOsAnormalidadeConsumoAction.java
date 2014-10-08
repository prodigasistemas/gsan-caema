/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.faturamento;


import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.ComandoOsAnormalidade;
import gcom.faturamento.ComandoOsAnormalidadeGrupo;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroComandoOsAnormalidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1280] gerar os anormalidade consumo
 * 
 * @author Fernanda Almeida
 * @date 15/02/2012
 */
public class FiltrarGerarOsAnormalidadeConsumoAction extends GcomAction {
	

    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
	
		GerarOsAnormalidadeConsumoFiltrarActionForm form = (GerarOsAnormalidadeConsumoFiltrarActionForm) actionForm;
		
		validaDados(form);
				
		
		// validar setor comercial sendo maior que setor comercial final
		if (form.getSetorComercialOrigemCD() != null
					&& form.getSetorComercialDestinoCD() != null) {
			if (!form.getSetorComercialOrigemCD().equals("")
					&& !form.getSetorComercialDestinoCD().equals("")) {
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form
						.getSetorComercialDestinoCD());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}
			}
		}
        
		//Dados da inscrição de origem
        Integer localidadeOrigemID = null;
        if(form.getLocalidadeOrigemID() != null && !form.getLocalidadeOrigemID().equals("")){
        	localidadeOrigemID = Integer.parseInt(form.getLocalidadeOrigemID());
        	
        	
        	Localidade localidadeInscricaoOrigem = new Localidade();
        	localidadeInscricaoOrigem.setId(localidadeOrigemID);
        	
        }
        
        Integer setorComercialOrigemCD = null;
        Integer setorComercialOrigemID = null;
        SetorComercial setorComercialInscricaoOrigem = new SetorComercial();
        if(form.getSetorComercialOrigemCD() != null && !form.getSetorComercialOrigemCD().equals("")){
           setorComercialOrigemCD = Integer.parseInt(form.getSetorComercialOrigemCD());
           setorComercialOrigemID = Integer.parseInt(form.getSetorComercialOrigemID());
           setorComercialInscricaoOrigem.setCodigo(setorComercialOrigemCD);
           setorComercialInscricaoOrigem.setId(setorComercialOrigemID);
       	
        }
        else{
        	
        	setorComercialInscricaoOrigem.setCodigo(0);
           	
        }
        
        Integer quadraOrigemNM = null;
        Quadra quadraInscricaoOrigem = new Quadra();
        if(form.getQuadraOrigemNM() != null && !form.getQuadraOrigemNM().equals("")){
        	quadraOrigemNM = Integer.parseInt(form.getQuadraOrigemNM());        	
        	quadraInscricaoOrigem.setNumeroQuadra(quadraOrigemNM);        	
        }
        else{
        	
        	quadraInscricaoOrigem.setNumeroQuadra(0);
        	
        }
           
        
        Localidade localidadeOrigem = (Localidade) validarCampo(
                localidadeOrigemID, null, 1);
        SetorComercial setorComercialOrigem = null;
        Quadra quadraOrigem = null;

        //Validação dos campos da inscrição de origem (FS0002, FS0003, FS0004)
        if (localidadeOrigem != null) {
            
        	if (setorComercialOrigemCD != null) {

                setorComercialOrigem = (SetorComercial) validarCampo(
                        localidadeOrigem.getId(),
                        setorComercialOrigemCD.toString(), 2);

                if (setorComercialOrigem == null) {
                    throw new ActionServletException(
                            "atencao.pesquisa.setor_origem_inexistente");
                } else {
                    if (quadraOrigemNM != null) {

                        quadraOrigem = (Quadra) validarCampo(
                                setorComercialOrigem.getId(), quadraOrigemNM.toString(), 3);

                        if (quadraOrigem == null) {
                            throw new ActionServletException(
                                    "atencao.pesquisa.quadra_origem_inexistente");
                        }else{
                       	 form.setQuadraOrigemID(String.valueOf(quadraOrigem.getId()));
                       }
                    }
                }
            }
        }
		
        Integer localidadeDestinoID = null;
        if(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals("")){
        	localidadeDestinoID = Integer.parseInt(form.getLocalidadeDestinoID());
        	
        	Localidade localidadeInscricaoDestino = new Localidade();
        	localidadeInscricaoDestino.setId(localidadeDestinoID);
        	
        }
        
        Integer setorComercialDestinoCD = null;
        Integer setorComercialDestinoID = null;
        SetorComercial setorComercialInscricaoDestino = new SetorComercial();
        if(form.getSetorComercialDestinoCD() != null && !form.getSetorComercialDestinoCD().equals("")){
        	setorComercialDestinoCD = Integer.parseInt(form.getSetorComercialDestinoCD());
        	setorComercialDestinoID = Integer.parseInt(form.getSetorComercialDestinoID());
        	
            setorComercialInscricaoDestino.setId(setorComercialDestinoID);
            setorComercialInscricaoDestino.setCodigo(setorComercialDestinoCD);
        	
        }
        else{
        	
        	setorComercialInscricaoDestino.setCodigo(0);
        	
        }
        
        //Remove as quadras selecionadas, caso não seja o mesmo setor comercial das inscrições
        if(setorComercialOrigemCD != null && setorComercialDestinoCD != null 
        	&& !setorComercialOrigemCD.equals(setorComercialDestinoCD)){
          if(sessao.getAttribute("quadraSelecionada") != null){
             sessao.removeAttribute("quadraSelecionada");
          }
        }
        
        Integer quadraDestinoNM = null;
        Quadra quadraInscricaoDestino = new Quadra();
        if(form.getQuadraDestinoNM() != null && !form.getQuadraDestinoNM().equals("")){
        	quadraDestinoNM = Integer.parseInt(form.getQuadraDestinoNM());
        	
        	quadraInscricaoDestino.setNumeroQuadra(quadraDestinoNM);
        	
        }
        else{
        	
        	quadraInscricaoDestino.setNumeroQuadra(0);
        	
        }
                
        
        Localidade localidadeDestino = (Localidade) validarCampo(
                localidadeDestinoID, null, 1);
        SetorComercial setorComercialDestino = null;
        Quadra quadraDestino = null;


        //Validação dos campos da inscrição de destino (FS0002, FS0003,
        // FS0004)
        if (localidadeDestino != null) {
            
            if (setorComercialDestinoCD != null) {

                setorComercialDestino = (SetorComercial) validarCampo(
                        localidadeDestino.getId(),
                        setorComercialDestinoCD.toString(), 2);

                if (setorComercialDestino == null) {
                    //Nenhum Setor Comercial encontrado
                    throw new ActionServletException(
                            "atencao.pesquisa.setor_destino_inexistente");
                } else {
                    if (quadraDestinoNM != null) {

                        quadraDestino = (Quadra) validarCampo(
                                setorComercialDestino.getId(),
                                quadraDestinoNM.toString(), 3);
                                               

                        if (quadraDestino == null) {
                            //Nenhuma Quadra encontrada
                            throw new ActionServletException(
                                    "atencao.pesquisa.quadra_destino_inexistente");
                        }else{
                        	 form.setQuadraDestinoID(String.valueOf(quadraDestino.getId()));
                        }
                    }
                }
            }
        }
            
     // Quadra inicial
        if ( !form.getQuadraOrigemID().equals("")){
 			
			//Verifica se ja existe uma quadra cadastrada com o mesmo numero
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			
			filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO,  ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.ID, new Integer(form.getQuadraOrigemID())));
			
			Collection<?> colecaoQuadraInicialAtiva = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
			
			if(colecaoQuadraInicialAtiva.isEmpty()){
				throw new ActionServletException("atencao.quadra_inativa");
			}
	
			filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialOrigemID()));
	
			Collection<?> colecaoQuadraInicial = fachada.pesquisar(filtroQuadra, Quadra.class
					.getName());
	
			if (form.getQuadraOrigemID() != null && !form.getQuadraOrigemID().equals("") && colecaoQuadraInicial.isEmpty()){
				throw new ActionServletException("atencao.quadra_setor_comercial", form.getQuadraOrigemID());
			}else if (colecaoQuadraInicial == null || colecaoQuadraInicial.isEmpty()){
				throw new ActionServletException("atencao.quadra.inexistente");
			}
		}
		
		// Quadra Final
		if (!form.getQuadraDestinoID().equals("")){
		
		//Verifica se ja existe uma quadra cadastrada com o mesmo numero
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.INDICADORUSO,  ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID, new Integer(form.getQuadraDestinoID())));
		
		Collection<?> colecaoQuadraInicialAtiva = fachada.pesquisar(filtroQuadra, Quadra.class
			.getName());
		
		if(colecaoQuadraInicialAtiva.isEmpty()){
			throw new ActionServletException("atencao.quadra_inativa");
		}
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialDestinoID()));
		
		Collection<?> colecaoQuadraFinal = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		
		if (form.getQuadraDestinoID() != null && !form.getQuadraDestinoID().equals("") && colecaoQuadraFinal.isEmpty()){
		throw new ActionServletException("atencao.quadra_setor_comercial", form.getQuadraOrigemID());
		}else if (colecaoQuadraFinal == null || colecaoQuadraFinal.isEmpty()){
			throw new ActionServletException("atencao.quadra.inexistente");
			}
		}  
		

		// Rota Inicial
		Integer idRotaInicial =null;
		if (!form.getCodigoRotaOrigem().equals("") ) {
			
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getCodigoRotaOrigem()));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,  ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Rota ativa
			Collection<Rota> colecaoRotasAtivas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
				filtroRota, Rota.class.getName());
			
			if(colecaoRotasAtivas.isEmpty()){
				throw new ActionServletException("atencao.rota_inicial_desativada");
			}
			
			if (form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("")){
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialOrigemID()));
			}
			
			// Rota sem setor
			Collection<Rota> colecaoRotas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
					filtroRota, Rota.class.getName());
			
			if(!colecaoRotas.isEmpty()){
				Rota idsRota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
				idRotaInicial = idsRota.getId();
			}
			
			if (form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("") && colecaoRotas.isEmpty()){
				throw new ActionServletException("atencao.rota_setor_comercial", form.getSetorComercialOrigemCD());
			}else if (colecaoRotas == null || colecaoRotas.isEmpty()){
				throw new ActionServletException("atencao.rota_inexistente", "inicial");
			}
			
			
		}
		
		// Rota Final
		Integer idRotaFinal =null;
		if (!form.getCodigoRotaDestino().equals("") ) {
				
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getCodigoRotaDestino()));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,  ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Rota ativa
			Collection<Rota> colecaoRotasAtivas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
				filtroRota, Rota.class.getName());
			
			if(colecaoRotasAtivas.isEmpty()){
				throw new ActionServletException("atencao.rota_final_desativada");
			}
			
			if (form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("")){
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialDestinoID()));
			}
			
			Collection<Rota> colecaoRotas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
					filtroRota, Rota.class.getName());
			
			if(!colecaoRotas.isEmpty()){
				Rota idsRota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
				idRotaFinal = idsRota.getId();
			}
			
			if (form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("") && colecaoRotas.isEmpty()){
				throw new ActionServletException("atencao.rota_setor_comercial", form.getSetorComercialOrigemCD());
			}else if (colecaoRotas == null || colecaoRotas.isEmpty()){
				throw new ActionServletException("atencao.rota_inexistente", "final");
			}
			
		}
	        
        // Seta o comando Os anormalidade para ser inserido
        ComandoOsAnormalidade comandoOsanormalidade = new ComandoOsAnormalidade();
        
        Object[] objChecarGrupo = new Object[13];
        
        String mesReferencia = form.getMes();
        String mes = mesReferencia.substring(0, 2);
		String ano = mesReferencia.substring(3, 7);

		String anoMesReferencia = ano + mes;
        comandoOsanormalidade.setAnoMesReferencia(Integer.parseInt(anoMesReferencia));
               
        
        if(!form.getIdGrupo().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
            FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
            faturamentoGrupo.setId(Integer.parseInt(form.getIdGrupo()));
            comandoOsanormalidade.setFaturamentoGrupo(faturamentoGrupo);
            objChecarGrupo[4] = Integer.parseInt(form.getIdGrupo());
        }
        
        if(!form.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
            GerenciaRegional gerenciaRegional = new GerenciaRegional();
            gerenciaRegional.setId(Integer.parseInt(form.getIdGerenciaRegional()));
            comandoOsanormalidade.setGerenciaRegional(gerenciaRegional);
            objChecarGrupo[2] = Integer.parseInt(form.getIdGerenciaRegional());
		}
        
        if(!form.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        	UnidadeNegocio unidade = new UnidadeNegocio();
        	unidade.setId(Integer.parseInt(form.getIdUnidadeNegocio()));
            comandoOsanormalidade.setUnidadeNegocio(unidade);
            objChecarGrupo[3] = Integer.parseInt(form.getIdUnidadeNegocio());
		}
        
        comandoOsanormalidade.setLocalidadeInicial(localidadeOrigem);
        objChecarGrupo[5] = localidadeOrigemID;
        comandoOsanormalidade.setLocalidadeFinal(localidadeDestino);
        objChecarGrupo[9] = localidadeDestinoID;
        comandoOsanormalidade.setSetorComercialInicial(setorComercialInscricaoOrigem);
        objChecarGrupo[6] = setorComercialOrigemID;
        comandoOsanormalidade.setSetorComercialFinal(setorComercialInscricaoDestino);
        objChecarGrupo[10] = setorComercialDestinoID;
        
        if(quadraOrigem!=null){
        	comandoOsanormalidade.setQuadraInicial(quadraOrigem);
            objChecarGrupo[7] = Integer.parseInt(form.getQuadraOrigemID());
        }
        if(quadraDestino!=null){
        	comandoOsanormalidade.setQuadraFinal(quadraDestino);
            objChecarGrupo[11] = Integer.parseInt(form.getQuadraDestinoID());
        }
        
        if(idRotaInicial != null){
            Rota rotaInicial= new Rota();
            rotaInicial.setId(idRotaInicial);
            objChecarGrupo[8] = idRotaInicial;
            comandoOsanormalidade.setRotaInicial(rotaInicial);
        }      
        
        if(idRotaFinal != null){
        	 Rota rotaFinal= new Rota();
             rotaFinal.setId(idRotaFinal);
             objChecarGrupo[12] = idRotaFinal;
             comandoOsanormalidade.setRotaFinal(rotaFinal);        	
        }

        Collection<Integer> gruposIdsCheck = fachada.pesquisarGruposParaComandoOsAnormalidade(objChecarGrupo);
        if(gruposIdsCheck.isEmpty()){
        	throw new ActionServletException("atencao.filtro_nao_grupo",form.getIdGrupo());
        }
        
        // Caso a opcao de anormalidade de consumo
        // esteja marcada como 'ambos' ou 'estouro'
        if(form.getAnormalidadeConsumo().equals("1") || form.getAnormalidadeConsumo().equals("3")){
        	comandoOsanormalidade.setIndicadorEstouroConsumo(ConstantesSistema.SIM);
        }else{
        	comandoOsanormalidade.setIndicadorEstouroConsumo(ConstantesSistema.NAO);
        }
        // Caso a opcao de anormalidade consumo
        // esteja marcada como 'ambos' ou 'baixo consumo'
        if(form.getAnormalidadeConsumo().equals("2") || form.getAnormalidadeConsumo().equals("3")){
        	comandoOsanormalidade.setIndicadorBaixoConsumo(ConstantesSistema.SIM);
        }else{
        	comandoOsanormalidade.setIndicadorBaixoConsumo(ConstantesSistema.NAO);
        }
     
        comandoOsanormalidade.setUltimaAlteracao(new Date());
        comandoOsanormalidade.setIndicadorComandoExecutado(ComandoOsAnormalidade.INDICADOR_COMANDO_NAO_EXECUTADO);
        
        //Insere o comando Os anormalidade filtrado
        fachada.inserir(comandoOsanormalidade);
        
        FiltroComandoOsAnormalidade filtroComandoOs = new FiltroComandoOsAnormalidade();
        filtroComandoOs.setCampoOrderBy(FiltroComandoOsAnormalidade.ID + " ASC");
        Collection<ComandoOsAnormalidade> comandoOsAnormalidadeAdicionado = fachada.pesquisar(filtroComandoOs, ComandoOsAnormalidade.class.getName());
        
        ComandoOsAnormalidade comandoAdicionado = (ComandoOsAnormalidade) Util.retonarObjetoDeColecao(comandoOsAnormalidadeAdicionado);
        
        Object[] objComandoAdicionado = fachada.buscaComandoOsAnormalidade(comandoAdicionado.getId().toString());
        
        Collection<Integer> gruposIds = fachada.pesquisarGruposParaComandoOsAnormalidade(objComandoAdicionado);
        
        if(!gruposIds.isEmpty()){
        	for(Integer idGrupo: gruposIds){
        		ComandoOsAnormalidadeGrupo comandoAnormGrupo = new ComandoOsAnormalidadeGrupo();
        		FaturamentoGrupo fGrupo = new FaturamentoGrupo();
        		fGrupo.setId(idGrupo);
        		comandoAnormGrupo.setFaturamentoGrupo(fGrupo);
        		comandoAnormGrupo.setComandoOsAnormalidade(comandoAdicionado);
        		comandoAnormGrupo.setIndicadorComandoExecutado(ComandoOsAnormalidadeGrupo.INDICADOR_COMANDO_NAO_EXECUTADO);
        		comandoAnormGrupo.setUltimaAlteracao(new Date());
        		
        		fachada.inserir(comandoAnormGrupo);
        	}
        }
        
        montarPaginaSucesso(httpServletRequest, "Comando Imóvel para Gerar OS Anormalidade de Consumo Criado com Sucesso.",
			"Criar Outro Comando",
			"exibirFiltrarGerarOsAnormalidadeConsumoAction.do?menu=sim");
        
        return retorno;
	
    }	
	 /**
     * Valida os valores digitados pelo usuário
     * 
     * @param campoDependencia
     * @param dependente
     * @param tipoObjeto
     * @return Object
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object validarCampo(Integer campoDependencia, String dependente,
            int tipoObjeto) {

        Object objeto = null;
        Collection<?> colecaoPesquisa;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        if (campoDependencia != null) {

            if (dependente == null || tipoObjeto == 1) {
                //Localidade
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.ID, campoDependencia));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                        Localidade.class.getName());

                if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                    objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                }
            } else if (dependente != null && !dependente.equalsIgnoreCase("")
                    && tipoObjeto > 1) {
                switch (tipoObjeto) {
                // Setor Comercial
                case 2:
                    FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.ID_LOCALIDADE,
                                    campoDependencia));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                                    new Integer(dependente)));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.INDICADORUSO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                            SetorComercial.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                // Quadra
                case 3:
                    FiltroQuadra filtroQuadra = new FiltroQuadra();
                    
                    //Objetos que serão retornados pelo hibernate
                    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
                    
                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.ID_SETORCOMERCIAL, campoDependencia));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.NUMERO_QUADRA, new Integer(dependente)));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.INDICADORUSO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroQuadra,
                            Quadra.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                default:
                    break;
                }
            }
        }

        return objeto;
    }
    
    /**
     * Verifica se as localidades inicial e final  foram digitadas e se elas 
     * pertencem à gerencia regional e  à Unidade de negocio selecionadas;
     * 
     * @author Erivan
     * @param gerenciaRegional
     * @param unidadeNegocio
     * @param idLocalidadeInicial
     * @param idLocalidadeFinal
     */
    private void validaDados(GerarOsAnormalidadeConsumoFiltrarActionForm form){
    	
    	Integer locaFinal = null;
    	Integer locaInicial = null;
    	Fachada fachada = Fachada.getInstancia();
    	
    	Collection<Rota> colRota = null;
    	
    	try{
    		if(form.getLocalidadeOrigemID() != null && !form.getLocalidadeOrigemID().equals("")){
    			if(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals("")){
    				locaFinal = new Integer(form.getLocalidadeDestinoID());
    				locaInicial = new Integer(form.getLocalidadeOrigemID());
       
    				if(locaFinal < locaInicial){
    					throw new ActionServletException("atencao.localidade_final_menor_inicial");
    				}
    			}else{
    				//força informar localidade final
    				throw new ActionServletException("atencao.informe_localidade_final");
    			}
    		}else{
    			if(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals("")){
    					//força informar localidade inicial
    					throw new ActionServletException("atencao.informe_localidade_inicial");
    			}
    		}
        }catch(java.lang.NumberFormatException ex){
        	throw new ActionServletException("atencao.localidade_numeros_positivos");
        }
    	
    	String msgErro = null; 
    	boolean possuiGerencia = false;
    	boolean possuiGrupo = false;
    	
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade.unidadeNegocio");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade.unidadeNegocio.gerenciaRegional");
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ICUSO,1));
		filtroRota.setCampoDistinct("objeto."+FiltroRota.FATURAMENTO_GRUPO_ID);
		if(form.getIdGrupo() != null &&  !form.getIdGrupo().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			possuiGrupo = true;
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ID, form.getIdGrupo()));
			colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
    		
		}
		
		// Verifica se ha gerencia no grupo
				if(form.getIdGerenciaRegional()!=null && !form.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
					if(possuiGrupo){
						msgErro = "atencao.gerencia_nao_grupo";
						possuiGrupo = true;
					}
					possuiGerencia = true;
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.GERENCIA_REGIONAL_ID, form.getIdGerenciaRegional()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
		    		if(colRota.isEmpty() && possuiGrupo){
		    			throw new ActionServletException(msgErro);
		    		}
				}
				// Verifica se ha unidade no grupo
				if(form.getIdUnidadeNegocio()!=null && !form.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
					
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.UNIDADE_NEGOCIO_ID, form.getIdUnidadeNegocio()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
					if(possuiGrupo && colRota.isEmpty()){
						msgErro = "atencao.unidade_nao_grupo";
					}else if(possuiGerencia && colRota.isEmpty()){
						msgErro = "unidade_nao_gerencia";
					}
		    		if(colRota.isEmpty() && (possuiGrupo ||possuiGerencia )){
		    			throw new ActionServletException(msgErro);
		    		}
				}
				// Verifica se ha localidade no grupo
				if(form.getLocalidadeOrigemID()!=null && !form.getLocalidadeOrigemID().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getLocalidadeOrigemID()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
					if(possuiGrupo && colRota.isEmpty()){
						msgErro = "atencao.localidade_inicial_nao_grupo";
						throw new ActionServletException(msgErro);
					}
				}
				
				if(form.getLocalidadeDestinoID()!=null && !form.getLocalidadeDestinoID().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getLocalidadeDestinoID()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
					if(possuiGrupo && colRota.isEmpty()){
						msgErro = "atencao.localidade_inicial_nao_grupo";
						throw new ActionServletException(msgErro);
					}
				}
				
				// Verifica se ha setor no grupo
				if(form.getSetorComercialOrigemID()!=null && !form.getSetorComercialOrigemID().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialOrigemID()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
		    		if(colRota.isEmpty() && possuiGrupo  ){
		    			throw new ActionServletException("atencao.setor_inicial_nao_grupo");
		    		}
				}
				
				if(form.getSetorComercialDestinoID()!=null && !form.getSetorComercialDestinoID().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialDestinoID()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
		    		if(colRota.isEmpty() && possuiGrupo){
		    			throw new ActionServletException("atencao.setor_final_nao_grupo");
		    		}
				}
				
				// Verifica se ha rota no grupo
				if(form.getCodigoRotaOrigem()!=null && !form.getCodigoRotaOrigem().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getCodigoRotaOrigem()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
					if(colRota.isEmpty() && possuiGrupo){
		    			throw new ActionServletException("atencao.rota_inicial_nao_grupo");
		    		}
				}
				
				if(form.getCodigoRotaDestino()!=null && !form.getCodigoRotaDestino().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getCodigoRotaDestino()));
					colRota = fachada.pesquisar(filtroRota,Rota.class.getName());
					if(colRota.isEmpty() && possuiGrupo){
		    			throw new ActionServletException("atencao.rota_final_nao_grupo");
		    		}
				}
				
        
    	/*Verifica se as localidades pertencem a gerencia regional e a unidade de negocio selecionadas*/
        if(form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        	if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        		
        		if((form.getLocalidadeOrigemID() != null && !form.getLocalidadeOrigemID().equals("")) &&
            			(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals(""))){
        			
        			Localidade localidadeInicial= this.buscarLocalidadePorId(form.getLocalidadeOrigemID(), Fachada.getInstancia());
        			Localidade localidadeFinal= this.buscarLocalidadePorId(form.getLocalidadeDestinoID(), Fachada.getInstancia());
           
        			this.validarLocalidades(localidadeInicial, localidadeFinal);
        		
        			if(!localidadeInicial.getGerenciaRegional().getId().toString().equals(form.getIdGerenciaRegional())){
        				throw new ActionServletException("atencao.localidade_inicial_nao_pertence_gerencia_regional");
        			}
        			if(!localidadeInicial.getUnidadeNegocio().getId().toString().equals(form.getIdUnidadeNegocio())){
        				throw new ActionServletException("atencao.localidade_inicial_nao_pertence_unidade_negocio");
        			} 
           
        			if(!localidadeFinal.getGerenciaRegional().getId().toString().equals(form.getIdGerenciaRegional())){
        				throw new ActionServletException("atencao.localidade_final_nao_pertence_gerencia_regional");
        			}
        			if(!localidadeFinal.getUnidadeNegocio().getId().toString().equals(form.getIdUnidadeNegocio())){
        				throw new ActionServletException("atencao.localidade_final_nao_pertence_unidade_negocio");
        			}
        		}
            }else{
            	//O usuário so selecionou a gerencia regional
            	if((form.getLocalidadeOrigemID() != null && !form.getLocalidadeOrigemID().equals("")) &&
            			(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals(""))){
            		
            		Localidade localidadeInicial= this.buscarLocalidadePorId(form.getLocalidadeOrigemID(), Fachada.getInstancia());
            		Localidade localidadeFinal= this.buscarLocalidadePorId(form.getLocalidadeDestinoID(), Fachada.getInstancia());
            	
            		this.validarLocalidades(localidadeInicial, localidadeFinal);
            	           	
            		if(!localidadeInicial.getGerenciaRegional().getId().toString().equals(form.getIdGerenciaRegional())){
            			throw new ActionServletException("atencao.localidade_inicial_nao_pertence_gerencia_regional");
            		}             
           
            		if(!localidadeFinal.getGerenciaRegional().getId().toString().equals(form.getIdGerenciaRegional())){
            			throw new ActionServletException("atencao.localidade_final_nao_pertence_gerencia_regional");
            		}
            	}
            }
        }else{
        	/* Caso o usuário não tenha preenchido os campos de gerencia e unidade, 
        	 * verifica se foi digitado algum valor nas localidades e se eles são válidos;
        	 */
        	if((form.getLocalidadeOrigemID() != null && !form.getLocalidadeOrigemID().equals("")) &&
        			(form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals(""))){
        		
        		Localidade localidadeInicial= this.buscarLocalidadePorId(form.getLocalidadeOrigemID(), Fachada.getInstancia());               
        		Localidade localidadeFinal= this.buscarLocalidadePorId(form.getLocalidadeDestinoID(), Fachada.getInstancia());
        		
        		this.validarLocalidades(localidadeInicial, localidadeFinal);
        	}
        }    
    }
    
    /**
     * Procura a localidade pelo id informado;  
     * @author Erivan
     * @param id
     * @param fachada
     * @return Localidade
     */
    private Localidade buscarLocalidadePorId(String id, Fachada fachada){
    	Localidade localidade = null;
   
	    FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	    
	    // Localidade desativada
	    Collection<Localidade> colecaoLocalidadeAtivada = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
	    
	    if(colecaoLocalidadeAtivada == null || colecaoLocalidadeAtivada.isEmpty()){
	    	throw new ActionServletException("atencao.localidade_desativada");
	    }
	    
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
   
	    Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
   
	    if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	    	localidade = (Localidade) colecaoLocalidade.iterator().next();    
	    }
   
	    return localidade;
    }
    
    /**
     * Verifica os campos Localidade inicial e final;  
     * @author Erivan
     * @param localidadeInicial
     * @param localidadeFinal
     */
    private void validarLocalidades(Localidade localidadeInicial, Localidade localidadeFinal){
    	
    	if(localidadeInicial== null){
    		throw new ActionServletException("atencao.localidade_inicial_inexistente");
    	}
    	if(localidadeFinal == null){
    		throw new ActionServletException("atencao.localidade_final_inexistente");
    	}
    }
    
    

}