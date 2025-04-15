package com.devsuperior.clientsCrud.dtos;

import java.time.LocalDate;

import com.devsuperior.clientsCrud.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {

	private Long id;

	@NotBlank(message = "Nome não pode ser vazio")
	private String name;
	
	private String cpf;
	
	private Double income;

	@PastOrPresent(message = "Data de Nascimento não pode ser data futura")
	private LocalDate birthDate;
	
	private Integer children;

	public ClientDTO() {

	}

	public ClientDTO(Long id, @NotBlank(message = "Campo requerido") String name, String cpf, Double income,
			LocalDate birthDate, @PastOrPresent Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.income = entity.getIncome();
		this.birthDate = entity.getBirthDate();
		this.children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

}
