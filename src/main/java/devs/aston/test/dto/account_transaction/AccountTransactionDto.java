package devs.aston.test.dto.account_transaction;

import devs.aston.test.dto.account.AccountDto;
import devs.aston.test.enums.OperationType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountTransactionDto {

    private AccountDto account;

    private OperationType operationType;

    private Timestamp operationTimestamp;

}
