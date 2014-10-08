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
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.bean.GerarRelatorioAnaliseImovelCorporativoGrandeHelper;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Este caso de uso gera relatório de análise do imóvel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */
public class RelatorioAnaliseImovelCorporativoGrande extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnaliseImovelCorporativoGrande(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ANALISE_IMOVEL_CORPORATIVO_GRANDE);
	}

	@Deprecated
	public RelatorioAnaliseImovelCorporativoGrande() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<Integer> idGerenciaRegional = (Collection<Integer>) getParametro("idGerenciaRegional");
		Collection<Integer> idUnidadeNegocio = (Collection<Integer>) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorComercialInicial = (Integer) getParametro("idSetorComercialInicial");
		Integer idSetorComercialFinal = (Integer) getParametro("idSetorComercialFinal");
		Integer selecionar = (Integer) getParametro("selecionar");
		Integer referencia = (Integer) getParametro("referencia");
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		Collection<Integer> idImovelPerfil = (Collection<Integer>)getParametro("idImovelPerfil");
		Collection<Integer> colecaoCategoria = (Collection<Integer>)getParametro("idCategoria");
		Collection<Integer> colecaoLigacaoEsgotoSituacao = (Collection<Integer>)getParametro("idLigacaoEsgotoSituacao");
		Collection<Integer> colecaoLigacaoAguaSituacao = (Collection<Integer>)getParametro("idLigacaoAguaSituacao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");	
		

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAnaliseImovelCorporativoGrandeBean relatorioBean = null;

		Collection colecaoRelatorioAnaliseImovelCorporativoGrandeHelper = fachada
				.pesquisarDadosRelatorioAnaliseImovelCorporativoGrande(
						idGerenciaRegional, idUnidadeNegocio,
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorComercialInicial, idSetorComercialFinal,
						referencia, idImovelPerfil, selecionar,
						colecaoCategoria, colecaoLigacaoAguaSituacao,
						colecaoLigacaoEsgotoSituacao);
		
		String matriculaAnterior=null;
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRelatorioAnaliseImovelCorporativoGrandeHelper != null
				&& !colecaoRelatorioAnaliseImovelCorporativoGrandeHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator = colecaoRelatorioAnaliseImovelCorporativoGrandeHelper
					.iterator();
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator.hasNext()) {

				GerarRelatorioAnaliseImovelCorporativoGrandeHelper helper = (GerarRelatorioAnaliseImovelCorporativoGrandeHelper) colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Gerência Regional
				String gerenciaRegional = "";

				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = helper.getIdGerenciaRegional().toString();
				}
				
				// Unidade de Negócio
				String unidadeNegocio = "";

				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = helper.getIdUnidadeNegocio().toString();
				}
				
				
				// Localidade
				String localidade = "";

				if (helper.getIdLocalidade() != null) {
					localidade = helper.getIdLocalidade().toString();
				}
				
				// Setor Comercial
				String idSetorComercial = "";
				if (helper.getIdSetorComercial() != null) {
					idSetorComercial = helper.getIdSetorComercial().toString();			
				}
				
				String setorComercial = "";
				if ( helper.getCodigoSetorComercial() != null ) {
					setorComercial = helper.getCodigoSetorComercial().toString();
				}
				
				//remove do contador os imoveis duplicados
				Integer imovelDuplicado = new Integer(0);
				
				// Imóvel, Endereço e Categoria
				String matriculaImovel = "";
				if (helper.getIdImovel() != null) {
					matriculaImovel = helper.getIdImovel().toString();
					Imovel imovel = new Imovel();
					imovel.setId(helper.getIdImovel());
					
					//Caso seja o mesmo imovel mas com a duas ligacoes, nao exibe a matricula duplicada. nem contabiliza no total.
					if ( matriculaAnterior != null &&matriculaImovel.equals(matriculaAnterior) ) {
						imovelDuplicado = new Integer(1);
					}
					matriculaAnterior = matriculaImovel;
				}
				
				// Capacidade do Hidrômetro
				String capacidadeHidrometro = "";
				
				if (helper.getCapacidadeHidrometro() != null) {
					capacidadeHidrometro = helper.getCapacidadeHidrometro();
				}			
				
				String consumoMedio = "";
				
				if (helper.getConsumoMedio() != null) {
					consumoMedio = helper.getConsumoMedio().toString();
				}
				
				String consumoFaturado = "";
				
				if (helper.getConsumoFaturado() != null) {
					consumoFaturado = helper.getConsumoFaturado().toString();
				}
				
				String tipoLigacao = "";
				
				if(helper.getIdTipoLigacao() != null){
					if(helper.getIdTipoLigacao().equals(1)){
						tipoLigacao = "ÁGUA";
					}else{
						tipoLigacao = "ESGOTO";
					}
				}
				
				String desvio = "";
				if (consumoMedio != null && !consumoMedio.equals("0") ){
					
					desvio = Util.formatarBigDecimalParaStringComVirgula( Util.subtrairBigDecimal(new BigDecimal(1), Util.formatarStringParaBigDecimal(consumoFaturado).divide(Util.formatarStringParaBigDecimal(consumoMedio), 2, BigDecimal.ROUND_HALF_UP)));
				}
				
				
				relatorioBean = new RelatorioAnaliseImovelCorporativoGrandeBean(

						// Unidade de Negócio
						unidadeNegocio,
						
						// Gerência Regional
						gerenciaRegional,

						// Localidade
						localidade,
						
						// Id do Setor Comercial
						idSetorComercial,
						
						// Setor Comercial
						setorComercial,
						
						// Imóvel
						matriculaImovel,
										
						// Capacidade do Hidrômetro
						capacidadeHidrometro,
						
						//Consumo Médio
						consumoMedio,
						
						//Consumo Faturado
						consumoFaturado,
						
						//Tipo de ligação
						tipoLigacao	,	
						
						//descricao Categoria
						helper.getCategoria(),
						
						//qtd Economia
						helper.getEconomia(),
					
						//valor faturado
						helper.getValorFaturado(),
						
						//cliente
						helper.getCliente(),
						
						//desvio
						desvio,
						
						//número da quadra
						helper.getNumeroQuadra(),
						
						//descrição da situação da ligação de água
						helper.getDescricaoLigacaoAguaSituacao(),
						
						//descrição do consumo anormalidade
						helper.getDescricaoConsumoAnormalidade(),
						
						//descrição da leitura anormalidade
						helper.getDescricaoLeituraAnormalidade(),
						
						//perfil
						helper.getIdPerfil(),
						
						//imovel duplicado
						imovelDuplicado,
						
						//descrição da localidade
						helper.getDescricaoLocalidade(),
						
						//volume de água
						helper.getVolumeAgua(),
						
						//volume de esgoto
						helper.getVolumeEsgoto(),
						
						//descrição da unidade de negócio
						helper.getDescricaoUnidade(),
						
						//descrição da gerência regional
						helper.getDescricaoGerencia(),
						
						//descrição do perfil do imóvel
						helper.getDescricaoPerfil()
						
				);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(referencia));		
		
		if(idUnidadeNegocio!=null && !idUnidadeNegocio.isEmpty()){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(FiltroUnidadeNegocio.ID, idUnidadeNegocio));		
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			String descricao = null;
			Iterator iteratorUnidadeNegocio = colecaoUnidadeNegocio.iterator();
			while ( iteratorUnidadeNegocio.hasNext() ) {
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) iteratorUnidadeNegocio.next();
				if ( descricao == null ) {
					descricao = unidadeNegocio.getNomeAbreviado();
				} else {
					descricao += " E " + unidadeNegocio.getNomeAbreviado();
				}
			}
			
			parametros.put("unidadeNegocio", descricao);
		}else{
			parametros.put("unidadeNegocio", "");
		}
		
		if(idLocalidadeInicial!=null){
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));		
			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidadeInicial, Localidade.class.getName());		
			Localidade localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			parametros.put("localidadeInicial",  localidadeInicial.getId() + "-" + localidadeInicial.getDescricao()) ;
			
		}else{
			parametros.put("localidadeInicial", "") ;
		}	
		
		if(idLocalidadeInicial!=null){
			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));		
			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidadeFinal, Localidade.class.getName());		
			Localidade localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);		
			parametros.put("localidadeFinal", localidadeFinal.getId() + "-" + localidadeFinal.getDescricao()) ;		
				
		}else{
			parametros.put("localidadeFinal", "") ;					
		}
		
		if(idGerenciaRegional!=null && !idGerenciaRegional.isEmpty()){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(FiltroGerenciaRegional.ID, idGerenciaRegional));		
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());		
			String descricao = null;
			Iterator iteratorGerenciaRegional = colecaoGerenciaRegional.iterator();
			while ( iteratorGerenciaRegional.hasNext() ) {
				GerenciaRegional gerenciaRegional = (GerenciaRegional) iteratorGerenciaRegional.next();
				if ( descricao == null ) {
					descricao = gerenciaRegional.getNomeAbreviado();
				} else {
					descricao += " E " + gerenciaRegional.getNomeAbreviado();
				}
			}
			
			parametros.put("gerenciaRegional", descricao);		
		}else{
			parametros.put("gerenciaRegional", "");		
		}
		
		
		if(idSetorComercialInicial!= null){
			FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercialInicial));		
			Collection colecaoSetorComercialInicial = fachada.pesquisar(filtroSetorComercialInicial, SetorComercial.class.getName());		
			SetorComercial setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
			parametros.put("setorComercialInicial",  setorComercialInicial.getId() + "-" + setorComercialInicial.getDescricao()) ;				
		}else{
			parametros.put("setorComercialInicial", "") ;				
		}
		
		if(idSetorComercialInicial!=null){
			FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
			filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercialInicial));		
			Collection colecaoSetorComercialFinal = fachada.pesquisar(filtroSetorComercialFinal, SetorComercial.class.getName());		
			SetorComercial setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);		
			parametros.put("setorComercialFinal",  setorComercialFinal.getId() + "-" + setorComercialFinal.getDescricao()) ;				
		}else{
			parametros.put("setorComercialFinal",  "") ;		
		}		
		
		if(idImovelPerfil!=null && !idImovelPerfil.isEmpty()){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(FiltroImovelPerfil.ID, idImovelPerfil));		
			Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());		
			String descricao = null;
			Iterator iteratorImovelPerfil = colecaoImovelPerfil.iterator();
			while ( iteratorImovelPerfil.hasNext() ) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) iteratorImovelPerfil.next();		
				if ( descricao == null ) {
					descricao = imovelPerfil.getDescricao() + "S";
				} else {
					descricao += " E " + imovelPerfil.getDescricao() +"S";
				}
			}
			
			parametros.put("imovelPerfil", descricao.toUpperCase()) ;
			
					
		}else{
			parametros.put("imovelPerfil", "") ;	
		}
		
		String descricaoOpcaoTotalizacao = "";
		if(opcaoTotalizacao != null){
			if(opcaoTotalizacao.equals("1")){
				descricaoOpcaoTotalizacao = "Estado";
			}else if(opcaoTotalizacao.equals("2")){
				descricaoOpcaoTotalizacao = "Estado por Gerência Regional";
			}else if(opcaoTotalizacao.equals("3")){
				descricaoOpcaoTotalizacao = "Estado por Unidade de Negócio";
			}else if(opcaoTotalizacao.equals("4")){
				descricaoOpcaoTotalizacao = "Estado por Perfil";
			}else if(opcaoTotalizacao.equals("5")){
				descricaoOpcaoTotalizacao = "Gerência Regional";
			}else if(opcaoTotalizacao.equals("6")){
				descricaoOpcaoTotalizacao = "Unidade de Negócio";
			}else if(opcaoTotalizacao.equals("7")){
				descricaoOpcaoTotalizacao = "Perfil";
			}
		}
		
		parametros.put("opcaoTotalizacao", opcaoTotalizacao);
		parametros.put("descricaoOpcaoTotalizacao", descricaoOpcaoTotalizacao);
		parametros.put("tipoFormatoRelatorio", "R0887");		
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANALISE_IMOVEL_CORPORATIVO_GRANDE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANALISE_IMOVEL_CORPORATIVO_GRANDE,
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
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliseImovelCorporativoGrande",
				this);
	}
}