package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.PesquisaSatisfacaoLoja;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Davi Menezes
 * @date 28/02/2012
 *
 * Classe responsável por salvar a pesquisa de satisfação no banco
 */
public class RegistrarPesquisaSatisfacaoAction extends GcomAction {

	/**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	        
	        RegistrarPesquisaSatisfacaoActionForm form = (RegistrarPesquisaSatisfacaoActionForm) actionForm;
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	        
	        PesquisaSatisfacaoLoja pesquisaSatisfacao = new PesquisaSatisfacaoLoja();
	        
	        if(form.getNome() != null && !form.getNome().equals("")){
	        	if(sistemaParametro.getIndicadorNomeClienteGenerico().toString().equals("2")){
	        		FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
	        		filtroDescricaoGenerica.adicionarParametro(new ParametroSimples(
	        			FiltroDescricaoGenerica.NOME_GENERICO, form.getNome()));
	        		
	        		Collection<DescricaoGenerica> colDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
	        		if(!Util.isVazioOrNulo(colDescricaoGenerica)){
	        			throw new ActionServletException("atencao.nome_cliente_generico");
	        		}
	        	}
	        	pesquisaSatisfacao.setNomeCliente(form.getNome());
	        }
	        
	        if(form.getCodigoDDD() != null && !form.getCodigoDDD().equals("")){
	        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
	        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.DDD, form.getCodigoDDD()));
	        	
	        	Collection<Municipio> colMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
	        	if(Util.isVazioOrNulo(colMunicipio)){
	        		throw new ActionServletException("atencao.telefone.ddd.nao_existente");
	        	}
	        	pesquisaSatisfacao.setCodigoDDD(Short.parseShort(form.getCodigoDDD()));
	        }
	        
	        if(form.getTelefone() != null && !form.getTelefone().equals("")){
	        	pesquisaSatisfacao.setTelefone(form.getTelefone());
	        }
	        
	        if(form.getEmail() != null && !form.getEmail().equals("")){
	        	pesquisaSatisfacao.setEmail(form.getEmail());
	        }
	        
	        if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
	        	Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
	        	if(imovel == null){
	        		throw new ActionServletException("atencao.imovel.inexistente");
	        	}
	        	pesquisaSatisfacao.setImovel(imovel);
	        }
	        
	        if(form.getIdUnidade() != null && !form.getIdUnidade().equals("")){
	        	UnidadeOrganizacional unidade = fachada.pesquisarUnidadeOrganizacional(Integer.parseInt(form.getIdUnidade()));
	        	if(unidade == null){
	        		throw new ActionServletException("atencao.unidade_organizacional_inexistente");
	        	}
	        	pesquisaSatisfacao.setUnidadeOrganizacional(unidade);
	        }
	        
	        if(form.getDataAtendimento() != null && !form.getDataAtendimento().equals("")){
	        	String dtAtual = Util.formatarData(new Date());
	        	Integer dataAtendimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(form.getDataAtendimento());
	        	Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dtAtual);
	        	
	        	if(dataAtendimento > dataAtual){
	        		throw new ActionServletException("atencao.data_atendimento_maior_que_data_corrente");
	        	}
	        	
	        	SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");
	        	if(form.getHoraAtendimento() != null && !form.getHoraAtendimento().equals("")){
	        		String horaAten = form.getHoraAtendimento().substring(0, 2) + form.getHoraAtendimento().substring(3, 5) + "00";
	        		String horaAtu = horaFormatada.format(new Date());
	        		String horaAtual = horaAtu.substring(0, 2) + horaAtu.substring(3, 5) + horaAtu.substring(6, 8);
	        		
	        		if(String.valueOf(dataAtendimento).equals(String.valueOf(dataAtual)) 
	        				&& (Integer.parseInt(horaAten) > Integer.parseInt(horaAtual))){
	        			throw new ActionServletException("atencao.hora_atendimento_maior_que_hora_corrente");
	        		}
	        		
	        		String dataAtendimentoCompleta = form.getDataAtendimento() + " " + (form.getHoraAtendimento() + ":00");
	        		
	        		try{
		        		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        		Date data = dataFormatada.parse(dataAtendimentoCompleta);
		        		pesquisaSatisfacao.setDataAtendimento(data);
	        		}catch(java.text.ParseException ex){
	        			pesquisaSatisfacao.setDataAtendimento(new Date());
	        		}
	        	}else{
	        		String hora = horaFormatada.format(new Date());
	        		String dataAtendimentoCompleta  = form.getDataAtendimento() + " " + hora;
	        		
	        		try{
		        		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        		Date data = dataFormatada.parse(dataAtendimentoCompleta);
		        		pesquisaSatisfacao.setDataAtendimento(data);
	        		}catch(java.text.ParseException ex){
	        			pesquisaSatisfacao.setDataAtendimento(new Date());
	        		}
	        	}
	        }else{
	        	if(form.getHoraAtendimento() != null && !form.getHoraAtendimento().equals("")){
	        		String dtAtual = Util.formatarData(new Date());
		        	String horaAten = form.getHoraAtendimento() + ":00";
		        	String dataAtendimentoCompleta  = dtAtual + " " + horaAten;
		        	
		        	try{
		        		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        		Date data = dataFormatada.parse(dataAtendimentoCompleta);
		        		pesquisaSatisfacao.setDataAtendimento(data);
	        		}catch(java.text.ParseException ex){
	        			pesquisaSatisfacao.setDataAtendimento(new Date());
	        		}
		        }else{
		        	pesquisaSatisfacao.setDataAtendimento(new Date());
		        }
	        }
	        
	        if(form.getComentarios() != null && !form.getComentarios().equals("")){
	        	if(form.getComentarios().length() <= 450){
	        		pesquisaSatisfacao.setComentarios(form.getComentarios());
	        	}else{
	        		throw new ActionServletException("atencao.limite_comentarios_excedidos");
	        	}
	        }
	        
	        pesquisaSatisfacao.setAvaliacaoAtendente(Short.parseShort(form.getAvaliacaoAtendente()));
	        pesquisaSatisfacao.setAgilidadeAtendimento(Short.parseShort(form.getAgilidadeAtendimento()));
	        pesquisaSatisfacao.setTempoEspera(Short.parseShort(form.getTempoEspera()));
	        pesquisaSatisfacao.setConfortoLimpeza(Short.parseShort(form.getConfortoAmbiente()));
	        pesquisaSatisfacao.setLocalizacao(Short.parseShort(form.getLocalizacaoAmbiente()));
	        pesquisaSatisfacao.setSeguranca(Short.parseShort(form.getSeguranca()));
	        pesquisaSatisfacao.setEstacionamento(Short.parseShort(form.getEstacionamento()));
	        pesquisaSatisfacao.setUltimaAlteracao(new Date());
	        
	        fachada.inserir(pesquisaSatisfacao);
	        
	        montarPaginaSucesso(httpServletRequest, "Pesquisa Satisfação Registrada com Sucesso", 
	        	"Registrar outra Pesquisa Satisfação", "exibirRegistrarPesquisaSatisfacaoAction.do?menu=sim");
	        		
	        return retorno;
	 }
}
