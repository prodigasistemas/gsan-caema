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

import gcom.atendimentopublico.ResolucaoImagem;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/01/2007
 */
public class ExibirInformarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("informarParametrosSistemaDadosGeraisEmpresaAction");
		
		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		// obtém a instância da sessão
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"informarParametrosSistemaWizardAction",
				"informarParametrosSistemaAction",
				"cancelarInformarParametrosSistemaAction",
				"exibirInformarParametrosSistemaAction.do");
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			1,
			"DadosGeraisPrimeiraAbaA.gif",
			"DadosGeraisPrimeiraAbaD.gif",
			"exibirInformarParametrosSistemaDadosGeraisEmpresaAction",
			"informarParametrosSistemaDadosGeraisEmpresaAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			2,
			"FaturamentoTarifaSocialIntervaloA.gif",
			"FaturamentoTarifaSocialIntervaloD.gif",
			"exibirInformarParametrosSistemaFaturamentoTarifaSocialAction",
			"informarParametrosSistemaFaturamentoTarifaSocialAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			3,
			"ArrecadacaoFinanceiroIntervaloA.gif",
			"ArrecadacaoFinanceiroIntervaloD.gif",
			"exibirInformarParametrosSistemaArrecadacaoFinanceiroAction",
			"informarParametrosSistemaArrecadacaoFinanceiroAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			4,
			"MedicaoCobrancaIntervaloA.gif",
			"MedicaoCobrancaIntervaloD.gif",
			"exibirInformarParametrosSistemaMicromedicaoCobrancaAction",
			"informarParametrosSistemaMicromedicaoCobrancaAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			5,
			"AtendimentoSegurancaUltimaAbaA.gif",
			"AtendimentoSegurancaUltimaAbaD.gif",
			"exibirInformarParametrosSistemaAtendimentoPublicoSegurancaAction",
			"informarParametrosSistemaAtendimentoPublicoSegurancaAction"));

		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clientePresidenteCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalIdPresidencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorComercialCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorGestao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteFicticioParaAssociarOsPagamentosNaoIdentificados");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteUsuarioDesconhecido");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteResponsavelProgramaEspecial");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRetificacaoPagamentoAntecipado");
		
		Collection colecaoSistemaParametro = 
			this.getFachada().pesquisar(filtroSistemaParametro, 
				SistemaParametro.class.getName());
		
		//Coleção Resolucao Imagem
		Filtro filtro = new FiltroCliente();
		Collection<ResolucaoImagem> colecao = 
				(Collection<ResolucaoImagem>)this.getFachada().pesquisar(filtro, ResolucaoImagem.class.getName());
		sessao.setAttribute("colResolucaoImagem", colecao);

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		setDadosAba1(sistemaParametro, form);
		setDadosAba2(sistemaParametro, form);
		setDadosAba3(sistemaParametro, form);
		setDadosAba4(sistemaParametro, form);
		setDadosAba5(sistemaParametro, form);

		sessao.setAttribute("sistemaParametro", sistemaParametro);
		sessao.setAttribute("paramId", sistemaParametro.getParmId());

		return retorno;
	}
	
	private void setDadosAba1(SistemaParametro sistemaParametro, InformarSistemaParametrosActionForm form) {
		
		form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(sistemaParametro
				.getIndicadorCriticarConteudoRetornoMovimentoNegativacao()
				.toString());

		
		
		form.setNomeEstado(sistemaParametro.getNomeEstado());
		form.setNomeEmpresa(sistemaParametro.getNomeEmpresa());
		form.setAbreviaturaEmpresa(sistemaParametro
				.getNomeAbreviadoEmpresa());
		form.setCnpj(sistemaParametro.getCnpjEmpresa());

		if (sistemaParametro.getNumeroImovel() != null) {
			form.setNumero(sistemaParametro.getNumeroImovel());
		}
		// Seta o número de dias de envio conta empresa cobrança
		if (sistemaParametro.getNumeroDiasEnvioContaEmpresaCobranca() != null) {
			form.setNumeroDiasEnvioContaEmpresaCobranca(sistemaParametro
					.getNumeroDiasEnvioContaEmpresaCobranca().toString());
		}
		// Seta o número de dias para retirada da empresa cobrança
		if (sistemaParametro.getNumeroDiaRetiradaContaEmpresaCobraca() != null) {
			form.setNumeroDiaRetiradaContaEmpresaCobraca(sistemaParametro
					.getNumeroDiaRetiradaContaEmpresaCobraca().toString());
		}
		// Número máximo de parcelas de contratos parcelamento na primeira
		// aba
		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null) {
			form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro
					.getNumeroMaximoParcelasContratosParcelamento()
					.toString());
		}
		// Indicador de conta fora do vencimeto na primeira aba
		if (sistemaParametro.getIndicadorIncluirContasForaVenCobranca() != null) {
			form.setIndicadorIncluirContasForaVenCobranca(sistemaParametro
					.getIndicadorIncluirContasForaVenCobranca().toString());
		}
		// Numero dias vencimento
		if (sistemaParametro
				.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() != null) {
			form.setDiasVencimentoCertidaoNegativa(""
					+ sistemaParametro
							.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		}

		if (sistemaParametro.getMensagemSistema() != null) {
			form.setMensagemSistema(sistemaParametro.getMensagemSistema());
		}

		if (sistemaParametro.getNumeroMesesMaximoCalculoMedia() != null) {
			form.setNumeroMesesMaximoCalculoMedia(sistemaParametro
					.getNumeroMesesMaximoCalculoMedia().toString());
		}
		
		if (sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null) {
			form.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
		}
		
		if (sistemaParametro.getUltimoRAManual() != null) {
			form.setUltimoIDGeracaoRA(sistemaParametro.getUltimoRAManual()
					.toString());
		}
		
		if (sistemaParametro.getDiasMaximoAlterarOS() != null) {
			form.setDiasMaximoAlterarOS(sistemaParametro
					.getDiasMaximoAlterarOS().toString());
		}
		
		if (sistemaParametro.getNumeroDiasBloqueiaSenha() != null) {
			form.setNumeroDiasBloqueiaSenha(sistemaParametro
					.getNumeroDiasBloqueiaSenha().toString());
		}
		
		if (sistemaParametro.getDiasAtualizarLigacaoAguaImovelFisc() != null) {
			form.setDiasAtualizarLigacaoAguaImovelFisc(sistemaParametro
					.getDiasAtualizarLigacaoAguaImovelFisc().toString());
		}
		
		if (sistemaParametro.getDiasReativacao() != null) {
			form.setDiasMaximoReativarRA(sistemaParametro
					.getDiasReativacao().toString());
		}
		
		// Seta o número de dias que permite inserir processo comando de cobrança
		if (sistemaParametro.getNumeroDiasInserirProcessoCobranca() != null) {
			form.setNumeroDiasInserirProcessoCobranca(sistemaParametro
					.getNumeroDiasInserirProcessoCobranca().toString());
		}

		// seta a quantidade de dígitos da quadra da empresa de acordo com
		// os dados pegos do banco de
		// dados que foram passados pelo
		// ExibirInformarParametrosSistemaAction
		// o número de quadra tem como valor default 3 - uma vez que é NOT
		// NULL, não precisa de
		// validação
		form.setQuantidadeDigitosQuadra(sistemaParametro
				.getNumeroDigitosQuadra() + "");

		if (sistemaParametro.getComplementoEndereco() != null) {
			form.setComplemento(sistemaParametro.getComplementoEndereco());
		}

		if (sistemaParametro.getNumeroTelefone() != null) {
			form.setNumeroTelefone(sistemaParametro.getNumeroTelefone());
		}

		if (sistemaParametro.getNumeroRamal() != null) {
			form.setRamal(sistemaParametro.getNumeroRamal());
		}

		if (sistemaParametro.getNumeroFax() != null) {
			form.setFax(sistemaParametro.getNumeroFax());
		}

		if (sistemaParametro.getDescricaoEmail() != null) {
			form.setEmail(sistemaParametro.getDescricaoEmail());
		}

		if (sistemaParametro.getTituloPagina() != null) {
			form.setTitulosRelatorio(sistemaParametro.getTituloPagina());
		}

		if (sistemaParametro.getUnidadeOrganizacionalIdPresidencia() != null) {
			form.setUnidadeOrganizacionalPresidencia(sistemaParametro
					.getUnidadeOrganizacionalIdPresidencia().getId()
					.toString());

			form.setNomeUnidadeOrganizacionalPresidencia(sistemaParametro
					.getUnidadeOrganizacionalIdPresidencia().getDescricao());
		}

		if (sistemaParametro.getClientePresidenteCompesa() != null) {
			form.setPresidente(sistemaParametro
					.getClientePresidenteCompesa().getId().toString());

			form.setNomePresidente(sistemaParametro
					.getClientePresidenteCompesa().getDescricao());
		}

		if (sistemaParametro.getClienteDiretorComercialCompesa() != null) {
			form.setDiretorComercial(sistemaParametro
					.getClienteDiretorComercialCompesa().getId().toString());

			form.setNomeDiretorComercial(sistemaParametro
					.getClienteDiretorComercialCompesa().getDescricao());
		}
		
		if (sistemaParametro.getClienteDiretorGestao() != null) {
			form.setDiretorGestao(sistemaParametro
					.getClienteDiretorGestao().getId().toString());

			form.setNomeDiretorGestao(sistemaParametro
					.getClienteDiretorGestao().getDescricao());
		}

		if (sistemaParametro.getNumero0800Empresa() != null) {
			form.setNumeroTelefoneAtendimento(sistemaParametro
					.getNumero0800Empresa());
		}

		if (sistemaParametro.getNomeSiteEmpresa() != null) {
			form.setSite(sistemaParametro.getNomeSiteEmpresa());
		}

		if (sistemaParametro.getInscricaoEstadual() != null) {
			form.setInscricaoEstadual(sistemaParametro
					.getInscricaoEstadual());
		}

		if (sistemaParametro.getInscricaoMunicipal() != null) {
			form.setInscricaoMunicipal(sistemaParametro
					.getInscricaoMunicipal());
		}

		if (sistemaParametro.getNumeroContratoPrestacaoServico() != null) {
			form.setNumeroContrato(sistemaParametro
					.getNumeroContratoPrestacaoServico().toString());
		}

		if (sistemaParametro.getImagemLogomarca() != null) {
			form.setImagemLogomarca(sistemaParametro.getImagemLogomarca());
		}

		if (sistemaParametro.getImagemRelatorio() != null) {
			form.setImagemRelatorio(sistemaParametro.getImagemRelatorio());
		}

		if (sistemaParametro.getImagemConta() != null) {
			form.setImagemConta(sistemaParametro.getImagemConta());
		}

		if (sistemaParametro.getNumeroExecucaoResumoNegativacao() != null) {
			form.setNumeroExecucaoResumoNegativacao(sistemaParametro
					.getNumeroExecucaoResumoNegativacao().toString());
		}

		if (sistemaParametro.getVersaoCelular() != null) {
			form.setVersaoCelular(sistemaParametro.getVersaoCelular());
		}

		if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {
			form.setNumeroDiasBloqueioCelular(sistemaParametro
					.getNumeroDiasBloqueioCelular().toString());
		}

		if (sistemaParametro.getPercentualConvergenciaRepavimentacao() != null) {
			form.setPercentualConvergenciaRepavimentacao(Util
					.formatarBigDecimalParaStringComVirgula(sistemaParametro
							.getPercentualConvergenciaRepavimentacao()));
		}

		form.setIndicadorControlaAutoInfracao(""
				+ sistemaParametro.getIndicadorControlaAutoInfracao());

		form.setIndicadorExibirMensagem(""
				+ sistemaParametro
						.getIndicadorExibeMensagemNaoReceberPagamento());

		form.setIndicadorUsaRota(""
				+ sistemaParametro.getIndicadorUsaRota());

		form.setIndicadorDuplicidadeCliente(sistemaParametro
				.getIndicadorDuplicidadeCliente().toString());
		form.setIndicadorNomeMenorDez(sistemaParametro
				.getIndicadorNomeMenorDez().toString());
		form.setIndicadorNomeClienteGenerico(sistemaParametro
				.getIndicadorNomeClienteGenerico().toString());

		

		if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null) {
			form.setIndicadorVariaHierarquiaUnidade(sistemaParametro
					.getIndicadorVariaHierarquiaUnidade().toString());
		}

		if (sistemaParametro
				.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
					.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
					.getId().toString());

			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
					.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
					.getDescricao());

		}

		if (sistemaParametro.getClienteUsuarioDesconhecido() != null) {
			form.setClienteUsuarioDesconhecido(sistemaParametro
					.getClienteUsuarioDesconhecido().getId().toString());

			form.setNomeClienteUsuarioDesconhecido(sistemaParametro
					.getClienteUsuarioDesconhecido().getDescricao());

		}
		
		if (sistemaParametro.getDominioEmailCorporativo() != null) {
			form.setDominioEmailCorporativo(sistemaParametro.getDominioEmailCorporativo());
		}

		// Cliente responsável

		if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {
			form.setIdClienteResponsavelProgramaEspecial(sistemaParametro
					.getClienteResponsavelProgramaEspecial().getId()
					.toString());

			form.setNomeClienteResponsavelProgramaEspecial(sistemaParametro
					.getClienteResponsavelProgramaEspecial().getDescricao());

		}

		if (sistemaParametro.getPerfilProgramaEspecial() != null) {
			form.setPerfilProgramaEspecial(sistemaParametro
					.getPerfilProgramaEspecial().getId().toString());
		}

		if (sistemaParametro.getIndicadorPopupAtualizacaoCadastral() != null) {
			form.setIndicadorPopupAtualizacaoCadastral(sistemaParametro
					.getIndicadorPopupAtualizacaoCadastral().toString());
		}

		if (sistemaParametro.getValorExtratoFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorExtratoFichaComp());

			form.setValorExtratoFichaComp(valorAux);
		}

		if (sistemaParametro.getValorGuiaFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorGuiaFichaComp());

			form.setValorGuiaFichaComp(valorAux);
		}

		if (sistemaParametro.getValorDemonstrativoParcelamentoFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorDemonstrativoParcelamentoFichaComp());

			form.setValorDemonstrativoParcelamentoFichaComp(valorAux);
		}

		if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia() != null) {
			form.setIndicadorUsoNMCliReceitaFantasia(sistemaParametro
					.getIndicadorUsoNMCliReceitaFantasia().toString());
		}
		
		if (sistemaParametro.getFaturamentoInicioContratoPPP() != null) {

			form.setFaturamentoInicioContratoPPP(
				Util.formatarAnoMesParaMesAno(
					sistemaParametro.getFaturamentoInicioContratoPPP().toString()));

		}
		
		if (sistemaParametro.getPercentualPPP() != null) {
			BigDecimal percentualPPPBigDecimal = new BigDecimal(sistemaParametro.getPercentualPPP().toString());
			String percentualPPPString = Util.formatarMoedaReal(percentualPPPBigDecimal); 
			
			form.setPercentualPPP(percentualPPPString);
		}
		
		if (sistemaParametro.getQuantidadeDiasFaturarFactivel() != null) {
			form.setQuantidadeDiasFaturarFactivel(sistemaParametro.getQuantidadeDiasFaturarFactivel().toString());
		}
		
		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null
				&& (form.getNumeroMaximoParcelasContratosParcelamento() == null || form
						.getNumeroMaximoParcelasContratosParcelamento()
						.equals(""))) {
			form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro
					.getNumeroMaximoParcelasContratosParcelamento()
					.toString());
		}

		if (sistemaParametro.getIndicadorBloquearFunUsuario() != null) {
			form.setIndicadorBloquearFunUsuario(sistemaParametro
					.getIndicadorBloquearFunUsuario().toString());
		}
		
		
		//RM8015 - alterado por Ana Maria - 23/10/2012 
		if(sistemaParametro.getDataLimiteCadastroSorteio() != null){

			String dataLimiteCadastroSorteio = 
				Util.formatarData(sistemaParametro.getDataLimiteCadastroSorteio());
			
			form.setDataLimiteCadastroSorteio(dataLimiteCadastroSorteio);
		}
		
		if(sistemaParametro.getDataSorteio() != null){

			String dataSorteio = 
				Util.formatarData(sistemaParametro.getDataSorteio());
			
			form.setDataSorteio(dataSorteio);
		}
		
		if(sistemaParametro.getNumeroLimiteOSCobranca() != null){
			form.setNumeroLimiteOSCobranca(sistemaParametro.getNumeroLimiteOSCobranca().toString());
		}
		
		if (sistemaParametro.getImagemArpe() != null) {
			form.setImagemArpe(sistemaParametro.getImagemArpe());
		}
		
		if (sistemaParametro.getImagemRodapeConta() != null) {
			form.setImagemRodapeConta(sistemaParametro.getImagemRodapeConta());
		}
		
		if (sistemaParametro.getUrl2ViaConta() != null) {
			form.setUrl2ViaConta(sistemaParametro.getUrl2ViaConta());
		}
		
		if (sistemaParametro.getIndicadorDocumentoObrigatorioManterCliente() != null) {
			form.setIndicadorDocumentoObrigatorioManterCliente(sistemaParametro.getIndicadorDocumentoObrigatorioManterCliente().toString());
		}
			
		if(sistemaParametro.getTamanhoMaximoAnexoRA() != null){
			form.setTamanhoMaximoAnexoRA(sistemaParametro.getTamanhoMaximoAnexoRA().toString());
		}
		
		if(sistemaParametro.getResolucaoImagem() != null){
			form.setResolucaoImagem(sistemaParametro.getResolucaoImagem().getId().toString());
		}
		
		
	}
	
	private void setDadosAba2(SistemaParametro sistemaParametro, InformarSistemaParametrosActionForm form) {
		String anoMesFaturamento = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento().toString());
			
		form.setMesAnoReferencia(anoMesFaturamento);
		form.setMenorConsumo(sistemaParametro.getMenorConsumoGrandeUsuario().toString());

		if (sistemaParametro.getValorMinimoEmissaoConta() != null) {

			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getValorMinimoEmissaoConta());

			form.setMenorValor(valorAux);
		}

		form.setQtdeEconomias(sistemaParametro.getMenorEconomiasGrandeUsuario().toString());
		
		form.setQtdeContasRetificadas(sistemaParametro.getQtdMaxContasRetificadas()+"");
		
		if(sistemaParametro.getQtdDiasParaSuspensaoDoacao() != null){
			form.setQtdDiasDebitoVencidoParaSuspensaoGeracaoDeDoacoes(sistemaParametro.getQtdDiasParaSuspensaoDoacao().toString());
		}
		
		if(sistemaParametro.getMesesMediaConsumo() != null){
			form.setMesesCalculoMedio(sistemaParametro.getMesesMediaConsumo().toString());	
		}

		if(sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null){
			form.setDiasMinimoVencimento(sistemaParametro.getNumeroMinimoDiasEmissaoVencimento().toString());
		}
		
		if(sistemaParametro.getNumeroDiasAdicionaisCorreios() != null){
			form.setDiasMinimoVencimentoCorreio(sistemaParametro.getNumeroDiasAdicionaisCorreios().toString());
		}
		
		if(sistemaParametro.getDiasVencimentoAlternativo() != null){
			form.setDiasVencimentoAlternativo(sistemaParametro.getDiasVencimentoAlternativo());
		}

		if(sistemaParametro.getNumeroMesesValidadeConta() != null){
			form.setNumeroMesesValidadeConta(sistemaParametro.getNumeroMesesValidadeConta().toString());
		}
		
		if(sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento() != null){
			form.setNumeroMesesAlteracaoVencimento(sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento().toString());
		}


		if(sistemaParametro.getNumeroMesesCalculoCorrecao() != null){
			form.setNumeroMesesCalculoCorrecao(sistemaParametro.getNumeroMesesCalculoCorrecao().toString());
		}
		
		if(sistemaParametro.getNumeroVezesSuspendeLeitura() != null){
			form.setNumeroVezesSuspendeLeitura(sistemaParametro.getNumeroVezesSuspendeLeitura().toString());
		}
		
		if(sistemaParametro.getNumeroMesesLeituraSuspensa() != null){
			form.setNumeroMesesLeituraSuspensa(sistemaParametro.getNumeroMesesLeituraSuspensa().toString());
		}
		
		if(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento() != null){
			form.setNumeroMesesReinicioSitEspFatu(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento().toString());
		}

		if(sistemaParametro.getQtdEconomiaContratoDemanda() != null){ 
			form.setQtdEconomiaContratoDemanda(sistemaParametro.getQtdEconomiaContratoDemanda().toString());
		}
		
		if (sistemaParametro.getIndicadorRoteiroEmpresa() != null) {
			form.setIndicadorRoteiroEmpresa(sistemaParametro.getIndicadorRoteiroEmpresa().toString());
		}
		
		if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento() != null) {
			form.setIndicadorLimiteAlteracaoVencimento(sistemaParametro.getIndicadorLimiteAlteracaoVencimento().toString());
		}

		if (sistemaParametro.getIndicadorCalculaVencimento() != null) {
			form.setIndicadorCalculaVencimento(sistemaParametro.getIndicadorCalculaVencimento().toString());
		}

		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			form.setIndicadorTarifaCategoria(sistemaParametro.getIndicadorTarifaCategoria().toString());
		}

		form.setIndicadorAtualizacaoTarifaria(""+sistemaParametro.getIndicadorAtualizacaoTarifaria());
		
		if(sistemaParametro.getAnoMesAtualizacaoTarifaria() != null){

			String anoMes = 
				Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesAtualizacaoTarifaria().toString());
			
			form.setMesAnoAtualizacaoTarifaria(anoMes);
		}

		if (sistemaParametro.getIndicadorFaturamentoAntecipado() != null) {
			form.setIndicadorFaturamentoAntecipado(sistemaParametro.getIndicadorFaturamentoAntecipado().toString());
		}
		
		form.setIndicadorRetificacaoValorMenor("" + sistemaParametro.getIndicadorRetificacaoValorMenor());
		
		form.setIndicadorTransferenciaComDebito("" + sistemaParametro.getIndicadorTransferenciaComDebito());
		
		form.setIndicadorNaoMedidoTarifa("" + sistemaParametro.getIndicadorNaoMedidoTarifa());
		
		form.setIndicadorQuadraFace("" + sistemaParametro.getIndicadorQuadraFace());
		
		if (sistemaParametro.getValorSalarioMinimo() != null) {

			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getValorSalarioMinimo());

			form.setSalarioMinimo(valorAux);
		}
		
		if (sistemaParametro.getAreaMaximaTarifaSocial() != null) {
			form.setAreaMaxima(sistemaParametro.getAreaMaximaTarifaSocial().toString());
		}
		
		if (sistemaParametro.getConsumoEnergiaMaximoTarifaSocial() != null) {
			form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
		}
		
		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
		}
		
		if (sistemaParametro.getNumeroDiasVariacaoConsumo() != null) {
			form.setNumeroDiasVariacaoConsumo(sistemaParametro.getNumeroDiasVariacaoConsumo().toString());
		}
		
		if ( sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao() != null ) {
			form.setNnDiasPrazoRecursoAutoInfracao( sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao().toString() );
		}
		
		if ( sistemaParametro.getPercentualBonusSocial() != null ) {
			
			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getPercentualBonusSocial());
			
			form.setPercentualBonusSocial( valorAux );
		}
		
		
		if(sistemaParametro.getIndicadorBloqueioContaMobile()!=null){
			form.setIndicadorBloqueioContaMobile(
					sistemaParametro.getIndicadorBloqueioContaMobile()
						.toString());
		}
	

		if ( sistemaParametro.getValorContaFichaComp() != null ) {
			
			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getValorContaFichaComp());
			
			form.setValorContaFichaComp( valorAux );
		}

		if (sistemaParametro.getNumeroMesesRetificarConta() != null) {

			String valorAux = sistemaParametro
					.getNumeroMesesRetificarConta().toString();

			form.setNumeroMesesRetificarConta(valorAux);
		}
		
		
		if (sistemaParametro.getMensagemContaBraile() != null) {

			String msgContaBraile = sistemaParametro
					.getMensagemContaBraile().toString();

			form.setMensagemContaBraile(msgContaBraile);
		}
		
		if(sistemaParametro.getCodigoTipoCalculoNaoMedido() != null){
			form.setCodigoTipoCalculoNaoMedido(sistemaParametro.getCodigoTipoCalculoNaoMedido().toString());
		}
		
		if (sistemaParametro.getIndicadorNormaRetificacao() != null) {
			form.setIndicadorNormaRetificacao(sistemaParametro.getIndicadorNormaRetificacao().toString());
		}
		
		form.setIndicadorPercentualColetaEsgoto(sistemaParametro.getIndicadorPercentualColetaEsgoto().toString());
		
		form.setIndicadorDesconsiderarRateioEsgoto(sistemaParametro.getIndicadorDesconsiderarRateioEsgoto().toString());
		
		if(sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao() != null){
			form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(
					sistemaParametro.
					getIndicadorCriticarConteudoRetornoMovimentoNegativacao().toString());
		}
		// quantidade maxima de vezes para alterar o vinculo de uma conta.
		if(sistemaParametro.getNumeroVezesAlterarVinculo() != null){
			form.setNumeroVezesAlterarVinculo(sistemaParametro.getNumeroVezesAlterarVinculo().toString());
		}
		
		// quantidade máxima de contas para retirar imóvel da 
		//situação especial de faturamento por inadimplência
		
		if(sistemaParametro.getQuantidadeMaximaContasItensEspecialFaturamento() != null){
			form.setQuantidadeMaximaContasItensEspecialFaturamento(sistemaParametro.getQuantidadeMaximaContasItensEspecialFaturamento().toString());
		}
	
		if (sistemaParametro.getContaMotivoRetificacaoPagamentoAntecipado() != null) {
			form.setMotivoRetificacaoContaPagtoAntecipado(sistemaParametro.getContaMotivoRetificacaoPagamentoAntecipado().getId().toString());
		}
		
		if(sistemaParametro.getNumeroLimiteAlteracaoVencimento() != null){
			form.setNumeroLimiteAlteracoesVencimento(
					sistemaParametro.
					getNumeroLimiteAlteracaoVencimento().toString());
		}

	}
	
	private void setDadosAba3(SistemaParametro sistemaParametro, InformarSistemaParametrosActionForm form) {
		String anoMesArrecadacao = 
				Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString());
			
		form.setMesAnoReferenciaArrecadacao("" + anoMesArrecadacao);

		if (sistemaParametro.getCodigoEmpresaFebraban() != null) {
			form.setCodigoEmpresaFebraban(sistemaParametro.getCodigoEmpresaFebraban().toString());
		}

		if (sistemaParametro.getNumeroLayoutFebraban() != null) {
			form.setNumeroLayOut(sistemaParametro.getNumeroLayoutFebraban().toString());
		}

		if (sistemaParametro.getContaBancaria() != null) {
			form.setIndentificadorContaDevolucao(sistemaParametro.getContaBancaria().getId().toString());
		}
		
		if(sistemaParametro.getIndicadorValorMovimentoArrecadador() != null){
			form.setIndicadorValorMovimentoArrecadador(String.valueOf(sistemaParametro.getIndicadorValorMovimentoArrecadador())) ; 
		}

		if (sistemaParametro.getPercentualFinanciamentoEntradaMinima() != null) {

			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getPercentualFinanciamentoEntradaMinima());

			form.setPercentualEntradaMinima(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelasFinanciamento() != null) {
			form.setMaximoParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento().toString());
		}

		if (sistemaParametro.getPercentualMaximoAbatimento() != null) {

			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getPercentualMaximoAbatimento());

			form.setPercentualMaximoAbatimento(valorAux);
		}

		if (sistemaParametro.getPercentualTaxaJurosFinanciamento() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualTaxaJurosFinanciamento());

			form.setPercentualTaxaFinanciamento(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelaCredito() != null) {
			form.setNumeroMaximoParcelaCredito(sistemaParametro.getNumeroMaximoParcelaCredito().toString());
		}

		if (sistemaParametro.getPercentualMediaIndice() != null) {

			String valorAux = 
				Util.formatarMoedaReal(sistemaParametro.getPercentualMediaIndice());

			form.setPercentualCalculoIndice(valorAux);
		}
		
		if(sistemaParametro.getNumeroModuloDigitoVerificador()!=null
				&& !sistemaParametro.getNumeroModuloDigitoVerificador().equals("")){
			
			form.setNumeroModuloDigitoVerificador(sistemaParametro.getNumeroModuloDigitoVerificador().toString());
			
		}
		if(sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos()!=null){
			form.setNumeroMesesPesquisaImoveisRamaisSuprimidos(
					sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos().toString());
		}
		if(sistemaParametro.getNumeroAnoQuitacao()!=null){
			form.setNumeroAnoQuitacao(
					sistemaParametro.getNumeroAnoQuitacao().toString());
	  	}
		if(sistemaParametro.getIndicadorContaParcelada()!=null){
			form.setIndicadorContaParcelada(
					sistemaParametro.getIndicadorContaParcelada().toString());	
		}
		if(sistemaParametro.getIndicadorCobrancaJudical()!=null){
			form.setIndicadorCobrancaJudical(
					sistemaParametro.getIndicadorCobrancaJudical().toString());	
		}
		if(sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao()!=null){
			form.setNumeroMesesAnterioresParaDeclaracaoQuitacao(
					sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao().toString());	
		}
		if (sistemaParametro.getCdDadosDiarios() != null){
			form.setCdDadosDiarios(
					sistemaParametro.getCdDadosDiarios().toString());
		}
		
		if(sistemaParametro.getNumeroConvenioFichaCompensacao() != null){
			form.setNumeroConvenioFichaCompensacao(
				sistemaParametro.getNumeroConvenioFichaCompensacao().toString());
		}
	}
	
	private void setDadosAba4(SistemaParametro sistemaParametro, InformarSistemaParametrosActionForm form) {
		
		if (sistemaParametro.getHidrometroCapacidade() != null) {
			form.setCodigoMenorCapacidade(sistemaParametro
					.getHidrometroCapacidade().getId().toString());
		}

		if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
			form.setIndicadorGeracaoFaixaFalsa(sistemaParametro
					.getIndicadorFaixaFalsa().toString());
		}

		if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
			form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro
					.getIndicadorUsoFaixaFalsa().toString());
		}

		if (sistemaParametro.getPercentualFaixaFalsa() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFaixaFalsa());

			form.setPercentualGeracaoFaixaFalsa(valorAux);
		}

		if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
			form.setIndicadorPercentualGeracaoFiscalizacaoLeitura(sistemaParametro
					.getIndicadorPercentualFiscalizacaoLeitura().toString());
		}

		if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
			form.setIndicadorGeracaoFiscalizacaoLeitura(sistemaParametro
					.getIndicadorUsoFiscalizadorLeitura().toString());
		}

		if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFiscalizacaoLeitura());

			form.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
		}

		if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

			form.setIncrementoMaximoConsumo(sistemaParametro
					.getIncrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
			form.setDecrementoMaximoConsumo(sistemaParametro
					.getDecrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getPercentualToleranciaRateio() != null) {

			String valorAux = Util.formataBigDecimal(
					sistemaParametro.getPercentualToleranciaRateio(), 1,
					false);

			form.setPercentualToleranciaRateioConsumo(valorAux);
		}

		if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
			form.setDiasVencimentoCobranca(sistemaParametro
					.getNumeroDiasVencimentoCobranca().toString());
		}

		if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
			form.setNumeroMaximoMesesSancoes(sistemaParametro
					.getNumeroMaximoMesesSancoes().toString());
		}

		form.setValorSegundaVia(Util.formatarMoedaReal(sistemaParametro
				.getValorSegundaVia()));

		form.setIndicadorCobrarTaxaExtrato(""
				+ sistemaParametro.getIndicadorCobrarTaxaExtrato());

		if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
			form.setCodigoPeriodicidadeNegativacao(sistemaParametro
					.getCodigoPeriodicidadeNegativacao().toString());
		}

		//Número dias Calcular Acrescimos
		if (sistemaParametro.getNumeroDiasCalculoAcrescimos() != null) {
			form.setNumeroDiasCalculoAcrescimos(sistemaParametro
					.getNumeroDiasCalculoAcrescimos().toString());
		}
		
		//Número dias Encerrar OS Fiscalização
		if (sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null) {
			form.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro
					.getQtdeDiasEncerraOSFiscalizacao().toString());
		}

		//Número dias para calcular extrato debito
		if (sistemaParametro.getNumeroDiasValidadeExtrato() != null) {
			form.setNumeroDiasValidadeExtrato(sistemaParametro
					.getNumeroDiasValidadeExtrato().toString());
		}

		form.setIndicadorParcelamentoConfirmado(""
				+ sistemaParametro.getIndicadorParcelamentoConfirmado());

		form.setIndicadorTabelaPrice(""
				+ sistemaParametro.getIndicadorTabelaPrice());

		if (sistemaParametro
				.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null) {
			form.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(sistemaParametro
					.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo()
					.toString());
		}

		if (sistemaParametro
				.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
			form.setNumeroDiasValidadeExtratoPermissaoEspecial(sistemaParametro
					.getNumeroDiasValidadeExtratoPermissaoEspecial()
					.toString());
		}

		form.setNumeroDiasVencimentoEntradaParcelamento(""
				+ sistemaParametro
						.getNumeroDiasVencimentoEntradaParcelamento());

		if (sistemaParametro.getResolucaoDiretoria() != null
				&& sistemaParametro.getResolucaoDiretoria().getId() != null) {
			form.setIdResolucaoDiretoria(sistemaParametro
					.getResolucaoDiretoria().getId().toString());
		}
	
		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null) {
			form.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro
					.getIndicadorBloqueioContasContratoParcelDebitos()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorBloqueioContasContratoParcelManterConta() != null) {
			form.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro
					.getIndicadorBloqueioContasContratoParcelManterConta()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() != null) {
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro
					.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorBloqueioDebitoACobrarContratoParcelDebito() != null) {
			form.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(sistemaParametro
					.getIndicadorBloqueioDebitoACobrarContratoParcelDebito()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null) {
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro
					.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() != null) {
			form.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(sistemaParametro
					.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()
					.toString());
		}
	
		if (sistemaParametro
				.getIndicadorCriticarConteudoRetornoMovimentoNegativacao() != null) {
			form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(sistemaParametro
					.getIndicadorCriticarConteudoRetornoMovimentoNegativacao()
					.toString());
		}
	
		if (sistemaParametro.getIndicadorTotalDebito() != null) {
			form.setIndicadorTotalDebito(sistemaParametro
					.getIndicadorTotalDebito().toString());
		}
	
		if (sistemaParametro.getIndicadorCanceNegatContaVencAlter() != null) {
			form.setIndicadorCanceNegatContaVencAlter(sistemaParametro
					.getIndicadorCanceNegatContaVencAlter().toString());
		}
		
		if (sistemaParametro.getIndicadorCanceNegatContaVencAlter() != null) {
			form.setIndicadorCanceNegatContaVencAlter(sistemaParametro
					.getIndicadorCanceNegatContaVencAlter().toString());
		}
		
		form.setIndicadorControleDividaAtiva("" + sistemaParametro.getIndicadorDividaAtiva());
		
		if (sistemaParametro.getIndicadorEmissaoExtratoNaConsulta() != null) {
			form.setIndicadorEmissaoExtratoDebitoNaConsulta(sistemaParametro
					.getIndicadorEmissaoExtratoNaConsulta().toString());
		}
		
		if (sistemaParametro.getIndicadorIncluirContasCanceladasPagamento() != null) {
			form.setIndicadorInclusaoContasCanceladasBoletim(sistemaParametro
					.getIndicadorIncluirContasCanceladasPagamento().toString());
		}
		
		if (sistemaParametro.getIndicadorIncluirContaEmCobranca() != null) {
			form.setIndicadorIncluirContaEmCobranca(sistemaParametro
					.getIndicadorIncluirContaEmCobranca().toString());
		}
		
		if (sistemaParametro.getNumeroDiasVencimentoCobrancaResultado() != null) {
			form.setNumeroDiasVencimentoCobrancaResultado(sistemaParametro
					.getNumeroDiasVencimentoCobrancaResultado().toString());
		}
		
	}
	
	private void setDadosAba5(SistemaParametro sistemaParametro, InformarSistemaParametrosActionForm form) {
		
		if (sistemaParametro.getIndicadorSugestaoTramite() != null) {
			form.setIndicadorSugestaoTramite(sistemaParametro
					.getIndicadorSugestaoTramite().toString());
		}

		if (sistemaParametro.getDiasReativacao() != null) {
			form.setDiasMaximoReativarRA(sistemaParametro
					.getDiasReativacao().toString());
		}

		if (sistemaParametro.getDiasMaximoAlterarOS() != null) {
			form.setDiasMaximoAlterarOS(sistemaParametro
					.getDiasMaximoAlterarOS().toString());
		}

		if (sistemaParametro.getNumeroDiasEncerramentoOrdemServico() != null) {
			form.setNumeroDiasEncerramentoOrdemServico(sistemaParametro
					.getNumeroDiasEncerramentoOrdemServico().toString());
		}
        
		
		if (sistemaParametro.getNumeroDiasEncerramentoOSSeletiva() != null) {
			form.setNumeroDiasEncerramentoOSSeletiva(sistemaParametro
					.getNumeroDiasEncerramentoOSSeletiva().toString());
		}

		// RM 5759
		if (sistemaParametro.getIndicadorPermiteCancelarDebito() != null) {
			form.setIndicadorPermiteCancelarDebito(sistemaParametro
					.getIndicadorPermiteCancelarDebito().toString());
		}

		// RM 3892
		if (sistemaParametro.getPeriodoRevalidarSenha() != null) {
			form.setPeriodoRevalidarSenha(sistemaParametro
					.getPeriodoRevalidarSenha().toString());
		}
		if (sistemaParametro.getDiasRevalidarSenha() != null) {
			form.setDiasRevalidarSenha(sistemaParametro
					.getDiasRevalidarSenha().toString());
		}

		if (sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null) {
			form.setNumeroDiasAlteracaoVencimentoPosterior(sistemaParametro
					.getNumeroDiasAlteracaoVencimentoPosterior().toString());
		}
		

		if (sistemaParametro.getUltimoRAManual() != null) {
			form.setUltimoIDGeracaoRA(sistemaParametro.getUltimoRAManual()
					.toString());
		}

		if (sistemaParametro.getNumeroDiasExpiracaoAcesso() != null) {
			form.setDiasMaximoExpirarAcesso(sistemaParametro
					.getNumeroDiasExpiracaoAcesso().toString());
		}

		if (sistemaParametro.getNumeroDiasMensagemExpiracao() != null) {
			form.setDiasMensagemExpiracaoSenha(sistemaParametro
					.getNumeroDiasMensagemExpiracao().toString());
		}

		if (sistemaParametro.getNumeroMaximoLoginFalho() != null) {
			form.setNumeroMaximoTentativasAcesso(sistemaParametro
					.getNumeroMaximoLoginFalho().toString());
		}

		if (sistemaParametro.getNumeroMaximoFavorito() != null) {
			form.setNumeroMaximoFavoritosMenu(sistemaParametro
					.getNumeroMaximoFavorito().toString());
		}
		
		if (sistemaParametro.getIpServidorSmtp() != null) {
			form.setIpServidorSmtp(sistemaParametro.getIpServidorSmtp());
		}
		
		if (sistemaParametro.getIpServidorModuloGerencial() != null) {
			form.setIpServidorGerencial(sistemaParametro
					.getIpServidorModuloGerencial());
		}

		if (sistemaParametro.getUrlhelp() != null) {
			form.setUrlHelp(sistemaParametro.getUrlhelp());
		}

		if (sistemaParametro.getDsEmailResponsavel() != null) {
			form.setEmailResponsavel(sistemaParametro
					.getDsEmailResponsavel());
		}

		if (sistemaParametro.getIndicadorTramitarRAEsgoto() != null) {
			form.setIndicadorTramiteEsgoto(sistemaParametro
					.getIndicadorTramitarRAEsgoto().toString());
		}
		
		
		if (sistemaParametro.getIndicadorBloqFuncInstalacaoSubtHidrometro() != null) {
			form.setIndicadorBloqFuncInstalacaoSubtHidrometro(sistemaParametro
					.getIndicadorBloqFuncInstalacaoSubtHidrometro()
					.toString());
		}

		if (sistemaParametro.getIndicadorControleTramitacaoRA() != null) {
			form.setIndicadorControleTramitacaoRA(""
					+ sistemaParametro.getIndicadorControleTramitacaoRA());
		}

		if (sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis() != null) {
			form.setIndicadorCalculoPrevisaoRADiasUteis(""
					+ sistemaParametro
							.getIndicadorCalculoPrevisaoRADiasUteis());
		}

		if (sistemaParametro.getIndicadorDocumentoValido() != null) {
			form.setIndicadorDocumentoValido(""
					+ sistemaParametro.getIndicadorDocumentoValido());
		}

		if (sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS() != null) {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS(""
					+ sistemaParametro
							.getIndicadorValidarLocalizacaoEncerramentoOS());
		} else {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS(""
					+ ConstantesSistema.NAO);
		}
		
		
		if (sistemaParametro.getIndicadorImprimirExtratoSorteio() != null) {
			form.setIndicadorImprimirExtratoSorteio(sistemaParametro.getIndicadorImprimirExtratoSorteio().toString());
		} else {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS(String.valueOf(ConstantesSistema.NAO));
		}

		if (sistemaParametro.getUltimoDiaVencimentoAlternativo() != null) {
			form.setUltimoDiaVencimentoAlternativo(sistemaParametro
					.getUltimoDiaVencimentoAlternativo().toString());
		}
		

		if (sistemaParametro.getQtdeDiasValidadeOSFiscalizacao() != null) {
			form.setQtdeDiasValidadeOSFiscalizacao(sistemaParametro
					.getQtdeDiasValidadeOSFiscalizacao().toString());
		}
		

		if (sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null) {
			form.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro
					.getQtdeDiasEncerraOSFiscalizacao().toString());
		}

		if (sistemaParametro.getQtdeDiasEnvioEmailConta() != null) {
			form.setQtdeDiasEnvioEmailConta(sistemaParametro
					.getQtdeDiasEnvioEmailConta().toString());
		}

		if (sistemaParametro.getDescricaoDecreto() != null) {
			form.setDescricaoDecreto(sistemaParametro.getDescricaoDecreto()
					.toString());
		}

		if (sistemaParametro.getDescricaoLeiEstTarif() != null) {
			form.setDescricaoLeiEstTarif(sistemaParametro
					.getDescricaoLeiEstTarif().toString());
		}

		if (sistemaParametro.getDescricaoLeiIndividualizacao() != null) {
			form.setDescricaoLeiIndividualizacao(sistemaParametro
					.getDescricaoLeiIndividualizacao().toString());
		}

		if (sistemaParametro.getDescricaoNormaCM() != null) {
			form.setDescricaoNormaCM(sistemaParametro.getDescricaoNormaCM()
					.toString());
		}

		if (sistemaParametro.getDescricaoNormaCO() != null) {
			form.setDescricaoNormaCO(sistemaParametro.getDescricaoNormaCO()
					.toString());
		}

		if (sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo() != null) {
			form.setIndicarControleExpiracaoSenhaPorGrupo(sistemaParametro
					.getIndicadorControleExpiracaoSenhaPorGrupo()
					.toString());
		}
		if (sistemaParametro.getIndicadorControleBloqueioSenhaAnterior() != null) {
			form.setIndicarControleBloqueioSenha(sistemaParametro
					.getIndicadorControleBloqueioSenhaAnterior().toString());
		}
		if (sistemaParametro.getIndicadorSenhaForte() != null) {
			form.setIndicadorSenhaForte(sistemaParametro
					.getIndicadorSenhaForte().toString());
		}

		if (sistemaParametro.getNumeroDiasBloqueiaSenha() != null) {
			form.setNumeroDiasBloqueiaSenha(sistemaParametro
					.getNumeroDiasBloqueiaSenha().toString());
		}
		if (sistemaParametro.getDiasAtualizarLigacaoAguaImovelFisc() != null) {
			form.setDiasAtualizarLigacaoAguaImovelFisc(sistemaParametro
					.getDiasAtualizarLigacaoAguaImovelFisc().toString());
		}
		
		if (sistemaParametro.getNumeroLimiteOSCobranca() != null) {
			form.setNumeroLimiteOSCobranca(sistemaParametro
					.getNumeroLimiteOSCobranca().toString());
		}


		// Consumo de água fixado para anormalidade hidrômetro
		if (sistemaParametro.getConsumoAguaFixadoAnormalidadeHidro() != null) {
			form.setConsumoAguaFixadoAnormalidadeHidro(sistemaParametro
					.getConsumoAguaFixadoAnormalidadeHidro().toString());
		}
		// Quantidade de meses da instalação e/ou substituição de hidrômetro
		if (sistemaParametro.getQtdMesIstalSubistituicaoHidro() != null) {
			form.setQtdMesIstalSubistituicaoHidro(sistemaParametro
					.getQtdMesIstalSubistituicaoHidro().toString());
		}
		// Quantidade de vezes da incidência de anormalidade de hidrômetro
		if (sistemaParametro.getQtdVezIncidenciaAnormalidadeHidro() != null) {
			form.setQtdVezIncidenciaAnormalidadeHidro(sistemaParametro
					.getQtdVezIncidenciaAnormalidadeHidro().toString());
		}
		
		/*
		 * UC - 0060
		 * RM - 9021
		 * Numero de Dias para Encerramento Ordem Servico Factivel Faturavel
		 */
		if(sistemaParametro.getNumeroDiasEncerramentoOrdemServicoFactivelFaturavel()!=null){
			form.setNumeroDiasEncerramentoOrdemServicoFactivelFatural(
				sistemaParametro.getNumeroDiasEncerramentoOrdemServicoFactivelFaturavel().toString());
		}
		
		/*
		 * UC - 0060
		 * RM - 9021
		 * Servico da Ordem de Servico seletiva Factivel Faturavel
		 */
		if(sistemaParametro.getServicoTipo()!=null){
			form.setIdServicoTipo(sistemaParametro.getServicoTipo().getId().toString());
		}
		
		form.setIndicadorCertidaoNegativaEfeitoPositivo(""
				+ sistemaParametro
						.getIndicadorCertidaoNegativaEfeitoPositivo());

		form.setIndicadorDebitoACobrarValidoCertidaoNegativa(""
				+ sistemaParametro
						.getIndicadorDebitoACobrarValidoCertidaoNegativa());

		form.setIndicadorLoginUnico(""
				+ sistemaParametro.getIndicadorLoginUnico());

		form.setIndicadorModuloSeguranca(""
				+ sistemaParametro.getIcModulosSeguranca());

		form.setIndicadorPermissaoEspecialGrupo(""
				+ sistemaParametro.getIndicadorPermissaoEspecialGrupo());

		if (sistemaParametro.getNumeroDiasRevisaoComPermEspecial() != null) {
			form.setNumeroDiasRevisaoConta(sistemaParametro
					.getNumeroDiasRevisaoComPermEspecial().toString());
		}

		if (sistemaParametro
				.getUnidadeOrganizacionalTramiteGrandeConsumidor() != null) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							sistemaParametro
									.getUnidadeOrganizacionalTramiteGrandeConsumidor()
									.getId()));

			Collection<UnidadeOrganizacional> colecao = this.getFachada()
					.pesquisar(filtroUnidadeOrganizacional,
							UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecao);

			form.setIdUnidadeDestinoGrandeConsumidor(unidade.getId()
					.toString());
			form.setNomeUnidadeDestinoGrandeConsumidor(unidade
					.getDescricao());
		}
		
	}
	
}