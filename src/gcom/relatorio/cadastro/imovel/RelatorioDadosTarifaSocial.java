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
package gcom.relatorio.cadastro.imovel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioDadosTarifaSocial extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioDadosTarifaSocial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_TARIFA_SOCIAL);
	}
	
	@Deprecated
	public RelatorioDadosTarifaSocial() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection imoveisRelatoriosHelper = (Collection) getParametro("imoveisRelatoriosHelper");
		Imovel imovelParametrosInicial = (Imovel) getParametro("imovelParametrosInicial");
		Imovel imovelParametrosFinal = (Imovel) getParametro("imovelParametrosFinal");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		MedicaoHistorico medicaoHistoricoParametrosInicial = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosInicial");
		MedicaoHistorico medicaoHistoricoParametrosFinal = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosFinal");
		ConsumoHistorico consumoHistoricoParametrosInicial = (ConsumoHistorico) getParametro("consumoHistoricoParametrosInicial");
		ConsumoHistorico consumoHistoricoParametrosFinal = (ConsumoHistorico) getParametro("consumoHistoricoParametrosFinal");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Categoria categoria = (Categoria) getParametro("categoria");
		Subcategoria subcategoria = (Subcategoria) getParametro("subcategoria");
		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) getParametro("cobrancaSituacao");
		Short indicadorMedicao = (Short) getParametro("indicadorMedicao");
		Short indicadorSituacaoImovelTarifaSocial = (Short) getParametro("indicadorSituacaoImovelTarifaSocial");
		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaInicial = (TarifaSocialDadoEconomia) getParametro("tarifaSocialDadoEconomiaInicial");
		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaFinal = (TarifaSocialDadoEconomia) getParametro("tarifaSocialDadoEconomiaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioDadosTarifaSocialBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper imovelRelatorioHelper = (ImovelRelatorioHelper) imovelRelatorioIterator
						.next();

				ImovelRelatorioHelper cliente = null;
				ImovelRelatorioHelper clienteUsuario = null;
				ImovelRelatorioHelper clienteProprietario = null;

				if (imovelRelatorioHelper.getClientes() != null
						&& !imovelRelatorioHelper.getClientes().isEmpty()) {

					// O Cliente Imovel foi encontrado
					Iterator clienteImovelIterator = imovelRelatorioHelper
							.getClientes().iterator();

					while (clienteImovelIterator.hasNext()) {
						cliente = (ImovelRelatorioHelper) clienteImovelIterator
								.next();

						// Trazer o cliente usuário do imóvel
						if (cliente.getClienteRelacaoTipo().equalsIgnoreCase(
								"USUARIO")) {
							clienteUsuario = cliente;
						}

						// Trazer o cliente responsável do imóvel
						if (cliente.getClienteRelacaoTipo().equalsIgnoreCase(
								"PROPRIETARIO")) {
							clienteProprietario = cliente;
						}
					}

				}

				// Início da Construção do objeto
				// RelatorioDadosTarifaSocialBean
				// para ser colocado no relatório
				relatorioBean = new RelatorioDadosTarifaSocialBean(

						// Código da Gerência Regional
						imovelRelatorioHelper.getIdGerenciaRegional() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdGerenciaRegional(),

						// Descrição da Gerência Regional
						imovelRelatorioHelper.getDescricaoGerenciaRegional() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoGerenciaRegional(),

						// Código da Localidade
						imovelRelatorioHelper.getIdLocalidade() == null ? ""
								: "" + imovelRelatorioHelper.getIdLocalidade(),

						// Descrição da Localidade
						imovelRelatorioHelper.getDescricaoLocalidade() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoLocalidade(),

						// Código do Setor Comercial
						imovelRelatorioHelper.getCodigoSetorComercial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getCodigoSetorComercial(),

						// Descrição do Setor Comercial
						imovelRelatorioHelper.getDescricaoSetorComercial() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoSetorComercial(),

						// Matrícula do Imóvel
						"" + imovelRelatorioHelper.getMatriculaImovel(),

						// Endereço
						imovelRelatorioHelper.getEnderecoFormatado(),

						// Nome do Cliente Proprietario
						clienteProprietario == null ? "" : clienteProprietario
								.getClienteNome(),

						// Cpf do Cliente Proprietario
						clienteProprietario == null ? "" : ""
								+ clienteProprietario.getClienteCpf(),

						// Nome do Cliente Usuário
						clienteUsuario == null ? "" : clienteUsuario
								.getClienteNome(),

						// Cpf do Cliente Usuário
						clienteUsuario == null ? "" : ""
								+ clienteUsuario.getClienteCpf(),

						// Data da Implantação
						imovelRelatorioHelper.getDataImplantacao(),

						// Data da Exclusão
						imovelRelatorioHelper.getDataExclusao(),

						// Motivo da Exclusão
						imovelRelatorioHelper.getMotivoExclusao() == null ? ""
								: imovelRelatorioHelper.getMotivoExclusao(),

						// Data do Último Recadastramento
						imovelRelatorioHelper.getUltimoRecadastramento(),

						// Número de Recadastramentos
						imovelRelatorioHelper.getNumeroRecadastramento() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroRecadastramento(),

						// Área Construída
						imovelRelatorioHelper.getAreaConstruida() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getAreaConstruida(),

						// Número do IPTU
						imovelRelatorioHelper.getNumeroIptu() == null ? "" : ""
								+ imovelRelatorioHelper.getNumeroIptu()
										.toString(),

						// Número do Contrato da Celpe
						imovelRelatorioHelper.getNumeroCelpe() == null ? ""
								: "" + imovelRelatorioHelper.getNumeroCelpe(),

						// Número do Cartão do Programa Social
						imovelRelatorioHelper.getNumeroCartaoTarifaSocial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroCartaoTarifaSocial(),

						// Tipo do Cartão do Programa Social
						imovelRelatorioHelper.getTipoCartaoTarifaSocial() == null ? ""
								: imovelRelatorioHelper
										.getTipoCartaoTarifaSocial(),

						// Data Validade do Cartão do Programa Social
						imovelRelatorioHelper.getValidadeCartao(),

						// Número de Meses de Adesão do Cartão do Programa
						// Social
						imovelRelatorioHelper.getNumeroMesesAdesao() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroMesesAdesao(),

						// Consumo Médio da Celpe
						imovelRelatorioHelper.getConsumoCelpe() == null ? ""
								: "" + imovelRelatorioHelper.getConsumoCelpe(),

						// Valor da Renda Familiar
						imovelRelatorioHelper.getValorRendaFamiliar() == null ? ""
								: imovelRelatorioHelper.getValorRendaFamiliar()
										.toString(),

						// Tipo da Renda Familiar
						imovelRelatorioHelper.getRendaTipo() == null ? ""
								: imovelRelatorioHelper.getRendaTipo());

				// Fim da Construção do objeto RelatorioManterImovelBean
				// para ser colocado no relatório

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		String dataImplantacaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial
				.getDataImplantacao() != null
				&& !tarifaSocialDadoEconomiaInicial
						.getDataImplantacao().equals("")) {
			dataImplantacaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getDataImplantacao());
		}

		String dataImplantacaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal
				.getDataImplantacao() != null
				&& !tarifaSocialDadoEconomiaFinal
						.getDataImplantacao().equals("")) {
			dataImplantacaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal
							.getDataImplantacao());
		}

		String dataExclusaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial
				.getDataExclusao() != null
				&& !tarifaSocialDadoEconomiaInicial
						.getDataExclusao().equals("")) {
			dataExclusaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getDataExclusao());
		}

		String dataExclusaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal
				.getDataExclusao() != null
				&& !tarifaSocialDadoEconomiaFinal
						.getDataExclusao().equals("")) {
			dataExclusaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal
							.getDataExclusao());
		}

		String dataValidadeCartaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial.getDataValidadeCartao() != null
				&& !tarifaSocialDadoEconomiaInicial.getDataValidadeCartao()
						.equals("")) {
			dataValidadeCartaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getDataValidadeCartao());
		}

		String dataValidadeCartaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal.getDataValidadeCartao() != null
				&& !tarifaSocialDadoEconomiaFinal.getDataValidadeCartao()
						.equals("")) {
			dataValidadeCartaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal
							.getDataValidadeCartao());
		}

		String dataRecadastramentoInicial = null;
		if (tarifaSocialDadoEconomiaInicial
				.getDataRecadastramento() != null
				&& !tarifaSocialDadoEconomiaInicial
						.getDataRecadastramento().equals("")) {
			dataRecadastramentoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getDataRecadastramento());
		}

		String dataRecadastramentoFinal = null;
		if (tarifaSocialDadoEconomiaFinal
				.getDataRecadastramento() != null
				&& !tarifaSocialDadoEconomiaFinal
						.getDataRecadastramento().equals("")) {
			dataRecadastramentoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal
							.getDataRecadastramento());
		}

		parametros.put("gerenciaRegional", gerenciaRegional.getNomeAbreviado());
		parametros.put("idLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosInicial.getLocalidade().getId());
		parametros.put("idLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosFinal.getLocalidade().getId());
		parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getDescricao());
		parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getDescricao());
		parametros.put("idSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosInicial.getSetorComercial().getCodigo());
		parametros.put("idSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosFinal.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getDescricao());
		parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadraOrigem", imovelParametrosInicial
				.getQuadra().getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosInicial.getQuadra().getNumeroQuadra());
		parametros.put("numeroQuadraDestino", imovelParametrosFinal.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosFinal.getQuadra().getNumeroQuadra());
		parametros.put("loteOrigem",
				imovelParametrosInicial.getLote() == 0 ? "" : ""
						+ imovelParametrosInicial.getLote());
		parametros.put("loteDestino", imovelParametrosFinal.getLote() == 0 ? ""
				: "" + imovelParametrosFinal.getLote());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		
		if(imovelParametrosInicial.getLogradouroCep() != null 
				&& (imovelParametrosInicial.getLogradouroCep().getCep().getCodigo() != null && 
						!imovelParametrosInicial.getLogradouroCep().getCep().getCodigo().equals(""))){
			parametros.put("cep",imovelParametrosInicial.getLogradouroCep().getCep().getCodigo());
		}else{
			parametros.put("cep","");
		}
		
		if(imovelParametrosInicial.getLogradouroCep() != null && (imovelParametrosInicial.getLogradouroCep().getLogradouro()
				.getId() != null && !imovelParametrosInicial.getLogradouroCep().getLogradouro()
				.getId().equals(""))){
			parametros.put("idLogradouro",imovelParametrosInicial.getLogradouroCep().getLogradouro()
				.getId());
		}else{
			parametros.put("idLogradouro","");
		}
		
		if(imovelParametrosInicial
				.getLogradouroCep() != null && (imovelParametrosInicial
						.getLogradouroCep().getLogradouro().getNome() != null &&
						!imovelParametrosInicial
						.getLogradouroCep().getLogradouro().getNome().equals(""))){
			parametros.put("nomeLogradouro",imovelParametrosInicial
				.getLogradouroCep().getLogradouro().getNome());
		}else{
			parametros.put("nomeLogradouro","");
		}
		
		parametros.put("idCliente", clienteImovelParametros.getCliente()
				.getId() == null ? "" : ""
				+ clienteImovelParametros.getCliente().getId());
		parametros.put("nomeCliente", clienteImovelParametros.getCliente()
				.getNome());
		parametros.put("tipoRelacao", clienteImovelParametros
				.getClienteRelacaoTipo().getDescricao());
		parametros.put("tipoCliente", clienteImovelParametros.getCliente()
				.getClienteTipo().getDescricao());
		parametros.put("imovelCondominio", imovelParametrosInicial
				.getImovelCondominio().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelCondominio().getId());
		parametros.put("imovelPrincipal", imovelParametrosInicial
				.getImovelPrincipal().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelPrincipal().getId());
//		parametros.put("nomeConta", imovelParametrosInicial.getNomeConta()
//				.getNomeConta());
		parametros.put("situacaoLigacaoAgua", imovelParametrosInicial
				.getLigacaoAguaSituacao().getDescricao());
		parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
				.getLigacaoEsgotoSituacao().getDescricao());
		parametros.put("consumoMinimoFixadoAguaInicial",
				imovelParametrosInicial.getLigacaoAgua()
						.getNumeroConsumoMinimoAgua() == null ? "" : ""
						+ imovelParametrosInicial.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
				.getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? ""
				: ""
						+ imovelParametrosFinal.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("percentualEsgotoInicial", imovelParametrosInicial
				.getLigacaoEsgoto().getPercentual() == null ? ""
				: imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros.put("percentualEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getPercentual() == null ? ""
				: imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros
				.put("consumoMinimoFixadoEsgotoInicial",
						imovelParametrosInicial.getLigacaoEsgoto()
								.getConsumoMinimo() == null ? 0 : ""
								+ imovelParametrosInicial.getLigacaoEsgoto()
										.getConsumoMinimo());
		parametros.put("consumoMinimoFixadoEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getConsumoMinimo() == null ? 0 : ""
				+ imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		parametros.put("indicadorMedicao", indicadorMedicao == null ? ""
				: indicadorMedicao.equals(new Short("0")) ? "SEM MEDIÇÃO"
						: "COM MEDIÇÃO");
		parametros.put("tipoMedicao", medicaoHistoricoParametrosInicial
				.getMedicaoTipo().getDescricao());
		parametros
				.put(
						"mediaMinimaImovelInicial",
						consumoHistoricoParametrosInicial.getConsumoMedio() == null ? ""
								: ""
										+ consumoHistoricoParametrosInicial
												.getConsumoMedio());
		parametros
				.put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
						.getConsumoMedio() == null ? "" : ""
						+ consumoHistoricoParametrosFinal.getConsumoMedio());
		parametros
				.put("mediaMinimaHidrometroInicial",
						medicaoHistoricoParametrosInicial
								.getConsumoMedioHidrometro() == null ? ""
								: ""
										+ medicaoHistoricoParametrosInicial
												.getConsumoMedioHidrometro());
		parametros
				.put("mediaMinimaHidrometroFinal",
						medicaoHistoricoParametrosFinal
								.getConsumoMedioHidrometro() == null ? ""
								: ""
										+ medicaoHistoricoParametrosFinal
												.getConsumoMedioHidrometro());
		parametros.put("perfilImovel", imovelParametrosInicial
				.getImovelPerfil().getDescricao());
		parametros.put("categoria", categoria.getDescricao());
		parametros.put("subCategoria", subcategoria.getDescricao());
		parametros.put("qtdeEconomiaInicial", imovelParametrosInicial
				.getQuantidadeEconomias() == null ? "" : ""
				+ imovelParametrosInicial.getQuantidadeEconomias());
		parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
				.getQuantidadeEconomias() == null ? "" : ""
				+ imovelParametrosFinal.getQuantidadeEconomias());
		parametros.put("numeroPontosInicial", imovelParametrosInicial
				.getNumeroPontosUtilizacao() == 0 ? 0 : ""
				+ imovelParametrosInicial.getNumeroPontosUtilizacao());
		parametros.put("numeroPontosFinal", imovelParametrosFinal
				.getNumeroPontosUtilizacao() == 0 ? 0 : ""
				+ imovelParametrosFinal.getNumeroPontosUtilizacao());
		parametros.put("numeroMoradoresInicial", imovelParametrosInicial
				.getNumeroMorador() == 0 ? 0 : ""
				+ imovelParametrosInicial.getNumeroMorador());
		parametros.put("numeroMoradoresFinal", imovelParametrosFinal
				.getNumeroMorador() == 0 ? 0 : ""
				+ imovelParametrosFinal.getNumeroMorador());
		parametros.put("areaConstruidaInicial", imovelParametrosInicial
				.getAreaConstruida().equals(new Short("0")) ? 0 : ""
				+ imovelParametrosInicial.getAreaConstruida());
		parametros.put("areaConstruidaFinal", imovelParametrosFinal
				.getAreaConstruida().equals(new Short("0")) ? 0 : ""
				+ imovelParametrosFinal.getAreaConstruida());
		parametros.put("tipoPoco", imovelParametrosInicial.getPocoTipo()
				.getDescricao());
		parametros.put("tipoSituacaoEspecialFaturamento",
				imovelParametrosInicial.getFaturamentoSituacaoTipo()
						.getDescricao());
		parametros.put("tipoSituacaoEspecialCobranca", imovelParametrosInicial
				.getCobrancaSituacaoTipo().getDescricao());
		parametros.put("situacaoCobranca", cobrancaSituacao == null ? ""
				: cobrancaSituacao.getDescricao());
		parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
				.getDiaVencimento() == null ? "" : ""
				+ imovelParametrosInicial.getDiaVencimento());
		parametros.put("anormalidadeElo", imovelParametrosInicial
				.getEloAnormalidade() == null ? "" : imovelParametrosInicial
				.getEloAnormalidade().getDescricao());
		parametros.put("ocorrenciaCadastro", imovelParametrosInicial
				.getCadastroOcorrencia().getDescricao());
		parametros.put("tarifaConsumo", imovelParametrosInicial
				.getConsumoTarifa().getDescricao());
		parametros.put("indicadorSituacaoImovelTarifaSocial",
				indicadorSituacaoImovelTarifaSocial == null ? ""
						: indicadorSituacaoImovelTarifaSocial == 1 ? "Ativo"
								: "Excluído");
		parametros.put("dataImplantacaoInicial", dataImplantacaoInicial);
		parametros.put("dataImplantacaoFinal", dataImplantacaoFinal);
		parametros.put("dataExclusaoInicial", dataExclusaoInicial);
		parametros.put("dataExclusaoFinal", dataExclusaoFinal);
		parametros.put("dataValidadeCartaoInicial", dataValidadeCartaoInicial);
		parametros.put("dataValidadeCartaoFinal", dataValidadeCartaoFinal);
		parametros
				.put("motivoExclusao",
						tarifaSocialDadoEconomiaInicial
								.getTarifaSocialExclusaoMotivo() == null ? ""
								: tarifaSocialDadoEconomiaInicial										
										.getTarifaSocialExclusaoMotivo()
										.getDescricao());
		parametros
				.put(
						"numeroMesesAdesaoInicial",
						tarifaSocialDadoEconomiaInicial.getNumeroMesesAdesao() == 0 ? ""
								: ""
										+ tarifaSocialDadoEconomiaInicial
												.getNumeroMesesAdesao());
		parametros.put("numeroMesesAdesaoFinal", tarifaSocialDadoEconomiaFinal
				.getNumeroMesesAdesao() == 0 ? "" : ""
				+ tarifaSocialDadoEconomiaFinal.getNumeroMesesAdesao());
		
		if(tarifaSocialDadoEconomiaInicial.getTarifaSocialCartaoTipo() != null &&
				tarifaSocialDadoEconomiaInicial.getTarifaSocialCartaoTipo().getDescricao() != null){
			parametros.put("tipoCartao", tarifaSocialDadoEconomiaInicial
				.getTarifaSocialCartaoTipo().getDescricao());			
		}else{
			parametros.put("tipoCartao","");
		}
		
		if(tarifaSocialDadoEconomiaInicial.getRendaTipo() != null && 
				tarifaSocialDadoEconomiaInicial.getRendaTipo().getDescricao() != null){
		
			parametros
			.put("tipoRenda", tarifaSocialDadoEconomiaInicial
					.getRendaTipo().getDescricao());
		}else{
			parametros
			.put("tipoRenda", "");
		}		
		
		
		parametros.put("rendaFamiliarInicial", tarifaSocialDadoEconomiaInicial
				.getValorRendaFamiliar() == null ? ""
				: tarifaSocialDadoEconomiaInicial.getValorRendaFamiliar()
						.toString());
		parametros.put("rendaFamiliarFinal", tarifaSocialDadoEconomiaFinal
				.getValorRendaFamiliar() == null ? ""
				: tarifaSocialDadoEconomiaFinal.getValorRendaFamiliar()
						.toString());
		parametros.put("consumoCelpeInicial", tarifaSocialDadoEconomiaInicial
				.getConsumoCelpe() == null ? ""
				: tarifaSocialDadoEconomiaInicial.getConsumoCelpe().toString());
		parametros.put("consumoCelpeFinal", tarifaSocialDadoEconomiaFinal
				.getConsumoCelpe() == null ? ""
				: tarifaSocialDadoEconomiaFinal.getConsumoCelpe().toString());
		parametros
				.put("dataRecadastramentoInicial", dataRecadastramentoInicial);
		parametros.put("dataRecadastramentoFinal", dataRecadastramentoFinal);
		parametros.put("numeroRecadastramentoInicial",
				tarifaSocialDadoEconomiaInicial
						.getQuantidadeRecadastramento() == null ? "" : ""
						+ tarifaSocialDadoEconomiaInicial
								.getQuantidadeRecadastramento());
		parametros.put("numeroRecadastramentoFinal",
				tarifaSocialDadoEconomiaFinal
						.getQuantidadeRecadastramento() == null ? "" : ""
						+ tarifaSocialDadoEconomiaFinal
								.getQuantidadeRecadastramento());
		parametros.put("tipoFormatoRelatorio", "R0162");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DADOS_TARIFA_SOCIAL, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.DADOS_TARIFA_SOCIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
		
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosTarifaSocial", this);
	}

}
