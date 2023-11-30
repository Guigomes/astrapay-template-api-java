package com.astrapay.template.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExampleResponseDTO {

	private int id;

	private String nome;

	private Double valor;

	private LocalDateTime dataCriacao;

	private Integer quantidade;

	private String email;

	private Integer limiteErros;

}
