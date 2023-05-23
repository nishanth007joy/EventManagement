package com.nish.eventmanagement.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nish.eventmanagement.handler.EventHandler;

/**
 * Route configuration
 * 
 * @author Nishanth Mathew Joy
 *
 */
@Configuration
public class EventManagementRouteConfiguration {
	/**
	 * Configured routes for the revent management api
	 * 
	 * @param eventHandler
	 * @return
	 */
	@Bean
	RouterFunction<ServerResponse> routes(EventHandler eventHandler) {
		return route().path("/api/artists", builder -> builder
		        .GET("/{id}", accept(MediaType.APPLICATION_JSON), eventHandler::getArtistById)).build();
		

	}

}
