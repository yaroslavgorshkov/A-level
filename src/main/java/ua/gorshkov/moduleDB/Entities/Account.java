package ua.gorshkov.moduleDB.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import ua.gorshkov.moduleDB.Validation.DoubleValidationStrategies;
import ua.gorshkov.moduleDB.Validation.SetterClass;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "operationList")
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

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> operationList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    public void setBankCardNumberByOwn() {
        this.bankCardNumber = SetterClass.setString("Bank card number = ",
                StringValidationStrategies.NUMERIC_ONLY,
                StringValidationStrategies.WITHOUT_PASSES,
                StringValidationStrategies.NOT_EMPTY);
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public void setStartMoneyAmountByOwn() {
        this.startMoneyAmount = SetterClass.setDouble("Start money amount = ",
                DoubleValidationStrategies.GREATER_OR_EQUAL_ZERO);
    }

    public void setStartMoneyAmount(Double startMoneyAmount) {
        this.startMoneyAmount = startMoneyAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
