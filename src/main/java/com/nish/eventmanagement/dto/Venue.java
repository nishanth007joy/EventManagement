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
public class Venue {
	private long id;
	private String name;
	private String url;
	private String city;
	private Set<Event> events;
}
