package com.astrapay.template.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.astrapay.astrautils.exception.AstraBusinessException;
import com.astrapay.astrautils.exception.AstraNotFoundException;


import com.astrapay.logger.AstraLogger;
import com.astrapay.template.delegate.ExampleDelegate;
import com.astrapay.template.dto.ExampleRequestDTO;
import com.astrapay.template.dto.ExampleResponseDTO;
import com.astrapay.template.dto.ExampleUpdateRequestDTO;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/example")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ExampleController {

	private final AstraLogger logger;

	private final ExampleDelegate exampleDelegate;

	@PostMapping("/")
	public ResponseEntity<ExampleResponseDTO> create(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId,

			@RequestBody @Valid ExampleRequestDTO request) throws Exception {

		logger.info("Criando novo exemplo: %s", request);

		var recursoCriado = exampleDelegate.save(request);

		return new ResponseEntity<>(recursoCriado, HttpStatus.CREATED);

	}

	@PutMapping("/")
	public ResponseEntity<ExampleResponseDTO> put(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId,

			@RequestBody @Valid ExampleUpdateRequestDTO request) throws Exception {

		logger.info("Atualizando  exemplo: %s", request);

		var updatedResource = exampleDelegate.update(request);

		return new ResponseEntity<>(updatedResource, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<ExampleResponseDTO>> listAll(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId)
			throws Exception {

		logger.info("Listando todos os exemplos");

		return ResponseEntity.ok(exampleDelegate.listAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<ExampleResponseDTO> findOne(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId,

			@PathVariable Integer id) throws Exception {

		logger.info("buscando um exemplo por id: " + id);

		return ResponseEntity.ok(exampleDelegate.findOne(id));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId,

			@PathVariable Integer id) throws Exception {

		logger.info("deletando um exemplo por id: " + id);
		exampleDelegate.delete(id);

	}

	@GetMapping("/businessException")
	public void businessException(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId)
			throws Exception {

		// Lançar uma AstraBusinessException já retornara um ResponseEntity com status
		// 400.
		logger.error("Log de Erro sem a exception");

		throw new AstraBusinessException("Teste businessException");

	}

	@GetMapping("/notFoundException")
	public void notFoundException(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId)
			throws Exception {

		// Lançar uma AstraNotFoundException já retornara um ResponseEntity com status
		// 404.
		var error = new AstraNotFoundException("Teste notFoundException");

		// Toda exception lançada já é logada automaticamente, faça isso somente se
		// quiser usar uma mensagem personalizada.
		logger.error("Log de Erro com a exception", error);

		throw error;

	}

	@GetMapping("/runtimeException")
	public void runtimeException(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId)
			throws Exception {

		// Qualquer outra exceção vai retornar um responseEntity com o status 500
		throw new RuntimeException("Teste RuntimeException");

	}

	@GetMapping("/restTemplate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void restTemplate(
			@RequestHeader("x-transaction-id") @Parameter(name = "x-transaction-id", description = "UUID da transação", example = "328d5e1a-06dd-4263-8081-0a2ec31c1f9a") String xTransactionId)
			throws Exception {

		exampleDelegate.restTemplate();

	}

}