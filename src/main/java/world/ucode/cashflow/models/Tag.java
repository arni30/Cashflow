package world.ucode.cashflow.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tag {
    @Id
    @Column(name="tagId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    //    private byte icon;
    private String description;
}
