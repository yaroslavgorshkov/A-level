package ua.gorshkov.moduleDB.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import ua.gorshkov.moduleDB.Validation.DoubleValidationStrategies;
import ua.gorshkov.moduleDB.Entities.Enums.OperationCategories;
import ua.gorshkov.moduleDB.Validation.SetterClass;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @ManyToOne
    @JoinColumn(name = "account_ID")
    private Account account;

    @Column(name = "creation_time")
    private String creationTime;

    public Operation() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d-H");
        this.creationTime = currentTime.format(formatter);
    }

    public void setAmountOfMoneyByOwn() {
        this.amountOfMoney = SetterClass.setDouble("Amount of money = ",
                DoubleValidationStrategies.GREATER_OR_EQUAL_ZERO);
    }

    public void setCategoryByOwn() {
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

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOperationCategoryByOwn() {
        this.operationCategory =
                SetterClass.setOperationCategory("Set operation category: (0 - expense, 1 - income) ");
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    } // delete!!!

    public void setAccount(Account account) {
        this.account = account;
    }
}
