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
* Ivan S�rgio Virginio da Silva J�nior
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
 * [UC1121] Gerar Relat�rio de Im�veis com Altera��o de Inscri��o Via Batch
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
	 * M�todo que executa a tarefa
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
	

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		String titulo = "";
		if(relatorioHelper.getEscolhaRelatorio().intValue() == 1){
			
			titulo = "Im�veis alterados com sucesso.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 2){
			
			titulo = "Im�veis sem altera��o devido a erro.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 3){
			
			titulo = "Im�veis pendentes de altera��o.";
		}
		
		String pesquisadoPor = "";
		if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
				!relatorioHelper.getIndicadorInscricaoAtualAnterior().equals("")){
			
			if(relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.SIM)){
				pesquisadoPor = "INSCRI��O ATUAL";
			}else{
				pesquisadoPor = "INSCRI��O ANTERIOR";
			}
		}
		
		String origemAtualizacao = "";
		if(relatorioHelper.getOrigemAtualizacao() != null &&
				!relatorioHelper.getOrigemAtualizacao().equals("")){
			
			if(relatorioHelper.getOrigemAtualizacao().equals("1")){
				origemAtualizacao = "GSAN";
			}else if(relatorioHelper.getOrigemAtualizacao().equals("2")){
				origemAtualizacao = "RESSETORIZA��O";
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
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
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
