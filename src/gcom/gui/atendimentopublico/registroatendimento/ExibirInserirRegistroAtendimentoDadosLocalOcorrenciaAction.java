
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.FiltroUnidadeRepavimentadoraMunicipioBairros;
import gcom.atendimentopublico.UnidadeRepavimentadoraMunicipioBairros;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.CoordenadasCompGis;
import gcom.atendimentopublico.registroatendimento.FiltroCoordenadasCompGis;
import gcom.atendimentopublico.registroatendimento.FiltroLocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.PesquisarDocumentosHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da inserção de um R.A (Aba nº 02 - Dados do local
 * de ocorrência)
 * 
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ExibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction extends
        GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("inserirRegistroAtendimentoDadosLocalOcorrencia");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

        InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm = (InserirRegistroAtendimentoActionForm) actionForm;        
        
        UnidadeOrganizacional unidadeDestino = null;
        
        /*
         * @author Jonathan Marcos
         * @date 27/02/2014
         * RM10257
         * [OBSERVACOES]
         * 		- PESQUISAR O IMOVEL E VERIFICAR SE O 
         * 		  IMOVEL POSSUI COORDENADAS X E Y
         */
        Imovel imovelCoordenadasXY = null;
        if(inserirRegistroAtendimentoActionForm.getIdImovel()!=null && !inserirRegistroAtendimentoActionForm.getIdImovel().trim().equals("")){
        	imovelCoordenadasXY = fachada.pesquisarImovel(Integer.valueOf(inserirRegistroAtendimentoActionForm.getIdImovel()));
        	
        	if(imovelCoordenadasXY.getCoordenadaX()!=null){
        		inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte(imovelCoordenadasXY.getCoordenadaY().toString());
        	}
        	if(imovelCoordenadasXY.getCoordenadaY()!=null){
        		inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste(imovelCoordenadasXY.getCoordenadaX().toString());
        	}
        }
        
        /*
         * @author Jonathan Marcos
         * @date 27/02/2014
         * RM10257
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
        
        httpServletRequest.setAttribute("origemAcquaGIS", false);
        
        sessao.setAttribute("gis",true);        
        
        ArrayList<String> listContas = null;
        
        List<Conta> colecaoConta = new ArrayList();        

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        //Verificar Campos Numericos
        if(httpServletRequest.getParameter("limparImovel") == null || httpServletRequest.getParameter("limparImovel").equals("")){
	        if(inserirRegistroAtendimentoActionForm.getIdImovel() != null && !inserirRegistroAtendimentoActionForm.getIdImovel().equals("")){
	        	if(!Util.isInteger(inserirRegistroAtendimentoActionForm.getIdImovel())){
	        		throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Matricula do Imovel");
	        	}
	        }
        }
        
        if(httpServletRequest.getParameter("limparLocalidade") == null || httpServletRequest.getParameter("limparLocalidade").equals("")){
	        if(inserirRegistroAtendimentoActionForm.getIdLocalidade() != null && !inserirRegistroAtendimentoActionForm.getIdLocalidade().equals("")){
	        	if(!Util.isInteger(inserirRegistroAtendimentoActionForm.getIdLocalidade())){
	        		throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Localidade");
	        	}
	        }
        }
        
        /**
         * RM 6417
         * @author Fernanda Almeida
         * @date 15/01/2012
         * ==============================================================================================================    
         */
        // Verifica se foi retornado da tela de pesquisar contas retificacao
        if (sessao.getAttribute("contasSelecionadas") != null
                && !sessao.getAttribute("contasSelecionadas").equals("")){
            //colecaoConta = (List<Conta>) sessao.getAttribute("colecaoConta");
            listContas = (ArrayList<String>) sessao.getAttribute("contasSelecionadas");
            ArrayList<String> listContasExcluidas = new ArrayList<String>();             
            for(int i = listContas.size()-1;i>=0;i--){
                
                Conta conta = this.pesquisarConta(listContas.get(i),
                    inserirRegistroAtendimentoActionForm, 
                    sessao, 
                    usuarioLogado);
            
                if (conta != null) {
                    inserirRegistroAtendimentoActionForm.setIdConta("");
                    inserirRegistroAtendimentoActionForm.setDescConta("");
                    
                    if (!this.adicionado(colecaoConta, conta)) {
                        colecaoConta.add(conta);
                        sessao.setAttribute("colecaoConta", colecaoConta);
                    }else{
                        // Poe num array as contas repetidas que devem ser excluidas
                        // do arraylist de sessao 'contasSelecionadas'
                        String contasRepetidas = listContas.get(i);
                        listContasExcluidas.add(contasRepetidas);
                    }
                }
            }
            // exclui as contas repetidas
            for(int i=0;i<listContasExcluidas.size();i++){
                listContas.remove(listContasExcluidas.get(i));
                sessao.setAttribute("contasSelecionadas", listContas);
            }
        }
        
        
        if (sessao.getAttribute("colecaoConta") != null
                && !sessao.getAttribute("colecaoConta").equals("")){
            colecaoConta = (List<Conta>) sessao.getAttribute("colecaoConta");
        }
        
        GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");    
        
        if(gisHelper!= null){                
            
            /**
             * 
             * RM5236 - Abertura de RA Através do PROGIS
             * Tratamento das variáveis oriundas de chamadas GIS
             * 
             */
            if(gisHelper.getNnDiametro()!=null && !gisHelper.equals("")){
                inserirRegistroAtendimentoActionForm.setNnDiamentro(gisHelper.getNnDiametro());
            }
            
            
            inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte(gisHelper.getNnCoordenadaNorte());
            inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste(gisHelper.getNnCoordenadaLeste());            
            inserirRegistroAtendimentoActionForm.setIndicCoordenadaSemLogradouro("" + gisHelper.getIndicadorCoordenadaSemLogradouro());
            
            sessao.setAttribute("indicCoordenadaSemLogradouro", gisHelper.getIndicadorCoordenadaSemLogradouro());
            
            if (gisHelper.getIndicadorCoordenadaSemLogradouro() == ConstantesSistema.NAO){
                if(!"NAO".equalsIgnoreCase(gisHelper.getInformarCep())&& gisHelper.getInformarCep()!=null){                
                    inserirRegistroAtendimentoActionForm.setInformarCep("SIM");                
                
                }else{
                    inserirRegistroAtendimentoActionForm.setInformarCep("NAO");            
                    carregarDadosGis(gisHelper,inserirRegistroAtendimentoActionForm,sessao,fachada,httpServletRequest);
                }
            } else {
            	if ( gisHelper.getImovel() != null && gisHelper.getImovel().getId() != null ){
            		inserirRegistroAtendimentoActionForm.setIdImovel(gisHelper.getImovel().getId().toString());
            		sessao.setAttribute("temImovelGIS","SIM");
            	}
                gisHelper = null;                 
                sessao.removeAttribute("gisHelper");                
            }
            
            httpServletRequest.setAttribute("origemAcquaGIS", true);
           
        }else {
            if(sessao.getAttribute("indicCoordenadaSemLogradouro") == null) {
                sessao.setAttribute("indicCoordenadaSemLogradouro", ConstantesSistema.NAO);
            }
        }
        
        /*
         * Carregamento inicial da tela responsável pelo redebimento das
         * informações referentes ao local da ocorrência (ABA Nº 02)
         * ============================================================================================================
         */

        /*
         * Divisão de Esgoto - Carregando a coleção que irá ficar disponível
         * para escolha do usuário
         * 
         * [FS0003] - Verificar existência de dados
         */
        Collection colecaoDivisaoEsgoto = (Collection) sessao.getAttribute("colecaoDivisaoEsgoto");

        if (colecaoDivisaoEsgoto == null) {

            FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto(
                    FiltroDivisaoEsgoto.DESCRICAO);

            filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
                    FiltroDivisaoEsgoto.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            filtroDivisaoEsgoto.setConsultaSemLimites(true);

            colecaoDivisaoEsgoto = fachada.pesquisar(filtroDivisaoEsgoto,
                    DivisaoEsgoto.class.getName());

            if (colecaoDivisaoEsgoto == null || colecaoDivisaoEsgoto.isEmpty()) {
                throw new ActionServletException(
                        "atencao.entidade_sem_dados_para_selecao", null,
                        "DIVISAO_ESGOTO");
            } else {
                sessao.setAttribute("colecaoDivisaoEsgoto", colecaoDivisaoEsgoto);
            }
        }

        /*
         * Local de Ocorrência - Carregando a coleção que irá ficar disponível
         * para escolha do usuário
         * 
         * [FS0003] - Verificar existência de dados
         */
        Collection colecaoLocalOcorrencia = (Collection) sessao.getAttribute("colecaoLocalOcorrencia");

        if (colecaoLocalOcorrencia == null) {

            FiltroLocalOcorrencia filtroLocalOcorrencia = new FiltroLocalOcorrencia(
                    FiltroLocalOcorrencia.DESCRICAO);

            filtroLocalOcorrencia.adicionarParametro(new ParametroSimples(
                    FiltroLocalOcorrencia.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            filtroLocalOcorrencia.setConsultaSemLimites(true);

            colecaoLocalOcorrencia = fachada.pesquisar(filtroLocalOcorrencia,
                    LocalOcorrencia.class.getName());

            if (colecaoLocalOcorrencia == null || colecaoLocalOcorrencia.isEmpty()) {
                throw new ActionServletException(
                        "atencao.entidade_sem_dados_para_selecao", null,
                        "LOCAL_OCORRENCIA");
            } else {
                sessao.setAttribute("colecaoLocalOcorrencia", colecaoLocalOcorrencia);
            }
        }

        /*
         * Pavimento Rua - Carregando a coleção que irá ficar disponível para
         * escolha do usuário
         * 
         * [FS0003] - Verificar existência de dados
         */
        Collection colecaoPavimentoRua = (Collection) sessao
                .getAttribute("colecaoPavimentoRua");

        if (colecaoPavimentoRua == null) {

            FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua(
                    FiltroPavimentoRua.DESCRICAO);

            filtroPavimentoRua.adicionarParametro(new ParametroSimples(
                    FiltroPavimentoRua.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            filtroPavimentoRua.setConsultaSemLimites(true);

            colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua,
                    PavimentoRua.class.getName());

            if (colecaoPavimentoRua == null || colecaoPavimentoRua.isEmpty()) {
                throw new ActionServletException(
                        "atencao.entidade_sem_dados_para_selecao", null,
                        "PAVIMENTO_RUA");
            } else {
                sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
            }
        }

        /*
         * Pavimento Calçada - Carregando a coleção que irá ficar disponível
         * para escolha do usuário
         * 
         * [FS0003] - Verificar existência de dados
         */
        Collection colecaoPavimentoCalcada = (Collection) sessao
                .getAttribute("colecaoPavimentoCalcada");

        if (colecaoPavimentoCalcada == null) {

            FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada(
                    FiltroPavimentoCalcada.DESCRICAO);

            filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
                    FiltroPavimentoCalcada.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            filtroPavimentoCalcada.setConsultaSemLimites(true);

            colecaoPavimentoCalcada = fachada.pesquisar(filtroPavimentoCalcada,
                    PavimentoCalcada.class.getName());

            if (colecaoPavimentoCalcada == null
                    || colecaoPavimentoCalcada.isEmpty()) {
                throw new ActionServletException(
                        "atencao.entidade_sem_dados_para_selecao", null,
                        "PAVIMENTO_CALCADA");
            } else {
                sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
            }
        }

        // [SB0002] - Habilita/Desabilita Município, Bairro, Área do Bairro e
        // Divisão de Esgoto
        ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = fachada
            .habilitarGeograficoDivisaoEsgoto(new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()));
        
        
        String pesquisarImovel = httpServletRequest.getParameter("pesquisarImovel");
        

        if (habilitaGeograficoDivisaoEsgoto != null) {
            if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua()) {
                sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua", "SIM");
                
                //Verificar carregamento do Município e Bairro de acordo com o tipo de solicitação
                if (inserirRegistroAtendimentoActionForm.getIdImovel() != null
                    && !inserirRegistroAtendimentoActionForm.getIdImovel().equalsIgnoreCase("")
                    && (pesquisarImovel == null || pesquisarImovel.equalsIgnoreCase(""))){
                    
                    ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = fachada
                    .obterDadosIdentificacaoLocalOcorrencia(
                            new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()), 
                            new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                            new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()), false);
                    
                    this.carregarMunicipioBairroParaImovel(
                            habilitaGeograficoDivisaoEsgoto, dadosIdentificacaoLocalOcorrencia,
                            inserirRegistroAtendimentoActionForm, sessao, fachada);
                }
                
            } else {
                sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua", "NAO");
            }

            if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()) {
                sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
            } else {
                sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "NAO");
            }
        } else {
            sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua", "SIM");
            sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
        }
        
        // [SB0036] - Habilita/Desabilita Conta
        short indicadorInformarPagamentoDuplicidade = ConstantesSistema.NAO;
        short indicadorDocPagosValorMaiorRA = ConstantesSistema.NAO;
        short indicadorDocPagosValorCobradosIndev = ConstantesSistema.NAO;
        
        if (inserirRegistroAtendimentoActionForm.getTipoSolicitacao() != null
            && !inserirRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")
            && !inserirRegistroAtendimentoActionForm.getEspecificacao().equalsIgnoreCase(
                    String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
            
            FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
            
            filtroSolicitacaoTipoEspecificacao.adicionarParametro(
                    new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
                            new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao())));
            
            filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(
            		FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
            
//            filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
//                    FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_CONTA_RA,
//                    ConstantesSistema.SIM));
            
            Collection colecao = fachada.pesquisar(
                    filtroSolicitacaoTipoEspecificacao,
                    SolicitacaoTipoEspecificacao.class.getName());
            
            if (colecao != null && !colecao.isEmpty()) {
                
                SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
                
                indicadorInformarPagamentoDuplicidade = especificacao.getIndicadorInformarPagamentoDuplicidade();
                indicadorDocPagosValorMaiorRA = especificacao.getIndicadorInformarDocumentoPagamentoAMaior();
                indicadorDocPagosValorCobradosIndev = especificacao.getIndicadorInformarDocumentoPagamentoIndevido();
                
                if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()){
                	 sessao.removeAttribute("colecaoPagValorMaiorDocumento");
                     sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");
                }
                else if(indicadorDocPagosValorMaiorRA == ConstantesSistema.SIM.shortValue()){
                	sessao.removeAttribute("colecaoPagamentosDuplicidade");
                	sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");
                }
                else if(indicadorDocPagosValorCobradosIndev == ConstantesSistema.SIM.shortValue()){
                	sessao.removeAttribute("colecaoPagamentosDuplicidade");
                	sessao.removeAttribute("colecaoPagValorMaiorDocumento");
                }
                
                short indicadorInformarRaConta = especificacao.getIndicadorInformarContaRA();
                
                if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.NAO.shortValue() && 
                    indicadorInformarRaConta == ConstantesSistema.SIM.shortValue()){
                    
                    sessao.setAttribute("conta", "habilita");    
                }else{
                    sessao.removeAttribute("conta");
                }
                
                if(especificacao.getServicoTipo() != null){
                	inserirRegistroAtendimentoActionForm.setIndicadorPavimento(String.valueOf(
                			especificacao.getServicoTipo().getIndicadorPavimento()));
                }else{
                	inserirRegistroAtendimentoActionForm.setIndicadorPavimento(""+ConstantesSistema.NAO);
                }
                
            } else {
                sessao.removeAttribute("conta");
            }
        }
        
        // 4. E para esses três indicadores de devolução, o sistema exibe uma tabela ("Contas") com as contas em aberto para o imóvel:
        if(inserirRegistroAtendimentoActionForm.getIdImovel() != null
        		&& !inserirRegistroAtendimentoActionForm.getIdImovel().trim().equals("") 
        		&& (indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()
	        		|| indicadorDocPagosValorMaiorRA == ConstantesSistema.SIM.shortValue()
	        		|| indicadorDocPagosValorCobradosIndev == ConstantesSistema.SIM.shortValue())){
        	
        	Date dataVencimentoInicial = Util.criarData(1, 1, 0001);
    		Date dataVencimentoFinal = Util.criarData(31, 12, 9999);

    		// [UC0067] Obter Débito do Imóvel ou Cliente
    		ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = Fachada.getInstancia()
				.obterDebitoImovelOuCliente(1, // indicadorDebito
					inserirRegistroAtendimentoActionForm.getIdImovel(), // idImovel
					null, // codigoCliente
					null, // clienteRelacaoTipo
					"000101", // anoMesInicialReferenciaDebito
					"999912", // anoMesFinalReferenciaDebito
					dataVencimentoInicial, // anoMesInicialVencimentoDebito
					dataVencimentoFinal, // anoMesFinalVencimentoDebito
					1, // indicadorPagamento
					2, // indicadorContaEmRevisao
					2, // indicadorDebitoACobrar
					2, // indicadorCreditoARealizar
					2, // indicadorNotasPromissorias
					2, // indicadorGuiasPagamento
					2, // indicadorCalcularAcrescimoImpontualidade
					true);// indicadorContas

    		// CONTA
    		if (obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel() != null
    				&& !obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel().isEmpty()) {

    			sessao.setAttribute("colecaoContaValoresImovel", obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel());
    			
    		} else {
    			sessao.removeAttribute("colecaoContaValoresImovel");
    		}
    		
        } else {
			sessao.removeAttribute("colecaoContaValoresImovel");
        }
        
        /*
         * Dados da identificação do local de ocorrência
         * 
         * [FS0019] - Verificar endereço do imóvel
         * 
         */
        
        if ((pesquisarImovel != null && !pesquisarImovel.equalsIgnoreCase("")) || sessao.getAttribute("temImovelGIS") != null ) {
            
            sessao.removeAttribute("colecaoConta");
            sessao.removeAttribute("contasSelecionadas");
            sessao.removeAttribute("colecaoPagamentosDuplicidade");
            sessao.removeAttribute("colecaoPagValorMaiorDocumento");
            sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");


            /*
             * [SB0004] - Obtém e Habilita/Desabilita Dados da Identificação do
             * Local da Ocorrência e Dados do Solicitante
             * 
             * [FS0019] - Verificar endereço do imóvel 
             * [FS0020] - Verificar existência de registro de atendimento para o imóvel com a mesma
             * especificação
             * 
             * [SB0020] - Verifica Situação do Imóvel e Especificação
             * 
             * [SB0021] - Verifica Existência de Registro de Atendimento Pendente para o Imóvel
             * 
             * [SB0032 - Verifica se o imóvel informado tem débito]
             * 
             * [SB0038]- Verificar instalação/substituição de hidrômetro para imóvel em situação especial de faturamento
             * 
             */
            ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
                fachada.obterDadosIdentificacaoLocalOcorrencia(
                        new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()), 
                        new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                        new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()), false);

            if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

                boolean msgAlert = false;

                // [SB0021] - Verifica Existência de Registro de Atendimento
                // Pendente para o Imóvel
                boolean raPendente = fachada.verificaExistenciaRAPendenteImovel(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getId());

                if (raPendente) {
                    httpServletRequest.setAttribute(
                                    "msgAlert",
                                    "Atenção! "
                                            + "Existe Registro de Atendimento pendente para o imóvel "
                                            + dadosIdentificacaoLocalOcorrencia
                                                    .getImovel().getId()
                                                    .toString());
                    msgAlert = true;
                }
                
                //[SB0020] - Verifica Situação do Imóvel e Especificação
                fachada.verificarSituacaoImovelEspecificacao(dadosIdentificacaoLocalOcorrencia.getImovel(),
                new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()));
                
                
                //[SB0032] - Verifica se o imóvel informado tem débito
                fachada.verificarImovelComDebitos(new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                dadosIdentificacaoLocalOcorrencia.getImovel().getId());
                
                //[FS0054] Verificar se o imóvel informado tem cliente usuário desconhecido
                if(    dadosIdentificacaoLocalOcorrencia.getImovel().getId() != null &&
                        !dadosIdentificacaoLocalOcorrencia.getImovel().equals("") &&
                        inserirRegistroAtendimentoActionForm.getTipoSolicitacao() != null && 
                        !inserirRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")){
                    
                fachada.verificarImovelClienteDesconhecido(dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
                                    new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()));
                
                } 
                
                //[SB0038]- Verificar instalação/substituição de hidrômetro para imóvel em situação especial de faturamento
                //=========================================================================================================
                //1. Caso tenha sido informado o parâmetro para bloquear instalação/substituição de hidrômetro
                if(sistemaParametro.getIndicadorBloqFuncInstalacaoSubtHidrometro().compareTo(ConstantesSistema.SIM) == 0){
                	
                	
                	 FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
                     
                     filtroSolicitacaoTipoEspecificacao.adicionarParametro(
                             new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
                                     new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao())));
                     Collection colecao = fachada.pesquisar(
                             filtroSolicitacaoTipoEspecificacao,
                             SolicitacaoTipoEspecificacao.class.getName());
                     
                     if (colecao != null && !colecao.isEmpty()) {
                         
                         SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
                         
                         boolean informouInstalacaoSubsHidr = fachada
                        		 .verificarInstalacaoSubstituicaoHidr(new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()));
                         FaturamentoSituacaoTipo imovelSitEspecialFat = fachada
                        		 .obterFaturamentoSituacaoTipoImovel(dadosIdentificacaoLocalOcorrencia.getImovel().getId());
                         
                         
                         //1.1. Caso tenha sido informada instalação ou substituição de hidrômetro
                         //     e o imóvel esteja em situação especial de faturamento
                         if(informouInstalacaoSubsHidr 
                        		 && imovelSitEspecialFat != null 
                        		 && imovelSitEspecialFat.getId().compareTo(FaturamentoSituacaoTipo.FATURAR_MAIOR_ENTRE_MEDIA_E_CONSUMO_FIXO) == 0){
                        	 
                        	 //1.1. o sistema deve exibir a mensagem "Não é permitido <<Descrição da Solicitação Tipo Especificação>> 
                        	 //     para o imóvel informado. O mesmo está na situação especial de faturamento 
                        	 //     << FTST_DSFATURAMENTOSITUACAOTIPO da tabela FATURAMENTO.FATUR_SITUACAO_TIPO >>"
                        	 throw new ActionServletException("atencao.solicitacao_nao_permitida_sit_especial", 
									 						  especificacao.getDescricao(), 
									 						  imovelSitEspecialFat.getDescricao());
                        	 
                         }
                     }
                }
                //=========================================================================================================

                inserirRegistroAtendimentoActionForm.setIdImovel(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getId().toString());

                inserirRegistroAtendimentoActionForm.setInscricaoImovel(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getInscricaoFormatada());
                
                /**
                 * Coordenada X deve ser informada como coordenada Leste, assim como a coordenada Y deve ser
                 * informada como coordenada norte. Solicitação feita por Giovani.
                 * 
                 * @author Arthur Carvalho
                 * @date 12/08/2010
                 */
                if( dadosIdentificacaoLocalOcorrencia.getImovel().getCoordenadaX() !=null ){
                	inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste(
                		dadosIdentificacaoLocalOcorrencia.getImovel().getCoordenadaX().toString());
                } else {
                	inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste( "" );
                }
                
                if( dadosIdentificacaoLocalOcorrencia.getImovel().getCoordenadaY() !=null ){
                	inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getCoordenadaY().toString());
                } else {
                	inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte( "" );
                }

                if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
                    
                    Collection colecaoEnderecos = new ArrayList();
                    colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia.getImovel());
                    
                    sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);    
                    sessao.setAttribute("enderecoPertenceImovel", "OK");
                    
                } else if (dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo() != null) {
                    
                    inserirRegistroAtendimentoActionForm.setDescricaoLocalOcorrencia(
                            dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo());

                    if (msgAlert) {
                        httpServletRequest
                                .setAttribute(
                                        "msgAlert2",
                                        "O Registro de Atendimento ficará bloqueado até "
                                                + " que seja informado o logradouro para o imóvel");
                    } else {
                        httpServletRequest
                                .setAttribute(
                                        "msgAlert",
                                        "O Registro de Atendimento ficará bloqueado até "
                                                + " que seja informado o logradouro para o imóvel");
                    }

                    sessao.removeAttribute("colecaoEnderecos");
                    sessao.removeAttribute("enderecoPertenceImovel");
                } else {
                    sessao.removeAttribute("colecaoEnderecos");
                    sessao.removeAttribute("enderecoPertenceImovel");
                }
                
                if (dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco()) {
                    sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
                } else {
                    sessao.setAttribute("habilitarAlteracaoEndereco", "NAO");
                }
                
                if(dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco() 
                        && dadosIdentificacaoLocalOcorrencia.isInformarEndereco()){
                    
                    sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
                }

                this.carregarMunicipioBairroParaImovel(
                        habilitaGeograficoDivisaoEsgoto, dadosIdentificacaoLocalOcorrencia,
                        inserirRegistroAtendimentoActionForm, sessao, fachada);

                Municipio municipio = dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getMunicipio(); 
                if(municipio != null){
                    inserirRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
                    httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
                }
                
                inserirRegistroAtendimentoActionForm.setIdLocalidade(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getId().toString());

                inserirRegistroAtendimentoActionForm.setDescricaoLocalidade(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getDescricao());

                inserirRegistroAtendimentoActionForm.setIdSetorComercial(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getId().toString());

                inserirRegistroAtendimentoActionForm.setCdSetorComercial(
                        String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getCodigo()));

                inserirRegistroAtendimentoActionForm.setDescricaoSetorComercial(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getDescricao());

                inserirRegistroAtendimentoActionForm.setIdQuadra(
                        String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getId()));
                
                inserirRegistroAtendimentoActionForm.setNnQuadra(
                        String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getNumeroQuadra()));
                               
                DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(
						dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getId(),
						habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());
                
                // [SB0006] - Obtém Divisão de Esgoto Quadra Imovel
                DivisaoEsgoto divisaoEsgotoQuadra = fachada.obterDivisaoEsgotoQuadra(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getId());
                
                // [SB0006] - Obtém Divisão de Esgoto Localidade
                DivisaoEsgoto divisaoEsgotoImovelLocalidade = fachada.obterDivisaoEsgotoImovelLocalidade(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getId());
                
                if ( sistemaParametro.getIndicadorTramitarRAEsgoto() != null
   	                 && sistemaParametro.getIndicadorTramitarRAEsgoto().equals(ConstantesSistema.SIM) && habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()) {                  	
                
	                if(divisaoEsgotoQuadra != null ){
	                    inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgotoQuadra.getId().toString());
	                   
	                }else if (divisaoEsgotoImovelLocalidade != null){
	                    inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgotoImovelLocalidade.getId().toString());
	                    
	                }else{
	                	    
	                	 inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
	                }
                
                }else{
                	if(divisaoEsgoto != null)
                	inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(""+divisaoEsgoto.getId());
                }
                
                // [SB0006] 1.1
                if( sistemaParametro.getIndicadorTramitarRAEsgoto() != null
      	                 && sistemaParametro.getIndicadorTramitarRAEsgoto().equals(ConstantesSistema.SIM)){
                                    
                    if(divisaoEsgotoQuadra != null ){
                        
                        sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                        sessao.setAttribute("desabilitaDivisao", "SIM");
                    }else if (divisaoEsgotoImovelLocalidade != null){
                        
                        sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                        sessao.setAttribute("desabilitaDivisao", "SIM");
                    }else{
                    	 sessao.setAttribute("desabilitaDivisao", "NAO");    
                    	 
                    }
                }else if(!habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()){           
	                	sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
	                    sessao.setAttribute("desabilitaDivisao", "SIM");
                       
                }else{
                	 sessao.removeAttribute("desabilitarDivisaoEsgoto");
                     sessao.setAttribute("desabilitaDivisao", "NAO");
                }
                
                // [SB0007] - Define Unidade Destino da Divisão de Esgoto    
                
            	 Integer idDivisaoEsgoto = null;
            	 if(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto() != null){
	                 if(!inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto().equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
	                 	idDivisaoEsgoto = Integer.valueOf(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto());
	                 }   
            	 }
                                 
            	 unidadeDestino = fachada.definirUnidadeDestinoTramiteImovel(new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                        new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()), 
                        new Integer(inserirRegistroAtendimentoActionForm.getIdLocalidade()),
                        idDivisaoEsgoto,
                        new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()),
                        habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());            	 
               
            	 
                if (inserirRegistroAtendimentoActionForm.getIdQuadra() != null
                		&& !inserirRegistroAtendimentoActionForm.getIdQuadra().equals("")) {
                	
                	
                	if(divisaoEsgoto != null){
                        inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgoto.getId().toString());
                        sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                        sessao.setAttribute("desabilitaDivisao", "SIM");                        
                	}
                  
                }
                else{
                    sessao.removeAttribute("desabilitarDivisaoEsgoto");
                }
    
                if (unidadeDestino != null) {
                    inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
                    inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
                }
