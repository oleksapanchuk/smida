package org.smida.smidaApplication.repository;

import org.smida.smidaApplication.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findAllByCompanyId(UUID companyId);
}
