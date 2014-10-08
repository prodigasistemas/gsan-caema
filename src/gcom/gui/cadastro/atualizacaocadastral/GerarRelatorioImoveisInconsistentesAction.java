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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Gerar Relatorio Imoveis Inconsistentes para Imoveis novos e Imoveis com Ocorrencia de Cadastro
 * 
 * @author Anderson Cabral
 * @date 08/08/2013
 */
public class GerarRelatorioImoveisInconsistentesAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		//Pesquisa od imoveis de acordo com os parametros informados
		ArrayList<DadosImovelPreGsanHelper> colecaoImovelPreGsan = this.pesquisarImoveis(form, httpServletRequest);

		if(colecaoImovelPreGsan != null && !colecaoImovelPreGsan.isEmpty()){
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			//Empresa
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			Collection<Empresa> empresas = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
			
			//Localidade
			Localidade localidade = new Localidade();
			localidade.setDescricao(form.getDescricaoLocalidade());			
			if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
			}
			
			//Setor Comercial
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setDescricao(form.getDescricaoSetorComercial());
			if(form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);			
			}
			
			//Quadras
			String numeroQuadras = "";
			Integer[] idsQuadras = form.getIdQuadraSelecionados();			
			fora: if(idsQuadras != null && idsQuadras.length > 0){
				for(int i=0; i < idsQuadras.length; i++){
					
					if(idsQuadras[i].intValue() != -1){
						numeroQuadras += idsQuadras[i] + ", ";
					
						if(i == 30){
							numeroQuadras = numeroQuadras.substring(0, numeroQuadras.length() -2);
							numeroQuadras = numeroQuadras + "...";
							break fora;
						}
					}
				}
				if(!numeroQuadras.equals("")){
					numeroQuadras = numeroQuadras.substring(0, numeroQuadras.length() -2);
				}
			}
			
			//Ocorrencia de Cadastro
			CadastroOcorrencia cadastroOcorrencia = new CadastroOcorrencia();
			cadastroOcorrencia.setDescricao("");
			if(form.getIdCadastroOcorrencia() != null && !form.getIdCadastroOcorrencia().equals("-1")){
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
				filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, form.getIdCadastroOcorrencia()));
				ArrayList<CadastroOcorrencia> colecaoCadastroOcorrencia = (ArrayList<CadastroOcorrencia>) fachada.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
				cadastroOcorrencia = (CadastroOcorrencia) Util.retonarObjetoDeColecao(colecaoCadastroOcorrencia);				
			}
			
			if(form.getIndicadorTipoSelecao().equals("2")){
				if(httpServletRequest.getParameter("resumo") != null){
					RelatorioResumoImoveisInconsistentesComOcorrencia relatorioResumoImoveisInconsistentesComOcorrencia = new RelatorioResumoImoveisInconsistentesComOcorrencia(
						(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("empresa", empresa.getDescricao());			
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("tipoSelecao", "IMÓVEIS COM OCORRÊNCIA CADASTRO");
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("idLocalidade", form.getIdLocalidade());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoLocalidade", localidade.getDescricao());
					if(setorComercial.getCodigo() != 0){
						relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
					}else{
						relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", "");
					}
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("numeroQuadras", numeroQuadras);
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					
					retorno = processarExibicaoRelatorio(relatorioResumoImoveisInconsistentesComOcorrencia, tipoRelatorio, 
							httpServletRequest, httpServletResponse, actionMapping);
				}else{
					RelatorioImoveisInconsistentesComOcorrencia relatorioImoveisInconsistentesComOcorrencia = new RelatorioImoveisInconsistentesComOcorrencia(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
					
					relatorioImoveisInconsistentesComOcorrencia.addParametro("empresa", empresa.getDescricao());			
					relatorioImoveisInconsistentesComOcorrencia.addParametro("tipoSelecao", "IMÓVEIS COM OCORRÊNCIA CADASTRO");
					relatorioImoveisInconsistentesComOcorrencia.addParametro("idLocalidade", form.getIdLocalidade());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoLocalidade", localidade.getDescricao());
					if(setorComercial.getCodigo() != 0){
						relatorioImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
					}else{
						relatorioImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", "");
					}
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("numeroQuadras", numeroQuadras);
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
					relatorioImoveisInconsistentesComOcorrencia.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					
					retorno = processarExibicaoRelatorio(relatorioImoveisInconsistentesComOcorrencia, tipoRelatorio, 
							httpServletRequest, httpServletResponse, actionMapping);
				}
			}else{
				
				RelatorioImoveisNovosInconsistentes relatorioImoveisNovosInconsistentes = new RelatorioImoveisNovosInconsistentes(
						(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				relatorioImoveisNovosInconsistentes.addParametro("empresa", empresa.getDescricao());			
				relatorioImoveisNovosInconsistentes.addParametro("tipoSelecao", "IMÓVEIS NOVOS");
				relatorioImoveisNovosInconsistentes.addParametro("idLocalidade", form.getIdLocalidade());
				relatorioImoveisNovosInconsistentes.addParametro("descricaoLocalidade", localidade.getDescricao());
				if(setorComercial.getCodigo() != 0){
					relatorioImoveisNovosInconsistentes.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
				}else{
					relatorioImoveisNovosInconsistentes.addParametro("codigoSetorComercial", "");
				}
				relatorioImoveisNovosInconsistentes.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
				relatorioImoveisNovosInconsistentes.addParametro("numeroQuadras", numeroQuadras);
				relatorioImoveisNovosInconsistentes.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
				relatorioImoveisNovosInconsistentes.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
				relatorioImoveisNovosInconsistentes.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
				
				retorno = processarExibicaoRelatorio(relatorioImoveisNovosInconsistentes, tipoRelatorio, 
						httpServletRequest, httpServletResponse, actionMapping);
			}
		}else{
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencao");
		}
		
		return retorno;	
	}
	
	private ArrayList<DadosImovelPreGsanHelper> pesquisarImoveis(ConsultarImoveisPreGsanActionForm form, HttpServletRequest httpServletRequest) {
		
		DadosImovelPreGsanHelper parametros = new DadosImovelPreGsanHelper();
		//monta os parametros informados na tela.
		parametros.setParametroEmpresa(form.getIdEmpresa());
		parametros.setParametroLocalidade(form.getIdLocalidade());
		parametros.setParametroSetorComercial(form.getIdSetorComercial());
		parametros.setParametroQuadra(form.getIdQuadraSelecionados());
		parametros.setParametroCadastroOcorrencia(form.getIdCadastroOcorrencia());
		parametros.setParametroTipoSelecao(form.getIndicadorTipoSelecao());
		parametros.setParametroCadastrador(form.getCadastrador());
		
		ArrayList<DadosImovelPreGsanHelper> colecaoImovelPreGsan = null;
		
		if(httpServletRequest.getParameter("resumo") == null){
			colecaoImovelPreGsan = (ArrayList<DadosImovelPreGsanHelper>) Fachada.getInstancia().pesquisarImovelPreGsan(parametros, true);
		}else{
			colecaoImovelPreGsan = (ArrayList<DadosImovelPreGsanHelper>) Fachada.getInstancia().pesquisarImovelPreGsanResumoPorCadastroOcorrencia(parametros);
		}
		
		// Ordena a coleção de Imoveis
//        if (colecaoImovelPreGsan != null && !colecaoImovelPreGsan.isEmpty()) {
//            Collections.sort((List) colecaoImovelPreGsan, new Comparator() {
//                public int compare(Object a, Object b) {
//                    String imac1 = ((DadosImovelPreGsanHelper) a).getIdImovelAtualizacaoCadastral();
//                    String imac2 = ((DadosImovelPreGsanHelper) b).getIdImovelAtualizacaoCadastral();
//
//                    return imac1.compareTo(imac2);
//                }
//            });
//        }
		
		return colecaoImovelPreGsan;		 
	}
}
