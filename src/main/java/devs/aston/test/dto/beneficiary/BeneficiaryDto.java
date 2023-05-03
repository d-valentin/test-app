package devs.aston.test.dto.beneficiary;

import devs.aston.test.dto.StandardDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BeneficiaryDto extends StandardDto {

    private String name;

}
