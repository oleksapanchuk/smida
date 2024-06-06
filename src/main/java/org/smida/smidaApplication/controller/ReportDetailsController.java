package org.smida.smidaApplication.controller;

import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportdetails")
public class ReportDetailsController {

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping
    public List<ReportDetails> getAllReportDetails() {
        return reportDetailsService.findAll();
    }
}
