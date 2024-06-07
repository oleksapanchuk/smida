package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Schema(
        name = "ReportDto",
        description = "Schema for Report data transfer object"
)
public class ReportDto {

    @Schema(
            description = "Report ID",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID id;

    @Schema(
            description = "ID of the company associated with the report",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID companyId;

    @Schema(
            description = "Date of the report",
            example = "2022-06-01T12:00:00Z"
    )
    private Timestamp reportDate;

    @Schema(
            description = "Total revenue in the report",
            example = "10000.00"
    )
    private BigDecimal totalRevenue;

    @Schema(
            description = "Net profit in the report",
            example = "5000.00"
    )
    private BigDecimal netProfit;

    public ReportDto() {
    }

    public ReportDto(UUID id, UUID companyId, Timestamp reportDate, BigDecimal totalRevenue, BigDecimal netProfit) {
        this.id = id;
        this.companyId = companyId;
        this.reportDate = reportDate;
        this.totalRevenue = totalRevenue;
        this.netProfit = netProfit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }
}
