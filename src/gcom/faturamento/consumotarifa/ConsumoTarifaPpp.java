package gcom.faturamento.consumotarifa;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ConsumoTarifaPpp extends ObjetoTransacao {

	/**
	 * [UC0168] Inserir Tarifa de Consumo.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 13/05/2013
	 */
	private static final long serialVersionUID = 1L;
	
public static final int ASSOCIAR_TARIFA_CONSUMO_IMOVEIS = 716;	
	
    /** identifier field */
	@ControleAlteracao(funcionalidade={ASSOCIAR_TARIFA_CONSUMO_IMOVEIS})
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Set consumoTarifaVigencias;
    
    /** nullable persistent field */
    private LigacaoAguaPerfil ligacaoAguaPerfil;
    
    /** nullable persistent field */
    private TarifaTipoCalculo tarifaTipoCalculo;
    
    private Short indicadorContratoDemanda;
    
	public static final Integer CONSUMO_NORMAL  = new Integer("1");
    
    public static final Integer CONSUMO_SOCIAL  = new Integer("2");

    public static final Integer AGUA_BRUTA_ADUTORA = new Integer("21");
    
    /** full constructor */
    public ConsumoTarifaPpp(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ConsumoTarifaPpp() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Set getConsumoTarifaVigencias() {
		return consumoTarifaVigencias;
	}

	public void setConsumoTarifaVigencias(
			Set consumoTarifaVigencias) {
		this.consumoTarifaVigencias = consumoTarifaVigencias;
	}

	/**
	 * @return Retorna o campo ligacaoAguaPerfil.
	 */
	public LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	/**
	 * @param ligacaoAguaPerfil O ligacaoAguaPerfil a ser setado.
	 */
	public void setLigacaoAguaPerfil(LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public TarifaTipoCalculo getTarifaTipoCalculo() {
		return tarifaTipoCalculo;
	}

	public void setTarifaTipoCalculo(TarifaTipoCalculo tarifaTipoCalculo) {
		this.tarifaTipoCalculo = tarifaTipoCalculo;
	}
	
	public Short getIndicadorContratoDemanda() {
		return indicadorContratoDemanda;
	}

	public void setIndicadorContratoDemanda(Short indicadorContratoDemanda) {
		this.indicadorContratoDemanda = indicadorContratoDemanda;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID, this.getId()));
		return filtroConsumoTarifa; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao() + "";
	}

}
