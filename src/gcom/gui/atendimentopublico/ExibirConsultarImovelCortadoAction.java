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

import gcom.atendimentopublico.bean.FiltrarImoveisCortadosHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1279] - Consultar Imóveis Com Situação da Ligação de Água Cortado
 * 
 * @author Arthur Carvalho
 * @created 14/02/2012
 */
public class ExibirConsultarImovelCortadoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("exibirConsultarImovelCortadoAction");

		FiltrarImovelCortadoActionForm form = (FiltrarImovelCortadoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa gerencia regional
		this.pesquisarGerenciaRegional(httpServletRequest);
		
		//Pesquisa Motivo Corte
		this.pesquisarMotivoCorte(httpServletRequest);
		
		if ( form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("-1") ) {
			this.pesquisarUnidadeNegocio(httpServletRequest, form);
			form.setIdLocalidadeFinal("");
			form.setDescricaoLocalidadeFinal("");
			form.setDescricaoLocalidadeInicial("");
			form.setIdLocalidadeInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setIdSetorComercialFinal("");
			form.setIdSetorComercialInicial("");
			form.setIdQuadraFinal("");
			form.setIdQuadraInicial("");
			form.setIdRegistro(null);
		}
		
		//Pesquisa Localidade | Setor Comercial  | Quadra
		if ( httpServletRequest.getParameter("objetoConsulta") != null && !httpServletRequest.getParameter("objetoConsulta").equals("") ) {
			
			String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
			pesquisarCampos(form, objetoConsulta, httpServletRequest);
			httpServletRequest.setAttribute("colecaoHelper", null);
			httpServletRequest.getSession().setAttribute("colecaoHelper", null);
		} 

		//SB0001 - Pesquisar Imoveis Cortados
		Collection<FiltrarImoveisCortadosHelper> colecaoHelper = null;
		FiltrarImoveisCortadosHelper filtro = new FiltrarImoveisCortadosHelper();
		if (  (httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("pesquisa")) || 
				httpServletRequest.getParameter("page.offset") != null && !httpServletRequest.getParameter("page.offset").equals("") ) {
			
			
			if ( httpServletRequest.getParameter("voltar") != null && httpServletRequest.getParameter("voltar").equals("ok") ) {
				form.setIdRegistro(null);
			}
			
			//FS0008 - Valida periodo de corte
			this.validaTipoCorte(form);
			
			if ( form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdLocalidadeInicial()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Localidade Inicial");
				}
				validarLocalidade(form);
			}
			
			if ( form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdLocalidadeFinal()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Localidade Final");
				}
				validarLocalidadeFinal(form);
			}
			
			if ( form.getIdSetorComercialInicial() != null && !form.getIdSetorComercialInicial().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdSetorComercialInicial()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Setor Comercial Inicial");
				}
				validarSetorComercialInicial(form);
			}
			
			if ( form.getIdSetorComercialFinal() != null && !form.getIdSetorComercialFinal().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdSetorComercialFinal()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Setor Comercial Final");
				}
				validarSetorComercialFinal(form);
			}
			
			if ( form.getIdQuadraInicial() != null && !form.getIdQuadraInicial().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdQuadraInicial()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Quadra Inicial");
				}
				validarQuadra(form);
			}
			
			if ( form.getIdQuadraFinal() != null && !form.getIdQuadraFinal().equals("") ) {
				if ( !Util.validarStringNumerica(form.getIdQuadraFinal()) ) {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null,
							"Quadra Final");
				}
				validarQuadraFinal(form);
			}
			
			String[] mensagem = new String[2];
			mensagem[0] = "Valor Débito Inicial";
			mensagem[1] = "Valor Débito Final";
			if (form.getValorDebitoInicial() != null && !form.getValorDebitoInicial().equals("") ) {
				if( Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoInicial()).compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoFinal())) > 0 ) {
					throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial", null, mensagem );
				}
				
			}
			
			filtro.setDataCorteInicial(form.getPeriodoCorteInicial());
			filtro.setDataCorteFinal(form.getPeriodoCorteFinal());
			filtro.setGerenciaRegional(form.getIdGerenciaRegional());
			filtro.setUnidadeNegocio(form.getIdUnidadeNegocio());
			filtro.setLocalidadeInicial(form.getIdLocalidadeInicial());
			filtro.setLocalidadeFinal(form.getIdLocalidadeFinal());
			filtro.setSetorComercialInicial(form.getIdSetorComercialInicial());
			filtro.setSetorComercialFinal(form.getIdSetorComercialFinal());
			filtro.setQuadraInicial(form.getIdQuadraInicial());
			filtro.setQuadraFinal(form.getIdQuadraFinal());
			filtro.setIdMotivoCorte(form.getIdMotivoCorte());
			filtro.setValorDebitoInicial(form.getValorDebitoInicial());
			filtro.setValorDebitoFinal(form.getValorDebitoFinal());
			filtro.setIndicadorGerarOSFiscalizacao(form.getIndicadorGerarOSFiscalizacao());
			
			Integer totalRegistros = fachada.pesquisarImoveisCortadosCount(filtro);
			
			// 2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
			
			colecaoHelper = new ArrayList<FiltrarImoveisCortadosHelper>();
			
			colecaoHelper = fachada.pesquisarImoveisCortados(filtro, ((Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa")));
			
			if ( colecaoHelper == null || colecaoHelper.isEmpty() ) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", "exibirConsultarImovelCortadoAction.do?acao=limpar", null, new String[0] );
			} else {
				form.setBotaoGerarOrdemServico("habilita");
			}
			
			httpServletRequest.setAttribute("colecaoHelper", colecaoHelper);
			httpServletRequest.getSession().setAttribute("colecaoHelper", colecaoHelper);
			
		}
		
		
		if (  httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("limpar") ) {
			form.setIdLocalidadeFinal("");
			form.setDescricaoLocalidadeFinal("");
			form.setDescricaoLocalidadeInicial("");
			form.setIdLocalidadeInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setIdSetorComercialFinal("");
			form.setIdSetorComercialInicial("");
			form.setIdQuadraFinal("");
			form.setIdQuadraInicial("");
			httpServletRequest.setAttribute("colecaoHelper", null);
			httpServletRequest.getSession().setAttribute("colecaoHelper", null);
			form.setIdGerenciaRegional("-1");
			form.setIdUnidadeNegocio("-1");
			form.setValorDebitoFinal("");
			form.setValorDebitoInicial("");
			form.setIdMotivoCorte("-1");
			form.setIndicadorGerarOSFiscalizacao("1");
			form.setIdRegistro(null);
			form.setPeriodoCorteFinal("");
			form.setPeriodoCorteInicial("");
			form.setBotaoGerarOrdemServico("desabilita");
		}
		
		return retorno;
	}
	
	private void validaTipoCorte(FiltrarImovelCortadoActionForm form) {
		Date periodoInicial = null;
		if ( form.getPeriodoCorteInicial() != null && !form.getPeriodoCorteInicial().equals("") ) {
			periodoInicial = Util.converteStringParaDate(form.getPeriodoCorteInicial());
		}
		
		Date periodoFinal = null;
		if ( form.getPeriodoCorteFinal() != null && !form.getPeriodoCorteFinal().equals("") ) {
			periodoFinal = Util.converteStringParaDate(form.getPeriodoCorteFinal());
		}
		
		if ( periodoInicial == null && periodoFinal == null ) {
			throw new ActionServletException("atencao.campo_texto.obrigatorio",
					"Periodo de Corte");
		} else if ( periodoInicial != null && periodoFinal == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Corte Final");
		} else if ( periodoFinal != null && periodoInicial == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Corte Inicial");
		} else if ( Util.compararData(periodoFinal, new Date()) > 0) {
			throw new ActionServletException("atencao.data_final_menor_data_atual",
					"Periodo de Corte Final");
		} else if ( periodoInicial != null && periodoFinal != null && Util.compararData(periodoInicial, periodoFinal) > 0 ) {
			String[] mensagem = new String[2];
			mensagem[0] = "Periodo de Corte Inicial";
			mensagem[1] = "Periodo de Corte Final";
			throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial",null,  mensagem );
		} else if ( Util.obterQuantidadeDiasEntreDuasDatasPositivo(periodoInicial, periodoFinal) > 30) {
			throw new ActionServletException("atencao.processo_iniciado.limite_trinta_dias");
		}
	}
	
	private void pesquisarCampos(FiltrarImovelCortadoActionForm form, String objetoConsulta, HttpServletRequest httpServletRequest) {
		//Pesquisa Localidade Inicial e Final
		if ( objetoConsulta.equals("1") ) {
			this.pesquisarLocalidade(form, objetoConsulta, httpServletRequest);

		} else if ( objetoConsulta.equals("2") ) {
			//Pesquisa Localidade Final
			this.pesquisarLocalidadeFinal(form, objetoConsulta, httpServletRequest);

		} else if ( objetoConsulta.equals("3") ) {
			//Pesquisa Setor Comercial inicial
			this.pesquisarSetorComercialInicial(form, objetoConsulta, httpServletRequest);
		
		} else if ( objetoConsulta.equals("4") ) {
			//Pesquisa Setor Comercial Final
			this.pesquisarSetorComercialFinal(form, objetoConsulta, httpServletRequest);
		
		} else if ( objetoConsulta.equals("5") ) {
			//Pesquisa Quadra Inicial
			form.setMensagemQuadra(this.pesquisarQuadra(form, objetoConsulta));
		
		}  else if ( objetoConsulta.equals("6") ) {
			//Pesquisa Quadra Final
			form.setMensageQuadraFinal(this.pesquisarQuadraFinal(form, objetoConsulta));
		}
		
	}
	
	private void pesquisarMotivoCorte(HttpServletRequest httpServletRequest){
		
		FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
		filtroMotivoCorte.adicionarParametro( new ParametroSimples(FiltroMotivoCorte.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<MotivoCorte> colecaoMotivoCorte = Fachada.getInstancia().pesquisar(filtroMotivoCorte , MotivoCorte.class.getName());
		
		if (colecaoMotivoCorte == null
				|| colecaoMotivoCorte.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Motivo Corte");
		} else {
			httpServletRequest.setAttribute("colecaoMotivoCorte",
					colecaoMotivoCorte);
		}

		
		
		
	}
	
	private void pesquisarLocalidade(FiltrarImovelCortadoActionForm form, String objetoConsulta, HttpServletRequest httpServletRequest) {


		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			if (objetoConsulta.trim().equals("1")) {
				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setDescricaoLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setIdLocalidadeFinal(localidade.getId().toString());
			form.setDescricaoLocalidadeFinal(localidade.getDescricao());

		} else {
			if (objetoConsulta.trim().equals("1")) {
				form.setIdLocalidadeInicial(null);
				form.setDescricaoLocalidadeInicial("Localidade Inexistente");

				form.setIdLocalidadeFinal(null);
				form.setDescricaoLocalidadeFinal(null);
				httpServletRequest.setAttribute("localidadeEncontrada",true);
			}
		}
	}

	private void pesquisarLocalidadeFinal(FiltrarImovelCortadoActionForm form, String objetoConsulta, HttpServletRequest httpServletRequest) {


		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			form.setIdLocalidadeFinal(localidade.getId().toString());
			form.setDescricaoLocalidadeFinal(localidade.getDescricao());

		} else {
			form.setIdLocalidadeFinal(null);
			form.setDescricaoLocalidadeFinal("Localidade Inexistente");
			httpServletRequest.setAttribute("localidadeFinalEncontrado", true);
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = this.getFachada().pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null
				|| colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",
					colecaoGerenciaRegional);
		}
	}

	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			FiltrarImovelCortadoActionForm form) {

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		if (form.getIdGerenciaRegional() != null
				&& !form.getIdGerenciaRegional().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID_GERENCIA, form
							.getIdGerenciaRegional()));
		}

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		}
	}
	
	private void pesquisarSetorComercialInicial(FiltrarImovelCortadoActionForm form, String objetoConsulta, HttpServletRequest httpServletRequest) {

		String loca = form.getIdLocalidadeInicial();

		if (!objetoConsulta.trim().equals("3")) {
			loca = form.getIdLocalidadeFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialInicial()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, loca));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorComercial);

			if (objetoConsulta.trim().equals("3")) {
				form.setIdSetorComercialInicial("" + setorComercial.getCodigo());
				form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setIdSetorComercialFinal("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());

		} else {

			if (objetoConsulta.trim().equals("3")) {
				form.setIdSetorComercialInicial(null);
				form.setDescricaoSetorComercialInicial("Setor Comercial Inexistente");

				form.setIdSetorComercialFinal(null);
				form.setDescricaoSetorComercialFinal(null);
				httpServletRequest.setAttribute("setorEncontrado", true);
			}

		}
	}
	
    private void pesquisarSetorComercialFinal(FiltrarImovelCortadoActionForm form, String objetoConsulta, HttpServletRequest httpServletRequest) {

		String loca = form.getIdLocalidadeInicial();

		if (!objetoConsulta.trim().equals("3")) {
			loca = form.getIdLocalidadeFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialFinal()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, loca));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorComercial);

			form.setIdSetorComercialFinal("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());

		} else {

			form.setIdSetorComercialFinal(null);
			form.setDescricaoSetorComercialFinal("Setor Comercial Inexistente");
			httpServletRequest.setAttribute("setorFinalEncontrado", true);
		}
	}
	
	private String pesquisarQuadra(FiltrarImovelCortadoActionForm form, String objetoConsulta) {

		String msgQuadra = "";
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraInicial())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
			
			// QUADRA ENCONTRADA
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadras);

			form.setIdQuadraInicial(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
			form.setIdQuadraFinal(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
			
			
			//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
			String msg = "ROTA:" + quadra.getRota().getCodigo().toString();

			msgQuadra = msg;

		
		} 
		else {
			form.setIdQuadraInicial("");
			form.setIdQuadraFinal("");
			msgQuadra = "<font color='#FF0000'>QUADRA INEXISTENTE</font>";
		}
	
		form.setMensageQuadraFinal(msgQuadra);
		return msgQuadra;
	}
	
	private String pesquisarQuadraFinal(FiltrarImovelCortadoActionForm form, String objetoConsulta) {

		
		String msgQuadra = "";
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraFinal())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
			
			// QUADRA ENCONTRADA
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadras);

			form.setIdQuadraFinal(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
			
			
			//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
			String msg = "ROTA:" + quadra.getRota().getCodigo().toString();

			msgQuadra = msg;
		
		} 
		else {
			
			msgQuadra = "<font color='#FF0000'>QUADRA INEXISTENTE</font>";
		}
	
		return msgQuadra;
	}
	
	private void validarLocalidade(FiltrarImovelCortadoActionForm form) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));

		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Localidade Inicial");
		} else {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setDescricaoLocalidadeInicial(localidade.getDescricao());
		}
	}

	private void validarLocalidadeFinal(FiltrarImovelCortadoActionForm form) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));

		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Localidade Final");
		} else {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setDescricaoLocalidadeFinal(localidade.getDescricao());
		}
	}

	private void validarSetorComercialInicial(FiltrarImovelCortadoActionForm form) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialInicial()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, form.getIdLocalidadeInicial()));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Setor Comercial Inicial");

		} else {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
		}
	}
	
	private void validarSetorComercialFinal(FiltrarImovelCortadoActionForm form) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercialFinal()));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, form.getIdLocalidadeFinal()));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Setor Comercial Inicial");

		} else {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
		}
	}
	
	private void validarQuadra(FiltrarImovelCortadoActionForm form) {

		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraInicial())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras == null || colecaoQuadras.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Quadra Inicial");
		
		}
	}
	
	private void validarQuadraFinal(FiltrarImovelCortadoActionForm form) {
		
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidadeInicial())));

		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercialInicial())));
		
		filtroQuadra.adicionarParametro(new ParametroSimples(
		FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraFinal())));
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras == null || colecaoQuadras.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,
					"Quadra Final");
		}
	}
}