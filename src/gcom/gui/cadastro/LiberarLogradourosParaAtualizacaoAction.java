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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro;

import gcom.cadastro.ImovelEnderecoArquivoTexto;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1561] - Liberar os Logradouros para Atualiza��o no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class LiberarLogradourosParaAtualizacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();

		LiberarLogradourosParaAtualizacaoActionForm form = (LiberarLogradourosParaAtualizacaoActionForm) actionForm;
		
		ArrayList<ImovelEnderecoArquivoTexto> colecaoArquivoLogradouro = new ArrayList<ImovelEnderecoArquivoTexto>();
		
		if(form.getIdsRegistros() != null && form.getIdsRegistros().length > 0){
			//Cria uma colecao com os logradouros selecionados
			for(String idArquivo : form.getIdsRegistros()){
				
				String[] idArq = idArquivo.split("-");
				String idLogradouro = idArq[0];
				String idBairro = idArq[1];
				
				colecaoArquivoLogradouro.addAll(fachada.pesquisarTotalImoveisArquivo(Integer.parseInt(idLogradouro), Integer.parseInt(idBairro)));
			}
			
	//		[SB0001] - Atualizar dados do endere�o
			for(ImovelEnderecoArquivoTexto imovelEnderecoArquivoTexto : colecaoArquivoLogradouro){
				//Atualiza Logradouro Bairro
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TIPO);
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TITULO);
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, imovelEnderecoArquivoTexto.getLogradouro().getId()));
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, imovelEnderecoArquivoTexto.getBairro().getId()));
				Collection<LogradouroBairro> colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
				LogradouroBairro logradouroBairro = (LogradouroBairro) Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
				
				Imovel imovel 		  		= imovelEnderecoArquivoTexto.getImovel();
				Bairro bairro 		  		= imovelEnderecoArquivoTexto.getBairro();
				Cep cep 			  		= imovelEnderecoArquivoTexto.getCep();
				Logradouro logradouro 		= imovelEnderecoArquivoTexto.getLogradouro();
				String numeroImovel	  		= imovelEnderecoArquivoTexto.getNumeroImovel();
				String complemento	  		= imovelEnderecoArquivoTexto.getComplementoEndereco();
				Logradouro logradouroImovelAntigo = imovel.getLogradouroBairro().getLogradouro();
				
				if(logradouroBairro == null || logradouroBairro.getId() == null){
					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setBairro(bairro);
					logradouroBairro.setLogradouro(logradouro);
					logradouroBairro.setUltimaAlteracao(new Date());
					
					fachada.inserir(logradouroBairro);
				}
				
				//Atualiza Logradouro CEP
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
				filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, imovelEnderecoArquivoTexto.getLogradouro().getId()));
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, imovelEnderecoArquivoTexto.getCep().getCepId()));
				Collection<LogradouroCep> colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());
				LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colecaoLogradouroCep);
				
				if(logradouroCep == null || logradouroCep.getId() == null){
					logradouroCep = new LogradouroCep();
					logradouroCep.setCep(cep);
					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
					logradouroCep.setUltimaAlteracao(new Date());
					
					fachada.inserir(logradouroCep);
				}
				
				fachada.atualizarImovelLiberarLogradouros(imovel, numeroImovel, complemento, logradouroBairro, logradouroCep, usuarioLogado, logradouro);
				
				//Atualiza Arquivo
				imovelEnderecoArquivoTexto.setIndicadorAtualizacao(ConstantesSistema.SIM);
				fachada.atualizar(imovelEnderecoArquivoTexto);
				
				//Atualizar o Antigo Logradouro do Imovel
				ArrayList<Integer> idsImovel = fachada.pesquisarImovelPorLogradouro(logradouroImovelAntigo.getId());
				
				if(Util.isVazioOrNulo(idsImovel)){
					logradouroImovelAntigo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_DESATIVO);
					logradouroImovelAntigo.setUltimaAlteracao(new Date());
					fachada.atualizar(logradouroImovelAntigo);
				}			
				
			}
		}else{
			throw new ActionServletException("atencao.logradouro_nao_selecionado");
		}
		
		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Logradouros Atualizados com Sucesso", 
			"Liberar  os Logradouros para Atualiza��o no GSAN", "exibirLiberarLogradourosParaAtualizacaoAction.do?limpar=sim");
		
		return retorno;
	}
}
