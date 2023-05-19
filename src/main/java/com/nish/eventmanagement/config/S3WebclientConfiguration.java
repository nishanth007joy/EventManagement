package com.nish.eventmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nish.eventmanagement.exception.WebClientStatusCodeHandler;
/**
 * Configuration for S3 Wen client
 * @author Nishanth Mathew Joy
 *
 */
@Configuration
public class S3WebclientConfiguration {

	@Value("${s3.base.url}")
	private String s3BaseUrl;

	/**
	 * This configure a webclient to be used for calling the S3 endpoint This has a
	 * central configuration for exception handling.
	 * 
	 * @return
	 */
	@Bean("s3WebClient")
	public WebClient getS3WebClient(ObjectMapper objectMapper) {
		ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
				.ofResponseProcessor(WebClientStatusCodeHandler::exchangeFilterResponseProcessor);

		ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(clientDefaultCodecsConfigurer -> {
			clientDefaultCodecsConfigurer.defaultCodecs()
					.jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
			clientDefaultCodecsConfigurer.defaultCodecs()
					.jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));

		}).build();
		return WebClient.builder().exchangeStrategies(strategies).filter(errorResponseFilter).baseUrl(s3BaseUrl)
				.build();
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

}
