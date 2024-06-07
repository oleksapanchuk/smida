package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.ReportDetailsDto;

import java.util.UUID;

public interface ReportDetailsService {
    ReportDetailsDto getReportDetails(UUID uuid);
}
