package devs.aston.test.models;

import devs.aston.test.enums.OperationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class AccountTransaction extends StandardEntity {

    @ManyToOne
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;

    private Timestamp operationTimestamp;

    private BigDecimal balanceValue;

}
