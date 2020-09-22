package com.democrata.dto;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "xmlcep")
@XmlAccessorType(XmlAccessType.PROPERTY)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cep", "logradouro", "complemento", "bairro", "localidade", "uf", "unidade", "ibge", "gia" })
public class AddressDto {

	@JsonProperty("cep")
	private String cep;
	@JsonProperty("logradouro")
	private String logradouro;
	@JsonProperty("complemento")
	private String complemento;
	@JsonProperty("bairro")
	private String bairro;
	@JsonProperty("localidade")
	private String localidade;
	@JsonProperty("uf")
	private String uf;
	@JsonProperty("unidade")
	private String unidade;
	@JsonProperty("ibge")
	private String ibge;
	@JsonProperty("gia")
	private String gia;
	@JsonProperty("ddd")
	private String ddd;
	@JsonProperty("siafi")
	private String siafi;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public AddressDto() {
	}

	/**
	 *
	 * @param uf
	 * @param complemento
	 * @param logradouro
	 * @param bairro
	 * @param localidade
	 * @param ibge
	 * @param unidade
	 * @param gia
	 * @param cep
	 * @param ddd
	 * @param siafi
	 */
	public AddressDto(String cep, String logradouro, String complemento, String bairro, String localidade, String uf,
			String unidade, String ibge, String gia, String ddd, String siafi) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.unidade = unidade;
		this.ibge = ibge;
		this.gia = gia;
		this.ddd = ddd;
		this.siafi = siafi;
	}

	@JsonProperty("cep")
	public String getCep() {
		return cep;
	}

	@JsonProperty("cep")
	public void setCep(String cep) {
		this.cep = cep;
	}

	@JsonProperty("logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	@JsonProperty("logradouro")
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@JsonProperty("complemento")
	public String getComplemento() {
		return complemento;
	}

	@JsonProperty("complemento")
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@JsonProperty("bairro")
	public String getBairro() {
		return bairro;
	}

	@JsonProperty("bairro")
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@JsonProperty("localidade")
	public String getLocalidade() {
		return localidade;
	}

	@JsonProperty("localidade")
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	@JsonProperty("uf")
	public String getUf() {
		return uf;
	}

	@JsonProperty("uf")
	public void setUf(String uf) {
		this.uf = uf;
	}

	@JsonProperty("unidade")
	public String getUnidade() {
		return unidade;
	}

	@JsonProperty("unidade")
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@JsonProperty("ibge")
	public String getIbge() {
		return ibge;
	}

	@JsonProperty("ibge")
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@JsonProperty("gia")
	public String getGia() {
		return gia;
	}

	@JsonProperty("gia")
	public void setGia(String gia) {
		this.gia = gia;
	}

	@JsonProperty("ddd")
	public String getDdd() {
		return ddd;
	}

	@JsonProperty("ddd")
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	@JsonProperty("siafi")
	public String getSiafi() {
		return siafi;
	}

	@JsonProperty("siafi")
	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "AddressDto [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro="
				+ bairro + ", localidade=" + localidade + ", uf=" + uf + ", unidade=" + unidade + ", ibge=" + ibge
				+ ", gia=" + gia + ", ddd=" + ddd + ", siafi=" + siafi + ", additionalProperties=" + additionalProperties 
				+ "]";
	}

}