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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */

public class ExibirAtualizarMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMunicipio");

		AtualizarMunicipioActionForm atualizarMunicipioActionForm = (AtualizarMunicipioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();
		
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		Collection colecaoUnidadeFederacao = fachada.pesquisar(
				filtroUnidadeFederacao, UnidadeFederacao.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeFederacao",
				colecaoUnidadeFederacao);

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

		filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMicrorregiao = fachada.pesquisar(filtroMicrorregiao,
				Microrregiao.class.getName());

		httpServletRequest.setAttribute("colecaoMicrorregiao",
				colecaoMicrorregiao);

		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();

		filtroRegiaoDesenvolvimento
				.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroRegiaoDesenvolvimento.adicionarParametro(new ParametroSimples(
				FiltroRegiaoDesenvolvimento.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoRegiaoDesenv = fachada.pesquisar(
				filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class
						.getName());

		httpServletRequest.setAttribute("colecaoRegiaoDesenv",
				colecaoRegiaoDesenv);

		Municipio municipio = null;

		String idMunicipio = null;

		if (httpServletRequest.getParameter("idMunicipio") != null) {
			// tela do manter
			idMunicipio = (String) httpServletRequest.getParameter("idMunicipio");
			sessao.setAttribute("idMunicipio", idMunicipio);
			
		} else if (sessao.getAttribute("idMunicipio") != null) {
			// tela do filtrar
			idMunicipio = (String) sessao.getAttribute("idMunicipio");
			
		} else if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
			// link na tela de sucesso do inserir municipio
			idMunicipio = (String) httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("idRegistroInseridoAtualizar", idMunicipio);
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/exibirFiltrarMunicipioAction.do?menu=sim");
			
		}else if(sessao.getAttribute("idRegistroInseridoAtualizar") != null){
			idMunicipio = (String) sessao.getAttribute("idRegistroInseridoAtualizar");
		}

		if (idMunicipio == null) {

			if (sessao.getAttribute("idRegistroAtualizar") != null) {
				municipio = (Municipio) sessao.getAttribute("idRegistroAtualizar");
				
			} else {
				idMunicipio = (String) httpServletRequest.getParameter("codigoMunicipio").toString();
			}
		}
		

		// ------Inicio da parte que verifica se vem da página de
		// municipio_manter.jsp

		if (municipio == null) {

			if (idMunicipio != null && !idMunicipio.equals("")) {

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("microrregiao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));

				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

					municipio = (Municipio) Util
							.retonarObjetoDeColecao(colecaoMunicipio);

				}
			}
		}

		// ------ O municipio foi encontrado
		
		String clientePesquisado = httpServletRequest.getParameter("clienteResponsavelPesquisar");
		
		if(clientePesquisado == null){

			atualizarMunicipioActionForm.setCodigoMunicipio(municipio.getId()
					.toString());
	
			atualizarMunicipioActionForm.setNomeMunicipio(municipio.getNome());
	
			if (municipio.getDdd() != null) {
				atualizarMunicipioActionForm.setCodigoDdd(municipio.getDdd()
						.toString());
			} else {
				atualizarMunicipioActionForm.setCodigoDdd("");
			}
	
			if (municipio.getCepInicio() != null) {
				atualizarMunicipioActionForm.setCepInicial(municipio.getCepInicio()
						.toString());
			} else {
				atualizarMunicipioActionForm.setCepInicial("");
			}
	
			if (municipio.getCepFim() != null) {
				atualizarMunicipioActionForm.setCepFinal(municipio.getCepFim()
						.toString());
			} else {
				atualizarMunicipioActionForm.setCepFinal("");
			}
	
			atualizarMunicipioActionForm.setUnidadeFederacao(municipio
					.getUnidadeFederacao().getId().toString());
	
			atualizarMunicipioActionForm.setMicroregiao(municipio.getMicrorregiao()
					.getId().toString());
	
			atualizarMunicipioActionForm.setRegiaoDesenv(municipio
					.getRegiaoDesenvolvimento().getId().toString());
	
			if (municipio.getDataConcessaoInicio() != null) {
				atualizarMunicipioActionForm.setDataInicioConcessao(Util
						.formatarData(municipio.getDataConcessaoInicio()));
			} else {
				atualizarMunicipioActionForm.setDataInicioConcessao("");
			}
	
			if (municipio.getDataConcessaoFim() != null) {
				atualizarMunicipioActionForm.setDataFimConcessao(Util
						.formatarData(municipio.getDataConcessaoFim()));
			} else {
				atualizarMunicipioActionForm.setDataFimConcessao("");
			}
	
			atualizarMunicipioActionForm.setIndicadorUso(""
					+ municipio.getIndicadorUso());
	
			if (municipio.getCodigoIbge() != null) {
	
				atualizarMunicipioActionForm.setCodigoIbge(municipio
						.getCodigoIbge());
			}
			
			if(municipio.getIndicadorRelacaoQuadraBairro() != null && 
					municipio.getIndicadorRelacaoQuadraBairro().toString().equals("1")){
				
				atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("1");
			}else{
				atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
			}
			
			//
			
			if(municipio.getIndicadorConvenioRepavimentacaoCompesa() != null && 
					municipio.getIndicadorConvenioRepavimentacaoCompesa().toString().equals("1")){
				
				atualizarMunicipioActionForm.setIndicadorConvenioRepavimentacaoCompesa("1");
			}else{
				atualizarMunicipioActionForm.setIndicadorConvenioRepavimentacaoCompesa("2");
			}
			
			if(municipio.getIndicadorDebitoCobrancaJudicial() != null && 
					municipio.getIndicadorDebitoCobrancaJudicial().toString().equals("1")){
				
				atualizarMunicipioActionForm.setIndicadorDebitoCobrancaJudicial("1");
			}else{
				atualizarMunicipioActionForm.setIndicadorDebitoCobrancaJudicial("2");
			}
			
			if(municipio.getIndicadorFaturaPagaIcms() != null && 
					municipio.getIndicadorFaturaPagaIcms().toString().equals("1")){
				
				atualizarMunicipioActionForm.setIndicadorFaturaPagaIcms("1");
			}else{
				atualizarMunicipioActionForm.setIndicadorFaturaPagaIcms("2");
			}
			
			if(municipio.getIndicadorParcelaPagaIcms() != null && 
					municipio.getIndicadorParcelaPagaIcms().toString().equals("1")){
				
				atualizarMunicipioActionForm.setIndicadorParcelaPagaIcms("1");
			}else{
				atualizarMunicipioActionForm.setIndicadorParcelaPagaIcms("2");
			}

			if( municipio.getClientePrefeitura()!=null &&
					municipio.getClientePrefeitura().getId() != null && !municipio.getClientePrefeitura().getId().equals("")){
					this.pesquisarCliente(""+municipio.getClientePrefeitura().getId(),atualizarMunicipioActionForm,fachada,httpServletRequest);
			}else{
				atualizarMunicipioActionForm.setIdClienteFiltro("");
				atualizarMunicipioActionForm.setNomeClienteFiltro("");
			}
		}else{
			
			if (atualizarMunicipioActionForm.getIdClienteFiltro() != null) {
				
				String idCliente = atualizarMunicipioActionForm.getIdClienteFiltro().toString();
				if(idCliente != null && !idCliente.equals("")){
					this.pesquisarCliente(idCliente,atualizarMunicipioActionForm,fachada,httpServletRequest);
				}
				
				
			} else {
				atualizarMunicipioActionForm.setIdClienteFiltro("");
			}
			
		}
		
		
		
		
		if(municipio.getIndicadorLogradouroBloqueado() != null && municipio.getIndicadorLogradouroBloqueado().toString().equals("1")){
			
			atualizarMunicipioActionForm.setIndicadorLogradouroBloqueado("1");
		}else{
			atualizarMunicipioActionForm.setIndicadorLogradouroBloqueado("2");
		}
		
		sessao.setAttribute("municipio", municipio);

		// ------ Fim da parte que verifica se vem da página de
		// municipio_manter.jsp
		
		//  verifica se o  usuario tem permissão especial
		boolean possuiPermissaoEspecial = fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.DESBLOQUEAR_ALTERACAO_MUNICIPIO, usuarioLogado);
		sessao.setAttribute("possuiPermissaoEspecial", possuiPermissaoEspecial);


		return retorno;
	}
	
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente(String idCliente,
			AtualizarMunicipioActionForm atualizarMunicipioActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
	
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

		Collection <Cliente> clienteEncontrado =  fachada.pesquisar(filtroCliente,Cliente.class.getName());

		if(clienteEncontrado!=null && clienteEncontrado.size()==1 ){
					
			filtroCliente.limparListaParametros();
			
			filtroCliente.adicionarParametro(new ParametroNulo(FiltroCliente.CLIENTE_RESPONSAVEL_ID));
			
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
			clienteEncontrado = fachada.pesquisar(filtroCliente, Cliente.class.getName());
				
			
			if( clienteEncontrado.size() != 0 && clienteEncontrado != null) {
				
				Cliente c = (Cliente) Util.retonarObjetoDeColecao(clienteEncontrado);
				
				if(c.getClienteTipo().getIndicadorPessoaFisicaJuridica()!=null && c.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)){
					
					Integer idClienteInt = Integer.parseInt(idCliente);
					Cliente clientePrefeituraEncontrado = fachada.pesquisarDadosClientePrefeitura(idClienteInt);

					
					if (clientePrefeituraEncontrado != null && clientePrefeituraEncontrado.getNome() != null && !clientePrefeituraEncontrado.getNome().equals("") ) {
						atualizarMunicipioActionForm.setIdClienteFiltro(""+ clientePrefeituraEncontrado.getId());
						atualizarMunicipioActionForm.setNomeClienteFiltro(clientePrefeituraEncontrado.getNome());
						httpServletRequest.setAttribute("idClienteNaoEncontrado","true");
					} else {
						throw new ActionServletException("atencao.cliente_nao_prefeitura");

					}
					
				}else{
					throw new ActionServletException("atencao.cliente_nao_juridico");

				}
			}
			else {
				httpServletRequest.setAttribute("clientePrefeitura", "sim");
			}

		}else{
			
			atualizarMunicipioActionForm.setIdClienteFiltro("");
			httpServletRequest.setAttribute("idClienteNaoEncontrado","exception");
			atualizarMunicipioActionForm.setNomeClienteFiltro("Código de cliente responsável inexistente");
			httpServletRequest.setAttribute("nomeCampo", "idClienteFiltro");
			
		}	
	}
}
