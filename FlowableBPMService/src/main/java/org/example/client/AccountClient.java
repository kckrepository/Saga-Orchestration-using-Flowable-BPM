package org.example.client;

import org.example.client.dto.CompensationDto;
import org.example.client.dto.CreditDto;
import org.example.client.dto.DebitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ACCOUNT-SERVICE", url = "http://localhost:8184")
public interface AccountClient {
    @PutMapping(value = "/credit")
    CreditDto credit(@RequestBody CreditDto creditDto);

    @PutMapping(value = "/debit")
    DebitDto debit(@RequestBody DebitDto debitDto);

    @PutMapping(value = "/compensation-credit")
    CompensationDto compensationCredit(@RequestBody CompensationDto compensationDto);

    @PutMapping(value = "/compensation-debit")
    CompensationDto compensationDebit(@RequestBody CompensationDto compensationDto);
}
