package com.example.gocardlessopenbanking.requisition.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RequisitionDetail {

    @NotBlank
    private String id;

    @NotEmpty
    private List<String> accountIds;

}
