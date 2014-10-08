package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.AreaAtualizacaoCadastral;
import gcom.cadastro.FiltroAreaAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1327] - Liberar Localidade Atualizacao Cadastral
 * 
 * @author Davi Menezes
 * @date 24/04/2012
 *
 */
public class LiberarLocalidadeAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		LiberarLocalidadeAtualizacaoCadastralActionForm form = 
				(LiberarLocalidadeAtualizacaoCadastralActionForm) actionForm;
		
		AreaAtualizacaoCadastral areaAtualizacaoCadastral = new AreaAtualizacaoCadastral();
		
		boolean novoRegistro = true;
		
		Fachada fachada = this.getFachada();
		
		Localidade localidade = null;
		if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
			
			Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(Util.isVazioOrNulo(colLocalidade)){
				throw new ActionServletException("atencao.localidade.inexistente");
			}else{
				localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
				areaAtualizacaoCadastral.setLocalidade(localidade);
			}
			
			if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
				
				Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if(Util.isVazioOrNulo(colSetorComercial)){
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}else{
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
					areaAtualizacaoCadastral.setSetorComercial(setorComercial);
				}
			}
		}
		
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			
			Collection<?> colEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if(!Util.isVazioOrNulo(colEmpresa)){
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colEmpresa);
				areaAtualizacaoCadastral.setEmpresa(empresa);
			}
		}
		
		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacao = new FiltroAreaAtualizacaoCadastral();
		filtroAreaAtualizacao.adicionarParametro(new ParametroSimples(
				FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, form.getLocalidade()));
		filtroAreaAtualizacao.adicionarParametro(new ParametroNulo(
				FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL));
		
		filtroAreaAtualizacao.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.EMPRESA);
		filtroAreaAtualizacao.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.LOCALIDADE);
		
		Collection<?> colecaoAreaAtualizacaoCadastral = 
			fachada.pesquisar(filtroAreaAtualizacao, AreaAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoAreaAtualizacaoCadastral)){
			AreaAtualizacaoCadastral areaAtualizacao = 
				(AreaAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoAreaAtualizacaoCadastral);
			
			if(areaAtualizacao.getCodigoSituacao().equals(ConstantesSistema.INDICADOR_LIBERADO)){
				String[] mensagem = new String[2];
				mensagem[0] = areaAtualizacao.getLocalidade().getDescricao();
				mensagem[1] = areaAtualizacao.getEmpresa().getDescricao();
				throw new ActionServletException("atencao.localidade_ja_liberada_empresa", null, mensagem);
			}
		}
		
		FiltroAreaAtualizacaoCadastral filtroArea = new FiltroAreaAtualizacaoCadastral();
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, 
				form.getLocalidade()));
		
		if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
			filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL_CODIGO, 
				form.getSetorComercial()));
		}
		
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.EMPRESA);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.LOCALIDADE);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL);
		
		Collection<?> colecaoArea = fachada.pesquisar(filtroArea, AreaAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoArea)){
			AreaAtualizacaoCadastral areaAtualizacao = (AreaAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoArea);
			
			if(areaAtualizacao.getCodigoSituacao().equals(ConstantesSistema.INDICADOR_LIBERADO)){
				String[] mensagem = new String[2];
				mensagem[1] = areaAtualizacao.getEmpresa().getDescricao();
				if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
					mensagem[0] = areaAtualizacao.getSetorComercial().getDescricao();
					throw new ActionServletException("atencao.setor_comercial_ja_liberado_empresa", null, mensagem);
				}else{
					mensagem[0] = areaAtualizacao.getLocalidade().getDescricao();
					throw new ActionServletException("atencao.localidade_ja_liberada_empresa", null, mensagem);
				}
			}else{
				novoRegistro = false;
				
				areaAtualizacao.setEmpresa(areaAtualizacaoCadastral.getEmpresa());
				areaAtualizacao.setCodigoSituacao(new Short("1"));
				areaAtualizacao.setDataLiberacao(new Date());
				areaAtualizacao.setUltimaAlteracao(new Date());
				
				fachada.atualizar(areaAtualizacao);
			}
		}
		
		if(novoRegistro){
			areaAtualizacaoCadastral.setCodigoSituacao(new Short("1"));
			areaAtualizacaoCadastral.setDataLiberacao(new Date());
			areaAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			fachada.inserir(areaAtualizacaoCadastral);
		}
		
		if(localidade != null){
			Municipio municipio = localidade.getMunicipio();
			if(municipio != null){
				//municipio.setIndicadorLogradouroBloqueado(new Short("1"));
				
				getFachada().atualizar(municipio);
			}
		}
		
		String mensagemSucesso = "Localidade liberada com sucesso.";
		
		montarPaginaSucesso(httpServletRequest, mensagemSucesso,
			"Liberar outra Localidade", "exibirLiberarLocalidadeAtualizacaoCadastralAction.do?menu=sim");
				
		return retorno;
	}
}