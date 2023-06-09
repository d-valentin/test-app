package devs.aston.test.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class StandardEntity {

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private Timestamp createdAt;

}
