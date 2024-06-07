package org.smida.smidaApplication.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "reports")
public class ReportDetails {
    @Id
    private String id;
    @Indexed(unique = true)
    private UUID reportId;
    private Object financialData;
    private String comments;

    // Constructors
    public ReportDetails() {
    }

    public ReportDetails(UUID reportId, Object financialData, String comments) {
        this.reportId = reportId;
        this.financialData = financialData;
        this.comments = comments;
    }

    // Getters and Setters
    public String getId() {
        return id;
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
