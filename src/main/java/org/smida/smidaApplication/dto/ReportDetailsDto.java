package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(
        name = "ReportDetailsDto",
        description = "Schema for Report Details data transfer object"
)
public class ReportDetailsDto {

    @Schema(
            description = "Report ID",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID reportId;

    @Schema(
            description = "Financial data related to the report",
            example = "{ \"revenue\": 10000, \"expenses\": 5000 }"
    )
    private Object financialData;

    @Schema(
            description = "Comments or additional information about the report",
            example = "This report includes data for the last quarter."
    )
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
