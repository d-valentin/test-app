package devs.aston.test.services;

import devs.aston.test.dto.account.*;
import devs.aston.test.dto.account_transaction.AccountTransactionDto;

import java.util.List;

public interface AccountService {

    AccountDto create(AccountCreateDto dto);

    AccountTransactionDto deposit(DepositDto dto);

    AccountTransactionDto withdraw(WithdrawDto dto);

    AccountTransactionDto transfer(TransferDto dto);

    boolean hasAccess(String number, String pin);

    List<AccountDto> findByBeneficiary(String name);

    AccountWithTransactionListDto find(String number);

}
