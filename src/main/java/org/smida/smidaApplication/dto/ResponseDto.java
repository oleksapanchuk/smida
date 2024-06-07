package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ResponseDto",
        description = "Schema for Response data transfer object"
)
public class ResponseDto {

    @Schema(
            description = "Status code",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message",
            example = "Success"
    )
    private String statusMessage;

    public ResponseDto(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
