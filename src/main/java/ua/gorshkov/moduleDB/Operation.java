package ua.gorshkov.moduleDB;

import jakarta.persistence.*;
import lombok.Data;

@Data
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
}
