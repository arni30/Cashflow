package world.ucode.cashflow.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String login;
    private String password;
    private String email;
}
