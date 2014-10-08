package gcom.gui.portal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir o Questionario de Satisfacao do Cliente
 * 
 * @author Paulo Diniz
 * @date 23/06/2011
 */
public class ExibirCadastrarImovelSorteioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirCadastrarImovelSorteio");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		CadastrarImovelSorteioActionForm cadastrarImovelSorteioActionForm = 
				(CadastrarImovelSorteioActionForm) actionForm;
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getDataLimiteCadastroSorteio() != null
				&& Util.compararData(sistemaParametro.getDataLimiteCadastroSorteio(), new Date()) < 0 ) {

			throw new ActionServletException("atencao.inscricoes_sorteio.encerradas");
			
		}
		
		this.carregarFormulario(sessao, cadastrarImovelSorteioActionForm, httpServletRequest);
		
		Integer quantidadeInscritos = this.getFachada().pesquisarQuantidadeTotalInscritos();
		
		if (quantidadeInscritos == null) {
			cadastrarImovelSorteioActionForm.setTotalInscritos("0");
		} else {
			cadastrarImovelSorteioActionForm.setTotalInscritos(quantidadeInscritos.toString());
		}
		
		if(usuarioLogado == null 
				|| usuarioLogado.getId() == null){
			sessao.removeAttribute("idUsuarioLogado");
		}else{
			sessao.setAttribute("idUsuarioLogado", usuarioLogado.getId());
		}
		

		if (httpServletRequest.getParameter("baixarRegulamento") != null
				&& httpServletRequest.getParameter("baixarRegulamento").toString().trim().equalsIgnoreCase("sim")) {
			OutputStream out = null;

			try {
				File regulamento = new File(
					"/usr/local/jboss/bin/sorteio/Regulamento da Campanha Conta em Dia.pdf");
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				FileInputStream inputStream = new FileInputStream(
					regulamento);
				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;
				
				while ((numBytesRead = inputStream.read(temp, 0,
					INPUT_BUFFER_SIZE)) != -1) {
					byteArrayOutputStream.write(temp, 0, numBytesRead);
				}
				
				String mimeType = null;
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=Regulamento da Campanha Conta em Dia.pdf");
				mimeType = "application/pdf";
	
				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();
				
				out.write(byteArrayOutputStream.toByteArray());
				out.flush();
				out.close();

				inputStream.close();
				inputStream = null;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sessao.setAttribute("operacao", "exibirQuestionario");

		return retorno;
	}
	
	private void carregarFormulario(HttpSession sessao,
			CadastrarImovelSorteioActionForm cadastrarImovelSorteioActionForm,
			HttpServletRequest httpServletRequest){
		
		// exibir como padrão o campo "Usuário" selecionado
		if (cadastrarImovelSorteioActionForm.getIndicadorTipoRelacao() == null
				|| cadastrarImovelSorteioActionForm.getIndicadorTipoRelacao().trim().equals("")) {
			cadastrarImovelSorteioActionForm.setIndicadorTipoRelacao("2");
		}
		
		// exibir campo CPF/CNPJ
		if (cadastrarImovelSorteioActionForm.getIndicadorCpfCnpj() == null
				|| cadastrarImovelSorteioActionForm.getIndicadorCpfCnpj().trim().equals("")
				|| cadastrarImovelSorteioActionForm.getIndicadorCpfCnpj().trim().equals("1")) {
			
			cadastrarImovelSorteioActionForm.setIndicadorCpfCnpj("1");
			cadastrarImovelSorteioActionForm.setCnpjCliente(null);
			sessao.setAttribute("exibirCpf", true);

			if (httpServletRequest.getParameter("selecionaCpfCnpj") != null
					&& httpServletRequest.getParameter("selecionaCpfCnpj")
							.toString().trim().equalsIgnoreCase("sim")) {
				
				httpServletRequest.setAttribute("nomeCampo", 
					"cpfCliente");
			}
			
		} else if (cadastrarImovelSorteioActionForm.getIndicadorCpfCnpj().trim().equals("2")) {

			cadastrarImovelSorteioActionForm.setIndicadorCpfCnpj("2");
			cadastrarImovelSorteioActionForm.setCpfCliente(null);
			cadastrarImovelSorteioActionForm.setRg(null);
			cadastrarImovelSorteioActionForm.setDataNascimento(null);
			sessao.removeAttribute("exibirCpf");

			if (httpServletRequest.getParameter("selecionaCpfCnpj") != null
					&& httpServletRequest.getParameter("selecionaCpfCnpj")
							.toString().trim().equalsIgnoreCase("sim")) {
				
				httpServletRequest.setAttribute("nomeCampo", 
					"cnpjCliente");
			}
			
		}
		
		// habilitar/desabilitar botão "Cadastrar"
		if (httpServletRequest.getParameter("habilitaCadastrar") != null
				&& httpServletRequest.getParameter("habilitaCadastrar").toString().trim().equalsIgnoreCase("sim")) {
			
			sessao.setAttribute("habilitaCadastrar", true);
			httpServletRequest.setAttribute("nomeCampo", "indicadorAceitaRegulamento");
			
		} else if (httpServletRequest.getParameter("habilitaCadastrar") != null
				&& httpServletRequest.getParameter("habilitaCadastrar").toString().trim().equalsIgnoreCase("nao")) {

			sessao.removeAttribute("habilitaCadastrar");
			cadastrarImovelSorteioActionForm.setIndicadorAceitaRegulamento(null);
			httpServletRequest.setAttribute("nomeCampo", "indicadorAceitaRegulamento");
			
		}

		//carregar coleção de Município
		if(sessao.getAttribute("colecaoMunicipio") == null) {
			// Coleção de Município
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMunicipio.setCampoOrderBy(FiltroMunicipio.NOME);
	
			Collection<Municipio> colecaoMunicipio = 
				this.getFachada().pesquisar(
					filtroMunicipio, 
					Municipio.class.getName());
	
			sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		}
		
		//carregar coleção de Bairro
		if (httpServletRequest.getParameter("selecionarMunicipio") != null
				&& httpServletRequest.getParameter("selecionarMunicipio").toString().trim().equalsIgnoreCase("sim")
				&& cadastrarImovelSorteioActionForm.getIdMunicipio() != null
				&& !cadastrarImovelSorteioActionForm.getIdMunicipio().trim().equals("")
				&& !cadastrarImovelSorteioActionForm.getIdMunicipio().trim().equals("-1")) {
			
			// Coleção de Bairro
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, cadastrarImovelSorteioActionForm.getIdMunicipio()));
			filtroBairro.setCampoOrderBy(FiltroBairro.NOME);

			Collection<Bairro> colecaoBairro = 
				this.getFachada().pesquisar(
					filtroBairro, 
					Bairro.class.getName());

			sessao.setAttribute("colecaoBairro", colecaoBairro);
			httpServletRequest.setAttribute("nomeCampo", "idBairro");
			
		} else if (cadastrarImovelSorteioActionForm.getIdMunicipio() == null
				|| cadastrarImovelSorteioActionForm.getIdMunicipio().trim().equals("")
				|| cadastrarImovelSorteioActionForm.getIdMunicipio().trim().equals("-1")) {
			
			sessao.removeAttribute("colecaoBairro");
			
		}
		
		//limpar imóvel
		if (httpServletRequest.getParameter("limparImovel") != null
				&& httpServletRequest.getParameter("limparImovel").toString().trim().equalsIgnoreCase("sim")) {
			
			cadastrarImovelSorteioActionForm.setMatricula(null);
			cadastrarImovelSorteioActionForm.setInscricaoImovel(null);
			
		//pesquisar imóvel
		} else if (cadastrarImovelSorteioActionForm.getMatricula() != null
				&& !cadastrarImovelSorteioActionForm.getMatricula().trim().equals("")
				&& Util.isInteger(cadastrarImovelSorteioActionForm.getMatricula().trim())) {
			
			Integer idImovel = new Integer(cadastrarImovelSorteioActionForm.getMatricula().trim());
			
			String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(idImovel);
			
			if (inscricaoImovel != null
					&& !inscricaoImovel.trim().equals("")) {
				cadastrarImovelSorteioActionForm.setMatricula(
					idImovel.toString());
				cadastrarImovelSorteioActionForm.setInscricaoImovel(
					inscricaoImovel);
				
				sessao.removeAttribute("imovelInexistente");
				
			} else {
				cadastrarImovelSorteioActionForm.setMatricula(
					null);
				cadastrarImovelSorteioActionForm.setInscricaoImovel(
					"MATRÍCULA INEXISTENTE");
				sessao.setAttribute("imovelInexistente", true);
			}
			
			
		}
		
		//limpar todos os campos
		if (httpServletRequest.getParameter("limpar") != null
				&& httpServletRequest.getParameter("limpar").toString().trim().equalsIgnoreCase("sim")) {
			cadastrarImovelSorteioActionForm.setMatricula(null);
			cadastrarImovelSorteioActionForm.setInscricaoImovel(null);
			cadastrarImovelSorteioActionForm.setNome(null);
			cadastrarImovelSorteioActionForm.setEmail(null);
			cadastrarImovelSorteioActionForm.setTelefoneFixo(null);
			cadastrarImovelSorteioActionForm.setTelefoneCelular(null);
			cadastrarImovelSorteioActionForm.setDataNascimento(null);
			cadastrarImovelSorteioActionForm.setRg(null);
			cadastrarImovelSorteioActionForm.setIndicadorCpfCnpj("1");
			sessao.setAttribute("exibirCpf", true);
			cadastrarImovelSorteioActionForm.setCpfCliente(null);
			cadastrarImovelSorteioActionForm.setCnpjCliente(null);
			cadastrarImovelSorteioActionForm.setIndicadorTipoRelacao("2");
			cadastrarImovelSorteioActionForm.setIdMunicipio("-1");
			cadastrarImovelSorteioActionForm.setIdBairro("-1");
			sessao.removeAttribute("colecaoBairro");
			cadastrarImovelSorteioActionForm.setLogradouro(null);
			cadastrarImovelSorteioActionForm.setNumeroEndereco(null);
			cadastrarImovelSorteioActionForm.setComplemento(null);
			cadastrarImovelSorteioActionForm.setCep(null);
			cadastrarImovelSorteioActionForm.setIndicadorAceitaRegulamento(null);
			sessao.removeAttribute("habilitaCadastrar");
			httpServletRequest.setAttribute("nomeCampo", "matricula");
			
		}
	}
}