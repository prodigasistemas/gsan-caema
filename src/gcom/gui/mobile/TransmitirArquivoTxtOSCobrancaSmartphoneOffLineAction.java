package gcom.gui.mobile;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.mobile.ArquivoTextoOSCobranca;
import gcom.mobile.FiltroArquivoTextoOSCobranca;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/10/2010
 */


public class TransmitirArquivoTxtOSCobrancaSmartphoneOffLineAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward( "consultarArquivoTextoOSCobrancaSmartphoneAction" );
		
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		String imei = httpServletRequest.getParameter("imei");
		String mac = httpServletRequest.getParameter("mac");
		
		if (Util.verificarNaoVazio(imei) || Util.verificarNaoVazio(mac)) {
			
			File temporario;
			
			byte[] arq = getFachada().baixarArquivoTextoExecucaoOS(Long.parseLong( imei ), mac );
			
			if ( arq != null && arq.length > 0 ){

				try {
					temporario = File.createTempFile("temporario", ".txt");
	
					ArquivoTextoOSCobranca arquivoTextoOSCobranca = new ArquivoTextoOSCobranca();
					FiltroArquivoTextoOSCobranca filtro = new FiltroArquivoTextoOSCobranca();
					filtro.adicionarParametro( new ParametroSimples( FiltroArquivoTextoOSCobranca.IMEI_LEITURISTA, imei ) );
					filtro.adicionarParametro( new ParametroSimples( FiltroArquivoTextoOSCobranca.ID_SITUACAO_TRANSMISSAO_LEITURA, SituacaoTransmissaoLeitura.LIBERADO ) );
					filtro.adicionarCaminhoParaCarregamentoEntidade( "leiturista" );
					
					arquivoTextoOSCobranca = (ArquivoTextoOSCobranca) Util.retonarObjetoDeColecao( getFachada().pesquisar(filtro, ArquivoTextoOSCobranca.class.getName()) );
					
					if (arquivoTextoOSCobranca.getSituacao().getId().equals( SituacaoTransmissaoLeitura.LIBERADO ) ) {

						this.verificarLeiturista(arquivoTextoOSCobranca, form);
						
						FileOutputStream out = new FileOutputStream(temporario);
						
						// Parametro que identifica que o tipo de arquivo da
						// rota
						// está sendo enviado
						String parametroArquivoBaixarRota = "";
	
						// 1 do tipo de resposta ok + parametro Arquivo baixar
						// rota
						// + tamanho do arquivo rota
						httpServletResponse.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arq.length);
	
						out.write(parametroArquivoBaixarRota.getBytes());
						out.write(arq);
	
						out.flush();
	
						FileInputStream inputStream = new FileInputStream(temporario);
	
						int INPUT_BUFFER_SIZE = 1024;
						byte[] temp = new byte[INPUT_BUFFER_SIZE];
						int numBytesRead = 0;
	
						ByteArrayOutputStream arquivo = new ByteArrayOutputStream();
	
						while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
							arquivo.write(temp, 0, numBytesRead);
						}
	
						inputStream.close();
						inputStream = null;
	
						arquivo.close();

						
						HttpSession sessao = httpServletRequest.getSession(false);
						sessao.setAttribute("arquivoGerado", arquivo.toByteArray());
						sessao.setAttribute("nomeArquivoGerado", arquivoTextoOSCobranca.getId() + "-" + Util.formatarDataAAAAMMDD(new Date())+".txt");
						httpServletRequest.setAttribute("baixarArquivo", true);
	
						// Atualiza o arquivo em campo
						SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
						arquivoTextoOSCobranca.setSituacao(situacao);
						arquivoTextoOSCobranca.setDataEnvio( new Date() );
	
						this.getFachada().atualizar(arquivoTextoOSCobranca);
						
						httpServletRequest.setAttribute("situacao", SituacaoTransmissaoLeitura.EM_CAMPO);
	
					} else {
						throw new ActionServletException("atencao.arquivo_ja_baixado");
					}
		
				} catch (IOException e) {
					reportarErros(httpServletRequest, "erro.sistema");
				}
			} else {
				throw new ActionServletException("atencao.arquivo_ja_baixado");
			}
		}
		return retorno;
	}
	
	private void verificarLeiturista(ArquivoTextoOSCobranca arquivoTextoOSCobranca, ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form) {
		String idTipoOS = form.getIdTipoOS();
		
		//2. Caso o Tipo da Ordem de Serviço corresponda a "O.S. DE MICROMEDICAO"
		if(idTipoOS != null && (Integer.valueOf(idTipoOS)).equals(OrdemServico.ORDEM_SERVICO_MICROMEDICAO)){
			
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_LEITURISTA_ID,arquivoTextoOSCobranca.getLeiturista().getId()));
			filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_IC_USO,ConstantesSistema.SIM));
			
			Collection<Hidrometro> colecaoHidrometros = Fachada.getInstancia().pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//1. Caso não exista registro na tabela MICROMEDICAO.HIDROMETRO
			if(colecaoHidrometros == null || colecaoHidrometros.isEmpty()){
				
				//1. exibir a mensagem "Não existem Hidrômetros informados para o Agente Comercial selecionado."
				throw new ActionServletException("atencao.arquivo_txt_nao_ex_hidrometros_agente");
			}
			else{
				SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
				
				//2. Caso contrário, caso a quantidade de registros 
				//   seja superior a PARM_NNLIMITEOSCOBRANCA da tabela CADASTRO.SISTEMA_PARAMETROS
				if(colecaoHidrometros.size() > sistemaParametro.getNumeroLimiteOSCobranca().intValue()){
					
					//2. exibir a mensagem "Existem mais de 
					//   <<PARM_NNLIMITEOSCOBRANCA>> hidrômetros 
					//   informados para o Agente Comercial selecionado."
					throw new ActionServletException("atencao.arquivo_txt_existem_mais_hidr_limite", 
						sistemaParametro.getNumeroLimiteOSCobranca().toString());
					
				}
			}
		}
	}
}
