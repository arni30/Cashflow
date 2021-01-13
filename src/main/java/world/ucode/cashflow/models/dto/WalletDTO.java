package world.ucode.cashflow.models.dto;

import lombok.Data;

@Data
public class WalletDTO {
    private Integer id;
    private String name;
    private Integer currencyId;
    private Double balance;
    //    private byte icon;
}
