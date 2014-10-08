package gcom.gui.cadastro.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.Iterator;

import gcom.cadastro.MensagemRetornoReceitaFederal;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.integracao.webservice.neurotech.fachada.ConsultaWebServiceGATEWAY;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.cliente.RelatorioClienteReceitaFederal;
import gcom.relatorio.cadastro.cliente.RelatorioClienteReceitaFederalHelper;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1429] Filtrar Cliente para Validar na Base da Receita Federal
 * 
 * @author Maxwell Moreira
 * @created 14/01/2013
 */

public class FiltrarClienteReceitaFederalAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("emitirRelatorioClienteReceitaFederal");
		FiltrarClienteReceitaFederalActionForm form = (FiltrarClienteReceitaFederalActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Short indicadorConsultaDocumentoReceita = ConstantesSistema.SIM;
		
		if((form.getCpf() == null || form.getCpf() == "") && 
				(form.getCnpj() == null || form.getCnpj() == "") &&
				(form.getIndicadorValidacao() == null || form.getIndicadorValidacao() == "")){
			indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
		}
		
		if (indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
		
			ConsultaWebServiceGATEWAY consultaWebService = new ConsultaWebServiceGATEWAY();
			Collection<Cliente> colecaoClientesFiltrados = (ArrayList<Cliente>) sessao.getAttribute("colecaoCliente");
			Collection colecaoClientesRelatorio = new ArrayList();
			Collection<RelatorioClienteReceitaFederalHelper> colecaoClienteErroValidacao = new ArrayList<RelatorioClienteReceitaFederalHelper>();
				
				if(colecaoClientesFiltrados != null && !colecaoClientesFiltrados.isEmpty()){	
						
						Iterator iterator = colecaoClientesFiltrados.iterator();
						Boolean erroValidacao = false;
						
						while (iterator.hasNext()) {
							
							Cliente cliente = (Cliente)iterator.next();
							ConsultarReceitaFederal consultaGSAN = null;
							Collection clienteFonePesquisado = null;
							Collection clienteEnderecoPesquisado = null;
							ConsultarReceitaFederal consultaRF = null;
							
							try {
								
								clienteFonePesquisado = fachada.pesquisarClienteFone(cliente.getId());
								ClienteFone clienteFone = this.getFonePrincipalDoCliente((Collection<ClienteFone>)clienteFonePesquisado);
								
								FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
								filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
								
								clienteEnderecoPesquisado = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
								ClienteEndereco clienteEndereco = this.getEnderecoPrincipalDoCliente(clienteEnderecoPesquisado);
								
								if ((cliente.getCpf() != null) && (!cliente.getCpf().equals(""))){
									
									consultaGSAN = fachada.pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(cliente.getCpf());
									
									if(consultaGSAN == null){
										consultaRF = consultaWebService.consultarPessoaFisica(cliente.getCpf(), usuario, cliente, clienteFone, clienteEndereco);
										sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
										sessao.setAttribute("consultaGSAN", false);
									}else{
										sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
										sessao.setAttribute("consultaGSAN", true);
									}
									
								}else if ((cliente.getCnpj() != null) && (!cliente.getCnpj().equals(""))){
									
									consultaGSAN = fachada.pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(cliente.getCnpj());
									
									if(consultaGSAN == null){
										consultaRF = consultaWebService.consultaPessoaJuridica(cliente.getCnpj(), usuario, cliente, clienteFone, clienteEndereco);
										System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CNPJ: "+cliente.getCnpj());
										sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
										sessao.setAttribute("consultaGSAN", false);
									}else{
										System.out.println("CONSULTA GSAN INSERIR CLIENTE CNPJ: "+cliente.getCnpj());
										sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
										sessao.setAttribute("consultaGSAN", true);
									}
									
								}
								
							} catch (Exception e) {
											
									RelatorioClienteReceitaFederalHelper helperErroValidacao = new RelatorioClienteReceitaFederalHelper();
							
									helperErroValidacao.setId(cliente.getId());
									helperErroValidacao.setNome(cliente.getNome());
									
									if ((cliente.getCpf() != null) && (!cliente.getCpf().equals(""))) {
										helperErroValidacao.setDocumento(cliente.getCpf());
									}
									
									if ((cliente.getCnpj() != null) && (!cliente.getCnpj().equals(""))){
										helperErroValidacao.setDocumento(cliente.getCnpj());
									}
									
									if (consultaRF != null){
																		
										if (consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno().equalsIgnoreCase("ERRO_DESCONHECIDO")){
											helperErroValidacao.setMensagem(MensagemRetornoReceitaFederal.SEM_CONEXAO);
										} else {
											helperErroValidacao.setMensagem(consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno());
										}
	
									} else {
										helperErroValidacao.setMensagem(MensagemRetornoReceitaFederal.SEM_CONEXAO);
									}
									
									if (erroValidacao == false){
										erroValidacao = true;
										helperErroValidacao.setErroValidacao(true);
									} else {
										erroValidacao = true;
										helperErroValidacao.setErroValidacao(false);
									}
									
									colecaoClienteErroValidacao.add(helperErroValidacao);
									
									continue;
							}
							
							consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
							
							if(consultaRF.getMensagemRetornoReceitaFederal() != null && consultaRF.getMensagemRetornoReceitaFederal().getId().intValue() != MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO){
								
								consultaRF.setAcaoUsuario(Short.parseShort("2"));
								
							} else {					
								
								if(consultaRF.getCpfCliente() != null && !consultaRF.getCpfCliente().equals("")){
									if(consultaRF.getNomePessoaFisica() == null || consultaRF.getNomePessoaFisica().equals("")){
										cliente.setNome(formatarNome(consultaRF.getNomeCliente()));	
										cliente.setNomeClienteReceitaFederal(consultaRF.getNomeCliente());
									}else{
										cliente.setNome(formatarNome(consultaRF.getNomePessoaFisica()));	
										cliente.setNomeClienteReceitaFederal(consultaRF.getNomePessoaFisica());
									}
									
								}else if(consultaRF.getCnpjCliente() != null && !consultaRF.getCnpjCliente().equals("")){
									cliente.setNome(formatarNome(consultaRF.getRazaoSocial()));	
									cliente.setNomeClienteReceitaFederal(consultaRF.getRazaoSocial());
								}
								
								Date ultimaAlteracao = new Date();
								cliente.setUltimaAlteracao(ultimaAlteracao);
								
							    fachada.atualizarCliente(cliente, clienteFonePesquisado, clienteEnderecoPesquisado, usuario);
							    
							    consultaRF.setAcaoUsuario(Short.parseShort("1"));
							    
							}
							
							Boolean resultadoGSAN = (Boolean) sessao.getAttribute("consultaGSAN");
							
							if (resultadoGSAN != null && resultadoGSAN.booleanValue() == false && consultaRF.getCliente() != null){
								
								Date dataAtual = new Date();
								consultaRF.setDataAcesso(dataAtual);
								consultaRF.setUsuarioSolicitante(usuario);
								this.getFachada().inserir(consultaRF);
							
							}else if((resultadoGSAN != null && resultadoGSAN.booleanValue() == true) ){
								this.getFachada().atualizar(consultaRF);
							} 

							if (consultaRF.getId() != null){
								colecaoClientesRelatorio.add(consultaRF.getId());
							} else if (consultaRF.getMensagemRetornoReceitaFederal().getId().intValue() != MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO){
								
								RelatorioClienteReceitaFederalHelper helperErroValidacao = new RelatorioClienteReceitaFederalHelper();
								
								helperErroValidacao.setId(cliente.getId());
								helperErroValidacao.setNome(cliente.getNome());
								
								if ((cliente.getCpf() != null) && (!cliente.getCpf().equals(""))) {
									helperErroValidacao.setDocumento(cliente.getCpf());
								}
								
								if ((cliente.getCnpj() != null) && (!cliente.getCnpj().equals(""))){
									helperErroValidacao.setDocumento(cliente.getCnpj());
								}
								
								if (consultaRF != null){
									
									if (consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno().equalsIgnoreCase("ERRO_DESCONHECIDO")){
										helperErroValidacao.setMensagem(MensagemRetornoReceitaFederal.SEM_CONEXAO);
									} else {
										helperErroValidacao.setMensagem(consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno());
									}

								} else {
									helperErroValidacao.setMensagem(MensagemRetornoReceitaFederal.SEM_CONEXAO);
								}								
								
								if (erroValidacao == false){
									erroValidacao = true;
									helperErroValidacao.setErroValidacao(true);
								} else {
									erroValidacao = true;
									helperErroValidacao.setErroValidacao(false);
								}
								
								colecaoClienteErroValidacao.add(helperErroValidacao);
								
							}
							
							sessao.removeAttribute("clienteCadastradoNaReceita");
							sessao.removeAttribute("consultaGSAN");
							
						}
						
						if ((colecaoClientesRelatorio.isEmpty()) && (colecaoClienteErroValidacao.isEmpty())){
							throw new ActionServletException("atencao.falha_webservice_gateway", "0199" + " - " + "Falha na consulta"+ ". Os dados do cliente não podem ser atualizados");
						} else {
							String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");			
							
							if (tipoRelatorio  == null) {
								tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
							}

							RelatorioClienteReceitaFederal relatorio = 		
									new RelatorioClienteReceitaFederal(this.getUsuarioLogado(httpServletRequest));
							relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
							relatorio.addParametro("colecaoClientesRelatorio",colecaoClientesRelatorio);
							relatorio.addParametro("colecaoClienteErroValidacao",colecaoClienteErroValidacao);
							httpServletRequest.setAttribute("telaSucessoRelatorio",true);
							retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
									httpServletResponse, actionMapping);
						}
					}
		}
		
		return retorno;
	
	}
	
	private ClienteFone getFonePrincipalDoCliente(Collection<ClienteFone> colecaoClienteFone){
		ClienteFone clienteFoneRetorno = null;
		
		if(colecaoClienteFone != null){
				for (ClienteFone clienteFone : colecaoClienteFone) {
					if(clienteFone.getIndicadorTelefonePadrao().intValue() == ClienteFone.INDICADOR_FONE_PADRAO.intValue()){
						clienteFoneRetorno = clienteFone;
					}
				}
		}
		return clienteFoneRetorno;
	}

	private String formatarNome(String nome){
		String nomeFormatado = "";
		if ( nome.length() > 49 ) {
			nomeFormatado = nome.substring(0, 49);
		} else {
			nomeFormatado = nome;
		}
		return nomeFormatado;
	}
	
	//Metodo privado que retorna o Endereco Principal da lista de enderecos a serem atualizados
	 
	private ClienteEndereco getEnderecoPrincipalDoCliente(Collection<ClienteEndereco> colecaoClienteEndereco){
		ClienteEndereco retorno = null;
		
		if(colecaoClienteEndereco != null){
			for (ClienteEndereco endereco : colecaoClienteEndereco) {
				if(endereco.getIndicadorEnderecoCorrespondencia().intValue() == ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA.intValue()){
					retorno = endereco;
				}
			}
		}
		
		return retorno;
	}
	
}