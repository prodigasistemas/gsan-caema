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

import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContratoDemandaImovelHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.contratodemanda.ContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaComercialIndustrial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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

public class AtualizarContratoDemandaAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

		ContratoDemandaComercialIndustrial contratoDemanda = (ContratoDemandaComercialIndustrial) sessao.getAttribute("contratoDemandaAtualizar");
		
		Collection<ContratoDemandaImovelHelper> colecaoContratoDemandaImovel = (Collection<ContratoDemandaImovelHelper>) 
				sessao.getAttribute("colecaoContratoDemandaImovel");
		
		if(atualizarContratoDemandaActionForm.getTipoEsgoto() != null && !atualizarContratoDemandaActionForm.getTipoEsgoto().equals("")){
			if(atualizarContratoDemandaActionForm.getTipoEsgoto().equals("1")){
				atualizarContratoDemandaActionForm.setVolumeEsgoto("");
			}else if(atualizarContratoDemandaActionForm.getTipoEsgoto().equals("2")){
				atualizarContratoDemandaActionForm.setPercentualEsgoto("");
			}
		}
		
		//Caso não foi informado nenhum imovel
		if(Util.isVazioOrNulo(colecaoContratoDemandaImovel)){
			throw new ActionServletException("atencao.imoveis_nao_informado_contrato_demanda");
		}
		
		//Caso foi informado o percentual de esgoto e o volume de esgoto
		if(atualizarContratoDemandaActionForm.getPercentualEsgoto() != null && !atualizarContratoDemandaActionForm.getPercentualEsgoto().equals("") 
				&& atualizarContratoDemandaActionForm.getVolumeEsgoto() != null && !atualizarContratoDemandaActionForm.getVolumeEsgoto().equals("")){
			throw new ActionServletException("atencao.valores_esgotos_informados");
		}
		
		if(!contratoDemanda.getNumeroContrato().equals(atualizarContratoDemandaActionForm.getNumeroContrato())){
			this.validarNumeroContratoDemanda(atualizarContratoDemandaActionForm.getNumeroContrato(), fachada);
		}
		
		String dataInicioContrato = atualizarContratoDemandaActionForm.getDataInicioContrato();
		String dataFimContrato = atualizarContratoDemandaActionForm.getDataFimContrato();
		String dataEncerramento = atualizarContratoDemandaActionForm.getDataEncerramento();
		String idMotivoEncerramento = atualizarContratoDemandaActionForm.getIdMotivoEncerramento();
		String numeroVolumeAgua = atualizarContratoDemandaActionForm.getVolumeAgua();
		String idConsumoTarifa = atualizarContratoDemandaActionForm.getTarifaConsumo();
		String percentualColetaEsgoto = atualizarContratoDemandaActionForm.getPercentualEsgoto();
		String percentualValorTarifaAgua = atualizarContratoDemandaActionForm.getPercentualValorTarifaAgua();
		
		this.validarCategoriasImoveis(colecaoContratoDemandaImovel, Integer.parseInt(idConsumoTarifa), fachada);
		
		contratoDemanda.setNumeroContrato(atualizarContratoDemandaActionForm.getNumeroContrato());
		
		if (dataInicioContrato != null && !dataInicioContrato.trim().equals("")) {
			contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataInicioContrato()));
		} else {
			contratoDemanda.setDataContratoInicio(null);
		}
		
		if (dataFimContrato != null && !dataFimContrato.trim().equals("")) {
			contratoDemanda.setDataContratoFim(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataFimContrato()));
		} else {
			contratoDemanda.setDataContratoFim(null);
		}
		
		if (dataEncerramento != null && !dataEncerramento.trim().equals("")) {
			contratoDemanda.setDataContratoEncerrado(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataEncerramento()));
		} else {
			contratoDemanda.setDataContratoEncerrado(null);
		}
		
		if (idMotivoEncerramento != null && !idMotivoEncerramento.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ContratoDemandaMotivoEncerramento contratoMotivoEncerramento = new ContratoDemandaMotivoEncerramento();
			contratoMotivoEncerramento.setId(Integer.parseInt(idMotivoEncerramento));
			contratoDemanda.setMotivoEncerramento(contratoMotivoEncerramento);
		} else {
			contratoDemanda.setMotivoEncerramento(null);
		}
		
		if(numeroVolumeAgua != null && !numeroVolumeAgua.equals("")){
			contratoDemanda.setNumeroVolumeAgua(Integer.parseInt(numeroVolumeAgua));
		}else{
			contratoDemanda.setNumeroVolumeAgua(null);
		}
		
		if(idConsumoTarifa != null && !idConsumoTarifa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ConsumoTarifa consumoTarifa = new ConsumoTarifa();
			consumoTarifa.setId(Integer.parseInt(idConsumoTarifa));
			contratoDemanda.setConsumoTarifa(consumoTarifa);
		}else{
			contratoDemanda.setConsumoTarifa(null);
		}
		
		if(percentualColetaEsgoto != null && !percentualColetaEsgoto.equals("")){
			BigDecimal percentualEsgoto = Util.formatarMoedaRealparaBigDecimal(percentualColetaEsgoto); 
			
			if(percentualEsgoto.compareTo(new BigDecimal("100")) > 0){
				throw new ActionServletException("atencao.percentual_maior_percentual_maximo");
			}
			
			contratoDemanda.setPercentualColetaEsgoto(percentualEsgoto);
		}else{
			contratoDemanda.setPercentualColetaEsgoto(null);
		}
		
		if(percentualValorTarifaAgua != null && !percentualValorTarifaAgua.equals("")){
			BigDecimal percentualAgua = Util.formatarMoedaRealparaBigDecimal(percentualValorTarifaAgua);
			
			if(percentualAgua.compareTo(new BigDecimal("100")) > 0){
				throw new ActionServletException("atencao.percentual_maior_percentual_maximo");
			}
			
			contratoDemanda.setPercentualValorTarifaAgua(percentualAgua);
		}else{
			contratoDemanda.setPercentualValorTarifaAgua(null);
		}
		
		String volumeInformadoEsgoto = null;
		if(atualizarContratoDemandaActionForm.getVolumeEsgoto() != null && !atualizarContratoDemandaActionForm.getVolumeEsgoto().equals("")){
			volumeInformadoEsgoto = atualizarContratoDemandaActionForm.getVolumeEsgoto();
			contratoDemanda.setNumeroVolumeInformadoFixadoEsgoto(Integer.parseInt(volumeInformadoEsgoto));
		}
		
		contratoDemanda.setUltimaAlteracao(new Date());
		
		// Inserir na base de dados ContratoDemanda
		fachada.atualizarNovoContratoDemanda(contratoDemanda, colecaoContratoDemandaImovel, usuarioLogado, volumeInformadoEsgoto);
		
		// Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de número "
				+ contratoDemanda.getNumeroContrato()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Contrato de Demanda",
				"exibirFiltrarContratoDemandaAction.do?menu=sim");

		return retorno;

	}
	
	/**
	 * Validar se já existe algum contrato de demanda com o mesmo número
	 * 
	 * @author Davi Menezes
	 * @date 25/04/2013
	 * 
	 * @param numeroContratoDemanda
	 * @param fachada
	 */
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
