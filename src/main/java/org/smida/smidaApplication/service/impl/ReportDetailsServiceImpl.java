package org.smida.smidaApplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportDetailsServiceImpl implements ReportDetailsService {

    private final ReportDetailsRepository reportDetailsRepository;

    public ReportDetailsServiceImpl(ReportDetailsRepository reportDetailsRepository) {
        this.reportDetailsRepository = reportDetailsRepository;
    }

    @Override
    public List<ReportDetails> findAll() {
        return reportDetailsRepository.findAll();
    }
}
