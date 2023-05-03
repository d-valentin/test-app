package devs.aston.test.services;

import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import devs.aston.test.enums.OperationType;
import devs.aston.test.models.Account;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface AccountTransactionService {

    AccountTransactionDto create(Account account, OperationType type, BigDecimal value, Timestamp timestamp);

    List<AccountTransactionDto> findByAccount(String number);

}
