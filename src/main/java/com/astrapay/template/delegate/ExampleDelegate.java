package com.astrapay.template.delegate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.astrapay.template.dto.ExampleRequestDTO;
import com.astrapay.template.dto.ExampleResponseDTO;
import com.astrapay.template.dto.ExampleUpdateRequestDTO;
import com.astrapay.template.repository.service.ExampleRepositoryService;
import com.astrapay.template.restservice.ExampleRestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExampleDelegate {

	private final ExampleRepositoryService oExampleRepositoryService;
	private final ExampleRestService oExampleRestService;

	public ExampleResponseDTO save(ExampleRequestDTO oExampleRequestDTO) {
		return oExampleRepositoryService.save(oExampleRequestDTO);
	}

	public ExampleResponseDTO update(ExampleUpdateRequestDTO oExampleUpdateRequestDTO) {

		return oExampleRepositoryService.update(oExampleUpdateRequestDTO);
	}

	public ExampleResponseDTO findOne(Integer id) {
		return oExampleRepositoryService.findOne(id);

	}

	public List<ExampleResponseDTO> listAll() {
		return oExampleRepositoryService.listAll();

	}

	public void delete(Integer id) {
		oExampleRepositoryService.delete(id);
	}
	
	public void restTemplate() {
		oExampleRestService.restTemplate();
	}
}
