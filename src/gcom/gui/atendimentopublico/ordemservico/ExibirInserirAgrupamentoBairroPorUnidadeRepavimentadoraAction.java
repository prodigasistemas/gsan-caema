/**
 * 
 */
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
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author J�ssica Diniz
 * @date 14/05/2013
 */
public class ExibirInserirAgrupamentoBairroPorUnidadeRepavimentadoraAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("agrupamentoBairroPorUnidadeRepavimentadoraInserir");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession();

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm form = (InserirAgrupamentoBairroPorUnidadeRepavimentadoraActionForm) actionForm;

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		Collection<UnidadeOrganizacional> colecaoUnidadesRepavimentadoras = new ArrayList<UnidadeOrganizacional>();
		
		boolean usuarioRepavimentadora = false;

		// colecao unidades repavimentadoras
		if (usuario.getUnidadeOrganizacional().getUnidadeRepavimentadora() != null) {
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getUnidadeRepavimentadora().getId()));
			
			Collection<?> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				UnidadeOrganizacional unidadeRepavimentadora = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				colecaoUnidadesRepavimentadoras.add(unidadeRepavimentadora);
			}
			
			usuarioRepavimentadora = true;

		} else {
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA));
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
			
			Collection<?> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				Iterator<?> it = colecaoUnidadeOrganizacional.iterator();
				while(it.hasNext()){
					UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) it.next();
					
					if(!colecaoUnidadesRepavimentadoras.contains(unidadeOrganizacional.getUnidadeRepavimentadora())){
						colecaoUnidadesRepavimentadoras.add(unidadeOrganizacional.getUnidadeRepavimentadora());
					}
				}
				
				usuarioRepavimentadora = false;
			}
		}

		httpServletRequest.setAttribute("colecaoUnidadesRepavimentadoras", colecaoUnidadesRepavimentadoras);
		
		//AGRUPAMENTO BAIRRO REPAVIMENTADORA
		FiltroUnidadeRepavimentadoraMunicipio filtroUnidadeRepavimentadoraMunicipio = new FiltroUnidadeRepavimentadoraMunicipio(
				FiltroUnidadeRepavimentadoraMunicipio.DESCRICAO_AGRUPAMENTO);
		filtroUnidadeRepavimentadoraMunicipio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeRepavimentadoraMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(usuarioRepavimentadora){
			filtroUnidadeRepavimentadoraMunicipio.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipio.
					ID_UNIDADE_REPAVIMENTADORA, usuario.getUnidadeOrganizacional().getUnidadeRepavimentadora().getId()));
		}
		
		Collection colecaoUnidadeRepavimentadoraMunicipio = fachada.pesquisar(filtroUnidadeRepavimentadoraMunicipio, UnidadeRepavimentadoraMunicipio.class.getName());
		
		httpServletRequest.setAttribute("colecaoAgrupamentos", colecaoUnidadeRepavimentadoraMunicipio);
		
		String recarregar = httpServletRequest.getParameter("recarregar");
		if(recarregar != null && !recarregar.equals("")){
			if(form.getIdAgrupamento() != null && !form.getIdAgrupamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				FiltroUnidadeRepavimentadoraMunicipio filtroUnidadeMunicipio = new FiltroUnidadeRepavimentadoraMunicipio();
				filtroUnidadeMunicipio.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipio.ID, form.getIdAgrupamento()));
				filtroUnidadeMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipio.UNIDADE_REPAVIMENTADORA);
				filtroUnidadeMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipio.MUNICIPIO);
				
				Collection colecaoUnidadeMunicipio = fachada.pesquisar(filtroUnidadeMunicipio, UnidadeRepavimentadoraMunicipio.class.getName());
				UnidadeRepavimentadoraMunicipio unidadeRepavimentadoraMunicipio = (UnidadeRepavimentadoraMunicipio) 
						Util.retonarObjetoDeColecao(colecaoUnidadeMunicipio);
				
				form.setUnidadeRepavimentadora(unidadeRepavimentadoraMunicipio.getUnidadeRepavimentadora().getId().toString());
				form.setIdMunicipio(unidadeRepavimentadoraMunicipio.getMunicipio().getId().toString());
				
			}else{
				form.setUnidadeRepavimentadora("-1");
				form.setIdMunicipio("");
				form.setNomeMunicipio("");
			}
		}
		
		// MUNICIPIO
		String idMunicipio = form.getIdMunicipio();
		
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
				form.setIdMunicipio("");
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado", "exception");
				form.setNomeMunicipio("Munic�pio inexistente");
			}

		}
		
		// colecao bairros
		if (form.getIdMunicipio() != null && !form.getIdMunicipio().trim().equals("")) {
			FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);
			filtroBairro.adicionarParametro(new ParametroSimples(	FiltroBairro.MUNICIPIO_ID,
																	form.getIdMunicipio()));
			filtroBairro.adicionarParametro(new ParametroSimples(	FiltroBairro.INDICADOR_USO,
																	ConstantesSistema.SIM));
			filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			
			if(form.getIdAgrupamento() != null && !form.getIdAgrupamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				colecaoBairro = this.validarBairrosMarcados(colecaoBairro, Integer.parseInt(form.getIdAgrupamento()), fachada);
			}
			
			httpServletRequest.setAttribute("colecaoBairro", colecaoBairro);
			httpServletRequest.setAttribute("nomeCampo", "nome");
		
		}else{
			httpServletRequest.removeAttribute("colecaoBairro");
			httpServletRequest.setAttribute("nomeCampo", "nome");
		}

		return retorno;
	}

	/**
	 * 
	 * @author Davi Menezes
	 * @date 31/07/2013
	 * 
	 * @param colecaoBairro
	 * @param idAgrupamento
	 */
	private Collection<Bairro> validarBairrosMarcados(Collection<Bairro> colecaoBairro, Integer idAgrupamento, Fachada  fachada){
		FiltroUnidadeRepavimentadoraMunicipioBairros filtroMunicipioBairros = new FiltroUnidadeRepavimentadoraMunicipioBairros(
				FiltroUnidadeRepavimentadoraMunicipioBairros.ID_BAIRRO);
		filtroMunicipioBairros.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraMunicipioBairros.
				ID_UNIDADE_REPAVIMENTADORA, idAgrupamento));
		filtroMunicipioBairros.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraMunicipioBairros.
				BAIRRO);
		
		Collection<?> colecaoMunicipioBairros = fachada.pesquisar(filtroMunicipioBairros, UnidadeRepavimentadoraMunicipioBairros.class.getName());
		if(!Util.isVazioOrNulo(colecaoMunicipioBairros)){
			UnidadeRepavimentadoraMunicipioBairros unidadeRepavimentadoraMunicipioBairros;
			
			Iterator<?> it = null;
			
			for(Bairro bairro : colecaoBairro){
				it = colecaoMunicipioBairros.iterator();
				while(it.hasNext()){
					unidadeRepavimentadoraMunicipioBairros = (UnidadeRepavimentadoraMunicipioBairros) it.next();
					
					if(bairro.getId().equals(unidadeRepavimentadoraMunicipioBairros.getComp_id().getBairroId().getId())){
						bairro.setPertenceAgrupamentoBairros("true");
						break;
					}
				}
			}
		}
		
		return colecaoBairro;
	}
	
}
