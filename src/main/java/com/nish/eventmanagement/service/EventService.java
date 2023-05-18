package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Event;

import reactor.core.publisher.Flux;

public interface EventService {

	Flux<Event> getAllEventsForAnArtist(long artistId);

}