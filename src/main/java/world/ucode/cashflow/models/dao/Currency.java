package world.ucode.cashflow.models.dao;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Currency {
    @Id
    @Column(name="currencyId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private double ticker;
}
