package ua.gorshkov.moduleDB.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import ua.gorshkov.moduleDB.Validation.SetterClass;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "accountList")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accountList = new ArrayList<>();

    public void setName() {
        this.name = SetterClass.setString("User name = ",
                StringValidationStrategies.ALPHABETIC_ONLY,
                StringValidationStrategies.CAPITALIZATION,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }

    public void setSurname() {
        this.surname = SetterClass.setString("User surname = ",
                StringValidationStrategies.ALPHABETIC_ONLY,
                StringValidationStrategies.CAPITALIZATION,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
