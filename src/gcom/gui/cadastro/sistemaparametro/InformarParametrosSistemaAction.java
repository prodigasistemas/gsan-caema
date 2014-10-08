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
package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.atendimentopublico.ResolucaoImagem;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 10/01/2007
 */
public class InformarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		Fachada fachada = Fachada.getInstancia();

		// 1a Aba
		this.montarSistemaParametro1Aba(form, sistemaParametro, fachada);

		// 2a aba
		this.montarSistemaParametro2Aba(form, sistemaParametro);

		// 3a aba
		this.montarSistemaParametro3Aba(form, sistemaParametro);

		// 4a Aba
		this.montarSistemaParametro4Aba(form, sistemaParametro);

		// 5a aba
		this.montarSistemaParametro5Aba(form, sistemaParametro,httpServletRequest);

		this.getFachada().informarParametrosSistema(sistemaParametro, usuario);

		montarPaginaSucesso(httpServletRequest,
				"Parâmetros do Sistema informados com sucesso.",
				"Informar outro Parâmetro do Sistema",
				"exibirInformarParametrosSistemaAction.do?menu=sim");

		return retorno;

	}

	/**
	 * Valida o Campo
	 * 
	 * @author Rafael Pinto
	 * @date 18/07/2008
	 */
	private boolean validaCampo(String campo) {
		boolean retorno = false;

		if (campo != null && !campo.equals("") && !campo.equals("-1")) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Monta os objetos da 1(Primeira) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 18/07/2008
	 */
	private void montarSistemaParametro1Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro, Fachada fachada) {

		// Nome do Estado
		if (validaCampo(form.getNomeEstado())) {
			sistemaParametro.setNomeEstado(form.getNomeEstado());
		} else {
			sistemaParametro.setNomeEstado(null);
		}

		// Nome da Empresa
		if (validaCampo(form.getNomeEmpresa())) {
			sistemaParametro.setNomeEmpresa(form.getNomeEmpresa());
		} else {
			sistemaParametro.setNomeEmpresa(null);
		}

		// Abreviatura da Empresa
		if (validaCampo(form.getAbreviaturaEmpresa())) {
			sistemaParametro.setNomeAbreviadoEmpresa(form
					.getAbreviaturaEmpresa());
		} else {
			sistemaParametro.setNomeAbreviadoEmpresa(null);
		}

		// CNPJ da Empresa
		if (validaCampo(form.getCnpj())) {
			sistemaParametro.setCnpjEmpresa(form.getCnpj());
		} else {
			sistemaParametro.setCnpjEmpresa(null);
		}

		// Inscricao Estadual
		if (validaCampo(form.getInscricaoEstadual())) {
			sistemaParametro.setInscricaoEstadual(form.getInscricaoEstadual());
		} else {
			sistemaParametro.setInscricaoEstadual(null);
		}

		// Inscricao Municipal
		if (validaCampo(form.getInscricaoMunicipal())) {
			sistemaParametro
					.setInscricaoMunicipal(form.getInscricaoMunicipal());
		} else {
			sistemaParametro.setInscricaoMunicipal(null);
		}

		// Número do Contrato
		if (validaCampo(form.getNumeroContrato())) {
			sistemaParametro.setNumeroContratoPrestacaoServico(form
					.getNumeroContrato());
		} else {
			sistemaParametro.setNumeroContratoPrestacaoServico(null);
		}
		
		//Unidade organizacional Tramite de grande consumidor
		if (validaCampo(form.getIdUnidadeDestinoGrandeConsumidor())) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			String idUnidadeOrganizacionalTramiteGrandeConsumidor = form.getIdUnidadeDestinoGrandeConsumidor();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacionalTramiteGrandeConsumidor));

			Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);	

			if (unidadeOrganizacional != null) {

				sistemaParametro
						.setUnidadeOrganizacionalTramiteIdGrandeConsumidor(unidadeOrganizacional);
			} else {
				throw new ActionServletException(
						"atencao.unidade.organizacional.perfil.grande.consumidor.inexistente");
			}
		} else {
			sistemaParametro.setUnidadeOrganizacionalTramiteIdGrandeConsumidor(null);
		}
	
		// Unidade Organizacional

		if (validaCampo(form.getUnidadeOrganizacionalPresidencia())) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			String idUnidade = form.getUnidadeOrganizacionalPresidencia();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			if (unidadeOrganizacional != null) {

				sistemaParametro
						.setUnidadeOrganizacionalIdPresidencia(unidadeOrganizacional);
			} else {
				throw new ActionServletException(
						"atencao.unidade.organizacional.presidente.inexistente");
			}
		} else {
			sistemaParametro.setUnidadeOrganizacionalIdPresidencia(null);
		}
		
		// Presidente
		if (validaCampo(form.getPresidente())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getPresidente()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);
			if (cliente != null) {

				sistemaParametro.setClientePresidenteCompesa(cliente);
			} else {
				throw new ActionServletException(
						"atencao.presidente.inexistente");
			}
		} else {
			sistemaParametro.setClientePresidenteCompesa(null);
		}

		// Diretor Comercial
		if (validaCampo(form.getDiretorComercial())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getDiretorComercial()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);
			if (cliente != null) {

				sistemaParametro.setClienteDiretorComercialCompesa(cliente);
			} else {
				throw new ActionServletException(
						"atencao.diretoria.comercial.inexistente");
			}
		} else {
			sistemaParametro.setClienteDiretorComercialCompesa(null);
		}

		// Diretor de Gestão
		if (validaCampo(form.getDiretorGestao())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getDiretorGestao()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);
			if (cliente != null) {
				sistemaParametro.setClienteDiretorGestao(cliente);
			} else {
				throw new ActionServletException(
						"atencao.diretoria.gestao.inexistente");
			}
		} else {
			sistemaParametro.setClienteDiretorGestao(null);
		}

		// Logradouro Bairro
		if (validaCampo(form.getLogradouroBairro())) {

			LogradouroBairro logradouroBairro = new LogradouroBairro();
			logradouroBairro.setId(new Integer(form.getLogradouroBairro()));

			sistemaParametro.setLogradouroBairro(logradouroBairro);
		} else {
			sistemaParametro.setLogradouroBairro(null);
		}

		// Logradouro Cep
		if (validaCampo(form.getLogradouroCep())) {

			LogradouroCep logradouroCep = new LogradouroCep();
			logradouroCep.setId(new Integer(form.getLogradouroCep()));

			sistemaParametro.setLogradouroCep(logradouroCep);
		} else {
			sistemaParametro.setLogradouroCep(null);
		}
		
		// Endereco Referencia
		if (validaCampo(form.getEnderecoReferencia())) {

			EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
			enderecoReferencia.setId(new Integer(form.getEnderecoReferencia()));

			sistemaParametro.setEnderecoReferencia(enderecoReferencia);
		} else {
			sistemaParametro.setEnderecoReferencia(null);
		}

		// Numero
		if (validaCampo(form.getNumero())) {
			sistemaParametro.setNumeroImovel(form.getNumero());
		} else {
			sistemaParametro.setNumeroImovel(null);
		}

		// Complemento
		if (validaCampo(form.getComplemento())) {
			sistemaParametro.setComplementoEndereco(form.getComplemento());
		} else {
			sistemaParametro.setComplementoEndereco(null);
		}

		// Numero Telefone
		if (validaCampo(form.getNumeroTelefone())) {
			sistemaParametro.setNumeroTelefone(form.getNumeroTelefone());
		} else {
			sistemaParametro.setNumeroTelefone(null);
		}

		// Quantidade de Dígitos da Quadra
		if (validaCampo(form.getQuantidadeDigitosQuadra())) {
			if (form.getQuantidadeDigitosQuadra().equals("3")
					|| form.getQuantidadeDigitosQuadra().equals("4")) {
				sistemaParametro.setNumeroDigitosQuadra(new Integer(form
						.getQuantidadeDigitosQuadra()).shortValue());
			} else {
				throw new ActionServletException(
						"atencao.campo_com_quantidade_digitos_invalida");
			}
		}

		// Indicador não medido por tarifa de consumo
		if (validaCampo(form.getIndicadorQuadraFace())) {
			sistemaParametro.setIndicadorQuadraFace(new Short(form
					.getIndicadorQuadraFace()));
		} else {
			sistemaParametro.setIndicadorQuadraFace(null);
		}

		// Ramal
		if (validaCampo(form.getRamal())) {
			sistemaParametro.setNumeroRamal(form.getRamal());
		} else {
			sistemaParametro.setNumeroRamal(null);
		}

		// Fax
		if (validaCampo(form.getFax())) {
			sistemaParametro.setNumeroFax(form.getFax());
		} else {
			sistemaParametro.setNumeroFax(null);
		}

		// Site
		if (validaCampo(form.getSite())) {
			sistemaParametro.setNomeSiteEmpresa(form.getSite());
		} else {
			sistemaParametro.setNomeSiteEmpresa(null);
		}
		
		// Email
		if (validaCampo(form.getEmail())) {
			sistemaParametro.setDescricaoEmail(form.getEmail());
		} else {
			sistemaParametro.setDescricaoEmail(null);
		}
		
		// Domínio do e_mail corporativo
		if (validaCampo(form.getDominioEmailCorporativo())) {
			sistemaParametro.setDominioEmailCorporativo(form
					.getDominioEmailCorporativo());
		} else {
			sistemaParametro.setDominioEmailCorporativo(null);
		}

		// Numero de Atendimento
		if (validaCampo(form.getNumeroTelefoneAtendimento())) {
			sistemaParametro.setNumero0800Empresa(form
					.getNumeroTelefoneAtendimento());
		} else {
			sistemaParametro.setNumero0800Empresa(null);
		}

		// Titulo do Relatorio
		if (validaCampo(form.getTitulosRelatorio())) {
			sistemaParametro.setTituloPagina(form.getTitulosRelatorio());
		} else {
			sistemaParametro.setTituloPagina(null);
		}

		// Caminho Imagem da Logomarca
		if (validaCampo(form.getImagemLogomarca())) {
			sistemaParametro.setImagemLogomarca(form.getImagemLogomarca());
		} else {
			sistemaParametro.setImagemLogomarca(null);
		}

		// Caminho Imagem do Relatorio
		if (validaCampo(form.getImagemRelatorio())) {
			sistemaParametro.setImagemRelatorio(form.getImagemRelatorio());
		} else {
			sistemaParametro.setImagemRelatorio(null);
		}

		// Caminho Imagem da Conta
		if (validaCampo(form.getImagemConta())) {
			sistemaParametro.setImagemConta(form.getImagemConta());
		} else {
			sistemaParametro.setImagemConta(null);
		}

		// Numero execucao do resumo de negativacao
		if (validaCampo(form.getNumeroExecucaoResumoNegativacao())) {
			sistemaParametro.setNumeroExecucaoResumoNegativacao(new Integer(
					form.getNumeroExecucaoResumoNegativacao()));
		} else {
			sistemaParametro.setNumeroExecucaoResumoNegativacao(null);
		}

		// Indicador para controlar os autos de infracao
		if (validaCampo(form.getIndicadorControlaAutoInfracao())) {
			sistemaParametro.setIndicadorControlaAutoInfracao(new Short(form
					.getIndicadorControlaAutoInfracao()));
		}

		// Indicador Usa Rota
		if (validaCampo(form.getIndicadorUsaRota())) {
			sistemaParametro.setIndicadorUsaRota(new Short(form
					.getIndicadorUsaRota()));
		}

		// Indicador Duplicidade Cliente
		if (validaCampo(form.getIndicadorDuplicidadeCliente())) {
			sistemaParametro.setIndicadorDuplicidadeCliente(new Short(form
					.getIndicadorDuplicidadeCliente()));
		}

		// Consumo de ·gua fixado para anormalidade hidrÙmetro
		if (validaCampo(form.getConsumoAguaFixadoAnormalidadeHidro())) {
			sistemaParametro.setConsumoAguaFixadoAnormalidadeHidro(new Integer(
					form.getConsumoAguaFixadoAnormalidadeHidro()));
		} else {
			sistemaParametro.setConsumoAguaFixadoAnormalidadeHidro(null);
		}
		
		// Quantidade de meses da instalação e/ou substituição de hidrômetro
		if (validaCampo(form.getQtdMesIstalSubistituicaoHidro())) {
			sistemaParametro.setQtdMesIstalSubistituicaoHidro(new Integer(form
					.getQtdMesIstalSubistituicaoHidro()));
		} else {
			sistemaParametro.setQtdMesIstalSubistituicaoHidro(null);
		}
		
		// Quantidade de vezes da incidência de anormalidade de hidrômetro
		if (validaCampo(form.getQtdVezIncidenciaAnormalidadeHidro())) {
			sistemaParametro.setQtdVezIncidenciaAnormalidadeHidro(new Integer(
					form.getQtdVezIncidenciaAnormalidadeHidro()));
		} else {
			sistemaParametro.setQtdVezIncidenciaAnormalidadeHidro(null);
		}

		// Indicador Nome Menor Que Dez
		if (validaCampo(form.getIndicadorNomeMenorDez())) {
			sistemaParametro.setIndicadorNomeMenorDez(new Short(form
					.getIndicadorNomeMenorDez()));
		}

		// Indicador Nome Cliente Generico
		if (validaCampo(form.getIndicadorNomeClienteGenerico())) {
			sistemaParametro.setIndicadorNomeClienteGenerico(new Short(form
					.getIndicadorNomeClienteGenerico()));
		}

		if (validaCampo(form.getVersaoCelular())) {
			sistemaParametro.setVersaoCelular(form.getVersaoCelular());
		} else {
			sistemaParametro.setVersaoCelular(null);
		}

		// Indicador exibir mensagem
		if (validaCampo(form.getIndicadorExibirMensagem())) {
			sistemaParametro
					.setIndicadorExibeMensagemNaoReceberPagamento(new Short(
							form.getIndicadorExibirMensagem()));
		}

		// Cliente Responsavel Programa Especial

		if (validaCampo(form.getIdClienteResponsavelProgramaEspecial())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form
							.getIdClienteResponsavelProgramaEspecial()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);
			if (cliente != null) {
				sistemaParametro.setClienteResponsavelProgramaEspecial(cliente);
			} else {
				throw new ActionServletException(
						"atencao.cliente.programa.inexistente");
			}
		} else {
			sistemaParametro.setClienteResponsavelProgramaEspecial(null);
		}
		
		// Perfil Programa Especial
		if (validaCampo(form.getPerfilProgramaEspecial())) {
			ImovelPerfil perfilPrograma = new ImovelPerfil();
			perfilPrograma.setId(new Integer(form.getPerfilProgramaEspecial()));
			sistemaParametro.setPerfilProgramaEspecial(perfilPrograma);
		} else {
			sistemaParametro.setPerfilProgramaEspecial(null);
		}

		// Percentual de Convergencia Repavimentacao
		if (validaCampo(form.getPercentualConvergenciaRepavimentacao())) {

			BigDecimal percentual = new BigDecimal(0);

			String valorAux = form.getPercentualConvergenciaRepavimentacao()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentual = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualConvergenciaRepavimentacao(percentual);
		} else {
			sistemaParametro.setPercentualConvergenciaRepavimentacao(null);
		}

		// Indicador Documento Obrigatorio
		if (validaCampo(form.getIndicadorDocumentoObrigatorio())) {
			sistemaParametro.setIndicadorDocumentoObrigatorio(new Short(form
					.getIndicadorDocumentoObrigatorio()));
		}

		// Indicador de verificação do CPF e CPJ no CDL
		if (validaCampo(form.getIndicadorCpfCnpj())) {
			sistemaParametro.setIndicadorConsultaDocumentoReceita(new Short(
					form.getIndicadorCpfCnpj()));
		}

		// Indicador de Exibição Automática do Popup de Atualização Cadastral
		if (validaCampo(form.getIndicadorPopupAtualizacaoCadastral())) {
			sistemaParametro.setIndicadorPopupAtualizacaoCadastral(new Short(
					form.getIndicadorPopupAtualizacaoCadastral()));
		}

		// Valor para Emissão de Extrato no Formato Ficha de Compensação
		if (validaCampo(form.getValorExtratoFichaComp())) {

			BigDecimal valorExtratoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorExtratoFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorExtratoFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorExtratoFichaComp(valorExtratoFichaComp);
		} else {
			sistemaParametro.setValorExtratoFichaComp(null);
		}

		if (validaCampo(form.getNumeroDiasBloqueioCelular())) {
			sistemaParametro.setNumeroDiasBloqueioCelular(new Integer(form
					.getNumeroDiasBloqueioCelular()));
		} else {
			sistemaParametro.setNumeroDiasBloqueioCelular(null);
		}

		// Valor para Emissão de Extrato no Formato Ficha de Compensação
		if (form.getValorExtratoFichaComp() != null
				&& !form.getValorExtratoFichaComp().equals("")) {

			BigDecimal valorExtratoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorExtratoFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorExtratoFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorExtratoFichaComp(valorExtratoFichaComp);
		} else {
			sistemaParametro.setValorExtratoFichaComp(null);
		}

		// Valor para Emissão de Guia de Pagamento no Formato Ficha de
		// Compensação
		if (form.getValorGuiaFichaComp() != null
				&& !form.getValorGuiaFichaComp().equals("")) {

			BigDecimal valorGuiaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorGuiaFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorGuiaFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorGuiaFichaComp(valorGuiaFichaComp);
		} else {
			sistemaParametro.setValorGuiaFichaComp(null);
		}

		// Valor para Emissão de Demonstrativo de Parcelamento no Formato Ficha
		// de Compensação
		if (form.getValorDemonstrativoParcelamentoFichaComp() != null
				&& !form.getValorDemonstrativoParcelamentoFichaComp()
						.equals("")) {

			BigDecimal valorDemonstrativoParcelamentoFichaComp = new BigDecimal(
					0);

			String valorAux = form.getValorDemonstrativoParcelamentoFichaComp()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorDemonstrativoParcelamentoFichaComp = new BigDecimal(valorAux);
			sistemaParametro
					.setValorDemonstrativoParcelamentoFichaComp(valorDemonstrativoParcelamentoFichaComp);
		} else {
			sistemaParametro.setValorDemonstrativoParcelamentoFichaComp(null);
		}

		// Indicador de Exibição Automática do Popup de Atualização Cadastral
		if (validaCampo(form.getIndicadorUsoNMCliReceitaFantasia())) {
			sistemaParametro.setIndicadorUsoNMCliReceitaFantasia(new Short(form
					.getIndicadorUsoNMCliReceitaFantasia()));
		}
		
		if (form.getFaturamentoInicioContratoPPP() != null
				&& !form.getFaturamentoInicioContratoPPP().equals("")) {

			String mes = form.getFaturamentoInicioContratoPPP().substring(0, 2);
			String ano = form.getFaturamentoInicioContratoPPP().substring(3, 7);

			Integer faturamentoInicioContratoPPP = new Integer(ano + mes);

			sistemaParametro.setFaturamentoInicioContratoPPP(faturamentoInicioContratoPPP);
			
		} else {
			sistemaParametro.setFaturamentoInicioContratoPPP(null);
		}
		
		if (form.getPercentualPPP() != null
				&& !form.getPercentualPPP().equals("")) {
			
			BigDecimal percentualPPPBigDecimal = Util.formatarMoedaRealparaBigDecimal(form.getPercentualPPP().toString());
			sistemaParametro.setPercentualPPP(percentualPPPBigDecimal);
		} else {
			sistemaParametro.setPercentualPPP(null);
		}
		
		if (form.getQuantidadeDiasFaturarFactivel() != null
				&& !form.getQuantidadeDiasFaturarFactivel().equals("")) {
			sistemaParametro.setQuantidadeDiasFaturarFactivel(new Integer(form.getQuantidadeDiasFaturarFactivel()));
		} else {
			sistemaParametro.setQuantidadeDiasFaturarFactivel(null);
		}
		
		// Indicador Variar Hierarquia de Unidade Organizacional
		if (validaCampo(form.getIndicadorVariaHierarquiaUnidade())) {
			sistemaParametro.setIndicadorVariaHierarquiaUnidade(new Short(form
					.getIndicadorVariaHierarquiaUnidade()));
		}
		// Cliente Ficiticio Associar Pagamentos Não Identificados
		if (validaCampo(form
				.getClienteFicticioAssociarPagamentosNaoIdentificados())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form
					.getClienteFicticioAssociarPagamentosNaoIdentificados()));

			sistemaParametro
					.setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(cliente);
		} else {
			sistemaParametro.setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(null);
		}

		// Cliente Usuário Desconhecido
		if (validaCampo(form.getClienteUsuarioDesconhecido())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getClienteUsuarioDesconhecido()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);
			if (cliente != null) {
				sistemaParametro.setClienteUsuarioDesconhecido(cliente);
			} else {
				throw new ActionServletException(
						"atencao.cliente.usuario.inexistente");
			}
		} else {
			sistemaParametro.setClienteUsuarioDesconhecido(null);
		}

		// Indicador de bloqueio funcionalidade inserir/atualizar usuário:
		if (validaCampo(form.getIndicadorBloquearFunUsuario())) {
			sistemaParametro.setIndicadorBloquearFunUsuario(new Short(form
					.getIndicadorBloquearFunUsuario()));
		}
		
		//RM8015 - alterado por Ana Maria - 23/10/2012 
		// Data Limite para Cadastramento no Sorteio
		if (validaCampo(form.getDataLimiteCadastroSorteio())) {
			String formato = "dd/MM/yyyy";
			boolean dataValida = Util.validarDataInvalida(form
					.getDataLimiteCadastroSorteio(), formato);

			if (dataValida == false) {
				throw new ActionServletException(
						"atencao.data.invalida");
			}

			sistemaParametro.setDataLimiteCadastroSorteio(Util.converteStringParaDate(form.getDataLimiteCadastroSorteio()));
		}else{
			sistemaParametro.setDataLimiteCadastroSorteio(null);
		}
		
		// Data Sorteio
		if (validaCampo(form.getDataSorteio())) {
			String formato = "dd/MM/yyyy";
			boolean dataValida = Util.validarDataInvalida(form
					.getDataSorteio(), formato);

			if (dataValida == false) {
				throw new ActionServletException(
						"atencao.data.invalida");
			}

			sistemaParametro.setDataSorteio(Util.converteStringParaDate(form.getDataSorteio()));
		}else{
			sistemaParametro.setDataSorteio(null);
		}
		
		// Imagem ARPE
		if (validaCampo(form.getImagemArpe())) {
			sistemaParametro.setImagemArpe(form.getImagemArpe());
		} else {
			sistemaParametro.setImagemArpe(null);
		}
		
		// Imagem Rodapé Conta
		if (validaCampo(form.getImagemRodapeConta())) {
			sistemaParametro.setImagemRodapeConta(form.getImagemRodapeConta());
		} else {
			sistemaParametro.setImagemRodapeConta(null);
		}
		
		// URL 2 Via Conta
		if (validaCampo(form.getUrl2ViaConta())) {
			sistemaParametro.setUrl2ViaConta(form.getUrl2ViaConta());
		} else {
			sistemaParametro.setUrl2ViaConta(null);
		}
		
		// Indicador Documento Obrigatório Manter Cliente
		if (validaCampo(form.getIndicadorDocumentoObrigatorioManterCliente())) {
			sistemaParametro.setIndicadorDocumentoObrigatorioManterCliente(new Short(form.getIndicadorDocumentoObrigatorioManterCliente()));
		} else {
			sistemaParametro.setIndicadorDocumentoObrigatorioManterCliente(null);
		}
		
		if(form.getTamanhoMaximoAnexoRA() != null){
			sistemaParametro.setTamanhoMaximoAnexoRA(Integer.parseInt(form.getTamanhoMaximoAnexoRA()));
		}
		
		if(form.getResolucaoImagem() != null){
			
			Filtro filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples("id",form.getResolucaoImagem()));
			ResolucaoImagem imagem = (ResolucaoImagem)(Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, ResolucaoImagem.class.getName())));
			
			sistemaParametro.setResolucaoImagem(imagem);
			sistemaParametro.setImagemResolucaoLargura(imagem.getLarguraImagem());
			sistemaParametro.setImagemResolucaoAltura(imagem.getAlturaImagem());
		}
		
	}

	/**
	 * Monta os objetos da 2(Segunda) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro2Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// Mês/Ano Referencia
		if (validaCampo(form.getMesAnoReferencia())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferencia());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMesReferenciaFaturamento = new Integer(ano + mes);

			sistemaParametro.setAnoMesFaturamento(anoMesReferenciaFaturamento);
		}

		if (validaCampo(form.getQtdeContasRetificadas())) {
			sistemaParametro.setQtdMaxContasRetificadas(Integer.parseInt(form
					.getQtdeContasRetificadas()));
		} else {
			throw new ActionServletException(
					"atencao.campo_com_quantidade_maxima_contas_retificada_invalido");
		}

		// Menor Consumo para ser Grande Usuario
		if (validaCampo(form.getMenorConsumo())) {
			sistemaParametro.setMenorConsumoGrandeUsuario(new Integer(form
					.getMenorConsumo()));
		} else {
			sistemaParametro.setMenorConsumoGrandeUsuario(null);
		}

		// Menor Valor para Emissao de Contas
		if (validaCampo(form.getMenorValor())) {

			BigDecimal valorMinimoEmissaoConta = new BigDecimal(0);

			String valorAux = form.getMenorValor().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorMinimoEmissaoConta = new BigDecimal(valorAux);
			sistemaParametro
					.setValorMinimoEmissaoConta(valorMinimoEmissaoConta);
		} else {
			sistemaParametro.setValorMinimoEmissaoConta(null);
		}

		// Valor para Emissão de Conta no Formato Ficha de Compensação
		if (validaCampo(form.getValorContaFichaComp())) {

			BigDecimal valorContaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorContaFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorContaFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorContaFichaComp(valorContaFichaComp);
		} else {
			sistemaParametro.setValorContaFichaComp(null);
		}

		// Qtde de Economias para Ser Grande Usuario
		if (validaCampo(form.getQtdeEconomias())) {
			sistemaParametro.setMenorEconomiasGrandeUsuario(new Short(form
					.getQtdeEconomias()));
		} else {
			sistemaParametro.setMenorEconomiasGrandeUsuario(null);
		}

		// Meses para Calculo de Media de Consumo
		if (validaCampo(form.getMesesCalculoMedio())) {
			sistemaParametro.setMesesMediaConsumo(new Short(form
					.getMesesCalculoMedio()));
		}

		// Dias Minimo para Calcular Vencimento
		if (validaCampo(form.getDiasMinimoVencimento())) {
			sistemaParametro.setNumeroMinimoDiasEmissaoVencimento(new Short(
					form.getDiasMinimoVencimento()));
		} else {
			sistemaParametro.setNumeroMinimoDiasEmissaoVencimento(null);
		}

		// Dias Minimo para Caluar Vencimento se entrega para os correios
		if (validaCampo(form.getDiasMinimoVencimentoCorreio())) {
			sistemaParametro.setNumeroDiasAdicionaisCorreios(new Short(form
					.getDiasMinimoVencimentoCorreio()));
		} else {
			sistemaParametro.setNumeroDiasAdicionaisCorreios(null);
		}

		// Número máximo de meses para retroagir o calculo da media
		if (validaCampo(form.getNumeroMesesMaximoCalculoMedia())) {
			sistemaParametro.setNumeroMesesMaximoCalculoMedia(new Short(form
					.getNumeroMesesMaximoCalculoMedia()));
		}

		if (validaCampo(form.getDiasVencimentoAlternativo())) {
			Matcher validarCaracteresDiferenteVirgulaNumero = Pattern.compile(
					"[^;0-9]").matcher(form.getDiasVencimentoAlternativo());
			if (validarCaracteresDiferenteVirgulaNumero.find()) {
				throw new ActionServletException(
						"atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_virgula_sem_espaco_branco");
			}
			

			Matcher validarMaisDeUmaVirgulaJunta = Pattern.compile(";;+")
					.matcher(form.getDiasVencimentoAlternativo());

			if (validarMaisDeUmaVirgulaJunta.find()) {
				throw new ActionServletException(
						"atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_apenas_uma_virgula");
			}

			String[] diasString = form.getDiasVencimentoAlternativo()
					.split(";");

			ArrayList<Integer> diasJaValidados = new ArrayList<Integer>();

			if (!Util.isVazioOrNulo(diasString)) {
				for (String diaAtualString : diasString) {
					if (!validaCampo(diaAtualString)) {
						throw new ActionServletException(
								"atencao.gsan.campo_formato_invalido",
								"Dias para vencimento alternativo");
					}

					Integer diaAtual = new Integer(diaAtualString.trim());

					if (diaAtual < 1 || diaAtual > 31) {
						throw new ActionServletException(
								"atencao.informar_sistema_parametro.dia_vencimento_alternativo_entre_um_trinta_um");
					}

					if (diasJaValidados.contains(diaAtual)) {
						throw new ActionServletException(
								"atencao.informar_sistema_parametro.dia_vencimento_alternativo_duplicado");
					}

					diasJaValidados.add(diaAtual);

					for (Integer diaValidado : diasJaValidados) {
						if (diaValidado > diaAtual) {
							throw new ActionServletException(
									"atencao.informar_sistema_parametro.dia_vencimento_alternativo_desordenado");
						}
					}
				}
			}

			sistemaParametro.setDiasVencimentoAlternativo(form
					.getDiasVencimentoAlternativo());
		} else {
			sistemaParametro.setDiasVencimentoAlternativo(null);
		}

		// Numero de meses para validade de conta
		if (validaCampo(form.getNumeroMesesValidadeConta())) {
			sistemaParametro.setNumeroMesesValidadeConta(new Short(form
					.getNumeroMesesValidadeConta()));
		} else {
			sistemaParametro.setNumeroMesesValidadeConta(null);
		}

		// Numero de meses para alteracao do vencimento para outro
		if (validaCampo(form.getNumeroMesesAlteracaoVencimento())) {
			sistemaParametro.setNumeroMesesMinimoAlteracaoVencimento(new Short(
					form.getNumeroMesesAlteracaoVencimento()));
		} else {
			sistemaParametro.setNumeroMesesMinimoAlteracaoVencimento(null);
		}
		
		// Indicador Roteiro Empresa
		if (validaCampo(form.getIndicadorRoteiroEmpresa())) {
			sistemaParametro.setIndicadorRoteiroEmpresa(new Short(form.getIndicadorRoteiroEmpresa()));
		}

		// Indicador Alteracao do Vencimento mais de uma vez
		if (validaCampo(form.getIndicadorLimiteAlteracaoVencimento())) {
			sistemaParametro.setIndicadorLimiteAlteracaoVencimento(new Short(
					form.getIndicadorLimiteAlteracaoVencimento()));
		}

		// Indicador Calculo feito pelo sistema
		if (validaCampo(form.getIndicadorCalculaVencimento())) {
			sistemaParametro.setIndicadorCalculaVencimento(new Short(form
					.getIndicadorCalculaVencimento()));
		}

		// Indicador tipo de tarifa de consumo
		if (validaCampo(form.getIndicadorTarifaCategoria())) {
			sistemaParametro.setIndicadorTarifaCategoria(new Short(form
					.getIndicadorTarifaCategoria()));
		}

		// Indicador Para Retificar com um valor Menor
		if (validaCampo(form.getIndicadorRetificacaoValorMenor())) {
			sistemaParametro.setIndicadorRetificacaoValorMenor(new Short(form
					.getIndicadorRetificacaoValorMenor()));
		}

		// Indicador Transferência com débito
		if (validaCampo(form.getIndicadorTransferenciaComDebito())) {
			sistemaParametro.setIndicadorTransferenciaComDebito(new Short(form
					.getIndicadorTransferenciaComDebito()));
		}

		// Indicador não medido por tarifa de consumo
		if (validaCampo(form.getIndicadorNaoMedidoTarifa())) {
			sistemaParametro.setIndicadorNaoMedidoTarifa(new Short(form
					.getIndicadorNaoMedidoTarifa()));
		}

		// Indicador de Atualização Tarifária
		if (validaCampo(form.getIndicadorAtualizacaoTarifaria())) {
			sistemaParametro.setIndicadorAtualizacaoTarifaria(new Short(form
					.getIndicadorAtualizacaoTarifaria()));
		}

		// Mês/Ano Atualização Tarifária
		if (validaCampo(form.getMesAnoAtualizacaoTarifaria())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoAtualizacaoTarifaria());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMes = new Integer(ano + mes);

			sistemaParametro.setAnoMesAtualizacaoTarifaria(anoMes);
		} else {
			sistemaParametro.setAnoMesAtualizacaoTarifaria(null);
		}

		// Indicador de Faturamento Antecipado
		if (validaCampo(form.getIndicadorFaturamentoAntecipado())) {
			sistemaParametro.setIndicadorFaturamentoAntecipado(new Short(form
					.getIndicadorFaturamentoAntecipado()));
		}

		// Numero de dias de Variação de Consumo
		if (validaCampo(form.getNumeroDiasVariacaoConsumo())) {
			sistemaParametro.setNumeroDiasVariacaoConsumo(new Short(form
					.getNumeroDiasVariacaoConsumo()));
		} else {
			sistemaParametro.setNumeroDiasVariacaoConsumo(null);
		}
		
		// Motivo de Retificacao Conta Pagamento Antecipado
		if (validaCampo(form.getMotivoRetificacaoContaPagtoAntecipado())) {
			ContaMotivoRetificacao contaMotivoRetificao = new ContaMotivoRetificacao();
			contaMotivoRetificao.setId(new Integer(form.getMotivoRetificacaoContaPagtoAntecipado()));
			
			sistemaParametro.setContaMotivoRetificacaoPagamentoAntecipado(contaMotivoRetificao);
		} else {
			sistemaParametro.setContaMotivoRetificacaoPagamentoAntecipado(null);
		}
		
		// Numero limite de alteracoes do vencimento
		if (validaCampo(form.getNumeroLimiteAlteracoesVencimento())) {
			sistemaParametro.setNumeroLimiteAlteracaoVencimento(new Integer(form
					.getNumeroLimiteAlteracoesVencimento()));
		} else {
			sistemaParametro.setNumeroLimiteAlteracaoVencimento(null);
		}

		// Salario Minimo
		if (validaCampo(form.getSalarioMinimo())) {

			BigDecimal valorValorSalarioMinimo = new BigDecimal(0);

			String valorAux = form.getSalarioMinimo().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");
			valorValorSalarioMinimo = new BigDecimal(valorAux);

			sistemaParametro.setValorSalarioMinimo(valorValorSalarioMinimo);
		} else {
			sistemaParametro.setValorSalarioMinimo(null);
		}

		// Area Maxima do Imovel tarifa social
		if (validaCampo(form.getAreaMaxima())) {
			sistemaParametro.setAreaMaximaTarifaSocial(new Integer(form
					.getAreaMaxima()));
		} else {
			sistemaParametro.setAreaMaximaTarifaSocial(null);
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getConsumoMaximo())) {
			sistemaParametro.setConsumoEnergiaMaximoTarifaSocial(new Integer(
					form.getConsumoMaximo()));
		} else {
			sistemaParametro.setConsumoEnergiaMaximoTarifaSocial(null);
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getNumeroMesesCalculoCorrecao())) {
			sistemaParametro.setNumeroMesesCalculoCorrecao(new Short(form
					.getNumeroMesesCalculoCorrecao()));
		} else {
			sistemaParametro.setNumeroMesesCalculoCorrecao(null);
		}

		// Numero de vezes de suspensao de leitura
		if (validaCampo(form.getNumeroVezesSuspendeLeitura())) {
			sistemaParametro.setNumeroVezesSuspendeLeitura(new Integer(form
					.getNumeroVezesSuspendeLeitura()));
		} else {
			sistemaParametro.setNumeroVezesSuspendeLeitura(null);
		}

		// Numero de meses da leitura suspensa
		if (validaCampo(form.getNumeroMesesLeituraSuspensa())) {
			sistemaParametro.setNumeroMesesLeituraSuspensa(new Integer(form
					.getNumeroMesesLeituraSuspensa()));
		} else {
			sistemaParametro.setNumeroMesesLeituraSuspensa(null);
		}

		// Numero de meses de reinicio situacao especial do faturamento
		if (validaCampo(form.getNumeroMesesReinicioSitEspFatu())) {
			sistemaParametro
					.setNumeroMesesReinicioSitEspFaturamento(new Integer(form
							.getNumeroMesesReinicioSitEspFatu()));
		} else {
			sistemaParametro.setNumeroMesesReinicioSitEspFaturamento(null);
		}

		// Numero de dias de prazo para entrada de recurso do auto de infracao
		if (validaCampo(form.getNnDiasPrazoRecursoAutoInfracao())) {
			sistemaParametro.setNumeroDiasPrazoRecursoAutoInfracao(new Integer(
					form.getNnDiasPrazoRecursoAutoInfracao()));
		} else {
			sistemaParametro.setNumeroDiasPrazoRecursoAutoInfracao(null);
		}
		
		// Percentual de Bonus Social
		if (validaCampo(form.getPercentualBonusSocial())) {
			BigDecimal percentualBonusSocial = new BigDecimal(0);

			String valorAux = form.getPercentualBonusSocial().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualBonusSocial = new BigDecimal(valorAux);
			sistemaParametro.setPercentualBonusSocial(percentualBonusSocial);
		} else {
			sistemaParametro.setPercentualBonusSocial(null);
		}
		// Indicador de bloqueio de recalculo e reemissão de conta na impressão
		// simultânea
		if (validaCampo(form.getIndicadorBloqueioContaMobile())) {
			sistemaParametro.setIndicadorBloqueioContaMobile(new Short(form
					.getIndicadorBloqueioContaMobile()));
		}
		
		// Número de meses para retificar uma conta
		if (form.getNumeroMesesRetificarConta() != null && !form.getNumeroMesesRetificarConta().trim().equals("")) {
			sistemaParametro.setNumeroMesesRetificarConta(new Integer(form
					.getNumeroMesesRetificarConta()));
		} else {
			sistemaParametro.setNumeroMesesRetificarConta(null);
		}

		// Está na Norma de Retificação da Conta
		if (validaCampo(form.getIndicadorNormaRetificacao())) {
			sistemaParametro.setIndicadorNormaRetificacao(new Short(form
					.getIndicadorNormaRetificacao()));
		}

		// Nos casos dos poÁos medidos, o percentual de coleta deve incidir
		// apenas sobre o volume de poço?
		if (validaCampo(form.getIndicadorPercentualColetaEsgoto())) {
			sistemaParametro.setIndicadorPercentualColetaEsgoto(new Short(form
					.getIndicadorPercentualColetaEsgoto()));
		}

		// Indicador para desconsiderar o rateio de esgoto
		if (validaCampo(form.getIndicadorDesconsiderarRateioEsgoto())) {
			sistemaParametro.setIndicadorDesconsiderarRateioEsgoto(new Short(
					form.getIndicadorDesconsiderarRateioEsgoto()));
		}

		// Mensagem Pedido Conta BRAILE
		if (validaCampo(form.getMensagemContaBraile())) {

			sistemaParametro.setMensagemContaBraile(form
					.getMensagemContaBraile().trim());
		} else {
			sistemaParametro.setMensagemContaBraile(null);
		}

		if (validaCampo(form.getCodigoTipoCalculoNaoMedido())) {
			sistemaParametro.setCodigoTipoCalculoNaoMedido(new Integer(form
					.getCodigoTipoCalculoNaoMedido()));
		} else {
			sistemaParametro.setCodigoTipoCalculoNaoMedido(null);
		}
		
		if (validaCampo(form.getQtdEconomiaContratoDemanda())) {
			sistemaParametro.setQtdEconomiaContratoDemanda(new Integer(form
					.getQtdEconomiaContratoDemanda()));
		} else {
			sistemaParametro.setQtdEconomiaContratoDemanda(null);
		}
		
		// quantidade maxima de vezes para alterar o vinculo de uma conta.
		if (validaCampo(form.getNumeroVezesAlterarVinculo())) {
			sistemaParametro.setNumeroVezesAlterarVinculo(new Integer(form
					.getNumeroVezesAlterarVinculo()));
		}
		else{
			sistemaParametro.setNumeroVezesAlterarVinculo(null);
		}
		
		if (validaCampo(form.getQuantidadeMaximaContasItensEspecialFaturamento())) {
			sistemaParametro.setQuantidadeMaximaContasItensEspecialFaturamento(new Integer(form
					.getQuantidadeMaximaContasItensEspecialFaturamento()));
		} else {
			sistemaParametro.setQuantidadeMaximaContasItensEspecialFaturamento(null);
		}
		
		if(validaCampo(form.getQtdDiasDebitoVencidoParaSuspensaoGeracaoDeDoacoes())){
			sistemaParametro.setQtdDiasParaSuspensaoDoacao(new Integer(form
				.getQtdDiasDebitoVencidoParaSuspensaoGeracaoDeDoacoes()));
		}else{
			sistemaParametro.setQtdDiasParaSuspensaoDoacao(null);
		}
	}

	/**
	 * Monta os objetos da 3(Terceira) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro3Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// Mês e Ano de Referencia
		if (validaCampo(form.getMesAnoReferenciaArrecadacao())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferenciaArrecadacao());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferenciaArrecadacao().substring(0, 2);
			String ano = form.getMesAnoReferenciaArrecadacao().substring(3, 7);

			Integer anoMesReferenciaArrecadacao = new Integer(ano + mes);

			sistemaParametro.setAnoMesArrecadacao(anoMesReferenciaArrecadacao);
		}

		// Código da Empresa para FEBRABAN
		if (validaCampo(form.getCodigoEmpresaFebraban())) {
			sistemaParametro.setCodigoEmpresaFebraban(new Short(form
					.getCodigoEmpresaFebraban()));
		} else {
			sistemaParametro.setCodigoEmpresaFebraban(null);
		}

		// Número do Lay-out
		if (validaCampo(form.getNumeroLayOut())) {
			sistemaParametro.setNumeroLayoutFebraban(new Short(form
					.getNumeroLayOut()));
		} else {
			sistemaParametro.setNumeroLayoutFebraban(null);
		}

		// Identificador de Conta Bancaria
		if (validaCampo(form.getIndentificadorContaDevolucao())) {

			ContaBancaria contaBancaria = new ContaBancaria();

			contaBancaria.setId(new Integer(form
					.getIndentificadorContaDevolucao()));
			sistemaParametro.setContaBancaria(contaBancaria);
		} else {
			sistemaParametro.setContaBancaria(null);
		}

		// Percentual de Entrada Minima
		if (validaCampo(form.getPercentualEntradaMinima())) {

			BigDecimal percentualEntradaMinima = new BigDecimal(0);

			String valorAux = form.getPercentualEntradaMinima().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualEntradaMinima = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFinanciamentoEntradaMinima(percentualEntradaMinima);
		} else {
			sistemaParametro.setPercentualFinanciamentoEntradaMinima(null);
		}

		// Maximo de Parcelas
		if (validaCampo(form.getMaximoParcelas())) {
			sistemaParametro.setNumeroMaximoParcelasFinanciamento(new Short(
					form.getMaximoParcelas()));
		}

		// Percentual Maximo
		if (validaCampo(form.getPercentualMaximoAbatimento())) {

			BigDecimal percentualMaximoAbatimento = new BigDecimal(0);

			String valorAux = form.getPercentualMaximoAbatimento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualMaximoAbatimento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualMaximoAbatimento(percentualMaximoAbatimento);
		} else {
			sistemaParametro.setPercentualMaximoAbatimento(null);
		}

		// Percentual de Taxa
		if (validaCampo(form.getPercentualTaxaFinanciamento())) {

			BigDecimal percentualTaxaFinanciamento = new BigDecimal(0);

			String valorAux = form.getPercentualTaxaFinanciamento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualTaxaFinanciamento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualTaxaJurosFinanciamento(percentualTaxaFinanciamento);
		} else {
			sistemaParametro.setPercentualTaxaJurosFinanciamento(null);
		}

		// Numero Maximo de Parcelas
		if (validaCampo(form.getNumeroMaximoParcelaCredito())) {
			sistemaParametro.setNumeroMaximoParcelaCredito(new Short(form
					.getNumeroMaximoParcelaCredito()));
		} else {
			sistemaParametro.setNumeroMaximoParcelaCredito(null);
		}

		// Percentual da Média do índice
		if (validaCampo(form.getPercentualCalculoIndice())) {

			BigDecimal percentualCalculoIndice = new BigDecimal(0);

			String valorAux = form.getPercentualCalculoIndice().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualCalculoIndice = new BigDecimal(valorAux);
			sistemaParametro.setPercentualMediaIndice(percentualCalculoIndice);
		} else {
			sistemaParametro.setPercentualMediaIndice(null);
		}
		
		// Número do módulo do dígito verificador
		if (validaCampo(form.getNumeroModuloDigitoVerificador())) {

			sistemaParametro.setNumeroModuloDigitoVerificador(new Short(form
					.getNumeroModuloDigitoVerificador()));

			if (sistemaParametro.getNumeroModuloDigitoVerificador().compareTo(
					ConstantesSistema.MODULO_VERIFICADOR_10) == 0
					&& sistemaParametro.getNumeroModuloDigitoVerificador()
							.compareTo(ConstantesSistema.MODULO_VERIFICADOR_11) == 0) {
				throw new ActionServletException(
						"atencao.digito_verificador_invalido");
			}

		} else {
			sistemaParametro.setNumeroModuloDigitoVerificador(null);
		}
		
		// Número meses para pesquisa de imoveis com ramais suprimidos
		if (validaCampo(form.getNumeroMesesPesquisaImoveisRamaisSuprimidos())) {

			sistemaParametro
					.setNumeroMesesPesquisaImoveisRamaisSuprimidos(new Integer(
							form.getNumeroMesesPesquisaImoveisRamaisSuprimidos()));

		} else {
			sistemaParametro.setNumeroMesesPesquisaImoveisRamaisSuprimidos(null);
		}
		
		// Número anos para Geração da declaração quitação de debitos anual
		if (validaCampo(form.getNumeroAnoQuitacao())) {
			sistemaParametro.setNumeroAnoQuitacao(new Integer(form
					.getNumeroAnoQuitacao()));
		} else {
			sistemaParametro.setNumeroAnoQuitacao(null);
		}
		
		// Indicador de verificação de contas em cobrança judicial,
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getIndicadorCobrancaJudical())) {
			sistemaParametro.setIndicadorCobrancaJudical(new Short(form
					.getIndicadorCobrancaJudical()));
		} else {
			sistemaParametro.setIndicadorCobrancaJudical(null);
		}
		
		// Indicador de verificação de contas parceladas,
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getIndicadorContaParcelada())) {
			sistemaParametro.setIndicadorContaParcelada(new Short(form
					.getIndicadorContaParcelada()));
		} else {
			sistemaParametro.setIndicadorContaParcelada(null);
		}
		
		// Numero meses para calculo de meses
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getNumeroMesesAnterioresParaDeclaracaoQuitacao())) {
			sistemaParametro
					.setNumeroMesesAnterioresParaDeclaracaoQuitacao(new Integer(
							form.getNumeroMesesAnterioresParaDeclaracaoQuitacao()));
		} else {
			sistemaParametro.setNumeroMesesAnterioresParaDeclaracaoQuitacao(null);
		}
		
		// Indicador de verificação do valor do movimento arrecadador
		if (validaCampo(form.getIndicadorValorMovimentoArrecadador())) {
			sistemaParametro.setIndicadorValorMovimentoArrecadador(Integer
					.parseInt(form.getIndicadorValorMovimentoArrecadador()));
		}
		// Codigo de exibição do Relatório de Dados Diários da Arrecadação por
		// Gerência
		if (validaCampo(form.getCdDadosDiarios())) {
			sistemaParametro.setCdDadosDiarios(new Integer(form
					.getCdDadosDiarios()));
		} else {
			sistemaParametro.setCdDadosDiarios(null);
		}
		
		// Numero Convenio ficha de compensação
		if (validaCampo(form.getNumeroConvenioFichaCompensacao())) {
			try {
				sistemaParametro.setNumeroConvenioFichaCompensacao(new Integer(
						form.getNumeroConvenioFichaCompensacao()));
			} catch (java.lang.NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.numero_convenio_ficha_compensacao_invalido");
			}
		} else {
			sistemaParametro.setNumeroConvenioFichaCompensacao(null);
		}
		
	}

	/**
	 * Monta os objetos da 4(Quarta) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro4Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// Codigo da Menor Capacidade
		if (validaCampo(form.getCodigoMenorCapacidade())) {

			HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
			hidrometroCapacidade.setId(new Integer(form
					.getCodigoMenorCapacidade()));

			sistemaParametro.setHidrometroCapacidade(hidrometroCapacidade);
		} else {
			sistemaParametro.setHidrometroCapacidade(null);
		}

		// Indicador de Geração de Faixa Falsa
		if (validaCampo(form.getIndicadorGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorFaixaFalsa(new Short(form
					.getIndicadorGeracaoFaixaFalsa()));
		}

		// Indicador do Percentual para Geração
		if (validaCampo(form.getIndicadorPercentualGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorUsoFaixaFalsa(new Short(form
					.getIndicadorPercentualGeracaoFaixaFalsa()));
		}

		// Percentual de Geração de Faixa
		if (validaCampo(form.getPercentualGeracaoFaixaFalsa())) {

			BigDecimal percentualGeracaoFaixaFalsa = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFaixaFalsa().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFaixaFalsa = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFaixaFalsa(percentualGeracaoFaixaFalsa);
		} else {
			sistemaParametro.setPercentualFaixaFalsa(null);
		}

		// Indicador de Geração de Fiscalização
		if (validaCampo(form.getIndicadorGeracaoFiscalizacaoLeitura())) {
			sistemaParametro
					.setIndicadorPercentualFiscalizacaoLeitura(new Short(form
							.getIndicadorPercentualGeracaoFiscalizacaoLeitura()));
		}

		// Indicador do Percentual Geração
		if (validaCampo(form.getIndicadorPercentualGeracaoFiscalizacaoLeitura())) {
			sistemaParametro.setIndicadorUsoFiscalizadorLeitura(new Short(form
					.getIndicadorGeracaoFiscalizacaoLeitura()));
		}

		// Percentual de Tolerancia
		if (validaCampo(form.getPercentualToleranciaRateioConsumo())) {

			BigDecimal percentualToleranciaRateioConsumo = new BigDecimal(0);

			String valorAux = form.getPercentualToleranciaRateioConsumo()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualToleranciaRateioConsumo = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualToleranciaRateio(percentualToleranciaRateioConsumo);
		} else {
			sistemaParametro.setPercentualToleranciaRateio(null);
		}

		// Percentual de Geração de Fiscalização
		if (validaCampo(form.getPercentualGeracaoFiscalizacaoLeitura())) {

			BigDecimal percentualGeracaoFiscalizacaoLeitura = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFiscalizacaoLeitura()
					.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFiscalizacaoLeitura = new BigDecimal(valorAux);

			sistemaParametro
					.setPercentualFiscalizacaoLeitura(percentualGeracaoFiscalizacaoLeitura);

		} else {
			sistemaParametro.setPercentualFiscalizacaoLeitura(null);
		}

		// Incremento Máximo de Consumo
		if (validaCampo(form.getIncrementoMaximoConsumo())) {
			sistemaParametro.setIncrementoMaximoConsumoRateio(new Integer(form
					.getIncrementoMaximoConsumo()));
		} else {
			sistemaParametro.setIncrementoMaximoConsumoRateio(null);
		}

		// Decremento Máximo de Consumo
		if (validaCampo(form.getDecrementoMaximoConsumo())) {
			sistemaParametro.setDecrementoMaximoConsumoRateio(new Integer(form
					.getDecrementoMaximoConsumo()));
		} else {
			sistemaParametro.setDecrementoMaximoConsumoRateio(null);
		}

		// Numero de Dias entre o Vencimento
		if (validaCampo(form.getDiasVencimentoCobranca())) {
			sistemaParametro.setNumeroDiasVencimentoCobranca(new Short(form
					.getDiasVencimentoCobranca()));
		} else {
			sistemaParametro.setNumeroDiasVencimentoCobranca(null);
		}

		// Número Máximo de Meses de Sanções
		if (validaCampo(form.getNumeroMaximoMesesSancoes())) {
			sistemaParametro.setNumeroMaximoMesesSancoes(new Short(form
					.getNumeroMaximoMesesSancoes()));
		} else {
			sistemaParametro.setNumeroMaximoMesesSancoes(null);
		}

		// Valor da Segunda Via
		if (validaCampo(form.getValorSegundaVia())) {

			String valorAux = form.getValorSegundaVia().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			sistemaParametro.setValorSegundaVia(new BigDecimal(valorAux));
		} else {
			sistemaParametro.setValorSegundaVia(null);
		}

		// Indicador de Cobrança da Taxa de Extrato
		if (validaCampo(form.getIndicadorCobrarTaxaExtrato())) {
			sistemaParametro.setIndicadorCobrarTaxaExtrato(new Short(form
					.getIndicadorCobrarTaxaExtrato()));
		}

		// Código da Periodicidade da Negativacao
		if (validaCampo(form.getCodigoPeriodicidadeNegativacao())) {
			sistemaParametro.setCodigoPeriodicidadeNegativacao(new Short(form
					.getCodigoPeriodicidadeNegativacao()));
		} else {
			sistemaParametro.setCodigoPeriodicidadeNegativacao(null);
		}

		// Número de Dias para Calculo de Adicionais de Impontualidade
		if (validaCampo(form.getNumeroDiasCalculoAcrescimos())) {
			sistemaParametro.setNumeroDiasCalculoAcrescimos(new Short(form
					.getNumeroDiasCalculoAcrescimos()));
		}

		// Número de Dias de Validade do Extrato de Débito
		if (validaCampo(form.getNumeroDiasValidadeExtrato())) {
			sistemaParametro.setNumeroDiasValidadeExtrato(new Short(form
					.getNumeroDiasValidadeExtrato()));
		} else {
			sistemaParametro.setNumeroDiasValidadeExtrato(null);
		}

		// Número de Dias de Validade do Extrato de Débito para quem possui
		// Permissão Especial
		if (validaCampo(form.getNumeroDiasValidadeExtratoPermissaoEspecial())) {
			sistemaParametro
					.setNumeroDiasValidadeExtratoPermissaoEspecial(new Short(
							form.getNumeroDiasValidadeExtratoPermissaoEspecial()));
		} else {
			sistemaParametro
					.setNumeroDiasValidadeExtratoPermissaoEspecial(null);
		}

		// Indicador Parcelamento Confirmado
		if (validaCampo(form.getIndicadorParcelamentoConfirmado())) {
			sistemaParametro.setIndicadorParcelamentoConfirmado(new Short(form
					.getIndicadorParcelamentoConfirmado()));
		}

		// Número de dias úteis para que a OS de Fiscalização seja encerrada por
		// Decurso de Prazo
		if (validaCampo(form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo())) {
			sistemaParametro
					.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(new Short(
							form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo()));
		} else {
			sistemaParametro
					.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(null);
		}

		// Número dias para Encerrar OS
		if (validaCampo(form.getNumeroDiasEncerramentoOrdemServico())) {
			sistemaParametro.setNumeroDiasEncerramentoOrdemServico(new Short(
					form.getNumeroDiasEncerramentoOrdemServico()));
		} else {
			sistemaParametro.setNumeroDiasEncerramentoOrdemServico(null);
		}

		// Número dias para Encerrar OS Seletiva
		if (validaCampo(form.getNumeroDiasEncerramentoOSSeletiva())) {
			sistemaParametro.setNumeroDiasEncerramentoOSSeletiva(new Short(
					form.getNumeroDiasEncerramentoOSSeletiva()));
		} else {
			sistemaParametro.setNumeroDiasEncerramentoOSSeletiva(null);
		}
		
		//Quantidade de dias de prorrogação do vencimento na retificação
		if (validaCampo(form.getNumeroDiasAlteracaoVencimentoPosterior())) {
			sistemaParametro.setNumeroDiasAlteracaoVencimentoPosterior(new Short(
					form.getNumeroDiasAlteracaoVencimentoPosterior()));
		} else {
			sistemaParametro.setNumeroDiasAlteracaoVencimentoPosterior(null);
		}

		// Indicador Calculo Juros Parcelamento Tabela Price
		if (validaCampo(form.getIndicadorTabelaPrice())) {
			sistemaParametro.setIndicadorTabelaPrice(new Short(form
					.getIndicadorTabelaPrice()));
		}

		// Indicador Divida ativa
		if (validaCampo(form.getIndicadorControleDividaAtiva())) {
			sistemaParametro.setIndicadorDividaAtiva(new Short(form
					.getIndicadorControleDividaAtiva()));
		}

		// Número de Dias para o Vencimento da Guia de pagamento de Entrada de
		// Parcelamento
		if (validaCampo(form.getNumeroDiasVencimentoEntradaParcelamento())) {
			sistemaParametro
					.setNumeroDiasVencimentoEntradaParcelamento(new Short(form
							.getNumeroDiasVencimentoEntradaParcelamento()));
		} else {
			sistemaParametro.setNumeroDiasVencimentoEntradaParcelamento(null);
		}

		// Resolução de Diretoria para Cálculo de Descontos para pagamento à
		// vista
		if (validaCampo(form.getIdResolucaoDiretoria())) {
			ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
			resolucaoDiretoria
					.setId(new Integer(form.getIdResolucaoDiretoria()));
			sistemaParametro.setResolucaoDiretoria(resolucaoDiretoria);
		} else {
			sistemaParametro.setResolucaoDiretoria(null);
		}

		// Retirar Contas Vinculadas a Contrato de Parcelamento da Composição do
		// Débito do Imóvel ou do Cliente
		if (validaCampo(form.getIndicadorBloqueioContasContratoParcelDebitos())) {
			sistemaParametro
					.setIndicadorBloqueioContasContratoParcelDebitos(new Short(
							form.getIndicadorBloqueioContasContratoParcelDebitos()));
		}

		// Retirar Guias Vinculadas a Contrato de Parcelamento da Composição do
		// Débito do Imóvel ou do Cliente
		if (validaCampo(form
				.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito())) {
			sistemaParametro
					.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(new Short(
							form.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()));
		}

		// Bloquear Contas Vinculadas a Contrato de Parcelamento na tela de
		// Manter Conta
		if (validaCampo(form
				.getIndicadorBloqueioContasContratoParcelManterConta())) {
			sistemaParametro
					.setIndicadorBloqueioContasContratoParcelManterConta(new Short(
							form.getIndicadorBloqueioContasContratoParcelManterConta()));
		}
		/*
		 * Adicionado por: Raimundo Martins Data: 19/07/2011 Indicador de
		 * Bloqueio de Débitos a Cobrar Vinculados ao Contrato de Parcelamento
		 * na Composição do Débito do Imóvel ou Cliente Obter Débito.
		 */
		if (validaCampo(form
				.getIndicadorBloqueioDebitoACobrarContratoParcelDebito())) {
			sistemaParametro
					.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(new Short(
							form.getIndicadorBloqueioDebitoACobrarContratoParcelDebito()));
		}

		// Vinculadas a Contrato de Parcelamento na tela de Manter Guia
		if (validaCampo(form
				.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta())) {
			sistemaParametro
					.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(new Short(
							form.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()));
		}

		/*
		 * Adicionado por: Raimundo Martins Data: 19/07/2011 Indicador de
		 * Bloqueio de Débitos a Cobrar Vinculados ao Contrato de Parcelamento
		 * no Manter Débitos a Cobrar
		 */

		if (validaCampo(form
				.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito())) {
			sistemaParametro
					.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(new Short(
							form.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()));
		}

		// Número Máximo de Parcelas para os Contratos de Parcelamento por
		// Cliente
		if (validaCampo(form.getNumeroMaximoParcelasContratosParcelamento())) {
			sistemaParametro
					.setNumeroMaximoParcelasContratosParcelamento(new Integer(
							form.getNumeroMaximoParcelasContratosParcelamento()));
		} else {
			sistemaParametro.setNumeroMaximoParcelasContratosParcelamento(null);
		}

		// Número de dias de vencimento para envio das contas para as empresas
		// de cobrança:
		if (validaCampo(form.getNumeroDiasEnvioContaEmpresaCobranca())) {
			sistemaParametro
					.setNumeroDiasEnvioContaEmpresaCobranca(new Integer(form
							.getNumeroDiasEnvioContaEmpresaCobranca()));
		} else {
			sistemaParametro.setNumeroDiasEnvioContaEmpresaCobranca(null);
		}

		// Número de dias para retirada das contas das empresas de cobrança:
		if (validaCampo(form.getNumeroDiaRetiradaContaEmpresaCobraca())) {
			sistemaParametro
					.setNumeroDiaRetiradaContaEmpresaCobraca(new Integer(form
							.getNumeroDiaRetiradaContaEmpresaCobraca()));
		} else {
			sistemaParametro.setNumeroDiaRetiradaContaEmpresaCobraca(null);
		}
		// Indicador incluir conta fora do vencimento da cobrança:
		if (validaCampo(form.getIndicadorIncluirContasForaVenCobranca())) {
			sistemaParametro
					.setIndicadorIncluirContasForaVenCobranca(new Short(form
							.getIndicadorIncluirContasForaVenCobranca()));
		}

		if (validaCampo(form
				.getIndicadorCriticarConteudoRetornoMovimentoNegativacao())) {
			sistemaParametro
					.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(new Short(
							form.getIndicadorCriticarConteudoRetornoMovimentoNegativacao()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Criticar Conteúdo do Retorno Movimento Negativação Confirmado");
		}

		// Número de dias que permite inserir processo comando de cobrança:

		if (validaCampo(form.getNumeroDiasInserirProcessoCobranca())) {
			sistemaParametro
					.setNumeroDiasInserirProcessoCobranca(new Integer(form
							.getNumeroDiasInserirProcessoCobranca()));
		} else {
			sistemaParametro.setNumeroDiasInserirProcessoCobranca(null);
		}
		
		/*
		 * Adicionado por: Paulo Diniz Data: 13/10/2011 Solicitação da Compesa
		 * para que seja exibido no filtro da tela de selecionar contas para
		 * cobrança o filtro de pesquisa por valor total do débito ou por valor
		 * de conta.
		 */

		if (validaCampo(form.getIndicadorTotalDebito())) {
			sistemaParametro.setIndicadorTotalDebito(new Short(form
					.getIndicadorTotalDebito()));

		}

		/*
		 * Adicionado por: Diego Maciel Data: 10/11/2011 PE2011095860 - Retirar
		 * as contas com vencimento alterado da negativação.
		 */
		if (validaCampo(form.getIndicadorCanceNegatContaVencAlter())) {
			sistemaParametro.setIndicadorCanceNegatContaVencAlter(new Short(
					form.getIndicadorCanceNegatContaVencAlter()));
		}
		
		if (validaCampo(form.getIndicadorEmissaoExtratoDebitoNaConsulta())) {
			sistemaParametro.setIndicadorEmissaoExtratoNaConsulta(new Short(
					form.getIndicadorEmissaoExtratoDebitoNaConsulta()));
		}
		
		if (validaCampo(form.getIndicadorInclusaoContasCanceladasBoletim())) {
			sistemaParametro.setIndicadorIncluirContasCanceladasPagamento(new Short(
					form.getIndicadorInclusaoContasCanceladasBoletim()));
		}
		
		if (validaCampo(form.getIndicadorIncluirContaEmCobranca())) {
			sistemaParametro.setIndicadorIncluirContaEmCobranca(new Short(
					form.getIndicadorIncluirContaEmCobranca()));
		}
		
		if (validaCampo(form.getNumeroDiasVencimentoCobrancaResultado())) {
			sistemaParametro.setNumeroDiasVencimentoCobrancaResultado(new Integer(
					form.getNumeroDiasVencimentoCobrancaResultado()));
		}
		
	}

	/**
	 * Monta os objetos da 5(Quinta) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2008
	 */
	private void montarSistemaParametro5Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro,HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Indicador de Sugestão de Tramite
		if (validaCampo(form.getIndicadorSugestaoTramite())) {
			sistemaParametro.setIndicadorSugestaoTramite(new Short(form
					.getIndicadorSugestaoTramite()));
		}

		// Indicador de controle de autorizacao para a tramitacao do RA
		if (validaCampo(form.getIndicadorControleTramitacaoRA())) {
			sistemaParametro.setIndicadorControleTramitacaoRA(new Short(form
					.getIndicadorControleTramitacaoRA()));
		}

		// Indicador de calculo da data prevista do RA em dias uteis
		if (validaCampo(form.getIndicadorCalculoPrevisaoRADiasUteis())) {
			sistemaParametro.setIndicadorCalculoPrevisaoRADiasUteis(new Short(
					form.getIndicadorCalculoPrevisaoRADiasUteis()));
		}

		// Indicador de documento obrigatorio para segunda via da conta
		if (validaCampo(form.getIndicadorDocumentoValido())) {
			sistemaParametro.setIndicadorDocumentoValido(new Short(form
					.getIndicadorDocumentoValido()));
		}

		// Dias Maximo para Reativar RA
		if (validaCampo(form.getDiasMaximoReativarRA())) {
			sistemaParametro.setDiasReativacao(new Short(form
					.getDiasMaximoReativarRA()));
		}else{
			throw new ActionServletException(
					"atencao.dias.maximo.para.reativar.ra.inexistente");
		}

		// Dias Maximo para alterar Dados da OS
		if (validaCampo(form.getDiasMaximoAlterarOS())) {
			sistemaParametro.setDiasMaximoAlterarOS(new Integer(form
					.getDiasMaximoAlterarOS()));
		} else {
			sistemaParametro.setDiasMaximoAlterarOS(null);
		}

		// Ultimo ID Utilizado para Geração de RA Manual
		if (validaCampo(form.getUltimoIDGeracaoRA())) {
			sistemaParametro.setUltimoRAManual(new Integer(form
					.getUltimoIDGeracaoRA()));
		}else{
			throw new ActionServletException(
					"atencao.ultimo.id.geracao.ra.inexistente");
		}

		// Dias Maximo para Expirar Acesso
		if (validaCampo(form.getDiasMaximoExpirarAcesso())) {
			sistemaParametro.setNumeroDiasExpiracaoAcesso(new Short(form
					.getDiasMaximoExpirarAcesso()));
		} else
			sistemaParametro.setNumeroDiasExpiracaoAcesso(null);

		// Dias para Começar Aparecer a Msg da Expiracao da Senha
		if (validaCampo(form.getDiasMensagemExpiracaoSenha())) {
			sistemaParametro.setNumeroDiasMensagemExpiracao(new Short(form
					.getDiasMensagemExpiracaoSenha()));
		} else
			sistemaParametro.setNumeroDiasMensagemExpiracao(null);

		// Indicador certidao negativa com efeito positivo
		if (validaCampo(form.getIndicadorCertidaoNegativaEfeitoPositivo())) {
			sistemaParametro
					.setIndicadorCertidaoNegativaEfeitoPositivo(new Short(form
							.getIndicadorCertidaoNegativaEfeitoPositivo()));
		}

		// Indicador debito a cobrar valido certidao negativa
		if (validaCampo(form.getIndicadorDebitoACobrarValidoCertidaoNegativa())) {
			sistemaParametro
					.setIndicadorDebitoACobrarValidoCertidaoNegativa(new Short(
							form.getIndicadorDebitoACobrarValidoCertidaoNegativa()));
		}

		// Numero Dias de Vencimento para gerar Certidao Negativa
		if (validaCampo(form.getDiasVencimentoCertidaoNegativa())) {
			sistemaParametro
					.setNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos(new Short(
							form.getDiasVencimentoCertidaoNegativa()));
		} else {
			sistemaParametro
			.setNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos(null);
		}

		// Numero Maximo de Tentativas de Acesso
		if (validaCampo(form.getNumeroMaximoTentativasAcesso())) {
			sistemaParametro.setNumeroMaximoLoginFalho(new Short(form
					.getNumeroMaximoTentativasAcesso()));
		} else
			sistemaParametro.setNumeroMaximoLoginFalho(null);

		// Numero Maximo de Favoritos no Menu do Sistema
		if (validaCampo(form.getNumeroMaximoFavoritosMenu())) {
			sistemaParametro.setNumeroMaximoFavorito(new Integer(form
					.getNumeroMaximoFavoritosMenu()));
		} else {
			sistemaParametro.setNumeroMaximoFavorito(null);
		}

		// IP do Servidor SMTP
		if (validaCampo(form.getIpServidorSmtp())) {
			sistemaParametro.setIpServidorSmtp(form.getIpServidorSmtp());
		} else {
			sistemaParametro.setIpServidorSmtp(null);
		}
		
		// IP do Servidor Gerencial
		if (validaCampo(form.getIpServidorGerencial())) {
			sistemaParametro.setIpServidorModuloGerencial(form
					.getIpServidorGerencial());
		} else {
			sistemaParametro.setIpServidorModuloGerencial(null);
		}

		// E-mail do Responsavel
		if (validaCampo(form.getEmailResponsavel())) {
			sistemaParametro.setDsEmailResponsavel(form.getEmailResponsavel());
		} else {
			sistemaParametro.setDsEmailResponsavel(null);
		}

		// Mensagem do Sistema
		if (validaCampo(form.getMensagemSistema())) {
			sistemaParametro.setMensagemSistema(form.getMensagemSistema());
		} else {
			sistemaParametro.setMensagemSistema(null);
		}

		// Indicador Login Unico
		if (validaCampo(form.getIndicadorLoginUnico())) {
			sistemaParametro.setIndicadorLoginUnico(new Short(form
					.getIndicadorLoginUnico()));
		}

		if (validaCampo(form.getUltimoDiaVencimentoAlternativo())) {
			sistemaParametro.setUltimoDiaVencimentoAlternativo(new Short(form
					.getUltimoDiaVencimentoAlternativo()));
		} else
			sistemaParametro.setUltimoDiaVencimentoAlternativo(null);

		// Indicador de validação da localidade no encerramento da OS Seletiva
		if (validaCampo(form.getIndicadorValidacaoLocalidadeEncerramentoOS())) {
			sistemaParametro
					.setIndicadorValidarLocalizacaoEncerramentoOS(new Short(
							form.getIndicadorValidacaoLocalidadeEncerramentoOS()));
		}
		
		// Indicador botao imprimir tela sorteio
		if (validaCampo(form.getIndicadorImprimirExtratoSorteio())) {
			sistemaParametro
					.setIndicadorImprimirExtratoSorteio(new Short(
							form.getIndicadorImprimirExtratoSorteio()));
		}
		
		// Indicador de controle de dias de expiração de senha por Grupo
		if (validaCampo(form.getIndicarControleExpiracaoSenhaPorGrupo())) {
			sistemaParametro
					.setIndicadorControleExpiracaoSenhaPorGrupo(new Integer(
							form.getIndicarControleExpiracaoSenhaPorGrupo()));
		}
		
		// Indicador de controle de bloqueio de senhas usadas anteriormente
		if (validaCampo(form.getIndicarControleBloqueioSenha())) {
			sistemaParametro
					.setIndicadorControleBloqueioSenhaAnterior(new Integer(form
							.getIndicarControleBloqueioSenha()));
		}
		
		// Indicador certidao negativa com efeito positivo
		if (validaCampo(form.getIndicadorModuloSeguranca())) {
			sistemaParametro.setIcModulosSeguranca(new Integer(form
					.getIndicadorModuloSeguranca()));
		}

		// Indicador tramite esgoto
		if (validaCampo(form.getIndicadorTramiteEsgoto())) {
			sistemaParametro.setIndicadorTramitarRAEsgoto((new Short(form
					.getIndicadorTramiteEsgoto())));
		}
		
		//Indicador para bloquear funcionalidades de Instalação/Substituição de hidrômetro
		if(validaCampo(form.getIndicadorBloqFuncInstalacaoSubtHidrometro())){
			sistemaParametro.setIndicadorBloqFuncInstalacaoSubtHidrometro((new Short(form
					.getIndicadorBloqFuncInstalacaoSubtHidrometro())));
		}

		// Indicador certidao negativa com efeito positivo
		if (validaCampo(form.getIndicadorPermissaoEspecialGrupo())) {
			sistemaParametro.setIndicadorPermissaoEspecialGrupo(new Short(form
					.getIndicadorPermissaoEspecialGrupo()));
		}

		// Numero Maximo de Favoritos no Menu do Sistema
		if (validaCampo(form.getNumeroDiasBloqueiaSenha())) {
			sistemaParametro.setNumeroDiasBloqueiaSenha(new Integer(form
					.getNumeroDiasBloqueiaSenha()));
		}else{
			throw new ActionServletException(
					"atencao.numero.dias.bloqueia.senha.inexistente");
		}

		// Indicador de controle de senha forte
		if (validaCampo(form.getIndicadorSenhaForte())) {
			sistemaParametro.setIndicadorSenhaForte(new Integer(form
					.getIndicadorSenhaForte()));
		}
		
		// Unidade Organizacional Tramite Grande Consumidor
		if (validaCampo(form.getIdUnidadeDestinoGrandeConsumidor())) {

			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form
							.getIdUnidadeDestinoGrandeConsumidor()));

			Collection<UnidadeOrganizacional> colecaoUnidade = fachada
					.pesquisar(filtroUnidadeEmpresa,
							UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidadeOrganizacionalTramiteGrandeConsumidor = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			if (unidadeOrganizacionalTramiteGrandeConsumidor != null) {

				if (new Short(
						unidadeOrganizacionalTramiteGrandeConsumidor
								.getIndicadorTramite())
						.compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException(
							"atencao.unidade.nao.aceita.tramite");
				}
				if (new Short(

				unidadeOrganizacionalTramiteGrandeConsumidor.getIndicadorUso())
						.compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException(
							"atencao.unidade.nao.ativa");
				}
				sistemaParametro
						.setUnidadeOrganizacionalTramiteGrandeConsumidor(unidadeOrganizacionalTramiteGrandeConsumidor);
			}
		} else {
			sistemaParametro.setUnidadeOrganizacionalTramiteGrandeConsumidor(null);
			
		}

		if (validaCampo(form.getNumeroDiasRevisaoConta())) {
			sistemaParametro.setNumeroDiasRevisaoComPermEspecial(new Integer(
					form.getNumeroDiasRevisaoConta()));
		} else
			sistemaParametro.setNumeroDiasRevisaoComPermEspecial(null);

		// Número de dias para validade ordem de fiscalização
		if (validaCampo(form.getQtdeDiasValidadeOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasValidadeOSFiscalizacao(new Integer(form
					.getQtdeDiasValidadeOSFiscalizacao()));
		} else {
			sistemaParametro.setQtdeDiasValidadeOSFiscalizacao(null);
		}

		// Número máximo de dias para uma ordem de serviço ser fiscalizada
		if (validaCampo(form.getQtdeDiasEncerraOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasEncerraOSFiscalizacao(new Integer(form
					.getQtdeDiasEncerraOSFiscalizacao()));
		} else{
			throw new ActionServletException(
					"atencao.quantidade.dias.encerra.os.fiscalizacao.inexistente");
		}
		
		// Número de dias para envio de conta por email
		if (validaCampo(form.getQtdeDiasEnvioEmailConta())) {
			sistemaParametro.setQtdeDiasEnvioEmailConta(new Integer(form
					.getQtdeDiasEnvioEmailConta()));
		} else
			sistemaParametro.setQtdeDiasEnvioEmailConta(null);

		// RM 5759 Indicador Exigir RA no cancelamento do débito
		if (validaCampo(form.getIndicadorPermiteCancelarDebito())) {
			sistemaParametro.setIndicadorPermiteCancelarDebito(new Short(form
					.getIndicadorPermiteCancelarDebito()));
		}

		// RM 3892 - Implementar Normas de Senhas no GSAN
		if (validaCampo(form.getPeriodoRevalidarSenha())) {
			sistemaParametro.setPeriodoRevalidarSenha(new Integer(form
					.getPeriodoRevalidarSenha()));
		} else {
			sistemaParametro.setPeriodoRevalidarSenha(null);
		}
		
		if (validaCampo(form.getDiasRevalidarSenha())) {
			sistemaParametro.setDiasRevalidarSenha(new Integer(form
					.getDiasRevalidarSenha()));
		} else {
			sistemaParametro.setDiasRevalidarSenha(null);
		}

		if (validaCampo(form.getDiasAtualizarLigacaoAguaImovelFisc())) {
			sistemaParametro.setDiasAtualizarLigacaoAguaImovelFisc(new Integer(
					form.getDiasAtualizarLigacaoAguaImovelFisc()));
		} else{
			throw new ActionServletException(
					"atencao.dias.limite.atualizar.situacao.ligacao.agua.imovel.fiscalizado.inexistente");
		}

		// Descrição do Decreto para Loja Virtual
		if (validaCampo(form.getDescricaoDecreto())) {
			sistemaParametro.setDescricaoDecreto(form.getDescricaoDecreto());
		} else {
			sistemaParametro.setDescricaoDecreto(null);
		}

		// Arquivo do Decreto para Loja Virtual
		if (form.getArquivoDecreto() != null) {
			try {
				if (form.getArquivoDecreto().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoDecreto().getFileData(),
							retornarExtensaoArquivo(form.getArquivoDecreto()));
					sistemaParametro.setArquivoDecreto(form.getArquivoDecreto()
							.getFileData());
				}
			} catch (IOException e) {

			}
		} else {
			sistemaParametro.setArquivoDecreto(null);
		}

		// Descrição da Lei de Estrutura Tarifaria para Loja Virtual
		if (validaCampo(form.getDescricaoLeiEstTarif())) {
			sistemaParametro.setDescricaoLeiEstTarif(form
					.getDescricaoLeiEstTarif());
		} else {
			sistemaParametro.setDescricaoLeiEstTarif(null);
		}

		// Arquivo da Lei de Estrutura Tarifaria para Loja Virtual
		if (form.getArquivoLeiEstTarif() != null) {
			try {
				if (form.getArquivoLeiEstTarif().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoLeiEstTarif().getFileData(),
							retornarExtensaoArquivo(form
									.getArquivoLeiEstTarif()));
					sistemaParametro.setArquivoLeiEstTarif(form
							.getArquivoLeiEstTarif().getFileData());

				}
			} catch (IOException e) {

			}
		} else {
			sistemaParametro.setArquivoLeiEstTarif(null);
		}

		// Descrição da Lei de Individualização Predial para Loja Virtual
		if (validaCampo(form.getDescricaoLeiIndividualizacao())) {
			sistemaParametro.setDescricaoLeiIndividualizacao(form
					.getDescricaoLeiIndividualizacao());
		} else {
			sistemaParametro.setDescricaoLeiIndividualizacao(null);
		}

		// Arquivo da Lei de Individualização Predial para Loja Virtual
		if (form.getArquivoLeiIndividualizacao() != null) {
			try {
				if (form.getArquivoLeiIndividualizacao().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoLeiIndividualizacao().getFileData(),
							retornarExtensaoArquivo(form
									.getArquivoLeiIndividualizacao()));
					sistemaParametro.setArquivoLeiIndividualizacao(form
							.getArquivoLeiIndividualizacao().getFileData());

				}
			} catch (IOException e) {

			}
		} else {
			sistemaParametro.setArquivoLeiIndividualizacao(null);
		}

		// Descrição da Norma CO para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCO())) {
			sistemaParametro.setDescricaoNormaCO(form.getDescricaoNormaCO());
		} else {
			sistemaParametro.setDescricaoNormaCO(null);
		}

		// Arquivo da Norma CO para Loja Virtual
		if (form.getArquivoNormaCO() != null) {
			try {
				if (form.getArquivoNormaCO().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoNormaCO().getFileData(),
							retornarExtensaoArquivo(form.getArquivoNormaCO()));
					sistemaParametro.setArquivoNormaCO(form.getArquivoNormaCO()
							.getFileData());

				}
			} catch (IOException e) {

			}
		} else {
			sistemaParametro.setArquivoNormaCO(null);
		}

		// Descrição da Norma CM para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCM())) {
			sistemaParametro.setDescricaoNormaCM(form.getDescricaoNormaCM());
		} else {
			sistemaParametro.setDescricaoNormaCM(null);
		}

		// Arquivo da Norma CM para Loja Virtual
		if (form.getArquivoNormaCM() != null) {
			try {
				if (form.getArquivoNormaCM().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoNormaCM().getFileData(),
							retornarExtensaoArquivo(form.getArquivoNormaCM()));

					sistemaParametro.setArquivoNormaCM(form.getArquivoNormaCM()
							.getFileData());

				}
			} catch (IOException e) {

			}
		} else {
			sistemaParametro.setArquivoNormaCM(null);
		}
		
		/*
		 * UC - 0060
		 * RM - 9021
		 * Numero de Dias para Encerramento Ordem Servico Factivel Faturavel
		 */
		if(form.getNumeroDiasEncerramentoOrdemServicoFactivelFatural() != null && validaCampo(form.getNumeroDiasEncerramentoOrdemServicoFactivelFatural().trim())){
			sistemaParametro.setNumeroDiasEncerramentoOrdemServicoFactivelFaturavel(
				Short.valueOf(form.getNumeroDiasEncerramentoOrdemServicoFactivelFatural()));
		}else{
			sistemaParametro.setNumeroDiasEncerramentoOrdemServicoFactivelFaturavel(null);
		}
		
		/*
		 * UC - 0060
		 * RM - 9021
		 * Número maximo de OS por arquivo para o sistema de cobrança via smartphone
		 */
		if(form.getNumeroLimiteOSCobranca() != null && validaCampo(form.getNumeroLimiteOSCobranca().trim())){
			sistemaParametro.setNumeroLimiteOSCobranca(
				Integer.valueOf(form.getNumeroLimiteOSCobranca()));
		}else{
			sistemaParametro.setNumeroLimiteOSCobranca(null);
		}
		
		/*
		 * UC - 0060
		 * RM - 9021
		 * Servico da Ordem de Servico seletiva Factivel Faturavel
		 */
		ServicoTipo servicoTipo = null;
		if(form.getIdServicoTipo() != null && validaCampo(form.getIdServicoTipo().trim())){
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getIdServicoTipo()));
			Collection<ServicoTipo> colecaoServicoTipo = fachada
					.pesquisar(filtroServicoTipo,
							ServicoTipo.class.getName());

			servicoTipo = (ServicoTipo) Util
					.retonarObjetoDeColecao(colecaoServicoTipo);

			if (colecaoServicoTipo != null) {
				/*
				 * UC - 0060
				 * RM - 9021
				 * FS - 0018
				 */
				if (servicoTipo==null) {
					throw new ActionServletException(
							"atencao.servico_tipo_inexistente");
				}
				/*
				 * UC - 0060
				 * RM - 9021
				 * FS - 0018
				 */
				if(servicoTipo.getIndicadorUso()==ConstantesSistema.NAO) {
					throw new ActionServletException(
							"atencao.servico_tipo_inexistente");
				}
				sistemaParametro
						.setServicoTipo(servicoTipo);
			}
		}else{
			sistemaParametro.setServicoTipo(null);
		}

	}

	private String retornarExtensaoArquivo(FormFile formFile) {
		String[] nomeArquivoPartido = formFile.getFileName().split("\\.");

		String formato = nomeArquivoPartido[1];

		return formato;

	}
}