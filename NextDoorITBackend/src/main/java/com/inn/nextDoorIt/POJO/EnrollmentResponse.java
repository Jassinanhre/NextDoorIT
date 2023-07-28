package com.inn.nextDoorIt.POJO;

import com.inn.nextDoorIt.entity.ITTraining;
import lombok.Data;

import java.util.List;

@Data
public class EnrollmentResponse {
    private String userName;
    private String email;
    private List<ITTraining> enrolledTrainings;
}
