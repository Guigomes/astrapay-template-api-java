package com.astrapay.template.repository.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astrapay.astrautils.exception.AstraNotFoundException;
import com.astrapay.template.dto.ExampleRequestDTO;
import com.astrapay.template.dto.ExampleResponseDTO;
import com.astrapay.template.dto.ExampleUpdateRequestDTO;
import com.astrapay.template.model.Example;
import com.astrapay.template.repository.ExampleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExampleRepositoryService {
	private final ExampleRepository repository;

	private final ModelMapper modelMapper;

	public ExampleResponseDTO save(ExampleRequestDTO oExampleRequestDTO) {

		Example oExample = modelMapper.map(oExampleRequestDTO, Example.class);

		var newExample = repository.saveAndFlush(oExample);

		return modelMapper.map(newExample, ExampleResponseDTO.class);

	}

	public ExampleResponseDTO update(ExampleUpdateRequestDTO oExampleUpdateRequestDTO) {

		Example oExample = modelMapper.map(oExampleUpdateRequestDTO, Example.class);

		repository.findById(oExample.getId()).orElseThrow(
				() -> new AstraNotFoundException(String.format("Resource with id % not found", oExample.getId())));

		repository.saveAndFlush(oExample);

		return modelMapper.map(oExample, ExampleResponseDTO.class);

	}

	@Transactional(readOnly = true)
	public ExampleResponseDTO findOne(Integer id) {

		var result = repository.findById(id)
				.orElseThrow(() -> new AstraNotFoundException(String.format("Resource with id % not found", id)));

		return modelMapper.map(result, ExampleResponseDTO.class);

	}

	@Transactional(readOnly = true)
	public List<ExampleResponseDTO> listAll() {

		var result = repository.findAll();

		return result.stream().map(source -> modelMapper.map(source, ExampleResponseDTO.class))
				.collect(Collectors.toList());

	}

	@Transactional
	public void delete(Integer id) {
		Example oExample = repository.findById(id)
				.orElseThrow(() -> new AstraNotFoundException(String.format("Resource with id % not found", id)));
		repository.delete(oExample);
	}
}
