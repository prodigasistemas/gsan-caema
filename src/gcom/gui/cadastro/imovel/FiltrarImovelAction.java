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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarImovelAction extends GcomAction {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = null;

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        String redirecionar = (String) sessao.getAttribute("redirecionar");
        
		if (redirecionar != null && !redirecionar.trim().equalsIgnoreCase("") && "ManterVinculoImoveisRateioConsumo".equals(redirecionar)) {
	      	retorno = actionMapping.findForward("retornarVinculosImoveisRateioConsumo");
		} else if (redirecionar != null && !redirecionar.trim().equalsIgnoreCase("") && "ManterDadosTarifaSocial".equals(redirecionar)) {
			retorno = actionMapping.findForward("retornarManterDadosTarifaSocial");
		} else {
        	retorno = actionMapping.findForward("retornarFiltroImovel");
        }
		
		
		FiltrarImovelActionForm filtrarImovelActionForm = null;
		
		//[UC1049] - Atualizar Dados Cadastrais PROMAIS
		//se houver algum imovel como parametro no request, carregar o imovel
		String idImovel = (String) httpServletRequest.getParameter("idImovel");
		
		if(idImovel != null && !idImovel.equals("")){
			
			filtrarImovelActionForm = new FiltrarImovelActionForm();
			filtrarImovelActionForm.setMatriculaFiltro(idImovel);
						
		}else{
			filtrarImovelActionForm = (FiltrarImovelActionForm) actionForm;
		}  
		
			
		//[UC1049] - Atualizar Dados Cadastrais PROMAIS
		//link para tela de sucesso
		String linkSucesso = (String) httpServletRequest.getParameter("linkSucesso");
		
		if(linkSucesso != null && !linkSucesso.equals("")){			
		
			sessao.setAttribute("linkSucesso", linkSucesso);							
		}		

        String idLocalidade = (String) filtrarImovelActionForm
                .getIdLocalidadeFiltro();
        String idSetorComercial = (String) filtrarImovelActionForm
                .getIdSetorComercialFiltro();
        String idQuadra = (String) filtrarImovelActionForm.getIdQuadraFiltro();
        String lote = (String) filtrarImovelActionForm.getLoteFiltro();
        String subLote = (String) filtrarImovelActionForm.getSubLoteFiltro();
        String codigoCliente = (String) filtrarImovelActionForm
                .getIdClienteFiltro();
        String idMunicipio = (String) filtrarImovelActionForm
                .getIdMunicipioFiltro();
        String cep = (String) filtrarImovelActionForm.getCepFiltro();
        String idBairro = (String) filtrarImovelActionForm.getIdBairroFiltro();
        String idLogradouro = (String) filtrarImovelActionForm.getIdLogradouroFiltro();
        //String indicadorUso = (String) filtrarImovelActionForm
          //      .get("indicadorUso");
        String matricula = (String) filtrarImovelActionForm.getMatriculaFiltro();
        
        String numeroImovelInicial = (String) filtrarImovelActionForm.getNumeroImovelInicialFiltro();
        String numeroImovelFinal = (String) filtrarImovelActionForm.getNumeroImovelFinalFiltro();
        
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");

		if (atualizar != null && atualizar.equals("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}else{
			filtrarImovelActionForm.setAtualizarFiltro("");
		}
        
        
        boolean peloMenosUmParametroInformado = false;

        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
        //filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.lote");
        //filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.subLote");
        //*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
        //*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");        
        //*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
        
        if(redirecionar != null && !redirecionar.trim().equalsIgnoreCase("") && "ManterVinculoImoveisRateioConsumo".equals(redirecionar)){
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));
        }
        
        //matricula
        if (matricula != null && !matricula.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.IMOVEL_ID, matricula));
        }
        //localidade
        if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
           
            //filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.LOCALIDADE_ID,
                    new Integer(idLocalidade)));
        }
        //setor comercial
        if (idSetorComercial != null
                && !idSetorComercial.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.SETOR_COMERCIAL_CODIGO, new Integer(
                            idSetorComercial)));
        }
        //quadra
        if (idQuadra != null && !idQuadra.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.QUADRA_NUMERO, new Integer(idQuadra)));
        }
        //lote
        if (lote != null && !lote.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.LOTE, new Short(lote)));
        }
        //lote
        if (subLote != null && !subLote.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.SUBLOTE, new Short(subLote)));
        }
        //cliente
        if (codigoCliente != null && !codigoCliente.trim().equalsIgnoreCase("")) {
        	
        	//filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	
            peloMenosUmParametroInformado = true;
            filtroClienteImovel
                    .adicionarParametro(new ParametroSimples(
                            FiltroClienteImovel.CLIENTE_ID, new Integer(
                                    codigoCliente)));
        }
        //municipio
        if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
           
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.MUNICIPIO_SETOR_COMERICAL_CODIGO,
                    new Integer(idMunicipio)));
        }
        //cep
        if (cep != null && !cep.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            //*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.CEP_CODIGO, cep));
        }
        //bairro
        if (idBairro != null && !idBairro.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
            
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.BAIRRO_CODIGO, new Integer(idBairro)));
        }
        //logradouro
        if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro");
            
            filtroClienteImovel.adicionarParametro(new ComparacaoTexto(
                    FiltroClienteImovel.LOGRADOURO_ID, idLogradouro));
        }
        
		filtroClienteImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
					Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));
        
		//adiciona o indicador de uso de clinte(caso ativo)
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");

        } else {
            httpServletRequest.setAttribute("filtroImovelPreenchido",
                    filtroClienteImovel);
            sessao.setAttribute("filtroImovelPreenchido",
                    filtroClienteImovel);
            
            sessao.setAttribute("idLocalidade",
            		idLocalidade);
            sessao.setAttribute("idSetorComercial",
            		idSetorComercial);
		    sessao.setAttribute("idQuadra",
		    		idQuadra);
		    sessao.setAttribute("lote",
		    		lote);
		    sessao.setAttribute("subLote",
		    		subLote);
		    sessao.setAttribute("codigoCliente",
		    		codigoCliente);
		    sessao.setAttribute("idMunicipio",
		    		idMunicipio);
		    sessao.setAttribute("cep",
		    		cep);
		    sessao.setAttribute("idBairro",
		    		idBairro);
		    sessao.setAttribute("idLogradouro",
		    		idLogradouro);
		    sessao.setAttribute("idImovel",
		    		matricula);
		    sessao.setAttribute("numeroImovelInicial", numeroImovelInicial);
		    sessao.setAttribute("numeroImovelFinal", numeroImovelFinal);
		    
        }

        sessao.removeAttribute("primeiraVez");
       // sessao.removeAttribute("redirecionar");
        return retorno;
    }

}
