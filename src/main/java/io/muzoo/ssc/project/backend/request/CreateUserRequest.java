package io.muzoo.ssc.project.backend.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Asia/Bangkok", lenient = OptBoolean.FALSE)
    private Date dateOfBirth;
    private String password;

}
