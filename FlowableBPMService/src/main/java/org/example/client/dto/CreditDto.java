package org.example.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreditDto {
    @JsonProperty("account_id")
    private long accountId;
    private BigDecimal amount;
    private String uuid;
}
