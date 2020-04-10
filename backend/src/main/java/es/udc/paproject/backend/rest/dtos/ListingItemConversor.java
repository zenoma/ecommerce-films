package es.udc.paproject.backend.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.ListingItem;
import static es.udc.paproject.backend.rest.dtos.MovieSessionConversor.toMovieSessionListingDtos;

public class ListingItemConversor {
	private ListingItemConversor() {
	}

	public final static Set<ListingItemDto> toListingItemDtos(Set<ListingItem> listing) {
		return listing.stream().map(listingItem -> toListingItemDto(listingItem)).collect(Collectors.toSet());
	}

	public final static ListingItemDto toListingItemDto(ListingItem listingItem) {
		return new ListingItemDto(listingItem.getMovie().getId(), listingItem.getMovie().getTitle(), toMovieSessionListingDtos(listingItem.getMovieSessions()));
	}
	
	
}