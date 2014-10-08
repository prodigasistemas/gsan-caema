package gcom.cadastro.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Davi Menezes
 * @date 22/11/2012
 *
 */
public class FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral(){
		
	}
	
	public FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_MEDICAO_TIPO = "medicaoTipo.id";
	
	public final static String MEDICAO_TIPO = "medicaoTipo";
	
	public final static String ID_HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao.id";
	
	public final static String HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao";
	
	public final static String ID_HIDROMETRO_PROTECAO = "hidrometroProtecao.id";
	
	public final static String HIDROMETRO_PROTECAO = "hidrometroProtecao";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral";
}
