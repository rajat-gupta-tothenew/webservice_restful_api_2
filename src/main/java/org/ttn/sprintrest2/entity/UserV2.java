package org.ttn.sprintrest2.entity;

public class UserV2 {
    private Name name;

    public UserV2(Name name) {

        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
