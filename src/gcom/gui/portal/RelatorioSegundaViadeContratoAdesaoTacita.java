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
package gcom.gui.portal;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1278] Gerar Segunda Via de Contrato Adesão Tácita 
 * 
 * @author Arthur Carvalho
 * @created 06/02/2012
 */
public class RelatorioSegundaViadeContratoAdesaoTacita extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioSegundaViadeContratoAdesaoTacita(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SEGUNDA_VIA_CONTRATO_ADESAO_TACITA);
	}

	@Deprecated
	public RelatorioSegundaViadeContratoAdesaoTacita() {
		super(null, "");
	}

	
	
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		String nomeRelatorio = ConstantesRelatorios.RELATORIO_SEGUNDA_VIA_CONTRATO_ADESAO_TACITA;
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		Fachada fachada = Fachada.getInstancia();
		
		Integer idImovel = (Integer) getParametro("matricula");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		
		Collection<ClienteImovel> colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		
		ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
		
		
		String imovelInscricao = fachada.pesquisarInscricaoImovel(idImovel);
		String nomeCliente = clienteImovel.getCliente().getNome();
		String endereco = "";
		String dataInicioVinculoClienteImovel = Util.formatarData(clienteImovel.getDataInicioRelacao());
		String dataEmissao = Util.formatarData(new Date());
		String cpfCnpj = "";
		String rg = "";
		if ( clienteImovel.getCliente().getCpf() != null && !clienteImovel.getCliente().getCpf().equals("") ) {
			cpfCnpj = clienteImovel.getCliente().getCpfFormatado();
			if ( clienteImovel.getCliente().getRg() != null ) {
				rg = clienteImovel.getCliente().getRg();
			}
		} else if ( clienteImovel.getCliente().getCnpj() != null && !clienteImovel.getCliente().getCnpj().equals("") ) {
			cpfCnpj = clienteImovel.getCliente().getCnpjFormatado();
		}
		
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		
		Cep cep = fachada.pesquisarCepImovel(imovel);
		Municipio municipio = fachada.pesquisarMunicipioImovel(idImovel);
		try {
			endereco = fachada.pesquisarEnderecoFormatado(idImovel);
		} catch (ControladorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		parametros.put("imagem", "./imagens/compesa_logo_relatorio.png");
		parametros.put("matriculaImovel", idImovel.toString());
		parametros.put("imovelInscricao", imovelInscricao);
		parametros.put("clienteNome", nomeCliente);
		parametros.put("endereco", endereco);
		parametros.put("dataVinculoClienteImovel", dataInicioVinculoClienteImovel);
		parametros.put("dataEmissao", dataEmissao);
		parametros.put("cep", cep.getCepFormatado());
		parametros.put("cpfCnpj", cpfCnpj);
		parametros.put("rg", rg);
		parametros.put("cidade", municipio.getNome());
		
		FiltroCliente filtroCliente = new FiltroCliente();
		Collection<Integer> colecaoIds = new ArrayList<Integer>();
		colecaoIds.add(sistemaParametro.getClientePresidenteCompesa().getId());
		colecaoIds.add(sistemaParametro.getClienteDiretorComercialCompesa().getId());
		colecaoIds.add(sistemaParametro.getClienteDiretorGestao().getId());
		
		filtroCliente.adicionarParametro( new ParametroSimplesIn(FiltroCliente.ID, colecaoIds));
		String diretorPresidente = "";
		String diretorComercial = "";
		String diretorGestao = "";
		
		Collection<Cliente> colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		Iterator iteratorCliente = colecaoCliente.iterator();
		while(iteratorCliente.hasNext()) {
			Cliente cliente = (Cliente) iteratorCliente.next();
			
			if (cliente.getId().toString().equals(sistemaParametro.getClienteDiretorComercialCompesa().getId().toString())) {
				diretorComercial = diretorComercial + cliente.getNome();
			} else if (cliente.getId().toString().equals(sistemaParametro.getClientePresidenteCompesa().getId().toString())) {
				diretorPresidente = diretorPresidente + cliente.getNome();
			} else if (cliente.getId().toString().equals(sistemaParametro.getClienteDiretorGestao().getId().toString())) {
				diretorGestao = diretorGestao + cliente.getNome();
			}
		}
		
		
		String primeiroPagina1 = 
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CONTRATO DE PRESTAÇÃO DE SERVIÇOS PÚBLICOS DE ABASTECIMENTO DE ÁGUA E/OU ESGOTAMENTO SANITÁRIO POR ADESÃO TÁCITA.</style>"+
					"\n"+
					"\n"+
					"\n"+
				"Pelo presente instrumento particular, de um lado, a <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPANHIA PERNAMBUCANA DE SANEAMENTO - COMPESA</style>, "+
				"sociedade de economia mista estadual por ações, delegatária de serviço público, inscrita no Cadastro Nacional "+
				"de Pessoas Jurídicas do Ministério da Fazenda - CNPJ sob Nº 09.769.035/0001-64, com sede na Av. Cruz Cabugá, nº 1.387, bairro de Santo Amaro, CEP 50.040-905, "+
				"nesta cidade do Recife, Estado de Pernambuco, doravante designada <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, e, de outro lado, o <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">proprietário e/ou usuário ocupante do imóvel, "+
				"responsável pela unidade receptora dos serviços prestados</style>, com inscrição, matrícula, nome, endereço, "+
				"CPF/CNPJ e RG descritos na primeira página deste documento, doravante designado <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>,  têm entre si justo e acordado celebrar este Contrato de Prestação de Serviços "+
				"Públicos de Abastecimento de Água e/ou Esgotamento Sanitário, a título de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">'Contrato de Adesão Tácita'</style>." +
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA BASE LEGAL</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA PRIMEIRA -</style> A prestação do serviço público de fornecimento de água e coleta de esgoto caracteriza negócio jurídico de natureza contratual e"+
				" é regido especialmente pela Lei Federal nº 11.445/2007, que estabelece as Diretrizes Nacionais e Política "+
				"Federal do Saneamento Básico, pela Lei Federal 8.078/90 que aprova o Código de Defesa do Consumidor e pelo Decreto Estadual nº 18.251/1994, que aprova o Regulamento Geral do Fornecimento de Água e Coleta de Esgotos."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA TERMINOLOGIA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA SEGUNDA -</style> Para os fins e efeitos deste contrato são adotadas as seguintes definições:"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Caixa de Inspeção -</style> caixa situada na calçada da via pública, que possibilita a inspeção e desobstrução do ramal predial de esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Categoria -</style> classificação dada ao imóvel cadastrado na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> de acordo com a natureza da ocupação de suas economias que são RESIDENCIAL, COMERCIAL, INDUSTRIAL e PÚBLICA."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Cliente -</style> pessoa física ou jurídica que solicita à COMPESA a prestação dos serviços de abastecimento de água e/ou esgotamento sanitário para a unidade receptora, responsabilizando-se pelas obrigações fixadas em regulamento que dispõem sobre a prestação desses serviços."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Economia -</style> todo imóvel ou subdivisão de um imóvel considerado ocupável com entrada própria independente das demais, razão social distinta e com instalações para o abastecimento de água e coleta de esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Fatura -</style> documento hábil para cobrança e pagamento correspondente à prestação de serviços contraídos pelo <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Fonte própria de abastecimento de água -</style> abastecimento de água de um imóvel não proveniente do sistema de abastecimento de água operado pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Hidrômetro - </style>equipamento instalado no ramal predial destinado a medir e indicar, continuamente, o volume de água que o atravessa."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Instalação predial de água - </style>conjunto de tubulações, conexões, aparelhos, equipamentos e peças especiais localizados dentro do imóvel até o hidrômetro ou a torneira de passagem.";
		
		
		String segundoPagina1 = "\n"+"\n"+"\n"+"\n"+"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Instalação predial de esgoto -</style> conjunto de tubulações, conexões, equipamentos e peças especiais localizados dentro do imóvel até a caixa de inspeção."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Interrupção do fornecimento -</style> suspensão temporária dos serviços de abastecimento de água, pela COMPESA, nos casos determinados no Regulamento Geral do Fornecimento de Água e Coleta de Esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Lacre - </style>dispositivo destinado a caracterizar a inviolabilidade do hidrômetro ou da interrupção do fornecimento."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Ramal predial de água -</style> conjunto de tubulações e peças especiais situadas entre a rede de distribuição de água e o hidrômetro ou a torneira de passagem."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Ramal predial de esgoto -</style> conjunto de tubulações e peças especiais situadas entre a rede coletora de esgotos e a caixa de inspeção."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Sistema público de abastecimento de água -</style> conjunto de canalizações, estação de tratamento, reservatórios, elevatórias, equipamentos e demais instalações, que tem por finalidade captar, aduzir, tratar, reservar e distribuir água."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Sistema público de esgotos sanitários -</style> conjunto de canalizações, estações de tratamento, elevatórias, equipamentos e demais instalações destinadas a coletar, transportar e dispor adequadamente os esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Supressão do ramal predial -</style> interrupção do fornecimento de água ou coleta de esgoto ao imóvel, com a retirada de todo ramal predial, nos casos determinados no Regulamento Geral do Fornecimento de Água e Coleta de Esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Tarifa -</style> valor fixado em moeda corrente, utilizado pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, referente à cobrança dos serviços públicos de abastecimento de água e/ou esgotamento sanitário."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Unidade receptora -</style> é o imóvel que recebe da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> a prestação dos serviços de abastecimento de água e/ou esgotamento sanitário."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO OBJETO</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA TERCEIRA -</style> Constitui objeto do presente contrato a prestação do serviço público de abastecimento de água e/ou de esgotamento sanitário à unidade receptora a pedido, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">com ônus e sob a responsabilidade do CLIENTE</style>."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA ABRANGÊNCIA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA QUARTA -</style> Este contrato aplica-se a todas as categorias de clientes contemplados com os serviços de abastecimento de água e/ou de esgotamento sanitário quais sejam:  <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Residencial,Comercial, Industrial e Público</style> ."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS RAMAIS PREDIAIS DE ÁGUA E DE ESGOTO</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA QUINTA -</style> Os ramais prediais de água e de esgotos serão implantados pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, à custa do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>, satisfeitas as exigências estabelecidas em normas e instrumentos regulamentares."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">PARÁGRAFO ÚNICO -</style> Os ramais prediais de água e/ou de esgotos, após suas execuções, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">passarão a integrar o patrimônio da COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA SEXTA -</style> A manutenção dos ramais prediais é de responsabilidade exclusiva da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA SÉTIMA -</style> O remanejamento ou ampliação do diâmetro do ramal predial por conveniência do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>, de" ;
				
			String primeiroPagina2=	"acordo com as normas da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA, serão executados às expensas do cliente</style>."+
					"\n"+
					"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS DIREITOS DO CLIENTE</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA OITAVA -</style> São direitos do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a)</style> receber abastecimento de água tratada no imóvel nos padrões de qualidade exigidos pela Portaria nº 518/2004, de 25 de março de 2004, do Ministério da Saúde;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b)</style> dispor de manutenção e assistência técnica nas instalações dos ramais prediais de água e/ou de esgotos;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c)</style> ser atendido com eficiência, rapidez e cortesia;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d)</style> ser orientado sobre a importância e o uso eficiente dos serviços prestados, de modo a reduzir desperdícios e garantir a segurança na sua utilização"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e)</style> escolher uma entre pelos menos 6 (seis) datas disponibilizadas pela COMPESA para o vencimento da fatura, ressalvando-se que somente poderá ser alterada depois de decorrido o período de 01 (um) ano da escolha;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f)</style> receber a fatura com antecedência mínima de 5 (cinco) dias úteis da data do vencimento; "+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g)</style> ser informado, na fatura, sobre o percentual de reajuste tarifário e a data de início de sua vigência, bem como sobre a qualidade da água e a existência de débitos para com a COMPESA;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h)</style> estar à sua disposição serviço de atendimento telefônico e eletrônico para atendimento usual e de emergência;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i)</style> dispor do serviço de endereço alternativo para o recebimento da fatura;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j)</style> ser informado sobre a ocorrência de interrupções programadas, por meio de jornais, rádio, televisão, 'site' da COMPESA ou qualquer outro meio de comunicação;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">l)</style> ter, para fins de consulta, nos locais de atendimento, acesso às Normas, Estrutura Tarifária, Tabela de Preços e Serviços e ao Regulamento Geral do Fornecimento de Água e Coleta de Esgotos da COMPESA, todos vigentes."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS OBRIGAÇÕES DO CLIENTE</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA NONA - São obrigações do CLIENTE:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) pagar a fatura mensal do fornecimento de água e/ou coleta de esgotos e outros serviços, até a data do vencimento, sujeitando-se em caso de atraso no pagamento da fatura e após a comunicação formal pela COMPESA, às ações de cobrança a ser legalmente por ela praticadas, inclusive a negativação de créditos junto aos órgãos competentes (SPC e SERASA);</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) informar qualquer alteração da atividade exercida no imóvel que possa resultar em mudança de categoria ou do número de economias, com o intuito de manter os dados cadastrais atualizados e para fins de tarifação adequada pela COMPESA.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) manter os dados cadastrais atualizados junto à COMPESA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) zelar pelas instalações dos ramais prediais de água e/ou de esgoto, de forma a evitar quaisquer tipos de danos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) responder, no caso de hidrômetro instalado no interior do imóvel, pela guarda, proteção e danos causados ao mesmo, sendo permanentemente proibida a instalação, reparação, substituição ou remoção do aparelho à revelia da COMPESA;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f) informar à COMPESA, mediante apresentação de documento comprobatório, a transferência de titularidade quanto à responsabilidade pelos serviços prestados à unidade receptora, sob pena de assumir todas as obrigações decorrentes deste contrato, inclusive os débitos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g) assegurar o livre acesso à entrada de empregados e representantes da COMPESA, para fins de inspeção e/ou leitura do hidrômetro instalado;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h) informar à COMPESA sobre a utilização no imóvel de fonte própria de abastecimento de água (poço artesiano) ou outra fonte de abastecimento (carro-pipa);</style>";
			
			String segundoPagina2 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i) tornar independente do ramal predial da COMPESA a instalação e o reservatório da fonte própria de água, com o intuito de não misturar a água tratada com a água proveniente da fonte própria;</style>"+
					"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j) pagar a fatura de esgoto do imóvel contemplado com a rede pública de esgotamento sanitário, mesmo que o imóvel tenha outra fonte de água que não seja a pública;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">l) informar à COMPESA, mediante laudo médico, a existência de pessoa no imóvel que use, em tratamento especial, equipamentos que dependam da água;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">m) reservar e manter a qualidade da água nas instalações prediais sob sua responsabilidade.</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS TARIFAS</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA  DÉCIMA -</style>  A  estrutura tarifária da COMPESA representa a distribuição de tarifas por faixa de consumo e volume esgotado, de forma a compatibilizar os aspectos econômico com os objetivos sociais, observando o disposto nos artigos 48 a 70, do Regulamento."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) nas unidades com hidrômetro, o volume consumido será obtido pela diferença entre a leitura atual e a anterior. Não sendo possível em determinado momento a realização da leitura, a apuração será feita com base na média aritmética dos consumos faturados nos últimos 6 (seis) meses.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) enquanto não implantado em definitivo o hidrômetro, o consumo será fixado por estimativa em função do consumo médio presumido, com base em atributos físicos do imóvel ou em medição temporária.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) as tarifas de esgoto serão fixadas entre 30% e 100% das tarifas de água, em função da origem e natureza dos investimentos necessários à implantação, operação e manutenção dos serviços</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO PAGAMENTO DAS FATURAS</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA PRIMEIRA -</style> Consoante o art. 71, do Regulamento Geral do Fornecimento de Água e Coleta de Esgotos, aprovado pelo Decreto Estadual nº 18.251/1994 e com a nova redação dada pelo Decreto Estadual nº 30.774/2007, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">respondem solidariamente pelos débitos relativos ao fornecimento de água, coleta de esgoto e outros serviços, o proprietário e o usuário ocupante do imóvel, podendo ser inscrito, um ou outro, nos serviços de proteção ao crédito, no caso de inadimplência.</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS INFRAÇÕES E PENALIDADES</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA SEGUNDA - Constitui infração a prática de atos decorrentes da ação ou omissão do CLIENTE sujeitando-o ao pagamento de multas a ser fixada pela COMPESA, nos seguintes casos:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) intervenção em ramais prediais de água ou de esgotos ou em redes de distribuição de água e coleta de esgotos, visando fraudar a medição;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) impedimento de livre acesso às instalações prediais de água e de esgotos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) falta de pagamento da fatura mensal e/ou de parcelas advindas de composição de débitos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) retirada e/ou avarias no hidrômetro, bem como intervenção no seu lacre ou na interrupção do fornecimento, visando fraudar a medição da rede de distribuição;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) fornecimento regular de água a terceiros;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f) desperdício de água;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g) colocação de bombas ou outro dispositivo que succione água diretamente da rede de distribuição;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h) lançamento de águas pluviais e despejos que por suas características exijam tratamento prévio na rede coletora de esgotos.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i) interconexão da instalação predial com canalizações alimentadas diretamente com água não procedente do abastecimento da COMPESA</style>.";
			
			String primeiroPagina3 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j) descumprimento de qualquer outra exigência técnica estabelecida no Regulamento Geral de Fornecimento de Água e Coleta de Esgotos.</style> "+
					"\n"+
					"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA INTERRUPÇÃO DOS SERVIÇOS</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA TERCEIRA - A interrupção do fornecimento de água dar-se-á após prévio aviso, nos casos do item 'e'. Nos casos previstos nos subitens 'b' e 'c' da cláusula anterior, esse prazo não poderá ser inferior a 30 (trinta) dias. Não se caracteriza como descontinuidade do serviço a sua interrupção em situação de emergência, conforme os subitens 'a' a 'c' ;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) razões de ordem técnica ou de segurança das instalações e redes de distribuição e de coleta;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) catástrofes, intempéries, acidentes, caso fortuito ou força maior; </style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) interdição do imóvel por autoridade competente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) solicitação do cliente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) cometimento de qualquer das infrações relacionadas na cláusula Décima Segunda.</style>"+
			"\n"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA QUARTA - Os ramais prediais de água somente serão suprimidos nos seguintes casos:</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) interdição do imóvel por autoridade competente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) desapropriação, incêndio ou demolição do imóvel;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) fusão de lotes;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) não regularização de qualquer infração que motivou a interrupção do abastecimento;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) solicitação do CLIENTE, desde que acompanhada da concordância dos órgãos de saúde pública e meio ambiente.</style>"+
			"\n"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA QUINTA - Os ramais prediais de esgoto somente serão suprimidos nos seguintes casos:</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) de ocorrência dos casos previstos nos subitens 'b', 'c' e 'e' da cláusula anterior; e</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) lançamento na rede de esgotos de despejos que exijam tratamento prévio.</style>"+
			"\n"+
			"\n"+
			
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA SEXTA -</style> Constituirá, igualmente, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">motivo de interrupção dos serviços à inobservância, pelo CLIENTE</style>, de quaisquer <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">cláusulas e condições do presente Contrato</style>, desde que, após devidamente notificado por escrito pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, persista na irregularidade ou inadimplência."+
			"\n"+
			"\n"+
			
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA SÉTIMA -</style> Em nenhuma hipótese será atribuída à <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, qualquer responsabilidade por danos, prejuízos ou acidentes conseqüentes de falha ou defeito nas instalações hidráulicas internas da unidade receptora do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>."
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+"Diretor Presidente: "
			+"\n"
			+ diretorPresidente
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+ "Diretor de Gestão: "
			+"\n"+
			diretorGestao
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+"\n"
			+"Diretor Comercial e de Atendimento: "
			+"\n"+
			diretorComercial
			;
				
			String segundoPagina3 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA COBRANÇA DE OUTROS SERVIÇOS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA OITAVA -</style> Fica <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">autorizado incluir na fatura a cobrança de outros serviços</style> vinculados ao abastecimento de água e coleta de esgotos, como também campanhas de utilidade pública, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">desde que autorizado antecipadamente pelo CLIENTE.</style>"+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA RESCISÃO</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA DÉCIMA NONA - O presente Contrato poderá ser rescindido:</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) por acordo entre as partes;</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) por força do término da concessão municipal dos serviços;</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) através de solicitação por escrito do proprietário do imóvel  e</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) por inadimplência de qualquer das partes.</style>"+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA VIGÊNCIA</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA VIGÉSIMA -</style> Este Contrato de Prestação de Serviços Públicos de Abastecimento de Água e/ou Esgotamento Sanitário entra em vigor a partir da data de execução da ligação dos ramais prediais de água e/ou de esgoto no imóvel do cliente solicitante."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS CASOS OMISSOS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA VIGÉSIMA PRIMEIRA -</style> Os casos omissos não regulados pelas cláusulas e condições deste Contrato, serão decididos pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> à luz das leis citadas na cláusula primeira e de outros diplomas legais pertinentes da esfera estadual e federal."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS RECURSOS E DAS COMPETÊNCIAS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA VIGÉSIMA SEGUNDA -</style> As solicitações ou reclamações do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style> sobre a prestação dos serviços deverão ser feitas a <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, porém se o <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style> não concordar com o resultado fornecido, tem o direito de apresentar recurso a Agência Estadual de Regulação dos Serviços Públicos Delegados do Estado de Pernambuco - ARPE."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO FORO</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLÁUSULA VIGÉSIMA TERCEIRA</style>"+
					"\n"+
					"Para dirimir quaisquer divergências relacionadas a este contrato, elegem as partes o foro da Comarca do Recife, Capital do Estado de Pernambuco, com renúncia a qualquer outro, por mais privilegiado que seja."
					+"\n"
					+"\n"
					+"\n"
					+"\n"
					
					;
			
			RelatorioSegundaviaAdesaoTacitaBean relatorioBean = new RelatorioSegundaviaAdesaoTacitaBean(primeiroPagina1, segundoPagina1);
			RelatorioSegundaviaAdesaoTacitaBean relatorioBean1 = new RelatorioSegundaviaAdesaoTacitaBean(primeiroPagina2, segundoPagina2);
			RelatorioSegundaviaAdesaoTacitaBean relatorioBean2 = new RelatorioSegundaviaAdesaoTacitaBean(primeiroPagina3, segundoPagina3);
			relatorioBeans.add(relatorioBean);
			relatorioBeans.add(relatorioBean1);
			relatorioBeans.add(relatorioBean2);
		
					parametros.put("primeiroPagina1", primeiroPagina1);		
		parametros.put("primeiroPagina2", primeiroPagina2);		
		parametros.put("segundoPagina1", segundoPagina1);		
		parametros.put("segundoPagina2", segundoPagina2);	
		
		
		

		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSegundaViadeContratoAdesaoTacita", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}