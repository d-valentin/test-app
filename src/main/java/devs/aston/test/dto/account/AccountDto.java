package devs.aston.test.dto.account;

import devs.aston.test.dto.StandardDto;
import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends StandardDto {

    private String number;

    private BigDecimal balance;

    private BeneficiaryDto beneficiary;

}
