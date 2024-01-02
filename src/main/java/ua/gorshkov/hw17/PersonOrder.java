package ua.gorshkov.hw17;
import jakarta.persistence.*;

import java.lang.annotation.Target;
import java.time.LocalTime;

@Entity
@Table(name = "person_order")
public class PersonOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_info")
    private String orderInfo;
    @Column(name = "order_time")
    private LocalTime orderTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "PersonOrder{" +
                "id=" + id +
                ", orderInfo='" + orderInfo + '\'' +
                ", orderTime=" + orderTime +
                "}\n";
    }

    public PersonOrder() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String order) {
        this.orderInfo = order;
    }
}
