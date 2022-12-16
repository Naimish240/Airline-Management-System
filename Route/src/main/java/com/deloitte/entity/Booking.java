package com.deloitte.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends BaseEntity<String> {

    private String hotelId;
    private String userId;
    private LocalDate date;
    private LocalTime time;
    private String roomId;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Booking(String id, String name, String hotelId, String userId, LocalDate date, LocalTime time, String roomId) {
        super(id, name);
        this.hotelId = hotelId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "hotelId='" + hotelId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", roomId='" + roomId + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
