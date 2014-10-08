/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.DadosContratoPPPHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

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
 * Classe responsavel por informar/retirar a situacao de esgoto Factível Faturável
 * [UC1467] - Informar/Retirar Situação Factível Faturável de Esgoto
 * 
 * @author Arthur
 * @since 13/05/13
 */
public class InformarRetirarSituacaoFactivelFaturavelEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form = (InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		Collection<Integer> colecaoIdImovel = (Collection<Integer>) sessao.getAttribute("colecaoIdImovelPPP");
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();


		if ( form.getIndicadorTipoOperacao().equals("1") ) {
			//Informar
			Fachada.getInstancia().informarImovelFactivelFaturavel(montarHelper(form, colecaoIdImovel, usuarioLogado));
		} else if ( form.getIndicadorTipoOperacao().equals("2") ) {
			//retirar
			Fachada.getInstancia().retirarImovelFactivelFaturavel(montarHelper(form, colecaoIdImovel, usuarioLogado));
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Imóvel(eis) atualizados com sucesso.",
				"Informar/Retirar Situação de Esgoto Factível Faturável",
				"exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do?menu=sim");

		
		sessao.removeAttribute("InserirLigacaoEsgotoSituacaoActionForm");

		return retorno;

	}
	
	
	public DadosContratoPPPHelper montarHelper(InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form, Collection<Integer> colecaoIdImovel, Usuario usuario) {
		
		DadosContratoPPPHelper helper = new DadosContratoPPPHelper();
		
		helper.setColecaoIdImovel(colecaoIdImovel);
		helper.setUsuario(usuario);
		if ( form.getIndicadorTipoOperacao().equals("1") ) {
			helper.setLigacaoEsgoto(validarCarregarLigacaoEsgoto(form));
		}
		
		helper.setIdEspecificacao(form.getIdEspecificacao());
		helper.setIdTipoSolicitacaoRA(form.getIdTipoSolicitacaoRA());
		return helper;
	}
	/**
	 * 
	 * @param form
	 */
	public LigacaoEsgoto validarCarregarLigacaoEsgoto(InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form){
		String materialLigacao = form.getMaterialLigacao();
		String perfilLigacao = form.getPerfilLigacao();
		String percentual = form.getPercentualColeta().toString().replace(",", ".");
		String percentualEsgoto = form.getPercentualEsgoto().toString().replace(",", ".");
		String ligacaoEsgotoEsgotamento = form.getCondicaoEsgotamento();
		String ligacaoEsgotoDestinoDejetos = form.getDestinoDejetos();
		String ligacaoEsgotoCaixaInspecao = form.getSituacaoCaixaInspecao();
		String ligacaoEsgotoDestinoAguasPluviais = form.getDestinoAguasPluviais();
		String idLigacaoOrigem = form.getIdLigacaoOrigem();

		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		ligacaoEsgoto.setIndicadorCaixaGordura(new Short(form.getIndicadorCaixaGordura()));
		ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(form.getIndicadorLigacao()));
		ligacaoEsgoto.setUltimaAlteracao(new Date());
		
		// LEST_ID
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.FACTIVEL_FATURAVEL);
					
		
		String dataLigacao = form.getDataLigacao();
		if (dataLigacao != null && !dataLigacao.equals("") ) {
			
			if ( Util.compararData(Util.converteStringParaDate(dataLigacao), new Date()) > 0) {
				throw new ActionServletException("atencao.data_final_menor_data_atual",
						"Data da ligação");
			}
			ligacaoEsgoto.setDataLigacao(Util.converteStringParaDate(dataLigacao));
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Data Ligação");
		}

		String diametroLigacao = form.getDiametroLigacao();
		if (diametroLigacao != null && !diametroLigacao.equals("") && !diametroLigacao.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
			ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
			ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Diametro da Ligação");
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
					"Material da Ligação");
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
					"Perfil da Ligação");
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

		// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
		// Ligacao Origem
		if (idLigacaoOrigem != null
				&& !idLigacaoOrigem.equals("")
				&& !idLigacaoOrigem.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
			ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

			ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

		}

		if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

			BigDecimal percentualEsgotoColeta = new BigDecimal(
					percentualEsgoto);
			ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
		}

		// Condicação do Esgotamento
		if (ligacaoEsgotoEsgotamento != null
				&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
			le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
			ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
		}

		// Destino dos dejetos
		if (ligacaoEsgotoDestinoDejetos != null
				&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
			ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
			ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
		}

		// Situacao da caixa de inspecao
		if (ligacaoEsgotoCaixaInspecao != null
				&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
			leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
			ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
		}

		// Destino das Aguas Pluviais
		if (ligacaoEsgotoDestinoAguasPluviais != null
				&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
			ledap
					.setId(Integer
							.parseInt(ligacaoEsgotoDestinoAguasPluviais));
			ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
		}
		return ligacaoEsgoto;
	}
}