package devs.aston.test.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StandardDto {

    private int id;

    private Timestamp createdAt;

}
