package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class EnrollTrainingRequest {
    private String name;
    private String email;
    private String trainingType;

    private String objective;
    private int trainingId;
}
