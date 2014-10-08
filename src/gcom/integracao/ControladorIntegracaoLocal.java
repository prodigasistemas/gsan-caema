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
package gcom.integracao;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import gcom.atendimentopublico.SmsTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.gui.integracao.CarroPipaAbastecimentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jboss.util.stream.AutoResetObjectOutputStream;



public interface ControladorIntegracaoLocal extends javax.ejb.EJBLocalObject {
	public void enviarMovimentoExportacaoFirma(); 
	public void receberMovimentoExportacaoFirma();
	public Integer gerarOS(Usuario usuario, OrdemServico ordemServico) throws ControladorException;
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ControladorException; 
	
	
	public void recepcaoDadosAdmin(Integer idFuncionalidadeIniciada) throws ControladorException;
	
   /** [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDadosAdmin(Integer idFuncionalidadeIniciada, Integer idParametro) throws ControladorException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDadosAdmin(Integer idParametro) throws ControladorException;
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelasBasicasRecepcaoDadosAdmin(Integer idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Enviar Mensagem SMS para celular aparti de um webservice 
	 * Metodo que envia mensagem sms para um celular por meio de um web service. 
	 * @author Carlos Chaves
	 * @since 04/12/2012
	 * @param String Mensagem - Deve conter no maximo 160 caracteres
	 * @param Long nroDestino - Telefone de destino Com 10 caracters.
	 * @return String
	 */
	public String enviarSmsWebService(String mensagem,Long nroDestino)
			throws ControladorException;	
	
	/**
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [UC1568] Receber Informacoes Abastecimento Carro Pipa Via Webservice
	 * [Observacoes]
	 * 		1 - Pamaretros :
	 * 			1.1 - Integer sequencial 
	 * 			1.2 - String descricaoPlaca
	 * 			1.3 - Integer idImovel
	 * 			1.4 - Date dataAbastecimento
	 * 			1.5 - Integer volumeAbastecimento
	 * 			1.6 - Short indicadorCobranca
	 * 			1.7 - Short indicadorAbastecimentoAvulso
	 * 		2 - return : Integer id gerado
	 */
	public Integer inserirCarroPipaAbastecimento(Integer sequencial,String descricaoPlaca,Integer idImovel,
			Date dataAbastecimento,Integer volumeAbastecimento,Short indicadorCobranca,
			Short indicadorAbastecimentoAvulso)
		throws ControladorException;
	
	/**
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [UC1567] Consultar Debitos Imovel Webservice
	 * [Observacoes]
	 * 		1 - Pamaretros : 
	 * 			1.1 - CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia
	 * 		2 - return : String numero protocolo
	 */
	public String inserirCarroPipaRetornoOcorrencia(CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia,
			boolean gerarNumeroProtocolo)
		throws ControladorException;
	
	public String gerarNumeroProtocoloCarroPipaRetornoOcorrencia(Short tipoRequisicao,
			Integer idCarroPipaRetornoOcorrenciaGerado)
			throws ControladorException;
	
	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public List pesquisarRelatorioCarroPipa(Integer idFaturamentoGrupo, Integer mesAnoReferencia,
			Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ControladorException;
	
	
	
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public void receberInformacoesAbastecimentoCarroPipaArquivoTxt(BufferedReader buffer,Integer quantidadeLinhas)
		throws ControladorException;
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1568] Receber Informacoes Abastecimento Carro Pipa Via Webservice
	 * @param request
	 * @param colunasArquivo
	 * @return
	 * @throws ControladorException
	 */
	public CarroPipaAbastecimentoHelper montarHelperCarroPipaAbastecimento(HttpServletRequest request,
			String colunasArquivo[]) throws ControladorException;
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public byte[] gerarArquivoTxtRetornoCarroPipaAbastecimento(List<CarroPipaRetornoOcorrencia>
	listaCarroPipaRetornoOcorrencia,Integer quantidadeLinhas)throws ControladorException;
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public void enviarEmailCarroPipaAbastecimentoArquivoTxt(byte[] arquivoGerado)
		throws ControladorException;
	
	/**
	 * autor:Jonathan Marcos
	 * data:23/10/2013
	 * [UC1568] Receber Informações Abastecimentos Carros Pipa Via Webservice 
	 */
	public Integer validarSequencialCarroPipaAbastecimento(Integer sequencial)
		throws ControladorException;
		

}
