package com.nish.eventmanagement;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArtistRouteIT {

	@Autowired
	private WebTestClient webTestClient;

	@RegisterExtension
	static WireMockExtension wireMockServer = WireMockExtension.newInstance().options(wireMockConfig().dynamicPort())
			.build();

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("s3.base.url", wireMockServer::baseUrl);
	}

	@AfterEach
	void resetAll() {

		wireMockServer.resetAll();
	}

	@Test
	void test() {
		 wireMockServer.stubFor(
			      WireMock.get(WireMock.urlEqualTo("/events.json"))
			        .willReturn(aResponse()
			          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			          .withBodyFile("s3/events-200.json"))
			    );
		 
		 wireMockServer.stubFor(
			      WireMock.get(WireMock.urlEqualTo("/artists.json"))
			        .willReturn(aResponse()
			          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			          .withBodyFile("s3/artist-200.json"))
			    );
		 
		webTestClient.get().uri("/api/artists/22").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
	}

}
