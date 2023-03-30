package org.example.bpm.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.AccountClient;
import org.example.client.dto.CreditDto;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
public class CreditService implements JavaDelegate {
    @Autowired
    private AccountClient accountClient;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("CreditService execution ... ");

        try {
            var credit_uuid = UUID.randomUUID().toString();
            delegateExecution.setVariable("credit_uuid", credit_uuid);
            CreditDto creditDto = new CreditDto();
            creditDto.setUuid(credit_uuid);
            creditDto.setAmount(BigDecimal.valueOf(Double.valueOf((String) delegateExecution.getVariable("amount"))));
            creditDto.setAccountId((long ) delegateExecution.getVariable("account2"));
            accountClient.credit(creditDto);
        }
        catch (Exception e) {
            delegateExecution.setVariable("credit_status", Integer.valueOf(400));
            log.info("Exception : " + e);
            log.info("CreditService result : 400");
            return;
        }

        delegateExecution.setVariable("credit_status", Integer.valueOf(200));
        log.info("CreditService result : 200");
    }
}
