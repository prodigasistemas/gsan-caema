/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * R�mulo Aur�lio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoEsgotoAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = ligacaoEsgotoActionForm.getIdOrdemServico();

		String materialLigacao = ligacaoEsgotoActionForm.getMaterialLigacao();
		String perfilLigacao = ligacaoEsgotoActionForm.getPerfilLigacao();
		String percentual = ligacaoEsgotoActionForm.getPercentualColeta()
				.toString().replace(",", ".");
		String percentualEsgoto = ligacaoEsgotoActionForm.getPercentualEsgoto()
				.toString().replace(",", ".");
		String idServicoMotivoNaoCobranca = ligacaoEsgotoActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = ligacaoEsgotoActionForm
				.getPercentualCobranca();
		String indicadorCaixaGordura = ligacaoEsgotoActionForm
				.getIndicadorCaixaGordura();

		String ligacaoEsgotoEsgotamento = ligacaoEsgotoActionForm
				.getCondicaoEsgotamento();
		String ligacaoEsgotoDestinoDejetos = ligacaoEsgotoActionForm
				.getDestinoDejetos();
		String ligacaoEsgotoCaixaInspecao = ligacaoEsgotoActionForm
				.getSituacaoCaixaInspecao();
		String ligacaoEsgotoDestinoAguasPluviais = ligacaoEsgotoActionForm
				.getDestinoAguasPluviais();
		String idLigacaoOrigem = ligacaoEsgotoActionForm.getIdLigacaoOrigem();
		String idImovel = ligacaoEsgotoActionForm.getIdImovel();
		String indicadorLigacaoEsgoto = ligacaoEsgotoActionForm.getIndicadorLigacao();
		Imovel imovel = null;
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		
		if (sessao.getAttribute("imovel") != null) {
			imovel = (Imovel) sessao.getAttribute("imovel");
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
		} else if (idImovel != null && !idImovel.trim().equals("")) {
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, idImovel));
		}
		
		Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
		
		if (colecaoLigacaoEsgoto != null && !colecaoLigacaoEsgoto.isEmpty()) {
			ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
		}

		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			imovel.setUltimaAlteracao(new Date());

			/*---------------------  In�cioDados da Liga��o Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Liga��o");
			}
			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& !ligacaoEsgotoActionForm.getDataLigacao().equals("")) {

				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Liga��o");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Liga��o");
			}

			if (perfilLigacao != null
					&& !perfilLigacao.equals("")
					&& !perfilLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
				ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Liga��o");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = new BigDecimal(
						percentual);
				if (percentualInformadoColeta != null
						&& !percentualInformadoColeta.equals("")
						&& (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) {
					ligacaoEsgoto
							.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			// Colocado por R�mulo Aur�lio Analista: S�vio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}else{
				ligacaoEsgoto.setLigacaoOrigem(null);
			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condica��o do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(null);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(null);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(null);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(null);
			}

			ligacaoEsgoto.setImovel(imovel);

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(null);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Liga��o de Esgoto efetuada com Sucesso",
						"Efetuar outra Liga��o de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&matriculaImovel="
								+ imovel.getId(),
						"Atualiza��o Liga��o de Esgoto efetuada");

			}

		}

		OrdemServico ordemServico = null;
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVI�O INEXISTENTE");

			}
			
			ligacaoEsgoto.setImovel(imovel);

			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& ligacaoEsgotoActionForm.getDataLigacao() != "") {
				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Liga��o");
			}
			/*---------------------  In�cioDados da Liga��o Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Liga��o");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Liga��o");
			}

			if (perfilLigacao != null
					&& !perfilLigacao.equals("")
					&& !perfilLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
				ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Liga��o");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = new BigDecimal(
						percentual);
				if (percentualInformadoColeta != null
						&& !percentualInformadoColeta.equals("")){				
					if (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO) {
						ligacaoEsgoto
								.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
					}else{
						throw new ActionServletException("atencao.atualizar_percentual_informado_maior_maximo_permitido");
					}
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			// Colocado por R�mulo Aur�lio Analista: S�vio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}else{
				ligacaoEsgoto.setLigacaoOrigem(null);
			}


			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condica��o do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(null);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(null);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(null);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}else{
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(null);
			}


			if (ordemServico != null
					&& ligacaoEsgotoActionForm.getIdTipoDebito() != null) {

				ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

				ordemServico.setIndicadorComercialAtualizado(new Short("1"));

				BigDecimal valorAtual = new BigDecimal(0);
				if (ligacaoEsgotoActionForm.getValorDebito() != null) {
					String valorDebito = ligacaoEsgotoActionForm
							.getValorDebito().toString().replace(".", "");

					valorDebito = valorDebito.replace(",", ".");

					valorAtual = new BigDecimal(valorDebito);

					ordemServico.setValorAtual(valorAtual);
				}

				if (idServicoMotivoNaoCobranca != null
						&& !idServicoMotivoNaoCobranca
								.equals(ConstantesSistema.NUMERO_NAO_INFORMADO
										+ "")) {
					servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(new Integer(
							idServicoMotivoNaoCobranca));
				}
				ordemServico
						.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

				if (valorPercentual != null) {
					ordemServico.setPercentualCobranca(new BigDecimal(
							ligacaoEsgotoActionForm.getPercentualCobranca()));
				}

				ordemServico.setUltimaAlteracao(new Date());

			}
			
			

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
			
			
			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setUsuarioLogado(usuario);

			if (ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")
					|| (sessao.getAttribute("movimentarOS") != null && sessao.getAttribute("movimentarOS").equals("TRUE"))) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			} else {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
				//Caso OS do tipo ligado decreto, situacao da ligacao de esgoto = factivel faturavel (ligado decreto)
				if ( ordemServico != null && ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getId() != null &&
						ordemServico.getServicoTipo().getId().equals(ServicoTipo.LIGADO_DECRETO) ) {
					
					integracaoComercialHelper.setIndicadorFactivelFaturavel(ConstantesSistema.SIM);
					LigacaoEsgotoSituacao ligacaoEsgotoSituacaoAnterior = Fachada.getInstancia().pesquisarLigacaoEsgotoSituacao(imovel.getId());
					integracaoComercialHelper.setLigacaoEsgotoSituacaoAnterior(ligacaoEsgotoSituacaoAnterior);
				}
				
				sessao.setAttribute("integracaoComercialHelper",
						integracaoComercialHelper);

				if (sessao.getAttribute("semMenu") == null) {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoAction");
				} else {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}

			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Liga��o de Esgoto efetuada com Sucesso",
						"Efetuar outra Liga��o de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&idOrdemServico="
								+ ordemServico.getId(),
						"Atualiza��o Liga��o de Esgoto efetuada");

			}

		}
		return retorno;

		/*
		 * else { throw new ActionServletException(
		 * "atencao.informe_campo_obrigatorio", null, "Ordem de Servi�o"); }
		 */
	}

}
