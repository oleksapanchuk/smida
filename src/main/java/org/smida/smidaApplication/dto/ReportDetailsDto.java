package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(
        name = "ReportDetailsDto",
        description = "Schema for Report Details data transfer object"
)
public class ReportDetailsDto {

    @Schema(
            description = "Report Details ID",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private String id;

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
}
