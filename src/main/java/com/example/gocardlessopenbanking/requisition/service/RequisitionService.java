package com.example.gocardlessopenbanking.requisition.service;

import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFacade;
import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFactory;
import com.example.gocardlessopenbanking.gocardless.service.GoCardlessOpenBankingTokenService;
import com.example.gocardlessopenbanking.requisition.domain.GoCardlessRequisitionEntity;
import com.example.gocardlessopenbanking.requisition.model.Requisition;
import com.example.gocardlessopenbanking.requisition.model.RequisitionDetail;
import com.example.gocardlessopenbanking.requisition.repository.GoCardlessRequisitionRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
public class RequisitionService {

    private final GoCardlessOpenBankingTokenService goCardlessOpenBankingTokenService;
    private final GoCardlessOpenBankingClientFactory goCardlessOpenBankingClientFactory;
    private final GoCardlessRequisitionRepository goCardlessRequisitionRepository;
    private final String goCardlessRedirectUrlWithoutTrailingSlash;

    public RequisitionService(GoCardlessOpenBankingTokenService goCardlessOpenBankingTokenService,
                              GoCardlessOpenBankingClientFactory goCardlessOpenBankingClientFactory,
                              GoCardlessRequisitionRepository goCardlessRequisitionRepository,
                              @Value("${gocardless.redirect-url}") String goCardlessRedirectUrl) {
        this.goCardlessOpenBankingTokenService = goCardlessOpenBankingTokenService;
        this.goCardlessOpenBankingClientFactory = goCardlessOpenBankingClientFactory;
        this.goCardlessRequisitionRepository = goCardlessRequisitionRepository;
        this.goCardlessRedirectUrlWithoutTrailingSlash = goCardlessRedirectUrl.replaceFirst("/*$", "");
    }

    @Transactional
    public Requisition createRequisition(@NotBlank String institutionId) {
        UUID reference = UUID.randomUUID();
        String redirectUrl = goCardlessRedirectUrlWithoutTrailingSlash + "/" + reference;

        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        Requisition requisition = clientFacade.createRequisition(institutionId, redirectUrl, reference);
        log.info("Created requisition: {}", requisition);

        GoCardlessRequisitionEntity newGoCardlessRequisitionEntity = createGoCardlessRequisitionEntity(reference, redirectUrl, institutionId, requisition);
        GoCardlessRequisitionEntity createdGoCardlessRequisitionEntity = goCardlessRequisitionRepository.save(newGoCardlessRequisitionEntity);
        log.info("Created requisition entity with id: {}", createdGoCardlessRequisitionEntity.getId());

        return requisition;
    }

    public RequisitionDetail getRequisition(@NotBlank String requisitionId) {
        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        return clientFacade.getRequisition(requisitionId);
    }

    private GoCardlessRequisitionEntity createGoCardlessRequisitionEntity(UUID reference,
                                                                          String redirectUrl,
                                                                          String institutionId,
                                                                          Requisition requisition) {
        return GoCardlessRequisitionEntity.builder()
                .reference(reference)
                .redirectUrl(redirectUrl)
                .institutionId(institutionId)
                .requisitionId(requisition.getRequisitionId())
                .requisitionStatus(requisition.getRequisitionStatus())
                .requisitionLink(requisition.getRequisitionLink())
                .build();
    }

}
