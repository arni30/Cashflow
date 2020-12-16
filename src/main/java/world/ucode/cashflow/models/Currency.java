package world.ucode.cashflow.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Currency {
    @Id
    @Column(name="currencyId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private double ticker;
}
