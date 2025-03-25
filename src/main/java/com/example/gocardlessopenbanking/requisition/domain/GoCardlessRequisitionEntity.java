package com.example.gocardlessopenbanking.requisition.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "gocardless_requisitions")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GoCardlessRequisitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false, unique = true)
    private UUID reference;

    @NotBlank
    @Column(name = "redirect_url", nullable = false, unique = true)
    private String redirectUrl;

    @NotBlank
    @Column(name = "institution_id", nullable = false)
    private String institutionId;

    @NotBlank
    @Column(name = "requisition_id", nullable = false, unique = true)
    private String requisitionId;

    @NotBlank
    @Column(name = "requisition_status", nullable = false)
    private String requisitionStatus;

    @NotBlank
    @Column(name = "requisition_link", nullable = false, unique = true)
    private String requisitionLink;

}
