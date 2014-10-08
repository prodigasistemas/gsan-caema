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

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.LimitacaoGeograficaRDHelper;
import gcom.cobranca.RdLimitacaoGeografica;
import gcom.cobranca.RdLimitacaoGeograficaLocalidade;
import gcom.cobranca.RdRestricaoUsuario;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarResolucaoDiretoriaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarResolucaoDiretoria");
		
		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (httpServletRequest.getParameter("inserir") != null
				&& !httpServletRequest.getParameter("inserir").equals("")) {
			String inserir = httpServletRequest.getParameter("inserir");
			httpServletRequest.setAttribute("inserir", inserir);
		}
		
		if(httpServletRequest.getParameter("addLimitacaoGeografica") != null){
			httpServletRequest.setAttribute("addLimitacaoGeograficaChamarPopup", "true");
		}
		
		if(httpServletRequest.getParameter("remover") != null 
				&& httpServletRequest.getParameter("remover").equals("sim")) {
			this.removerLimitacaoGeografica(atualizarResolucaoDiretoriaActionForm, httpServletRequest);
		}
		
		if(httpServletRequest.getParameter("desfazer") != null &&
				httpServletRequest.getParameter("desfazer").equals("sim")){
			sessao.removeAttribute("collectionRDHelper");
		}
		
		if(sessao.getAttribute("tipoRetorno") != null){
			if(sessao.getAttribute("tipoRetorno").equals("1")){
				httpServletRequest.setAttribute("tipoRetorno", "1");
			}else if(sessao.getAttribute("tipoRetorno").equals("2")){
				httpServletRequest.setAttribute("tipoRetorno", "2");
			}
		}else{
			httpServletRequest.setAttribute("tipoRetorno", "1");
		}
		
		String veioAdicionar = httpServletRequest.getParameter("veioAdicionar");
		
		if(veioAdicionar == null){
			if (sessao.getAttribute("resolucaoDiretoria") != null) {
	
				ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
						.getAttribute("resolucaoDiretoria");
	
				atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
				atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
				atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
				atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorValidoAcaoCobranca(resolucaoDiretoria.getIndicadorValidoAcaoCobranca().toString());
				
				if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
						!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
					atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
				}
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
				
				if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
						!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
					atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
				}
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());
				
				//RM8248 - adicionado por Vivianne Sousa - 22/11/2012
				if (resolucaoDiretoria.getValorDebitoMinimo()!= null &&
						!resolucaoDiretoria.getValorDebitoMinimo().equals("")){
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMinimo(Util.formatarMoedaReal(
							resolucaoDiretoria.getValorDebitoMinimo()));
				}else{
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMinimo("");
				}
				
				if (resolucaoDiretoria.getValorDebitoMaximo()!= null &&
						!resolucaoDiretoria.getValorDebitoMaximo().equals("")){
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMaximo(Util.formatarMoedaReal(
							resolucaoDiretoria.getValorDebitoMaximo()));
				}else{
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMaximo("");
				}
				
				sessao.setAttribute("assuntoRD", atualizarResolucaoDiretoriaActionForm.getAssunto());
				sessao.setAttribute("dataInicioVigencia", atualizarResolucaoDiretoriaActionForm.getDataInicio());
				sessao.setAttribute("dataFinalVigencia", atualizarResolucaoDiretoriaActionForm.getDataFim());
				
				sessao.setAttribute("resolucaoDiretoriaAtualizar",resolucaoDiretoria);
				
				sessao.setAttribute("resolucaoDiretoria", resolucaoDiretoria);
				sessao.removeAttribute("colecaoRdRestricaoUsuario");
			} else {
	
				String idResolucaoDiretoria = httpServletRequest.getParameter("resolucaoDiretoriaID");
	
				FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
				filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));
	
				Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
	
				if (Util.isVazioOrNulo(colecaoResolucaoDiretoria)) {
					throw new ActionServletException("atencao.atualizacao.timestamp");
				}
	
				ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucaoDiretoria.iterator().next();
	
				atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
				atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
				atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
				atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
				atualizarResolucaoDiretoriaActionForm.setIndicadorValidoAcaoCobranca(resolucaoDiretoria.getIndicadorValidoAcaoCobranca().toString());
				
				if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
						!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
					atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
				}
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
				
				if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
						!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
					atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
				}
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
				
				atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());
				
				//RM8248 - adicionado por Vivianne Sousa - 22/11/2012
				if (resolucaoDiretoria.getValorDebitoMinimo()!= null &&
						!resolucaoDiretoria.getValorDebitoMinimo().equals("")){
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMinimo(Util.formatarMoedaReal(
							resolucaoDiretoria.getValorDebitoMinimo()));
				}else{
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMinimo("");
				}
				
				if (resolucaoDiretoria.getValorDebitoMaximo()!= null &&
						!resolucaoDiretoria.getValorDebitoMaximo().equals("")){
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMaximo(Util.formatarMoedaReal(
							resolucaoDiretoria.getValorDebitoMaximo()));
				}else{
					atualizarResolucaoDiretoriaActionForm.setValorDebitoMaximo("");
				}
				
				sessao.setAttribute("assuntoRD", atualizarResolucaoDiretoriaActionForm.getAssunto());
				sessao.setAttribute("dataInicioVigencia", atualizarResolucaoDiretoriaActionForm.getDataInicio());
				sessao.setAttribute("dataFinalVigencia", atualizarResolucaoDiretoriaActionForm.getDataFim());
				
				sessao.setAttribute("resolucaoDiretoriaAtualizar",resolucaoDiretoria);
				sessao.setAttribute("resolucaoDiretoria", resolucaoDiretoria);
				sessao.removeAttribute("colecaoRdRestricaoUsuario");
			}
		}else{
			if(atualizarResolucaoDiretoriaActionForm.getAssunto() == null 
					|| atualizarResolucaoDiretoriaActionForm.getAssunto().equals("")){
				if(sessao.getAttribute("assuntoRD") != null){
					atualizarResolucaoDiretoriaActionForm.setAssunto((String) sessao.getAttribute("assuntoRD"));
				}
			}else{
				sessao.setAttribute("assuntoRD", atualizarResolucaoDiretoriaActionForm.getAssunto());
			}
			
			if(atualizarResolucaoDiretoriaActionForm.getDataInicio() == null 
					|| atualizarResolucaoDiretoriaActionForm.getDataInicio().equals("")){
				if(sessao.getAttribute("dataInicioVigencia") != null){
					atualizarResolucaoDiretoriaActionForm.setDataInicio((String) sessao.getAttribute("dataInicioVigencia"));
				}
			}else{
				sessao.setAttribute("dataInicioVigencia", atualizarResolucaoDiretoriaActionForm.getDataInicio());
			}
			
			if(atualizarResolucaoDiretoriaActionForm.getDataFim() == null 
					|| atualizarResolucaoDiretoriaActionForm.getDataFim().equals("")){
				if(sessao.getAttribute("dataFinalVigencia") != null){
					atualizarResolucaoDiretoriaActionForm.setDataFim((String) sessao.getAttribute("dataFinalVigencia"));
				}
			}else{
				sessao.setAttribute("dataFinalVigencia", atualizarResolucaoDiretoriaActionForm.getDataFim());
			}
		}
		
		this.pesquisarLimitacaoGeografica(atualizarResolucaoDiretoriaActionForm, httpServletRequest);
		
		this.pesquisarRdRestricaoUsuario(atualizarResolucaoDiretoriaActionForm,	httpServletRequest);
		
		return retorno;
	}

	private void removerLimitacaoGeografica(AtualizarResolucaoDiretoriaActionForm form,
			HttpServletRequest httpServletRequest) {
		
		HttpSession sessao  = httpServletRequest.getSession();
		
		String idRdLimitacao = httpServletRequest.getParameter("rdLimitacao");
		
		Collection<?> collectionLimitacaoGeograficaRDHelper = (Collection<?>)
			sessao.getAttribute("collectionRDHelper");
		
		if(!Util.isVazioOrNulo(collectionLimitacaoGeograficaRDHelper)){
			Iterator<?> iterator = collectionLimitacaoGeograficaRDHelper.iterator();
			
			int count = 1;
			while(iterator.hasNext()){
				LimitacaoGeograficaRDHelper helper = (LimitacaoGeograficaRDHelper) iterator.next();
				
				if(idRdLimitacao.equals(""+count)){
					collectionLimitacaoGeograficaRDHelper.remove(helper);
					break;
				}
				count++;
			}
		
			sessao.setAttribute("collectionRDHelper", collectionLimitacaoGeograficaRDHelper);
		}
		
	}

	private void pesquisarLimitacaoGeografica(AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm,
			HttpServletRequest httpServletRequest) {
		
		HttpSession sessao = httpServletRequest.getSession();
		String numeroRD = atualizarResolucaoDiretoriaActionForm.getNumero();
		
		Collection<LimitacaoGeograficaRDHelper> collectionRDHelper = 
				(Collection) sessao.getAttribute("collectionRDHelper");
		
		if(collectionRDHelper == null){
			collectionRDHelper = new ArrayList<LimitacaoGeograficaRDHelper>();
		
			Collection<RdLimitacaoGeograficaLocalidade> colLimitacaoGeograficaLocalidade =
					this.getFachada().obterRDLimitacaoGeografica(numeroRD);
			
			if(!Util.isVazioOrNulo(colLimitacaoGeograficaLocalidade)){
				Iterator<?> iterator = colLimitacaoGeograficaLocalidade.iterator();
				
				while (iterator.hasNext()){
					RdLimitacaoGeograficaLocalidade rdLimitacaoGeograficaLocalidade = 
						(RdLimitacaoGeograficaLocalidade) iterator.next();
					
					collectionRDHelper.add(this.montarHelper(rdLimitacaoGeograficaLocalidade));
				}
			}
		}
		
		if(httpServletRequest.getParameter("veioAdicionar") != null && httpServletRequest.getParameter("veioAdicionar").equals("true")){
			Collection<?> collectionLimitacaoGeograficaRDHelper = (Collection<?>)
				sessao.getAttribute("collectionLimitacaoGeograficaRDHelper");
			
			if(!Util.isVazioOrNulo(collectionLimitacaoGeograficaRDHelper)){
				Iterator<?> iter = collectionLimitacaoGeograficaRDHelper.iterator();
				while(iter.hasNext()){
					LimitacaoGeograficaRDHelper helper = (LimitacaoGeograficaRDHelper) iter.next();
					
					Collection<LimitacaoGeograficaRDHelper> collectionRDHelperAux = 
						(Collection<LimitacaoGeograficaRDHelper>) sessao.getAttribute("collectionRDHelper");
					if(!collectionRDHelperAux.contains(helper)){
						collectionRDHelper.add(helper);
					}
				}
			}
			sessao.removeAttribute("collectionLimitacaoGeograficaRDHelper");
		}
		
		sessao.setAttribute("collectionRDHelper", collectionRDHelper);
		
	}

	private LimitacaoGeograficaRDHelper montarHelper(RdLimitacaoGeograficaLocalidade rdLimitacaoGeograficaLocalidade) {
		LimitacaoGeograficaRDHelper helper = new LimitacaoGeograficaRDHelper();
		
		if(rdLimitacaoGeograficaLocalidade != null){
			if(rdLimitacaoGeograficaLocalidade.getLocalidade() != null){
				helper.setIdLocalidade(rdLimitacaoGeograficaLocalidade.getLocalidade().getId());
			}
			
			if(rdLimitacaoGeograficaLocalidade.getSetorComercial() != null){
				helper.setCodigoSetorComercial(rdLimitacaoGeograficaLocalidade.getSetorComercial().getCodigo());
			}
			
			if(rdLimitacaoGeograficaLocalidade.getQuadra() != null){
				helper.setNumeroQuadra(rdLimitacaoGeograficaLocalidade.getQuadra().getNumeroQuadra());
			}
		}
		
		RdLimitacaoGeografica rdLimitacaoGeografica = rdLimitacaoGeograficaLocalidade.getRdLimitacaoGeografica();
		
		if(rdLimitacaoGeografica != null){
			if(rdLimitacaoGeografica.getDataLimiteVencimentoContaParcelar() != null){
				helper.setDataLimiteVencimentoContaParcelar(
					Util.formatarData(rdLimitacaoGeografica.getDataLimiteVencimentoContaParcelar()));
			}
			
			if(rdLimitacaoGeografica.getDataLimiteVencimentoContaVista() != null){
				helper.setDataLimiteVencimentoContaVista(
					Util.formatarData(rdLimitacaoGeografica.getDataLimiteVencimentoContaVista()));
			}
			
			if(rdLimitacaoGeografica.getDataVigenciaInicio() != null){
				helper.setDataVigenciaInicio(
					Util.formatarData(rdLimitacaoGeografica.getDataVigenciaInicio()));
			}
			
			if(rdLimitacaoGeografica.getDataVigenciaFim() != null){
				helper.setDataVigenciaFim(
					Util.formatarData(rdLimitacaoGeografica.getDataVigenciaFim()));
			}
			
			if(rdLimitacaoGeografica.getUnidadeNegocio() != null){
				helper.setIdUnidadeNegocio(rdLimitacaoGeografica.getUnidadeNegocio().getId()+"");
			}
			
			if(rdLimitacaoGeografica.getGerenciaRegional() != null){
				helper.setIdGerenciaRegional(rdLimitacaoGeografica.getGerenciaRegional().getId()+"");
			}
		}
		
		return helper;
	}

	//RM8255 - adicionado por Vivianne Sousa - 12/11/2012
	private void pesquisarRdRestricaoUsuario(
			AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm,
			HttpServletRequest httpServletRequest) {
		
		HttpSession sessao = httpServletRequest.getSession();
		
		Collection<RdRestricaoUsuario> colecaoRdRestricaoUsuario = 
				(Collection) sessao.getAttribute("colecaoRdRestricaoUsuario");
		
		if(colecaoRdRestricaoUsuario == null){
			colecaoRdRestricaoUsuario = new ArrayList<RdRestricaoUsuario>();
		
			colecaoRdRestricaoUsuario = this.getFachada().
					obterRdRestricaoUsuario(atualizarResolucaoDiretoriaActionForm.getNumero());
			
			if(colecaoRdRestricaoUsuario != null && !colecaoRdRestricaoUsuario.isEmpty()){
				atualizarResolucaoDiretoriaActionForm.setIndicadorAcessoRestrito(ConstantesSistema.SIM.toString());
			}
		}
		
		//Usuário
		if (atualizarResolucaoDiretoriaActionForm.getLoginUsuario() != null &&
			!atualizarResolucaoDiretoriaActionForm.getLoginUsuario().equals("")) {
			
			Usuario usuario = getUsuario(atualizarResolucaoDiretoriaActionForm, 
					atualizarResolucaoDiretoriaActionForm.getLoginUsuario(), sessao);
			
			if(httpServletRequest.getParameter("associarUsuario") != null){
					
				if(colecaoRdRestricaoUsuario == null){
					colecaoRdRestricaoUsuario = new ArrayList();	
				}  
		
				if(usuario != null){
					RdRestricaoUsuario rdRestricaoUsuario = new RdRestricaoUsuario();
					rdRestricaoUsuario.setUsuario(usuario);
					colecaoRdRestricaoUsuario.add(rdRestricaoUsuario);
					
					atualizarResolucaoDiretoriaActionForm.setLoginUsuario("");
					atualizarResolucaoDiretoriaActionForm.setNomeUsuario("");
				}
				
			}
			
		}
		
		
		if (httpServletRequest.getParameter("removerUsuario") != null && (httpServletRequest.getParameter("removerUsuario").equals("sim"))){
			
			String idRdRestricaoUsuario = (String) httpServletRequest.getParameter("idRdRestricaoUsuario");
			
			if(!Util.isVazioOrNulo(colecaoRdRestricaoUsuario)){
				Iterator  iterator =  colecaoRdRestricaoUsuario.iterator();
				
				int count = 1;
				while(iterator.hasNext()){
					RdRestricaoUsuario rdRestricaoUsuario = (RdRestricaoUsuario) iterator.next();
					
					if(idRdRestricaoUsuario.equals(""+count)){
						colecaoRdRestricaoUsuario.remove(rdRestricaoUsuario);
						break;
					}
					count++;
				}
			}
			
		}
		
		sessao.setAttribute("colecaoRdRestricaoUsuario", colecaoRdRestricaoUsuario);
		
		if(httpServletRequest.getParameter("limparColecaoUsuario") != null){
			sessao.removeAttribute("colecaoRdRestricaoUsuario");
		}
	}
	
	/**
	 * Recupera o Usuário
	 */
	private Usuario getUsuario(AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm, 
			String loginUsuario, HttpSession sessao) {
		Usuario usuario = null;
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		//filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			atualizarResolucaoDiretoriaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			atualizarResolucaoDiretoriaActionForm.setLoginUsuario("");
			atualizarResolucaoDiretoriaActionForm.setNomeUsuario("Usuário Inexistente");
		}
		return usuario;
	}
}