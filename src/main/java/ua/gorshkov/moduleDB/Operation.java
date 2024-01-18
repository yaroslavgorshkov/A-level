package ua.gorshkov.moduleDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "operation_category")
    private OperationCategories operationCategory;

    @Column(name = "amount_of_money")
    private Double amountOfMoney;

    @Column(name = "category")
    private String category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_ID")
    private Account account;

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = SetterClass.setDouble("Amount of money = ",
                DoubleValidationStrategies.GREATER_OR_EQUAL_ZERO);
    }

    public void setCategory(String category) {
        this.category = SetterClass.setString("Category = ",
                StringValidationStrategies.CAPITALIZATION,
                StringValidationStrategies.NOT_EMPTY
        );
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOperationCategory(OperationCategories operationCategory) {
        this.operationCategory = operationCategory;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
