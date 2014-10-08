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

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.DadosContratoPPPHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
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
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1467] - Informar/Retirar Situação Factível Faturável de Esgoto
 * @author Arthur Carvalho
 * @date 13/05/13
 */
public class ExibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("informarRetirarSituacaoFactivelFaturavelEsgoto");

		InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form = (InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm) actionForm;
		
		//Coloca na sessao o caminho para pesquisar os objetos da inscricao do imóvel. 
		//Necessario caso o programador adicione o include filtro_inscricao_inicial_final.jsp
		sessao.setAttribute("pesquisarObjetoPeloCaminho", "exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do");
		
		//Carrega os valores na primeira vez que o usuario entra na funcionalidade
		if ( httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").toString().equals("sim") ) {
			form.setPercentualColeta("100");
			form.setIndicadorTipoOperacao(String.valueOf(ConstantesSistema.SIM));
			limpar(null, form);
			
			//Pesquisa todos os municipios ativos.
			pesquisarMunicipio(sessao);
			
			//Pesquisa tipo solicitacao
			pesquisarTipoSolicitacao(sessao);
		}
		
		//Caso o usuario selecione concluir.
		//Caso o usuario manipule/recarregue a pagina de conclusao.
		if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").toString().equals("concluir") ) {
			
			ActionForward ret = acaoInformarRetirarImovelFactivelFaturavel(httpServletRequest, sessao, form, actionMapping);
			
			//redireciona para a tela de informar ou retirar situação da ligação de esgoto.
			if ( ret != null ) {
				return ret;
			}
		}
			
		
		//Verifica se o usuario quer realizar alguma pesquisa no sistema.
		//Pesquisa da inscrição do imóvel inicial e final
		if ( httpServletRequest.getParameter("acao") != null && !httpServletRequest.getParameter("acao").toString().equals("") ) {
			
			//pesquisa informacoes para exibir no sistema.
			pesquisarDadosDoFormulario(httpServletRequest.getParameter("acao"), form, httpServletRequest);
			
		} else {

			
			//Caso o usuario tenha informado municipio pesquisa os logradouros.
			if ( form.getIdMunicipio() != null && 
					!form.getIdMunicipio().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO) )) {
				
				pesquisarLogradouro(sessao, form.getIdMunicipio());
			} else {
				sessao.removeAttribute("colecaoLogradouro");
				form.setIdLogradouro(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			}
			
			//Caso o usuario tenha informado municipio pesquisa os logradouros.
			if ( form.getIdTipoSolicitacaoRA() != null && 
					!form.getIdTipoSolicitacaoRA().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO) )) {
				
				pesquisarTipoSolicitacaoEspecificacao(sessao, form.getIdTipoSolicitacaoRA());
			} else {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			}
		}
		
		if ( httpServletRequest.getParameter("voltar") != null && httpServletRequest.getParameter("voltar").toString().equals("sim") ) {
			form.setIdTipoSolicitacaoRA(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			form.setIdEspecificacao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			form.setDataLigacao("");
			form.setIdEspecificacao(null);
			form.setIndicadorLigacao("1");
			form.setIndicadorCaixaGordura("2");
			form.setDiametroLigacao(null);
			form.setMaterialLigacao(null);
			form.setPerfilLigacao(null);
			form.setPercentualColeta("100");
			
			form.setPercentualEsgoto(null);
			form.setIdLigacaoOrigem(null);
			form.setCondicaoEsgotamento(null);
			form.setSituacaoCaixaInspecao(null);
			form.setDestinoDejetos(null);
			form.setDestinoAguasPluviais(null);
			form.setQtdTotalImoveis(null);
			
		}
		
		return retorno;
	}
	
	
	private void pesquisarMunicipio(HttpSession sessao) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.NOME);
		filtroMunicipio.adicionarParametro( new ParametroSimples( FiltroMunicipio.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<Municipio> colecaoMunicipio = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		
	}
	
	private void pesquisarLogradouro(HttpSession sessao, String idMunicipio) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro(FiltroLogradouro.NOME);
		filtroLogradouro.adicionarParametro( new ParametroSimples( FiltroLogradouro.INDICADORUSO, ConstantesSistema.SIM));
		filtroLogradouro.adicionarParametro( new ParametroSimples( FiltroLogradouro.ID_MUNICIPIO, Integer.valueOf(idMunicipio)));
		
		Collection<Logradouro> colecaoLogradouro = Fachada.getInstancia().pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		sessao.setAttribute("colecaoLogradouro", colecaoLogradouro);
	}
	
	private void pesquisarTipoSolicitacao(HttpSession sessao) {
		
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(FiltroSolicitacaoTipo.DESCRICAO);
		filtroSolicitacaoTipo.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.SIM));
		filtroSolicitacaoTipo.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_FACTIVEL_FATURAVEL, ConstantesSistema.SIM));
		filtroSolicitacaoTipo.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, ConstantesSistema.NAO));
		
		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = Fachada.getInstancia().pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		
		sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
	}
	
	private void pesquisarTipoSolicitacaoEspecificacao(HttpSession sessao, String idSolicitacaoTipo) {
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		filtroSolicitacaoTipoEspecificacao.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, Integer.valueOf(idSolicitacaoTipo)));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.INDICADOR_FACTIVEL_FATURAVEL, ConstantesSistema.SIM));
		
		Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = Fachada.getInstancia().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
		
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
	}
	
	private void pesquisarDadosDoFormulario(String acao, InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form, HttpServletRequest request) {
		
		if ( acao.equals("pesquisarLocalidadeInicial") ) {
			
			//verifica se a localidade foi informada.
			if ( form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("") ) {
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro( new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));
				
				Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if ( colecaoLocalidade != null && !colecaoLocalidade.isEmpty() ) {
					
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
					
					form.setIdLocalidadeInicial(localidade.getId().toString());
					form.setIdLocalidadeFinal(localidade.getId().toString());
					form.setDescricaoLocalidadeInicial(localidade.getDescricao());
					form.setDescricaoLocalidadeFinal(localidade.getDescricao());
					limpar("setorComercialInicial", form);
					
				} else {
					limpar("", form);
					form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
					form.setDescricaoLocalidadeFinal("");
					request.setAttribute("localidadeInicialEncontrada", true);
				}
			
			} else {
				throw new ActionServletException("atencao.naoinformado", null, "Localidade Inicial");
			}
			
		} else if ( acao.equals("pesquisarSetorInicial") ) {
			
			//verifica se a localidade foi informada.
			if ( form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("") ) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidadeInicial()));
				filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialInicial()));
				
				Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if ( colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty() ) {
					
					SetorComercial SetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					
					form.setCodigoSetorComercialInicial(String.valueOf(SetorComercial.getCodigo()));
					form.setCodigoSetorComercialFinal(String.valueOf(SetorComercial.getCodigo()));
					form.setDescricaoSetorComercialInicial(SetorComercial.getDescricao());
					form.setDescricaoSetorComercialFinal(SetorComercial.getDescricao());
					
					limpar("quadraInicial", form);
		
				} else {
					limpar("setorComercialInicial", form);
					form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
					form.setDescricaoSetorComercialFinal("");
					request.setAttribute("setorComercialInicialEncontrado", true);
				}
			
			} else {
				throw new ActionServletException("atencao.naoinformado", null, "Setor Comercial Inicial");
			}
			
			
		} else if ( acao.equals("pesquisarLocalidadeFinal") ) {
			
			//verifica se a localidade inicial foi informada.
			if ( form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("") ) {
				
				if ( form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("") ) {
				
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro( new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));
					
					Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
					
					if ( colecaoLocalidade != null && !colecaoLocalidade.isEmpty() ) {
						
						Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
						
						form.setIdLocalidadeFinal(localidade.getId().toString());
						form.setDescricaoLocalidadeFinal(localidade.getDescricao());
						limpar("setorComercialInicial", form);
					
					} else {
						limpar("localidadeFinal", form);
						form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
						request.setAttribute("localidadeFinalEncontrada", true);
					}
				
				} else {
					throw new ActionServletException("atencao.naoinformado", null, "Localidade");
				}
			} else {
				throw new ActionServletException("atencao.naoinformado", null, "Localidade Inicial");
			}
				
			
		} else if ( acao.equals("pesquisarSetorFinal") ) {
			
			//verifica se a localidade foi informada.
			if ( form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("") ) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidadeFinal()));
				filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialFinal()));
				
				Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if ( colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty() ) {
					
					SetorComercial SetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					
					form.setCodigoSetorComercialFinal(String.valueOf(SetorComercial.getCodigo()));
					form.setDescricaoSetorComercialFinal(SetorComercial.getDescricao());
					
					limpar("quadraInicial", form);
		
				} else {
					limpar("setorComercialFinal", form);
					form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
					request.setAttribute("setorComercialFinalEncontrado", true);
				}
			
			} else {
				throw new ActionServletException("atencao.naoinformado", null, "Setor Comercial Inicial");
			}
			
			
		} else if ( acao.equals("pesquisarQuadraInicial") ) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			
			filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, Integer.valueOf(form.getIdLocalidadeInicial())));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, Integer.valueOf(form.getCodigoSetorComercialInicial())));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, Integer.valueOf(form.getNumeroQuadraInicial())));
			
			Collection<Quadra> colecaoQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
					
			if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
				
				//QUADRA ENCONTRADA
				Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
				
				form.setNumeroQuadraInicial(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
				form.setNumeroQuadraFinal(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
				
				//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
				String msg = "Rota:" + quadra.getRota().getCodigo().toString();
				
				request.setAttribute("msgQuadra", msg);
				request.setAttribute("msgQuadraFinal", msg);
			
			} else {
						
				request.setAttribute("codigoQuadraNaoEncontrada", "true");
				request.setAttribute("msgQuadra","QUADRA INEXISTENTE");

				limpar("quadraInicial", form);
			}
		} else if ( acao.equals("pesquisarQuadraFinal") ) {
			
			//verifica se a localidade foi informada.
			if ( form.getNumeroQuadraInicial() != null && !form.getNumeroQuadraInicial().equals("") ) {
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, Integer.valueOf(form.getIdLocalidadeFinal())));
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, Integer.valueOf(form.getCodigoSetorComercialFinal())));
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, Integer.valueOf(form.getNumeroQuadraFinal())));
				
				Collection<Quadra> colecaoQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
						
				if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
					
					//QUADRA ENCONTRADA
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
					
					form.setNumeroQuadraFinal(Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
					
					//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
	//				String msg = "Rota:" + quadra.getRota().getCodigo().toString();
	//				
	//				request.setAttribute("msgQuadraFinal", msg);
				
				} else {
							
					request.setAttribute("codigoQuadraFinalNaoEncontrada", "true");
					request.setAttribute("msgQuadraFinal","QUADRA INEXISTENTE");
					limpar("quadraFinal", form);
				}
			} else {
				throw new ActionServletException("atencao.naoinformado", null, "Quadra Inicial");
			}
		}
	}
	
	private void limpar(String tipo, InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form) {
		
		if ( tipo == null ){
			form.setIdLocalidadeInicial("");
			form.setIdLocalidadeFinal("");
			form.setDescricaoLocalidadeInicial("");
			form.setDescricaoLocalidadeFinal("");
			form.setCodigoSetorComercialInicial("");
			form.setCodigoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
			form.setIdMunicipio(null);
			form.setIdLogradouro(null);
			form.setIdTipoSolicitacaoRA(null);
			form.setDataLigacao("");
			form.setIdEspecificacao(null);
			form.setIndicadorLigacao("1");
			form.setIndicadorCaixaGordura("2");
			form.setDiametroLigacao(null);
			form.setMaterialLigacao(null);
			form.setPerfilLigacao(null);
			form.setPercentualColeta("100");
			
			form.setPercentualEsgoto(null);
			form.setIdLigacaoOrigem(null);
			form.setCondicaoEsgotamento(null);
			form.setSituacaoCaixaInspecao(null);
			form.setDestinoDejetos(null);
			form.setDestinoAguasPluviais(null);
			form.setQtdTotalImoveis(null);
			
		} else if ( tipo.equals("setorComercialInicial") ) {
			form.setCodigoSetorComercialInicial("");
			form.setCodigoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		} else if ( tipo.equals("setorComercialFinal") ) {
			form.setCodigoSetorComercialFinal("");
			form.setDescricaoSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		} else if ( tipo.equals("quadraInicial") ) {
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		} else if ( tipo.equals("quadraFinal") ) {
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		} else if ( tipo.equals("localidadeInicial") ) {
			form.setIdLocalidadeInicial("");
			form.setIdLocalidadeFinal("");
			form.setDescricaoLocalidadeInicial("");
			form.setDescricaoLocalidadeFinal("");
			form.setCodigoSetorComercialInicial("");
			form.setCodigoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		}  else if ( tipo.equals("localidadeFinal") ) {
			form.setIdLocalidadeFinal("");
			form.setDescricaoLocalidadeFinal("");
			form.setCodigoSetorComercialInicial("");
			form.setCodigoSetorComercialFinal("");
			form.setDescricaoSetorComercialInicial("");
			form.setDescricaoSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setNumeroRotaInicial("");
			form.setNumeroRotaFinal("");
			form.setNumeroSequencialRotaInicial("");
			form.setNumeroSequencialRotaFinal("");
		} else if (tipo.equals("informarRetirar")) {
			form.setIdTipoSolicitacaoRA(null);
			form.setDataLigacao("");
			form.setIdEspecificacao(null);
			form.setIndicadorLigacao("1");
			form.setIndicadorCaixaGordura("2");
			form.setDiametroLigacao(null);
			form.setMaterialLigacao(null);
			form.setPerfilLigacao(null);
			form.setPercentualColeta("100");
			form.setPercentualEsgoto(null);
			form.setIdLigacaoOrigem(null);
			form.setCondicaoEsgotamento(null);
			form.setSituacaoCaixaInspecao(null);
			form.setDestinoDejetos(null);
			form.setDestinoAguasPluviais(null);
			form.setQtdTotalImoveis(null);
			
		}
	}
	
	
	/**
	 * Monta os select´s obrigatorios
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {
		
		// Filtro para o campo Diametro Ligação Água
		Collection colecaoDiametroLigacao = (Collection) sessao.getAttribute("colecaoDiametroLigacaoAgua");
		
		if (colecaoDiametroLigacao == null){

			FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();

			filtroDiametroLigacaoEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroDiametroLigacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacaoEsgoto
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = this.getFachada().pesquisar(
					filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
							.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacao",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Ligação");
			}
		}

		// Filtro para o campo Material da Ligação
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroLigacaoMaterialEsgoto filtroLigacaoMaterialEsgoto = new FiltroLigacaoMaterialEsgoto();
			filtroLigacaoMaterialEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoMaterialEsgoto
					.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

			colecaoMaterialLigacao = this.getFachada().pesquisar(
					filtroLigacaoMaterialEsgoto, LigacaoEsgotoMaterial.class
							.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Ligação");
			}
		}

		// Filtro para o campo Perfil da Ligação
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoPerfil
					.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.DESCRICAO);

			colecaoPerfilLigacao = this.getFachada().pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Perfil de Ligação");
			}
		}

		// Filtro para o campo Motivo nao cobranca
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = this.getFachada().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}

		// Filtro para o campo ligação esgotamento
		Collection colecaoLigacaoEsgotoEsgotamento = (Collection) sessao.getAttribute("colecaoLigacaoEsgotoEsgotamento");

		if (colecaoLigacaoEsgotoEsgotamento == null){

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoEsgotamento
					.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);

			colecaoLigacaoEsgotoEsgotamento = this.getFachada().pesquisar(
					filtroLigacaoEsgotoEsgotamento,
					LigacaoEsgotoEsgotamento.class.getName());

			if (colecaoLigacaoEsgotoEsgotamento != null
					&& !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoEsgotoEsgotamento",
						colecaoLigacaoEsgotoEsgotamento);
			}
		}

		// Filtro para o campo destino dos dejetos
		Collection colecaoDestinoDejetos = (Collection) sessao
				.getAttribute("colecaoDestinoDejetos");

		if (colecaoDestinoDejetos == null) {

			FiltroLigacaoEsgotoDestinoDejetos filtroDestinoDejetos = new FiltroLigacaoEsgotoDestinoDejetos();
			filtroDestinoDejetos.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoDejetos.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoDejetos
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoDejetos.DESCRICAO);

			colecaoDestinoDejetos = this.getFachada().pesquisar(filtroDestinoDejetos,
					LigacaoEsgotoDestinoDejetos.class.getName());

			if (colecaoDestinoDejetos != null
					&& !colecaoDestinoDejetos.isEmpty()) {
				sessao.setAttribute("colecaoDestinoDejetos",
						colecaoDestinoDejetos);
			}
		}

		// Filtro para o campo caixa de inspeção
		Collection colecaoSituacaoCaixaInspecao = (Collection) sessao.getAttribute("colecaoSituacaoCaixaInspecao");

		if (colecaoSituacaoCaixaInspecao == null) {

			FiltroLigacaoEsgotoCaixaInspecao filtroSituacaoCaixaInspecao = new FiltroLigacaoEsgotoCaixaInspecao();
			filtroSituacaoCaixaInspecao
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoCaixaInspecao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSituacaoCaixaInspecao
					.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			colecaoSituacaoCaixaInspecao = this.getFachada().pesquisar(
					filtroSituacaoCaixaInspecao,
					LigacaoEsgotoCaixaInspecao.class.getName());

			if (colecaoSituacaoCaixaInspecao != null
					&& !colecaoSituacaoCaixaInspecao.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoCaixaInspecao",
						colecaoSituacaoCaixaInspecao);
			}
		}

		// Filtro para o campo destino caixas pluviais
		Collection colecaoDestinoAguasPluviais = (Collection) sessao
				.getAttribute("colecaoDestinoAguasPluviais");

		if (colecaoDestinoAguasPluviais == null) {

			FiltroLigacaoEsgotoDestinoAguasPluviais filtroDestinoAguasPluviais = new FiltroLigacaoEsgotoDestinoAguasPluviais();
			filtroDestinoAguasPluviais.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoAguasPluviais.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoAguasPluviais
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoAguasPluviais.DESCRICAO);

			colecaoDestinoAguasPluviais = this.getFachada().pesquisar(
					filtroDestinoAguasPluviais,
					LigacaoEsgotoDestinoAguasPluviais.class.getName());

			if (colecaoDestinoAguasPluviais != null
					&& !colecaoDestinoAguasPluviais.isEmpty()) {
				sessao.setAttribute("colecaoDestinoAguasPluviais",
						colecaoDestinoAguasPluviais);
			}
		}

		// Filtro para o campo Ligacao origem
		Collection colecaoLigacaoOrigem = (Collection) sessao
				.getAttribute("colecaoLigacaoOrigem");

		if (colecaoLigacaoOrigem == null) {

			FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();

			filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
					FiltroLigacaoOrigem.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

			colecaoLigacaoOrigem = this.getFachada().pesquisar(filtroLigacaoOrigem,
					LigacaoOrigem.class.getName());

			if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoOrigem",
						colecaoLigacaoOrigem);
			} else {
				sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
			}
		}
	}

	public DadosContratoPPPHelper montarHelper(InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form) {
		DadosContratoPPPHelper helper = new DadosContratoPPPHelper();
		 
		helper.setIndicadorTipoOperacao(form.getIndicadorTipoOperacao());
		helper.setIdMunicipio(form.getIdMunicipio());
		helper.setIdLogradouro(form.getIdLogradouro());
		helper.setIdLocalidadeInicial(form.getIdLocalidadeInicial());
		helper.setIdLocalidadeFinal( form.getIdLocalidadeFinal());
		helper.setCodigoSetorComercialInicial(form.getCodigoSetorComercialInicial());
		helper.setCodigoSetorComercialFinal(form.getCodigoSetorComercialFinal());
		helper.setNumeroQuadraInicial(form.getNumeroQuadraInicial());
		helper.setNumeroQuadraFinal(form.getNumeroQuadraFinal());
		helper.setNumeroRotaInicial(form.getNumeroRotaInicial());
		helper.setNumeroRotaFinal(form.getNumeroRotaFinal());
		helper.setNumeroSequencialRotaInicial(form.getNumeroSequencialRotaInicial());
		helper.setNumeroSequencialRotaFinal(form.getNumeroSequencialRotaFinal());
		
		helper.setIdTipoSolicitacaoRA(form.getIdTipoSolicitacaoRA());
		helper.setIdEspecificacao( form.getIdEspecificacao() ) ;
		helper.setDataLigacao( form.getDataLigacao() );
		
		return helper;
	}
	
	/**
	 * Pesquisa o percentual de esgoto a partir do perfil da ligacao.
	 * 
	 * @param form
	 */
	public void pesquisarPercentualEsgoto(InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form){
		
		if (form.getPerfilLigacao() != null && !form.getPerfilLigacao().equals("-1")) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoPercentualEsgoto.adicionarParametro( new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.ID, Integer.valueOf(form.getPerfilLigacao())));

			Collection colecaoPercentualEsgoto = this.getFachada().pesquisar( filtroLigacaoPercentualEsgoto, LigacaoEsgotoPerfil.class.getName());

			if (colecaoPercentualEsgoto != null && !colecaoPercentualEsgoto.isEmpty()) {

				LigacaoEsgotoPerfil percentualEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

				String percentualFormatado = percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

				form.setPercentualEsgoto(percentualFormatado);
			}
		}
	}
	
	public ActionForward acaoInformarRetirarImovelFactivelFaturavel(HttpServletRequest httpServletRequest, HttpSession sessao, 
			InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm form, ActionMapping actionMapping){
		//pesquisa o percentual de esgoto 
		if ( httpServletRequest.getParameter("ligacaoPerfil") != null && httpServletRequest.getParameter("ligacaoPerfil").toString().equals("sim") ) {
			
			pesquisarPercentualEsgoto(form);
		}
		
		//Pesquisar a especificacao
		if ( httpServletRequest.getParameter("pesquisarEspecificacao") != null && httpServletRequest.getParameter("pesquisarEspecificacao").toString().equals("sim") ) {
			
			pesquisarTipoSolicitacaoEspecificacao(sessao, form.getIdTipoSolicitacaoRA());
		}
		
		//Limpar
		if ( httpServletRequest.getParameter("limpar") != null && httpServletRequest.getParameter("limpar").toString().equals("sim") ) {
			
			limpar("informarRetirar", form);
			sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			
		}
		
		//pesquisar os imóveis
		Collection<Integer> colecaoIdImovel = Fachada.getInstancia().pesquisarImovelPPP(montarHelper(form));
		
		if ( colecaoIdImovel  != null && !colecaoIdImovel.isEmpty() ) {
			
			//atualiza a quantidade de imoveis
			form.setQtdTotalImoveis(String.valueOf(colecaoIdImovel.size()));
			sessao.setAttribute("colecaoIdImovelPPP", colecaoIdImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,	"Imóvel");
		}
		
		
		if (  form.getIndicadorTipoOperacao().equals("2") ) {
			
			//muda a tela para informar os dados da ra.
			return actionMapping.findForward("retirarDadosDaLigacaoEsgoto");	
			
		} else if (form.getIndicadorTipoOperacao().equals("1")  ) {
			
			//carrega as informações da tela - dados da ligacao de esgoto.
			this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));
				
			//muda a tela para informar os dados da ligacao esgoto.
			return actionMapping.findForward("informarDadosDaLigacaoEsgoto");
			
		}
		
		return null;
	}
}
