package org.example.bpm.service;

import lombok.extern.slf4j.Slf4j;
import org.example.client.AccountClient;
import org.example.client.dto.DebitDto;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
public class DebitService implements JavaDelegate  {
    @Autowired
    private AccountClient accountClient;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("DebitService execution ... ");

        try {
            var debit_uuid = UUID.randomUUID().toString();
            delegateExecution.setVariable("debit_uuid", debit_uuid);
            DebitDto debitDto = new DebitDto();
            debitDto.setUuid(debit_uuid);
            debitDto.setAmount(BigDecimal.valueOf(Double.valueOf((String) delegateExecution.getVariable("amount"))));
            debitDto.setAccountId((long ) delegateExecution.getVariable("account1"));
            accountClient.debit(debitDto);
        }
        catch (Exception e) {
            delegateExecution.setVariable("debit_status", Integer.valueOf(400));
            log.info("Exception : " + e);
            log.info("DebitService result : 400");
            return;
        }

        delegateExecution.setVariable("debit_status", Integer.valueOf(200));
        log.info("DebitService result : 200");
    }
}
