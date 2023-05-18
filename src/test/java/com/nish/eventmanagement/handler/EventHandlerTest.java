package com.nish.eventmanagement.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nish.eventmanagement.facade.EventFacade;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class EventHandlerTest {

	private EventHandler eventHandler;

	@Mock
	private EventFacade eventFacade;

	@BeforeEach
	void setUp() {
		eventHandler = new EventHandler(eventFacade);
	}

	@Test
	void testGetArtistById() {
		when(eventFacade.getArtistByArtistId(anyLong())).thenReturn(Mono.empty());
		ServerRequest serverRequest =  MockServerRequest.builder().pathVariable("id", "1").build();
		Mono<ServerResponse> serverResponseActual = eventHandler.getArtistById(serverRequest);
		assertThat(HttpStatus.OK).isEqualTo(serverResponseActual.block().statusCode());
	
	}

}
