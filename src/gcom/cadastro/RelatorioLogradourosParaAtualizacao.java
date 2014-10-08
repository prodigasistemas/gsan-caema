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
* Anderson Cabral do Nascimento
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
package gcom.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.ImovelEnderecoArquivoTextoBean;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class RelatorioLogradourosParaAtualizacao extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioLogradourosParaAtualizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LOGRADOUROS_PARA_ATUALIZACAO);
	}

	public RelatorioLogradourosParaAtualizacao(Usuario usuario, String nomeRelatorio) {
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
		ArrayList<ImovelEnderecoArquivoTexto> colecaoArquivoLogradouro = (ArrayList<ImovelEnderecoArquivoTexto>) getParametro("colecaoArquivoLogradouro");
		String nomeMunicipio = (String) getParametro("nomeMunicipio");
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String subtitulo = (String) getParametro("subtitulo");
		
		ArrayList<ImovelEnderecoArquivoTextoBean> colecaoImovelEnderecoArquivoTextoBean = montaBean(colecaoArquivoLogradouro);
		
		String total = "0";
		if(colecaoImovelEnderecoArquivoTextoBean != null && !colecaoImovelEnderecoArquivoTextoBean.isEmpty()){
			total = "" + colecaoImovelEnderecoArquivoTextoBean.size();
		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nomeMunicipio", nomeMunicipio);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1561");
		parametros.put("total", total);
		parametros.put("subtitulo", subtitulo);
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoImovelEnderecoArquivoTextoBean);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_LOGRADOUROS_PARA_ATUALIZACAO, parametros, ds, tipoRelatorio);
		
		
		return retorno;
	}
	
	private ArrayList<ImovelEnderecoArquivoTextoBean> montaBean(ArrayList<ImovelEnderecoArquivoTexto> colecaoArquivoLogradouro){
		Fachada fachada = Fachada.getInstancia();
		
		ArrayList<ImovelEnderecoArquivoTextoBean> colecaoImovelEnderecoArquivoTextoBean = new ArrayList<ImovelEnderecoArquivoTextoBean>();
		
		for(ImovelEnderecoArquivoTexto imovelEnderecoArquivoTexto : colecaoArquivoLogradouro){
			ImovelEnderecoArquivoTextoBean imovelEnderecoArquivoTextoBean = new ImovelEnderecoArquivoTextoBean();
			
			//ID Logradouro
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getLogradouro() != null && imovelEnderecoArquivoTexto.getLogradouro().getId() != null){
				imovelEnderecoArquivoTextoBean.setIdLogradouro(imovelEnderecoArquivoTexto.getLogradouro().getId().toString());
			}else{
				imovelEnderecoArquivoTextoBean.setIdLogradouro("");
			}
			
			//Nome Logradouro
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getLogradouro() != null && imovelEnderecoArquivoTexto.getLogradouro().getNome() != null){
				String nomeLogradouro = "";
				
				//tipo
				if(imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTipo() != null
						&& imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTipo().getId() != null){
					
					FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo();
					filtroLogradouroTipo.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTipo().getId()));
					Collection<LogradouroTipo> colecaoLogradouroTipo = fachada.pesquisar(filtroLogradouroTipo, LogradouroTipo.class.getName());
					LogradouroTipo logradouroTipo = (LogradouroTipo) Util.retonarObjetoDeColecao(colecaoLogradouroTipo);
					
					if(logradouroTipo != null){
						nomeLogradouro += " " + logradouroTipo.getDescricao();
					}
				}
				
				//titulo
				if(imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTitulo() != null
						&& imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTitulo().getId() != null){
					
					FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo();
					filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(filtroLogradouroTitulo.ID, imovelEnderecoArquivoTexto.getLogradouro().getLogradouroTitulo().getId()));
					Collection<LogradouroTitulo> colecaoLogradouroTitulo = fachada.pesquisar(filtroLogradouroTitulo, LogradouroTitulo.class.getName());
					LogradouroTitulo logradouroTitulo = (LogradouroTitulo) Util.retonarObjetoDeColecao(colecaoLogradouroTitulo);
					
					if(logradouroTitulo != null){
						nomeLogradouro += " " + logradouroTitulo.getDescricao();
					}
				}
				
				//nome
				nomeLogradouro += " " + imovelEnderecoArquivoTexto.getLogradouro().getNome();
				
				imovelEnderecoArquivoTextoBean.setNomeLogradouro(nomeLogradouro);
			}else{
				imovelEnderecoArquivoTextoBean.setNomeLogradouro("");
			}
			
			//Matricula Imovel
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getImovel() != null && imovelEnderecoArquivoTexto.getImovel().getId() != null){
				imovelEnderecoArquivoTextoBean.setMatriculaImovel(imovelEnderecoArquivoTexto.getImovel().getId().toString());
			}else{
				imovelEnderecoArquivoTextoBean.setMatriculaImovel("");
			}
			
			//Inscricao Imovel
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getImovel() != null && imovelEnderecoArquivoTexto.getImovel().getInscricaoFormatada() != null){
				imovelEnderecoArquivoTextoBean.setInscricaoImovel(imovelEnderecoArquivoTexto.getImovel().getInscricaoFormatada());
			}else{
				imovelEnderecoArquivoTextoBean.setInscricaoImovel("");
			}
			
			//Numero Imovel
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getNumeroImovel() != null){
				imovelEnderecoArquivoTextoBean.setNumeroImovel(imovelEnderecoArquivoTexto.getNumeroImovel());
			}else{
				imovelEnderecoArquivoTextoBean.setNumeroImovel("");
			}
			
			//Complemento Endereco Imovel
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getComplementoEndereco() != null){
				imovelEnderecoArquivoTextoBean.setComplementoEndereco(imovelEnderecoArquivoTexto.getComplementoEndereco());
			}else{
				imovelEnderecoArquivoTextoBean.setComplementoEndereco("");
			}
			
			//Nome do Bairro
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getBairro() != null && imovelEnderecoArquivoTexto.getBairro().getNome() != null){
				imovelEnderecoArquivoTextoBean.setNomeBairro(imovelEnderecoArquivoTexto.getBairro().getNome());
			}else{
				imovelEnderecoArquivoTextoBean.setNomeBairro("");
			}
			
			//Nome do Municipio
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getLogradouro() != null && imovelEnderecoArquivoTexto.getLogradouro().getMunicipio() != null
					&& imovelEnderecoArquivoTexto.getLogradouro().getMunicipio().getNome() != null){
				imovelEnderecoArquivoTextoBean.setNomeMunicipio(imovelEnderecoArquivoTexto.getLogradouro().getMunicipio().getNome());
			}else{
				imovelEnderecoArquivoTextoBean.setNomeMunicipio("");
			}
			
			//CEP Imovel
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getCep() != null && imovelEnderecoArquivoTexto.getCep().getCepFormatado() != null){
				imovelEnderecoArquivoTextoBean.setCodigoCep(imovelEnderecoArquivoTexto.getCep().getCepFormatado());
			}else{
				imovelEnderecoArquivoTextoBean.setCodigoCep("");
			}
			
			//Data da inclusao do logradouro
			if(imovelEnderecoArquivoTexto != null && imovelEnderecoArquivoTexto.getUltimaAlteracao() != null){
				imovelEnderecoArquivoTextoBean.setData(Util.formatarDataComHora(imovelEnderecoArquivoTexto.getUltimaAlteracao()));
			}else{
				imovelEnderecoArquivoTextoBean.setData("");
			}
			
			colecaoImovelEnderecoArquivoTextoBean.add(imovelEnderecoArquivoTextoBean);
		}
		
		Collections.sort(colecaoImovelEnderecoArquivoTextoBean, new ImoveisComparator());
		
		return colecaoImovelEnderecoArquivoTextoBean;	
	}
	
	class ImoveisComparator implements Comparator<ImovelEnderecoArquivoTextoBean>{  
	    public int compare(ImovelEnderecoArquivoTextoBean o1, ImovelEnderecoArquivoTextoBean o2) {  
	       int idLogradouro = o1.getIdLogradouro().compareTo(o2.getIdLogradouro());  
	       if (idLogradouro != 0) return idLogradouro; //Caso o ID dos logradouro sejam diferentes, ordena pelo id do Logradouro.  
	       
	       //Se forem iguais, ordena pela inscricao do imovel.  
	       int prof = o1.getInscricaoImovel().compareTo(o2.getInscricaoImovel());  
	       if (prof != 0) return prof;
	       
	       return 0;
	    }
	} 
	
	@Override
	public void agendarTarefaBatch() {
		
	}
	
}
