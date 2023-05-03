package devs.aston.test.services;

import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import devs.aston.test.enums.OperationType;
import devs.aston.test.mappers.AccountTransactionMapper;
import devs.aston.test.models.Account;
import devs.aston.test.models.AccountTransaction;
import devs.aston.test.repositories.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountTransactionRepository repository;

    private final AccountTransactionMapper mapper;

    @Override
    @Transactional
    public AccountTransactionDto create(Account account, OperationType type, BigDecimal value, Timestamp timestamp) {
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setOperationType(type);
        transaction.setOperationTimestamp(timestamp);
        transaction.setBalanceValue(value);
        transaction = repository.save(transaction);

        return mapper.toDto(transaction);
    }

    @Override
    public List<AccountTransactionDto> findByAccount(String number) {
        return mapper.toDto(repository.findByAccountNumber(number));
    }
}
