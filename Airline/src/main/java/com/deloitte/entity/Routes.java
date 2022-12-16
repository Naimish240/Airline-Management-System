package com.deloitte.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

public class Routes extends BaseEntity<BigInteger> {

    private int capacity;

    public Routes(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id,
            @JsonProperty("capacity") int capacity) {
        super(id, name);
        this.capacity = capacity;
    }

    public int getNumOfPeople() {
        return capacity;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.capacity = numOfPeople;
    }

    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
