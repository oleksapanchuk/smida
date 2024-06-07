package org.smida.smidaApplication.repository;

import org.smida.smidaApplication.entity.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportDetailsRepository extends MongoRepository<ReportDetails, UUID> {
    ReportDetails findByReportId(UUID reportId);
}
