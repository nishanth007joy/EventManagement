package com.nish.eventmanagement.service;

import com.nish.eventmanagement.dto.Artist;

import reactor.core.publisher.Mono;

public interface ArtistService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	Mono<Artist> getArtistByID(long id);

}