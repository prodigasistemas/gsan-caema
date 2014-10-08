package gcom.micromedicao;

import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1359] - Requisições Foto Anormalidade
 * 
 * @author Davi Menezes
 * @date 17/07/2012
 *
 */
public class MovimentoRoteiroEmpresaFoto extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	private LeituraAnormalidade leituraAnormalidade;
	private Integer anoMesReferencia;
	
	private byte[] foto;
	
	private MedicaoTipo medicaoTipo;
	private FotoSituacaoOrdemServico fotoSituacao;	
	
	private Date ultimaAlteracao;
	
	private ConsumoAnormalidade consumoAnormalidade;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroMovimentoRoteiroEmpresaFoto filtro = new FiltroMovimentoRoteiroEmpresaFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroMovimentoRoteiroEmpresaFoto.ID, this.id));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}
	
	public FotoSituacaoOrdemServico getFotoSituacao() {
		return fotoSituacao;
	}

	public void setFotoSituacao(FotoSituacaoOrdemServico fotoSituacao) {
		this.fotoSituacao = fotoSituacao;
	}

	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}
	
}
