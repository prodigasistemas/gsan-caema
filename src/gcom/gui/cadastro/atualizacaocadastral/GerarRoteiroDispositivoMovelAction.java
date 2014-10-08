package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.batch.Processo;
import gcom.cadastro.atualizacaocadastral.bean.ComandoAtualizacaoCadastralHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;

/**
 * [UC 1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 16/11/2012
 *
 */
public class GerarRoteiroDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso"); 
		
		//Form
		GerarRoteiroDispositivoMovelActionForm form = (GerarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Coleção de Imóveis
		ArrayList<String> colecaoImoveis = new ArrayList<String>();
		
		//Verificar se não foi selecionado nenhum registro
		if(form.getIndicadorGerarRoteiroPeloGEO().equals("2")){
			if((form.getIdsRegistros() == null || form.getIdsRegistros().length < 1)){
				throw new ActionServletException("atencao.nenhum_registro_selecionado");
			}else{
				for(int i = 0; i < form.getIdsRegistros().length; i++){
					colecaoImoveis.add(form.getIdsRegistros()[i]);
				}
			}
		}
		
		//Recuperar o Usuário Logado
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Montar o Helper
		ComandoAtualizacaoCadastralHelper helper = this.montarHelper(form, usuarioLogado, fachada, colecaoImoveis);
		
		//adicionado por Davi Menezes - 23/11/2012
    	Map parametros = new HashMap();
    	parametros.put("helper", helper);
    	parametros.put("colecaoImoveis", colecaoImoveis);
    	
    	fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.GERAR_ROTEIRO_DISPOSITIVO_MOVEL,
         		this.getUsuarioLogado(httpServletRequest));
		
		//Montar tela de sucesso
		this.montarPaginaSucesso(httpServletRequest, 
				"Roteiro para Atualização Cadastral foi encaminhado para batch.", 
				"Gerar Roteiro Dispositivo Móvel", 
				"exibirGerarRoteiroDispositivoMovelAction.do?menu=sim");
		
		
		return retorno;
	}
	
	/**
	 * [SB 0001] - Gerar Comando de Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 20/11/2012
	 * 
	 */
	public ComandoAtualizacaoCadastralHelper montarHelper(GerarRoteiroDispositivoMovelActionForm form, Usuario usuario, Fachada fachada, ArrayList<String> colecaoImoveis){
		ComandoAtualizacaoCadastralHelper helper = new ComandoAtualizacaoCadastralHelper();
		
		Localidade localidade = new Localidade(Integer.parseInt(form.getIdLocalidade()));
		
		Empresa empresa = new Empresa();
		empresa.setId(Integer.parseInt(form.getIdEmpresa()));
		
		Leiturista leiturista = new Leiturista();
		leiturista.setId(Integer.parseInt(form.getCadastrador()));
		
		Integer codigoSetorComercial = null;
		if(!form.getCodigoSetorComercial().equals("-1")){
			codigoSetorComercial = Integer.parseInt(form.getCodigoSetorComercial());
		}
		
		helper.setCodigoSetorComercial(codigoSetorComercial);
		helper.setEmpresa(empresa);
		helper.setLeiturista(leiturista);
		helper.setLocalidade(localidade);
		helper.setUsuarioLogado(usuario);
		helper.setIndicadorGerarRoteiroPeloGEO(form.getIndicadorGerarRoteiroPeloGEO());
		
		Set<Integer> quadrasSet = new HashSet<Integer>(); 
		
		for(int index = 0; index < colecaoImoveis.size(); index++){
			String  idImovel = colecaoImoveis.get(index);
			idImovel = idImovel.substring(0, idImovel.length() - 2);
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(idImovel));
			
			quadrasSet.add(imovel.getQuadra().getNumeroQuadra());
		}
		
		Integer[] quadras = quadrasSet.toArray(new Integer[quadrasSet.size()]);
		
		helper.setQuadrasSelecionadas(quadras);
		
		return helper;
	}
}
