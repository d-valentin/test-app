package devs.aston.test.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Account extends StandardEntity {

    private String number;

    private BigDecimal balance;

    private String pin;

    @ManyToOne
    private Beneficiary beneficiary;

}
