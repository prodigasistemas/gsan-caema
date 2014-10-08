package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.Cadastrador;
import gcom.cadastro.MensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroCadastrador;
import gcom.cadastro.atualizacaocadastral.FiltroMensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.RelatorioImoveisInconsistentes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1297] - Relatório Atualização Cadastral - Imóveis Inconsistentes
 * @author Davi Menezes
 * @date  23/03/2012
 *
 */
public class GerarRelatorioImoveisInconsistentes extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		//Form
		FiltrarDadosCadastraisImoveisInconsistentesActionForm form = 
			(FiltrarDadosCadastraisImoveisInconsistentesActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		
		RelatorioImoveisInconsistentes relatorio = new RelatorioImoveisInconsistentes(usuarioLogado);
		
		String[] idsRegistro = null;
		if(form.getIdRegistro() != null){
			idsRegistro = form.getIdRegistro();
		}else{
			throw new ActionServletException("atencao.ids_movimento_nao_selecionado");
		}
		
		String nomeEmpresa = "";
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<?> colEmpresa = getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if(!Util.isVazioOrNulo(colEmpresa)){
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colEmpresa);
				nomeEmpresa = empresa.getDescricao();
			}
		}
		
		String descricaoLocalidade = "";
		if(form.getDescricaoLocalidade() != null && !form.getDescricaoLocalidade().equals("")){
			descricaoLocalidade = form.getDescricaoLocalidade();
		}
		
		String descricaoSetor = "";
		if(form.getDescricaoSetorComercial() != null && !form.getDescricaoSetorComercial().equals("")){
			descricaoSetor = form.getDescricaoSetorComercial();
		}
		
		String quadraInicial = "";
		if(form.getDescricaoQuadraInicial() != null && !form.getDescricaoQuadraInicial().equals("")){
			quadraInicial = form.getDescricaoQuadraInicial();
		}
		
		String quadraFinal = "";
		if(form.getDescricaoQuadraFinal() != null && !form.getDescricaoQuadraFinal().equals("")){
			quadraFinal = form.getDescricaoQuadraFinal();
		}
		
		String nomeCadastrador = "";
		if(form.getIdCadastrador() != null && !form.getIdCadastrador().equals("-1")){
			FiltroCadastrador filtro = new FiltroCadastrador();
			filtro.adicionarParametro(new ParametroSimples(FiltroCadastrador.ID, form.getIdCadastrador()));
			
			Collection<?> colCadastrador = this.getFachada().pesquisar(filtro, Cadastrador.class.getName());
			
			if(!Util.isVazioOrNulo(colCadastrador)){
				Cadastrador cadastrador = (Cadastrador) Util.retonarObjetoDeColecao(colCadastrador);
				nomeCadastrador = cadastrador.getNome();
			}
		}
		
		String situacaoMovimento = "";
		if(form.getIndicadorSituacaoMovimento() != null && !form.getIndicadorSituacaoMovimento().equals("")){
			if(form.getIndicadorSituacaoMovimento().equals("1")){
				situacaoMovimento = "Inconsistente";
			}else if(form.getIndicadorSituacaoMovimento().equals("2")){
				situacaoMovimento = "Atualizado";
			}else{
				situacaoMovimento = "Todos";
			}
			
		}
		
		String tipoInconsistencia = "";
		if(form.getIdTipoInconsistencia() != null && !form.getIdTipoInconsistencia().equals("-1")){
			FiltroMensagemAtualizacaoCadastral filtroMensagem = new FiltroMensagemAtualizacaoCadastral();
			filtroMensagem.adicionarParametro(new ParametroSimples(
				FiltroMensagemAtualizacaoCadastral.ID, form.getIdTipoInconsistencia()));
			
			Collection<?> colMensagem = getFachada().pesquisar(filtroMensagem, MensagemAtualizacaoCadastral.class.getName());
			
			if(!Util.isVazioOrNulo(colMensagem)){
				MensagemAtualizacaoCadastral mensagem = (MensagemAtualizacaoCadastral) Util.retonarObjetoDeColecao(colMensagem);
				tipoInconsistencia = mensagem.getMensagem();
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if(tipoRelatorio == null){
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		//Adiciona parametros no relatório
		relatorio.addParametro("empresa", nomeEmpresa);
		relatorio.addParametro("descricaoLocalidade", descricaoLocalidade);
		relatorio.addParametro("descricaoSetor", descricaoSetor);
		relatorio.addParametro("quadraInicial", quadraInicial);
		relatorio.addParametro("quadraFinal", quadraFinal);
		relatorio.addParametro("cadastrador", nomeCadastrador);
		relatorio.addParametro("situacaoMovimento", situacaoMovimento);
		relatorio.addParametro("tipoInconsistencia", tipoInconsistencia);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("idsRegistro", idsRegistro);
		
		
		relatorio.addParametro("idLocalidade", form.getIdLocalidade());
		relatorio.addParametro("codigoSetorComercial", form.getIdSetorComercial());
		relatorio.addParametro("numeroQuadraInicial", form.getIdQuadraInicial());
		relatorio.addParametro("numeroQuadraFinal", form.getIdQuadraFinal());
		relatorio.addParametro("idCadastrador", form.getIdCadastrador());
		relatorio.addParametro("indicadorSituacaoMovimento", form.getIndicadorSituacaoMovimento());
		relatorio.addParametro("idTipoInconsistencia", form.getIdTipoInconsistencia());
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
			httpServletResponse, actionMapping);
		
		return retorno;
	}
	
}
