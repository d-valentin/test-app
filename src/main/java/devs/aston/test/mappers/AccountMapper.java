package devs.aston.test.mappers;

import devs.aston.test.dto.account.AccountDto;
import devs.aston.test.models.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account account);

    List<AccountDto> toDto(List<Account> accounts);

}
