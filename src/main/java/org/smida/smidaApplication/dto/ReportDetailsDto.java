package org.smida.smidaApplication.dto;

import java.util.UUID;

public class ReportDetailsDto {

    private UUID reportId;

    private Object financialData;

    private String comments;

    public ReportDetailsDto() {
    }

    public ReportDetailsDto(UUID reportId, Object financialData, String comments) {
        this.reportId = reportId;
        this.financialData = financialData;
        this.comments = comments;
    }

    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }

    public Object getFinancialData() {
        return financialData;
    }

    public void setFinancialData(Object financialData) {
        this.financialData = financialData;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
