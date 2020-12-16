package world.ucode.cashflow.models;

import lombok.Data;

import javax.persistence.*;

@Data
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
