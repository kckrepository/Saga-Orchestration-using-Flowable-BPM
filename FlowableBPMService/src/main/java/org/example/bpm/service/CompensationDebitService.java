package org.example.bpm.service;

import lombok.extern.slf4j.Slf4j;
import org.example.client.AccountClient;
import org.example.client.dto.CompensationDto;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompensationDebitService implements JavaDelegate  {
    @Autowired
    private AccountClient accountClient;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("CompensationDebitService execution ... ");

        try {
            CompensationDto compensationDto = new CompensationDto();
            compensationDto.setTrxId((String) delegateExecution.getVariable("debit_uuid"));
            accountClient.compensationDebit(compensationDto);
        }
        catch (Exception e) {
            log.info("Exception : " + e);
        }
    }
}
