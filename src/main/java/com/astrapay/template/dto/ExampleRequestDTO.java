package com.astrapay.template.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExampleRequestDTO {

//	A validação é feita por anotações. Nenhum outro código é necessário	
	@NotBlank(message = "the field name is mandatory")
	private String nome;

	@DecimalMin(value = "2.0", message = "the minimn amount is 2.0")
	private Double valor;

    @NotNull
	private LocalDateTime  dataCriacao;

	@Positive
	private Integer quantidade;

	@Email
	private String email;

	@Max(value = 3)
	private Integer limiteErros;
	
	
}
