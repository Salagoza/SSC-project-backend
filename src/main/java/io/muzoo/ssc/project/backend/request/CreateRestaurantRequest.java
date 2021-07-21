package io.muzoo.ssc.project.backend.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateRestaurantRequest {

    private String name;
    private MultipartFile photo;
    private String description;
    private String address;
}
