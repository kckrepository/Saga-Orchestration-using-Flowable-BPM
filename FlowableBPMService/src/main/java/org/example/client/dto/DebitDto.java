package org.example.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class DebitDto {
    @JsonProperty("account_id")
    private long accountId;
    private BigDecimal amount;
    private String uuid;
}
