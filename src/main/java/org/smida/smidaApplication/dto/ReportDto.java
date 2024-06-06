package org.smida.smidaApplication.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class ReportDto {
    private UUID id;
    private UUID companyId;
    private Timestamp reportDate;
    private BigDecimal totalRevenue;
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
