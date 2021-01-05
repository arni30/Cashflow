package world.ucode.cashflow.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Wallet {
    @Id
    @Column(name="walletId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currencyId", referencedColumnName = "currencyId")
    private Currency currency;
    private Double balance;
//    private byte icon;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;
}
