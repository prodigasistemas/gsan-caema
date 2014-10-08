package gcom.atendimentopublico;

import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoGcom;

public class UnidadeRepavimentadoraMunicipioBairrosPK extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	private UnidadeRepavimentadoraMunicipio unidadeRepavimentadoraMunicipioId;

	private Bairro bairroId;

	public UnidadeRepavimentadoraMunicipioBairrosPK() {
		super();
	}

	public UnidadeRepavimentadoraMunicipio getUnidadeRepavimentadoraMunicipioId() {
		return unidadeRepavimentadoraMunicipioId;
	}

	public void setUnidadeRepavimentadoraMunicipioId(UnidadeRepavimentadoraMunicipio unidadeRepavimentadoraMunicipioId) {
		this.unidadeRepavimentadoraMunicipioId = unidadeRepavimentadoraMunicipioId;
	}

	public Bairro getBairroId() {
		return bairroId;
	}

	public void setBairroId(Bairro bairroId) {
		this.bairroId = bairroId;
	}

	public UnidadeRepavimentadoraMunicipioBairrosPK(
			UnidadeRepavimentadoraMunicipio unidadeRepavimentadoraMunicipioId, Bairro bairroId) {
		super();
		this.unidadeRepavimentadoraMunicipioId = unidadeRepavimentadoraMunicipioId;
		this.bairroId = bairroId;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
		retorno[0] = "unidadeRepavimentadoraMunicipioId";
		retorno[1] = "bairroId";
		return retorno;
	}

}
