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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
 * @author Vivianne Sousa
 * @date 24/07/2013
 */

public class RelatorioResumoImoveisAlteracaoInscricaoViaBatch extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;

		
	public RelatorioResumoImoveisAlteracaoInscricaoViaBatch(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH);
	}
	
	public RelatorioResumoImoveisAlteracaoInscricaoViaBatch(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioResumoImoveisAlteracaoInscricaoViaBatch() {
		super(null, "");
	}
	
	
	protected Collection<RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean> inicializarBeanRelatorio(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper,
			Fachada fachada) {
		
		Collection<RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean> retorno = 
				fachada.pesquisarResumoImoveisAlteracaoInscricaoViaBatch(relatorioHelper);
				
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		// valor de retorno
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper = 
				(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper) getParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper");
			
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String periodo = (String) getParametro("periodo");
		
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		
		String setorComercialInicial = (String) getParametro("setorComercialInicial");
		String setorComercialFinal = (String) getParametro("setorComercialFinal");
		
		String quadraIncial = (String) getParametro("quadraIncial");
		String quadraFinal = (String) getParametro("quadraFinal");
		
		String loteInicial = (String) getParametro("loteInicial");
		String loteFinal = (String) getParametro("loteFinal");
		
		String subLoteInicial = (String) getParametro("subLoteInicial");
		String subLoteFinal = (String) getParametro("subLoteFinal");
	

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		String titulo = "";
		if(relatorioHelper.getEscolhaRelatorio().intValue() == 1){
			
			titulo = "Imóveis alterados com sucesso.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 2){
			
			titulo = "Imóveis sem alteração devido a erro.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 3){
			
			titulo = "Imóveis pendentes de alteração.";
		}
		
		String pesquisadoPor = "";
		if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
				!relatorioHelper.getIndicadorInscricaoAtualAnterior().equals("")){
			
			if(relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.SIM)){
				pesquisadoPor = "INSCRIÇÃO ATUAL";
			}else{
				pesquisadoPor = "INSCRIÇÃO ANTERIOR";
			}
		}
		
		String origemAtualizacao = "";
		if(relatorioHelper.getOrigemAtualizacao() != null &&
				!relatorioHelper.getOrigemAtualizacao().equals("")){
			
			if(relatorioHelper.getOrigemAtualizacao().equals("1")){
				origemAtualizacao = "GSAN";
			}else if(relatorioHelper.getOrigemAtualizacao().equals("2")){
				origemAtualizacao = "RESSETORIZAÇÃO";
			}else{
				origemAtualizacao = "TODOS";
			}
		}
		
		parametros.put("titulo", titulo);
		
		parametros.put("periodo", periodo);
		
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		
		parametros.put("quadraIncial", quadraIncial);
		parametros.put("quadraFinal", quadraFinal);
		
		parametros.put("loteInicial", loteInicial);
		parametros.put("loteFinal", loteFinal);
		
		parametros.put("subLoteInicial", subLoteInicial);
		parametros.put("subLoteFinal", subLoteFinal);
		
		parametros.put("pesquisadoPor", pesquisadoPor);
		parametros.put("origemAtualizacao",origemAtualizacao);


		Collection<RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean> colecaoBean = 
			this.inicializarBeanRelatorio(relatorioHelper,fachada);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
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
		
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoImoveisAlteracaoInscricaoViaBatch", this);
	}
	
}
