package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Venue;

import reactor.core.publisher.Mono;

/**
 * Contract for implementing Venue search service from backend storage
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface VenueService {
	/**
	 * Getting venue with venue ID
	 * 
	 * @param id
	 * @return
	 */
	Mono<Venue> getVenueByID(long id);

}