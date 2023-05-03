package devs.aston.test.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Beneficiary extends StandardEntity {

    private String name;

}
