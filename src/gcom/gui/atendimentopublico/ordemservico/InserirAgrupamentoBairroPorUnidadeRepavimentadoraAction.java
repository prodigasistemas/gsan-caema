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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.FiltroUnidadeRepavimentadoraMunicipio;
import gcom.atendimentopublico.FiltroUnidadeRepavimentadoraMunicipioBairros;
import gcom.atendimentopublico.UnidadeRepavimentadoraMunicipio;
import gcom.atendimentopublico.UnidadeRepavimentadoraMunicipioBairros;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author J�ssica Diniz
 * @date 14/05/2013
 */
public class InserirAgrupamentoBairroPorUnidadeRepavimentadoraAction extends GcomAction {

	/**
	 * Este caso de uso permite a associa��o de bairros de um determinado
	 * munic�pio a um agrupamento com denomina��o informada que n�o deve ser
	 * repetida.
	 * 
	 * [UC1470] Informar Agrupamento de Bairros por Unidade Repavimentadora
	 * 
	 * 
	 * @author J�ssica Diniz
	 * @date 14/05/2013
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm form = (InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String nome = form.getNome();
		
		String idAgrupamento = form.getIdAgrupamento();

		String unidadeRepavimentadora = form.getUnidadeRepavimentadora();

		String idMunicipio = form.getIdMunicipio();

		String[] bairros = form.getBairros();
		
		String descricaoAgrupamento = "";

		boolean novoAgrupamento = true;

		// VALIDAR NOME
		if (nome != null && !nome.trim().equals("")) {
			FiltroUnidadeRepavimentadoraMunicipio filtroUnidadeRepavimentadoraMunicipio = new FiltroUnidadeRepavimentadoraMunicipio();
			filtroUnidadeRepavimentadoraMunicipio.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipio.
					DESCRICAO_AGRUPAMENTO, nome));
			Collection<UnidadeRepavimentadoraMunicipio> colecaoUnidadeRepavimentadoraMunicipio = fachada.pesquisar(
					filtroUnidadeRepavimentadoraMunicipio, UnidadeRepavimentadoraMunicipio.class.getName());

			if (colecaoUnidadeRepavimentadoraMunicipio != null && !colecaoUnidadeRepavimentadoraMunicipio.isEmpty()) {
				throw new ActionServletException("atencao.existe_agrupamento_de_bairro_nome_informado", null, nome);
			}
			
			novoAgrupamento = true;
		}
		
		UnidadeRepavimentadoraMunicipio unidadeRepavimentadoraMunicipio = null;
		
		if(idAgrupamento != null && !idAgrupamento.equals("") && !idAgrupamento.equals("-1")){
			FiltroUnidadeRepavimentadoraMunicipio filtroUnidadeRepavimentadoraMunicipio = new FiltroUnidadeRepavimentadoraMunicipio();
			filtroUnidadeRepavimentadoraMunicipio.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipio.
					ID, idAgrupamento));
			Collection<UnidadeRepavimentadoraMunicipio> colecaoUnidadeRepavimentadoraMunicipio = fachada.pesquisar(
					filtroUnidadeRepavimentadoraMunicipio, UnidadeRepavimentadoraMunicipio.class.getName());

			if (!Util.isVazioOrNulo(colecaoUnidadeRepavimentadoraMunicipio)) {
				unidadeRepavimentadoraMunicipio = (UnidadeRepavimentadoraMunicipio) 
					Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadoraMunicipio);
				
				descricaoAgrupamento = unidadeRepavimentadoraMunicipio.getDescricaoAgrupamento();
			}
			
			novoAgrupamento = false;
		}

		// VALIDAR UNIDADE REPAVIMENTADORA
		if (unidadeRepavimentadora == null || unidadeRepavimentadora.equals("-1")) {
			throw new ActionServletException("atencao.unidade_repavimentadora_nao_informada");
		}

		// VALIDAR MUNICIPIO
		if (idMunicipio == null || idMunicipio.trim().equals("")) {
			throw new ActionServletException("atencao.municipio_nao_informado");
		}

		if (idMunicipio != null && !idMunicipio.trim().equals("") && !Util.validarValorNaoNumerico(idMunicipio)) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,
																	idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
																	ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				form.setIdMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getId().toString());
				form.setNomeMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getNome());

				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado", "true");

			} else {
				throw new ActionServletException("atencao.municipio.inexistente");
			}

		}

		// VALIDAR SELECAO DOS BAIRROS
		if(bairros == null || bairros.length == 0){
			throw new ActionServletException("atencao.ids_bairro_nao_selecionado");
		}
		
		Collection<?> colecaoBairros = null;
		FiltroBairro filtroBairro = null;
		Bairro bairro = null;
		
		// VALIDAR BAIRROS DE ACORDO COM O MUNICIPIO INFORMADO
		for (int i = 0; i < bairros.length; i++){
			filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, Integer.parseInt(bairros[i])));
			filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
			
			colecaoBairros = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			if(!Util.isVazioOrNulo(colecaoBairros)){
				bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairros);
				
				if(!bairro.getMunicipio().getId().equals(Integer.parseInt(idMunicipio))){
					String[] msg = new String[2];
					
					msg[0] = "" + bairro.getNome(); 
					msg[1] = "" + form.getNomeMunicipio();
					
					throw new ActionServletException("atencao.logradouro_municipio_bairro_invalido", null, msg);
				}
			}
		}
		
		// VALIDAR BAIRROS DE ACORDO COM O AGRUPAMENTO
		for (int i = 0; i < bairros.length; i++){
			Integer id = Integer.parseInt(bairros[i]);
			
			FiltroUnidadeRepavimentadoraMunicipioBairros filtroUnidadeRepavimentadoraMunicipioBairros = new FiltroUnidadeRepavimentadoraMunicipioBairros();
			filtroUnidadeRepavimentadoraMunicipioBairros.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipioBairros.ID_BAIRRO, id));
			
			if(idAgrupamento != null && !idAgrupamento.equals("") && !idAgrupamento.equals("-1")){
				filtroUnidadeRepavimentadoraMunicipioBairros.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUnidadeRepavimentadoraMunicipioBairros.ID_UNIDADE_REPAVIMENTADORA, idAgrupamento));
			}
			
			filtroUnidadeRepavimentadoraMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.BAIRRO);
			filtroUnidadeRepavimentadoraMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.UNIDADE_REPAVIMENTADORA_MUNICIPIO);
			
			Collection<?> colecaoUnidRepMun = fachada.pesquisar(filtroUnidadeRepavimentadoraMunicipioBairros, UnidadeRepavimentadoraMunicipioBairros.class.getName());
			if(!Util.isVazioOrNulo(colecaoUnidRepMun)){
				UnidadeRepavimentadoraMunicipioBairros unidadeRepavimentadoraMuncipioBairros = (UnidadeRepavimentadoraMunicipioBairros) 
						Util.retonarObjetoDeColecao(colecaoUnidRepMun);
				
				form.setBairros(null);
				
				throw new ActionServletException("atencao.bairro_pertence_agrupamento_existente", 
					unidadeRepavimentadoraMuncipioBairros.getComp_id().getBairroId().getNome(), 
					unidadeRepavimentadoraMuncipioBairros.getComp_id().getUnidadeRepavimentadoraMunicipioId().getDescricaoAgrupamento());
			}
		}
		
		if(novoAgrupamento){
			unidadeRepavimentadoraMunicipio = new UnidadeRepavimentadoraMunicipio();
			
			unidadeRepavimentadoraMunicipio.setDescricaoAgrupamento(nome);
			unidadeRepavimentadoraMunicipio.setIndicadorUso(ConstantesSistema.SIM);
		}
		
		UnidadeOrganizacional unidRepavimentadora = new UnidadeOrganizacional();
		unidRepavimentadora.setId(Integer.parseInt(unidadeRepavimentadora));
		unidadeRepavimentadoraMunicipio.setUnidadeRepavimentadora(unidRepavimentadora);
		
		Municipio municipioNovo = new Municipio();
		municipioNovo.setId(Integer.parseInt(idMunicipio));
		unidadeRepavimentadoraMunicipio.setMunicipio(municipioNovo);
		
		unidadeRepavimentadoraMunicipio.setUltimaAlteracao(new Date());
		
		// Inserir na base
		fachada.informarAgrupamentoBairrosUnidadeRepavimentadora(unidadeRepavimentadoraMunicipio, bairros, novoAgrupamento);
		
		// Monta a p�gina de sucesso
		if(novoAgrupamento){
			montarPaginaSucesso(httpServletRequest, "Agrupamento de Bairros " + nome.toUpperCase() + " por Unidade Repavimentadora "
				+ " inserido com sucesso.", "Informar outro Agrupamento de Bairros", "exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do?menu=sim");
		}else{
			montarPaginaSucesso(httpServletRequest, "Agrupamento de Bairros " + descricaoAgrupamento.toUpperCase() + " por Unidade Repavimentadora "
				+ " atualizado com sucesso.", "Informar outro Agrupamento de Bairros", "exibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction.do?menu=sim");
		}

		return retorno;
	}

}