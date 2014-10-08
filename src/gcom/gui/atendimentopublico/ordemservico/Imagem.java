package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.cadastro.atualizacaocadastral.ImovelFotoAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.util.Util;

import java.util.ArrayList;

public class Imagem{

	public static byte[] getImagem(Integer idFoto){
		byte[] foto = null; 
		ArrayList<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) Fachada.getInstancia().pesquisarFotosOrdemServico(idFoto, false);
		if(!Util.isVazioOrNulo(fotos)){
			foto = fotos.get(0).getFoto();
		}else{
			throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
		}
		return foto;
	}
	
	public static byte[] getImagemImovel(Integer idFoto){
		byte[] foto = null; 
		ArrayList<ImovelFotoAtualizacaoCadastral> fotos = (ArrayList<ImovelFotoAtualizacaoCadastral>) Fachada.getInstancia().pesquisarImovelFotoAtualizacaoCadastral(idFoto, false);
		if(!Util.isVazioOrNulo(fotos)){
			foto = fotos.get(0).getFotoImovel();
		}
		return foto;
	}
}