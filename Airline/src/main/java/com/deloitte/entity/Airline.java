package com.deloitte.entity;

import java.util.ArrayList;
import java.util.List;

public class Airline extends BaseEntity<String> {

    private List<Routes> routes = new ArrayList<>();
    private String location;

    public Airline(String name, String id, String location, List<Routes> routes) {
        super(id, name);
        this.location = location;
        this.routes = routes;
    }

    public List<Routes> getRooms() {
        return routes;
    }

    public void setRooms(List<Routes> routes) {
        this.routes = routes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "routes=" + routes +
                ", location='" + location + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
