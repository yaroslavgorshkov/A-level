package ua.gorshkov.moduleDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "bank_card_number")
    private String bankCardNumber;

    @Column(name = "start_money_amount")
    private Double startMoneyAmount;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operationList = new ArrayList<>(); //?? = new ArrayList<>(); ??

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID")
    private User user;

    public void setBankCardNumber() {
        this.bankCardNumber = SetterClass.setString("Bank card number = ",
                StringValidationStrategies.NUMERIC_ONLY,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }

    public void setStartMoneyAmount() {
        this.startMoneyAmount = SetterClass.setDouble("Start money amount = ",
                DoubleValidationStrategies.GREATER_OR_EQUAL_ZERO);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
