package world.ucode.cashflow.models.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private Integer id;
    private Integer walletId;
    private String type; //(expense or income)
    private Integer categoryId;
    private Integer tagId;
    private Timestamp date;
    private String description;
}
