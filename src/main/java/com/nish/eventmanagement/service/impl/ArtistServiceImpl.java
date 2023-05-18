package com.nish.eventmanagement.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.service.ArtistService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ArtistServiceImpl implements ArtistService {

	private WebClient webClient;

	public ArtistServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Mono<Artist> getArtistByID(long id) {
		log.info("Searching Artist by ID {}", id);

		Flux<Artist> artists = webClient.get().uri("/artists.json").accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(Artist.class);
		return artists.filterWhen(t -> Mono.just(t.getId() == id)).next();
	}

}
