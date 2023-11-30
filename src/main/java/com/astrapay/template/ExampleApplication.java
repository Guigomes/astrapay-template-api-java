package com.astrapay.template;

import java.util.concurrent.Executor;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan("com.astrapay")
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@Bean
	Executor taskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("AstraAsync-");
		executor.initialize();
		return executor;
	}

	@Bean
	RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(5000);
		httpRequestFactory.setConnectTimeout(5000);

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(50);
		connectionManager.setDefaultMaxPerRoute(20);

		var httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();

		httpRequestFactory.setHttpClient(httpClient);

		return new RestTemplate(httpRequestFactory);
	}

}
