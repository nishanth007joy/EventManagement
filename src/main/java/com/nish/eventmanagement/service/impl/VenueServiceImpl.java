package com.nish.eventmanagement.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Venue;
import com.nish.eventmanagement.service.VenueService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class VenueServiceImpl implements VenueService {

	private WebClient webClient;

	public VenueServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Mono<Venue> getVenueByID(long id) {
		log.info("Searching Venue by ID {}", id);

		return webClient.get().uri("/venues.json").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Venue.class)
				.filterWhen(venue -> Mono.just(venue.getId() == id)).next();

	}

}
