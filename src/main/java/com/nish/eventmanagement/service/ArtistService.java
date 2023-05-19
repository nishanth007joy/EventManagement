package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Artist;

import reactor.core.publisher.Mono;

/**
 * Contract for implementing Artist search service from backend storage
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface ArtistService {
	/**
	 * Get artist with the artist ID
	 * 
	 * @param id
	 * @return
	 */
	Mono<Artist> getArtistByID(long id);

}