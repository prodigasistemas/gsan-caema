package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.ArrayList;

import gcom.cadastro.atualizacaocadastral.LogradouroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroBairroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroCepAtlzCad;

public class AtualizacaoCadastralLogradouroHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idSubstituirLogra;
	private LogradouroAtlzCad logradouroAtlzCad;
	private String bairros;
	private ArrayList<LogradouroBairroAtlzCad> colecaoLogardouroBairroAtlzCad;
	private ArrayList<LogradouroCepAtlzCad> colecaoLogradouroCepAtlzCad;
	
	public String getIdSubstituirLogra() {
		return idSubstituirLogra;
	}
	public void setIdSubstituirLogra(String idSubstituirLogra) {
		this.idSubstituirLogra = idSubstituirLogra;
	}
	public LogradouroAtlzCad getLogradouroAtlzCad() {
		return logradouroAtlzCad;
	}
	public void setLogradouroAtlzCad(LogradouroAtlzCad logradouroAtlzCad) {
		this.logradouroAtlzCad = logradouroAtlzCad;
	}
	public String getBairros() {
		return bairros;
	}
	public void setBairros(String bairros) {
		this.bairros = bairros;
	}
	public ArrayList<LogradouroBairroAtlzCad> getColecaoLogardouroBairroAtlzCad() {
		return colecaoLogardouroBairroAtlzCad;
	}
	public void setColecaoLogardouroBairroAtlzCad(
			ArrayList<LogradouroBairroAtlzCad> colecaoLogardouroBairroAtlzCad) {
		this.colecaoLogardouroBairroAtlzCad = colecaoLogardouroBairroAtlzCad;
	}
	public ArrayList<LogradouroCepAtlzCad> getColecaoLogradouroCepAtlzCad() {
		return colecaoLogradouroCepAtlzCad;
	}
	public void setColecaoLogradouroCepAtlzCad(
			ArrayList<LogradouroCepAtlzCad> colecaoLogradouroCepAtlzCad) {
		this.colecaoLogradouroCepAtlzCad = colecaoLogradouroCepAtlzCad;
	}
}
