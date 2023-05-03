package devs.aston.test.dto.beneficiary;

import devs.aston.test.dto.account.AccountDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BeneficiaryWithAccountListDto extends BeneficiaryDto {

    private List<AccountDto> accounts;

}
