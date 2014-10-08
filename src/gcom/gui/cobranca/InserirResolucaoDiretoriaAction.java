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
package gcom.gui.cobranca;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.LimitacaoGeograficaRDHelper;
import gcom.cobranca.RdLimitacaoGeografica;
import gcom.cobranca.RdLimitacaoGeograficaLocalidade;
import gcom.cobranca.RdRestricaoUsuario;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolução de diretoria no banco
 * 
 * [UC0217] Inserir Resolução de Diretoria Permite inserir uma
 * ResolucaoDiretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class InserirResolucaoDiretoriaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		InserirResolucaoDiretoriaActionForm inserirResolucaoDiretoriaActionForm = (InserirResolucaoDiretoriaActionForm) actionForm;

		// Cria uma resolução de diretoria setando os valores informados pelo
		// usuário na pagina para ser inserido no banco
		ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();

		resolucaoDiretoria.setNumeroResolucaoDiretoria(inserirResolucaoDiretoriaActionForm.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(inserirResolucaoDiretoriaActionForm.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(inserirResolucaoDiretoriaActionForm.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes()));
		
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
		resolucaoDiretoria.setIndicadorValidoAcaoCobranca(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorValidoAcaoCobranca()));
		
		if (inserirResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()!= null &&
				!inserirResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(inserirResolucaoDiretoriaActionForm.getIdParcelasEmAtraso())));
			
			Collection colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(colecaoRD != null && !colecaoRD.isEmpty()){
				ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
				rdParcelasEmAtraso.setId(new Integer(inserirResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()));
				resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
			}else{
				//RD Parcelas em Atraso inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}
		}
		
		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(inserirResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento()));
		
		if (inserirResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()!= null &&
				!inserirResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(inserirResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento())));

			Collection colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(colecaoRD != null && !colecaoRD.isEmpty()){
				ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
				rdParcelamentoEmAndamento.setId(new Integer(inserirResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()));
				resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
			}else{
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}
			
		}

		// verifica se a data final foi digitada em caso afirmativo seta-a no objeto
		if (inserirResolucaoDiretoriaActionForm.getDataFim() != null
				&& !inserirResolucaoDiretoriaActionForm.getDataFim().equals("")) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(
					inserirResolucaoDiretoriaActionForm.getDataFim()));
		}
		
		//RM8248 - adicionado por Vivianne Sousa - 22/11/2012
		// verifica se valor mínimo foi digitado em caso afirmativo seta-a no objeto
		if (inserirResolucaoDiretoriaActionForm.getValorDebitoMinimo() != null
				&& !inserirResolucaoDiretoriaActionForm.getValorDebitoMinimo().equals("")) {
			resolucaoDiretoria.setValorDebitoMinimo(Util.formatarMoedaRealparaBigDecimal(
					inserirResolucaoDiretoriaActionForm.getValorDebitoMinimo()));
		}
		
		// verifica se valor máximo foi digitado em caso afirmativo seta-a no objeto
		if (inserirResolucaoDiretoriaActionForm.getValorDebitoMaximo() != null
				&& !inserirResolucaoDiretoriaActionForm.getValorDebitoMaximo().equals("")) {
			resolucaoDiretoria.setValorDebitoMaximo(Util.formatarMoedaRealparaBigDecimal(
					inserirResolucaoDiretoriaActionForm.getValorDebitoMaximo()));
		}
		
		// Insere uma Resolução de Diretoria na base, mas fazendo, antes,
		// algumas verificações no ControladorCobrancaSEJB.
		Integer id = (Integer) fachada.inserirResolucaoDiretoria(
				resolucaoDiretoria, this.getUsuarioLogado(httpServletRequest));
		resolucaoDiretoria.setId(id);
		
		HttpSession sessao  = httpServletRequest.getSession();
		
		Collection<LimitacaoGeograficaRDHelper> collectionLimitacaoGeograficaRDHelper = 
				(Collection<LimitacaoGeograficaRDHelper>) sessao.getAttribute("collectionLimitacaoGeograficaRDHelper");
		
		
		HashMap<RdLimitacaoGeografica,RdLimitacaoGeografica> colecaoInserida = new HashMap<RdLimitacaoGeografica,RdLimitacaoGeografica>();
		
		
		
		
		if (!Util.isVazioOrNulo(collectionLimitacaoGeograficaRDHelper)){
			RdLimitacaoGeografica rdLimitacaoGeografica = new RdLimitacaoGeografica();
			RdLimitacaoGeograficaLocalidade rdLimitacaoGeograficaLocalidade = new RdLimitacaoGeograficaLocalidade();
			
			Iterator  iterator =   collectionLimitacaoGeograficaRDHelper.iterator();
			
			while(iterator.hasNext()){	
				rdLimitacaoGeografica = new RdLimitacaoGeografica();
				rdLimitacaoGeograficaLocalidade = new RdLimitacaoGeograficaLocalidade();
				
				LimitacaoGeograficaRDHelper helper = (LimitacaoGeograficaRDHelper) iterator.next();
				
				if(helper.getDataLimiteVencimentoContaParcelar() != null){
					rdLimitacaoGeografica.setDataLimiteVencimentoContaParcelar(Util.converteStringParaDate(helper.getDataLimiteVencimentoContaParcelar()));
				}
				
				if(helper.getDataLimiteVencimentoContaVista() != null){
					rdLimitacaoGeografica.setDataLimiteVencimentoContaVista(Util.converteStringParaDate(helper.getDataLimiteVencimentoContaVista()));
				}
				
				rdLimitacaoGeografica.setDataVigenciaInicio(Util.converteStringParaDate(helper.getDataVigenciaInicio()));
				rdLimitacaoGeografica.setDataVigenciaFim(Util.converteStringParaDate(helper.getDataVigenciaFim()));
				rdLimitacaoGeografica.setResolucaoDiretoria(resolucaoDiretoria);
				rdLimitacaoGeografica.setUltimaAlteracao(new Date());
				
				if(helper.getIdGerenciaRegional() != null){
					
					FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
					filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,helper.getIdGerenciaRegional()));
					
					Collection colecaoGerenciaRegional = 
							this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
					
					if (!Util.isVazioOrNulo(colecaoGerenciaRegional)){
						GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
						rdLimitacaoGeografica.setGerenciaRegional(gerenciaRegional);
					}
				}
				
				if(helper.getIdUnidadeNegocio() != null){
					
					FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,helper.getIdUnidadeNegocio()));
				
					Collection colecaoUnidadeNegocio = 
							this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
					
					if (!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
						UnidadeNegocio unidadeNegocio  = (UnidadeNegocio ) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
						rdLimitacaoGeografica.setUnidadeNegocio(unidadeNegocio);
					}
				}
				
				Integer idRdLimitacaoGeografica = null;
				if(!colecaoInserida.containsValue(rdLimitacaoGeografica)){
					
					idRdLimitacaoGeografica = (Integer) this.getFachada().inserir(rdLimitacaoGeografica);
					rdLimitacaoGeografica.setId(idRdLimitacaoGeografica);
					colecaoInserida.put(rdLimitacaoGeografica,rdLimitacaoGeografica);
				}else{
					
					Collection<RdLimitacaoGeografica> colecaoPesquisa = colecaoInserida.values();
					
					Iterator itera = colecaoPesquisa.iterator();
					
					while (itera.hasNext()) {
						RdLimitacaoGeografica objectComparacao = (RdLimitacaoGeografica) itera.next();
						
						if(objectComparacao.equals(rdLimitacaoGeografica)){
							rdLimitacaoGeografica = objectComparacao;
							break;
						}
					}
				}
				
				
				rdLimitacaoGeograficaLocalidade.setRdLimitacaoGeografica(rdLimitacaoGeografica);
				
				if (helper.getIdLocalidade() != null){ 
					
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,helper.getIdLocalidade()));
					
					Collection colecaoLocalidade = 
							this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
				
					if (!Util.isVazioOrNulo(colecaoLocalidade)){
						Localidade localidade  = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
						rdLimitacaoGeograficaLocalidade.setLocalidade(localidade);
						
					}
				}
				
				if (helper.getCodigoSetorComercial() != null){ 
					
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,helper.getIdLocalidade()));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,helper.getCodigoSetorComercial()));
				
					Collection colecaoSetorComercial = 
						this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
					if (!Util.isVazioOrNulo(colecaoSetorComercial)){
						SetorComercial setorComercial  = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
						rdLimitacaoGeograficaLocalidade.setSetorComercial(setorComercial);
					}
				}
				
				if (helper.getNumeroQuadra() != null){ 
					
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,rdLimitacaoGeograficaLocalidade.getSetorComercial().getId()));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,helper.getNumeroQuadra()));
				
					Collection colecaoQuadra = 
						this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			
					if (!Util.isVazioOrNulo(colecaoQuadra)){
						Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
						rdLimitacaoGeograficaLocalidade.setQuadra(quadra);
					}
				}
					
				rdLimitacaoGeograficaLocalidade.setUltimaAlteracao(new Date());
				
				this.getFachada().inserir(rdLimitacaoGeograficaLocalidade);
			}	
		}
	
		//RM8255 - adicionado por Vivianne Sousa - 12/11/2012
		Collection<RdRestricaoUsuario> colecaoRdRestricaoUsuario = 
				(Collection<RdRestricaoUsuario>) sessao.getAttribute("colecaoRdRestricaoUsuario");
		
		if(colecaoRdRestricaoUsuario != null && !colecaoRdRestricaoUsuario.isEmpty()){
			
			Iterator<RdRestricaoUsuario> iterRdRestricaoUsuario = colecaoRdRestricaoUsuario.iterator();
			
			while(iterRdRestricaoUsuario.hasNext()){	
				RdRestricaoUsuario rdRestricaoUsuario = (RdRestricaoUsuario) iterRdRestricaoUsuario.next();
				rdRestricaoUsuario.setResolucaoDiretoria(resolucaoDiretoria);
				rdRestricaoUsuario.setUltimaAlteracao(new Date());
				this.getFachada().inserir(rdRestricaoUsuario);
			}
		}
		

		// Monta a página de sucesso de acordo com o padrão do sistema.
		montarPaginaSucesso(httpServletRequest, "Resolução de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " inserida com sucesso.",
				"Inserir outra Resolução de Diretoria",
				"exibirInserirResolucaoDiretoriaAction.do?menu=sim",
				"exibirAtualizarResolucaoDiretoriaAction.do?inserir=sim&resolucaoDiretoriaID="
						+ id, "Atualizar Resolução de Diretoria inserida");

		return retorno;

	}
	

}