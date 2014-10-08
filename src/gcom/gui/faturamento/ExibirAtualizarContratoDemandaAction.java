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
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContratoDemandaImovelHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.contratodemanda.ContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.ContratoDemandaImovelComercialIndustrial;
import gcom.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaImovelComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaMotivoEncerramento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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

/**
 * Permite atualizar um contrato de demanda [UC0513] Manter Contrato de Demanda
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirAtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarContratoDemanda");

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroContratoDemandaMotivoEncerramento filtroContratoDemandaMotivoEncerramento = new FiltroContratoDemandaMotivoEncerramento(
				FiltroContratoDemandaMotivoEncerramento.DESCRICAO);
		
		filtroContratoDemandaMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroContratoDemandaMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoMotivoEncerramento = fachada.pesquisar(filtroContratoDemandaMotivoEncerramento, 
				ContratoDemandaMotivoEncerramento.class.getName());
		
		sessao.setAttribute("colecaoMotivoEncerramento", colecaoMotivoEncerramento);
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa(FiltroConsumoTarifa.DESCRICAO);
		
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_CONTRATO_DEMANDA, ConstantesSistema.SIM));
		
		Collection<?> colecaoConsumoTarifa = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);

		// Recupera os valores da unidade do form
		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		String inscricaoImovel = atualizarContratoDemandaActionForm.getInscricaoImovel();

		// Verifica se o usuário solicitou a pesquisa de unidade
		if (idImovel != null
				&& !idImovel.trim().equals("")
				&& (inscricaoImovel == null || inscricaoImovel.trim()
						.equals(""))) {

			try{
				Imovel imovel = fachada.pesquisarImovelDigitado(new Integer(
						idImovel));
	
				if (imovel != null) {
	
					atualizarContratoDemandaActionForm.setInscricaoImovel(imovel
							.getInscricaoFormatada());
					httpServletRequest.setAttribute("nomeCampo",
							"dataInicioContrato");
	
				} else {
	
					atualizarContratoDemandaActionForm
							.setInscricaoImovel("IMÓVEL INEXISTENTE");
					atualizarContratoDemandaActionForm.setIdImovel("");
					httpServletRequest.setAttribute("existeImovel", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idImovel");
	
				}
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.imovel_possui_caracteres_especiais");
			}

		} else if (inscricaoImovel != null
				&& !inscricaoImovel.trim().equals("")
				&& (idImovel == null || idImovel.trim().equals(""))) {
			atualizarContratoDemandaActionForm.setInscricaoImovel("");
		}
		
		// Verifica se o usuário está adicionando um novo imovel
		// ao contrato de demanda
		String adicionar = httpServletRequest.getParameter("adicionar");
		if(adicionar != null && adicionar.equals("sim")){
			this.adicionarImovelContratoDemanda(idImovel, fachada, sessao);
			atualizarContratoDemandaActionForm.setIdImovel("");
			atualizarContratoDemandaActionForm.setInscricaoImovel("");
		}
		
		// Verifica se o usuário está removendo o imóvel
		// do contraro de demanda
		String remover = httpServletRequest.getParameter("remover");
		String posicao = httpServletRequest.getParameter("posicao");
		if(remover != null && remover.equals("sim")){
			this.removerImovelContratoDemanda(posicao, sessao);
		}
		
		httpServletRequest.setAttribute("nomeCampo", "numeroContrato");

		// Verifica se o usuário está entrando pela primeira vez na tela ou se
		// ele selecionou a opção de desfazer
		if (sessao.getAttribute("contratoDemandaAtualizar") == null
				|| httpServletRequest.getParameter("desfazer") != null) {

			ContratoDemandaComercialIndustrial contratoDemanda = null;

			if (httpServletRequest.getParameter("desfazer") != null) {
				
				String idContratoDemanda = ((ContratoDemandaComercialIndustrial) sessao
						.getAttribute("contratoDemandaAtualizar")).getId().toString();

				FiltroContratoDemandaComercialIndustrial filtroContratoDemanda = new FiltroContratoDemandaComercialIndustrial();
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContratoDemandaComercialIndustrial.ID, idContratoDemanda));

				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONSUMO_TARIFA);
				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONTRATO_DEMANDA_MOTIVO_ENCERRAMENTO);
				
				Collection<?> colecaoContratoDemanda = fachada.pesquisar(
						filtroContratoDemanda, ContratoDemandaComercialIndustrial.class.getName());

				if (Util.isVazioOrNulo(colecaoContratoDemanda)) {
					throw new ActionServletException("atencao.atualizacao.timestamp");
				}

				contratoDemanda = (ContratoDemandaComercialIndustrial) Util.retonarObjetoDeColecao(colecaoContratoDemanda);

				sessao.setAttribute("contratoDemandaAtualizar", contratoDemanda);
				
				this.pesquisarImoveisContratoDemanda(Integer.parseInt(idContratoDemanda), fachada, sessao);

			} else {

				if (sessao.getAttribute("contratoDemanda") != null) {

					contratoDemanda = (ContratoDemandaComercialIndustrial) sessao.getAttribute("contratoDemanda");

					sessao.setAttribute("contratoDemandaAtualizar", contratoDemanda);
					sessao.removeAttribute("contratoDemanda");

					sessao.setAttribute("filtrar", true);

				} else {

					String idContratoDemanda = httpServletRequest.getParameter("contratoDemandaID");

					if (httpServletRequest.getParameter("inserir") != null) {
						sessao.setAttribute("inserir", true);
					} else {
						sessao.removeAttribute("filtrar");
						sessao.removeAttribute("inserir");
					}

					FiltroContratoDemandaComercialIndustrial filtroContratoDemanda = new FiltroContratoDemandaComercialIndustrial();
					filtroContratoDemanda.adicionarParametro(new ParametroSimples(
							FiltroContratoDemandaComercialIndustrial.ID, idContratoDemanda));

					filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONSUMO_TARIFA);
					filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONTRATO_DEMANDA_MOTIVO_ENCERRAMENTO);

					Collection<?> colecaoContratoDemanda = fachada.pesquisar(
							filtroContratoDemanda, ContratoDemandaComercialIndustrial.class.getName());

					if (Util.isVazioOrNulo(colecaoContratoDemanda)) {
						throw new ActionServletException("atencao.atualizacao.timestamp");
					}

					contratoDemanda = (ContratoDemandaComercialIndustrial) Util.retonarObjetoDeColecao(colecaoContratoDemanda);

					sessao.setAttribute("contratoDemandaAtualizar", contratoDemanda);
				}
			}

			this.pesquisarImoveisContratoDemanda(contratoDemanda.getId(), fachada, sessao);
			
			atualizarContratoDemandaActionForm.setNumeroContrato(contratoDemanda.getNumeroContrato());
			atualizarContratoDemandaActionForm.setIdImovel("");
			atualizarContratoDemandaActionForm.setInscricaoImovel("");
			atualizarContratoDemandaActionForm.setDataInicioContrato(Util.formatarData(contratoDemanda.getDataContratoInicio()));
			atualizarContratoDemandaActionForm.setDataFimContrato(Util.formatarData(contratoDemanda.getDataContratoFim()));
			atualizarContratoDemandaActionForm.setVolumeAgua(contratoDemanda.getNumeroVolumeAgua().toString());

			if (contratoDemanda.getDataContratoEncerrado() != null) {
				atualizarContratoDemandaActionForm.setDataEncerramento(Util.formatarData(contratoDemanda.getDataContratoEncerrado()));
				sessao.setAttribute("desabilitarAtualizar", true);
			} else {
				atualizarContratoDemandaActionForm.setDataEncerramento("");
				sessao.removeAttribute("desabilitarAtualizar");
			}

			if (contratoDemanda.getMotivoEncerramento() != null) {
				atualizarContratoDemandaActionForm.setIdMotivoEncerramento(contratoDemanda.getMotivoEncerramento().getId().toString());
			} else {
				atualizarContratoDemandaActionForm.setIdMotivoEncerramento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			}
			
			if(contratoDemanda.getConsumoTarifa() != null){
				atualizarContratoDemandaActionForm.setTarifaConsumo(contratoDemanda.getConsumoTarifa().getId().toString());
			} else {
				atualizarContratoDemandaActionForm.setTarifaConsumo("");
			}
			
			if(contratoDemanda.getPercentualValorTarifaAgua() != null){
				atualizarContratoDemandaActionForm.setPercentualValorTarifaAgua(Util.formatarMoedaReal(
						contratoDemanda.getPercentualValorTarifaAgua()));
			}else{
				atualizarContratoDemandaActionForm.setPercentualValorTarifaAgua("");
			}
			
			if(contratoDemanda.getPercentualColetaEsgoto() != null){
				atualizarContratoDemandaActionForm.setPercentualEsgoto(Util.formatarMoedaReal(contratoDemanda.getPercentualColetaEsgoto()));
			}else{
				atualizarContratoDemandaActionForm.setPercentualEsgoto("");
			}
			
			if(contratoDemanda.getNumeroVolumeInformadoFixadoEsgoto() != null){
				atualizarContratoDemandaActionForm.setVolumeEsgoto(contratoDemanda.getNumeroVolumeInformadoFixadoEsgoto().toString());
			}else{
				atualizarContratoDemandaActionForm.setVolumeEsgoto("");
			}
			
			httpServletRequest.setAttribute("nomeCampo", "numeroContrato");
		}

		return retorno;
	}
	
	/**
	 * Pesquisar os imóveis do contrato de demanda
	 * 
	 * @author Davi Menezes
	 * @date 25/04/2013
	 * 
	 * @param idContratoDemanda
	 * @param fachada
	 * @param sessao
	 */
	private void pesquisarImoveisContratoDemanda(Integer idContratoDemanda, Fachada fachada, HttpSession sessao){
		FiltroContratoDemandaImovelComercialIndustrial filtroContratoDemandaImovel = new FiltroContratoDemandaImovelComercialIndustrial();
		filtroContratoDemandaImovel.adicionarParametro(new ParametroSimples(
				FiltroContratoDemandaImovelComercialIndustrial.ID_CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL, idContratoDemanda));
		
		Collection<?> colContratoDemandaImovel = fachada.pesquisar(filtroContratoDemandaImovel, ContratoDemandaImovelComercialIndustrial.class.getName());
		if(!Util.isVazioOrNulo(colContratoDemandaImovel)){
			Collection<ContratoDemandaImovelHelper> colecaoContratoDemandaImovel = new ArrayList<ContratoDemandaImovelHelper>(); 
			ContratoDemandaImovelHelper helper = null;
			Imovel imovel = null;
			Integer idImovel = null;
			
			Iterator<?> it = colContratoDemandaImovel.iterator();
			while(it.hasNext()){
				ContratoDemandaImovelComercialIndustrial contratoDemadaImovel = (ContratoDemandaImovelComercialIndustrial) it.next();
				helper = new ContratoDemandaImovelHelper();
				
				idImovel = contratoDemadaImovel.getComp_id().getImovel().getId();
				
				imovel = fachada.pesquisarImovelDigitado(idImovel);
				
				String inscricao = fachada.pesquisarInscricaoImovel(imovel.getId());
				String endereco  = fachada.pesquisarEndereco(imovel.getId());
				
				helper.setIdImovel(idImovel.toString());
				helper.setMatricula(imovel.getMatriculaFormatada());
				helper.setInscricaoImovel(inscricao);
				helper.setEnderecoImovel(endereco);
				
				colecaoContratoDemandaImovel.add(helper);
			}
			
			sessao.setAttribute("colecaoContratoDemandaImovel", colecaoContratoDemandaImovel);
		}
	}

	/**
	 * Adicionar os Imóveis ao contrato de demanda
	 * 
	 * @author Davi Menezes
	 * @date 25/04/2013
	 * 
	 * @param idImovel
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	public void adicionarImovelContratoDemanda(String idImovel, Fachada fachada, HttpSession sessao){
		ContratoDemandaImovelHelper helper = new ContratoDemandaImovelHelper();
		
		Collection<ContratoDemandaImovelHelper> colecaoContratoDemandaImovel = (Collection<ContratoDemandaImovelHelper>) 
				sessao.getAttribute("colecaoContratoDemandaImovel");
		
		if(Util.isVazioOrNulo(colecaoContratoDemandaImovel)){
			colecaoContratoDemandaImovel = new ArrayList<ContratoDemandaImovelHelper>();
		}
		
		if(fachada.verificarExistenciaImovelContratoDemanda(Integer.parseInt(idImovel))){
			throw new ActionServletException("atencao.imovel_possui_contrato_demanda_nao_encerrado", null, idImovel);
		}
		
		Imovel imovel = fachada.pesquisarImovelDigitado(Integer.parseInt(idImovel));
		
		if(imovel == null){
			throw new ActionServletException("atencao.imovel.inexistente");
		}
		
		if(imovel.getLigacaoAguaSituacao() != null && !imovel.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)){
			throw new ActionServletException("atencao.informe_imovel_situacao_ligacao_agua_contrato_demanda", null, "LIGADO");
		}
		
		if(imovel.getCategoriaPrincipalId() != null && !imovel.getCategoriaPrincipalId().equals(Categoria.COMERCIAL) 
				&& !imovel.getCategoriaPrincipalId().equals(Categoria.INDUSTRIAL)){
			throw new ActionServletException("atencao.informe_imovel_categorias_contrato_demanda");
		}
		
		if(imovel.getImovelCondominio() != null){
			throw new ActionServletException("atencao.imovel_nao_pode_ser_associado_contrato_demanda", null, "MICROMEDIDOR");
		}
		
		if(imovel.getIndicadorImovelCondominio().equals(ConstantesSistema.SIM)){
			throw new ActionServletException("atencao.imovel_nao_pode_ser_associado_contrato_demanda", null, "MACROMEDIDOR");
		}
		
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
		filtroLigacaoAgua.adicionarParametro(new ParametroNulo(FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO));
		
		Collection<?> colecao = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
		if(!Util.isVazioOrNulo(colecao)){
			throw new ActionServletException("atencao.imovel_nao_medido_nao_pode_ser_vinculado_contrato_demanda");
		}
		
		String inscricao = fachada.pesquisarInscricaoImovel(Integer.parseInt(idImovel));
		String endereco  = fachada.pesquisarEndereco(Integer.parseInt(idImovel));
		
		helper.setIdImovel(idImovel);
		helper.setMatricula(imovel.getMatriculaFormatada());
		helper.setInscricaoImovel(inscricao);
		helper.setEnderecoImovel(endereco);
		
		if(!Util.isVazioOrNulo(colecaoContratoDemandaImovel)){
			this.validarRotaImoveis(colecaoContratoDemandaImovel, fachada, idImovel);
		}
		
		if(!colecaoContratoDemandaImovel.contains(helper)){
			colecaoContratoDemandaImovel.add(helper);
		}else{
			throw new ActionServletException("atencao.imovel_ja_adicionado_contrato_demanda", null, idImovel);
		}
		
		sessao.setAttribute("colecaoContratoDemandaImovel", colecaoContratoDemandaImovel);
	}
	
	/**
	 * Remover o imóvel do contrato de demanda
	 * 
	 * @author Davi Menezes
	 * @date 25/04/2013
	 * 
	 * @param posicao
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	public void removerImovelContratoDemanda(String posicao, HttpSession sessao){
		Collection<ContratoDemandaImovelHelper> colecaoContratoDemandaImovel = (Collection<ContratoDemandaImovelHelper>) 
				sessao.getAttribute("colecaoContratoDemandaImovel");
		
		Iterator<ContratoDemandaImovelHelper> it = colecaoContratoDemandaImovel.iterator();
		while(it.hasNext()){
			ContratoDemandaImovelHelper helper = (ContratoDemandaImovelHelper) it.next();
			
			if(helper.getIdImovel().equals(posicao)){
				colecaoContratoDemandaImovel.remove(helper);
				break;
			}
		}
		
		sessao.setAttribute("colecaoContratoDemandaImovel", colecaoContratoDemandaImovel);
	}
	
	private void validarRotaImoveis(Collection<ContratoDemandaImovelHelper> colecaoHelper, Fachada fachada, String idImovel){
		Imovel imovel = fachada.pesquisarImovelDigitado(Integer.parseInt(idImovel));
		
		String idLocalidade = imovel.getLocalidade().getId().toString();
		String codigoSetorComercial = String.valueOf(imovel.getSetorComercial().getCodigo());
		String numeroQuadra = String.valueOf(imovel.getQuadra().getNumeroQuadra());
		String rota = String.valueOf(imovel.getQuadra().getRota().getId());
		
		ContratoDemandaImovelHelper helper = null;
		
		Iterator<ContratoDemandaImovelHelper> iterator = colecaoHelper.iterator();
		while(iterator.hasNext()){
			helper = (ContratoDemandaImovelHelper) iterator.next();
			
			imovel = fachada.pesquisarImovelDigitado(Integer.parseInt(helper.getIdImovel()));
			
			if(!idLocalidade.equals("")){
				if(idLocalidade.equals(String.valueOf(imovel.getLocalidade().getId()))){
					if(!codigoSetorComercial.equals("")){
						if(codigoSetorComercial.equals(String.valueOf(imovel.getSetorComercial().getCodigo()))){
							if(!numeroQuadra.equals("")){
								if(numeroQuadra.equals(String.valueOf(imovel.getQuadra().getNumeroQuadra()))){
									if(!rota.equals("")){
										if(!rota.equals(String.valueOf(imovel.getQuadra().getRota().getId()))){
											throw new ActionServletException("atencao.existe_imoveis_diferentes_rotas");
										}
									}
								}else{
									throw new ActionServletException("atencao.existe_imoveis_diferentes_rotas");
								}
							}
						}else{
							throw new ActionServletException("atencao.existe_imoveis_diferentes_rotas");
						}
					}
				}else{
					throw new ActionServletException("atencao.existe_imoveis_diferentes_rotas");
				}
			}
		}
	}
	
}
