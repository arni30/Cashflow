package world.ucode.cashflow.models.dao;

import lombok.Data;
import world.ucode.cashflow.models.Role;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Users {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String login;
    private String password;
    private String email;
    String token;
    int validationStatus;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Users() {}

    public Users(String email, String login, String password) {
//        this.id = id;
        this.login = login;
        this.password = password; // &&& hash
        this.email = email;
        this.validationStatus = 1;
    }
}
