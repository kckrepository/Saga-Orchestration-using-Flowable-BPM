package org.example.dto;

import lombok.Data;
import org.example.entity.Account;

import java.util.List;

@Data
public class AccountsDto {
    private List<Account> listAccount;
}
