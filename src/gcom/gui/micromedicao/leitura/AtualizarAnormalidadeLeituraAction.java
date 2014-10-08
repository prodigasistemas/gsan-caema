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
 * Magno Jean Gouveia Silveira
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
package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.empresa.Empresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * <b>[UC0191]</b> Manter Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite atualizar uma Anormalidade de Leitura
 * </p>
 * 
 * @author lms, Magno Gouveia
 * @since 06/07/2006, 23/08/2011
 */
public class AtualizarAnormalidadeLeituraAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        // Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarAnormalidadeLeituraActionForm form = (AtualizarAnormalidadeLeituraActionForm) actionForm;

        LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) sessao.getAttribute("leituraAnormalidadeAtualizar");
        Integer id = null;
        if(leituraAnormalidade == null)
        	id = new Integer(form.getIdAnormalidadeAtualizar());
        else
        	id = leituraAnormalidade.getId();
      
        FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
        filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
        		id));
        Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
                LeituraAnormalidade.class.getName());

		if (colecaoLeituraAnormalidade == null || colecaoLeituraAnormalidade.isEmpty()) {
			throw new ActionServletException("atencao.atualizacao.timestamp");
		} else if ( leituraAnormalidade == null ) {
			
			leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(colecaoLeituraAnormalidade);
		}
		
		//Validar campo Fator Com leitura para ser > 0
		if(Util.formatarMoedaRealparaBigDecimal(form.getNumeroFatorComLeitura()).compareTo(new BigDecimal("0")) <= 0){
			throw new ActionServletException(	"atencao.invalido_zero",
					null,
					"Fator que deverá atualizar o consumo de imóveis com anormalidade (Com leitura)".toUpperCase());
		}
		
		//Validar campo Fator Sem Leitura para ser > 0
		if(Util.formatarMoedaRealparaBigDecimal(form.getNumeroFatorSemLeitura()).compareTo(new BigDecimal("0")) <= 0){
			throw new ActionServletException(	"atencao.invalido_zero",
					null,
					"Fator que deverá atualizar o consumo de imóveis com anormalidade (Sem leitura)".toUpperCase());
		}
		 if (form.getDescricao() != null && !form.getDescricao().equals("-1")) {
			 leituraAnormalidade.setDescricao(form.getDescricao());
		 }
        leituraAnormalidade.setDescricaoAbreviada(form.getAbreviatura());
        leituraAnormalidade.setIndicadorRelativoHidrometro(new Short(form.getIndicadorRelativoHidrometro()));
        leituraAnormalidade.setIndicadorSistema(new Short(form.getUsoRestritoSistema()));
        leituraAnormalidade.setIndicadorPerdaTarifaSocial(new Short(form.getPerdaTarifaSocial()));
        leituraAnormalidade.setIndicadorImovelSemHidrometro(new Short(form.getIndicadorImovelSemHidrometro()));
        leituraAnormalidade.setUltimaAlteracao(Util.converteStringParaDateHora(form.getDataUltimaAlteracao()));
        leituraAnormalidade.setIndicadorUso(new Short(form.getIndicadorUso()));
        leituraAnormalidade.setIndicadorEmissaoOrdemServico(ConstantesSistema.NAO);
        

        ServicoTipo servicoTipo = null;
        

        if (form.getTipoServico() != null && !form.getTipoServico()
                                                  .equals("") && !form.getTipoServico()
                                                                      .equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
            servicoTipo = new ServicoTipo();

            servicoTipo.setId(new Integer(form.getTipoServico()));
            leituraAnormalidade.setServicoTipo(servicoTipo);
        }else {
			leituraAnormalidade.setServicoTipo(null);
		}

        if (form.getConsumoLeituraNaoInformado() != null) {

            Integer idConsumoLeituraNaoInformado = new Integer(form.getConsumoLeituraNaoInformado());

            if (idConsumoLeituraNaoInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

                leituraAnormalidade.setLeituraAnormalidadeConsumoSemleitura(null);
            } else {
                FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
                filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeConsumo.ID,
                                                                                         form.getConsumoLeituraNaoInformado()
                                                                                             .toString()));
                Collection colecaoConsumoLeituraNaoInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

                leituraAnormalidade.setLeituraAnormalidadeConsumoSemleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraNaoInformado.iterator()
                                                                                                                                          .next());
            }
        }

        if (form.getConsumoLeituraInformado() != null) {

            Integer idConsumoLeituraInformado = new Integer(form.getConsumoLeituraInformado());

            if (idConsumoLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

                leituraAnormalidade.setLeituraAnormalidadeConsumoComleitura(null);
            } else {
                FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
                filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeConsumo.ID,
                                                                                         form.getConsumoLeituraInformado()
                                                                                             .toString()));
                Collection colecaoConsumoLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

                leituraAnormalidade.setLeituraAnormalidadeConsumoComleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraInformado.iterator()
                                                                                                                                       .next());
            }
        }

        if (form.getLeituraLeituraNaoturaInformado() != null) {

            Integer idLeituraLeituraNaoturaInformado = new Integer(form.getLeituraLeituraNaoturaInformado());

            if (idLeituraLeituraNaoturaInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

                leituraAnormalidade.setLeituraAnormalidadeLeituraSemleitura(null);
            } else {
                FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
                filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeLeitura.ID,
                                                                                         form.getLeituraLeituraNaoturaInformado()
                                                                                             .toString()));
                Collection colecaoLeituraLeituraNaoturaInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

                // setando
                leituraAnormalidade.setLeituraAnormalidadeLeituraSemleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraNaoturaInformado.iterator()
                                                                                                                                              .next());
            }
        }

        if (form.getLeituraLeituraInformado() != null) {

            Integer idLeituraLeituraInformado = new Integer(form.getLeituraLeituraInformado());

            if (idLeituraLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

                leituraAnormalidade.setLeituraAnormalidadeLeituraComleitura(null);
            } else {
                FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
                filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeLeitura.ID,
                                                                                         form.getLeituraLeituraInformado()
                                                                                             .toString()));
                Collection colecaoLeituraLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

                leituraAnormalidade.setLeituraAnormalidadeLeituraComleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraInformado.iterator()
                                                                                                                                       .next());
            }
        }

        if (form.getNumeroFatorComLeitura() != null) {
            leituraAnormalidade.setNumeroFatorComLeitura(Util.formatarMoedaRealparaBigDecimal(form.getNumeroFatorComLeitura()));
        }
        if (form.getNumeroFatorSemLeitura() != null) {
            leituraAnormalidade.setNumeroFatorSemLeitura(Util.formatarMoedaRealparaBigDecimal(form.getNumeroFatorSemLeitura()));
        }
        if (form.getIndicadorLeitura() != null) {
            leituraAnormalidade.setIndicadorLeitura(new Short(form.getIndicadorLeitura()));
        }

        if (!Util.verificarNaoVazio(form.getNumeroVezesSuspendeLeitura())) {
            form.setNumeroVezesSuspendeLeitura("0");
        }
        leituraAnormalidade.setNumeroVezesSuspendeLeitura(new Integer(form.getNumeroVezesSuspendeLeitura()));

        if (!Util.verificarNaoVazio(form.getNumeroMesesLeituraSuspensa())) {
            form.setNumeroMesesLeituraSuspensa("0");
        }
        leituraAnormalidade.setNumeroMesesLeituraSuspensa(new Integer(form.getNumeroMesesLeituraSuspensa()));
        
       
        SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
        
        if (form.getTipoSolicitacao() != null && !form.getTipoSolicitacao().equals("-1")){
        	if (form.getEspecificacao() == null || form.getEspecificacao().equals("-1")){
        		throw new ActionServletException(	"atencao.required",
						null,
						"Especificação");
        	} 	
        }
        
        if (form.getTipoSolicitacao() != null && !form.getTipoSolicitacao().equals("-1")) {

            Integer idTipoSolicitacao = new Integer(form.getTipoSolicitacao());

            if (idTipoSolicitacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

                leituraAnormalidade.setSolicitacaoTipoEspecificacao(null);
            } else {

            	solicitacaoTipoEspecificacao.setId(Integer.valueOf(form.getEspecificacao()));
				leituraAnormalidade.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
            }
        }
		
        
        if(form.getIndicadorExibirAnormalidadeRelatorio() != null && 
        		!form.getIndicadorExibirAnormalidadeRelatorio().equals("")){
        	leituraAnormalidade.setIndicadorExibirAnormalidadeRelatorio(
        			new Short(form.getIndicadorExibirAnormalidadeRelatorio()));
        }
        leituraAnormalidade.setIndicadorExibirMensagemHidrometrosCalcada(new Short(form.getIndicadorExibirMensagemHidrometrosCalcada()));
        
        leituraAnormalidade.setIndicadorExibirMensagemHidrometrosSubstituidos(new Short(form.getIndicadorExibirMensagemHidrometrosSubstituidos()));
        
        leituraAnormalidade.setIndicadorNaoImprimirConta(new Short(form.getIndicadorNaoImprimirConta()));
        
        leituraAnormalidade.setIndicadorAnormalidadeImpactaLeitura(new Short(form.getIndicadorAnormalidadeImpactaLeitura()));
        
        leituraAnormalidade.setIndicadorFotoObrigatoria(new Short(form.getIndicadorFotoObrigatoria()));
        
        if(form.getEmpresa() != null && !form.getEmpresa().equals("")){
        	Empresa empresa = new Empresa();
        	empresa.setId(Integer.parseInt(form.getEmpresa()));
        	
        	leituraAnormalidade.setEmpresa(empresa);
        }else{
        	leituraAnormalidade.setEmpresa(null);
        }
        
        fachada.atualizarAnormalidadeLeitura(leituraAnormalidade);

        montarPaginaSucesso(httpServletRequest, "Anormalidade de Leitura de código " + leituraAnormalidade.getId()
                                                                                                          .toString()
                + " atualizada com sucesso.", "Realizar outra Manutenção de Anormalidade de Leitura ", "exibirFiltrarAnormalidadeLeituraAction.do?menu=sim");

        return retorno;
    }
}
