package io.muzoo.ssc.project.backend.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Asia/Bangkok", lenient = OptBoolean.FALSE)
    private Date dateOfBirth;

}