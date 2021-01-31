package world.ucode.cashflow.models.dto;

import lombok.*;
import world.ucode.cashflow.models.dao.Category;
import world.ucode.cashflow.models.dao.Currency;
import world.ucode.cashflow.models.dao.Tag;
import world.ucode.cashflow.models.dao.Wallet;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoriesTagsDTO {
    private List<Category> categories;
    private List<Tag> tags;
}
