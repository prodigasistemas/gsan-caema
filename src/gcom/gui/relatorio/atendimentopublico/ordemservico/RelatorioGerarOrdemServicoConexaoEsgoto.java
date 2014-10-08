package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.seguranca.RelatorioAlteracoesSistemaColunaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**[UC1534] Gerar Ordem de Servico Conexao Esgoto
 * @author: Jonathan Marcos
 * @date:12/08/2013
 */
public class RelatorioGerarOrdemServicoConexaoEsgoto extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioGerarOrdemServicoConexaoEsgoto(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_CONEXAO_ESGOTO_BATCH);
	}
	
	@Deprecated
	public RelatorioGerarOrdemServicoConexaoEsgoto() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Collection colecaoImoveis = (Collection) getParametro("colecaoImoveis");
		short numeroDiasFactivelFaturavelSistemaParametro = (Short) getParametro("numeroDias");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		Integer idImovel = null;
		Imovel imovel = new Imovel();
		
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecaoImoveis.iterator();
			RelatorioGerarOrdemServicoConexaoEsgotoHelper objetoHelper = null;
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				Object[] objeto = (Object[]) colecaoIterator.next();
				objetoHelper = new RelatorioGerarOrdemServicoConexaoEsgotoHelper();
				
				imovel.setId(Integer.valueOf((String)objeto[1].toString()));
				objetoHelper.setMatricula(imovel.getMatriculaFormatada());
				
				idImovel = Integer.valueOf((String)objeto[1].toString());
				try {
					objetoHelper.setEndereco(fachada.pesquisarEnderecoFormatado(idImovel));
				} catch (ControladorException e) {
					e.printStackTrace();
				}
				
				objetoHelper.setNumeroDiasFactivelFaturavelSistemaParametro(String.
					valueOf(numeroDiasFactivelFaturavelSistemaParametro)+" ("+
						this.valorPorExtenso(Double.valueOf(numeroDiasFactivelFaturavelSistemaParametro), 1)+")");
				
				RelatorioGerarOrdemServicoConexaoEsgotoBean bean = 
						new RelatorioGerarOrdemServicoConexaoEsgotoBean(objetoHelper);
				
				// adiciona o bean a coleção
				relatorioBeans.add(bean);				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1434");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
					new RelatorioAlteracoesSistemaColunaBean();
				
				relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
				
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		
		}else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_CONEXAO_ESGOTO_BATCH,
				parametros, ds, tipoFormatoRelatorio);
							
		}
		
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ORDEM_SERVICO_CONEXAO_ESGOTO_BATCH,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}
	
	public int verificarQuantidadePontosHabitantes(String valorHabitantes){
		
		String quantidadeHabitantes = new String(valorHabitantes);
		int retorno = 0;
		for(int a = 0;a<quantidadeHabitantes.length();a++){
			if(quantidadeHabitantes.charAt(a)=='.'){
				retorno++;
			}
		}
		
		return retorno;
	}
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarOrdemServicoConexaoEsgoto", this);
	}
	
	/*
	 * Autor: Jonathan Marcos
	 * Data: 11/10/2013
	 */
	public String valorPorExtenso(double vlr,int indicadorReal) {
	    if (vlr == 0)
	       return("zero");

	    long inteiro = (long)Math.abs(vlr); // parte inteira do valor
	    double resto = vlr - inteiro;       // parte fracionária do valor

	    String vlrS = String.valueOf(inteiro);
	    if (vlrS.length() > 15)
	       return("Erro: valor superior a 999 trilhões.");

	    String s = "", saux, vlrP;
	    String centavos = String.valueOf((int)Math.round(resto * 100));

	    String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
	             "seis", "sete", "oito", "nove", "dez", "onze",
	             "doze", "treze", "quatorze", "quinze", "dezesseis",
	             "dezessete", "dezoito", "dezenove"};
	    String[] centena = {"", "cento", "duzentos", "trezentos",
	             "quatrocentos", "quinhentos", "seiscentos",
	             "setecentos", "oitocentos", "novecentos"};
	    String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
	             "sessenta", "setenta", "oitenta", "noventa"};
	    String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
	    String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};

	    // definindo o extenso da parte inteira do valor
	    int n, unid, dez, cent, tam, i = 0;
	    boolean umReal = false, tem = false;
	    while (!vlrS.equals("0")) {
	      tam = vlrS.length();
	      // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
		// 1a. parte = 789 (centena)
		// 2a. parte = 456 (mil)
		// 3a. parte = 123 (milhões)
	      if (tam > 3) {
	         vlrP = vlrS.substring(tam-3, tam);
	         vlrS = vlrS.substring(0, tam-3);
	      }
	      else { // última parte do valor
	        vlrP = vlrS;
	        vlrS = "0";
	      }
	      if (!vlrP.equals("000")) {
	         saux = "";
	         if (vlrP.equals("100"))
	            saux = "cem";
	         else {
	           n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
	           cent = n / 100;                  // cent = 3 (centena trezentos)
	           dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
	           unid = (n % 100) % 10;           // unid = 1 (unidade um)
	           if (cent != 0)
	              saux = centena[cent];
	           if ((n % 100) <= 19) {
	              if (saux.length() != 0)
	                 saux = saux + " e " + unidade[n % 100];
	              else saux = unidade[n % 100];
	           }
	           else {
	              if (saux.length() != 0)
	                 saux = saux + " e " + dezena[dez];
	              else saux = dezena[dez];
	              if (unid != 0) {
	                 if (saux.length() != 0)
	                    saux = saux + " e " + unidade[unid];
	                 else saux = unidade[unid];
	              }
	           }
	         }
	         if (vlrP.equals("1") || vlrP.equals("001")) {
	            if (i == 0) // 1a. parte do valor (um real)
	               umReal = true;
	            else saux = saux + " " + qualificaS[i];
	         }
	         else if (i != 0)
	                 saux = saux + " " + qualificaP[i];
	         if (s.length() != 0)
	            s = saux + ", " + s;
	         else s = saux;
	      }
	      if (((i == 0) || (i == 1)) && s.length() != 0)
	         tem = true; // tem centena ou mil no valor
	      i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
	    }

	    if (s.length() != 0 && indicadorReal==0) {
	       if (umReal)
	          s = s + " real";
	       else if (tem)
	               s = s + " reais";
	            else s = s + " de reais";
	    }

	// definindo o extenso dos centavos do valor
	    if (!centavos.equals("0")) { // valor com centavos
	       if (s.length() != 0) // se não é valor somente com centavos
	          s = s + " e ";
	       if (centavos.equals("1"))
	          s = s + "um centavo";
	       else {
	         n = Integer.parseInt(centavos, 10);
	         if (n <= 19)
	            s = s + unidade[n];
	         else {             // para n = 37, tem-se:
	           unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
	           dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
	           s = s + dezena[dez];
	           if (unid != 0)
	              s = s + " e " + unidade[unid];
	         }
	         s = s + " centavos";
	       }
	    }
	    return(s);
	}
}
