package com.astrapay.template.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tabela_exemplo")

@Getter
@Setter
@ToString
public class Example {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "email")
	private String email;

	@Column(name = "limite_erros")
	private Integer limiteErros;
}
