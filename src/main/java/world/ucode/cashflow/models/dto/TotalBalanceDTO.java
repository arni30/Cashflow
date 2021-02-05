package world.ucode.cashflow.models.dto;

import lombok.*;
import world.ucode.cashflow.models.dao.Wallet;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// no validation, internal, for StatisticDTO

public class TotalBalanceDTO {
    private Wallet wallet;
    private BigDecimal totalIncomes;
    private BigDecimal totalExpenses;
    private BigDecimal totalBalance;  // incomes - expenses
}
