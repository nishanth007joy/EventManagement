package com.nish.eventmanagement.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nish.eventmanagement.handler.EventHandler;

@Configuration
public class EventManagementRouteConfiguration {

	@Bean
	RouterFunction<ServerResponse> routes(EventHandler eventHandler) {
		return nest(path("/api/artists"),
				nest(accept(MediaType.APPLICATION_JSON), route(GET("/{id}"), eventHandler::getArtistById)));

	}

}
