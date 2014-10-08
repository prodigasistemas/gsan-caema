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

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContratoDemandaImovelHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.contratodemanda.ContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaComercialIndustrial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0512] Inserir Contrato de Demanda
 * 
 * @author Eduardo Bianchi
 * @date 15/02/2007
 */

public class InserirContratoDemandaAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirContratoDemandaActionForm inserirContratoDemandaActionForm = (InserirContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		Collection<ContratoDemandaImovelHelper> colecaoContratoDemandaImovel = (Collection<ContratoDemandaImovelHelper>) 
				sessao.getAttribute("colecaoContratoDemandaImovel");
		
		if(Util.isVazioOrNulo(colecaoContratoDemandaImovel)){
			throw new ActionServletException("atencao.imoveis_nao_informado_contrato_demanda");
		}
		
		if(inserirContratoDemandaActionForm.getPercentualEsgoto() != null && !inserirContratoDemandaActionForm.getPercentualEsgoto().equals("") 
				&& inserirContratoDemandaActionForm.getVolumeEsgoto() != null && !inserirContratoDemandaActionForm.getVolumeEsgoto().equals("")){
			throw new ActionServletException("atencao.valores_esgotos_informados");
		}
		
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(Integer.parseInt(inserirContratoDemandaActionForm.getTarifaConsumo()));
		
		String numeroContratoDemanda = inserirContratoDemandaActionForm.getNumeroContrato();
		this.validarNumeroContratoDemanda(numeroContratoDemanda, fachada);
		this.validarCategoriasImoveis(colecaoContratoDemandaImovel, consumoTarifa.getId(), fachada);
		
		ContratoDemandaComercialIndustrial contratoDemanda = new ContratoDemandaComercialIndustrial();
		contratoDemanda.setNumeroContrato(numeroContratoDemanda);
		contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(inserirContratoDemandaActionForm.getDataInicioContrato()));
		contratoDemanda.setDataContratoFim(Util.converteStringParaDate(inserirContratoDemandaActionForm.getDataFimContrato()));
		contratoDemanda.setNumeroVolumeAgua(Integer.parseInt(inserirContratoDemandaActionForm.getVolumeAgua()));
		contratoDemanda.setUltimaAlteracao(new Date());
		contratoDemanda.setConsumoTarifa(consumoTarifa);
		
		if(inserirContratoDemandaActionForm.getPercentualEsgoto() != null && !inserirContratoDemandaActionForm.getPercentualEsgoto().equals("")){
			BigDecimal percentualEsgoto = Util.formatarMoedaRealparaBigDecimal(inserirContratoDemandaActionForm.getPercentualEsgoto()); 
			
			if(percentualEsgoto.compareTo(new BigDecimal("100")) > 0){
				throw new ActionServletException("atencao.percentual_maior_percentual_maximo");
			}
			
			contratoDemanda.setPercentualColetaEsgoto(percentualEsgoto);
		}
		
		String numeroVolumeInformadoEsgoto = null; 
		if(inserirContratoDemandaActionForm.getVolumeEsgoto() != null && !inserirContratoDemandaActionForm.getVolumeEsgoto().equals("")){
			numeroVolumeInformadoEsgoto = inserirContratoDemandaActionForm.getVolumeEsgoto();
			contratoDemanda.setNumeroVolumeInformadoFixadoEsgoto(Integer.parseInt(inserirContratoDemandaActionForm.getVolumeEsgoto()));
		}
		
		if(inserirContratoDemandaActionForm.getPercentualValorTarifaAgua() != null 
				&& !inserirContratoDemandaActionForm.getPercentualValorTarifaAgua().equals("")){
			BigDecimal percentualAgua = Util.formatarMoedaRealparaBigDecimal(inserirContratoDemandaActionForm.getPercentualValorTarifaAgua());
			
			if(percentualAgua.compareTo(new BigDecimal("100")) > 0){
				throw new ActionServletException("atencao.percentual_maior_percentual_maximo");
			}
			
			contratoDemanda.setPercentualValorTarifaAgua(percentualAgua);
		}
		
		Integer idContratoDemanda = fachada.inserirNovoContratoDemanda(contratoDemanda, 
				colecaoContratoDemandaImovel, usuarioLogado, numeroVolumeInformadoEsgoto);
		
		Iterator icolecaoContratoDemandaImovel = colecaoContratoDemandaImovel.iterator();
		
		while (icolecaoContratoDemandaImovel.hasNext()){
			
			ContratoDemandaImovelHelper helper = (ContratoDemandaImovelHelper) icolecaoContratoDemandaImovel.next();
			
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID_IMOVEL, helper.getIdImovel()));
			
			Collection<LigacaoAgua> colecaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
			
			if (!colecaoLigacaoAgua.isEmpty()){
				
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				
				ligacaoAgua.setNumeroConsumoMinimoAgua(null);
				
				fachada.atualizar(ligacaoAgua);
				
			}
			
			
			FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.IMOVEL_ID, helper.getIdImovel()));
			
			Collection<LigacaoEsgoto> colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
			
			if (!colecaoLigacaoEsgoto.isEmpty()){
				
				LigacaoEsgoto ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
				
				ligacaoEsgoto.setConsumoMinimo(null);
				
				fachada.atualizar(ligacaoEsgoto);
				
			}
			
		}
		
		// Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de número "
				+ contratoDemanda.getNumeroContrato() 
				+ " inserido com sucesso.",
				"Inserir outro Contrato de Demanda",
				"exibirInserirContratoDemandaAction.do?menu=sim",
				"exibirAtualizarContratoDemandaAction.do?inserir=sim&contratoDemandaID="
				+ idContratoDemanda,
		"Atualizar Contrato de Demanda Inserido");
		
		sessao.setAttribute("filtrar", "filtrar");
		sessao.setAttribute("inserir", "inserir");
		sessao.removeAttribute("InserirContratoDemandaActionForm");
		
		return retorno;
	}
	
	private void validarNumeroContratoDemanda(String numeroContratoDemanda, Fachada fachada){
		FiltroContratoDemandaComercialIndustrial filtro = new FiltroContratoDemandaComercialIndustrial();
		filtro.adicionarParametro(new ParametroSimples(FiltroContratoDemandaComercialIndustrial.NUMEROCONTRATO, numeroContratoDemanda));
		
		Collection<?> colecaoContratoDemanda = fachada.pesquisar(filtro, ContratoDemandaComercialIndustrial.class.getName());
		if(!Util.isVazioOrNulo(colecaoContratoDemanda)){
			throw new ActionServletException("atencao.existe_contrato_demanda_numero_informado", null, numeroContratoDemanda);
		}
	}
	
	private void validarCategoriasImoveis(Collection<ContratoDemandaImovelHelper> colecaoHelper, Integer idConsumoTarifa, Fachada fachada){
		Collection<Integer> colecaoImoveis = new ArrayList<Integer>();
		
		ContratoDemandaImovelHelper helper = null;
		
		Iterator<?> it = colecaoHelper.iterator();
		while(it.hasNext()){
			helper = (ContratoDemandaImovelHelper) it.next();
			
			colecaoImoveis.add(Integer.parseInt(helper.getIdImovel()));
		}
		
		fachada.verificarExistenciaCategoriaConsumoTarifaImoveisContratoDemanda(colecaoImoveis, idConsumoTarifa);
	}
	
}
