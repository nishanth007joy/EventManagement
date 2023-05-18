package com.nish.eventmanagement.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
	private String name;
	private long id;
	private String imgSrc;
	private String url;
	private int rank;
	private Set<Event> events;
}
