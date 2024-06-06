package org.smida.smidaApplication.repository;

import org.smida.smidaApplication.entity.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReportDetailsRepository extends MongoRepository<ReportDetails, UUID> {
}
