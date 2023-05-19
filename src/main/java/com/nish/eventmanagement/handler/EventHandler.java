package com.nish.eventmanagement.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.facade.EventFacade;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class EventHandler {

	private EventFacade eventFacade;

	public EventHandler(EventFacade eventFacade) {
		this.eventFacade = eventFacade;
	}

	/**
	 * This handled the get artist by ID which respond with artist and events
	 * associated.
	 * 
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse> getArtistById(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");
		log.info("Entering getArtistById for ID {}", id);
		Mono<Artist> mono = eventFacade.getArtistByArtistId(Long.parseLong(id));
		log.info("Exiting getArtistById for ID {}", id);

		Mono<ServerResponse> notFound = ServerResponse.notFound().build();

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(mono, Artist.class)
				.switchIfEmpty(notFound);

	}
}
