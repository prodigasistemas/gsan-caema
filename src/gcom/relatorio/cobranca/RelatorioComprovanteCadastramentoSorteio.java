package gcom.relatorio.cobranca;

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.email.ServicosEmail;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
 * [SB0003] Emitir Comprovante
 * [SB0004] Enviar E-mail com Comprovante
 * 
 * @author Hugo Azevedo
 * @date 18/10/2012
 * 
 **/
public class RelatorioComprovanteCadastramentoSorteio extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioComprovanteCadastramentoSorteio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMPROVANTE_CADAST_SORTEIO);
	}
	
	@Deprecated
	public RelatorioComprovanteCadastramentoSorteio() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		//Parâmetros iniciais
		byte[] retorno = null;		
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idImovel = (Integer) getParametro("idImovel");
		Boolean enviarEmail = (Boolean) getParametro("enviarEmail");
		Fachada fachada = Fachada.getInstancia();
	
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("idImovel", idImovel.toString());

		//[IT0004] Pesquisar Dados Cadastro Sorteio
		List<RelatorioComprovanteCadastramentoSorteioBean> colecaoBean = Fachada.getInstancia().pesquisarDadosCadastroSorteio(idImovel);
		
		//Finalizando e gerando o relatório
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);	
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMPROVANTE_CADAST_SORTEIO,
				parametros, ds, tipoFormatoRelatorio);
		
		
		if(enviarEmail) {
			//[SB0003] Enviar E-mail com Comprovante
			//========================================================
			//2. O sistema deverá enviar o comprovante gerado para o email cadastrado,
			//   com o assunto "Comprovante de Cadastramento do Sorteio de Prêmios - COMPESA"
			//   e com o conteúdo "Parabéns! Você está participando do sorteio de prêmios da COMPESA! 
			//   O comprovante de Cadastramento para Sorteio do seu Imóvel segue em anexo nesse e-mail."
			//[FE0004] Verificar E-mail cadastrado
			//[IT0005] Obter E-mail cadastrado
			String email = this.verificarEmailCadastrado(idImovel);
			
			File leitura = new File("comprovante.pdf");
			try {
				FileOutputStream fos = new FileOutputStream(leitura.getAbsolutePath());
				fos.write(retorno);
				fos.close();
				
				
				EnvioEmail envioEmail = fachada.pesquisarEnvioEmail(EnvioEmail.EMITIR_COMPROVANTE_CADASTRAMENTO_SORTEIO);
				
				String emailRemetente = envioEmail.getEmailRemetente();					
				String emailDestinatario = email;					
				String tituloMensagem = envioEmail.getTituloMensagem();					
				String mensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagemArquivoAnexado(emailDestinatario, emailRemetente, tituloMensagem,mensagem, leitura);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			leitura.delete();
		}
			
		//========================================================	
			
		return retorno;
		
	}
	
	
	/**
	 * [FE0004] Verificar E-mail cadastrado
	 * [IT0005] Obter E-mail cadastrado
	 **/
	public String verificarEmailCadastrado(Integer idImovel){
		
		Fachada fachada = Fachada.getInstancia(); 
		
		//[IT0005] Obter E-mail cadastrado
		String emailCadastrado = fachada.obterEmailCadastrado(idImovel);
		
		return emailCadastrado;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {		
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComprovanteCadastramentoSorteio", this);		
	}

}
