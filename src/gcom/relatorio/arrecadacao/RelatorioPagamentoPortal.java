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
 * classe respons�vel por criar o relat�rio de pagamento do portal
 * 
 * @author Davi Menezes
 * @created 16 de mar�o de 2011
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
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
		
		// Par�metros do relat�rio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		// adiciona os par�metros do relat�rio
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("inscricao", imovel.getInscricaoFormatada());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_PAGAMENTO_PORTAL;
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
			parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
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
