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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.ImovelEnderecoArquivoTexto;
import gcom.cadastro.RelatorioLogradourosParaAtualizacao;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

/**
 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class GerarRelatoriosLogradourosParaAtualizacao extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = null;
		String subtitulo = "";
//		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		LiberarLogradourosParaAtualizacaoActionForm form = (LiberarLogradourosParaAtualizacaoActionForm) actionForm;
		
		ArrayList<ImovelEnderecoArquivoTexto> colecaoArquivoLogradouro = new ArrayList<ImovelEnderecoArquivoTexto>();
		String tipo = httpServletRequest.getParameter("tipo");
		
		if(tipo != null && tipo.equalsIgnoreCase("total")){
			String idLogradouro = httpServletRequest.getParameter("idLogradouro");
			String idBairro 	= httpServletRequest.getParameter("idBairro");
			
			colecaoArquivoLogradouro = fachada.pesquisarTotalImoveisArquivo(Integer.parseInt(idLogradouro), Integer.parseInt(idBairro));
		}else if(tipo != null && tipo.equalsIgnoreCase("transferidos")){
			String idLogradouro = httpServletRequest.getParameter("idLogradouro");
			String idBairro 	= httpServletRequest.getParameter("idBairro");
			
			subtitulo = "IMÓVEIS TRANSFERIDOS";
			
			ArrayList<Integer> colecaoidsArquivos = fachada.pesquisarImoveisTransferidos(Integer.parseInt(idLogradouro), Integer.parseInt(idBairro));
			
			if(!Util.isVazioOrNulo(colecaoidsArquivos)){
				for(Integer idArquivo : colecaoidsArquivos){
					ImovelEnderecoArquivoTexto imovelEnderecoArquivo = fachada.pesquisarImovelEnderecoArquivo(idArquivo);
					 colecaoArquivoLogradouro.add(imovelEnderecoArquivo);
				}
			}
			
		}else{
			for(String idArquivo : form.getIdsRegistros()){
				
				String[] idArq = idArquivo.split("-");
				String idLogradouro = idArq[0];
				String idBairro = idArq[1];
				
				colecaoArquivoLogradouro.addAll(fachada.pesquisarTotalImoveisArquivo(Integer.parseInt(idLogradouro), Integer.parseInt(idBairro)));

			}
		}

		
		if(!Util.isVazioOrNulo(colecaoArquivoLogradouro)){
			RelatorioLogradourosParaAtualizacao relatorioLogradourosParaAtualizacao = new RelatorioLogradourosParaAtualizacao(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			relatorioLogradourosParaAtualizacao.addParametro("subtitulo", subtitulo);
			relatorioLogradourosParaAtualizacao.addParametro("nomeMunicipio", form.getDescricaoMunicipio());
			relatorioLogradourosParaAtualizacao.addParametro("colecaoArquivoLogradouro", colecaoArquivoLogradouro);
			relatorioLogradourosParaAtualizacao.addParametro("tipoFormatoRelatorio",	Integer.parseInt(tipoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorioLogradourosParaAtualizacao, tipoRelatorio, 
					httpServletRequest, httpServletResponse, actionMapping);
		}else{
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencao");
		}
		
		return retorno;	
	}
}
