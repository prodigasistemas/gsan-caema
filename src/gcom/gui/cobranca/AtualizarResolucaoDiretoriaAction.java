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

import java.util.ArrayList;
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

public class AtualizarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
				.getAttribute("resolucaoDiretoriaAtualizar");

		resolucaoDiretoria.setNumeroResolucaoDiretoria(atualizarResolucaoDiretoriaActionForm.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(atualizarResolucaoDiretoriaActionForm.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes()));
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
		resolucaoDiretoria.setIndicadorValidoAcaoCobranca(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorValidoAcaoCobranca()));
		
		if (atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso())));
			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
				rdParcelasEmAtraso.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()));
				resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
			}else{
				//RD Parcelas em Atraso inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}
		}

		if (atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento())));

			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
				rdParcelamentoEmAndamento.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()));
				resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
			}else{
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}
			
		}
		if (atualizarResolucaoDiretoriaActionForm.getDataFim() != null 
				&& !atualizarResolucaoDiretoriaActionForm.getDataFim().equals("")) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataFim()));
		}
		
		//RM8248 - adicionado por Vivianne Sousa - 22/11/2012
		// verifica se valor mínimo foi digitado em caso afirmativo seta-a no objeto
		if (atualizarResolucaoDiretoriaActionForm.getValorDebitoMinimo() != null
				&& !atualizarResolucaoDiretoriaActionForm.getValorDebitoMinimo().equals("")) {
			resolucaoDiretoria.setValorDebitoMinimo(Util.formatarMoedaRealparaBigDecimal(
					atualizarResolucaoDiretoriaActionForm.getValorDebitoMinimo()));
		}
		
		// verifica se valor máximo foi digitado em caso afirmativo seta-a no objeto
		if (atualizarResolucaoDiretoriaActionForm.getValorDebitoMaximo() != null
				&& !atualizarResolucaoDiretoriaActionForm.getValorDebitoMaximo().equals("")) {
			resolucaoDiretoria.setValorDebitoMaximo(Util.formatarMoedaRealparaBigDecimal(
					atualizarResolucaoDiretoriaActionForm.getValorDebitoMaximo()));
		}
		
		Collection<?> colecaoRdLimitacaoHelper =  (Collection<?>)
				sessao.getAttribute("collectionRDHelper");
		
		Collection<RdLimitacaoGeograficaLocalidade> colRDLimGeoLoc = null;
		Collection<RdLimitacaoGeografica> colRDLimGeo = null;
		
		if(!Util.isVazioOrNulo(colecaoRdLimitacaoHelper)){
			String numeroRD = atualizarResolucaoDiretoriaActionForm.getNumero();
			Collection<RdLimitacaoGeograficaLocalidade> colLimitacaoGeograficaLocalidade =
					this.getFachada().obterRDLimitacaoGeografica(numeroRD);
			
			if(!Util.isVazioOrNulo(colLimitacaoGeograficaLocalidade)){
				colRDLimGeoLoc = new ArrayList<RdLimitacaoGeograficaLocalidade>();
				colRDLimGeo = new ArrayList<RdLimitacaoGeografica>();
				
				Iterator<?> iterator = colLimitacaoGeograficaLocalidade.iterator();
				while(iterator.hasNext()){
					RdLimitacaoGeograficaLocalidade rdLimGeoLoc = (RdLimitacaoGeograficaLocalidade) iterator.next();
					RdLimitacaoGeografica rdLimGeo = rdLimGeoLoc.getRdLimitacaoGeografica();
					
					colRDLimGeoLoc.add(rdLimGeoLoc);
					
					if(!colRDLimGeo.contains(rdLimGeo)){
						colRDLimGeo.add(rdLimGeo);
					}
				}
				this.removerValores(colRDLimGeoLoc, colRDLimGeo);
			}
			
			this.inserirValores(colecaoRdLimitacaoHelper, resolucaoDiretoria);
		}else{
			String numeroRD = atualizarResolucaoDiretoriaActionForm.getNumero();
			Collection<RdLimitacaoGeograficaLocalidade> colLimitacaoGeograficaLocalidade =
					this.getFachada().obterRDLimitacaoGeografica(numeroRD);
			
			if(!Util.isVazioOrNulo(colLimitacaoGeograficaLocalidade)){
				colRDLimGeoLoc = new ArrayList<RdLimitacaoGeograficaLocalidade>();
				colRDLimGeo = new ArrayList<RdLimitacaoGeografica>();
				
				Iterator<?> iterator = colLimitacaoGeograficaLocalidade.iterator();
				while(iterator.hasNext()){
					RdLimitacaoGeograficaLocalidade rdLimGeoLoc = (RdLimitacaoGeograficaLocalidade) iterator.next();
					RdLimitacaoGeografica rdLimGeo = rdLimGeoLoc.getRdLimitacaoGeografica();
					
					colRDLimGeoLoc.add(rdLimGeoLoc);
					
					if(!colRDLimGeo.contains(rdLimGeo)){
						colRDLimGeo.add(rdLimGeo);
					}
				}
				this.removerValores(colRDLimGeoLoc, colRDLimGeo);
			}
		}
			
		
		this.atualizarRDRestricaoUsuario(fachada, sessao,atualizarResolucaoDiretoriaActionForm, resolucaoDiretoria);
		
		
		fachada.atualizarResolucaoDiretoria(resolucaoDiretoria, this.getUsuarioLogado(httpServletRequest));

		montarPaginaSucesso(httpServletRequest, "Resolução de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Resolução de Diretoria",
				"exibirFiltrarResolucaoDiretoriaAction.do?menu=sim");

		return retorno;

	}

	//RM8255 - adicionado por Vivianne Sousa - 12/11/2012
	private void atualizarRDRestricaoUsuario(
			Fachada fachada,
			HttpSession sessao,
			AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm,
			ResolucaoDiretoria resolucaoDiretoria) {
	
		fachada.removerRdRestricaoUsuario(resolucaoDiretoria.getId().toString());
		
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
	}
	
	private void removerValores(Collection<RdLimitacaoGeograficaLocalidade> colRDLimGeoLoc, Collection<RdLimitacaoGeografica> colRDLimGeo) {
		if(!Util.isVazioOrNulo(colRDLimGeoLoc)){
			Iterator<RdLimitacaoGeograficaLocalidade> iterator = colRDLimGeoLoc.iterator();
			while(iterator.hasNext()){
				RdLimitacaoGeograficaLocalidade rdLimitacaoGeograficaLocalidade = (RdLimitacaoGeograficaLocalidade) iterator.next();
				
				this.getFachada().remover(rdLimitacaoGeograficaLocalidade);
			}
		}
		
		if(!Util.isVazioOrNulo(colRDLimGeo)){
			Iterator<RdLimitacaoGeografica> iterator = colRDLimGeo.iterator();
			while(iterator.hasNext()){
				RdLimitacaoGeografica rdLimitacaoGeografica = (RdLimitacaoGeografica) iterator.next();
				
				this.getFachada().remover(rdLimitacaoGeografica);
			}
		}
	}

	private void inserirValores(Collection<?> colecaoRdLimitacao, ResolucaoDiretoria resolucaoDiretoria) {
		RdLimitacaoGeograficaLocalidade rdLimitacaoGeograficaLocalidade = new RdLimitacaoGeograficaLocalidade();
		RdLimitacaoGeografica rdLimitacaoGeografica = new RdLimitacaoGeografica();
		
		HashMap<RdLimitacaoGeografica,RdLimitacaoGeografica> colecaoInserida = new HashMap<RdLimitacaoGeografica,RdLimitacaoGeografica>();
		
		Iterator<?> iterator = colecaoRdLimitacao.iterator();
		
		while(iterator.hasNext()){
			rdLimitacaoGeograficaLocalidade = new RdLimitacaoGeograficaLocalidade();
			rdLimitacaoGeografica = new RdLimitacaoGeografica();
			
			LimitacaoGeograficaRDHelper helper = (LimitacaoGeograficaRDHelper) iterator.next();
			
			if(helper.getDataLimiteVencimentoContaParcelar() != null && !helper.getDataLimiteVencimentoContaParcelar().equals("")){
				rdLimitacaoGeografica.setDataLimiteVencimentoContaParcelar(
					Util.converteStringParaDate(helper.getDataLimiteVencimentoContaParcelar()));
			}
			
			if(helper.getDataLimiteVencimentoContaVista() != null && !helper.getDataLimiteVencimentoContaVista().equals("")){
				rdLimitacaoGeografica.setDataLimiteVencimentoContaVista(
					Util.converteStringParaDate(helper.getDataLimiteVencimentoContaVista()));
			}
			
			if(helper.getDataVigenciaInicio() != null && !helper.getDataVigenciaInicio().equals("")){
				rdLimitacaoGeografica.setDataVigenciaInicio(
					Util.converteStringParaDate(helper.getDataVigenciaInicio()));
			}
			
			if(helper.getDataVigenciaFim() != null && !helper.getDataVigenciaFim().equals("")){
				rdLimitacaoGeografica.setDataVigenciaFim(
					Util.converteStringParaDate(helper.getDataVigenciaFim()));
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("-1")){
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, helper.getIdUnidadeNegocio()));
				
				Collection<?> colUnidade = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
				if(!Util.isVazioOrNulo(colUnidade)){
					UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colUnidade);
					rdLimitacaoGeografica.setUnidadeNegocio(unidadeNegocio);
				}
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("-1")){
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, helper.getIdGerenciaRegional()));
				
				Collection<?> colGerencia = getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
				if(!Util.isVazioOrNulo(colGerencia)){
					GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colGerencia);
					rdLimitacaoGeografica.setGerenciaRegional(gerenciaRegional);
				}
			}
			
			rdLimitacaoGeografica.setResolucaoDiretoria(resolucaoDiretoria);
			rdLimitacaoGeografica.setUltimaAlteracao(new Date());
			
			Integer idRDLimitacaoGeografica = null; 
			if(!colecaoInserida.containsValue(rdLimitacaoGeografica)){
				idRDLimitacaoGeografica = (Integer) getFachada().inserir(rdLimitacaoGeografica);
				rdLimitacaoGeografica.setId(idRDLimitacaoGeografica);
				colecaoInserida.put(rdLimitacaoGeografica, rdLimitacaoGeografica);
			}else{
				Collection<RdLimitacaoGeografica> colecaoPesquisa = colecaoInserida.values();
				
				Iterator<?> itera = colecaoPesquisa.iterator();
				while(itera.hasNext()){
					RdLimitacaoGeografica objetoComparacao = (RdLimitacaoGeografica) itera.next();
					
					if(objetoComparacao.equals(rdLimitacaoGeografica)){
						rdLimitacaoGeografica = objetoComparacao;
						break;
					}
				}
			}
			
			rdLimitacaoGeograficaLocalidade.setRdLimitacaoGeografica(rdLimitacaoGeografica);
			rdLimitacaoGeograficaLocalidade.setUltimaAlteracao(new Date());
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("-1")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, helper.getIdLocalidade()));
				
				Collection<?> colLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(!Util.isVazioOrNulo(colLocalidade)){
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
					rdLimitacaoGeograficaLocalidade.setLocalidade(localidade);
				}
			}
			
			if(helper.getCodigoSetorComercial() != null && !helper.getCodigoSetorComercial().equals("-1")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercial()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, helper.getIdLocalidade()));
				
				Collection<?> colSetorComercial = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if(!Util.isVazioOrNulo(colSetorComercial)){
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
					rdLimitacaoGeograficaLocalidade.setSetorComercial(setorComercial);
				}
			}
			
			if(helper.getNumeroQuadra() != null && !helper.getNumeroQuadra().equals("-1")){
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, helper.getNumeroQuadra()));
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, helper.getCodigoSetorComercial()));
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, helper.getIdLocalidade()));
				
				Collection<?> colQuadra = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
				
				if(!Util.isVazioOrNulo(colQuadra)){
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
					rdLimitacaoGeograficaLocalidade.setQuadra(quadra);
				}
			}
			
			getFachada().inserir(rdLimitacaoGeograficaLocalidade);
		}
	}
}
