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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.RelatorioAnaliseImovelCorporativoGrande;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso gera relatório de análise do imóvel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */
public class GerarRelatorioAnaliseImovelCorporativoGrandeAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioAnaliseImovelCorporativoGrandeActionForm form = (GerarRelatorioAnaliseImovelCorporativoGrandeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		httpServletRequest.setAttribute("telaSucessoRelatorio",true);	
		
		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;
		
		//Opção de Totalização
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		if(opcaoTotalizacao == null || opcaoTotalizacao.equals("")){
			throw new ActionServletException("atencao.informe_opcao_totalizacao");
		}else{
			if(opcaoTotalizacao.equals("5")){
				if(form.getRegional() == null || form.getRegional().length == 0){
					throw new ActionServletException("atencao.informe_gerencia_regional");
				}else if(form.getRegional().length == 1 && form.getRegional()[0].toString().equals(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					throw new ActionServletException("atencao.informe_gerencia_regional");
				}
			}else if(opcaoTotalizacao.equals("6")){
				if(form.getUnidadeNegocio() == null || form.getUnidadeNegocio().length == 0){
					throw new ActionServletException("atencao.informe_unidade_negocio");
				}else if(form.getUnidadeNegocio().length == 1 && form.getUnidadeNegocio()[0].toString().equals(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					throw new ActionServletException("atencao.informe_unidade_negocio");
				}
			}else if(opcaoTotalizacao.equals("7")){
				if(form.getIdImovelPerfil() == null || form.getIdImovelPerfil().length == 0){
					throw new ActionServletException("atencao.perfil_imovel_nao_informado");
				}else if(form.getIdImovelPerfil().length == 1 && form.getIdImovelPerfil()[0].toString().equals(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					throw new ActionServletException("atencao.perfil_imovel_nao_informado");
				}
			}
		}

		// Gerência Regional
		Collection<Integer> idGerenciaRegional = new ArrayList<Integer>();	

		if (form.getRegional() != null && form.getRegional().length > 0) {
			for (int i = 0; i < form.getRegional().length; i++) {
				if ( !form.getRegional()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
					idGerenciaRegional.add(Integer.valueOf(form.getRegional()[i]));
					peloMenosUmParametroInformado = true;
				}
			}
		}

		// Unidade de Negócio
		Collection<Integer> idUnidadeNegocio = new ArrayList<Integer>();

		if (form.getUnidadeNegocio() != null && form.getUnidadeNegocio().length > 0) {
			for (int i = 0; i < form.getUnidadeNegocio().length; i++) {
				if ( !form.getUnidadeNegocio()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
					idUnidadeNegocio.add(Integer.valueOf(form.getUnidadeNegocio()[i]));
					peloMenosUmParametroInformado = true;
				}
			}
		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		boolean perfilNaoInformado = true;
		
		// Perfil do Imóvel
		Collection<Integer> idImovelPerfil = new ArrayList<Integer>();
		if (form.getIdImovelPerfil() != null && form.getIdImovelPerfil().length > 0) {
			for (int i = 0; i < form.getIdImovelPerfil().length; i++) {
				if ( !form.getIdImovelPerfil()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
					idImovelPerfil.add(Integer.valueOf(form.getIdImovelPerfil()[i]));
					peloMenosUmParametroInformado = true;
					perfilNaoInformado = false;
				}
			}
		}
		
		if ( perfilNaoInformado ) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<?> colImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			if(!Util.isVazioOrNulo(colImovelPerfil)){
				Iterator<?> itImovelPerfil = colImovelPerfil.iterator();
				while(itImovelPerfil.hasNext()){
					ImovelPerfil imovelPerfil = (ImovelPerfil) itImovelPerfil.next();
					idImovelPerfil.add(imovelPerfil.getId());
//					peloMenosUmParametroInformado = true;
				}
			}
		}	
		
		boolean categoriaNaoInformado = true;
		//Categoria
		Collection<Integer> idCategoria = new ArrayList<Integer>();
		if (form.getIdCategoria() != null && form.getIdCategoria().length > 0) {
			
			for (int i = 0; i < form.getIdCategoria().length; i++) {
				if ( !form.getIdCategoria()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
					idCategoria.add(Integer.valueOf(form.getIdCategoria()[i]));
					peloMenosUmParametroInformado = true;
					categoriaNaoInformado = false;
				}
			}
		}
//		if ( categoriaNaoInformado ) {
//			throw new ActionServletException(
//					"atencao.campo_texto.obrigatorio", null,
//					"Categoria");
//		}
		
		//Ligacao agua situacao
		Collection<Integer> idLigacaoAguaSituacao = new ArrayList<Integer>();
		if (form.getIdLigacaoAguaSituacao() != null && form.getIdLigacaoAguaSituacao().length > 0) {
			
			for (int i = 0; i < form.getIdLigacaoAguaSituacao().length; i++) {
				if ( !form.getIdLigacaoAguaSituacao()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					idLigacaoAguaSituacao.add(Integer.valueOf(form.getIdLigacaoAguaSituacao()[i]));
//					peloMenosUmParametroInformado = true;
				}
			}
		}
		

		//Ligacao Esgoto situacao
		Collection<Integer> idLigacaoEsgotoSituacao = new ArrayList<Integer>();
		if (form.getIdLigacaoEsgotoSituacao() != null && form.getIdLigacaoEsgotoSituacao().length > 0) {
			
			for (int i = 0; i < form.getIdLigacaoEsgotoSituacao().length; i++) {
				if ( !form.getIdLigacaoEsgotoSituacao()[i].toString().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
					idLigacaoEsgotoSituacao.add(Integer.valueOf(form.getIdLigacaoEsgotoSituacao()[i]));
//					peloMenosUmParametroInformado = true;
				}
			}
		}


		
		// Selecionar
		Integer selecionar = null;
		if (form.getSelecionar() != null && !form.getSelecionar().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
//			peloMenosUmParametroInformado = true;
			selecionar = new Integer(form.getSelecionar());
		}


		// Referência
		Integer referencia = null;
		if (form.getReferencia() != null && !form.getReferencia().equals("")) {
			//peloMenosUmParametroInformado = true;
			
			referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getReferencia());
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			if (referencia > sistemaParametro.getAnoMesFaturamento()) {
				throw new ActionServletException("atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, Util.somaMesMesAnoComBarra((Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento())),1));
			}
		}


		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioAnaliseImovelCorporativoGrande relatorio = new RelatorioAnaliseImovelCorporativoGrande(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("idGerenciaRegional",idGerenciaRegional);
		relatorio.addParametro("idUnidadeNegocio",idUnidadeNegocio);
		relatorio.addParametro("idImovelPerfil",idImovelPerfil);
		relatorio.addParametro("idCategoria",idCategoria);
		relatorio.addParametro("idLigacaoEsgotoSituacao",idLigacaoEsgotoSituacao);
		relatorio.addParametro("idLigacaoAguaSituacao",idLigacaoAguaSituacao);
		relatorio.addParametro("selecionar", selecionar);
		relatorio.addParametro("referencia", referencia);
		relatorio.addParametro("opcaoTotalizacao", opcaoTotalizacao);
		
		if (localidadeInicial != null) {
			relatorio.addParametro("idLocalidadeInicial",localidadeInicial.getId());
		}
		if (localidadeFinal != null) {
			relatorio.addParametro("idLocalidadeFinal",localidadeFinal.getId());
		}
		if (setorComercialInicial != null) {
			relatorio.addParametro("idSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		if (setorComercialFinal != null) {
			relatorio.addParametro("idSetorComercialFinal", setorComercialFinal.getCodigo());
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		return retorno;
	}

}
