/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
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
 * [UC1278] Gerar Segunda Via de Contrato Ades�o T�cita 
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

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		// Par�metros do relat�rio
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
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CONTRATO DE PRESTA��O DE SERVI�OS P�BLICOS DE ABASTECIMENTO DE �GUA E/OU ESGOTAMENTO SANIT�RIO POR ADES�O T�CITA.</style>"+
					"\n"+
					"\n"+
					"\n"+
				"Pelo presente instrumento particular, de um lado, a <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPANHIA PERNAMBUCANA DE SANEAMENTO - COMPESA</style>, "+
				"sociedade de economia mista estadual por a��es, delegat�ria de servi�o p�blico, inscrita no Cadastro Nacional "+
				"de Pessoas Jur�dicas do Minist�rio da Fazenda - CNPJ sob N� 09.769.035/0001-64, com sede na Av. Cruz Cabug�, n� 1.387, bairro de Santo Amaro, CEP 50.040-905, "+
				"nesta cidade do Recife, Estado de Pernambuco, doravante designada <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, e, de outro lado, o <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">propriet�rio e/ou usu�rio ocupante do im�vel, "+
				"respons�vel pela unidade receptora dos servi�os prestados</style>, com inscri��o, matr�cula, nome, endere�o, "+
				"CPF/CNPJ e RG descritos na primeira p�gina deste documento, doravante designado <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>,  t�m entre si justo e acordado celebrar este Contrato de Presta��o de Servi�os "+
				"P�blicos de Abastecimento de �gua e/ou Esgotamento Sanit�rio, a t�tulo de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">'Contrato de Ades�o T�cita'</style>." +
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA BASE LEGAL</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA PRIMEIRA -</style> A presta��o do servi�o p�blico de fornecimento de �gua e coleta de esgoto caracteriza neg�cio jur�dico de natureza contratual e"+
				" � regido especialmente pela Lei Federal n� 11.445/2007, que estabelece as Diretrizes Nacionais e Pol�tica "+
				"Federal do Saneamento B�sico, pela Lei Federal 8.078/90 que aprova o C�digo de Defesa do Consumidor e pelo Decreto Estadual n� 18.251/1994, que aprova o Regulamento Geral do Fornecimento de �gua e Coleta de Esgotos."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA TERMINOLOGIA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA SEGUNDA -</style> Para os fins e efeitos deste contrato s�o adotadas as seguintes defini��es:"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Caixa de Inspe��o -</style> caixa situada na cal�ada da via p�blica, que possibilita a inspe��o e desobstru��o do ramal predial de esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Categoria -</style> classifica��o dada ao im�vel cadastrado na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> de acordo com a natureza da ocupa��o de suas economias que s�o RESIDENCIAL, COMERCIAL, INDUSTRIAL e P�BLICA."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Cliente -</style> pessoa f�sica ou jur�dica que solicita � COMPESA a presta��o dos servi�os de abastecimento de �gua e/ou esgotamento sanit�rio para a unidade receptora, responsabilizando-se pelas obriga��es fixadas em regulamento que disp�em sobre a presta��o desses servi�os."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Economia -</style> todo im�vel ou subdivis�o de um im�vel considerado ocup�vel com entrada pr�pria independente das demais, raz�o social distinta e com instala��es para o abastecimento de �gua e coleta de esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Fatura -</style> documento h�bil para cobran�a e pagamento correspondente � presta��o de servi�os contra�dos pelo <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Fonte pr�pria de abastecimento de �gua -</style> abastecimento de �gua de um im�vel n�o proveniente do sistema de abastecimento de �gua operado pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Hidr�metro - </style>equipamento instalado no ramal predial destinado a medir e indicar, continuamente, o volume de �gua que o atravessa."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Instala��o predial de �gua - </style>conjunto de tubula��es, conex�es, aparelhos, equipamentos e pe�as especiais localizados dentro do im�vel at� o hidr�metro ou a torneira de passagem.";
		
		
		String segundoPagina1 = "\n"+"\n"+"\n"+"\n"+"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Instala��o predial de esgoto -</style> conjunto de tubula��es, conex�es, equipamentos e pe�as especiais localizados dentro do im�vel at� a caixa de inspe��o."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Interrup��o do fornecimento -</style> suspens�o tempor�ria dos servi�os de abastecimento de �gua, pela COMPESA, nos casos determinados no Regulamento Geral do Fornecimento de �gua e Coleta de Esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Lacre - </style>dispositivo destinado a caracterizar a inviolabilidade do hidr�metro ou da interrup��o do fornecimento."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Ramal predial de �gua -</style> conjunto de tubula��es e pe�as especiais situadas entre a rede de distribui��o de �gua e o hidr�metro ou a torneira de passagem."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Ramal predial de esgoto -</style> conjunto de tubula��es e pe�as especiais situadas entre a rede coletora de esgotos e a caixa de inspe��o."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Sistema p�blico de abastecimento de �gua -</style> conjunto de canaliza��es, esta��o de tratamento, reservat�rios, elevat�rias, equipamentos e demais instala��es, que tem por finalidade captar, aduzir, tratar, reservar e distribuir �gua."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Sistema p�blico de esgotos sanit�rios -</style> conjunto de canaliza��es, esta��es de tratamento, elevat�rias, equipamentos e demais instala��es destinadas a coletar, transportar e dispor adequadamente os esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Supress�o do ramal predial -</style> interrup��o do fornecimento de �gua ou coleta de esgoto ao im�vel, com a retirada de todo ramal predial, nos casos determinados no Regulamento Geral do Fornecimento de �gua e Coleta de Esgotos."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Tarifa -</style> valor fixado em moeda corrente, utilizado pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, referente � cobran�a dos servi�os p�blicos de abastecimento de �gua e/ou esgotamento sanit�rio."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Unidade receptora -</style> � o im�vel que recebe da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> a presta��o dos servi�os de abastecimento de �gua e/ou esgotamento sanit�rio."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO OBJETO</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA TERCEIRA -</style> Constitui objeto do presente contrato a presta��o do servi�o p�blico de abastecimento de �gua e/ou de esgotamento sanit�rio � unidade receptora a pedido, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">com �nus e sob a responsabilidade do CLIENTE</style>."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA ABRANG�NCIA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA QUARTA -</style> Este contrato aplica-se a todas as categorias de clientes contemplados com os servi�os de abastecimento de �gua e/ou de esgotamento sanit�rio quais sejam:  <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">Residencial,Comercial, Industrial e P�blico</style> ."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS RAMAIS PREDIAIS DE �GUA E DE ESGOTO</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA QUINTA -</style> Os ramais prediais de �gua e de esgotos ser�o implantados pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, � custa do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>, satisfeitas as exig�ncias estabelecidas em normas e instrumentos regulamentares."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">PAR�GRAFO �NICO -</style> Os ramais prediais de �gua e/ou de esgotos, ap�s suas execu��es, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">passar�o a integrar o patrim�nio da COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA SEXTA -</style> A manuten��o dos ramais prediais � de responsabilidade exclusiva da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA S�TIMA -</style> O remanejamento ou amplia��o do di�metro do ramal predial por conveni�ncia do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>, de" ;
				
			String primeiroPagina2=	"acordo com as normas da <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA, ser�o executados �s expensas do cliente</style>."+
					"\n"+
					"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS DIREITOS DO CLIENTE</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA OITAVA -</style> S�o direitos do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a)</style> receber abastecimento de �gua tratada no im�vel nos padr�es de qualidade exigidos pela Portaria n� 518/2004, de 25 de mar�o de 2004, do Minist�rio da Sa�de;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b)</style> dispor de manuten��o e assist�ncia t�cnica nas instala��es dos ramais prediais de �gua e/ou de esgotos;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c)</style> ser atendido com efici�ncia, rapidez e cortesia;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d)</style> ser orientado sobre a import�ncia e o uso eficiente dos servi�os prestados, de modo a reduzir desperd�cios e garantir a seguran�a na sua utiliza��o"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e)</style> escolher uma entre pelos menos 6 (seis) datas disponibilizadas pela COMPESA para o vencimento da fatura, ressalvando-se que somente poder� ser alterada depois de decorrido o per�odo de 01 (um) ano da escolha;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f)</style> receber a fatura com anteced�ncia m�nima de 5 (cinco) dias �teis da data do vencimento; "+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g)</style> ser informado, na fatura, sobre o percentual de reajuste tarif�rio e a data de in�cio de sua vig�ncia, bem como sobre a qualidade da �gua e a exist�ncia de d�bitos para com a COMPESA;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h)</style> estar � sua disposi��o servi�o de atendimento telef�nico e eletr�nico para atendimento usual e de emerg�ncia;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i)</style> dispor do servi�o de endere�o alternativo para o recebimento da fatura;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j)</style> ser informado sobre a ocorr�ncia de interrup��es programadas, por meio de jornais, r�dio, televis�o, 'site' da COMPESA ou qualquer outro meio de comunica��o;"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">l)</style> ter, para fins de consulta, nos locais de atendimento, acesso �s Normas, Estrutura Tarif�ria, Tabela de Pre�os e Servi�os e ao Regulamento Geral do Fornecimento de �gua e Coleta de Esgotos da COMPESA, todos vigentes."+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS OBRIGA��ES DO CLIENTE</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA NONA - S�o obriga��es do CLIENTE:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) pagar a fatura mensal do fornecimento de �gua e/ou coleta de esgotos e outros servi�os, at� a data do vencimento, sujeitando-se em caso de atraso no pagamento da fatura e ap�s a comunica��o formal pela COMPESA, �s a��es de cobran�a a ser legalmente por ela praticadas, inclusive a negativa��o de cr�ditos junto aos �rg�os competentes (SPC e SERASA);</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) informar qualquer altera��o da atividade exercida no im�vel que possa resultar em mudan�a de categoria ou do n�mero de economias, com o intuito de manter os dados cadastrais atualizados e para fins de tarifa��o adequada pela COMPESA.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) manter os dados cadastrais atualizados junto � COMPESA</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) zelar pelas instala��es dos ramais prediais de �gua e/ou de esgoto, de forma a evitar quaisquer tipos de danos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) responder, no caso de hidr�metro instalado no interior do im�vel, pela guarda, prote��o e danos causados ao mesmo, sendo permanentemente proibida a instala��o, repara��o, substitui��o ou remo��o do aparelho � revelia da COMPESA;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f) informar � COMPESA, mediante apresenta��o de documento comprobat�rio, a transfer�ncia de titularidade quanto � responsabilidade pelos servi�os prestados � unidade receptora, sob pena de assumir todas as obriga��es decorrentes deste contrato, inclusive os d�bitos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g) assegurar o livre acesso � entrada de empregados e representantes da COMPESA, para fins de inspe��o e/ou leitura do hidr�metro instalado;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h) informar � COMPESA sobre a utiliza��o no im�vel de fonte pr�pria de abastecimento de �gua (po�o artesiano) ou outra fonte de abastecimento (carro-pipa);</style>";
			
			String segundoPagina2 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i) tornar independente do ramal predial da COMPESA a instala��o e o reservat�rio da fonte pr�pria de �gua, com o intuito de n�o misturar a �gua tratada com a �gua proveniente da fonte pr�pria;</style>"+
					"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j) pagar a fatura de esgoto do im�vel contemplado com a rede p�blica de esgotamento sanit�rio, mesmo que o im�vel tenha outra fonte de �gua que n�o seja a p�blica;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">l) informar � COMPESA, mediante laudo m�dico, a exist�ncia de pessoa no im�vel que use, em tratamento especial, equipamentos que dependam da �gua;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">m) reservar e manter a qualidade da �gua nas instala��es prediais sob sua responsabilidade.</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS TARIFAS</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA  D�CIMA -</style>  A  estrutura tarif�ria da COMPESA representa a distribui��o de tarifas por faixa de consumo e volume esgotado, de forma a compatibilizar os aspectos econ�mico com os objetivos sociais, observando o disposto nos artigos 48 a 70, do Regulamento."+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) nas unidades com hidr�metro, o volume consumido ser� obtido pela diferen�a entre a leitura atual e a anterior. N�o sendo poss�vel em determinado momento a realiza��o da leitura, a apura��o ser� feita com base na m�dia aritm�tica dos consumos faturados nos �ltimos 6 (seis) meses.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) enquanto n�o implantado em definitivo o hidr�metro, o consumo ser� fixado por estimativa em fun��o do consumo m�dio presumido, com base em atributos f�sicos do im�vel ou em medi��o tempor�ria.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) as tarifas de esgoto ser�o fixadas entre 30% e 100% das tarifas de �gua, em fun��o da origem e natureza dos investimentos necess�rios � implanta��o, opera��o e manuten��o dos servi�os</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO PAGAMENTO DAS FATURAS</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA PRIMEIRA -</style> Consoante o art. 71, do Regulamento Geral do Fornecimento de �gua e Coleta de Esgotos, aprovado pelo Decreto Estadual n� 18.251/1994 e com a nova reda��o dada pelo Decreto Estadual n� 30.774/2007, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">respondem solidariamente pelos d�bitos relativos ao fornecimento de �gua, coleta de esgoto e outros servi�os, o propriet�rio e o usu�rio ocupante do im�vel, podendo ser inscrito, um ou outro, nos servi�os de prote��o ao cr�dito, no caso de inadimpl�ncia.</style>"+
				"\n"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DAS INFRA��ES E PENALIDADES</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA SEGUNDA - Constitui infra��o a pr�tica de atos decorrentes da a��o ou omiss�o do CLIENTE sujeitando-o ao pagamento de multas a ser fixada pela COMPESA, nos seguintes casos:</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) interven��o em ramais prediais de �gua ou de esgotos ou em redes de distribui��o de �gua e coleta de esgotos, visando fraudar a medi��o;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) impedimento de livre acesso �s instala��es prediais de �gua e de esgotos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) falta de pagamento da fatura mensal e/ou de parcelas advindas de composi��o de d�bitos;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) retirada e/ou avarias no hidr�metro, bem como interven��o no seu lacre ou na interrup��o do fornecimento, visando fraudar a medi��o da rede de distribui��o;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) fornecimento regular de �gua a terceiros;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">f) desperd�cio de �gua;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">g) coloca��o de bombas ou outro dispositivo que succione �gua diretamente da rede de distribui��o;</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">h) lan�amento de �guas pluviais e despejos que por suas caracter�sticas exijam tratamento pr�vio na rede coletora de esgotos.</style>"+
				"\n"+
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">i) interconex�o da instala��o predial com canaliza��es alimentadas diretamente com �gua n�o procedente do abastecimento da COMPESA</style>.";
			
			String primeiroPagina3 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">j) descumprimento de qualquer outra exig�ncia t�cnica estabelecida no Regulamento Geral de Fornecimento de �gua e Coleta de Esgotos.</style> "+
					"\n"+
					"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA INTERRUP��O DOS SERVI�OS</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA TERCEIRA - A interrup��o do fornecimento de �gua dar-se-� ap�s pr�vio aviso, nos casos do item 'e'. Nos casos previstos nos subitens 'b' e 'c' da cl�usula anterior, esse prazo n�o poder� ser inferior a 30 (trinta) dias. N�o se caracteriza como descontinuidade do servi�o a sua interrup��o em situa��o de emerg�ncia, conforme os subitens 'a' a 'c' ;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) raz�es de ordem t�cnica ou de seguran�a das instala��es e redes de distribui��o e de coleta;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) cat�strofes, intemp�ries, acidentes, caso fortuito ou for�a maior; </style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) interdi��o do im�vel por autoridade competente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) solicita��o do cliente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) cometimento de qualquer das infra��es relacionadas na cl�usula D�cima Segunda.</style>"+
			"\n"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA QUARTA - Os ramais prediais de �gua somente ser�o suprimidos nos seguintes casos:</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) interdi��o do im�vel por autoridade competente;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) desapropria��o, inc�ndio ou demoli��o do im�vel;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) fus�o de lotes;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) n�o regulariza��o de qualquer infra��o que motivou a interrup��o do abastecimento;</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">e) solicita��o do CLIENTE, desde que acompanhada da concord�ncia dos �rg�os de sa�de p�blica e meio ambiente.</style>"+
			"\n"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA QUINTA - Os ramais prediais de esgoto somente ser�o suprimidos nos seguintes casos:</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) de ocorr�ncia dos casos previstos nos subitens 'b', 'c' e 'e' da cl�usula anterior; e</style>"+
			"\n"+
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) lan�amento na rede de esgotos de despejos que exijam tratamento pr�vio.</style>"+
			"\n"+
			"\n"+
			
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA SEXTA -</style> Constituir�, igualmente, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">motivo de interrup��o dos servi�os � inobserv�ncia, pelo CLIENTE</style>, de quaisquer <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">cl�usulas e condi��es do presente Contrato</style>, desde que, ap�s devidamente notificado por escrito pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, persista na irregularidade ou inadimpl�ncia."+
			"\n"+
			"\n"+
			
			"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA S�TIMA -</style> Em nenhuma hip�tese ser� atribu�da � <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, qualquer responsabilidade por danos, preju�zos ou acidentes conseq�entes de falha ou defeito nas instala��es hidr�ulicas internas da unidade receptora do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style>."
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
			+ "Diretor de Gest�o: "
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
				
			String segundoPagina3 = "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA COBRAN�A DE OUTROS SERVI�OS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA OITAVA -</style> Fica <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">autorizado incluir na fatura a cobran�a de outros servi�os</style> vinculados ao abastecimento de �gua e coleta de esgotos, como tamb�m campanhas de utilidade p�blica, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">desde que autorizado antecipadamente pelo CLIENTE.</style>"+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA RESCIS�O</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA D�CIMA NONA - O presente Contrato poder� ser rescindido:</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">a) por acordo entre as partes;</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">b) por for�a do t�rmino da concess�o municipal dos servi�os;</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">c) atrav�s de solicita��o por escrito do propriet�rio do im�vel  e</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">d) por inadimpl�ncia de qualquer das partes.</style>"+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DA VIG�NCIA</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA VIG�SIMA -</style> Este Contrato de Presta��o de Servi�os P�blicos de Abastecimento de �gua e/ou Esgotamento Sanit�rio entra em vigor a partir da data de execu��o da liga��o dos ramais prediais de �gua e/ou de esgoto no im�vel do cliente solicitante."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS CASOS OMISSOS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA VIG�SIMA PRIMEIRA -</style> Os casos omissos n�o regulados pelas cl�usulas e condi��es deste Contrato, ser�o decididos pela <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style> � luz das leis citadas na cl�usula primeira e de outros diplomas legais pertinentes da esfera estadual e federal."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DOS RECURSOS E DAS COMPET�NCIAS</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA VIG�SIMA SEGUNDA -</style> As solicita��es ou reclama��es do <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style> sobre a presta��o dos servi�os dever�o ser feitas a <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">COMPESA</style>, por�m se o <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CLIENTE</style> n�o concordar com o resultado fornecido, tem o direito de apresentar recurso a Ag�ncia Estadual de Regula��o dos Servi�os P�blicos Delegados do Estado de Pernambuco - ARPE."+
					"\n"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DO FORO</style>"+
					"\n"+
					"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">CL�USULA VIG�SIMA TERCEIRA</style>"+
					"\n"+
					"Para dirimir quaisquer diverg�ncias relacionadas a este contrato, elegem as partes o foro da Comarca do Recife, Capital do Estado de Pernambuco, com ren�ncia a qualquer outro, por mais privilegiado que seja."
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
		
		
		

		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
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