//                }else{
//                	inserirRegistroAtendimentoActionForm.setIdUnidadeDestino("");
//                    inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino("");
//                }
                                            
                           
                
                if(divisaoEsgoto == null && divisaoEsgotoImovelLocalidade == null && divisaoEsgotoQuadra ==null){
                    sessao.setAttribute("desabilitaDivisao", "NAO");    
                    inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                }

                if (dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoRua() != null) {
                    inserirRegistroAtendimentoActionForm.setIdPavimentoRua(
                        dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoRua().getId().toString());
                    sessao.setAttribute("desabilitarPavimentoRua", "OK");
                } else {
                    sessao.removeAttribute("desabilitarPavimentoRua");
                }

                if (dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoCalcada() != null) {
                    inserirRegistroAtendimentoActionForm.setIdPavimentoCalcada(
                            dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoCalcada().getId().toString());
                    sessao.setAttribute("desabilitarPavimentoCalcada", "OK");
                } else {
                    sessao.removeAttribute("desabilitarPavimentoCalcada");
                }
                
                sessao.setAttribute("desabilitarLcalidadeSetorQuadra", "OK");
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");

            } else {

                inserirRegistroAtendimentoActionForm.setIdImovel("");
                inserirRegistroAtendimentoActionForm.setInscricaoImovel("Imóvel Inexistente");
                
                sessao.removeAttribute("colecaoEnderecos");
                sessao.removeAttribute("enderecoPertenceImovel");
                sessao.removeAttribute("desabilitarDivisaoEsgoto");
                sessao.removeAttribute("desabilitarPavimentoRua");
                sessao.removeAttribute("desabilitarPavimentoCalcada");
                sessao.removeAttribute("desabilitarLcalidadeSetorQuadra");
                
                httpServletRequest.setAttribute("corImovel", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idImovel");
            }
            
            sessao.removeAttribute("temImovelGIS");
            
            //Caso o indicadorInformarPagamentoDuplicidade = 1, exibe todos os pagamentos em duplicidade.
            if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()){
                
                if(inserirRegistroAtendimentoActionForm.getIdImovel() != null && !inserirRegistroAtendimentoActionForm.getIdImovel().equals("")){
                    
                    Collection colecaoPagamento = 
                        this.pesquisaPagamentosEmDuplicidade(
                            new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()));
                    
                    if(colecaoPagamento != null && 
                        !colecaoPagamento.isEmpty()){
                        
                        sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoPagamento);                  
                            
                    }else{
                        throw new ActionServletException("atencao.nao_exite_pagamento_duplicidade");
                    }
                }
            }
            
            //2. Caso o indicador "Informar Documentos Pagos com Valor a maior no Registro de Atendimento" esteja com sim
            if(indicadorDocPagosValorMaiorRA == ConstantesSistema.SIM.shortValue()){
            	
            	if(inserirRegistroAtendimentoActionForm.getIdImovel() != null && !inserirRegistroAtendimentoActionForm.getIdImovel().equals("")){
            		
    	        	//[FS0059] - Verificar pagamento a maior para imóvel informado
    	        	this.verificarPagamentoMaiorImovelInformado(inserirRegistroAtendimentoActionForm.getIdImovel());
    	        	
    	        	//2.1. O sistema exibe uma tabela ("Pagamentos com Valor a Maior que o Documento") com os pagamentos selecionados
    	        	Collection colecaoPagValorMaiorDocumento = fachada.pesquisarPagamentosValorMaiorDocumento(new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()));
    	        	
    	        	sessao.setAttribute("colecaoPagValorMaiorDocumento",colecaoPagValorMaiorDocumento);  
    	        	
            	}	
            }
        }

        
        
        String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");

        if (pesquisarMunicipio != null && !pesquisarMunicipio.equalsIgnoreCase("")) {

            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
                    inserirRegistroAtendimentoActionForm.getIdMunicipio()));

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

            if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setIdMunicipio("");
                inserirRegistroAtendimentoActionForm.setDescricaoMunicipio("Município Inexistente");

                httpServletRequest.setAttribute("corMunicipio", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

            } else {
                Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

                inserirRegistroAtendimentoActionForm.setIdMunicipio(municipio.getId().toString());
                inserirRegistroAtendimentoActionForm.setDescricaoMunicipio(municipio.getNome());

                httpServletRequest.setAttribute("nomeCampo", "cdBairro");
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }

        String pesquisarBairro = httpServletRequest.getParameter("pesquisarBairro");

        if (pesquisarBairro != null && !pesquisarBairro.equalsIgnoreCase("")) {

            FiltroBairro filtroBairro = new FiltroBairro();

            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.CODIGO, inserirRegistroAtendimentoActionForm.getCdBairro()));

            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.MUNICIPIO_ID, inserirRegistroAtendimentoActionForm.getIdMunicipio()));

            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());

            if (colecaoBairro == null || colecaoBairro.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setCdBairro("");
                inserirRegistroAtendimentoActionForm.setDescricaoBairro("Bairro Inexistente");

                httpServletRequest.setAttribute("corBairro", "exception");
                httpServletRequest.setAttribute("nomeCampo", "cdBairro");

            } else {
                Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

                inserirRegistroAtendimentoActionForm.setCdBairro(String.valueOf(bairro.getCodigo()));
                inserirRegistroAtendimentoActionForm.setDescricaoBairro(bairro.getNome());

                this.pesquisarBairroArea(bairro.getId(), fachada, sessao);
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }

        String pesquisarBairroArea = httpServletRequest.getParameter("pesquisarBairroArea");

        if (pesquisarBairroArea != null && !pesquisarBairroArea.equalsIgnoreCase("")) {
            
            this.pesquisarBairroArea(new Integer(inserirRegistroAtendimentoActionForm
            .getIdBairro()), fachada, sessao);
            
            httpServletRequest.setAttribute("nomeCampo", "idBairroArea");
            
            httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
        }

        String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");

        if (pesquisarLocalidade != null    && !pesquisarLocalidade.equalsIgnoreCase("")) {

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, inserirRegistroAtendimentoActionForm.getIdLocalidade()));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
            
            Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setIdLocalidade("");
                inserirRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");

                httpServletRequest.setAttribute("corLocalidade", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idLocalidade");

            } else {
                Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
                
                // [SB0006] - Obtém Divisão de Esgoto Localidade
                DivisaoEsgoto divisaoEsgotoLocalidade = fachada.obterDivisaoEsgotoLocalidade(
                    localidade.getId());
                
                if(sistemaParametro.getIndicadorTramitarRAEsgoto().equals(ConstantesSistema.SIM)){
                	if( habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()){
                		 if(divisaoEsgotoLocalidade != null && habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()){
                    		 inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgotoLocalidade.getId().toString());
                             sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                             sessao.setAttribute("desabilitaDivisao", "SIM");
                    		 
                    	 }else if(!habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto() && divisaoEsgotoLocalidade != null){
                         	inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgotoLocalidade.getId().toString());
                            sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                            sessao.setAttribute("desabilitaDivisao", "SIM");
                    	 } else if(divisaoEsgotoLocalidade == null){
                    		 inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
                             sessao.setAttribute("desabilitaDivisao", "NAO");
                        }else{
                        	 inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgotoLocalidade.getId().toString());
                             sessao.removeAttribute("desabilitarDivisaoEsgoto");
                             sessao.setAttribute("desabilitaDivisao", "NAO");
                        }
                	}
                }else{
                	inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
                    sessao.removeAttribute("desabilitarDivisaoEsgoto");
                    sessao.setAttribute("desabilitaDivisao", "NAO");
                }
                       
                inserirRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
                inserirRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());

                httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");

                this.getFachada().pesquisarParametrosDoSistema();
                
                Integer idDivisaoEsgoto = null;
                if(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto() != null && !inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto().equals("") ){
	                if(!inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
	                	idDivisaoEsgoto = Integer.valueOf(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto());
	                }
                }
              
               unidadeDestino = fachada.definirUnidadeDestinoLocalidadeTramite( new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                       new Integer(inserirRegistroAtendimentoActionForm.getIdLocalidade()), 
                       idDivisaoEsgoto,
                       new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()),
                       habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()); 
                                

                if (unidadeDestino != null) {
                    inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
                    inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
                }else{
                	inserirRegistroAtendimentoActionForm.setIdUnidadeDestino("");
                    inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino("");
                }
                
                Municipio municipio = localidade.getMunicipio();
                if(municipio != null){
                    inserirRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
                    httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
                }
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }

        String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");

        if (pesquisarSetorComercial != null && !pesquisarSetorComercial.equalsIgnoreCase("")) {

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE,inserirRegistroAtendimentoActionForm.getIdLocalidade()));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    inserirRegistroAtendimentoActionForm.getCdSetorComercial()));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection colecaoSetorComercial = fachada.pesquisar(
                    filtroSetorComercial, SetorComercial.class.getName());

            if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setIdSetorComercial("");
                inserirRegistroAtendimentoActionForm.setCdSetorComercial("");
                inserirRegistroAtendimentoActionForm.setDescricaoSetorComercial("Setor Comercial Inexistente");

                httpServletRequest.setAttribute("corSetorComercial", "exception");
                httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");

            } else {
                SetorComercial setorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoSetorComercial);

                inserirRegistroAtendimentoActionForm.setIdSetorComercial(setorComercial.getId().toString());
                inserirRegistroAtendimentoActionForm.setCdSetorComercial(String.valueOf(setorComercial.getCodigo()));
                inserirRegistroAtendimentoActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());

                httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }

        String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");

        if (pesquisarQuadra != null && !pesquisarQuadra.equalsIgnoreCase("")) {

            FiltroQuadra filtroQuadra = new FiltroQuadra();

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL,    inserirRegistroAtendimentoActionForm.getIdSetorComercial()));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA,    inserirRegistroAtendimentoActionForm.getNnQuadra()));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

            if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setIdQuadra("");
                inserirRegistroAtendimentoActionForm.setNnQuadra("");

                httpServletRequest.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
                httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

            } else {
                Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

                inserirRegistroAtendimentoActionForm.setIdQuadra(quadra.getId().toString());
                inserirRegistroAtendimentoActionForm.setNnQuadra(String.valueOf(quadra.getNumeroQuadra()));

                // [SB0006] - Obtém Divisão de Esgoto
                DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(quadra.getId(), 
                        habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

                if (divisaoEsgoto != null ) {
                    inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgoto.getId().toString());
                    sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                    sessao.setAttribute("desabilitaDivisao", "SIM");
                    /*
                     * [FS0013] - Verificar compatibilidade entre divisão de
                     * esgoto e localidade/setor/quadra [SB0007] - Define
                     * Unidade Destino da Divisão de Esgoto
                     */
                    unidadeDestino = this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
                                    fachada, inserirRegistroAtendimentoActionForm,
                                    habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

                    httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");
                } else if(!habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()){
                	 sessao.setAttribute("desabilitarDivisaoEsgoto", "OK");
                     sessao.setAttribute("desabilitaDivisao", "SIM");
                }else{
                    inserirRegistroAtendimentoActionForm.setIdDivisaoEsgoto(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                	sessao.removeAttribute("desabilitarDivisaoEsgoto");
                    sessao.setAttribute("desabilitaDivisao", "NAO");
                    httpServletRequest.setAttribute("nomeCampo", "idDivisaoEsgoto");
                }
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }

        String pesquisarUnidadeDestino = httpServletRequest.getParameter("pesquisarUnidadeDestino");

        if (pesquisarUnidadeDestino != null && !pesquisarUnidadeDestino.equalsIgnoreCase("")) {

            FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();

            filtroUnidadeDestino.adicionarParametro(new ParametroSimples(
                FiltroUnidadeOrganizacional.ID,    inserirRegistroAtendimentoActionForm.getIdUnidadeDestino()));

            Collection<?> colecaoUnidadeDestino = fachada.pesquisar(
                    filtroUnidadeDestino, UnidadeOrganizacional.class.getName());

            if (colecaoUnidadeDestino == null || colecaoUnidadeDestino.isEmpty()) {

                inserirRegistroAtendimentoActionForm.setIdUnidadeDestino("");
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino("Unidade Destino Inexistente");

                httpServletRequest.setAttribute("corUnidadeDestino", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");

            } else {
                unidadeDestino = (UnidadeOrganizacional) Util
                        .retonarObjetoDeColecao(colecaoUnidadeDestino);
                
                // [FS0021] - Verificar possibilidade de encaminhamento para a
                // unidade destino
                fachada.verificaPossibilidadeEncaminhamentoUnidadeDestino(unidadeDestino);

                inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());                                
    
                httpServletRequest.setAttribute("nomeCampo", "parecerUnidadeDestino");
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
            }
        }
        
        /*
         * Dados de repavimentação
         * 
         * @author Davi Menezes
         * @date 02/08/2013
         */
        if(inserirRegistroAtendimentoActionForm.getIndicadorPavimento() != null 
        		&& inserirRegistroAtendimentoActionForm.getIndicadorPavimento().equals(""+ServicoTipo.INDICADOR_PAVIMENTO_RUA_SIM)){
        	
        	FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional(FiltroUnidadeOrganizacional.DESCRICAO);
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			//Unidade Tipo R = REPAVIMENTADORA.
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R" ) );
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_TIPO);

			Collection colecaoUnidadeOrgazanicional = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());

			sessao.setAttribute("colecaoUnidadeOrgazanicional", colecaoUnidadeOrgazanicional);
				
			if(inserirRegistroAtendimentoActionForm.getIdBairro() != null && !inserirRegistroAtendimentoActionForm.getIdBairro().equals("")){
				FiltroUnidadeRepavimentadoraMunicipioBairros filtroUnidadeRepavimentadoraMunicipioBairros = new FiltroUnidadeRepavimentadoraMunicipioBairros();
				
				filtroUnidadeRepavimentadoraMunicipioBairros.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipioBairros.ID_BAIRRO, 
						inserirRegistroAtendimentoActionForm.getIdBairro()));
				filtroUnidadeRepavimentadoraMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.BAIRRO);
				filtroUnidadeRepavimentadoraMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.UNIDADE_REPAVIMENTADORA_MUNICIPIO);
				filtroUnidadeRepavimentadoraMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.UNIDADE_REPAVIMENTADORA);
				
				Collection<?> colecaoUnidRepMunBairros = fachada.pesquisar(filtroUnidadeRepavimentadoraMunicipioBairros, UnidadeRepavimentadoraMunicipioBairros.class.getName());
				if(!Util.isVazioOrNulo(colecaoUnidRepMunBairros)){
					UnidadeRepavimentadoraMunicipioBairros unidRepavMuniBairros = (UnidadeRepavimentadoraMunicipioBairros) 
							Util.retonarObjetoDeColecao(colecaoUnidRepMunBairros);
					
					inserirRegistroAtendimentoActionForm.setIdUnidadeRepavimentadora(unidRepavMuniBairros.getComp_id()
							.getUnidadeRepavimentadoraMunicipioId().getUnidadeRepavimentadora().getId().toString());
				}
			}
		}else{
			inserirRegistroAtendimentoActionForm.setIdPavimentoRuaEnviado("-1");
			inserirRegistroAtendimentoActionForm.setMetragemPavimentoRua("");
			inserirRegistroAtendimentoActionForm.setIdUnidadeRepavimentadora("-1");
		}
        
        /*
         * ==================
         * Adicionar Conta
         * ==================
         */
        String adicionarConta = httpServletRequest.getParameter("adicionarConta");

        if (adicionarConta != null && !adicionarConta.equalsIgnoreCase("")
                && inserirRegistroAtendimentoActionForm.getIdConta() != null
                && !inserirRegistroAtendimentoActionForm.getIdConta().equals("")) {
            
            Conta conta = this.pesquisarConta(
                    inserirRegistroAtendimentoActionForm, 
                    sessao, 
                    usuarioLogado);
            
            if (conta != null) {
                inserirRegistroAtendimentoActionForm.setIdConta("");
                inserirRegistroAtendimentoActionForm.setDescConta("");
                sessao.setAttribute("colecaoConta", "");
                
                if (!this.adicionado(colecaoConta, conta)) {
                    colecaoConta.add(conta);
                    sessao.setAttribute("colecaoConta", colecaoConta);
                }
            }
        }

        /*
         * ===========================================================================================================
         * Remover Conta
         * ===========================================================================================================
         */
        String removerConta = httpServletRequest.getParameter("removerConta");

        if (removerConta != null && !removerConta.equalsIgnoreCase("")) {
            Integer indice = new Integer(httpServletRequest.getParameter("removerConta"));
            
            if (colecaoConta != null
                    && !colecaoConta.isEmpty()
                    && colecaoConta.size() >= indice) {
                //Salva a conta a ser excluida para ser retirado do atributo de sessao 'contasSelecionadas'
                String contaAExcluir = colecaoConta.get(indice-1).getId().toString();
                
                // remove a conta excluida da colecao de contas
                colecaoConta.remove(indice-1);
                
                sessao.setAttribute("colecaoConta", colecaoConta);
                if( sessao.getAttribute("contasSelecionadas") != null){
                    
                    listContas.remove(contaAExcluir);
                    
                    if(listContas.size() == 0){
                        sessao.removeAttribute("contasSelecionadas");
                        sessao.removeAttribute("colecaoConta");
                    }else{
                        sessao.setAttribute("contasSelecionadas", listContas);
                    }
                }
                
            }
        }
                
        
        /*
         * [FS0013] - Verificar compatibilidade entre divisão de esgoto e
         * localidade/setor/quadra [SB0007] - Define Unidade Destino da Divisão
         * de Esgoto
         */
        String verificarCompatibilidade = httpServletRequest.getParameter("verificarCompatibilidade");

        if (verificarCompatibilidade != null && !verificarCompatibilidade.equalsIgnoreCase("")) {

            unidadeDestino = this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
                    fachada, inserirRegistroAtendimentoActionForm,
                    habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

            httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");
            
            httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
        }

        /*
         * Removendo ColecaoBairroArea
         */
        String removerColecaoBairroArea = httpServletRequest.getParameter("removerColecaoBairroArea");

        if (removerColecaoBairroArea != null && !removerColecaoBairroArea.equalsIgnoreCase("")) {
            sessao.removeAttribute("colecaoBairroArea");
            httpServletRequest.setAttribute("nomeCampo", httpServletRequest.getParameter("campoFoco"));
        }

        /*
         * Removendo endereço
         */
        String removerEndereco = httpServletRequest.getParameter("removerEndereco");

        if (removerEndereco != null    && !removerEndereco.trim().equalsIgnoreCase("")) {

            if (sessao.getAttribute("colecaoEnderecos") != null) {

                Collection enderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

                if (!enderecos.isEmpty()) {
//                    enderecos.remove(enderecos.iterator().next());
                    sessao.removeAttribute("colecaoEnderecos");
                    sessao.removeAttribute("habilitarAlteracaoEndereco");
                    
                    // Limpa os dados associados ao endereço caso não tenha informado o imóvel
                    if (inserirRegistroAtendimentoActionForm.getIdImovel() == null
                            || inserirRegistroAtendimentoActionForm.getIdImovel().trim().equals("")) {
                        
                        inserirRegistroAtendimentoActionForm.setIdMunicipio("");
                        inserirRegistroAtendimentoActionForm.setDescricaoMunicipio("");
                        inserirRegistroAtendimentoActionForm.setIdBairro("");
                        inserirRegistroAtendimentoActionForm.setCdBairro("");
                        inserirRegistroAtendimentoActionForm.setDescricaoBairro("");
                        inserirRegistroAtendimentoActionForm.setPontoReferencia("");
                        inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste("");
                        inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte("");
                    }
                }
            }
        }
		
        CoordenadasCompGis coordenadasCompGis = pesquisarCoordenadasCompGis(usuarioLogado.getId());
        
        /*
         * Adicionar endereço
         */
        String adicionarEndereco = httpServletRequest.getParameter("tipoPesquisaEndereco");

		if ((adicionarEndereco != null && !adicionarEndereco.trim().equalsIgnoreCase(""))) {
			
			 if(coordenadasCompGis != null && coordenadasCompGis.getId() != null){
				 if(coordenadasCompGis.getLogradouroBairro() != null){
					 httpServletRequest.setAttribute("logradouroId", coordenadasCompGis.getLogradouroBairro().getLogradouro().getId().toString());
					 httpServletRequest.setAttribute("bairroId", coordenadasCompGis.getLogradouroBairro().getBairro().getId().toString());
				 }
			 }
			 
			retorno = actionMapping.findForward("informarEndereco");
			httpServletRequest.setAttribute("mostrarPerimetro", "sim");
			sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
		}
		
		// Caso tenha adicionado o endereço seta os valores dos campos de municipio e bairro
		if (sessao.getAttribute("colecaoEnderecos") != null
				&& (inserirRegistroAtendimentoActionForm.getIdImovel() == null
				|| inserirRegistroAtendimentoActionForm.getIdImovel().trim().equals(""))) {
			
			Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
			
			if (!colecaoEnderecos.isEmpty()) {
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
				
				
				inserirRegistroAtendimentoActionForm.setIdMunicipio(
						imovel.getLogradouroBairro().getBairro().getMunicipio().getId().toString());
				
				inserirRegistroAtendimentoActionForm.setDescricaoMunicipio(
						imovel.getLogradouroBairro().getBairro().getMunicipio().getNome());
				
				inserirRegistroAtendimentoActionForm.setIdBairro(
						imovel.getLogradouroBairro().getBairro().getId().toString());
				
				inserirRegistroAtendimentoActionForm.setCdBairro(
						"" + imovel.getLogradouroBairro().getBairro().getCodigo());
				
				inserirRegistroAtendimentoActionForm.setDescricaoBairro(
						imovel.getLogradouroBairro().getBairro().getNome());
				
				this.pesquisarBairroArea(imovel.getLogradouroBairro().getBairro().getId(), fachada, sessao);
				
			
				httpServletRequest.setAttribute("desabilitarMunicipioBairro", "OK");
				httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
				
		        //Caso as informacoes das coordenadas estejam presentes na tabela ATENDIMENTOPUBLICO.COORDENADAS_COMPGIS para o usuário logado 
		        //(USUR_ID = id do usuário logado), recupera os dados a partir dessa tabela.
		        if(coordenadasCompGis != null && coordenadasCompGis.getId() != null){
		        	inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte(coordenadasCompGis.getCoordenadaX().toString());
		        	inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste(coordenadasCompGis.getCoordenadaY().toString());
		        }
				
			}
		}
		
