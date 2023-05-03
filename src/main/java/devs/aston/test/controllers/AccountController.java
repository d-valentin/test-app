package devs.aston.test.controllers;

import devs.aston.test.dto.account.*;
import devs.aston.test.dto.account_transaction.AccountTransactionDto;
import devs.aston.test.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountDto create(@RequestBody AccountCreateDto dto) {
        return accountService.create(dto);
    }

    @PostMapping("/deposit")
    public AccountTransactionDto deposit(@RequestBody DepositDto dto) {
        return accountService.deposit(dto);
    }

    @PostMapping("/withdraw")
    public AccountTransactionDto withdraw(@RequestBody WithdrawDto dto) {
        return accountService.withdraw(dto);
    }

    @PostMapping("/transfer")
    public AccountTransactionDto transfer(@RequestBody TransferDto dto) {
        return accountService.transfer(dto);
    }

    @GetMapping
    public AccountWithTransactionListDto find(@RequestParam String number) {
        return accountService.find(number);
    }
}