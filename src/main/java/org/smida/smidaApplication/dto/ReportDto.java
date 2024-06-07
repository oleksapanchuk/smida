package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
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
}
