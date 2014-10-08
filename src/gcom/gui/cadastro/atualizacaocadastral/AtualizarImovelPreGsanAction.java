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
package gcom.gui.cadastro.atualizacaocadastral;


import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1447] - Consultar Imóveis no Ambiente Pré-GSAN
 * 
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class AtualizarImovelPreGsanAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		//Exibe mensagem para o usuario, apos todo o processamento dos imóveis.
		boolean matriculaSemValidacao = false;
		String matriculaInscricaoDuplicidade = null;
		String matriculaInscricaoDuplicidadeAmbienteVirtual2 = null;
		String inscricaoInexistenteRemoverMatriculaIndicada = null;
		//IMOVEL NOVO
		if ( sessao.getAttribute("colecaoImovelPreGsan") != null ) {

			
			//RECUPERA TODOS OS NOVOS IMOVEIS.
			Collection<DadosImovelPreGsanHelper> colecaoImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelPreGsan");
			Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoImovelPreGsanHelper.iterator();
			while( iteratorHelper.hasNext() ) {
				
				DadosImovelPreGsanHelper dadosHelper = (DadosImovelPreGsanHelper) iteratorHelper.next();
				//RECUPERA O VALOR QUE O USUARIO INFORMOU PARA ALTERACAO DO IMOVEL
				//recupera o tipo de atualizacao que o usuario informou para o imovel.
				String valorAlteracao = (requestMap.get("alteracao"+ dadosHelper.getIdImovelAtualizacaoCadastral()))[0];
		
				//USUARIO SELECIONOU REMOVER O IMOVEL ATUALIZACAO CADASTRAL
				if ( valorAlteracao.equals("11") ) {
					//apaga os dados de atualizacao cadastral do imovel selecionado.
					//apaga todos os registros do imovel atualizacao cadastral.
					fachada.removerRegistroAtualizacaoCadastral(Integer.valueOf(dadosHelper.getIdImovelAtualizacaoCadastral()), null, Integer.valueOf(dadosHelper.getIdComando()));
				
				} //o sistema verifica se o imóvel é valido para atualizacao, desconsiderando todos os imóveis que estiverem com a situacao abaixo. 
				else if ( !valorAlteracao.equals("7") && //Logradouro novo, inexistente no GSAN 
						!valorAlteracao.equals("-1") && //nenhum valor informado
						!valorAlteracao.equals("8") && //Setor Comercial novo, inexistente no GSAN
						!valorAlteracao.equals("9") &&//Quadra nova, inexistente no GSAN 
						!valorAlteracao.equals("10") && //Imovel com inscrição em duplicidade
						!valorAlteracao.equals("12") && //Imóvel com inscrição em duplicidade no Ambiente Virtual 2
						!valorAlteracao.equals("3") // CNPJ/CPF Inconsistente
						) {

					//verifica 
					matriculaSemValidacao = existeMatriculaSemValidarCPFCNPJ(dadosHelper,  matriculaSemValidacao, valorAlteracao);
					if ( matriculaSemValidacao ) {
						String[] array = new String[1];
						throw new ActionServletException("atencao.falta_verificar_dados_imoveis_novos", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar",null, array);
					}
					matriculaInscricaoDuplicidade = validarInscricaoImovel(dadosHelper, "1", valorAlteracao, matriculaInscricaoDuplicidade);
					matriculaInscricaoDuplicidadeAmbienteVirtual2 = validarInscricaoImovelAmbienteVirtual2(dadosHelper, "1", valorAlteracao, matriculaInscricaoDuplicidadeAmbienteVirtual2);
					
					//Caso exista alguma critica para o imóvel, desconsidera o imovel e exibe a critica no final apos todos os imóveis sem criticas atualizados.
					if ( dadosHelper.getIndicadorDesconsiderarImoveldaAtualizacao().equals("2") ) {
					
						// atualiza os indicadores do imovel atualizacao cadastral 
						//imac_cdsituacao
						//imac_icdadosretorno  
						inscricaoInexistenteRemoverMatriculaIndicada = validarInscricaoInexistenteRemoverMatriculaIndicada(dadosHelper, valorAlteracao, matriculaInscricaoDuplicidadeAmbienteVirtual2);
						if ( dadosHelper.getIndicadorDesconsiderarImoveldaAtualizacao().equals("2") ) {
							
							fachada.atualizarSituacaoImovelAtualizacaoCadastralPreGsan(obterCodigoSituacao(valorAlteracao), obterIndicadorDadosRetorno(valorAlteracao), dadosHelper.getIdImovelAtualizacaoCadastral());
							
							if ( valorAlteracao.equals("2")  || valorAlteracao.equals("3") ) {
								//atualiza o imovel - permitindo gerar o roteiro.
								fachada.atualizarImovelSituacaoAtualizacaoCadastral(Integer.valueOf(dadosHelper.getMatricula()), Integer.valueOf(0));
							}
							
							//liberado para atualizacao e exista matricula associada
							if ( valorAlteracao.equals("1") && dadosHelper.getMatriculaGsan() != null && !dadosHelper.getMatriculaGsan().equals("") ) {
								
								//atualiza a matricula do imovel no ambiente virtual 2.
								fachada.associarImovelNovoAImovelExistenteAtualizacaoCadastral(dadosHelper);
							} 
						}
					}
				}
			}
		}//IMOVEL JA CADASTRADO 
		else if ( sessao.getAttribute("colecaoImovelCadastradoPreGsan") != null ) {
			//Atualiza os Imóveis com ocorrencia de cadastro
			Collection<DadosImovelPreGsanHelper> colecaoImovelCadastradoPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("colecaoImovelCadastradoPreGsan");
			
			Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoImovelCadastradoPreGsanHelper.iterator();
			
			while( iteratorHelper.hasNext() ) {
				
				DadosImovelPreGsanHelper dadosHelper = (DadosImovelPreGsanHelper) iteratorHelper.next();
				
				String valorAlteracao = (requestMap.get("alteracao"+ dadosHelper.getIdImovelAtualizacaoCadastral()))[0];
				
				//caso o usuario selecione remover o registro
				if ( valorAlteracao.equals("11") ) {
					//apaga todos os registros do imovel.
					boolean liberarImovelGSAN = fachada.removerRegistroAtualizacaoCadastral(Integer.valueOf(dadosHelper.getIdImovelAtualizacaoCadastral()), Integer.valueOf(dadosHelper.getMatricula()), Integer.valueOf(dadosHelper.getIdComando()));
					if(liberarImovelGSAN){
						//libera o imovel para um novo roteiro.
						fachada.atualizarImovelSituacaoAtualizacaoCadastral(Integer.valueOf(dadosHelper.getMatricula()), Integer.valueOf(0));
					}
				} else 
				//Valida se pode atualizar os dados do imovel.
				if ( !valorAlteracao.equals("7") &&  //Logradouro novo, inexistente no GSAN 
						!valorAlteracao.equals("-1") && //nenhum valor informado
						!valorAlteracao.equals("8") && //Setor Comercial novo, inexistente no GSAN
						!valorAlteracao.equals("9") && //Quadra nova, inexistente no GSAN
						!valorAlteracao.equals("10") && //Imovel com inscrição em duplicidade
						!valorAlteracao.equals("12")  && //Imóvel com inscrição em duplicidade no Ambiente Virtual 2
						!valorAlteracao.equals("3") // CNPJ/CPF Inconsistente
						) {
					
					//verifica
					matriculaSemValidacao = existeMatriculaSemValidarCPFCNPJ(dadosHelper,  matriculaSemValidacao, valorAlteracao);
					if ( matriculaSemValidacao ) {
						String[] array = new String[1];
						throw new ActionServletException("atencao.falta_verificar_dados_imoveis_ocorrencia_cadastro", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar",null, array);
					}
					
					matriculaInscricaoDuplicidade = validarInscricaoImovel(dadosHelper, "2", valorAlteracao, matriculaInscricaoDuplicidade);
					matriculaInscricaoDuplicidadeAmbienteVirtual2 = validarInscricaoImovelAmbienteVirtual2(dadosHelper, "2", valorAlteracao, matriculaInscricaoDuplicidadeAmbienteVirtual2);

					
					if ( dadosHelper.getIndicadorDesconsiderarImoveldaAtualizacao().equals("2") ) {
						
						fachada.atualizarSituacaoImovelAtualizacaoCadastralPreGsan(valorAlteracao, obterIndicadorDadosRetorno(valorAlteracao), dadosHelper.getIdImovelAtualizacaoCadastral());
						
						//retorna para campo
						if ( valorAlteracao.equals("2")  ) {
							fachada.atualizarImovelSituacaoAtualizacaoCadastral(Integer.valueOf(dadosHelper.getMatricula()), Integer.valueOf(0));
						}
					}
				}
			}
		} else {
			throw new ActionServletException("atencao.nao_existe_imovel_para_ser_atualizado");	
		}
		
		if ( matriculaSemValidacao ) {
			String[] array = new String[1];
			throw new ActionServletException("atencao.falta_verificar_dados_imoveis_novos", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar",null, array);
		}
		
		if ( matriculaInscricaoDuplicidadeAmbienteVirtual2 != null ) {
			String[] array = new String[1];
			array[0] = matriculaInscricaoDuplicidadeAmbienteVirtual2;
			throw new ActionServletException( "atencao.inscricao_imovel_cadastrado_ambiente_virtual", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar", null, array);
		}
		
		if ( matriculaInscricaoDuplicidade != null ) {
			String[] array = new String[1];
			array[0] = matriculaInscricaoDuplicidade;
			throw new ActionServletException( "atencao.imovel_inscricao_pa_cadastrada_gsan", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar", null, array);
		}
		if ( inscricaoInexistenteRemoverMatriculaIndicada != null ) {
			String[] array = new String[1];
			array[0] = inscricaoInexistenteRemoverMatriculaIndicada;
			throw new ActionServletException( "atencao.imovel_setor_ou_quadra_inexistente", "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar", null, array);
		}

		montarPaginaSucesso(httpServletRequest, "Atualizado com sucesso.",
				"Realizar outra Manutenção",
				"exibirConsultarImoveisPreGsanAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 *  Metodo responsavel por validar novos imoveis que nao tiveram o CPF/CNPJ validado.
	 *  
	 * @param dadosMovimentoHelper
	 * @param matriculaSemValidacao
	 * @param valorAlteracao
	 * @return
	 */
	public boolean existeMatriculaSemValidarCPFCNPJ( DadosImovelPreGsanHelper dadosMovimentoHelper , boolean matriculaSemValidacao, String valorAlteracao) {

		//[FE0008] - Verificar imóvel novo sem verificar CPF/CNPJ
		if ( dadosMovimentoHelper.getIndicadorCpfCnpjValidadoNaReceita().equals("2") //CPF/CNPJ NAO VALIDADO - se for igual a 2 quer dizer que o imóvel nao teve o cpf/cnpj validado
				//situacoes que o imóvel nao precisa de validacao do CPF/CNPJ
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("7") //Logradouro novo, inexistente no GSAN
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("8")//Setor Comercial novo, inexistente no GSAN
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("9")//Quadra nova, inexistente no GSAN
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("10")//Imovel com inscrição em duplicidade
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("11")//REMOVER IMOVEL
				&& !dadosMovimentoHelper.getIndicadorHabilitaTipoSituacao().equals("12")//Imóvel com inscrição em duplicidade no Ambiente Virtual 2
						&& (valorAlteracao.equals("1") ||  //liberado para atualizacao 
							valorAlteracao.equals("3") ||  //CNPJ/CPF Inconsistente
							valorAlteracao.equals("4") ||  //Sem CPF, Atualizar GSAN
							valorAlteracao.equals("13"))   //liberado para atualizao sem associar matricula existente ao imovel novo
							)  {


			matriculaSemValidacao = true;
			dadosMovimentoHelper.setIndicadorDesconsiderarImoveldaAtualizacao("1");
		} else {
			dadosMovimentoHelper.setIndicadorDesconsiderarImoveldaAtualizacao("2");

		}
		
		return matriculaSemValidacao;
	}
	
	/**
	 * Verifica qual o tipo de alterações libera o imovel para atualizacao.
	 * 
	 * @param valorAlteracao
	 * @return
	 */
	public String obterIndicadorDadosRetorno(String valorAlteracao) {
		String retorno = "3";
		
		//liberado para atualizar || sem cpf mais liberado para atualizar || retorna para campo || Remover Matrícula Indicada
		if ( valorAlteracao.equals("1") || valorAlteracao.equals("4") || valorAlteracao.equals("2") || valorAlteracao.equals("13") ) {
			
			//o imovel sai do pre-gsan e vai para o ambiente virtual 2
			retorno = "1";
		}
		
		return retorno;
	}
	

	/**
	 * Metodo responsavel por retornar o codigo da situacao que vai ser atualizado na tabela imovel atualizacao cadastral
	 * 
	 * @param valorAlteracao
	 * @return
	 */
	public String obterCodigoSituacao(String valorAlteracao) {
		
		//Remover matricula indicada, libera para atualizar no gsan, mas nao associa o imovel cadastrado no gsan ao imovel novo.
		if ( valorAlteracao.equals("13") ) {

			valorAlteracao = String.valueOf(ImovelAtualizacaoCadastral.REMOVER_MATRICULA_INDICADA);
		}
		
		return valorAlteracao;
	}
	
	
	/**
	 * 
	 * @param helper
	 * @param indicadorImovelNovo
	 * @param valorAlteracao
	 * @param matriculaInscricaoDuplicidade
	 * @return
	 */
	private String validarInscricaoInexistenteRemoverMatriculaIndicada(DadosImovelPreGsanHelper helper, 
			String valorAlteracao, 
			String inscricaoInexistenteRemoverMatriculaIndicada ) {
		
		//Caso a situacao seja diferente de Retornar para campo, valida a inscricao do imóvel.
		if ( !valorAlteracao.equals("2") ) {
			
			//Caso a inscricao do imovel seja valida no GSAN, valida a inscricao no ambiente virtual 2
			if ( helper.getIndicadorDesconsiderarImoveldaAtualizacao() != null && helper.getIndicadorDesconsiderarImoveldaAtualizacao().equals("2") ) {
				
				boolean existeInscricao = Fachada.getInstancia().verificarInscricaoInformadaValida(helper);
				
				if(!existeInscricao){
					
					if ( inscricaoInexistenteRemoverMatriculaIndicada != null ) {
						
						inscricaoInexistenteRemoverMatriculaIndicada = inscricaoInexistenteRemoverMatriculaIndicada +" <br/> Matrícula no pre-gsan: " +  helper.getMatricula() + " - Inscrição Inválida: " + helper.getInscricaoFormatada();
					} else {
						inscricaoInexistenteRemoverMatriculaIndicada = " <br/> Matrícula no pre-gsan: " +  helper.getMatricula() + " - Inscrição Inválida: " + helper.getInscricaoFormatada();
					}
					helper.setIndicadorDesconsiderarImoveldaAtualizacao("1");
				}else {
					helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
				}
			}
		} else {
			helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
		}
		return inscricaoInexistenteRemoverMatriculaIndicada;
	}
	
	/**
	 * Metodo responsavel por validar a inscricao do imóvel, evitando imóvel com inscricao em duplicidade no GSAN e no Ambiente virtual 2.
	 * 
	 * @param helper
	 * @param indicadorImovelNovo
	 * @param valorAlteracao
	 */
	private String validarInscricaoImovel(DadosImovelPreGsanHelper helper, String indicadorImovelNovo, String valorAlteracao, String matriculaInscricaoDuplicidade){

		//Caso a situacao seja diferente de Retornar para campo, valida a inscricao do imóvel.
		if ( !valorAlteracao.equals("2") ) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID, helper.getIdLocalidade()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO, helper.getCodigoSetorComercial()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO, helper.getNumeroQuadra()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOTE, helper.getNumeroLote()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SUBLOTE, helper.getNumeroSubLote()));
		
			if ( !indicadorImovelNovo.equals("1") ) {
				//se nao for imovel novo, informa a matricula do imovel.
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.ID, helper.getMatricula()));
			}	
			
			//se tiver matricula associada ao imovel, desconsidera a inscricao dessa matricula.
			//caso a alteração seja - Remover Matricula Indicada (13) o sistema nao considera a matricula associada.
			if ( helper.getMatriculaGsan() != null && !helper.getMatriculaGsan().equals("") && !valorAlteracao.equals("13")) {
				
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.ID, Integer.valueOf(helper.getMatriculaGsan())));
			}
			
			Collection<?> colImovel = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
			
			if(!Util.isVazioOrNulo(colImovel)){
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colImovel);
				if ( matriculaInscricaoDuplicidade != null ) {
					
					matriculaInscricaoDuplicidade = matriculaInscricaoDuplicidade +" <br/> Matrícula no pre-gsan: " +  helper.getMatricula() + " - Inscrição em duplicidade: " + helper.getInscricaoFormatada() + " - Matricula no GSAN: "+ imovel.getId();;
				} else {
					matriculaInscricaoDuplicidade = "<br/> Matrícula no pre-gsan: " +  helper.getMatricula() + " - Inscrição em duplicidade: " + helper.getInscricaoFormatada() + " - Matricula no GSAN: "+ imovel.getId();;
				}
				helper.setIndicadorDesconsiderarImoveldaAtualizacao("1");
			}else {
				helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
			}
			
		} else {
			helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
		}
		return matriculaInscricaoDuplicidade;
	}
	
	/**
	 * Metodo responsavel por validar a inscricao do imóvel, evitando imóvel com inscricao em duplicidade no GSAN e no Ambiente virtual 2.
	 * 
	 * @param helper
	 * @param indicadorImovelNovo
	 * @param valorAlteracao
	 */
	private String validarInscricaoImovelAmbienteVirtual2(DadosImovelPreGsanHelper helper, String indicadorImovelNovo, String valorAlteracao, String matriculaInscricaoDuplicidadeAmbienteVirtual2 ){
		
		//Caso a situacao seja diferente de Retornar para campo, valida a inscricao do imóvel.
		if ( !valorAlteracao.equals("2")  ) {
			
			//Caso a inscricao do imovel seja valida no GSAN, valida a inscricao no ambiente virtual 2
			if ( helper.getIndicadorDesconsiderarImoveldaAtualizacao() != null && helper.getIndicadorDesconsiderarImoveldaAtualizacao().equals("2") ) {
				
				FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_LOCALIDADE, helper.getIdLocalidade()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercial()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.NUMERO_QUADRA, helper.getNumeroQuadra()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.LOTE, helper.getNumeroLote()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.SUB_LOTE, helper.getNumeroSubLote()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovelAtualizacaoCadastral.IMOVEL, helper.getMatricula()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, ConstantesSistema.SIM));
				
				Collection<?> colImovelAtualizacaoCadastral = this.getFachada().pesquisar(
						filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
				if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
					
					ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(colImovelAtualizacaoCadastral);
					
					if ( matriculaInscricaoDuplicidadeAmbienteVirtual2 != null ) {
						matriculaInscricaoDuplicidadeAmbienteVirtual2 = matriculaInscricaoDuplicidadeAmbienteVirtual2 +" <br/> Matrícula no pre-gsan: " +  helper.getMatricula() + " - Inscrição em duplicidade: " + helper.getInscricaoFormatada() + " - Matricula no ambiente virtual 2: "+ imovelAtualizacaoCadastral.getImovel();
					} else {
						matriculaInscricaoDuplicidadeAmbienteVirtual2 ="<br/> Matrícula no pre-gsan: " +  helper.getMatricula() +" - Inscrição em duplicidade: " + helper.getInscricaoFormatada() + " - Matricula no ambiente virtual 2: "+ imovelAtualizacaoCadastral.getImovel() ;
					}
					helper.setIndicadorDesconsiderarImoveldaAtualizacao("1");
				} else {
					helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
				}
			}
		} else {
			helper.setIndicadorDesconsiderarImoveldaAtualizacao("2");
		}
			
		
		return matriculaInscricaoDuplicidadeAmbienteVirtual2;
	}

}
