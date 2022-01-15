package io.muzoo.ssc.project.backend.response;

import lombok.Data;

@Data
public class RestaurantResponse {

    private long id;
    private String name;
    private String Photo;
    private String address;
    private String description;
}
