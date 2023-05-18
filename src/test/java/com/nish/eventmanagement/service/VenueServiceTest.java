package com.nish.eventmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.nish.eventmanagement.dto.Venue;
import com.nish.eventmanagement.service.impl.VenueServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class VenueServiceTest {

	private VenueService venueService;

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
		venueService = new VenueServiceImpl(webClientMock);
	}

	@Test
	void test() {
		Venue venue = Venue.builder().id(1).build();
		when(webClientMock.get()).thenReturn(requestHeadersUriMock);

		when(requestHeadersUriMock.uri("/venues.json")).thenReturn(requestHeadersMock);

		when(requestHeadersMock.retrieve()).thenReturn(responseMock);

		when(responseMock.bodyToFlux(Venue.class)).thenReturn(Flux.just(venue));

		when(requestHeadersMock.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersMock);

		Mono<Venue> venueActual = venueService.getVenueByID(1);

		assertThat(venue).isEqualToComparingFieldByField(venueActual.block());
	}

}
