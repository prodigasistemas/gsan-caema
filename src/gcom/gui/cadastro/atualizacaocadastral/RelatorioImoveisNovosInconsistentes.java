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
 * Gerar Relatorio Imoveis Inconsistentes para Imoveis novos
 * 
 * @author Anderson Cabral
 * @date 08/08/2013
 */
public class RelatorioImoveisNovosInconsistentes extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisNovosInconsistentes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_NOVOS_INCONSISTENTES);
	}

	public RelatorioImoveisNovosInconsistentes(Usuario usuario, String nomeRelatorio) {
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

		String empresa 		  				= (String) getParametro("empresa");
		String tipoSelecao   				= (String) getParametro("tipoSelecao");
		String idLocalidade 				= (String) getParametro("idLocalidade");
		String descricaoLocalidade 			= (String) getParametro("descricaoLocalidade");
		String codigoSetorComercial 		= (String) getParametro("codigoSetorComercial");
		String descricaoSetorComecial 		= (String) getParametro("descricaoSetorComecial");
		String numeroQuadras 				= (String) getParametro("numeroQuadras");
		String descricaoCadastroOcorrencia  = (String) getParametro("descricaoCadastroOcorrencia");
		int tipoRelatorio 					= (Integer) getParametro("tipoFormatoRelatorio");	
		String total = "0";
		
		ArrayList<DadosImovelPreGsanHelper> colecaoImoveisInconsistentes = (ArrayList<DadosImovelPreGsanHelper>) getParametro("colecaoImoveisInconsistentes");
		
		ArrayList<ImovelInconsistenteBean> colecaoImoveisInconsistentesBean = montaBean(colecaoImoveisInconsistentes);
		
		if(colecaoImoveisInconsistentesBean != null && !colecaoImoveisInconsistentesBean.isEmpty()){
			total = "" + colecaoImoveisInconsistentesBean.size();
		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", empresa);
		parametros.put("tipoSelecao", tipoSelecao);
		parametros.put("idLocalidade", idLocalidade);
		parametros.put("descricaoLocalidade", descricaoLocalidade);
		parametros.put("codigoSetorComercial", codigoSetorComercial);
		parametros.put("descricaoSetorComecial", descricaoSetorComecial);
		parametros.put("numeroQuadras", numeroQuadras);
		parametros.put("descricaoCadastroOcorrencia", descricaoCadastroOcorrencia);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1447-B");
		parametros.put("total", total);
		
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoImoveisInconsistentesBean);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_NOVOS_INCONSISTENTES, parametros, ds, tipoRelatorio);
		
		return retorno;
	}
	
	private ArrayList<ImovelInconsistenteBean> montaBean(ArrayList<DadosImovelPreGsanHelper> colecaoImoveisInconsistentes){
		
		ArrayList<ImovelInconsistenteBean> colecaoImovelInconsistenteBean = new ArrayList<ImovelInconsistenteBean>();
		
		for(DadosImovelPreGsanHelper dadosImovelPreGsanHelper : colecaoImoveisInconsistentes){
			ImovelInconsistenteBean imovelInconsistenteBean = new ImovelInconsistenteBean();
			
			//Setor
			if(dadosImovelPreGsanHelper.getCodigoSetorComercial() != null){
				imovelInconsistenteBean.setSetor(dadosImovelPreGsanHelper.getCodigoSetorComercial());
			}else{
				imovelInconsistenteBean.setSetor("");
			}
			
			//Quadra
			if(dadosImovelPreGsanHelper.getNumeroQuadra() != null){
				imovelInconsistenteBean.setQuadra(dadosImovelPreGsanHelper.getNumeroQuadra());
			}else{
				imovelInconsistenteBean.setQuadra("");
			}
			
			//Lote
			if(dadosImovelPreGsanHelper.getNumeroLote() != null){
				imovelInconsistenteBean.setNumeroLote(dadosImovelPreGsanHelper.getNumeroLote());
			}else{
				imovelInconsistenteBean.setNumeroLote("");
			}
			
			//Cpf/Cnpj
			if(dadosImovelPreGsanHelper.getCpfCnpj() != null && !dadosImovelPreGsanHelper.getCpfCnpj().equals("")){
				if(dadosImovelPreGsanHelper.getCpfCnpj().length() > 11){
					imovelInconsistenteBean.setCpfCnpj(Util.formatarCnpj(dadosImovelPreGsanHelper.getCpfCnpj()));
				}else{
					imovelInconsistenteBean.setCpfCnpj(Util.formatarCpf(dadosImovelPreGsanHelper.getCpfCnpj()));
				}
			}else{
				dadosImovelPreGsanHelper.setCpfCnpj("");
			}
			
			//Situacao
			if(dadosImovelPreGsanHelper.getSituacao() != null){
				imovelInconsistenteBean.setSituacao(dadosImovelPreGsanHelper.getSituacao());
			}else{
				imovelInconsistenteBean.setSituacao("");
			}
			
			//Endereco
			if(dadosImovelPreGsanHelper.getEnderecoFormatado() != null){
				imovelInconsistenteBean.setEnderecoFormatado(dadosImovelPreGsanHelper.getEnderecoFormatado());
			}else{
				imovelInconsistenteBean.setEnderecoFormatado("");
			}
					
			//Matriculas
			if(dadosImovelPreGsanHelper.getColecaoMatriculaGsan() != null && dadosImovelPreGsanHelper.getColecaoMatriculaGsan().size() > 0){
				ArrayList<Integer> colecaoMatriculas = (ArrayList<Integer>) dadosImovelPreGsanHelper.getColecaoMatriculaGsan();
				String matriculas = "";
				for(Integer matricula : colecaoMatriculas){
					matriculas += Util.retornaMatriculaImovelFormatada(matricula) + ", ";
				}
				
				imovelInconsistenteBean.setMatriculaGsan(matriculas.substring(0, matriculas.length() - 2));
			}else{
				dadosImovelPreGsanHelper.setMatriculaGsan("");
			}
			
			//Login Cadastrador
			if(dadosImovelPreGsanHelper.getLoginCadastrador() != null){
				imovelInconsistenteBean.setLoginCadastrador(dadosImovelPreGsanHelper.getLoginCadastrador());
			}else{
				imovelInconsistenteBean.setLoginCadastrador("");
			}
			
			//Nome Cadastrador
			if(dadosImovelPreGsanHelper.getNomeCadastrador() != null){
				imovelInconsistenteBean.setNomeCadastrador(dadosImovelPreGsanHelper.getNomeCadastrador());
			}else{
				imovelInconsistenteBean.setNomeCadastrador("");
			}
			
			//Id Cadastro Ocorrencia
			if(dadosImovelPreGsanHelper.getIdCadastroOcorrencia() != null){
				imovelInconsistenteBean.setIdCadastroOcorrencia(dadosImovelPreGsanHelper.getIdCadastroOcorrencia());
			}else{
				imovelInconsistenteBean.setIdCadastroOcorrencia("");
			}
			
			//Descricao Cadastro Ocorrencia
			if(dadosImovelPreGsanHelper.getDescricaoCadastroOcorrencia() != null){
				imovelInconsistenteBean.setDescricaoCadastroOcorrencia(dadosImovelPreGsanHelper.getDescricaoCadastroOcorrencia());
			}else{
				imovelInconsistenteBean.setDescricaoCadastroOcorrencia("");
			}
			
			colecaoImovelInconsistenteBean.add(imovelInconsistenteBean);
		}
		
		return colecaoImovelInconsistenteBean;	
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}
