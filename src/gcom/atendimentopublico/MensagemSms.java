package gcom.atendimentopublico;

import java.util.Date;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class MensagemSms extends ObjetoTransacao{

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private SmsTipo smsTipo;
	private RegistroAtendimento registroAtendimento;
	private Long numeroCelular;
	private Short indicadorEnvio;
	private Date dataHoraEnvio;
	private Date ultimaAlteracao;

	public MensagemSms() {
		super();
	}
	
	public MensagemSms(SmsTipo smsTipo,
			RegistroAtendimento registroAtendimento, Long numeroCelular,
			Short indicadorEnvio, Date dataHoraEnvio, Date ultimaAlteracao) {
		super();
		this.smsTipo = smsTipo;
		this.registroAtendimento = registroAtendimento;
		this.numeroCelular = numeroCelular;
		this.indicadorEnvio = indicadorEnvio;
		this.dataHoraEnvio = dataHoraEnvio;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SmsTipo getSmsTipo() {
		return smsTipo;
	}

	public void setSmsTipo(SmsTipo smsTipo) {
		this.smsTipo = smsTipo;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Long getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(Long numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public Short getIndicadorEnvio() {
		return indicadorEnvio;
	}

	public void setIndicadorEnvio(Short indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}

	public Date getDataHoraEnvio() {
		return dataHoraEnvio;
	}

	public void setDataHoraEnvio(Date dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
}
