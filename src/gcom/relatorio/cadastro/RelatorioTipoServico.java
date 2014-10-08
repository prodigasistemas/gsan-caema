package gcom.relatorio.cadastro;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.batch.Relatorio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cadastro.GerarRelatorioTipoServicoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1336] Gerar Relatório Tipo de Servico
 * 
 * @autor Carlos Chaves
 * @date 07/05/2012
 */
public class RelatorioTipoServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioTipoServico(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioTipoServico() {
		super(null, "");
	}
	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		// helper pesquisa
		GerarRelatorioTipoServicoHelper helper = 
			(GerarRelatorioTipoServicoHelper) getParametro("filtroHelper");
		
		//  tipo relatorio
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = (List) fachada.pesquisarDadosRelatorioTipoServico(helper);
		
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("periodo", helper.getPeriodoInicial() + " A " + helper.getPeriodoFinal());
		parametros.put("imagem", fachada.pesquisarParametrosDoSistema().getImagemRelatorio());
		
		if(helper.getIdServico().equals("1")){
			parametros.put("tipoServico", "Parcelamento");
		}else if(helper.getIdServico().equals("2")){
			parametros.put("tipoServico", "Segunda via de conta");
		}else if(helper.getIdServico().equals("3")){
			parametros.put("tipoServico", "Extrato débito");
		}else if(helper.getIdServico().equals("4")){
			parametros.put("tipoServico", "Averbação");
		}else if(helper.getIdServico().equals("5")){
			parametros.put("tipoServico", "Revisão de consumo");
		}

		if(helper.getTipoRelatorio().equals("1")){
			
			String usuarios = "";
			String meios = "";
			String unidadeSuperior = "";
			String unidadeOrganizacional = "";
			
			if(helper.getColecaoUsuario() != null){
				
				Collection<Usuario> colecaoUsuario = helper.getColecaoUsuario(); 
				
				
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
					
						if(colecaoUsuario.size()==1){
							usuarios = u.getNomeUsuario() + ", ";
						}else{
							usuarios += u.getId() + ", ";	
						}
					
					}
				}
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-2);
				}
				
			}
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				Collection<MeioSolicitacao> colecaoMeioSolicitacao = helper.getColecaoMeioSolicitacao();
				
				for(MeioSolicitacao meio : colecaoMeioSolicitacao ){
					for(int i=0; i<helper.getColecaoMeio().length; i++){
						
						if( Integer.toString(meio.getId()).equals(helper.getColecaoMeio()[i] )){
							meios += meio.getDescricao() + ", ";
						}
					}
				}
				if(!meios.equals("")){
					meios = meios.substring(0, meios.length()-2);
				}
				
			}
			
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				
				String idUnidadeSuperior = helper.getIdUnidadeSuperior(); 				
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples (FiltroUnidadeOrganizacional.ID, idUnidadeSuperior) );	
				Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
				
				for(UnidadeOrganizacional unid : colecaoUnidadeOrganizacional){
					unidadeSuperior = unid.getDescricao();
				}
				
			}
			
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = helper.getColecaoUnidadeOrganizacional(); 
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					if(! u.getId().equals("") && u.getId() !=null ){
						
						if(colecaoUnidadeOrganizacional.size()==1){
							unidadeOrganizacional = u.getDescricao() + ", ";
						}else{
							unidadeOrganizacional += u.getId() + ", ";	
						}
					}
				}
				if(! unidadeOrganizacional.equals("")){
					unidadeOrganizacional = unidadeOrganizacional.substring(0, unidadeOrganizacional.length()-2);
				}
				
			}
			

			parametros.put("tipoRelatorio", "R1334");
			parametros.put("usuarios", usuarios);
			parametros.put("meios", meios);
			parametros.put("unidadeSuperior", unidadeSuperior);
			parametros.put("unidadeOrganizacional", unidadeOrganizacional);
			
			
		}	
		else if (helper.getTipoRelatorio().equals("2")){
			String meios = "";
			String localidade = "";
			String unidadeNegocio = "";
			String gerenciaRegional = "";
			String opcaoTotalizacao = "";
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				Collection<MeioSolicitacao> colecaoMeioSolicitacao = helper.getColecaoMeioSolicitacao();
				
				for(MeioSolicitacao meio : colecaoMeioSolicitacao ){
					for(int i=0; i<helper.getColecaoMeio().length; i++){
						
						if( Integer.toString(meio.getId()).equals(helper.getColecaoMeio()[i] )){
							meios += meio.getDescricao() + ", ";
						}
					}
				}
				if(!meios.equals("")){
					meios = meios.substring(0, meios.length()-2);
				}
				
			}
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") && helper.getOpcaoTotalizacao().equals("localidade") ){
				localidade = helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") && helper.getOpcaoTotalizacao().equals("unidadeNegocio") ){
				unidadeNegocio = helper.getIdUnidadeNegocio();
			}
			
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") && helper.getOpcaoTotalizacao().equals("gerenciaRegional") ){
				gerenciaRegional = helper.getIdGerenciaRegional();
			}
			
			if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("estado") ){
				opcaoTotalizacao = "Estado";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("estadoGerencia") ){
				opcaoTotalizacao = "Estado por Gerência Regional";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("estadoUnidadeNegocio") ){
				opcaoTotalizacao = "Estado por Unidade de Negócio";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("estadoLocalidade") ){
				opcaoTotalizacao = "Estado por Localidade";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("gerenciaRegional") ){
				opcaoTotalizacao = "Gerência Regional";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("gerenciaRegionalLocalidade") ){
				opcaoTotalizacao = "Gerência Regional por Localidade";
				//parametros.put("gerenciaRegionalInformada", "Gerencia Regional: " + helper.getIdGerenciaRegionalPorLocalidade());
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("unidadeNegocio") ){
				opcaoTotalizacao = "Unidade de Negócio";
			}else if(helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("") && !helper.getOpcaoTotalizacao().equals("-1") && helper.getOpcaoTotalizacao().equals("localidade") ){
				opcaoTotalizacao = "Localidade";
			}
			
		
			parametros.put("tipoRelatorio", "R1335");
			parametros.put("opcaoTotalizacao", opcaoTotalizacao);
			parametros.put("gerenciaRegional", gerenciaRegional);
			parametros.put("unidadeNegocio", unidadeNegocio);
			parametros.put("localidade", localidade);
			parametros.put("meios", meios);
			
			
		}
		
		else if (helper.getTipoRelatorio().equals("3")){
			parametros.put("tipoRelatorio", "R1336");
			
			
			
			
			
		}
		
		//Caso não possua dados aprensetar mensagem de relatorio vazio ao usuario.
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.nao_existe_dados_filtro");
			
		}else if(helper.getTipoRelatorio().equals("1")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_USUARIO;
		}else if (helper.getTipoRelatorio().equals("2")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_LOCALIDADE;
		}else if(helper.getTipoRelatorio().equals("3")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_MEIO;
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		
		retorno = this.gerarRelatorio(this.nomeRelatorio,parametros, ds, tipoFormatoRelatorio);
			
		// ------------------------------------
		// Grava o relatório no sistema
		try {

			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			
			persistirRelatorioConcluido(retorno,
					Relatorio.REL_ALTERACOES_NO_SISTEMA_COLUNA,
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
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioTipoServico", this);
	}
}
