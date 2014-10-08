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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcao;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcaoMensal;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAtividadeAcao;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirConsumoAnormalidadeAcaoAction extends GcomAction {

	/**
	 * [UC1057] Inserir Consumo Anormalidade Acao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Rodrigo Cabral, Amelia Pessoa
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);		

		InserirConsumoAnormalidadeAcaoActionForm form = (InserirConsumoAnormalidadeAcaoActionForm) actionForm;
		
		sessao.removeAttribute("tipoPesquisaRetorno");

		//Consumo Anormalidade não informado
		if(form.getConsumoAnormalidade() == null || form.getConsumoAnormalidade().equals("-1")){
        	throw new ActionServletException("atencao.consumo_anormalidade_nao_informado");
	    } 
		
		//Colecao de ações mensais
		Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal = 
				(Collection<ConsumoAnormalidadeAtividadeAcaoMensal>) sessao.getAttribute("colecaoAnormalidadeAcaoMensal");
		if (colecaoAnormalidadeAcaoMensal==null || colecaoAnormalidadeAcaoMensal.size()==0){
			throw new ActionServletException("atencao.atividades_acoes_mensais");
		}
		
		//[FS0007] -   Verificar continuidade da quantidade de ocorrências consecutivas da anormalidade
		verificarAcoesMensaisConsecutivas(colecaoAnormalidadeAcaoMensal);
		
		//Verifica se ja existe ConsumoAnormalidadeAtividadeAcao com o mesmo ConsumoAnormalidade, Categoria e ImovelPerfil
		FiltroConsumoAnormalidadeAtividadeAcao filtroConsumoAnormalidadeAtividadeAcao = new FiltroConsumoAnormalidadeAtividadeAcao();
		filtroConsumoAnormalidadeAtividadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAtividadeAcao.CONSUMO_ANORMALIDADE_ID,
				form.getConsumoAnormalidade()));
		filtroConsumoAnormalidadeAtividadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.CONSUMO_ANORMALIDADE);
		
		if(form.getCategoria() == null || !form.getCategoria().equals("-1")){
			filtroConsumoAnormalidadeAtividadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAtividadeAcao.CATEGORIA_ID,
					form.getCategoria()));
			filtroConsumoAnormalidadeAtividadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.CATEGORIA);
	    }
		if(form.getImovelPerfil() == null || !form.getImovelPerfil().equals("-1")){
			filtroConsumoAnormalidadeAtividadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAtividadeAcao.IMOVEL_PERFIL_ID,
					form.getImovelPerfil()));
			filtroConsumoAnormalidadeAtividadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.IMOVEL_PERFIL);
	    }
		Collection<ConsumoAnormalidadeAtividadeAcao> colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroConsumoAnormalidadeAtividadeAcao, ConsumoAnormalidadeAtividadeAcao.class.getName()); 

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.consumo_anormalidade_acao_ja_existe");
		} else {
			ConsumoAnormalidadeAtividadeAcao consumoAnormalidadeAtividadeAcao = new ConsumoAnormalidadeAtividadeAcao();
			
			ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();
			consumoAnormalidade.setId(Integer.parseInt(form.getConsumoAnormalidade()));
			consumoAnormalidadeAtividadeAcao.setConsumoAnormalidade(consumoAnormalidade);
			
			if(form.getCategoria() == null || !form.getCategoria().equals("-1")){
				Categoria categoria = new Categoria();
				categoria.setId(Integer.parseInt(form.getCategoria()));
				consumoAnormalidadeAtividadeAcao.setCategoria(categoria);
			}
			
			if(form.getImovelPerfil() == null || !form.getImovelPerfil().equals("-1")){
				ImovelPerfil imovelPerfil = new ImovelPerfil();
				imovelPerfil.setId(Integer.parseInt(form.getImovelPerfil()));
				consumoAnormalidadeAtividadeAcao.setImovelPerfil(imovelPerfil);
			}
			
			consumoAnormalidadeAtividadeAcao.setIndicadorUso(new Short("1"));
			consumoAnormalidadeAtividadeAcao.setIndicadorValidarRetificacao(new Short(form.getIndicadorValidarRetificacao()));
			consumoAnormalidadeAtividadeAcao.setUltimaAlteracao(new Date());
			
			Integer idConsumoAnormalidadeAtividadeAcao = (Integer) fachada.inserir(consumoAnormalidadeAtividadeAcao);
			consumoAnormalidadeAtividadeAcao.setId(idConsumoAnormalidadeAtividadeAcao);
			
			for (ConsumoAnormalidadeAtividadeAcaoMensal consumoMensal : colecaoAnormalidadeAcaoMensal){
				consumoMensal.setConsumoAnormalidadeAtividadeAcao(consumoAnormalidadeAtividadeAcao);
				consumoMensal.setUltimaAlteracao(new Date());
				
				fachada.inserir(consumoMensal);
			}			
			
			montarPaginaSucesso(httpServletRequest,
					"Consumo Anormalidade e Ação inserida com sucesso.",
					"Inserir outra Consumo Anormalidade e Ação",
					"exibirInserirConsumoAnormalidadeAcaoAction.do?menu=sim",
					"exibirAtualizarConsumoAnormalidadeAcaoAction.do?menu=sim&idRegistroAtualizacao="
							+ idConsumoAnormalidadeAtividadeAcao,
					"Atualizar Consumo Anormalidade e Ação");
		}
        
		// devolve o mapeamento de retorno
		return retorno;
	}

	//[FS0007] -   Verificar continuidade da quantidade de ocorrências consecutivas da anormalidade
	private void verificarAcoesMensaisConsecutivas(
			Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal) {
		int quantidade = colecaoAnormalidadeAcaoMensal.size();
		int[] conferencia = new int[quantidade];
		
		for (int i=0; i<quantidade; i++){
			for (ConsumoAnormalidadeAtividadeAcaoMensal consumo : colecaoAnormalidadeAcaoMensal){
				if (consumo.getCodigoOrdemMes().intValue()==(i+1)){
					conferencia[i] += 1; 
				}
			}
		}		
		
		for (int i=0; i<conferencia.length; i++){		
			if (conferencia[i]>1){
				throw new ActionServletException(
						"atencao.acao_mensal_duplicidade", null,
						"0"+(i+1));
			} else if (conferencia[i]==0){
				throw new ActionServletException(
						"atencao.acao_mensal_nao_informado", null,
						"0"+(i+1));
			}
		}		
	}
}
