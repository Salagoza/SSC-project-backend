package io.muzoo.ssc.project.backend.request;
import lombok.Data;

@Data
public class CreateRatingRequest {

    private long id;
    private int rating;
}
