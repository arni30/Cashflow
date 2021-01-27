package world.ucode.cashflow.models.dto;

import lombok.Data;
import world.ucode.cashflow.models.dao.Currency;

@Data
public class CurrencyDTO {
    Iterable<Currency> currencies;
}
