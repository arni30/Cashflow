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
public class Category {
    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
//    private byte icon;
    private String description;
}
