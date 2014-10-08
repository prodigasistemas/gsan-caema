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

import gcom.atendimentopublico.ordemservico.FiltroFotoSituacaoOrdemServico;
import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelContaEnvio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelFotoHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.FiltroNomeConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.operacional.AreaOperacional;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DistritoSetorAbastecimento;
import gcom.operacional.FiltroDistritoSetorAbastecimento;
import gcom.operacional.FiltroLocalidadeSistemaAbastecimento;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroZonaAbastecimento;
import gcom.operacional.LocalidadeSistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.operacional.ZonaAreaOperacional;
import gcom.operacional.ZonaPressao;
import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelConclusaoAction extends GcomAction {
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
    @SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirImovelConclusao");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // USUARIO LOGADO
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

        DynaValidatorForm inserirImovelConclusaoActionForm = 
        	(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");

        //Cria Filtros
        FiltroImovelContaEnvio filtroImovelContaEnvio = new FiltroImovelContaEnvio();

        //Cria coleçao
        Collection colecaoImovelEnnvioConta = null;


        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();   
        
        String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
        
        /*
         * @author Jonathan Marcos
         * @date 28/02/2014
         * RM10258
         * [OBSERVACOES]
         * 		- VERIFICAR SE O USUARIO LOGADO POSSUI 
         * 		  PERMISSAO ESPECIAL ID 147 PARA ALTERAR
         * 		  AS COORDENADAS X E Y
         */
       	if(fachada.verificarPermissaoEspecial(PermissaoEspecial.DIGITAR_COORDENADAS_IMOVEL, usuarioLogado)){
           	sessao.setAttribute("permissaoEspecialDigitarCoordenadasImovel", true);
        }else{
        	sessao.removeAttribute("permissaoEspecialDigitarCoordenadasImovel");
        }
        
        /*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
		   carregarDadosGis(gisHelper,inserirImovelConclusaoActionForm,sessao,fachada);
		}		
		
		
        String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
        if(informacoesComplementares != null && informacoesComplementares.equals("")){
        	inserirImovelConclusaoActionForm.set("informacoesComplementares","");        	
        }else {
        	inserirImovelConclusaoActionForm.set("informacoesComplementares",informacoesComplementares);
        }
		/*
		 * Carregamento inicial da tela responsável pelo redebimento das
		 * informações referentes ao local da ocorrência (ABA Nº 02)
		 * ============================================================================================================
		 */
        
//        //Faz a pesquisa de Ocupacao Tipo
//        filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(FiltroNomeConta.INDICADOR_USO,
//        		ConstantesSistema.INDICADOR_USO_ATIVO));
//        
//        filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
//        
//        colecaoImovelEnnvioConta = 
//        	fachada.pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
//        
//        if (!colecaoImovelEnnvioConta.isEmpty()) {
//            
//        	//Coloca a coleçao da pesquisa na sessao
//            sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
//            
//            /*
//             * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
//             * OBJ: Marcar o indicador de emissão de extrato de faturamento para NAO EMITIR
//             */
//            inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
//            
//        } else {
//            throw new ActionServletException("atencao.naocadastrado",null, "Imovel Conta Envio");
//        }        
        
        //Verifica se existe cliente responsavel
		boolean eResponsavel = false;

        //Testa se já existe um clinte proprietário
        if (sessao.getAttribute("imovelClientesNovos") == null) {
            httpServletRequest.setAttribute("envioContaListar", "naoListar");
        } else {
            Collection imovelClientes = (Collection) sessao.getAttribute("imovelClientesNovos");
            ClienteImovel clienteImovel = new ClienteImovel();
            Iterator iteratorClienteImovel = imovelClientes.iterator();

            while (iteratorClienteImovel.hasNext()) {
                
            	clienteImovel = null;
                clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
                
                if (clienteImovel.getClienteRelacaoTipo().getDescricao().equalsIgnoreCase(ConstantesSistema.IMOVEL_ENVIO_CONTA)) {
                    httpServletRequest.setAttribute("envioContaListar","listar");
                }
                
				if (clienteImovel.getClienteRelacaoTipo() != null &&
						clienteImovel.getClienteRelacaoTipo().getId() == 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","obrigatorio");
					
					eResponsavel = true;
					
				}else if ( clienteImovel.getClienteRelacaoTipo() != null && !eResponsavel &&
						clienteImovel.getClienteRelacaoTipo().getId() != 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","opcional");
					
				}
				
				if ( sessao.getAttribute("imovelContaEnvio") != null && !sessao.getAttribute("imovelContaEnvio").equals("") ){
					
					String envioConta = (String) sessao.getAttribute("imovelContaEnvio");
					
					if ( ( envioConta.equals("4") || envioConta.equals("5") )
							&& clienteImovel.getIndicadorNomeConta().compareTo(new Short("1")) == 0
							&& ( clienteImovel.getCliente().getEmail() == null
							|| clienteImovel.getCliente().getEmail().equals("") )){
	
							throw new ActionServletException("atencao.email.nao.cadastrado");
						
						
					}
					
				}
                
            }
        }
        
        //Faz a pesquisa de Ocupacao Tipo
		filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
				FiltroNomeConta.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Se existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 1
		if ( eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "1" ));
			
		}//Se não existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 2
		else if( !eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "2" ));
			
		}
		
		filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
		
		colecaoImovelEnnvioConta = 
			this.getFachada().pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
		
		if (!colecaoImovelEnnvioConta.isEmpty()) {
			httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "false");
			//Coloca a coleçao da pesquisa na sessao
			sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
			
			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
			 * OBJ: Marcar o indicador de emissão de extrato de faturamento para NAO EMITIR
			 */
			if (inserirImovelConclusaoActionForm.get("extratoResponsavel") != null &&
					!inserirImovelConclusaoActionForm.get("extratoResponsavel").equals("") &&
					inserirImovelConclusaoActionForm.get("extratoResponsavel").equals(ConstantesSistema.SIM.toString())){
				
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.SIM.toString());
			}else{
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
			}
			
			
		} else {
			
			/*throw new ActionServletException("atencao.naocadastrado",null,"Imóvel Conta Envio");*/
			if ( !eResponsavel ){
				
				httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "true");
			
			}else if ( eResponsavel ){
			
				throw new ActionServletException("atencao.naocadastrado",null,"Imóvel Conta Envio");
				
			}
			
		}

        
        //Cria variaveis
        String idImovel = (String) inserirImovelConclusaoActionForm.get("idImovel");
        String idFuncionario = (String) inserirImovelConclusaoActionForm.get("idFuncionario");
        String idRota = (String) inserirImovelConclusaoActionForm.get("idRota");
        String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");

        //Cria Filtros
        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que serão retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
        	FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

        filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
        
        
        // Sistema Abastecimento		
 		FiltroLocalidadeSistemaAbastecimento  filtro = new FiltroLocalidadeSistemaAbastecimento();
 		filtro.adicionarParametro( new ParametroSimples
 				(FiltroLocalidadeSistemaAbastecimento.LOCALIDADE_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO) );
 		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidadeSistemaAbastecimento.SISTEMA_ABASTECIMENTO);
 		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidadeSistemaAbastecimento.LOCALIDADE_ID,idLocalidade ));
 		
 		Collection<LocalidadeSistemaAbastecimento> colecaoLocalidadeSistemaAbs = fachada.pesquisar(
 				filtro, LocalidadeSistemaAbastecimento.class.getName());
 		
 		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = new ArrayList<SistemaAbastecimento>();
 		Iterator<LocalidadeSistemaAbastecimento> itLocalidade = colecaoLocalidadeSistemaAbs.iterator();
 		while(itLocalidade.hasNext()){
 			LocalidadeSistemaAbastecimento obj = itLocalidade.next();
 			colecaoSistemaAbastecimento.add(obj.getSistemaAbastecimento());
 		}
 		
 		
 		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
        

        Collection imoveis = null;

        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
            
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel.trim())));

            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (imoveis != null && !imoveis.isEmpty()) {

            	filtroImovel = new FiltroImovel();
            	filtroImovel.limparListaParametros();

            	//Cria Variaveis
                String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
                String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
                
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel.trim()));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID,new Integer(idLocalidade)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO,new Integer(idSetorComercial)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO,new Integer(idQuadra)));
            	
            	Collection colecaoImovel = null;
            	colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
            	
            	if(colecaoImovel == null || colecaoImovel.isEmpty()){
                    throw new ActionServletException("atencao.imovel_principal.inexistente.quadra");        		
            	}
            	
                sessao.setAttribute("imoveisPrincipal", imoveis);
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
                	fachada.pesquisarInscricaoImovel(new Integer(idImovel.trim())));
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", null);                
            }else {
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", "true");
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", "IMOVEL INEXISTENTE");
            }
        }
        
        
        if(idFuncionario != null && !idFuncionario.trim().equals("")){
        	
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = 
				this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getSimpleName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);

				httpServletRequest.setAttribute("idImovelPrincipalEncontrado","true");
				
				inserirImovelConclusaoActionForm.set("idFuncionario",funcionario.getId().toString());
				inserirImovelConclusaoActionForm.set("nomeFuncionario",funcionario.getNome());

			}else{
				
				inserirImovelConclusaoActionForm.set("idFuncionario","");
				inserirImovelConclusaoActionForm.set("nomeFuncionario","Funcionário Inexistente");
				
				
			}
        }
        
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRota", rota.getCodigo().toString());
        	}
        }
        
        if (idRotaAlternativa != null && !idRotaAlternativa.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaAlternativa));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRotaAlternativa", rota.getCodigo().toString());
        	}
        }
        
        Collection<?> colTipoFoto = (Collection<?>) sessao.getAttribute("colTipoFoto");
        
        if(Util.isVazioOrNulo(colTipoFoto)){
        	FiltroFotoSituacaoOrdemServico filtroFotoSituacao = new FiltroFotoSituacaoOrdemServico(FiltroFotoSituacaoOrdemServico.ID);
        	
        	colTipoFoto = fachada.pesquisar(filtroFotoSituacao, FotoSituacaoOrdemServico.class.getName());
        	
        	if(!Util.isVazioOrNulo(colTipoFoto)){
        		sessao.setAttribute("colTipoFoto", colTipoFoto);
        	}
        }
        
        String adicionarFoto = (String) httpServletRequest.getParameter("adicionarFoto");
        String removerFoto = (String) httpServletRequest.getParameter("remover");
        String visualizar = (String) httpServletRequest.getParameter("visualizar");
        
        //Adicionando um arquivo
        if(adicionarFoto != null && adicionarFoto.equals("OK")){
        	
        	Object[] parametrosFormulario = new Object[3];
	        FormFile arquivoInformado = null;
	        String observacaoAnexo = null;
	        String tipoFoto = null;
			
			//ARQUIVO
			parametrosFormulario[0] = (FormFile) inserirImovelConclusaoActionForm.get("documento");
			parametrosFormulario[1] = (String) inserirImovelConclusaoActionForm.get("observacoes");
			parametrosFormulario[2] = (String) inserirImovelConclusaoActionForm.get("idTipoFoto");
			
			arquivoInformado = (FormFile) parametrosFormulario[0];
			observacaoAnexo = (String) parametrosFormulario[1];
			tipoFoto = (String) parametrosFormulario[2];
			
			//VALIDAÇÃO DO ARQUIVO
			this.validarImovelFoto(arquivoInformado);
			
			ImovelFotoHelper helper = this.gerarImovelFotoHelper(arquivoInformado, observacaoAnexo, tipoFoto);
			
			//Limpar Arquivos do Formulario
			this.LimparArquivosForm(inserirImovelConclusaoActionForm);
			
			//INSERINDO O ARQUIVO NA COLEÇÃO DE VISUALIZAÇÃO
			Collection<ImovelFotoHelper> colecaoFoto = null;
			
			if (sessao.getAttribute("colecaoFoto") != null){
				colecaoFoto = (Collection<ImovelFotoHelper>) sessao.getAttribute("colecaoFoto");
				
				if(!colecaoFoto.contains(helper)){
					colecaoFoto.add(helper);
					sessao.setAttribute("colecaoFoto", colecaoFoto);
				}else{
					throw new ActionServletException("atencao.arquivo_ja_adicionado");
				}
			}
			else{
				
				colecaoFoto = new ArrayList<ImovelFotoHelper>();
				colecaoFoto.add(helper);
				
				sessao.setAttribute("colecaoFoto", colecaoFoto);
			}
        }
        
        //REMOVENDO UM ARQUIVO
        if(removerFoto != null && !removerFoto.equals("")){
        	this.removerArquivo(removerFoto, sessao);
        	
        	//Limpar Arquivos do Formulario
			this.LimparArquivosForm(inserirImovelConclusaoActionForm);
        }
        
        //OBTENDO ARQUIVO PARA VISUALIZAÇÃO
        if(visualizar != null && !visualizar.equals("")){
        	ImovelFotoHelper fotoHelper = this.obterArquivoParaVisualizacao(visualizar, sessao);
        	
        	//PREPARANDO VISUALIZAÇÃO DO ARQUIVO
      		if (fotoHelper != null){
      			
      			OutputStream out = null;
      			
      			String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
      			
      			if (fotoHelper.getExtensaoArquivo().equalsIgnoreCase(ConstantesSistema.EXTENSAO_JPG)){
      				mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
      			}else if(fotoHelper.getExtensaoArquivo().equalsIgnoreCase(ConstantesSistema.EXTENSAO_JPEG)){
      				mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
      			}
      			
      			try {
      				httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + fotoHelper.getNomeArquivo());
      				httpServletResponse.setContentType(mimeType);
      				
      				out = httpServletResponse.getOutputStream();
      				out.write(fotoHelper.getFoto());
      				out.flush();
      				out.close();
      			} 
      			catch (IOException e) {
      				throw new ActionServletException("erro.sistema", e);
      			}
      		}
      		
      		//Limpar Arquivos do Formulario
			this.LimparArquivosForm(inserirImovelConclusaoActionForm);
        }
      	
      	return retorno;
    }
	
    //=================================================================================================================
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			DynaValidatorForm inserirImovelConclusaoActionForm,
			HttpSession sessao, Fachada fachada) {
	
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		inserirImovelConclusaoActionForm.set("cordenadasUtmX",nnCoordenadaLeste);
		inserirImovelConclusaoActionForm.set("cordenadasUtmY",nnCoordenadaNorte);
		
	     sessao.removeAttribute("gisHelper");	
	
	}
	
	private void validarImovelFoto(FormFile arquivoInformado) {
    	if(arquivoInformado == null || arquivoInformado.getFileSize() == 0){
    		throw new ActionServletException("atencao.campo.informado", null, "Arquivo");
    	}
    	
    	//Só poderão ser informados arquivos com as extensões: JPG ou JPEG
    	String arquivoExtensao = Util.retornarExtensaoArquivo(arquivoInformado);
    	
    	if (!arquivoExtensao.equalsIgnoreCase("JPG") &&
    			!arquivoExtensao.equalsIgnoreCase("JPEG")) {
    		throw new ActionServletException("atencao.arquivo_invalido");
    	}
    	
    	if(arquivoInformado.getFileSize() > 3072000 ) {
    		throw new ActionServletException("atencao.foto_tamanho_maximo_3_MB");
    	}
    	
    }
	
	private ImovelFotoHelper gerarImovelFotoHelper(FormFile arquivoAnexo, 
			String observacaoAnexo, String tipoFoto){
		
		ImovelFotoHelper helper = new ImovelFotoHelper();
		
		//ARQUIVO EM BYTES
		try {
			helper.setFoto(arquivoAnexo.getFileData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//NOME DO ARQUIVO
		helper.setNomeArquivo(arquivoAnexo.getFileName());
		
		//EXTENSÃO
		helper.setExtensaoArquivo(Util.retornarExtensaoArquivo(arquivoAnexo));
		
		//OBSERVAÇÃO
		if (observacaoAnexo != null && !observacaoAnexo.equals("")){
			if(observacaoAnexo.length() > 200){
				throw new ActionServletException("atencao.limite_comentarios_excedidos");
			}
			
			helper.setObservacao(observacaoAnexo.trim());
		}
		
		//HINT
		helper.setHint("Visualizar arquivo: " + arquivoAnexo.getFileName());
		
		//TIPO FOTO
		if(tipoFoto != null && !tipoFoto.equals("")){
			helper.setIdTipoFoto(tipoFoto);
			
			FiltroFotoSituacaoOrdemServico filtro = new FiltroFotoSituacaoOrdemServico();
			filtro.adicionarParametro(new ParametroSimples(FiltroFotoSituacaoOrdemServico.ID, tipoFoto));
			
			Collection<?> colecaoFotoSituacaoOS = this.getFachada().pesquisar(filtro, FotoSituacaoOrdemServico.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoFotoSituacaoOS)){
				FotoSituacaoOrdemServico fotoSituacaoOS = (FotoSituacaoOrdemServico) Util.retonarObjetoDeColecao(colecaoFotoSituacaoOS);
				helper.setDescricao(fotoSituacaoOS.getDescricao());
			}
		}
		
		return helper;
	}
	
	private void removerArquivo(String identificacao, HttpSession sessao){
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection<ImovelFotoHelper> colecaoFoto = (Collection<ImovelFotoHelper>) sessao.getAttribute("colecaoFoto");
			
			Iterator<ImovelFotoHelper> it = colecaoFoto.iterator();
			ImovelFotoHelper anexoColecao = null;
			int cont = 1;
			
			while (it.hasNext()){
				
				anexoColecao = (ImovelFotoHelper) it.next();
				
				if (cont == Integer.parseInt(identificacao)){
					colecaoFoto.remove(anexoColecao);
					break;
				}
				cont++;
			}
			
			if (colecaoFoto.isEmpty()){
				sessao.removeAttribute("colecaoFoto");
			}
		}
	}
	
	private ImovelFotoHelper obterArquivoParaVisualizacao(String identificacao, HttpSession sessao){
		
		ImovelFotoHelper fotoHelper = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection<ImovelFotoHelper> colecaoFoto = (Collection<ImovelFotoHelper>) sessao.getAttribute("colecaoFoto");
			
			Iterator<ImovelFotoHelper> it = colecaoFoto.iterator();
			ImovelFotoHelper anexoColecao = null;
			
			int cont = 1;
			
			while (it.hasNext()){
				
				anexoColecao = (ImovelFotoHelper) it.next();
				
				if (cont == Integer.parseInt(identificacao)){
					fotoHelper = anexoColecao;
					break;
				}
				cont++;
			}
		}
		
		return fotoHelper;
	}
	
	private void LimparArquivosForm(DynaValidatorForm inserirImovelConclusaoActionForm){
		inserirImovelConclusaoActionForm.set("documento", null);
		inserirImovelConclusaoActionForm.set("observacoes", "");
		inserirImovelConclusaoActionForm.set("idTipoFoto", "");
	}
	
	
	
	
	public ActionForward atualizarListaSubsistemaAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//InserirDistritoOperacionalActionForm form = (InserirDistritoOperacionalActionForm)actionForm;
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(1,sessao);
		
		DynaValidatorForm form = 
				(DynaValidatorForm) actionForm;
		
		String idSistemaPrincipal = (String)form.get("sistemaAbastecimento");
		
		Collection<SubsistemaSistemaAbastecimento> colecaoSub = 
				fachada.pesquisarSubSistemaSistemaAbastecimento(Integer.parseInt(idSistemaPrincipal));
		
		Collection<SubsistemaAbastecimento> colTelaPrincipal = new ArrayList<SubsistemaAbastecimento>();
		Iterator<SubsistemaSistemaAbastecimento> itTelaPrincipal =  colecaoSub.iterator();
		while(itTelaPrincipal.hasNext()){
			SubsistemaSistemaAbastecimento sub = itTelaPrincipal.next();
			colTelaPrincipal.add(sub.getSubsistemaAbastecimento());
		}
		
		sessao.setAttribute("colecaoSubsistemaPrincipal", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<SubsistemaSistemaAbastecimento> it = colecaoSub.iterator();
		while(it.hasNext()){
			SubsistemaSistemaAbastecimento sub = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", sub.getSubsistemaAbastecimento().getId());
				obj.append("descricao", sub.getSubsistemaAbastecimento().getDescricao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward atualizarListaSetorAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(2,sessao);
		
		DynaValidatorForm form = 
				(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");
		
		String idSistemaPrincipal = (String)form.get("subsistemaAbastecimento");
		
		FiltroSetorAbastecimento filtro = new FiltroSetorAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		filtro.adicionarParametro(new ParametroSimples("subsistemaAbastecimento.id",idSistemaPrincipal));
		filtro.adicionarParametro(new ParametroSimples("setorAbastecimento.indicadorUso",ConstantesSistema.SIM));
		

		Collection<SetorSubsistemaAbastecimento> colecaoSetor = 
				(Collection<SetorSubsistemaAbastecimento>)fachada.pesquisar(filtro,SetorSubsistemaAbastecimento.class.getName());
		
		Collection<SetorAbastecimento> colTelaPrincipal = new ArrayList<SetorAbastecimento>();
		Iterator<SetorSubsistemaAbastecimento> itTelaPrincipal =  colecaoSetor.iterator();
		while(itTelaPrincipal.hasNext()){
			SetorSubsistemaAbastecimento sub = itTelaPrincipal.next();
			colTelaPrincipal.add(sub.getSetorAbastecimento());
		}
		
		sessao.setAttribute("colecaoSetorAbastecimento", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<SetorSubsistemaAbastecimento> it = colecaoSetor.iterator();
		while(it.hasNext()){
			SetorSubsistemaAbastecimento sub = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", sub.getSetorAbastecimento().getId());
				obj.append("descricao", sub.getSetorAbastecimento().getDescricao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	@SuppressWarnings("unchecked")
	public ActionForward atualizarListaDistritoOperacional(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(3,sessao);
		
		DynaValidatorForm form = 
				(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");
		
		String idSetorAbastecimento = (String)form.get("setorAbastecimento");
		
		
		FiltroDistritoSetorAbastecimento filtro = new FiltroDistritoSetorAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtro.adicionarParametro(new ParametroSimples("setorAbastecimento.id",idSetorAbastecimento));
		filtro.adicionarParametro(new ParametroSimples("distritoOperacional.indicadorUso",ConstantesSistema.SIM));
		

		Collection<DistritoSetorAbastecimento> colecaoSetor = 
				(Collection<DistritoSetorAbastecimento>)fachada.pesquisar(filtro,DistritoSetorAbastecimento.class.getName());
		
		Collection<DistritoOperacional> colTelaPrincipal = new ArrayList<DistritoOperacional>();
		Iterator<DistritoSetorAbastecimento> itTelaPrincipal =  colecaoSetor.iterator();
		while(itTelaPrincipal.hasNext()){
			DistritoSetorAbastecimento dist = itTelaPrincipal.next();
			colTelaPrincipal.add(dist.getDistritoOperacional());
		}
		
		sessao.setAttribute("colecaoDistritoOperacional", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<DistritoOperacional> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			DistritoOperacional dist = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", dist.getId());
				obj.append("descricao", dist.getDescricao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public ActionForward atualizarListaAreaOperacional(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(4,sessao);
		
		DynaValidatorForm form = 
				(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");
		
		String idDistritoOperacional = (String)form.get("distritoOperacional");
		
		Collection<AreaOperacionalHelper> colAreaOperacional 
			= fachada.pesquisarAreaOperacional(null, null, null, null, idDistritoOperacional, null, null);
		
		Collection<AreaOperacional> colTelaPrincipal = new ArrayList<AreaOperacional>();
		Iterator<AreaOperacionalHelper> itTelaPrincipal =  colAreaOperacional.iterator();
		while(itTelaPrincipal.hasNext()){
			AreaOperacionalHelper dist = itTelaPrincipal.next();
			AreaOperacional area = new AreaOperacional();
			area.setId(dist.getIdAreaOperacional());
			area.setDescricao(dist.getDescricaoAreaOperacional());
			colTelaPrincipal.add(area);
		}
		
		sessao.setAttribute("colecaoAreaOperacional", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<AreaOperacional> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			AreaOperacional area = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", area.getId());
				obj.append("descricao", area.getDescricao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward atualizarListaZonaPressao(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(5,sessao);
		
		DynaValidatorForm form = 
				(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");
		
		String idAreaOperacional = (String)form.get("areaOperacional");
		
		FiltroZonaAbastecimento filtro = new FiltroZonaAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("zonaPressao");
		filtro.adicionarParametro(new ParametroSimples("areaOperacional.id",idAreaOperacional));
		filtro.adicionarParametro(new ParametroSimples("zonaPressao.indicadorUso",ConstantesSistema.SIM));
		

		Collection<ZonaAreaOperacional> colecaoZona = 
				(Collection<ZonaAreaOperacional>)fachada.pesquisar(filtro,ZonaAreaOperacional.class.getName());
		
		Collection<ZonaPressao> colTelaPrincipal = new ArrayList<ZonaPressao>();
		Iterator<ZonaAreaOperacional> itTelaPrincipal =  colecaoZona.iterator();
		while(itTelaPrincipal.hasNext()){
			ZonaAreaOperacional zona = itTelaPrincipal.next();
			colTelaPrincipal.add(zona.getZonaPressao());
		}
		
		sessao.setAttribute("colecaoZonaPressao", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<ZonaPressao> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			ZonaPressao zona = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", zona.getId());
				obj.append("descricao", zona.getDescricaoZonaPressao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private void removerColecaoSessao(int metodo, HttpSession sessao){
		
		switch(metodo){
			case 1:
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
				
			case 2:
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 3:
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 4:
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 5:
				sessao.removeAttribute("colecaoZonaPressao");
		}
		
	}
	
}
