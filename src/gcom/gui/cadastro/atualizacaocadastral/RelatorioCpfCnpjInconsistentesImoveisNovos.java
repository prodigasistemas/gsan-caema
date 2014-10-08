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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

/**
 * Gerar Relatorio CPF/CNPJ Inconsistentes para Imoveis novos
 * 
 * @author Anderson Cabral
 * @date 22/03/2013
 */
public class RelatorioCpfCnpjInconsistentesImoveisNovos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioCpfCnpjInconsistentesImoveisNovos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CPF_CNPJ_INCONSISTENTES_IMOVEIS_NOVOS);
	}

	public RelatorioCpfCnpjInconsistentesImoveisNovos(Usuario usuario, String nomeRelatorio) {
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
		
		String empresa 		  = (String) getParametro("empresa");
		String idLocalidade   = (String) getParametro("idLocalidade");
		String nomeLocalidade = (String) getParametro("nomeLocalidade");
		String total = "0";
		
		ArrayList<DadosImovelPreGsanHelper> colecaoCpfCnpjInconsistentes = (ArrayList<DadosImovelPreGsanHelper>) getParametro("colecaoCpfCnpjInconsistentes");
		
		ArrayList<CpfCnpjInconsistentesImoveisNovosBean> colecaoCpfCnpjInconsistentesBean = montaBean(colecaoCpfCnpjInconsistentes);
		
		if(colecaoCpfCnpjInconsistentesBean != null && !colecaoCpfCnpjInconsistentesBean.isEmpty()){
			total = "" + colecaoCpfCnpjInconsistentesBean.size();
		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", empresa);
		parametros.put("idLocalidade", idLocalidade);
		parametros.put("nomeLocalidade", nomeLocalidade);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1447");
		parametros.put("total", total);
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoCpfCnpjInconsistentesBean);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CPF_CNPJ_INCONSISTENTES_IMOVEIS_NOVOS, parametros, ds, tipoRelatorio);
		
		return retorno;
	}
	
	private ArrayList<CpfCnpjInconsistentesImoveisNovosBean> montaBean(ArrayList<DadosImovelPreGsanHelper> colecaoCpfCnpjInconsistentes){
		
		ArrayList<CpfCnpjInconsistentesImoveisNovosBean> colecaoCpfCnpjInconsistentesBean = new ArrayList<CpfCnpjInconsistentesImoveisNovosBean>();
		
		for(DadosImovelPreGsanHelper dadosImovelPreGsanHelper : colecaoCpfCnpjInconsistentes){
			CpfCnpjInconsistentesImoveisNovosBean cpfCnpjInconsistentesImoveisNovosBean = new CpfCnpjInconsistentesImoveisNovosBean();
			
			//Cpf/Cnpj
			if(dadosImovelPreGsanHelper.getCpfCnpj() != null && !dadosImovelPreGsanHelper.getCpfCnpj().equals("")){
				if(dadosImovelPreGsanHelper.getCpfCnpj().length() > 11){
					cpfCnpjInconsistentesImoveisNovosBean.setCpfCnpj(Util.formatarCnpj(dadosImovelPreGsanHelper.getCpfCnpj()));
				}else{
					cpfCnpjInconsistentesImoveisNovosBean.setCpfCnpj(Util.formatarCpf(dadosImovelPreGsanHelper.getCpfCnpj()));
				}
			}else{
				dadosImovelPreGsanHelper.setCpfCnpj("");
			}
			
			//Quadra
			if(dadosImovelPreGsanHelper.getNumeroQuadra() != null){
				cpfCnpjInconsistentesImoveisNovosBean.setQuadra(dadosImovelPreGsanHelper.getNumeroQuadra());
			}else{
				cpfCnpjInconsistentesImoveisNovosBean.setQuadra("");
			}
			
			//Setor
			if(dadosImovelPreGsanHelper.getCodigoSetorComercial() != null){
				cpfCnpjInconsistentesImoveisNovosBean.setSetor(dadosImovelPreGsanHelper.getCodigoSetorComercial());
			}else{
				cpfCnpjInconsistentesImoveisNovosBean.setSetor("");
			}
			
			//Lote
			if(dadosImovelPreGsanHelper.getNumeroLote() != null){
				cpfCnpjInconsistentesImoveisNovosBean.setLote(dadosImovelPreGsanHelper.getNumeroLote());
			}else{
				cpfCnpjInconsistentesImoveisNovosBean.setLote("");
			}
			
			//Endereco
			if(dadosImovelPreGsanHelper.getEnderecoFormatado() != null){
				cpfCnpjInconsistentesImoveisNovosBean.setEndereco(dadosImovelPreGsanHelper.getEnderecoFormatado());
			}else{
				cpfCnpjInconsistentesImoveisNovosBean.setEndereco("");
			}
						
			colecaoCpfCnpjInconsistentesBean.add(cpfCnpjInconsistentesImoveisNovosBean);
		}
		
		return colecaoCpfCnpjInconsistentesBean;	
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}