//        String adicionarEndereco = httpServletRequest.getParameter("tipoPesquisaEndereco");
//
//        if ((adicionarEndereco != null && !adicionarEndereco.trim().equalsIgnoreCase(""))) {
//            
//            retorno = actionMapping.findForward("informarEndereco");
//            httpServletRequest.setAttribute("mostrarPerimetro", "sim");
//            sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
//        }
//        
//        // Caso tenha adicionado o endereço seta os valores dos campos de municipio e bairro
//        if (sessao.getAttribute("colecaoEnderecos") != null
//                && (inserirRegistroAtendimentoActionForm.getIdImovel() == null
//                || inserirRegistroAtendimentoActionForm.getIdImovel().trim().equals(""))) {
//            
//            Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
//            
//            if (!colecaoEnderecos.isEmpty()) {
//                Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
//                
//                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
//                filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.MUNICIPIO_ID, imovel.getLogradouroBairro().getBairro().getMunicipio().getId()));
//                Collection<?> colLocalidade =  fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
//                Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
//                
//                inserirRegistroAtendimentoActionForm.setIdMunicipio(
//                        imovel.getLogradouroBairro().getBairro().getMunicipio().getId().toString());
//                
//                inserirRegistroAtendimentoActionForm.setDescricaoMunicipio(
//                        imovel.getLogradouroBairro().getBairro().getMunicipio().getNome());
//                
//                inserirRegistroAtendimentoActionForm.setIdBairro(
//                        imovel.getLogradouroBairro().getBairro().getId().toString());
//                
//                inserirRegistroAtendimentoActionForm.setCdBairro(
//                        "" + imovel.getLogradouroBairro().getBairro().getCodigo());
//                
//                inserirRegistroAtendimentoActionForm.setDescricaoBairro(
//                        imovel.getLogradouroBairro().getBairro().getNome());
//                
//                if(localidade != null){
//                	inserirRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
//                	inserirRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());
//                	
//                	 unidadeDestino = this.verificarUnidadeTramiteLocalidade(habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto(), 
//                			 sistemaParametro, fachada, inserirRegistroAtendimentoActionForm, localidade);
//                	 
//                	 if(unidadeDestino != null ){
//                		 inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
//                		 inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
//	                	 this.verificarUnidadeTramite(fachada, unidadeDestino, 
//	                			 inserirRegistroAtendimentoActionForm,  habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto(), 
//	                			 sistemaParametro);
//                	 }
////                	
////                		 if(unidadeDestino.getUnidadeTramitacao() != null){
////                			 FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();
////            		        filtroUnidadeDestino.adicionarParametro(new ParametroSimples(
////            		            FiltroUnidadeOrganizacional.ID,    inserirRegistroAtendimentoActionForm.getIdUnidadeDestino()));
////            		        Collection<?> colecaoUnidadeDestino = fachada.pesquisar(
////            		                filtroUnidadeDestino, UnidadeOrganizacional.class.getName());
////            		        
////            		        UnidadeOrganizacional unidadeDestinoTramite = (UnidadeOrganizacional) Util
////                		                .retonarObjetoDeColecao(colecaoUnidadeDestino);
////            		        
////                			 inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getUnidadeTramitacao());
////                		 }
////                		 
////                	 }
////                }
//                
//                this.pesquisarBairroArea(imovel.getLogradouroBairro().getBairro().getId(), fachada, sessao);
//                
//            
//                httpServletRequest.setAttribute("desabilitarMunicipioBairro", "OK");
//                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
//                
//            }
//        }
        /*
         * Limpar Imóvel
         */
        String limparImovel = httpServletRequest.getParameter("limparImovel");

        if (limparImovel != null && !limparImovel.trim().equalsIgnoreCase("")) {

            this.limparImovel(inserirRegistroAtendimentoActionForm, sessao);
            httpServletRequest.setAttribute("nomeCampo", "idImovel");
        }
        
        //Limpar Localidade
        String limparLocalidade = httpServletRequest.getParameter("limparLocalidade");
        if (limparLocalidade != null && !limparLocalidade.trim().equalsIgnoreCase("")) {
            this.limparLocalidade(inserirRegistroAtendimentoActionForm, httpServletRequest);
            httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
        }
        
        String removerPagamento = httpServletRequest.getParameter("removerPagamento");

        if (removerPagamento != null && !removerPagamento.equals("")) {
            Integer idPagamento = new Integer(httpServletRequest.getParameter("removerPagamento"));
            
            Collection colecaoPagamento = 
                (Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
            
            Iterator itera = colecaoPagamento.iterator();
            
            while (itera.hasNext()) {
                Pagamento paga = (Pagamento) itera.next();
                
                if(paga.getId().intValue() == idPagamento.intValue()){
                    itera.remove();
                    break;
                }
            }
        }
        
        //Remover Documento com valor Cobrado indevidamente
        String removerPagValorMaiorDoc = httpServletRequest.getParameter("removerPagamentoValorMaiorDoc");
        if(removerPagValorMaiorDoc != null && removerPagValorMaiorDoc.equals("ok")){
        	
        	String idPagamento = httpServletRequest.getParameter("idPagamento");
        	Collection colecaoDocumentosPagos = (Collection)sessao.getAttribute("colecaoPagValorMaiorDocumento");
        	this.removerDocumento(colecaoDocumentosPagos,idPagamento);
        	sessao.setAttribute("colecaoPagValorMaiorDocumento", colecaoDocumentosPagos);
        	
        }
        
        
        //3. Caso o indicador "Informar Documentos Pagos com Valor Cobrado indevidamente no Registro de Atendimento" esteja com sim
        if(indicadorDocPagosValorCobradosIndev == ConstantesSistema.SIM.shortValue()){
        	
        	//3.1. Caso o usuário tenha permissão especial para devolução de valores indevidos
        	if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.DEVOLUCAO_VALORES_INDEVIDOS_RA, usuarioLogado ) ){
        		
        		//3.1.1. O sistema exibe um botão "Pesquisar Documentos Pagos" para a seleção dos documentos
    			httpServletRequest.setAttribute( "permissaoDevolucaoValoresIndevidosRA", "SIM" );
    		} else {
    			httpServletRequest.setAttribute( "permissaoDevolucaoValoresIndevidosRA", "NAO" );
    			sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");    		
    		}
        	
        }
        
        
        //Remover Documento com valor Cobrado indevidamente
        String removerPagCobIndev = httpServletRequest.getParameter("removerPagCobIndev");
        if(removerPagCobIndev != null && removerPagCobIndev.equals("ok")){
        	
        	String idPagamento = httpServletRequest.getParameter("idPagamento");
        	Collection colecaoDocumentosPagos = (Collection)sessao.getAttribute("colecaoPagamentosValoresCobIndevidamente");
        	this.removerDocumento(colecaoDocumentosPagos,idPagamento);
        	sessao.setAttribute("colecaoPagamentosValoresCobIndevidamente", colecaoDocumentosPagos);
        	
        }
        
        
        if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.GERAR_ARQUIVO_TEXTO_IMOVEIS_NAO_ENVIADOS, usuarioLogado ) ){
			httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "SIM" );
		} else {
			httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "NAO" );
		}
        
        
        String localOcorrencia = inserirRegistroAtendimentoActionForm.getIdLocalOcorrencia();
        
        if (localOcorrencia != null && !localOcorrencia.trim().equals("")) {
            httpServletRequest.setAttribute("localOcorrencia", localOcorrencia);
        } else {
            httpServletRequest.setAttribute("localOcorrencia", ""+ConstantesSistema.NUMERO_NAO_INFORMADO);
        }
        
        //[SB0007] 1.1.2.4
        if(unidadeDestino != null && pesquisarLocalidade == null && pesquisarImovel == null){
            
            unidadeDestino = fachada.verificarUnidadeTramite(unidadeDestino,
                    habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto(),sistemaParametro);
            
            if(unidadeDestino != null){
	            inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
	            inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
            }else{
            	inserirRegistroAtendimentoActionForm.setIdUnidadeDestino("");
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino("");
            }
        }                
        
        // Verifica se o Endereco foi informado
        if (sessao.getAttribute("colecaoEnderecos") != null)
            httpServletRequest.setAttribute("enderecoPreenchido", "OK");        
        
        //adicionado por Vivianne Sousa - 29/12/2009 - analista:Rosana Carvalho
        //[FS0046] - Verificar Tramite de Grandes Consumidores.
        if (inserirRegistroAtendimentoActionForm.getIdImovel() != null
                && !inserirRegistroAtendimentoActionForm.getIdImovel().equalsIgnoreCase("")){
//                && (pesquisarImovel == null || pesquisarImovel.equalsIgnoreCase(""))){
            
            unidadeDestino = fachada.verificarTramiteGrandesConsumidores(
                    new Integer(inserirRegistroAtendimentoActionForm.getIdImovel()));

            if (unidadeDestino != null) {
                inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
                
                sessao.setAttribute("desabilitarUnidadeDestino", "1");
                
            }else{
                sessao.removeAttribute("desabilitarUnidadeDestino");
            }
        }else{
            sessao.removeAttribute("desabilitarUnidadeDestino");
        }
        
        //--------------------------------------------------------//
        
        if( httpServletRequest.getAttribute("origemAcquaGIS").equals(true) 
                || httpServletRequest.getAttribute("origem") != null){
            
            sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
        }
   
        if (((inserirRegistroAtendimentoActionForm.getInscricaoImovel() != null) &&
        (!inserirRegistroAtendimentoActionForm.getInscricaoImovel().equals("")) && 
        !inserirRegistroAtendimentoActionForm.getInscricaoImovel().equals("Imóvel Inexistente")) || 
        ((inserirRegistroAtendimentoActionForm.getIdLocalidade() != null) &&
                (!inserirRegistroAtendimentoActionForm.getIdLocalidade().equals("")))){
        
			// Caso a localidade do imóvel possua indicador para incluir as
			// coordenadas no
			// ProGIS (LOCA_ICINCLCOORDPROGISRA = 1 na tabela
			// CADASTRO.LOCALIDADE
			// com LOCA_ID = LOCA_ID da tabela IMOVEL para IMOV_ID=Matrícula do
			// Imóvel)
			String idLocalidade = "";

			if ((inserirRegistroAtendimentoActionForm.getIdLocalidade() != null)
					&& (!inserirRegistroAtendimentoActionForm.getIdLocalidade().equals(""))) {
				idLocalidade = inserirRegistroAtendimentoActionForm.getIdLocalidade();
			} else {
				idLocalidade = inserirRegistroAtendimentoActionForm.getInscricaoImovel().substring(0, 3);
			}
			boolean indicadorIncluirCoordenaasPROGISLocalidade = fachada.verificarLocalidadeInclusaoCoordenadasPROGIS(Integer.valueOf(idLocalidade));

			// E o serviço tipo especificação possua indicador para incluir as
			// coordenadas
			// PROGIS (STEP_ICCOORDENADAPROGISRA da tabela
			// ATENDIMENTOPUBLICO.SOLICITACAO_TIPO_ESPEC
			// onde STEP_ID = STEP_ID da tabela
			// ATENDIMENTOPUBLICO.REGISTRO_ATENDIMENTO)
			boolean indicadorCoordenadaProgisEspecificacao = false;
			SolicitacaoTipoEspecificacao especificacao = pesquisarSolicitacaoTipoEspecificacao(inserirRegistroAtendimentoActionForm, fachada);

			if (especificacao != null) {
				if (especificacao.getIndicadorCoordenadaProgisRA().equals(new Short("1"))) {
					indicadorCoordenadaProgisEspecificacao = true;
				} else {
					indicadorCoordenadaProgisEspecificacao = false;
				}
			}

			if (indicadorIncluirCoordenaasPROGISLocalidade == false && indicadorCoordenadaProgisEspecificacao == true) {
				sessao.setAttribute("habilitarSomentePROGIS", "habilitarSomentePROGIS");
			} else {
				sessao.removeAttribute("habilitarSomentePROGIS");
			}        	
        	
        }
        
                
        return retorno;

    }
   
    //Remove o documento de id idPagamento da coleção colecaoDocs
    private void removerDocumento(Collection colecaoDocs, String idPagamento) {
		Iterator it = colecaoDocs.iterator();
		while(it.hasNext()){
			PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)it.next();
			if(helper.getIdPagamento().toString().equals(idPagamento)){
				it.remove();
				break;
			}
		}
	}


	/**
     * 
     * [FS0059] - Verificar pagamento a maior para imóvel informado
     * 
     * @param idImovel
     * @date 19/11/2012
     */
	private void verificarPagamentoMaiorImovelInformado(String idImovel) {

		FiltroPagamento filtroPagamento = new FiltroPagamento();
		
		filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.IMOVEL_ID,idImovel));
		filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.PAGAMENTO_SITUACAO_ATUAL_ID,PagamentoSituacao.VALOR_NAO_CONFERE));
		filtroPagamento.adicionarParametro(new ParametroNaoNulo(FiltroPagamento.VALOR_EXCEDENTE));
		
		Collection colecaoPag = this.getFachada().pesquisar(filtroPagamento, Pagamento.class.getName());
		
		
		//Caso o imóvel informada não tenha pagamento em duplicidade na tabela ARRECADACAO.PAGAMENTO
		if(colecaoPag == null || colecaoPag.size() == 0){
			
			//exibir a mensagem "Não existe pagamento em duplicidade para imóvel informado"
			throw new ActionServletException("atencao.nao_existe_pag_a_maior");
		}
	
	}
		
	

	/**
     * Caso a especificacao tenha o indicador de pagamento em duplicidade,
     * eh feita a pesquisa dos pagamentos em duplicidade
     * 
     * @author Rafael Pinto
     * @date 15/03/2011
     * 
     * @param Integer idImovel
     * @return Collection colecao de pagamentos em duplicidade
     */
    private Collection pesquisaPagamentosEmDuplicidade(Integer idImovel){
        
        FiltroPagamento filtro = new FiltroPagamento(FiltroPagamento.ANO_MES_REFERENCIA_PAGAMENTO);
        
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA_GERAL);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_GERAL);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.GUIA_PAGAMENTO);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA_HISTORICO);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_HISTORICO);
        
        filtro.adicionarParametro(
            new ParametroSimples(FiltroPagamento.IMOVEL,
                idImovel));
        
        filtro.adicionarParametro(
            new ParametroSimples(FiltroPagamento.PAGAMENTO_SITUACAO_ATUAL_ID,
                PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE));
                
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.IMOVEL);
        
        Collection colecaoPagamento = 
            (Collection) this.getFachada().pesquisar(filtro,Pagamento.class.getName());
        
        return colecaoPagamento;
    }

    
    /*
     * Métodos auxiliares
     * =============================================================================================================
     */
    public UnidadeOrganizacional verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
            Fachada fachada,
            InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
            boolean solicitacaoTipoRelativoAreaEsgoto) {
        
        UnidadeOrganizacional unidadeDestino = null;
        
        fachada.verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
                Util.converterStringParaInteger(inserirRegistroAtendimentoActionForm.getIdLocalidade()),
                Util.converterStringParaInteger(inserirRegistroAtendimentoActionForm.getIdSetorComercial()),
                Util.converterStringParaInteger(inserirRegistroAtendimentoActionForm.getIdQuadra()),
                Util.converterStringParaInteger(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto()));
        
        if(!(inserirRegistroAtendimentoActionForm.getIdLocalidade().equals("") || inserirRegistroAtendimentoActionForm.getTipoSolicitacao().equals(""))){
                    
            unidadeDestino = fachada.definirUnidadeDestinoDivisaoEsgoto(
                    new Integer(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
                    new Integer(inserirRegistroAtendimentoActionForm.getIdDivisaoEsgoto()),
                    solicitacaoTipoRelativoAreaEsgoto,
                    new Integer(inserirRegistroAtendimentoActionForm.getIdLocalidade()), 
                    new Integer(inserirRegistroAtendimentoActionForm.getTipoSolicitacao()));
    
            if (unidadeDestino != null) {                
                
                inserirRegistroAtendimentoActionForm.setIdUnidadeDestino(unidadeDestino.getId().toString());
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
            }else{
            	inserirRegistroAtendimentoActionForm.setIdUnidadeDestino("");
                inserirRegistroAtendimentoActionForm.setDescricaoUnidadeDestino("");
            }
        }
        return unidadeDestino;
    }

    public void pesquisarBairroArea(Integer idBairro, Fachada fachada, HttpSession sessao) {

        FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

        filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, idBairro));

        Collection colecaoBairroArea = fachada.pesquisar(filtroBairroArea,BairroArea.class.getName());

        if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()) {
            throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "BAIRRO_AREA");
        } else {
            sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
            
        }
    }

    
    public void carregarMunicipioBairroParaImovel(
            ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
            ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
            InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
            HttpSession sessao, Fachada fachada) {

        if (habilitaGeograficoDivisaoEsgoto != null
                && habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua()
                && obterDadosIdentificacaoLocalOcorrenciaHelper.getEnderecoDescritivo() == null) {

            inserirRegistroAtendimentoActionForm.setIdMunicipio(
                    obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().
                    getLogradouroBairro().getBairro().getMunicipio().getId().toString());

            inserirRegistroAtendimentoActionForm.setDescricaoMunicipio(
                    obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().
                    getLogradouroBairro().getBairro().getMunicipio().getNome());

            inserirRegistroAtendimentoActionForm.setIdBairro(
                    obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().
                    getLogradouroBairro().getBairro().getId().toString());

            inserirRegistroAtendimentoActionForm.setCdBairro(
                    String.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper
                    .getImovel().getLogradouroBairro().getBairro().getCodigo()));

            inserirRegistroAtendimentoActionForm.setDescricaoBairro(
                    obterDadosIdentificacaoLocalOcorrenciaHelper
                    .getImovel().getLogradouroBairro().getBairro().getNome());
            
            this.pesquisarBairroArea(obterDadosIdentificacaoLocalOcorrenciaHelper
            .getImovel().getLogradouroBairro().getBairro().getId(), fachada, sessao);

            sessao.setAttribute("desabilitarMunicipioBairro", "OK");
            
        } else {

            inserirRegistroAtendimentoActionForm.setIdMunicipio("");

            inserirRegistroAtendimentoActionForm.setDescricaoMunicipio("");

            inserirRegistroAtendimentoActionForm.setIdBairro("");

            inserirRegistroAtendimentoActionForm.setCdBairro("");

            inserirRegistroAtendimentoActionForm.setDescricaoBairro("");
            
            sessao.removeAttribute("colecaoBairroArea");

            sessao.removeAttribute("desabilitarMunicipioBairro");
        }
    }
    
    private void limparLocalidade(InserirRegistroAtendimentoActionForm form, HttpServletRequest httpServletRequest){
        form.setIdLocalidade("");
        form.setDescricaoLocalidade("");
        form.setCdSetorComercial("");
        form.setIdSetorComercial("");
        form.setDescricaoSetorComercial("");
        form.setIdQuadra("");
        form.setNnQuadra("");
        form.setDescricaoMunicipioOcorrencia("");
        form.setIdUnidadeDestino("");
        form.setDescricaoUnidadeDestino("");
        form.setIdDivisaoEsgoto("");
        form.setDescricaoMunicipioOcorrencia("");
        form.setNnQuadra("");
    }
    
    private void limparImovel(InserirRegistroAtendimentoActionForm form, HttpSession sessao){
        
        form.setIdImovel("");
        form.setInscricaoImovel("");
        form.setDescricaoLocalOcorrencia("");
        form.setIdMunicipio("");
        form.setDescricaoMunicipio("");
        form.setIdBairro("");
        form.setCdBairro("");
        form.setDescricaoBairro("");
        form.setIdLocalidade("");
        form.setDescricaoLocalidade("");
        form.setIdSetorComercial("");
        form.setCdSetorComercial("");
        form.setDescricaoSetorComercial("");
        form.setIdQuadra("");
        form.setNnQuadra("");
        form.setIdUnidadeDestino("");
        form.setDescricaoUnidadeDestino("");
        form.setPontoReferencia("");
        form.setIdPavimentoRua(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        form.setIdPavimentoCalcada(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));        
        form.setIdDivisaoEsgoto(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        form.setIndicCoordenadaSemLogradouro(String.valueOf(ConstantesSistema.NAO));
        form.setDescricaoMunicipioOcorrencia("");
        form.setNnCoordenadaLeste( "" );
        form.setNnCoordenadaNorte( "" );
        
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("enderecoPertenceImovel");
        sessao.removeAttribute("habilitarAlteracaoEndereco");
        sessao.removeAttribute("colecaoBairroArea");
        sessao.removeAttribute("desabilitarMunicipioBairro");
        sessao.removeAttribute("desabilitarDivisaoEsgoto");
        sessao.removeAttribute("desabilitarPavimentoRua");
        sessao.removeAttribute("desabilitarPavimentoCalcada");
        sessao.removeAttribute("desabilitarLcalidadeSetorQuadra");
        sessao.removeAttribute("indicCoordenadaSemLogradouro");
        sessao.removeAttribute("colecaoPagamentosDuplicidade");
        sessao.removeAttribute("colecaoConta");
        sessao.removeAttribute("contasSelecionadas");
        sessao.removeAttribute("colecaoPagValorMaiorDocumento");
        sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");
        sessao.removeAttribute("habilitarSomentePROGIS");
    }
    //=================================================================================================================
    
    
    
    public void carregarDadosGis(            
            GisHelper gisHelper,
            InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
            HttpSession sessao, Fachada fachada, HttpServletRequest httpServletRequest) {

        
        Imovel imovel =  gisHelper.getImovel();
        String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
        String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste();     
        String localidade = gisHelper.getLocalidade();    
        String setorComercial = gisHelper.getSetorComercial();
        Collection enderecos = null;    
        
        Boolean obj = (Boolean)sessao.getAttribute("origemGIS");
        boolean origemGIS = false;
        if(obj != null)
            origemGIS = obj.booleanValue();
           
        if(origemGIS){
        
            
            if(imovel.getId() != null){    
                inserirRegistroAtendimentoActionForm.setIdImovel(imovel.getId().toString());
                sessao.setAttribute("temImovelGIS","SIM");
            }else{
                inserirRegistroAtendimentoActionForm.setIdLocalidade(localidade);
                inserirRegistroAtendimentoActionForm.setCdSetorComercial(setorComercial);
                if (sessao.getAttribute("colecaoEnderecos") == null){
                      enderecos = new ArrayList();
                      if (imovel != null) {
                          enderecos.add(imovel);
                      }
                      sessao.setAttribute("colecaoEnderecos", enderecos);
                    }
                
                
                /*************************** Pesquisar Localidade **********************************/
                
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.ID, inserirRegistroAtendimentoActionForm.getIdLocalidade()));
    
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
                filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
                
                Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
                        Localidade.class.getName());
    
                if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
    
                    inserirRegistroAtendimentoActionForm.setIdLocalidade("");
                    //inserirRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");
    
                    /*httpServletRequest.setAttribute("corLocalidade", "exception");
                    httpServletRequest.setAttribute("nomeCampo", "idLocalidade");*/
    
                } else {
                    Localidade lc = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
                    inserirRegistroAtendimentoActionForm.setIdLocalidade(lc.getId().toString());
                    inserirRegistroAtendimentoActionForm.setDescricaoLocalidade(lc.getDescricao());
                    httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");
                    Municipio municipio = lc.getMunicipio();
                    if(municipio != null){
                        inserirRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
                        httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
                    }
                }
                
                
                /**********************Pesquisar Setor Comercial***************************************/
                
                FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.ID_LOCALIDADE,inserirRegistroAtendimentoActionForm.getIdLocalidade()));
    
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                        inserirRegistroAtendimentoActionForm.getCdSetorComercial()));
    
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
    
                Collection colecaoSetorComercial = fachada.pesquisar(
                        filtroSetorComercial, SetorComercial.class.getName());
    
                if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
    
                    inserirRegistroAtendimentoActionForm.setIdSetorComercial("");
                    inserirRegistroAtendimentoActionForm.setCdSetorComercial("");
                    /*inserirRegistroAtendimentoActionForm.setDescricaoSetorComercial("Setor Comercial Inexistente");
                    httpServletRequest.setAttribute("corSetorComercial", "exception");
                    httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");*/
                } else {
                    SetorComercial sc = (SetorComercial) Util
                            .retonarObjetoDeColecao(colecaoSetorComercial);
                    inserirRegistroAtendimentoActionForm.setIdSetorComercial(sc.getId().toString());
                    inserirRegistroAtendimentoActionForm.setCdSetorComercial(String.valueOf(sc.getCodigo()));
                    inserirRegistroAtendimentoActionForm.setDescricaoSetorComercial(sc.getDescricao());
    
                    httpServletRequest.setAttribute("nomeCampo", "nnQuadra");
    
                    httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
                }
                
                httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
                sessao.setAttribute("enderecoSemImovel","SIM");
    
            }
        
        }
        else{
            if (sessao.getAttribute("colecaoEnderecos") == null){
              enderecos = new ArrayList();
              if (imovel != null) {
                  enderecos.add(imovel);
              }
              sessao.setAttribute("colecaoEnderecos", enderecos);
            }                 
        }
        inserirRegistroAtendimentoActionForm.setNnCoordenadaNorte(nnCoordenadaNorte);
        inserirRegistroAtendimentoActionForm.setNnCoordenadaLeste(nnCoordenadaLeste);
        sessao.removeAttribute("gisHelper");
    
    }
    
    
    private Conta pesquisarConta(InserirRegistroAtendimentoActionForm form, 
        HttpSession sessao,
        Usuario usuarioLogado) {
        
        Conta conta = null;
        
        String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getIdConta());
        
        FiltroConta filtroConta = new FiltroConta();
        if (form.getIdContaPesquisada() != null && 
            !form.getIdContaPesquisada().equals("")) {
            
            filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, form.getIdContaPesquisada()));
        } else {
            filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.REFERENCIA, anoMes));
        }
        
        filtroConta.adicionarParametro(
            new ParametroSimples(FiltroConta.IMOVEL_ID, form.getIdImovel()));
        filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
        
        Collection colecao = 
            this.getFachada().pesquisar(filtroConta, Conta.class.getName());

        // [FS0048] - Verificar existência da conta.
        if (colecao != null && !colecao.isEmpty()) {
            
            conta = this.retornaConta(colecao);
            
            // [FS0049] - Verificar se a conta pode ser associada.
            if (conta != null) {
                
                SistemaParametro sistemaParametro = 
                    this.getFachada().pesquisarParametrosDoSistema();

                // Verifica se o usuário possua permissão especial
                boolean temPermissaoParaRetificarContaNorma = 
                    this.getFachada().verificarPermissaoEspecial(
                        PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, 
                        usuarioLogado);    
                
                // [FS0050] - Verificar prazo de vencimento das contas para associação
                if (temPermissaoParaRetificarContaNorma || 
                    (Util.adcionarOuSubtrairMesesAData(conta.getDataVencimentoConta(), 
                        sistemaParametro.getNumeroMesesRetificarConta(), 0).compareTo(new Date()) != -1)) {
                    
                    form.setIdConta(conta.getReferencia() + "");
                    form.setDescConta(conta.getFormatarAnoMesParaMesAno());
                    sessao.setAttribute("contaEncontrada", "");
                    
                    return conta;
                    
                } else {
                    form.setIdConta("");
                    form.setDescConta("Conta com prazo para associação excedido!");
                    sessao.removeAttribute("contaEncontrada");
                }

            } else {
                form.setIdConta("");
                form.setDescConta("Conta não pode ser associada ao Registro de Atendimento!");
                sessao.removeAttribute("contaEncontrada");
            }
        } else {

            form.setIdConta("");
            form.setDescConta("Conta não Localizada");

            sessao.removeAttribute("contaEncontrada");
        }
        
        return null;
    }
    
    private boolean adicionado(Collection<Conta> colecaoConta, Conta conta) {
        
        Iterator iterator = colecaoConta.iterator();
        
        while(iterator.hasNext()) {
            Conta contaAdicionada = (Conta) iterator.next();
            
            if (contaAdicionada.getId().equals(conta.getId())) {
                return true;
            }
        }
        return false;
    }

    private Conta retornaConta(Collection colecao) {
        
        Iterator iterator = colecao.iterator();
        
        while(iterator.hasNext()) {
            Conta conta = (Conta) iterator.next();
            
            if (conta.getDebitoCreditoSituacaoAtual() != null
                    && (conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_NORMAL)
                        || conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_RETIFICADA)
                        || conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_INCLUIDA))) {
                return conta;
            }
        }
        
        return null;
    }
    
    // RM6417
    // Pesquisa as contas retificacao selecionadas
    private Conta pesquisarConta(String id, InserirRegistroAtendimentoActionForm form,
            HttpSession sessao,
            Usuario usuarioLogado) {
            
            Conta conta = null;
            
            String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(id);
            
            FiltroConta filtroConta = new FiltroConta();
            
                
            filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, id));
            
            
            //filtroConta.adicionarParametro(
                //new ParametroSimples(FiltroConta.IMOVEL_ID, form.getIdImovel()));
            filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
            
            Collection<?> colecao = 
                this.getFachada().pesquisar(filtroConta, Conta.class.getName());

            // [FS0048] - Verificar existência da conta.
            if (colecao != null && !colecao.isEmpty()) {
                
                conta = this.retornaConta(colecao);
                
                // [FS0049] - Verificar se a conta pode ser associada.
                if (conta != null) {
                    
                    SistemaParametro sistemaParametro = 
                        this.getFachada().pesquisarParametrosDoSistema();

                    // Verifica se o usuário possua permissão especial
                    boolean temPermissaoParaRetificarContaNorma = 
                        this.getFachada().verificarPermissaoEspecial(
                            PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, 
                            usuarioLogado);    
                    
                    // [FS0050] - Verificar prazo de vencimento das contas para associação
                    if (temPermissaoParaRetificarContaNorma || 
                        (Util.adcionarOuSubtrairMesesAData(conta.getDataVencimentoConta(), 
                            sistemaParametro.getNumeroMesesRetificarConta(), 0).compareTo(new Date()) != -1)) {
                        
                        form.setIdConta(conta.getReferencia() + "");
                        form.setDescConta(conta.getFormatarAnoMesParaMesAno());
                        sessao.setAttribute("contaEncontrada", "");
                        
                        return conta;
                        
                    } else {
                        form.setIdConta("");
                        form.setDescConta("Conta com prazo para associação excedido!");
                        sessao.removeAttribute("contaEncontrada");
                    }

                } else {
                    form.setIdConta("");
                    form.setDescConta("Conta não pode ser associada ao Registro de Atendimento!");
                    sessao.removeAttribute("contaEncontrada");
                }
            } else {

                form.setIdConta("");
                form.setDescConta("Conta não Localizada");

                sessao.removeAttribute("contaEncontrada");
            }
            
            return null;
        }
    
    
    /**
     * Pesquisa a SolicitacaoTipoEspecificacao do RA
     * 
     * @param InserirRegistroAtendimentoActionForm
     * @param Fachada
     * @return SolicitacaoTipoEspecificacao
     * 
     * @author Anderson Cabral
     * @date 22/04/2012
     */
    private SolicitacaoTipoEspecificacao pesquisarSolicitacaoTipoEspecificacao(InserirRegistroAtendimentoActionForm form, Fachada fachada){
        SolicitacaoTipoEspecificacao especificacao = null;
    	FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
        
        filtroSolicitacaoTipoEspecificacao.adicionarParametro(
                new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
                        new Integer(form.getEspecificacao())));
        
        Collection colecao = fachada.pesquisar(
                filtroSolicitacaoTipoEspecificacao,
                SolicitacaoTipoEspecificacao.class.getName());
        
        if (colecao != null && !colecao.isEmpty()) {
        	especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
        }
        
        return especificacao;       
    }
    
    /**
     * Pesquisa a CoordenadasCompGis por usuario
     * 
     * @param idUsuario
     * @return CoordenadasCompGis
     * 
     * @author Anderson Cabral
     * @date 14/01/2013
     */
	private CoordenadasCompGis pesquisarCoordenadasCompGis(Integer idUsuario){
		FiltroCoordenadasCompGis filtroCoordenadasCompGis = new FiltroCoordenadasCompGis();
		filtroCoordenadasCompGis.adicionarCaminhoParaCarregamentoEntidade(FiltroCoordenadasCompGis.LOGRADOURO);
		filtroCoordenadasCompGis.adicionarCaminhoParaCarregamentoEntidade(FiltroCoordenadasCompGis.BAIRRO);
		filtroCoordenadasCompGis.adicionarParametro(new ParametroSimples(FiltroCoordenadasCompGis.USUARIO_ID, idUsuario));
		Collection<CoordenadasCompGis> colecaoCoordenadasCompGis = getFachada().pesquisar(filtroCoordenadasCompGis, CoordenadasCompGis.class.getName());
		return (CoordenadasCompGis) Util.retonarObjetoDeColecao(colecaoCoordenadasCompGis);
	}
}