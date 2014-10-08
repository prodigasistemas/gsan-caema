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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.MensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroMensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1279] - Consultar Imóveis Com Situação da Ligação de Água Cortado
 * 
 * @author Arthur Carvalho
 * @created 14/02/2012
 */
public class ExibirFiltrarDadosCadastraisImoveisInconsistentesAction extends GcomAction {
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
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirFiltrarDadosCadastraisImoveisInconsistentesAction");

		FiltrarDadosCadastraisImoveisInconsistentesActionForm form = (FiltrarDadosCadastraisImoveisInconsistentesActionForm) actionForm;

		HttpSession sessao = request.getSession(false);
		
		this.pesquisarEmpresa(request);
		
		this.pesquisarMensagemAtualizacaoCadastral(request);
		
		if ( form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") ) {
			this.pesquisarCadastrador(form.getIdEmpresa(), request);
		}

		if ( request.getParameter("menu") != null && !request.getParameter("menu").equals("sim") ) {
		
			form.setIndicadorSituacaoMovimento("3");
		}
		
		if ( request.getParameter("objetoConsulta") != null && !request.getParameter("objetoConsulta").equals("") ) {
			
			String objetoConsulta = request.getParameter("objetoConsulta");
			
			if ( objetoConsulta.equals("1") ) {
				if ( form.getIdEmpresa() != null && form.getIdEmpresa().equals("-1") ) {
					form.limpar();
				} else { 
					this.pesquisarCadastrador(form.getIdEmpresa(), request);
				}
			} else if ( objetoConsulta.equals("2") ) {
				this.pesquisarLocalidade( form, request);
		
			} else if ( objetoConsulta.equals("3") ) {
				this.pesquisarSetorComercial(form, request);
			
			} else if ( objetoConsulta.equals("4") ) {
				this.pesquisarQuadra(form, request);
			
			} else if ( objetoConsulta.equals("5") ) {
				this.pesquisarQuadraFinal(form, request);
			
			} else if ( objetoConsulta.equals("6") ) {
				this.pesquisarImovel(form, request);
			
			} else if ( objetoConsulta.equals("7") ) {
				this.pesquisarCliente(form, request);
			
			} else if ( objetoConsulta.equals("movimento") ) {
				this.pesquisarDadosMovimento(request, sessao, form);
				
			} else if ( objetoConsulta.equals("imovel") ) {
				this.pesquisarDadosImovel(request, sessao, form);
			
			} else if ( objetoConsulta.equals("cliente") ) {
				this.pesquisarDadosCliente(request, sessao, form);
			
			} else if ( objetoConsulta.equals("documento") ) {
				this.pesquisarDadosDocumento(request, sessao, form);
			
			} else if ( objetoConsulta.equals("total") ) {
				this.pesquisarDadosImovelTotal(request, sessao, form);
				
			} else if ( objetoConsulta.equals("pendente") ) {
				this.pesquisarDadosImovelPendente(request, sessao, form);
				
			} else if ( objetoConsulta.equals("pendenteInscricao") ) {
				this.pesquisarDadosImovelPendenteInscricao(request, sessao, form);
				
			} else if ( objetoConsulta.equals("atualizar") ) {
				this.pesquisarDadosMovimentoParaAtualizar(request, sessao, form);
				
			} else if ( objetoConsulta.equals("limpar") ) {
				form.limpar();
			}
		} 
	
		
		return retorno;
	}
	
	public void pesquisarDadosImovelPendente(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		if ( request.getParameter("id") != null && !request.getParameter("id").equals("") ) {
			String idData = request.getParameter("id") ;
			form.setDataRecebimento(this.recuperaData(idData));
			form.setIdParametroTabelaAtualizacaoCadastral(this.recuperaId(idData));
		}
		
		String sistuacaoPendente = "2";
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
						form.getCodigoCliente(), form.getNumeroDocumento(), sistuacaoPendente,form.getIdLocalidade(), form.getIdSetorComercial(), 
						form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa() );
		
		request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	
	}
	
	public void pesquisarDadosImovelPendenteInscricao(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		if ( request.getParameter("id") != null && !request.getParameter("id").equals("") ) {
			String idData = request.getParameter("id") ;
			form.setDataRecebimento(this.recuperaData(idData));
			form.setIdParametroTabelaAtualizacaoCadastral(this.recuperaId(idData));
		}
		
		String sistuacaoPendente = "3";
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
						form.getCodigoCliente(), form.getNumeroDocumento(), sistuacaoPendente,form.getIdLocalidade(), form.getIdSetorComercial(), 
						form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
		
		request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	
	}
	
	public void pesquisarDadosImovelTotal(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		if ( request.getParameter("id") != null && !request.getParameter("id").equals("") ) {
			String idData = request.getParameter("id") ;
			form.setDataRecebimento(this.recuperaData(idData));
			form.setIdParametroTabelaAtualizacaoCadastral(this.recuperaId(idData));
		}
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
						form.getCodigoCliente(), form.getNumeroDocumento(), null,form.getIdLocalidade(), form.getIdSetorComercial(), 
						form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
		
		request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	
	}
	
	public void pesquisarDadosMovimentoParaAtualizar(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		String[] ids = form.getIdRegistroImovel();
		Collection<Integer> colecaoIdImoveis = new ArrayList<Integer>();
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.nenhum_imovel_selecionado_atualizacao_cadastral");
		}

		if (ids != null && ids.length != 0) {
			// remove todas as unidade executoras informadas
			for (int i = 0; i < ids.length; i++) {
				// atribui a variável "id" o código da unidade executora para
				// remoção
				int id = Integer.parseInt(ids[i]);
				colecaoIdImoveis.add(id);
			}
		}

		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoImoveisInconsistentesHelper = Fachada.getInstancia().pesquisarInconsistenciasDoRecadastramento(colecaoIdImoveis);
		
		if ( (sessao.getAttribute("colecaoImoveisInconsistentesHelper") == null || sessao.getAttribute("colecaoImoveisInconsistentesHelper").equals(""))
				&& colecaoImoveisInconsistentesHelper != null && !colecaoImoveisInconsistentesHelper.isEmpty() ) {
			
			sessao.setAttribute("colecaoImoveisInconsistentesHelper", colecaoImoveisInconsistentesHelper);
			
		} else if ( request.getParameter("acao") != null && request.getParameter("acao").equals("ok") ) {
			sessao.setAttribute("colecaoImoveisInconsistentesHelper", colecaoImoveisInconsistentesHelper);
		}
	}
	
	private void pesquisarImovel(FiltrarDadosCadastraisImoveisInconsistentesActionForm form, HttpServletRequest request) {
		Fachada fachada = Fachada.getInstancia();

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			request.removeAttribute("imovelEncontrada");
			Imovel imovel = colecaoImovel.iterator().next();
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
		} else {
			request.setAttribute("imovelEncontrada", "true");
			
			form.setIdImovel("");
			form.setInscricaoImovel("Matrícula inexistente");
		}
	}
	
	public void pesquisarCliente(FiltrarDadosCadastraisImoveisInconsistentesActionForm form, HttpServletRequest request) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, Integer.valueOf(form.getCodigoCliente())));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			form.setCodigoCliente(""+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			form.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());
			
			request.removeAttribute("clienteEncontrada");
		} else {
			form.setCodigoCliente("");
			request.setAttribute("clienteEncontrada", "true");
			form.setNomeCliente("Cliente Inexistente");

		}
	}
	
	public void pesquisarDadosMovimento(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		Date periodoInicial = null;
		if ( form.getPeriodoMovimentoInicial() != null && !form.getPeriodoMovimentoInicial().equals("") ) {
			periodoInicial = Util.converteStringParaDate(form.getPeriodoMovimentoInicial());
		}
		
		Date periodoFinal = null;
		if ( form.getPeriodoMovimentoFinal() != null && !form.getPeriodoMovimentoFinal().equals("") ) {
			periodoFinal = Util.converteStringParaDate(form.getPeriodoMovimentoFinal());
		}
		
		 if ( periodoInicial != null && periodoFinal == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Movimento Final");
		} else if ( periodoFinal != null && periodoInicial == null ) {
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Movimento Inicial");
		} else if (periodoFinal != null && Util.compararData(periodoFinal, new Date()) > 0) {
			throw new ActionServletException("atencao.data_final_menor_data_atual",
					"Periodo de Movimento Final");
		} else if ( periodoInicial != null && periodoFinal != null && Util.compararData(periodoInicial, periodoFinal) > 0 ) {
			String[] mensagem = new String[2];
			mensagem[0] = "Periodo de Movimento Inicial";
			mensagem[1] = "Periodo de Movimento Final";
			throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial",null, mensagem);
		}
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosMovimentoAtualizacaoCadastralHelper = Fachada.getInstancia().
				pesquisarDadosMovimentoAtualizacaoCadastralHelper(form.getIdLocalidade(), form.getIdSetorComercial(), form.getIdQuadraInicial(), 
						form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
	
		if ( colecaoDadosMovimentoAtualizacaoCadastralHelper != null && !colecaoDadosMovimentoAtualizacaoCadastralHelper.isEmpty() ) {
			form.setIndicadorBloqueiaTela(ConstantesSistema.SIM.toString());
			
			request.setAttribute("colecaoDadosMovimentoAtualizacaoCadastralHelper", colecaoDadosMovimentoAtualizacaoCadastralHelper);
			
			DadosMovimentoAtualizacaoCadastralHelper helperTotal = null;
			Iterator<DadosMovimentoAtualizacaoCadastralHelper> iteratorHelperTotal = colecaoDadosMovimentoAtualizacaoCadastralHelper.iterator();
			while( iteratorHelperTotal.hasNext() ) {
				helperTotal = (DadosMovimentoAtualizacaoCadastralHelper) iteratorHelperTotal.next();
			}
			Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoHelper = new ArrayList<DadosMovimentoAtualizacaoCadastralHelper>();
			colecaoHelper.add(helperTotal);
			request.setAttribute("colecaoDadosMovimentoAtualizacaoCadastralTotalHelper", colecaoHelper);
		} else {
			form.setIndicadorBloqueiaTela(ConstantesSistema.NAO.toString());
			throw new ActionServletException("atencao.nao_existe_imovel_para_filtro_informado");
		}
	}
	
	public void pesquisarDadosImovel(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
						form.getCodigoCliente(), form.getNumeroDocumento(), null,form.getIdLocalidade(), form.getIdSetorComercial(), 
						form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
		
		if ( colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty() ) {
			request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		} else {
			throw new ActionServletException("atencao.nao_existe_dados_movimento_imovel");
		}
		
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	}
	
	public void pesquisarDadosCliente(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
						form.getCodigoCliente(), form.getNumeroDocumento(), null,form.getIdLocalidade(), form.getIdSetorComercial(), 
						form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
						form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
		
		if ( colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty() ) {
			request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		} else {
			throw new ActionServletException("atencao.nao_existe_dados_movimento_cliente");
		}
		
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	}
	
	public void pesquisarDadosDocumento(HttpServletRequest request, HttpSession sessao, FiltrarDadosCadastraisImoveisInconsistentesActionForm form) {
		
		if ( !Util.validacaoCPF(form.getNumeroDocumento()) && !Util.validacaoCNPJ(form.getNumeroDocumento()) ) {
			throw new ActionServletException("atencao.documento_invalido");
		} else {
		
			Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
					pesquisarDadosImovelAtualizacaoCadastralHelper(form.getIdParametroTabelaAtualizacaoCadastral(), form.getDataRecebimento(), form.getIdImovel(),
							form.getCodigoCliente(), form.getNumeroDocumento(), null,form.getIdLocalidade(), form.getIdSetorComercial(), 
							form.getIdQuadraInicial(), form.getIdQuadraFinal(), form.getIdCadastrador(), form.getPeriodoMovimentoInicial(), form.getPeriodoMovimentoFinal(), 
							form.getIndicadorSituacaoMovimento(), form.getIdTipoInconsistencia(), form.getIdEmpresa());
			
			if ( colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty() ) {
				request.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
			} else {
				throw new ActionServletException("atencao.nao_existe_dados_movimento_documento");
			}
		}
		
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
	}

    public String recuperaId(String idData) {
        String id = "";

        char[] chars = idData.toCharArray();


        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '|') {
                id = id + chars[i];
            } else {
               break;
            }
        }

        return id;
    }
    
    public String recuperaData(String idData) {
        String data = "";

        char[] chars = idData.toCharArray();
        
        boolean aux = false;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '|') {
            	
               if ( aux ) {
            	data = data + chars[i];
               }
            } else {
               aux = true;
            }
        }

        return data;
    }
	
	
	private void pesquisarCadastrador(String idEmpresa, HttpServletRequest request) {
		
		Collection<Usuario> colecaoCadastrador = this.getFachada().pesquisarUsuarioAtuCadastral(new Integer (idEmpresa));
		
		if ( colecaoCadastrador != null && !colecaoCadastrador.isEmpty() ) {
			request.setAttribute("colecaoCadastrador", colecaoCadastrador);
		}
	}
	
	private void pesquisarMensagemAtualizacaoCadastral(HttpServletRequest request) {
		
		Collection<Integer> colecaoIdsMensagens = new ArrayList<Integer>();
		colecaoIdsMensagens.add(9);
		colecaoIdsMensagens.add(10);
		colecaoIdsMensagens.add(23);
		FiltroMensagemAtualizacaoCadastral filtroMensagemAtualizacaoCadastral = new FiltroMensagemAtualizacaoCadastral();
		filtroMensagemAtualizacaoCadastral.adicionarParametro( new ParametroSimplesNotIn(FiltroMensagemAtualizacaoCadastral.ID, colecaoIdsMensagens));
		filtroMensagemAtualizacaoCadastral.setCampoOrderBy(FiltroMensagemAtualizacaoCadastral.MENSAGEM);
		Collection<MensagemAtualizacaoCadastral> colecaoMensagemAtualizacaoCadastral = Fachada.getInstancia().pesquisar(filtroMensagemAtualizacaoCadastral, MensagemAtualizacaoCadastral.class.getName());
		
		if ( colecaoMensagemAtualizacaoCadastral != null && !colecaoMensagemAtualizacaoCadastral.isEmpty() ) {
			request.setAttribute("colecaoMensagem", colecaoMensagemAtualizacaoCadastral);
		} 
	}
	
	private void pesquisarEmpresa(HttpServletRequest request) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
		
		Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
	
		if ( colecaoEmpresa != null && !colecaoEmpresa.isEmpty() ) {
			request.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,"Empresa");
		}
	}
	
	private void pesquisarLocalidade(FiltrarDadosCadastraisImoveisInconsistentesActionForm form , HttpServletRequest request) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, Integer.valueOf(form.getIdLocalidade())));

		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());

		} else {
			form.setIdLocalidade(null);
			form.setDescricaoLocalidade("Localidade Inexistente");
			request.setAttribute("localidadeEncontrada",true);
		}
	}
	
	private void pesquisarSetorComercial(FiltrarDadosCadastraisImoveisInconsistentesActionForm form , HttpServletRequest request) {


		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, Integer.valueOf(form.getIdSetorComercial())));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, Integer.valueOf(form.getIdLocalidade())));

		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

			form.setIdSetorComercial("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercial(setorComercial.getDescricao());

		} else {

			form.setIdSetorComercial(null);
			form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			request.setAttribute("setorEncontrado", true);

		}
	}
	
	private String pesquisarQuadra(FiltrarDadosCadastraisImoveisInconsistentesActionForm form , HttpServletRequest request) {

		String msgQuadra = "";
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraInicial())));
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
			
			// QUADRA ENCONTRADA
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadras);

			form.setIdQuadraInicial(quadra.getNumeroQuadra() + "");
			form.setIdQuadraFinal(quadra.getNumeroQuadra() + "");
			
			if(quadra.getBairro() == null){
				Bairro bairro = new Bairro();
				bairro.setNome("Quadra " + quadra.getNumeroQuadra());
				quadra.setBairro(bairro);
			}
			
			form.setDescricaoQuadraInicial(quadra.getBairro().getNome());
			form.setDescricaoQuadraFinal(quadra.getBairro().getNome());
		
		} else {
			form.setIdQuadraInicial("");
			form.setIdQuadraFinal("");
			form.setDescricaoQuadraInicial("Quadra Inexistente");
			form.setDescricaoQuadraFinal("");
			request.setAttribute("quadraEncontrado", true);
		}
	
		return msgQuadra;
	}
	
	private String pesquisarQuadraFinal(FiltrarDadosCadastraisImoveisInconsistentesActionForm form , HttpServletRequest request) {

		
		String msgQuadra = "";
		//PESQUISANDO QUADRA...
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadraFinal())));
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		
		Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
			
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadras);
			
			if(quadra.getBairro() == null){
				Bairro bairro = new Bairro();
				bairro.setNome("Quadra " + quadra.getNumeroQuadra());
				quadra.setBairro(bairro);
			}

			if ( Integer.valueOf(quadra.getNumeroQuadra()) >= Integer.valueOf(form.getIdQuadraInicial()) ) {
				form.setIdQuadraFinal("" + quadra.getNumeroQuadra());
				form.setDescricaoQuadraFinal(quadra.getBairro().getNome());
			} else {
				throw new ActionServletException("atencao.quadra.final.maior.quadra.inical");
			}
		
		} else {
			form.setIdQuadraFinal("");
			form.setDescricaoQuadraFinal("Quadra Inexistente");
			request.setAttribute("quadraFinalEncontrado", true);
		}
	
		return msgQuadra;
	}


		
}