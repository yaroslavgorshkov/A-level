package ua.gorshkov.moduleDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accountList = new ArrayList<>();

    public void setName() {
        this.name = SetterClass.setString("User name = ",
                StringValidationStrategies.ALPHABETIC_ONLY,
                StringValidationStrategies.CAPITALIZATION,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }

    public void setSurname() {
        System.out.print("User surname = ");
        this.surname = SetterClass.setString("User surname = ",
                StringValidationStrategies.ALPHABETIC_ONLY,
                StringValidationStrategies.CAPITALIZATION,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }
}
