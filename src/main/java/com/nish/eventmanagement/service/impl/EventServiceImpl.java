package com.nish.eventmanagement.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Event;
import com.nish.eventmanagement.service.EventService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

	private WebClient webClient;

	public EventServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Flux<Event> getAllEventsForAnArtist(long artistId) {
		log.info("Getting event details for artist id {}", artistId);
		Flux<Event> events = webClient.get().uri("/events.json").accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(Event.class);
		return events
				.filterWhen(t -> Mono.just(t.getArtists().stream().anyMatch(artist -> artist.getId() == artistId)));

	}
}
