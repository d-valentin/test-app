package devs.aston.test.dto.account;

import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountWithTransactionListDto extends AccountDto {

    private List<AccountTransactionDto> transactions;

}
