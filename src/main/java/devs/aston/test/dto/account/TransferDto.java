package devs.aston.test.dto.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {

    private String source;

    private String destination;

    private BigDecimal value;

    private String pin;

}
