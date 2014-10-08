package gcom.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioClienteVirtual extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioClienteVirtual(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTE_VIRTUAL);
	}
	
	public RelatorioClienteVirtual() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	@Override
	public Object executar() throws TarefaException {
		
		//valor de Retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String periodoInicial = (String) getParametro("periodoInicial");
		String periodoFinal = (String) getParametro("periodoFinal");
		String idImovel = (String) getParametro("matriculaImovel");
		String codigoSituacao = (String) getParametro("codigoSituacao");
		String tempoCadastro = (String) getParametro("tempoCadastro");
		
		Date dataInicial = Util.converteStringParaDate(periodoInicial);
		dataInicial = Util.formatarDataInicial(dataInicial);
		Date dataFinal = Util.converteStringParaDate(periodoFinal);
		dataFinal = Util.formatarDataFinal(dataFinal);
		
		List<RelatorioClienteVirtualBean> beans = new ArrayList<RelatorioClienteVirtualBean>();
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioClienteVirtualBean> colecaoClientesBean = fachada.pesquisarClienteVirtual(dataInicial, dataFinal, idImovel, codigoSituacao, tempoCadastro) ;
		
		if(Util.isVazioOrNulo(colecaoClientesBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			Iterator<?> iterator = colecaoClientesBean.iterator();
			while(iterator.hasNext()){
				RelatorioClienteVirtualBean bean = 
					(RelatorioClienteVirtualBean) iterator.next();
				beans.add(bean);
			}
		}
		
		String matriculaImovel = "";
		if(idImovel != null && !idImovel.equals("")){
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(idImovel));
			if(imovel != null){
				matriculaImovel = "Im�vel: " + imovel.getMatriculaFormatada();
			}
		}
		
		String descricaoSituacao = "";
		if(codigoSituacao != null && !codigoSituacao.equals("")){
			if(codigoSituacao.equals("1")){
				descricaoSituacao = "Situa��o: Pendente";
			}else if(descricaoSituacao.equals("2")){
				descricaoSituacao = "Situa��o: Atualizado";
			}else if(descricaoSituacao.equals("3")){
				descricaoSituacao = "Situa��o: Rejeitado";
			}else if(descricaoSituacao.equals("4")){
				descricaoSituacao = "Situa��o: Em An�lise";
			}else{
				descricaoSituacao = "Situa��o: Removido de em An�lise";
			}
		}
		
		if(periodoInicial == null){
			periodoInicial = "";
		}
		
		if(periodoFinal == null){
			periodoFinal = "";
		}
		
		//Param�tros do Relat�rio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//adiciona os parametros no relatorio
		parametros.put("tipoFormatoRelatorio", "R1304");
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("periodoInicial", periodoInicial);
		parametros.put("periodoFinal", periodoFinal);
		parametros.put("imovel", matriculaImovel);
		parametros.put("situacao", descricaoSituacao);
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CLIENTE_VIRTUAL;
		
		//cria uma instancia do data source no relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		retorno = gerarRelatorio(nomeRelatorio, parametros, ds, tipoFormatoRelatorio);
		
		//retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioClienteVirtual", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

}
