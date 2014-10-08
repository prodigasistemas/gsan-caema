package gcom.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class OrdemRepavimentadoraServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal quantidadeServico;
	
	private Date ultimaAlteracao;
	
	private ServicoRepavimentadora servicoRepavimentadora;
	
	private OrdemServico ordemServico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidadeServico() {
		return quantidadeServico;
	}

	public void setQuantidadeServico(BigDecimal quantidadeServico) {
		this.quantidadeServico = quantidadeServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ServicoRepavimentadora getServicoRepavimentadora() {
		return servicoRepavimentadora;
	}

	public void setServicoRepavimentadora(ServicoRepavimentadora servicoRepavimentadora) {
		this.servicoRepavimentadora = servicoRepavimentadora;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}
