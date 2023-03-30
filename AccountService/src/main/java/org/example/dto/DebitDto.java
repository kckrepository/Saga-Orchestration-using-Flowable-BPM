package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class DebitDto {
    @Min(value = 1)
    @Max(value = 2)
    @JsonProperty("account_id")
    private long accountId;

    @Min(value = 1)
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotBlank
    @JsonProperty("uuid")
    private String uuid;
}
