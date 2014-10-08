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
package gcom.relatorio.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioAcessoLojaVirtual extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAcessoLojaVirtual(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACESSO_LOJA_VIRTUAL);
	}
	
	@Deprecated
	public RelatorioAcessoLojaVirtual() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String periodoInicial = (String) getParametro("periodoInicial");
		String periodoFinal = (String) getParametro("periodoFinal");
		String tipoAtendimento = (String) getParametro("tipoAtendimento");
		String indicadorServicoExecutado = (String) getParametro("indicadorServicoExecutado");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Collection colecaoHelper = (Collection) getParametro("colecaoHelper");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioAcessoLojaVirtualBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
//		Collection<AcessoLojaVirtualHelper> colecaoHelper = fachada.pesquisarAcessoLojaVirtual(Util.converteStringParaDate(periodoInicial), Util.converteStringParaDate(periodoFinal), tipoAtendimento);
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoHelper != null && !colecaoHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator<AcessoLojaVirtualHelper> helperIterator = colecaoHelper.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (helperIterator.hasNext()) {

				AcessoLojaVirtualHelper helper = (AcessoLojaVirtualHelper) helperIterator.next();

				
				relatorioBean = new RelatorioAcessoLojaVirtualBean(helper.getTipoAtendimento() ,
						helper.getQuantidade(), helper.getQtdServicosExecutados());
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		} else {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("periodoInicial", periodoInicial);
		parametros.put("periodoFinal", periodoFinal);
		parametros.put("indicadorServicoExecutado", indicadorServicoExecutado);
		
		if ( tipoAtendimento != null && !tipoAtendimento.equals("-1") ) {
			
			AcessoLojaVirtualHelper acessoLojaVirtualHelper = new AcessoLojaVirtualHelper();
			acessoLojaVirtualHelper.setTipoAtendimento(tipoAtendimento);
			parametros.put("tipoAtendimento", acessoLojaVirtualHelper.getTipoAtendimento());
		} else {
			parametros.put("tipoAtendimento", "");
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACESSO_LOJA_VIRTUAL, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcessoLojaVirtual", this);
	}

}