package world.ucode.cashflow.models.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Data
public class WalletDTO {
    private Integer id;
    private String name;
    private Integer currencyId;
    private BigDecimal balance;
    //    private byte icon;
}
