package gcom.gui.portal;

import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Arthur Carvalho
 * @date 14/12/2011
 *
 */
public class InserirClientePortalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirClientePortal");

		Fachada fachada = Fachada.getInstancia();
		
		InserirClientePortalActionForm form = (InserirClientePortalActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Validar Cliente Bloqueado
		Collection<ClienteVirtual> colecaoClienteVirtual = 
			fachada.pesquisarSituacaoClienteCadastradoPortal(Integer.parseInt(form.getMatricula()));
		
		boolean bloqueado = false;
		if(!Util.isVazioOrNulo(colecaoClienteVirtual)){
			int numeroVezesBloqueado = 0;
			Iterator<?> iterator = colecaoClienteVirtual.iterator();
			while(iterator.hasNext()){
				ClienteVirtual clienteVirtual = (ClienteVirtual) iterator.next();
				
				if(String.valueOf(clienteVirtual.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_CADASTRO_REJEITADO)){
					numeroVezesBloqueado++;
				}else{
					break;
				}
			}
			if(String.valueOf(numeroVezesBloqueado).equals("3")){
				bloqueado = true;
			}
		}
		
		ClienteVirtual clienteVirtual = new ClienteVirtual();
		
		Imovel imovel = new Imovel();
		imovel.setId(Integer.valueOf(form.getMatricula()));
		clienteVirtual.setImovel(imovel);
		clienteVirtual.setNome(form.getNome().toUpperCase());
		clienteVirtual.setEmail(form.getEmail());
		
		FoneTipo foneTipo = new FoneTipo();
		foneTipo.setId(Integer.valueOf(form.getTipoTelefone()));
		clienteVirtual.setFoneTipo(foneTipo);
		
		String telefone = form.getNumeroTelefone().trim();
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		telefone = telefone.replace(" ", "");
	
		String ddd = telefone.substring(0, 2);
		String numeroTelefone = telefone.substring(2);
		
//		validaCampoInvalido(form);
		
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo(FiltroFoneTipo.DESCRICAO);
		Collection<FoneTipo> colecaoFone = (Collection<FoneTipo>) Fachada.getInstancia().pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		httpServletRequest.setAttribute("tipoTelefone", colecaoFone);
		
		
		clienteVirtual.setDdd(ddd);
		clienteVirtual.setNumeroTelefone(numeroTelefone);
		clienteVirtual.setNomeContato(form.getNomeContato().toUpperCase());
		clienteVirtual.setIndicadorPessoaFisicaJuridica(Short.valueOf(form.getIndicadorTipoPessoa()));
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.DDD, ddd));
		Collection<Municipio> colecaoMunicipio = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if ( colecaoMunicipio == null || colecaoMunicipio.isEmpty() ) {
			if ( form.getCpf() != null && !form.getCpf().equals("") ) {
				httpServletRequest.setAttribute("tipoPessoa", "1");
				httpServletRequest.setAttribute("tipoPessoaFisica", true);
			} else {
				httpServletRequest.setAttribute("tipoPessoa", "2");
				httpServletRequest.setAttribute("tipoPessoaJuridica", true);
			}
			
			httpServletRequest.setAttribute("dddInvalido", true);
			return retorno;
		}
		
		
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
				
				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado= form.getNome().replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
					
					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");
						
						if (nomeGenerico.equalsIgnoreCase(form.getNome())
								|| nomeGenericoFormatado.equalsIgnoreCase(form.getNome())
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
						
							if ( form.getCpf() != null && !form.getCpf().equals("") ) {
								httpServletRequest.setAttribute("tipoPessoa", "1");
								httpServletRequest.setAttribute("tipoPessoaFisica", true);
							} else {
								httpServletRequest.setAttribute("tipoPessoa", "2");
								httpServletRequest.setAttribute("tipoPessoaJuridica", true);
							}
							
							httpServletRequest.setAttribute("nomeGenericoInvalido", true);
							return retorno;
						}
					}
				}
		}
		
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString()
				.equals(ConstantesSistema.NAO.toString())) {
		
			String nomeFormatado= form.getNome().replaceAll(" ", "");
			if (nomeFormatado.length() < 10) {
				if ( form.getCpf() != null && !form.getCpf().equals("") ) {
					httpServletRequest.setAttribute("tipoPessoa", "1");
					httpServletRequest.setAttribute("tipoPessoaFisica", true);
				} else {
					httpServletRequest.setAttribute("tipoPessoa", "2");
					httpServletRequest.setAttribute("tipoPessoaJuridica", true);
				}
				
				httpServletRequest.setAttribute("nomeMenorDez", true);
				return retorno;
			}
			
		}
		
		
		if ( form.getRamalTelefone() != null && !form.getRamalTelefone().equals("") ) {
			clienteVirtual.setNumeroFoneRamal(form.getRamalTelefone());			
		}
		
		if ( form.getIndicadorTipoPessoa().equals("1") ) {
			
			
			if( !Util.validacaoCPF(form.getCpf())) {
				httpServletRequest.setAttribute("tipoPessoa", "1");
				httpServletRequest.setAttribute("tipoPessoaFisica", true);
				httpServletRequest.setAttribute("clienteCpfInvalido", true);
				return retorno;
			}
			
			
			clienteVirtual.setCpf(form.getCpf());
			clienteVirtual.setNomeMae(form.getNomeMae().toUpperCase());
			
			PessoaSexo pessoaSexo = new PessoaSexo();
			pessoaSexo.setId(Integer.valueOf(form.getSexo()));
			clienteVirtual.setPessoaSexo(pessoaSexo);
			
			clienteVirtual.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
			Profissao profissao = new Profissao();
			profissao.setId(Integer.valueOf(form.getProfissao()));
			clienteVirtual.setProfissao(profissao);
			
			clienteVirtual.setRg(form.getRg());
			clienteVirtual.setDataEmissaoRg(Util.converteStringParaDate(form.getDataEmissao()));
			
			OrgaoExpedidorRg expedidorRg = new OrgaoExpedidorRg();
			expedidorRg.setId(Integer.valueOf(form.getOrgaoExpedidor()));
			clienteVirtual.setOrgaoExpedidorRg(expedidorRg);
			
			UnidadeFederacao federacao = new UnidadeFederacao();
			federacao.setId(Integer.valueOf(form.getEstado()));
			clienteVirtual.setUnidadeFederacao(federacao);
			
		
		} else {
			if( !Util.validacaoCNPJ(form.getCnpj()) ) {
				httpServletRequest.setAttribute("tipoPessoa", "2");
				httpServletRequest.setAttribute("tipoPessoaJuridica", true);
				httpServletRequest.setAttribute("clienteCnpjInvalido", true);
				return retorno;
			}
			clienteVirtual.setCnpj(form.getCnpj());
			
			RamoAtividade ramoAtividade = new RamoAtividade();
			ramoAtividade.setId(Integer.valueOf(form.getRamoAtividade()));
			clienteVirtual.setRamoAtividade(ramoAtividade);
		}
		
		clienteVirtual.setUltimaAlteracao(new Date());
		clienteVirtual.setDataCadastro(new Date());
		
		if(!bloqueado){
			clienteVirtual.setCodigoSituacao(new Short("1"));
		}else{
			clienteVirtual.setCodigoSituacao(new Short("4"));
		}
		
		fachada.inserir(clienteVirtual);
		
		if(!bloqueado){
		
			retorno = actionMapping.findForward("clienteCadastradoComSucesso");
			
			String mensagemSucesso = "Os dados cadastrais foram atualizados com sucesso e estão vinculados à matrícula "
					+ clienteVirtual.getImovel().getId();
	
			httpServletRequest.setAttribute("mensagemSucesso", mensagemSucesso);
		}else{
			retorno = actionMapping.findForward("clienteCadastradoComSucesso");
			
			String mensagemAvisoCadastro = "Seu cadastro encontra-se em análise, qualquer dúvida entrar em contato com a central "
					+ "de atendimento pelo telefone 0800-081-0195.";
			httpServletRequest.setAttribute("mensagemAvisoCadastro", mensagemAvisoCadastro);
		}
		
		return retorno;
	}
	
}