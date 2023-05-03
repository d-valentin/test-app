package devs.aston.test.mappers;

import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import devs.aston.test.models.AccountTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTransactionMapper {

    AccountTransactionDto toDto(AccountTransaction transaction);

}
