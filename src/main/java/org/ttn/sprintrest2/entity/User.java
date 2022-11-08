package org.ttn.sprintrest2.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

@JsonFilter("dynamic-filter")
public class User  extends RepresentationModel<User> {

    @ApiModelProperty(notes = "User ID", example = "1", required = true)
    private int id;

    @ApiModelProperty(notes = "User Name", example = "Rajat", required = true)
    private String name;
    @ApiModelProperty(notes = "User Password", example = "187456", required = true)
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
