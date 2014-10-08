package gcom.gui.portal;

import java.util.Collection;

import gcom.cadastro.ImovelCadastroSorteio;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1380] Cadastrar Imóveis para Sorteio
 * 
 * @author Mariana Victor
 * @date 19/10/2012
 */
public class CadastrarImovelSorteioAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("confirmacaoCadastroImovelSorteio");
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String operacao = (String) sessao.getAttribute("operacao");
		
		if(operacao == null || !operacao.equals("sucesso")){
			CadastrarImovelSorteioActionForm form = (CadastrarImovelSorteioActionForm) actionForm;
			
			if (!this.validarCamposInformados(form, httpServletRequest)) {
				return actionMapping.findForward("exibirCadastrarImovelSorteio");
			}
			
			CadastrarImovelSorteioActionHelper helper = this.carregarCamposFormulario(form, usuarioLogado);
			
			String mensagemValidacao = this.getFachada().verificarImovelApto(helper);
			
			if (mensagemValidacao != null 
					&& !mensagemValidacao.trim().equals("")) {

				httpServletRequest.setAttribute("mensagemImovelNaoApto", true);
				sessao.setAttribute("mensagemValidacao", "'" + mensagemValidacao + "'");
				return actionMapping.findForward("exibirCadastrarImovelSorteio"); 
				
			} else {
				ImovelCadastroSorteio imovelCadastroSorteio = this.getFachada()
						.cadastrarImovelParaSorteio(true, null, helper);
				form.setNumeroSorteio(imovelCadastroSorteio.getNumeroGerado().toString());
				sessao.setAttribute("operacao", "sucesso");
			}
			
		}

		return retorno;
	}

	/**
	 * [FE0002] Validar campos informados.
	 * */
	private boolean validarCamposInformados(CadastrarImovelSorteioActionForm form, 
			HttpServletRequest httpServletRequest) {
		
		if ( form.getIndicadorCpfCnpj().equals("1") ) {
			
			if( !Util.validacaoCPF(form.getCpfCliente()
							.replace(" ","")
							.replace("-", "")
							.replace(".", ""))) {
				httpServletRequest.setAttribute("clienteCpfInvalido", true);
				return false;
			}
		} else {
			
			if( !Util.validacaoCNPJ(form.getCnpjCliente()
							.replace(" ","")
							.replace("-", "")
							.replace(".", "")
							.replace("/", ""))) {
				httpServletRequest.setAttribute("clienteCnpjInvalido", true);
				return false;
			}
		}
		
		if (form.getTelefoneFixo() != null 
				&& !form.getTelefoneFixo().replace(" ","").equals("")
				&& form.getTelefoneFixo().replace(" ","").length() != 13) {
			httpServletRequest.setAttribute("telefoneFixoInvalido", true);
			return false;
		}
		
		if (form.getTelefoneCelular() != null 
				&& !form.getTelefoneCelular().replace(" ","").equals("")
				&& form.getTelefoneCelular().replace(" ","").length() != 13) {
			httpServletRequest.setAttribute("telefoneCelularInvalido", true);
			return false;
		}
		
		if (form.getDataNascimento() != null 
				&& !form.getDataNascimento().trim().equals("")
				&& !Util.verificaSeDataValida(form.getDataNascimento(), "dd/MM/yyyy")) {
			httpServletRequest.setAttribute("dataInvalida", true);
			return false;
		}
		
		/**
		 *@author Flávio Ferreira
		 *@date 12/09/2013
		 */
		
		//  validaçao do CEP
		Municipio municipio = null;
		String idMunicipio = form.getIdMunicipio();
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		Collection colecaoCep = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(!colecaoCep.isEmpty()){
			municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoCep);
		}
		
		int cepInicial = municipio.getCepInicio();
		int cepFinal = municipio.getCepFim();
		String cepString = form.getCep().replace("-", "");
		
		if(Util.verificaSeNumeroNatural(cepString)){
			int cep = Integer.parseInt(cepString);
			 
			if (form.getCep() != null && !form.getCep().trim().equals("") && !(cep >= cepInicial && cep <= cepFinal)) {
				httpServletRequest.setAttribute("cepInvalido", true);
				return false;
			}
			
		}else{
			httpServletRequest.setAttribute("cepInvalido", true);
			return false;
		}
		
		//a validação do email está sendo feita no javascript
		
		return true;
	}
	
	private CadastrarImovelSorteioActionHelper carregarCamposFormulario(
			CadastrarImovelSorteioActionForm form,
			Usuario usuarioLogado) {
		
		CadastrarImovelSorteioActionHelper helper = new CadastrarImovelSorteioActionHelper();

		if(form.getMatricula() != null && !form.getMatricula().trim().equals("")){
			helper.setIdImovel(new Integer(form.getMatricula()));
		}

		if(form.getNome() != null && !form.getNome().trim().equals("")){
			helper.setNome(form.getNome());
		}

		if(form.getEmail() != null && !form.getEmail().trim().equals("")){
			helper.setEmail(form.getEmail());
		}

		if(form.getTelefoneFixo() != null && !form.getTelefoneFixo().replace(" ","").equals("")){
			helper.setDddTelefoneFixo(Util.obterDDDTelefone(form.getTelefoneFixo().replace(" ","")));
			helper.setNumeroTelefoneFixo(Util.obterNumeroTelefone(form.getTelefoneFixo().replace(" ","")));
		}

		if(form.getTelefoneCelular() != null && !form.getTelefoneCelular().replace(" ","").equals("")){
			helper.setDddTelefoneCelular(Util.obterDDDTelefone(form.getTelefoneCelular().replace(" ","")));
			helper.setNumeroTelefoneCelular(Util.obterNumeroTelefone(form.getTelefoneCelular().replace(" ","")));
		}

		if(form.getDataNascimento() != null && !form.getDataNascimento().trim().equals("")){
			helper.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}

		if(form.getRg() != null && !form.getRg().replace(" ","").equals("")){
			helper.setRg(form.getRg());
		}

		if(form.getIndicadorCpfCnpj() != null && !form.getIndicadorCpfCnpj().trim().equals("")){
			helper.setIndicadorCpfCnpj(new Short(form.getIndicadorCpfCnpj()));
		}

		if(form.getCpfCliente() != null && !form.getCpfCliente().replace(" ","").equals("")){
			helper.setCpfCliente(form.getCpfCliente()
				.replace(" ","").replace("-", "").replace(".", ""));
		}

		if(form.getCnpjCliente() != null && !form.getCnpjCliente().replace(" ","").equals("")){
			helper.setCnpjCliente(form.getCnpjCliente()
				.replace(" ","").replace("-", "").replace(".", "").replace("-", "").replace("/", ""));
		}

		if(form.getIndicadorTipoRelacao() != null && !form.getIndicadorTipoRelacao().trim().equals("")){
			helper.setIndicadorTipoRelacao(new Short(form.getIndicadorTipoRelacao()));
		}

		if(form.getIdMunicipio() != null && !form.getIdMunicipio().trim().equals("")){
			helper.setIdMunicipio(new Integer(form.getIdMunicipio()));
		}

		if(form.getIdBairro() != null && !form.getIdBairro().trim().equals("")){
			helper.setIdBairro(new Integer(form.getIdBairro()));
		}

		if(form.getLogradouro() != null && !form.getLogradouro().trim().equals("")){
			helper.setLogradouro(form.getLogradouro());
		}

		if(form.getNumeroEndereco() != null && !form.getNumeroEndereco().replace(" ","").equals("")){
			helper.setNumeroEndereco(new Integer(form.getNumeroEndereco()));
		}

		if(form.getComplemento() != null && !form.getComplemento().trim().equals("")){
			helper.setComplemento(form.getComplemento());
		}

		if(form.getCep() != null && !form.getCep().replace(" ","").equals("")){
			helper.setCep(new Integer(form.getCep()
				.replace("-", "")));
		}

		if(usuarioLogado != null 
				&& usuarioLogado.getId() != null){
			helper.setIdUsuarioLogado(usuarioLogado.getId());
		}
		
		return helper;
	}

}