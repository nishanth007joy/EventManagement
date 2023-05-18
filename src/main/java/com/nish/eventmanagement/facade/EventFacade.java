package com.nish.eventmanagement.facade;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.dto.Event;
import com.nish.eventmanagement.service.ArtistService;
import com.nish.eventmanagement.service.EventService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EventFacade {
	private EventService eventService;

	private ArtistService artistService;

	public EventFacade(EventService eventService, ArtistService artistService) {
		this.artistService = artistService;
		this.eventService = eventService;
	}

	public Mono<Artist> getArtistByArtistId(long artistId) {
		log.info("Getting artist information for ID {}", artistId);
		Mono<Artist> artistMono = artistService.getArtistByID(artistId);

		log.info("Getting events information for ID {}", artistId);
		Flux<Event> eventsFlux = eventService.getAllEventsForAnArtist(artistId);
		log.info("Mapping artist and events for ID {}", artistId);
		Mono<Set<Event>> eventList = eventsFlux.map(t -> {
			t.setArtists(null);
			t.setVenue(null);
			return t;
		}).collect(Collectors.toSet());

		return artistMono.flatMap(artist -> eventList.map(el -> {
			artist.setEvents(el);
			return artist;
		}));

	}

}
