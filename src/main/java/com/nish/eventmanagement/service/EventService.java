package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Event;

import reactor.core.publisher.Flux;

/**
 * Contract for implementing Event search service from backend storage
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface EventService {
	/**
	 * Get all events for the given artistId
	 * 
	 * @param artistId
	 * @return
	 */
	Flux<Event> getAllEventsForAnArtist(long artistId);

}