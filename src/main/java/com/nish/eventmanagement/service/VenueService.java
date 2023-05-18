package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Venue;

import reactor.core.publisher.Mono;

public interface VenueService {

	Mono<Venue> getVenueByID(long id);

}