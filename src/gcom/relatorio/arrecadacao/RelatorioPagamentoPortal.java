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
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.pagamento.TipoPagamentoPortalHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de pagamento do portal
 * 
 * @author Davi Menezes
 * @created 16 de março de 2011
 */
public class RelatorioPagamentoPortal extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioPagamentoPortal(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PAGAMENTO_PORTAL);
	}

	@Deprecated
	public RelatorioPagamentoPortal() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Integer matricula = (Integer) getParametro("matricula");
		
		Collection<?> colecaoPagamentosConta = (Collection<?>) getParametro("colecaoPagamentosConta");
		Collection<?> colecaoPagamentosDebito = (Collection<?>) getParametro("colecaoPagamentosDebito");
		Collection<?> colecaoPagamentosGuia = (Collection<?>) getParametro("colecaoPagamentosGuia");
		
		List<RelatorioPagamentoPortalBean> colecaoBeans = new ArrayList<RelatorioPagamentoPortalBean>();
		
		RelatorioPagamentoPortalBean bean = null;
		TipoPagamentoPortalHelper helper = null;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Imovel imovel = fachada.pesquisarImovel(matricula);
		
		String matriculaFormatada = imovel.getMatriculaFormatada();
		
		if(!Util.isVazioOrNulo(colecaoPagamentosConta)){
			Iterator<?> iterator = colecaoPagamentosConta.iterator();
			while(iterator.hasNext()){
				bean = new RelatorioPagamentoPortalBean();
				helper = (TipoPagamentoPortalHelper) iterator.next();
				
				if(helper.getTipoPagamento().equals("pagamento")){
					bean.setTipoDebito("");
					bean.setMesAno(helper.getReferenciaConta());
					bean.setDataPagamento(Util.formatarData(helper.getPagamento().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamento().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamento().getValorPagamento());
				}else{
					bean.setTipoDebito("");
					bean.setMesAno(helper.getReferenciaConta());
					bean.setDataPagamento(Util.formatarData(helper.getPagamentoHistorico().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamentoHistorico().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamentoHistorico().getValorPagamento());
				}
				colecaoBeans.add(bean);
			}
		}
		
		if(!Util.isVazioOrNulo(colecaoPagamentosDebito)){
			Iterator<?> iterator = colecaoPagamentosDebito.iterator();
			while(iterator.hasNext()){
				bean = new RelatorioPagamentoPortalBean();
				helper = (TipoPagamentoPortalHelper) iterator.next();
				
				if(helper.getTipoPagamento().equals("pagamento")){
					bean.setMesAno("");
					bean.setTipoDebito(helper.getPagamento().getDebitoTipo().getDescricao());
					bean.setDataPagamento(Util.formatarData(helper.getPagamento().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamento().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamento().getValorPagamento());
				}else{
					bean.setMesAno("");
					bean.setTipoDebito(helper.getPagamentoHistorico().getDebitoTipo().getDescricao());
					bean.setDataPagamento(Util.formatarData(helper.getPagamentoHistorico().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamentoHistorico().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamentoHistorico().getValorPagamento());
				}
				colecaoBeans.add(bean);
			}
		}
		
		if(!Util.isVazioOrNulo(colecaoPagamentosGuia)){
			Iterator<?> iterator = colecaoPagamentosGuia.iterator();
			while(iterator.hasNext()){
				bean = new RelatorioPagamentoPortalBean();
				helper = (TipoPagamentoPortalHelper) iterator.next();
				
				if(helper.getTipoPagamento().equals("pagamento")){
					bean.setMesAno("");
					bean.setTipoDebito(helper.getPagamento().getDebitoTipo().getDescricao());
					bean.setDataPagamento(Util.formatarData(helper.getPagamento().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamento().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamento().getValorPagamento());
				}else{
					bean.setMesAno("");
					bean.setTipoDebito(helper.getPagamentoHistorico().getDebitoTipo().getDescricao());
					bean.setDataPagamento(Util.formatarData(helper.getPagamentoHistorico().getDataPagamento()));
					bean.setTipoDocumento(helper.getPagamentoHistorico().getDocumentoTipo().getDescricaoDocumentoTipo());
					bean.setValorPagamento(helper.getPagamentoHistorico().getValorPagamento());
				}
				colecaoBeans.add(bean);
			}
		}
		
		// Parâmetros do relatório
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		// adiciona os parâmetros do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("inscricao", imovel.getInscricaoFormatada());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_PAGAMENTO_PORTAL;
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
			parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentoPortal", this);
	}

}
