package com.astrapay.template.restservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.astrapay.logger.AstraLogger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExampleRestService {

	private final RestTemplate restTemplate;

	private final AstraLogger logger;

	public void restTemplate() {

		String result = restTemplate.getForObject("http://www.google.com.br", String.class);

		logger.info(result);
	}

}
