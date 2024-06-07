package org.smida.smidaApplication.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "reports")
public class ReportDetails {
    @Id
    private String id;
    @Indexed(unique = true)
    private UUID reportId;
    private Object financialData;
    private String comments;

    public ReportDetails() {
    }

    public ReportDetails(UUID reportId, Object financialData, String comments) {
        this.reportId = reportId;
        this.financialData = financialData;
        this.comments = comments;
    }
}
