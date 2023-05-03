package devs.aston.test.services;

import devs.aston.test.dto.account.*;
import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import devs.aston.test.enums.Errors;
import devs.aston.test.enums.OperationType;
import devs.aston.test.exceptions.SimpleException;
import devs.aston.test.mappers.AccountMapper;
import devs.aston.test.models.Account;
import devs.aston.test.models.Beneficiary;
import devs.aston.test.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Autowired @Lazy
    private BeneficiaryService beneficiaryService;

    private final AccountTransactionService accountTransactionService;


    @Override
    @Transactional
    public AccountDto create(AccountCreateDto dto) {
        checkDto(dto);

        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        account.setBeneficiary(getBeneficiary(dto));
        account.setNumber(generateAccountNumber());
        account.setPin(hashPin(dto.getPin()));
        account = accountRepository.save(account);

        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountTransactionDto deposit(DepositDto dto) {
        System.out.println(dto);
        Optional<Account> opt = accountRepository.findByNumber(dto.getNumber());
        System.out.println(opt);
        throwIfDenyAccess(dto.getNumber(), dto.getPin());

        if (opt.isPresent()) {
            Account account = opt.get();
            account.setBalance(account.getBalance().add(dto.getValue()));
            accountRepository.save(account);
            return accountTransactionService.create(account, OperationType.DEPOSIT, dto.getValue(),
                    Timestamp.from(Instant.now()));
        }

        throw SimpleException.with(dto.getNumber(), Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
    }

    @Override
    @Transactional
    public AccountTransactionDto withdraw(WithdrawDto dto) {
        Optional<Account> opt = accountRepository.findByNumber(dto.getNumber());
        throwIfDenyAccess(dto.getNumber(), dto.getPin());

        if (opt.isPresent()) {
            Account account = opt.get();
            BigDecimal result = account.getBalance().subtract(dto.getValue());
            int compared = result.compareTo(BigDecimal.ZERO);

            if (compared < 0) {
                throw SimpleException.with(account.getBalance().toString(), Errors.NOT_ENOUGH_FUNDS_ON_BALANCE);
            } else {
                account.setBalance(result);
                accountRepository.save(account);
            }

            return accountTransactionService.create(account, OperationType.WITHDRAW, dto.getValue(),
                    Timestamp.from(Instant.now()));
        }

        throw SimpleException.with(dto.getNumber(), Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
    }

    @Override
    @Transactional
    public AccountTransactionDto transfer(TransferDto dto) {
        Optional<Account> optSource = accountRepository.findByNumber(dto.getSource());
        Optional<Account> optDest = accountRepository.findByNumber(dto.getDestination());
        throwIfDenyAccess(dto.getSource(), dto.getPin());

        if (optSource.isPresent() && optDest.isPresent()) {
            Account source = optSource.get();
            Account destination = optDest.get();

            BigDecimal sourceBalance = source.getBalance().subtract(dto.getValue());

            if (sourceBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw SimpleException.with(source.getBalance().toString(), Errors.NOT_ENOUGH_FUNDS_ON_BALANCE);
            } else {
                source.setBalance(sourceBalance);
                destination.setBalance(destination.getBalance().add(dto.getValue()));
                accountRepository.save(source);
                accountRepository.save(destination);
            }


            AccountTransactionDto sourceTransaction = accountTransactionService.create(source,
                    OperationType.SEND, dto.getValue(), Timestamp.from(Instant.now()));

            accountTransactionService.create(destination, OperationType.RECEIVE, dto.getValue(),
                    Timestamp.from(Instant.now()));

            return sourceTransaction;
        }

        if (!optSource.isPresent()) {
            throw SimpleException.with(dto.getSource(), Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
        }

        throw SimpleException.with(dto.getDestination(), Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
    }

    @Override
    public boolean hasAccess(String number, String pin) {
        Optional<Account> opt = accountRepository.findByNumber(number);

        if (opt.isPresent()) {
            Account account = opt.get();
            return account.getPin().equals(hashPin(pin));
        }

        throw SimpleException.with(number, Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
    }

    @Override
    public List<AccountDto> findByBeneficiary(String name) {
        return accountMapper.toDto(accountRepository.findByBeneficiaryName(name));
    }

    @Override
    public AccountWithTransactionListDto find(String number) {
        Optional<Account> opt = accountRepository.findByNumber(number);

        System.out.println(opt);
        if (opt.isPresent()) {
            Account account = opt.get();
            AccountWithTransactionListDto dto = accountMapper.toWithTransactionListDto(accountMapper.toDto(account));
            dto.setTransactions(accountTransactionService.findByAccount(number));
        }

        throw SimpleException.with(number, Errors.ACCOUNT_BY_NUMBER_NOT_FOUND);
    }

    private String generateAccountNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    private Beneficiary getBeneficiary(AccountCreateDto dto) {
        Optional<Beneficiary> user = beneficiaryService.findByName(dto.getName());

        BeneficiaryDto beneficiaryDto = new BeneficiaryDto();
        beneficiaryDto.setName(dto.getName());

        return user.orElseGet(() -> beneficiaryService.create(beneficiaryDto));
    }

    private void throwIfDenyAccess(String number, String pin) {
        if (!hasAccess(number, pin)) {
            throw SimpleException.with(number, Errors.PIN_IS_NOT_CORRECT);
        }
    }

    private String hashPin(String pin) {
        return DigestUtils.sha256Hex(pin);
    }

    private void checkDto(AccountCreateDto dto) {
        if (!StringUtils.hasLength(dto.getPin()) || !dto.getPin().matches("^\\d{4}$")) {
            throw SimpleException.with(dto.getPin(), Errors.PIN_CAN_BE_4_DIGITS);
        }
    }
}
