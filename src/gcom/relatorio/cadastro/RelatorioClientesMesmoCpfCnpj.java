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
package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
 * 2. O sistema executa o step "Gerar Relatorio de Clientes com o Mesmo Cpf/Cnpj"
 *
 * @author Mariana Victor
 * @date 22/01/2013
 */
public class RelatorioClientesMesmoCpfCnpj extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioClientesMesmoCpfCnpj(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTES_MESMO_CPF_CNPJ);
	}

	@Deprecated
	public RelatorioClientesMesmoCpfCnpj() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param colecaoHelper
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		Fachada fachada = Fachada.getInstancia();

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<RelatorioClientesMesmoCpfCnpjHelper> colecaoHelper = 
				(Collection<RelatorioClientesMesmoCpfCnpjHelper>) getParametro("colecaoHelper");
		
		Integer quantidadeRegistros = (Integer) getParametro("quantidadeRegistros");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List<RelatorioClientesMesmoCpfCnpjBean> relatorioBeans = 
				new ArrayList<RelatorioClientesMesmoCpfCnpjBean>();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoHelper != null
				&& !colecaoHelper.isEmpty()) {

			Iterator<RelatorioClientesMesmoCpfCnpjHelper> iterator = colecaoHelper
					.iterator();

			while (iterator.hasNext()) {

				RelatorioClientesMesmoCpfCnpjHelper helper = (RelatorioClientesMesmoCpfCnpjHelper) 
						iterator.next();
				
				RelatorioClientesMesmoCpfCnpjBean bean = new RelatorioClientesMesmoCpfCnpjBean();
				
				bean.setIdClienteAtual(helper.getIdClienteAtual());
				bean.setIdClienteAnterior(helper.getIdClienteAnterior());
				bean.setDocumento(helper.getDocumento());
				bean.setNomeClienteAtual(helper.getNomeClienteAtual());
				bean.setNomeClienteAnterior(helper.getNomeClienteAnterior());
				bean.setImovel(helper.getImovel());

				// adiciona o bean a cole��o
				relatorioBeans.add(bean);
			}

		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map<String, Object> parametros = new HashMap<String, Object>();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("totalRegistros", quantidadeRegistros);

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		
		if (relatorioBeans != null 
				&& relatorioBeans.size() > 0) {
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_CLIENTES_MESMO_CPF_CNPJ,
					parametros, ds, tipoFormatoRelatorio);
			
		} else {
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
			
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_CLIENTES_MESMO_CPF_CNPJ, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	public int calcularTotalRegistrosRelatorio() {

		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioClientesMesmoCpfCnpj", this);
	}

}
