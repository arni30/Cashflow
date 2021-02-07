package world.ucode.cashflow.models.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Tag {
    @Id
    @Column(name="tagId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    //    private byte icon;
    private String description;
    private BigDecimal price;
}
