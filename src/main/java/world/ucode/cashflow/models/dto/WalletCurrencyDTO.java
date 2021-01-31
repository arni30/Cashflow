package world.ucode.cashflow.models.dto;

import lombok.*;
import world.ucode.cashflow.models.dao.Currency;
import world.ucode.cashflow.models.dao.Wallet;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WalletCurrencyDTO {
    private List<Wallet> wallets;
    private List<Currency> currencies;
}
