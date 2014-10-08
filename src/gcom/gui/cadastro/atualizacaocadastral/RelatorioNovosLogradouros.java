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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

/**
 * [UC1443] Gerar Relatorio de Novos Logradouros 
 * @author Anderson Cabral
 * @date 15/03/2013
 */
public class RelatorioNovosLogradouros extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioNovosLogradouros(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NOVOS_LOGRADOUROS);
	}

	public RelatorioNovosLogradouros(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String idEmpresa = (String) getParametro("idEmpresa");
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String idLocalidade = (String) getParametro("idLocalidade");
		String nomeLocalidade = (String) getParametro("nomeLocalidade");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("idLocalidade", idLocalidade);
		parametros.put("nomeLocalidade", nomeLocalidade);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1443");
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		ArrayList<RelatorioNovosLogradourosBean> beans = new ArrayList<RelatorioNovosLogradourosBean>();

		Collection<Object[]> colecaoNovosLogradouros = fachada.pesquisarLogradouroAtlzCad(idEmpresa, idLocalidade);
		
		if(colecaoNovosLogradouros != null && colecaoNovosLogradouros.size() > 0){
			RelatorioNovosLogradourosBean bean = null;
			Object[] objLogradouro = null;
			
			Iterator<Object[]> interatorColecaoNovosLogradouros = colecaoNovosLogradouros.iterator();
			
			while(interatorColecaoNovosLogradouros.hasNext()){
				objLogradouro =	(Object[]) interatorColecaoNovosLogradouros.next();
				bean = new RelatorioNovosLogradourosBean();
				
				bean.setIdLogradouro((String) objLogradouro[0]);
				if(objLogradouro[1] != null){
					bean.setIdTipo((String) objLogradouro[1]);
				}
				if(objLogradouro[2] != null){
					bean.setNomeTipo((String) objLogradouro[2]);
				}
				if(objLogradouro[3] != null){
					bean.setIdTitulo((String) objLogradouro[3]);
				}
				if(objLogradouro[4] != null){
					bean.setNomeTitulo((String) objLogradouro[4]);
				}
				bean.setNomeLogradouro((String) objLogradouro[5]);
				bean.setNomePopular((String) objLogradouro[6]);
				bean.setIdMunicipio((String) objLogradouro[7]);
				bean.setNomeMunicipio((String) objLogradouro[8]);
				
				Collection<Object[]> colecaoBairros = fachada.pesquisarLogradouroBairroAtlzCad(bean.getIdLogradouro());
				
				if(colecaoBairros != null && colecaoBairros.size() > 0){
					Object[] objBairro = null;
					Iterator<Object[]> interatorColecaoBairros = colecaoBairros.iterator();
					String nomeBairros = "";
					
					while(interatorColecaoBairros.hasNext()){
						objBairro =	(Object[]) interatorColecaoBairros.next();
						
						nomeBairros = nomeBairros + (String) objBairro[2] + ", ";
					}
					
					if(nomeBairros.trim() != ""){
						nomeBairros = nomeBairros.substring(0, nomeBairros.length() - 2);
					}

					bean.setNomeBairro(nomeBairros);
				}
				
				Collection<Object[]> colecaoCeps = fachada.pesquisarLogradouroCepAtlzCad(bean.getIdLogradouro());
				
				if(colecaoCeps != null && colecaoCeps.size() > 0){
					Object[] objCep = null;
					Iterator<Object[]> interatorColecaoCeps = colecaoCeps.iterator();
					String codigoCeps = "";
					
					while(interatorColecaoCeps.hasNext()){
						objCep = (Object[]) interatorColecaoCeps.next();
						
						codigoCeps = codigoCeps + (String) objCep[2] + ", ";						
					}
					
					if(codigoCeps.trim() != ""){
						codigoCeps = codigoCeps.substring(0, codigoCeps.length() - 2);
					}
					
					bean.setCodigoCep(codigoCeps);
				}
				
				beans.add(bean);			
			}
			
			if(beans != null && !beans.isEmpty()){
				parametros.put("total", String.valueOf(beans.size()));
			}else{
				parametros.put("total", "0");
			}
			
			RelatorioDataSource ds = new RelatorioDataSource(beans);			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOVOS_LOGRADOUROS, parametros, ds, tipoRelatorio);
			
		}else{			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}
