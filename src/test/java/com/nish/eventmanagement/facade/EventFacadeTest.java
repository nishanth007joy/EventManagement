package com.nish.eventmanagement.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.dto.Event;
import com.nish.eventmanagement.service.ArtistService;
import com.nish.eventmanagement.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class EventFacadeTest {

	private EventFacade eventFacade;
	@Mock
	private EventService eventService;
	@Mock
	private ArtistService artistService;

	@BeforeEach
	void setUp() {
		eventFacade = new EventFacade(eventService, artistService);
	}

	@Test
	void testGetArtistByArtistId() {
		Event event = Event.builder().artists(Set.of(Artist.builder().id(1).build())).build();
		when(eventService.getAllEventsForAnArtist(1)).thenReturn(Flux.just(event));
		Artist artist = Artist.builder().id(1).build();
		when(artistService.getArtistByID(1)).thenReturn(Mono.just(artist));
		Mono<Artist> artistMonoActual = eventFacade.getArtistByArtistId(1);
		artist.setEvents(Set.of(event));
		assertThat(artist).isEqualToComparingFieldByField(artistMonoActual.block());
	}

}
