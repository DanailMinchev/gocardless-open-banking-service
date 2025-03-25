package com.example.gocardlessopenbanking.institution.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Institution {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String logo;

}
