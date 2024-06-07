package org.smida.smidaApplication.repository;

import org.smida.smidaApplication.entity.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportDetailsRepository extends MongoRepository<ReportDetails, String> {
    Optional<ReportDetails> findByReportId(UUID reportId);

    void deleteByReportId(UUID uuid);

    boolean existsByReportId(UUID uuid);
}
