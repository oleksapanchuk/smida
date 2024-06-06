package org.smida.smidaApplication.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "report_details")
public class ReportDetails {
    @Id
    private UUID reportId;
    private String financialData; // JSON as String
    private String comments;
}
