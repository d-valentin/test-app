package devs.aston.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    NAME_CANNOT_BE_EMPTY("Name parameter cannot be empty"),
    NAME_ALREADY_EXISTS("This name already exists"),
    BENEFICIARY_BY_NAME_NOT_FOUND("Beneficiary by this name not found"),
    PIN_CAN_BE_4_DIGITS("PIN can be match 4 digits"),
    PIN_IS_NOT_CORRECT("PIN is not correct for this account number"),

    ACCOUNT_BY_NUMBER_NOT_FOUND("Account by this number not found"),
    NOT_ENOUGH_FUNDS_ON_BALANCE("There are not enough funds on the balance sheet");

    private final String message;
}
