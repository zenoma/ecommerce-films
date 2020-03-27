package es.udc.paproject.backend.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Room;

public class RoomConversor {

	private RoomConversor() {
	}

	public final static Set<RoomDto> toRoomDtos(Set<Room> rooms) {
		return rooms.stream().map(room -> toRoomDto(room)).collect(Collectors.toSet());
	}

	public final static RoomDto toRoomDto(Room room) {
		return new RoomDto(room.getId(), room.getName(), room.getCapacity());
	}
}
