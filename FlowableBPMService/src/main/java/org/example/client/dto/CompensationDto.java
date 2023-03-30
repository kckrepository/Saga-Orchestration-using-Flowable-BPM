package org.example.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompensationDto {
    @JsonProperty("trx_id")
    private String trxId;
}
