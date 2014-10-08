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
package gcom.gui.cadastro.geografico;


import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cadastro.imovel.FiltrarImovelActionForm;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoComandosCobrancaBean;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoComandosCobrancaSubBean;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author K�ssia Albuquerque
 * @date 13/12/2006
 */
 
public class ExibirInserirMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping.findForward("inserirMunicipio");	
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirMunicipioActionForm inserirMunicipioActionForm = (InserirMunicipioActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
        	//Retorna o maior id de Munic�pio existente 
			int id = this.getFachada().pesquisarMaximoIdMunicipio();
			// Acrescenta 1 no valor do id para setar o primeiro id vazio para o usu�rio
			id = (id + 1);
			inserirMunicipioActionForm.setCodigoMunicipio("" + id);
			httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			inserirMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
			inserirMunicipioActionForm.setIndicadorDebitoCobrancaJudicial("2");
			inserirMunicipioActionForm.setIndicadorConvenioRepavimentacaoCompesa("2");
			inserirMunicipioActionForm.setIndicadorFaturaPagaIcms("2");
			inserirMunicipioActionForm.setIndicadorParcelaPagaIcms("2");
			inserirMunicipioActionForm.setIndicadorLogradouroBloqueado("2");
		}

		
		// [FS0001]-  Verificar a existencia de dados
		
				// UNIDADE FEDERA��O
				
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				
				filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
				
				Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada.pesquisar(
						filtroUnidadeFederacao, UnidadeFederacao.class.getName());
				
				if (colecaoUnidadeFederacao == null || colecaoUnidadeFederacao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Federa��o");
					
				}
		
				httpServletRequest.setAttribute("colecaoUnidadeFederacao",colecaoUnidadeFederacao);
				
				
				// MICRORREGIAO
				
				FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		
				filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
				
				filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<Microrregiao> colecaoMicrorregiao = fachada.pesquisar(
						filtroMicrorregiao, Microrregiao.class.getName());
				
				if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Microrregiao");
				}
		
				httpServletRequest.setAttribute("colecaoMicrorregiao",colecaoMicrorregiao);
		
				
				// REGIAO DESENVOLVIMENTO
				
				FiltroRegiaoDesenvolvimento filtroRegiaoDesenv = new FiltroRegiaoDesenvolvimento();
				
				filtroRegiaoDesenv.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);
				
				filtroRegiaoDesenv.adicionarParametro(new ParametroSimples(
						FiltroRegiaoDesenvolvimento.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<RegiaoDesenvolvimento> colecaoRegiaoDesenv = fachada.pesquisar(
						filtroRegiaoDesenv, RegiaoDesenvolvimento.class.getName());
		
				if (colecaoRegiaoDesenv == null || colecaoRegiaoDesenv.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Regiao Desenvolvimento");
				}
		
				httpServletRequest.setAttribute("colecaoRegiaoDesenv",colecaoRegiaoDesenv);
				
				String idCliente = null;
				
				idCliente = (String) inserirMunicipioActionForm.getIdClienteFiltro();
				
				// PESQUISAR CLIENTE
				if (idCliente != null
						&& !idCliente.toString().trim().equalsIgnoreCase("")) {
					this.pesquisarCliente(idCliente,inserirMunicipioActionForm,fachada,httpServletRequest);
				}

		
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
	public void pesquisarCliente( String idCliente,
			InserirMunicipioActionForm inserirMunicipioActionForm,
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
			
			
				if(c.getClienteTipo().getIndicadorPessoaFisicaJuridica()!=null && c.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA) ){
					
					Integer idClienteInt = Integer.parseInt(idCliente);
					Cliente clientePrefeituraEncontrado = fachada.pesquisarDadosClientePrefeitura(idClienteInt);

					
					if (clientePrefeituraEncontrado != null && clientePrefeituraEncontrado.getNome() != null && !clientePrefeituraEncontrado.getNome().equals("") ) {
						inserirMunicipioActionForm.setIdClienteFiltro(""+ clientePrefeituraEncontrado.getId());
						inserirMunicipioActionForm.setNomeClienteFiltro(clientePrefeituraEncontrado.getNome());
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
			
			inserirMunicipioActionForm.setIdClienteFiltro("");
			httpServletRequest.setAttribute("idClienteNaoEncontrado","exception");
			inserirMunicipioActionForm.setNomeClienteFiltro("C�digo de cliente respons�vel inexistente");
			httpServletRequest.setAttribute("nomeCampo", "idClienteFiltro");
			
		}
				
	}
	
}
