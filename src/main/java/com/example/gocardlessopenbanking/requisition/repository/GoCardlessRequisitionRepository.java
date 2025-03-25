package com.example.gocardlessopenbanking.requisition.repository;

import com.example.gocardlessopenbanking.requisition.domain.GoCardlessRequisitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoCardlessRequisitionRepository extends JpaRepository<GoCardlessRequisitionEntity, Long> {
}
