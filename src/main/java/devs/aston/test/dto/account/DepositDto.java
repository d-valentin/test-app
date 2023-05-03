package devs.aston.test.dto.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositDto {

    private String number;

    private BigDecimal value;

    private String pin;

}
