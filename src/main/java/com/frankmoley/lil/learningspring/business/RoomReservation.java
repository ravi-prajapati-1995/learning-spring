package com.frankmoley.lil.learningspring.business;

import com.frankmoley.lil.learningspring.data.Reservation;
import com.frankmoley.lil.learningspring.data.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoomReservation {
    private long roomId;
    private long guestId;
    private String roomName;
    private String roomNumber;
    private String firstName;
    private String lastName;
    private Date date;

    public static RoomReservation from(Room room) {
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setRoomId(room.getId());
        roomReservation.setRoomName(room.getName());
        roomReservation.setRoomNumber(room.getRoomNumber());
        return roomReservation;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "roomId=" + roomId +
                ", guestId=" + guestId +
                ", roomName='" + roomName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date +
                '}';
    }
}
