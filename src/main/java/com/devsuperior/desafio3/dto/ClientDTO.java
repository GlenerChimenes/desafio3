package com.devsuperior.desafio3.dto;

import java.time.LocalDate;

import com.devsuperior.desafio3.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {

	private Long id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String name;
	private String cpf;
	private Double income;
	@PastOrPresent(message = "Data de nascimento não pode ser data futura")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.income = client.getIncome();
		this.birthDate = client.getBirthDate();
		this.children = client.getChildren();
	}

	public ClientDTO(Long id, String name,String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

}
