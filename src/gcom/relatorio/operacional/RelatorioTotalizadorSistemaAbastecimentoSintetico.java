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
package gcom.relatorio.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class RelatorioTotalizadorSistemaAbastecimentoSintetico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioTotalizadorSistemaAbastecimentoSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_SISTEMA_ABASTECIMENTO_SINTETICO);
	}
	
	@Deprecated
	public RelatorioTotalizadorSistemaAbastecimentoSintetico() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		String sistemaAbastecimento = (String)getParametro("sistemaAbastecimento");
		String subsistemaAbastecimento = (String)getParametro("subsistemaAbastecimento");
		String setorAbastecimento = (String)getParametro("setorAbastecimento");
		String distritoOperacional = (String)getParametro("distritoOperacional");
		String areaOperacional = (String)getParametro("areaOperacional");
		String zonaPressao = (String)getParametro("zonaPressao");
		String tipoRelatorio = (String)getParametro("tipoRelatorio");
		Fachada fachada = Fachada.getInstancia();
		
		
		byte[] retorno = null;
		Collection<RelatorioTotalizadorSistemaAbastecimentoBean> beans = 
				fachada.pesquisarTotalizacaoSetoresAbastecimento(
						sistemaAbastecimento, subsistemaAbastecimento, setorAbastecimento, distritoOperacional, areaOperacional, zonaPressao, tipoRelatorio);
		
		if(beans == null || beans.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado","exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=botaoVoltar",new Exception());
		}
		
		List<RelatorioTotalizadorSistemaAbastecimentoBean> listaBeans = new ArrayList<RelatorioTotalizadorSistemaAbastecimentoBean>();
		listaBeans.addAll(beans);
		
		HashMap<String,String> parametros = new HashMap<String,String>();
		RelatorioDataSource ds = new RelatorioDataSource(listaBeans);
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		String sistemaParametroMenosUm = Util.formatarAnoMesParaMesAno(Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1));

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1613");
		parametros.put("referenciaFaturamento", sistemaParametroMenosUm);
		
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEIS_SISTEMA_ABASTECIMENTO_SINTETICO, parametros,
				ds, TarefaRelatorio.TIPO_PDF);

		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioTotalizadorSistemaAbastecimentoSintetico", this);
	}

}