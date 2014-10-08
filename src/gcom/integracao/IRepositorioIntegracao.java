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
* Thiago Silva Toscano de Brito
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

package gcom.integracao;

import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.cadastro.atualizacaocadastral.AcaAtualizadores;
import gcom.cadastro.atualizacaocadastral.AtcBairro;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovel;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteTelefoneRetorno;
import gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetorno;
import gcom.cadastro.atualizacaocadastral.AtcImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcLogradouro;
import gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetorno;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public interface IRepositorioIntegracao {

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM() throws ErroRepositorioException;

	public void exportarOrdemServicoMovimentos(Collection ordensServicoParaExportacao) throws ErroRepositorioException;

	public Collection pesquisarOrdensServicoParaRecebimentoUPA() throws ErroRepositorioException;

	public void importarOrdensServico(Collection ordensServicoParaImportacao) throws ErroRepositorioException;

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(OrdemServicoMovimento movimento) throws ErroRepositorioException;
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ErroRepositorioException; 
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void exportarDadosContabilidadeIFS(String company,
			String voucher_type,String currency_code,
			String load_id,Date transaction_date,
			String accounting_cod,String cost_center,
			String	accounting_year,String accounting_period,
			BigDecimal debet_amount,BigDecimal credit_amount,
			String	text,String code_c,
			String code_d,String code_g,
			String code_h,String activity_sequence,
			String status,String transaction_type,
			String error_text,Integer record_no,
			Date rowversion)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer valorMaximoChaveIFS()
			throws ErroRepositorioException ;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcImovelRetorno> pesquisarAtcImovelRetorno(int quantidadeRegistros) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteRetorno> pesquisarAtcClienteRetorno(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteImovelRetorno> pesquisarAtcClienteImovelRetorno(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteTelefoneRetorno> pesquisarAtcClienteTelefoneRetorno(Long idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcSubcategoriaImovelRetorno> pesquisarAtcSubcategoriaImovelRetorno(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcHidrometroInstalacaoRetorno> pesquisarAtcHidrometroInstalacaoRetorno(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Metodo generico para atualizar os objetod na base atualizacao_cadastral_admin
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarIntegracaoAtualizacaoCadastral(Object objeto) throws ErroRepositorioException ;
	
	/**
	 * Metodo generico para atualizar os objetod na base atualizacao_cadastral_admin
	 * 
	 * @author Arthur Carvalho
	 */
	public Object inserirIntegracaoAtualizacaoCadastral(Object objeto) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarAtcImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteImovel> pesquisarAtcClienteImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcClienteTelefone(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcClienteImovel(Integer idAtcClienteImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcHidrometroInstalacao(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeAtcCliente(Long idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0001]-Inserir Bairro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcBairro> pesquisarAtcBairro(String bairroAtualizado) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0003]-Inserir Logradouro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcLogradouro> pesquisarAtcLogradouro(String logradouroAtualizado) throws ErroRepositorioException;
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0003]-Inserir Logradouro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AcaAtualizadores> pesquisarAcaAtualizadores() throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AtcLogradouro existeAtcLogradouro(Integer idLogradouro) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AtcBairro existeAtcBairro(Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeAtcMunicipio(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote Enviados
	 * 
	 * @author Arthur Carvalho
	 * @since 06/06/2012
	 * 
	 * @param Helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarQuantidadeImoveisVisitados(DadosResumoMovimentoAtualizacaoCadastralHelper helper, Date dataMovimento) throws ErroRepositorioException;
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SBXXXX] Limpar os dados da integração com sistema IFS para COMPESA
	 * para evitar duplicação
	 *
	 * @author Carlos Chaves
	 * @date 05/05/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public void apagarRegistrosIntegracao(String text,Date transaction_date)
			throws ErroRepositorioException;

	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public Collection pesquisarRelatorioCarroPipa(Integer mesAnoReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public Collection pesquisarRelatorioCarroPipaGRupoFaturamento(Integer idFaturamentoGrupo, 
			Integer mesAnoReferencia) throws ErroRepositorioException;

	
	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * 
	 * @author Diogo Luiz
	 * @Date 25/10/2013
	 * 
	 */
	public Collection pesquisarRelatorioCarroPipaGerenciaUnidade(Integer mesAnoReferencia,
			Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ErroRepositorioException;
	

	/**
	 * autor:Jonathan Marcos
	 * data:23/10/2013
	 * [UC1568] Receber Informações Abastecimentos Carros Pipa Via Webservice 
	 */
	public Integer validarSequencialCarroPipaAbastecimento(Integer sequencial)
		throws ErroRepositorioException;

}
