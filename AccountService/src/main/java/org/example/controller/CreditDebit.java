package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.DetailTrx;
import org.example.entity.TypeTrx;
import org.example.dto.*;
import org.example.repository.AccountRepository;
import org.example.repository.DetailTrxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CreditDebit {
    private final AccountRepository accountRepository;
    private final DetailTrxRepository detailTrxRepository;

    @GetMapping("/account")
    public ResponseEntity<AccountsDto> getAccounts() {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setListAccount(accountRepository.findAll());
        return new ResponseEntity<AccountsDto>(accountsDto, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        var account = accountRepository.findById(id);
        if (account.isPresent()) {
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.get().getId());
            accountDto.setAmount(account.get().getAmount());
            return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
        }

        throw new RuntimeException();
    }

    @GetMapping("/trx")
    public ResponseEntity<TrxDto> listOfTrx() {
        TrxDto trxDto = new TrxDto();
        trxDto.setListTrx(detailTrxRepository.findAll());
        return new ResponseEntity<TrxDto>(trxDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/credit")
    public ResponseEntity<CreditDto> create(@Valid @RequestBody CreditDto creditDto) {
        UUID trxId = UUID.fromString(creditDto.getUuid());
        if (!trxId.toString().equalsIgnoreCase(creditDto.getUuid())) {
            throw new RuntimeException();
        }

        var account = accountRepository.findById(creditDto.getAccountId());
        if (account.isPresent()) {
            DetailTrx detailTrx = new DetailTrx();
            detailTrx.setId(trxId);
            detailTrx.setAccountId(creditDto.getAccountId());
            detailTrx.setAmount(creditDto.getAmount());
            detailTrx.setTypeTrx(TypeTrx.CREDIT);
            detailTrxRepository.save(detailTrx);

            var accountDtl = account.get();
            accountDtl.setAmount(accountDtl.getAmount().add(creditDto.getAmount()));
            accountRepository.save(accountDtl);

            return new ResponseEntity<CreditDto>(creditDto, HttpStatus.OK);
        }

        throw new RuntimeException();
    }

    @Transactional
    @PutMapping("/debit")
    public ResponseEntity<DebitDto> create(@Valid @RequestBody DebitDto debitDto) {
        UUID trxId = UUID.fromString(debitDto.getUuid());
        if (!trxId.toString().equalsIgnoreCase(debitDto.getUuid())) {
            throw new RuntimeException();
        }

        var account = accountRepository.findById(debitDto.getAccountId());
        if (account.isPresent()) {
            DetailTrx detailTrx = new DetailTrx();
            detailTrx.setId(trxId);
            detailTrx.setAccountId(debitDto.getAccountId());
            detailTrx.setAmount(debitDto.getAmount());
            detailTrx.setTypeTrx(TypeTrx.DEBIT);
            detailTrxRepository.save(detailTrx);

            var accountDtl = account.get();
            accountDtl.setAmount(accountDtl.getAmount().subtract(debitDto.getAmount()));
            accountRepository.save(accountDtl);

            return new ResponseEntity<DebitDto>(debitDto, HttpStatus.OK);
        }

        throw new RuntimeException();
    }

    @Transactional
    @PutMapping("/compensation-debit")
    public ResponseEntity<CompensationDto> compensationDebit(@RequestBody CompensationDto compensationDto) {
        UUID trxId = UUID.fromString(compensationDto.getTrxId());
        if (!trxId.toString().equalsIgnoreCase(compensationDto.getTrxId())) {
            throw new RuntimeException();
        }

        var trx = detailTrxRepository.findById(trxId);
        if (trx.isPresent()) {
            var account = accountRepository.findById(trx.get().getAccountId()).get();
            account.setAmount(account.getAmount().add(trx.get().getAmount()));
            accountRepository.save(account);

            detailTrxRepository.deleteById(trx.get().getId());
            return new ResponseEntity<CompensationDto>(compensationDto, HttpStatus.OK);
        }

        throw new RuntimeException();
    }

    @Transactional
    @PutMapping("/compensation-credit")
    public ResponseEntity<CompensationDto> compensationCredit(@RequestBody CompensationDto compensationDto) {
        UUID trxId = UUID.fromString(compensationDto.getTrxId());
        if (!trxId.toString().equalsIgnoreCase(compensationDto.getTrxId())) {
            throw new RuntimeException();
        }

        var trx = detailTrxRepository.findById(trxId);
        if (trx.isPresent()) {
            var account = accountRepository.findById(trx.get().getAccountId()).get();
            account.setAmount(account.getAmount().subtract(trx.get().getAmount()));
            accountRepository.save(account);

            detailTrxRepository.deleteById(trx.get().getId());
            return new ResponseEntity<CompensationDto>(compensationDto, HttpStatus.OK);
        }

        throw new RuntimeException();
    }

}
