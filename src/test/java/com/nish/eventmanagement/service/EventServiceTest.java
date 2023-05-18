package com.nish.eventmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.dto.Event;
import com.nish.eventmanagement.service.impl.EventServiceImpl;

import reactor.core.publisher.Flux;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

	private EventService eventService;

	@Mock
	private WebClient webClientMock;

	@Mock
	private WebClient.RequestHeadersSpec requestHeadersMock;

	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriMock;

	@Mock
	private WebClient.ResponseSpec responseMock;

	@BeforeEach
	void setUp() {
		eventService = new EventServiceImpl(webClientMock);
	}

	@Test
	void testGetAllEventsForAnArtist() {
		Event event = Event.builder().artists(Set.of(Artist.builder().id(1).build())).build();
		when(webClientMock.get()).thenReturn(requestHeadersUriMock);

		when(requestHeadersUriMock.uri("/events.json")).thenReturn(requestHeadersMock);

		when(requestHeadersMock.retrieve()).thenReturn(responseMock);

		when(responseMock.bodyToFlux(Event.class)).thenReturn(Flux.just(event));

		when(requestHeadersMock.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersMock);

		Flux<Event> eventActual = eventService.getAllEventsForAnArtist(1);

		assertThat(event).isEqualToComparingFieldByField(eventActual.blockFirst());

	}

}
