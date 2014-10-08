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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.AreaOperacional;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.ZonaPressao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe respon�vel pela conclus�o do cadastro de um im�vel 
 *
 * @author Raphael Rossiter
 * @date 11/05/2009
 */
public class InserirImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) sessao
				.getAttribute("InserirImovelActionForm");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Cria Variaveis
		String idLocalidade = (String) inserirImovelActionForm.get("idLocalidade");
		String idSetorComercial = (String) inserirImovelActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelActionForm.get("idQuadra");
		String idQuadraFace = (String) inserirImovelActionForm.get("idQuadraFace");
		String lote = (String) inserirImovelActionForm.get("lote");
		String subLote = (String) inserirImovelActionForm.get("subLote");
		String testadaLote = (String) inserirImovelActionForm.get("testadaLote");
		String areaConstruida = (String) inserirImovelActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelActionForm.get("faixaAreaConstruida");
		String volumeReservatorioSuperior = (String) inserirImovelActionForm.get("reservatorioSuperior");
		String faixaVolumeReservatorioSuperior = (String) inserirImovelActionForm.get("faixaResevatorioSuperior");
		String volumeReservatorioInferior = (String) inserirImovelActionForm.get("reservatorioInferior");
		String faixaVolumeReservaotirio = (String) inserirImovelActionForm.get("faixaReservatorioInferior");
		String piscina = (String) inserirImovelActionForm.get("piscina");
		String jardim = (String) inserirImovelActionForm.get("jardim");
		String faixaPiscina = (String) inserirImovelActionForm.get("faixaPiscina");
		String pavimentoCalcada = (String) inserirImovelActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelActionForm.get("situacaoLigacaoEsgoto");
		String perfilImovel = (String) inserirImovelActionForm.get("perfilImovel");
		String poco = (String) inserirImovelActionForm.get("poco");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelActionForm.get("idLigacaoEsgotoEsgotamento");
		String subsistemaID = (String) inserirImovelActionForm.get("subsistemaID");

		String tipoHabitacao = (String) inserirImovelActionForm.get("imovelTipoHabitacao");
		String tipoPropriedade = (String) inserirImovelActionForm.get("imovelTipoPropriedade");
		String tipoConstrucao = (String) inserirImovelActionForm.get("imovelTipoConstrucao");
		String tipoCobertura = (String) inserirImovelActionForm.get("imovelTipoCobertura");
		
		String pontoUtilizaco = (String) inserirImovelActionForm.get("numeroPontos");
		String numeroMoradores = (String) inserirImovelActionForm.get("numeroMoradores");
		String numeroIptu = (String) inserirImovelActionForm.get("numeroIptu");
		String numeroContratoCelpe = (String) inserirImovelActionForm.get("numeroContratoCelpe");
		String imovelContaEnvioForm = (String) inserirImovelActionForm.get("imovelContaEnvio");
		String cordenadasX = (String) inserirImovelActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelActionForm.get("cordenadasUtmY");
		String extratoResponsavel = (String) inserirImovelActionForm.get("extratoResponsavel");
		// String nomeContaForm = (String) inserirImovelActionForm
		// .get("tipoOcupacao");
		String tipoDespejo = (String) inserirImovelActionForm.get("tipoDespejo");
		String idImovelPrincipal = (String) inserirImovelActionForm.get("idImovel");
		String sequencialRota = (String) inserirImovelActionForm.get("sequencialRota");
		String sequencialRotaEntrega = (String) inserirImovelActionForm.get("sequencialRotaEntrega");
		String idRotaEntrega = (String) inserirImovelActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelActionForm.get("idRotaAlternativa");
		String idFuncionario = (String) inserirImovelActionForm.get("idFuncionario"); 
		String numeroMedidorEnergia = (String) inserirImovelActionForm.get("numeroMedidorEnergia");
		String dataVisitaComercialInformada  = (String) inserirImovelActionForm.get("dataVisitaComercial");
		String numeroQuadraEntrega = (String) inserirImovelActionForm.get("numeroQuadraEntrega");
		
		String informacoesComplementares = (String) inserirImovelActionForm.get("informacoesComplementares");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelActionForm.get("indicadorNivelInstalacaoEsgoto");
		
		String sistemaAbastecimento = (String) inserirImovelActionForm.get("sistemaAbastecimento");
		String subsistemaAbastecimento = (String) inserirImovelActionForm.get("subsistemaAbastecimento");
		String setorAbastecimento = (String) inserirImovelActionForm.get("setorAbastecimento");
		String distritoOperacional = (String) inserirImovelActionForm.get("distritoOperacional");
		String areaOperacional = (String) inserirImovelActionForm.get("areaOperacional");
		String zonaPressao = (String) inserirImovelActionForm.get("zonaPressao");
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		Imovel imovel = new Imovel();
		
		// ABA LOCALIDADE
		ImovelAbaLocalidadeHelper helperLocalidade = new ImovelAbaLocalidadeHelper();
		helperLocalidade.setIdLocalidade(idLocalidade);
		helperLocalidade.setCodigoSetorComercial(idSetorComercial);
		helperLocalidade.setNumeroQuadra(idQuadra);
		helperLocalidade.setIdQuadraFace(idQuadraFace);
		helperLocalidade.setLote(lote);
		helperLocalidade.setSublote(subLote);
		ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade = fachada.validarImovelAbaLocalidade(helperLocalidade);
		
		imovel.setLocalidade(resultadoAbaLocalidade.getLocalidade());
		imovel.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		imovel.setQuadra(resultadoAbaLocalidade.getQuadra());
		imovel.setQuadraFace(resultadoAbaLocalidade.getQuadraFace());
		imovel.setLote(new Short(lote).shortValue());
		imovel.setSubLote(new Short(subLote).shortValue());

		if (naoVazio(testadaLote)) {
			imovel.setTestadaLote(new Short(testadaLote).shortValue());
		}
		if (naoVazio(sequencialRota)) {
			imovel.setNumeroSequencialRota(new Integer(sequencialRota));
		}
		
		// ABA ENDERECO
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente � invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
					Object obj = (Object) colecaoEnderecos.iterator().next();
					if (!(obj instanceof Imovel)) {
						sessao.removeAttribute("colecaoEnderecos");
					}
					if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
						colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
					}
				}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
				}
			}
		}
		//**********************************************************************
		
		ImovelAbaEnderecoHelper helperEndereco = new ImovelAbaEnderecoHelper();
		helperEndereco.setImovelEnderecos(colecaoEnderecos);
		helperEndereco.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperEndereco.setUsuarioLogado(usuario);
		helperEndereco.setIdFuncionalidade(Funcionalidade.INSERIR_IMOVEL);
		
		fachada.validarImovelAbaEndereco(helperEndereco);
		
		Imovel imovelEndereco = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
		
		Logradouro logradouro = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getLogradouro() != null && 
			!imovelEndereco.getLogradouroCep().getLogradouro().equals("")) {
			Integer idLogradouro = imovelEndereco.getLogradouroCep().getLogradouro().getId();
			logradouro = new Logradouro();
			logradouro.setId(idLogradouro);
		} else {
			// ALTERA��O FEITTA PARA FUNCIONAMENTO DA APLICA��O, APAGAR
			// A CONDI��O DO "SE N�O",
			// ALTERA��O DO VALOR DO LOGRADOURO ESTA SENDO ANALIZADA POR ARYED,
			// ANA E LEO NARDO NO DIA 16/03/2006
			logradouro = new Logradouro();
			logradouro.setId(new Integer("0"));
		}
		
		LogradouroCep logradouroCep = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getCep() != null) {
			Integer cep = imovelEndereco.getLogradouroCep().getCep().getCepId();
			Cep cepObj = new Cep();
			cepObj.setCepId(cep);
			logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cepObj.getCepId(), logradouro.getId());
		}
		
		EnderecoReferencia enderecoReferencia = null;
		if (imovelEndereco.getEnderecoReferencia() != null) {
			Integer idEnderecoReferencia = imovelEndereco.getEnderecoReferencia().getId();
			if (idEnderecoReferencia != null && !idEnderecoReferencia.toString().trim().equals("")) {
				enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId(idEnderecoReferencia);
			}
		}
		
		imovel.setNumeroImovel(imovelEndereco.getNumeroImovel());
		imovel.setComplementoEndereco(imovelEndereco.getComplementoEndereco());
		imovel.setLogradouroCep(logradouroCep);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
		imovel.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
		imovel.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
		
		// ABA CLIENTES
		
		//Par�metros do Sistema, com Cliente Desconhecido associado
		//==========================================================
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteUsuarioDesconhecido");
		
		Collection colecaoSis = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)Util.retonarObjetoDeColecao(colecaoSis);
		//===========================================================
		
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		int validar = fachada.validarImovelAbaCliente(clientes, usuario);
		
		if(validar == -1){
			throw new ActionServletException("atencao.imovel_cliente_sem_documento");
		}
		
		//[FS0035] - Associar cliente usu�rio desconhecido
		else if(validar == 1){
			
			Cliente clienteUsuarioDesconhecido = sistemaParametro.getClienteUsuarioDesconhecido();
			
			//Caso o cliente usu�rio n�o seja informado e n�o 
			//exista parametriza��o do cliente usu�rio desconhecido
			if(clienteUsuarioDesconhecido == null){
				throw new ActionServletException("atencao.required", null, "Um Cliente do tipo Usu�rio");
			}
			else{
				if(clientes == null)
					clientes = new ArrayList();
					
				//Cliente Relacao Tipo Usu�rio
				FiltroClienteRelacaoTipo filtroClienteRelacao = new FiltroClienteRelacaoTipo();
				filtroClienteRelacao.adicionarParametro(new ParametroSimples("id",ClienteRelacaoTipo.USUARIO));
				
				ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo)Util
						.retonarObjetoDeColecao(fachada.pesquisar(filtroClienteRelacao,ClienteRelacaoTipo.class.getName()));

		        //inicializa o cliente imovel
		        ClienteImovel clienteImovel = new ClienteImovel(new Date(), null, null,
		        		clienteUsuarioDesconhecido, clienteRelacaoTipo);
		        
		        //Coloca a data de ultima altera��o para identificar o objeto
		        clienteImovel.setUltimaAlteracao(new Date());
		        
		        if(clientes.isEmpty())
		        	clienteImovel.setIndicadorNomeConta(new Short((short)1));
		        else{
		        	boolean indicadorMarcado = false;
		        	Iterator iteratorCliente = clientes.iterator();
		    		while(iteratorCliente.hasNext()){
		    			ClienteImovel clienteImovelIt = (ClienteImovel)iteratorCliente.next();
		    			if(clienteImovelIt.getIndicadorNomeConta().compareTo(new Short((short)1)) == 0){
		    				indicadorMarcado = true;
		    			}
		    		}
		    		if(indicadorMarcado)
		    			clienteImovel.setIndicadorNomeConta(new Short((short)2));
		    		else
		    			clienteImovel.setIndicadorNomeConta(new Short((short)1));
		        }
		        
		        clientes.add(clienteImovel);
			}
		}
		
		// ABA SUBCATEGORIA ECONOMIAS
        Collection subcategorias = (Collection) sessao.getAttribute("colecaoImovelSubCategorias");
       
        Collection ramosAtividades = (Collection) sessao.getAttribute("colecaoImovelRamosAtividade");
		fachada.validarAbaInserirImovelSubcategoria(
				subcategorias, PermissaoEspecial.INSERIR_IMOVEL_PARA_ORGAO_PUBLICO, usuario);
		
		// ABA CARACTERISTICA
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		helperCaracteristica.setIdSubsistema(subsistemaID);	
		
		
		//*************************************************
		// Autor: Ivan Sergio
		// Data: 23/04/2009
		// CRC1657
		//*************************************************
		// [FS0023] - Verificar Setor e Quadra
		//*************************************************
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		//*************************************************
		
		// Autor: Nathalia santos
		// Data: 13/07/2011
		// RR201106690
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		//*************************************************
		
		//[FS0036] - Verificar preenchimento do cliente usu�rio
		Cliente clienteUsuarioDesconhecido = sistemaParametro.getClienteUsuarioDesconhecido();
		Iterator iteratorCliente = clientes.iterator();
		while(iteratorCliente.hasNext()){
			ClienteImovel clienteImovelIt = (ClienteImovel)iteratorCliente.next();
			
			//Caso o cliente usu�rio seja o cliente parametrizado como "Cliente Usu�rio Desconhecido"
			//e a situa��o da liga��o de �gua ou esgoto informado seja diferente de potencial e fact�vel
			if(clienteImovelIt.getClienteRelacaoTipo().getId().compareTo(new Integer(ClienteRelacaoTipo.USUARIO)) == 0){
				if(clienteImovelIt.getCliente() != null &&
				   clienteImovelIt.getCliente().getId().compareTo(clienteUsuarioDesconhecido.getId()) == 0 && 
				   !(
						   (situacaoLigacaoAgua.equals(LigacaoAguaSituacao.FACTIVEL.toString()) ||
							situacaoLigacaoAgua.equals(LigacaoAguaSituacao.POTENCIAL.toString())) &&
						   (situacaoLigacaoEsgoto.equals(LigacaoEsgotoSituacao.POTENCIAL.toString()) || 
							situacaoLigacaoEsgoto.equals(LigacaoEsgotoSituacao.FACTIVEL.toString())))
					){
					
						throw new ActionServletException("atencao.imovel_situacao_agua_esgoto_cliente_desconhecido");	
				}
			}
			
		}
		
		ImovelAbaCaracteristicasRetornoHelper resultadoAbaCaracteristicas = 
			fachada.validarImovelAbaCaracteristicas(helperCaracteristica);
		
		if (naoVazio(areaConstruida)) {
			imovel.setAreaConstruida(Util.formatarMoedaRealparaBigDecimal(areaConstruida));
		}
		imovel.setAreaConstruidaFaixa(resultadoAbaCaracteristicas.getAreaConstruidaFaixa());

		if (Util.verificarIdNaoVazio(faixaVolumeReservaotirio)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInf = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaInf.setId(new Integer(faixaVolumeReservaotirio));
			imovel.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInf);
		}
		if (naoVazio(volumeReservatorioInferior)) {
			imovel.setVolumeReservatorioInferior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior));
		}
		if (Util.verificarIdNaoVazio(faixaVolumeReservatorioSuperior)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSup = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaSup.setId(new Integer(faixaVolumeReservatorioSuperior));
			imovel.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSup);
		}
		if (naoVazio(volumeReservatorioSuperior)) {
			imovel.setVolumeReservatorioSuperior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior));
		}
		if (Util.verificarIdNaoVazio(faixaPiscina)) {
			PiscinaVolumeFaixa piscinaVolumeFaixa = new PiscinaVolumeFaixa();
			piscinaVolumeFaixa.setId(new Integer(faixaPiscina));
			imovel.setPiscinaVolumeFaixa(piscinaVolumeFaixa);
		}
		if (naoVazio(piscina)) {
			imovel.setVolumePiscina(Util.formatarMoedaRealparaBigDecimal(piscina));
		}
		if (naoVazio(jardim)) {
			imovel.setIndicadorJardim(new Short(jardim));
		}
		imovel.setPavimentoCalcada(resultadoAbaCaracteristicas.getPavimentoCalcada());
		imovel.setPavimentoRua(resultadoAbaCaracteristicas.getPavimentoRua());
		imovel.setFonteAbastecimento(resultadoAbaCaracteristicas.getFonteAbastecimento());
		imovel.setLigacaoAguaSituacao(resultadoAbaCaracteristicas.getLigacaoAguaSituacao());
		imovel.setLigacaoEsgotoSituacao(resultadoAbaCaracteristicas.getLigacaoEsgotoSituacao());
		imovel.setImovelPerfil(resultadoAbaCaracteristicas.getImovelPerfil());
		imovel.setSubSistemaEsgoto(resultadoAbaCaracteristicas.getSubSistemaEsgoto());
		
		if(indicadorNivelInstalacaoEsgoto != null && !indicadorNivelInstalacaoEsgoto.equals("")){
			imovel.setIndicadorNivelInstalacaoEsgoto(new Short(indicadorNivelInstalacaoEsgoto));
		}else{
			imovel.setIndicadorNivelInstalacaoEsgoto(new Short("2"));
		}
	
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = resultadoAbaCaracteristicas.getLigacaoEsgotoEsgotamento();
		
		if (Util.verificarIdNaoVazio(poco)) {
			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(poco));
			imovel.setPocoTipo(pocoTipo);
		}
		if (Util.verificarIdNaoVazio(tipoDespejo)) {
			Despejo despejo = new Despejo();
			despejo.setId(new Integer(tipoDespejo));
			imovel.setDespejo(despejo);
		}
		if (Util.verificarIdNaoVazio(tipoHabitacao)) {
			ImovelTipoHabitacao imovelTipoHabitacao = new ImovelTipoHabitacao();
			imovelTipoHabitacao.setId(new Integer(tipoHabitacao));
			imovel.setImovelTipoHabitacao(imovelTipoHabitacao);
		}
		if (Util.verificarIdNaoVazio(tipoPropriedade)) {
			ImovelTipoPropriedade imovelTipoPropriedade = new ImovelTipoPropriedade();
			imovelTipoPropriedade.setId(new Integer(tipoPropriedade));
			imovel.setImovelTipoPropriedade(imovelTipoPropriedade);
		}
		if (Util.verificarIdNaoVazio(tipoConstrucao)) {
			ImovelTipoConstrucao imovelTipoConstrucao = new ImovelTipoConstrucao();
			imovelTipoConstrucao.setId(new Integer(tipoConstrucao));
			imovel.setImovelTipoConstrucao(imovelTipoConstrucao);
		}
		if (Util.verificarIdNaoVazio(tipoCobertura)) {
			ImovelTipoCobertura imovelTipoCobertura = new ImovelTipoCobertura();
			imovelTipoCobertura.setId(new Integer(tipoCobertura));
			imovel.setImovelTipoCobertura(imovelTipoCobertura);
		}
		
		// ABA CONCLUSAO
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(numeroIptu);
		helperConclusao.setNumeroContratoCelpe(numeroContratoCelpe);
		
		if ( cordenadasX != null && !cordenadasX.equals("") ){
			
        	//Coordenada X
    		BigDecimal bigDecimal = Util.formatarStringParaBigDecimal(cordenadasX.replace(",", "."));
    		
    		if ( bigDecimal == null) {
    			throw new ActionServletException("atencao.valor_limite_coordenada_x_invalido");
    		}
    		
    		if ( bigDecimal.compareTo(new BigDecimal("-999999999999")) < 0 || bigDecimal.compareTo(new BigDecimal("999999999999")) > 0) {
    			throw new ActionServletException("atencao.valor_limite_coordenada_x");
    		}
			
		}
        
        if ( cordenadasY != null && !cordenadasY.equals("") ){
			
        	//Coordenada X
    		BigDecimal bigDecimal = Util.formatarStringParaBigDecimal(cordenadasY.replace(",", "."));
    		
    		if ( bigDecimal == null) {
    			throw new ActionServletException("atencao.valor_limite_coordenada_y_invalido");
    		}
    		
    		if ( bigDecimal.compareTo(new BigDecimal("-999999999999")) < 0 || bigDecimal.compareTo(new BigDecimal("999999999999")) > 0) {
    			throw new ActionServletException("atencao.valor_limite_coordenada_y");
    		}
			
		}
        
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		helperConclusao.setInformacoesComplementares(informacoesComplementares);
		helperConclusao.setIdFuncionario(idFuncionario);
		ImovelAbaConclusaoRetornoHelper resultadoAbaConclusao = fachada.validarImovelAbaConclusao(helperConclusao);
		
		
		if (resultadoAbaConclusao.getSequencialRotaEntrega() != null) {
			imovel.setNumeroSequencialRotaEntrega(new Integer(resultadoAbaConclusao.getSequencialRotaEntrega()));
		}
		if (resultadoAbaConclusao.getRotaEntrega() != null) {
			imovel.setRotaEntrega(resultadoAbaConclusao.getRotaEntrega());
		}
		
		if(resultadoAbaConclusao.getRotaAlternativa() != null){
			imovel.setRotaAlternativa(resultadoAbaConclusao.getRotaAlternativa());
		}
		
		if (numeroQuadraEntrega != null && !numeroQuadraEntrega.equals("")){
			
			imovel.setNumeroQuadraEntrega(new Integer(numeroQuadraEntrega));
		}
		
		if(informacoesComplementares != null && !informacoesComplementares.equals("")){
			imovel.setInformacoesComplementares(informacoesComplementares);
		}
		imovel.setNumeroPontosUtilizacao(naoVazio(pontoUtilizaco) ? new Short(pontoUtilizaco) : null);
		imovel.setNumeroMorador(naoVazio(numeroMoradores) ? new Short(numeroMoradores) : null);
		imovel.setNumeroIptu(naoVazio(numeroIptu) ? new BigDecimal(numeroIptu) : null);
		imovel.setNumeroCelpe(naoVazio(numeroContratoCelpe) ? new Long(numeroContratoCelpe) : null);
		imovel.setCoordenadaX(naoVazio(cordenadasX) ? new BigDecimal(cordenadasX.replace(',','.')) : null);
		imovel.setCoordenadaY(naoVazio(cordenadasY) ? new BigDecimal(cordenadasY.replace(',','.')) : null);
		if (Util.verificarIdNaoVazio(idImovelPrincipal)) {
			Imovel imovelPrincipal = new Imovel();
			imovelPrincipal.setId(new Integer(idImovelPrincipal));
			imovel.setImovelPrincipal(imovelPrincipal);
		}
		if (Util.verificarIdNaoVazio(idFuncionario)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionario));
			imovel.setFuncionario(funcionario);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		
		if(Util.verificarIdNaoVazio(sistemaAbastecimento)){
			SistemaAbastecimento obj = new SistemaAbastecimento();
			obj.setId(Integer.parseInt(sistemaAbastecimento));
			imovel.setSistemaAbastecimento(obj);
		}
		
		if(Util.verificarIdNaoVazio(subsistemaAbastecimento)){
			SubsistemaAbastecimento obj = new SubsistemaAbastecimento();
			obj.setId(Integer.parseInt(subsistemaAbastecimento));
			imovel.setSubsistemaAbastecimento(obj);
		}
		
		if(Util.verificarIdNaoVazio(setorAbastecimento)){
			SetorAbastecimento obj = new SetorAbastecimento();
			obj.setId(Integer.parseInt(setorAbastecimento));
			imovel.setSetorAbastecimento(obj);
		}
		
		if(Util.verificarIdNaoVazio(distritoOperacional)){
			DistritoOperacional obj = new DistritoOperacional();
			obj.setId(Integer.parseInt(distritoOperacional));
			imovel.setDistritoOperacional(obj);
		}
		
		if(Util.verificarIdNaoVazio(areaOperacional)){
			AreaOperacional obj = new AreaOperacional();
			obj.setId(Integer.parseInt(areaOperacional));
			imovel.setAreaOperacional(obj);
		}
		
		if(Util.verificarIdNaoVazio(zonaPressao)){
			ZonaPressao obj = new ZonaPressao();
			obj.setId(Integer.parseInt(zonaPressao));
			imovel.setZonaPressao(obj);
		}
		
		// OUTROS
		imovel.setUltimaAlteracao(new Date());
		imovel.setIndicadorExclusao(ConstantesSistema.NAO);
		imovel.setIndicadorImovelCondominio(ConstantesSistema.INDICADOR_USO_DESATIVO);
		imovel.setIndicadorDebitoConta(ConstantesSistema.INDICADOR_USO_DESATIVO);
		imovel.setIndicadorRateioNegativo(ConstantesSistema.INDICADOR_USO_DESATIVO);
		imovel.setIndicadorEmissaoExtratoFaturamento(naoVazio(extratoResponsavel) ? new Short(extratoResponsavel) : ConstantesSistema.NAO);
		
		
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(ConsumoTarifa.CONSUMO_NORMAL);
		imovel.setConsumoTarifa(consumoTarifa);
		
		short quantidadeEconomias = 0;
		Iterator iteratorSubcategorias = subcategorias.iterator();
		while (iteratorSubcategorias.hasNext()) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorSubcategorias.next();
			quantidadeEconomias += imovelSubcategoria.getQuantidadeEconomias();
		}
		imovel.setQuantidadeEconomias(quantidadeEconomias);
		
		ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
		if (Util.verificarIdNaoVazio(imovelContaEnvioForm)) {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(imovelContaEnvioForm));
		} else {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(2));
		}
		imovel.setImovelContaEnvio(imovelContaEnvio);
		
		if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
			if (imovel.getIndicadorEmissaoExtratoFaturamento().equals(new Short("0"))){
				imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.NAO);
			}
		} else {
			imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.NAO);
		}
		
		imovel.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
		imovel.setIndicadorVinculoCliente(ConstantesSistema.NAO);
		
		//CRC2258 solicitacao do analista Adriano Brito
		//data alteracao: 13/07/2009
		imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
		//fim da alteracao
		
		// CRC3184 
		// Desenvolvedor:Hugo Amorim Analista:Rosana
		// Data: 15/12/2009
		if(imovel.getImovelPerfil()!=null){
		boolean achou = false;	
			if(imovel.getImovelPerfil().getSubcategoria()!=null){

				for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
					Subcategoria subcategoria = imovelSubcategoria.getComp_id().getSubcategoria();
					if(subcategoria.getId().compareTo(imovel.getImovelPerfil().getSubcategoria().getId())==0){
						achou = true;
					}
				}
			
			
			if(!achou || subcategorias.size()>1){
				
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovel.getImovelPerfil().getSubcategoria().getId()));
				
				Collection<Subcategoria> colecaoSubs =
					 fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubs);
				
				throw new ActionServletException("atencao.perfil_exige_subcategoria",subcategoria.getDescricao());
			}
			}
		}	
		
		
		
		//Fim da Altera��o CRC3184


		//**********************************************************************
		// Autor: Rodrigo Cabral
		// Data: 13/09/2011
		// Inserir a Principal Categoria e SubCategoria do Imovel
		//**********************************************************************
		
		Categoria principalCategoria = fachada.obterPrincipalCategoria(subcategorias);
		
		if (principalCategoria != null){
			
			imovel.setCategoriaPrincipalId(principalCategoria.getId());
			

			ImovelSubcategoria principalSubCategoria = 
				fachada.obterPrincipalSubcategoria(principalCategoria.getId(), subcategorias);
	
			imovel.setSubCategoriaPrincipalId(principalSubCategoria.getComp_id().getSubcategoria().getId());
		}
		
		
		/**
		 * alterado por pedro alexandre dia 17/11/2006 Recupera o usu�rio logado
		 * para passar no met�do de inserir im�vel para verificar se o usu�rio
		 * tem abrang�ncia para inserir o im�vel na localidade informada.
		 */
		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		//Colocado por Davi Menezes em 15/06/2012 - Analista: Vivianne Sousa 
		Collection<?> colFoto = (Collection<?>) sessao.getAttribute("colecaoFoto");
	
		//Colocado por Raphael Rossiter em 19/08/2008 - Analista: Rosana Carvalho
		InserirImovelHelper inserirImovelHelper = new InserirImovelHelper(imovel, subcategorias, ramosAtividades, colecaoEnderecos,
		clientes, colFoto, ligacaoEsgotoEsgotamento, usuarioLogado);
		
		Integer id = fachada.inserirImovelRetorno(inserirImovelHelper);
		
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 24/07/2009
		// CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter
		// cliente pendente de atualizacao do imovel.
		//**********************************************************************
		fachada.verificarAtualizarOperacaoPendente(id, clientes, usuarioLogado.getId());
		//**********************************************************************

		sessao.removeAttribute("colecaoImovelSubCategorias");
		sessao.removeAttribute("imovelClientesNovos");
		
		
		sessao.removeAttribute("statusWizardAnterior");
		
		montarPaginaSucesso(httpServletRequest, "Im�vel de matr�cula " + id
				+ " inclu�do com sucesso.", "Inserir outro Im�vel",
				"exibirInserirImovelAction.do?menu=sim",
				"exibirAtualizarImovelAction.do?idRegistroAtualizacao=" + id
						+ "&sucesso=sucesso", "Atualizar Im�vel Inserido",
				"Informar Ocorr�ncias / Anormalidades",
				"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
						+ id);

		return retorno;
	}
	
	private boolean naoVazio(String valor) {
		if (valor == null || valor.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
