package org.example.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class AccountDto {

    @Min(value = 0)
    private BigDecimal amount;

    @Min(value = 0)
    @Max(value = 2)
    private long id;

}
