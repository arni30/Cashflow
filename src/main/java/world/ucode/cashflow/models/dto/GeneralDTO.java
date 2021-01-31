package world.ucode.cashflow.models.dto;

import lombok.*;
import world.ucode.cashflow.models.dao.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Data
public class GeneralDTO {
    private List<Transaction> transactions;
    private List<Wallet> wallets;
    private List<Currency> currencies;
    private List<Category> categories;
    private List<Tag> tags;
}
