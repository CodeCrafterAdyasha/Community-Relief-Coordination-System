package com.communityrelief.model;

import java.util.Objects;

/**
 * Base user abstraction shared by residents and volunteers.
 */
public abstract class User {
    private final String id;
    private final String name;
    private final String phone;
    private final String location;

    protected User(String id, String name, String phone, String location) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.phone = Objects.requireNonNull(phone, "phone");
        this.location = Objects.requireNonNull(location, "location");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}

