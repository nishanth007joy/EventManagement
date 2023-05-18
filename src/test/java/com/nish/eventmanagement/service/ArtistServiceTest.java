package com.nish.eventmanagement.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Artist;
import com.nish.eventmanagement.service.impl.ArtistServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

	private ArtistService artistService;

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
		artistService = new ArtistServiceImpl(webClientMock);
	}

	@Test
	void testGetArtistByID() {
		Artist artist = Artist.builder().id(1).build();
		when(webClientMock.get()).thenReturn(requestHeadersUriMock);

		when(requestHeadersUriMock.uri("/artists.json")).thenReturn(requestHeadersMock);

		when(requestHeadersMock.retrieve()).thenReturn(responseMock);

		when(responseMock.bodyToFlux(Artist.class)).thenReturn(Flux.just(artist));

		Mono<Artist> artistActual = artistService.getArtistByID(1);

	}

}
