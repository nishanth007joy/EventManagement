package com.nish.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;

import reactor.core.publisher.Mono;

public final class WebClientStatusCodeHandler {

	private WebClientStatusCodeHandler() {
	}

	/**
	 * This will handle the exception based on response from webclient
	 * 
	 * @param response
	 * @return
	 */
	public static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
		HttpStatusCode status = response.statusCode();

		if (status.isError()) {
			return response.bodyToMono(String.class)
					.flatMap(body -> Mono.error(new RemoteAPIInvocationException(body)));
		}
		return Mono.just(response);
	}

}
