package ua.gorshkov.moduleDB;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
}
